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
public class Settings {

    public static final Codec<Settings> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            option("enableMod", true, Codec.BOOL, Settings::isEnableMod),
            option("enableFading", true, Codec.BOOL, Settings::isEnableFading),
            option("visibleTicks", 100, Codec.INT, Settings::getVisibleTicks),
            option("hideSpeed", 0.2, Codec.DOUBLE, Settings::getHideSpeed),
            option("hideFadeSpeed", 0.05, Codec.DOUBLE, Settings::getHideFadeSpeed),
            option("maxY", 0, Codec.INT, Settings::getMaxY),
            option("minY", -50, Codec.INT, Settings::getMinY)
    ).apply(instance, Settings::new));

    private boolean enableMod;
    private boolean enableFading;
    private int visibleTicks;
    private double hideSpeed;
    private double hideFadeSpeed;
    private int maxY;
    private int minY;

    public static Settings getNewInstance() {
        return CodecUtils.parseNewInstanceHacky(CODEC);
    }
}