import static java.lang.Math.sqrt;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.tan;

public class Vector3 {
    private double x, y, z;
    Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }

    public double getXYZValue(int i) { return i <= 0 ? x : (1 == i ? y : z); }
    public double abs() {
        return sqrt(x*x + y*y + z*z);
    }
    public static double scalarProduct(Vector3 a, Vector3 b) { return a.x*b.x + a.y*b.y + a.z*b.z; }
    public static Vector3 crossProduct(Vector3 a, Vector3 b) {
        return new Vector3(a.y*b.z - a.z*b.y, a.z*b.x - a.x*b.z, a.x*b.y - a.y*b.x);
    }
    public static Vector3 vectorBetweenPoints(Vector3 a, Vector3 b) {
        return new Vector3(b.x - a.x, b.y - a.y, b.z - a.z);
    }
    public static double distanceBetweenPointAndLine(Vector3 a, Vector3 b) {
        return crossProduct(a, b).abs() / b.abs();
    }
    public static Vector3 normalize(Vector3 a) {
        double abs = a.abs();
        return new Vector3(a.x / abs, a.y / abs, a.z / abs);
    }
}
