package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Testing Geometries
 *
 * @author Moriya Moskovich
 *
 */
class GeometriesTest {

    @Test
    void add() {
    }

    /**
     * Test method for {@link Geometries#findIntersections(Ray)}.
     */
    @Test
    void findIntersections() {
        Geometries geometries = new Geometries(
                new Sphere(2,new Point(2,0,0)),
                new Plane(new Point(1,1,0.5), new Vector(0, 0, 1)),
                new Triangle(new Point(0, 1, 0), new Point(0, -1, 0), new Point(3,0,0))
        );

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray intersects only sphere and plane (3 points)
        assertEquals(3,
                geometries.findIntersections(
                        new Ray(new Point(1,1,-2), new Vector(0,0,1))).size(),
                "Ray intersects only sphere and plane (3 points)- wrong points of intersection");


        // =============== Boundary Values Tests ==================

        // TC02: Ray intersects all the geometries (4 points)
        assertEquals(4,
                geometries.findIntersections(
                        new Ray(new Point(0.5, 0.5, -2), new Vector(0, 0, 1))).size(),
                "Ray intersects all the geometries (4 points) - wrong points of intersection");


        // TC03: Ray intersects one the geometries (1 point)
        assertEquals(1,
                geometries.findIntersections(
                        new Ray(new Point(5, 0, -2), new Vector(0, 0, 1))).size(),
                "Ray intersects one the geometries (1 points) - wrong points of intersection");


        // TC04: Ray doesn't intersect any geometries (0 points)
        assertNull( geometries.findIntersections(
                        new Ray(new Point(2.5,-5.5,-2), new Vector(6.5,10.5,-3))),
                "Ray doesn't intersect any geometries (0 points) - found a point of intersection");


        // TC05: Empty collection of geometries
        assertNull(new Geometries().findIntersections(
                new Ray(new Point(2.5,-5.5,-2), new Vector(6.5,10.5,-3))),
                "Empty collection of geometries - found an intersection");

    }
}