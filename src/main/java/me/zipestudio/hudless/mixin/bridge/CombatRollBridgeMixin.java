package me.zipestudio.hudless.mixin.bridge;

import me.zipestudio.hudless.backend.HudAnimationHandler;
import me.zipestudio.hudless.config.HudElement;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "net.combat_roll.client.gui.HudRenderHelper", remap = false)
public class CombatRollBridgeMixin {

    @Inject(
            method = "render(Lnet/minecraft/client/gui/GuiGraphics;IIF)V",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private static void beforeHotbar(GuiGraphics guiGraphics, float tickDelta, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.HOTBAR, guiGraphics, ci);
    }

    @Inject(
            method = "render(Lnet/minecraft/client/gui/GuiGraphics;IIF)V",
            at = @At("RETURN"),
            remap = false
    )
    private static void afterHotbar(GuiGraphics guiGraphics, float tickDelta, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

}