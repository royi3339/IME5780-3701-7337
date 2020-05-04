package elements;

import primitives.Color;

public class AmbientLight {
    private Color _intensity;

    public AmbientLight(Color iA, double kA) { _intensity = iA.scale(kA); }

    public Color getIntensity() { return _intensity; }
}