package me.zipestudio.hudless.mixin.fresh;

//? if >=1.21.6 {

/*import me.zipestudio.hudless.backend.HudAnimationHandler;
import me.zipestudio.hudless.config.HudElement;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.contextualbar.ExperienceBarRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExperienceBarRenderer.class)
public class ExperienceHudMixin {

    @Inject(method = "renderBackground", at = @At("HEAD"), cancellable = true)
    private void beforeBackground(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.PROGRESS_BAR, guiGraphics, ci);
    }

    @Inject(method = "renderBackground", at = @At("RETURN"))
    private void afterBackground(GuiGraphics graphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.afterInject(graphics);
    }

}

*///?}
