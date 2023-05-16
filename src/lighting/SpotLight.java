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
    protected SpotLight(Color intensity, Point position,Vector direction) {
        super(intensity, position);
        this.direction=direction.normalize();
    }



    /**
     * Models point light source with direction (such as a luxo lamp)
     * Intensity (I0)
     * Position (PL)
     * Direction dir (Vector) - normalized
     * Attenuation factors
     * @param p - point on the geometry for which the color is calculated
     * @return
     */
    @Override
    public Color getIntensity(Point p) {
        double lDir = alignZero(this.direction.dotProduct(super.getL(p)));
        if (lDir <= 0)
            return Color.BLACK;
        return super.getIntensity(p).scale(lDir);
    }

    @Override
    public Vector getL(Point p) {
        return super.getL(p);
    }
}
