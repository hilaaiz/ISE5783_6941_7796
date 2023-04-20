package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Sphere extends RadialGeometry {

    /*the center point of the sphere*/
private Point center;

//private double radius




    /**
     * ctr for Sphere
     * @param radius
     * @param center
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * get the center point
     * @return
     */
    public Point getCenter() {
        return center;
    }



    @Override
    public Vector getNormal(Point p) {
        if(p.equals(center))
            throw new IllegalArgumentException("point can not be equals to the center of the sphere");
        Vector OP =p.subtract(center);
        return OP.normalize();//לשנות לשורה אחת
    }


    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }


    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
