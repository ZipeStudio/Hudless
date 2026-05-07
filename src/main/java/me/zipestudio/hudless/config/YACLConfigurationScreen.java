package me.zipestudio.hudless.config;

import dev.isxander.yacl3.api.*;
import lombok.experimental.ExtensionMethod;
import me.zipestudio.hudless.config.groups.Huds;
import me.zipestudio.hudless.config.groups.Settings;
import me.zipestudio.hudless.config.groups.Triggers;
import me.zipestudio.hudless.utils.ModMenuUtils;
import me.zipestudio.hudless.utils.yacl.base.SimpleCategory;
import me.zipestudio.hudless.utils.yacl.base.SimpleOption;
import me.zipestudio.hudless.utils.yacl.extension.SimpleOptionExtension;
import me.zipestudio.hudless.utils.yacl.screen.SimpleYACLScreen;
import me.zipestudio.hudless.utils.yacl.utils.SimpleContent;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.function.Function;

@ExtensionMethod(SimpleOptionExtension.class)
public class YACLConfigurationScreen {

    private static final Function<Boolean, Component> ENABLED_OR_DISABLED_FORMATTER = ModMenuUtils.getEnabledOrDisabledFormatter();

    private YACLConfigurationScreen() {
        throw new IllegalStateException("Screen class");
    }

    public static Screen createScreen(Screen parent) {
        LeafyConfig def = LeafyConfig.getNewInstance();
        LeafyConfig config = LeafyConfig.getInstance();

        return SimpleYACLScreen.startBuilder(parent, config::saveAsync)
                .categories(
                        getSettingsCategory(def.getSettings(), config.getSettings()),
                        getTriggersCategory(def.getTriggers(), config.getTriggers()),
                        getHudCategory(def.getHuds(), config.getHuds())
                )
                .build();
    }

    private static ConfigCategory getSettingsCategory(Settings def, Settings config) {
        return SimpleCategory.startBuilder("settings")
                .options(

                        SimpleOption.<Boolean>startBuilder("enableMod")
                                .withBinding(def.isEnableMod(), config::isEnableMod, config::setEnableMod, true)
                                .withController(ENABLED_OR_DISABLED_FORMATTER)
                                .withDescription(SimpleContent.NONE)
                                .build(),

                        SimpleOption.<Boolean>startBuilder("enableFading")
                                .withBinding(def.isEnableFading(), config::isEnableFading, config::setEnableFading, true)
                                .withController(ENABLED_OR_DISABLED_FORMATTER)
                                .withDescription(SimpleContent.NONE)
                                .build(),

                        SimpleOption.<Integer>startBuilder("visibleTicks")
                                .withBinding(def.getVisibleTicks(), config::getVisibleTicks, config::setVisibleTicks, true)
                                .withDescription(SimpleContent.NONE)
                                .withController(0, Integer.MAX_VALUE, 1, false)
                                .build(),

                        SimpleOption.<Double>startBuilder("hideSpeed")
                                .withBinding(def.getHideSpeed(), config::getHideSpeed, config::setHideSpeed, true)
                                .withDescription(SimpleContent.NONE)
                                .withController(0.0, Double.MAX_VALUE, 0.1, false)
                                .build(),

                        SimpleOption.<Double>startBuilder("hideFadeSpeed")
                                .withBinding(def.getHideFadeSpeed(), config::getHideFadeSpeed, config::setHideFadeSpeed, true)
                                .withDescription(SimpleContent.NONE)
                                .withController(0.0, Double.MAX_VALUE, 0.01, false)
                                .build(),

                        SimpleOption.<Integer>startBuilder("minY")
                                .withBinding(def.getMinY(), config::getMinY, config::setMinY, true)
                                .withDescription(SimpleContent.NONE)
                                .withController(Integer.MIN_VALUE, Integer.MAX_VALUE, 1, false)
                                .build()
                )
                .build();
    }

