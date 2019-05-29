package net.acemuffins.fbnc.block;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import java.security.spec.EllipticCurve;

public class FBNCGui extends GuiContainer {
    public static String BlockName = "Fission Based Neutron Collector";

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
        this.fontRenderer.drawString(BlockName, this.xSize / 2 - this.fontRenderer.getStringWidth(BlockName) / 2, 6, 4210752);
        this.fontRenderer.drawString("Inventory", 8, this.ySize - 96 + 2, 4210752);
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, 176, 166);
        int l = this.getFissionProgressScaled(25);
        this.drawTexturedModalRect(i + 79, j + 34, 176, 14, l , 17);
    }
    private int getFissionProgressScaled(int pixels)
    {
        int i = FBNCTileEntity.cook;
        int j = 50;
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }
}
