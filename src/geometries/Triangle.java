package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

/**
 * implements Triangle class, which extending the Polygon class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Triangle extends Polygon {

    /**
     * Triangle constructor.
     *
     * @param pA <b> the first Point3D of  the Triangle </b>
     * @param pB <b> the second Point3D of  the Triangle </b>
     * @param pC <b> the third Point3D of  the Triangle </b>
     */
    public Triangle(Point3D pA, Point3D pB, Point3D pC) { super(pA, pB, pC); }

    /**
     * @return Point3D <b> point #1 </b>
     */
    public Point3D getP1() { return _vertices.get(0); }

    /**
     * @return Point3D <b> point #2 </b>
     */
    public Point3D getP2() { return _vertices.get(1); }

    /**
     * @return Point3D <b> point #3 </b>
     */
    public Point3D getP3() { return _vertices.get(2); }

    /**
     * @return String <b> the info </b>
     */
    @Override
    public String toString() {
        return "Triangle:\t" + "point a = " + _vertices.get(0).toString() + ", point b = " + _vertices.get(1).toString() + ", point c = " + _vertices.get(2).toString();
    }

    /**
     * @param ray <b> the Ray we will find his intersections </b>
     * @return List<Point3D> <b> find the intersections </b>
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
