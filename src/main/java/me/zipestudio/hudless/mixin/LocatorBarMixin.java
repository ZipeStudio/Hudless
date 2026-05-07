package me.zipestudio.hudless.mixin;

import me.zipestudio.hudless.backend.HudAnimationHandler;
import me.zipestudio.hudless.config.HudElement;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.DeltaTracker;

@Pseudo
@Mixin(targets = "net.minecraft.client.gui.contextualbar.LocatorBarRenderer")
public class LocatorBarMixin {

    //? if >=1.21.6 {
    /*@Inject(
            method = "render",
            at = @At("HEAD"),
            cancellable = true
    )
    private void beforeRender(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.PROGRESS_BAR, guiGraphics, ci);
    }

    @Inject(
            method = "render",
            at = @At("RETURN")
    )
    private void afterRender(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(
            method = "renderBackground",
            at = @At("HEAD"),
            cancellable = true
    )
    private void beforeBackground(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.PROGRESS_BAR, guiGraphics, ci);
    }

    @Inject(
            method = "renderBackground",
            at = @At("RETURN")
    )
    private void afterBackground(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }
    *///?}

}