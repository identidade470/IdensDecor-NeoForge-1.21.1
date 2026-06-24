package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleHorizontalBlock;
import net.identidade.iden_decor.block.properties.HorizontalThreeConnectableProperty;
import net.identidade.iden_decor.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ControlPanelBlock extends SimpleHorizontalBlock {

    public static final EnumProperty<HorizontalThreeConnectableProperty> PART = EnumProperty.create("part", HorizontalThreeConnectableProperty.class);

    public ControlPanelBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(PART, HorizontalThreeConnectableProperty.SINGLE));
    }

    private HorizontalThreeConnectableProperty updatePart(LevelAccessor level, BlockPos pos, BlockState state, Direction facing) {
        Direction leftDir = facing.getCounterClockWise();
        Direction rightDir = facing.getClockWise();

        BlockState leftNeighbor = level.getBlockState(pos.relative(leftDir));
        BlockState rightNeighbor = level.getBlockState(pos.relative(rightDir));

        boolean left = (leftNeighbor.is(ModTags.Blocks.CONTROL_PANELS) && leftNeighbor.getValue(FACING) == facing);
        boolean right = (rightNeighbor.is(ModTags.Blocks.CONTROL_PANELS) && rightNeighbor.getValue(FACING) == facing);

        if (left && right) return HorizontalThreeConnectableProperty.CENTER;
        if (left) return HorizontalThreeConnectableProperty.RIGHT;
        if (right) return HorizontalThreeConnectableProperty.LEFT;

        return HorizontalThreeConnectableProperty.SINGLE;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {

        Direction facing = state.getValue(FACING);
        HorizontalThreeConnectableProperty part = state.getValue(PART);

            return switch (facing) {
                case EAST -> Shapes.or(
                        Block.box(0, 0, 0, 16, 11, 16),
                        Block.box(6, 11, 0, 11, 14, 16),
                        Block.box(0, 0, 0, 6, 16, 16)
                );
                case SOUTH -> Shapes.or(
                        Block.box(0, 0, 0, 16, 11, 16),
                        Block.box(0, 11, 6, 16, 14, 11),
                        Block.box(0, 0, 0, 16, 16, 6)
                );
                case WEST -> Shapes.or(
                        Block.box(0, 0, 0, 16, 11, 16),
                        Block.box(5, 11, 0, 10, 14, 16),
                        Block.box(10, 0, 0, 16, 16, 16)
                );
                default -> Shapes.or(
                        Block.box(0, 0, 0, 16, 11, 16),
                        Block.box(0, 11, 5, 16, 14, 10),
                        Block.box(0, 0, 10, 16, 16, 16)
                );
            };
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        Direction facing = state.getValue(FACING);
        HorizontalThreeConnectableProperty part = updatePart(level, pos, state, facing);

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

    public void updateNeighbours(BlockState state, Level level, BlockPos pos) {
        level.updateNeighborsAt(pos, this);
        level.updateNeighborsAt(pos.below(), this);
    }
}
