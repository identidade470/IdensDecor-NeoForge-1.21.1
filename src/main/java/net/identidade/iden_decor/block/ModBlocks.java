package net.identidade.iden_decor.block;

import net.identidade.iden_decor.IdenDecorMod;
import net.identidade.iden_decor.block.custom.*;
import net.identidade.iden_decor.block.custom.RefrigeranteBlock;
import net.identidade.iden_decor.block.custom.templates.SimpleHorizontalBlock;
import net.identidade.iden_decor.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(IdenDecorMod.MOD_ID);

    // Grates
    public static final DeferredBlock<Block> IRON_GRATE = registerBlock("iron_grate",
            () -> new WaterloggedTransparentBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.COPPER_GRATE).mapColor(MapColor.COLOR_GRAY).requiresCorrectToolForDrops().noOcclusion().isViewBlocking((state, level, pos) -> false).isSuffocating((state, level, pos) -> false)));
    public static final DeferredBlock<Block> STEEL_GRATE = registerBlock("steel_grate",
            () -> new WaterloggedTransparentBlock(BlockBehaviour.Properties.of().strength(3.0F, 6.0F).sound(SoundType.COPPER_GRATE).mapColor(MapColor.COLOR_GRAY).requiresCorrectToolForDrops().noOcclusion().isViewBlocking((state, level, pos) -> false).isSuffocating((state, level, pos) -> false)));

    // Tiles
    public static final DeferredBlock<Block> WHITE_BRICKS = registerBlock("white_bricks",
            () -> new Block(BlockBehaviour.Properties.of().strength(2.0F, 6.0F)));
    public static final DeferredBlock<SlabBlock> WHITE_BRICKS_SLAB = registerBlock("white_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F)));
    public static final DeferredBlock<StairBlock> WHITE_BRICKS_STAIRS = registerBlock("white_brick_stairs",
            () -> new StairBlock(ModBlocks.WHITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(2.0F, 6.0F)));

    public static final DeferredBlock<Block> WHITE_TILES = registerBlock("white_tiles",
            () -> new Block(BlockBehaviour.Properties.of().strength(2.0F, 6.0F)));
    public static final DeferredBlock<SlabBlock> WHITE_TILES_SLAB = registerBlock("white_tiles_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F)));
    public static final DeferredBlock<StairBlock> WHITE_TILES_STAIRS = registerBlock("white_tiles_stairs",
            () -> new StairBlock(ModBlocks.WHITE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(2.0F, 6.0F)));

    public static final DeferredBlock<Block> SMOOTH_STONE_BRICKS = registerBlock("smooth_stone_bricks",
            () -> new Block(BlockBehaviour.Properties.of().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> SMOOTH_STONE_TILES = registerBlock("smooth_stone_tiles",
            () -> new Block(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<SlabBlock> SMOOTH_STONE_TILES_SLAB = registerBlock("smooth_stone_tiles_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<StairBlock> SMOOTH_STONE_TILES_STAIRS = registerBlock("smooth_stone_tiles_stairs",
            () -> new StairBlock(ModBlocks.SMOOTH_STONE_TILES.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(2.0F, 6.0F).sound(SoundType.STONE)));

    // Water Dispensers
    public static final DeferredBlock<Block> DARK_WATER_DISPENSER = registerBlock("dark_water_dispenser",
            () -> new WaterDispenserBlock(BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.COPPER)));
    public static final DeferredBlock<Block> LIGHT_WATER_DISPENSER = registerBlock("light_water_dispenser",
            () -> new WaterDispenserBlock(BlockBehaviour.Properties.of().strength(1.0F).sound(SoundType.COPPER)));

    // Posters
    public static final DeferredBlock<Block> CCS_POSTER = registerBlock("ccs_poster",
            () -> new PosterBlock(BlockBehaviour.Properties.of().sound(SoundType.MOSS).strength(0.5F)));

    public static final DeferredBlock<Block> WATER_POSTER = registerBlock("water_poster",
            () -> new PosterBlock(BlockBehaviour.Properties.of().sound(SoundType.MOSS).strength(0.5F)));

    // Buttons
    public  static final DeferredBlock<Block> HEAVY_BUTTON = registerBlock("heavy_button",
            () -> new HeavyButtonBlock(BlockSetType.GOLD, 20, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noCollission().strength(0.5F).noOcclusion()));

    public  static final DeferredBlock<Block> GATE_BUTTON = registerBlock("gate_button",
            () -> new GateButtonBlock(BlockSetType.GOLD, 20, BlockBehaviour.Properties.of().pushReaction(PushReaction.DESTROY).noCollission().strength(0.5F).noOcclusion()));

    // Levers
    public static final DeferredBlock<Block> HEAVY_LEVER = registerBlock("heavy_lever",
            () -> new HeavyLeverBlock(BlockBehaviour.Properties.of().strength(0.5F).noCollission()));

    public static final DeferredBlock<Block> EMERGENCY_LEVER = registerBlock("emergency_lever",
            () -> new LeverBlock(BlockBehaviour.Properties.of().noCollission().strength(0.5F)));

    public static final DeferredBlock<Block> LIGHT_SWITCH = registerBlock("light_switch",
            () -> new LeverBlock(BlockBehaviour.Properties.of().strength(0.5F).noCollission()));

    public static final DeferredBlock<Block> POWER_SWITCH = registerBlock("power_switch",
            () -> new LeverBlock(BlockBehaviour.Properties.of().strength(0.5F).noCollission()));

    public static final DeferredBlock<Block> VALVE_SWITCH = registerBlock("valve_switch",
            () -> new LeverBlock(BlockBehaviour.Properties.of().strength(0.5F).noCollission()));

    // Railings
    public static final DeferredBlock<Block> IRON_RAILING = registerBlock("iron_railing",
            () -> new RailingBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.CHAIN).noOcclusion()));

    public static final DeferredBlock<Block> COPPER_RAILING = registerBlock("copper_railing",
            () -> new RailingBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.COPPER).noOcclusion()));

    public static final DeferredBlock<Block> GOLD_RAILING = registerBlock("gold_railing",
            () -> new RailingBlock(BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.CHAIN).noOcclusion()));

    public static final DeferredBlock<Block> GUARANA_ANTARTICA = registerBlock("guarana_antartica",
            () -> new RefrigeranteBlock(BlockBehaviour.Properties.of()));

    // Barrels
    public static final DeferredBlock<Block> BLUE_METAL_BARREL = registerBlock("blue_metal_barrel",
            () -> new MetalBarrelBlock(BlockBehaviour.Properties.of().sound(SoundType.COPPER).strength(3.0F)));
    public static final DeferredBlock<Block> METAL_BARREL = registerBlock("metal_barrel",
            () -> new MetalBarrelBlock(BlockBehaviour.Properties.of().sound(SoundType.COPPER).strength(3.0F)));

    // Plushies
    public static final DeferredBlock<Block> PLUSHIE_IDEN = registerBlock("plushie_iden",
            () -> new PlushieBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(1.0F)));
    public static final DeferredBlock<Block> PLUSHIE_DOLI = registerBlock("plushie_doli",
            () -> new PlushieBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(1.0F)));
    public static final DeferredBlock<Block> PLUSHIE_DINO = registerBlock("plushie_dino",
            () -> new PlushieBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(1.0F)));
    public static final DeferredBlock<Block> PLUSHIE_RED = registerBlock("plushie_red",
            () -> new PlushieBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(1.0F)));
    public static final DeferredBlock<Block> PLUSHIE_RAFA = registerBlock("plushie_rafa",
            () -> new PlushieBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(1.0F)));

    // Sheet Metals
    public static final DeferredBlock<Block> IRON_SHEET_METAL = registerBlock("iron_sheet_metal",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.COPPER).strength(5.0F,6.0F)));

    // Caixas
    public static final DeferredBlock<Block> SMALL_BOX = registerBlock("small_box",
            () -> new BoxBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion().strength(1.0F), Block.box(5, 0, 4, 11, 5, 12), Block.box(4, 0, 5, 12, 5, 11)));
    public static final DeferredBlock<Block> MEDIUM_BOX = registerBlock("medium_box",
            () -> new BoxBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion().strength(1.0F), Block.box(3, 0, 2, 13, 8, 14), Block.box(2, 0, 3, 14, 8, 13)));
