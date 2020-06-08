package elements;

import primitives.Color;

/**
 * implements the Light abstract class.
 *
 * @author Royi Alishayev idan darmoni
 */
public abstract class Light {
    protected Color _intensity;

    /**
     * <b> {@link Light} constructor. </b>
     *
     * @param intensity <b> the {@link Color} intensity of the {@link Light} </b>
     */
    public Light(Color intensity) { _intensity = new Color(intensity); }

    /**
     * @return {@link Color} <b> the {@link Color} of {@link Light} </b>
     */
    public Color getIntensity() { return _intensity; }
}
