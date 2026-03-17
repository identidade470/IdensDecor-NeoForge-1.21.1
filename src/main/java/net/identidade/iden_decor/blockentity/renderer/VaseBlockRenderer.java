package net.identidade.iden_decor.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.identidade.iden_decor.blockentity.VaseBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class VaseBlockRenderer implements BlockEntityRenderer<VaseBlockEntity> {

    public VaseBlockRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(VaseBlockEntity blockEntity, float partialTicks, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, int overlay) {
        Block plant = blockEntity.getPlant();

        if (plant == Blocks.AIR) return;

        poseStack.pushPose();
        poseStack.translate(0.2,1,0.2);
        poseStack.scale(0.6f,0.6f,0.6f);

        Minecraft.getInstance().getBlockRenderer()
                .renderSingleBlock(plant.defaultBlockState(), poseStack, multiBufferSource, light, overlay);

        poseStack.popPose();
    }
}
