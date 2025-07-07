package sad.ami.woozy.api.geo.util;

import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

public record VertexPos(float x, float y, float z) {

    public Vector4f toVec4f() {
        return new Vector4f(x, y, z, 1.0f);
    }

    public static List<VertexPos> generateCubeVertices(float ox, float oy, float oz, float sx, float sy, float sz) {
        float x2 = ox + sx;
        float y2 = oy + sy;
        float z2 = oz + sz;

        return makeVertices(ox, y2, oz, x2, y2, oz, x2, oy, oz, ox, oy, oz,
                x2, y2, z2, ox, y2, z2, ox, oy, z2, x2, oy, z2,
                ox, y2, z2, ox, y2, oz, ox, oy, oz, ox, oy, z2,
                x2, y2, oz, x2, y2, z2, x2, oy, z2, x2, oy, oz,
                ox, y2, z2, x2, y2, z2, x2, y2, oz, ox, y2, oz,
                ox, oy, oz, x2, oy, oz, x2, oy, z2, ox, oy, z2);
    }

    private static List<VertexPos> makeVertices(float... cors) {
        if (cors.length % 3 != 0)
            throw new IllegalArgumentException("Coordinates must be multiple of 3");

        var list = new ArrayList<VertexPos>(cors.length / 3);

        for (int i = 0; i < cors.length; i += 3)
            list.add(new VertexPos(cors[i], cors[i + 1], cors[i + 2]));

        return list;
    }
}