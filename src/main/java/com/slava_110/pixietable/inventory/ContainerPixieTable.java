package com.slava_110.pixietable.inventory;

import com.slava_110.pixietable.PixieTableMod;
import com.slava_110.pixietable.tile.TilePixieTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerPixieTable extends Container {
    private final TilePixieTable tile;
    public final InventoryPlayer playerInv;

    public ContainerPixieTable(TilePixieTable tile, InventoryPlayer playerInv) {
        this.tile = tile;
        this.playerInv = playerInv;

        // Grid
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                addSlotToContainer(new SlotItemHandler(tile.inventory, j + i * 3, 13 + j * 18, 8 + i * 19));
            }
        }

        // Output
        for(int i = 0; i < 3; ++i) {
            addSlotToContainer(new SlotItemHandler(tile.inventory, 9 + i, 147, 8 + i * 19));
        }

        // Food
        for(int i = 0; i < 7; ++i) {
            addSlotToContainer(new SlotItemHandler(tile.inventory, 14 + i, 8 + i * 24, 74));
        }

        // Player inventory
        for (int sloty = 0; sloty < 3; sloty++) {
            for (int slotx = 0; slotx < 9; slotx++) {
                addSlotToContainer(new Slot(playerInv, slotx + sloty * 9 + 9, 8 + slotx * 18, 95 + sloty * 18));
            }
        }

        // Player hotbar
        for (int slot = 0; slot < 9; slot++) {
            addSlotToContainer(new Slot(playerInv, slot, 8 + slot * 18, 153));
        }
    }

    @Override
    public ItemStack transferStackInSlot(final EntityPlayer player, final int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        final Slot slot = inventorySlots.get(index);
        if ((slot != null) && slot.getHasStack()) {
            final ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            final int containerSlots = inventorySlots.size() - player.inventory.mainInventory.size();
            if (index < containerSlots) {
                if (!mergeItemStack(itemstack1, containerSlots, inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!mergeItemStack(itemstack1, 0, containerSlots, false)) {
                return ItemStack.EMPTY;
            }
            if (itemstack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, itemstack1);
        }
        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        if (tile.getWorld().getBlockState(tile.getPos()).getBlock() != PixieTableMod.BLOCK_PIXIE_TABLE) {
            return false;
        } else {
            return playerIn.getDistanceSq(
                    tile.getPos().getX() + 0.5D,
                    tile.getPos().getY() + 0.5D,
                    tile.getPos().getZ() + 0.5D
            ) <= 64.0D;
        }
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn) {
        super.onCraftMatrixChanged(inventoryIn);
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);

        /*if (!tile.getWorld().isRemote) {
            this.clearContainer(playerIn, this.world, this.craftMatrix);
        }*/
    }
}
