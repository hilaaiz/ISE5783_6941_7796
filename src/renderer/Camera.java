package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

import static primitives.Util.isZero;


/**
 * Camera to take the picture
 */
public class Camera {

    /**
     * the reference point of the camera
     */
    private Point position;

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

    /**
     * Paints and creates the image
     */
    private ImageWriter imageWriter;

    /**
     * Calculating colors by sending rays
     */
    private RayTracerBase rayTracer;


    private int antiAliasingFactor = 1;



    //region constructor
    /**
     * @param p0  camera position
     * @param vUp camera upward vector
     * @param vTo camera front vector
     * @throws IllegalArgumentException throws an exception if
     *                                  the reference vectors (vUp, vTo) are not orthogonal
     */
    public Camera(Point p0, Vector vTo, Vector vUp) throws IllegalArgumentException {
        if (!isZero(vTo.dotProduct(vUp))) {
            throw new IllegalArgumentException("constructor threw - vUp and vTo are not orthogonal");
        }
        this.position = p0;
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = vTo.crossProduct(vUp).normalize();
    }
    //endregion

    //region getters
    /**
     * function that gets the position of the camera
     *
     * @return the position
     */
    public Point getPosition() {
        return position;
    }

    /**
     * function that gets the vTo vector
     *
     * @return the vTo vector
     */
    public Vector getVTo() {
        return vTo;
    }

    /**
     * function that gets the vUp vector
     *
     * @return the vUp vector
     */
    public Vector getVUp() {
        return vUp;
    }

    /**
     * function that gets the vRight vector
     *
     * @return the vRight vector
     */
    public Vector getVRight() {
        return vRight;
    }

    /**
     * function that gets the distance
     *
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * function that gets the height
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * function that gets the width
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }
    //endregion

    //region setters
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * function that sets the distance
     *
     * @param distance value to set
     * @return this
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * function that sets imageWriter
     *
     * @param imageWriter object to set
     * @return camera
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * function that sets the rayTracer
     *
     * @param rayTracer object to set
     * @return camera itself
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * function that sets the antiAliasingFactor
     *
     * @param antiAliasingFactor value to set
     * @return camera itself
     */
    public Camera setAntiAliasingFactor(int antiAliasingFactor) {
        this.antiAliasingFactor = antiAliasingFactor;
        return this;
    }

    //endregion

    //region findPixelLocation
    /**
     * function that calculates the pixels location
     *
     * @param nX the x resolution
     * @param nY the y resolution
     * @param i  the x coordinate
     * @param j  the y coordinate
     * @return the ray
     */
    private Point findPixelLocation(int nX, int nY, int j, int i) {

        double rY = height / nY;
        double rX = width / nX;

        double yI = -(i - (nY - 1d) / 2) * rY;
        double jX = (j - (nX - 1d) / 2) * rX;
        Point pIJ = position.add(vTo.scale(distance));

        if (yI != 0) pIJ = pIJ.add(vUp.scale(yI));
        if (jX != 0) pIJ = pIJ.add(vRight.scale(jX));
        return pIJ;
    }
    //endregion

    //region constructRay
    /**
     * function that returns the ray from the camera to the point
     *
     * @param nX the x resolution
     * @param nY the y resolution
     * @param i  the x coordinate
     * @param j  the y coordinate
     * @return the ray
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        return new Ray(position, findPixelLocation(nX, nY, j, i).subtract(position));
    }
    //endregion

    //region constructRays
    /**
     * function that returns the rays from the camera to the point
     *
     * @param nX the x resolution
     * @param nY the y resolution
     * @param i  the x coordinate
     * @param j  the y coordinate
     * @return the ray
     */
    public List<Ray> constructRays(int nX, int nY, int j, int i) {
        List<Ray> rays = new LinkedList<>();
        Point centralPixel = findPixelLocation(nX, nY, j, i);
        double rY = height / nY / antiAliasingFactor;
        double rX = width / nX / antiAliasingFactor;
        double x, y;

        for (int rowNumber = 0; rowNumber < antiAliasingFactor; rowNumber++) {
            for (int colNumber = 0; colNumber < antiAliasingFactor; colNumber++) {
                y = -(rowNumber - (antiAliasingFactor - 1d) / 2) * rY;
                x = (colNumber - (antiAliasingFactor - 1d) / 2) * rX;
                Point pIJ = centralPixel;
                if (y != 0) pIJ = pIJ.add(vUp.scale(y));
                if (x != 0) pIJ = pIJ.add(vRight.scale(x));
                rays.add(new Ray(position, pIJ.subtract(position)));
            }
        }
        return rays;
    }

    //endregion

    //region renderImage
    /**
     * make sure that all the params are not empty.
     * and also paint all the pixels.
     *
     */
    public Camera renderImage() throws MissingResourceException {
        if (imageWriter == null || rayTracer == null || width == 0 || height == 0 || distance == 0)
            throw new MissingResourceException("Camera is missing some fields", "Camera", "field");

        //move over the coordinates of the grid
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nX; i++)
            for (int j = 0; j < nY; j++) {

                //get the ray through the pixel
                imageWriter.writePixel(j, i, this.castRay(nX, nY, j, i));
            }

        return this;

    }
    //endregion

    //region color for pixel
    /**
     * function that casts ray and returns color
     *
     * @param nX the x resolution
     * @param nY the y resolution
     * @param j  the x coordinate
     * @param i  the y coordinate
     * @return the color
     */
    private Color castRay(int nX, int nY, int i, int j) {
        if (antiAliasingFactor == 1)
            return rayTracer.traceRay(constructRay(nX, nY, i, j));
        else
            return rayTracer.traceRays(constructRays(nX, nY, i, j));
    }
    //endregion

    //region writeToImage
    /**
     * create the image file using the imageWriter object
     *
     * @throws MissingResourceException if the imageWriter in uninitialized - unable to generate the image
     */
    public void writeToImage() throws MissingResourceException {
        if (imageWriter == null)
            throw new MissingResourceException("there is no image writer", "Camera", "field");

        imageWriter.writeToImage();
    }
    //endregion

    //region printGrid
    /**
     * print a grid on the image without running over the original image
     *
     * @param interval the size of the grid squares
     * @param color    the color of the grid
     * @throws MissingResourceException if the imageWriter is uninitialized - unable to print a grid
     */
    void printGrid(int interval, Color color) throws MissingResourceException {
        if (imageWriter == null)
            throw new MissingResourceException("there is no image writer", "Camera", "field");

        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        int Row;
        int column;

        // loop over Row
        for (Row = 0; Row < nY; Row = Row + interval)
            for (column = 0; column < nX; ++column)
                imageWriter.writePixel(column, Row, color);

        // loop over column
        for (Row = 0; Row < nY; ++Row)
            for (column = 0; column < nX; column = column + interval)
                imageWriter.writePixel(column, Row, color);

    }
    //endregion

}
