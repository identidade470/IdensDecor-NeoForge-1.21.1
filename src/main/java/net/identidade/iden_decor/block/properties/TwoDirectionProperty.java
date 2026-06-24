package net.identidade.iden_decor.block.properties;

import net.minecraft.util.StringRepresentable;

public enum TwoDirectionProperty implements StringRepresentable {
    FRONT,
    SIDE;

    @Override
    public String getSerializedName() {
        return name();
    }
}
