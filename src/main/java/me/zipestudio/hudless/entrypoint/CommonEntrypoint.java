package me.zipestudio.hudless.entrypoint;

//? if fabric {

import me.zipestudio.hudless.HLServer;

import net.fabricmc.api.ModInitializer;

public class CommonEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		HLServer.onInitialize();
	}

}

//?} elif neoforge {

/*import me.zipestudio.hudless.HLServer;
import net.neoforged.fml.common.Mod;

@Mod(HLServer.MOD_ID)
public class CommonEntrypoint {

	public CommonEntrypoint() {
		HLServer.onInitialize();
	}

}

*///?}
