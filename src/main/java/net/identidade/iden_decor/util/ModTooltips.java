package net.identidade.iden_decor.util;

import net.identidade.iden_decor.IdenDecorMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

@EventBusSubscriber(
        modid = IdenDecorMod.MOD_ID,
        value = Dist.CLIENT
)
public class ModTooltips {

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        List<Component> tooltip = event.getToolTip();
        ItemStack stack = event.getItemStack();

        ResourceLocation id = BuiltInRegistries.ITEM.getKey(stack.getItem());

        if (id==null) return;

        String base = "tooltip." + id.getNamespace() + "." + id.getPath();

        if (!I18n.exists(base)) return;

        if (event.getFlags().hasShiftDown()) {
            tooltip.add(Component.translatable("tooltip.iden_decor.shift_down").withStyle(ChatFormatting.RED));

            tooltip.add(Component.translatable(base).withStyle(ChatFormatting.GRAY));
        } else {
            tooltip.add(Component.translatable("tooltip.iden_decor.shift_up").withStyle(ChatFormatting.GOLD));
        }



    }

}
