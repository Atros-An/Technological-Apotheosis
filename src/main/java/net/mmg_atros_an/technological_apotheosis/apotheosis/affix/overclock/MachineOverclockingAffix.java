package net.mmg_atros_an.technological_apotheosis.apotheosis.affix.overclock;

import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import dev.shadowsoffire.apotheosis.adventure.affix.AffixType;
import dev.shadowsoffire.apotheosis.adventure.loot.LootCategory;
import dev.shadowsoffire.apotheosis.adventure.loot.LootRarity;
import dev.shadowsoffire.placebo.util.StepFunction;
import net.mmg_atros_an.technological_apotheosis.apotheosis.affix.MachineAffix;


import java.util.Map;
import java.util.Set;

import net.mmg_atros_an.technological_apotheosis.apotheosis.affix.util.extraoverclock.ExtraOverclockParams;

public abstract class MachineOverclockingAffix extends MachineAffix {
    public MachineOverclockingAffix(Set<LootCategory> categories, Map<LootRarity, StepFunction> values) {
        super(categories, values);
    }

    public MachineOverclockingAffix(AffixType type, Set<LootCategory> categories, Map<LootRarity, StepFunction> values) {
        super(type, categories, values);
    }

    public MachineOverclockingAffix(AffixType type, Map<LootRarity, StepFunction> values) {
        super(type, values);
    }

    public MachineOverclockingAffix(Map<LootRarity, StepFunction> values) {
        super(values);
    }

    public ExtraOverclockParams updateOverclockParams(ExtraOverclockParams params, GTRecipe recipe, IRecipeLogicMachine machine, LootRarity rarity, float level) {
        return params;
    }

}
