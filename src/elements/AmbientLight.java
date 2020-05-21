package elements;

import primitives.Color;

/**
 * implements the AmbientLight class, which extending the {@link Light} abstract class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class AmbientLight extends Light {

    /**
     * <b> AmbientLight constructor. </b>
     * <p>
     * calculate the intensity of the Color, by scaling the iA with the kA,
     * <p>
     * and send it to the Light constructor.
     *
     * @param iA <b> the {@link Color} of the AmbientLight </b>
     * @param kA <b> the value of the {@link Color} </b>
     */
    public AmbientLight(Color iA, double kA) { super(iA.scale(kA)); }
}