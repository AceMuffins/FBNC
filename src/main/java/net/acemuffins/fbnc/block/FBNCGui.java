package net.acemuffins.fbnc.block;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class FBNCGui extends GuiContainer {
    public static String BlockName = "Fission Based Neutron Collector";

    private EntityPlayer player;
    private BlockPos pos;
    public FBNCGui(FBNCContainer cont, EntityPlayer playerEnt, BlockPos position) {
        super(cont);
        this.player = playerEnt;
        this.pos = position;
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
        this.fontRenderer.drawString("Inventory", 8, this.ySize - 96 + 2+37+6, 4210752);
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, 176, 202+6);
        int l = this.getFissionProgressScaled(25);
        int ll = this.getFissionProgressScaled(105);
        this.drawTexturedModalRect(i + 118, j + 24, 176, 14, l , 17);
        this.drawTexturedModalRect(i + 146-ll, j + 61, 104-ll, 209, ll , 47);
    }
    private int getFissionProgressScaled(int pixels)
    {
        final TileEntity te = this.player.world.getTileEntity(this.pos);
        if (te instanceof FBNCTileEntity) {
            final FBNCTileEntity real = (FBNCTileEntity) te;
            final int i = real.cook;
            int j = 1200;
            return i != 0 ? i * pixels / j : 0;
        }
        return -1;
    }
}
