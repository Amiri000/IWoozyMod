package sad.ami.woozy.client;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import sad.ami.woozy.Woozy;
import sad.ami.woozy.api.geo.manage.GeoModelManager;

import java.util.List;

@EventBusSubscriber(modid = Woozy.MODID, value = Dist.CLIENT)
public class ClientHandler {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        var resourceManager = Minecraft.getInstance().getResourceManager();

            for (var loc : resourceManager.listResources("geo/models/entity", path -> path.getPath().endsWith(".geo.json")).keySet())
                event.enqueueWork(() -> GeoModelManager.preload(loc));
    }
}
