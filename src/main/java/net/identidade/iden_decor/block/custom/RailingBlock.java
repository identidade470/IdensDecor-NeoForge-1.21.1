package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleHorizontalBlock;
import net.identidade.iden_decor.block.properties.ColorProperty;
import net.identidade.iden_decor.block.properties.HorizontalThreeConnectableProperty;
import net.identidade.iden_decor.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.Nullable;

public class RailingBlock extends SimpleHorizontalBlock {

    public static final EnumProperty<HorizontalThreeConnectableProperty> PART = EnumProperty.create("part", HorizontalThreeConnectableProperty.class);
    public static final EnumProperty<ColorProperty> COLOR = EnumProperty.create("color", ColorProperty.class);

    public RailingBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(PART, HorizontalThreeConnectableProperty.SINGLE)
                .setValue(COLOR, ColorProperty.BASE));
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case EAST -> Block.box(14, 0, 0, 16, 24, 16);
            case SOUTH -> Block.box(0, 0, 0, 16, 24, 2);
            case WEST -> Block.box(0, 0, 0, 2, 24, 16);
            default -> Block.box(0, 0, 14, 16, 24, 16);
        };
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

            if (stack.is(Tags.Items.DYES)) {
                if (!level.isClientSide) {
                    ColorProperty newDye = ColorProperty.fromDye(stack.getItem());

                    if (newDye == state.getValue(COLOR)) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

                    level.setBlockAndUpdate(pos, state.setValue(COLOR, newDye));
                    level.playSound(null, pos, SoundEvents.DYE_USE, player.getSoundSource(), 1, 1);

                    if (!player.isCreative()) {
                        stack.shrink(1);
                    }
                }
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }

        return  ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    private HorizontalThreeConnectableProperty updatePart(LevelAccessor level, BlockPos pos, BlockState state, Direction facing) {
        Direction leftDir = facing.getCounterClockWise();
        Direction rightDir = facing.getClockWise();

        BlockState leftNeighbor = level.getBlockState(pos.relative(leftDir));
        BlockState rightNeighbor = level.getBlockState(pos.relative(rightDir));

        boolean left = (leftNeighbor.is(ModTags.Blocks.RAILINGS) && leftNeighbor.getValue(FACING) == facing);
        boolean right = (rightNeighbor.is(ModTags.Blocks.RAILINGS) && rightNeighbor.getValue(FACING) == facing);

        if (left && right) return HorizontalThreeConnectableProperty.CENTER;
        if (left) return HorizontalThreeConnectableProperty.RIGHT;
        if (right) return HorizontalThreeConnectableProperty.LEFT;

        return HorizontalThreeConnectableProperty.SINGLE;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {

        Direction facing = state.getValue(FACING);
        HorizontalThreeConnectableProperty part = updatePart(level, pos, state, facing);

        return state
                .setValue(PART, part);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);

        if (!level.isClientSide) {
            Direction facing = state.getValue(FACING);

            Direction leftDir = facing.getCounterClockWise();
            Direction rightDir = facing.getClockWise();

            level.updateNeighborsAt(pos, this);
        }
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {

        Direction facing = context.getHorizontalDirection().getOpposite();
        HorizontalThreeConnectableProperty part = updatePart(context.getLevel(), context.getClickedPos(), defaultBlockState(), facing);

        return this.defaultBlockState()
                .setValue(FACING, facing)
                .setValue(PART, part);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PART);
        builder.add(COLOR);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> Block.box(0, 0, 14, 16, 16, 16);
            case SOUTH -> Block.box(0, 0, 0, 16, 16, 2);
            case EAST -> Block.box(0, 0, 0, 2, 16, 16);
            case WEST -> Block.box(14, 0, 0, 16, 16, 16);
            case null, default -> Block.box(0, 0, 14, 16, 16, 16);
        };
    }
}
