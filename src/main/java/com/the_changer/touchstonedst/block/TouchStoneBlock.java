package com.the_changer.touchstonedst.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;
import java.util.Random;

public class TouchStoneBlock extends Block {
    //create the deactivated property for the block
    public static final BooleanProperty DEACTIVATED = BooleanProperty.of("deactivated");
    public static final BooleanProperty NOT_PLAYED_PARTICLE = BooleanProperty.of("not_played_particle");

    //initialize the class
    public TouchStoneBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        //make it only run when in the server, with the main hand event, and if the stone is deactivated
        if (!world.isClient() && hand == Hand.MAIN_HAND && state.get(DEACTIVATED))
        {
            //set the property to false
            world.setBlockState(pos, state.with(DEACTIVATED, false), NOTIFY_ALL);

            //tell the player that it's activated and where it is
            player.sendMessage(Text.of(
                "This Touch Stone is now activated at " + Integer.toString(pos.getX()) + "/" + Integer.toString(pos.getY()) + "/" + Integer.toString(pos.getZ()) + ".")
                , false);

        }

        //only trigger in spectator mode with the block activated
        if (world.isClient() && player.isSpectator() && !world.getBlockState(pos).get(DEACTIVATED))
        {
            //get the list of players in the server
            List<ServerPlayerEntity> listOfPlayers =  MinecraftClient.getInstance().getServer().getPlayerManager().getPlayerList();
            for (int i=0; i<listOfPlayers.size(); i++)
            {
                //check to see if any of the players on the server is the user
                if (listOfPlayers.get(i).getDisplayName().equals(player.getDisplayName()))
                {
                    //spawn a lighting on the block
                    LightningEntity lightningEntity = (LightningEntity) EntityType.LIGHTNING_BOLT.create(world);
                    lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(pos));
                    MinecraftClient.getInstance().getServer().getOverworld().spawnEntity(lightningEntity);

                    //set the client gamemode to survival
                    listOfPlayers.get(i).changeGameMode(GameMode.SURVIVAL);

                    //spawn the player on top of the touch stone
                    listOfPlayers.get(i).teleport(pos.getX(), pos.getY()+1, pos.getZ());

                    //destroy the block
                    MinecraftClient.getInstance().getServer().getOverworld().breakBlock(pos, false);

                    //send a message to the player
                    player.sendMessage(Text.of("You have been brought back from the dead. Now, don't go dying again."), false);
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    //play the explosion particle effect when the touch stone is activated
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        //check to see if the stone is activated, and the particle hasn't been played before
        if (!state.get(DEACTIVATED) && state.get(NOT_PLAYED_PARTICLE)){
            //play the explosion particle at the coords with zero velocity
            world.addParticle(ParticleTypes.EXPLOSION, pos.getX(), pos.getY(), pos.getZ(), 0.0D, 0.0D, 0.0D);
            //set the not played particle to false
            world.setBlockState(pos, state.with(NOT_PLAYED_PARTICLE, false), Block.NOTIFY_ALL);
        }
    }

    //add the property to the block
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DEACTIVATED, NOT_PLAYED_PARTICLE);
    }
}
