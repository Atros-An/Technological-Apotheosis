package net.mmg_atros_an.technological_apotheosis.apotheosis.affix.overclock;

import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.shadowsoffire.apotheosis.adventure.affix.Affix;
import dev.shadowsoffire.apotheosis.adventure.loot.LootRarity;
import dev.shadowsoffire.apotheosis.adventure.socket.gem.bonus.GemBonus;
import dev.shadowsoffire.placebo.util.StepFunction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.mmg_atros_an.technological_apotheosis.apotheosis.affix.util.extraoverclock.ExtraOverclockParams;

import java.util.Map;




public class MachineOverclockingElectricityReductionAffix extends MachineOverclockingAffix {
    public static final Codec<MachineOverclockingElectricityReductionAffix> CODEC =RecordCodecBuilder.create(inst -> inst.group(
            GemBonus.VALUES_CODEC.fieldOf("values").forGetter(a -> a.values)
    ).apply(inst, MachineOverclockingElectricityReductionAffix::new));

    protected final Map<LootRarity, StepFunction> values;

    public MachineOverclockingElectricityReductionAffix(Map<LootRarity, StepFunction> values) {
        super(values);
        this.values = values;
    }

    @Override
    public Codec<? extends Affix> getCodec() {
        return CODEC;
    }

    @Override
    public MutableComponent getDescription(ItemStack stack, LootRarity rarity, float level) {
        return getPercentDescription(stack, rarity, level);
    }

    @Override
    public Component getAugmentingText(ItemStack stack, LootRarity rarity, float level) {
        return getPercentAugmentingText(stack, rarity, level);
    }

    @Override
    public GTRecipe processRecipe(GTRecipe recipe, IRecipeLogicMachine machine, LootRarity rarity, float level){
        if (recipe.ocLevel <= 0) {
            return recipe;
        }

        double multiplier = 1d;
        for (int i = 0; i < recipe.ocLevel; i++) {
            multiplier /= getTrueLevel(rarity,level);
        }

        return ModifierFunction.builder()
                .eutMultiplier(multiplier)
                .build()
                .apply(recipe);
    }

    @Override
    public ExtraOverclockParams updateOverclockParams(ExtraOverclockParams params, GTRecipe recipe, IRecipeLogicMachine machine, LootRarity rarity, float level) {
        return params.updateExtraVoltageFactor(params.getExtraVoltageFactor()/getTrueLevel(rarity, level));
    }
}
