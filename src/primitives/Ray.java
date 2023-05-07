package primitives;

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


    //region findClosestGeoPoint
    /**
     * find the closest Point to the head of the ray
     * @param points a list of Points
     * @return the closest Point
     */
    public Point findClosestPoint(List<Point> points) {
        if (points == null)
            return null;

        Point closestPoint = points.get(0);
        double distance = closestPoint.distanceSquared(this.p0);

        for (Point point : points) {
            double d = point.distanceSquared(this.p0);
            if(distance > d)    // if there is a closer point then 'point', replace the values
            {
                closestPoint = point;
                distance = d;
            }
        }
        return closestPoint;
    }
    //endregion


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
