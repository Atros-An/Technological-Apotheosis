package net.mmg_atros_an.technological_apotheosis.apotheosis.affix;

import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier;
import com.gregtechceu.gtceu.api.recipe.modifier.ModifierFunction;
import com.gregtechceu.gtceu.api.recipe.modifier.ParallelLogic;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.shadowsoffire.apotheosis.adventure.affix.Affix;
import dev.shadowsoffire.apotheosis.adventure.loot.LootRarity;
import dev.shadowsoffire.apotheosis.adventure.socket.gem.bonus.GemBonus;
import dev.shadowsoffire.placebo.util.StepFunction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;

import java.util.Map;


public class MachineParallelAffix extends MachineAffix {
    public static final Codec<MachineParallelAffix> CODEC =RecordCodecBuilder.create(inst -> inst.group(
            GemBonus.VALUES_CODEC.fieldOf("values").forGetter(a -> a.values)
    ).apply(inst, MachineParallelAffix::new));

    protected final Map<LootRarity, StepFunction> values;

    public MachineParallelAffix(Map<LootRarity, StepFunction> values) {
        super(values);
        this.values = values;
    }

    @Override
    public Codec<? extends Affix> getCodec() {
        return CODEC;
    }

    @Override
    public MutableComponent getDescription(ItemStack stack, LootRarity rarity, float level) {
        return getIntDescription(stack, rarity, level);
    }

    @Override
    public Component getAugmentingText(ItemStack stack, LootRarity rarity, float level) {
        return getIntAugmentingText(stack, rarity, level);
    }

    @Override
    public GTRecipe processRecipe(GTRecipe recipe, IRecipeLogicMachine machine, LootRarity rarity, float level){

        int parallels = ParallelLogic.getParallelAmount(machine.self(), recipe, recipe.parallels + (int)getTrueLevel(rarity,level));
        return ModifierFunction.builder()
                .modifyAllContents(ContentModifier.multiplier(parallels))
                .eutMultiplier(parallels)
                .parallels(parallels)
                .build()
                .apply(recipe);
    }

}
