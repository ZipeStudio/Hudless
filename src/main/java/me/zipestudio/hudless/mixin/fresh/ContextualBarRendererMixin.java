package me.zipestudio.hudless.mixin.fresh;

//? if >=1.21.6 {

import me.zipestudio.hudless.backend.HudAnimationHandler;
import me.zipestudio.hudless.config.HudElement;
import net.minecraft.client.gui.Font;

//? if >=26.1 {
import net.minecraft.client.gui.GuiGraphicsExtractor;
//?} else {
/*import net.minecraft.client.gui.GuiGraphics;
*///?}

//? if >=26.2 {
import net.minecraft.client.gui.contextualbar.ContextualBar;
//?} else {
/*import net.minecraft.client.gui.contextualbar.ContextualBarRenderer;
*///?}

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if >=26.2 {
@Mixin(ContextualBar.class)
//?} else {
/*@Mixin(ContextualBarRenderer.class)
*///?}
public interface ContextualBarRendererMixin {

    //? if >=26.1 {
    @Inject(method = "extractExperienceLevel", at = @At("HEAD"), cancellable = true)
    private static void beforeExperienceLevel(GuiGraphicsExtractor guiGraphics, Font font, int i, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.PROGRESS_BAR, guiGraphics, ci);
    }

    @Inject(method = "extractExperienceLevel", at = @At("RETURN"))
    private static void afterExperienceLevel(GuiGraphicsExtractor guiGraphics, Font font, int i, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }
    //?} else {
    /*@Inject(method = "renderExperienceLevel", at = @At("HEAD"), cancellable = true)
    private static void beforeExperienceLevel(GuiGraphics guiGraphics, Font font, int i, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.PROGRESS_BAR, guiGraphics, ci);
    }

    @Inject(method = "renderExperienceLevel", at = @At("RETURN"))
    private static void afterExperienceLevel(GuiGraphics guiGraphics, Font font, int i, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }
    *///?}

}

//?}
