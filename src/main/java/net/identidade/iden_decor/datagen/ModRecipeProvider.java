package net.identidade.iden_decor.datagen;

import net.identidade.iden_decor.block.ModBlocks;
import net.identidade.iden_decor.block.properties.ColorProperty;
import net.identidade.iden_decor.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

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

        // Stone Tiles
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STONE_TILES.get(), 4)
                .pattern("AA")
                .pattern("AA")
                .define('A', Blocks.SMOOTH_STONE)
                .unlockedBy("has_stone", has(Blocks.SMOOTH_STONE.asItem())).save(recipeOutput);

        stairBuilder(ModBlocks.STONE_TILES_STAIRS.get(), Ingredient.of(ModBlocks.STONE_TILES)).group("stone_tiles")
                .unlockedBy("has_stone_tiles", has(ModBlocks.STONE_TILES.asItem())).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.STONE_TILES_SLAB.get(), ModBlocks.STONE_TILES.get());

        // White Tiles
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_TILES.get(), 8)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .define('B', ModBlocks.STONE_TILES.get())
                .define('A', Items.WHITE_DYE)
                .unlockedBy("has_stone_tiles", has(ModBlocks.STONE_TILES.asItem())).save(recipeOutput);

        stairBuilder(ModBlocks.WHITE_TILES_STAIRS.get(), Ingredient.of(ModBlocks.WHITE_TILES)).group("white_tiles")
                .unlockedBy("has_stone_tiles", has(ModBlocks.WHITE_TILES.asItem())).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_TILES_SLAB.get(), ModBlocks.WHITE_TILES.get());

        // Iron Block
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_SHEET_METAL.get(), Blocks.IRON_BLOCK, 32);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_SHEET_METAL.get(), ModBlocks.AIR_DUCT.get());

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

        // Buttons / Levers
        stonecutterResultFromBase(recipeOutput, RecipeCategory.REDSTONE, ModBlocks.HEAVY_BUTTON.get(), Blocks.STONE_BUTTON);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.REDSTONE, ModBlocks.GATE_BUTTON.get(), Blocks.STONE_BUTTON);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.REDSTONE, ModBlocks.HEAVY_LEVER.get(), Blocks.LEVER);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.REDSTONE, ModBlocks.EMERGENCY_LEVER.get(), Blocks.LEVER);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.REDSTONE, ModBlocks.POWER_SWITCH.get(), Blocks.LEVER);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.REDSTONE, ModBlocks.LIGHT_SWITCH.get(), Blocks.LEVER);

        // Telephone
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModItems.TELEPHONE_ITEM.get())
                .pattern("AAA")
                .pattern("B B")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.RED_DYE)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.TELEPHONE.get())
                .pattern(" A ")
                .pattern(" B ")
                .pattern("BBB")
                .define('A', ModItems.TELEPHONE_ITEM)
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

//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BLOODY_PLANKS.get())
//                .pattern("BBB")
//                .pattern("BBB")
//                .pattern("BBB")
//                .define('B', ModItems.BLOODY_SAP_BOTTLE.get())
//                .unlockedBy("has_bloody_sap", has(ModItems.BLOODY_SAP_BOTTLE)).save(recipeOutput);
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.BLOODY_PLANKS.get(), 4)
//                .requires(ModTags.Items.BLOODY_LOGS)
//                .unlockedBy("has_bloody_log", has(ModTags.Items.BLOODY_LOGS)).save(recipeOutput, "crimsomania:bloody_planks_from_log");
//
//        stairBuilder(ModBlocks.BLOODY_STAIRS.get(), Ingredient.of(ModBlocks.BLOODY_PLANKS)).group("bloody_planks")
//                .unlockedBy("has_bloody_planks", has(ModBlocks.BLOODY_PLANKS)).save(recipeOutput);
//        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLOODY_SLAB.get(), ModBlocks.BLOODY_PLANKS.get());
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
