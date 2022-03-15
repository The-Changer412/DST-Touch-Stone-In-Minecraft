package com.the_changer.touchstonedst.block;

import com.the_changer.touchstonedst.TouchStoneDST;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    //create the blocks
    public static final Block MYTHRIL_BLOCK = registerBlock("mythril_block",
            new Block(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool()), ItemGroup.MISC);
    public static final Block MYTHRIL_ORE = registerBlock("mythril_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(6f).requiresTool()), ItemGroup.MISC);

    //register a new block item
    private static Item registerBlockItem(String name, Block block, ItemGroup group){
        return Registry.register(Registry.ITEM, new Identifier(TouchStoneDST.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(group)));
    }

    //register a block
    private static Block registerBlock(String name, Block block, ItemGroup group){
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(TouchStoneDST.MOD_ID, name), block);
    }

    //register the modded blocks to the mod
    public static void registerModBlocks(){
        TouchStoneDST.LOGGER.info("Registering Mod Blocks for "+ TouchStoneDST.MOD_ID);
    }

}
