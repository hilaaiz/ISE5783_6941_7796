package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void testGetPoint() {

        Ray r= new Ray(new Point(0,1,0),new Vector(0,1,0));
        Point p;
        int t;

        // ============ Equivalence Partitions Tests ==============

        // TC01:t -the distance from the head of the ray to point is positive
        t=2;
        p=new Point(0,3,0);
        assertEquals(p,r.getPoint(t),"ERROR -> ray.getPoint(t): when t is positive");

        // TC02:t -the distance from the head of the ray to point is negative
        t=-2;
        p=new Point(0,-1,0);
        assertEquals(p,r.getPoint(t),"ERROR -> ray.getPoint(t): when t is negative");


        // =============== Boundary Values Tests ==================

        // TC03:t -the distance from the head of the ray to point is 0
        t=0;
        p=new Point(0,1,0);
        assertEquals(p,r.getPoint(t),"ERROR -> ray.getPoint(t): when t is 0");



    }
}