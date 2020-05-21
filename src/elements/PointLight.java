package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * implements the PointLight class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class PointLight extends Light implements LightSource {

    protected Point3D _position;
    protected double _kC, _kL, _kQ;


    /**
     * <b> PointLight constructor. </b>
     *
     * @param intensity <b> of the {@link Color} of the PointLight </b>
     * @param position  <b> the position {@link Point3D} of PointLight  </b>
     * @param kC        <b>  </b>
     * @param kL        <b>  </b>
     * @param kQ        <b>  </b>
     */
    public PointLight(Color intensity, Point3D position, double kC, double kL, double kQ) {
        super(intensity);
        _position = new Point3D(position);
        _kC = kC;
        _kL = kL;
        _kQ = kQ;
    }

    /**
     * @param intensity
     * @return
     */
    @Override
    public Color getIntensity(Point3D intensity) { }            // לממששששששששששש

    /**
     * @param p
     * @return
     */
    @Override
    public Vector getL(Point3D p) { }                           // למממשששששששששששש


}
