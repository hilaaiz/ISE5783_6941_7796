package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public abstract class Geometry extends Intersectable {
    protected Color emission= Color.BLACK;

    /**
     * get the emission for geometry
     * @return
     */
    public Color getEmission() {
        return emission;
    }

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return  this;
    }

    /**
     * get the normal of the geometry
     * @param p
     * @return
     */
    public abstract Vector getNormal(Point p);

}
