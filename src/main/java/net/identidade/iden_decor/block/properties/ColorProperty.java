package net.identidade.iden_decor.block.properties;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum ColorProperty implements StringRepresentable {
    WHITE,
    LIGHT_GRAY,
    GRAY,
    BLACK,
    BROWN,
    RED,
    ORANGE,
    YELLOW,
    LIME,
    GREEN,
    CYAN,
    LIGHT_BLUE,
    BLUE,
    PURPLE,
    MAGENTA,
    PINK,
    BASE,
    ;

    @Override
    public @NotNull String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static ColorProperty fromDye(Item dye) {
        return ColorProperty.valueOf(BuiltInRegistries.ITEM.getKey(dye).getPath().replace("_dye", "").toUpperCase(Locale.ROOT));
    }
}
