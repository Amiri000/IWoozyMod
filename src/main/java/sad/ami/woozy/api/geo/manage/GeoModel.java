package sad.ami.woozy.api.geo.manage;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeoModel {
    @SerializedName("minecraft:geometry")
    public List<Geometry> minecraft_geometry;

    public static class Geometry {
        public Description description;
        public List<Bone> bones;
    }

    public static class Description {
        public String identifier;

        @SerializedName("texture_width")
        public int texture_width;

        @SerializedName("texture_height")
        public int texture_height;

        @SerializedName("visible_bounds_width")
        public float visible_bounds_width;

        @SerializedName("visible_bounds_height")
        public float visible_bounds_height;

        @SerializedName("visible_bounds_offset")
        public List<Float> visible_bounds_offset;
    }

    public static class Bone {
        public String name;
        public List<Float> pivot;
        public List<Float> rotation;
        public List<Cube> cubes;
        public Boolean mirror;
    }

    public static class Cube {
        public List<Float> origin;
        public List<Float> size;
        public Float inflate;
        public Boolean mirror;
        public List<Float> rotation;
        public List<Float> pivot;

        @SerializedName("uv")
        public FaceUV uv_faces;
    }

    public static class FaceUV {
        public Face north;
        public Face east;
        public Face south;
        public Face west;
        public Face up;
        public Face down;
    }

    public static class Face {
        public List<Float> uv;

        @SerializedName("uv_size")
        public List<Float> uv_size;
    }
}
