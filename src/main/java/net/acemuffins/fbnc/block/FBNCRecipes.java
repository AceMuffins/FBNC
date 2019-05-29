package net.acemuffins.fbnc.block;


import net.minecraft.item.ItemStack;

public class FBNCRecipes {
    final ItemStack input,output;
    public FBNCRecipes(ItemStack input, ItemStack output){
        this.input = input;
        this.output = output;
    }
    public ItemStack getInput(){
        return input;
    }
    public ItemStack getOutput(){
        return output;
    }
}
