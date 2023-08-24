package com.slava_110.pixietable;

import com.slava_110.pixietable.client.gui.GuiPixieTable;
import com.slava_110.pixietable.inventory.ContainerPixieTable;
import com.slava_110.pixietable.tile.TilePixieTable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandlerPixieTable implements IGuiHandler {


    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 1:
                TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
                if(tile instanceof TilePixieTable) {
                    return new GuiPixieTable(new ContainerPixieTable((TilePixieTable) tile, player.inventory));
                }
        }
        return null;
    }

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 1:
                TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
                if(tile instanceof TilePixieTable) {
                    return new ContainerPixieTable((TilePixieTable) tile, player.inventory);
                }
        }
        return null;
    }
}
