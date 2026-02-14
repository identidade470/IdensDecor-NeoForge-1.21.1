package net.identidade.iden_decor.item;

import net.identidade.iden_decor.IdenDecorMod;
import net.identidade.iden_decor.item.custom.DrinkItem;
import net.identidade.iden_decor.item.custom.GuaranaCupItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.WritableBookItem;
import net.minecraft.world.item.component.WritableBookContent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(IdenDecorMod.MOD_ID);

    public static final DeferredItem<Item> PLASTIC_CUP = ITEMS.register("plastic_cup",
            () -> new Item(new Item.Properties().stacksTo(16)));

    public static final DeferredItem<Item> WATER_CUP = ITEMS.register("water_cup",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.WATER_CUP).stacksTo(1)));

    public static final DeferredItem<Item> GUARANA_CUP = ITEMS.register("guarana_cup",
            () -> new GuaranaCupItem(new Item.Properties().food(ModFoodProperties.GUARANA_CUP).stacksTo(1)));

    public static final DeferredItem<Item> TELEPHONE_ITEM = ITEMS.register("telephone_item",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> RED_WRAP = ITEMS.register("red_wrap",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> FLOPPY_DISK = ITEMS.register("floppy_disk",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> KEYCARD = ITEMS.register("keycard",
            () -> new Item(new Item.Properties().stacksTo(1)));

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
