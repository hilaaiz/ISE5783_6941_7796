package renderer;
import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test the integration between the constructRay and geometric entities.
 */
public class IntegrationTest {

    /**
     * Determine how many intersection points suppose to have with a geometric entity.
     *
     * @param expected the expected amount of intersection points.
     * @param cam      the camera that we construct ray from.
     * @param geo      the geometric entity that we are testing intersection with.
     * @param test     the test that called this function.
     */
    private void assertCountIntersections(int expected, Camera cam, Intersectable geo, String test) {

        //counts how many intersection points.
        int countIntersection = 0;

        //the view plane is the size of 3x3 and a distance of 1 from the camera.
        cam.setVPSize(3, 3).setVPDistance(1);

        //the resolution is 3x3.
        // the view plane is the size of 3x3 and a resolution of 3x3, therefore Rx = Ry = 1.
        int nX = 3;
        int nY = 3;

        //A loop that iterates on each pixel.
        for (int i = 0; i < nY; ++i) {
            for (int j = 0; j < nX; ++j) {
                //create a list of all the intersection points with the geometric entity through the pixel.
                List<Point> cameraIntersectionsPoints = geo.findIntersections(cam.constructRay(nX, nY, j, i));
                //adds the length of the list, the amount of intersection points.
                countIntersection += ((cameraIntersectionsPoints == null) ? 0 : cameraIntersectionsPoints.size());
            }
        }
        assertEquals(expected, countIntersection, "Wrong amount of intersections with "
                                                  + geo.getClass() + ": " + test);
    }

    /***
     * test for triangle
     */
    @Test
    public void cameraRayTriangleIntegration() {

        Camera cam = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0));

        // TC01: Small triangle in front of the view plane (1 point)
        assertCountIntersections(1, cam, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2),
                                 new Point(0, -1, -2)), "TC01");

        // TC02: Large triangle in front of the view plane (2 points)
        assertCountIntersections(2, cam, new Triangle(new Point(1, 1, -2), new Point(-1, 1, -2),
                                 new Point(0, -20, -2)), "TC02");
    }

    /***
     * test for plane
     */
    @Test
    public void cameraRayPlaneIntegration() {

        Camera cam = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0));

        // TC01: Plane against the camera, parallel to the view plane (9 points)
        assertCountIntersections(9, cam, new Plane(new Point(0, 0, -5),
                                 new Vector(0, 0, 1)), "TC01");

        // TC02: Plane has acute angle to the view plane, all rays intersect (9 points)
        assertCountIntersections(9, cam, new Plane(new Point(0, 0, -5),
                                 new Vector(0, 1, 2)), "TC02");

        // TC03: Plane has obtuse angle to the view plane, parallel to lower rays (6 points)
        assertCountIntersections(6, cam, new Plane(new Point(0, 0, -5),
                                 new Vector(0, 1, 1)), "TC03");

        // TC04: Plane beyond the view plane (0 points)
        assertCountIntersections(
                6,
                cam,
                new Plane(new Point(0, 0, -5), new Vector(0, 1, 1)),
                "TC04");
    }

    /***
     * test for sphere
     */
    @Test
    public void cameraRaySphereIntegration() {

        Camera cam = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0));
        Camera cam2 = new Camera(new Point(0,0,0.5), new Vector(0, 0, -1),
                                 new Vector(0, 1, 0));

        // TC01: Sphere in front of the camera (2 points)
        assertCountIntersections(2, cam, new Sphere(1 ,new Point(0, 0, -3)), "TC01");

        // TC02: Sphere intersects the view plane before the camera (18 points)
        assertCountIntersections(18, cam2, new Sphere(2.5, new Point(0, 0, -2.5) ), "TC02");

        // TC03: Sphere intersects the view plane before the camera (10 points)
        assertCountIntersections(10, cam2, new Sphere(2, new Point(0, 0, -2)), "TC03");

        // TC04: Sphere contains the view plane and the camera (9 points)
        assertCountIntersections(9, cam, new Sphere(2, new Point(0,0,-1)), "TC04");

        // TC05: Sphere behind the camera (0 points)
        assertCountIntersections(0, cam, new Sphere(0.5, new Point(0, 0, 1)), "TC05");

    }
}

