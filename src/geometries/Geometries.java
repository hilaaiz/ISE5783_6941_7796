package geometries;

import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {

    private final List<Intersectable> intersectGeometries;



    public Geometries() {
        intersectGeometries = new LinkedList<>();
    }


    /**
     * A constructor that accepts an unlimited number of intersections
     * @param geometries
     */
    public Geometries(Intersectable... geometries) {
        this.intersectGeometries = new LinkedList<>();
        this.add(geometries);
    }


    /**
     *  concatenate a collection of geometries to the existing collection
     * @param geometries  the collection to concat
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geometry : geometries) {
            intersectGeometries.add(geometry);
        }
    }


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        List<GeoPoint> Intersections = null;

        for (Intersectable intersectable : this.intersectGeometries) {
            List<GeoPoint> currentIntersection = intersectable.findGeoIntersectionsHelper(ray,maxDistance);
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

