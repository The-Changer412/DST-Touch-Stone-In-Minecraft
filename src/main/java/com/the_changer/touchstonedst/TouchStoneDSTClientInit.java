package com.the_changer.touchstonedst;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class TouchStoneDSTClientInit implements ClientModInitializer {
    public static KeyBinding TouchStoneActivateRespawnFromSpectator;

    //add in a new keybind so the player can respawn in spectator mode
    @Override
    public void onInitializeClient() {
        TouchStoneActivateRespawnFromSpectator = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.touchstonedst.TouchStoneActivateRespawnFromSpectator",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_H,
                "category.touchstonedst.KeyBinds"
        ));
    }
}
