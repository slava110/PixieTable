package com.slava_110.pixietable.inventory;

import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

/**
 * Pixie table inventory
 * Slots 0-8 - Grid
 * Slot 9-11 - Result
 * Slot 12-13 - Pixie jar slot
 * Slots 14-20 - Food
 */
public class InventoryPixieTable extends ItemStackHandler {

    public InventoryPixieTable() {
        setSize(21);
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        if(slot >= 0 && slot <= 8)
            return true; //TODO
        else if(slot == 12)
            return stack.getItem() == Items.MUSHROOM_STEW;
        else if(slot >= 14 && slot <= 20)
            return stack.getItem() instanceof ItemFood;
        else
            return false;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return super.insertItem(slot, stack, simulate);
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return super.extractItem(slot, amount, simulate);
    }
}
