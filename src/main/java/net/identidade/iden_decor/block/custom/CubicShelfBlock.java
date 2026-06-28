package net.identidade.iden_decor.block.custom;

import com.mojang.serialization.MapCodec;
import net.identidade.iden_decor.blockentity.CubicShelfBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CubicShelfBlock extends BaseEntityBlock {

    public static final MapCodec<CubicShelfBlock> CODEC = simpleCodec(CubicShelfBlock::new);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_NORTH = Shapes.or(
            Block.box(2, 2, 10, 14, 4, 16),
            Block.box(12, 2, 10, 14, 14, 16),
            Block.box(2, 2, 10, 4, 14, 16),
            Block.box(2, 12, 10, 14, 14, 16)
    );
    private static final VoxelShape SHAPE_SOUTH = Shapes.or(
            Block.box(2, 2, 0, 14, 4, 6),
            Block.box(2, 2, 0, 4, 14, 6),
            Block.box(12, 2, 0, 14, 14, 6),
            Block.box(2, 12, 0, 14, 14, 6)
    );
    private static final VoxelShape SHAPE_WEST = Shapes.or(
            Block.box(10, 2, 2, 16, 4, 14),
            Block.box(10, 2, 2, 16, 14, 4),
            Block.box(10, 2, 12, 16, 14, 14),
            Block.box(10, 12, 2, 16, 14, 14)
    );
    private static final VoxelShape SHAPE_EAST = Shapes.or(
            Block.box(0, 2, 2, 6, 4, 14),
            Block.box(0, 2, 12, 6, 14, 14),
            Block.box(0, 2, 2, 6, 14, 4),
            Block.box(0, 12, 2, 6, 14, 14)
    );

    public CubicShelfBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (stack.isEmpty()) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof CubicShelfBlockEntity blockEntity) {

            if (!blockEntity.getItem().isEmpty()) {
                return ItemInteractionResult.CONSUME;
            }

            level.playSound(player, pos, SoundEvents.BUNDLE_INSERT, SoundSource.PLAYERS);

            ItemStack stored = stack.copyWithCount(1);
            blockEntity.setItem(stored);
            stack.consume(1, player);

            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        BlockEntity be = level.getBlockEntity(pos);

        System.out.println(0);

        if (be instanceof CubicShelfBlockEntity blockEntity) {
            if (blockEntity.getItem().isEmpty()) {
                return InteractionResult.PASS;
            }

            if (!level.isClientSide) {
                ItemStack stack = blockEntity.getItem().copy();
                if (!player.addItem(stack)) {
                    player.drop(stack, false);
                }
            }

            blockEntity.setItem(ItemStack.EMPTY);

            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.PASS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case EAST -> SHAPE_EAST;
            case WEST -> SHAPE_WEST;
            case SOUTH -> SHAPE_SOUTH;
            default -> SHAPE_NORTH;
        };
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CubicShelfBlockEntity(blockPos, blockState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
}
