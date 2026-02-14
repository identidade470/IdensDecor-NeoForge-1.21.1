package net.identidade.iden_decor.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class FloodLampBlock extends FaceAttachedHorizontalDirectionalBlock {
    public static final MapCodec<FloodLampBlock> CODEC = simpleCodec(FloodLampBlock::new);

    public FloodLampBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACE, AttachFace.FLOOR)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {

        AttachFace face = state.getValue(FACE);
        Direction facing = state.getValue(FACING);

        switch (face) {
            case WALL -> {
                return switch (facing) {
                    case EAST -> Block.box(0, 4, 3, 7, 12, 13);
                    case WEST -> Block.box(9, 4, 3, 16, 12, 13);
                    case SOUTH -> Block.box(3, 4, 0, 13, 12, 7);
                    default -> Block.box(3, 4, 9, 13, 12, 16);
                };
            }
            case CEILING -> {
                return switch (facing) {
                    case EAST, WEST -> Block.box(4, 9, 3, 12, 16, 13);
                    default -> Block.box(3, 9, 4, 13, 16, 12);
                };
            }
            case FLOOR -> {
                return  switch (facing) {
                    case EAST, WEST -> Block.box(4, 0, 3, 12, 7, 13);
                    default -> Block.box(3, 0, 4, 13, 7, 12);
                };
            }
        }

        return Block.box(0,0,0,16,16,16);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        for (Direction direction : context.getNearestLookingDirections()) {
            BlockState blockState;
            if (direction.getAxis() == Direction.Axis.Y) {
                blockState = this.defaultBlockState().setValue(FACE, direction == Direction.UP ? AttachFace.CEILING : AttachFace.FLOOR).setValue(FACING, context.getHorizontalDirection().getOpposite());
            } else {
                blockState = this.defaultBlockState().setValue(FACE, AttachFace.WALL).setValue(FACING, direction.getOpposite());
            }

            if (blockState.canSurvive(context.getLevel(), context.getClickedPos())) {
                return blockState;
            }
        }

        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACE, FACING);
    }

    @Override
    protected MapCodec<? extends FaceAttachedHorizontalDirectionalBlock> codec() {
        return CODEC;
    }
}
