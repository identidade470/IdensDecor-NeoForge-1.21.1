package net.identidade.iden_decor.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WideGasCylinderBlock extends GasCylinderBlock {

    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(4, 14, 4, 12, 16, 12),
            Block.box(0, 1, 0, 16, 14, 16),
            Block.box(2, 0, 2, 14, 1, 14)
    );

    public WideGasCylinderBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
