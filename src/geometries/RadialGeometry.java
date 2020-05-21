package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * * implements the RadialGeometry abstract class, which extending the {@link Geometry} abstract class.
 *
 * @author Royi Alishayev idan darmoni
 */
public abstract class RadialGeometry extends Geometry {
    protected double _radius;

    /**
     * <b> RadialGeometry constructor. </b>
     *
     * @param r <b> thr radius of the RadialGeometry </b>
     * @throws IllegalArgumentException if the radius <= 0.
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

    /**
     * @param P <b> {@link Point3D} </b>
     * @return {@link Vector} <b> normal </b>
     */
    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }

    /**
     * @param ray <b> the {@link Ray} we will find his intersections </b>
     * @return List<GeoPoint> <b> the intersections points (null) </b>
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        return null;
    }
}
