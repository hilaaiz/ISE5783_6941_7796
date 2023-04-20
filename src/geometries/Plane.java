package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Plane implements Geometry {

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
     * @param v
     */
    public Plane (Point p,Vector v ){
        P0= p;
        normal=v.normalize();
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


    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
