package net.mmg_atros_an.technological_apotheosis.apotheosis.affix.util;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import dev.shadowsoffire.apotheosis.adventure.affix.Affix;
import dev.shadowsoffire.apotheosis.adventure.affix.AffixRegistry;
import dev.shadowsoffire.apotheosis.adventure.loot.LootRarity;
import dev.shadowsoffire.apotheosis.adventure.loot.RarityRegistry;
import dev.shadowsoffire.placebo.reload.DynamicHolder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.mmg_atros_an.technological_apotheosis.apotheosis.affix.MachineAffixContexts;
import net.mmg_atros_an.technological_apotheosis.apotheosis.affix.MachineAffix;
import net.mmg_atros_an.technological_apotheosis.geceu.machine.trait.MachineAffixTrait;

import java.util.ArrayList;


public class MachineAffixHelper {

    public static MachineAffixContexts getAffixes(MetaMachine metaMachine){
        ArrayList<MachineAffixContexts.AffixLevelPair> list = new ArrayList<MachineAffixContexts.AffixLevelPair>();
        MachineAffixTrait trait = metaMachine.getTraits().stream()
                .filter(MachineAffixTrait.class::isInstance)
                .map(MachineAffixTrait.class::cast)
                .findFirst()
                .orElse(null);
        if (trait == null) {
            return new MachineAffixContexts(list,null);
        }
        CompoundTag affixData = trait.getAffixData();
        CompoundTag affixes = affixData.getCompound("affixes");
        LootRarity rarity = RarityRegistry.INSTANCE.getValue(ResourceLocation.parse(affixData.getString("rarity")));
        for (String affixId : affixes.getAllKeys()) {
            float level = affixes.getFloat(affixId);
            DynamicHolder<Affix> afx = AffixRegistry.INSTANCE.holder(ResourceLocation.parse(affixId));
            Affix actuallAffix = afx.getOptional().orElse(null);
            if (actuallAffix == null) {
                continue;
            }
            if(actuallAffix instanceof MachineAffix machineAffix){
                list.add(new MachineAffixContexts.AffixLevelPair(machineAffix,level));
            }
        }
        return new MachineAffixContexts(list,rarity);
    }
}
