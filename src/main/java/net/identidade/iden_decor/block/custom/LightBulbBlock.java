package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.interfaces.IPliersUsable;
import net.identidade.iden_decor.block.custom.templates.light.DirectionalLightBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class LightBulbBlock extends DirectionalLightBlock implements IPliersUsable {

    private static final VoxelShape SHAPE_NORTH = Shapes.or(
            Block.box(5.75, 5.75, 8.75, 10.25, 10.25, 15.25),
            Block.box(7, 7, 15, 9, 9, 16)
    );

    private static final VoxelShape SHAPE_DOWN = Shapes.or(
            Block.box(5.75, 8.75, 5.75, 10.25, 15.25, 10.25),
            Block.box(7, 15, 7, 9, 16, 9)
    );

    private static final VoxelShape SHAPE_UP = Shapes.or(
            Block.box(5.75, 0.75, 5.75, 10.25, 7.25, 10.25),
            Block.box(7, 0, 7, 9, 1, 9)
    );
    private static final VoxelShape SHAPE_SOUTH = Shapes.or(
            Block.box(5.75, 5.75, 0.75, 10.25, 10.25, 7.25),
            Block.box(7, 7, 0, 9, 9, 1)
    );
    private static final VoxelShape SHAPE_WEST = Shapes.or(
            Block.box(8.75, 5.75, 5.75, 15.25, 10.25, 10.25),
            Block.box(15, 7, 7, 16, 9, 9)
    );
    private static final VoxelShape SHAPE_EAST = Shapes.or(
            Block.box(0.75, 5.75, 5.75, 7.25, 10.25, 10.25),
            Block.box(0, 7, 7, 1, 9, 9)
    );

    public LightBulbBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case EAST -> SHAPE_EAST;
            case WEST -> SHAPE_WEST;
            case SOUTH -> SHAPE_SOUTH;
            case UP -> SHAPE_UP;
            case DOWN -> SHAPE_DOWN;
            default -> SHAPE_NORTH;
        };
    }
}
