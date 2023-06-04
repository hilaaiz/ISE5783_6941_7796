package geometries;

import primitives.*;

import java.util.List;

public abstract class Geometry extends Intersectable {
    protected Color emission= Color.BLACK;

    private Material material=new Material();




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
     * @param point
     * @return
     */
    public abstract Vector getNormal(Point point);

    public Material getMaterial() {
        return material;
    }

    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
