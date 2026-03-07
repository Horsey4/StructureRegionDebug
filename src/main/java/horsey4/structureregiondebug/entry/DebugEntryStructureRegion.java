package horsey4.structureregiondebug.entry;

import horsey4.structureregiondebug.StructureSetInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.debug.DebugEntryPosition;
import net.minecraft.client.gui.components.debug.DebugScreenDisplayer;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import org.jspecify.annotations.Nullable;

public class DebugEntryStructureRegion implements DebugScreenEntry {
    @Override
    public void display(DebugScreenDisplayer displayer, @Nullable Level level, @Nullable LevelChunk clientChunk, @Nullable LevelChunk serverChunk) {
        var entity = Minecraft.getInstance().getCameraEntity();
        if (level == null || entity == null) return;

        var pos = new ChunkPos(entity.blockPosition());
        for (var structure : StructureSetInfo.STRUCTURES) {
            if (level.dimension() != structure.dimension()) continue;

            var regionX = structure.getRegionPos(pos.x);
            var regionZ = structure.getRegionPos(pos.z);
            displayer.addToGroup(DebugEntryPosition.GROUP, String.format(
                "%s region: %d %d (Extents: %d %d to %d %d)",
                structure.name(),
                regionX, regionZ,
                structure.getMinExtentPos(regionX), structure.getMinExtentPos(regionZ),
                structure.getMaxExtentPos(regionX), structure.getMaxExtentPos(regionZ)
            ));
        }
    }
}