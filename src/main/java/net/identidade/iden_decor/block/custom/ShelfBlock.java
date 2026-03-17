package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleHorizontalBlock;
import net.identidade.iden_decor.block.properties.HorizontalThreeConnectableProperty;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ShelfBlock extends SimpleHorizontalBlock {

    public static final EnumProperty<HorizontalThreeConnectableProperty> PART = EnumProperty.create("part", HorizontalThreeConnectableProperty.class);

    public ShelfBlock() {
        super(Properties.of()
                .strength(3.0f)
                .sound(SoundType.WOOD)
                .noOcclusion());

        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(PART, HorizontalThreeConnectableProperty.SINGLE));
    }

    private HorizontalThreeConnectableProperty updatePart(LevelAccessor level, BlockPos pos, Direction facing) {

        Direction rightDir = facing.getClockWise();
        Direction leftDir = facing.getCounterClockWise();

        BlockState left = level.getBlockState(pos.relative(leftDir));
        BlockState right = level.getBlockState(pos.relative(rightDir));

        boolean leftConnected = (left.is(this) && left.getValue(FACING) == facing );
        boolean rightConnected = (right.is(this) && right.getValue(FACING) == facing);

        if (leftConnected && rightConnected) return HorizontalThreeConnectableProperty.CENTER;
        if (leftConnected) return HorizontalThreeConnectableProperty.RIGHT;
        if (rightConnected) return HorizontalThreeConnectableProperty.LEFT;

        return HorizontalThreeConnectableProperty.SINGLE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PART);
    }

    @Override
    protected boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Block.box(0, 3, 0, 16, 5, 16);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        HorizontalThreeConnectableProperty part = updatePart(level, pos, state.getValue(FACING));

        return state.setValue(PART, part);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (!level.isClientSide) {
            level.updateNeighborsAt(pos, this);
        }
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection().getOpposite();
        HorizontalThreeConnectableProperty part = updatePart(context.getLevel(), context.getClickedPos(), facing);

        return this.defaultBlockState()
                .setValue(FACING, facing)
                .setValue(PART, part);
    }
}
