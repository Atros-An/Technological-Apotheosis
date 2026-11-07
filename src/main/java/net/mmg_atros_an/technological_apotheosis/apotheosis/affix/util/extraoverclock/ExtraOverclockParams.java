package net.mmg_atros_an.technological_apotheosis.apotheosis.affix.util.extraoverclock;

import lombok.Getter;

@Getter
public class ExtraOverclockParams {
    private double extraDurationFactor;
    private double extraVoltageFactor;
    private int maxOcs;

    public ExtraOverclockParams updateExtraDurationFactor(double extraDurationFactor) {
        this.extraDurationFactor = extraDurationFactor;
        return this;
    }

    public ExtraOverclockParams updateExtraVoltageFactor(double extraVoltageFactor) {
        this.extraVoltageFactor = extraVoltageFactor;
        return this;
    }

    public ExtraOverclockParams updateMaxOcs(int maxOcs) {
        this.maxOcs = maxOcs;
        return this;
    }

    public ExtraOverclockParams(double extraDurationFactor, double extraVoltageFactor, int maxOcs) {
        this.extraDurationFactor = extraDurationFactor;
        this.extraVoltageFactor = extraVoltageFactor;
        this.maxOcs = maxOcs;
    }
}
