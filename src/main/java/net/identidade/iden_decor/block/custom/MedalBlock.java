package net.identidade.iden_decor.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MedalBlock extends WallProp {

    public MedalBlock(Properties properties) {
        super(properties, false);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        AttachFace face = state.getValue(FACE);
        return switch (face) {
            case WALL -> switch (facing) {
                case WEST -> Block.box(14, 2, 5, 16, 16, 11);
                case SOUTH -> Block.box(5, 2, 0, 11, 16, 2);
                case EAST -> Block.box(0, 2, 5, 2, 16, 11);
                default -> Block.box(5, 2, 14, 11, 16, 16);
            };
            case FLOOR -> switch (facing) {
                case EAST -> Block.box(2, 0, 5, 16, 2, 11);
                case SOUTH -> Block.box(5, 0, 0, 11, 2, 14);
                case WEST -> Block.box(0, 0, 5, 14, 2, 11);
                default -> Block.box(5, 0, 2, 11, 2, 16);
            };
            default -> switch (facing) {
                case WEST -> Block.box(2, 14, 5, 16, 16, 11);
                case SOUTH -> Block.box(5, 14, 0, 11, 16, 14);
                case EAST -> Block.box(0, 14, 5, 14, 16, 11);
                default -> Block.box(5, 14, 2, 11, 16, 16);
            };
        };
    }
}
