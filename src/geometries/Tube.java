package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.isZero;

/**
 * implements the Tube class, which extending the {@link RadialGeometry} class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Tube extends RadialGeometry {
    protected Ray axis;

    /**
     * <b> {@link Tube} with {@link Color}, and with {@link Material} constructor. </b>
     *
     * @param color    <b> the {@link Color} of the {@link Tube} </b>
     * @param material <b> the {@link Material} of the {@link Cylinder} </b>
     * @param r        <b> the radius of the {@link Tube} </b>
     * @param a        <b> the axis of the {@link Tube} </b>
     */
    public Tube(Color color, Material material, double r, Ray a) {
        super(color, material, r);
        axis = new Ray(a);
        box = new BoundingBox();
    }

    /**
     * <b> {@link Tube} with {@link Color} constructor. </b>
     *
     * @param color <b> the {@link Color} of the {@link Tube} </b>
     * @param r     <b> the radius of the {@link Tube} </b>
     * @param a     <b> the axis of the {@link Tube} </b>
     */
    public Tube(Color color, double r, Ray a) {
        this(color, new Material(0, 0, 0), r, a);
    }

    /**
     * <b> {@link Tube} constructor. </b>
     *
     * @param r <b> the radius of the {@link Tube} </b>
     * @param a <b> the axis of the {@link Tube} </b>
     */
    public Tube(double r, Ray a) { this(Color.BLACK, r, a); }

    /**
     * <b> {@link Tube} constructor. </b>
     *
     * @param r    <b> the radius of the {@link Tube} </b>
     * @param head <b> the header {@link Point3D} of the {@link Tube} </b>
     * @param dir  <b> the direction {@link Vector} of the {@link Tube} </b>
     */
    public Tube(double r, Point3D head, Vector dir) { this(r, new Ray(head, dir)); }

    /**
     * @return double <b> radius </b>
     */
    @Override
    public double getRadius() {
        return super.getRadius();
    }

    /**
     * @return {@link Ray} <b> axis </b>
     */
    public Ray getAxis() {
        return axis;
    }

    /**
     * @return {@link Point3D} <b> middle </b>
     */
    public Point3D getMiddle() {
        return axis.getHead();
    }

    /**
     * @return {@link Vector} <b> direction </b>
     */
    public Vector getDirection() {
        return axis.getDirection();
    }

    /**
     * @param p <b> the {@link Point3D} in the {@link Tube} </b>
     * @return {@link Vector} <b> normal </b>
     */
    @Override
    public Vector getNormal(Point3D p) {
        Vector v = getDirection();
        Point3D p0 = axis.getHead();

        // if the point is on the base
        if (isZero(v.dotProduct(p.subtract(p0)))) { return p.subtract(p0).normalize(); }

        // if the point is on the casing
        double t = v.dotProduct(p.subtract(p0));
        Point3D o = p0.add(v.scale(t));
        return p.subtract(o).normalize();
    }

    /**
     * @return String <b> the info </b>
     */
    @Override
    public String toString() {
        return "Tube:\t" + super.toString() + ", axis = " + axis.toString();
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
     * @return List<GeoPoint> <b> the intersections points (null) </b>
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double maxDistance) { return null; }
}
