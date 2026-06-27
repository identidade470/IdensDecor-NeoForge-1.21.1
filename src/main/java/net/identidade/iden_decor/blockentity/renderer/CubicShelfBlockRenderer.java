package net.identidade.iden_decor.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.identidade.iden_decor.block.custom.CubicShelfBlock;
import net.identidade.iden_decor.blockentity.CubicShelfBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.*;

public class CubicShelfBlockRenderer implements BlockEntityRenderer<CubicShelfBlockEntity> {

    public CubicShelfBlockRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(CubicShelfBlockEntity blockEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int i1) {

        ItemStack stack = blockEntity.getItem();
        Direction facing = blockEntity.getBlockState().getValue(CubicShelfBlock.FACING);

        if (stack.isEmpty()) return;

        poseStack.pushPose();
        poseStack.translate(0.5, 0.5, 0.5);
        poseStack.mulPose(Axis.YP.rotationDegrees(-facing.toYRot()));
        poseStack.scale(.45f,.45f,.45f);

        if (stack.getItem() instanceof BlockItem blockItem) {
            poseStack.translate(-0.5, -0.55, -1);
            Minecraft.getInstance().getBlockRenderer()
                    .renderSingleBlock(blockItem.getBlock().defaultBlockState(), poseStack, multiBufferSource, i, i1);
        } else {
            poseStack.translate(0,0,-.5f);
            Minecraft.getInstance().getItemRenderer()
                    .renderStatic(stack, ItemDisplayContext.FIXED, i, i1, poseStack, multiBufferSource, blockEntity.getLevel(), 0);
        }

        poseStack.popPose();
    }
}
