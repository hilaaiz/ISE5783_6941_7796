package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {

    private List<Intersectable> intersectableGeometries;


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
    }//todo: לבדוק האם זה תקין הלולאה מבחינת יעילות


    //endregion

    @Override
    public List<Point> findIntersections(Ray ray) {

        List<Point> Intersections = null;

        for (Intersectable intersectable : this.intersectableGeometries) {
            List<Point> currentIntersection = intersectable.findIntersections(ray);
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

