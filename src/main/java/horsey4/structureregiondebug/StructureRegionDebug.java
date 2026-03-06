package horsey4.structureregiondebug;

import net.fabricmc.api.ClientModInitializer;

import net.minecraft.client.gui.components.debug.DebugScreenEntries;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StructureRegionDebug implements ClientModInitializer {
	public static final String MOD_ID = "structureregiondebug";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		DebugScreenEntries.register(Identifier.fromNamespaceAndPath(MOD_ID, "structure_region"), new DebugEntryStructureRegion());
		LOGGER.info("Debug entry registered");
	}
}