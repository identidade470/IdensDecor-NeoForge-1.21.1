package net.identidade.iden_decor.entity;

import net.identidade.iden_decor.IdenDecorMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, IdenDecorMod.MOD_ID);

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }

    public static final DeferredHolder<EntityType<?>, EntityType<Seat>> SEAT = ENTITIES.register("seat",
            () -> EntityType.Builder.<Seat>of(Seat::new, MobCategory.MISC)
                    .sized(0.1f, 0.1f)
                    .clientTrackingRange(8)
                    .build("seat")
    );
}
