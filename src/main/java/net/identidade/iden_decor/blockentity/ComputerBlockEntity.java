package net.identidade.iden_decor.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class ComputerBlockEntity extends BlockEntity {

    public String displayText = "";
    private ItemStack disk = ItemStack.EMPTY;

    public ComputerBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.COMPUTER_BE.get(), pos, blockState);
    }

    public boolean hasDisk() {
        return !disk.isEmpty();
    }

    public ItemStack getDisk() {
        return disk;
    }

    public void setDisk(ItemStack stack) {
        this.disk = stack.copyWithCount(1);
        this.displayText = this.disk.getHoverName().getString();
        setChanged();
    }

    public ItemStack removeDisk() {
        ItemStack removed = disk;
        disk = ItemStack.EMPTY;
        displayText = "";
        setChanged();
        return removed;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putString("display_text", displayText);

        if (!disk.isEmpty()) {
            tag.put("disk", disk.save(registries));
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        displayText = tag.getString("display_text");

        if (tag.contains("disk")) {
            disk = ItemStack.parse(registries, tag.getCompound("disk")).orElse(ItemStack.EMPTY);
        } else {
            disk = ItemStack.EMPTY;
        }
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveCustomOnly(registries);
    }
}
