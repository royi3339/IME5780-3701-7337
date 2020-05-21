package elements;

import primitives.Color;

/**
 * implements the AmbientLight class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class AmbientLight {
    private Color _intensity;

    /**
     * <b> AmbientLight constructor. </b>
     * <p>
     * calculate the intensity of the Color, by scaling the iA with the kA
     *
     * @param iA <b> the {@link Color} of the AmbientLight </b>
     * @param kA <b> the value of the {@link Color} </b>
     */
    public AmbientLight(Color iA, double kA) { _intensity = iA.scale(kA); }

    /**
     * @return {@link Color} <b> the intensity of the {@link Color} of the AmbientLight </b>
     */
    public Color getIntensity() { return _intensity; }
}