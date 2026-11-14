package net.identidade.iden_decor;

import java.util.List;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public enum LightModeEnum {
        MANUAL(0),
        REDSTONE(1),
        REFURBISHED(2);

        private int value;

        LightModeEnum(int value) {
            this.value = value;
        }

        public int getKey(){
            return value;
        }
    }

    public static final ModConfigSpec.EnumValue<LightModeEnum> DEFAULT_LIGHT_MODE = BUILDER
            .comment("changing the mode to 'Refurbished' without it installed may cause issues")
            .defineEnum("default_light_mode", LightModeEnum.MANUAL);

    public static final ModConfigSpec.BooleanValue PLIERS_USABLE = BUILDER
            .comment("sets if the pliers can be used")
            .define("pliers_usable", true);

    static final ModConfigSpec SPEC = BUILDER.build();
}
