package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Tube extends RadialGeometry{

    /**
     * axis ray of the tube
     */
    protected Ray axisRay;

    //private double radius




    /**
     * ctr for Tube
     * @param radius
     * @param axisRay
     */
    public Tube(double radius, Ray axisRay) {
        super(radius);
        this.axisRay = axisRay;
    }

    /**
     * get the axis ray
     * @return
     */
    public Ray getAxisRay() {
        return axisRay;
    }


    @Override
    public Vector getNormal(Point P) {
        Point P0= axisRay.getP0();
        Vector v= axisRay.getDirection();

        Vector P0toP= P.subtract(P0);
        double t= alignZero(P0toP.dotProduct(v));

        if (isZero(t))
            return P0toP.normalize();

        Point O= P0.add(v.scale(t));
        Vector OtoP= P.subtract(O); //TODO: CHECK LATER the direction
        return OtoP.normalize();

    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    @Override
    public List<Point> findIntersections(Ray ray) {

        // solve for t : At^2 + Bt + C = 0
        // axisRay params: pa,va, ray params: p,vr

        Vector vr = ray.getDirection();
        Vector va= axisRay.getDirection();
        if (vr.equals(va) || vr.equals(va.scale(-1))) //ray parallel to axisRay
            return null;
        double vrDotVa = vr.dotProduct(va);//to calculate t1 t2 later

        if (ray.getP0().equals(axisRay.getP0())) { //ray start on axis's head point
            if (isZero(vrDotVa)) //ray also orthogonal to axis
                return List.of(ray.getPoint(radius));
            double t = radius / (vr.subtract(va.scale(vrDotVa)).length());
            return List.of(ray.getPoint(t));
        }

        Vector vecDeltaP = ray.getP0().subtract(axisRay.getP0());
        double deltaPDotVa = vecDeltaP.dotProduct(va);
        if (va.equals(vecDeltaP.normalize()) || va.equals(vecDeltaP.normalize())) { //ray start along axis
            if (isZero(vrDotVa)) //ray also orthogonal to axis
                return List.of(ray.getPoint(radius));
            double t = radius / (vr.subtract(va.scale(vrDotVa)).length());
            return List.of(ray.getPoint(t));
        }

        // is either of the vectors, v or deltaP, orthogonal to the vector va-
        // We don't need the multiplier, we'll use them themselves
        Vector v1 = isZero(vrDotVa) ? vr : vr.subtract(va.scale(vrDotVa));
        Vector v2 = isZero(deltaPDotVa) ? vecDeltaP : vecDeltaP.subtract(va.scale(deltaPDotVa));//todo: orthogonal

        // A = (vr - (vr,va)va)^2
        // B = 2(vr-(vr,va)va , deltaP-(deltaP,va)va)
        // C = (deltaP - (deltaP,va)va)^2 - r^2
        // where: deltaP: p-pa , (x,y): dot product

        double A = v1.lengthSquared();
        double B = v1.dotProduct(v2) * 2;
        double C = v2.lengthSquared() - radius * radius;

        double discriminant = B * B - 4 * A * C; //in order to solve the quadratic equation
        if (discriminant <= 0)
            return null; // ray doesn't intersect at all OR is tangent to tube

        double t1 = alignZero((-B - Math.sqrt(discriminant)) / (2 * A));
        double t2 = alignZero((-B + Math.sqrt(discriminant)) / (2 * A));
        if (t1 > 0 && t2 > 0)
            return List.of(ray.getPoint(t1), ray.getPoint(t2));
        if (t1 > 0)
            return List.of(ray.getPoint(t1));
        if (t2 > 0)
            return List.of(ray.getPoint(t2));

        // The points are on the line that is equations
        // but not on the ray that has a specific starting point
        return null;

    }


}
