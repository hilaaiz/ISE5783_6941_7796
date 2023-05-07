package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

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
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = super.findIntersections(ray);

        List<Point> pointList = new ArrayList<>();

        if(intersections != null) {
            for (Point point : intersections) {
                double projection = point.subtract(axisRay.getP0()).dotProduct(axisRay.getDirection());
                if (alignZero(projection) > 0 && alignZero(projection - this.height) < 0)
                    pointList.add(point);
            }
        }

        // intersect with base
        Circle base = new Circle(axisRay.getP0(), radius, axisRay.getDirection());
        intersections = base.findIntersections(ray);
        if(intersections != null)
            pointList.add(intersections.get(0));

        base = new Circle(axisRay.getPoint(height), radius, axisRay.getDirection());
        intersections = base.findIntersections(ray);
        if(intersections != null)
            pointList.add( intersections.get(0));

        if (pointList.size() == 0)
            return null;
        return pointList;
    }

    @Override
    public String toString() {
        return "height=" + height +
                ", axisRay=" + axisRay +
                ", radius=" + radius ;
    }
}
