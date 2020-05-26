package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.isZero;

/**
 * implements the Cylinder class, which extending the {@link Tube} class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Cylinder extends Tube {
    private double _height;


    /**
     * <b> {@link Cylinder} with {@link Color}, and with {@link Material} constructor </b>
     *
     * @param color    <b> the {@link Color} of the {@link Cylinder} </b>
     * @param material <b> the {@link Material} of the {@link Cylinder} </b>
     * @param h        <b> the height of the {@link Cylinder} </b>
     * @param r        <b> the radius of the {@link Cylinder} </b>
     * @param m        <b> the middle {@link Point3D} of the {@link Cylinder} </b>
     * @param dir      <b> the direction of the {@link Cylinder} </b>
     * @throws IllegalArgumentException if the radius <= 0.
     * @throws IllegalArgumentException if the height <= 0.
     */
    public Cylinder(Color color, Material material, double h, double r, Point3D m, Vector dir) {
        super(color, material, r, new Ray(m, dir));
        _height = h;
        if (h <= 0)
            throw new IllegalArgumentException("Error ! ! ! the height should be a positive number");
    }

    /**
     * <b> {@link Cylinder} with {@link Color} constructor </b>
     *
     * @param color <b> the {@link Color} of the {@link Cylinder} </b>
     * @param h     <b> the height of the {@link Cylinder} </b>
     * @param r     <b> the radius of the {@link Cylinder} </b>
     * @param m     <b> the middle {@link Point3D} of the {@link Cylinder} </b>
     * @param dir   <b> the direction of the {@link Cylinder} </b>
     * @throws IllegalArgumentException if the radius <= 0.
     * @throws IllegalArgumentException if the height <= 0.
     */
    public Cylinder(Color color, double h, double r, Point3D m, Vector dir) {
        this(color, new Material(0, 0, 0), h, r, m, dir);
    }

    /**
     * <b> {@link Cylinder} constructor. </b>
     *
     * @param h   <b> the height of the {@link Cylinder} </b>
     * @param r   <b> the radius of the {@link Cylinder} </b>
     * @param m   <b> the middle {@link Point3D} of the {@link Cylinder} </b>
     * @param dir <b> the direction of the {@link Cylinder} </b>
     * @throws IllegalArgumentException if the radius <= 0.
     * @throws IllegalArgumentException if the height <= 0.
     */
    public Cylinder(double h, double r, Point3D m, Vector dir) { this(h, r, new Ray(m, dir)); }

    /**
     * <b> {@link Cylinder} constructor. </b>
     *
     * @param h <b> the height of the {@link Cylinder} </b>
     * @param r <b> the radius of the {@link Cylinder} </b>
     * @param a <b> the Rau of the {@link Cylinder} </b>
     * @throws IllegalArgumentException if the radius <= 0.
     * @throws IllegalArgumentException if the height <= 0.
     */
    public Cylinder(double h, double r, Ray a) {
        this(Color.BLACK, h, r, a.getHead(), a.getDirection());
    }

    /**
     * @return double <b> height </b>
     */
    public double getHeight() { return _height; }

    /**
     * @return {@link Point3D} <b> middle </b>
     */
    @Override
    public Point3D getMiddle() { return super.getMiddle(); }

    /**
     * @return {@link Vector} <b> direction </b>
     */
    @Override
    public Vector getDirection() { return super.getDirection(); }

    /**
     * @return double <b> radius </b>
     */
    @Override
    public double getRadius() { return super.getRadius(); }

    /**
     * @return String <b> the info </b>
     */
    @Override
    public String toString() {
        return "Cylinder:\t" + "height = " + _height + ", radius = " + super.getRadius();
    }

    /**
     * @param p <b> the {@link Point3D} on the {@link Cylinder} </b>
     * @return {@link Vector} <b> normal </b>
     */
    @Override
    public Vector getNormal(Point3D p) {
        // if the Point 3D is on the header of the axis direction
        if (p.equals(axis.getHead())) { return getDirection().scale(-1); }

        // if the Point 3D is on the end of the axis direction
        if (p.equals(axis.getHead().add(getDirection().scale(_height)))) { return getDirection(); }

        // if the Point 3D is on the first base
        if (isZero(getDirection().dotProduct(axis.getHead().subtract(p)))) { return getDirection().scale(-1); }

        // if the Point 3D is on the second base
        Vector subVector = axis.getHead().add(getDirection().scale(_height)).subtract(p);
        if (isZero(getDirection().dotProduct(subVector))) { return getDirection(); }

        // if the Point3D is on the casing which implements in the Tube class.
        return super.getNormal(p);
    }

    /**
     * @param ray <b> the {@link Ray} we will find his intersections </b>
     * @return List<GeoPoint> <b> the intersections points (null) </b>
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) { return null; }
}
