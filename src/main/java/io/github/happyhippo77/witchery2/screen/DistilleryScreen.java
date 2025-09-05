package io.github.happyhippo77.witchery2.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.happyhippo77.witchery2.Witchery2;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DistilleryScreen extends HandledScreen<DistilleryScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(Witchery2.MOD_ID, "textures/gui/distillery.png");
    private static final int[] BUBBLE_PROGRESS = new int[]{0, 6, 11, 16, 20, 24, 29};

    public DistilleryScreen(DistilleryScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(matrices, x, y);
        renderBubbles(matrices, x, y);
        renderPowerWarning(matrices, x, y);
    }

    private void renderProgressArrow(MatrixStack matrices, int x, int y) {
        if(handler.isCrafting()) {
            drawTexture(matrices, x + 68, y + 14, 176, 29, handler.getScaledProgress(), 35);
        }
    }

    private void renderBubbles(MatrixStack matrices, int x, int y) {
        if (this.handler.getProgress() > 0) {
            int n = BUBBLE_PROGRESS[this.handler.getProgress() / 2 % 7];
            if (n > 0) {
                drawTexture(matrices, x + 33, y + 20 + 29 - n, 185, 29 - n, 12, n);
            }
        }
    }

    private void renderPowerWarning(MatrixStack matrices, int x, int y) {
        if (this.handler.hasPower()) {
            drawTexture(matrices, x + 35, y + 58, 197, 0, 9, 9);
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
