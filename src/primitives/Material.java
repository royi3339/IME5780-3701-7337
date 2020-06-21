package primitives;

/**
 * implements the Material class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Material {
    private double _kD, _kS;
    private double _kT, _kR;
    private int _nShininess;
    private double _glurry;         // glossy, and blurry factors.

    /**
     * <b> {@link Material} complete constructor. </b>
     *
     * @param kD         <b> the diffuse's attenuation factor of the {@link Material} </b>
     * @param kS         <b> the specular's value factor of the {@link Material} </b>
     * @param nShininess <b> the object’s shininess value of the {@link Material} </b>
     * @param kT         <b> the transparency factor of the {@link Material} </b>
     * @param kR         <b> the reflection factor of the {@link Material} </b>
     * @param glurry     <b>  </b>
     */
    public Material(double kD, double kS, int nShininess, double kT, double kR, double glurry) {
        _kD = kD;
        _kS = kS;
        _nShininess = nShininess;
        _kT = kT;
        _kR = kR;
        _glurry = glurry;
    }

    /**
     * <b> {@link Material} all must complete constructor. </b>
     *
     * @param kD         <b> the diffuse's attenuation factor of the {@link Material} </b>
     * @param kS         <b> the specular's value factor of the {@link Material} </b>
     * @param nShininess <b> the object’s shininess value of the {@link Material} </b>
     * @param kT         <b> the transparency factor of the {@link Material} </b>
     * @param kR         <b> the reflection factor of the {@link Material} </b>
     */
    public Material(double kD, double kS, int nShininess, double kT, double kR) {
        this(kD, kS, nShininess, kT, kR, 0);
    }

    /**
     * <b> {@link Material} constructor. </b>
     *
     * @param kD         <b> the diffuse's attenuation factor of the {@link Material} </b>
     * @param kS         <b> the specular's value factor of the {@link Material} </b>
     * @param nShininess <b> the object’s shininess value of the {@link Material} </b>
     */
    public Material(double kD, double kS, int nShininess) { this(kD, kS, nShininess, 0, 0); }

    /**
     * @return double <b> the diffuse's attenuation factor of the {@link Material} </b>
     */
    public double getKD() { return _kD; }

    /**
     * @return double <b> the specular's value factor of the {@link Material} </b>
     */
    public double getKS() { return _kS; }

    /**
     * @return double <b> the transparency factor of the {@link Material} </b>
     */
    public double getKT() { return _kT; }

    /**
     * @return double <b> the reflection factor of the {@link Material} </b>
     */
    public double getKR() { return _kR; }

    /**
     * @return int <b> the shininess of the {@link Material} </b>
     */
    public int getNShininess() { return _nShininess; }

    /**
     * @return double <b> the glurry factor of the {@link Material} </b>
     */
    public double getGlurry() { return _glurry; }

    /**
     * setting the KR
     *
     * @param kr <b> the reflection factor of the {@link Material} </b>
     */
    public void setKr(double kr) { this._kR = kr; }

    /**
     * setting the KT
     *
     * @param kt <b> the transparency factor of the {@link Material} </b>
     */
    public void setKt(double kt) { this._kT = kt; }

    /**
     * setting the blurry factor of the {@link Material}
     *
     * @param glurry <b> the given value </b>
     */
    public void setGlurry(double glurry) { this._glurry = glurry; }
}


