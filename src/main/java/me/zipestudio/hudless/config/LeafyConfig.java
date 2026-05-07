package me.zipestudio.hudless.config;

import lombok.*;
import me.zipestudio.hudless.HLServer;
import me.zipestudio.hudless.config.groups.Huds;
import me.zipestudio.hudless.config.groups.Settings;
import me.zipestudio.hudless.config.groups.Triggers;
import me.zipestudio.hudless.utils.CodecUtils;
import me.zipestudio.hudless.utils.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import static me.zipestudio.hudless.utils.CodecUtils.*;

@Getter
@AllArgsConstructor
public class LeafyConfig {

    // ===== CODEC =====
    public static final Codec<LeafyConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            option("settings", Settings.getNewInstance(), Settings.CODEC, LeafyConfig::getSettings),
            option("huds", Huds.getNewInstance(), Huds.CODEC, LeafyConfig::getHuds),
            option("triggers", Triggers.getNewInstance(), Triggers.CODEC, LeafyConfig::getTriggers)
    ).apply(instance, LeafyConfig::new));

    @Setter
    private Settings settings;
    private Huds huds;
    private Triggers triggers;

    private static final Logger LOGGER = LoggerFactory.getLogger(HLServer.MOD_NAME + "/Config");

    private static final File CONFIG_FILE = getConfigDir().resolve(HLServer.MOD_ID + ".json5").toFile();
    private static LeafyConfig INSTANCE;

    private LeafyConfig() { throw new IllegalArgumentException(); }

    public static LeafyConfig getInstance() {
        return INSTANCE == null ? reload() : INSTANCE;
    }

    public static LeafyConfig reload() {
        return INSTANCE = LeafyConfig.read();
    }

    public static LeafyConfig getNewInstance() {
        return CodecUtils.parseNewInstanceHacky(CODEC);
    }

    private static LeafyConfig read() {
        return ConfigUtils.readConfig(CODEC, CONFIG_FILE, LOGGER);
    }

    public void saveAsync() {
        CompletableFuture.runAsync(this::save);
    }

    public void save() {
        ConfigUtils.saveConfig(this, CODEC, CONFIG_FILE, LOGGER);
    }

    //? if fabric {
    public static Path getConfigDir() {
        return net.fabricmc.loader.api.FabricLoader.getInstance().getConfigDir();
    }
    //?} else if neoforge {
	/*public static Path getConfigDir() {
		return net.neoforged.fml.loading.FMLPaths.CONFIGDIR.get();
	}
	*///?}

}