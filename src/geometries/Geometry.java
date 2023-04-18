package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public interface Geometry extends Intersectable {

    /**
     * get the normal of the geometry
     * @param p
     * @return
     */
    public Vector getNormal(Point p);

}
