package geometries;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

/**
 * Testing Triangle
 *
 * @author Hila
 *
 */

class TriangleTests {


    /**
     * Test method for {@link geometries.Triangle#Triangle(Point, Point, Point)} .
     */

    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct creation of a triangle
        try {
            new Triangle(new Point(-7, 2, 0), new Point(3, 2, 3), new Point(0, 4, 3));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct triangle");
        }

        // =============== Boundary Values Tests ==================

        // TC02: Vertex on a same line
        assertThrows(IllegalArgumentException.class,
                () -> new Triangle(new Point(0, 1, 0), new Point(0, 0, 0), new Point(0, 6, 0)),
                "Constructed a triangle with vertex on a single line");

        // TC03: Last point = first point
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(0, 0, 1)),
                "Constructed a triangle with vertices on a side");

        // TC045: Co-located points
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(0, 1, 0)),
                "Constructed a triangle with vertices on a side");

    }


    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Triangle tr = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3), tr.getNormal(new Point(0, 0, 1)), "Bad normal to triangle");
    }

}