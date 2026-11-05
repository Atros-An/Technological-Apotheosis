package net.mmg_atros_an.technological_apotheosis.apotheosis.affix.util;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IOverclockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.RecipeModifierList;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.common.data.machines.GCYMMachines;
import com.gregtechceu.gtceu.common.data.machines.GTMultiMachines;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.FusionReactorMachine;
import net.mmg_atros_an.technological_apotheosis.TechnologicalApotheosis;

import static com.gregtechceu.gtceu.api.recipe.OverclockingLogic.*;

public class ExtraOverclocks {

    public static ExtraOverclockParams getIdentityParams(){
        return new ExtraOverclockParams(1,1,0);
    }

    public record ExtraOverclockParams(double extraDurationFactor, double extraVoltageFactor, int maxOcs){}

    @FunctionalInterface
    public interface ExtraOverclock{
        ModifierFunction getModifier(MetaMachine machine, GTRecipe recipe, double extraDurationFactor, double extraVoltageFactor, int maxOcs);
    }

    public static final ExtraOverclock ebfExtraOverclock = (machine, recipe, extraDurationFactor, extraVoltageFactor, maxOcs) -> applyExtraOverclockForEbfLike((CoilWorkableElectricMultiblockMachine) machine, recipe, extraDurationFactor, extraVoltageFactor, maxOcs);

    public static final ExtraOverclock fusionExtraOverclock = (machine, recipe, extraDurationFactor, extraVoltageFactor, maxOcs) -> applyExtraOverclock(machine, recipe, PERFECT_HALF_DURATION_FACTOR*extraDurationFactor, PERFECT_HALF_VOLTAGE_FACTOR*extraVoltageFactor, maxOcs, false);

    public static final ExtraOverclock extraNonPerfectOverclock = (machine, recipe, extraDurationFactor, extraVoltageFactor, maxOcs) -> applyExtraOverclock(machine, recipe, STD_DURATION_FACTOR*extraDurationFactor, STD_VOLTAGE_FACTOR*extraVoltageFactor, maxOcs, false);

    public static final ExtraOverclock extraPerfectOverclock = (machine, recipe, extraDurationFactor, extraVoltageFactor, maxOcs) -> applyExtraOverclock(machine, recipe, PERFECT_DURATION_FACTOR*extraDurationFactor, STD_VOLTAGE_FACTOR*extraVoltageFactor, maxOcs, false);

    public static final ExtraOverclock extraNonPerfectOverclockSubtick = (machine, recipe, extraDurationFactor, extraVoltageFactor, maxOcs) -> applyExtraOverclock(machine, recipe, STD_DURATION_FACTOR*extraDurationFactor, STD_VOLTAGE_FACTOR*extraVoltageFactor, maxOcs, true);

    public static final ExtraOverclock extraPerfectOverclockSubtick = (machine, recipe, extraDurationFactor, extraVoltageFactor, maxOcs) -> applyExtraOverclock(machine, recipe, PERFECT_DURATION_FACTOR*extraDurationFactor, STD_VOLTAGE_FACTOR*extraVoltageFactor, maxOcs, true);

    public static ModifierFunction applyExtraOverclock(MetaMachine machine, GTRecipe recipe, ExtraOverclockParams params) {
        return applyExtraOverclock(machine, recipe, params.extraDurationFactor, params.extraVoltageFactor, params.maxOcs);
    }

