package geometries;

import primitives.Point;
import primitives.Vector;

import java.util.Collections;
import java.util.List;

public class Triangle extends Polygon{

    public Triangle(Point p1,Point p2,Point p3) {
        super(p1,p2,p3);
    }

    public List<Point> GetVertices(){
        List<Point> myList = null;

        for(int i=0;i<3;i++){
            myList.add(this.vertices.get(i));
        }

        return myList;
    }

    public Vector GetNormal(Point point)
    {
        return null;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + vertices +
                ", plane=" + plane +
                '}';
    }
}
