package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.entity.ModEntities;
import net.identidade.iden_decor.entity.Seat;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class CouchBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final VoxelShape SHAPE_NORTH = Shapes.or(
            Block.box(4, 0, 0, 12, 7, 16),
            Block.box(4, 7, 11, 12, 15, 16),
            Block.box(12, 0, 0, 16, 11, 16),
            Block.box(0, 0, 0, 4, 11, 16)
    );
    public static final VoxelShape SHAPE_SOUTH = Shapes.or(
            Block.box(4, 0, 0, 12, 7, 16),
            Block.box(4, 7, 0, 12, 15, 5),
            Block.box(0, 0, 0, 4, 11, 16),
            Block.box(12, 0, 0, 16, 11, 16)
    );
    public static final VoxelShape SHAPE_WEST = Shapes.or(
            Block.box(0, 0, 4, 16, 7, 12),
            Block.box(11, 7, 4, 16, 15, 12),
            Block.box(0, 0, 0, 16, 11, 4),
            Block.box(0, 0, 12, 16, 11, 16)
    );
    public static final VoxelShape SHAPE_EAST = Shapes.or(
            Block.box(0, 0, 4, 16, 7, 12),
            Block.box(0, 7, 4, 5, 15, 12),
            Block.box(0, 0, 12, 16, 11, 16),
            Block.box(0, 0, 0, 16, 11, 4)
    );

    public CouchBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH));
    }


    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> SHAPE_NORTH;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            case EAST -> SHAPE_EAST;
            case UP -> SHAPE_NORTH;
            case DOWN -> SHAPE_NORTH;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) return InteractionResult.SUCCESS;
        if (player.isPassenger()) return InteractionResult.PASS;

        if (!player.isCrouching()) {
            Seat seat = ModEntities.SEAT.get().create(level);
            if (seat==null) return InteractionResult.FAIL;

            seat.setPos(
                    pos.getX()+0.5,
                    pos.getY()+0.5,
                    pos.getZ()+0.5);

            level.addFreshEntity(seat);
            player.startRiding(seat, false);
        }

        return InteractionResult.CONSUME;
    }
}
