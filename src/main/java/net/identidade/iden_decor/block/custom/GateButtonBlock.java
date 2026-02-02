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

public class GateButtonBlock extends ButtonBlock {

    // chão (base)
    protected static final VoxelShape FLOOR_AABB =
            Block.box(4, 0, 4, 12, 8, 12);
    protected static final VoxelShape CEILING_AABB =
            Block.box(4, 8, 4, 12, 16, 12);
    protected static final VoxelShape WALL_NORTH_AABB =
            Block.box(4, 4, 8, 12, 12, 16);
    protected static final VoxelShape WALL_SOUTH_AABB =
            Block.box(4, 4, 0, 12, 12, 8);
    protected static final VoxelShape WALL_WEST_AABB =
            Block.box(8, 4, 4, 16, 12, 12);
    protected static final VoxelShape WALL_EAST_AABB =
            Block.box(0, 4, 4, 8, 12, 12);
    protected static final VoxelShape PRESSED_FLOOR_AABB =
            Block.box(4, 0, 4, 12, 7, 12);
    protected static final VoxelShape PRESSED_CEILING_AABB =
            Block.box(4, 9, 4, 12, 16, 12);
    protected static final VoxelShape PRESSED_WALL_NORTH_AABB =
            Block.box(4, 4, 9, 12, 12, 16);
    protected static final VoxelShape PRESSED_WALL_SOUTH_AABB =
            Block.box(4, 4, 0, 12, 12, 7);
    protected static final VoxelShape PRESSED_WALL_WEST_AABB =
            Block.box(9, 4, 4, 16, 12, 12);
    protected static final VoxelShape PRESSED_WALL_EAST_AABB =
            Block.box(0, 4, 4, 7, 12, 12);



    public GateButtonBlock(BlockSetType type, int ticksToStayPressed, Properties properties) {
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

        if (!pressed) {
            return switch (face) {
                case FLOOR -> FLOOR_AABB;
                case CEILING -> CEILING_AABB;
                case WALL -> switch (dir) {
                    case NORTH -> WALL_NORTH_AABB;
                    case SOUTH -> WALL_SOUTH_AABB;
                    case WEST  -> WALL_WEST_AABB;
                    case EAST  -> WALL_EAST_AABB;
                    default -> FLOOR_AABB;
                };
            };
        }

        // PRESSED
        return switch (face) {
            case FLOOR -> PRESSED_FLOOR_AABB;
            case CEILING -> PRESSED_CEILING_AABB;
            case WALL -> switch (dir) {
                case NORTH -> PRESSED_WALL_NORTH_AABB;
                case SOUTH -> PRESSED_WALL_SOUTH_AABB;
                case WEST  -> PRESSED_WALL_WEST_AABB;
                case EAST  -> PRESSED_WALL_EAST_AABB;
                default -> PRESSED_FLOOR_AABB;
            };
        };
    }

    //    @Override
//    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
//        return SHAPE;
//    }
}
