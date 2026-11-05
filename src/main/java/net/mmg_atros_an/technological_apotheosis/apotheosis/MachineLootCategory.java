package net.mmg_atros_an.technological_apotheosis.apotheosis;

import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import dev.shadowsoffire.apotheosis.adventure.loot.LootCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;

public class MachineLootCategory {
    public static LootCategory MACHINE;

    public static void init() {
        MACHINE = LootCategory.register(null,"machine", s -> s.getItem() instanceof BlockItem blockItem && blockItem instanceof MetaMachineItem, new EquipmentSlot[]{});
    }
}
