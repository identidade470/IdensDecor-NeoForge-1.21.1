package net.identidade.iden_decor.block.custom;

import net.identidade.iden_decor.block.custom.templates.SimpleHorizontalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class CurtainBlock extends SimpleHorizontalBlock {

    public static final BooleanProperty OPENED = BooleanProperty.create("opened");

    public CurtainBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(OPENED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(OPENED);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        BlockState belowState = level.getBlockState(pos.below());
        BlockState aboveState = level.getBlockState(pos.above());

        boolean above = (aboveState.is(this) && aboveState.getValue(FACING) == state.getValue(FACING));
        boolean below = (belowState.is(this) && belowState.getValue(FACING) == state.getValue(FACING));

        if (below) {
            return state.setValue(OPENED, belowState.getValue(OPENED));
        }

        return state;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            level.setBlock(pos, state.setValue(OPENED, !state.getValue(OPENED)), 3);
            level.updateNeighborsAt(pos, this);
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
