package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleMultidirectionalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WallProp extends SimpleMultidirectionalBlock {
    public WallProp(Properties properties, Boolean inverted) {
        super(properties.noOcclusion().noCollission(), inverted);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {

        AttachFace face = state.getValue(FACE);
        Direction facing = state.getValue(FACING);

        return  switch(face) {
            case FLOOR -> Block.box(0, 0, 0, 16, 1, 16);
            case CEILING -> Block.box(0, 15, 0, 16, 16, 16);
            case WALL -> switch(facing) {
                case WEST -> Block.box(15, 0, 0, 16, 16, 16);
                case SOUTH -> Block.box(0, 0, 0, 16, 16, 1);
                case EAST -> Block.box(0, 0, 0, 1, 16, 16);
                default -> Block.box(0, 0, 15, 16, 16, 16);
            };
        };
    }
}
