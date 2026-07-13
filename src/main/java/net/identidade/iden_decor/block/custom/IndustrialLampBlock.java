package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleMultidirectionalBlock;
import net.identidade.iden_decor.block.custom.templates.SimpleWallHorizontalBlock;
import net.identidade.iden_decor.item.ModItems;
import net.identidade.iden_decor.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class IndustrialLampBlock extends SimpleMultidirectionalBlock {

    private static final VoxelShape SHAPE1 = Block.box(3, 0, 5, 13, 4.5, 11);
    private static final VoxelShape SHAPE2 = Block.box(5, 0, 3, 11, 4.5, 13);
    private static final VoxelShape SHAPE3 = Block.box(3, 5, 11.5, 13, 11, 16);
    private static final VoxelShape SHAPE4 = Block.box(0, 5, 3, 4.5, 11, 13);
    private static final VoxelShape SHAPE5 = Block.box(3, 5, 0, 13, 11, 4.5);
    private static final VoxelShape SHAPE6 = Block.box(11.5, 5, 3, 16, 11, 13);
    private static final VoxelShape SHAPE7 = Block.box(3, 11.5, 5, 13, 16, 11);
    private static final VoxelShape SHAPE8 = Block.box(5, 11.5, 3, 11, 16, 13);

    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    public static final BooleanProperty ANALOG = BooleanProperty.create("analog");

    public IndustrialLampBlock(Properties properties) {
        super(properties, false);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(POWERED, false)
                .setValue(ANALOG, false));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {

        if (!state.getValue(ANALOG)) {
            if (!level.isClientSide) {
                level.setBlock(pos, state.cycle(POWERED), 3);
                level.playSound(null, pos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 1, state.getValue(POWERED)?.85f:.75f);
            }
            return InteractionResult.SUCCESS;
        }

        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (stack.is(ModItems.PLIERS)) {
            if (!level.isClientSide) {
                BlockState newState = state.cycle(ANALOG);

                if (newState.getValue(POWERED) && newState.getValue(ANALOG) && !level.hasNeighborSignal(pos)) {
                    newState = newState.setValue(POWERED, false);
                }
                if (!newState.getValue(POWERED) && newState.getValue(ANALOG) && level.hasNeighborSignal(pos)) {
                    newState = newState.setValue(POWERED, true);
                }

                player.displayClientMessage(state.getValue(ANALOG)? Component.translatable("iden_decor.messages.set_analog_on") : Component.translatable("iden_decor.messages.set_analog_off"), true);
                level.playSound(null, pos, ModSounds.SCREW_CLICK.get(), SoundSource.BLOCKS);
                level.setBlock(pos, newState, 3);
            }
            return ItemInteractionResult.SUCCESS;
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if (!level.isClientSide) {
            if (state.getValue(ANALOG)) {
                if (state.getValue(POWERED) != level.hasNeighborSignal(pos)) {
                    level.setBlock(pos, state.cycle(POWERED), 3);
                }
            }
        }
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(POWERED, ANALOG);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACE)) {
            case CEILING -> switch (state.getValue(FACING)) {
                case WEST, EAST -> SHAPE8;
                default -> SHAPE7;
            };
            case WALL -> switch (state.getValue(FACING)) {
                case WEST -> SHAPE6;
                case SOUTH -> SHAPE5;
                case EAST -> SHAPE4;
                default -> SHAPE3;
            };
            default -> switch (state.getValue(FACING)) {
                case EAST, WEST -> SHAPE2;
                default -> SHAPE1;
            };
        };
    }
}
