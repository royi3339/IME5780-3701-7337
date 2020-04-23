package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

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
            // using the Tube getNormal method.
            return super.getNormal(p);
        }

        // if the Point 3D is on the end of the axis direction
        if (p.equals(axis.getHead().add(getDirection().scale(_height)))) {
            return getDirection();
        }

        // if the Point 3D is on the second base
        Vector subVector = axis.getHead().add(getDirection().scale(_height)).subtract(p);
        if (isZero(getDirection().dotProduct(subVector))) {
            return getDirection();
        }

        // if the Point3D is on the casing, and the first base which implements in the Tube class.
        return super.getNormal(p);
    }

    /**
     * @param ray <b> the Ray we will find his intersections </b>
     * @return List<Point3D> <b> find the intersections </b>
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> result=new ArrayList<Point3D>();
        Point3D base1=this.findIntersectionsBase(ray,axis.getHead(),axis.getDirection());
        if(base1!=null)
        {
            result.add(base1);
        }
        Point3D base2=this.findIntersectionsBase(ray,axis.getPoint(_height),axis.getDirection());
        if(base2!=null)
        {
            result.add(base2);
        }
        //בדיקה גליל והאם יצא מהגליל
        //לבדוק כפילויות
    }
}
