package net.mmg_atros_an.technological_apotheosis.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.mmg_atros_an.technological_apotheosis.TechnologicalApotheosis;

public class CreativeModeTabsRegistry {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TechnologicalApotheosis.MODID);

    public static final RegistryObject<CreativeModeTab> My_Tab=CREATIVE_MODE_TABS.register("technological_apotheosis",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemRegistry.TestItem.get()))
                    .title(Component.translatable("creativetab.technological_apotheosis.tab"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ItemRegistry.TestItem.get());

                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
