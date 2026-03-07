package horsey4.structureregiondebug;

import horsey4.structureregiondebug.entry.StructureRegionDebugEntries;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StructureRegionDebug implements ClientModInitializer {
	public static final String MOD_ID = "structureregiondebug";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		StructureRegionDebugEntries.initialize();
		LOGGER.info("Debug entries registered");
	}
}