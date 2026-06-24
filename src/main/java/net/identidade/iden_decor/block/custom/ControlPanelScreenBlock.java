package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleHorizontalBlock;
import net.identidade.iden_decor.block.properties.HorizontalThreeConnectableProperty;
import net.identidade.iden_decor.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ControlPanelScreenBlock extends SimpleHorizontalBlock {

    public static final EnumProperty<HorizontalThreeConnectableProperty> PART = EnumProperty.create("part", HorizontalThreeConnectableProperty.class);

    public ControlPanelScreenBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(PART, HorizontalThreeConnectableProperty.SINGLE));
    }

    private HorizontalThreeConnectableProperty updatePart(LevelAccessor level, BlockPos pos, BlockState state, Direction facing) {
        Direction leftDir = facing.getCounterClockWise();
        Direction rightDir = facing.getClockWise();

        BlockState leftNeighbor = level.getBlockState(pos.relative(leftDir));
        BlockState rightNeighbor = level.getBlockState(pos.relative(rightDir));

        boolean left = (leftNeighbor.is(this) && leftNeighbor.getValue(FACING) == facing);
        boolean right = (rightNeighbor.is(this) && rightNeighbor.getValue(FACING) == facing);

        if (left && right) return HorizontalThreeConnectableProperty.CENTER;
        if (left) return HorizontalThreeConnectableProperty.RIGHT;
        if (right) return HorizontalThreeConnectableProperty.LEFT;

        return HorizontalThreeConnectableProperty.SINGLE;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        HorizontalThreeConnectableProperty part = state.getValue(PART);
        Direction facing = state.getValue(FACING);
        return switch (part) {
            case CENTER -> switch (facing) {
                case WEST -> Block.box(12, -1, 0, 15, 15, 16);
                case SOUTH -> Block.box(0, -1, 1, 16, 15, 4);
                case EAST -> Block.box(1, -1, 0, 4, 15, 16);
                default -> Block.box(0, -1, 12, 16, 15, 15);
            };
            case LEFT -> switch (facing) {
                case WEST -> Block.box(12, -1, 0, 15, 15, 15);
                case SOUTH -> Block.box(0, -1, 1, 15, 15, 4);
                case EAST -> Block.box(1, -1, 1, 4, 15, 16);
                default -> Block.box(1, -1, 12, 16, 15, 15);
            };
            case RIGHT -> switch(facing) {
                case WEST -> Block.box(12, -1, 1, 15, 15, 16);
                case SOUTH -> Block.box(1, -1, 1, 16, 15, 4);
                case EAST -> Block.box(1, -1, 0, 4, 15, 15);
                default -> Block.box(0, -1, 12, 15, 15, 15);
            };
            default -> switch (facing) {
                case WEST -> Block.box(12, -1, 1, 15, 15, 15);
                case SOUTH -> Block.box(1, -1, 1, 15, 15, 4);
                case EAST -> Block.box(1, -1, 1, 4, 15, 15);
                default -> Block.box(1, -1, 12, 15, 15, 15);
            };
        };
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        Direction facing = state.getValue(FACING);
        HorizontalThreeConnectableProperty part = updatePart(level, pos, state, facing);

        return state.setValue(PART, part);
    }

    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);

        if (!level.isClientSide) {
            level.updateNeighborsAt(pos, this);
        }
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {

        Direction facing = context.getHorizontalDirection().getOpposite();
        HorizontalThreeConnectableProperty part = updatePart(context.getLevel(), context.getClickedPos(), defaultBlockState(), facing);

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        if (pos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(pos.above()).canBeReplaced(context)) {
            return  this.defaultBlockState()
                    .setValue(FACING, facing)
                    .setValue(PART, part);
        }

        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PART);
    }
}
