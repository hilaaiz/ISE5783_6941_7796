package geometries;
import primitives.Point;
import primitives.Ray;

import java.util.List;


/**
 * Interface for intersection a ray to geometry
 */
public interface Intersectable {

    /**
     * function for finding the intersection points between ray and the geometries
     * @param ray
     * @return
     */
    List<Point> findIntersections(Ray ray);

}
