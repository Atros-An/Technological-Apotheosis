package net.mmg_atros_an.technological_apotheosis.apotheosis.affix;

import dev.shadowsoffire.apotheosis.adventure.loot.LootRarity;

import java.util.ArrayList;

public record MachineAffixContexts(ArrayList<AffixLevelPair> pairs, LootRarity rarity) {

    public record AffixLevelPair(MachineAffix machineAffix, float level){}

    public record SingleAffixContext(AffixLevelPair pair, LootRarity rarity){}
}
