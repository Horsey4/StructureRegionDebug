package horsey4.structureregiondebug;

import com.mojang.blaze3d.platform.InputConstants;
import horsey4.structureregiondebug.entry.StructureRegionDebugEntries;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StructureRegionDebug implements ClientModInitializer {
	public static final String MOD_ID = "structureregiondebug";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final KeyMapping KEY_SHOW_EXTENTS = KeyBindingHelper.registerKeyBinding(new KeyMapping(
		"key.structureregiondebug.showExtents",
		InputConstants.Type.KEYSYM,
		GLFW.GLFW_KEY_R,
		KeyMapping.Category.DEBUG
	));

	@Override
	public void onInitializeClient() {
		StructureRegionDebugEntries.initialize();
		LOGGER.info("Debug entries registered");
	}
}