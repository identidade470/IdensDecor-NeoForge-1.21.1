package net.identidade.iden_decor.client.gui.custom;

import com.google.common.collect.Lists;
import net.identidade.iden_decor.blockentity.SewingMachineBlockEntity;
import net.identidade.iden_decor.client.gui.ModMenus;
import net.identidade.iden_decor.recipe.ModRecipes;
import net.identidade.iden_decor.recipe.SewingMachineRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SewingMachineMenu extends AbstractContainerMenu {

    private final DataSlot selectedRecipeIndex;
    private final Container sewingContainer;
    private final Container sewingResultContainer;

    private final Level level;
    private List<RecipeHolder<SewingMachineRecipe>> recipes;
    private ItemStack input;

    Runnable slotUpdateListener;

    private final Slot inputSlot;
    private final Slot outputSlot;

    public SewingMachineMenu(int containerId, Inventory playerInventory, FriendlyByteBuf buf) {
        this(containerId, playerInventory ,ContainerLevelAccess.NULL);
    }

    public SewingMachineMenu(int containerId, Inventory playerInventory ,final ContainerLevelAccess access) {
        super(ModMenus.SEWING_MACHINE.get(), containerId);
        this.selectedRecipeIndex = DataSlot.standalone();

        this.level = playerInventory.player.level();
        this.recipes = Lists.newArrayList();
        this.slotUpdateListener = () -> {
        };

        this.sewingContainer = new SimpleContainer(1) {
            @Override
            public void setChanged() {
                super.setChanged();
                SewingMachineMenu.this.slotsChanged(this);
                SewingMachineMenu.this.slotUpdateListener.run();
            }
        };

        this.input = ItemStack.EMPTY;

        this.sewingResultContainer = new ResultContainer();
        this.inputSlot = addSlot(new Slot(this.sewingContainer, 0, 20, 33));
        this.outputSlot = addSlot(new Slot(this.sewingResultContainer, 1, 143, 33) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                ItemStack itemStack = SewingMachineMenu.this.inputSlot.remove(1);
                super.onTake(player, stack);
            }
        });

        for (int line = 0; line < 3; ++line) {
            for (int collumn = 0; collumn < 9; ++collumn) {
                this.addSlot(new Slot(playerInventory, collumn + line * 9 + 9, 8 + collumn * 18, 84 + line * 18));
            }
        }

        for (int hotbarSlot = 0; hotbarSlot < 9; ++hotbarSlot) {
            this.addSlot(new Slot(playerInventory, hotbarSlot, 8 + hotbarSlot * 18 , 142));
        }

        this.addDataSlot(this.selectedRecipeIndex);
    }

    @Override
    public void slotsChanged(Container container) {
        ItemStack stack = this.inputSlot.getItem();
        if (!stack.is(this.input.getItem())) {
            this.input = stack.copy();
            setupRecipeList(container, stack);
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    private static SingleRecipeInput createRecipeInput(Container container) {
        return new SingleRecipeInput(container.getItem(0));
    }

    private void setupRecipeList(Container container, ItemStack stack){
        this.recipes.clear();
        this.selectedRecipeIndex.set(-1);
        this.outputSlot.set(ItemStack.EMPTY);
        if (!stack.isEmpty()) {
            this.recipes = this.level.getRecipeManager().getRecipesFor(ModRecipes.SEWING_MACHINE_TYPE.get(), createRecipeInput(container), this.level);
        }
    }

    public int getNumRecipes() {
        return this.recipes.size();
    }

    public int getSelectedRecipeIndex() {
        return this.selectedRecipeIndex.get();
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
