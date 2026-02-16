package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleHorizontalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WallLampBlock extends SimpleHorizontalBlock {
    public WallLampBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockPos = pos.relative(state.getValue(FACING).getOpposite());
        return level.getBlockState(blockPos).isFaceSturdy(level, blockPos, state.getValue(FACING).getOpposite());
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case WEST -> Block.box(9, 5, 3, 16, 12, 13);
            case SOUTH -> Block.box(3, 5, 0, 13, 12, 7);
            case EAST -> Block.box(0, 5, 3, 7, 12, 13);
            default -> Block.box(3, 5, 9, 13, 12, 16);
        };
    }
}
