package primitives;

import java.util.Objects;
import java.lang.Math;

public class Point {
    Double3 xyz;

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

    /**
     * return new point from this to myVector
     * @param myVector
     * @return
     */
    public Point add(Vector myVector){
        return new Point(xyz.add(myVector.xyz));
    }

    /**
     * return vector from myPoint to this
     * @param myPoint
     * @return
     */
    public Vector subtract(Point myPoint){
        return new Vector(xyz.subtract((myPoint).xyz));
    }

    /**
     * return the distance Squared for 2 points
     * @param myPoint
     * @return
     */
    public double distanceSquared(Point myPoint){
        Point p= new Point(xyz.subtract(myPoint.xyz));
        return ((p.xyz.d1*p.xyz.d1)+(p.xyz.d2*p.xyz.d2)+(p.xyz.d3*p.xyz.d3));
    }

    /**
     * return distance from this to myPoint
     * @param myPoint
     * @return
     */
    public double distance(Point myPoint){
        return Math.sqrt(distance(myPoint));
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
