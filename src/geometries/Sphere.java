package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * implements the Sphere class, which extending the {@link RadialGeometry} class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Sphere extends RadialGeometry {
    private Point3D _center;

    /**
     * <b> {@link Sphere} with {@link Color}, and with {@link Material} constructor. </b>
     *
     * @param color    <b> the {@link Color} of the {@link Sphere} </b>
     * @param material <b> the {@link Material} of the {@link Sphere} </b>
     * @param r        <b> the radius of the {@link Sphere}  </b>
     * @param c        <b> the center {@link Point3D} of the {@link Sphere}  </b>
     */
    public Sphere(Color color, Material material, double r, Point3D c) {
        super(color, material, r);
        _center = new Point3D(c);
    }

    /**
     * <b> {@link Sphere}  with {@link Color} constructor. </b>
     *
     * @param color <b> the {@link Color} of the {@link Sphere}  </b>
     * @param r     <b> the radius of the {@link Sphere}  </b>
     * @param c     <b> the center {@link Point3D} of the {@link Sphere}  </b>
     */
    public Sphere(Color color, double r, Point3D c) {
        this(color, new Material(0, 0, 0), r, c);
    }


    /**
     * <b> {@link Sphere}  constructor. </b>
     *
     * @param r <b> the radius of the {@link Sphere}  </b>
     * @param c <b> the center {@link Point3D} of the {@link Sphere}  </b>
     */
    public Sphere(double r, Point3D c) { this(Color.BLACK, r, c); }

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
     * @param p <b> the {@link Point3D} on the {@link Sphere}  </b>
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
     * @param ray         <b> the {@link Ray} we will find his intersections </b>
     * @param maxDistance <b> the range of the distance checking of the {@link Ray} </b>
     * @return List<GeoPoint> <b> the intersections points </b>
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double maxDistance) {

        Point3D o = _center;
        Point3D p0 = ray.getHead();
        Vector u, v = ray.getDirection();

        // check if the the subtract between point o and p0 is the ZERO Vector
        try {
            u = o.subtract(p0);
        } catch (IllegalArgumentException e) {
            if (alignZero(_radius - maxDistance) <= 0) { return List.of(new GeoPoint(this, ray.getPoint(_radius))); }
            return null;
        }

        double tm = v.dotProduct(u);
        double d = Math.sqrt(u.lengthSquared() - tm * tm);
        // check if the distance bigger then the radius, if it does, this mean that its outside of the Sphere.
        if (d >= _radius) { return null; }
        double th = Math.sqrt(_radius * _radius - d * d);

        // check if the Ray is tangent to the Sphere
        if (isZero(th)) { return null; }
        double t1 = alignZero(tm + th), t2 = alignZero(tm - th); // t1 >= t2.

        // check if the t1 point is at the back of the Ray
        if (t1 <= 0) { return null; } // there is no intersection point.

        // check if the t2 point is at the back of the Ray,  check whether the only first point is relevant
        if (t2 <= 0 && t1 <= maxDistance) { return List.of(new GeoPoint(this, ray.getPoint(t1))); }

        // check if 2 of the intersections point are relevant, and return those 2 intersections points
        if (t1 <= maxDistance && t2 > 0) {
            return List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));
        }

        // check whether the only second point is relevant
        if (t2 > 0 && t2 <= maxDistance && t1 > maxDistance) { return List.of(new GeoPoint(this, ray.getPoint(t2))); }
        return null;
    }
}
