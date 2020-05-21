package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * implements the SpotLight class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class SpotLight extends PointLight {

    private Vector _direction;

    /**
     * <b> SpotLight constructor. </b>
     *
     * @param intensity <b> of the {@link Color} of the SpotLight </b>
     * @param position  <b> the position {@link Point3D} of SpotLight  </b>
     * @param kC        <b>  </b>
     * @param kL        <b>  </b>
     * @param kQ        <b>  </b>                                                 לממששששששששששששששששששששששששששששששששששששש
     * @param direction <b>  </b>
     */
    public SpotLight(Color intensity, Point3D position, double kC, double kL, double kQ, Vector direction) {
        super(intensity, position, kC, kL, kQ);
        _direction = new Vector(direction);
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
