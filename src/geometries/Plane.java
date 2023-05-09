package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.*;

public class Plane extends Geometry {

    final private Point P0;
    final private Vector normal;


    /**
     * ctr for Plane
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1, Point p2, Point p3) {
        Vector v1 = p2.subtract(p1); //vector from p1 towards p2
        Vector v2 = p3.subtract(p1); //vector from p1 towards p3
        normal = v1.crossProduct(v2).normalize(); //v1.crossProduct(v2).normalize();
        P0= p1;
    }


    /**
     * ctr for Plane
     * @param p
     * @param normal
     */
    public Plane (Point p,Vector normal ){
        P0= p;
        this.normal=normal.normalize();
    }


    /**
     * get p0
     * @return
     */
    public Point getP0() {
        return P0;
    }


    /**
     * get the normal to the plane
     * @return
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "P0=" + P0 +
                ", normal=" + normal +
                '}';
    }



    /**
     * Finds all intersection GeoPoints of a ray and a geometric entity
     *
     * @param ray the ray that intersect with the geometric entity.
     * @return list of intersection Geopoints.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if(this.P0.equals(ray.getP0())) //ray starts at the reference point of the plane
            return null;

        Vector vecFromRayToP0 = this.P0.subtract(ray.getP0());
        double numerator = this.normal.dotProduct(vecFromRayToP0);
        if(isZero(numerator)) // ray starts on the plane
            return null;

        double denominator = this.normal.dotProduct(ray.getDirection());
        if(isZero(denominator)) // ray is parallel to the plane
            return null;

        double t = numerator / denominator;
        if(t < 0 ) // ray starts after the plane
            return null;

        //List<Point> intersections = new LinkedList<>();
        //intersections.add(ray.getPoint(t));

        return List.of(new GeoPoint(this, ray.getPoint(t))); //צורת בניה שביקשו במקום מה שלמעלה
    }
}
