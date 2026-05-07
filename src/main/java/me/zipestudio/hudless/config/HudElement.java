package me.zipestudio.hudless.config;

import java.util.function.Supplier;

public enum HudElement {

    CROSSHAIR(() -> LeafyConfig.getInstance().getHuds().isHideCrosshair(), false),
    HOTBAR(() -> LeafyConfig.getInstance().getHuds().isHideHotbar()),

    EFFECTS(() -> LeafyConfig.getInstance().getHuds().isHideEffects(), false),
    SCOREBOARD(() -> LeafyConfig.getInstance().getHuds().isHideScoreboard(), false),
    BOSSBAR(() -> LeafyConfig.getInstance().getHuds().isHideBossBar(), false),

    CHAT(() -> LeafyConfig.getInstance().getHuds().isHideChat(), false),
    SUBTITLES(() -> LeafyConfig.getInstance().getHuds().isHideSubtitles(), false),

    STATUS_BAR(() -> LeafyConfig.getInstance().getHuds().isHideStatusBar()),
    PROGRESS_BAR(() -> LeafyConfig.getInstance().getHuds().isHideProgressBar());

    private final Supplier<Boolean> configGetter;
    private final boolean useTranslate;

    public static HudElement currentElement = null;

    HudElement(Supplier<Boolean> configGetter) {
        this(configGetter, true);
    }

    HudElement(Supplier<Boolean> configGetter, boolean useTranslate) {
        this.configGetter = configGetter;
        this.useTranslate = useTranslate;
    }

    public boolean functionDisabled() {
        return !configGetter.get();
    }

    public boolean isTranslate() {
        return useTranslate;
    }

}
