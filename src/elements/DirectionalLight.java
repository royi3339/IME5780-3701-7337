package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * implements the DirectionalLight class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class DirectionalLight extends Light implements LightSource {

    private Vector _direction;

    /**
     * <b> DirectionalLight constructor. </b>
     *
     * @param intensity <b> of the {@link Color} of the DirectionalLight </b>
     * @param direction <b> the direction of the {@link Vector} </b>
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        _direction = new Vector(direction);
    }

    /**
     * @param intensity
     * @return
     */
    @Override
    public Color getIntensity(Point3D intensity) { }            // לממששששששששששש

    /**
     * @param p
     * @return
     */
    @Override
    public Vector getL(Point3D p) { }                           // למממשששששששששששש



}
