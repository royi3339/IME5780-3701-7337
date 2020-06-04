package unittests;

import geometries.Plane;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.Assert.*;

import geometries.Intersectable.GeoPoint;

/**
 * implements the PlaneTests class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class PlaneTests {
    /**
     * test method for
     * {@link Plane#getNormal(Point3D)}
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Correct normal of the plane
        double sqrt3 = Math.sqrt(1d / 3);
        Plane p1 = new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        Plane p2 = new Plane(new Point3D(0, 0, 1), new Vector(sqrt3, sqrt3, sqrt3));
        Point3D point = new Point3D(2, 5, 8);
        assertEquals("TC01: Bad normal to plane", p1.getNormal(point), p2.getNormal(point));
    }

    /**
     * test method for
     * {@link Plane#findIntersections(Ray)}
     */
    @Test
    public void testFindIntersections() {
        List<GeoPoint> result;
        Plane plane = new Plane(new Point3D(0, 0, 1), new Vector(0, 0, 1));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray intersects the Plane
        Ray ray = new Ray(new Point3D(4, 8, 0), new Vector(0, 1, 1));
        result = plane.findIntersections(ray);
        assertEquals("TC01: the Ray should intersect the Plane", List.of(new GeoPoint(plane, new Point3D(4, 9, 1))), result);

        // TC02: Ray does not intersect the Plane
        ray = new Ray(new Point3D(4, 8, 9), new Vector(0, 1, 1));
        result = plane.findIntersections(ray);
        assertEquals("TC02: the Ray should not intersect the Plane", null, result);


        // =============== Boundary Values Tests ==================

        // **** Group: Ray is parallel to the plane
        // TC11: Ray is parallel to the plane, and the Ray included in the plane
        ray = new Ray(new Point3D(0, 0, 1), new Vector(0, 1, 0));
        result = plane.findIntersections(ray);
        assertEquals("TC11: the Ray should not intersect the Plane", null, result);

        // TC12: Ray is parallel to the plane, and the Ray not included in the plane
        ray = new Ray(new Point3D(0, 0, -1), new Vector(0, 1, 0));
        result = plane.findIntersections(ray);
        assertEquals("TC12: the Ray should not intersect the Plane", null, result);


        // **** Group: Ray is orthogonal to the plane
        // TC13: Ray is orthogonal to the plane, and before the Plane
        ray = new Ray(new Point3D(1, 1, -1), new Vector(0, 0, 1));
        result = plane.findIntersections(ray);
        assertEquals("TC13: the Ray should intersect the Plane", List.of(new GeoPoint(plane, new Point3D(1, 1, 1))), result);



      //  Plane plane = new Plane(new Point3D(0, 0, 1), new Vector(0, 0, 1));
        // TC14: Ray is orthogonal to the plane, and in the Plane
        ray = new Ray(new Point3D(1, 1, 1), new Vector(0, 0, 1));
        result = plane.findIntersections(ray);
        assertEquals("TC14: the Ray should not intersect the Plane", null, result);

        // TC15: Ray is orthogonal to the plane, and after the Plane
        ray = new Ray(new Point3D(4, 8, 9), new Vector(0, 0, 1));
        result = plane.findIntersections(ray);
        assertEquals("TC15: the Ray should not intersect the Plane", null, result);
        //          End Group ! ! !

        // TC16: Ray is neither orthogonal nor parallel to and begins at the plane
        ray = new Ray(new Point3D(4, 8, 1), new Vector(0, 1, 1));
        result = plane.findIntersections(ray);
        assertEquals("TC16: the Ray should not intersect the Plane", null, result);

        // TC17: Ray is neither orthogonal nor parallel to the plane and begins in the same point which appears as reference point in the plane
        ray = new Ray(new Point3D(0, 0, 1), new Vector(0, 1, 1));
        result = plane.findIntersections(ray);
        assertEquals("TC17: the Ray should not intersect the Plane", null, result);
    }
}