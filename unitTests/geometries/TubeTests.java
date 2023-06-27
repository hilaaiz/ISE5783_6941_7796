package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Tube
 *
 * @author Hila
 *
 */
class TubeTests {

    /**
     * Test method for {@link geometries.Tube#Tube(double, Ray)}.
     */
    @Test
    void testGetNormal() {
        Tube tb = new Tube(2,
                new Ray(new Point(0, 0, 0), new Vector(0, 0, 1))
        );

        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        assertEquals(tb.getNormal(new Point(2, 2, 10)).dotProduct(new Vector(0, 0, 1)), 0,
                "Bad normal to tube");

        // =============== Boundary Values Tests ==================
        // TC02: Normal is orthogonal to the head of the axis Ray
        assertEquals(tb.getNormal(new Point(2, 2, 0)).dotProduct(new Vector(0, 0, 1)), 0,
                "Bad normal to tube when the normal is orthogonal to the head of the axisRay");
    }

    /**
     * Test method for {@link geometries.Tube#findIntersections(primitives.Ray)}
     */
    @Test
    public void testFindIntersections() {

        Tube tubeToTest;

        // ============ Equivalence Partitions Tests ==============

        tubeToTest = new Tube( 1, new Ray(new Point(1, 0, 0),
                new Vector(0, 1, 0)));

        //TC01 a teat unparalleled ray that cuts the cylinder in a shell of the cylinder (1 point)
        assertEquals(1, tubeToTest.findIntersections(new Ray(new Point(1, 0.5, 0.5),
                        new Vector(2, 1, 1))).size(),
                     "TC01  findIntersections with ray that cut in one point" );

        //TC02 a test unparalleled ray that cuts the cylinder in a shell of the cylinder (2 points)
        assertEquals(2, tubeToTest.findIntersections(new Ray(new Point(1,1,2),
                     new Vector(0.23,0.15, -1.75))).size(),
                    "TC03  findIntersections with ray that cut in one point" );

        //TC03 a test unparalleled ray that cuts the cylinder zero in a shell of the cylinder (0 points)
        Ray ray = new Ray(new Point(1, 1, 2), new Vector(1, 1, 0));
        assertNull(tubeToTest.findIntersections(ray),
                "TC03  findIntersections with ray that cut in zero point");

        // =============== Boundary Values Tests ==================

        tubeToTest = new Tube(200, new Ray(new Point(-600,0,0), new Vector(1,0,0)));

        // TC01: ray is parallel to axisRay and is inside the tube
        ray = new Ray(new Point(-600,100,0), new Vector(1,0,0));
        assertNull(tubeToTest.findIntersections(ray),
                  "ray is parallel to axisRay and is inside the tubeToTest- found an intersection");

        // TC02: ray is parallel to axisRay and is on the tube
        ray = new Ray(new Point(-200,-200,0), new Vector(1,0,0));
        assertNull(tubeToTest.findIntersections(ray),
                  "ray is parallel to axisRay and is on the tubeToTest- found an intersection");

        // TC03: ray is parallel to axisRay and is outside the tube
        ray = new Ray(new Point(600,0,5), new Vector(1,0,0));
        assertNull(tubeToTest.findIntersections(ray),
                  "ray is parallel to axisRay and is outside the tubeToTest- found an intersection");

        // TC04: ray is orthogonal to axisRay and intersects the tube
        ray = new Ray(new Point(0,600,0), new Vector(0,-1,0));
        List<Point> intersectionsList = tubeToTest.findIntersections(ray);
        assertEquals(2, intersectionsList.size(),
                "ray is orthogonal to axisRay and intersects the tubeToTest-" +
                        " found wrong number of intersection");
        if (intersectionsList.get(0).getY() > intersectionsList.get(1).getY())
            intersectionsList = List.of(intersectionsList.get(1), intersectionsList.get(0));
        assertEquals(new Point(0,-200,0), intersectionsList.get(0),
                "ray is orthogonal to axisRay and intersects the tubeToTest-" +
                        " found wrong intersection");
        assertEquals(new Point(0,200,0), intersectionsList.get(1),
                "ray is orthogonal to axisRay and intersects the tubeToTest-" +
                        " found wrong intersection");

    }
}