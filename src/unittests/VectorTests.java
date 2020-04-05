package unittests;

import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

/**
 * implements the VectorTests class.
 *
 * @author Royi Alishayev idan darmoni
 */
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
        // ============ Equivalence Partitions Tests ==============
        // TC01: test constructor
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
    public void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test the subtract
        assertEquals("ERROR: Point - Point does not work correctly", new Vector(1, 1, 1), new Point3D(2, 3, 4).subtract(p1));
        try {
            new Vector(1, 1, 1).subtract(new Vector(1, 1, 1));
            fail("ERROR: zero vector does not throw an exception");
        } catch (IllegalArgumentException e) { }
    }

    /**
     * Test method for
     * {@link Vector#add(Vector)}
     */
    @Test
    public void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test the add
        assertEquals("ERROR: Point + Vector does not work correctly", Point3D.ZERO, p1.add(new Vector(-1, -2, -3)));
        try {
            new Vector(1, 1, 1).add(new Vector(-1, -1, -1));
            fail("ERROR: zero vector does not throw an exception");
        } catch (IllegalArgumentException e) { }
    }

    /**
     * Test method for
     * {@link Vector#scale(double)}
     */
    @Test
    public void testScale() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test the scale
        Vector vv1 = new Vector(v);
        vv1 = vv1.scale(3);
        Vector vv2 = new Vector(3, 6, 9);
        assertEquals("ERROR: the scale does not work correctly", vv2, vv1);
        try {
            vv1 = vv1.scale(0);
            fail("ERROR: zero vector does not throw an exception");
        } catch (IllegalArgumentException e) {}
    }

    /**
     * Test method for
     * {@link primitives.Vector#dotProduct(Vector)}
     * test Dot-Product
     */
    @Test
    public void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test the dot product
        assertTrue("ERROR: dotProduct() for orthogonal vectors is not zero", isZero(v1.dotProduct(v3)));
        assertEquals("ERROR: dotProduct() wrong value", -28, v1.dotProduct(v2), 1E-8);
    }

    /**
     * test Cross-Product
     * {@link Vector#crossProduct(Vector)}
     */
    @Test
    public void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test the cross product
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
    public void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test the length squared
        assertEquals("ERROR: lengthSquared() wrong value", 14, v1.lengthSquared(), 1E-8);
    }

    /**
     * test method for
     * {@link Vector#length()}
     */
    @Test
    public void testLength() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test the length
        assertEquals("ERROR: length() wrong value", 5, new Vector(0, 3, 4).length(), 1E-8);
    }

    /**
     * test vector normalization vs vector length and cross-product
     * test method for
     * {@link Vector#normalize()}
     */
    @Test
    public void testNormalize() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test the normalize
        Vector vCopy = new Vector(v);
        Vector vCopyNormalize = vCopy.normalize();
        assertEquals("ERROR: normalize() function creates a new vector", vCopy, vCopyNormalize);
        assertEquals("ERROR: normalize() result is not a unit vector", 1, vCopyNormalize.length(), 1E-8);
    }

    /**
     * test vector normalized
     * {@link Vector#normalized()}
     */
    @Test
    public void testNormalized() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test the normalized
        Vector uu = new Vector(v);
        Vector u = v.normalized();
        assertNotEquals("ERROR: normalized() function does not create a new vector", u, v);
        assertEquals("ERROR: normalized() function changed the  original vector", uu, v);
    }
}