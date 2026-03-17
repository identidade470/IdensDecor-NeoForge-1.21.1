package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleHorizontalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CalendarBlock extends SimpleHorizontalBlock {
    public CalendarBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.relative(state.getValue(FACING).getOpposite())).isFaceSturdy(level, pos, state.getValue(FACING));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            player.displayClientMessage(Component.literal("Day: " + level.getDayTime() / 24000L), true);
            level.playSound(player, pos, SoundEvents.UI_CARTOGRAPHY_TABLE_TAKE_RESULT, SoundSource.BLOCKS, 1, 1);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    };

    @Override
    protected boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case WEST -> Block.box(15, 2, 2, 16, 14, 14);
            case EAST -> Block.box(0, 2, 2, 1, 14, 14);
            case SOUTH -> Block.box(2, 2, 0, 14, 14, 1);
            default -> Block.box(2, 2, 15, 14, 14, 16);
        };
    }
}
