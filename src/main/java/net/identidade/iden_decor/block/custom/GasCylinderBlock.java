package net.identidade.iden_decor.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GasCylinderBlock extends Block {

    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(5, 14, 5, 11, 16, 11),
            Block.box(4, 0, 4, 12, 2, 12),
            Block.box(3, 2, 3, 13, 14, 13)
    );

    public GasCylinderBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
