package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Class that represents a directional light, i.e.
 * light source with direction and without attenuation by distance.
 * This class extends the abstract class Light and the interface LightSource.
 */
public class DirectionalLight extends Light implements LightSource{

    /**
     * the direction vector of the directionalLight
     */
    private Vector direction;


    /**
     * constructor for DirectionalLight
     *
     * @param intensity
     */
    public DirectionalLight(Color intensity, Vector directionVector) {
        super(intensity);
        direction=directionVector.normalize();
    }


    /**
     *Returns the intensity of the light source on the point we received
     * @param point -the point that we calc the color for it
     * @return
     */
    @Override
    public Color getIntensity(Point point) {
        return this.getIntensity();
    }

    @Override
    public Vector getL(Point point) {
        return this.direction;
    }

    @Override
    public double getDistance(Point point) {
        //Since directional light doesn't have a real source and comes from infinity
        return Double.POSITIVE_INFINITY;
    }
}
