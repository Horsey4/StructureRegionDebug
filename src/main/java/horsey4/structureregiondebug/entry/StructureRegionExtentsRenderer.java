package horsey4.structureregiondebug.entry;

import horsey4.structureregiondebug.StructureSetInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.debug.DebugRenderer;
import net.minecraft.core.SectionPos;
import net.minecraft.gizmos.GizmoStyle;
import net.minecraft.gizmos.Gizmos;
import net.minecraft.util.ARGB;
import net.minecraft.util.debug.DebugValueAccess;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.phys.AABB;

public class StructureRegionExtentsRenderer implements DebugRenderer.SimpleDebugRenderer {
    private static final GizmoStyle CLOSE_STYLE = GizmoStyle.strokeAndFill(ARGB.color(0, 255, 0), 2.0f, ARGB.color(31, 0, 255, 0));
    private static final GizmoStyle FAR_STYLE = GizmoStyle.strokeAndFill(ARGB.color(0, 255, 255), 2.0f, ARGB.color(63, 0, 255, 255));

    private static int structureIndex = 0;
    private final Minecraft minecraft;

    public StructureRegionExtentsRenderer(Minecraft minecraft) {
        this.minecraft = minecraft;
    }

    public static void gotoNextStructure() {
        if (++structureIndex >= StructureSetInfo.STRUCTURES.length) structureIndex = 0;
    }

    public static StructureSetInfo getCurrentStructure() {
        return StructureSetInfo.STRUCTURES[structureIndex];
    }

    @Override
    public void emitGizmos(double camX, double camY, double camZ, DebugValueAccess values, Frustum frustum, float deltaTime) {
        var entity = minecraft.getCameraEntity();
        if (entity == null) return;

        var structure = getCurrentStructure();
        var pos = new ChunkPos(entity.blockPosition());
        var regionX = structure.getRegionPos(pos.x);
        var regionZ = structure.getRegionPos(pos.z);
        for (var x = -1; x <= 1; x++) {
            for (var z = -1; z <= 1; z++) {
                Gizmos.cuboid(new AABB(
                    SectionPos.sectionToBlockCoord(structure.getMinExtentPos(regionX + x)),
                    entity.getEyeY() - 16,
                    SectionPos.sectionToBlockCoord(structure.getMinExtentPos(regionZ + z)),
                    SectionPos.sectionToBlockCoord(structure.getMaxExtentPos(regionX + x), 16),
                    entity.getEyeY() + 16,
                    SectionPos.sectionToBlockCoord(structure.getMaxExtentPos(regionZ + z), 16)
                ), x == 0 && z == 0 ? CLOSE_STYLE : FAR_STYLE).setAlwaysOnTop();
            }
        }
    }
}