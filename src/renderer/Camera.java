package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

public class Camera {

    /**
     * the reference point of the camera
     */
    private Point p0;

    /**
     * the vector that points upwards relative to the camera
     */
    private Vector vUp;

    /**
     * the vector that points onwards relative to the camera
     */
    private Vector vTo;

    /**
     * the vector that points to the right side relative to the camera
     */
    private Vector vRight;

    /**
     * width of the view plane
     */
    private double width;

    /**
     * height of the view plane
     */
    private double height;

    /**
     * distance between the camera and the view plane
     */
    private double distance;



    //region constructor
    /**
     * @throws IllegalArgumentException throws an exception if
     *         the reference vectors (vUp, vTo) are not orthogonal
     */
    public Camera(Point p0, Vector vTo, Vector vUp) throws IllegalArgumentException{
        if (!isZero(vTo.dotProduct(vUp))){
            throw new IllegalArgumentException("constructor threw - vUp and vTo are not orthogonal");
        }
        this.p0 = p0;
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = vTo.crossProduct(vUp);
    }
    //endregion

    //region set view plane size
    /**
     * set the perimeters of the view plane
     * @param width- the vp width
     * @param height- the vp height
     * @return the camera itself. builder pattern
     */
    public Camera setVPSize(double width, double height){
        this.width=width;
        this.height=height;
        return this;
    }
    //endregion

    //region set view plane distance from the camera
    /**
     * set the distance of the camera from the view plane
     * @param distance- the distance from the VP
     * @return the camera itself. builder pattern
     */
    public Camera setVPDistance(double distance){
        this.distance=distance;
        return this;
    }
    //endregion

    //region constructRay
    /**
     *constructs a ray from the camera through pixel i,j.
     * @param nX- number of pixels on the width of the view plane.
     * @param nY- number of pixels on the width of the view plane.
     * @param j- location of the pixel in the X direction.
     * @param i-location of the pixel in the Y direction.
     * @return the constructed ray - from p0 through the wanted pixel.
     */
    public Ray constructRay(int nX, int nY, int j, int i){

        //view plane center point
        Point Pc=p0.add(vTo.scale(distance));

        //pixels size
        double Rx=width/nX;
        double Ry=height/nY;

        //Pij point[i,j] in view plane coordinates
        Point Pij=Pc;

        //delta values for moving on the view-plane
        double Xj = (j-(nX-1)/2d)*Rx;
        double Yi = -(i-(nY-1)/2d)*Ry;

        if(!isZero(Xj)){
            Pij=Pij.add(vRight.scale(Xj));
        }
        if (!isZero(Yi)){
            Pij=Pij.add(vUp.scale(Yi));
        }

        //vector from camera's eye in the direction of point (i,j) in the vp
        Vector Vij=Pij.subtract(p0);

        return new Ray(p0,Vij);
    }
    //endregion

}
