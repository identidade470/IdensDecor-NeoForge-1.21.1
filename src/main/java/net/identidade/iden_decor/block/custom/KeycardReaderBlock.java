package net.identidade.iden_decor.block.custom;

import com.mojang.serialization.MapCodec;
import net.identidade.iden_decor.block.custom.templates.SimpleHorizontalBlock;
import net.identidade.iden_decor.blockentity.KeycardReaderBlockEntity;
import net.identidade.iden_decor.item.ModItems;
import net.identidade.iden_decor.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class KeycardReaderBlock extends BaseEntityBlock {
    public static final MapCodec<KeycardReaderBlock> CODEC = simpleCodec(KeycardReaderBlock::new);

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");

    public KeycardReaderBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(ACTIVATED, false)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    public void activate(BlockState state, Level level, BlockPos pos, Player player) {
        level.setBlock(pos, state.setValue(ACTIVATED, true), 3);
        level.updateNeighborsAt(pos, this);
        level.updateNeighborsAt(pos.relative(state.getValue(FACING).getOpposite()), this);
        level.playSound(null, pos, ModSounds.ELETRONIC_BEEP.get(), SoundSource.BLOCKS, 0.5f, 1);
        level.scheduleTick(pos, this, 40);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (level.isClientSide) {
            return stack.is(ModItems.KEYCARD) ? ItemInteractionResult.SUCCESS : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof KeycardReaderBlockEntity keycard_reader) {
            if (!stack.is(ModItems.KEYCARD)) {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }

            String cardCode = stack.getHoverName().getString();

            if (!keycard_reader.hasKeycardCode()) {
                keycard_reader.setKeycardCode(cardCode);
                player.sendSystemMessage(Component.translatable("iden_decor.messages.keycard_assign", cardCode));
                return ItemInteractionResult.SUCCESS;
            }

            if (keycard_reader.keycardCodeMatches(cardCode)) {
                this.activate(state, level, pos, player);
                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.CONSUME;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (state != newState) {
            if (state.getValue(ACTIVATED)) {
                level.updateNeighborsAt(pos, this);
                level.updateNeighborsAt(pos.relative(state.getValue(FACING).getOpposite()), this);
            }
        }

        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(ACTIVATED)) {
            level.setBlock(pos, state.setValue(ACTIVATED, false), 3);
            level.updateNeighborsAt(pos, this);
            level.updateNeighborsAt(pos.relative(state.getValue(FACING).getOpposite()), this);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(ACTIVATED);
        builder.add(FACING);
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return (state.getValue(ACTIVATED) ? 16 : 0);
    }

    @Override
    protected int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return state.getValue(ACTIVATED) && state.getValue(FACING) == direction ? 15: 0;
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> Shapes.or(
                    Block.box(2, 2, 14, 14, 14, 16),
                    Block.box(2, 2, 12, 7, 14, 14)
            );
            case EAST -> Shapes.or(
                    Block.box(0, 2, 2, 2, 14, 14),
                    Block.box(2, 2, 2, 4, 14, 7)
            );
            case WEST -> Shapes.or(
                    Block.box(14, 2, 2, 16, 14, 14),
                    Block.box(12, 2, 9, 14, 14, 14)
            );
            case SOUTH -> Shapes.or(
                    Block.box(2, 2, 0, 14, 14, 2),
                    Block.box(9, 2, 2, 14, 14, 4)
            );
            default -> Block.box(0,0,0,16,16,16);
        };
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new KeycardReaderBlockEntity(blockPos, blockState);
    }
}
