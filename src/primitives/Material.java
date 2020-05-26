package primitives;

/**
 * implements the Material class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Material {
    private double _kD, _kS;
    private int _nShininess;

    /**
     * <b> {@link Material} constructor. </b>
     *
     * @param kD         <b> the diffuse's value factor of the {@link Material} </b>
     * @param kS         <b> the specular's value factor of the {@link Material} </b>
     * @param nShininess <b> the object’s shininess </b>
     */
    public Material(double kD, double kS, int nShininess) {
        _kD = kD;
        _kS = kS;
        _nShininess = nShininess;
    }

    /**
     * @return double <b> the diffuse's value factor of the {@link Material} </b>
     */
    public double getKD() { return _kD; }

    /**
     * @return double <b> the specular's value factor of the {@link Material} </b>
     */
    public double getKS() { return _kS; }

    /**
     * @return int <b> the shininess of the {@link Material} </b>
     */
    public int getNShininess() { return _nShininess; }
}


