package lighting;

import primitives.Color;


/**
 * An abstract class for the light sources in the scene
 */
abstract class Light {

    /**
     * The intensity of the light source
     */
    private Color intensity;


    /**
     * default constructor
     * @param intensity
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * get function for intensity
     * @return
     */
    public Color getIntensity() {
        return intensity;
    }


}
