package net.identidade.iden_decor.block.custom;

import com.mojang.serialization.MapCodec;
import net.identidade.iden_decor.blockentity.ComputerBlockEntity;
import net.identidade.iden_decor.item.ModItems;
import net.identidade.iden_decor.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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

public class ComputerBlock extends BaseEntityBlock {
    public static final MapCodec<ComputerBlock> CODEC = simpleCodec(ComputerBlock::new);

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty HAS_DISK = BooleanProperty.create("has_disk");

    public ComputerBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(HAS_DISK, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
        builder.add(HAS_DISK);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (!(blockEntity instanceof ComputerBlockEntity computer)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

//        boolean emptyHand = player.getMainHandItem().isEmpty();
//        boolean noText = computer.displayText.isEmpty();

        ItemStack held = player.getItemInHand(hand);

        // inserir
        if (held.is(ModItems.FLOPPY_DISK.get()) && !computer.hasDisk()) {
            if (!level.isClientSide) {

                computer.setDisk(held);

                level.playSound(null, pos, ModSounds.FLOPPY_DISK_INSERT.get(), SoundSource.BLOCKS, 1, 1);

                level.setBlockAndUpdate(pos, state.setValue(HAS_DISK, true));

                held.shrink(1);

                level.sendBlockUpdated(pos, state, state, 3);
            }

            return ItemInteractionResult.SUCCESS;
        }

        // remover
        if (held.isEmpty() && computer.hasDisk()) {
            if (!level.isClientSide) {
                ItemStack disk = computer.removeDisk();

                level.playSound(null, pos, ModSounds.FLOPPY_DISK_INSERT.get(), SoundSource.BLOCKS, 1, 1);

                player.addItem(disk);

                level.setBlockAndUpdate(pos, state.setValue(HAS_DISK, false));
                level.sendBlockUpdated(pos, state, state, 3);
            }

            return ItemInteractionResult.SUCCESS;
        }

//        if (emptyHand && noText) {
//            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
//        }
//
//        if (!level.isClientSide) {
//            if (emptyHand) {
//                computer.displayText = "";
//            } else {
//                computer.displayText = stack.getHoverName().getString();
//            }
//            computer.setChanged();
//            level.sendBlockUpdated(pos, state, state, 3);
//        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {

        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);

            if (blockEntity instanceof ComputerBlockEntity computer) {
                if (!level.isClientSide && computer.hasDisk()) {
                    popResource(level, pos, computer.getDisk());
                }
            }
        }

        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> Shapes.or(
                    Block.box(1, 0, 1, 15, 3, 15),
                    Block.box(3, 1, 3, 13, 4, 13),
                    Block.box(3, 4, 7, 13, 12, 14),
                    Block.box(2, 4, 2, 14, 14, 7)
            );
            case EAST -> Shapes.or(
                    Block.box(1, 0, 1, 15, 3, 15),
                    Block.box(3, 1, 3, 13, 4, 13),
                    Block.box(2, 4, 3, 9, 12, 13),
                    Block.box(9, 4, 2, 14, 14, 14)
            );
            case SOUTH -> Shapes.or(
                    Block.box(1, 0, 1, 15, 3, 15),
                    Block.box(3, 1, 3, 13, 4, 13),
                    Block.box(3, 4, 2, 13, 12, 9),
                    Block.box(2, 4, 9, 14, 14, 14)
            );
            case WEST -> Shapes.or(
                    Block.box(1, 0, 1, 15, 3, 15),
                    Block.box(3, 1, 3, 13, 4, 13),
                    Block.box(7, 4, 3, 14, 12, 13),
                    Block.box(2, 4, 2, 7, 14, 14)
            );
            default -> Block.box(0,0,0,16,16,16);
        };
    }

    @Override
    protected boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ComputerBlockEntity(blockPos, blockState);
    }
}
