package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.alignZero;

public class SpotLight extends PointLight{

    /**
     * The light direction of the spotlight
     */
    private Vector direction;


    /**
     * ctr for PointLight
     *
     * @param intensity
     * @param position
     */
    public SpotLight(Color intensity, Point position,Vector direction) {
        super(intensity, position);
        this.direction=direction.normalize();
    }



    /**
     * Models point light source with direction (such as a luxo lamp)
     * Intensity (I0)
     * Position (PL)
     * Direction dir (Vector) - normalized
     * Attenuation factors
     * @param point - point on the geometry for which the color is calculated
     * @return
     */
    @Override
    public Color getIntensity(Point point) {
        double lDir = alignZero(this.direction.dotProduct(super.getL(point)));
        if (lDir <= 0)
            return Color.BLACK;
        return super.getIntensity(point).scale(lDir);
    }

    @Override
    public Vector getL(Point point) {
        return super.getL(point);
    }
}
