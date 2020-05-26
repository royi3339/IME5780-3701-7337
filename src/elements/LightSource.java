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
     * @param p <b> the other {@link Point3D} of the object </b>
     * @return {@link Color} <b> the {@link Color} of {@link Light} </b>
     */
    public Color getIntensity(Point3D p);

    /**
     * @param p <b> the other {@link Point3D} of the object </b>
     * @return {@link Vector} <b> the {@link Vector} between the given {@link Point3D} which it's on the object,
     * <p>
     * and between the {@link Light} position </b>
     */
    public Vector getL(Point3D p);


}
