package net.identidade.iden_decor.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class KeyboardBlock extends ButtonBlock {

    public static final VoxelShape WALL_EAST = Block.box(0, 5, 1, 1, 11, 15);
    public static final VoxelShape WALL_NORTH = Block.box(1, 5, 15, 15, 11, 16);
    public static final VoxelShape WALL_SOUTH = Block.box(1, 5, 0, 15, 11, 1);
    public static final VoxelShape WALL_WEST = Block.box(15, 5, 1, 16, 11, 15);
    public static final VoxelShape CEILING_EW = Block.box(5, 15, 1, 11, 16, 15);
    public static final VoxelShape CEILING_NS = Block.box(1, 15, 5, 15, 16, 11);
    public static final VoxelShape FLOOR_EW = Block.box(5, 0, 1, 11, 1, 15);
    public static final VoxelShape FLOOR_NS = Block.box(1, 0, 5, 15, 1, 11);

    public KeyboardBlock(BlockSetType type, int ticksToStayPressed, Properties properties) {
        super(type, ticksToStayPressed, properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACE)) {
            case WALL -> switch (state.getValue(FACING)) {
                case WEST -> WALL_WEST;
                case EAST -> WALL_EAST;
                case SOUTH -> WALL_SOUTH;
                default -> WALL_NORTH;
            };
            case CEILING -> switch (state.getValue(FACING)) {
                case WEST, EAST -> CEILING_EW;
                default -> CEILING_NS;
            };
            default -> switch (state.getValue(FACING)) {
                case WEST, EAST -> FLOOR_EW;
                default -> FLOOR_NS;
            };
        };
    }
}
