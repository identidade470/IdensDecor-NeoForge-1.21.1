package net.identidade.iden_decor.sound;

import net.identidade.iden_decor.IdenDecorMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, IdenDecorMod.MOD_ID);

    public static final Supplier<SoundEvent> BUTTON_USE = registerSoundEvent("button_use");
    public static final Supplier<SoundEvent> LEVER_USE = registerSoundEvent("lever_use");
    public static final Supplier<SoundEvent> PHONE_PICK = registerSoundEvent("phone_pick");
    public static final Supplier<SoundEvent> PHONE_SLAM = registerSoundEvent("phone_slam");
    public static final Supplier<SoundEvent> PHONE_RING = registerSoundEvent("phone_ring");
    public static final Supplier<SoundEvent> TOY_USE = registerSoundEvent("toy_use");

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
