package net.mmg_atros_an.technological_apotheosis.mixin.apotheosis;


import dev.shadowsoffire.apotheosis.adventure.loot.AffixConvertLootModifier;
import dev.shadowsoffire.apotheosis.adventure.loot.LootController;
import dev.shadowsoffire.apotheosis.adventure.loot.LootRarity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = AffixConvertLootModifier.class, remap = false)
public abstract class AffixConvertLootModifierMixin {

    @Redirect(
            method = "doApply",
            at = @At(
                    value = "INVOKE",
                    target = "Ldev/shadowsoffire/apotheosis/adventure/loot/LootController;createLootItem(Lnet/minecraft/world/item/ItemStack;Ldev/shadowsoffire/apotheosis/adventure/loot/LootRarity;Lnet/minecraft/util/RandomSource;)Lnet/minecraft/world/item/ItemStack;"
            ),
            remap = false
    )
    private ItemStack technologicalApotheosis$skipMachineAffixConversion(
            ItemStack stack,
            LootRarity rarity,
            RandomSource rand) {

        ResourceLocation itemId =
                BuiltInRegistries.ITEM.getKey(stack.getItem());

        if ("gtceu".equals(itemId.getNamespace())) {
            return stack;
        }

        return LootController.createLootItem(stack, rarity, rand);
    }
}