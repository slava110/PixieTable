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

public class ContainerPixieTable extends Container {
    private final TilePixieTable tile;
    public final InventoryPlayer playerInv;

    public ContainerPixieTable(TilePixieTable tile, InventoryPlayer playerInv) {
        this.tile = tile;
        this.playerInv = playerInv;

        //addSlotToContainer(new SlotCrafting(playerInv, this.craftMatrix, this.craftResult, 0, 124, 35));

        /*for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 3, 30 + j * 18, 17 + i * 18));
            }
        }*/

        for (int sloty = 0; sloty < 3; sloty++) {
            for (int slotx = 0; slotx < 9; slotx++) {
                addSlotToContainer(new Slot(playerInv, slotx + sloty * 9 + 9, 8 + slotx * 18, 84 + sloty * 18));
            }
        }

        for (int slot = 0; slot < 9; slot++) {
            addSlotToContainer(new Slot(playerInv, slot, 8 + slot * 18, 142));
        }
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
