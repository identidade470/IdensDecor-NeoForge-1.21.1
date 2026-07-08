package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.HorizontalConnectableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ControlPanelBlock extends HorizontalConnectableBlock {

    public ControlPanelBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {

        Direction facing = state.getValue(FACING);

            return switch (facing) {
                case EAST -> Shapes.or(
                        Block.box(0, 0, 0, 16, 11, 16),
                        Block.box(6, 11, 0, 11, 14, 16),
                        Block.box(0, 0, 0, 6, 16, 16)
                );
                case SOUTH -> Shapes.or(
                        Block.box(0, 0, 0, 16, 11, 16),
                        Block.box(0, 11, 6, 16, 14, 11),
                        Block.box(0, 0, 0, 16, 16, 6)
                );
                case WEST -> Shapes.or(
                        Block.box(0, 0, 0, 16, 11, 16),
                        Block.box(5, 11, 0, 10, 14, 16),
                        Block.box(10, 0, 0, 16, 16, 16)
                );
                default -> Shapes.or(
                        Block.box(0, 0, 0, 16, 11, 16),
                        Block.box(0, 11, 5, 16, 14, 10),
                        Block.box(0, 0, 10, 16, 16, 16)
                );
            };
    }
}
