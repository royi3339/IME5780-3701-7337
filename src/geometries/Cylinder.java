package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * implements the Cylinder class, which extending the Tube class.
 *
 * @author Royi Alishayev
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

    @Override
    public Vector getNormal(Point3D p) {
        Vector subVector = p.subtract(getAxis().getHead());
        if ((getAxis().getDirection()).dotProduct(subVector) == 0) {
            return getAxis().getDirection().scale(-1);
        }
        subVector = getAxis().getHead().add(getAxis().getDirection().scale(_height)).subtract(p);
        if ((getAxis().getDirection()).dotProduct(subVector) == 0) {
            return getAxis().getDirection();
        }
        return super.getNormal(p);
    }
}
