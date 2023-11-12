package com.slava_110.pixietable;

import com.slava_110.pixietable.block.BlockPixieTable;
import com.slava_110.pixietable.tile.TilePixieTable;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = "pixietable")
public final class PixieTableMod {
    @Mod.Instance
    public static PixieTableMod INSTANCE = null;
    @GameRegistry.ObjectHolder("pixietable:pixietable")
    public static final BlockPixieTable BLOCK_PIXIE_TABLE = null;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent ev) {
        MinecraftForge.EVENT_BUS.register(this);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandlerPixieTable());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent ev) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent ev) {

    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> ev) {
        ev.getRegistry().register(new BlockPixieTable());
        GameRegistry.registerTileEntity(TilePixieTable.class, new ResourceLocation("pixietable:pixietable"));
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> ev) {
        ev.getRegistry().register(
                new ItemBlock(BLOCK_PIXIE_TABLE)
                        .setRegistryName(new ResourceLocation("pixietable:pixietable"))
        );
    }

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent ev) {
        ModelLoader.setCustomModelResourceLocation(
                Item.getItemFromBlock(BLOCK_PIXIE_TABLE),
                0,
                new ModelResourceLocation(
                        new ResourceLocation("pixietable:pixietable"),
                        "inventory"
                )
        );
    }
}
