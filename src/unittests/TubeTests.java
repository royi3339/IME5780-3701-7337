package unittests;

import geometries.Tube;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

/**
 * implements the TubeTests class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class TubeTests {

    /**
     * test method for
     * * {@link Tube#Tube(double, Ray)}
     * <p>
     * test method for
     * * {@link Tube#Tube(double, Point3D, Vector)}
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: negative radius constructor#1
        try {
            new Tube(-5, new Point3D(0, 0, 0), new Vector(0, 0, 13));
            fail("Constructed a Sphere with a not positive radius");
        } catch (IllegalArgumentException e) {}

        // TC02: negative radius constructor#1
        try {
            new Tube(-5, new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 13)));
            fail("Constructed a Sphere with a not positive radius");
        } catch (IllegalArgumentException e) { }

        // TC03: correct Tube
        try {
            new Tube(7, new Point3D(0, 0, 0), new Vector(0, 0, 13));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Sphere");
        }

        // TC04: correct Tube
        try {
            new Tube(7, new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 13)));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Sphere");
        }
    }

    /**
     * test method for
     * * {@link Tube#getNormal(Point3D)}
     */
    @Test
    public void testGetNormal() {
        Tube t = new Tube(2, new Point3D(0, 0, 0), new Vector(0, 0, 1));
        // ============ Equivalence Partitions Tests ==============
        //TC01: the point is on the casing
        assertEquals("wrong normal detected", new Vector(0, 1, 0), t.getNormal(new Point3D(0, 2, 3339)));

        // =============== Boundary Values Tests ==================
        // we decided that in those 2 boundary situations that we will calculate the normal, as the normal to the bases.
        //TC11: the point is on the base
        assertEquals("wrong normal detected", new Vector(0, 0, -1), t.getNormal(new Point3D(0, 2, 0)));

        //TC11: the point is on the axis
        assertEquals("wrong normal detected", new Vector(0, 0, -1), t.getNormal(new Point3D(0, 0, 0)));
    }
}