package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * implements the Tube class, which extending the RadialGeometry class.
 *
 * @author Royi Alishayev
 */
public class Tube extends RadialGeometry {
    private double radius;
    private Ray axis;

    /**
     * <b> Tube constructor. </b>
     *
     * @param r <b> the radius of the Tube </b>
     * @param a <b> the axis of the Tube </b>
     */
    public Tube(double r, Ray a) {
        super(r);
        axis = new Ray(a);
    }

    /**
     * @return double <b> radius </b>
     */
    @Override
    public double getRadius() { return super.getRadius(); }

    /**
     * @return Ray <b> axis </b>
     */
    public Ray getAxis() { return axis; }

    /**
     * @return Point3D <b> middle </b>
     */
    public Point3D getMiddle() { return axis.getHead(); }

    /**
     * @return Vector <b> direction </b>
     */
    public Vector getDirection() { return axis.getDirection(); }

    /**
     * @return String <b> the info </b>
     */
    @Override
    public String toString() {
        return "Tube:\t" + super.toString() + ", axis = " + axis.toString();
    }
}
