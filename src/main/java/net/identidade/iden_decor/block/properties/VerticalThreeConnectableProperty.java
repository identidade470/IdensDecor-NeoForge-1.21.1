package net.identidade.iden_decor.block.properties;

import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public enum VerticalThreeConnectableProperty implements StringRepresentable {
    UPPER,
    CENTER,
    LOWER,
    SINGLE;
    ;

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
