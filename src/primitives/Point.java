package primitives;

import java.util.Objects;
import java.lang.Math;

public class Point {
    Double3 xyz;


    Point(Double3 xyz) {
        this.xyz = xyz;
    }
    public Point(double x ,double y, double z ){
        xyz=new Double3(x,y,z);
    }


    public Point add(Vector myVector){
        return new Point(xyz.add(myVector.xyz));
    }

    public Vector subtract(Point myPoint){
        return new Vector(xyz.subtract((myPoint).xyz));
    }

    public double distanceSquared(Point myPoint){
        Point p= new Point(xyz.subtract(myPoint.xyz));
        return ((p.xyz.d1*p.xyz.d1)+(p.xyz.d2*p.xyz.d2)+(p.xyz.d3*p.xyz.d3));
    }

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
