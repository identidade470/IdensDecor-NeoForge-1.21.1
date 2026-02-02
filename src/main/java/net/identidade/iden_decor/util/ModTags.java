package net.identidade.iden_decor.util;

import net.identidade.iden_decor.IdenDecorMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> RAILINGS = createTag("railings");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, name));
        }
    }
}
