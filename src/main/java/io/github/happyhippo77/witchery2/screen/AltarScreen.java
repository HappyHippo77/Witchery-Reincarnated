package io.github.happyhippo77.witchery2.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.happyhippo77.witchery2.Witchery2;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class AltarScreen extends HandledScreen<AltarScreenHandler> {
    private static final Identifier TEXTURE = new Identifier(Witchery2.MOD_ID, "textures/gui/altar.png");

    public AltarScreen(AltarScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 176;
        this.backgroundHeight = 88;
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
    }

    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
    }

    private void renderStats(MatrixStack matrices) {
        String power = String.format("%.0f / %.0f (x%d)", (float) handler.propertyDelegate.get(0), (float) handler.propertyDelegate.get(1) * (float) handler.propertyDelegate.get(3), handler.propertyDelegate.get(2));
        drawCenteredTextWithShadow(matrices, textRenderer, power, width / 2, height / 2, 16777215);
        drawCenteredTextWithShadow(matrices, textRenderer, "Altar", width / 2, height / 2 - 20, 16777215);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        renderStats(matrices);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
