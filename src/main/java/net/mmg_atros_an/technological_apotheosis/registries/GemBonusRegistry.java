package net.mmg_atros_an.technological_apotheosis.registries;

import dev.shadowsoffire.apotheosis.adventure.socket.gem.bonus.GemBonus;
import net.mmg_atros_an.technological_apotheosis.apotheosis.gems.RecipeTimeReductionBonus;
import net.mmg_atros_an.technological_apotheosis.apotheosis.gems.injection.GemInjectionRegistry;

public class GemBonusRegistry {
    public static void register(){
        GemInjectionRegistry.INSTANCE.registerToBus();
        GemBonus.CODEC.register(RecipeTimeReductionBonus.ID,RecipeTimeReductionBonus.CODEC);

    }
}
