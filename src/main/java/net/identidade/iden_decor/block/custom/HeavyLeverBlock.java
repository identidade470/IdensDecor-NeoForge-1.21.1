package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.properties.ColorProperty;
import net.identidade.iden_decor.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.Nullable;

public class HeavyLeverBlock extends LeverBlock {

    public static final EnumProperty<ColorProperty> COLOR = EnumProperty.create("color", ColorProperty.class);

    public HeavyLeverBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(COLOR, ColorProperty.BASE)
                .setValue(FACING, Direction.NORTH)
                .setValue(FACE, AttachFace.FLOOR)
                .setValue(POWERED, false));
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(COLOR);
    }

    @Override
    public void pull(BlockState state, Level level, BlockPos pos, @Nullable Player player) {

        state = (BlockState)state.cycle(POWERED);
        level.setBlock(pos, state, 3);
        level.updateNeighborsAt(pos, this);
        level.updateNeighborsAt(pos.relative(getConnectedDirection(state).getOpposite()), this);
        level.gameEvent(player, (Boolean)state.getValue(POWERED) ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pos);

        level.playSound(player, pos, ModSounds.LEVER_USE.get(), SoundSource.BLOCKS);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            if (stack.is(Tags.Items.DYES)) {
                ColorProperty newColor = ColorProperty.fromDye(stack.getItem());

                if (state.getValue(COLOR) == newColor) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

                level.setBlockAndUpdate(pos, state.setValue(COLOR, newColor));
                level.playSound(null, pos, SoundEvents.DYE_USE, player.getSoundSource(), 1, 1);
                if (!player.isCreative()) {
                    stack.shrink(1);
                }
                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}
