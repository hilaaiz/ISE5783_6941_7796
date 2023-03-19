package geometries;

public abstract class RadialGeometry implements Geometry{

    /**
     * radius of the radial geometry
     */
    protected double radius;




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
