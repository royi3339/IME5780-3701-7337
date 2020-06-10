package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * implements the Plane class, which extending the {@link Geometry} abstract class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Plane extends Geometry {
    private Vector _normal;
    private Point3D _header;

    /**
     * <b> {@link Plane} based on {@link Point3D} and the normal {@link Vector} with {@link Color} and {@link Material} constructor. </b>
     *
     * @param color     <b> the {@link Color} of the {@link Plane} </b>
     * @param material  <b> the {@link Material} of the {@link Plane} </b>
     * @param h         <b> the {@link Point3D} of the {@link Plane} </b>
     * @param normalVec <b> normal {@link Vector} of the {@link Plane} </b>
     */
    public Plane(Color color, Material material, Point3D h, Vector normalVec) {
        super(color, material);
        _normal = new Vector(normalVec);
        _header = new Point3D(h);
    }

    /**
     * <b> {@link Plane} based on {@link Point3D} and the normal {@link Vector} with {@link Color} constructor. </b>
     *
     * @param color     <b> the {@link Color} of the {@link Plane} </b>
     * @param h         <b> the {@link Point3D} of the {@link Plane} </b>
     * @param normalVec <b> normal {@link Vector} of the {@link Plane} </b>
     */
    public Plane(Color color, Point3D h, Vector normalVec) {
        this(color, new Material(0, 0, 0), h, normalVec);
    }

    /**
     * <b> {@link Plane} based on {@link Point3D} and the normal {@link Vector} constructor. </b>
     *
     * @param h         <b> the {@link Point3D} of the {@link Plane} </b>
     * @param normalVec <b> normal {@link Vector} of the {@link Plane} </b>
     */
    public Plane(Point3D h, Vector normalVec) { this(Color.BLACK, h, normalVec); }

    /**
     * <b> {@link Plane} based on 3 {@link Point3D}, with {@link Color}, and {@link Material} constructor. </b>
     *
     * @param color    <b> the {@link Color} of the {@link Plane} </b>
     * @param material <b> the {@link Material} of the {@link Plane} </b>
     * @param a        <b> the first {@link Point3D} of the {@link Plane} </b>
     * @param b        <b> the second {@link Point3D} of the {@link Plane} </b>
     * @param c        <b> the third {@link Point3D} of the {@link Plane} </b>
     */
    public Plane(Color color, Material material, Point3D a, Point3D b, Point3D c) {
        this(color, material, a, b.subtract(c).crossProduct(b.subtract(a)).normalized()); // using the Plane constructor, with point and vector.
    }

    /**
     * <b> {@link Plane} based on 3 {@link Point3D}, with {@link Color} constructor. </b>
     *
     * @param color <b> the {@link Color} of the {@link Plane} </b>
     * @param a     <b> the first {@link Point3D} of the {@link Plane} </b>
     * @param b     <b> the second {@link Point3D} of the {@link Plane} </b>
     * @param c     <b> the third {@link Point3D} of the {@link Plane} </b>
     */
    public Plane(Color color, Point3D a, Point3D b, Point3D c) { this(color, new Material(0, 0, 0), a, b, c); }

    /**
     * <b> {@link Plane} based on 3 {@link Point3D} constructor. </b>
     *
     * @param a <b> the first {@link Point3D} of the {@link Plane} </b>
     * @param b <b> the second {@link Point3D} of the {@link Plane} </b>
     * @param c <b> the third {@link Point3D} of the {@link Plane} </b>
     */
    public Plane(Point3D a, Point3D b, Point3D c) { this(Color.BLACK, a, b, c); }

    /**
     * @param P <b> {@link Point3D} </b>
     * @return {@link Vector} <b> normal </b>
     */
    @Override
    public Vector getNormal(Point3D P) { return _normal; }

    /**
     * @return {@link Vector} <b> normal </b>
     */
    public Vector getNormal() { return _normal; }

    /**
     * @return {@link Point3D} <b> header </b>
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
     * checking the Points which intersections with the object, and with the given {@link Ray}, and return a List of those points.
     * <p>
     * in range of the given distance.
     * <p>
     * if there is no intersections, will return null.
     *
     * @param ray         <b> the {@link Ray} we will find his intersections </b>
     * @param maxDistance <b> the range of the distance checking of the {@link Ray} </b>
     * @return List<GeoPoint> <b> the intersections points </b>
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double maxDistance) {
        Point3D q0 = _header;
        Vector normal = _normal;

        Point3D p0 = ray.getHead();
        Vector v = ray.getDirection();

        Vector sub;
        try {
            sub = q0.subtract(p0);
        } catch (IllegalArgumentException e) { // if throw zero vector exception so the intersection point (ray) begin on the plane.
            return null;
        }

        double vn = v.dotProduct(normal); //              the ray is parallel to the Plane.
        if (isZero(vn)) { return null; }

        double t = alignZero(normal.dotProduct(sub) / vn); // t = the distance of the Point from the Ray's start.
        if (t > 0 && t <= maxDistance) { return List.of(new GeoPoint(this, ray.getPoint(t))); }
        return null;
    }
}
