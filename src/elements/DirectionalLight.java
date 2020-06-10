package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * implements the DirectionalLight class.
 * <p>
 * which extending {@link Light} abstract class,
 * <p>
 * and implements the {@link LightSource} interface.
 *
 * @author Royi Alishayev idan darmoni
 */
public class DirectionalLight extends Light implements LightSource {

    private Vector _direction;

    /**
     * <b> {@link DirectionalLight} constructor. </b>
     *
     * @param intensity <b> of the {@link Color} of the {@link DirectionalLight} </b>
     * @param direction <b> the direction of the {@link Vector} </b>
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        _direction = new Vector(direction);
    }

    /**
     * Provides intensity of light from {@link LightSource} at a point according to light propagation model.
     *
     * @param p <b> a {@link Point3D} object's surface </b>
     * @return {@link Color} <b> the {@link Color} of {@link Light} </b>
     */
    @Override
    public Color getIntensity(Point3D point) { return _intensity; }

    /**
     * provided the normalized {@link Vector} between the given {@link Point3D} which it's on the object,
     * <p>
     * and between the {@link Light} position.
     *
     * @param p <b> the other {@link Point3D} of the object </b>
     * @return {@link Vector} <b>  the normalized {@link Vector} </b>
     */
    @Override
    public Vector getL(Point3D p) { return _direction.normalized(); }


    /**
     * @param p <b> the {@link Point3D} that we check the distance with </b>
     * @return double <b> return distance (because its the {@link DirectionalLight} will return INFINITY positive) </b>
     */
    @Override
    public double getDistance(Point3D p) { return Double.POSITIVE_INFINITY; }
}
