package net.identidade.iden_decor.item;

import net.identidade.iden_decor.IdenDecorMod;
import net.identidade.iden_decor.block.ModBlocks;
import net.mcexpanded.fancytabsections.FancyTabSections;
import net.mcexpanded.fancytabsections.creativetab.ConglomerateOfItems;
import net.mcexpanded.fancytabsections.creativetab.SectionTextured;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

public class ModCreativeModeTab {

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
        addItems();
    }

    public static final List<DyeColor> COLOR_ORDER = List.of(
            DyeColor.WHITE,
            DyeColor.LIGHT_GRAY,
            DyeColor.GRAY,
            DyeColor.BLACK,
            DyeColor.BROWN,
            DyeColor.RED,
            DyeColor.ORANGE,
            DyeColor.YELLOW,
            DyeColor.LIME,
            DyeColor.GREEN,
            DyeColor.CYAN,
            DyeColor.LIGHT_BLUE,
            DyeColor.BLUE,
            DyeColor.PURPLE,
            DyeColor.MAGENTA,
            DyeColor.PINK
    );

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, IdenDecorMod.MOD_ID);

    static void addItems() {
        FancyTabSections.addSection(IdenDecorMod.rl("base"),
                new SectionTextured(
                        IdenDecorMod.rl("misc_blocks"),
                        Component.translatable("itemGroup.iden_decor.misc_blocks"),
                        IdenDecorMod.rl("textures/gui/fancy_tab_section/misc_blocks.png"),
                        0xFFFFFFFF,

                        ConglomerateOfItems.create()
                                .add(ModBlocks.GOLDEN_MEDAL)
                                .add(ModBlocks.SILVER_MEDAL)
                                .add(ModBlocks.COPPER_MEDAL)
                                .add(ModBlocks.WHEEL)
                                .add(ModBlocks.WHEEL_TIRE)
                ));


        FancyTabSections.addSection(IdenDecorMod.rl("base"),
                new SectionTextured(
                        IdenDecorMod.rl("scifi_blocks"),
                        Component.translatable("itemGroup.iden_decor.scifi_blocks"),
                        IdenDecorMod.rl("textures/gui/fancy_tab_section/scifi_blocks.png"),
                        0xFFFFFFFF,

                        ConglomerateOfItems.create()
                                .add(ModBlocks.CORE_PILLAR)
                                .add(ModBlocks.CORE_PILLAR_JUNCTION)
                                .add(ModBlocks.CORE_PLATES)
                                .add(ModBlocks.ENERGIZED_CORE_PLATES)
                                .add(ModBlocks.DIAGONAL_CORE_TILES)
                                .add(ModBlocks.CORE_TILES)
                                .add(ModBlocks.CORE_DOOR)
                                .add(ModBlocks.CORE_CONTROL_PANEL)
                                .add(ModBlocks.CORE_BUTTON_CONTROL_PANEL)
                                .add(ModBlocks.CORE_LEVER_CONTROL_PANEL)
                                .add(ModBlocks.CORE_CONTROL_PANEL_SCREEN)
                                .add(ModBlocks.BATTERY_CELL)
                                .add(ModItems.CORE_BATTERY)
                                .add(ModBlocks.WALL_CABLE)
                ));

        FancyTabSections.addSection(IdenDecorMod.rl("base"),
                new SectionTextured(
                        IdenDecorMod.rl("facility_blocks"),
                        Component.translatable("itemGroup.iden_decor.facility_blocks"),
                        IdenDecorMod.rl("textures/gui/fancy_tab_section/facility_blocks.png"),
                        0xFFFFFFFF,

                        ConglomerateOfItems.create()
                                .add(ModBlocks.OAK_METAL_SHELF)
                                .add(ModBlocks.ACACIA_METAL_SHELF)
                                .add(ModBlocks.BIRCH_METAL_SHELF)
                                .add(ModBlocks.SPRUCE_METAL_SHELF)
                                .add(ModBlocks.JUNGLE_METAL_SHELF)
                                .add(ModBlocks.DARK_OAK_METAL_SHELF)
                                .add(ModBlocks.CHERRY_METAL_SHELF)
                                .add(ModBlocks.CRIMSON_METAL_SHELF)
                                .add(ModBlocks.WARPED_METAL_SHELF)
                                .add(ModBlocks.BAMBOO_METAL_SHELF)
                                .add(ModBlocks.MANGROVE_METAL_SHELF)
                                .add(ModBlocks.METAL_SHELF)

                                .add(ModBlocks.COMPUTER)
                                .add(ModBlocks.KEYCARD_READER)

                                .add(ModBlocks.CEILING_LAMP)
                                .add(ModBlocks.FLOODLIGHT)
                                .add(ModBlocks.FLOOD_LAMP)
                                .add(ModBlocks.FLUORESCENT_LIGHT)
                                .add(ModBlocks.FLUORESCENT_LIGHT_BLOCK)

                                .add(ModBlocks.DRINKING_FOUNTAIN)
                                .add(ModBlocks.CUP_DISPENSER)
                                .add(ModBlocks.DARK_WATER_DISPENSER)
                                .add(ModBlocks.LIGHT_WATER_DISPENSER)
                                .add(ModBlocks.CCS_POSTER)
                                .add(ModBlocks.WATER_POSTER)

                                .add(ModBlocks.IRON_RAILING)
                                .add(ModBlocks.IRON_LATTICE)
                                .add(ModBlocks.GOLD_RAILING)
                                .add(ModBlocks.GOLDEN_LATTICE)
                                .add(ModBlocks.COPPER_RAILING)
                                .add(ModBlocks.COPPER_LATTICE)

                                .add(ModBlocks.DOUBLE_IRON_PIPES)

                                .add(ModBlocks.METAL_BARREL)
                                .add(ModBlocks.BLUE_METAL_BARREL)

                                .add(ModBlocks.IRON_GRATE)
                                .add(ModBlocks.STEEL_GRATE)
                                .add(ModBlocks.AIR_DUCT)
                                .add(ModBlocks.AIR_VENT)
                                .add(ModBlocks.IRON_SHEET_METAL)
                                .add(ModBlocks.CAUTION_FLOOR)
                                .add(ModBlocks.CAUTION_BLOCK)
                                .add(ModBlocks.CAUTION_BLOCK_STAIRS)
                                .add(ModBlocks.CAUTION_BLOCK_SLAB)
                                .add(ModBlocks.CAUTION_BLOCK_WALL)
                                .add(ModBlocks.CAUTION_BLOCK_FENCE)
                                .add(ModBlocks.WHITE_BRICKS)
                                .add(ModBlocks.WHITE_BRICKS_STAIRS)
                                .add(ModBlocks.WHITE_BRICKS_SLAB)
                                .add(ModBlocks.DIAGONAL_WHITE_TILES)
                                .add(ModBlocks.WHITE_SHORT_TILES)
                                .add(ModBlocks.CRACKED_WHITE_SHORT_TILES)
                                .add(ModBlocks.WHITE_TILES)
                                .add(ModBlocks.WHITE_TILES_STAIRS)
                                .add(ModBlocks.WHITE_TILES_SLAB)
                                .add(ModBlocks.SMOOTH_STONE_BRICKS)
                                .add(ModBlocks.DIAGONAL_SMOOTH_STONE_TILES)
                                .add(ModBlocks.SMOOTH_STONE_TILES)
                                .add(ModBlocks.SMOOTH_STONE_TILES_STAIRS)
                                .add(ModBlocks.SMOOTH_STONE_TILES_SLAB)

                                .add(ModBlocks.GRID_METAL_DOOR)
                                .add(ModBlocks.WHITE_METAL_DOOR)
                                .add(ModBlocks.YELLOW_METAL_DOOR)

                                .add(ModItems.FLOPPY_DISK)
                                .add(ModItems.KEYCARD)
                                .add(ModItems.PLASTIC_CUP)
                                .add(ModItems.WATER_CUP)
                                .add(ModItems.GUARANA_CUP)
                ));


        ConglomerateOfItems urban = ConglomerateOfItems.create()
                .add(ModBlocks.WALL_CLOCK)
                .add(ModBlocks.SEWING_MACHINE)
                .add(ModBlocks.MEDIUM_BOX)
                .add(ModBlocks.SMALL_BOX)
                .add(ModBlocks.PLASTIC_TABLE)
                .add(ModBlocks.METAL_TABLE)
                .add(ModBlocks.GUARANA_CAN)
                .add(ModBlocks.GUARANA_ANTARTICA)
                .add(ModBlocks.TRAFFIC_CONE)
                .add(ModBlocks.CONCRETE_BARRIER)
                .add(ModBlocks.SIGN_POST)
                .add(ModBlocks.STOP_SIGN)
                .add(ModBlocks.DO_NOT_ENTER_SIGN)
                .add(ModBlocks.NO_TURN_LEFT_SIGN)
                .add(ModBlocks.NO_U_TURN_LEFT_SIGN)
                .add(ModBlocks.INTERSECTION_SIGN)
                .add(ModBlocks.BLACK_HALF_WINDOW)
                .add(ModBlocks.BLACK_PANEL_WINDOW)
                .add(ModBlocks.BLACK_LATTICE_WINDOW)
                .add(ModBlocks.BLACK_WOOD_RAILING)
                .add(ModBlocks.BLACK_WOODEN_PANEL_DOOR)
                .add(ModBlocks.WHITE_HALF_WINDOW)
                .add(ModBlocks.WHITE_PANEL_WINDOW)
                .add(ModBlocks.WHITE_LATTICE_WINDOW)
                .add(ModBlocks.WHITE_WOOD_RAILING)
                .add(ModBlocks.WHITE_WOODEN_PANEL_DOOR)
                .add(ModBlocks.CALENDAR)
                .add(ModBlocks.WALL_NOTES);

        COLOR_ORDER.forEach(color -> {
            urban.add(ModBlocks.FRAMED_PLANKS.get(color));
        });

        FancyTabSections.addSection(IdenDecorMod.rl("base"),
                new SectionTextured(
                        IdenDecorMod.rl("urban_blocks"),
                        Component.translatable("itemGroup.iden_decor.urban_blocks"),
                        IdenDecorMod.rl("textures/gui/fancy_tab_section/urban_blocks.png"),
                        0xFFFFFFFF,
                        urban
                ));

        FancyTabSections.addSection(IdenDecorMod.rl("base"),
                new SectionTextured(
                        IdenDecorMod.rl("hotel_blocks"),
                        Component.translatable("itemGroup.iden_decor.hotel_blocks"),
                        IdenDecorMod.rl("textures/gui/fancy_tab_section/hotel_blocks.png"),
                        0xFFFFFFFF,

                        ConglomerateOfItems.create()
                                .add(ModBlocks.RED_DIAMOND_WALLPAPER)
                                .add(ModBlocks.GREEN_DIAMOND_WALLPAPER)
                                .add(ModBlocks.YELLOW_ARROW_WALLPAPER)
                                .add(ModBlocks.CYAN_ARROW_WALLPAPER)
                                .add(ModBlocks.CARVED_SPRUCE_PLANKS)
                                .add(ModBlocks.RED_GOLDEN_CARPET_BLOCK)
                                .add(ModBlocks.RED_GOLDEN_CARPET)
                                .add(ModBlocks.GREEN_ARROW_CARPET_BLOCK)
                                .add(ModBlocks.GREEN_ARROW_CARPET)
                                .add(ModBlocks.TELEPHONE)
                                .add(ModBlocks.WALL_LAMP)
                                .add(ModBlocks.LONG_CONCRETE_VASE)
                                .add(ModBlocks.WOODEN_PANEL_DOOR)
                                .add(ModItems.TELEPHONE_ITEM)
                ));

        FancyTabSections.addSection(IdenDecorMod.rl("base"),
                new SectionTextured(
                        IdenDecorMod.rl("daycare_blocks"),
                        Component.translatable("itemGroup.iden_decor.daycare_blocks"),
                        IdenDecorMod.rl("textures/gui/fancy_tab_section/daycare_blocks.png"),
                        0xFFFFFFFF,

                        ConglomerateOfItems.create()
                                .add(ModBlocks.BLUE_CLOUDS_WALLPAPER)
                                .add(ModBlocks.BLACK_CLOUDS_WALLPAPER)
                                .add(ModBlocks.BLACK_STARRY_WALLPAPER)
                                .add(ModBlocks.PUZZLE_WOOL)
                                .add(ModBlocks.PUZZLE_CARPET)
                                .add(ModBlocks.WOODEN_CRIB)
                                .add(ModBlocks.HANGING_SUN_LIGHT)
                                .add(ModBlocks.HANGING_MOON_LIGHT)
                                .add(ModBlocks.HANGING_CLOUD)
                                .add(ModBlocks.PLUSHIE_IDEN)
                                .add(ModBlocks.PLUSHIE_DOLI)
                                .add(ModBlocks.PLUSHIE_RED)
                                .add(ModBlocks.PLUSHIE_DINO)
                                .add(ModBlocks.PLUSHIE_RAFA)
                ));

        FancyTabSections.addSection(IdenDecorMod.rl("base"),
                new SectionTextured(
                        IdenDecorMod.rl("levers_and_buttons"),
                        Component.translatable("itemGroup.iden_decor.levers_and_buttons"),
                        IdenDecorMod.rl("textures/gui/fancy_tab_section/levers_and_buttons.png"),
                        0xFFFFFFFF,

                        ConglomerateOfItems.create()
                                .add(ModBlocks.EMERGENCY_LEVER)
                                .add(ModBlocks.HEAVY_LEVER)
                                .add(ModBlocks.VALVE_SWITCH)
                                .add(ModBlocks.POWER_SWITCH)
                                .add(ModBlocks.LIGHT_SWITCH)
                                .add(ModBlocks.GATE_BUTTON)
                                .add(ModBlocks.HEAVY_BUTTON)
                                .add(ModBlocks.BLAST_LEVER)
                ));

        ConglomerateOfItems coloredPlanks = ConglomerateOfItems.create();
        COLOR_ORDER.forEach(color -> {
            coloredPlanks.add(ModBlocks.PAINTED_PLANKS.get(color));
            coloredPlanks.add(ModBlocks.PAINTED_PLANKS_STAIRS.get(color));
            coloredPlanks.add(ModBlocks.PAINTED_PLANKS_SLABS.get(color));
        });

        FancyTabSections.addSection(IdenDecorMod.rl("base"),
                new SectionTextured(
                        IdenDecorMod.rl("colored_wood"),
                        Component.translatable("itemGroup.iden_decor.colored_wood"),
                        IdenDecorMod.rl("textures/gui/fancy_tab_section/colored_wood.png"),
                        0xFFFFFFFF,

                        coloredPlanks

                ));
    }

    public static final Supplier<CreativeModeTab> IDEN_DECOR_TAB = CREATIVE_MODE_TAB.register("base",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.COMPUTER.get()))
                    .title(Component.translatable("creativetab.iden_decor.base"))
                    .displayItems(((itemDisplayParameters, output) -> {

                    })).build());

}
