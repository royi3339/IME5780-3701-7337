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
     * @param p <b> the {@link Point3D} </b>
     * @return {@link Color} <b> the {@link Color} intensity of the Point3D </b>
     */
    public Color getIntensity(Point3D p);

    /**
     * @param p
     * @return
     */
    public Vector getL(Point3D p);

}
