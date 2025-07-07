package sad.ami.woozy.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import sad.ami.woozy.Woozy;

public class ClientRenderer {
    private static final ResourceLocation SPRITE = ResourceLocation.fromNamespaceAndPath(Woozy.MODID, "textures/gui/babt.png");
    private static final int W = 4, H = 4, FRAMES = 3, FRAME_TICKS = 40;

    private static int frame = 0, tick = 0, direction = 1;
    private static float xPos = -1, lastMin = -1, lastMax = -1;

    public static void renderAnimatedSprite(GuiGraphics graphics) {
        var mc = Minecraft.getInstance();

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

        graphics.blit(SPRITE, (int) xPos, y, 0f, frame * H, W, H, W, H * FRAMES);

        RenderSystem.disableBlend();
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