    public static ModifierFunction applyExtraOverclock(MetaMachine machine, GTRecipe recipe, double extraDurationFactor, double extraVoltageFactor, int maxOcs) {
        MachineDefinition definition = machine.getDefinition();
        if (definition == GTMultiMachines.ELECTRIC_BLAST_FURNACE||definition== GCYMMachines.BLAST_ALLOY_SMELTER||definition==GCYMMachines.MEGA_BLAST_FURNACE){
            return ebfExtraOverclock.getModifier(machine, recipe, extraDurationFactor, extraVoltageFactor, maxOcs);
        } else if (machine instanceof FusionReactorMachine) {
            return fusionExtraOverclock.getModifier(machine, recipe, extraDurationFactor, extraVoltageFactor, maxOcs);
        } else if (definition == GTMultiMachines.CRACKER || definition == GTMultiMachines.PYROLYSE_OVEN){
            return extraNonPerfectOverclockSubtick.getModifier(machine, recipe, extraDurationFactor, extraVoltageFactor, maxOcs);
        }
        if (definition.getRecipeModifier() instanceof RecipeModifierList modifierList){
            for (RecipeModifier modifier : modifierList.getModifiers()){
                if (modifier.equals(GTRecipeModifiers.OC_PERFECT)) {
                    return extraPerfectOverclock.getModifier(machine, recipe, extraDurationFactor, extraVoltageFactor, maxOcs);
                } else if (modifier.equals(GTRecipeModifiers.OC_NON_PERFECT)) {
                    return extraNonPerfectOverclock.getModifier(machine, recipe, extraDurationFactor, extraVoltageFactor, maxOcs);
                } else if (modifier.equals(GTRecipeModifiers.OC_PERFECT_SUBTICK)) {
                    return extraPerfectOverclockSubtick.getModifier(machine, recipe, extraDurationFactor, extraVoltageFactor, maxOcs);
                } else if (modifier.equals(GTRecipeModifiers.OC_NON_PERFECT_SUBTICK)) {
                    return extraNonPerfectOverclockSubtick.getModifier(machine, recipe, extraDurationFactor, extraVoltageFactor, maxOcs);
                }
            }
        }
        return extraNonPerfectOverclock.getModifier(machine, recipe, extraDurationFactor, extraVoltageFactor, maxOcs);
    }

    public static int getRemainingPerfectOverclockForEbf(CoilWorkableElectricMultiblockMachine coilMachine, GTRecipe recipe){
        int recipeTemp = recipe.data.getInt("ebf_temp");
        int machineTemp = coilMachine.getCoilType().getCoilTemperature() +
                (100 * Math.max(0, coilMachine.getTier() - GTValues.MV));
        return Math.max(0, ((machineTemp - recipeTemp) / COIL_EUT_DISCOUNT_TEMPERATURE*2) - recipe.ocLevel);
    }

    public static ModifierFunction applyExtraOverclockForEbfLike(CoilWorkableElectricMultiblockMachine coilMachine, GTRecipe recipe, int maxOcs){
        return applyExtraOverclockForEbfLike(coilMachine,recipe,maxOcs,1,1);
    }

    public static ModifierFunction applyExtraOverclockForEbfLike(CoilWorkableElectricMultiblockMachine coilMachine, GTRecipe recipe, double extraDurationFactor, double extraVoltageFactor, int maxOcs){
        return applyExtraOverclock(coilMachine,recipe, STD_VOLTAGE_FACTOR*extraVoltageFactor, PERFECT_DURATION_FACTOR*extraDurationFactor,getRemainingPerfectOverclockForEbf(coilMachine,recipe), true).andThen(
                applyExtraOverclock(coilMachine,recipe, STD_VOLTAGE_FACTOR*extraVoltageFactor, STD_DURATION_FACTOR*extraDurationFactor, maxOcs, true)
        );
    }

    public static ModifierFunction applyExtraOverclock(MetaMachine machine, GTRecipe recipe, double durationFactor, double voltageFactor, boolean subtick){
        return applyExtraOverclock(machine, recipe, durationFactor, voltageFactor, Integer.MAX_VALUE, subtick);
    }

    public static ModifierFunction applyExtraOverclock(MetaMachine machine, GTRecipe recipe, double durationFactor, double voltageFactor, int maxOcs, boolean subtick) {

        if (!(machine instanceof IOverclockMachine overclockMachine)) {
            return ModifierFunction.IDENTITY;
        }
        long EUt = RecipeHelper.getRealEUt(recipe).getTotalEU();
        final long MAX_EU = overclockMachine.getOverclockVoltage();
        int duration = recipe.duration;

        int maxParallels = ParallelLogic.getParallelAmount(machine, recipe, Integer.MAX_VALUE);

        OCParams ocParams = new OCParams(EUt, duration, maxOcs, maxParallels);

        if (subtick) {
            return subTickParallelOC(ocParams, MAX_EU, durationFactor, voltageFactor).toModifier();
        } else {
            return standardOC(ocParams, MAX_EU, durationFactor, voltageFactor).toModifier();
        }
    }
}
