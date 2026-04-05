package net.identidade.iden_decor.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class CribBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<BedPart> PART = BlockStateProperties.BED_PART;

    public CribBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().
                setValue(FACING, Direction.NORTH).
                setValue(PART, BedPart.FOOT));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PART).add(FACING);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection();
        BlockPos pos = context.getClickedPos();
        BlockPos rleativePos = pos.relative(facing);

        Level level = context.getLevel();

        return level.getBlockState(pos).canBeReplaced(context) && level.getWorldBorder().isWithinBounds(pos) ? this.defaultBlockState().setValue(FACING, facing) : null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            BlockPos relativePos = pos.relative(state.getValue(FACING));

            level.setBlock(relativePos, state.setValue(PART, BedPart.HEAD), 3);
            level.blockUpdated(pos, Blocks.AIR);

            state.updateNeighbourShapes(level, pos, 3);
        }
    }

    private static Direction getNeighbourDirection(BedPart part, Direction direction) {
        return part == BedPart.FOOT ? direction : direction.getOpposite();
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction != getNeighbourDirection(state.getValue(PART), state.getValue(FACING))) {
            return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
        } else {
            return neighborState.is(this) && neighborState.getValue(PART) != state.getValue(PART) ? state : Blocks.AIR.defaultBlockState();
        }
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide && player.isCreative()) {
            BedPart part = state.getValue(PART);
            if (part == BedPart.FOOT) {
                BlockPos relative = pos.relative(getNeighbourDirection(part, state.getValue(FACING)));
                BlockState blockState = level.getBlockState(relative);
                if (blockState.is(this) && blockState.getValue(PART) == BedPart.HEAD) {
                    level.setBlock(relative, Blocks.AIR.defaultBlockState(), 35);
                    level.levelEvent(player, 2001, relative, Block.getId(blockState));
                }
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    protected boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        BedPart part = state.getValue(PART);

        Boolean partIsHead = part == BedPart.HEAD;

        return switch (state.getValue(PART)) {
            case HEAD -> switch (state.getValue(FACING)) {
                case WEST -> Shapes.or(
                        Block.box(4, 3, 13, 16, 16, 15),
                        Block.box(4, 3, 1, 16, 16, 3),
                        Block.box(2, 0, 1, 4, 16, 15),
                        Block.box(4, 3, 3, 16, 5, 13),
                        Block.box(5, 5, 3, 16, 7, 13)
                );
                case SOUTH -> Shapes.or(
                        Block.box(13, 3, 0, 15, 16, 12),
                        Block.box(1, 3, 0, 3, 16, 12),
                        Block.box(1, 0, 12, 15, 16, 14),
                        Block.box(3, 3, 0, 13, 5, 12),
                        Block.box(3, 5, 0, 13, 7, 11)
                );
                case EAST -> Shapes.or(
                        Block.box(0, 3, 1, 12, 16, 3),
                        Block.box(0, 3, 13, 12, 16, 15),
                        Block.box(12, 0, 1, 14, 16, 15),
                        Block.box(0, 3, 3, 12, 5, 13),
                        Block.box(0, 5, 3, 11, 7, 13)
                );
                default -> Shapes.or(
                        Block.box(1, 3, 4, 3, 16, 16),
                        Block.box(13, 3, 4, 15, 16, 16),
                        Block.box(1, 0, 2, 15, 16, 4),
                        Block.box(3, 3, 4, 13, 5, 16),
                        Block.box(3, 5, 5, 13, 7, 16)
                );
            };
            case FOOT -> switch (state.getValue(FACING)) {
                case WEST -> Shapes.or(
                        Block.box(0, 3, 1, 12, 16, 3),
                        Block.box(0, 3, 13, 12, 16, 15),
                        Block.box(12, 0, 1, 14, 16, 15),
                        Block.box(0, 3, 3, 12, 5, 13),
                        Block.box(0, 5, 3, 11, 7, 13)
                );
                case SOUTH -> Shapes.or(
                        Block.box(1, 3, 4, 3, 16, 16),
                        Block.box(13, 3, 4, 15, 16, 16),
                        Block.box(1, 0, 2, 15, 16, 4),
                        Block.box(3, 3, 4, 13, 5, 16),
                        Block.box(3, 5, 5, 13, 7, 16)
                );
                case EAST -> Shapes.or(
                        Block.box(4, 3, 13, 16, 16, 15),
                        Block.box(4, 3, 1, 16, 16, 3),
                        Block.box(2, 0, 1, 4, 16, 15),
                        Block.box(4, 3, 3, 16, 5, 13),
                        Block.box(5, 5, 3, 16, 7, 13)
                );
                default -> Shapes.or(
                        Block.box(13, 3, 0, 15, 16, 12),
                        Block.box(1, 3, 0, 3, 16, 12),
                        Block.box(1, 0, 12, 15, 16, 14),
                        Block.box(3, 3, 0, 13, 5, 12),
                        Block.box(3, 5, 0, 13, 7, 11)
                );
            };
        };
    }
}
