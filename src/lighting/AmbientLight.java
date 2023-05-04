package lighting;

import primitives.Color;
import primitives.Double3;


/**
 * Class that represents an ambient light.
 * Ambient light is an omni-directional, fixed intensity and fixed color type of light.
 */
public class AmbientLight {

    Color intensity;

    /**
     * Constructor that takes a Color object and an attenuation coefficient (Double3) and return
     * the color object scaled by the attenuation coefficient.
     * @param Ia Color of the ambient light
     * @param Ka Attenuation coefficient
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        this.intensity = Ia.scale(Ka);
    }

    /**
     * Constructor that takes a Color object and an attenuation coefficient (double) and return
     * the color object scaled by the attenuation coefficient.
     * @param Ia Color of the ambient light
     * @param Ka Attenuation coefficient
     */
    public AmbientLight(Color Ia, double Ka) {
        this.intensity = Ia.scale(Ka);
    }

    /**
     * default AmbientLight
     * black color
     */
    public static final AmbientLight NONE=new AmbientLight(Color.BLACK,Double3.ZERO);

    /**
     * Intensity field getter
     * @return intensity (Color)
     */
    public Color getIntensity() {
        return intensity;
    }
}
