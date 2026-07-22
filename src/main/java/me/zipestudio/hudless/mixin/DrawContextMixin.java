package me.zipestudio.hudless.mixin;

import me.zipestudio.hudless.backend.HudAnimationHandler;
//? if >=26.1 {
import net.minecraft.client.gui.GuiGraphicsExtractor;
//?} else {
/*import net.minecraft.client.gui.GuiGraphics;
*///?}
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

//? if >=26.1 {
@Mixin(GuiGraphicsExtractor.class)
//?} else {
/*@Mixin(GuiGraphics.class)
*///?}
public class DrawContextMixin {

    //? if >=26.1 {

    @ModifyVariable(
            method = "text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/util/FormattedCharSequence;IIIZ)V",
            at = @At("HEAD"),
            argsOnly = true,
            index = 5
    )
    private int modifyTextColor(int color) {
        return HudAnimationHandler.applyAlphaToColor(color);
    }

    @ModifyVariable(
            method = "innerBlit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIIFFFFI)V",
            at = @At("HEAD"),
            argsOnly = true,
            index = 11
    )
    private int modifyBlitColor(int color) {
        return HudAnimationHandler.applyAlphaToColor(color);
    }

    @ModifyVariable(
            method = "innerBlit(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lcom/mojang/blaze3d/textures/GpuTextureView;Lcom/mojang/blaze3d/textures/GpuSampler;IIIIFFFFI)V",
            at = @At("HEAD"),
            argsOnly = true,
            index = 12
    )
    private int modifyBlitColorTextureView(int color) {
        return HudAnimationHandler.applyAlphaToColor(color);
    }

    @ModifyVariable(
            method = "innerFill",
            at = @At("HEAD"),
            argsOnly = true,
            index = 7
    )
    private int modifySubmitColor(int color) {
        return HudAnimationHandler.applyAlphaToColor(color);
    }

    @ModifyVariable(
            method = "innerFill",
            at = @At("HEAD"),
            argsOnly = true,
            index = 8
    )
    private Integer modifySubmitSecondColor(Integer integer) {
        if (integer == null) return null;
        return HudAnimationHandler.applyAlphaToColor(integer);
    }

    //?} elif >=1.21.6 {

    /*@ModifyVariable(
            method = "drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/util/FormattedCharSequence;IIIZ)V",
            at = @At("HEAD"),
            argsOnly = true,
            index = 5
    )
    private int modifyTextColor(int color) {
        return HudAnimationHandler.applyAlphaToColor(color);
    }

    //? if >=1.21.11 {
    @ModifyVariable(
            method = "submitBlit",
            at = @At("HEAD"),
            argsOnly = true,
            index = 12
    )
    //?} else {
    /^@ModifyVariable(
            method = "submitBlit",
            at = @At("HEAD"),
            argsOnly = true,
            index = 11
    )
    ^///?}
    private int modifyBlitColor(int color) {
        return HudAnimationHandler.applyAlphaToColor(color);
    }

    @ModifyVariable(
            method = "submitColoredRectangle",
            at = @At("HEAD"),
            argsOnly = true,
            index = 7
    )
    private int modifySubmitColor(int color) {
        return HudAnimationHandler.applyAlphaToColor(color);
    }

    @ModifyVariable(
            method = "submitColoredRectangle",
            at = @At("HEAD"),
            argsOnly = true,
            index = 8
    )
    private Integer modifySubmitSecondColor(Integer integer) {
        if (integer == null) return null;
        return HudAnimationHandler.applyAlphaToColor(integer);
    }

    *///?} else {

    /*@ModifyVariable(
            method = "drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/util/FormattedCharSequence;IIIZ)I",
            at = @At("HEAD"),
            index = 5,
            argsOnly = true
    )
    private int modifyTextColorFormatted(int color) {
        return HudAnimationHandler.applyAlphaToColor(color);
    }

    @ModifyVariable(
            method = "drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)I",
            at = @At("HEAD"),
            index = 5,
            argsOnly = true
    )
    private int modifyTextColorComponent(int color) {
        return HudAnimationHandler.applyAlphaToColor(color);
    }

    //? if >=1.21.2 {
    @ModifyVariable(
            method = "innerBlit(Ljava/util/function/Function;Lnet/minecraft/resources/Identifier;IIIIFFFFI)V",
            at = @At("HEAD"),
            index = 11,
            argsOnly = true
    )
    private int modifyBlitColorLegacy(int color) {
        return HudAnimationHandler.applyAlphaToColor(color);
    }
    //?}

    @ModifyVariable(
            method = "fill(Lnet/minecraft/client/renderer/RenderType;IIIIII)V",
            at = @At("HEAD"),
            index = 7,
            argsOnly = true
    )
    private int modifyFillColor(int color) {
        return HudAnimationHandler.applyAlphaToColor(color);
    }

    *///?}

}
