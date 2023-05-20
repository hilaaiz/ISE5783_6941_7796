package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * The class Scene is a passive data structure (PDS)
 * its purpose is to bind the different components that
 * usually make up a scene. It has no functionality of itself.
 */
public class Scene {
    public String name;
    public Color background=Color.BLACK;
    public AmbientLight ambientLight=AmbientLight.NONE;

    public Geometries geometries=new Geometries();
    public List<LightSource> lights=new LinkedList<>();

    /**
     * Constructor that sets the scene name and sets the other fields to their default values
     * @param name The name of the scene
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Set the background of the scene
     * @param background background of the scene
     * @return The object itself
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Set the ambientLight of the scene
     * @param ambientLight ambientLight of the scene
     * @return The object itself
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Set the geometric bodies of the scene
     * @param geometries geometric bodies of the scene
     * @return The object itself
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * Set the list of light sources in the scene
     * @param lights geometric bodies of the scene
     * @return The object itself
     */
    public Scene setLights(List<LightSource> lights){
        this.lights = lights;
        return  this;
    }
}
