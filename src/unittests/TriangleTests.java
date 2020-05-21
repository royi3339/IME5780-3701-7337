package unittests;


import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

import geometries.Intersectable.GeoPoint;

/**
 * implements the TriangleTests class.
 *
 * @author Royi Alishayev idan darmoni
 */
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
        assertEquals("TC01: the get normal function not correct", normal, t.getNormal(new Point3D(4, 5, 8)));
    }

    @Test
    public void testFindIntersections() {
        Triangle triangle = new Triangle(new Point3D(1, 1, 5), new Point3D(3, 1, 5), new Point3D(3, 3, 5));
        // ============ Equivalence Partitions Tests ==============
        // TC01: inside triangle
        Ray ray = new Ray(new Point3D(2, 1.5, 0), new Vector(0, 0, 1));
        List<GeoPoint> result = triangle.findIntersections(ray);
        assertEquals("TC01 : intersect in the triangle", List.of(new GeoPoint(triangle, new Point3D(2, 1.5, 5))), result);
        // TC02 : Outside against edge
        ray = new Ray(new Point3D(1, 3, 0), new Vector(0, 0, 1));
        result = triangle.findIntersections(ray);
        assertEquals("TC02 : intersect Outside against edge", null, result);
        // TC03 : Outside against vertex
        ray = new Ray(new Point3D(4, 0, 0), new Vector(0, 0, 1));
        result = triangle.findIntersections(ray);
        assertEquals("TC03 : intersect Outside against vertex", null, result);

        // =============== Boundary Values Tests ==================
        // TC11 : On edge
        ray = new Ray(new Point3D(2, 2, 0), new Vector(0, 0, 1));
        result = triangle.findIntersections(ray);
        assertEquals("TC11 : intersect On edge", null, result);
        // TC12 : In vertex
        ray = new Ray(new Point3D(1, 1, 0), new Vector(0, 0, 1));
        result = triangle.findIntersections(ray);
        assertEquals("TC12 : intersect In vertex", null, result);
        // TC13 : On edge's continuation
        ray = new Ray(new Point3D(4, 4, 0), new Vector(0, 0, 1));
        result = triangle.findIntersections(ray);
        assertEquals("TC13 : intersect On edge's continuation", null, result);
    }
}