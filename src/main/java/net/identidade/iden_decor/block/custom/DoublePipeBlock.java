package net.identidade.iden_decor.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DoublePipeBlock extends FaceAttachedHorizontalDirectionalBlock {
    public static final MapCodec<DoublePipeBlock> CODEC = simpleCodec(DoublePipeBlock::new);



    public DoublePipeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACE, AttachFace.FLOOR).setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACE, FACING);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        AttachFace face = state.getValue(FACE);
        Direction direction = state.getValue(FACING);

        if (face == AttachFace.WALL) {
            return switch (direction) {
                case WEST, EAST -> Block.box(0, 4, 1, 16, 12, 15);
                default -> Block.box(1, 4, 0, 15, 12, 16);
            };
        } else {
            return switch (direction) {
                case WEST, EAST -> Block.box(4, 0, 1, 12, 16, 15);
                default -> Block.box(1, 0, 4, 15, 16, 12);
            };
        }
    }


    @Override
    protected boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }

    @Override
    protected MapCodec<? extends FaceAttachedHorizontalDirectionalBlock> codec() {
        return CODEC;
    }
}
