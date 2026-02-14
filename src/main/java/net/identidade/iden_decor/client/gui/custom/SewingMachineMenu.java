package net.identidade.iden_decor.client.gui.custom;

import net.identidade.iden_decor.blockentity.SewingMachineBlockEntity;
import net.identidade.iden_decor.client.gui.ModMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

public class SewingMachineMenu extends AbstractContainerMenu {

    public SewingMachineMenu(int containerId, Inventory playerInventory, FriendlyByteBuf buf) {
        this(containerId, playerInventory ,ContainerLevelAccess.NULL);
    }

    public SewingMachineMenu(int containerId, Inventory playerInventory ,final ContainerLevelAccess access) {
        super(ModMenus.SEWING_MACHINE.get(), containerId);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
