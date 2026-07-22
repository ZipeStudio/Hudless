package me.zipestudio.hudless.mixin.bridge;

import me.zipestudio.hudless.backend.HudAnimationHandler;
import me.zipestudio.hudless.config.HudElement;
//? if >=26.1 {
import net.minecraft.client.gui.GuiGraphicsExtractor;
//?} else {
/*import net.minecraft.client.gui.GuiGraphics;
*///?}
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "com.moulberry.axiom.hooks.ScreenRenderHook", remap = false)
public class AxiomBridgeMixin {

    //? if >=26.1 {
    @Inject(
            method = "renderHotbar(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private static void beforeHotbar(GuiGraphicsExtractor guiGraphics, int w, int h, float delta, CallbackInfo ci) {

        if (isAxiomSlotActive()) {
            HudAnimationHandler.revealHud();
            return;
        }

        HudAnimationHandler.beforeInject(HudElement.HOTBAR, guiGraphics, ci);
    }

    @Inject(
            method = "renderHotbar(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V",
            at = @At("RETURN"),
            remap = false
    )
    private static void afterHotbar(GuiGraphicsExtractor guiGraphics, int w, int h, float delta, CallbackInfo ci) {

        if (isAxiomSlotActive()) {
            return;
        }

        HudAnimationHandler.afterInject(guiGraphics);
    }
    //?} else {
    /*@Inject(
            method = "renderHotbar(Lnet/minecraft/client/gui/GuiGraphics;IIF)V",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private static void beforeHotbar(GuiGraphics guiGraphics, int w, int h, float delta, CallbackInfo ci) {

        if (isAxiomSlotActive()) {
            HudAnimationHandler.revealHud();
            return;
        }

        HudAnimationHandler.beforeInject(HudElement.HOTBAR, guiGraphics, ci);
    }

    @Inject(
            method = "renderHotbar(Lnet/minecraft/client/gui/GuiGraphics;IIF)V",
            at = @At("RETURN"),
            remap = false
    )
    private static void afterHotbar(GuiGraphics guiGraphics, int w, int h, float delta, CallbackInfo ci) {

        if (isAxiomSlotActive()) {
            return;
        }

        HudAnimationHandler.afterInject(guiGraphics);
    }
    *///?}

    @Unique
    private static boolean isAxiomSlotActive() {
        try {
            Class<?> clazz = Class.forName("com.moulberry.axiom.buildertools.BuilderToolManager");
            return (boolean) clazz.getMethod("isToolSlotActive").invoke(null);
        } catch (Exception e) {
            return false;
        }
    }

}