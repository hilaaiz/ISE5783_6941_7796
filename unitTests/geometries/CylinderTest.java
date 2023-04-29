package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Testing Cylinder
 *
 * @author Moriya Moskovich
 *
 */
class CylinderTest {

    /**
     * Test method for {@link geometries.Cylinder#getNormal(Point)}.
     */
    @Test
    void testGetNormal() {
        Cylinder cy = new Cylinder(3, new Ray(new Point(0, 0, 0),new Vector(0, 0, 3)),2  );

        // ============ Equivalence Partitions Tests ==============

        // TC01: normal for a point on the casing of the cylinder
        assertEquals(cy.getNormal(new Point(3, 0, 1)), new Vector(1, 0, 0),
                "Bad normal for a point on the cylinder");

        // TC02: normal for a point on the base of the cylinder where the axis ray starts
        assertEquals(cy.getNormal(new Point(1, 1, 0)), new Vector(0, 0, -1),
                "Bad normal for a point on the base of the cylinder where the axis ray starts");

        // TC03: normal for a point on the parallel base of the cylinder where the axis ray starts
        assertEquals(cy.getNormal(new Point(1, 1, 3)), new Vector(0, 0, 1),
                "Bad normal for a point on the parallel base of the cylinder where the axis ray starts");

        // TC04: test for a normalized vector
        assertEquals(cy.getNormal(new Point(2, 0, 1)).length(), 1, "normal vector is not normalized");

        // =============== Boundary Values Tests ==================

        // TC01: normal to a point on the corner between the casing and the base of the cylinder
        assertEquals(cy.getNormal(new Point(2, 0, 0)), new Vector(0, 0, -1),
                "Bad normal for a point on the the corner between the casing and the base of the cylinder");

        // TC02: normal to the point that represents the ray
        assertEquals(cy.getNormal(new Point(0, 0, 0)), new Vector(0, 0, -1),
                "Bad normal for a point on the the corner between the casing and the base of the cylinder");
    }


    /**
     * Test method for {@link geometries.Cylinder#findIntersections(Ray)}.
     */
    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        Cylinder cylinder = new Cylinder( 1,new Ray(new Point(1, 0, 0), new Vector(0, 1, 0)),4);
        //TC01 a test unparalleled ray that cuts the cylinder once in a shell of the cylinder
        assertEquals(1, cylinder.findIntersections(new Ray(new Point(1, 0.5, 0.5), new Vector(2, 1, 1))).size(), "TC01  findGeoIntersectionsHelper with ray that cut in one point" );

        //TC02 a test unparalleled ray that cuts the cylinder twice in a shell of the cylinder
        assertEquals(2, cylinder.findIntersections(new Ray(new Point(1,1,2), new Vector(0.23,0.15, -1.75))).size(), "TC03  findGeoIntersectionsHelper with ray that cut in one point" );

        //TC03 a test unparalleled ray that cuts the cylinder zero in a shell of the cylinder
        Ray ray = new Ray(new Point(1, 1, 2), new Vector(1, 1, 0));
        assertNull(cylinder.findIntersections(ray), "TC03  findGeoIntersectionsHelper with ray that cut in zero point");

        // =============== Boundary Values Tests ==================
        cylinder = new Cylinder(200,new Ray(new Point(-600,0,0), new Vector(1,0,0)),  1400);

        // TC01: ray is parallel to axisRay and is inside the cylinder
        ray = new Ray(new Point(-600,100,0), new Vector(1,0,0));
        assertEquals(new Point(800,100,0), cylinder.findIntersections(ray).get(0), "ray is parallel to axisRay and is inside the cylinder- found a wrong number of intersection");

        // TC02: ray is parallel to axisRay and is on the cylinder
        ray = new Ray(new Point(-200,-200,0), new Vector(1,0,0));
        assertNull(cylinder.findIntersections(ray), "ray is parallel to axisRay and is on the cylinder- found an intersection");

        // TC03: ray is parallel to axisRay and is outside the cylinder
        ray = new Ray(new Point(600,0,500), new Vector(1,0,0));
        assertNull(cylinder.findIntersections(ray), "ray is parallel to axisRay and is outside the cylinder- found an intersection");

        // TC04: ray is orthogonal to axisRay and intersects the cylinder
        ray = new Ray(new Point(0,600,0), new Vector(0,-1,0));
        assertEquals(2, cylinder.findIntersections(ray).size(), "ray is orthogonal to axisRay and intersects the cylinder- found wrong number of intersection");

        // TC05: ray is orthogonal to axisRay and does not intersect the cylinder
        ray = new Ray(new Point(-800,0,0), new Vector(0,-1,0));
        assertNull(cylinder.findIntersections(ray), "ray is orthogonal to axisRay and intersects the cylinder- found wrong number of intersection");
    }
}