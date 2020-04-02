package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * @author Royi Alishayev idan darmoni
 */
public abstract class RadialGeometry implements Geometry {
    private double _radius;

    /**
     * <b> RadialGeometry constructor. </b>
     *
     * @param r <b> thr radius of the RadialGeometry </b>
     */
    public RadialGeometry(double r) {
        _radius = r;
        if (r <= 0)
            throw new IllegalArgumentException("Error ! ! ! the radius shuld be a positive number");
    }

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

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }
}
