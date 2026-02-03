package net.identidade.iden_decor.datagen;

import net.identidade.iden_decor.IdenDecorMod;
import net.identidade.iden_decor.block.ModBlocks;
import net.identidade.iden_decor.block.custom.*;
import net.identidade.iden_decor.block.properties.ColorProperty;
import net.identidade.iden_decor.block.properties.ThreeConnectableProperty;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, IdenDecorMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
//        blockWithItem(ModBlocks.BLOODY_LOG);
//        blockWithItem(ModBlocks.BLOODY_MOUTH)

        blockWithRenderType(ModBlocks.IRON_GRATE, "translucent");
        blockWithRenderType(ModBlocks.STEEL_GRATE, "translucent");


//        blockWithItem(ModBlocks.FLESH_BLOCK);
//        blockWithItem(ModBlocks.PULSAR_BULB);
//
//        stairsBlock(ModBlocks.BLOODY_STAIRS.get(), blockTexture(ModBlocks.BLOODY_PLANKS.get()));
//        slabBlock(ModBlocks.BLOODY_SLAB.get(), blockTexture(ModBlocks.BLOODY_PLANKS.get()), blockTexture(ModBlocks.BLOODY_PLANKS.get()));
//        buttonBlock(ModBlocks.BLOODY_BUTTON.get(), blockTexture(ModBlocks.BLOODY_PLANKS.get()));
//        fenceBlock(ModBlocks.BLOODY_FENCE.get(), blockTexture(ModBlocks.BLOODY_PLANKS.get()));
//        fenceGateBlock(ModBlocks.BLOODY_FENCE_GATE.get(), blockTexture(ModBlocks.BLOODY_PLANKS.get()));
//        pressurePlateBlock(ModBlocks.BLOODY_PRESSURE_PLATE.get(), blockTexture(ModBlocks.BLOODY_PLANKS.get()));
//
//        logBlock(ModBlocks.STRIPPED_BLOODY_LOG.get());
//        axisBlock(ModBlocks.BLOODY_WOOD.get(), modLoc("block/bloody_log"), modLoc("block/bloody_log"));
//        axisBlock(ModBlocks.STRIPPED_BLOODY_WOOD.get(), modLoc("block/stripped_bloody_log"), modLoc("block/stripped_bloody_log"));
//
//        doorBlockWithRenderType(ModBlocks.BLOODY_DOOR.get(), modLoc("block/bloody_door_bottom"), modLoc("block/bloody_door_top"), "cutout");
//        trapdoorBlockWithRenderType(ModBlocks.BLOODY_TRAPDOOR.get(), modLoc("block/bloody_trapdoor"), true, "cutout");
//
        blockItem(ModBlocks.IRON_GRATE);
        blockItem(ModBlocks.STEEL_GRATE);

        blockWithItem(ModBlocks.WHITE_BRICKS);
        stairsBlock(ModBlocks.WHITE_BRICKS_STAIRS.get(), blockTexture(ModBlocks.WHITE_BRICKS.get()));
        slabBlock(ModBlocks.WHITE_BRICKS_SLAB.get(), blockTexture(ModBlocks.WHITE_BRICKS.get()), blockTexture(ModBlocks.WHITE_BRICKS.get()));

        blockWithItem(ModBlocks.WHITE_TILES);
        stairsBlock(ModBlocks.WHITE_TILES_STAIRS.get(), blockTexture(ModBlocks.WHITE_TILES.get()));
        slabBlock(ModBlocks.WHITE_TILES_SLAB.get(), blockTexture(ModBlocks.WHITE_TILES.get()), blockTexture(ModBlocks.WHITE_TILES.get()));

        blockWithItem(ModBlocks.SMOOTH_STONE_TILES);
        blockWithItem(ModBlocks.SMOOTH_STONE_BRICKS);
        stairsBlock(ModBlocks.SMOOTH_STONE_TILES_STAIRS.get(), blockTexture(ModBlocks.SMOOTH_STONE_TILES.get()));
        slabBlock(ModBlocks.SMOOTH_STONE_TILES_SLAB.get(), blockTexture(ModBlocks.SMOOTH_STONE_TILES.get()), blockTexture(ModBlocks.SMOOTH_STONE_TILES.get()));

        blockItemWithItemTexture(ModBlocks.LIGHT_WATER_DISPENSER);
        blockItemWithItemTexture(ModBlocks.DARK_WATER_DISPENSER);

        blockItem(ModBlocks.CCS_POSTER);
        blockItem(ModBlocks.WATER_POSTER);

        trapdoorBlockWithRenderType(ModBlocks.AIR_VENT.get(), ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/air_vent"), true, "cutout");

        blockItem(ModBlocks.HEAVY_BUTTON);
        blockItem(ModBlocks.GATE_BUTTON);
        blockItem(ModBlocks.LIGHT_SWITCH);
        blockItem(ModBlocks.POWER_SWITCH);
        blockItem(ModBlocks.EMERGENCY_LEVER);
        blockItem(ModBlocks.WHITE_BRICKS_SLAB);
        blockItem(ModBlocks.WHITE_BRICKS_STAIRS);
        blockItem(ModBlocks.WHITE_TILES_SLAB);
        blockItem(ModBlocks.WHITE_TILES_STAIRS);
        blockItem(ModBlocks.SMOOTH_STONE_TILES_SLAB);
        blockItem(ModBlocks.SMOOTH_STONE_TILES_STAIRS);
        blockItem(ModBlocks.AIR_VENT, "_bottom");

        blockWithItem(ModBlocks.IRON_SHEET_METAL);
        blockWithItem(ModBlocks.AIR_DUCT);

        metal_barrel(ModBlocks.BLUE_METAL_BARREL.get());
        metal_barrel(ModBlocks.METAL_BARREL.get());

        heavyLever(ModBlocks.HEAVY_LEVER.get());

        plushie(ModBlocks.PLUSHIE_IDEN.get());
        plushie(ModBlocks.PLUSHIE_DOLI.get());
        plushie(ModBlocks.PLUSHIE_DINO.get());
        plushie(ModBlocks.PLUSHIE_RED.get());
        plushie(ModBlocks.PLUSHIE_RAFA.get());

        simpleModelBlockWithRenderType(ModBlocks.PLASTIC_TABLE.get(), "cutout");
        simpleHorizontalModelBlock(ModBlocks.GUARANA_ANTARTICA.get());

        box(ModBlocks.SMALL_BOX.get());
//        simpleHorizontalModelBlock(ModBlocks.MEDIUM_BOX.get());
//        simpleHorizontalModelBlock(ModBlocks.BIG_BOX.get());

        telephone(ModBlocks.TELEPHONE.get());

        paintableRailing(ModBlocks.IRON_RAILING.get(), "iron_grate");
        paintableRailing(ModBlocks.COPPER_RAILING.get(), "copper_grate");
        paintableRailing(ModBlocks.GOLD_RAILING.get(), "gold_railing/base");

        fluorescentLight(ModBlocks.FLUORESCENT_LIGHT.get());

        ModBlocks.PAINTED_PLANKS.values().forEach(block -> simpleBlockWithItem(block.get(), cubeAll(block.get())));


//        blockItemWithItemTexture(ModBlocks.HEAVY_BUTTON);
//        blockItemWithItemTexture(ModBlocks.GATE_BUTTON);
//        blockItemWithItemTexture(ModBlocks.LIGHT_SWITCH);
//        blockItem(ModBlocks.BLOODY_LOG);
//        blockItem(ModBlocks.STRIPPED_BLOODY_LOG);
//        blockItem(ModBlocks.STRIPPED_BLOODY_WOOD);
//        blockItem(ModBlocks.BLOODY_WOOD);
//        blockItem(ModBlocks.BLOODY_PRESSURE_PLATE);
//        blockItem(ModBlocks.BLOODY_STAIRS);
//        blockItem(ModBlocks.BLOODY_SLAB);
//        blockItem(ModBlocks.BLOODY_FENCE_GATE);
//        blockItem(ModBlocks.BLOODY_TRAPDOOR, "_bottom");
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("iden_decor:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("iden_decor:block/" + deferredBlock.getId().getPath() + appendix));
    }

    public void blockItemWithItemTexture(DeferredBlock<?> deferredBlock) {
        itemModels().getBuilder(deferredBlock.getId().getPath())
                .parent(new ModelFile.UncheckedModelFile("minecraft:item/generated"))
                .texture("layer0", modLoc("item/" + deferredBlock.getId().getPath()));
    }

    public void blockWithRenderType(DeferredBlock<?> deferredBlock, String renderType) {
        simpleBlock(deferredBlock.get(), ((BlockModelBuilder) cubeAll(deferredBlock.get())).renderType(renderType));
    }

    private void cubeBottomTop(Block block) {
        simpleBlockItem(block, new ModelFile.UncheckedModelFile(modLoc("block/" + getPath(block))));

        simpleBlock(block, models().withExistingParent(
                getPath(block),
                mcLoc("block/cube_bottom_top"))
                .texture("side", modLoc("block/" + getPath(block) + "_side"))
                .texture("top", modLoc("block/" + getPath(block) + "_top"))
                .texture("bottom", modLoc("block/" + getPath(block) + "_bottom")));
    }

    private void simpleModelBlock(Block block) {
        simpleBlockItem(block, models().getExistingFile(modLoc("block/base/" + getPath(block))));

        ResourceLocation parent = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/base/" + getPath(block));
        ResourceLocation baseTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/" + getPath(block));

        simpleBlock(block, models().withExistingParent(getPath(block), parent)
                .texture("0", baseTexture)
                .texture("particle", baseTexture));
    }

    private void simpleAxisModelBlock(RotatedPillarBlock block, String path) {
        simpleBlockItem(block, models().getExistingFile(modLoc("block/"+ path)));

        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction.Axis axis = state.getValue(RotatedPillarBlock.AXIS);

                    int rotX = 0;
                    int rotY = 0;

                    if (axis== Direction.Axis.X) {
                        rotX=90;
                        rotY=90;
                    } else if (axis== Direction.Axis.Z) {
                        rotX=90;
                    }

                    ResourceLocation model = modLoc("block/" + path);

                    return ConfiguredModel.builder()
                            .modelFile(models().getExistingFile(model))
                            .rotationY(rotY)
                            .rotationX(rotX)
                            .build();
                });
    }

    private void simpleHorizontalModelBlock(Block block){
        simpleBlockItem(block, models().getExistingFile(modLoc("block/base/" + getPath(block))));

        ResourceLocation parent = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/base/" + getPath(block));
        ResourceLocation baseTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/" + getPath(block));

        horizontalBlock(block, models().withExistingParent(getPath(block), parent)
                .texture("0", baseTexture)
                .texture("particle", baseTexture));
    }

    private void simpleModelBlockWithRenderType(Block block, String renderType) {
        simpleBlockItem(block, models().getExistingFile(modLoc("block/base/" + getPath(block))));

        ResourceLocation parent = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/base/" + getPath(block));
        ResourceLocation baseTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/" + getPath(block));

        simpleBlock(block, models().withExistingParent(getPath(block), parent)
                .texture("0", baseTexture)
                .texture("particle", baseTexture)
                .renderType(renderType));
    }

    private void metal_barrel(Block block) {
        simpleBlockItem(block, new ModelFile.UncheckedModelFile(modLoc("block/" + getPath(block))));

        ResourceLocation sideTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/" + getPath(block) + "_side");
        ResourceLocation bottomTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/" + getPath(block) + "_bottom");
        ResourceLocation topTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/" + getPath(block) + "_top");

        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction facing = state.getValue(MetalBarrelBlock.FACING);

                    int rotY = switch (facing) {
                        case NORTH -> 180;
                        case WEST  -> 90;
                        case EAST  -> 270;
                        default    -> 0; // SOUTH
                    };

                    return ConfiguredModel.builder()
                            .modelFile(
                                    models().getBuilder(getPath(block))
                                            .parent(models().getExistingFile(mcLoc("block/barrel")))
                                            .texture("side", sideTexture)
                                            .texture("top", topTexture)
                                            .texture("bottom", bottomTexture)
                                            .texture("particle", bottomTexture)
                            )
                            .rotationY(rotY)
                            .build();
                });
    }

    private void telephone(Block block) {
        simpleBlockItem(block, new ModelFile.UncheckedModelFile(modLoc("block/" + getPath(block))));
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction facing = state.getValue(TelephoneBlock.FACING);
                    Boolean phone = state.getValue(TelephoneBlock.PHONE);

                    ResourceLocation parent = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/telephone/telephone" + (phone?"":"_phoneless"));
                    ResourceLocation baseTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/telephone");

                    return ConfiguredModel.builder()
                            .modelFile(
                                    models().getBuilder(getPath(block) + (phone?"":"_phoneless"))
                                            .parent(models().getExistingFile(parent))
                                            .texture("0", baseTexture)
                                            .texture("particle", baseTexture)
                            )
                            .rotationY(((int) facing.toYRot() + 180) % 360)
                            .build();
                });
    }

    private void plushie(Block block) {
        simpleBlockItem(block, new ModelFile.UncheckedModelFile(modLoc("block/" + getPath(block))));
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction facing = state.getValue(PlushieBlock.FACING);

                    ResourceLocation parent = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/base/plushie");
                    ResourceLocation plushTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/plushie/" + getPath(block).split("_")[1]);
                    ResourceLocation particle = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/plushie/" + getPath(block).split("_")[1]);

                    return ConfiguredModel.builder()
                            .modelFile(
                                    models().getBuilder(getPath(block))
                                            .parent(models().getExistingFile(parent))
                                            .texture("0", plushTexture)
                                            .texture("particle", particle)
                                            .renderType("cutout")
                            )
                            .rotationY(((int) facing.toYRot() + 180) % 360)
                            .build();
                });
    }

    private void paintableRailing(Block block, String particleTexture) {
        simpleBlockItem(block, models().getExistingFile(modLoc("block/" + getPath(block) + "/single")));
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction facing = state.getValue(RailingBlock.FACING);
                    ThreeConnectableProperty part = state.getValue(RailingBlock.PART);
                    ColorProperty color = state.getValue(RailingBlock.COLOR);

                    String partName = switch (part) {
                        case LEFT -> "left";
                        case RIGHT -> "right";
                        case CENTER -> "center";
                        case SINGLE -> "single";
                    };

                    ResourceLocation parent = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/" + getPath(block) + "/" + partName);
                    ResourceLocation baseTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/" + getPath(block) + "/base");
                    ResourceLocation colorTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/" + getPath(block) + "/colors/" + color.getSerializedName());
                    ResourceLocation particle = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/" + particleTexture);


                    return ConfiguredModel.builder()
                            .modelFile(
                                    models().getBuilder(getPath(block) + "_" + partName + "_" + color.getSerializedName())
                                            .parent(models().getExistingFile(parent))
                                            .renderType("cutout")
                                            .texture("0", baseTexture)
                                            .texture("1", colorTexture)
                                            .texture("particle", particle))
                            .rotationY(((int) facing.toYRot() + 180) % 360)
                            .build();
                });
    }

    private void railing(Block block, String particleTexture) {
        simpleBlockItem(block, models().getExistingFile(modLoc("block/" + "metal_railing" + "/single")));
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction facing = state.getValue(RailingBlock.FACING);
                    ThreeConnectableProperty part = state.getValue(RailingBlock.PART);

                    String partName = switch (part) {
                        case LEFT -> "left";
                        case RIGHT -> "right";
                        case CENTER -> "center";
                        case SINGLE -> "single";
                    };

                    ResourceLocation parent = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/metal_railing/" + partName);
                    ResourceLocation baseTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/metal_railing/" + getPath(block));
                    ResourceLocation particle = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/" + particleTexture);

                    return ConfiguredModel.builder()
                            .modelFile(
                                    models().getBuilder(getPath(block) + "_" + partName)
                                            .parent(models().getExistingFile(parent))
                                            .renderType("cutout")
                                            .texture("0", baseTexture)
                                            .texture("particle", particle))
                            .rotationY(((int) facing.toYRot() + 180) % 360)
                            .build();
                });
    }

    private void box(Block block) {
        simpleBlockItem(block, models().getExistingFile(modLoc("block/" + getPath(block) + "/closed")));

        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction facing = state.getValue(BoxBlock.FACING);
                    Boolean opened = state.getValue(BoxBlock.OPENED);

                    ResourceLocation parent = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/" + getPath(block) + "/" + (opened?"opened":"closed"));
                    ResourceLocation baseTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/" + getPath(block));

                    return ConfiguredModel.builder()
                            .modelFile(models().getBuilder(getPath(block) + "_" + facing.getSerializedName() + "_" + (opened?"opened":"closed"))
                                    .parent(models().getExistingFile(parent))
                                    .texture("0", baseTexture)
                                    .texture("particle", baseTexture)
                            )
                            .rotationY(((int) facing.toYRot() + 180) % 360)
                            .build();
                });
    }

    private void fluorescentLight(Block block) {
        simpleBlockItem(block, models().getExistingFile(modLoc("block/fluorescent_light/single")));
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction facing = state.getValue(FluorescentLightBlock.FACING);
                    AttachFace face = state.getValue(FluorescentLightBlock.FACE);
                    ThreeConnectableProperty part = state.getValue(FluorescentLightBlock.PART);

                    ResourceLocation parent = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/fluorescent_light/" + part.getSerializedName());
                    ResourceLocation baseTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/fluorescent_light");

                    int rotX = switch (face) {
                        case FLOOR -> 0;
                        case WALL -> 90;
                        case CEILING -> 180;
                    };

                    return ConfiguredModel.builder()
                            .modelFile(models().getBuilder(getPath(block) + "_" + part.getSerializedName())
                                    .parent(models().getExistingFile(parent))
                                    .texture("0", baseTexture)
                                    .texture("particle", baseTexture))
                            .rotationY(((int) facing.toYRot() + 180) % 360)
                            .rotationX(rotX)
                            .build();
                });
    }

    private void heavyLever(Block block) {
        simpleBlockItem(block, models().getExistingFile(modLoc("block/heavy_lever/single")));
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction facing = state.getValue(HeavyLeverBlock.FACING);
                    AttachFace face = state.getValue(HeavyLeverBlock.FACE);
                    Boolean powered = state.getValue(HeavyLeverBlock.POWERED);
                    ColorProperty color = state.getValue(HeavyLeverBlock.COLOR);

                    ResourceLocation parent = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/heavy_lever/single" + (powered ? "_pressed":""));
                    ResourceLocation baseTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/heavy_lever/heavy_lever");
                    ResourceLocation colorTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/heavy_lever/colors/" + color.getSerializedName());

                    int rotX = switch (face) {
                        case FLOOR -> 0;
                        case WALL -> 90;
                        case CEILING -> 180;
                    };

                    return ConfiguredModel.builder()
                            .modelFile(
                                    models().getBuilder(String.format("%s_%s_%s", getPath(block), powered?"on":"off", color.getSerializedName()))
                                            .parent(models().getExistingFile(parent))
                                            .texture("0", baseTexture)
                                            .texture("1", colorTexture)
                                            .texture("particle", colorTexture))
                            .rotationY((((int) facing.toYRot() + 180)) % 360)
                            .rotationX(rotX)
                            .build();
                });
    }

    private static String getPath(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

}
