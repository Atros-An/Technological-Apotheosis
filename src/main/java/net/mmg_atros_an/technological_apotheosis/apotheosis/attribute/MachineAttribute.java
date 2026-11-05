package net.mmg_atros_an.technological_apotheosis.apotheosis.attribute;

import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.mmg_atros_an.technological_apotheosis.apotheosis.affix.util.ExtraOverclocks;

import java.util.HashMap;
import java.util.Map;


public class MachineAttribute {

    public final AttributeRecipeModifier recipeModifier;
    public final ExtraOverclocks.ExtraOverclockParams extraOverclockParams;

    public MachineAttribute(AttributeRecipeModifier recipeModifier, ExtraOverclocks.ExtraOverclockParams extraOverclockParams) {
        this.recipeModifier = recipeModifier;
        this.extraOverclockParams = extraOverclockParams;
    }

    @FunctionalInterface
    public interface AttributeRecipeModifier{
        GTRecipe apply(GTRecipe recipe, IRecipeLogicMachine machine, AttributeModifier.Operation operation, double value);
    }

}
