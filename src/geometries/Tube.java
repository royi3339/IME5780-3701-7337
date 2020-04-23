package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * implements the Tube class, which extending the RadialGeometry class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Tube extends RadialGeometry {
    protected Ray axis;

    /**
     * <b> Tube constructor. </b>
     *
     * @param r <b> the _radius of the Tube </b>
     * @param a <b> the axis of the Tube </b>
     */
    public Tube(double r, Ray a) {
        super(r);
        axis = new Ray(a);
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
        axis = new Ray(head, dir);
    }

    /**
     * @return double <b> radius </b>
     */
    @Override
    public double getRadius() {
        return super.getRadius();
    }

    /**
     * @return Ray <b> axis </b>
     */
    public Ray getAxis() {
        return axis;
    }

    /**
     * @return Point3D <b> middle </b>
     */
    public Point3D getMiddle() {
        return axis.getHead();
    }

    /**
     * @return Vector <b> direction </b>
     */
    public Vector getDirection() {
        return axis.getDirection();
    }

    /**
     * @param p <b> the Point3D in the Tube </b>
     * @return Vector <b> normal </b>
     */
    @Override
    public Vector getNormal(Point3D p) {
        Vector v = getDirection();
        Point3D p0 = axis.getHead();
        // if the point if on the axis
        if (p.equals(p0)) {
            return getDirection().scale(-1);
        }
        // if the point is on the base
        if (isZero(v.dotProduct(p.subtract(p0)))) {
            return getDirection().scale(-1);
        }
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
     * @param ray <b> the Ray we will find his intersections </b>
     * @return List<Point3D> <b> find the intersections </b>
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Point3D baseIntersection = findIntersectionsBase(ray, axis.getHead(), axis.getDirection());
        List<Point3D> result = new ArrayList<Point3D>();
        //check the base intersection
        if (baseIntersection != null) {
            result.add(baseIntersection);
        }
        //check the casing intesection
        List<Point3D> casingIntersection = findIntersectionCasing(ray);
        if (casingIntersection != null) {
            result.add(casingIntersection.get(0));//לבדוק שהוא לא בחוץ
            if (casingIntersection.size() == 2) {
                result.add(casingIntersection.get(1));//לבדוק שהוא לא בחוץ
            }
        }

        if(result.size()==0)
            return null;
        return result;
    }


    protected List<Point3D> findIntersectionCasing(Ray ray) {

    }

    protected Point3D findIntersectionsBase(Ray ray, Point3D o, Vector n) {
        Plane base = new Plane(o, n);
        List<Point3D> baseIntersections = base.findIntersections(ray);
        if (baseIntersections != null && baseIntersections.get(0).subtract(o).length() <= _radius) {
            return baseIntersections.get(0);
        }
        return null;
    }

}
