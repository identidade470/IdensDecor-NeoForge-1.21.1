package net.identidade.iden_decor.block.properties;

import net.minecraft.util.StringRepresentable;

import java.util.Locale;

public enum TwoConnectableProperty implements StringRepresentable {
    SINGLE,
    CENTER,
    SIDE;
    ;

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
