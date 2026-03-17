package net.identidade.iden_decor.block.custom;

import com.mojang.serialization.MapCodec;
import net.identidade.iden_decor.blockentity.VaseBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class VaseBlock extends BaseEntityBlock {

    public static final MapCodec<VaseBlock> CODEC = simpleCodec(VaseBlock::new);

    public VaseBlock(Properties properties) {
        super(properties);
    }


    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (!(stack.getItem() instanceof BlockItem blockItem)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof VaseBlockEntity blockEntity) {

            if (blockEntity.getPlant() != Blocks.AIR) {
                return ItemInteractionResult.CONSUME;
            }

            level.playSound(null, pos, SoundEvents.GRASS_PLACE, SoundSource.PLAYERS);
            blockEntity.setPlant(blockItem.getBlock());
            stack.consume(1, player);

            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    public static void setHeight(float height) {

    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof VaseBlockEntity blockEntity) {
            if (blockEntity.getPlant() == Blocks.AIR) {
                return InteractionResult.PASS;
            }

            if (!level.isClientSide) {

                ItemStack plantStack = blockEntity.getPlant().asItem().getDefaultInstance();
                if (!player.addItem(plantStack)) {
                    player.drop(plantStack, false);
                }
            }

            blockEntity.setPlant(Blocks.AIR);
            blockEntity.setChanged();

            return InteractionResult.SUCCESS;

        }

        return InteractionResult.PASS;
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity be = level.getBlockEntity(pos);
            if (be instanceof VaseBlockEntity blockEntity) {
                if (!level.isClientSide) {

                    if (blockEntity.getPlant() == Blocks.AIR) {
                        return;
                    }

                    popResource(level, pos, blockEntity.getPlant().asItem().getDefaultInstance());
                }
            }
        }

        super.onRemove(state, level, pos, newState, movedByPiston);
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
        return new VaseBlockEntity(blockPos, blockState);
    }
}
