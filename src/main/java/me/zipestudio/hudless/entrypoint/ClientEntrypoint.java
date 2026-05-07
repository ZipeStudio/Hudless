package me.zipestudio.hudless.entrypoint;

//? if fabric {

import me.zipestudio.hudless.client.HLClient;
import net.fabricmc.api.ClientModInitializer;

public class ClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		HLClient.onInitializeClient();
	}

}

//?} elif neoforge {

/*import me.zipestudio.hudless.HLServer;
import me.zipestudio.hudless.client.HLClient;
import me.zipestudio.hudless.utils.modmenu.ModMenuIntegration;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = HLServer.MOD_ID, dist = Dist.CLIENT)
public class ClientEntrypoint {

	public ClientEntrypoint(ModContainer container) {
		HLClient.onInitializeClient();
		ModMenuIntegration integration = new ModMenuIntegration();
		integration.register(container);
	}

}

*///?}
