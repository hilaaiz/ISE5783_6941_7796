package geometries;

import primitives.Point;
import primitives.Vector;

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
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
