package lighting;

import primitives.Color;
import primitives.Double3;


/**
 * Class that represents an ambient light.
 * Ambient light is an omnidirectional, fixed intensity and fixed color type of light.
 */
public class AmbientLight extends Light{


    /**
     * Constructor that takes a Color object and an attenuation coefficient (Double3) and return
     * the color object scaled by the attenuation coefficient.
     * @param Ia Color of the ambient light
     * @param Ka Attenuation coefficient
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        super(Ia.scale(Ka));
    }

    /**
     * Constructor that takes a Color object and an attenuation coefficient (double) and return
     * the color object scaled by the attenuation coefficient.
     * @param Ia Color of the ambient light
     * @param Ka Attenuation coefficient
     */
    public AmbientLight(Color Ia, double Ka) {
        super(Ia.scale(Ka));
    }

    /**
     * default AmbientLight
     * black color
     */
    public static final AmbientLight NONE=new AmbientLight(Color.BLACK,Double3.ZERO);


}
