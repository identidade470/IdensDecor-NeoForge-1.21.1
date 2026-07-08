package net.identidade.iden_decor.block.properties;

import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public enum HorizontalConnectableProperty implements StringRepresentable {
    LEFT,
    CENTER,
    RIGHT,
    SINGLE,
    INNER_LEFT,
    INNER_RIGHT,
    OUTER_LEFT,
    OUTER_RIGHT;

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
