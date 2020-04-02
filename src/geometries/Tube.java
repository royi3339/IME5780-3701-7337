package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * implements the Tube class, which extending the RadialGeometry class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Tube extends RadialGeometry {
    private Ray _axis;

    /**
     * <b> Tube constructor. </b>
     *
     * @param r <b> the _radius of the Tube </b>
     * @param a <b> the _axis of the Tube </b>
     */
    public Tube(double r, Ray a) {
        super(r);
        _axis = new Ray(a);
    }

    /**
     * <b> Tube constructor. </b>
     *
     * @param r    <b> the _radius of the Tube </b>
     * @param head <b> the header Point3D of the Tube </b>
     * @param dir  <b> the direction Vector of the Tube </b>
     */
    public Tube(double r, Point3D head, Vector dir) {
        super(r);
        _axis = new Ray(head, dir);
    }

    /**
     * @return double <b> radius </b>
     */
    @Override
    public double getRadius() { return super.getRadius(); }

    /**
     * @return Ray <b> axis </b>
     */
    public Ray getAxis() { return _axis; }

    /**
     * @return Point3D <b> middle </b>
     */
    public Point3D getMiddle() { return _axis.getHead(); }

    /**
     * @return Vector <b> direction </b>
     */
    public Vector getDirection() { return _axis.getDirection(); }

    /**
     * @param p <b> the Point3D in the Tube </b>
     * @return Vector <b> normal </b>
     */
    @Override
    public Vector getNormal(Point3D p) {
        //return null;
        Vector v = _axis.getDirection();
        Point3D p0 = _axis.getHead();
        double t = v.dotProduct(p.subtract(p0));
        Point3D o = p0.add(v.scale(t));
        return p.subtract(o).normalize();
    }

    /**
     * @return String <b> the info </b>
     */
    @Override
    public String toString() {
        return "Tube:\t" + super.toString() + ", _axis = " + _axis.toString();
    }
}
