package net.identidade.iden_decor.blockentity;

import net.identidade.iden_decor.block.custom.templates.light.GenericLightBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class LightBlockEntity extends BlockEntity {

    private String[] availableModes = {"manual", "redstone"};
    private int activationMode = 0;

    public LightBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.LIGHT_BE.get(), pos, blockState);
    }

    public int getActivationMode() {return activationMode;}
    public String[] getAvailableModes() {return availableModes;}

    public void setActivationMode(int mode) {
        activationMode = mode;
        this.setChanged();
        this.onModeChanged(mode);
    }

    public void onModeChanged(int newMode) {
        Level level = getLevel();
        BlockState state = getBlockState();
        BlockPos pos = getBlockPos();
        Boolean powered = state.getValue(GenericLightBlock.POWERED);

        if (powered && newMode != 0 && !level.hasNeighborSignal(pos)) {
            level.setBlock(pos, state.setValue(GenericLightBlock.POWERED, false), 3);
        }

        if (!powered && newMode == 1 && level.hasNeighborSignal(pos)) {
            level.setBlock(pos, state.setValue(GenericLightBlock.POWERED, true), 3);
        }
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.saveWithoutMetadata(registries);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.putInt("activationMode", this.activationMode);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        this.activationMode = tag.getInt("activationMode");
        super.loadAdditional(tag, registries);
    }
}
