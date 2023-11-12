package com.slava_110.pixietable.block;

import com.slava_110.pixietable.PixieTableMod;
import com.slava_110.pixietable.tile.TilePixieTable;
import com.slava_110.pixietable.tile.TilePixieTableChild;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockPixieTable extends Block {
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool CHILD = PropertyBool.create("child");

    public BlockPixieTable() {
        super(Material.WOOD);
        setRegistryName(new ResourceLocation("pixietable:pixietable"));
        setTranslationKey("pixietable");
        setDefaultState(getDefaultState().withProperty(FACING, EnumFacing.NORTH).withProperty(CHILD, false));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, CHILD);
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return super.canPlaceBlockAt(worldIn, pos);
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public EnumPushReaction getPushReaction(IBlockState state) {
        return EnumPushReaction.BLOCK;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote) {
            if(state.getValue(CHILD)) {
                TileEntity tile = worldIn.getTileEntity(pos);
                if(tile instanceof TilePixieTableChild) {
                    BlockPos masterPos = ((TilePixieTableChild) tile).getMasterTilePos();
                    playerIn.openGui(PixieTableMod.INSTANCE, 1, worldIn, masterPos.getX(), masterPos.getY(), masterPos.getZ());
                }
            } else {
                playerIn.openGui(PixieTableMod.INSTANCE, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
        }

        return true;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta & 0b011)).withProperty(CHILD, (meta & 0b100) != 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getHorizontalIndex() | (state.getValue(CHILD) ? 0b100 : 0);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return ((Boolean) state.getProperties().get(CHILD)) ? EnumBlockRenderType.MODEL : EnumBlockRenderType.INVISIBLE;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return ((Boolean) state.getProperties().get(CHILD)) ? new TilePixieTableChild() : new TilePixieTable();
    }
}
