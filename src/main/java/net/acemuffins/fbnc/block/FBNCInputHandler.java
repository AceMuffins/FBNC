package net.acemuffins.fbnc.block;

import net.acemuffins.fbnc.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.items.ItemStackHandler;


public class FBNCInputHandler extends ItemStackHandler {
    @Override
    public ItemStack insertItem(int slot, ItemStack input, boolean simulate){
        if(input.getItem() == ForgeRegistries.ITEMS.getValue(new ResourceLocation("nuclearcraft:californium")) && input.getItemDamage() == 0 ) {
                return super.insertItem(slot,input,simulate);
            } else {
                return input;
        }
    }
}
