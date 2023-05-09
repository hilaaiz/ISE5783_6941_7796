package renderer;

import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * A class that inherits from RayTracerBase to trace rays in a scene
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * RayTracerBasic Constructor.
     * @param scene the scene we trace rays in.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }


    /**
     * Trace the ray to find the color at the intersection point
     * @param ray the ray to find the color at the intersection point
     * @return the color at the intersection point
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint geoPoint=ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
        if (geoPoint==null)
            return scene.background;
        return calcColor(geoPoint);
    }


    /**
     * calculates the color of given point
     * @param geoPoint the point to calculate the color of it
     * @return the color that was calculated
     */
    private Color calcColor(GeoPoint geoPoint)
    {
        return scene.ambientLight.getIntensity().add(geoPoint.geometry.getEmission());
    }
}
