package com.slava_110.pixietable.compat.crafttweaker;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.recipes.ICraftingRecipe;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.pixieworkshop.PixieWorkshop")
public class CTPixieWorkshop {

    @ZenMethod
    public static void setVanillaRecipeDuration(ICraftingRecipe recipe, int duration) {

    }

    @ZenMethod
    public static void addPixie(IIngredient input, @Optional IItemStack returnedStack) {

    }
}
