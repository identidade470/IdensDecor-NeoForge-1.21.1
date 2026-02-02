package net.identidade.iden_decor.item;

import net.identidade.iden_decor.IdenDecorMod;
import net.identidade.iden_decor.item.custom.PaintBrushItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(IdenDecorMod.MOD_ID);

    public static final DeferredItem<Item> PLASTIC_CUP = ITEMS.register("plastic_cup",
            () -> new Item(new Item.Properties().stacksTo(16)));

    public static final DeferredItem<Item> WATER_CUP = ITEMS.register("water_cup",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> TELEPHONE_ITEM = ITEMS.register("telephone_item",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> RED_WRAP = ITEMS.register("red_wrap",
            () -> new Item(new Item.Properties()));

//    public static final Map<DyeColor, Supplier<PaintBrushItem>> PAINT_BRUSHES = new HashMap<>();
//    static {
//        for (DyeColor color:DyeColor.values()) {
//            PAINT_BRUSHES.put(color, ITEMS.register(
//                    color.getSerializedName() + "_paint_brush",
//                    () -> new PaintBrushItem(color)
//            ));
//        }
//    }

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
