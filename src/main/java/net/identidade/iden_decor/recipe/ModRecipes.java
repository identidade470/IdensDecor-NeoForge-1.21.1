package net.identidade.iden_decor.recipe;

import net.identidade.iden_decor.IdenDecorMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, IdenDecorMod.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, IdenDecorMod.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<SewingMachineRecipe>> SEWING_MACHINE_SERIALIZER =
            SERIALIZERS.register("sewing_machine", SewingMachineRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<SewingMachineRecipe>> SEWING_MACHINE_TYPE =
            TYPES.register("sewing_machine", () -> new RecipeType<SewingMachineRecipe>() {
                @Override
                public String toString() {
                    return "sewing_machine";
                }
            });


    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
