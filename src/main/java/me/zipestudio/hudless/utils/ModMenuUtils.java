package me.zipestudio.hudless.utils;

import me.zipestudio.hudless.HLServer;
import me.zipestudio.hudless.utils.yacl.utils.SimpleContent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.function.Function;

public class ModMenuUtils {

	public static String getOptionKey(String optionId) {
		return String.format("modmenu.option.%s", optionId);
	}

	public static String getCategoryKey(String categoryId) {
		return String.format("modmenu.category.%s", categoryId);
	}

	public static String getGroupKey(String groupId) {
		return String.format("modmenu.group.%s", groupId);
	}

	public static Component getName(String key) {
		return HLServer.text(key + ".name");
	}

	public static Component getDescription(String key) {
		return HLServer.text(key + ".description");
	}

	public static Identifier getContentId(SimpleContent content, String contentId) {
		return HLServer.id(String.format("textures/config/%s.%s", contentId, content.getFileExtension()));
	}

	public static Component getModTitle() {
		return HLServer.text("modmenu.title");
	}

	public static Function<Boolean, Component> getEnabledOrDisabledFormatter() {
		return state -> HLServer.text("modmenu.formatter.enabled_or_disabled." + state);
	}

	public static Component getNoConfigScreenMessage() {
		return HLServer.text("modmenu.no_config_library_screen.message");
	}

	public static Component getOldConfigScreenMessage(String version) {
		return HLServer.text("modmenu.old_config_library_screen.message", version, HLServer.YACL_DEPEND_VERSION);
	}
}