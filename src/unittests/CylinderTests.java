package unittests;

import geometries.Cylinder;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

/**
 * implements the CylinderTests class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class CylinderTests {
    Vector axis = new Vector(0, 0, 1);

    /**
     * Test method for
     * {@link Cylinder#Cylinder(double, double, Point3D, Vector)}
     * <p>{@link Cylinder#Cylinder(double, double, Ray)}</p>
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: not positive height constructor#1
        try {
            new Cylinder(-5, 5, new Point3D(0, 0, 0), new Vector(0, 0, 1));
            fail("created a wrong Cylinder with negative height");
        } catch (IllegalArgumentException e) {}

        // TC02: not positive radius constructor#1
        try {
            new Cylinder(5, -5, new Point3D(0, 0, 0), new Vector(0, 0, 1));
            fail("created a wrong Cylinder with negative radius");
        } catch (IllegalArgumentException e) {}

        // TC03: correct Cylinder constructor#1
        try {
            new Cylinder(5, 5, new Point3D(0, 0, 0), new Vector(0, 0, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC04: not positive height constructor#2
        try {
            new Cylinder(-5, 5, new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1)));
            fail("created a wrong Cylinder with negative height");
        } catch (IllegalArgumentException e) {}

        // TC05: not positive radius constructor#2
        try {
            new Cylinder(5, -5, new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1)));
            fail("created a wrong Cylinder with negative radius");
        } catch (IllegalArgumentException e) {}

        // TC06: correct Cylinder constructor#2
        try {
            new Cylinder(5, 5, new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1)));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }
    }

    /**
     * Test method for
     * {@link Cylinder#getNormal(Point3D)}
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============

        Cylinder c = new Cylinder(3, 3, new Point3D(0, 0, 0), axis);

        // TC01: the point is on the first base
        assertEquals("wrong normal detected", axis.scale(-1), c.getNormal(new Point3D(1, 1, 0)));

        // TC02: the point is on the second base
        assertEquals("wrong normal detected", axis, c.getNormal(new Point3D(1, 1, 3)));

        // TC03: the point is on the casing
        assertEquals("wrong normal detected", new Vector(0, 1, 0), c.getNormal(new Point3D(0, 3, 2)));


        // =============== Boundary Values Tests ==================
        // we decided that in those 4 boundary situations that we will calculate the normal, as the normal to the bases.
        // TC10: the point is on the casing, and on the first base
        assertEquals("wrong normal detected", axis.scale(-1), c.getNormal(new Point3D(3, 0, 0)));

        // TC11: the point is on the casing, and on the second base
        assertEquals("wrong normal detected", axis, c.getNormal(new Point3D(3, 0, 3)));

        //TC12: the point is on the header of the axis
        assertEquals("wrong normal detected", axis.scale(-1), c.getNormal(new Point3D(0, 0, 0)));

        //TC13: the point is on the header of the axis
        assertEquals("wrong normal detected", axis, c.getNormal(new Point3D(0, 0, 3)));
    }
}