package net.acemuffins.fbnc.block;

import net.acemuffins.fbnc.fbncMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class FBNCBlock extends Block {
    public FBNCBlock(Material materialIn) {
        super(materialIn);
    }

    public void registerItemModel(Item itemBlock) {
        fbncMod.proxy.registerItemRenderer(itemBlock, 0, "machine_fbnc");
    }
    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new FBNCTileEntity();
    }
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        }
        TileEntity te = world.getTileEntity(pos);
        if (!(te instanceof FBNCTileEntity)) {
            return false;
        }
        player.openGui(fbncMod.instance, 1, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
