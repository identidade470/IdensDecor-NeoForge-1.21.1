package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleHorizontalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DrinkingFountainBlock extends SimpleHorizontalBlock {
    public DrinkingFountainBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> Shapes.or(
                    Block.box(1, 0, 0, 15, 12, 6),
                    Block.box(2, 0, 6, 14, 7, 9),
                    Block.box(2, 7, 6, 14, 13, 11),
                    Block.box(0, 12, 0, 16, 15, 16),
                    Block.box(0, 15, 0, 16, 16, 2),
                    Block.box(0, 15, 13, 16, 16, 16),
                    Block.box(0, 15, 2, 2, 16, 13),
                    Block.box(14, 15, 2, 16, 16, 13)
            );
            case EAST -> Shapes.or(
                    Block.box(0, 0, 1, 6, 12, 15),
                    Block.box(6, 0, 2, 9, 7, 14),
                    Block.box(6, 7, 2, 11, 13, 14),
                    Block.box(0, 12, 0, 16, 15, 16),
                    Block.box(0, 15, 0, 2, 16, 16),
                    Block.box(13, 15, 0, 16, 16, 16),
                    Block.box(2, 15, 14, 13, 16, 16),
                    Block.box(2, 15, 0, 13, 16, 2)
            );
            case WEST -> Shapes.or(
                    Block.box(10, 0, 1, 16, 12, 15),
                    Block.box(7, 0, 2, 10, 7, 14),
                    Block.box(5, 7, 2, 10, 13, 14),
                    Block.box(0, 12, 0, 16, 15, 16),
                    Block.box(14, 15, 0, 16, 16, 16),
                    Block.box(0, 15, 0, 3, 16, 16),
                    Block.box(3, 15, 0, 14, 16, 2),
                    Block.box(3, 15, 14, 14, 16, 16)
            );
            default -> Shapes.or(
                    Block.box(1, 0, 10, 15, 12, 16),
                    Block.box(2, 0, 7, 14, 7, 10),
                    Block.box(2, 7, 5, 14, 13, 10),
                    Block.box(0, 12, 0, 16, 15, 16),
                    Block.box(0, 15, 14, 16, 16, 16),
                    Block.box(0, 15, 0, 16, 16, 3),
                    Block.box(14, 15, 3, 16, 16, 14),
                    Block.box(0, 15, 3, 2, 16, 14)
            );
        };
    }
}
