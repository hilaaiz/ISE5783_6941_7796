package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
}
