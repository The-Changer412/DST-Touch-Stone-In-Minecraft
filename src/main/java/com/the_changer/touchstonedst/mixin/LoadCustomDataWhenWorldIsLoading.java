package com.the_changer.touchstonedst.mixin;

import com.the_changer.touchstonedst.TouchStoneDST;
import net.minecraft.block.Block;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.*;
import java.util.regex.Pattern;

@Mixin(PlayerManager.class)
public abstract class LoadCustomDataWhenWorldIsLoading {

    //inject into the ModifyloadFromWorld method so I can modify it
    @Inject(method = "onPlayerConnect", at = @At("HEAD"))
    protected void ModifyloadFromWorld(ClientConnection connection, ServerPlayerEntity player, CallbackInfo info)
    {
//      get the save file
        String WorldName = player.world.getServer().getServerMotd().split(" - ")[1];
        String SavePath = System.getProperty("user.dir") + File.separator + "saves" + File.separator + WorldName + File.separator + "data" + File.separator + TouchStoneDST.File;

        try
        {
            //load the file and only get the numbers
            FileReader reader = new FileReader(SavePath);
            BufferedReader br = new BufferedReader(reader);
            String[] nums = br.readLine().split("[^0-9.]");

            int pos = 0;
            int X = 0;
            int Y = 0;
            int Z = 0;

            for (int i=0; i < nums.length; i++)
            {
                //get the num
                String num = nums[i];

                //filter out the junk
                if (!num.equals("2339") && !num.equals(""))
                {
//                    TouchStoneDST.LOGGER.warn(num);

                    //place the num to the correct plane and create the block pos
                    if (pos == 0)
                    {
                        X = Integer.parseInt(num);
                        pos = 1;
                    }
                    else if (pos == 1)
                    {
                        Y = Integer.parseInt(num);
                        pos = 2;
                    }
                    else if (pos == 2)
                    {
                        Z = Integer.parseInt(num);
                        pos = 0;
                        BlockPos blockpos = new BlockPos(X, Y, Z);
                        TouchStoneDST.ACTIVATED_TOUCH_STONES.add(blockpos);

                        X = 0;
                        Y = 0;
                        Z = 0;
                    }
                }
            }
        }
        catch (Exception e)
        {
            TouchStoneDST.LOGGER.warn(e.getMessage());
        }
    }
}
