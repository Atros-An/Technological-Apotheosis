package net.mmg_atros_an.technological_apotheosis.registries;

import net.minecraft.resources.ResourceLocation;
import net.mmg_atros_an.technological_apotheosis.apotheosis.attribute.MachineAttribute;

import java.util.HashMap;
import java.util.Map;

public class MachineAttributeRegistry {
    private static final Map<ResourceLocation, MachineAttribute> INSTANCE = new HashMap<ResourceLocation, MachineAttribute>();

    public static void register(ResourceLocation resourceLocation, MachineAttribute attribute) {
        INSTANCE.put(resourceLocation, attribute);
    }

    public static MachineAttribute get(ResourceLocation resourceLocation) {
        return INSTANCE.get(resourceLocation);
    }

}
