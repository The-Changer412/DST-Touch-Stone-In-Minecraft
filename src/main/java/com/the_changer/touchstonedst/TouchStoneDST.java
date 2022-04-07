package com.the_changer.touchstonedst;

import com.the_changer.touchstonedst.block.ModBlocks;
import com.the_changer.touchstonedst.structures.RegisterStructures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginConnectionEvents;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TouchStoneDST implements ModInitializer {
	//save the mod id for easy access
	public static final String MOD_ID = "touchstonedst";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final List<BlockPos> ACTIVATED_TOUCH_STONES = new ArrayList<BlockPos>();
	public static String SavePath = null;
	public static String File = "ActivatedTouchStone.txt";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Hello Fabric world!");

		//register the items blocks, and structures
		ModBlocks.registerModBlocks();
		RegisterStructures.registerStructureFeatures();
	}
}
