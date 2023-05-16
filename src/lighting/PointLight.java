package lighting;

import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

/**
 * Realization of the light source point light
 */
public class PointLight extends Light implements LightSource{

    /**
     * The location of the lighting in the scene
     */
    protected Point position;


    private Double3 kC = new Double3(1);//Attenuation coefficient
    private Double3 kL = Double3.ZERO;//Attenuation coefficient
    private Double3 kQ = Double3.ZERO;//Attenuation coefficient


    /**
     * ctr for PointLight
     * @param intensity
     * @param position
     */
    protected PointLight(Color intensity,Point position) {
        super(intensity);
        this.position=position;
    }

//region setters
    /**
     * set function for kC
     * Builder pattern
     * @param kC
     * @return
     */
    public PointLight setkC(Double3 kC) {
        this.kC = kC;
        return this;
    }

    /**
     * set function for kL
     * Builder pattern
     * @param kL
     * @return
     */
    public PointLight setkL(Double3 kL) {
        this.kL = kL;
        return this;
    }

    /**
     * set function for kQ
     * Builder pattern
     * @param kQ
     * @return
     */
    public PointLight setkQ(Double3 kQ) {
        this.kQ = kQ;
        return this;
    }

    public PointLight setkC(double kC) {
        this.kC = new Double3(kC);
        return this;
    }

    /**
     * set function for kL
     * Builder pattern
     * @param kL
     * @return
     */
    public PointLight setkL(double kL) {
        this.kL =new Double3(kL);
        return this;
    }

    /**
     * set function for kQ
     * Builder pattern
     * @param kQ
     * @return
     */
    public PointLight setkQ(double kQ) {
        this.kQ =new Double3(kQ);
        return this;
    }
//endregion


    /**
     * Models omni-directional point source (such as a bulb)
     * • Intensity (I0)
     * • Position (PL )
     * • Factors (kc ,kl , kq) for attenuation with distance (d)
     * @param p - point on the geometry for which the color is calculated
     * @return
     */
    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(this.position);
        return this.getIntensity().reduce(kC.add(kL.scale(d)).add(kQ.scale(d * d)));
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(this.position).normalize();
    }

    public double getDistance(Point point) {
        return point.distance(position);
    }
}
