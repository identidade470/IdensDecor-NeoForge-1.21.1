package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleMultidirectionalBlock;
import net.identidade.iden_decor.block.custom.templates.SimpleWallHorizontalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class IndustrialLampBlock extends SimpleMultidirectionalBlock {

    private static final VoxelShape SHAPE1 = Block.box(3, 0, 5, 13, 4.5, 11);
    private static final VoxelShape SHAPE2 = Block.box(5, 0, 3, 11, 4.5, 13);
    private static final VoxelShape SHAPE3 = Block.box(3, 5, 11.5, 13, 11, 16);
    private static final VoxelShape SHAPE4 = Block.box(0, 5, 3, 4.5, 11, 13);
    private static final VoxelShape SHAPE5 = Block.box(3, 5, 0, 13, 11, 4.5);
    private static final VoxelShape SHAPE6 = Block.box(11.5, 5, 3, 16, 11, 13);
    private static final VoxelShape SHAPE7 = Block.box(3, 11.5, 5, 13, 16, 11);
    private static final VoxelShape SHAPE8 = Block.box(5, 11.5, 3, 11, 16, 13);

    public IndustrialLampBlock(Properties properties) {
        super(properties, false);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACE)) {
            case CEILING -> switch (state.getValue(FACING)) {
                case WEST, EAST -> SHAPE8;
                default -> SHAPE7;
            };
            case WALL -> switch (state.getValue(FACING)) {
                case WEST -> SHAPE6;
                case SOUTH -> SHAPE5;
                case EAST -> SHAPE4;
                default -> SHAPE3;
            };
            default -> switch (state.getValue(FACING)) {
                case EAST, WEST -> SHAPE2;
                default -> SHAPE1;
            };
        };
    }
}
