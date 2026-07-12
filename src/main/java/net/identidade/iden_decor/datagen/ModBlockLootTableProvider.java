package net.identidade.iden_decor.datagen;

import net.identidade.iden_decor.block.ModBlocks;
import net.identidade.iden_decor.block.custom.CribBlock;
import net.identidade.iden_decor.block.custom.templates.SimpleFourStackableBlock;
import net.identidade.iden_decor.block.custom.templates.SimpleThreeStackableBlock;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.List;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.IRON_GRATE.get());
        dropSelf(ModBlocks.STEEL_GRATE.get());

        dropSelf(ModBlocks.WHITE_BRICKS.get());
        dropSelf(ModBlocks.WHITE_BRICKS_STAIRS.get());

        dropSelf(ModBlocks.WHITE_TILES.get());
        dropSelf(ModBlocks.WHITE_TILES_STAIRS.get());

        dropSelf(ModBlocks.SMOOTH_STONE_TILES.get());
        dropSelf(ModBlocks.SMOOTH_STONE_TILES_STAIRS.get());
        dropSelf(ModBlocks.SMOOTH_STONE_BRICKS.get());

        dropSelf(ModBlocks.DARK_WATER_DISPENSER.get());
        dropSelf(ModBlocks.LIGHT_WATER_DISPENSER.get());
        dropSelf(ModBlocks.HEAVY_BUTTON.get());
        dropSelf(ModBlocks.GATE_BUTTON.get());
        dropSelf(ModBlocks.LIGHT_SWITCH.get());
        dropSelf(ModBlocks.POWER_SWITCH.get());
        dropSelf(ModBlocks.EMERGENCY_LEVER.get());
        dropSelf(ModBlocks.HEAVY_LEVER.get());
        dropSelf(ModBlocks.WHITE_COUCH.get());

        dropSelf(ModBlocks.IRON_RAILING.get());
        dropSelf(ModBlocks.COPPER_RAILING.get());
        dropSelf(ModBlocks.GOLD_RAILING.get());

        dropSelf(ModBlocks.PLUSHIE_IDEN.get());
        dropSelf(ModBlocks.PLUSHIE_DOLI.get());
        dropSelf(ModBlocks.PLUSHIE_DINO.get());
        dropSelf(ModBlocks.PLUSHIE_RED.get());
        dropSelf(ModBlocks.PLUSHIE_RAFA.get());

        dropSelf(ModBlocks.BLUE_METAL_BARREL.get());
        dropSelf(ModBlocks.METAL_BARREL.get());

        dropSelf(ModBlocks.SMALL_BOX.get());
        dropSelf(ModBlocks.MEDIUM_BOX.get());
