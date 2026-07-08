package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleFourStackableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class JuiceBottleBlock extends SimpleFourStackableBlock {

    private static final VoxelShape SHAPE1 = Block.box(5, 0, 5, 11, 10, 11);
    private static final VoxelShape SHAPE2 = Block.box(3, 0, 3, 14, 10, 12);
    private static final VoxelShape SHAPE3 = Block.box(2, 0, 1, 13, 10, 13);
    private static final VoxelShape SHAPE4 = Block.box(2, 0, 1, 14, 10, 15);

    public JuiceBottleBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(QUANTITY)) {
            case 2 -> SHAPE2;
            case 3 -> SHAPE3;
            case 4 -> SHAPE4;
            default -> SHAPE1;
        };
    }
}
