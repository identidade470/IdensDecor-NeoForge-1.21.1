package net.identidade.iden_decor.rendering;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.identidade.iden_decor.block.custom.ComputerBlock;
import net.identidade.iden_decor.blockentity.ComputerBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

import java.util.List;

public class ComputerBlockRenderer implements BlockEntityRenderer<ComputerBlockEntity> {

    public ComputerBlockRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(ComputerBlockEntity pBlockEntity, float pPartialTick, PoseStack pPostStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        pPostStack.pushPose();

        pPostStack.translate(0.5,0.6,0.5);

        var state = pBlockEntity.getBlockState();
        var facing = state.getValue(ComputerBlock.FACING);

        float depth = 0.32f;

        switch (facing) {
            case NORTH -> {
                pPostStack.translate(0,0,-depth);
                pPostStack.mulPose(Axis.YP.rotationDegrees(180));
            }
            case SOUTH -> {
                pPostStack.translate(0,0,depth);
                pPostStack.mulPose(Axis.YP.rotationDegrees(0));
            }
            case WEST -> {
                pPostStack.translate(-depth,0,0);
                pPostStack.mulPose(Axis.YP.rotationDegrees(-90));
            }
            case EAST -> {
                pPostStack.translate(depth,0,0);
                pPostStack.mulPose(Axis.YP.rotationDegrees(90));
            }
        }

        float scale = 0.01f;
        pPostStack.scale(scale, -scale, scale);

        var font = Minecraft.getInstance().font;
        String text = pBlockEntity.displayText;

        int maxWidth = 60;

        List<FormattedCharSequence> lines = font.split(Component.literal(text), maxWidth);

        int totalHeight = lines.size() * font.lineHeight;
        int yOffset = -totalHeight / 3;

        for (FormattedCharSequence line: lines) {
            float x = -font.width(line) / 2f;
            font.drawInBatch(
                    line,
                    x,
                    yOffset,
                    0xffffff,
                    false,
                    pPostStack.last().pose(),
                    pBufferSource,
                    Font.DisplayMode.NORMAL,
                    0,
                    LightTexture.FULL_BRIGHT
            );

            yOffset += font.lineHeight;
        }

        pPostStack.popPose();
    }
}
