package net.identidade.iden_decor.item.custom;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;

public class PaintBrushItem extends Item {

    private final DyeColor color;

    public PaintBrushItem(DyeColor color) {
        super(new Item.Properties().stacksTo(1));
        this.color = color;
    }
}
