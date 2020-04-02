package unittests;

import geometries.Plane;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

public class TriangleTests {
    /**
     * test method for
     * {@link Triangle#getNormal(Point3D)}
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Correct normal of the triangle
        double sqrt3 = Math.sqrt(1d / 3);
        Vector normal = new Vector(sqrt3, sqrt3, sqrt3);
        Triangle t = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        assertEquals("the get normal function not correct", normal, t.getNormal(new Point3D(4, 5, 8)));
    }
}