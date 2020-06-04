package geometries;

import primitives.*;

import static primitives.Util.alignZero;

/**
 * * implements the RadialGeometry abstract class, which extending the {@link Geometry} abstract class.
 *
 * @author Royi Alishayev idan darmoni
 */
public abstract class RadialGeometry extends Geometry {
    protected double _radius;

    /**
     * <b> {@link RadialGeometry} with {@link Color} and with {@link Material} constructor. </b>
     *
     * @param color    <b> the {@link Color} of the {@link RadialGeometry} </b>
     * @param material <b> {@link Material} of the {@link RadialGeometry} </b>
     * @param r        <b> thr radius of the {@link RadialGeometry} </b>
     * @throws IllegalArgumentException if the radius <= 0.
     */
    public RadialGeometry(Color color, Material material, double r) {
        super(color, material);
        _radius = alignZero(r);
        if (r <= 0)
            throw new IllegalArgumentException("Error ! ! ! the radius should be a positive number");
    }

    /**
     * <b> {@link RadialGeometry} constructor. </b>
     *
     * @param r <b> thr radius of the {@link RadialGeometry} </b>
     * @throws IllegalArgumentException if the radius <= 0.
     */
    public RadialGeometry(double r) {
        this(Color.BLACK, new Material(0, 0, 0), r);
    }

    /**
     * @return double <b> radius </b>
     */
    public double getRadius() { return _radius; }

    /**
     * @return String <b> the info </b>
     */
    @Override
    public String toString() { return "radius = " + _radius; }
}
