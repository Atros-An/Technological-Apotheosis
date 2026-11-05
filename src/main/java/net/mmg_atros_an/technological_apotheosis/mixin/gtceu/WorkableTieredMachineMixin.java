package net.mmg_atros_an.technological_apotheosis.mixin.gtceu;

import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.WorkableTieredMachine;
import com.gregtechceu.gtceu.api.machine.feature.IDropSaveMachine;
import net.mmg_atros_an.technological_apotheosis.geceu.machine.trait.MachineAffixTrait;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorkableTieredMachine.class)
public abstract class WorkableTieredMachineMixin
        implements IDropSaveMachine {

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void technologicalApotheosis$addAffixTrait(
            CallbackInfo ci
    ) {
        new MachineAffixTrait((MetaMachine) (Object) this);
    }
}