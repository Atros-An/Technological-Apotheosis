package net.mmg_atros_an.technological_apotheosis.registries;

import dev.shadowsoffire.apotheosis.adventure.affix.AffixRegistry;
import net.mmg_atros_an.technological_apotheosis.TechnologicalApotheosis;
import net.mmg_atros_an.technological_apotheosis.apotheosis.affix.MachineElectricityReductionAffix;
import net.mmg_atros_an.technological_apotheosis.apotheosis.affix.overclock.MachineExtraOverclockAffix;
import net.mmg_atros_an.technological_apotheosis.apotheosis.affix.overclock.MachineOverclockingTimeReductionAffix;
import net.mmg_atros_an.technological_apotheosis.apotheosis.affix.overclock.MachineOverclockingElectricityReductionAffix;
import net.mmg_atros_an.technological_apotheosis.apotheosis.affix.MachineParallelAffix;
import net.mmg_atros_an.technological_apotheosis.apotheosis.affix.MachineTimeReductionAffix;



public class MachineAffixRegistry {
    public static void register(){
        AffixRegistry.INSTANCE.registerCodec(TechnologicalApotheosis.getResourceLocation("machine_time_reduction"), MachineTimeReductionAffix.CODEC);
        AffixRegistry.INSTANCE.registerCodec(TechnologicalApotheosis.getResourceLocation("machine_electricity_reduction"), MachineElectricityReductionAffix.CODEC);
        AffixRegistry.INSTANCE.registerCodec(TechnologicalApotheosis.getResourceLocation("machine_parallel"), MachineParallelAffix.CODEC);


        AffixRegistry.INSTANCE.registerCodec(TechnologicalApotheosis.getResourceLocation("machine_overclocking_time_reduction"), MachineOverclockingTimeReductionAffix.CODEC);
        AffixRegistry.INSTANCE.registerCodec(TechnologicalApotheosis.getResourceLocation("machine_overclocking_electricity_reduction"), MachineOverclockingElectricityReductionAffix.CODEC);
        AffixRegistry.INSTANCE.registerCodec(TechnologicalApotheosis.getResourceLocation("machine_extra_overclock"), MachineExtraOverclockAffix.CODEC);
    }
}
