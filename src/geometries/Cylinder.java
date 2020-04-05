package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * implements the Cylinder class, which extending the Tube class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Cylinder extends Tube {
    private double _height;

    /**
     * <b> Cylinder constructor. </b>
     *
     * @param h   <b> the height of the Cylinder </b>
     * @param r   <b> the radius of the Cylinder </b>
     * @param m   <b> the middle Point3D of the Cylinder </b>
     * @param dir <b> the direction of the Cylinder </b>
     */
    public Cylinder(double h, double r, Point3D m, Vector dir) {
        super(r, m, dir);
        _height = h;
        if (h <= 0)
            throw new IllegalArgumentException("Error ! ! ! the radius shuld be a positive number");
    }

    /**
     * <b> Cylinder constructor. </b>
     *
     * @param h <b> the height of the Cylinder </b>
     * @param r <b> the radius of the Cylinder </b>
     * @param a <b> the Rau of the Cylinder </b>
     */
    public Cylinder(double h, double r, Ray a) {
        super(r, a);
        _height = h;
        if (h <= 0)
            throw new IllegalArgumentException("Error ! ! ! the radius shuld be a positive number");
    }

    /**
     * @return double <b> height </b>
     */
    public double getHeight() { return _height; }

    /**
     * @return Point3D <b> middle </b>
     */
    @Override
    public Point3D getMiddle() { return super.getMiddle(); }

    /**
     * @return Vector <b> direction </b>
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
     * @param p <b> the Point3D on the Cylinder </b>
     * @return Vector <b> normal </b>
     */
    @Override
    public Vector getNormal(Point3D p) {
        // if the Point 3D is on the header of the axis direction
        if (p.equals(axis.getHead())) {
            return getDirection().scale(-1);
        }
        // if the Point 3D is on the end of the axis direction
        if (p.equals(axis.getHead().add(getDirection().scale(_height)))) {
            return getDirection();
        }
        // if the Point 3D is on the first base
        Vector subVector = p.subtract(axis.getHead());
        if (isZero(getDirection().dotProduct(subVector))) {
            return getDirection().scale(-1);
        }
        // if the Point 3D is on the second base
        subVector = axis.getHead().add(getDirection().scale(_height)).subtract(p);
        if (isZero(getDirection().dotProduct(subVector))) {
            return getDirection();
        }
        // if the Point3D is on the casing
        return super.getNormal(p);
    }
}
