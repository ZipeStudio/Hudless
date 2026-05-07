package me.zipestudio.hudless.mixin.fresh;

//? if >=1.21.6 {

/*import me.zipestudio.hudless.backend.HudAnimationHandler;
import me.zipestudio.hudless.config.HudElement;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.contextualbar.ContextualBarRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContextualBarRenderer.class)
public interface ContextualBarRendererMixin {

    @Inject(method = "renderExperienceLevel", at = @At("HEAD"), cancellable = true)
    private static void beforeExperienceLevel(GuiGraphics guiGraphics, Font font, int i, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.PROGRESS_BAR, guiGraphics, ci);
    }

    @Inject(method = "renderExperienceLevel", at = @At("RETURN"))
    private static void afterExperienceLevel(GuiGraphics guiGraphics, Font font, int i, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

}

*///?}