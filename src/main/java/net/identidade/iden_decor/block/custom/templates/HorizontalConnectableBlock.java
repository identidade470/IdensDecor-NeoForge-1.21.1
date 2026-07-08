package net.identidade.iden_decor.block.custom.templates;

import net.identidade.iden_decor.block.properties.HorizontalConnectableProperty;
import net.identidade.iden_decor.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import org.jetbrains.annotations.Nullable;

public class HorizontalConnectableBlock extends SimpleHorizontalBlock {

    public static final EnumProperty<HorizontalConnectableProperty> SHAPE = EnumProperty.create("shape", HorizontalConnectableProperty.class);

    public HorizontalConnectableBlock(Properties properties) {
        super(properties);
    }

    private boolean canConnect(BlockState state, Direction facing) {
        if (!state.is(ModTags.Blocks.CONTROL_PANELS)) {
            return false;
        }

        Direction other = state.getValue(FACING);

        return other == facing || other == facing.getClockWise() || other == facing.getCounterClockWise();
    }

    private HorizontalConnectableProperty updatePart(BlockState state, BlockGetter level, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        BlockState otherState = level.getBlockState(pos.relative(direction));

        if (otherState.is(this)) {
            Direction direction1 = otherState.getValue(FACING);
            if (direction1.getAxis() != state.getValue(FACING).getAxis()) {
                if (direction1 == direction.getCounterClockWise()) {
                    return  HorizontalConnectableProperty.INNER_LEFT;
                }

                return HorizontalConnectableProperty.INNER_RIGHT;
            }
        }

        BlockState blockstate1 = level.getBlockState(pos.relative(direction.getOpposite()));
        if (blockstate1.is(this)) {
            Direction direction2 = blockstate1.getValue(FACING);
            if (direction2.getAxis() != (state.getValue(FACING)).getAxis()) {
                if (direction2 == direction.getCounterClockWise()) {
                    return HorizontalConnectableProperty.OUTER_LEFT;
                }

                return HorizontalConnectableProperty.OUTER_RIGHT;
            }
        }

        Direction leftDir = direction.getCounterClockWise();
        Direction rightDir = direction.getClockWise();

        BlockState leftNeighbor = level.getBlockState(pos.relative(rightDir));
        BlockState rightNeighbor = level.getBlockState(pos.relative(leftDir));

        boolean left = (leftNeighbor.is(this));
        boolean right = (rightNeighbor.is(this));

        if (left && right) return HorizontalConnectableProperty.CENTER;
        if (left) return HorizontalConnectableProperty.LEFT;
        if (right) return HorizontalConnectableProperty.RIGHT;

        return HorizontalConnectableProperty.SINGLE;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        Direction facing = state.getValue(FACING);

        return facing.getAxis().isHorizontal() ? state.setValue(SHAPE, updatePart(state,level, pos)): super.updateShape(state, direction, neighborState, level, pos ,neighborPos);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);

        if (!level.isClientSide) {
            level.updateNeighborsAt(pos, this);
        }
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        return state.setValue(SHAPE, updatePart(state, level, pos));
    }

    public void updateNeighbours(BlockState state, Level level, BlockPos pos) {
        level.updateNeighborsAt(pos, this);
        level.updateNeighborsAt(pos.below(), this);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(SHAPE);
    }
}
