package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * implements the Plane class, which implementing the Geometry interface.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Plane implements Geometry {
    private Vector _normal;
    private Point3D _header;

    /**
     * <b> Plane constructor. </b>
     *
     * @param a <b> the first Point3D of the Plane </b>
     * @param b <b> the second Point3D of the Plane </b>
     * @param c <b> the third Point3D of the Plane </b>
     */
    public Plane(Point3D a, Point3D b, Point3D c) {
        _header = new Point3D(a);
        Vector v1 = b.subtract(a);
        Vector v2 = b.subtract(c);
        _normal = v2.crossProduct(v1).normalized();
    }

    /**
     * <b> Plane constructor. </b>
     *
     * @param h         <b> the Point3D of the Plane </b>
     * @param normalVec <b> normal Vector of the Plane </b>
     */
    public Plane(Point3D h, Vector normalVec) {
        _normal = new Vector(normalVec);
        _header = new Point3D(h);
    }

    /**
     * @param P <b> Point3D </b>
     * @return Vector <b> normal </b>
     */
    @Override
    public Vector getNormal(Point3D P) { return _normal; }

    /**
     * @return Vector <b> normal </b>
     */
    public Vector getNormal() { return _normal; }

    /**
     * @return Point3D <b> header </b>
     */
    public Point3D getHeader() { return _header; }

    /**
     * @return String <b> the info </b>
     */
    @Override
    public String toString() {
        return "Plane:\t" + "normal = " + _normal.toString() + ", header = " + _header.toString();
    }

    /**
     * @param ray <b> the Ray we will find his intersections </b>
     * @return List<Point3D> <b> find the intersections </b>
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersectionsList = null;

        Point3D q0 = _header;
        Vector normal = _normal;

        Point3D p0 = ray.getHead();
        Vector v = ray.getDirection();

        if (p0.equals(q0)) { return null; }
        if (isZero(v.dotProduct(normal))) { return null; }

        double t = normal.dotProduct(q0.subtract(p0)) / normal.dotProduct(v);

        if (t > 0) {
            intersectionsList = new ArrayList<Point3D>();
            intersectionsList.add(ray.getPoint(t));
        }
        return intersectionsList;
    }
}
