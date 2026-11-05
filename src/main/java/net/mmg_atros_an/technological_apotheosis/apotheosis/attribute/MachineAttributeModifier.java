package net.mmg_atros_an.technological_apotheosis.apotheosis.attribute;

import com.gregtechceu.gtceu.api.machine.feature.IRecipeLogicMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class MachineAttributeModifier {
    public final AttributeModifier.Operation operation;
    public final double value;
    public final MachineAttribute machineAttribute;

    public MachineAttributeModifier(AttributeModifier.Operation operation, double value, ResourceLocation resourceLocation, MachineAttribute machineAttribute) {
        this.operation = operation;
        this.value = value;
        this.machineAttribute = machineAttribute;
    }

    public GTRecipe apply(GTRecipe recipe, IRecipeLogicMachine machine) {
        return machineAttribute.recipeModifier.apply(recipe, machine, operation, value);
    }

}
