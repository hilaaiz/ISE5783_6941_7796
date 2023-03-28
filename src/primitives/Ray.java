package primitives;

import java.util.Objects;

public class Ray {

    /**
     * The starting point of the ray
     */
    private Point p0;

    /**
     * The ray direction vector
     */
    private Vector dir;



    /**
     * ctr for ray
     * @param p
     * @param v
     */
    public Ray(Point p, Vector v){
        p0=p;
        dir=v.normalize();
    }

    /**
     * get the starting point of the ray
     * @return
     */
    public Point getP0() {
        return p0;
    }

    /**
     * get the direction vector of the ray
     * @return
     */
    public Vector getDirection() {
        return dir;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Ray other)
            return this.p0.equals(other.p0) && this.dir.equals(other.dir);
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0.toString() +
                ", dir=" + dir.toString() +
                '}';
    }
}
