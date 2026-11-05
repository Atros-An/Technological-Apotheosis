package net.mmg_atros_an.technological_apotheosis.registries;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mmg_atros_an.technological_apotheosis.TechnologicalApotheosis;
import net.mmg_atros_an.technological_apotheosis.items.TestItem;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TechnologicalApotheosis.MODID);

    public static final RegistryObject<Item> TestItem = ITEMS.register("test_item", () -> new TestItem(new Item.Properties()));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
