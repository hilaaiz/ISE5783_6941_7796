package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Testing Cylinder
 *
 * @author Tamar Edri
 *
 */
class CylinderTest {

    @Test
    public void testConstructor() {

        //negative radius
        try {
            new Tube(-1, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)));
        } catch (IllegalArgumentException e) {
            fail("Constructed a Tube with wrong order of radius");
        }
        //(flat Tube בגליל סופי בעצם)
        //wrong radius or Ray todo :) מקרי קצה חריגות..


    }
    /**
     * Test method for {@link geometries.Cylinder#Cylinder( double, Ray, double)}.
     */
    @Test
    void testGetNormal() {
        Cylinder cy = new Cylinder(2,new Ray(new Point(0, 0, 0), new Vector(0, 0, 3)),  3);

        // ============ Equivalence Partitions Tests ==============

        // TC01: normal for a point on the casing of the cylinder
        assertEquals(cy.getNormal(new Point(2, 0, 1)), new Vector(1, 0, 0),
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
}
