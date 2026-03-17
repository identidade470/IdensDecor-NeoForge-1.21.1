package net.identidade.iden_decor.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CubicShelfBlockEntity extends BlockEntity {

    private ItemStack placedStack;

    public CubicShelfBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.CUBIC_SHELF_BE.get(), pos, blockState);
        placedStack = ItemStack.EMPTY;
    }

    public void setItem(ItemStack stack) {
        placedStack = stack;
    }

    public ItemStack getItem() {
        return placedStack;
    }
}
