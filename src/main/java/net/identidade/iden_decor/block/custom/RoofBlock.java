package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleHorizontalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RoofBlock extends SimpleHorizontalBlock {
    public RoofBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case EAST -> Shapes.or(
                    Block.box(12.25, 0, 0, 16, 7, 16),
                    Block.box(6.75, 0, 0, 12.25, 11, 16),
                    Block.box(0, 0, 0, 6.75, 16, 16)
            );
            case SOUTH -> Shapes.or(
                    Block.box(0, 0, 12.25, 16, 7, 16),
                    Block.box(0, 0, 6.75, 16, 11, 12.25),
                    Block.box(0, 0, 0, 16, 16, 6.75)
            );
            case WEST -> Shapes.or(
                    Block.box(0, 0, 0, 3.75, 7, 16),
                    Block.box(3.75, 0, 0, 9.25, 11, 16),
                    Block.box(9.25, 0, 0, 16, 16, 16)
            );
            default -> Shapes.or(
                    Block.box(0, 0, 0, 16, 7, 3.75),
                    Block.box(0, 0, 3.75, 16, 11, 9.25),
                    Block.box(0, 0, 9.25, 16, 16, 16)
            );
        };
    }
}
