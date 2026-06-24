package net.identidade.iden_decor.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.SimpleMapCodec;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.MultifaceSpreader;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class PlaneWallBlock extends MultifaceBlock {

    private static final MapCodec<PlaneWallBlock> CODEC = simpleCodec(PlaneWallBlock::new);

    public PlaneWallBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends MultifaceBlock> codec() {
        return CODEC;
    }

    @Override
    public MultifaceSpreader getSpreader() {
        return null;
    }
}
