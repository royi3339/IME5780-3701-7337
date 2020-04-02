package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * implements the Sphere class, which extending the RadialGeometry class.
 *
 * @author Royi Alishayev
 */
public class Sphere extends RadialGeometry {
    private Point3D _center;
    private double _radius;

    /**
     * <b> Sphere constructor. </b>
     *
     * @param c <b> the center Point3D of the Sphere </b>
     * @param r <b> the radius of the Sphere </b>
     */
    public Sphere(Point3D c, double r) {
        super(r);
        _center = new Point3D(c);
    }

    /**
     * @return Point3D <b> center </b>
     */
    public Point3D getCenter() { return _center; }

    /**
     * @return double <b> radius </b>
     */
    @Override
    public double getRadius() { return super.getRadius(); }

    @Override
    public Vector getNormal(Point3D p) {
        return (new Vector(p.subtract(_center))).normalize();
    }
    /**
     * @return String <b> the info </b>
     */
    @Override
    public String toString() { return "Sphere:\t" + "center = " + _center.toString() + ", " + super.toString(); }
}
