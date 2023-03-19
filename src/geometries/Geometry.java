package geometries;

import primitives.Point;
import primitives.Vector;

public interface Geometry {

    /**
     * get the normal of the geometry
     * @param p
     * @return
     */
    public Vector getNormal(Point p);
}
