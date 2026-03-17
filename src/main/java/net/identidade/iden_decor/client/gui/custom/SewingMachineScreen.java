package net.identidade.iden_decor.client.gui.custom;

import net.identidade.iden_decor.IdenDecorMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class SewingMachineScreen extends AbstractContainerScreen<SewingMachineMenu> {

    private int startIndex;

    private static final ResourceLocation SCROLLER_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/scroller");
    private static final ResourceLocation SCROLLER_DISABLED_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/scroller_disabled");
    private static final ResourceLocation RECIPE_SELECTED_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/recipe_selected");
    private static final ResourceLocation RECIPE_HIGHLIGHTED_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/recipe_highlighted");
    private static final ResourceLocation RECIPE_SPRITE = ResourceLocation.withDefaultNamespace("container/stonecutter/recipe");

    public SewingMachineScreen(SewingMachineMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int mouseX, int mouseY) {
        guiGraphics.blit(ResourceLocation.fromNamespaceAndPath(IdenDecorMod.MOD_ID, "textures/gui/container/sewing_machine.png"), this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        this.renderButtons(guiGraphics, mouseX, mouseY, this.leftPos, this.topPos, this.startIndex);
    }

    private void renderButtons(GuiGraphics guiGraphics, int mouseX, int mouseY, int x, int y, int lastVisibleElementIndex) {
        for (int button = this.startIndex; button < lastVisibleElementIndex && button < ((SewingMachineMenu) this.menu).getNumRecipes(); ++ button) {
            ResourceLocation resourceLocation;
            if (button == ((SewingMachineMenu) this.menu).getSelectedRecipeIndex()) {
                resourceLocation = RECIPE_SELECTED_SPRITE;
//            } else if () {
//                resourceLocation = RECIPE_HIGHLIGHTED_SPRITE;
            } else {
                resourceLocation = RECIPE_SPRITE;
            }

            guiGraphics.blitSprite(resourceLocation, button * 16, button - 1, 16, 16);
        }
    }

//    private void containerChanged() {
//        this.displayRecipes = ((SewingMachineMenu)this.menu).
//    }
}
