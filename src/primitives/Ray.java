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
     * ctr for ray
     * @param p
     * @param v
     */
    public Ray(Point p, Vector v){
        p0=p;
        dir=v.normalize();
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
     * @param lst List of points
     * @return Closest GeoPoint to the head of the ray
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> lst) {
        //In case of empty list return null
        if (lst == null) {
            return null;
        }

        GeoPoint p = null;
        double d = Double.POSITIVE_INFINITY;
        //Iterating through the list. Once we find smaller distance
        //than we have we replace the values.
        //This goes on until the end of the list.
        for (GeoPoint pnt : lst) {
            double distance = this.p0.distance(pnt.point);
            if (d > distance) {
                d = distance;
                p = pnt;
            }
        }

        return p;
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
