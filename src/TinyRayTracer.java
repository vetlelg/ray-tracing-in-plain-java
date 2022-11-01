import static java.lang.Math.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TinyRayTracer {
    public static Vector3 castRay(Vector3 a, Vector3 c, Sphere sphere) {
        if (sphere.rayIntersect(a, c)) {
            return new Vector3(0.9, 0.1, 0.8);
        }
        return new Vector3(0.9, 0.9, 0.3);
    }
    public static void render(Sphere sphere) {
        final int width = 1024;
        final int height = 768;
        final double fov = PI/2.0;
        Vector3[] framebuffer = new Vector3[width * height];

        // load image to memory
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                double x = (2*(i + 0.5)/(double)width - 1)*tan(PI/2.0)*width/(double)height;
                double y = -(2*(j + 0.5)/(double)width - 1)*tan(PI/2.0);

                Vector3 b = Vector3.normalize(new Vector3(x, y, -1));
                Vector3 a = new Vector3(0, 0, 0);
                framebuffer[i+j*width] = castRay(a, b, sphere);
                // framebuffer[i+j*width] = new Vector3(j / (double) height, i / (double) width, 0);
            }
        }

        // save image to disk
        try {
            FileOutputStream out = new FileOutputStream("out.ppm");
            out.write(("P6\n" + width + " " + height + "\n255\n").getBytes(StandardCharsets.UTF_8));
            for (int i = 0; i < height * width; i++) {
                for (int j = 0; j < 3; j++) {
                    out.write((byte) (255 * max(0.0, min(1.0, framebuffer[i].getXYZValue(j)))));
                }
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Sphere sphere = new Sphere(new Vector3(-3, 0, -16), 2);
        render(sphere);
    }
}


