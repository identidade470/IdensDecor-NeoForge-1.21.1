package net.identidade.iden_decor.datagen;

import net.identidade.iden_decor.IdenDecorMod;
import net.identidade.iden_decor.block.ModBlocks;
import net.identidade.iden_decor.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, IdenDecorMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
//        tag(ModTags.Blocks.BLOODY_LOGS)
//                .add(ModBlocks.STRIPPED_BLOODY_WOOD.get())
//                .add(ModBlocks.STRIPPED_BLOODY_LOG.get())
//                .add(ModBlocks.BLOODY_WOOD.get())
//                .add(ModBlocks.BLOODY_LOG.get());
//
//        tag(BlockTags.MINEABLE_WITH_AXE)
//                .add(ModBlocks.STRIPPED_BLOODY_WOOD.get())
//                .add(ModBlocks.STRIPPED_BLOODY_LOG.get())
//                .add(ModBlocks.BLOODY_LOG.get())
//                .add(ModBlocks.BLOODY_WOOD.get())
//                .add(ModBlocks.BLOODY_PLANKS.get());
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.GREEN_DIAMOND_WALLPAPER.get())
                .add(ModBlocks.RED_DIAMOND_WALLPAPER.get())
                .add(ModBlocks.YELLOW_ARROW_WALLPAPER.get())
                .add(ModBlocks.CARVED_SPRUCE_PLANKS.get())
                .add(ModBlocks.BLUE_CLOUDS_WALLPAPER.get())
                .add(ModBlocks.BLACK_CLOUDS_WALLPAPER.get())
                .add(ModBlocks.BLACK_STARRY_WALLPAPER.get())

                .add(ModBlocks.OAK_METAL_SHELF.get())
                .add(ModBlocks.ACACIA_METAL_SHELF.get())
                .add(ModBlocks.BIRCH_METAL_SHELF.get())
                .add(ModBlocks.DARK_OAK_METAL_SHELF.get())
                .add(ModBlocks.JUNGLE_METAL_SHELF.get())
                .add(ModBlocks.CHERRY_METAL_SHELF.get())
                .add(ModBlocks.SPRUCE_METAL_SHELF.get())
                .add(ModBlocks.WARPED_METAL_SHELF.get())
                .add(ModBlocks.CRIMSON_METAL_SHELF.get())
                .add(ModBlocks.BAMBOO_METAL_SHELF.get())
                .add(ModBlocks.MANGROVE_METAL_SHELF.get())
                .add(ModBlocks.WHITE_WOODEN_PANEL_DOOR.get())
                .add(ModBlocks.WOODEN_PANEL_DOOR.get())
                .add(ModBlocks.WHITE_WOOD_RAILING.get())

                .add(ModBlocks.WHITE_CUBIC_SHELF.get())
        ;
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                // Furniture
                .add(ModBlocks.DARK_WATER_DISPENSER.get())
                .add(ModBlocks.LIGHT_WATER_DISPENSER.get())
                // Railings
                .add(ModBlocks.IRON_RAILING.get())
                .add(ModBlocks.COPPER_RAILING.get())
                .add(ModBlocks.GOLD_RAILING.get())
                // Blocks
                .add(ModBlocks.IRON_GRATE.get())
                .add(ModBlocks.STEEL_GRATE.get())

                .add(ModBlocks.SMOOTH_STONE_BRICKS.get())

                .add(ModBlocks.SMOOTH_STONE_TILES.get())
                .add(ModBlocks.SMOOTH_STONE_TILES_STAIRS.get())
                .add(ModBlocks.SMOOTH_STONE_TILES_SLAB.get())

                .add(ModBlocks.CAUTION_BLOCK.get())
                .add(ModBlocks.CAUTION_BLOCK_STAIRS.get())
                .add(ModBlocks.CAUTION_BLOCK_SLAB.get())

                .add(ModBlocks.WHITE_TILES.get())
                .add(ModBlocks.WHITE_TILES_STAIRS.get())
                .add(ModBlocks.WHITE_TILES_SLAB.get())

                .add(ModBlocks.WHITE_BRICKS.get())
                .add(ModBlocks.WHITE_BRICKS_STAIRS.get())
                .add(ModBlocks.WHITE_BRICKS_SLAB.get())

                .add(ModBlocks.IRON_SHEET_METAL.get())
                .add(ModBlocks.AIR_DUCT.get())

                .add(ModBlocks.METAL_BARREL.get())
                .add(ModBlocks.BLUE_METAL_BARREL.get())

                .add(ModBlocks.GRID_METAL_DOOR.get())
                .add(ModBlocks.YELLOW_METAL_DOOR.get())
                .add(ModBlocks.WHITE_METAL_DOOR.get())

                .add(ModBlocks.DOUBLE_IRON_PIPES.get())

                .add(ModBlocks.TELEPHONE.get())
                .add(ModBlocks.COMPUTER.get())
                .add(ModBlocks.DRINKING_FOUNTAIN.get())
                .add(ModBlocks.SIGN_POST.get())
                .add(ModBlocks.STOP_SIGN.get())
                .add(ModBlocks.KEYCARD_READER.get())
                .add(ModBlocks.CUP_DISPENSER.get())
                .add(ModBlocks.CONCRETE_BARRIER.get())
                .add(ModBlocks.FLUORESCENT_LIGHT_BLOCK.get())
                .add(ModBlocks.WALL_CLOCK.get())
                .add(ModBlocks.FLUORESCENT_LIGHT.get())
                .add(ModBlocks.METAL_SHELF.get())

                .add(ModBlocks.LONG_CONCRETE_VASE.get())
        ;

        tag(ModTags.Blocks.RAILINGS)
                .add(ModBlocks.GOLD_RAILING.get())
                .add(ModBlocks.IRON_RAILING.get())
                .add(ModBlocks.COPPER_RAILING.get());

        ModBlocks.PAINTED_PLANKS.values().forEach(block -> tag(BlockTags.PLANKS)
                .add(block.get()));

        ModBlocks.PAINTED_PLANKS.values().forEach(block -> tag(BlockTags.MINEABLE_WITH_AXE)
                .add(block.get()));

        ModBlocks.FRAMED_PLANKS.values().forEach(block -> tag(BlockTags.MINEABLE_WITH_AXE)
                .add(block.get()));

        ModBlocks.PAINTED_PLANKS_SLABS.values().forEach(block -> {
            tag(BlockTags.MINEABLE_WITH_AXE).add(block.get());
            tag(BlockTags.WOODEN_SLABS).add(block.get());
            tag(BlockTags.SLABS).add(block.get());
        });

        ModBlocks.PAINTED_PLANKS_STAIRS.values().forEach(block -> {
            tag(BlockTags.MINEABLE_WITH_AXE).add(block.get());
            tag(BlockTags.WOODEN_STAIRS).add(block.get());
            tag(BlockTags.STAIRS).add(block.get());
        });

        tag(BlockTags.FENCES)
                .add(ModBlocks.CAUTION_BLOCK_FENCE.get());

        tag(BlockTags.WALLS)
                .add(ModBlocks.WHITE_WOOD_RAILING.get())
                .add(ModBlocks.CAUTION_BLOCK_WALL.get());

        tag(BlockTags.SLABS)
                .add(ModBlocks.CAUTION_BLOCK_SLAB.get())
                .add(ModBlocks.SMOOTH_STONE_TILES_SLAB.get())
                .add(ModBlocks.WHITE_BRICKS_SLAB.get())
                .add(ModBlocks.WHITE_TILES_SLAB.get());

        tag(BlockTags.STAIRS)
                .add(ModBlocks.CAUTION_BLOCK_STAIRS.get())
                .add(ModBlocks.WHITE_BRICKS_STAIRS.get())
                .add(ModBlocks.SMOOTH_STONE_TILES_STAIRS.get())
                .add(ModBlocks.WHITE_TILES_STAIRS.get());

        tag(BlockTags.DOORS)
                .add(ModBlocks.WHITE_METAL_DOOR.get())
                .add(ModBlocks.YELLOW_METAL_DOOR.get())
                .add(ModBlocks.GRID_METAL_DOOR.get())
                .add(ModBlocks.WHITE_WOODEN_PANEL_DOOR.get())
                .add(ModBlocks.WOODEN_PANEL_DOOR.get())
        ;

        tag(BlockTags.TRAPDOORS)
                .add(ModBlocks.AIR_VENT.get());
//
//        tag(BlockTags.PLANKS)
//                .add(ModBlocks.BLOODY_PLANKS.get());
//
//        tag(BlockTags.FENCES)
//                .add(ModBlocks.BLOODY_FENCE.get());
//        tag(BlockTags.WOODEN_FENCES)
//                .add(ModBlocks.BLOODY_FENCE.get());
//        tag(BlockTags.FENCE_GATES)
//                .add(ModBlocks.BLOODY_FENCE_GATE.get());
    }
}
