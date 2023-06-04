package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * An interface for different kinds of lighting.
 * All of them should implement getIntensity and getL.
 */
public interface LightSource {
    /**
     * Returns the intensity of the light (color) of a light source in a specific point
     * @param point The point for the intensity calculation
     * @return Color object that represent the color (intensity) of the point
     */
    public Color getIntensity(Point point);

    /**
     * Returns the vector from the light source to the point
     * @param point The point which we want the vector to
     * @return Vector from the light source to the point
     */
    public Vector getL(Point point);

    /**
     * The distance from the received point to the light source
     * @param point
     * @return
     */
    public double getDistance(Point point);
}
