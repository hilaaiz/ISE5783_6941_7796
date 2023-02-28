package primitives;

import java.util.Objects;

public class Point {
    protected Double3 xyz;


    public Point add(Vector myVector){

    }

    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(xyz, point.xyz);
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
