package com.the_changer.touchstonedst.block;

import com.the_changer.touchstonedst.TouchStoneDST;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
        return ActionResult.SUCCESS;
    }

    //play the explosion particle effect when the touch stone is activated
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        //check to see if the stone is activated, and the particle hasn't been played before
        if (!state.get(DEACTIVATED) && state.get(NOT_PLAYED_PARTICLE)){
            //get the coords
            double X = (double)pos.getX() + 0.5D;
            double Y = (double)pos.getY() + 0.7D;
            double Z = (double)pos.getZ() + 0.5D;
            //play the explosion particle at the coords with zero velocity
            world.addParticle(ParticleTypes.EXPLOSION, X, Y, Z, 0.0D, 0.0D, 0.0D);
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
