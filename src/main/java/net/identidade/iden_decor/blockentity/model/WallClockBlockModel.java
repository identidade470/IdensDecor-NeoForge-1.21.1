package net.identidade.iden_decor.blockentity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.identidade.iden_decor.IdenDecorMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class WallClockBlockModel<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "wall_clock"), "main");
    private final ModelPart hour_pin;
    private final ModelPart minute_pin;
    private final ModelPart base;

    public WallClockBlockModel(ModelPart root) {
        this.hour_pin = root.getChild("hour_pin");
        this.minute_pin = root.getChild("minute_pin");
        this.base = root.getChild("base");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition hour_pin = partdefinition.addOrReplaceChild("hour_pin", CubeListBuilder.create().texOffs(12, 19).addBox(-15.5F, -9.0F, 2.0F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(11.5F, 24.5F, 4.75F));

        PartDefinition minute_pin = partdefinition.addOrReplaceChild("minute_pin", CubeListBuilder.create().texOffs(12, 20).addBox(-12.0F, -12.5F, 1.75F, 1.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(11.5F, 24.5F, 4.75F));

        PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-17.5F, -14.5F, 2.25F, 12.0F, 12.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 13).addBox(-17.5F, -14.5F, 1.25F, 2.0F, 12.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 13).addBox(-15.5F, -14.5F, 1.25F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(-15.5F, -4.5F, 1.25F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 19).addBox(-7.5F, -14.5F, 1.25F, 2.0F, 12.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(6, 19).addBox(-12.5F, -9.5F, 1.25F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(11.5F, 24.5F, 4.75F));
        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        float time = (float) Math.floor(ageInTicks / 20.0F) * 20.0F;

        this.hour_pin.zRot = (((time / 1000.0F) + 6.0F) % 12.0F / 12.0F) * ((float) Math.PI * 2F);
        //this.minute_hand.zRot = ((time % 1000.0F) / 1000.0F) * ((float) Math.PI * 2F);
        this.minute_pin.zRot = ((time % 1000.0F) / 1000.0F) * ((float) Math.PI * 2F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int i1, int i2) {
        this.hour_pin.render(poseStack, vertexConsumer, i, i1, i2);
        this.minute_pin.render(poseStack, vertexConsumer, i, i1, i2);
        this.base.render(poseStack, vertexConsumer, i, i1, i2);
    }

}