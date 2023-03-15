package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane {

    private Point P0;
    private Vector normal;

    public Plane(Point vertex0, Point vertex1, Point vertex2) {
        normal= null;
        P0= vertex0;
    }

    public Plane (Point p,Vector v ){
        P0= p;
        normal=v.normalize();
    }

    public Vector getNormal() {
        return normal;
    }
    public Vector getNormal(Point p){
        return normal;
    }
}