//        dropSelf(ModBlocks.BIG_BOX.get());

        dropSelf(ModBlocks.GUARANA_ANTARTICA.get());
        dropSelf(ModBlocks.TELEPHONE.get());

        dropSelf(ModBlocks.PLASTIC_TABLE.get());

        dropSelf(ModBlocks.CCS_POSTER.get());
        dropSelf(ModBlocks.WATER_POSTER.get());

        dropSelf(ModBlocks.AIR_VENT.get());
        dropSelf(ModBlocks.AIR_DUCT.get());
        dropSelf(ModBlocks.IRON_SHEET_METAL.get());

        dropSelf(ModBlocks.FLUORESCENT_LIGHT.get());

        dropSelf(ModBlocks.SEWING_MACHINE.get());
        dropSelf(ModBlocks.COMPUTER.get());
        dropSelf(ModBlocks.FLOODLIGHT.get());
        dropSelf(ModBlocks.KEYCARD_READER.get());
        dropSelf(ModBlocks.VALVE_SWITCH.get());
        dropSelf(ModBlocks.DOUBLE_IRON_PIPES.get());
        dropSelf(ModBlocks.DRINKING_FOUNTAIN.get());
        dropSelf(ModBlocks.FLOOD_LAMP.get());
        dropSelf(ModBlocks.CUP_DISPENSER.get());
        dropSelf(ModBlocks.SIGN_POST.get());
        dropSelf(ModBlocks.STOP_SIGN.get());
        dropSelf(ModBlocks.DO_NOT_ENTER_SIGN.get());
        dropSelf(ModBlocks.NO_TURN_LEFT_SIGN.get());
        dropSelf(ModBlocks.NO_U_TURN_LEFT_SIGN.get());
        dropSelf(ModBlocks.INTERSECTION_SIGN.get());
        dropSelf(ModBlocks.TRAFFIC_CONE.get());
        dropSelf(ModBlocks.CONCRETE_BARRIER.get());
        dropSelf(ModBlocks.CAUTION_BLOCK.get());
        dropSelf(ModBlocks.CAUTION_BLOCK_STAIRS.get());
        dropSelf(ModBlocks.CAUTION_BLOCK_FENCE.get());
        dropSelf(ModBlocks.CAUTION_BLOCK_WALL.get());
        dropSelf(ModBlocks.BLUE_RUG.get());
        dropSelf(ModBlocks.WALL_LAMP.get());
        dropSelf(ModBlocks.CEILING_LAMP.get());
        dropSelf(ModBlocks.ROOF.get());
        dropSelf(ModBlocks.WHITE_PANEL_WINDOW.get());
        dropSelf(ModBlocks.WHITE_HALF_WINDOW.get());
        dropSelf(ModBlocks.WHITE_LATTICE_WINDOW.get());
        dropSelf(ModBlocks.BLACK_PANEL_WINDOW.get());
        dropSelf(ModBlocks.BLACK_HALF_WINDOW.get());
        dropSelf(ModBlocks.BLACK_LATTICE_WINDOW.get());
        dropSelf(ModBlocks.WHITE_WOOD_RAILING.get());
        dropSelf(ModBlocks.BLACK_WOOD_RAILING.get());
        dropSelf(ModBlocks.GREEN_DIAMOND_WALLPAPER.get());
        dropSelf(ModBlocks.RED_DIAMOND_WALLPAPER.get());
        dropSelf(ModBlocks.YELLOW_ARROW_WALLPAPER.get());
        dropSelf(ModBlocks.CYAN_ARROW_WALLPAPER.get());
        dropSelf(ModBlocks.BLUE_CLOUDS_WALLPAPER.get());
        dropSelf(ModBlocks.BLACK_CLOUDS_WALLPAPER.get());
        dropSelf(ModBlocks.BLACK_STARRY_WALLPAPER.get());
        dropSelf(ModBlocks.FLUORESCENT_LIGHT_BLOCK.get());
        dropSelf(ModBlocks.CARVED_SPRUCE_PLANKS.get());
        dropSelf(ModBlocks.WALL_CLOCK.get());
        dropSelf(ModBlocks.PUZZLE_CARPET.get());
        dropSelf(ModBlocks.PUZZLE_WOOL.get());
        dropSelf(ModBlocks.RED_GOLDEN_CARPET.get());
        dropSelf(ModBlocks.RED_GOLDEN_CARPET_BLOCK.get());
        dropSelf(ModBlocks.GREEN_ARROW_CARPET.get());
        dropSelf(ModBlocks.GREEN_ARROW_CARPET_BLOCK.get());

        dropSelf(ModBlocks.OAK_METAL_SHELF.get());
        dropSelf(ModBlocks.ACACIA_METAL_SHELF.get());
        dropSelf(ModBlocks.BIRCH_METAL_SHELF.get());
        dropSelf(ModBlocks.SPRUCE_METAL_SHELF.get());
        dropSelf(ModBlocks.JUNGLE_METAL_SHELF.get());
        dropSelf(ModBlocks.CHERRY_METAL_SHELF.get());
        dropSelf(ModBlocks.DARK_OAK_METAL_SHELF.get());
        dropSelf(ModBlocks.WARPED_METAL_SHELF.get());
        dropSelf(ModBlocks.CRIMSON_METAL_SHELF.get());
        dropSelf(ModBlocks.BAMBOO_METAL_SHELF.get());
        dropSelf(ModBlocks.MANGROVE_METAL_SHELF.get());
        dropSelf(ModBlocks.METAL_SHELF.get());
        dropSelf(ModBlocks.CALENDAR.get());
        dropSelf(ModBlocks.HANGING_CLOUD.get());
        dropSelf(ModBlocks.HANGING_MOON_LIGHT.get());
        dropSelf(ModBlocks.HANGING_SUN_LIGHT.get());
        dropSelf(ModBlocks.LONG_CONCRETE_VASE.get());
        dropSelf(ModBlocks.RED_CURTAIN.get());
        dropSelf(ModBlocks.WHITE_CUBIC_SHELF.get());

        dropSelf(ModBlocks.WALL_NOTES.get());
        dropSelf(ModBlocks.WALL_CABLE.get());
        dropSelf(ModBlocks.CORE_PILLAR.get());
        dropSelf(ModBlocks.CORE_PILLAR_JUNCTION.get());
        dropSelf(ModBlocks.CORE_TILES.get());
        dropSelf(ModBlocks.CORE_PLATES.get());
        dropSelf(ModBlocks.CORE_DOOR.get());
        dropSelf(ModBlocks.CORE_CONTROL_PANEL.get());
        dropSelf(ModBlocks.CORE_BUTTON_CONTROL_PANEL.get());
        dropSelf(ModBlocks.CORE_LEVER_CONTROL_PANEL.get());
        dropSelf(ModBlocks.CORE_CONTROL_PANEL_SCREEN.get());
        dropSelf(ModBlocks.BATTERY_CELL.get());
        dropSelf(ModBlocks.CAUTION_FLOOR.get());
        dropSelf(ModBlocks.WHEEL.get());
        dropSelf(ModBlocks.WHEEL_TIRE.get());
        dropSelf(ModBlocks.BLAST_LEVER.get());
        dropSelf(ModBlocks.IRON_LATTICE.get());
        dropSelf(ModBlocks.GOLDEN_LATTICE.get());
        dropSelf(ModBlocks.COPPER_LATTICE.get());
        dropSelf(ModBlocks.METAL_TABLE.get());
        dropSelf(ModBlocks.DIAGONAL_SMOOTH_STONE_TILES.get());
        dropSelf(ModBlocks.DIAGONAL_CORE_TILES.get());
        dropSelf(ModBlocks.ENERGIZED_CORE_PLATES.get());
        dropSelf(ModBlocks.DIAGONAL_WHITE_TILES.get());
        dropSelf(ModBlocks.WHITE_SHORT_TILES.get());
        dropSelf(ModBlocks.CRACKED_WHITE_SHORT_TILES.get());
        dropSelf(ModBlocks.WHITE_CLEAR_WINDOW.get());
        dropSelf(ModBlocks.WHITE_CLEAR_WINDOW_BLOCK.get());
        dropSelf(ModBlocks.PLUSHIE_WOW.get());
        dropSelf(ModBlocks.BLACK_BRICKS.get());
        dropSelf(ModBlocks.GRAY_BRICKS.get());
        dropSelf(ModBlocks.LIGHT_GRAY_BRICKS.get());
        dropSelf(ModBlocks.BLUE_BRICKS.get());
        dropSelf(ModBlocks.RED_BRICKS.get());
        dropSelf(ModBlocks.OAK_PLANKS_STRIPS.get());
        dropSelf(ModBlocks.SPRUCE_PLANKS_STRIPS.get());
        dropSelf(ModBlocks.DARK_OAK_PLANKS_STRIPS.get());
        dropSelf(ModBlocks.ACACIA_PLANKS_STRIPS.get());
        dropSelf(ModBlocks.JUNGLE_PLANKS_STRIPS.get());
        dropSelf(ModBlocks.CHERRY_PLANKS_STRIPS.get());
        dropSelf(ModBlocks.MANGROVE_PLANKS_STRIPS.get());
        dropSelf(ModBlocks.BIRCH_PLANKS_STRIPS.get());
        dropSelf(ModBlocks.WARPED_PLANKS_STRIPS.get());
        dropSelf(ModBlocks.CRIMSON_PLANKS_STRIPS.get());

        add(ModBlocks.GUARANA_CAN.get(), this::threeStackableBlockLoot);
        add(ModBlocks.JUICE_BOTTLE.get(), this::fourStackableBlockLoot);

        add(ModBlocks.WOODEN_CRIB.get(), block ->
                createSinglePropConditionTable(block, CribBlock.PART, BedPart.HEAD));

        ModBlocks.PAINTED_PLANKS.values().forEach(block -> dropSelf(block.get()));
        ModBlocks.PAINTED_PLANKS_STRIPS.values().forEach(block -> dropSelf(block.get()));
        ModBlocks.PAINTED_PLANKS_STAIRS.values().forEach(block -> dropSelf(block.get()));
        ModBlocks.FRAMED_PLANKS.values().forEach(block -> dropSelf(block.get()));

        add(ModBlocks.WHITE_TILES_SLAB.get(), block -> createSlabItemTable(ModBlocks.WHITE_TILES_SLAB.get()));
        add(ModBlocks.WHITE_BRICKS_SLAB.get(), block -> createSlabItemTable(ModBlocks.WHITE_BRICKS_SLAB.get()));
        add(ModBlocks.SMOOTH_STONE_TILES_SLAB.get(), block -> createSlabItemTable(ModBlocks.SMOOTH_STONE_TILES_SLAB.get()));
        add(ModBlocks.CAUTION_BLOCK_SLAB.get(), block -> createSlabItemTable(ModBlocks.CAUTION_BLOCK_SLAB.get()));

        dropSelf(ModBlocks.GOLDEN_MEDAL.get());
        dropSelf(ModBlocks.SILVER_MEDAL.get());
        dropSelf(ModBlocks.COPPER_MEDAL.get());

        dropSelf(ModBlocks.RED_METAL_GAS_CYLINDER.get());
        dropSelf(ModBlocks.RED_SLIM_METAL_GAS_CYLINDER.get());
        dropSelf(ModBlocks.RED_WIDE_METAL_GAS_CYLINDER.get());
        dropSelf(ModBlocks.INDUSTRIAL_RED_LAMP.get());

        ModBlocks.PAINTED_PLANKS_SLABS.values().forEach(block -> {
            add(block.get(), block1 -> createSlabItemTable(block.get()));
        });
