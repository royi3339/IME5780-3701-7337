package unittests;

import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.Assert.*;
import static primitives.Util.isZero;

public class VectorTests {
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);
    Vector v = new Vector(1, 2, 3);
    Point3D p1 = new Point3D(1, 2, 3);

    /**
     * Test method for
     * {@link primitives.Vector#Vector(double, double, double)}
     */
    @Test
    public void testConstructor() {
        try { // test zero vector
            new Vector(0, 0, 0);
            fail("ERROR: zero vector does not throw an exception");
        } catch (Exception e) {}
    }


    /**
     * Test method for
     * {@link primitives.Vector#subtract(primitives.Vector)}
     */
    @Test
    public void subtract() {
        assertEquals("ERROR: Point - Point does not work correctly", new Vector(1, 1, 1),new Point3D(2, 3, 4).subtract(p1));
    }

    /**
     * Test add
     */
    @Test
    public void add() {
        assertEquals("ERROR: Point + Vector does not work correctly", Point3D.ZERO,p1.add(new Vector(-1, -2, -3)));
    }

    /**
     * Test scale
     */
    @Test
    public void scale() {
        Vector vv1 = new Vector(v);
        vv1 = vv1.scale(3);
        Vector vv2 = new Vector(3, 6, 9);
        assertEquals("ERROR: the scale does not work correctly", vv2,vv1);
    }

    /**
     * Test method for
     * {@link primitives.Vector#dotProduct(Vector)}
     * test Dot-Product
     */
    @Test
    public void dotProduct() {
        assertTrue("ERROR: dotProduct() for orthogonal vectors is not zero", isZero(v1.dotProduct(v3)));
        assertEquals("ERROR: dotProduct() wrong value", -28, v1.dotProduct(v2), 1E-8);
    }

    /**
     * test Cross-Product
     * {@link Vector#crossProduct(Vector)}
     */
    @Test
    public void crossProduct() {
        try { // test zero vector
            v1.crossProduct(v2);
            fail("ERROR: crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}
        Vector vr = v1.crossProduct(v3);
        assertTrue("ERROR: crossProduct() wrong result length", isZero(vr.length() - v1.length() * v3.length()));
        assertTrue("ERROR: crossProduct() result is not orthogonal to its operands", isZero(vr.dotProduct(v1)) && isZero(vr.dotProduct(v3)));
    }

    /**
     * test length squared
     * {@link Vector#lengthSquared()}
     */
    @Test
    public void lengthSquared() {
        assertEquals("ERROR: lengthSquared() wrong value", 14, v1.lengthSquared(), 1E-8);
    }

    /**
     * test length
     */
    @Test
    public void length() {
        assertEquals("ERROR: length() wrong value", 5,new Vector(0, 3, 4).length(), 1E-8);
    }

    /**
     * test vector normalization vs vector length and cross-product
     */
    @Test
    public void normalize() {
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v);
        Vector vCopyNormalize = vCopy.normalize();
        assertEquals("ERROR: normalize() function creates a new vector", vCopy, vCopyNormalize);
        assertEquals("ERROR: normalize() result is not a unit vector", 1,  vCopyNormalize.length(),1E-8);
    }

    /**
     * test vector normalized
     */
    @Test
    public void normalized() {
        Vector u = v.normalized();
        assertNotEquals("ERROR: normalizated() function does not create a new vector", u, v);
    }
}