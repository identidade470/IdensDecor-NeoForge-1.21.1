package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.properties.TwoDirectionProperty;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class SlidingDoor extends Block {

    public static final BooleanProperty OPEN = null;
    public static final DirectionProperty FACING = null;
    public static final BooleanProperty POWERED = null;
    public static final EnumProperty<DoubleBlockHalf> HALF = null;


    public SlidingDoor(Properties properties) {
        super(properties);

        this.registerDefaultState(this.defaultBlockState()
                .setValue(OPEN, false)
                .setValue(FACING, Direction.NORTH)
                .setValue(POWERED, false)
                .setValue(HALF, DoubleBlockHalf.LOWER));
    }


}
