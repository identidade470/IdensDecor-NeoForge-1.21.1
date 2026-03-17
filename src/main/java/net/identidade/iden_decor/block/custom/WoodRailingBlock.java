package net.identidade.iden_decor.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class WoodRailingBlock extends FenceBlock {

    public static final BooleanProperty UP = BooleanProperty.create("up");

    public WoodRailingBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(UP, false));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {

        if (!level.getBlockState(currentPos.above()).isAir()) {
            return this.defaultBlockState()
                    .setValue(UP, true);
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(UP);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        if (!context.getLevel().getBlockState(context.getClickedPos().above()).isAir()) {
            return this.defaultBlockState()
                    .setValue(UP, true);
        }
        return super.getStateForPlacement(context);
    }
}
