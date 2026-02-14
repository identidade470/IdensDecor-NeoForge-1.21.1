package net.identidade.iden_decor.client.gui;

import net.identidade.iden_decor.IdenDecorMod;
import net.identidade.iden_decor.client.gui.custom.SewingMachineMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, IdenDecorMod.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<SewingMachineMenu>> SEWING_MACHINE = MENUS.register("sewing_machine_menu",
            () -> IMenuTypeExtension.create(SewingMachineMenu::new));

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
