package me.zipestudio.hudless.mixin.legacy;

//? if <1.21.6 {

/*import me.zipestudio.hudless.backend.HudAnimationHandler;
import net.minecraft.client.gui.Font;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Font.class)
public class FontMixin {

    //? if >=1.21.2 {

    @ModifyVariable(
            method = "renderText(Ljava/lang/String;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/gui/Font$DisplayMode;IIZ)F",
            at = @At("HEAD"),
            index = 4,
            argsOnly = true
    )
    private int injectInStringRender(int color) {
        return HudAnimationHandler.applyAlphaToColor(color);
    }

    @ModifyVariable(
            method = "renderText(Lnet/minecraft/util/FormattedCharSequence;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/gui/Font$DisplayMode;IIZ)F",
            at = @At("HEAD"),
            index = 4,
            argsOnly = true
    )
    private int injectInFormattedCharSequenceRender(int color) {
        return HudAnimationHandler.applyAlphaToColor(color);
    }

    //?} else {

    /^@ModifyVariable(
            method = "renderText(Ljava/lang/String;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/gui/Font$DisplayMode;II)F",
            at = @At("HEAD"),
            index = 4,
            argsOnly = true
    )
    private int injectInStringRender(int color) {
        return HudAnimationHandler.applyAlphaToColor(color);
    }

    @ModifyVariable(
            method = "renderText(Lnet/minecraft/util/FormattedCharSequence;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/gui/Font$DisplayMode;II)F",
            at = @At("HEAD"),
            index = 4,
            argsOnly = true
    )
    private int injectInFormattedCharSequenceRender(int color) {
        return HudAnimationHandler.applyAlphaToColor(color);
    }

    ^///?}

}

*///?}