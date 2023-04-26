package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    /**
     * Test method for {@link geometries.Sphere#getNormal(Point)} .
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Sphere sp = new Sphere(5,new Point(0,0,0));
        assertEquals(new Vector(0, 0, 1), sp.getNormal(new Point(0, 0, 5)),
                "Bad normal to triangle");
    }

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {

        Sphere sphere = new Sphere(1, new Point (1, 0, 0));
        Sphere sp = new Sphere(5,new Point(1,0,0));

        double b=sphere.getRadius();
        double r= sp.getRadius();
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                "Ray's line out of sphere");
       b=sphere.getRadius();
        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                new Vector(3, 1, 0)));
        b=sphere.getRadius();
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");


        // TC03: Ray starts inside the sphere (1 point)
        Point p = new Point(1, 0 , 5);
        result = sp.findIntersections(new Ray(new Point(1, 0, 0.5), new Vector(0,0,1)));
        assertEquals(result.size(),1, "Ray starts inside the sphere- wrong number of points");
        assertEquals(result.get(0), p, "Ray starts inside the sphere- wrong point");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sp.findIntersections(new Ray(new Point(18, 0,0), new Vector(0, 2, 0) )),
                "Ray starts after the sphere- didn't return null");


        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center) ****
        // TC11: Ray starts at sphere and goes inside (1 point)
        result = sp.findIntersections(new Ray(new Point(1, 0,1), new Vector(0, 0,-1)));
        assertEquals(result.size(), 1,"Ray starts at sphere and goes inside- wrong amount of points");
        assertEquals(result.get(0), new Point(1,0,-5),
                "Ray starts at sphere and goes inside- wrong point");

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sp.findIntersections(new Ray(new Point(6, 5,0), new Vector(0, 1,0))),
                "Ray starts at sphere and goes outside- didn't return null" );

        // **** Group: Ray's line goes through the center ****
        // TC13: Ray starts before the sphere (2 points)
        p1 = new Point(1, 0, 5);
        p2 = new Point(1, 0, -5);
        result = sp.findIntersections(new Ray(new Point(1,0,-7), new Vector(0,0,1)));
        assertEquals( 2, result.size(), "Ray starts before the sphere- wrong number of points");
        if (result.get(0).getY() < result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray starts before the sphere- wrong points");

        // TC14: Ray starts at sphere and goes inside (1 point)
        result = sphere.findIntersections(new Ray(new Point(1,-1,0), new Vector(0,1,0)));
        assertEquals(result.size(), 1,"Ray starts at sphere and goes inside- wrong amount of points");
        assertEquals(result.get(0), new Point(1,1,0),
                "Ray starts at sphere and goes inside- wrong point");

        // TC15: Ray starts inside (1 point)
        result = sphere.findIntersections(new Ray(new Point(1,0.5,0), new Vector(0,-1,0)));
        p = new Point(1,-1,0);
        assertEquals(result.size(), 1,"Ray starts inside- wrong amount of points" );
        assertEquals(result.get(0), p, "Ray starts inside- wrong point");

        // TC16: Ray starts at the center (1 point)
        result = sphere.findIntersections(new Ray(new Point(1,0,0), new Vector(0,1,0)));
        p = new Point(1,1,0);
        assertEquals(result.size(), 1,"Ray starts at center- wrong amount of points" );
        assertEquals(result.get(0), p, "Ray starts at center- wrong point");

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1,-1,0), new Vector(0,-1,0))),
                "Ray starts at sphere and goes outside- didn't return null" );

        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1,1.5,0), new Vector(0,1,0))),
                "Ray starts after sphere- didn't return null" );

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points) ****
        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1,-2,1), new Vector(0,1,0))),
                "Ray starts before the tangent point- didn't return null" );

        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1,0,1), new Vector(1,3,0))),
                "Ray starts at the tangent point- didn't return null" );

        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1,2,1), new Vector(0,1,0))),
                "Ray starts at the tangent point- didn't return null" );

        // **** Group: Special cases ****
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sp.findIntersections(new Ray(new Point(1,0,7), new Vector(1,0,0))),
                "Ray's line is outside, ray is orthogonal to ray start to sphere's center line- " +
                        "didn't return null" );

    }
}