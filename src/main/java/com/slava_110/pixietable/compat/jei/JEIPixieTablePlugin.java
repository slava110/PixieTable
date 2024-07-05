package com.slava_110.pixietable.compat.jei;

import com.slava_110.pixietable.PixieTableMod;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEIPixieTablePlugin implements IModPlugin {

    @Override
    public void register(IModRegistry registry) {
        registry.addRecipeCatalyst(
                new ItemStack(PixieTableMod.BLOCK_PIXIE_TABLE),
                VanillaRecipeCategoryUid.CRAFTING
        );
    }
}
