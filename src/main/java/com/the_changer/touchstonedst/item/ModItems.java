package com.the_changer.touchstonedst.item;

import com.the_changer.touchstonedst.TouchStoneDST;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    //create the item
    public static final Item MYTHRIL_INGOT = registerItem("mythril_ingot",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    public static final Item MYTHRIL_NUGGET = registerItem("mythril_nugget",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    //create a method that will register items
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(TouchStoneDST.MOD_ID, name), item);
    }

    //register the modded items to the mod
    public static void registerModItems() {
        TouchStoneDST.LOGGER.info("Registering Mod Items for " + TouchStoneDST.MOD_ID);
    }
}
