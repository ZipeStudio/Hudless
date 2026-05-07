package me.zipestudio.hudless.config.groups;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import me.zipestudio.hudless.utils.CodecUtils;
import static me.zipestudio.hudless.utils.CodecUtils.*;

@Getter
@Setter
@AllArgsConstructor
public class Huds {

    public static final Codec<Huds> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            option("hideCrosshair", true, Codec.BOOL, Huds::isHideCrosshair),
            option("hideHotbar", true, Codec.BOOL, Huds::isHideHotbar),
            option("hideEffects", true, Codec.BOOL, Huds::isHideEffects),
            option("hideScoreboard", true, Codec.BOOL, Huds::isHideScoreboard),
            option("hideBossBar", true, Codec.BOOL, Huds::isHideBossBar),
            option("hideChat", true, Codec.BOOL, Huds::isHideChat),
            option("hideSubtitles", false, Codec.BOOL, Huds::isHideSubtitles),
            option("hideStatusBar", true, Codec.BOOL, Huds::isHideStatusBar),
            option("hideProgressBar", true, Codec.BOOL, Huds::isHideProgressBar)
    ).apply(instance, Huds::new));

    private boolean hideCrosshair;
    private boolean hideHotbar;

    private boolean hideEffects;
    private boolean hideScoreboard;
    private boolean hideBossBar;

    private boolean hideChat;
    private boolean hideSubtitles;

    private boolean hideStatusBar;
    private boolean hideProgressBar;


    public static Huds getNewInstance() {
        return CodecUtils.parseNewInstanceHacky(CODEC);
    }
}