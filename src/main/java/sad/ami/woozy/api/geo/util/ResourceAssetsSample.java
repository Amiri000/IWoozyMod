package sad.ami.woozy.api.geo.util;

import lombok.Data;
import net.minecraft.resources.ResourceLocation;
import sad.ami.woozy.Woozy;
import sad.ami.woozy.api.geo.manage.GeoModel;
import sad.ami.woozy.api.geo.manage.GeoModelManager;

@Data
public class ResourceAssetsSample {
    private ResourceLocation model;
    private ResourceLocation texture;

    public ResourceAssetsSample(String entity) {
        this.model = ResourceLocation.fromNamespaceAndPath(Woozy.MODID, "geo/models/entity/" + entity + ".geo.json");
        this.texture = ResourceLocation.fromNamespaceAndPath(Woozy.MODID, "textures/entity/" + entity + ".png");
    }

    public GeoModel getGeoModel() {
        return GeoModelManager.get(getModel());
    }
}