package com.slava_110.pixietable.recipe;

import com.slava_110.pixietable.ConfigPixieTable;
import com.slava_110.pixietable.inventory.InventoryPixieTable;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.IShapedRecipe;

public class PixieRecipeVanilla implements IPixieRecipe {
    private final IShapedRecipe vanillaRecipe;

    public PixieRecipeVanilla(IShapedRecipe vanillaRecipe) {
        this.vanillaRecipe = vanillaRecipe;
    }

    @Override
    public int getCraftingDuration() {
        return ConfigPixieTable.vanillaRecipeTime;
    }

    @Override
    public boolean matches(InventoryPixieTable inv, World world) {
        return vanillaRecipe.matches(inv.getCraftingWrapper(), world);
    }

    @Override
    public void process(InventoryPixieTable inv) {
        ItemStack result = vanillaRecipe.getCraftingResult(inv.getCraftingWrapper());


    }
}
