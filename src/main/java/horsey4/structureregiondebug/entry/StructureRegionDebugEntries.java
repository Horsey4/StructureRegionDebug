package horsey4.structureregiondebug.entry;

import horsey4.structureregiondebug.StructureRegionDebug;
import net.minecraft.client.gui.components.debug.DebugEntryNoop;
import net.minecraft.client.gui.components.debug.DebugScreenEntries;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.resources.Identifier;

public final class StructureRegionDebugEntries {
    public static final Identifier STRUCTURE_REGION = register("structure_region", new DebugEntryStructureRegion());
    public static final Identifier STRUCTURE_REGION_EXTENTS = register("structure_region_extents", new DebugEntryNoop());

    public static void initialize() {}

    private static Identifier register(String name, DebugScreenEntry entry) {
        return DebugScreenEntries.register(Identifier.fromNamespaceAndPath(StructureRegionDebug.MOD_ID, name), entry);
    }
}