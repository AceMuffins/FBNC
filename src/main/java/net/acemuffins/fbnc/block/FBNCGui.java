package net.acemuffins.fbnc.block;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class FBNCGui extends GuiContainer {
    public FBNCGui(FBNCContainer cont) {
        super(cont);
    }
    private static final ResourceLocation background = new ResourceLocation("fbnc", "textures/fbnc.png");
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = "Fission Based Neutron Collector";
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, 176, 166);
    }
}
