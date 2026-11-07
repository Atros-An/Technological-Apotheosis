package net.mmg_atros_an.technological_apotheosis.apotheosis.attribute;

import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import net.mmg_atros_an.technological_apotheosis.apotheosis.affix.util.extraoverclock.ExtraOverclockParams;
import net.mmg_atros_an.technological_apotheosis.apotheosis.affix.util.extraoverclock.ExtraOverclockParamsModifier;


@FunctionalInterface
public interface MachineAttribute{
    MachineAttributeContext apply(GTRecipe recipe, IRecipeLogicMachine machine, double value);
    record MachineAttributeContext(ModifierFunction modifier, ExtraOverclockParamsModifier extraOverclockParams) {
    }
}


