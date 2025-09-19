package me.zipestudio.hudless.mixin;

import me.zipestudio.hudless.backend.HudAnimationHandler;
import me.zipestudio.hudless.config.HudElement;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "net.minecraft.client.gui.hud.bar.LocatorBar")
public class LocatorBarMixin {

    //? if >=1.21.6 {
    @Inject(
            method = "renderAddons(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void hideLocatorAddonsWithInfoBar(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (HudAnimationHandler.beforeInject(HudElement.INFO_BAR, context, HudElement.INFO_BAR.isTranslate())) {
            return;
        }

        ci.cancel();
    }

    @Inject(
            method = "renderAddons(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V",
            at = @At("RETURN")
    )
    private void restoreMatrixState(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (HudElement.currentElement != HudElement.INFO_BAR) {
            return;
        }

        HudAnimationHandler.afterInject(context);
    }
    //?}

}