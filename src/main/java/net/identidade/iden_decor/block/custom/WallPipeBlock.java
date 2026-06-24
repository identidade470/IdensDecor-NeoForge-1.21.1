package net.identidade.iden_decor.block.custom;

import com.mojang.serialization.MapCodec;
import net.identidade.iden_decor.block.custom.templates.SimpleMultidirectionalBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.Map;

public class WallPipeBlock extends PipeBlock {
    public static final MapCodec<WallPipeBlock> CODEC = simpleCodec(WallPipeBlock::new);

    public WallPipeBlock(Properties properties) {
        super(4/16f, properties);
        registerDefaultState(super.defaultBlockState());
    }

    @Override
    protected MapCodec<? extends PipeBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
        super.createBlockStateDefinition(builder);
    }
}
