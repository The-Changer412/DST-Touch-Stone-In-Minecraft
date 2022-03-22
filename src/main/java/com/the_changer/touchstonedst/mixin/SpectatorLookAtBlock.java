package com.the_changer.touchstonedst.mixin;

import com.the_changer.touchstonedst.TouchStoneDSTClientInit;
import com.the_changer.touchstonedst.block.ModBlocks;
import com.the_changer.touchstonedst.block.TouchStoneBlock;
import net.minecraft.block.Block;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Locale;

@Mixin(InGameHud.class)
public abstract class SpectatorLookAtBlock{

    //inject into the shouldRenderSpectatorCrosshair method so I can modify it
    @Inject(method = "shouldRenderSpectatorCrosshair", at = @At("HEAD"), cancellable = true)
    protected void ModifyShouldRenderSpectatorCrosshair(HitResult hitResult, CallbackInfoReturnable<Boolean> cir)
    {
        //check if the player is looking at the block
        if (hitResult.getType() == HitResult.Type.BLOCK)
        {
            //get the block
            BlockPos blockPos = ((BlockHitResult)hitResult).getBlockPos();
            Block lookingBlock = MinecraftClient.getInstance().world.getBlockState(blockPos).getBlock();
            //check if the block is a touchstone
            if (lookingBlock == ModBlocks.TOUCH_STONE)
            {
                //check if the player is pressing the keybind to respawn
                if (TouchStoneDSTClientInit.TouchStoneActivateRespawnFromSpectator.wasPressed())
                {
                    //onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
                    lookingBlock.onUse(lookingBlock.getDefaultState(), MinecraftClient.getInstance().world, blockPos, MinecraftClient.getInstance().player, Hand.MAIN_HAND, ((BlockHitResult)hitResult));
                }

                //tell the user what key to press to respawn if the touchstone is activated
                if (!MinecraftClient.getInstance().getServer().getOverworld().getBlockState(blockPos).get(TouchStoneBlock.DEACTIVATED))
                {
                    String[] Keybinds = TouchStoneDSTClientInit.TouchStoneActivateRespawnFromSpectator.getBoundKeyTranslationKey().split("key.keyboard.");
                    String Keybind = Keybinds[1].toUpperCase(Locale.ROOT);
                    MinecraftClient.getInstance().player.sendMessage(Text.of("Press the " + Keybind +  " key to respawn."), true);
                }

                //show the crosshair
                cir.setReturnValue(true);
            }
        }
    }
}
