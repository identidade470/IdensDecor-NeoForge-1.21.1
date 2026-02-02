package net.identidade.iden_decor.datagen;

import net.identidade.iden_decor.IdenDecorMod;
import net.identidade.iden_decor.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, IdenDecorMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
//        tag(ModTags.Items.BLOODY_LOGS)
//                .add(ModBlocks.STRIPPED_BLOODY_WOOD.asItem())
//                .add(ModBlocks.STRIPPED_BLOODY_LOG.asItem())
//                .add(ModBlocks.BLOODY_WOOD.asItem())
//                .add(ModBlocks.BLOODY_LOG.asItem());
//
//        tag(ModTags.Items.MODIFIABLE_TOOLS)
//                .add(ModItems.FLESH_SWORD.get());
//
//        tag(ItemTags.PLANKS)
//                .add(ModBlocks.BLOODY_PLANKS.asItem());

        ModBlocks.PAINTED_PLANKS.values().forEach(block -> tag(ItemTags.PLANKS)
                .add(block.get().asItem()));
    }
}
