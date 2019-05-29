package net.acemuffins.fbnc.block;

import net.minecraft.item.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class FBNCRecipeHandler
{
    static Set<FBNCRecipes> fbncRecipes = new HashSet<>();

    public static Set<FBNCRecipes> getAllRecipes()
    {
        return fbncRecipes;
    }

    public static void addFBNCRecipe(ItemStack input, ItemStack output)
    {
        fbncRecipes.add(new FBNCRecipes(input, output));
    }

    public static FBNCRecipes getRecipe(ItemStack input)
    {
        for (FBNCRecipes recipe : fbncRecipes)
        {
            if (input.isItemEqual(recipe.getInput()))
            {
                return recipe;
            }
        }

        return null;
    }
    public static ItemStack getRecipeOutput(ItemStack input)
    {

        FBNCRecipes recipe = getRecipe(input);
        if(recipe != null)
        {
            return recipe.getOutput();
        }
        return null;
    }
}
