package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Unit tests for primitives.Vector class
 * @author Moriya and Hila
 */
class VectorTests {


    /**
     * Test method for {@link Vector#Vector(double, double, double)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: only one regular test to check
        assertDoesNotThrow(() -> new Vector(1,2,3), "Does not construct a Vector");


        //=============== Boundary Values Tests ==================
        // TC01: Constructor of (0,0,0) vector throws an exception
        assertThrows(IllegalArgumentException.class, ()-> new Vector(0, 0, 0),
                "Constructed (0,0,0) vector");
    }


    /**
     * Test method for {@link Vector#add(Vector)}
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: Add two regular vector one to another
        assertEquals(
                new Vector(1, 5, 1),
                new Vector(1, 2, 3).add(new Vector(0, 3, -2)),
                "ERROR: add vectors does not work correctly");


        //=============== Boundary Values Tests ==================
        //TC02: Add two vectors that gets the zero vector
        assertThrows(IllegalArgumentException.class,()->(new Vector(1, 2, 3)).add(new Vector(-1, -2, -3)),"ERROR: Vector + itself throws wrong exception");
    }


    /**
     * Test method for{@link Vector#scale(double)}
     */
    @Test
    void testScale() {
        //=============== Boundary Values Tests ==================
        //TC01: scale a vector by 0 throws an exception
        assertThrows(IllegalArgumentException.class, ()->new Vector(1,2,3).scale(0),
                "Scale a vector by zero does not throw an exception");

        // ============ Equivalence Partitions Tests ==============
        //TC02: scale a vector by a regular scalar
        assertEquals(new Vector(1, 2, 3).scale(2), new Vector(2, 4, 6),
                "scale does not return the right vector");
    }


    /**
     * Test method for {@link Vector#dotProduct(Vector)}
     */
    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct calculation of dotProduct() of orthogonal vectors
        assertTrue(isZero(new Vector(1, 2, 3).dotProduct(new Vector(0, 3, -2))), "ERROR: dotProduct() for orthogonal vectors is not zero");


        // TC02: Correct calculation of dotProduct() of 2 regular vectors
        assertTrue(isZero(new Vector(1, 2, 3).dotProduct(new Vector(-3, -2, -1)) + 10), "ERROR: dotProduct() wrong value");
    }


    /**
     * Test method for {@link Vector#crossProduct(Vector)}
     */
    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");

        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");


        // =============== Boundary Values Tests ==================

        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");

    }


    /**
     * Test method for {@link Vector#normalize()}
     */
    @Test
    void testNormalize() {
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();

        // TC01: test vector normalization vs vector length and cross-product
        assertTrue(isZero(u.length() - 1), "ERROR: the normalized vector is not a unit vector");

        // TC02: test that the vectors are co-lined
        assertThrows(IllegalArgumentException.class, ()-> v.crossProduct(u),
                "ERROR: the normalized vector is not parallel to the original one");

        // TC03: test that the vectors are not in opposite directions
        assertFalse(v.dotProduct(u) < 0, "ERROR: the normalized vector is opposite to the original one");
    }


    /**
     * Test method for{@link Vector#lengthSquared()}
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct calculation of a vectors length
        assertEquals(14,
                new Vector(1,2,3).lengthSquared(),
                "ERROR: lengthSquared() wrong value");

    }


    /**
     * Test method for {@link Vector#length()}
     */
    @Test
    void testLength() {

        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct calculation of a vectors length
        assertTrue(isZero((new Vector(0,3,4)).length()-5),"ERROR: length() wrong value");
    }
}