    private static ConfigCategory getTriggersCategory(Triggers def, Triggers config) {
        return SimpleCategory.startBuilder("triggers")
                .options(

                        SimpleOption.<Boolean>startBuilder("triggerHotbar")
                                .withBinding(def.isTriggerHotbar(), config::isTriggerHotbar, config::setTriggerHotbar, true)
                                .withController(ENABLED_OR_DISABLED_FORMATTER)
                                .withDescription(SimpleContent.NONE)
                                .build(),

                        SimpleOption.<Boolean>startBuilder("triggerHealth")
                                .withBinding(def.isTriggerHealth(), config::isTriggerHealth, config::setTriggerHealth, true)
                                .withController(ENABLED_OR_DISABLED_FORMATTER)
                                .withDescription(SimpleContent.NONE)
                                .build(),

                        SimpleOption.<Boolean>startBuilder("triggerHunger")
                                .withBinding(def.isTriggerHunger(), config::isTriggerHunger, config::setTriggerHunger, true)
                                .withController(ENABLED_OR_DISABLED_FORMATTER)
                                .withDescription(SimpleContent.NONE)
                                .build(),

                        SimpleOption.<Boolean>startBuilder("triggerArmor")
                                .withBinding(def.isTriggerArmor(), config::isTriggerArmor, config::setTriggerArmor, true)
                                .withController(ENABLED_OR_DISABLED_FORMATTER)
                                .withDescription(SimpleContent.NONE)
                                .build(),

                        SimpleOption.<Boolean>startBuilder("triggerAirBubbles")
                                .withBinding(def.isTriggerAirBubbles(), config::isTriggerAirBubbles, config::setTriggerAirBubbles, true)
                                .withController(ENABLED_OR_DISABLED_FORMATTER)
                                .withDescription(SimpleContent.NONE)
                                .build(),

                        SimpleOption.<Boolean>startBuilder("triggerEffects")
                                .withBinding(def.isTriggerEffects(), config::isTriggerEffects, config::setTriggerEffects, true)
                                .withController(ENABLED_OR_DISABLED_FORMATTER)
                                .withDescription(SimpleContent.NONE)
                                .build(),

                        SimpleOption.<Boolean>startBuilder("triggerMountHealth")
                                .withBinding(def.isTriggerMountHealth(), config::isTriggerMountHealth, config::setTriggerMountHealth, true)
                                .withController(ENABLED_OR_DISABLED_FORMATTER)
                                .withDescription(SimpleContent.NONE)
                                .build(),

                        SimpleOption.<Boolean>startBuilder("triggerExperience")
                                .withBinding(def.isTriggerExperience(), config::isTriggerExperience, config::setTriggerExperience, true)
                                .withController(ENABLED_OR_DISABLED_FORMATTER)
                                .withDescription(SimpleContent.NONE)
                                .build()
                )
                .build();
    }

    private static ConfigCategory getHudCategory(Huds def, Huds config) {
        return SimpleCategory.startBuilder("huds")
                .options(

                        SimpleOption.<Boolean>startBuilder("hideCrosshair")
                                .withBinding(def.isHideCrosshair(), config::isHideCrosshair, config::setHideCrosshair, true)
                                .withController(ENABLED_OR_DISABLED_FORMATTER)
                                .withDescription(SimpleContent.NONE)
                                .build(),

                        SimpleOption.<Boolean>startBuilder("hideHotbar")
                                .withBinding(def.isHideHotbar(), config::isHideHotbar, config::setHideHotbar, true)
                                .withController(ENABLED_OR_DISABLED_FORMATTER)
                                .withDescription(SimpleContent.NONE)
                                .build(),

                        SimpleOption.<Boolean>startBuilder("hideEffects")
                                .withBinding(def.isHideEffects(), config::isHideEffects, config::setHideEffects, true)
                                .withController(ENABLED_OR_DISABLED_FORMATTER)
                                .withDescription(SimpleContent.NONE)
                                .build(),

                        SimpleOption.<Boolean>startBuilder("hideScoreboard")
                                .withBinding(def.isHideScoreboard(), config::isHideScoreboard, config::setHideScoreboard, true)
                                .withController(ENABLED_OR_DISABLED_FORMATTER)
                                .withDescription(SimpleContent.NONE)
                                .build(),

                        SimpleOption.<Boolean>startBuilder("hideBossBar")
                                .withBinding(def.isHideBossBar(), config::isHideBossBar, config::setHideBossBar, true)
                                .withController(ENABLED_OR_DISABLED_FORMATTER)
                                .withDescription(SimpleContent.NONE)
                                .build(),

                        SimpleOption.<Boolean>startBuilder("hideChat")
                                .withBinding(def.isHideChat(), config::isHideChat, config::setHideChat, true)
                                .withController(ENABLED_OR_DISABLED_FORMATTER)
                                .withDescription(SimpleContent.NONE)
                                .build(),

                        SimpleOption.<Boolean>startBuilder("hideSubtitles")
                                .withBinding(def.isHideSubtitles(), config::isHideSubtitles, config::setHideSubtitles, true)
                                .withController(ENABLED_OR_DISABLED_FORMATTER)
                                .withDescription(SimpleContent.NONE)
                                .build(),

                        SimpleOption.<Boolean>startBuilder("hideStatusBar")
                                .withBinding(def.isHideStatusBar(), config::isHideStatusBar, config::setHideStatusBar, true)
                                .withController(ENABLED_OR_DISABLED_FORMATTER)
                                .withDescription(SimpleContent.NONE)
                                .build(),

                        SimpleOption.<Boolean>startBuilder("hideProgressBar")
                                .withBinding(def.isHideProgressBar(), config::isHideProgressBar, config::setHideProgressBar, true)
                                .withController(ENABLED_OR_DISABLED_FORMATTER)
                                .withDescription(SimpleContent.NONE)
                                .build()
                )
                .build();
    }

}