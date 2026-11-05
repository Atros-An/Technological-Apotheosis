package net.mmg_atros_an.technological_apotheosis.apotheosis.affix;

import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import dev.shadowsoffire.apotheosis.adventure.affix.Affix;
import dev.shadowsoffire.apotheosis.adventure.affix.AffixType;
import dev.shadowsoffire.apotheosis.adventure.loot.LootCategory;
import dev.shadowsoffire.apotheosis.adventure.loot.LootRarity;
import dev.shadowsoffire.placebo.util.StepFunction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.mmg_atros_an.technological_apotheosis.apotheosis.MachineLootCategory;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public abstract class MachineAffix extends Affix {
    protected final Set<LootCategory> categories;
    private final Map<LootRarity, StepFunction> values;

    public MachineAffix(AffixType type ,Set<LootCategory> categories, Map<LootRarity, StepFunction> values) {
        super(type);
        this.categories = categories;
        this.values = values;
    }

    public MachineAffix(Set<LootCategory> categories, Map<LootRarity, StepFunction> values) {
        this(AffixType.STAT,categories,values);
    }

    public MachineAffix(AffixType type, Map<LootRarity, StepFunction> values) {
        this(type,Set.of(MachineLootCategory.MACHINE), values);
    }

    public MachineAffix(Map<LootRarity, StepFunction> values) {
        this(AffixType.STAT,Set.of(MachineLootCategory.MACHINE), values);
    }

    public Set<LootCategory> getApplicableCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public final Map<LootRarity, StepFunction> getValues() {
        return Collections.unmodifiableMap(values);
    }

    public float getTrueLevel(LootRarity rarity, float level) {
        return getValues().get(rarity).get(level);
    }

    @Override
    public boolean canApplyTo(ItemStack stack, LootCategory category, LootRarity rarity) {
        if (!getValues().containsKey(rarity)) {
            return false;
        }
        if (category == LootCategory.NONE) {
            return false;
        }
        var validTypes = getApplicableCategories();
        return validTypes.isEmpty() || validTypes.contains(category);
    }

    public String desc() {
        return "affix." + getId() + ".desc";
    }

    public String fmtPercent(LootRarity rarity, float level) {
        return fmt(100 * getTrueLevel(rarity, level));
    }

    public MutableComponent getPercentDescription(ItemStack stack, LootRarity rarity, float level) {
        return Component.translatable(desc(), fmtPercent(rarity, level));
    }

    public MutableComponent getIntDescription(ItemStack stack, LootRarity rarity, float level) {
        return Component.translatable(desc(), (int)getTrueLevel(rarity, level));
    }

    public Component getAugmentingTextBase(ItemStack stack, LootRarity rarity, float level, Object min, Object max) {
        MutableComponent comp = this.getDescription(stack, rarity, level);
        Component minComp = Component.translatable("%s", min);
        Component maxComp = Component.translatable("%s", max);
        return comp.append(valueBounds(minComp, maxComp));
    }

    public Component getPercentAugmentingText(ItemStack stack, LootRarity rarity, float level) {
        return getAugmentingTextBase(stack,rarity,level,fmtPercent(rarity, 0),fmtPercent(rarity, 1));
    }

    public Component getIntAugmentingText(ItemStack stack, LootRarity rarity, float level) {
        return getAugmentingTextBase(stack,rarity,level,(int)getTrueLevel(rarity, 0), (int)getTrueLevel(rarity, 1));
    }

    public GTRecipe processRecipe(GTRecipe recipe, IRecipeLogicMachine machine, LootRarity rarity, float level){
        return recipe;
    }

}
