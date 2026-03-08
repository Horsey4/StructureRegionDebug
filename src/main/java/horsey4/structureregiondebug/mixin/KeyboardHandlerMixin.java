package horsey4.structureregiondebug.mixin;

import horsey4.structureregiondebug.StructureRegionDebug;
import horsey4.structureregiondebug.entry.StructureRegionDebugEntries;
import horsey4.structureregiondebug.entry.StructureRegionExtentsRenderer;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.input.KeyEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(KeyboardHandler.class)
public abstract class KeyboardHandlerMixin {
    @Shadow
    @Final
    private Minecraft minecraft;

    @Shadow
    protected abstract void debugFeedbackTranslated(String string, Object... objects);

    @Inject(method = "handleDebugKeys", at = @At("TAIL"), cancellable = true)
    private void checkStructureRegionBorderRendererKeybinds(KeyEvent keyEvent, CallbackInfoReturnable<Boolean> cir) {
        if (StructureRegionDebug.KEY_SHOW_EXTENTS.matches(keyEvent)) {
            if (keyEvent.hasShiftDown() && minecraft.debugEntries.isCurrentlyEnabled(StructureRegionDebugEntries.STRUCTURE_REGION_EXTENTS)) {
                StructureRegionExtentsRenderer.gotoNextStructure();
                debugFeedbackTranslated("debug.structureregiondebug.extents.on", StructureRegionExtentsRenderer.getCurrentStructure().name());
            } else {
                var enabled = minecraft.debugEntries.toggleStatus(StructureRegionDebugEntries.STRUCTURE_REGION_EXTENTS);
                debugFeedbackTranslated(enabled ? "debug.structureregiondebug.extents.on" : "debug.structureregiondebug.extents.off",
                    StructureRegionExtentsRenderer.getCurrentStructure().name());
                cir.setReturnValue(true);
            }
        }
    }
}