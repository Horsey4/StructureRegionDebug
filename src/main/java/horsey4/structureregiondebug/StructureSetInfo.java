package horsey4.structureregiondebug;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

public record StructureSetInfo(ResourceKey<Level> dimension, String name, int spacing, int seperation) {
    public static final StructureSetInfo[] STRUCTURES = {
        new StructureSetInfo(Level.OVERWORLD, "Village", 34, 8),
        new StructureSetInfo(Level.OVERWORLD, "Ancient city", 24, 8),
        new StructureSetInfo(Level.OVERWORLD, "Ocean monument", 32, 5),
        new StructureSetInfo(Level.OVERWORLD, "Shipwreck", 24, 4),
        new StructureSetInfo(Level.OVERWORLD, "Trial chamber", 34, 12),
        new StructureSetInfo(Level.NETHER, "Nether complex", 27, 4),
        new StructureSetInfo(Level.END, "End city", 20, 11)
    };

    public int getRegionPos(int chunkPos) {
        return Math.floorDiv(chunkPos, spacing);
    }

    public int getMinExtentPos(int regionPos) {
        return regionPos * spacing;
    }

    public int getMaxExtentPos(int regionPos) {
        return getMinExtentPos(regionPos) + spacing - seperation - 1;
    }
}