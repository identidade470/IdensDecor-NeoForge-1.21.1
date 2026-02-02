package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleHorizontalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BoxBlock extends SimpleHorizontalBlock {

    private final VoxelShape shape;
    private final VoxelShape shape2;

    public static final BooleanProperty OPENED = BooleanProperty.create("opened");

    public BoxBlock(Properties properties, VoxelShape shape, VoxelShape shape2) {
        super(properties);
        this.registerDefaultState(defaultBlockState().setValue(OPENED, false));
        this.shape = shape;
        this.shape2 = shape2;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(OPENED);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> shape;
            case SOUTH -> shape;
            case WEST -> shape2;
            case EAST -> shape2;
            default -> shape;
        };
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {

        if (!level.isClientSide) {
            level.setBlock(pos, state.setValue(OPENED, !state.getValue(OPENED)), 3);
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
