package com.slava_110.pixietable.client;

import com.slava_110.pixietable.PixieTableMod;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Collections;

@Mod.EventBusSubscriber(modid = "pixietable", value = Side.CLIENT)
public class PixieTableClient {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent ev) {
        ModelLoader.setCustomStateMapper(PixieTableMod.BLOCK_PIXIE_TABLE_CHILD, (b) -> Collections.emptyMap());
    }
}
