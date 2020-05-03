package unittests;

import static org.junit.Assert.*;

import elements.Camera;
import geometries.*;
import org.junit.Test;
import primitives.Point3D;

import primitives.Ray;
import primitives.Vector;

/**
 * implements the IntegrationTests class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class IntegrationTests {
    /**
     * Test method for Integration Tests.
     */
    @Test
    public void testRayIntersection() {
        Camera camera;

        // **** Group: Ray's intersections test with Sphere
        // TC01: Sphere test r = 1, with 2 intersections at the center Ray
        camera = new Camera(new Point3D(Point3D.ZERO), new Vector(0, 0, 1), new Vector(0, -1, 0));
        assertEquals("TC01: should be 2 intersections with the Sphere !", 2, sumOfIntersections(camera, new Sphere(new Point3D(0, 0, 3), 1)));
        // TC02: Sphere test r = 2.5, with 18 intersections, enter and exit of all of the Rays
        camera = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
        assertEquals("TC02: should be 18 intersections with the Sphere !", 18, sumOfIntersections(camera, new Sphere(new Point3D(0, 0, 2.5), 2.5)));
        // TC03: Sphere test r = 2, with 10 intersections, enter and exit of all of the Ray excluded the corners
        assertEquals("TC03: should be 10 intersections with the Sphere !", 10, sumOfIntersections(camera, new Sphere(new Point3D(0, 0, 2), 2)));
        // TC04: Sphere test r = 4, with 9 intersections exit for all of the Rays
        assertEquals("TC04: should be 9 intersections with the Sphere !", 9, sumOfIntersections(camera, new Sphere(new Point3D(0, 0, 1), 4)));
        // TC05: Sphere test r = 0.5, with 0 intersections, the Sphere is behind the camera
        assertEquals("TC05: should not be any intersections with the Sphere !", 0, sumOfIntersections(camera, new Sphere(new Point3D(0, 0, -1), 0.5)));

        // **** Group: Ray's intersections test with Plane
        // TC11: the plane is parallel to the view plane and toward to the camera, with 9 intersections
        assertEquals("TC11: should be 9 intersections with the Plane !", 9, sumOfIntersections(camera, new Plane(new Point3D(0, 0, 3), new Vector(0, 0, 1))));
        // TC12: the plane is not parallel to the view plane and toward to the camera, with 9 intersections
        assertEquals("TC12: should be 9 intersections with the Plane !", 9, sumOfIntersections(camera, new Plane(new Point3D(0, 0, 10), new Vector(0, -0.5, 1))));
        //TC13: the plane is not parallel to the view plane and toward to the camera, with 6 intersections
        assertEquals("TC13: should be 6 intersections with the Plane !", 6, sumOfIntersections(camera, new Plane(new Point3D(0, 0, 10), new Vector(0, -1, 1))));

        // **** Group: Ray's intersections test with Triangle
        // TC21: the triangle is parallel to the view plane and has 1 intersection
        assertEquals("TC21: should be 1 intersection with the Triangle !", 1, sumOfIntersections(camera, new Triangle(new Point3D(0, -1, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2))));
        // TC22: the triangle is parallel to the view plane with 2 intersections
        assertEquals("TC22: should be 2 intersections with the Triangle !", 2, sumOfIntersections(camera, new Triangle(new Point3D(0, -20, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2))));
    }

    /**
     * this method work only with 3x3 matrix.
     * <p>
     * HxW = 3x3.
     * <p>
     * the distance = 1.
     * <p>
     * this method using the constructRayThroughPixel() function.
     *
     * @param camera <b> the given Camera </b>
     * @param geoObj <b> the geometries object that we checking the intersections with </b>
     * @return int <b> the sum of the intersections, with the Ray's Camera </b>
     */
    public int sumOfIntersections(Camera camera, Geometry geoObj) {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRayThroughPixel(3, 3, i, j, 1, 3, 3);
                if (geoObj.findIntersections(ray) != null) {
                    sum += geoObj.findIntersections(ray).size();
                }
            }
        }
        return sum;
    }
}
