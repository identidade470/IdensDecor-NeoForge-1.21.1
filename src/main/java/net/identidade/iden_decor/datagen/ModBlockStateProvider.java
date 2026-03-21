package net.identidade.iden_decor.datagen;

import net.identidade.iden_decor.IdenDecorMod;
import net.identidade.iden_decor.block.ModBlocks;
import net.identidade.iden_decor.block.custom.*;
import net.identidade.iden_decor.block.properties.ColorProperty;
import net.identidade.iden_decor.block.properties.HorizontalThreeConnectableProperty;
import net.identidade.iden_decor.block.properties.VerticalThreeConnectableProperty;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.WallSide;
import net.neoforged.neoforge.client.model.generators.*;
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
        blockItem(ModBlocks.CEILING_LAMP);

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
        ModBlocks.FRAMED_PLANKS.values().forEach(block -> connectedBlockWithItem(block.get()));

        computer(ModBlocks.COMPUTER.get());

        doorBlockWithRenderType(ModBlocks.GRID_METAL_DOOR.get(), modLoc("block/grid_metal_door_bottom"), modLoc("block/grid_metal_door_top"), "cutout");
        doorBlockWithRenderType(ModBlocks.WHITE_METAL_DOOR.get(), modLoc("block/white_metal_door_bottom"), modLoc("block/white_metal_door_top"), "cutout");
        doorBlock(ModBlocks.WHITE_WOODEN_PANEL_DOOR.get(), modLoc("block/white_wooden_panel_door_bottom"), modLoc("block/white_wooden_panel_door_top"));
        doorBlock(ModBlocks.WOODEN_PANEL_DOOR.get(), modLoc("block/wooden_panel_door_bottom"), modLoc("block/wooden_panel_door_top"));
        doorBlock(ModBlocks.YELLOW_METAL_DOOR.get(), modLoc("block/yellow_metal_door_bottom"), modLoc("block/yellow_metal_door_top"));
        fenceBlock(ModBlocks.CAUTION_BLOCK_FENCE.get(), blockTexture(ModBlocks.CAUTION_BLOCK.get()));
        wallBlock(ModBlocks.CAUTION_BLOCK_WALL.get(), blockTexture(ModBlocks.CAUTION_BLOCK.get()));

        blockWithItem(ModBlocks.PUZZLE_WOOL);
        carpetBlock(ModBlocks.PUZZLE_CARPET, "puzzle_wool");

        blockItem(ModBlocks.CAUTION_BLOCK_FENCE);
        blockItem(ModBlocks.CAUTION_BLOCK_WALL);

        horizontalBlockGen(ModBlocks.FLOODLIGHT);
        keycard_reader(ModBlocks.KEYCARD_READER.get());

        horizontalFaceBlock(ModBlocks.DOUBLE_IRON_PIPES.get(), models().getExistingFile(modLoc("block/double_iron_pipes")));
        horizontalFaceBlock(ModBlocks.FLOOD_LAMP.get(), models().getExistingFile(modLoc("block/flood_lamp")));

        connectedBlockWithItem(ModBlocks.GREEN_DIAMOND_WALLPAPER.get());
        connectedBlockWithItem(ModBlocks.RED_DIAMOND_WALLPAPER.get());
        connectedBlockWithItem(ModBlocks.YELLOW_ARROW_WALLPAPER.get());
        connectedBlockWithItem(ModBlocks.BLUE_CLOUDS_WALLPAPER.get());
        connectedBlockWithItem(ModBlocks.BLACK_CLOUDS_WALLPAPER.get());
        connectedBlockWithItem(ModBlocks.FLUORESCENT_LIGHT_BLOCK.get());
        connectedBlockWithItem(ModBlocks.BLACK_STARRY_WALLPAPER.get());
        connectedBlockWithItem(ModBlocks.RED_GOLDEN_CARPET_BLOCK.get());
        connectedBlockWithItem(ModBlocks.RED_GOLDEN_CARPET.get());
        connectedBlockWithItem(ModBlocks.GREEN_ARROW_CARPET_BLOCK.get());
        connectedBlockWithItem(ModBlocks.GREEN_ARROW_CARPET.get());

        horizontalBlockGen(ModBlocks.ROOF);
        window(ModBlocks.WHITE_PANEL_WINDOW.get(), "panel_window");
        window(ModBlocks.WHITE_HALF_WINDOW.get(), "half_window");
        window(ModBlocks.WHITE_LATTICE_WINDOW.get(), "lattice_window");


        fence_railing(ModBlocks.WHITE_WOOD_RAILING.get());

        simpleBlock(ModBlocks.CEILING_LAMP.get(), models().getExistingFile(modLoc("block/ceiling_lamp")));

        shelfBlock(ModBlocks.OAK_METAL_SHELF.get(), "smooth_oak_planks");
        shelfBlock(ModBlocks.SPRUCE_METAL_SHELF.get(), "smooth_spruce_planks");
        shelfBlock(ModBlocks.ACACIA_METAL_SHELF.get(), "smooth_acacia_planks");
        shelfBlock(ModBlocks.BIRCH_METAL_SHELF.get(), "smooth_birch_planks");
        shelfBlock(ModBlocks.DARK_OAK_METAL_SHELF.get(), "smooth_dark_oak_planks");
        shelfBlock(ModBlocks.JUNGLE_METAL_SHELF.get(), "smooth_jungle_planks");
        shelfBlock(ModBlocks.CHERRY_METAL_SHELF.get(), "smooth_cherry_planks");
        shelfBlock(ModBlocks.WARPED_METAL_SHELF.get(), "smooth_warped_planks");
        shelfBlock(ModBlocks.CRIMSON_METAL_SHELF.get(), "smooth_crimson_planks");
        shelfBlock(ModBlocks.BAMBOO_METAL_SHELF.get(), "smooth_bamboo_planks");
        shelfBlock(ModBlocks.MANGROVE_METAL_SHELF.get(), "smooth_mangrove_planks");
        shelfBlock(ModBlocks.METAL_SHELF.get(), "steel_grate");

        horizontalBlockGen(ModBlocks.WALL_LAMP);
        horizontalBlockGen(ModBlocks.WALL_CLOCK);

        horizontalBlockWithExistingParent(ModBlocks.CALENDAR);
        horizontalBlockWithExistingParent(ModBlocks.HANGING_MOON_LIGHT);
        horizontalBlockWithExistingParent(ModBlocks.HANGING_CLOUD);
        horizontalBlockWithExistingParent(ModBlocks.WHITE_CUBIC_SHELF, "cubic_shelf");

        blockWithExistingParent(ModBlocks.LONG_CONCRETE_VASE);

        axisBlock(ModBlocks.CARVED_SPRUCE_PLANKS.get(), modLoc("block/carved_spruce_planks"), mcLoc("block/spruce_planks"));

        blockItem(ModBlocks.LONG_CONCRETE_VASE);
        blockItem(ModBlocks.CARVED_SPRUCE_PLANKS);

        blockWithItem(ModBlocks.BLUE_RUG);

        curtainBlock(ModBlocks.RED_CURTAIN.get());

    }

    private void connectedBlockWithItem(Block block) {
        simpleBlock(block, new ModelFile.UncheckedModelFile(modLoc("block/" + getPath(block))));
        simpleBlockItem(block, new ModelFile.UncheckedModelFile(modLoc("block/" + getPath(block))));
    }

