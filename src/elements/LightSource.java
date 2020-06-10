package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * implements the LightSource interface.
 *
 * @author Royi Alishayev idan darmoni
 */
public interface LightSource {

    /**
     * Provides intensity of light from {@link LightSource} at a point according to light propagation model.
     *
     * @param p <b> a {@link Point3D} object's surface </b>
     * @return {@link Color} <b> the {@link Color} of {@link Light} </b>
     */
    public Color getIntensity(Point3D p);

    /**
     * provided the normalized {@link Vector} between the given {@link Point3D} which it's on the object,
     * <p>
     * and between the {@link Light} position.
     *
     * @param p <b> the other {@link Point3D} of the object </b>
     * @return {@link Vector} <b>  the normalized {@link Vector} </b>
     */
    public Vector getL(Point3D p);


    /**
     * @param p <b> the {@link Point3D} that we check the distance with </b>
     * @return double <b> return distance </b>
     */
    public double getDistance(Point3D p);
}
