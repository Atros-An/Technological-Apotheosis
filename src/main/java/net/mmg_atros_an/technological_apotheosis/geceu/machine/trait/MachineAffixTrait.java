package net.mmg_atros_an.technological_apotheosis.geceu.machine.trait;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.MachineTrait;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.mmg_atros_an.technological_apotheosis.TechnologicalApotheosis;


public class MachineAffixTrait extends MachineTrait {

    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER =
            new ManagedFieldHolder(MachineAffixTrait.class);

    public MachineAffixTrait(MetaMachine machine) {
        super(machine);
    }

    @Getter
    @Setter
    private CompoundTag affixData = new CompoundTag();

    @Override
    public void saveCustomPersistedData(CompoundTag tag, boolean forDrop) {
        tag.put("affix_data", affixData.copy());
    }

    @Override
    public void loadCustomPersistedData(CompoundTag tag) {
        if (tag.contains("affix_data", CompoundTag.TAG_COMPOUND)) {
            affixData = tag.getCompound("affix_data").copy();
        } else {
            affixData = new CompoundTag();
        }
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
}