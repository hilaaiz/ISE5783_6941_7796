package primitives;

import java.util.Objects;
import java.lang.Math;

public class Point {
    Double3 xyz;

    /**
     * Constant object of Point at the center of our cartesian coordinate system
     */
    public static final Point ZERO = new Point(Double3.ZERO);

    /**
     * c-ctor
     * @param xyz
     */
    Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * c-ctor
     * @param x
     * @param y
     * @param z
     */
    public Point(double x ,double y, double z ){
        xyz=new Double3(x,y,z);
    }

    public double getX() {
        return xyz.d1;
    }

    public double getY() {
        return xyz.d2;
    }

    public double getZ() {
        return xyz.d3;
    }

    /**
     * return new point from this to myVector
     * @param myVector
     * @return
     */
    public Point add(Vector myVector){
        return new Point(xyz.add(myVector.xyz));
    }

    /**
     * add betwin 2 poins
     * @param x
     * @param y
     * @param z
     * @return
     */
    public Point add(double x, double y, double z){return new Point(x+xyz.d1,y+xyz.d2,z+xyz.d3);}

    /**
     * return vector from point to this
     * @param point
     * @return
     */
    public Vector subtract(Point point){
        return new Vector(xyz.subtract((point).xyz));
    }

    /**
     * return the distance Squared for 2 points
     * @param myPoint
     * @return
     */
    public double distanceSquared(Point myPoint){
        return  (xyz.d1 - myPoint.xyz.d1) * (xyz.d1 - myPoint.xyz.d1) +
                (xyz.d2 - myPoint.xyz.d2) * (xyz.d2 - myPoint.xyz.d2) +
                (xyz.d3 - myPoint.xyz.d3) * (xyz.d3 - myPoint.xyz.d3);
    }

    /**
     * return distance from this to myPoint
     * @param myPoint
     * @return
     */
    public double distance(Point myPoint){
        return Math.sqrt(distanceSquared(myPoint));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Point other)
            return this.xyz.equals(other.xyz);
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    @Override//לתקן
    public String toString() {
        return xyz.toString();
    }
}
