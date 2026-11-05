package net.mmg_atros_an.technological_apotheosis.apotheosis.affix.overclock;

import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.shadowsoffire.apotheosis.adventure.affix.Affix;
import dev.shadowsoffire.apotheosis.adventure.loot.LootRarity;
import dev.shadowsoffire.apotheosis.adventure.socket.gem.bonus.GemBonus;
import dev.shadowsoffire.placebo.util.StepFunction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.mmg_atros_an.technological_apotheosis.apotheosis.affix.MachineAffix;
import net.mmg_atros_an.technological_apotheosis.apotheosis.affix.util.ExtraOverclocks;

import java.util.Map;


public class MachineExtraOverclockAffix extends MachineOverclockingAffix {
    public static final Codec<MachineExtraOverclockAffix> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            GemBonus.VALUES_CODEC.fieldOf("values").forGetter(a -> a.values)
    ).apply(inst, MachineExtraOverclockAffix::new));

    protected final Map<LootRarity, StepFunction> values;

    public MachineExtraOverclockAffix(Map<LootRarity, StepFunction> values) {
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
    public ExtraOverclocks.ExtraOverclockParams updateOverclockParams(ExtraOverclocks.ExtraOverclockParams params, GTRecipe recipe, IRecipeLogicMachine machine, LootRarity rarity, float level) {
        return new ExtraOverclocks.ExtraOverclockParams(params.extraDurationFactor(),params.extraVoltageFactor(),params.maxOcs() + (int) getTrueLevel(rarity, level));
    }
}
