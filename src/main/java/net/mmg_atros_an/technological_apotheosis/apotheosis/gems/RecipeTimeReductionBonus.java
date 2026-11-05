package net.mmg_atros_an.technological_apotheosis.apotheosis.gems;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.shadowsoffire.apotheosis.adventure.affix.Affix;
import dev.shadowsoffire.apotheosis.adventure.loot.LootCategory;
import dev.shadowsoffire.apotheosis.adventure.loot.LootRarity;
import dev.shadowsoffire.apotheosis.adventure.socket.gem.GemClass;
import dev.shadowsoffire.apotheosis.adventure.socket.gem.bonus.GemBonus;
import dev.shadowsoffire.placebo.codec.PlaceboCodecs;
import dev.shadowsoffire.placebo.util.StepFunction;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.mmg_atros_an.technological_apotheosis.TechnologicalApotheosis;
import net.mmg_atros_an.technological_apotheosis.apotheosis.MachineLootCategory;

import java.util.Map;

public class RecipeTimeReductionBonus extends GemBonus {

    public static final ResourceLocation ID = TechnologicalApotheosis.getResourceLocation("recipe_time_reduction_bonus");

    public static Codec<RecipeTimeReductionBonus> CODEC = RecordCodecBuilder.create(inst -> inst
            .group(
                    PlaceboCodecs.enumCodec(AttributeModifier.Operation.class).fieldOf("operation").forGetter(a -> a.operation),
                    VALUES_CODEC.fieldOf("values").forGetter(a -> a.values))
            .apply(inst, RecipeTimeReductionBonus::new));

    protected final AttributeModifier.Operation operation;
    public final Map<LootRarity, StepFunction> values;

    public RecipeTimeReductionBonus(AttributeModifier.Operation op, Map<LootRarity, StepFunction> values) {
        super(TechnologicalApotheosis.getResourceLocation("time_reduction"), new GemClass("machine", ImmutableSet.of(MachineLootCategory.MACHINE)));
        this.operation = op;
        this.values = values;
    }

    @Override
    public RecipeTimeReductionBonus validate() {
        Preconditions.checkNotNull(this.values);
        this.values.forEach((k, v) -> {
            Preconditions.checkNotNull(k);
            Preconditions.checkNotNull(v);
        });
        return this;
    }

    @Override
    public boolean supports(LootRarity rarity) {
        return this.values.containsKey(rarity);
    }

    @Override
    public int getNumberOfUUIDs() {
        return 0;
    }

    @Override
    public Component getSocketBonusTooltip(ItemStack gem, LootRarity rarity) {
        float value = this.values.get(rarity).min();
        return Component.translatable("bonus." + this.getId() + ".desc", Affix.fmt(value * 100)).withStyle(ChatFormatting.YELLOW);
    }

    @Override
    public Codec<? extends GemBonus> getCodec() {
        return CODEC;
    }
}
