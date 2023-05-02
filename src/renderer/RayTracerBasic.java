package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase {

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
        Point point=ray.findClosestPoint(scene.geometries.findIntersections(ray));
        if (point==null)
            return scene.background;
        return calcColor(point);
    }


    /**
     * calculates the color of given point
     * @param point the point to calculate the color of it
     * @return the color that was calculated
     */
    private Color calcColor(Point point)
    {
        return scene.ambientLight.getIntensity();
    }
}
