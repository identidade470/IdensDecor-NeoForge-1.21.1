package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleHorizontalBlock;
import net.identidade.iden_decor.block.custom.templates.SimpleMutiDectionalBlock;
import net.identidade.iden_decor.block.properties.ThreeConnectableProperty;
import net.identidade.iden_decor.block.properties.TwoConnectableProperty;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class FluorescentLightBlock extends SimpleMutiDectionalBlock {

    public static final EnumProperty<ThreeConnectableProperty> PART = EnumProperty.create("part", ThreeConnectableProperty.class);

    public FluorescentLightBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(defaultBlockState().setValue(FACING,Direction.NORTH).setValue(FACE,AttachFace.FLOOR).setValue(PART, ThreeConnectableProperty.SINGLE));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        Direction facing = state.getValue(FACING);
        AttachFace face = state.getValue(FACE);

//        boolean relevant = switch (face) {
//            case FLOOR, CEILING -> direction == facing || direction == facing.getOpposite();
//            case WALL -> direction == Direction.UP || direction == Direction.DOWN;
//        };

        boolean relevant = false;
        if (direction == facing.getClockWise() || direction == facing.getCounterClockWise()) {
            relevant = true;
        }

        if (!relevant) return state;

        return state.setValue(PART, updatePart(level, pos, state));
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);

        if (!level.isClientSide) {
            Direction facing = state.getValue(FACING);

            Direction leftDir = facing.getCounterClockWise();
            Direction rightDir = facing.getClockWise();

            level.updateNeighborsAt(pos.relative(leftDir), this);
            level.updateNeighborsAt(pos.relative(rightDir), this);
        }
    }

    private ThreeConnectableProperty updatePart(LevelAccessor level, BlockPos pos, BlockState state) {
        Direction facing = state.getValue(FACING);
        AttachFace face = state.getValue(FACE);

        Direction leftDir = facing.getCounterClockWise();
        Direction rightDir = facing.getClockWise();

        BlockState leftNeighbor = level.getBlockState(pos.relative(leftDir));
        BlockState rightNeighbor = level.getBlockState(pos.relative(rightDir));
//        BlockState upNeighbor = level.getBlockState(pos.relative(upDir));
//        BlockState downNeighbor = level.getBlockState(pos.relative(downDir));

        boolean left = leftNeighbor.is(this) && leftNeighbor.getValue(FACING) == facing && leftNeighbor.getValue(FACE) == face;
        boolean right = rightNeighbor.is(this) && rightNeighbor.getValue(FACING) == facing  && rightNeighbor.getValue(FACE) == face;
//        boolean up = upNeighbor.is(this) && upNeighbor.getValue(FACING) == facing && upNeighbor.getValue(FACE) == face;
//        boolean down = downNeighbor.is(this) && downNeighbor.getValue(FACING) == facing && downNeighbor.getValue(FACE) == face;

//        switch (face) {
//            case FLOOR -> {
//                if (front && back) return ThreeConnectableProperty.CENTER;
//                if (front) return ThreeConnectableProperty.RIGHT;
//                if (back) return ThreeConnectableProperty.LEFT;
//            }
//            case WALL -> {
//                if (up && down) return ThreeConnectableProperty.CENTER;
//                if (down) return ThreeConnectableProperty.RIGHT;
//                if (up) return ThreeConnectableProperty.LEFT;
//            }
//            case CEILING -> {
//                if (front && back) return ThreeConnectableProperty.CENTER;
//                if (back) return ThreeConnectableProperty.RIGHT;
//                if (front) return ThreeConnectableProperty.LEFT;
//            }
//        }

        if (left && right) return ThreeConnectableProperty.CENTER;
        if (left) return ThreeConnectableProperty.RIGHT;
        if (right) return ThreeConnectableProperty.LEFT;




        return ThreeConnectableProperty.SINGLE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PART);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);

        if (state==null) {
            return null;
        }

        ThreeConnectableProperty part = updatePart(context.getLevel(), context.getClickedPos(), state);

        return state.setValue(PART, part);
    }

    @Override
    protected boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        AttachFace face = state.getValue(FACE);
        Direction dir = state.getValue(FACING);

        if (face== AttachFace.CEILING) {
            return (dir.getAxis()== Direction.Axis.X)
                    ?Block.box(5, 12, 0, 11, 16, 16)
                    :Block.box(0, 12, 5, 16, 16, 11);
        }
        if (face==AttachFace.FLOOR) {
            return (dir.getAxis()== Direction.Axis.X)
                    ?Block.box(5, 0, 0, 11, 4, 16)
                    :Block.box(0, 0, 5, 16, 4, 11);
        }

        return switch (dir) {
            case SOUTH -> Block.box(0, 5, 0, 16, 11, 4);
            case EAST -> Block.box(0, 5, 0, 4, 11, 16);
            case NORTH -> Block.box(0, 5, 12, 16, 11, 16);
            case WEST -> Block.box(12, 5, 0, 16, 11, 16);
            default -> Block.box(0,0,0,16,16,16);
        };
    }
}
