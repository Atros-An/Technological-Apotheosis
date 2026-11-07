package net.mmg_atros_an.technological_apotheosis.apotheosis.affix.util.extraoverclock;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;

@FunctionalInterface
public interface ExtraOverclockModifier {
    ModifierFunction getModifier(MetaMachine machine, GTRecipe recipe, double extraDurationFactor, double extraVoltageFactor, int maxOcs);
}
