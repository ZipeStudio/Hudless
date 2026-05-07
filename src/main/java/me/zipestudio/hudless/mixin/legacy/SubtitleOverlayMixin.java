package me.zipestudio.hudless.mixin.legacy;

//? if <1.21.6 {

import me.zipestudio.hudless.backend.HudAnimationHandler;
import me.zipestudio.hudless.config.HudElement;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.SubtitleOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SubtitleOverlay.class)
public class SubtitleOverlayMixin {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void beforeInject(GuiGraphics guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.SUBTITLES, guiGraphics, ci);
    }
    @Inject(method = "render", at = @At("RETURN"))
    private void afterInject(GuiGraphics guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

}

//?}
