package me.zipestudio.hudless.mixin;

import me.zipestudio.hudless.backend.HudAnimationHandler;
import me.zipestudio.hudless.backend.HudConditionHandler;
import me.zipestudio.hudless.config.HudElement;
import net.minecraft.client.DeltaTracker;
//? if >=26.2 {
import net.minecraft.client.gui.Hud;
//?} else {
/*import net.minecraft.client.gui.Gui;
*///?}
//? if >=26.1 {
import net.minecraft.client.gui.GuiGraphicsExtractor;
//?} else {
/*import net.minecraft.client.gui.GuiGraphics;
*///?}
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.PlayerRideableJumping;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if >=26.2 {
@Mixin(Hud.class)
//?} else {
/*@Mixin(Gui.class)
*///?}
public abstract class GameHudMixin {

    @Inject(method = "tick()V", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        HudConditionHandler.tick();
    }

    //? if >=26.1 {

    @Inject(method = "extractRenderState", at = @At("HEAD"))
    private void onRender(GuiGraphicsExtractor guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.render(delta.getGameTimeDeltaPartialTick(false));
    }

    @Inject(method = "extractEffects", at = @At("HEAD"), cancellable = true)
    private void beforeEffects(GuiGraphicsExtractor guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.EFFECTS, guiGraphics, ci);
    }

    @Inject(method = "extractEffects", at = @At("RETURN"))
    private void afterEffects(GuiGraphicsExtractor guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(method = "extractScoreboardSidebar", at = @At("HEAD"), cancellable = true)
    private void beforeScoreboard(GuiGraphicsExtractor guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.SCOREBOARD, guiGraphics, ci);
    }

    @Inject(method = "extractScoreboardSidebar", at = @At("RETURN"))
    private void afterScoreboard(GuiGraphicsExtractor guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(method = "extractSelectedItemName", at = @At("HEAD"), cancellable = true)
    private void beforeSelectedItemName(GuiGraphicsExtractor guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.HOTBAR, guiGraphics, ci);
    }

    @Inject(method = "extractSelectedItemName", at = @At("RETURN"))
    private void afterSelectedItemName(GuiGraphicsExtractor guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(method = "extractItemHotbar", at = @At("HEAD"), cancellable = true)
    private void beforeHotbar(GuiGraphicsExtractor guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.HOTBAR, guiGraphics, ci);
    }

    @Inject(method = "extractItemHotbar", at = @At("RETURN"))
    private void afterHotbar(GuiGraphicsExtractor guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(method = "extractCrosshair", at = @At("HEAD"), cancellable = true)
    private void beforeCrosshair(GuiGraphicsExtractor guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.CROSSHAIR, guiGraphics, ci);
    }

    @Inject(method = "extractCrosshair", at = @At("RETURN"))
    private void afterCrosshair(GuiGraphicsExtractor guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(method = "extractVehicleHealth", at = @At("HEAD"), cancellable = true)
    private void beforeMountHealth(GuiGraphicsExtractor guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.STATUS_BAR, guiGraphics, ci);
    }

    @Inject(method = "extractVehicleHealth", at = @At("RETURN"))
    private void afterMountHealth(GuiGraphicsExtractor guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(method = "extractChat", at = @At("HEAD"), cancellable = true)
    private void beforeChat(GuiGraphicsExtractor guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.CHAT, guiGraphics, ci);
    }
    @Inject(method = "extractChat", at = @At("RETURN"))
    private void afterChat(GuiGraphicsExtractor guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(method = "extractBossOverlay", at = @At("HEAD"), cancellable = true)
    private void beforeBossOverlay(GuiGraphicsExtractor guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.BOSSBAR, guiGraphics, ci);
    }
    @Inject(method = "extractBossOverlay", at = @At("RETURN"))
    private void afterBossOverlay(GuiGraphicsExtractor guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(method = "extractSubtitleOverlay", at = @At("HEAD"), cancellable = true)
    private void beforeSubtitles(GuiGraphicsExtractor guiGraphics, boolean bl, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.SUBTITLES, guiGraphics, ci);
    }
    @Inject(method = "extractSubtitleOverlay", at = @At("RETURN"))
    private void afterSubtitles(GuiGraphicsExtractor guiGraphics, boolean bl, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(method = "extractPlayerHealth", at = @At("HEAD"), cancellable = true)
    private void beforeStatusBar(GuiGraphicsExtractor guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.STATUS_BAR, guiGraphics, ci);
    }
    @Inject(method = "extractPlayerHealth", at = @At("RETURN"))
    private void afterStatusBar(GuiGraphicsExtractor guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    //?} else {

    /*@Inject(method = "render", at = @At("HEAD"))
    private void onRender(GuiGraphics guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.render(delta.getGameTimeDeltaPartialTick(false));
    }

    @Inject(method = "renderEffects", at = @At("HEAD"), cancellable = true)
    private void beforeEffects(GuiGraphics guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.EFFECTS, guiGraphics, ci);
    }

    @Inject(method = "renderEffects", at = @At("RETURN"))
    private void afterEffects(GuiGraphics guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(method = "renderScoreboardSidebar", at = @At("HEAD"), cancellable = true)
    private void beforeScoreboard(GuiGraphics guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.SCOREBOARD, guiGraphics, ci);
    }

    @Inject(method = "renderScoreboardSidebar", at = @At("RETURN"))
    private void afterScoreboard(GuiGraphics guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(method = "renderSelectedItemName", at = @At("HEAD"), cancellable = true)
    private void beforeSelectedItemName(GuiGraphics guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.HOTBAR, guiGraphics, ci);
    }

    @Inject(method = "renderSelectedItemName", at = @At("RETURN"))
    private void afterSelectedItemName(GuiGraphics guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(method = "renderItemHotbar", at = @At("HEAD"), cancellable = true)
    private void beforeHotbar(GuiGraphics guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.HOTBAR, guiGraphics, ci);
    }

    @Inject(method = "renderItemHotbar", at = @At("RETURN"))
    private void afterHotbar(GuiGraphics guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(method = "renderCrosshair", at = @At("HEAD"), cancellable = true)
    private void beforeCrosshair(GuiGraphics guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.CROSSHAIR, guiGraphics, ci);
    }

    @Inject(method = "renderCrosshair", at = @At("RETURN"))
    private void afterCrosshair(GuiGraphics guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(method = "renderVehicleHealth", at = @At("HEAD"), cancellable = true)
    private void beforeMountHealth(GuiGraphics guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.STATUS_BAR, guiGraphics, ci);
    }

    @Inject(method = "renderVehicleHealth", at = @At("RETURN"))
    private void afterMountHealth(GuiGraphics guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(method = "renderChat", at = @At("HEAD"), cancellable = true)
    private void beforeChat(GuiGraphics guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.CHAT, guiGraphics, ci);
    }
    @Inject(method = "renderChat", at = @At("RETURN"))
    private void afterChat(GuiGraphics guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    //? if >=1.21.6 {

    @Inject(method = "renderBossOverlay", at = @At("HEAD"), cancellable = true)
    private void beforeBossOverlay(GuiGraphics guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.BOSSBAR, guiGraphics, ci);
    }
    @Inject(method = "renderBossOverlay", at = @At("RETURN"))
    private void afterBossOverlay(GuiGraphics guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    //? if >=1.21.9 {
    @Inject(method = "renderSubtitleOverlay", at = @At("HEAD"), cancellable = true)
    private void beforeSubtitles(GuiGraphics guiGraphics, boolean bl, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.SUBTITLES, guiGraphics, ci);
    }
    @Inject(method = "renderSubtitleOverlay", at = @At("RETURN"))
    private void afterSubtitles(GuiGraphics guiGraphics, boolean bl, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }
    //?} else {
    /^@Inject(method = "renderSubtitleOverlay", at = @At("HEAD"), cancellable = true)
    private void beforeSubtitles(GuiGraphics guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.SUBTITLES, guiGraphics, ci);
    }
    @Inject(method = "renderSubtitleOverlay", at = @At("RETURN"))
    private void afterSubtitles(GuiGraphics guiGraphics, DeltaTracker delta, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }
    ^///?}

    //?} else {
    /^@Inject(method = "renderJumpMeter", at = @At("HEAD"), cancellable = true)
    private void beforeJumpMeter(PlayerRideableJumping playerRideableJumping, GuiGraphics guiGraphics, int i, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.PROGRESS_BAR, guiGraphics, ci);
    }
    @Inject(method = "renderJumpMeter", at = @At("RETURN"))
    private void afterJumpMeter(PlayerRideableJumping playerRideableJumping, GuiGraphics guiGraphics, int i, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(method = "renderExperienceBar", at = @At("HEAD"), cancellable = true)
    private void beforeExperienceBar(GuiGraphics guiGraphics, int i, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.PROGRESS_BAR, guiGraphics, ci);
    }
    @Inject(method = "renderExperienceBar", at = @At("RETURN"))
    private void afterExperienceBar(GuiGraphics guiGraphics, int i, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(method = "renderExperienceLevel", at = @At("HEAD"), cancellable = true)
    private void beforeExperienceLevel(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.PROGRESS_BAR, guiGraphics, ci);
    }
    @Inject(method = "renderExperienceLevel", at = @At("RETURN"))
    private void afterExperienceLevel(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }
    ^///?}

    @Inject(method = "renderPlayerHealth", at = @At("HEAD"), cancellable = true)
    private void beforeStatusBar(GuiGraphics guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.STATUS_BAR, guiGraphics, ci);
    }
    @Inject(method = "renderPlayerHealth", at = @At("RETURN"))
    private void afterStatusBar(GuiGraphics guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    //? if neoforge {
    /^@Inject(method = "renderHealthLevel", at = @At("HEAD"), cancellable = true)
    private void health(GuiGraphics guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.STATUS_BAR, guiGraphics, ci);
    }
    @Inject(method = "renderHealthLevel", at = @At("RETURN"))
    private void health2(GuiGraphics guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(method = "renderArmorLevel", at = @At("HEAD"), cancellable = true)
    private void arm(GuiGraphics guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.STATUS_BAR, guiGraphics, ci);
    }
    @Inject(method = "renderArmorLevel", at = @At("RETURN"))
    private void arm2(GuiGraphics guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(method = "renderFoodLevel", at = @At("HEAD"), cancellable = true)
    private void food(GuiGraphics guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.STATUS_BAR, guiGraphics, ci);
    }
    @Inject(method = "renderFoodLevel", at = @At("RETURN"))
    private void food2(GuiGraphics guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }

    @Inject(method = "renderAirLevel", at = @At("HEAD"), cancellable = true)
    private void air(GuiGraphics guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.beforeInject(HudElement.STATUS_BAR, guiGraphics, ci);
    }
    @Inject(method = "renderAirLevel", at = @At("RETURN"))
    private void air2(GuiGraphics guiGraphics, CallbackInfo ci) {
        HudAnimationHandler.afterInject(guiGraphics);
    }
    ^///?}

    *///?}

}
