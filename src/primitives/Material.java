package primitives;

/**
 * Class that represents the material of a geometric object.
 * The class is PDS.
 */
public class Material
{
    public Double3 kD=Double3.ZERO;
    public Double3 kS=Double3.ZERO;
    public int Shininess=0;

    /**
     * Setter for the kD field
     * @param kD parameter for the kD field
     * @return The object itself
     */
    public Material setkD(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Setter for the kD field
     * @param kD double parameter for all three values in the kD field
     * @return The object itself
     */
    public Material setkD(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Setter for the kS field
     * @param kS parameter for the kS field
     * @return The object itself
     */
    public Material setkS(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Setter for the kS field
     * @param kS double parameter for all three values in the kS field
     * @return The object itself
     */
    public Material setkS(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Setter for the nShininess field
     * @param nShininess parameter for the nShininess field
     * @return The object itself
     */
    public Material setShininess(int nShininess) {
        this.Shininess = nShininess;
        return this;
    }


}
