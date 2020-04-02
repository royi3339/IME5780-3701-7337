package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * implements the Plane class, which implementing the Geometry interface.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Plane implements Geometry {
    private Vector _normal = null;
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
}
