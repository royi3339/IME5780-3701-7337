package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * implements the PointLight class.
 * <p>
 * which extending {@link Light} abstract class,
 * <p>
 * and implements the {@link LightSource} interface.
 *
 * @author Royi Alishayev idan darmoni
 */
public class PointLight extends Light implements LightSource {

    protected Point3D _position;
    protected double _kC, _kL, _kQ;

    /**
     * <b> {@link PointLight} constructor. </b>
     * <p>
     * the {@link PointLight} must not be on surface of an object.
     *
     * @param intensity <b> of the {@link Color} of the {@link PointLight} </b>
     * @param position  <b> the position {@link Point3D} of {@link PointLight} </b>
     *                  <p>
     *                  with those 3 parameters we can use the distance to calculate intensity :
     * @param kC        <b> free factor </b>
     * @param kL        <b> factor of the first exponent </b>
     * @param kQ        <b> factor of the second exponent </b>
     */
    public PointLight(Color intensity, Point3D position, double kC, double kL, double kQ) {
        super(intensity);
        _position = new Point3D(position);
        _kC = kC;
        _kL = kL;
        _kQ = kQ;
    }

    /**
     * Provides intensity of light from {@link LightSource} at a point according to light propagation model.
     *
     * @param p <b> a {@link Point3D} object's surface </b>
     * @return {@link Color} <b> the intensity {@link Color} of {@link Light} </b>
     */
    @Override
    public Color getIntensity(Point3D point) {
        double d = _position.distance(point);
        Color iL = _intensity.scale(1d / (_kC + _kL * d + _kQ * d * d));
        return iL;
    }

    /**
     * @param p <b> the other {@link Point3D} of the object </b>
     * @return {@link Vector} <b> the normalized {@link Vector} between the given {@link Point3D} which it's on the object,
     * <p>
     * and between the {@link Light} position </b>
     */
    @Override
    public Vector getL(Point3D p) { return p.subtract(_position).normalized(); }


    /**
     * @param p <b> the {@link Point3D} that we check the distance with </b>
     * @return double <b> return distance </b>
     */
    @Override
    public double getDistance(Point3D p) { return _position.distance(p); }
}
