package geometries;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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


    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    @Test
    void testFindIntersections() {

        // ============ Equivalence Partitions Tests ==============
        Triangle tr = new Triangle(new Point(2, 0, 0), new Point(0, 2,0 )
                , new Point(0, -2, 0));
        Ray r;
        Point p;
        List<Point> result;


        // TC01: Ray intersects inside the triangle
        result = tr.findIntersections(new Ray(new Point(0, 0, -2), new Vector(1, 0, 2)));

        assertEquals(
                1,
                result.size(),
                "Ray intersects inside the triangle - wrong number of intersections");

        assertEquals(
                new Point(1, 0, 0),
                result.get(0),
                "Ray intersects inside the triangle - wrong Point of intersection");


        // TC02: Ray outside the triangle against edge
        assertNull(
                tr.findIntersections(new Ray(new Point(-1, 0, -1), new Vector(0, 0, 1))),
                "Ray outside the triangle against edge - found an intersection");


        // TC03: Ray outside the triangle against vertex
        r = new Ray(new Point(3, 0, -1), new Vector(0, 0, 1));
        assertNull(
                tr.findIntersections(new Ray(new Point(3, 0, -1), new Vector(0, 0, 1))),
                "Ray outside the triangle against vertex- found an intersection");


        // =============== Boundary Values Tests ==================

        // TC04: Ray begins before the triangle and intersects on the edge
        assertNull(
                tr.findIntersections(new Ray(new Point(0,0,-1), new Vector(0, 0, 1))),
                "Ray begins before the triangle and intersects on the edge - didn't return null");

        // TC05: Ray begins before the triangle and intersects on the vertex
        assertNull(
                tr.findIntersections(new Ray(new Point(2,0,-1), new Vector(0, 0, 1))),
                "Ray begins before the triangle and intersects on the vertex - didn't return null");

        // TC06: Ray starts before the triangle and intersects on edge's continuation
        assertNull(
                tr.findIntersections(new Ray(new Point(0,3,-1), new Vector(0, 0, 1))),
                "Ray starts before the triangle and intersects on edge's continuation - didn't return null");

    }
}