package geometries;
import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;


/**
 * Interface for intersection a ray to geometry
 */
public abstract class Intersectable {

    /**
     * function for finding the intersection points between ray and the geometries
     * @param ray
     * @return
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * Finds all intersection GeoPoints of a ray and a geometric entity.
     *
     * @param ray the ray that intersect with the geometric entity.
     * @return list of intersection Geopoints.
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * Finds all intersection GeoPoints of a ray and a geometric entity
     *
     * @param ray  the ray that intersect with the geometric entity.
     * @return list of intersection Geopoints.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**
     * nested PDS class for Geometry & Point
     */
    public static class GeoPoint {

        /**
         * The type of geometry
         */
        public Geometry geometry;

        /**
         * The intersection points with the geometry
         */
        public Point point;

        /**
         * ctr for nested class
         * @param geometry
         * @param point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }



        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            return (o instanceof GeoPoint other)
                    && this.geometry.equals(other.geometry)
                    && this.point.equals(other.point);
        }

        @Override
        public String toString() {
            return  "GeoPoint:" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }


}
