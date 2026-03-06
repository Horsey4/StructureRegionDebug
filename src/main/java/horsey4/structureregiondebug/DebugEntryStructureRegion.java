package horsey4.structureregiondebug;

import net.minecraft.client.gui.components.debug.DebugEntryPosition;
import net.minecraft.client.gui.components.debug.DebugScreenDisplayer;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class DebugEntryStructureRegion implements DebugScreenEntry {
    public static final Identifier GROUP = DebugEntryPosition.GROUP;
    private static final int VILLAGE_SPACING = 34;
    private static final int VILLAGE_SEPERATION = 8;
    private static final int ANCIENT_CITY_SPACING = 24;
    private static final int ANCIENT_CITY_SEPERATION = 8;
    private static final int OCEAN_MONUMENT_SPACING = 32;
    private static final int OCEAN_MONUMENT_SEPERATION = 5;
    private static final int SHIPWRECK_SPACING = 24;
    private static final int SHIPWRECK_SEPERATION = 4;
    private static final int TRIAL_CHAMBER_SPACING = 34;
    private static final int TRIAL_CHAMBER_SEPERATION = 12;
    private static final int NETHER_COMPLEX_SPACING = 27;
    private static final int NETHER_COMPLEX_SEPERATION = 4;
    private static final int END_CITY_SPACING = 20;
    private static final int END_CITY_SEPERATION = 11;

    @Override
    public void display(DebugScreenDisplayer displayer, @Nullable Level level, @Nullable LevelChunk clientChunk, @Nullable LevelChunk serverChunk) {
        if (level == null || clientChunk == null) return;

        var pos = clientChunk.getPos();
        if (level.dimension() == Level.OVERWORLD) {
            displayer.addToGroup(GROUP, List.of(
                "Village region: " + getRegion(pos, VILLAGE_SPACING, VILLAGE_SEPERATION),
                "Ancient city region: " + getRegion(pos, ANCIENT_CITY_SPACING, ANCIENT_CITY_SEPERATION),
                "Ocean monument region: " + getRegion(pos, OCEAN_MONUMENT_SPACING, OCEAN_MONUMENT_SEPERATION),
                "Shipwreck region: " + getRegion(pos, SHIPWRECK_SPACING, SHIPWRECK_SEPERATION),
                "Trial chamber region: " + getRegion(pos, TRIAL_CHAMBER_SPACING, TRIAL_CHAMBER_SEPERATION)
            ));
        }
        else if (level.dimension() == Level.NETHER) {
            displayer.addToGroup(GROUP, "Nether complex region: " + getRegion(pos, NETHER_COMPLEX_SPACING, NETHER_COMPLEX_SEPERATION));
        }
        else if (level.dimension() == Level.END) {
            displayer.addToGroup(GROUP, "End city region: " + getRegion(pos, END_CITY_SPACING, END_CITY_SEPERATION));
        }
    }

    private String getRegion(ChunkPos pos, int spacing, int seperation) {
        var regionX = Math.floorDiv(pos.x, spacing);
        var regionZ = Math.floorDiv(pos.z, spacing);
        var size = spacing - seperation;

        return String.format(
            "%d %d (Extents: %d %d to %d %d)",
            regionX, regionZ,
            regionX * spacing, regionZ * spacing,
            regionX * spacing + size, regionZ * spacing + size
        );
    }
}