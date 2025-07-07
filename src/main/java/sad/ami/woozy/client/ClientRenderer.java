package sad.ami.woozy.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import sad.ami.woozy.Woozy;
import sad.ami.woozy.api.geo.GeoRenderer;
import sad.ami.woozy.api.geo.util.ResourceAssetsSample;

public class ClientRenderer {
    private static final ResourceLocation SPRITE = ResourceLocation.fromNamespaceAndPath(Woozy.MODID, "textures/gui/babt.png");
    private static final int W = 4, H = 4, FRAMES = 3, FRAME_TICKS = 40;

    private static int frame = 0, tick = 0, direction = 1;
    private static float xPos = -1, lastMin = -1, lastMax = -1;

    public static void renderAnimatedSprite(GuiGraphics graphics) {
        var mc = Minecraft.getInstance();
        var pose = graphics.pose();

        pose.pushPose();

        var sw = mc.getWindow().getGuiScaledWidth();
        var sh = mc.getWindow().getGuiScaledHeight();

        var min = (sw - 182) / 2f + 3;
        var max = min + 8 * 20;

        var y = sh - H - 24;

        updatePosition(min, max);

        if (++tick >= FRAME_TICKS) {
            tick = 0;
            frame = (frame + 1) % FRAMES;
        }

        RenderSystem.enableBlend();

        pose.translate(xPos + 2, y + 2, 500);
        pose.scale(2,2,2);

        float angle = (System.currentTimeMillis() % 5000L) / 5000f * 360f;
        pose.mulPose(Axis.YP.rotationDegrees(angle));

        new GeoRenderer(pose, graphics.bufferSource(), new ResourceAssetsSample("test"), OverlayTexture.NO_OVERLAY, 0xF000F0)
                .drawModel();

        RenderSystem.disableBlend();

       pose.popPose();
    }

    private static void updatePosition(float min, float max) {
        if (lastMin != -1 && lastMax != -1 && (min != lastMin || max != lastMax))
            xPos = min + (xPos - lastMin) / (lastMax - lastMin) * (max - min);

        if (xPos == -1 || xPos < min || xPos > max) {
            xPos = min;
            direction = 1;
        }

        xPos += direction * 0.5f;

        if (xPos <= min || xPos >= max) {
            direction *= -1;
            xPos = Math.max(min, Math.min(max, xPos));
        }

        lastMin = min;
        lastMax = max;
    }
}
