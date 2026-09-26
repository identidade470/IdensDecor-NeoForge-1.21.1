package net.identidade.iden_decor.mixin;

import com.mrcrayfish.furniture.refurbished.electricity.Connection;
import com.mrcrayfish.furniture.refurbished.electricity.IModuleNode;
import net.identidade.iden_decor.block.custom.templates.light.GenericLightBlock;
import net.identidade.iden_decor.blockentity.LightBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

@Mixin(LightBlockEntity.class)
public class CompatRefurbishedFurnitureMixin implements IModuleNode {

    @Unique
    private final Set<Connection> light$connections = new HashSet<>();
    @Unique
    private final Set<BlockPos> light$powerSources = new HashSet<>();
    @Unique
    private boolean light$receivingPower;

    @Shadow
    private String[] availableModes;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addRefurbishedMode(CallbackInfo ci) {
        availableModes = new String[]{"manual", "redstone", "refurbished_energy"};
    }

    @Unique
    private LightBlockEntity light$self() {
        return (LightBlockEntity) (Object) this;
    }

    @Override
    public BlockPos getNodePosition() {
        return this.light$self().getBlockPos();
    }

    @Override
    public Level getNodeLevel() {
        return this.light$self().getLevel();
    }

    @Override
    public BlockEntity getNodeOwner() {
        return this.light$self();
    }

    @Override
    public boolean isNodePowered() {
        return this.light$self().getBlockState().getValue(GenericLightBlock.POWERED) && this.light$self().getActivationMode() == 2;
    }

    @Override
    public void setNodePowered(boolean b) {
        LightBlockEntity self = this.light$self();
        Level level = self.getLevel();

        if (level == null) {
            return;
        }

        BlockPos pos = self.getBlockPos();
        BlockState state = self.getBlockState();

        if (state.getValue(GenericLightBlock.POWERED) != b && this.light$self().getActivationMode() == 2) {
            level.setBlock(pos, state.setValue(GenericLightBlock.POWERED, b), 3);
        }
    }

    @Override
    public Set<Connection> getNodeConnections() {
        return this.light$connections;
    }

    @Override
    public void setNodeReceivingPower(boolean b) {
        this.light$receivingPower = b;
    }

    @Override
    public Set<BlockPos> getPowerSources() {
        return this.light$powerSources;
    }

    @Override
    public boolean isNodeReceivingPower() {
        return this.light$receivingPower;
    }

    @Override
    public boolean isNodeInPowerableNetwork() {
        return this.light$self().getActivationMode() != 2 || IModuleNode.super.isNodeInPowerableNetwork();
    }

    @Override
    public int getNodeMaximumConnections() {
        return this.light$self().getActivationMode() == 2?IModuleNode.super.getNodeMaximumConnections():0;
    }

    @Override
    public void updateNodePoweredState() {
        if (this.light$self().getActivationMode() == 2) {
            IModuleNode.super.updateNodePoweredState();
        }
    }

    @Inject(method = "onModeChanged", at = @At("TAIL"))
    private void lightModeChanged(int newMode, CallbackInfo ci) {
        this.removeAllNodeConnections();
    }

    @Inject(method = "saveAdditional", at = @At("TAIL"))
    private void saveNodeInfo(CompoundTag tag, HolderLookup.Provider registries, CallbackInfo ci) {
        tag.putBoolean("receivingPower", this.light$receivingPower);
        this.writeNodeNbt(tag);
    }

    @Inject(method =  "loadAdditional", at = @At("TAIL"))
    private void readNodeInfo(CompoundTag tag, HolderLookup.Provider registries, CallbackInfo ci) {
        this.light$receivingPower = tag.getBoolean("receivingPower");
        this.readNodeNbt(tag);
    }
}