//    public static final DeferredBlock<Block> BIG_BOX = registerBlock("big_box",
//            () -> new BoxBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOD).noOcclusion().strength(1.0F), Block.box(0,0,0,16,16,16), Block.box(0,0,0,16,16,16)));

    // Air Vent
    public static final DeferredBlock<TrapDoorBlock> AIR_VENT = registerBlock("air_vent",
            () -> new TrapDoorBlock(BlockSetType.COPPER, BlockBehaviour.Properties.of().noOcclusion()));
    public static final DeferredBlock<Block> AIR_DUCT = registerBlock("air_duct",
            () -> new Block(BlockBehaviour.Properties.of().sound(SoundType.COPPER).strength(3.0F, 2.0F)));

    // Lights
    public static final DeferredBlock<Block> FLUORESCENT_LIGHT = registerBlock("fluorescent_light",
            () -> new FluorescentLightBlock(BlockBehaviour.Properties.of().strength(2.0f).lightLevel(state -> 14)));

    // Tables
    public static final DeferredBlock<Block> PLASTIC_TABLE = registerBlock("plastic_table",
            () -> new TableBlock(BlockBehaviour.Properties.of().sound(SoundType.BAMBOO_WOOD).strength(1.5F)));

    // Couches
    public static final DeferredBlock<Block> WHITE_COUCH = registerBlock("white_couch",
            () -> new CouchBlock(BlockBehaviour.Properties.of().sound(SoundType.WOOL).strength(0.8F).noOcclusion()));

    // Other Furniture
    public static final DeferredBlock<Block> TELEPHONE = registerBlock("telephone",
            () -> new TelephoneBlock(BlockBehaviour.Properties.of().strength(2.0f).noOcclusion()));

    public static final DeferredBlock<Block> SEWING_MACHINE = registerBlock("sewing_machine",
            () -> new SewingMachineBlock(BlockBehaviour.Properties.of().strength(1.5f)));

    public static final DeferredBlock<Block> COMPUTER = registerBlock("computer",
            () -> new ComputerBlock(BlockBehaviour.Properties.of().strength(3.0f)));

    public static final DeferredBlock<DoorBlock> GRID_METAL_DOOR = registerBlock("grid_metal_door",
            () -> new DoorBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(5.0f).noOcclusion()));

    public static final DeferredBlock<DoorBlock> YELLOW_METAL_DOOR = registerBlock("yellow_metal_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(5.0f)));

    public static final DeferredBlock<DoorBlock> WHITE_METAL_DOOR = registerBlock("white_metal_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(5.0f).noOcclusion()));

    public static final DeferredBlock<Block> FLOODLIGHT = registerBlock("floodlight",
            () -> new FloodlightBlock(BlockBehaviour.Properties.of().noOcclusion().strength(1.0f).lightLevel(state -> 14)));

    public static final DeferredBlock<Block> FLOOD_LAMP = registerBlock("flood_lamp",
            () -> new FloodLampBlock(BlockBehaviour.Properties.of().noOcclusion().strength(1.0f).lightLevel(state -> 14)));

    public static final DeferredBlock<Block> KEYCARD_READER = registerBlock("keycard_reader",
            () -> new KeycardReaderBlock(BlockBehaviour.Properties.of().strength(5.0f)));

    public static final DeferredBlock<Block> DOUBLE_IRON_PIPES = registerBlock("double_iron_pipes",
            () -> new DoublePipeBlock(BlockBehaviour.Properties.of().strength(2.0f).noOcclusion().sound(SoundType.METAL)));

    public static final DeferredBlock<Block> DRINKING_FOUNTAIN = registerBlock("drinking_fountain",
            () -> new DrinkingFountainBlock(BlockBehaviour.Properties.of().strength(2.0f).noOcclusion().sound(SoundType.METAL)));

    public static final DeferredBlock<Block> CUP_DISPENSER = registerBlock("cup_dispenser",
            () -> new CupDispenserBlock(BlockBehaviour.Properties.of().strength(2.0f).noOcclusion()));

    public static final DeferredBlock<Block> SIGN_POST = registerBlock("sign_post",
            () -> new SignPostBlock(BlockBehaviour.Properties.of().strength(1.0f).sound(SoundType.METAL)));

    public static final DeferredBlock<Block> STOP_SIGN = registerBlock("stop_sign",
            () -> new RoadSignBlock(BlockBehaviour.Properties.of().strength(1.0f).sound(SoundType.METAL)));

    public static final DeferredBlock<Block> TRAFFIC_CONE = registerBlock("traffic_cone",
            () -> new TrafficConeBlock(BlockBehaviour.Properties.of().strength(1.0f).sound(SoundType.BAMBOO)));

    public static final DeferredBlock<Block> CONCRETE_BARRIER = registerBlock("concrete_barrier",
            () -> new ConcreteBarrierBlock(BlockBehaviour.Properties.of().strength(3.0f).sound(SoundType.STONE).noOcclusion()));

    public static final DeferredBlock<Block> CAUTION_BLOCK = registerBlock("caution_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(3.0f).sound(SoundType.COPPER)));
    public static final DeferredBlock<SlabBlock> CAUTION_BLOCK_SLAB = registerBlock("caution_block_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(3.0f).sound(SoundType.COPPER)));
    public static final DeferredBlock<StairBlock> CAUTION_BLOCK_STAIRS = registerBlock("caution_block_stairs",
            () -> new StairBlock(CAUTION_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(3.0f).sound(SoundType.COPPER)));

    // Planks
    public static final Map<DyeColor, Supplier<Block>> PAINTED_PLANKS = new HashMap<>();
    public static final Map<DyeColor, DeferredBlock<StairBlock>> PAINTED_PLANKS_STAIRS = new HashMap<>();
    public static final Map<DyeColor, DeferredBlock<SlabBlock>> PAINTED_PLANKS_SLABS = new HashMap<>();
    static {
        for (DyeColor color:DyeColor.values()) {
            PAINTED_PLANKS.put(color, registerBlock(
                    color.getSerializedName() + "_painted_planks",
                    () -> new Block(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD))
            ));
            PAINTED_PLANKS_STAIRS.put(color, registerBlock(
                    color.getSerializedName() + "_painted_planks_stairs",
                    () -> new StairBlock(PAINTED_PLANKS.get(color).get().defaultBlockState(), BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD))
            ));
            PAINTED_PLANKS_SLABS.put(color, registerBlock(
                    color.getSerializedName() + "_painted_plank_slab",
                    () -> new SlabBlock(BlockBehaviour.Properties.of().strength(2.0F, 3.0F).sound(SoundType.WOOD))
            ));
        }
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
