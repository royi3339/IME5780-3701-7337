package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * implements the Sphere class, which extending the RadialGeometry class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Sphere extends RadialGeometry {
    private Point3D _center;

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

    /**
     * @param p <b> the Point3D on the Sphere </b>
     * @return Vector <b> normal </b>
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
     * @param ray <b> the Ray we will find his intersections </b>
     * @return List<Point3D> <b> find the intersections </b>
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersectionsList = null; // = new ArrayList<>();

        Point3D o = _center;
        Point3D p0 = ray.getHead();
        Vector v = ray.getDirection();
        Vector u = null;
        // check if the the subtract between point o and p0 is the ZERO Vector
        try {
            u = o.subtract(p0);
        } catch (IllegalArgumentException e) {
            intersectionsList = new ArrayList<Point3D>();
            intersectionsList.add(ray.getPoint(_radius));
            return intersectionsList;
        }
        double tm = v.dotProduct(u);
        double d = Math.sqrt(u.lengthSquared() - tm * tm);
        // check if the distance bigger then the radius, if it does, this mean that its outside of the Sphere.
        if (d > _radius) { return null; }
        double th = Math.sqrt(_radius * _radius - d * d);
        // check if the Ray is tangent to the Sphere
        if (isZero(th)) { return null; }
        double t1 = tm + th, t2 = tm - th;
        // check if the the point is not at the back of the Ray
        if (t1 > 0) {
            intersectionsList = new ArrayList<Point3D>();
            intersectionsList.add(ray.getPoint(t1));
        }
        // check if the the point is not at the back of the Ray
        if (t2 > 0) {
            // check if the intersectionsList is not initialized
            if (intersectionsList == null) {
                intersectionsList = new ArrayList<Point3D>();
            }
            intersectionsList.add(ray.getPoint(t2));
        }
        return intersectionsList;
    }
}
