package net.mmg_atros_an.technological_apotheosis.apotheosis.gems.injection;


import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.shadowsoffire.apotheosis.adventure.socket.gem.bonus.GemBonus;
import dev.shadowsoffire.placebo.codec.CodecProvider;
import lombok.Getter;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.function.Supplier;

public class GemInjection implements CodecProvider<GemInjection> {
    public static final Supplier<Codec<GemInjection>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> inst.group(
                    ResourceLocation.CODEC.fieldOf("gem").forGetter(GemInjection::getInjectionTarget),
                    GemBonus.CODEC.listOf().fieldOf("bonuses").forGetter(GemInjection::getBonuses))
            .apply(inst, GemInjection::new)));

    public GemInjection(ResourceLocation target, List<GemBonus> bonuses) {
        this.target = target;
        this.bonuses = bonuses;
    }

    public ResourceLocation getInjectionTarget() {
        return target;
    }

    private final ResourceLocation target;
    @Getter
    private final List<GemBonus> bonuses;

    @Override
    public Codec<? extends GemInjection> getCodec() {
        return CODEC.get();
    }
}
