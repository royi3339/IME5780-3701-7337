package unittests;

import geometries.*;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.Assert.*;

/**
 * implements GeometriesTests class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class GeometriesTests {

    /**
     * test method for
     * {@link geometries.Geometries#findIntersections(Ray)}
     */
    @Test
    public void findIntersectionsTest() {
        Geometries geoList = new Geometries();
        Ray rT = new Ray(new Point3D(0, 5, -11), new Vector(0, 0, 1));

        // =============== Boundary Values Tests ==================
        // TC11: empty object collection
        assertEquals("TC11: Error ! ! ! should not be found any intersections", null, geoList.findIntersections(rT));

        // TC12: there is no intersections at all
        Ray r = new Ray(new Point3D(0, 0, -10), new Vector(0, 2, 0));
        Triangle triangle = new Triangle(new Point3D(15, 0, 0), new Point3D(-15, 0, 0), new Point3D(0, 15, 0));
        Plane plane = new Plane(new Point3D(15, 0, 2), new Point3D(-15, 0, 2), new Point3D(0, 15, 2));
        Sphere sphere = new Sphere(new Point3D(0, 5, 22), 1);
        Polygon polygon = new Polygon(new Point3D(-1, 6, 1), new Point3D(3, 6, 1),
                new Point3D(6, 2, 1), new Point3D(2, 0, 1), new Point3D(-2, -1, 1));
        geoList.add(plane, sphere, polygon, triangle);
        assertEquals("TC12: Error ! ! ! should not be found any intersections", null, geoList.findIntersections(r));

        // TC13: only 1 object intersection
        Ray r2 = new Ray(new Point3D(0, 5, 11), new Vector(0, 0, 1));
        assertEquals("TC13: Error ! ! ! should be found only 0ne intersection", 2, geoList.findIntersections(r2).size());

        // TC14: all of the objects will intersections
        assertEquals("TC14: Error ! ! ! all of the objects should be intersections", 5, geoList.findIntersections(rT).size());

        // ============ Equivalence Partitions Tests ==============
        // TC01: few (2) object will intersections, but not all of them
        geoList.add(new Sphere(new Point3D(0, 7, 9), 1), new Sphere(new Point3D(0, 5, 9), 1));
        Ray r3 = new Ray(new Point3D(0, 1, 9), new Vector(0, 1, 0));
        assertEquals("TC01: Error ! ! ! should be intersections with few objects, but not all of them", 4, geoList.findIntersections(r3).size());
    }
}