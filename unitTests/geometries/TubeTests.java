package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Tube
 *
 * @author Hila
 *
 */
class TubeTests {

    //@Test
    //void testGetAxisRay() {//האם זה נצרך הדבר השטותי הזה?

      //  Tube t= new Tube(5, new Ray(new Point(0, 0, 0), new Vector(0, 0, 5)));
        //assertEquals(new Vector(0, 0, 5),t.axisRay, "wrong get method");
    //}

    @Test
    public void testConstructor() {

        //negative radius
        try {
            new Tube(-1, new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)));
        } catch (IllegalArgumentException e) {
            fail("Constructed a Tube with wrong order of radius");
        }
        //wrong radius or Ray todo :) מקרי קצה חריגות..


    }

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
}