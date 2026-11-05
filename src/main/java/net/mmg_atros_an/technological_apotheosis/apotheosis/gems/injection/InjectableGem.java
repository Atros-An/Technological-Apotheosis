package net.mmg_atros_an.technological_apotheosis.apotheosis.gems.injection;


import dev.shadowsoffire.apotheosis.adventure.loot.LootCategory;
import dev.shadowsoffire.apotheosis.adventure.socket.gem.bonus.GemBonus;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;
import java.util.Map;

@ApiStatus.Internal
public interface InjectableGem {
    void taep$reset();
    void taep$setBonuses(List<GemBonus> value);
    void taep$setUuidsNeeded(int value);
    Map<LootCategory, GemBonus> taep$getBonusMap();
    void taep$setBonusMap(Map<LootCategory, GemBonus> value);
}