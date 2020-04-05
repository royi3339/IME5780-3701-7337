package unittests;

import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

/**
 * implements the SphereTests class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class SphereTests {
    /**
     * test method for
     * {@link Sphere#Sphere(Point3D, double)}
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: negative radius
        try {
            new Sphere(new Point3D(1, 1, 1), -5);
            fail("Constructed a Sphere with a negative radius");
        } catch (IllegalArgumentException e) {}

        // TC02: zero radius
        try {
            new Sphere(new Point3D(1, 1, 1), 0);
            fail("Constructed a Sphere with a ZERO radius");
        } catch (IllegalArgumentException e) {}

        // TC03: correct Sphere
        try {
            new Sphere(new Point3D(1, 2, 3), 4);
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct Sphere");
        }
    }

    /**
     * test method for
     * {@link Sphere#getNormal(Point3D)}
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: check the normal
        Sphere s = new Sphere(new Point3D(1, 1, 1), 5);
        assertEquals(" Bad normal to Sphere ", new Vector(0, 0, 1), s.getNormal(new Point3D(1, 1, 6)));
    }
}