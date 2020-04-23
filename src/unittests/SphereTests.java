package unittests;

import geometries.Sphere;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
            fail("TC01: Constructed a Sphere with a negative radius");
        } catch (IllegalArgumentException e) {}

        // TC02: zero radius
        try {
            new Sphere(new Point3D(1, 1, 1), 0);
            fail("TC02: Constructed a Sphere with a ZERO radius");
        } catch (IllegalArgumentException e) {}

        // TC03: correct Sphere
        try {
            new Sphere(new Point3D(1, 2, 3), 4);
        } catch (IllegalArgumentException e) {
            fail("TC03: Failed constructing a correct Sphere");
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
        assertEquals("TC01: Bad normal to Sphere ", new Vector(0, 0, 1), s.getNormal(new Point3D(1, 1, 6)));
    }

    /**
     * Test method for
     * {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(new Point3D(1, 0, 0), 1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertEquals("TC01: Ray's line out of sphere", null,
                sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals("TC02: Wrong number of points", 2, result.size());
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals("TC02: Ray crosses sphere, but not the same points", List.of(p1, p2), result);

        // TC03: Ray starts inside the sphere (1 point)
        result = sphere.findIntersections(new Ray(new Point3D(1, 0, 0.5), new Vector(0, 0, 1)));
        assertEquals("TC03: the Ray don't intersections the Sphere from the inside", List.of(new Point3D(1, 0, 1)), result);

        // TC04: Ray starts after the sphere (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, 2, 1), new Vector(0, 1, 1)));
        assertEquals("TC04: the Ray should not intersection the Sphere", null, result);

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(0, -1, 1)));
        assertEquals("TC11: the Ray should intersection the Sphere 1 time if it is on the Sphere", List.of(new Point3D(1, 0, 1)), result);

        // TC12: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(0, 1, -1)));
        assertEquals("TC12: the Ray should not intersection the Sphere if it is on the Sphere", null, result);

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        p1 = new Point3D(1, -1, 0);
        p2 = new Point3D(1, 1, 0);
        result = sphere.findIntersections(new Ray(new Point3D(1, -2, 0), new Vector(0, 1, 0)));
        assertEquals("TC13: Wrong number of points", 2, result.size());
        if (result.get(0).getY() > result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals("TC13: Ray crosses sphere, but not the same points", List.of(p1, p2), result);

        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, -1, 0), new Vector(0, 1, 0)));
        assertEquals("TC14: the Ray should intersection the Sphere 1", List.of(new Point3D(1, 1, 0)), result);

        // TC15: Ray starts inside (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, 0.7, 0), new Vector(0, 1, 0)));
        assertEquals("TC15: the Ray should intersection the Sphere 1 time", List.of(new Point3D(1, 1, 0)), result);

        // TC16: Ray starts at the center (1 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, 1, 0)));
        assertEquals("TC16: the Ray should intersection the Sphere 1 time", List.of(new Point3D(1, 1, 0)), result);

        // TC17: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(0, 1, 0)));
        assertEquals("TC17: the Ray should not intersection the Sphere if it is on the Sphere", null, result);

        // TC18: Ray starts after sphere (0 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, 2, 0), new Vector(0, 1, 0)));
        assertEquals("TC18: the Ray should not intersection the Sphere", null, result);

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        result = sphere.findIntersections(new Ray(new Point3D(1, 1, -4), new Vector(0, 0, 1)));
        assertEquals("TC19: the Ray should not intersection the Sphere", null, result);

        // TC20: Ray starts at the tangent point
        result = sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(0, 0, 1)));
        assertEquals("TC20: the Ray should not intersection the Sphere if it is on the Sphere", null, result);

        // TC21: Ray starts after the tangent point
        result = sphere.findIntersections(new Ray(new Point3D(1, 1, 4), new Vector(0, 0, 1)));
        assertEquals("TC21: the Ray should not intersection the Sphere", null, result);

        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        result = sphere.findIntersections(new Ray(new Point3D(1, 2, 0), new Vector(0, 0, 1)));
        assertEquals("TC22: the Ray should not intersection the Sphere ", null, result);
    }
}