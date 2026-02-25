package net.identidade.iden_decor.datagen;

import net.identidade.iden_decor.IdenDecorMod;
import net.identidade.iden_decor.block.ModBlocks;
import net.identidade.iden_decor.block.custom.*;
import net.identidade.iden_decor.block.properties.ColorProperty;
import net.identidade.iden_decor.block.properties.ThreeConnectableProperty;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.HashMap;
import java.util.Map;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, IdenDecorMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithRenderType(ModBlocks.IRON_GRATE, "translucent");
        simpleBlockWithRenderType(ModBlocks.STEEL_GRATE, "translucent");

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

        blockWithItem(ModBlocks.CAUTION_BLOCK);
        stairsBlock(ModBlocks.CAUTION_BLOCK_STAIRS.get(), blockTexture(ModBlocks.CAUTION_BLOCK.get()));
        slabBlock(ModBlocks.CAUTION_BLOCK_SLAB.get(), blockTexture(ModBlocks.CAUTION_BLOCK.get()), blockTexture(ModBlocks.CAUTION_BLOCK.get()));

        trapdoorBlockWithRenderType(ModBlocks.AIR_VENT.get(), ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/air_vent"), true, "cutout");

//        blockItem(ModBlocks.CCS_POSTER);
//        blockItem(ModBlocks.WATER_POSTER);
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
        blockItem(ModBlocks.VALVE_SWITCH);
        blockItem(ModBlocks.DOUBLE_IRON_PIPES);
        blockItem(ModBlocks.FLOOD_LAMP);
        blockItem(ModBlocks.SIGN_POST);
        blockItem(ModBlocks.TRAFFIC_CONE);
        blockItem(ModBlocks.CAUTION_BLOCK_SLAB);
        blockItem(ModBlocks.CAUTION_BLOCK_STAIRS);

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

        box(ModBlocks.SMALL_BOX.get());
        box(ModBlocks.MEDIUM_BOX.get());
//        simpleHorizontalModelBlock(ModBlocks.BIG_BOX.get());

        telephone(ModBlocks.TELEPHONE.get());

        paintableRailing(ModBlocks.IRON_RAILING.get(), "iron_grate");
        paintableRailing(ModBlocks.COPPER_RAILING.get(), "copper_grate");
        paintableRailing(ModBlocks.GOLD_RAILING.get(), "gold_railing/base");

        fluorescentLight(ModBlocks.FLUORESCENT_LIGHT.get());

        horizontalBlockGen(ModBlocks.SEWING_MACHINE);
        horizontalBlockGen(ModBlocks.GUARANA_ANTARTICA);
        horizontalBlockGen(ModBlocks.DRINKING_FOUNTAIN);
        horizontalBlockGen(ModBlocks.CUP_DISPENSER);
        horizontalBlockGen(ModBlocks.STOP_SIGN);
        horizontalBlockGen(ModBlocks.INTERSECTION_SIGN);
        horizontalBlockGen(ModBlocks.CONCRETE_BARRIER);

        simpleBlock(ModBlocks.SIGN_POST.get(), models().getExistingFile(modLoc("block/sign_post")));
        simpleBlock(ModBlocks.TRAFFIC_CONE.get(), models().getExistingFile(modLoc("block/traffic_cone")));

        simpleModelWithRenderType(ModBlocks.PLASTIC_TABLE.get(), "cutout");

        ModBlocks.PAINTED_PLANKS.values().forEach(block -> simpleBlockWithItem(block.get(), cubeAll(block.get())));
        ModBlocks.PAINTED_PLANKS_STAIRS.forEach((color, block) -> {stairsBlock(block.get(), blockTexture(ModBlocks.PAINTED_PLANKS.get(color).get()));blockItem(block);});
        ModBlocks.PAINTED_PLANKS_SLABS.forEach((color, block) -> {slabBlock(block.get(), blockTexture(ModBlocks.PAINTED_PLANKS.get(color).get()), blockTexture(ModBlocks.PAINTED_PLANKS.get(color).get()));blockItem(block);});

        computer(ModBlocks.COMPUTER.get());

        doorBlockWithRenderType(ModBlocks.GRID_METAL_DOOR.get(), modLoc("block/grid_metal_door_bottom"), modLoc("block/grid_metal_door_top"), "cutout");
        doorBlockWithRenderType(ModBlocks.WHITE_METAL_DOOR.get(), modLoc("block/white_metal_door_bottom"), modLoc("block/white_metal_door_top"), "cutout");
        doorBlock(ModBlocks.YELLOW_METAL_DOOR.get(), modLoc("block/yellow_metal_door_bottom"), modLoc("block/yellow_metal_door_top"));
        fenceBlock(ModBlocks.CAUTION_BLOCK_FENCE.get(), blockTexture(ModBlocks.CAUTION_BLOCK.get()));
        wallBlock(ModBlocks.CAUTION_BLOCK_WALL.get(), blockTexture(ModBlocks.CAUTION_BLOCK.get()));

        blockItem(ModBlocks.CAUTION_BLOCK_FENCE);
        blockItem(ModBlocks.CAUTION_BLOCK_WALL);

        horizontalBlockGen(ModBlocks.FLOODLIGHT);
        keycard_reader(ModBlocks.KEYCARD_READER.get());

        horizontalFaceBlock(ModBlocks.DOUBLE_IRON_PIPES.get(), models().getExistingFile(modLoc("block/double_iron_pipes")));
        horizontalFaceBlock(ModBlocks.FLOOD_LAMP.get(), models().getExistingFile(modLoc("block/flood_lamp")));

        blockWithItem(ModBlocks.GREEN_DIAMOND_WALLPAPER);
        blockWithItem(ModBlocks.BLUE_RUG);

        horizontalBlockGen(ModBlocks.WALL_LAMP);
        blockItem(ModBlocks.WALL_LAMP);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("iden_decor:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItemWithGuiLight(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("iden_decor:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("iden_decor:block/" + deferredBlock.getId().getPath() + appendix));
    }

    public void simpleBlockWithRenderType(DeferredBlock<?> deferredBlock, String renderType) {
        simpleBlock(deferredBlock.get(), ((BlockModelBuilder) cubeAll(deferredBlock.get())).renderType(renderType));
    }

    private void horizontalBlockGen(DeferredBlock<Block> deferredBlock){

        Block block = deferredBlock.get();
        ModelFile model = models().getExistingFile(modLoc("block/"+getPath(block)));

        blockItem(deferredBlock);
        horizontalBlock(block, model);
    }

    private void simpleModelWithRenderType(Block block, String renderType) {
        simpleBlockItem(block, models().getExistingFile(modLoc("block/base/" + getPath(block))));

        ResourceLocation parent = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/base/" + getPath(block));
        ResourceLocation baseTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/" + getPath(block));

        simpleBlock(block, models().withExistingParent(getPath(block), parent)
                .texture("0", baseTexture)
                .texture("particle", baseTexture)
                .renderType(renderType));
    }

    private void metal_barrel(Block block) {
        ResourceLocation sideTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/" + getPath(block) + "_side");
        ResourceLocation bottomTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/" + getPath(block) + "_bottom");
        ResourceLocation topTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/" + getPath(block) + "_top");

        ModelFile model = models().getBuilder(getPath(block))
                .parent(models().getExistingFile(mcLoc("block/barrel")))
                .texture("side", sideTexture)
                .texture("top", topTexture)
                .texture("bottom", bottomTexture)
                .texture("particle", bottomTexture);

        horizontalBlock(block, model);
        simpleBlockItem(block, model);
    }

    private void telephone(Block block) {
        ModelFile phoneModel = models().getExistingFile(modLoc("block/telephone"));
        ModelFile phonelessModel = models().getExistingFile(modLoc("block/telephone_phoneless"));

        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(TelephoneBlock.FACING);
            boolean phone = state.getValue(TelephoneBlock.PHONE);

            return ConfiguredModel.builder()
                    .modelFile(phone?phoneModel:phonelessModel)
                    .rotationY(((int) facing.toYRot() + 180) % 360)
                    .build();
        });

        simpleBlockItem(block, phonelessModel);
    }

    private void computer(Block block) {
        ModelFile computerModel = models().getExistingFile(modLoc("block/computer"));
        ModelFile computerDiskModel = models().getExistingFile(modLoc("block/computer_with_disk"));

        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(ComputerBlock.FACING);
            boolean has_disk = state.getValue(ComputerBlock.HAS_DISK);

            return ConfiguredModel.builder()
                    .modelFile(has_disk?computerDiskModel:computerModel)
                    .rotationY(((int) facing.toYRot() + 180) % 360)
                    .build();
        });

        simpleBlockItem(block, computerModel);
    }

    private void keycard_reader(Block block) {
        ModelFile readerModel = models().getExistingFile(modLoc("block/keycard_reader"));
        ModelFile readerModelActivated = models().getExistingFile(modLoc("block/keycard_reader_activated"));

        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(KeycardReaderBlock.FACING);
            boolean activated = state.getValue(KeycardReaderBlock.ACTIVATED);

            return ConfiguredModel.builder()
                    .modelFile(activated?readerModelActivated:readerModel)
                    .rotationY(((int) facing.toYRot() + 180) % 360)
                    .build();
        });

        simpleBlockItem(block, readerModel);
    }

    private void plushie(Block block) {

        ResourceLocation parent = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/base/plushie");
        ResourceLocation baseTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/plushie/" + getPath(block).split("_")[1]);

        ModelFile model = models().getBuilder(getPath(block))
                .parent(models().getExistingFile(parent))
                .texture("0", baseTexture)
                .texture("particle", baseTexture)
                .renderType("cutout");

       horizontalBlock(block, model);
       simpleBlockItem(block, model);
    }

    private void paintableRailing(Block block, String particleTexture) {

        Map<String, ModelFile> modelsByPartAndColor = new HashMap<>();

        for (ThreeConnectableProperty part: ThreeConnectableProperty.values()) {
            String partName = part.name().toLowerCase();

            for (ColorProperty color: ColorProperty.values()) {
                String key = partName + "_" + color.getSerializedName();

                modelsByPartAndColor.put(key,
                        models().getBuilder(getPath(block) + "_" + key)
                                .parent(models().getExistingFile(modLoc("block/railing/" + partName)))
                                .renderType("cutout")
                                .texture("0", modLoc("block/" + getPath(block) + "/base"))
                                .texture("1", modLoc("block/" + getPath(block) + "/colors/") + color.getSerializedName())
                                .texture("particle", modLoc("block/" + getPath(block) + "/base"))
                );
            }
        }

        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction facing = state.getValue(RailingBlock.FACING);
                    ThreeConnectableProperty part = state.getValue(RailingBlock.PART);
                    ColorProperty color = state.getValue(RailingBlock.COLOR);

                    String key = part.name().toLowerCase() + "_" + color.getSerializedName();

                    return ConfiguredModel.builder()
                            .modelFile(modelsByPartAndColor.get(key))
                            .rotationY(((int) facing.toYRot() + 180) % 360)
                            .build();
                });
        simpleBlockItem(block, modelsByPartAndColor.get("single_base"));
    }

    private void box(Block block) {
        ModelFile closedModel = models().getExistingFile(modLoc("block/" + getPath(block) + "_closed"));
        ModelFile openedModel = models().getExistingFile(modLoc("block/" + getPath(block) + "_opened"));

        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(BoxBlock.FACING);
            Boolean opened = state.getValue(BoxBlock.OPENED);

            return ConfiguredModel.builder()
                    .modelFile(opened?openedModel:closedModel)
                    .rotationY(((int) facing.toYRot() + 180) % 360)
                    .build();
        });

        simpleBlockItem(block, closedModel);
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

        Map<Boolean, Map<ColorProperty, ModelFile>> models = new HashMap<>();

        for (boolean powered: new boolean[]{false, true}) {
            Map<ColorProperty, ModelFile> byColor = new HashMap<>();

            for (ColorProperty color: ColorProperty.values()) {
                String suffix = (powered?"on":"off") + "_" + color.getSerializedName();

                byColor.put(color,
                        models().getBuilder(getPath(block) + "_" + suffix)
                                .parent(models().getExistingFile(modLoc("block/heavy_lever/single" + (powered?"_pressed":""))))
                                .texture("0", modLoc("block/heavy_lever/base"))
                                .texture("1", modLoc("block/heavy_lever/colors/") + color.getSerializedName())
                                .texture("particle", modLoc("block/heavy_lever/base"))
                );
            }

            models.put(powered, byColor);
        }

        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction facing = state.getValue(HeavyLeverBlock.FACING);
                    AttachFace face = state.getValue(HeavyLeverBlock.FACE);
                    Boolean powered = state.getValue(HeavyLeverBlock.POWERED);
                    ColorProperty color = state.getValue(HeavyLeverBlock.COLOR);

                    int rotX = switch (face) {
                        case FLOOR -> 0;
                        case WALL -> 90;
                        case CEILING -> 180;
                    };

                    return ConfiguredModel.builder()
                            .modelFile(models.get(powered).get(color))
                            .rotationX(rotX)
                            .rotationY(((int) facing.toYRot() + 180) % 360)
                            .build();

                });
        simpleBlockItem(block, models.get(false).get(ColorProperty.BASE));
    }

    private static String getPath(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getPath();
    }

}