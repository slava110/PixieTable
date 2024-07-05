package com.slava_110.pixietable.recipe;

import com.slava_110.pixietable.inventory.InventoryPixieTable;
import net.minecraft.world.World;

public interface IPixieRecipe {

    int getCraftingDuration();

    boolean matches(InventoryPixieTable inv, World world);

    void process(InventoryPixieTable inv);


}
