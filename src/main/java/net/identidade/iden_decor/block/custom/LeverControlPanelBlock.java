package net.identidade.iden_decor.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class LeverControlPanelBlock extends ControlPanelBlock {

    public static final BooleanProperty POWERED = BooleanProperty.create("powered");

    public LeverControlPanelBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(POWERED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(POWERED);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            BlockState blockstate = state.cycle(POWERED);
            if (blockstate.getValue(POWERED)) {
                // Particle
            }

            return  InteractionResult.SUCCESS;
        } else {
            this.pull(state, level, pos, (Player) null);
            return  InteractionResult.CONSUME;
        }
    }

    private void pull(BlockState state, Level level, BlockPos pos, @Nullable Player player) {
        state = state.cycle(POWERED);
        level.setBlock(pos, state, 3);
        this.updateNeighbours(state, level, pos);
        level.playSound(player, pos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.3f, state.getValue(POWERED)?0.6F:0.5F);
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!movedByPiston && !state.is(newState.getBlock())) {
            if (state.getValue(POWERED)) {
                this.updateNeighbours(state, level, pos);
            }

            super.onRemove(state, level, pos, newState, movedByPiston);
        }
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return state.getValue(POWERED)?15:0;
    }

    @Override
    protected int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return state.getValue(POWERED)?15:0;
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }
}
