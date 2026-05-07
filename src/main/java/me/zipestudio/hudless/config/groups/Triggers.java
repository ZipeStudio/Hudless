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
public class Triggers {

    public static final Codec<Triggers> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            option("triggerHotbar", true, Codec.BOOL, Triggers::isTriggerHotbar),
            option("triggerHealth", true, Codec.BOOL, Triggers::isTriggerHealth),
            option("triggerHunger", true, Codec.BOOL, Triggers::isTriggerHunger),
            option("triggerArmor", true, Codec.BOOL, Triggers::isTriggerArmor),
            option("triggerAirBubbles", true, Codec.BOOL, Triggers::isTriggerAirBubbles),
            option("triggerEffects", true, Codec.BOOL, Triggers::isTriggerEffects),
            option("triggerMountHealth", true, Codec.BOOL, Triggers::isTriggerMountHealth),
            option("triggerExperience", false, Codec.BOOL, Triggers::isTriggerExperience)
    ).apply(instance, Triggers::new));

    private boolean triggerHotbar;
    private boolean triggerHealth;
    private boolean triggerHunger;
    private boolean triggerArmor;
    private boolean triggerAirBubbles;
    private boolean triggerEffects;
    private boolean triggerMountHealth;
    private boolean triggerExperience;

    public static Triggers getNewInstance() {
        return CodecUtils.parseNewInstanceHacky(CODEC);
    }
}