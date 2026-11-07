package net.mmg_atros_an.technological_apotheosis.apotheosis.attribute;

import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.mmg_atros_an.technological_apotheosis.registries.MachineAttributeRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MachineAttributeModifier {
    public final AttributeModifier.Operation operation;
    public final double value;
    public final ResourceLocation machineAttribute;

    public MachineAttributeModifier(AttributeModifier.Operation operation, double value, ResourceLocation machineAttribute) {
        this.operation = operation;
        this.value = value;
        this.machineAttribute = machineAttribute;
    }

    public static double getFinalValue(MachineAttributeModifier[] modifiers) {
        double additions = 0;
        double multiplyBase = 1;
        double multiplyTotal = 1;

        for (MachineAttributeModifier modifier : modifiers) {
            switch (modifier.operation) {
                case ADDITION ->
                        additions += modifier.value;

                case MULTIPLY_BASE ->
                        multiplyBase += modifier.value;

                case MULTIPLY_TOTAL ->
                        multiplyTotal *= modifier.value;
            }
        }

        return additions * multiplyBase * multiplyTotal;
    }

    public static HashMap<MachineAttribute,Double> normalize(MachineAttributeModifier[] modifiers) {
        HashMap<MachineAttribute,Double> map = new HashMap<>();
        for (ResourceLocation key : MachineAttributeRegistry.getKeys()) {
            map.put(
                    MachineAttributeRegistry.get(key),
                    getFinalValue(Arrays.stream(modifiers).filter(modifier -> modifier.machineAttribute.equals(key)).toArray(MachineAttributeModifier[]::new))
            );
        }
        return map;
    }

    public static GTRecipe applyModifiers(GTRecipe recipe, HashMap<MachineAttribute,Double> normalizedModifiers) {

        for (HashMap.Entry<MachineAttribute,Double> entry : normalizedModifiers.entrySet()) {

        }
        return recipe;
    }
}
