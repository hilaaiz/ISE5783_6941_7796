package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;

public class Ray {

    /**
     * The starting point of the ray
     */
    private final Point p0;

    /**
     * The ray direction vector
     */
    private final Vector dir;

    /**
     * We will move the ray head in the direction of the normal at a distance of delta
     */
    private static final double DELTA = 0.1;

    /**
     * ctr for ray
     * @param p
     * @param v
     */
    public Ray(Point p, Vector v){
        p0=p;
        dir=v.normalize();
    }

    /**
     * the head of the constructed ray will be moves by the direction vector multiplied by delta.
     * the direction ov the move with or against the normal vector determined by the dot product of the direction and normal.
     *
     * @param head      the point that the ray suppose to start with.
     * @param direction the direction of the ray.
     * @param normal    the direction to move the ray with.
     */
    public Ray(Point head, Vector direction, Vector normal) {
        double mult = direction.dotProduct(normal);
        this.p0 = head.add(normal.scale(mult >= 0 ?  DELTA : -DELTA));
        this.dir = direction;
    }

    /**
     * get the starting point of the ray
     * @return
     */
    public Point getP0() {
        return p0;
    }

    /**
     * get the direction vector of the ray
     * @return
     */
    public Vector getDirection() {
        return dir;
    }

    /**
     * returns the point on the ray that is in a given distance from the head of the ray
     * @param t -the distance from the head of the ray to point
     * @return the point that is in the given distance from the head of the ray
     */
    public Point getPoint(double t) {
        if (isZero(t)) return p0;
        return p0.add(dir.scale(t));
    }


    /**
     * Method for finding the closest point to the head of the ray in a given list of points
     * @param points List of points
     * @return Closest point to the head of the ray
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }


    /**
     * Method for finding the closest GeoPoint to the head of the ray in a given list of points
     * @param points List of points
     * @return Closest GeoPoint to the head of the ray
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {

        if (points == null) {
            return null;
        }

        GeoPoint geoPoint = null;
        double minDistance = Double.POSITIVE_INFINITY;
        //Iterating through the list. Once we find smaller distance
        //than we have we replace the values.
        //This goes on until the end of the list.
        for (GeoPoint gp : points) {
            double distance = this.p0.distanceSquared(gp.point);
            if (minDistance > distance) {
                minDistance = distance;
                geoPoint = gp;
            }
        }

        return geoPoint;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Ray other)
            return this.p0.equals(other.p0) && this.dir.equals(other.dir);
        return false;
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0.toString() +
                ", dir=" + dir.toString() +
                '}';
    }



}
