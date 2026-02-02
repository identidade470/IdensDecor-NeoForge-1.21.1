package net.identidade.iden_decor.block.properties;

import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public enum ThreeConnectableProperty implements StringRepresentable {
    LEFT,
    CENTER,
    RIGHT,
    SINGLE;
    ;

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
