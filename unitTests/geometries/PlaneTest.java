package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Testing Planes
 *
 * @author Moriya Moskovich
 *
 */
class PlaneTest {

    /**
     * Test method for {@link geometries.Plane#Plane(Point, Point, Point)}.
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct construction of a plane with different Points
        try {
            Plane p = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));
            assertEquals(p.getNormal().length(), 1, "dir vector constructed is not normalized");
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct plane");
        }

        // =============== Boundary Values Tests ==================

        // TC02: Points generate the same direction vector
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 5), new Point(0, 0, 2)),
                "Constructed a plane with two parallel vectors");

        // TC03: Co-located Points cannot generate s plane
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(3, 2, 1), new Point(3, 2, 1), new Point(1, 1, 1)),
                "Constructed a plane with two parallel vectors");

    }


    /**
     * Test method for {@link Plane#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(new Vector(sqrt3, sqrt3, sqrt3),
                new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0))
                        .getNormal(),
                "Bad normal to plane");
    }


    /**
     * Test method for {@link Plane#findIntersections(Ray)}.
     */
    @Test
    void testFindIntersections() {

        Plane pl = new Plane (new Point(0, 1, 0), new Vector(0,0,1));
        List<Point> result;

        // ============ Equivalence Partitions Tests ==============

        //TC01: A normal cut of a ray starting out of the plane and making
        // a non-right angle with the plane
        result = pl.findIntersections(
                new Ray(new Point(0, 0, -1), new Vector(0, 1, 1))
        );

        assertEquals(
                1,
                result.size(),
                "Ray intersects the plane - wrong number of intersections");

        assertEquals(
                new Point(0,1,0),
                result.get(0),
                "Ray intersects the plane - wrong Point of intersection");


        // TC02: Ray does not intersect the plane
        result = pl.findIntersections(new Ray(new Point(0, 0, 1), new Vector(0, 1, 1)));

        assertNull(
                result,
                "Ray does not intersect the plane - found an intersection");

        // =============== Boundary Values Tests ==================

        // **** Group: Ray is parallel to the plane
        // TC03: Ray included in the plane
        result=pl.findIntersections(new Ray(new Point(2,1,0),new Vector(1,0,0)));

        assertNull(
                result,
                "Ray included in the plane - found and intersection");


        // TC04: Ray not included in the plane
        result=pl.findIntersections(new Ray(new Point(0,0,1),new Vector(1,0,0)));

        assertNull(
                result,
                "Ray not included in the plane - found and intersection");

        // **** Group: Ray is orthogonal to the plane
        // TC05: Ray starts before the plane
        result=pl.findIntersections(new Ray(new Point(2,1,-1),new Vector(0,0,1)));

        assertEquals(
                1,
                result.size(),
                "Ray starts before the plane - didn't find an intersection");

        assertEquals(
                new Point(2,1,0),
                result.get(0),
                "Ray starts before the plane - wrong Point of intersection");


        // TC06: Ray starts on the plane

        result=pl.findIntersections(new Ray(new Point(2,1,0),new Vector(0,0,1)));

        assertNull(
                result,
                "Ray starts on the plane - found and intersection");


        // TC07: Ray starts after the plane
        result=pl.findIntersections(new Ray(new Point(0,0,1),new Vector(0,0,1)));

        assertNull(
                result,
                "Ray starts after the plane - found and intersection");


        // **** Group: Ray is not orthogonal nor parallel
        // TC08: Ray starts on the plane and not orthogonal or parallel

        result=pl.findIntersections(new Ray(new Point(0,1,0),new Vector(0,-1,1)));
        assertNull(
                result,
                "Ray starts on the plane and not orthogonal nor parallel- found and intersection");

        // TC09: Ray starts on the reference point of the plane and not orthogonal nor parallel
        result=pl.findIntersections(new  Ray(new Point(1,0,0),new Vector(1,1,1)));
        assertNull(
                result,
                "Ray starts on the reference point of the plane" +
                " and not orthogonal nor parallel - found and intersection");
    }
}

