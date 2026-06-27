package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleThreeStackableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GuaranaCanBlock extends SimpleThreeStackableBlock {

    private static final VoxelShape SHAPE1 = Shapes.or(
            Block.box(6, 0, 6, 10, 7, 10)
    );
    private static final VoxelShape SHAPE2 = Shapes.or(
            Block.box(3, 0, 5, 12, 7, 12)
    );
    private static final VoxelShape SHAPE3 = Shapes.or(
            Block.box(3, 0, 3, 12, 7, 13)
    );
    public GuaranaCanBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        int quantity = state.getValue(QUANTITY);

        return  switch ((Integer) quantity) {
            case 2 -> SHAPE2;
            case 3 -> SHAPE3;
            default -> SHAPE1;
        };
    }

}
