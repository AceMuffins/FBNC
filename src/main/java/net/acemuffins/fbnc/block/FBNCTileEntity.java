package net.acemuffins.fbnc.block;

import net.acemuffins.fbnc.fbncMod;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.function.Predicate;

import static com.ibm.icu.impl.duration.impl.DataRecord.EGender.N;


public class FBNCTileEntity extends TileEntity implements ITickable {
    public static int cook = 0;

    IItemHandlerModifiable input = new ItemStackHandler(1);
    IItemHandlerModifiable output = new ItemStackHandler(1);
    CombinedInvWrapper wrapper = new CombinedInvWrapper(input, output);
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(wrapper);
        }
        return super.getCapability(capability, facing);
    }
     @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }
    public static boolean matches(ItemStack stack){

        Set<FBNCRecipes> recipes = FBNCRecipeHandler.getAllRecipes();

        Predicate<FBNCRecipes> p = s -> ItemStack.areItemsEqual(s.input,stack);

        return recipes.stream().anyMatch(p);
    }

    @Override
    public void update() {
        if(!world.isRemote) {
            ItemStack itemStackInput = input.getStackInSlot(0);
            ItemStack itemStackOutput = output.getStackInSlot(0);
            int n = itemStackInput.getCount();
            if (FBNCRecipeHandler.getRecipe(itemStackInput)!=null) {
                cook++;
                if (cook > 50) {
                    itemStackInput.shrink(1);
                    if(!itemStackOutput.isEmpty()){
                        itemStackOutput.grow(FBNCRecipeHandler.getRecipe(itemStackInput).getOutput().getCount());
                    } else {
                        IItemHandler#insertItem(output, new ItemStack(A, N), simulate);
                    }
                    cook = 0;
                }
            } else {
                cook = 0;
            }
        }
    }
}