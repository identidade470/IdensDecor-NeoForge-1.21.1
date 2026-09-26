package net.identidade.iden_decor.block.custom.templates.light;

import net.identidade.iden_decor.blockentity.LightBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class GenericLightBlock extends Block implements EntityBlock {

    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    public static final BooleanProperty ANALOG = BooleanProperty.create("analog");

    public GenericLightBlock(Properties properties) {
        super(properties.lightLevel(state -> state.getValue(POWERED)?15:0));
        this.registerDefaultState(this.defaultBlockState()
                .setValue(POWERED, false)
                .setValue(ANALOG, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(POWERED, ANALOG);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if (!level.isClientSide) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof LightBlockEntity blockEntity) {
                if (blockEntity.getActivationMode() == 1) {
                    if (state.getValue(POWERED) != level.hasNeighborSignal(pos)) {
                        level.setBlock(pos, state.cycle(POWERED), 3);
                    }
                }
            }
        }
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {

        if (player.getMainHandItem().isEmpty()) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof LightBlockEntity blockEntity) {
                if (blockEntity.getActivationMode() == 0) {
                    if (!level.isClientSide) {
                        level.setBlock(pos, state.cycle(POWERED), 3);
                        level.playSound(null, pos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 1, state.getValue(POWERED)?.85f:.75f);
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }

        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new LightBlockEntity(blockPos, blockState);
    }
}
