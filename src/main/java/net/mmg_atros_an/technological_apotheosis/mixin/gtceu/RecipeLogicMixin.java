package net.mmg_atros_an.technological_apotheosis.mixin.gtceu;


import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import net.mmg_atros_an.technological_apotheosis.apotheosis.affix.MachineAffixContexts;
import net.mmg_atros_an.technological_apotheosis.apotheosis.affix.overclock.MachineOverclockingAffix;
import net.mmg_atros_an.technological_apotheosis.apotheosis.affix.util.ExtraOverclocks;
import net.mmg_atros_an.technological_apotheosis.apotheosis.affix.util.MachineAffixHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(RecipeLogic.class)
public class RecipeLogicMixin {
    @Shadow(remap = false)
    @Final
    public IRecipeLogicMachine machine;

    @ModifyVariable(
            method = "setupRecipe",
            at = @At("HEAD"),
            argsOnly = true,
            remap = false
    )
    private GTRecipe technologicalApotheosis$applyAffixMultiplier(GTRecipe recipe) {

        if (!(this.machine instanceof MetaMachine metaMachine)) {
            return recipe;
        }

        GTRecipe copy = recipe.copy();
        MachineAffixContexts ctx = MachineAffixHelper.getAffixes(metaMachine);
        ExtraOverclocks.ExtraOverclockParams extraOverclockParams = ExtraOverclocks.getIdentityParams();
        for (MachineAffixContexts.AffixLevelPair pair : ctx.pairs()){
            copy = pair.machineAffix().processRecipe(copy, machine, ctx.rarity(), pair.level());
            if (pair.machineAffix() instanceof MachineOverclockingAffix overclockingAffix) {
                extraOverclockParams = overclockingAffix.updateOverclockParams(extraOverclockParams, recipe, machine, ctx.rarity(), pair.level());
            }
        }

        return ExtraOverclocks.applyExtraOverclock(metaMachine, copy, extraOverclockParams).apply(copy);
    }

}
