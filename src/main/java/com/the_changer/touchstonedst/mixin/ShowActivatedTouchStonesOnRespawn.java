package com.the_changer.touchstonedst.mixin;

import com.the_changer.touchstonedst.TouchStoneDST;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ClientPlayerEntity.class)
public class ShowActivatedTouchStonesOnRespawn  {

    @Inject(method = "requestRespawn", at = @At("HEAD"))
    public void requestRespawn(CallbackInfo info)
    {
        MinecraftClient.getInstance().player.sendMessage(Text.of(
                "Uh oh. U have died. Here is the following touch stones that you had activated while you were alive:"
        ), false);
        for (int i=0; i < TouchStoneDST.ACTIVATED_TOUCH_STONES.size(); i++)
        {
            BlockPos pos = TouchStoneDST.ACTIVATED_TOUCH_STONES.get(i);
            MinecraftClient.getInstance().player.sendMessage(Text.of(
                            Integer.toString(pos.getX()) + "/" + Integer.toString(pos.getY()) + "/" + Integer.toString(pos.getZ()))
                    , false);
        }
    }
}
