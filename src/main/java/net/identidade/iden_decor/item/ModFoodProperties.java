package net.identidade.iden_decor.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties WATER_CUP = new FoodProperties.Builder().alwaysEdible().usingConvertsTo(ModItems.PLASTIC_CUP.get()).build();
    public static final FoodProperties GUARANA_CUP = new FoodProperties.Builder().alwaysEdible().usingConvertsTo(ModItems.PLASTIC_CUP.get()).build();
}
