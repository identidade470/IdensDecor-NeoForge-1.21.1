package net.identidade.iden_decor.block.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;

public class WoodRailingBlock extends WallBlock {

    private final Map<BlockState, VoxelShape> shapeByIndex;

    public WoodRailingBlock(Properties properties) {
        super(properties);
        this.shapeByIndex = this.makeShapes(2.0F, 1.0F, 16.0F, 1.0F, 14.0F, 14.0F);
    }

    private static VoxelShape applyWallShape(VoxelShape baseShape, WallSide height, VoxelShape lowShape, VoxelShape tallShape) {
        if (height == WallSide.TALL) {
            return Shapes.or(baseShape, tallShape);
        } else {
            return height == WallSide.LOW ? Shapes.or(baseShape, lowShape) : baseShape;
        }
    }

    private Map<BlockState, VoxelShape> makeShapes(float width, float depth, float wallPostHeight, float wallMinY, float wallLowHeight, float wallTallHeight) {
        float f = 8.0F - width;
        float f1 = 8.0F + width;
        float f2 = 8.0F - depth;
        float f3 = 8.0F + depth;
        VoxelShape voxelshape = Block.box((double)f, (double)0.0F, (double)f, (double)f1, (double)wallPostHeight, (double)f1);
        VoxelShape voxelshape1 = Block.box((double)f2, (double)wallMinY, (double)0.0F, (double)f3, (double)wallLowHeight, (double)f3);
        VoxelShape voxelshape2 = Block.box((double)f2, (double)wallMinY, (double)f2, (double)f3, (double)wallLowHeight, (double)16.0F);
        VoxelShape voxelshape3 = Block.box((double)0.0F, (double)wallMinY, (double)f2, (double)f3, (double)wallLowHeight, (double)f3);
        VoxelShape voxelshape4 = Block.box((double)f2, (double)wallMinY, (double)f2, (double)16.0F, (double)wallLowHeight, (double)f3);
        VoxelShape voxelshape5 = Block.box((double)f2, (double)wallMinY, (double)0.0F, (double)f3, (double)wallTallHeight, (double)f3);
        VoxelShape voxelshape6 = Block.box((double)f2, (double)wallMinY, (double)f2, (double)f3, (double)wallTallHeight, (double)16.0F);
        VoxelShape voxelshape7 = Block.box((double)0.0F, (double)wallMinY, (double)f2, (double)f3, (double)wallTallHeight, (double)f3);
        VoxelShape voxelshape8 = Block.box((double)f2, (double)wallMinY, (double)f2, (double)16.0F, (double)wallTallHeight, (double)f3);
        ImmutableMap.Builder<BlockState, VoxelShape> builder = ImmutableMap.builder();

        for(Boolean obool : UP.getPossibleValues()) {
            for(WallSide wallside : EAST_WALL.getPossibleValues()) {
                for(WallSide wallside1 : NORTH_WALL.getPossibleValues()) {
                    for(WallSide wallside2 : WEST_WALL.getPossibleValues()) {
                        for(WallSide wallside3 : SOUTH_WALL.getPossibleValues()) {
                            VoxelShape voxelshape9 = Shapes.empty();
                            voxelshape9 = applyWallShape(voxelshape9, wallside, voxelshape4, voxelshape8);
                            voxelshape9 = applyWallShape(voxelshape9, wallside2, voxelshape3, voxelshape7);
                            voxelshape9 = applyWallShape(voxelshape9, wallside1, voxelshape1, voxelshape5);
                            voxelshape9 = applyWallShape(voxelshape9, wallside3, voxelshape2, voxelshape6);
                            if (obool) {
                                voxelshape9 = Shapes.or(voxelshape9, voxelshape);
                            }

                            BlockState blockstate = (BlockState)((BlockState)((BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue(UP, obool)).setValue(EAST_WALL, wallside)).setValue(WEST_WALL, wallside2)).setValue(NORTH_WALL, wallside1)).setValue(SOUTH_WALL, wallside3);
                            builder.put((BlockState)blockstate.setValue(WATERLOGGED, false), voxelshape9);
                            builder.put((BlockState)blockstate.setValue(WATERLOGGED, true), voxelshape9);
                        }
                    }
                }
            }
        }

        return builder.build();
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return (VoxelShape)this.shapeByIndex.get(state);
    }
}
