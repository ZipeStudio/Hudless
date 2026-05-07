package me.zipestudio.hudless.utils.modmenu;

import net.minecraft.client.gui.screens.Screen;
import me.zipestudio.hudless.config.YACLConfigurationScreen;

public class ModMenuIntegration extends AbstractModMenuIntegration {

	@Override
	protected Screen createConfigScreen(Screen parent) {
		return YACLConfigurationScreen.createScreen(parent);
	}

}