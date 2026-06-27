package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.client.gui.custom.SewingMachineMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SewingMachineBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public static final VoxelShape NORTH_SHAPE = Shapes.or(
            Block.box(4, 0, 1, 12, 2, 15),
            Block.box(5, 0, 10, 11, 7, 15),
            Block.box(4, 3, 11.5, 10, 5, 13.5),
            Block.box(6, 7, 2, 10, 10, 15),
            Block.box(6, 5, 2, 10, 10, 6)
    );
    public static final VoxelShape SOUTH_SHAPE = Shapes.or(
            Block.box(4, 0, 1, 12, 2, 15),
            Block.box(5, 0, 1, 11, 7, 6),
            Block.box(6, 3, 2.5, 12, 5, 4.5),
            Block.box(6, 7, 1, 10, 10, 14),
            Block.box(6, 5, 10, 10, 10, 14)
    );
    public static final VoxelShape WEST_SHAPE = Shapes.or(
            Block.box(1, 0, 4, 15, 2, 12),
            Block.box(10, 0, 5, 15, 7, 11),
            Block.box(11.5, 3, 6, 13.5, 5, 12),
            Block.box(2, 7, 6, 15, 10, 10),
            Block.box(2, 5, 6, 6, 10, 10)
    );
    public static final VoxelShape EAST_SHAPE = Shapes.or(
            Block.box(1, 0, 4, 15, 2, 12),
            Block.box(1, 0, 5, 6, 7, 11),
            Block.box(2.5, 3, 4, 4.5, 5, 10),
            Block.box(1, 7, 6, 14, 10, 10),
            Block.box(10, 5, 6, 14, 10, 10)
    );

    public SewingMachineBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    //@Override
    //protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
    //    if (level.isClientSide) {
    //        return InteractionResult.SUCCESS;
    //    } else {
    //        player.openMenu(state.getMenuProvider(level, pos));
    //        return InteractionResult.CONSUME;
    //    }
    //}

    //@Override
    //protected @Nullable MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
    //    return new SimpleMenuProvider((syncId, playerInventory, player) ->
    //            new SewingMachineMenu(syncId, playerInventory, ContainerLevelAccess.create(level, pos)), Component.translatable("container.sewing_machine"));
    //}


    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case WEST -> WEST_SHAPE;
            case EAST -> EAST_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            default -> NORTH_SHAPE;
        };
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
