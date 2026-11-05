package net.mmg_atros_an.technological_apotheosis.apotheosis.gems.injection;

import dev.shadowsoffire.placebo.reload.DynamicRegistry;
import net.mmg_atros_an.technological_apotheosis.TechnologicalApotheosis;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GemInjectionRegistry extends DynamicRegistry<GemInjection> {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final GemInjectionRegistry INSTANCE = new GemInjectionRegistry();

    public GemInjectionRegistry() {
        super(LOGGER, "teap_gem_injection", true, false);
    }

    @Override
    protected void registerBuiltinCodecs() {
        registerDefaultCodec(TechnologicalApotheosis.getResourceLocation("teap_gem_injection"), GemInjection.CODEC.get());
    }
}
