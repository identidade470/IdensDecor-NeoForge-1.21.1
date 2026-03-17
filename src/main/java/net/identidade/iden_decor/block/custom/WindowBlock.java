package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleHorizontalBlock;
import net.identidade.iden_decor.block.properties.VerticalThreeConnectableProperty;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WindowBlock extends SimpleHorizontalBlock {

    public static final EnumProperty<VerticalThreeConnectableProperty> PART = EnumProperty.create("part", VerticalThreeConnectableProperty.class);

    public WindowBlock(Properties properties) {
        super(properties);
        super.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(PART, VerticalThreeConnectableProperty.SINGLE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PART);
    }

    private VerticalThreeConnectableProperty updatePart(LevelAccessor level, BlockPos pos) {
        BlockState upperDir = level.getBlockState(pos.above());
        BlockState lowerDir = level.getBlockState(pos.below());

        boolean upper = upperDir.is(this);
        boolean lower = lowerDir.is(this);

        if (upper && lower) return VerticalThreeConnectableProperty.CENTER;
        if (upper) return VerticalThreeConnectableProperty.LOWER;
        if (lower) return VerticalThreeConnectableProperty.UPPER;

        return VerticalThreeConnectableProperty.SINGLE;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {

        VerticalThreeConnectableProperty part = updatePart(level, pos);

        return state.setValue(PART, part);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);

        if (!level.isClientSide) {
            level.updateNeighborsAt(pos, this);
        }
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VerticalThreeConnectableProperty part = state.getValue(PART);
        boolean center = part == VerticalThreeConnectableProperty.CENTER;
        boolean single = part == VerticalThreeConnectableProperty.SINGLE;
        boolean upper = part == VerticalThreeConnectableProperty.UPPER;
        boolean lower = part == VerticalThreeConnectableProperty.LOWER;

        return switch (state.getValue(FACING)) {
            case EAST, WEST -> Block.box(7, 0, 0, 9, 16, 16);
            default -> Block.box(0, 0, 7, 16, 16, 9);
        };
    }
}
