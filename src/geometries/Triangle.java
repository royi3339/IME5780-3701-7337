package geometries;

import primitives.Color;
import primitives.Point3D;

/**
 * implements Triangle class, which extending the {@link Polygon} class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Triangle extends Polygon {

    /**
     * <b> Triangle with {@link Color} constructor. </b>
     *
     * @param color <b> the {@link Color} of the Triangle </b>
     * @param pA    <b> the first {@link Point3D} of  the Triangle </b>
     * @param pB    <b> the second {@link Point3D} of  the Triangle </b>
     * @param pC    <b> the third {@link Point3D} of  the Triangle </b>
     */
    public Triangle(Color color, Point3D pA, Point3D pB, Point3D pC) {
        this(pA, pB, pC);
        _emmission = new Color(color);
    }

    /**
     * <b> Triangle constructor. </b>.
     *
     * @param pA <b> the first {@link Point3D} of  the Triangle </b>
     * @param pB <b> the second {@link Point3D} of  the Triangle </b>
     * @param pC <b> the third {@link Point3D} of  the Triangle </b>
     */
    public Triangle(Point3D pA, Point3D pB, Point3D pC) { super(pA, pB, pC); }

    /**
     * @return {@link Point3D} <b> point #1 </b>
     */
    public Point3D getP1() { return _vertices.get(0); }

    /**
     * @return {@link Point3D} <b> point #2 </b>
     */
    public Point3D getP2() { return _vertices.get(1); }

    /**
     * @return {@link Point3D} <b> point #3 </b>
     */
    public Point3D getP3() { return _vertices.get(2); }

    /**
     * @return String <b> the info </b>
     */
    @Override
    public String toString() {
        return "Triangle:\t" + "point a = " + _vertices.get(0).toString() + ", point b = " + _vertices.get(1).toString() + ", point c = " + _vertices.get(2).toString();
    }
}
