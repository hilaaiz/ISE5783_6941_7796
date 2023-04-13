package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube {

    /**
     * height of the cylinder
     */
    final private double height;


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


    // region getNormal
    @Override
    public Vector getNormal(Point point) {
        Point P0 = axisRay.getP0();
        if (point.distance(P0) <= radius)    // on the base circle of the cylinder.
            return axisRay.getDirection().scale(-1);

        Vector heightVector = axisRay.getDirection().scale(height);
        P0 = P0.add(heightVector);

        if (point.distance(P0) <= radius)    // on the second base circle of the cylinder.
            return axisRay.getDirection();

        return super.getNormal(point);              // on the casing of the cylinder.
    }
    //endregion


    @Override
    public String toString() {
        return "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius ;
    }
}
