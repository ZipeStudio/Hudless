package me.zipestudio.hudless.mixin.fresh;

//? if >=1.21.6 {

import me.zipestudio.hudless.backend.HudAnimationHandler;
import me.zipestudio.hudless.config.HudElement;
import net.minecraft.client.DeltaTracker;
//? if >=26.1 {
import net.minecraft.client.gui.GuiGraphicsExtractor;
//?} else {
/*import net.minecraft.client.gui.GuiGraphics;
*///?}
//? if >=26.2 {
import net.minecraft.client.gui.contextualbar.JumpableVehicleBar;
//?} else {
/*import net.minecraft.client.gui.contextualbar.JumpableVehicleBarRenderer;
*///?}
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if >=26.2 {
@Mixin(JumpableVehicleBar.class)
//?} else {
/*@Mixin(JumpableVehicleBarRenderer.class)
*///?}
public class JumpableVehicleBarRendererMixin {

    //? if >=26.1 {
    @Inject(method = "extractBackground", at = @At("HEAD"), cancellable = true)
    private void beforeBackground(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.PROGRESS_BAR, guiGraphics, ci);
    }

    @Inject(method = "extractBackground", at = @At("RETURN"))
    private void afterBackground(GuiGraphicsExtractor graphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.afterInject(graphics);
    }
    //?} else {
    /*@Inject(method = "renderBackground", at = @At("HEAD"), cancellable = true)
    private void beforeBackground(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.PROGRESS_BAR, guiGraphics, ci);
    }

    @Inject(method = "renderBackground", at = @At("RETURN"))
    private void afterBackground(GuiGraphics graphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.afterInject(graphics);
    }
    *///?}

}

//?}
