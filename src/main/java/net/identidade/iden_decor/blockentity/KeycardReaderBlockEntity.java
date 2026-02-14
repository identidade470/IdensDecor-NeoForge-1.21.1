package net.identidade.iden_decor.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class KeycardReaderBlockEntity extends BlockEntity {

    private String keycardCode;

    public KeycardReaderBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.KEYCARD_READER_BE.get(), pos, blockState);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (keycardCode != null) {
            tag.putString("keycard_code", keycardCode);
        }
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.keycardCode = tag.contains("keycard_code") ? tag.getString("keycard_code") : null;
    }

    public String getKeycardCode() {
        return keycardCode;
    }

    public void  setKeycardCode(String code) {
        keycardCode = code;
        this.setChanged();
    }

    public boolean hasKeycardCode() {
        return keycardCode != null;
    }

    public boolean keycardCodeMatches(String code) {
        return keycardCode != null && keycardCode.equals(code);
    }
}
