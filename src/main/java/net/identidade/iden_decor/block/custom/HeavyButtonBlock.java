package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class HeavyButtonBlock extends ButtonBlock {

    // FLOOR
    protected static final VoxelShape FLOOR_AABB_Z = Block.box(5, 0, 3, 11, 4, 13);
    protected static final VoxelShape FLOOR_AABB_X = Block.box(3, 0, 5, 13, 4, 11);

    // CEILING
    protected static final VoxelShape CEILING_AABB_Z = Block.box(5, 12, 3, 11, 16, 13);
    protected static final VoxelShape CEILING_AABB_X = Block.box(3, 12, 5, 13, 16, 11);

    // WALL
    protected static final VoxelShape NORTH_AABB = Block.box(5, 3, 12, 11, 13, 16);
    protected static final VoxelShape SOUTH_AABB = Block.box(5, 3, 0, 11, 13, 4);
    protected static final VoxelShape WEST_AABB = Block.box(12, 3, 5, 16, 13, 11);
    protected static final VoxelShape EAST_AABB = Block.box(0, 3, 5, 4, 13, 11);

    // FLOOR (pressed)
    protected static final VoxelShape PRESSED_FLOOR_AABB_Z = Block.box(5, 0, 3, 11, 3, 13);
    protected static final VoxelShape PRESSED_FLOOR_AABB_X = Block.box(3, 0, 5, 13, 3, 11);

    // CEILING (pressed)
    protected static final VoxelShape PRESSED_CEILING_AABB_Z = Block.box(5, 13, 3, 11, 16, 13);
    protected static final VoxelShape PRESSED_CEILING_AABB_X = Block.box(3, 13, 5, 13, 16, 11);

    // WALL (pressed)
    protected static final VoxelShape PRESSED_NORTH_AABB = Block.box(5, 3, 13, 11, 13, 16);
    protected static final VoxelShape PRESSED_SOUTH_AABB = Block.box(5, 3, 0, 11, 13, 3);
    protected static final VoxelShape PRESSED_WEST_AABB = Block.box(13, 3, 5, 16, 13, 11);
    protected static final VoxelShape PRESSED_EAST_AABB = Block.box(0, 3, 5, 3, 13, 11);


    public HeavyButtonBlock(BlockSetType type, int ticksToStayPressed, Properties properties) {
        super(type, ticksToStayPressed, properties);
    }

    @Override
    protected void playSound(@Nullable Player player, LevelAccessor level, BlockPos pos, boolean hitByArrow) {
        level.playSound(player, pos, ModSounds.BUTTON_USE.get(), SoundSource.BLOCKS);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        boolean pressed = state.getValue(POWERED);
        AttachFace face = state.getValue(FACE);
        Direction dir = state.getValue(FACING);

        if (face == AttachFace.FLOOR) {
            return (dir.getAxis() == Direction.Axis.X)
                    ? (pressed ? PRESSED_FLOOR_AABB_X : FLOOR_AABB_X)
                    : (pressed ? PRESSED_FLOOR_AABB_Z : FLOOR_AABB_Z);
        }

        if (face == AttachFace.CEILING) {
            return (dir.getAxis() == Direction.Axis.X)
                    ? (pressed ? PRESSED_CEILING_AABB_X : CEILING_AABB_X)
                    : (pressed ? PRESSED_CEILING_AABB_Z : CEILING_AABB_Z);
        }

        // WALL
        return switch (dir) {
            case NORTH -> pressed ? PRESSED_NORTH_AABB : NORTH_AABB;
            case SOUTH -> pressed ? PRESSED_SOUTH_AABB : SOUTH_AABB;
            case WEST  -> pressed ? PRESSED_WEST_AABB  : WEST_AABB;
            case EAST  -> pressed ? PRESSED_EAST_AABB  : EAST_AABB;
            default -> FLOOR_AABB_X;
        };
    }

    //    @Override
//    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
//        return SHAPE;
//    }
}
