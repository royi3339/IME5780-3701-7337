package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * implements the SpotLight class, which extending the {@link PointLight} class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class SpotLight extends PointLight {

    private Vector _direction;

    /**
     * <b> {@link SpotLight} constructor. </b>
     *
     * @param intensity <b> of the {@link Color} of the {@link SpotLight} </b>
     * @param position  <b> the position {@link Point3D} of {@link SpotLight}  </b>
     * @param kC        <b>  </b>
     * @param kL        <b>  </b>
     * @param kQ        <b>  </b>
     * @param direction <b> the direction {@link Vector} of the {@link SpotLight} </b>
     */
    public SpotLight(Color intensity, Point3D position, Vector direction, double kC, double kL, double kQ) {
        super(intensity, position, kC, kL, kQ);
        _direction = new Vector(direction).normalize();
    }

    /**
     * @param point <b> the other {@link Point3D} of the object </b>
     * @return {@link Color} <b> the {@link Color} of {@link Light} </b>
     */
    @Override
    public Color getIntensity(Point3D point) {
        Vector l = getL(point);
        Color i0 = super.getIntensity(point);
        return i0.scale(Math.max(0d, _direction.dotProduct(l)));
    }

    /**
     * @param p <b> the other {@link Point3D} of the object </b>
     * @return {@link Vector} <b> the {@link Vector} between the given {@link Point3D} which it's on the object,
     * <p>
     * and between the {@link Light} position </b>
     */
    @Override
    public Vector getL(Point3D p) { return super.getL(p).normalized(); }
}
