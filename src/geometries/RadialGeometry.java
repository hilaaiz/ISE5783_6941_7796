package geometries;

import primitives.Point;
import primitives.Vector;

public abstract class RadialGeometry implements Geometry{

    /**
     * radius of the radial geometry
     */
    protected double radius;



    public abstract Vector getNormal(Point point);


    /**
     * ctr for RadialGeometry
     * @param radius
     */
    RadialGeometry(double radius){
        this.radius=radius;
    }

    /**
     * get the radius
     * @return
     */
    public double getRadius() {return radius;}

}
