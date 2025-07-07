package sad.ami.woozy.api.geo.manage;

import com.google.gson.Gson;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class GeoModelManager {
    public static final Map<ResourceLocation, GeoModel> CACHE = new HashMap<>();
    private static final Gson GSON = new Gson();

    public static void preload(ResourceLocation location) {
        if (!CACHE.containsKey(location)) {

            var model = load(location);

            if (model != null)
                CACHE.put(location, model);
        }
    }

    private static GeoModel load(ResourceLocation location) {
        try {
            var optional = Minecraft.getInstance().getResourceManager().getResource(location);

            if (optional.isEmpty()) {
                System.err.println("GeoModel not found: " + location);

                return null;
            }

            try (InputStream stream = optional.get().open()) {
                var model = GSON.fromJson(new InputStreamReader(stream), GeoModel.class);

                if (model == null)
                    System.err.println("GeoModel loaded but parsed as null: " + location);

                return model;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load geo model: " + location, e);
        }
    }

    public static GeoModel get(ResourceLocation location) {
        return CACHE.get(location);
    }
}
