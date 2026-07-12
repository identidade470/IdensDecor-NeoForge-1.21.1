package net.identidade.iden_decor.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SlimGasCylinderBlock extends GasCylinderBlock {

    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(6, 0, 6, 10, 1, 10),
            Block.box(5, 1, 5, 11, 14, 11),
            Block.box(6, 14, 6, 10, 16, 10)
    );

    public SlimGasCylinderBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
