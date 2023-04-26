package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * represent a Circle in 3D
 */
public class Circle implements Geometry {
    Plane plane;
    Point center;
    double radius;

    public Circle(Point center, double radius, Vector normal) {
        this.center = center;
        this.radius = radius;
        plane = new Plane(center, normal.normalize());
    }

    @Override
    public Vector getNormal(Point point) {
        return this.plane.getNormal();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> planeIntersection = this.plane.findIntersections(ray);
        if(planeIntersection == null)
            return null;

        Point p = planeIntersection.get(0);

        if(alignZero(p.distanceSquared(this.center) - this.radius * this.radius) >= 0)
            return null;

        planeIntersection = new ArrayList<>();
        planeIntersection.add(p);
        return planeIntersection;
    }
}