package unittests;

import geometries.Plane;
import geometries.Polygon;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;

public class PlaneTests {
    /**
     * test methot for
     * {@link Plane#getNormal(Point3D)}
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Correct normal of the plane
        double sqrt3 = Math.sqrt(1d / 3);
        Plane p1=new Plane(new Point3D(0,0,1),new Point3D(1,0,0),new Point3D(0,1,0));
        Plane p2=new Plane(new Point3D(0,0,1),new Vector(sqrt3,sqrt3,sqrt3));
        Point3D point=new Point3D(2,5,8);
        assertEquals("Bad normal to plane", p1.getNormal(point), p2.getNormal(point));
    }
}