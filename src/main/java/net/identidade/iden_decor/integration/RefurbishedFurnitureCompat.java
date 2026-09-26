package net.identidade.iden_decor.integration;

import com.mrcrayfish.furniture.refurbished.client.renderer.blockentity.ElectricBlockEntityRenderer;
import net.identidade.iden_decor.blockentity.LightBlockEntity;
import net.identidade.iden_decor.blockentity.ModBlockEntities;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class RefurbishedFurnitureCompat {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.LIGHT_BE.get(), context -> new ElectricBlockEntityRenderer(context));
    }
}
