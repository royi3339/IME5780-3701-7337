package geometries;

import primitives.*;

import java.util.List;

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
        _radius = r;
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
     * <b> {@link RadialGeometry} copy constructor. </b>
     *
     * @param r <b> the {@link RadialGeometry} </b>
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
    public List<GeoPoint> findIntersections(Ray ray) { return null; }
}
