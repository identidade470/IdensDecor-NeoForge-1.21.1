package net.identidade.iden_decor.datagen;

import net.identidade.iden_decor.IdenDecorMod;
import net.identidade.iden_decor.block.ModBlocks;
import net.identidade.iden_decor.item.ModItems;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, IdenDecorMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.PLASTIC_CUP.get());
        basicItem(ModItems.WATER_CUP.get());
        basicItem(ModItems.TELEPHONE_ITEM.get());
        basicItem(ModItems.RED_WRAP.get());
        basicItem(ModItems.FLOPPY_DISK.get());

//        ModItems.PAINT_BRUSHES.values().forEach(item -> basicItem(item.get()));
//
//        buttonItem(ModBlocks.BLOODY_BUTTON, ModBlocks.BLOODY_PLANKS);
//        fenceItem(ModBlocks.BLOODY_FENCE, ModBlocks.BLOODY_PLANKS);
//
        basicItem(ModBlocks.GRID_METAL_DOOR.asItem());
        basicItem(ModBlocks.YELLOW_METAL_DOOR.asItem());
        basicItem(ModBlocks.WHITE_METAL_DOOR.asItem());

        basicItem(ModBlocks.DARK_WATER_DISPENSER.asItem());
        basicItem(ModBlocks.LIGHT_WATER_DISPENSER.asItem());

        basicItem(ModItems.KEYCARD.get());
        basicItem(ModItems.GUARANA_CUP.get());

        blockItemWithFrontGuiLight(ModBlocks.WATER_POSTER);
        blockItemWithFrontGuiLight(ModBlocks.CCS_POSTER);
        blockItemWithFrontGuiLight(ModBlocks.STOP_SIGN);
    }

    private void blockItemWithFrontGuiLight(DeferredBlock<?> block) {
        this.withExistingParent(block.getId().getPath(), modLoc("block/" + block.getId().getPath()))
                .guiLight(BlockModel.GuiLight.FRONT);
    }



//    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
//        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
//                .texture("texture",  ResourceLocation.fromNamespaceAndPath(Crimsomania.MOD_ID,
//                        "block/" + baseBlock.getId().getPath()));
//    }
//
//    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
//        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
//                .texture("texture",  ResourceLocation.fromNamespaceAndPath(Crimsomania.MOD_ID,
//                        "block/" + baseBlock.getId().getPath()));
//    }
//
//    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
//        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
//                .texture("wall",  ResourceLocation.fromNamespaceAndPath(Crimsomania.MOD_ID,
//                        "block/" + baseBlock.getId().getPath()));
//    }
}
