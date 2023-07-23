package io.github.happyhippo77.witchery2.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.happyhippo77.witchery2.Witchery2;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class WitchsOvenScreen extends HandledScreen<WitchsOvenScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(Witchery2.MOD_ID, "textures/gui/witchs_oven.png");

    public WitchsOvenScreen(WitchsOvenScreenHandler handler, PlayerInventory inventory, Text title) {
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
        renderFuel(matrices, x, y);
    }

    private void renderProgressArrow(MatrixStack matrices, int x, int y) {
        if(handler.isCrafting()) {
            drawTexture(matrices, x + 80, y + 20, 176, 14, handler.getScaledProgress(), 17);
        }
    }

    private void renderFuel(MatrixStack matrices, int x, int y) {
        drawTexture(matrices, x + 56, y + 36 + (14 - handler.getScaledFuel()), 176, 14 - handler.getScaledFuel(), 14, handler.getScaledFuel());
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
