package net.acemuffins.fbnc.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {

    public static Block machineFBNC = new FBNCBlock(Material.IRON).setCreativeTab(CreativeTabs.MATERIALS).setRegistryName("machine_fbnc").setUnlocalizedName("machine_fbnc");
    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(
                machineFBNC
        );

    }
    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        new ItemBlock(machineFBNC).setRegistryName(machineFBNC.getRegistryName());
        registry.register(new ItemBlock(machineFBNC).setRegistryName(machineFBNC.getRegistryName()));
        GameRegistry.registerTileEntity(FBNCTileEntity.class, new ResourceLocation("fbnc", "fbnc_tile"));

    }
    public static void registerModels() {
        ((FBNCBlock)machineFBNC).registerItemModel(Item.getItemFromBlock(machineFBNC));
    }

}