//    private void connectedCarpetWithItem(Block block) {
//        simpleBlock(block, new Mode);
//    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }


    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("iden_decor:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("iden_decor:block/" + deferredBlock.getId().getPath() + appendix));
    }


    private void blockWithExistingParent(DeferredBlock<?> deferredBlock) {
        simpleBlock(deferredBlock.get(), models().getExistingFile(modLoc("block/" + getPath(deferredBlock.get()))));
    }

    private void horizontalBlockWithExistingParent(DeferredBlock<?> deferredBlock) {
        horizontalBlock(deferredBlock.get(), models().getExistingFile(modLoc("block/" + getPath(deferredBlock.get()))));
    }

    private void horizontalBlockWithExistingParent(DeferredBlock<?> deferredBlock, String appendix) {
        horizontalBlock(deferredBlock.get(), models().getExistingFile(modLoc("block/" + appendix)));
    }

    public void simpleBlockWithRenderType(DeferredBlock<?> deferredBlock, String renderType) {
        simpleBlock(deferredBlock.get(), ((BlockModelBuilder) cubeAll(deferredBlock.get())).renderType(renderType));
    }

    private void horizontalBlockGen(DeferredBlock<?> deferredBlock){

        Block block = deferredBlock.get();
        ModelFile model = models().getExistingFile(modLoc("block/"+getPath(block)));

        blockItem(deferredBlock);
        horizontalBlock(block, model);
    }

    private void carpetBlock(DeferredBlock<CarpetBlock> block, String texture) {
        simpleBlock(block.get(), models().withExistingParent(getPath(block.get()), mcLoc("block/carpet"))
                .texture("wool", modLoc("block/"+texture)));
        blockItem(block);
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

    private void curtainBlock(Block block) {
        ResourceLocation baseTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/" + getPath(block));

        ModelFile closedModel = models().getExistingFile(ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/base/curtain"));
        ModelFile openedModel = models().getExistingFile(ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/base/curtain_opened"));

        getVariantBuilder(block).forAllStates(state -> {
           Direction facing = state.getValue(CurtainBlock.FACING);
            Boolean opened = state.getValue(CurtainBlock.OPENED);

            return ConfiguredModel.builder()
                    .modelFile(models().getBuilder(getPath(block) + (opened?"_opened":""))
                            .parent(opened?openedModel:closedModel)
                            .texture("0", baseTexture))
                    .rotationY((int)(facing.toYRot() + 180) % 360)
                    .build();
        });

        simpleBlockItem(block, new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/" + getPath(block))));
    }

    private void shelfBlock(Block block, String baseTexture) {
        ResourceLocation woodTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/"+baseTexture);
        ResourceLocation metalTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/metal_shelf");

        ModelFile leftModel = models().getExistingFile(ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/metal_shelf/left"));

        ModelFile rightModel = models().getExistingFile(ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/metal_shelf/right"));

        ModelFile singleModel = models().getExistingFile(ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/metal_shelf/single"));

        ModelFile centerModel = models().getExistingFile(ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/metal_shelf/center"));

        getVariantBuilder(block).forAllStates(state -> {

            Direction facing = state.getValue(ShelfBlock.FACING);
            HorizontalThreeConnectableProperty part = state.getValue(ShelfBlock.PART);

            return ConfiguredModel.builder()
                    .modelFile(models().getBuilder(getPath(block) + "_" + part.getSerializedName())
                            .parent(switch (part) {
                                case LEFT -> leftModel;
                                case RIGHT -> rightModel;
                                case CENTER -> centerModel;
                                default -> singleModel;
                            })
                            .renderType("cutout")
                            .texture("0", metalTexture)
                            .texture("1", woodTexture)
                            .texture("particle", woodTexture))
                    .rotationY(((int) facing.toYRot() + 180) % 360)
                    .build();
        });

        simpleBlockItem(block, models().getExistingFile(modLoc("block/" + getPath(block) + "_single")));
    }

    private void fence_railing(WallBlock block) {
        ResourceLocation baseTexture = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/" + getPath(block));
        ModelFile sideModel = models().withExistingParent(getPath(block) + "_side", ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/fence_railing/side"))
                .texture("0", baseTexture)
                .renderType("cutout");
        ModelFile postModel = models().withExistingParent(getPath(block) + "_post", ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/fence_railing/post"))
                .texture("0", baseTexture);
        ModelFile centerModel = models().withExistingParent(getPath(block) + "_center", ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "block/fence_railing/center"))
                .texture("0", baseTexture)
                .renderType("cutout");

        MultiPartBlockStateBuilder builder = getMultipartBuilder(block);
        builder.part().modelFile(postModel).addModel().condition(WallBlock.UP, true).end();
        WALL_PROPS.entrySet().stream().filter((e) -> e.getKey().getAxis().isHorizontal()).forEach(e -> {
            builder.part().modelFile(sideModel).rotationY((int) (e.getKey().toYRot() + 180) % 360).addModel().condition(e.getValue(), WallSide.LOW);
            builder.part().modelFile(sideModel).rotationY((int) (e.getKey().toYRot() + 180) % 360).addModel().condition(e.getValue(), WallSide.TALL);
        });

//        wallBlock(block, postModel, sideModel, centerModel);
//        simpleBlockItem(block, models().getExistingFile(modLoc("block/"+getPath(block)+"_center")));
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


    private void window(Block block, String folder) {
        ModelFile singlePart = models().getExistingFile(modLoc("block/"+folder+"/single"));
        ModelFile upperPart = models().getExistingFile(modLoc("block/"+folder+"/upper"));
        ModelFile lowerPart = models().getExistingFile(modLoc("block/"+folder+"/lower"));
        ModelFile centerPart = models().getExistingFile(modLoc("block/"+folder+"/center"));

        getVariantBuilder(block).forAllStates(state -> {
            VerticalThreeConnectableProperty part = state.getValue(WindowBlock.PART);
            Direction facing = state.getValue(WindowBlock.FACING);

            String partName = part.name().toLowerCase();

            return ConfiguredModel.builder()
                    .modelFile(models().getBuilder(getPath(block) + "_" + partName)
                            .parent(switch (part) {
                                case UPPER -> upperPart;
                                case LOWER -> lowerPart;
                                case CENTER -> centerPart;
                                default -> singlePart;
                            })
                            .renderType("cutout")
                            .texture("0", modLoc("block/" + getPath(block)))
                            .texture("particle", modLoc("block/" + getPath(block))))
                    .rotationY(((int) facing.toYRot() + 180) % 360)
                    .build();
        });

        simpleBlockItem(block, new ModelFile.UncheckedModelFile(modLoc("block/" + getPath(block) + "_single")));
    }

    private void paintableRailing(Block block, String particleTexture) {

        Map<String, ModelFile> modelsByPartAndColor = new HashMap<>();

        for (HorizontalThreeConnectableProperty part: HorizontalThreeConnectableProperty.values()) {
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
                    HorizontalThreeConnectableProperty part = state.getValue(RailingBlock.PART);
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
                    HorizontalThreeConnectableProperty part = state.getValue(FluorescentLightBlock.PART);

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