package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleHorizontalBlock;
import net.identidade.iden_decor.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class PlushieBlock extends SimpleHorizontalBlock {
    public PlushieBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> Block.box(5, 0, 7, 11, 10, 11);
            case SOUTH -> Block.box(5, 0, 5, 11, 10, 9);
            case EAST -> Block.box(5, 0, 5, 9, 10, 11);
            case WEST -> Block.box(7, 0, 5, 11, 10, 11);
            default -> Block.box(0,0,0,16,16,16);
        };
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            level.playSound(null, pos, ModSounds.TOY_USE.get(), SoundSource.BLOCKS, 0.5f, 1);
        } else {
            level.addParticle(
                    ParticleTypes.HEART,
                    pos.getX() + 0.5 + (level.random.nextDouble() - 0.5) * 0.4,
                    pos.getY() + 0.8 + level.random.nextDouble() * 0.2,
                    pos.getZ() + 0.5 + (level.random.nextDouble() - 0.5) * 0.4,
                    0,
                    0.02,
                    0)
            ;
        }
        return InteractionResult.SUCCESS;
    }
}
