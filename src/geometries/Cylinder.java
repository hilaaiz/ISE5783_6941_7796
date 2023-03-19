package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube {

    /**
     * height of the cylinder
     */
    private double height;



    /**
     * ctr for Cylinder
     * @param radius
     * @param axisRay
     * @param height
     */
    public Cylinder(double radius, Ray axisRay, double height) {
        super(radius, axisRay);
        this.height = height;
    }

    /**
     * get the height of the Cylinder
     * @return
     */
    public double getHeight() {
        return height;
    }


    public Vector getNormal(Point point) {
        return null;
    }


    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }
}