//
        add(ModBlocks.GRID_METAL_DOOR.get(),
                block -> createDoorTable(ModBlocks.GRID_METAL_DOOR.get()));
        add(ModBlocks.BLACK_OFFICE_DOOR.get(),
                block -> createDoorTable(ModBlocks.BLACK_OFFICE_DOOR.get()));
        add(ModBlocks.YELLOW_METAL_DOOR.get(),
                block -> createDoorTable(ModBlocks.YELLOW_METAL_DOOR.get()));
        add(ModBlocks.WHITE_METAL_DOOR.get(),
                block -> createDoorTable(ModBlocks.WHITE_METAL_DOOR.get()));
        add(ModBlocks.WHITE_WOODEN_PANEL_DOOR.get(),
                block -> createDoorTable(ModBlocks.WHITE_WOODEN_PANEL_DOOR.get()));
        add(ModBlocks.BLACK_WOODEN_PANEL_DOOR.get(),
                block -> createDoorTable(ModBlocks.BLACK_WOODEN_PANEL_DOOR.get()));
        add(ModBlocks.WOODEN_PANEL_DOOR.get(),
                block -> createDoorTable(ModBlocks.WHITE_WOODEN_PANEL_DOOR.get()));

//
//        dropOther(ModBlocks.FLESH_PLANT.get(), ModItems.RAW_FLESH_PLANT.get());

//        add(ModBlocks.BLOODY_MOUTH.get(),
//                block -> createSilkTouchOnlyTable(ModBlocks.BLOODY_MOUTH.get()));
    }
//
    private LootTable.Builder threeStackableBlockLoot(Block block) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add((LootPoolEntryContainer.Builder)this.applyExplosionDecay(block, LootItem.lootTableItem(block).apply(List.of(2, 3, 4), (p_249985_) -> SetItemCountFunction.setCount(ConstantValue.exactly((float)p_249985_)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(SimpleThreeStackableBlock.QUANTITY, p_249985_)))))));
    }

    private LootTable.Builder fourStackableBlockLoot(Block block) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add((LootPoolEntryContainer.Builder)this.applyExplosionDecay(block, LootItem.lootTableItem(block).apply(List.of(2, 3, 4), (p_249985_) -> SetItemCountFunction.setCount(ConstantValue.exactly((float)p_249985_)).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(net.minecraft.advancements.critereon.StatePropertiesPredicate.Builder.properties().hasProperty(SimpleFourStackableBlock.QUANTITY, p_249985_)))))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
