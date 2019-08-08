package net.acemuffins.fbnc.block;

import net.acemuffins.fbnc.fbncMod;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
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



public class FBNCTileEntity extends TileEntity implements ITickable {
    public int cook = 0;
    ItemStackHandler input = new ItemStackHandler(1);
    ItemStackHandler output1 = new ItemStackHandler(1);
    CombinedInvWrapper wrapper = new CombinedInvWrapper(input, output1);
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if(facing == EnumFacing.UP)
                return (T) input;
            if(facing == EnumFacing.DOWN)
                return (T) output1;
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
            ItemStack recipeOut = FBNCRecipeHandler.getRecipeOutput(itemStackInput);
            if(recipeOut != null && output1.insertItem(0, recipeOut, true).isEmpty()){
                recipeOut = recipeOut.copy();
                cook++;
                if(cook%4==0){
                    this.sync();
                }
                if (cook > 1200) {
                    itemStackInput.shrink(1);
                    this.output1.insertItem(0, recipeOut, false);
                    cook = 0;
                }
            } else {
                cook = 0;
            }
        }
    }
    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.cook = compound.getInteger("CookTime");
        this.input.deserializeNBT(compound.getCompoundTag("input"));
        this.output1.deserializeNBT(compound.getCompoundTag("output"));
    }
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("CookTime", (short)this.cook);
        compound.setTag("input", this.input.serializeNBT());
        compound.setTag("output", this.output1.serializeNBT());
        return compound;
    }
    @Override
    public NBTTagCompound getUpdateTag(){
        return this.writeToNBT(new NBTTagCompound());
    }
    @Override
    public SPacketUpdateTileEntity getUpdatePacket(){
        return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
    }
    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        this.handleUpdateTag(pkt.getNbtCompound());
    }
    public void sync(){
        this.world.notifyBlockUpdate(this.pos, this.world.getBlockState(this.pos), this.world.getBlockState(this.pos), 3);
        this.markDirty();
    }
}