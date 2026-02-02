package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleHorizontalBlock;
import net.identidade.iden_decor.item.ModItems;
import net.identidade.iden_decor.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TelephoneBlock extends SimpleHorizontalBlock {

    public static final BooleanProperty PHONE = BooleanProperty.create("phone");
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");

    public TelephoneBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(defaultBlockState().setValue(PHONE, true));
        this.registerDefaultState(defaultBlockState().setValue(POWERED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PHONE);
        builder.add(POWERED);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (state.getValue(PHONE)) {
            if (!player.getMainHandItem().isEmpty()) return InteractionResult.PASS;
            level.setBlock(pos, state.setValue(PHONE, false), 3);
            level.playSound(null, pos, ModSounds.PHONE_PICK.get(), SoundSource.BLOCKS, 1, 1);

            player.setItemInHand(InteractionHand.MAIN_HAND, ModItems.TELEPHONE_ITEM.toStack());

            return InteractionResult.sidedSuccess(level.isClientSide);
        }

        return InteractionResult.PASS;
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        super.neighborChanged(state, level, pos, neighborBlock, neighborPos, movedByPiston);
        boolean redstone = level.hasNeighborSignal(pos);

        if (redstone != state.getValue(POWERED)) {
            if (redstone && state.getValue(PHONE)) {
                level.playSound(null, pos, ModSounds.PHONE_RING.get(), SoundSource.BLOCKS);
            }

            level.setBlock(pos, state.setValue(POWERED, redstone), 3);
        }
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return state.getValue(PHONE) ? 5 : 0;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (!stack.is(ModItems.TELEPHONE_ITEM)) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        if (!state.getValue(PHONE)) {
            if (!level.isClientSide) {
                level.setBlock(pos, state.setValue(PHONE, true), 3);
                level.playSound(null, pos, ModSounds.PHONE_SLAM.get(), SoundSource.BLOCKS, 1, 1);
                stack.shrink(1);
            }

            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        boolean flag = (Boolean)state.getValue(PHONE);
        return switch (state.getValue(FACING)) {
            case NORTH -> !flag?
                    Shapes.or(
                            Block.box(6, 5, 8, 10, 7, 10),
                            Block.box(5, 2, 7, 11, 5, 11),
                            Block.box(4, 0, 4, 12, 3, 12)
                    ) :
                    Shapes.or(
                            Block.box(2, 5, 7.5, 14, 8, 10.5),
                            Block.box(5, 2, 7, 11, 5, 11),
                            Block.box(4, 0, 4, 12, 3, 12)
                    );
            case EAST -> !flag?
                    Shapes.or(
                            Block.box(6, 5, 6, 8, 7, 10),
                            Block.box(5, 2, 5, 9, 5, 11),
                            Block.box(4, 0, 4, 12, 3, 12)
                    ):
                    Shapes.or(
                            Block.box(5.5, 5, 2, 8.5, 8, 14),
                            Block.box(5, 2, 5, 9, 5, 11),
                            Block.box(4, 0, 4, 12, 3, 12)
            );
            case SOUTH -> !flag?
                    Shapes.or(
                            Block.box(6, 5, 6, 10, 7, 8),
                            Block.box(5, 2, 5, 11, 5, 9),
                            Block.box(4, 0, 4, 12, 3, 12)
                    ):
                    Shapes.or(
                            Block.box(2, 5, 5.5, 14, 8, 8.5),
                            Block.box(5, 2, 5, 11, 5, 9),
                            Block.box(4, 0, 4, 12, 3, 12)
                    );
            case WEST -> !flag?
                    Shapes.or(
                            Block.box(8, 5, 6, 10, 7, 10),
                            Block.box(7, 2, 5, 11, 5, 11),
                            Block.box(4, 0, 4, 12, 3, 12)
                    ):
                    Shapes.or(
                            Block.box(7.5, 5, 2, 10.5, 8, 14),
                            Block.box(7, 2, 5, 11, 5, 11),
                            Block.box(4, 0, 4, 12, 3, 12)
                    );
            default -> Block.box(0,0,0,16,16,16);
        };
    }
}
