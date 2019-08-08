package net.acemuffins.fbnc.block;

import net.acemuffins.fbnc.block.FBNCTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public class FBNCContainer extends Container {
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
    int inputFuel = 0;
    int outputNeutron = 1;
    int addheight = 6;
    private final FBNCTileEntity te;
    public FBNCContainer(IInventory playerInventory, FBNCTileEntity te) {
        this.te = te;
        IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        //y=17 is really good
        this.addSlotToContainer(new SlotItemHandler(itemHandler, inputFuel, 97, 24));
        this.addSlotToContainer(new SlotItemHandler(itemHandler, outputNeutron, 21, 62));
        for(int i=0;i<3;++i){
            for(int j=0;j<9;++j){
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 37+84+addheight + i * 18));
            }
        }
        for(int k=0;k<9;++k) {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142+37+addheight));
        }

    }
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == 1)
            {
                if (!this.mergeItemStack(itemstack1, 3, 38, true))
                {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 0)
            {
                if (!this.mergeItemStack(itemstack1, 0, 1, false))
                {
                    return ItemStack.EMPTY;
                } else
                if (index >= 2 && index < 29)
                {
                    if (!this.mergeItemStack(itemstack1, 29, 38, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 29 && index < 38 && !this.mergeItemStack(itemstack1, 2, 29, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 2, 38, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
}

