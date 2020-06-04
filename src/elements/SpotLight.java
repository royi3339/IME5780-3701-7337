package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

import static primitives.Util.alignZero;

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

        double dl = alignZero(_direction.dotProduct(l));
        if (dl <= 0) { return Color.BLACK; }

        Color i0 = super.getIntensity(point);
        return i0.scale(dl);
    }
}
