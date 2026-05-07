package me.zipestudio.hudless.backend;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.mojang.blaze3d.vertex.PoseStack;
import lombok.Getter;
import me.zipestudio.hudless.client.HLClient;
import me.zipestudio.hudless.config.HudElement;
import me.zipestudio.hudless.config.LeafyConfig;
import me.zipestudio.hudless.config.groups.Settings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import org.joml.Matrix3x2fStack;
import org.spongepowered.asm.mixin.Unique;

//? if >=1.21.2 {
/*import net.minecraft.util.ARGB;
 *///?} else {
import net.minecraft.util.FastColor;
//?}

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

public class HudAnimationHandler {

    private static double y = 0;
    private static double speed = 0;

    @Getter
    private static int alpha = 255;

    private static long lastHudVisibleTime;

    public static double getY() {
        Settings config = LeafyConfig.getInstance().getSettings();
        return -Math.clamp(config.getMinY(), y, config.getMaxY());
    }

    public static boolean isHided() {
        return getY() <= LeafyConfig.getInstance().getSettings().getMinY() || getAlpha() <= 0;
    }

    public static void revealHud() {
        Settings config = LeafyConfig.getInstance().getSettings();

        y = config.getMaxY();
        speed = 0;
        alpha = 255;

        if (Minecraft.getInstance().level != null) {
            lastHudVisibleTime = Minecraft.getInstance().level.getGameTime();
        }
    }

    public static void render(float delta) {
        Settings config = LeafyConfig.getInstance().getSettings();

        if (!config.isEnableMod()) {
            if (getY() == config.getMaxY() && speed == 0 && alpha == 255) {
                return;
            }
            revealHud();
            return;
        }

        if (Minecraft.getInstance().level != null) {
            if (Minecraft.getInstance().level.getGameTime() - lastHudVisibleTime >= config.getVisibleTicks()) {
                if (getY() > config.getMinY()) {
                    speed += config.getHideSpeed() * delta;
                    y -= speed * delta;
                }
                alpha = Math.max(0, getAlpha() - (int) (config.getHideFadeSpeed() * 255 * delta));
            }
        }
    }

    private static double getHotbarLayoutOffset(HudElement element) {

        double yOffset = getY();
        LeafyConfig config = LeafyConfig.getInstance();

        boolean hideHotbar = config.getHuds().isHideHotbar();
        boolean hideProgress = config.getHuds().isHideProgressBar();

        if (element == HudElement.PROGRESS_BAR) {
            if (hideProgress) {
                return yOffset;
            } else {
                return Math.min(yOffset, 18f);
            }
        }

        if (element == HudElement.STATUS_BAR) {

            double offset = Math.min(yOffset, 18f);
            if (hideHotbar && hideProgress) {
                offset = element.functionDisabled() ? Math.min(yOffset, 22) : getY();
            } else if (!hideHotbar && hideProgress) {
                offset = Math.min(yOffset, 4);
            }

            return offset;
        }

        return getY();
    }

