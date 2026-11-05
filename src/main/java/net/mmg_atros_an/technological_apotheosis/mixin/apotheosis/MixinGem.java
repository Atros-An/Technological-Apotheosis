package net.mmg_atros_an.technological_apotheosis.mixin.apotheosis;

import dev.shadowsoffire.apotheosis.adventure.loot.LootCategory;
import dev.shadowsoffire.apotheosis.adventure.loot.LootRarity;
import dev.shadowsoffire.apotheosis.adventure.socket.gem.Gem;
import dev.shadowsoffire.apotheosis.adventure.socket.gem.bonus.GemBonus;
import net.minecraft.resources.ResourceLocation;
import net.mmg_atros_an.technological_apotheosis.apotheosis.gems.injection.InjectableGem;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Mixin(value = Gem.class, remap = false)
public abstract class MixinGem implements InjectableGem {
    @Inject(method = "<init>", at = @At("TAIL"))
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private void saveInitialData(int weight, float quality, Set<ResourceLocation> dimensions, Optional<LootRarity> minRarity, Optional<LootRarity> maxRarity, List<GemBonus> bonuses, boolean unique, Optional<Set<String>> stages, CallbackInfo ci) {
        teap$backup$bonuses = bonuses;
        teap$backup$uuidsNeeded = uuidsNeeded;
        teap$backup$bonusMap = bonusMap;
    }

    @Unique
    private List<GemBonus> teap$backup$bonuses;
    @Unique
    private int teap$backup$uuidsNeeded;
    @Unique
    private Map<LootCategory, GemBonus> teap$backup$bonusMap;

    @Override
    public void taep$reset() {
        taep$setBonuses(teap$backup$bonuses);
        taep$setUuidsNeeded(teap$backup$uuidsNeeded);
        taep$setBonusMap(teap$backup$bonusMap);
    }

    @Mutable @Accessor("bonuses")
    public abstract void taep$setBonuses(List<GemBonus> value);
    @Mutable @Accessor("uuidsNeeded")
    public abstract void taep$setUuidsNeeded(int value);
    @Mutable @Accessor("bonusMap")
    public abstract Map<LootCategory, GemBonus> taep$getBonusMap();
    @Mutable @Accessor("bonusMap")
    public abstract void taep$setBonusMap(Map<LootCategory, GemBonus> value);

    @Shadow @Final protected transient int uuidsNeeded;
    @Shadow
    @Final
    protected transient Map<LootCategory, GemBonus> bonusMap;
}