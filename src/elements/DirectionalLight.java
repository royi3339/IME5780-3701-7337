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
     * @param point <b> the other {@link Point3D} of the object </b>
     * @return {@link Color} <b> the {@link Color} of {@link Light} </b>
     */
    @Override
    public Color getIntensity(Point3D point) { return _intensity; }

    /**
     * @param p <b> the other {@link Point3D} of the object </b>
     * @return {@link Vector} <b> the {@link Vector} between the given {@link Point3D} which it's on the object,
     * <p>
     * and between the {@link Light} position </b>
     */
    @Override
    public Vector getL(Point3D p) { return _direction; }
}
