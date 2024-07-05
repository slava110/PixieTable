package com.slava_110.pixietable.block;

import com.slava_110.pixietable.PixieTableMod;
import com.slava_110.pixietable.tile.TilePixieTableChild;
import net.minecraft.block.Block;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockPixieTableChild extends Block {

    public BlockPixieTableChild() {
        super(Material.WOOD);
        setRegistryName(new ResourceLocation("pixietable:pixietablechild"));
        setHardness(2.0F);
        setResistance(5.0F);
        setTranslationKey("pixietable");
        disableStats();
        translucent = true;
    }

    /*@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return getMasterPos(source, pos).subtract(pos).;
    }*/

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return false;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(PixieTableMod.BLOCK_PIXIE_TABLE);
    }

    @Override
    public boolean addDestroyEffects(World world, BlockPos pos, ParticleManager manager) {
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        BlockPos masterPos = getMasterPos(worldIn, pos);

        if(masterPos != null) {
            worldIn.setBlockState(masterPos, Blocks.AIR.getDefaultState());
        }

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
            BlockPos masterPos = getMasterPos(worldIn, pos);
            if(masterPos != null)
                playerIn.openGui(PixieTableMod.INSTANCE, 1, worldIn, masterPos.getX(), masterPos.getY(), masterPos.getZ());
        }

        return true;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.INVISIBLE;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TilePixieTableChild();
    }

    @Nullable
    private static BlockPos getMasterPos(IBlockAccess world, BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);
        if(tile instanceof TilePixieTableChild) {
            return ((TilePixieTableChild) tile).getMasterTilePos();
        }
        return null;
    }
}