    //? if >=1.21.6 {
    /*public static void beforeInject(HudElement element, GuiGraphics drawContext, CallbackInfo ci) {

        Matrix3x2fStack matrices = drawContext.pose();
        matrices.pushMatrix();

        LeafyConfig config = LeafyConfig.getInstance();
        if (!config.getSettings().isEnableMod()) {
            return;
        }

        if (!element.functionDisabled()) {
            if (isHided()) {
                if (ci != null) {
                    ci.cancel();
                }
                return;
            }
        }

        HudElement.currentElement = element;
        if (!element.isTranslate()) return;

        boolean hideHotbar = config.getHuds().isHideHotbar();
        boolean hideProgress = config.getHuds().isHideProgressBar();

        boolean shiftedByLayout =
                (element == HudElement.STATUS_BAR && (hideHotbar || hideProgress)) ||
                        (element == HudElement.PROGRESS_BAR && (hideHotbar || hideProgress));

        if (shiftedByLayout) {
            matrices.translate(0f, (float) getHotbarLayoutOffset(element));
            return;
        }

        if (!element.functionDisabled()) {
            matrices.translate(0f, (float) getY());
            return;
        }

    }
    *///?} else if >=1.21.2 {
    /*public static void beforeInject(HudElement element, GuiGraphics drawContext, CallbackInfo ci) {

        PoseStack matrices = drawContext.pose();
        matrices.pushPose();

        LeafyConfig config = LeafyConfig.getInstance();
        if (!config.getSettings().isEnableMod()) {
            return;
        }

        if (!element.functionDisabled()) {
            if (isHided()) {
                if (ci != null) {
                    ci.cancel();
                }
                return;
            }
        }

        HudElement.currentElement = element;
        if (!element.isTranslate()) return;

        boolean hideHotbar = config.getHuds().isHideHotbar();
        boolean hideProgress = config.getHuds().isHideProgressBar();

        boolean shiftedByLayout =
                (element == HudElement.STATUS_BAR && (hideHotbar || hideProgress)) ||
                        (element == HudElement.PROGRESS_BAR && hideHotbar);

        if (shiftedByLayout) {
            matrices.translate(0f, getHotbarLayoutOffset(element), 0f);
            return;
        }

        if (!element.functionDisabled()) {
            matrices.translate(0f, getY(), 0f);
            return;
        }

    }
    *///?} else {
    public static void beforeInject(HudElement element, GuiGraphics drawContext, CallbackInfo ci) {

        PoseStack matrices = drawContext.pose();
        matrices.pushPose();

        LeafyConfig config = LeafyConfig.getInstance();
        if (!config.getSettings().isEnableMod()) {
            return;
        }

        if (!element.functionDisabled()) {
            if (isHided()) {
                if (ci != null) {
                    ci.cancel();
                }
                matrices.popPose();
                return;
            }
        }

        HudElement.currentElement = element;

        if (config.getSettings().isEnableFading() && !element.functionDisabled()) {
            float a = getAlpha() / 255f;
            drawContext.setColor(1f, 1f, 1f, a);
        }

        if (!element.isTranslate()) return;

        boolean hideHotbar = config.getHuds().isHideHotbar();
        boolean hideProgress = config.getHuds().isHideProgressBar();

        boolean shiftedByLayout =
                (element == HudElement.STATUS_BAR && (hideHotbar || hideProgress)) ||
                        (element == HudElement.PROGRESS_BAR && hideHotbar);

        if (shiftedByLayout) {
            matrices.translate(0f, getHotbarLayoutOffset(element), 0f);
            return;
        }

        if (!element.functionDisabled()) {
            matrices.translate(0f, getY(), 0f);
            return;
        }

    }
    //?}

    //? if >=1.21.6 {
    /*public static void afterInject(GuiGraphics drawContext) {
        drawContext.pose().popMatrix();
        HudElement.currentElement = null;
    }
    *///?} elif >=1.21.2 {
    /*public static void afterInject(GuiGraphics drawContext) {

        PoseStack pose = drawContext.pose();
        pose.translate(0f, LeafyConfig.getInstance().getSettings().getMaxY(), 0f);
        pose.popPose();

        HudElement.currentElement = null;
    }
    *///?} else {
    public static void afterInject(GuiGraphics drawContext) {

        drawContext.setColor(1f, 1f, 1f, 1f);

        PoseStack pose = drawContext.pose();
        pose.translate(0f, LeafyConfig.getInstance().getSettings().getMaxY(), 0f);
        pose.popPose();

        HudElement.currentElement = null;
    }
    //?}

    public static int applyAlphaToColor(int originalColor) {

        LeafyConfig config = LeafyConfig.getInstance();
        if (!config.getSettings().isEnableMod() || !config.getSettings().isEnableFading()) {
            return originalColor;
        }

        HudElement element = HudElement.currentElement;
        if (element == null || element.functionDisabled()) {
            return originalColor;
        }

        int globalAlpha = getAlpha();

        //? if >=1.21.2 {
        /*int originalAlpha = ARGB.alpha(originalColor);
        int newAlpha = (originalAlpha * globalAlpha) / 255;
        return ARGB.color(newAlpha,
                ARGB.red(originalColor),
                ARGB.green(originalColor),
                ARGB.blue(originalColor));
        *///?} else {
        int originalAlpha = FastColor.ARGB32.alpha(originalColor);
        int newAlpha = (originalAlpha * globalAlpha) / 255;
        return FastColor.ARGB32.color(newAlpha,
                FastColor.ARGB32.red(originalColor),
                FastColor.ARGB32.green(originalColor),
                FastColor.ARGB32.blue(originalColor));
        //?}
    }

}