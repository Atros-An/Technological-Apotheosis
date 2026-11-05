package net.mmg_atros_an.technological_apotheosis.apotheosis.gems.injection;

import com.google.common.collect.ImmutableList;
import dev.shadowsoffire.apotheosis.Apotheosis;
import dev.shadowsoffire.apotheosis.adventure.loot.LootCategory;
import dev.shadowsoffire.apotheosis.adventure.socket.gem.Gem;
import dev.shadowsoffire.apotheosis.adventure.socket.gem.GemRegistry;
import dev.shadowsoffire.apotheosis.adventure.socket.gem.bonus.GemBonus;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mmg_atros_an.technological_apotheosis.TechnologicalApotheosis;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GemInjector {

    public static void doInjections() {
        for (GemInjection injection : GemInjectionRegistry.INSTANCE.getValues()) {
            var gem = GemRegistry.INSTANCE.getValue(injection.getInjectionTarget());
            if (gem != null) {
                injectGem(injection, gem);
            }
        }
    }

    private static void injectGem(@Nonnull GemInjection injection, @Nonnull Gem gem) {
        var accessor = (InjectableGem) gem;
        accessor.taep$reset();

        var newBonuses = Stream.concat(gem.getBonuses().stream(), injection.getBonuses().stream())
                .collect(ImmutableList.toImmutableList());
        var oldBonusMap = accessor.taep$getBonusMap();
        var newBonusMap = injection.getBonuses().stream()
                .<Pair<LootCategory, GemBonus>>mapMulti((gemData, mapper) -> gemData.getGemClass().types().forEach(c -> mapper.accept(Pair.of(c, gemData))))
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
        newBonusMap.putAll(oldBonusMap);
        var uuidsNeeded = newBonuses.stream()
                .mapToInt(GemBonus::getNumberOfUUIDs)
                .max()
                .orElse(0);

        accessor.taep$setBonuses(newBonuses);
        accessor.taep$setBonusMap(newBonusMap);
        accessor.taep$setUuidsNeeded(uuidsNeeded);
    }
}
