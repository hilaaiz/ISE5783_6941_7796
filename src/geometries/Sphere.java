package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

public class Sphere extends RadialGeometry {

    /*the center point of the sphere*/
    private Point center;


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
        Point p0 = ray.getP0();
        Vector v = ray.getDirection();

        if(p0.equals(center))
            return List.of(center.add(v.scale(radius)));

        Vector u = center.subtract(p0);

        double tm = alignZero(v.dotProduct(u));
        double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));

        //the ray direction is above the sphere
        if(d>=radius)
            return null;

        //the ray is outside the sphere
        double th = alignZero(Math.sqrt(radius*radius -d*d));
        if (th<=0)
            return null;

        double t1 = alignZero(tm + th);
        double t2 = alignZero(tm - th);

        if (t1 > 0 && t2 > 0)
        {
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);
            return List.of(p1,p2);
        }
        if (t1 > 0)
        {
            Point p1 = ray.getPoint(t1);
            return List.of(p1);
        }
        if (t2 > 0)
        {
            Point p2 = ray.getPoint(t2);
            return List.of(p2);
        }

        return null;

    }

}
