package net.identidade.iden_decor.datagen;

import net.identidade.iden_decor.block.ModBlocks;
import net.identidade.iden_decor.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.awt.*;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        // Painted Planks
        ModBlocks.PAINTED_PLANKS.forEach((color, block) -> ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block.get(), 8)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .define('B', ItemTags.PLANKS)
                .define('A', DyeItem.byColor(color))
                .unlockedBy("has_planks", has(ItemTags.PLANKS)).save(recipeOutput));

        ModBlocks.PAINTED_PLANKS_STAIRS.forEach((color, block) ->
                stairBuilder(block.get(), Ingredient.of(ModBlocks.PAINTED_PLANKS.get(color).get())).group("painted_"+color+"_planks")
                        .unlockedBy("has_painted_"+color+"_planks", has(ModBlocks.PAINTED_PLANKS.get(color).get().asItem())).save(recipeOutput));

        ModBlocks.PAINTED_PLANKS_SLABS.forEach((color, block) ->
                slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, block.get(), ModBlocks.PAINTED_PLANKS.get(color).get()));

        // Smooth Stone Tiles
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_TILES.get(), 4)
                .pattern("AA")
                .pattern("AA")
                .define('A', ModBlocks.SMOOTH_STONE_BRICKS)
                .unlockedBy("has_smooth_stone", has(Blocks.SMOOTH_STONE.asItem())).save(recipeOutput);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_TILES, ModBlocks.SMOOTH_STONE_BRICKS);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_TILES_STAIRS, ModBlocks.SMOOTH_STONE_TILES);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_TILES_SLAB, ModBlocks.SMOOTH_STONE_TILES, 2);

        stairBuilder(ModBlocks.SMOOTH_STONE_TILES_STAIRS.get(), Ingredient.of(ModBlocks.SMOOTH_STONE_TILES)).group("stone_tiles")
                .unlockedBy("has_stone_tiles", has(ModBlocks.SMOOTH_STONE_TILES.asItem())).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_TILES_SLAB.get(), ModBlocks.SMOOTH_STONE_TILES.get());

        // Smooth Stone Bricks
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_BRICKS.get(), 4)
                .pattern("AA")
                .pattern("AA")
                .define('A', Blocks.SMOOTH_STONE)
                .unlockedBy("has_smooth_stone", has(Blocks.SMOOTH_STONE.asItem())).save(recipeOutput);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_BRICKS, Blocks.SMOOTH_STONE);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_TILES_STAIRS, Blocks.SMOOTH_STONE);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SMOOTH_STONE_TILES_SLAB, Blocks.SMOOTH_STONE, 2);

        // White Tiles
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_TILES.get(), 8)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .define('B', ModBlocks.SMOOTH_STONE_TILES.get())
                .define('A', Items.WHITE_DYE)
                .unlockedBy("has_stone_tiles", has(ModBlocks.SMOOTH_STONE_TILES.asItem())).save(recipeOutput);

        stairBuilder(ModBlocks.WHITE_TILES_STAIRS.get(), Ingredient.of(ModBlocks.WHITE_TILES)).group("white_tiles")
                .unlockedBy("has_stone_tiles", has(ModBlocks.WHITE_TILES.asItem())).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_TILES_SLAB.get(), ModBlocks.WHITE_TILES.get());

        // White Bricks

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_BRICKS.get(), 8)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .define('B', Blocks.BRICKS)
                .define('A', Items.WHITE_DYE)
                .unlockedBy("has_bricks", has(Blocks.BRICKS.asItem())).save(recipeOutput);

        stairBuilder(ModBlocks.WHITE_BRICKS_STAIRS.get(), Ingredient.of(ModBlocks.WHITE_BRICKS)).group("white_bricks")
                .unlockedBy("has_white_bricks", has(ModBlocks.WHITE_BRICKS.asItem())).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_BRICKS_SLAB.get(), ModBlocks.WHITE_BRICKS.get());

        // Iron Block
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_SHEET_METAL.get(), Blocks.IRON_BLOCK, 32);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CAUTION_BLOCK.get(), Blocks.IRON_BLOCK, 32);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CAUTION_BLOCK_STAIRS.get(), Blocks.IRON_BLOCK, 32);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CAUTION_BLOCK_STAIRS.get(), ModBlocks.CAUTION_BLOCK.get());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CAUTION_BLOCK_SLAB.get(), Blocks.IRON_BLOCK, 64);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CAUTION_BLOCK_SLAB.get(), ModBlocks.CAUTION_BLOCK.get(), 2);
        stairBuilder(ModBlocks.CAUTION_BLOCK_STAIRS.get(), Ingredient.of(ModBlocks.CAUTION_BLOCK)).group("caution_blocks")
                        .unlockedBy("has_caution_block", has(ModBlocks.CAUTION_BLOCK.get())).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CAUTION_BLOCK_SLAB.get(), ModBlocks.CAUTION_BLOCK.get());

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.AIR_DUCT.get(), Blocks.IRON_BLOCK, 32);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.AIR_DUCT.get(), ModBlocks.IRON_SHEET_METAL.get());

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_GRATE, ModBlocks.IRON_SHEET_METAL, 4);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_GRATE.get(), 4)
                .pattern(" A ")
                .pattern("A A")
                .pattern(" A ")
                .define('A', ModBlocks.IRON_SHEET_METAL.get())
                .unlockedBy("has_iron_sheetmetal", has(ModBlocks.IRON_SHEET_METAL.asItem()))
                .save(recipeOutput);

        trapdoorBuilder(ModBlocks.AIR_VENT, Ingredient.of(ModBlocks.AIR_DUCT)).group("duct")
                .unlockedBy("has_duct", has(ModBlocks.AIR_DUCT.asItem())).save(recipeOutput);

        // Ingots
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_RAILING.get(), Items.IRON_INGOT, 4);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOUBLE_IRON_PIPES.get(), Items.IRON_INGOT, 2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SIGN_POST.get(), Items.IRON_INGOT, 2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.STOP_SIGN.get(), Items.IRON_INGOT);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.COPPER_RAILING.get(), Items.COPPER_INGOT, 4);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLD_RAILING.get(), Items.GOLD_INGOT, 4);

        // Plushies
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, ModBlocks.PLUSHIE_IDEN.get(), Blocks.WHITE_WOOL);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, ModBlocks.PLUSHIE_RAFA.get(), Blocks.WHITE_WOOL);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, ModBlocks.PLUSHIE_RED.get(), Blocks.WHITE_WOOL);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, ModBlocks.PLUSHIE_DINO.get(), Blocks.WHITE_WOOL);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, ModBlocks.PLUSHIE_DOLI.get(), Blocks.WHITE_WOOL);

        // Fluorescent Light
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.FLUORESCENT_LIGHT.get())
                .pattern("AAA")
                .pattern("BCB")
                .define('A', Items.IRON_INGOT)
                .define('B', Blocks.GLASS)
                .define('C', Blocks.REDSTONE_LAMP)
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(recipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.FLOODLIGHT.asItem())
                .pattern("AAA")
                .pattern("BCB")
                .pattern("BBB")
                .define('A', Items.IRON_INGOT)
                .define('B', Blocks.YELLOW_CONCRETE.asItem())
                .define('C', Blocks.REDSTONE_LAMP.asItem())
                .unlockedBy("has_redstone_lamp", has(Blocks.REDSTONE_LAMP.asItem()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.FLOOD_LAMP.asItem())
                .pattern("   ")
                .pattern("ABA")
                .pattern("   ")
                .define('A', Blocks.YELLOW_CONCRETE.asItem())
                .define('B', Blocks.REDSTONE_LAMP.asItem())
                .unlockedBy("has_redstone_lamp", has(Blocks.REDSTONE_LAMP.asItem()))
                .save(recipeOutput);

        // Water Dispensers
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.LIGHT_WATER_DISPENSER.get())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" C ")
                .define('A', Blocks.GLASS.asItem())
                .define('B', Items.WATER_BUCKET)
                .define('C', Items.IRON_INGOT)
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.DARK_WATER_DISPENSER.get())
                .requires(ModBlocks.LIGHT_WATER_DISPENSER.asItem())
                .requires(Items.BLACK_DYE)
                .unlockedBy("has_dispenser", has(ModBlocks.LIGHT_WATER_DISPENSER.asItem()))
                .save(recipeOutput);

        // Posters
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.CCS_POSTER.get())
                .requires(Items.BLACK_DYE)
                .requires(Items.PAPER)
                .unlockedBy("has_paper", has(Items.PAPER))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.WATER_POSTER.get())
                .requires(Items.LIGHT_BLUE_DYE)
                .requires(Items.PAPER)
                .unlockedBy("has_paper", has(Items.PAPER))
                .save(recipeOutput);

        // Plastic
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.PLASTIC_TABLE.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern("A A")
                .define('A', Items.SUGAR_CANE)
                .unlockedBy("has_sugar_cane", has(Items.SUGAR_CANE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.PLASTIC_CUP.get(), 4)
                .pattern("A A")
                .pattern(" A ")
                .define('A', Items.SUGAR_CANE)
                .unlockedBy("has_sugar_cane", has(Items.SUGAR_CANE))
                .save(recipeOutput);

        // Cardboard box
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.SMALL_BOX.get())
                .pattern("AA ")
                .pattern("AA ")
                .define('A', Items.PAPER)
                .unlockedBy("has_paper", has(Items.PAPER))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.MEDIUM_BOX.get())
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.PAPER)
                .define('B', ModBlocks.SMALL_BOX.asItem())
                .unlockedBy("has_paper", has(Items.PAPER))
                .save(recipeOutput);

        // Buttons / Levers
        stonecutterResultFromBase(recipeOutput, RecipeCategory.REDSTONE, ModBlocks.HEAVY_BUTTON.get(), Blocks.STONE_BUTTON);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.REDSTONE, ModBlocks.GATE_BUTTON.get(), Blocks.STONE_BUTTON);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.REDSTONE, ModBlocks.HEAVY_LEVER.get(), Blocks.LEVER);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.REDSTONE, ModBlocks.EMERGENCY_LEVER.get(), Blocks.LEVER);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.REDSTONE, ModBlocks.POWER_SWITCH.get(), Blocks.LEVER);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.REDSTONE, ModBlocks.LIGHT_SWITCH.get(), Blocks.LEVER);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.REDSTONE, ModBlocks.VALVE_SWITCH.get(), Blocks.LEVER);

        // Telephone
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModItems.TELEPHONE_ITEM.get())
                .pattern("AAA")
                .pattern("B B")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.RED_DYE)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.TELEPHONE.get())
                .pattern("   ")
                .pattern("ABA")
                .pattern("BBB")
                .define('A', Items.REDSTONE)
                .define('B', Items.IRON_INGOT)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        // Barrels

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.METAL_BARREL.get())
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.IRON_INGOT)
                .define('B', Blocks.BARREL.asItem())
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.BLUE_METAL_BARREL.get())
                .requires(ModBlocks.METAL_BARREL.asItem())
                .requires(Items.BLUE_DYE)
                .unlockedBy("has_metal_barrel", has(ModBlocks.METAL_BARREL.asItem()))
                .save(recipeOutput);

        // Metal Doors
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS,ModBlocks.GRID_METAL_DOOR.asItem(), Items.IRON_DOOR);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS,ModBlocks.YELLOW_METAL_DOOR.asItem(), Items.IRON_DOOR);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS,ModBlocks.WHITE_METAL_DOOR.asItem(), Items.IRON_DOOR);

        // Others
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.DRINKING_FOUNTAIN.asItem())
                .pattern(" A ")
                .pattern("BBB")
                .pattern(" B ")
                .define('B', Items.IRON_INGOT)
                .define('A', Items.POTION)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.TRAFFIC_CONE.asItem(), 5)
                .pattern(" A ")
                .pattern(" B ")
                .pattern("AAA")
                .define('A', Blocks.ORANGE_CONCRETE.asItem())
                .define('B', Blocks.WHITE_CONCRETE.asItem())
                .unlockedBy("has_white_concrete", has(Blocks.WHITE_CONCRETE.asItem()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.CONCRETE_BARRIER.asItem(), 6)
                .pattern("   ")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', Blocks.SMOOTH_STONE.asItem())
                .unlockedBy("has_smooth_stone", has(Blocks.SMOOTH_STONE.asItem()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.CUP_DISPENSER.asItem())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" A ")
                .define('A', Items.IRON_INGOT)
                .define('B', ModItems.PLASTIC_CUP)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.GUARANA_ANTARTICA.asItem())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" C ")
                .define('A', Items.SUGAR)
                .define('B', Items.SUGAR_CANE)
                .define('C', Items.GLASS_BOTTLE)
                .unlockedBy("has_glass_bottle", has(Items.GLASS_BOTTLE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FLOPPY_DISK)
                .pattern(" AB")
                .pattern("BCB")
                .pattern("BAB")
                .define('A', Items.BLACK_DYE)
                .define('B', Items.IRON_INGOT)
                .define('C', Items.REDSTONE)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.COMPUTER.asItem())
                .pattern("ABA")
                .pattern("CDC")
                .pattern("ABA")
                .define('A', Items.IRON_INGOT)
                .define('B', ModBlocks.IRON_SHEET_METAL.asItem())
                .define('C', Items.REDSTONE)
                .define('D', Blocks.GLASS.asItem())
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.KEYCARD)
                .pattern("   ")
                .pattern("ABA")
                .pattern("CCC")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.BLACK_DYE)
                .define('C', Items.COPPER_INGOT)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.KEYCARD_READER.asItem())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.IRON_INGOT)
                .define('B', ModItems.KEYCARD)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);


