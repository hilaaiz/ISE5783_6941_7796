package sampling;

import geometries.Cylinder;
import geometries.Intersectable;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.BLUE;
import static java.awt.Color.WHITE;

public class gums {

//    public void testJar(){
//       Cylinder bottomJar= new Cylinder(10, new Ray(new Point(500,500,0),new Vector(0,0,1)),2);
//       Sphere bodyJar= new Sphere(50,new Point(50,50,27));
//
//    }


    private Intersectable sphere     = new Sphere(60d,new Point(0, 0, -200))                                         //
            .setEmission(new Color(BLUE))                                                                                  //
            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));
    private Material trMaterial = new Material().setkD(0.5).setkS(0.5).setShininess(30);

    private Scene scene = new Scene("Test scene");
    private Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 1, 0),
            new Vector(0, 0, 1))   //
            .setVPSize(200, 200).setVPDistance(1000)                                                                       //
            .setRayTracer(new RayTracerBasic(scene));

    /** Produce a picture of two triangles lighted by a spotlight with a Sphere
     * producing a shading */
    @Test
    public void trianglesSphere() {
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                        new Point(75, 75, -150)) //
                        .setMaterial(new Material().setkS(0.8).setShininess(60)), //
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setkS(0.8).setShininess(60)), //
                new Sphere(30d,new Point(0, 0, -11)) //
                        .setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(700, 400, 400), new Point(40, 40, 115), new Vector(-1, -1, -4)) //
                        .setkL(4E-4).setkQ(2E-5));

        camera.setImageWriter(new ImageWriter("try", 600, 600)) //
                .renderImage() //
                .writeToImage();
    }

}
