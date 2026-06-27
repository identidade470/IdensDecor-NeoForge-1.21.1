package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleMultidirectionalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WheelTireBlock extends SimpleMultidirectionalBlock {

    private static final VoxelShape SHAPE1 = Shapes.or(
            Block.box(10, 1, 11, 16, 15, 15),
            Block.box(10, 1, 1, 16, 15, 5),
            Block.box(10, 11, 5, 16, 15, 11),
            Block.box(10, 1, 5, 16, 5, 11)
    );
    private static final VoxelShape SHAPE2 = Shapes.or(
            Block.box(11, 1, 0, 15, 15, 6),
            Block.box(1, 1, 0, 5, 15, 6),
            Block.box(5, 11, 0, 11, 15, 6),
            Block.box(5, 1, 0, 11, 5, 6)
    );
    private static final VoxelShape SHAPE3 = Shapes.or(
            Block.box(0, 1, 1, 6, 15, 5),
            Block.box(0, 1, 11, 6, 15, 15),
            Block.box(0, 11, 5, 6, 15, 11),
            Block.box(0, 1, 5, 6, 5, 11)
    );
    private static final VoxelShape SHAPE4 = Shapes.or(
            Block.box(1, 1, 10, 5, 15, 16),
            Block.box(11, 1, 10, 15, 15, 16),
            Block.box(5, 11, 10, 11, 15, 16),
            Block.box(5, 1, 10, 11, 5, 16)
    );
    private static final VoxelShape SHAPE5 = Shapes.or(
            Block.box(1, 10, 1, 15, 16, 5),
            Block.box(1, 10, 11, 15, 16, 15),
            Block.box(11, 10, 5, 15, 16, 11),
            Block.box(1, 10, 5, 5, 16, 11)
    );
    private static final VoxelShape SHAPE6 = Shapes.or(
            Block.box(1, 0, 1, 5, 6, 15),
            Block.box(11, 0, 1, 15, 6, 15),
            Block.box(5, 0, 11, 11, 6, 15),
            Block.box(5, 0, 1, 11, 6, 5)
    );

    public WheelTireBlock(Properties properties) {
        super(properties, false);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        AttachFace face = state.getValue(FACE);
        Direction facing = state.getValue(FACING);

        return switch (face) {
            case WALL -> switch (facing) {
                case WEST -> SHAPE1;
                case SOUTH -> SHAPE2;
                case EAST -> SHAPE3;
                default -> SHAPE4;
            };
            case CEILING -> SHAPE5;
            default -> SHAPE6;
        };
    }
}
