package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleHorizontalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MetalTable extends SimpleHorizontalBlock {

    public static final VoxelShape SHAPE1 = Shapes.or(
            Block.box(0, 14, 0, 16, 16, 16),
            Block.box(12, 0, 1, 14, 16, 15),
            Block.box(2, 0, 1, 4, 16, 15)
    );
    public static final VoxelShape SHAPE2 = Shapes.or(
            Block.box(0, 14, 0, 16, 16, 16),
            Block.box(1, 0, 2, 15, 16, 4),
            Block.box(1, 0, 12, 15, 16, 14)
    );

    public MetalTable(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);

        return  switch (facing) {
            case EAST, WEST -> SHAPE2;
            default -> SHAPE1;
        };
    }
}
