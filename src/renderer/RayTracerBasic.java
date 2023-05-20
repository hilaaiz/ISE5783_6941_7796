package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

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
        return calcColor(geoPoint,ray);
    }


    /**
     * calculates the color of given point
     * @param intersection the point to calculate the color of it
     * @return the color that was calculated
     */
    private Color calcColor(GeoPoint intersection,Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(calcLocalEffects(intersection, ray));
    }


    /**
     * Calculating the local lighting effects of different light sources
     *
     * @param intersection the intersection point of the ray parameter with the geometric body.
     *                     This method calculate the light intensity at this point.
     * @param ray          the ray that intersects the geometric body
     * @return
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {

        Color color = intersection.geometry.getEmission();

        Vector v = ray.getDirection (); Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v)); if (nv == 0) return color;
        Material material = intersection.geometry.getMaterial();

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color iL = lightSource.getIntensity(intersection.point);
                color = color.add(iL.scale(calcDiffusive(material, nl)),
                        iL.scale(calcSpecular(material, n, l, nl, v)));
            }
        }

        return color;
    }

    /**
     * Calculates the diffusive effect
     *
     * @param material material of the geometric object
     * @param nl       the dot product of the light source ray direction and the normal to the geometric object at the point
     * @return the diffusive effect expressed by Double3 object
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(nl > 0 ? nl : -nl);
    }

    /**
     * Calculates the specular effect
     *
     * @param material material of the geometric object
     * @param n        the normal to the geometric object at the point
     * @param l        the ray of the light source
     * @param nl       the dot product of the light source ray direction and the normal to the geometric object at the point
     * @param v        the direction of the camera ray
     * @return the specular effect expressed by Double3 object
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        //the reflection of the light source vector (l)
        Vector r = l.subtract(n.scale(2 * nl));
        double minusVR = alignZero(-v.dotProduct(r));
        if (minusVR <= 0)
            return Double3.ZERO;

        //Calculation of the effect according to phong model
        return material.kS.scale(Math.pow(minusVR, material.Shininess));
    }

}
