package net.identidade.iden_decor.blockentity;

import net.identidade.iden_decor.IdenDecorMod;
import net.identidade.iden_decor.block.ModBlocks;
import net.identidade.iden_decor.block.custom.WallClockBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, IdenDecorMod.MOD_ID);

//    public static final Supplier<BlockEntityType<SewingMachineBlockEntity>> SEWING_MACHINE_BE =
//            BLOCK_ENTITIES.register("sewing_machine_be", () -> BlockEntityType.Builder.of(
//                    SewingMachineBlockEntity::new, ModBlocks.SEWING_MACHINE.get()).build(null));

    public static final Supplier<BlockEntityType<ComputerBlockEntity>> COMPUTER_BE =
            BLOCK_ENTITIES.register("computer_be", () -> BlockEntityType.Builder.of(
                    ComputerBlockEntity::new, ModBlocks.COMPUTER.get()).build(null));

    public static final Supplier<BlockEntityType<KeycardReaderBlockEntity>> KEYCARD_READER_BE =
            BLOCK_ENTITIES.register("keycard_reader_be", () -> BlockEntityType.Builder.of(
                    KeycardReaderBlockEntity::new, ModBlocks.KEYCARD_READER.get()).build(null));

    public static final Supplier<BlockEntityType<WallClockBlockEntity>> WALL_CLOCK_BE =
            BLOCK_ENTITIES.register("wall_clock_be", () -> BlockEntityType.Builder.of(
                    WallClockBlockEntity::new, ModBlocks.WALL_CLOCK.get()).build(null));

    public static final Supplier<BlockEntityType<VaseBlockEntity>> VASE_BE =
            BLOCK_ENTITIES.register("vase_be", () -> BlockEntityType.Builder.of(
                    VaseBlockEntity::new, ModBlocks.LONG_CONCRETE_VASE.get()).build(null));

    public static final Supplier<BlockEntityType<CubicShelfBlockEntity>> CUBIC_SHELF_BE =
            BLOCK_ENTITIES.register("cubic_shelf_be", () -> BlockEntityType.Builder.of(
                    CubicShelfBlockEntity::new, ModBlocks.WHITE_CUBIC_SHELF.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
