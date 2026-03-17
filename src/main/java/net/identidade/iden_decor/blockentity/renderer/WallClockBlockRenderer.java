package net.identidade.iden_decor.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.identidade.iden_decor.IdenDecorMod;
import net.identidade.iden_decor.blockentity.WallClockBlockEntity;
import net.identidade.iden_decor.blockentity.model.WallClockBlockModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class WallClockBlockRenderer implements BlockEntityRenderer<WallClockBlockEntity> {

    private final WallClockBlockModel<Entity> model;

    public WallClockBlockRenderer(BlockEntityRendererProvider.Context context) {
        this.model = new WallClockBlockModel<>(context.bakeLayer(WallClockBlockModel.LAYER_LOCATION));
    }

    @Override
    public void render(WallClockBlockEntity blockEntity, float v, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, int overlay) {
        poseStack.pushPose();

        poseStack.translate(0.5, -0.5, 0.5);

        model.setupAnim(null, 0, 0, blockEntity.getLevel().getDayTime(), 0, 0);
        model.renderToBuffer(poseStack, multiBufferSource.getBuffer(RenderType.entityCutout(ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "textures/block/wall_clock.png"))), light, overlay);

        poseStack.popPose();
    }
}
