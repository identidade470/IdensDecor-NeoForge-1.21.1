package net.identidade.iden_decor.block.custom.interfaces;

import net.identidade.iden_decor.block.custom.templates.light.GenericLightBlock;
import net.identidade.iden_decor.block.custom.templates.light.MultifaceLightBlock;
import net.identidade.iden_decor.blockentity.LightBlockEntity;
import net.identidade.iden_decor.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public interface IPliersUsable {
    default InteractionResult onPliers(BlockState state, UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();

        BlockEntity entity = level.getBlockEntity(pos);
        if (entity instanceof LightBlockEntity blockEntity) {
            blockEntity.setActivationMode((blockEntity.getActivationMode() + 1) % blockEntity.getAvailableModes().length);

            player.displayClientMessage(Component.translatable("iden_decor.messages.activation_mode." + blockEntity.getActivationMode()), true);
            playPliersSound(level, pos);

            return InteractionResult.SUCCESS;
        }

        //if (state.hasProperty(GenericLightBlock.ANALOG)) {
        //    BlockState newState = state.cycle(GenericLightBlock.ANALOG);
        //    newState = onPliersUpdate(newState, level, pos);
        //
        //    level.setBlock(pos, newState, 3);
        //
        //    player.displayClientMessage(state.getValue(GenericLightBlock.ANALOG)? Component.translatable("iden_decor.messages.set_analog_on") : Component.translatable("iden_decor.messages.set_analog_off"), true);
        //    playPliersSound(level, pos);
        //    return InteractionResult.SUCCESS;
        //}

        return InteractionResult.PASS;
    }

    default BlockState onPliersUpdate(LightBlockEntity blockEntity, BlockState state, Level level, BlockPos pos) {
        if (state.hasProperty(GenericLightBlock.POWERED)) {
            int currentMode = blockEntity.getActivationMode();
            if (state.getValue(GenericLightBlock.POWERED) && currentMode > 1 && !level.hasNeighborSignal(pos)) {
                return state.setValue(GenericLightBlock.POWERED, false);
            }
            if (!state.getValue(GenericLightBlock.POWERED) && currentMode > 1 && level.hasNeighborSignal(pos)) {
                return state.setValue(GenericLightBlock.POWERED, true);
            }
        }

        return state;
    }

    static void playPliersSound(Level level, BlockPos pos) {
        level.playSound(null, pos, ModSounds.SCREW_CLICK.get(), SoundSource.BLOCKS);
    }
}
