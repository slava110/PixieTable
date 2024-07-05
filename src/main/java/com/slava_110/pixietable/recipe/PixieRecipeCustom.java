package com.slava_110.pixietable.recipe;

import com.slava_110.pixietable.inventory.InventoryPixieTable;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class PixieRecipeCustom extends IForgeRegistryEntry.Impl<PixieRecipeCustom> implements IPixieRecipe {

    @Override
    public int getCraftingDuration() {
        return 0;
    }

    @Override
    public boolean matches(InventoryPixieTable inv, World world) {
        return false;
    }

    @Override
    public void process(InventoryPixieTable inv) {

    }

}
