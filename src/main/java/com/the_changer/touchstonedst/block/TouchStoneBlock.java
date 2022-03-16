package com.the_changer.touchstonedst.block;

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

public class TouchStoneBlock extends Block {
    //create the deactivated property for the block
    public static final BooleanProperty DEACTIVATED = BooleanProperty.of("deactivated");

    //initialize the class
    public TouchStoneBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient() && hand == Hand.MAIN_HAND && state.get(DEACTIVATED) == true)
        {
            //save the coords
            double X = pos.getX();
            double Y = pos.getY();
            double Z = pos.getZ();
            //set the property to false
            world.setBlockState(pos, state.with(DEACTIVATED, false), Block.NOTIFY_ALL);

            //tell the player that it's activated and where it is
            player.sendMessage(Text.of(
                "This Touch Stone is now activated at " + Integer.toString(pos.getX()) + "/" + Integer.toString(pos.getY()) + "/" + Integer.toString(pos.getZ()) + ".")
                , false);

            //play the explosion particle (at least, it's suppose to)
            world.addParticle(ParticleTypes.EXPLOSION, X, Y+1, Z, 0D, 0D, 0D);

        }
        return ActionResult.SUCCESS;
    }

    //add the property to the block
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DEACTIVATED);
    }
}
