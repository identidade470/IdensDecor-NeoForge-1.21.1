package net.identidade.iden_decor.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WallClockBlockEntity extends BlockEntity {
    public WallClockBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.WALL_CLOCK_BE.get(), pos, blockState);
    }
}
