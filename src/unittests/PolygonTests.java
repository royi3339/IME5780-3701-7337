/**
 *
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.*;
import primitives.*;

import java.util.List;

/**
 * Testing Polygons
 * @author Dan
 *
 */
public class PolygonTests {

    /**
     * Test method for
     * {@link geometries.Polygon#Polygon(primitives.Point3D, primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {}

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {}

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {}

        // =============== Boundary Values Tests ==================

        // TC10: Vertix on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {}

        // TC11: Last point = first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

        // TC12: Collocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

    }

    /**
     * Test method for
     * {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
    }
    @Test
    public void testFindIntersections(){
        Polygon polygon=new Polygon(new Point3D(1,1,5),new Point3D(3,1,5),
                new Point3D(6,2,5),new Point3D(5,3,5),new Point3D(3,3,5));
        // ============ Equivalence Partitions Tests ==============
        // TC01: inside polygon
        Ray ray=new Ray(new Point3D(2,1.5,0),new Vector(0,0,1));
        List<Point3D> result=polygon.findIntersections(ray);
        assertEquals("TC01 : intersect in the polygon",List.of(new Point3D(2,1.5,5)),result);
        // TC02 : Outside against edge
        ray=new Ray(new Point3D(1,3,0),new Vector(0,0,1));
        result=polygon.findIntersections(ray);
        assertEquals("TC02 : intersect Outside against edge",null,result);
        // TC03 : Outside against vertex
        ray=new Ray(new Point3D(7,2,0),new Vector(0,0,1));
        result=polygon.findIntersections(ray);
        assertEquals("TC03 : intersect Outside against vertex",null,result);

        // =============== Boundary Values Tests ==================
        // TC11 : On edge
        ray=new Ray(new Point3D(2,2,0),new Vector(0,0,1));
        result=polygon.findIntersections(ray);
        assertEquals("TC11 : intersect On edge",null,result);
        // TC12 : In vertex
        ray=new Ray(new Point3D(1,1,0),new Vector(0,0,1));
        result=polygon.findIntersections(ray);
        assertEquals("TC12 : intersect In vertex",null,result);
        // TC13 : On edge's continuation
        ray=new Ray(new Point3D(4,4,0),new Vector(0,0,1));
        result=polygon.findIntersections(ray);
        assertEquals("TC13 : intersect On edge's continuation",null,result);

    }
}
