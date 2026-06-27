package net.identidade.iden_decor.block.custom.templates;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class SimpleWallHorizontalBlock extends SimpleHorizontalBlock {
    public SimpleWallHorizontalBlock(Properties properties) {
        super(properties);
    }

    private boolean canAttachTo(BlockGetter blockReader, BlockPos pos, Direction direction) {
        BlockState state = blockReader.getBlockState(pos);
        return state.isFaceSturdy(blockReader, pos, direction);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        return this.canAttachTo(level, pos.relative(direction.getOpposite()), direction);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction.getOpposite() == state.getValue(FACING) && !state.canSurvive(level, pos)) {
            return Blocks.AIR.defaultBlockState();
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {

        BlockState state = this.defaultBlockState();

       for (Direction direction: context.getNearestLookingDirections()) {
           System.out.println(direction.getAxis().isHorizontal());
            if (direction.getAxis().isHorizontal()) {
                return state = state.setValue(FACING, direction.getOpposite());
            }
       }

       return null;
    }
}