//
//        fenceBuilder(ModBlocks.BLOODY_FENCE.get(), Ingredient.of(ModBlocks.BLOODY_PLANKS)).group("bloody_planks")
//                .unlockedBy("has_bloody_planks", has(ModBlocks.BLOODY_PLANKS)).save(recipeOutput);
//        fenceGateBuilder(ModBlocks.BLOODY_FENCE_GATE.get(), Ingredient.of(ModBlocks.BLOODY_PLANKS)).group("bloody_planks")
//                .unlockedBy("has_bloody_planks", has(ModBlocks.BLOODY_PLANKS)).save(recipeOutput);
//
//        buttonBuilder(ModBlocks.BLOODY_BUTTON.get(), Ingredient.of(ModBlocks.BLOODY_PLANKS)).group("bloody_planks")
//                .unlockedBy("has_bloody_planks", has(ModBlocks.BLOODY_PLANKS)).save(recipeOutput);
//        pressurePlate(recipeOutput, ModBlocks.BLOODY_PRESSURE_PLATE.get(), ModBlocks.BLOODY_PLANKS.get());
//
//        doorBuilder(ModBlocks.BLOODY_DOOR.get(), Ingredient.of(ModBlocks.BLOODY_PLANKS)).group("bloody_planks")
//                .unlockedBy("has_bloody_planks", has(ModBlocks.BLOODY_PLANKS)).save(recipeOutput);
//        trapdoorBuilder(ModBlocks.BLOODY_TRAPDOOR.get(), Ingredient.of(ModBlocks.BLOODY_PLANKS)).group("bloody_planks")
//                .unlockedBy("has_bloody_planks", has(ModBlocks.BLOODY_PLANKS)).save(recipeOutput);
//
//        woodFromLogs(recipeOutput, ModBlocks.BLOODY_WOOD.get(), ModBlocks.BLOODY_LOG.get());
//        woodFromLogs(recipeOutput, ModBlocks.STRIPPED_BLOODY_WOOD.get(), ModBlocks.STRIPPED_BLOODY_LOG.get());
    }
}
