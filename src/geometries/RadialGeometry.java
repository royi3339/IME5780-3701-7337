package geometries;

/**
 * @author Royi Alishayev
 */
public abstract class RadialGeometry {
    private double _radius;

    /**
     * <b> RadialGeometry constructor. </b>
     *
     * @param r <b> thr radius of the RadialGeometry </b>
     */
    public RadialGeometry(double r) { _radius = r; }

    /**
     * <b> RadialGeometry copy constructor. </b>
     *
     * @param r <b> the RadialGeometry </b>
     */
    public RadialGeometry(RadialGeometry r) { _radius = r._radius; }

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
