package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleHorizontalBlock;
import net.identidade.iden_decor.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BatteryCellBlock extends SimpleHorizontalBlock {

    public static final BooleanProperty BATTERY = BooleanProperty.create("battery");

    public BatteryCellBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(BATTERY, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(BATTERY);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
       if (stack.is(ModItems.CORE_BATTERY) && !state.getValue(BATTERY)) {
            if (!level.isClientSide) {
                level.setBlock(pos, state.setValue(BATTERY, true), 3);
                level.playSound(null, pos, SoundEvents.VAULT_INSERT_ITEM, SoundSource.PLAYERS, 0.5f, 1.25f);
                stack.shrink(1);

                updateNeighbours(level, state, pos);
            }

           return  ItemInteractionResult.SUCCESS;
        }

       return  ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (state.getValue(BATTERY) && player.getMainHandItem().isEmpty()) {
            if (level.isClientSide) {
                level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.25f, 0.5f);
                return InteractionResult.SUCCESS;
            } else {
                player.setItemInHand(InteractionHand.MAIN_HAND ,ModItems.CORE_BATTERY.toStack());
                level.setBlock(pos, state.setValue(BATTERY, false), 3);

                return  InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.CONSUME;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {

        VoxelShape battery = (state.getValue(BATTERY)?Block.box(4, 3, 4, 12, 13, 12):Shapes.empty());

        return switch (state.getValue(FACING)) {
            case WEST -> Shapes.or(
                    Block.box(2, 0, 2, 14, 2, 14),
                    Block.box(12, 2, 4, 14, 14, 12),
                    Block.box(3, 13, 3, 13, 14, 13),
                    Block.box(3, 2, 3, 13, 3, 13),
                    Block.box(2, 14, 2, 14, 16, 14),
                    battery
            );
            case SOUTH -> Shapes.or(
                    Block.box(2, 0, 2, 14, 2, 14),
                    Block.box(4, 2, 2, 12, 14, 4),
                    Block.box(3, 13, 3, 13, 14, 13),
                    Block.box(3, 2, 3, 13, 3, 13),
                    Block.box(2, 14, 2, 14, 16, 14),
                    battery
            );
            case EAST -> Shapes.or(
                    Block.box(2, 0, 2, 14, 2, 14),
                    Block.box(2, 2, 4, 4, 14, 12),
                    Block.box(3, 13, 3, 13, 14, 13),
                    Block.box(3, 2, 3, 13, 3, 13),
                    Block.box(2, 14, 2, 14, 16, 14),
                    battery
            );
            default -> Shapes.or(
                    Block.box(2, 0, 2, 14, 2, 14),
                    Block.box(4, 2, 12, 12, 14, 14),
                    Block.box(3, 13, 3, 13, 14, 13),
                    Block.box(3, 2, 3, 13, 3, 13),
                    Block.box(2, 14, 2, 14, 16, 14),
                    battery
            );
        };
    }

    private void updateNeighbours(Level level, BlockState state, BlockPos pos) {
        level.updateNeighborsAt(pos, this);
        level.updateNeighborsAt(pos.above(), this);
        level.updateNeighborsAt(pos.below(), this);
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return state.getValue(BATTERY)?15:0;
    }

    @Override
    protected int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        if (state.getValue(BATTERY)) {
            return (direction==Direction.UP||direction==Direction.DOWN)?15:0;
        }
        return 0;
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        updateNeighbours(level, state, pos);
        if (state.getValue(BATTERY) && !state.is(newState.getBlock())) {
            popResource(level, pos, ModItems.CORE_BATTERY.toStack());
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }
}
