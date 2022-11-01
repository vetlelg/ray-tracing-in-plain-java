public class Sphere {
    private Vector3 c; // this point represent the center of sphere
    private double r; // radius of sphere

    public Sphere(Vector3 c, double r) {
        this.c = c;
        this.r = r;
    }

    // a is the origin point. v is the ray vector.
    public boolean rayIntersect(Vector3 a, Vector3 v) {
        // calculate ac vector. ac = c - a
        Vector3 ac = Vector3.vectorBetweenPoints(a, c);
        if (Vector3.distanceBetweenPointAndLine(ac, v) > r)
            return false;
        return true;
    }
}
