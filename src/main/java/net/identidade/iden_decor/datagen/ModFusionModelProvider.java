package net.identidade.iden_decor.datagen;

import com.supermartijn642.fusion.api.model.DefaultModelTypes;
import com.supermartijn642.fusion.api.model.ModelInstance;
import com.supermartijn642.fusion.api.model.data.ConnectingModelDataBuilder;
import com.supermartijn642.fusion.api.predicate.DefaultConnectionPredicates;
import com.supermartijn642.fusion.api.provider.FusionModelProvider;
import net.identidade.iden_decor.IdenDecorMod;
import net.identidade.iden_decor.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModFusionModelProvider extends FusionModelProvider {
    public ModFusionModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(IdenDecorMod.MOD_ID, output, existingFileHelper);
    }

    @Override
    protected void generate() {
        simpleConnectedBlock(ModBlocks.GREEN_DIAMOND_WALLPAPER.get());
        simpleConnectedBlock(ModBlocks.RED_DIAMOND_WALLPAPER.get());
        simpleConnectedBlock(ModBlocks.YELLOW_ARROW_WALLPAPER.get());
        simpleConnectedBlock(ModBlocks.BLUE_CLOUDS_WALLPAPER.get());
        simpleConnectedBlock(ModBlocks.BLACK_CLOUDS_WALLPAPER.get());
        simpleConnectedBlock(ModBlocks.FLUORESCENT_LIGHT_BLOCK.get());
        simpleConnectedBlock(ModBlocks.BLACK_STARRY_WALLPAPER.get());
        simpleConnectedBlock(ModBlocks.RED_GOLDEN_CARPET_BLOCK.get());
        simpleConnectedBlock(ModBlocks.GREEN_ARROW_CARPET_BLOCK.get());

        carpetConnectedBlock(ModBlocks.RED_GOLDEN_CARPET.get(), ModBlocks.RED_GOLDEN_CARPET_BLOCK.get());
        carpetConnectedBlock(ModBlocks.GREEN_ARROW_CARPET.get(), ModBlocks.GREEN_ARROW_CARPET_BLOCK.get());

        ModBlocks.FRAMED_PLANKS.values().forEach(block -> simpleConnectedBlock(block.get()));
    }

    private void simpleConnectedBlock(Block block) {
        var modelData = ConnectingModelDataBuilder.builder()
                .parent(ResourceLocation.fromNamespaceAndPath("minecraft", "block/cube_all"))
                .texture("all", ResourceLocation.fromNamespaceAndPath("iden_decor", "block/"+getPath(block)))
                .defaultConnections(DefaultConnectionPredicates.isSameBlock())
                .build();

        var modelInstance = ModelInstance.of(DefaultModelTypes.CONNECTING, modelData);
        this.addModel(ResourceLocation.fromNamespaceAndPath("iden_decor", "block/"+getPath(block)), modelInstance);
    }

    private void carpetConnectedBlock(Block block, Block wool) {
        var modelData = ConnectingModelDataBuilder.builder()
                .parent(ResourceLocation.fromNamespaceAndPath("minecraft", "block/carpet"))
                .texture("wool", ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/"+getPath(wool)))
                .defaultConnections(DefaultConnectionPredicates.isSameBlock())
                .build();

        var modelInstance = ModelInstance.of(DefaultModelTypes.CONNECTING, modelData);
        this.addModel(ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/"+getPath(block)), modelInstance);
    }

    private static String getPath(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }
}
