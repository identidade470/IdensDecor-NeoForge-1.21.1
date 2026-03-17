package net.identidade.iden_decor;

import net.identidade.iden_decor.blockentity.ModBlockEntities;
import net.identidade.iden_decor.blockentity.model.WallClockBlockModel;
import net.identidade.iden_decor.blockentity.renderer.CubicShelfBlockRenderer;
import net.identidade.iden_decor.blockentity.renderer.VaseBlockRenderer;
import net.identidade.iden_decor.blockentity.renderer.WallClockBlockRenderer;
import net.identidade.iden_decor.client.gui.ModMenus;
import net.identidade.iden_decor.client.gui.custom.SewingMachineScreen;
import net.identidade.iden_decor.blockentity.renderer.ComputerBlockRenderer;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = IdenDecorMod.MOD_ID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = IdenDecorMod.MOD_ID, value = Dist.CLIENT)
public class IdenDecorModClient {


    public IdenDecorModClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        IdenDecorMod.LOGGER.info("HELLO FROM CLIENT SETUP");
        IdenDecorMod.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.COMPUTER_BE.get(), ComputerBlockRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.WALL_CLOCK_BE.get(), WallClockBlockRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.VASE_BE.get(), VaseBlockRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.CUBIC_SHELF_BE.get(), CubicShelfBlockRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(WallClockBlockModel.LAYER_LOCATION, WallClockBlockModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenus.SEWING_MACHINE.get(), SewingMachineScreen::new);
    }
}
