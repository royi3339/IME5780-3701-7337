package elements;

import primitives.Color;

/**
 * implements the Light abstract class.
 *
 * @author Royi Alishayev idan darmoni
 */
private abstract class Light {
    protected Color _intensity;

    /**
     * <b> Light constructor. </b>
     *
     * @param intensity <b> the intensity of the {@link Color} of the Light </b>
     */
    public Light(Color intensity) { _intensity = new Color(intensity); }

    /**
     * @return {@link Color} <b> the intensity </b>
     */
    public Color getIntensity() { return _intensity; }
}
