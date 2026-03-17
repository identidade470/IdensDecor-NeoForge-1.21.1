package net.identidade.iden_decor.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class SewingMachineRecipe extends SingleItemRecipe {
    public SewingMachineRecipe(String group, Ingredient ingredient, ItemStack result) {
        super(ModRecipes.SEWING_MACHINE_TYPE.get(), ModRecipes.SEWING_MACHINE_SERIALIZER.get(), group, ingredient, result);
    }

    @Override
    public boolean matches(SingleRecipeInput singleRecipeInput, Level level) {
        return this.ingredient.test(singleRecipeInput.item());
    }

    public static class Serializer extends SingleItemRecipe.Serializer<SewingMachineRecipe> {

        public Serializer() {
            super(SewingMachineRecipe::new);
        }
    }
}
