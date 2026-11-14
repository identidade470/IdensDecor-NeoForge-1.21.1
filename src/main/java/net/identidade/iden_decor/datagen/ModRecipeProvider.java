package net.identidade.iden_decor.datagen;

import net.identidade.iden_decor.block.ModBlocks;
import net.identidade.iden_decor.item.ModItems;
import net.identidade.iden_decor.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
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
        ModBlocks.FRAMED_PLANKS.forEach((color, block) -> ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block.get(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlocks.PAINTED_PLANKS.get(color).get())
                .define('B', Items.STICK)
                .unlockedBy("has_planks", has(ItemTags.PLANKS)).save(recipeOutput));

        ModBlocks.PAINTED_PLANKS.forEach((color, block) -> ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block.get(), 8)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .define('B', ItemTags.PLANKS)
                .define('A', DyeItem.byColor(color))
                .unlockedBy("has_planks", has(ItemTags.PLANKS)).save(recipeOutput));

        ModBlocks.PAINTED_PLANKS_STRIPS.forEach((color, block) -> ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block.get(), 8)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .define('B', ModTags.Items.PLANKS_STRIPS)
                .define('A', DyeItem.byColor(color))
                .unlockedBy("has_planks_strips", has(ModTags.Items.PLANKS_STRIPS)).save(recipeOutput));

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
        
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIAGONAL_SMOOTH_STONE_TILES, Blocks.SMOOTH_STONE);

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

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIAGONAL_WHITE_TILES, ModBlocks.WHITE_TILES.get());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_SHORT_TILES, ModBlocks.WHITE_TILES.get());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRACKED_WHITE_SHORT_TILES, ModBlocks.WHITE_TILES.get());

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


        // BLACK BRICKS
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACK_BRICKS.get(), 8)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .define('B', Blocks.BRICKS)
                .define('A', Items.BLACK_DYE)
                .unlockedBy("has_bricks", has(Blocks.BRICKS.asItem())).save(recipeOutput);

        stairBuilder(ModBlocks.BLACK_BRICK_STAIRS.get(), Ingredient.of(ModBlocks.BLACK_BRICKS)).group("black_bricks")
                .unlockedBy("has_black_bricks", has(ModBlocks.BLACK_BRICKS.asItem())).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACK_BRICK_SLAB.get(), ModBlocks.BLACK_BRICKS.get());

        // GRAY BRICKS
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GRAY_BRICKS.get(), 8)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .define('B', Blocks.BRICKS)
                .define('A', Items.GRAY_DYE)
                .unlockedBy("has_bricks", has(Blocks.BRICKS.asItem())).save(recipeOutput);

        stairBuilder(ModBlocks.GRAY_BRICK_STAIRS.get(), Ingredient.of(ModBlocks.GRAY_BRICKS)).group("gray_bricks")
                .unlockedBy("has_gray_bricks", has(ModBlocks.GRAY_BRICKS.asItem())).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GRAY_BRICK_SLAB.get(), ModBlocks.GRAY_BRICKS.get());

        // LIGHT GRAY BRICKS
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIGHT_GRAY_BRICKS.get(), 8)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .define('B', Blocks.BRICKS)
                .define('A', Items.LIGHT_GRAY_DYE)
                .unlockedBy("has_bricks", has(Blocks.BRICKS.asItem())).save(recipeOutput);

        stairBuilder(ModBlocks.LIGHT_GRAY_BRICK_STAIRS.get(), Ingredient.of(ModBlocks.LIGHT_GRAY_BRICKS)).group("light_gray_bricks")
                .unlockedBy("has_light_gray_bricks", has(ModBlocks.LIGHT_GRAY_BRICKS.asItem())).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIGHT_GRAY_BRICK_SLAB.get(), ModBlocks.LIGHT_GRAY_BRICKS.get());

        // BLUE BRICKS
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_BRICKS.get(), 8)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .define('B', Blocks.BRICKS)
                .define('A', Items.BLUE_DYE)
                .unlockedBy("has_bricks", has(Blocks.BRICKS.asItem())).save(recipeOutput);

        stairBuilder(ModBlocks.BLUE_BRICK_STAIRS.get(), Ingredient.of(ModBlocks.BLUE_BRICKS)).group("blue_bricks")
                .unlockedBy("has_blue_bricks", has(ModBlocks.BLUE_BRICKS.asItem())).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_BRICK_SLAB.get(), ModBlocks.BLUE_BRICKS.get());

        //RED BRICKS
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_BRICKS.get(), 8)
                .pattern("BBB")
                .pattern("BAB")
                .pattern("BBB")
                .define('B', Blocks.BRICKS)
                .define('A', Items.WHITE_DYE)
                .unlockedBy("has_bricks", has(Blocks.BRICKS.asItem())).save(recipeOutput);

        stairBuilder(ModBlocks.RED_BRICK_STAIRS.get(), Ingredient.of(ModBlocks.RED_BRICKS)).group("red_bricks")
                .unlockedBy("has_red_bricks", has(ModBlocks.RED_BRICKS.asItem())).save(recipeOutput);

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_BRICK_SLAB.get(), ModBlocks.RED_BRICKS.get());
        

        // Iron Block
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_SHEET_METAL.get(), Blocks.IRON_BLOCK, 32);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CAUTION_BLOCK.get(), Blocks.IRON_BLOCK, 32);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CAUTION_BLOCK_STAIRS.get(), Blocks.IRON_BLOCK, 32);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CAUTION_BLOCK_STAIRS.get(), ModBlocks.CAUTION_BLOCK.get());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CAUTION_BLOCK_SLAB.get(), Blocks.IRON_BLOCK, 64);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CAUTION_BLOCK_SLAB.get(), ModBlocks.CAUTION_BLOCK.get(), 2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CAUTION_FLOOR.get(), ModBlocks.CAUTION_BLOCK.get());
        stairBuilder(ModBlocks.CAUTION_BLOCK_STAIRS.get(), Ingredient.of(ModBlocks.CAUTION_BLOCK)).group("caution_blocks")
                        .unlockedBy("has_caution_block", has(ModBlocks.CAUTION_BLOCK.get())).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CAUTION_BLOCK_SLAB.get(), ModBlocks.CAUTION_BLOCK.get());
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CAUTION_BLOCK_WALL.get(), ModBlocks.CAUTION_BLOCK.get());
                fenceBuilder(ModBlocks.CAUTION_BLOCK_FENCE.get(), Ingredient.of(ModBlocks.CAUTION_BLOCK)).group("caution_blocks")
                .unlockedBy("has_bloody_planks", has(ModBlocks.CAUTION_BLOCK)).save(recipeOutput);

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
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.INTERSECTION_SIGN.get(), Items.IRON_INGOT);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.COPPER_RAILING.get(), Items.COPPER_INGOT, 4);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLD_RAILING.get(), Items.GOLD_INGOT, 4);

        // Plushies
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, ModBlocks.PLUSHIE_IDEN.get(), Blocks.WHITE_WOOL);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, ModBlocks.PLUSHIE_RAFA.get(), Blocks.WHITE_WOOL);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, ModBlocks.PLUSHIE_RED.get(), Blocks.WHITE_WOOL);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, ModBlocks.PLUSHIE_DINO.get(), Blocks.WHITE_WOOL);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, ModBlocks.PLUSHIE_DOLI.get(), Blocks.WHITE_WOOL);
        
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, ModBlocks.PLUSHIE_WOW.get(), Blocks.WHITE_WOOL);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, ModBlocks.PLUSHIE_WD.get(), Blocks.WHITE_WOOL);


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
        stonecutterResultFromBase(recipeOutput, RecipeCategory.REDSTONE, ModBlocks.BLAST_LEVER.get(), Blocks.LEVER);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.REDSTONE, ModBlocks.ELECTRICAL_PANEL.get(), Blocks.LEVER);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.REDSTONE, ModBlocks.KEYBOARD.get(), Blocks.LEVER);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, ModBlocks.HANGING_CLOUD.get(), Blocks.CHAIN);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, ModBlocks.HANGING_MOON_LIGHT.get(), Blocks.CHAIN);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS, ModBlocks.HANGING_SUN_LIGHT.get(), Blocks.CHAIN);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.REDSTONE, ModBlocks.WOODEN_PANEL_DOOR.get(), Blocks.SPRUCE_DOOR);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACK_CLOUDS_WALLPAPER, Blocks.STRIPPED_SPRUCE_LOG, 4);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLACK_STARRY_WALLPAPER, Blocks.STRIPPED_SPRUCE_LOG, 4);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GREEN_DIAMOND_WALLPAPER, Blocks.STRIPPED_SPRUCE_LOG, 4);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_CLOUDS_WALLPAPER, Blocks.STRIPPED_SPRUCE_LOG, 4);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_DIAMOND_WALLPAPER, Blocks.STRIPPED_SPRUCE_LOG, 4);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.YELLOW_ARROW_WALLPAPER, Blocks.STRIPPED_SPRUCE_LOG, 4);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CYAN_ARROW_WALLPAPER, Blocks.STRIPPED_SPRUCE_LOG, 4);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.REDSTONE, ModBlocks.WALL_LAMP, Blocks.REDSTONE_LAMP);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, ModBlocks.WHITE_WOODEN_PANEL_DOOR.get())
                .requires(Tags.Items.DYES_WHITE)
                .requires(ModBlocks.WOODEN_PANEL_DOOR.asItem())
                .unlockedBy("has_door", has(ItemTags.DOORS))
                .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, ModBlocks.BLACK_WOODEN_PANEL_DOOR.get())
                .requires(Tags.Items.DYES_BLACK)
                .requires(ModBlocks.WOODEN_PANEL_DOOR.asItem())
                .unlockedBy("has_door", has(ItemTags.DOORS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.FLUORESCENT_LIGHT_BLOCK.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.IRON_NUGGET)
                .define('B', Blocks.REDSTONE_LAMP.asItem())
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.CALENDAR.get())
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', Items.PAPER)
                .define('B', Items.CLOCK)
                .unlockedBy("has_paper", has(Items.PAPER))
                .save(recipeOutput);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CARVED_SPRUCE_PLANKS.get(), Blocks.SPRUCE_PLANKS);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.LONG_CONCRETE_VASE.get(), 5)
                .pattern("A A")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Blocks.WHITE_CONCRETE)
                .define('B', Blocks.DIRT)
                .unlockedBy("has_concrete", has(Blocks.WHITE_CONCRETE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PUZZLE_WOOL.get(), 4)
                .pattern("AB")
                .pattern("CD")
                .define('A', Blocks.ORANGE_WOOL)
                .define('B', Blocks.LIGHT_BLUE_WOOL)
                .define('C', Blocks.YELLOW_WOOL)
                .define('D', Blocks.RED_WOOL)
                .unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PUZZLE_CARPET.get(), 3)
                .pattern("AA")
                .define('A', ModBlocks.PUZZLE_WOOL.get())
                .unlockedBy("has_wool", has(ItemTags.WOOL))
                .save(recipeOutput);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GREEN_ARROW_CARPET_BLOCK.get(), Blocks.GREEN_WOOL, 1);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_GOLDEN_CARPET_BLOCK.get(), Blocks.RED_WOOL, 1);
        carpet(recipeOutput, ModBlocks.GREEN_ARROW_CARPET.get(), ModBlocks.GREEN_ARROW_CARPET_BLOCK.get());
        carpet(recipeOutput, ModBlocks.RED_GOLDEN_CARPET.get(), ModBlocks.RED_GOLDEN_CARPET_BLOCK.get());



        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.WHITE_HALF_WINDOW.get(), 4)
                .pattern("AAA")
                .pattern("BBB")
                .pattern("AAA")
                .define('A', ModBlocks.PAINTED_PLANKS.get(DyeColor.WHITE).get())
                .define('B', Items.STICK)
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BLACK_HALF_WINDOW.get(), 4)
                .pattern("AAA")
                .pattern("BBB")
                .pattern("AAA")
                .define('A', ModBlocks.PAINTED_PLANKS.get(DyeColor.BLACK).get())
                .define('B', Items.STICK)
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.WHITE_PANEL_WINDOW.get(), 4)
                .pattern("ABA")
                .pattern("BBB")
                .pattern("ABA")
                .define('A', ModBlocks.PAINTED_PLANKS.get(DyeColor.WHITE).get())
                .define('B', Items.STICK)
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BLACK_PANEL_WINDOW.get(), 4)
                .pattern("ABA")
                .pattern("BBB")
                .pattern("ABA")
                .define('A', ModBlocks.PAINTED_PLANKS.get(DyeColor.BLACK).get())
                .define('B', Items.STICK)
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.WHITE_LATTICE_WINDOW.get(), 4)
                .pattern("ABA")
                .pattern("B B")
                .pattern("ABA")
                .define('A', ModBlocks.PAINTED_PLANKS.get(DyeColor.WHITE).get())
                .define('B', Items.STICK)
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BLACK_LATTICE_WINDOW.get(), 4)
                .pattern("ABA")
                .pattern("B B")
                .pattern("ABA")
                .define('A', ModBlocks.PAINTED_PLANKS.get(DyeColor.BLACK).get())
                .define('B', Items.STICK)
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.WHITE_WOOD_RAILING.get(), 3)
                .pattern("   ")
                .pattern("ABA")
                .pattern("ABA")
                .define('A', ModBlocks.PAINTED_PLANKS.get(DyeColor.WHITE).get())
                .define('B', Items.STICK)
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BLACK_WOOD_RAILING.get(), 3)
                .pattern("   ")
                .pattern("ABA")
                .pattern("ABA")
                .define('A', ModBlocks.PAINTED_PLANKS.get(DyeColor.BLACK).get())
                .define('B', Items.STICK)
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);

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
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS,ModBlocks.BLACK_OFFICE_DOOR.asItem(), Items.IRON_DOOR);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.DECORATIONS,ModBlocks.GREEN_OFFICE_DOOR.asItem(), Items.IRON_DOOR);

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

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.GUARANA_CAN.asItem())
                .pattern(" A ")
                .pattern(" B ")
                .pattern(" A ")
                .define('B', Items.SUGAR)
                .define('A', Items.IRON_NUGGET)
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

        // Metal shelves
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.OAK_METAL_SHELF.asItem())
                .pattern("A A")
                .pattern("ABA")
                .pattern("A A")
                .define('A', Items.IRON_NUGGET)
                .define('B', Blocks.OAK_PLANKS.asItem())
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.DARK_OAK_METAL_SHELF.asItem())
                .pattern("A A")
                .pattern("ABA")
                .pattern("A A")
                .define('A', Items.IRON_NUGGET)
                .define('B', Blocks.DARK_OAK_PLANKS.asItem())
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BAMBOO_METAL_SHELF.asItem())
                .pattern("A A")
                .pattern("ABA")
                .pattern("A A")
                .define('A', Items.IRON_NUGGET)
                .define('B', Blocks.BAMBOO_PLANKS.asItem())
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.CHERRY_METAL_SHELF.asItem())
                .pattern("A A")
                .pattern("ABA")
                .pattern("A A")
                .define('A', Items.IRON_NUGGET)
                .define('B', Blocks.CHERRY_PLANKS.asItem())
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.WARPED_METAL_SHELF.asItem())
                .pattern("A A")
                .pattern("ABA")
                .pattern("A A")
                .define('A', Items.IRON_NUGGET)
                .define('B', Blocks.WARPED_PLANKS.asItem())
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.CRIMSON_METAL_SHELF.asItem())
                .pattern("A A")
                .pattern("ABA")
                .pattern("A A")
                .define('A', Items.IRON_NUGGET)
                .define('B', Blocks.CRIMSON_PLANKS.asItem())
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.ACACIA_METAL_SHELF.asItem())
                .pattern("A A")
                .pattern("ABA")
                .pattern("A A")
                .define('A', Items.IRON_NUGGET)
                .define('B', Blocks.ACACIA_PLANKS.asItem())
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.JUNGLE_METAL_SHELF.asItem())
                .pattern("A A")
                .pattern("ABA")
                .pattern("A A")
                .define('A', Items.IRON_NUGGET)
                .define('B', Blocks.JUNGLE_PLANKS.asItem())
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.MANGROVE_METAL_SHELF.asItem())
                .pattern("A A")
                .pattern("ABA")
                .pattern("A A")
                .define('A', Items.IRON_NUGGET)
                .define('B', Blocks.MANGROVE_PLANKS.asItem())
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.SPRUCE_METAL_SHELF.asItem())
                .pattern("A A")
                .pattern("ABA")
                .pattern("A A")
                .define('A', Items.IRON_NUGGET)
                .define('B', Blocks.SPRUCE_PLANKS.asItem())
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.METAL_SHELF.asItem())
                .pattern("A A")
                .pattern("ABA")
                .pattern("A A")
                .define('A', Items.IRON_NUGGET)
                .define('B', Items.IRON_INGOT)
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.BIRCH_METAL_SHELF.asItem())
                .pattern("A A")
                .pattern("ABA")
                .pattern("A A")
                .define('A', Items.IRON_NUGGET)
                .define('B', Blocks.BIRCH_PLANKS.asItem())
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CORE_BATTERY.get())
                .pattern(" B ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', Items.REDSTONE)
                .define('B', Items.IRON_INGOT)
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.BATTERY_CELL.asItem())
                .pattern(" B ")
                .pattern(" A ")
                .pattern(" B ")
                .define('A', ModItems.CORE_BATTERY.get())
                .define('B', ModBlocks.CORE_PLATES.asItem())
                .unlockedBy("has_core_plates", has(ModBlocks.CORE_PLATES.asItem()))
                .save(recipeOutput);
        
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS,ModBlocks.CORE_PLATES.asItem(), Items.IRON_BLOCK, 32);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CORE_PILLAR.asItem(), ModBlocks.CORE_PLATES.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CORE_PILLAR_JUNCTION.asItem(), ModBlocks.CORE_PLATES.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENERGIZED_CORE_PLATES.asItem(), ModBlocks.CORE_PLATES.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CORE_TILES.asItem(), ModBlocks.CORE_PLATES.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIAGONAL_CORE_TILES.asItem(), ModBlocks.CORE_PLATES.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CORE_CONTROL_PANEL.asItem(), ModBlocks.CORE_PLATES.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CORE_BUTTON_CONTROL_PANEL.asItem(), ModBlocks.CORE_PLATES.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CORE_LEVER_CONTROL_PANEL.asItem(), ModBlocks.CORE_PLATES.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CORE_CONTROL_PANEL_SCREEN.asItem(), ModBlocks.CORE_PLATES.asItem());
        
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CORE_DOOR.asItem())
                .pattern("AA ")
                .pattern("AA ")
                .pattern("AA ")
                .define('A', ModBlocks.CORE_PLATES.asItem())
                .unlockedBy("has_core_plates", has(ModBlocks.CORE_PLATES.asItem()))
                .save(recipeOutput);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WALL_NOTES.asItem(), Items.PAPER);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.JUICE_BOTTLE.asItem(), Items.PAPER);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLDEN_MEDAL.asItem(), Items.GOLD_INGOT);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SILVER_MEDAL.asItem(), Items.IRON_INGOT);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.COPPER_MEDAL.asItem(), Items.COPPER_INGOT);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHEEL_TIRE.asItem())
                .pattern(" A ")
                .pattern("A A")
                .pattern(" A ")
                .define('A', Items.KELP)
                .unlockedBy("has_kelp", has(Items.KELP))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHEEL.asItem(), 1)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.KELP)
                .define('B', Items.IRON_INGOT)
                .unlockedBy("has_kelp", has(Items.KELP))
                .save(recipeOutput);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_LATTICE.asItem(), Items.IRON_INGOT, 2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.GOLDEN_LATTICE.asItem(), Items.GOLD_INGOT, 2);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.COPPER_LATTICE.asItem(), Items.COPPER_INGOT, 2);
        
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.METAL_TABLE.asItem(), Items.IRON_INGOT);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SEWING_MACHINE.asItem(), Items.IRON_INGOT);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.OAK_PLANKS_STRIPS.asItem(), Blocks.OAK_PLANKS.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.DARK_OAK_PLANKS_STRIPS.asItem(), Blocks.DARK_OAK_PLANKS.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ACACIA_PLANKS_STRIPS.asItem(), Blocks.ACACIA_PLANKS.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.JUNGLE_PLANKS_STRIPS.asItem(), Blocks.JUNGLE_PLANKS.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BIRCH_PLANKS_STRIPS.asItem(), Blocks.BIRCH_PLANKS.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.MANGROVE_PLANKS_STRIPS.asItem(), Blocks.MANGROVE_PLANKS.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CHERRY_PLANKS_STRIPS.asItem(), Blocks.CHERRY_PLANKS.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPRUCE_PLANKS_STRIPS.asItem(), Blocks.SPRUCE_PLANKS.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WARPED_PLANKS_STRIPS.asItem(), Blocks.WARPED_PLANKS.asItem());
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CRIMSON_PLANKS_STRIPS.asItem(), Blocks.CRIMSON_PLANKS.asItem());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ModBlocks.WHITE_CUBIC_SHELF.get())
                .requires(ModBlocks.PAINTED_PLANKS.get(DyeColor.WHITE).get())
                .unlockedBy("has_whited_planks", has(ModBlocks.PAINTED_PLANKS.get(DyeColor.WHITE).get()))
                .save(recipeOutput);

        //LIGHTS

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LED_LAMP.asItem())
                .pattern("AAA")
                .pattern("ACA")
                .pattern("BBB")
                .define('A', Blocks.COBBLESTONE.asItem())
                .define('B', Blocks.GLASS_PANE.asItem())
                .define('C', Blocks.REDSTONE_LAMP.asItem())
                .unlockedBy("has_redstone_lamp", has(Blocks.REDSTONE_LAMP.asItem()))
                .save(recipeOutput);

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_LED_LAMP.asItem(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlocks.LED_LAMP.asItem())
                .define('B', Items.RED_DYE)
                .unlockedBy("has_led_lamp", has(ModBlocks.LED_LAMP.asItem()))
                .save(recipeOutput);

            ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ORANGE_LED_LAMP.asItem(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlocks.LED_LAMP.asItem())
                .define('B', Items.ORANGE_DYE)
                .unlockedBy("has_led_lamp", has(ModBlocks.LED_LAMP.asItem()))
                .save(recipeOutput);

                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.YELLOW_LED_LAMP.asItem(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlocks.LED_LAMP.asItem())
                .define('B', Items.YELLOW_DYE)
                .unlockedBy("has_led_lamp", has(ModBlocks.LED_LAMP.asItem()))
                .save(recipeOutput);

                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIME_LED_LAMP.asItem(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlocks.LED_LAMP.asItem())
                .define('B', Items.LIME_DYE)
                .unlockedBy("has_led_lamp", has(ModBlocks.LED_LAMP.asItem()))
                .save(recipeOutput);

                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.GREEN_LED_LAMP.asItem(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlocks.LED_LAMP.asItem())
                .define('B', Items.GREEN_DYE)
                .unlockedBy("has_led_lamp", has(ModBlocks.LED_LAMP.asItem()))
                .save(recipeOutput);

                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CYAN_LED_LAMP.asItem(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlocks.LED_LAMP.asItem())
                .define('B', Items.CYAN_DYE)
                .unlockedBy("has_led_lamp", has(ModBlocks.LED_LAMP.asItem()))
                .save(recipeOutput);

                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIGHT_BLUE_LED_LAMP.asItem(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlocks.LED_LAMP.asItem())
                .define('B', Items.LIGHT_BLUE_DYE)
                .unlockedBy("has_led_lamp", has(ModBlocks.LED_LAMP.asItem()))
                .save(recipeOutput);

                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLUE_LED_LAMP.asItem(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlocks.LED_LAMP.asItem())
                .define('B', Items.BLUE_DYE)
                .unlockedBy("has_led_lamp", has(ModBlocks.LED_LAMP.asItem()))
                .save(recipeOutput);

                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PURPLE_LED_LAMP.asItem(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlocks.LED_LAMP.asItem())
                .define('B', Items.PURPLE_DYE)
                .unlockedBy("has_led_lamp", has(ModBlocks.LED_LAMP.asItem()))
                .save(recipeOutput);

                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MAGENTA_LED_LAMP.asItem(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlocks.LED_LAMP.asItem())
                .define('B', Items.MAGENTA_DYE)
                .unlockedBy("has_led_lamp", has(ModBlocks.LED_LAMP.asItem()))
                .save(recipeOutput);

                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PINK_LED_LAMP.asItem(), 8)
                .pattern("AAA")
                .pattern("ABA")
                .pattern("AAA")
                .define('A', ModBlocks.LED_LAMP.asItem())
                .define('B', Items.PINK_DYE)
                .unlockedBy("has_led_lamp", has(ModBlocks.LED_LAMP.asItem()))
                .save(recipeOutput);
        
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.LIGHT_BULB.asItem(), ModBlocks.LED_LAMP.asItem(), 6);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.INDUSTRIAL_RED_LAMP.asItem(), ModBlocks.LED_LAMP.asItem(), 4);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.REDSTONE, ModBlocks.CEILING_LAMP, ModBlocks.LED_LAMP.get(), 4);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.REDSTONE, ModBlocks.FLUORESCENT_LIGHT, ModBlocks.LED_LAMP.get(), 4);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_SLIM_METAL_GAS_CYLINDER.asItem(), Items.IRON_INGOT);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_METAL_GAS_CYLINDER.asItem(), Items.IRON_INGOT);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.RED_WIDE_METAL_GAS_CYLINDER.asItem(), Items.IRON_INGOT);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.LOZENGE_TACTILE_PAVING.asItem(), 6)
                .pattern("A A")
                .pattern("ABA")
                .pattern("A A")
                .define('A', Items.YELLOW_DYE)
                .define('B', Blocks.SMOOTH_STONE.asItem())
                .unlockedBy("has_smooth_stone", has(Blocks.SMOOTH_STONE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BLISTER_TACTILE_PAVING.asItem(), 4)
                .pattern("A A")
                .pattern(" B ")
                .pattern("A A")
                .define('A', Items.YELLOW_DYE)
                .define('B', Blocks.SMOOTH_STONE.asItem())
                .unlockedBy("has_smooth_stone", has(Blocks.SMOOTH_STONE))
                .save(recipeOutput);

                ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.PLIERS.get(), 1)
                .pattern("A A")
                .pattern("BAB")
                .pattern("C C")
                .define('A', Items.IRON_NUGGET)
                .define('B', Items.IRON_INGOT)
                .define('C', Items.ORANGE_DYE)
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WALL_CLOCK.get().asItem(), 1)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.IRON_INGOT)
                .define('B', Items.CLOCK)
                .unlockedBy("has_iron", has(Items.IRON_INGOT))
                .save(recipeOutput);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_WOODEN_TILE_DOOR.asItem(), ModBlocks.WHITE_WOODEN_PANEL_DOOR);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WHITE_WOODEN_CLEAR_DOOR.asItem(), ModBlocks.WHITE_WOODEN_PANEL_DOOR);

        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WOODEN_TILE_DOOR.asItem(), ModBlocks.WOODEN_PANEL_DOOR);
        stonecutterResultFromBase(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.WOODEN_CLEAR_DOOR.asItem(), ModBlocks.WOODEN_PANEL_DOOR);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.WOODEN_CRIB.get().asItem(), 1)
                .pattern("A A")
                .pattern("ABA")
                .define('A', Items.OAK_PLANKS)
                .define('B', Items.WHITE_WOOL)
                .unlockedBy("has_wool", has(Items.WHITE_WOOL))
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
