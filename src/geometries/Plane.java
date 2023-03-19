package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {

    private Point P0;
    private Vector normal;




    /**
     * ctr for Plane
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1, Point p2, Point p3) {
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        this.normal = null; //v1.crossProduct(v2).normalize();
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
    public String toString() {
        return "Plane{" +
                "P0=" + P0 +
                ", normal=" + normal +
                '}';
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
