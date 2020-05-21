package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * implements the Sphere class, which extending the {@link RadialGeometry} class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Sphere extends RadialGeometry {
    private Point3D _center;

    /**
     * <b> Sphere with {@link Color} constructor. </b>
     *
     * @param color <b> the {@link Color} of the Sphere </b>
     * @param c     <b> the center {@link Point3D} of the Sphere </b>
     * @param r     <b> the radius of the Sphere </b>
     */
    public Sphere(Color color, Point3D c, double r) {
        this(c, r);
        _emmission = new Color(color);
    }

    /**
     * <b> Sphere constructor. </b>
     *
     * @param c <b> the center {@link Point3D} of the Sphere </b>
     * @param r <b> the radius of the Sphere </b>
     */
    public Sphere(Point3D c, double r) {
        super(r);
        _center = new Point3D(c);
    }

    /**
     * <b> Sphere constructor. </b>
     *
     * @param r <b> the radius of the Sphere </b>
     * @param c <b> the center {@link Point3D} of the Sphere </b>
     */
    public Sphere(double r, Point3D c) {
        super(r);
        _center = new Point3D(c);
    }

    /**
     * @return {@link Point3D} <b> center </b>
     */
    public Point3D getCenter() { return _center; }

    /**
     * @return double <b> radius </b>
     */
    @Override
    public double getRadius() { return super.getRadius(); }

    /**
     * @param p <b> the {@link Point3D} on the Sphere </b>
     * @return {@link Vector} <b> normal </b>
     */
    @Override
    public Vector getNormal(Point3D p) {
        return (p.subtract(_center)).normalize();
    }

    /**
     * @return String <b> the info </b>
     */
    @Override
    public String toString() { return "Sphere:\t" + "center = " + _center.toString() + ", " + super.toString(); }

    /**
     * @param ray <b> the {@link Ray} we will find his intersections </b>
     * @return List<GeoPoint> <b> the intersections points </b>
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersectionsList = null;

        Point3D o = _center;
        Point3D p0 = ray.getHead();
        Vector v = ray.getDirection();
        Vector u = null;
        // check if the the subtract between point o and p0 is the ZERO Vector
        try {
            u = o.subtract(p0);
        } catch (IllegalArgumentException e) { return List.of(new GeoPoint(this, ray.getPoint(_radius))); }

        double tm = v.dotProduct(u);
        double d = Math.sqrt(u.lengthSquared() - tm * tm);

        // check if the distance bigger then the radius, if it does, this mean that its outside of the Sphere.
        if (d >= _radius) { return null; }
        double th = Math.sqrt(_radius * _radius - d * d);

        // check if the Ray is tangent to the Sphere
        if (isZero(th)) { return null; }
        double t1 = tm + th, t2 = tm - th;

        // check if the the point is not at the back of the Ray
        if (t1 > 0) {
            intersectionsList = new LinkedList<GeoPoint>();
            intersectionsList.add(new GeoPoint(this, ray.getPoint(t1)));
        }

        // check if the the point is not at the back of the Ray
        if (t2 > 0) {
            // check if the intersectionsList is not initialized
            if (intersectionsList == null) {
                intersectionsList = new LinkedList<GeoPoint>();
            }
            intersectionsList.add(new GeoPoint(this, ray.getPoint(t2)));
        }
        return intersectionsList;
    }
}
