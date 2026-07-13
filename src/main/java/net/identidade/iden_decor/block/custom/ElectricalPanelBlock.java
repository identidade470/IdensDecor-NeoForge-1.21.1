package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleHorizontalBlock;
import net.identidade.iden_decor.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ElectricalPanelBlock extends SimpleHorizontalBlock {

    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    public static final BooleanProperty OPENED = BooleanProperty.create("opened");

    private static final VoxelShape SHAPE_NORTH = Block.box(3, 1, 12, 13, 15, 16);
    private static final VoxelShape SHAPE_WEST = Block.box(12, 1, 3, 16, 15, 13);
    private static final VoxelShape SHAPE_SOUTH = Block.box(3, 1, 0, 13, 15, 4);
    private static final VoxelShape SHAPE_EAST = Block.box(0, 1, 3, 4, 15, 13);

    public ElectricalPanelBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(POWERED, false)
                .setValue(OPENED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(POWERED, OPENED);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (player.isShiftKeyDown()) {
            // Open the panel

            boolean opened = !state.getValue(OPENED);

            if (!level.isClientSide) {
                level.setBlock(pos, state.setValue(OPENED, opened), 3);
                level.playSound(null, pos, opened?SoundEvents.COPPER_TRAPDOOR_CLOSE:SoundEvents.COPPER_TRAPDOOR_OPEN, SoundSource.BLOCKS);
            }

            return InteractionResult.SUCCESS;
        } else {
            // Cycles the powered

            if (state.getValue(OPENED)) {

                level.addParticle(
                        new DustParticleOptions(DustParticleOptions.REDSTONE_PARTICLE_COLOR, 1f),
                        pos.getX() + 0.5f + (level.random.nextDouble() - 0.5) * 0.4,
                        pos.getY() + 0.5f + (level.random.nextDouble() - 0.5) * 0.4,
                        pos.getZ() + 0.5f + (level.random.nextDouble() - 0.5) * 0.4,
                        0, 0, 0
                );

                if (!level.isClientSide) {
                    level.setBlock(pos, state.cycle(POWERED), 3);
                    updateNeighbors(state, level, pos);



                    level.playSound(null, pos, ModSounds.BREAKER_CLICK.get(), SoundSource.BLOCKS, 1, state.getValue(POWERED)?1:0.75f);

                }

                return InteractionResult.SUCCESS;
            }
        }

        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = state.getValue(FACING).getOpposite();
        BlockPos blockPos = pos.relative(direction);
        return level.getBlockState(blockPos).isFaceSturdy(level, pos, direction.getOpposite());
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction.getOpposite() == state.getValue(FACING) && !state.canSurvive(level, pos)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
        }
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (state.getBlock() != newState.getBlock()) {
               if (state.getValue(POWERED)) {
                   updateNeighbors(state, level, pos);
               }
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    private void updateNeighbors(BlockState state, Level level, BlockPos pos) {
        level.updateNeighborsAt(pos, this);
        level.updateNeighborsAt(pos.relative(state.getValue(FACING).getOpposite()), this);
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return state.getValue(POWERED)?15:0;
    }

    @Override
    protected int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return state.getValue(POWERED) && state.getValue(FACING) == direction ? 15:0;
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case WEST -> SHAPE_WEST;
            case SOUTH -> SHAPE_SOUTH;
            case EAST -> SHAPE_EAST;
            default -> SHAPE_NORTH;
        };
    }
}
