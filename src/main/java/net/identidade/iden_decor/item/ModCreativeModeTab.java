package net.identidade.iden_decor.item;

import net.identidade.iden_decor.IdenDecorMod;
import net.identidade.iden_decor.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IdenDecorMod.MOD_ID);

    public static final Supplier<CreativeModeTab> IDEN_DECOR_TAB = CREATIVE_MODE_TAB.register("base",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.PLUSHIE_DOLI.get()))
                    .title(Component.translatable("creativetab.iden_decor.base"))
                    .displayItems(((itemDisplayParameters, output) -> {

//                        ModItems.PAINT_BRUSHES.values().forEach(item -> output.accept(item.get()));

                        output.accept(ModBlocks.METAL_BARREL);
                        output.accept(ModBlocks.BLUE_METAL_BARREL);
                        output.accept(ModBlocks.WHITE_TILES);
                        output.accept(ModBlocks.WHITE_TILES_STAIRS);
                        output.accept(ModBlocks.WHITE_TILES_SLAB);
                        output.accept(ModBlocks.STONE_TILES);
                        output.accept(ModBlocks.STONE_TILES_STAIRS);
                        output.accept(ModBlocks.STONE_TILES_SLAB);
                        output.accept(ModBlocks.IRON_SHEET_METAL);
                        output.accept(ModBlocks.AIR_DUCT);
                        output.accept(ModBlocks.AIR_VENT);
                        output.accept(ModBlocks.STEEL_GRATE);
                        output.accept(ModBlocks.IRON_GRATE);
                        output.accept(ModBlocks.IRON_RAILING);
                        output.accept(ModBlocks.COPPER_RAILING);
                        output.accept(ModBlocks.GOLD_RAILING);
                        output.accept(ModBlocks.PLASTIC_TABLE);
                        output.accept(ModBlocks.CCS_POSTER);
                        output.accept(ModBlocks.WATER_POSTER);
                        output.accept(ModBlocks.DARK_WATER_DISPENSER);
                        output.accept(ModBlocks.LIGHT_WATER_DISPENSER);
                        output.accept(ModBlocks.HEAVY_BUTTON);
                        output.accept(ModBlocks.GATE_BUTTON);
                        output.accept(ModBlocks.HEAVY_LEVER);
                        output.accept(ModBlocks.EMERGENCY_LEVER);
                        output.accept(ModBlocks.POWER_SWITCH);
                        output.accept(ModBlocks.LIGHT_SWITCH);
                        output.accept(ModBlocks.TELEPHONE);
                        output.accept(ModBlocks.SMALL_BOX);
//                        output.accept(ModBlocks.MEDIUM_BOX);
//                        output.accept(ModBlocks.BIG_BOX);
                        output.accept(ModBlocks.GUARANA_ANTARTICA);
                        output.accept(ModBlocks.PLUSHIE_IDEN);
                        output.accept(ModBlocks.PLUSHIE_DOLI);
                        output.accept(ModBlocks.PLUSHIE_DINO);
                        output.accept(ModBlocks.PLUSHIE_RED);
                        output.accept(ModBlocks.PLUSHIE_RAFA);
                        output.accept(ModItems.PLASTIC_CUP);
                        output.accept(ModItems.WATER_CUP);
                        output.accept(ModItems.TELEPHONE_ITEM);
                        output.accept(ModBlocks.FLUORESCENT_LIGHT);
                        output.accept(ModItems.RED_WRAP);

                        ModBlocks.PAINTED_PLANKS.values().forEach(block -> output.accept(block.get()));
                    })).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
