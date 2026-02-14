package net.identidade.iden_decor.datagen;

import net.identidade.iden_decor.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

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
        dropSelf(ModBlocks.TRAFFIC_CONE.get());
        dropSelf(ModBlocks.CONCRETE_BARRIER.get());
        dropSelf(ModBlocks.CAUTION_BLOCK.get());
        dropSelf(ModBlocks.CAUTION_BLOCK_STAIRS.get());

        ModBlocks.PAINTED_PLANKS.values().forEach(block -> dropSelf(block.get()));
        ModBlocks.PAINTED_PLANKS_STAIRS.values().forEach(block -> dropSelf(block.get()));

        add(ModBlocks.WHITE_TILES_SLAB.get(), block -> createSlabItemTable(ModBlocks.WHITE_TILES_SLAB.get()));
        add(ModBlocks.WHITE_BRICKS_SLAB.get(), block -> createSlabItemTable(ModBlocks.WHITE_BRICKS_SLAB.get()));
        add(ModBlocks.SMOOTH_STONE_TILES_SLAB.get(), block -> createSlabItemTable(ModBlocks.SMOOTH_STONE_TILES_SLAB.get()));
        add(ModBlocks.CAUTION_BLOCK_SLAB.get(), block -> createSlabItemTable(ModBlocks.CAUTION_BLOCK_SLAB.get()));

        ModBlocks.PAINTED_PLANKS_SLABS.values().forEach(block -> {
            add(block.get(), block1 -> createSlabItemTable(block.get()));
        });
//
        add(ModBlocks.GRID_METAL_DOOR.get(),
                block -> createDoorTable(ModBlocks.GRID_METAL_DOOR.get()));
        add(ModBlocks.YELLOW_METAL_DOOR.get(),
                block -> createDoorTable(ModBlocks.YELLOW_METAL_DOOR.get()));
        add(ModBlocks.WHITE_METAL_DOOR.get(),
                block -> createDoorTable(ModBlocks.WHITE_METAL_DOOR.get()));

//
//        dropOther(ModBlocks.FLESH_PLANT.get(), ModItems.RAW_FLESH_PLANT.get());

//        add(ModBlocks.BLOODY_MOUTH.get(),
//                block -> createSilkTouchOnlyTable(ModBlocks.BLOODY_MOUTH.get()));
    }
//
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
