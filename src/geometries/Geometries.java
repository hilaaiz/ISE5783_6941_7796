package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {

    private final List<Intersectable> intersectableGeometries;


    //region default constructor
    public Geometries() {
        intersectableGeometries = new LinkedList<>();
    }
    //endregion


    //region constructor with Intersectable parameter
    public Geometries(Intersectable... geometries) {
        this.intersectableGeometries = new LinkedList<>();
        this.add(geometries);
    }
    //endregion


    //region add function

    /**
     *  concatenate a collection of geometries to the existing collection
     * @param geometries  the collection to concat
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geometry : geometries) {
            intersectableGeometries.add(geometry);
        }
    }

    /**
     * Finds all intersection GeoPoints of a ray and a geometric entity
     *
     * @param ray the ray that intersect with the geometric entity.
     * @return list of intersection Geopoints.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> Intersections = null;

        for (Intersectable intersectable : this.intersectableGeometries) {
            List<GeoPoint> currentIntersection = intersectable.findGeoIntersectionsHelper(ray);
            if (currentIntersection != null) {// intersection was found
                if (Intersections == null)
                    Intersections = new LinkedList<>();
                Intersections.addAll(currentIntersection);
            }
        }

        if (Intersections == null)
            return null;

        return Intersections;
    }

}

