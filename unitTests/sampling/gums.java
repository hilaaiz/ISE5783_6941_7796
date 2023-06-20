package sampling;

import geometries.*;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;

public class gums {


        private Scene scene = new Scene("Test scene");
        private Camera camera = new Camera(new Point(1000, 0, 0), new Vector(-1, 0, 0), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000) //
                .setRayTracer(new RayTracerBasic(scene));

        @Test
        public void gumsP(){

            scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE),new Double3(0.15)));
            Scene scene1 = scene.setBackground(new Color(157, 234, 249));

            // camera.setAntiAliasingFactor(9);

            scene.geometries.add(
                    new Triangle(new Point(0,35,35), new Point(0,35,-35), new Point(0,-35,-35))
                            .setEmission(new Color(0,0,200))
                            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)),
                    new Triangle(new Point(0,-35,35), new Point(0,-35,-35), new Point(0,35,35))
                            .setEmission(new Color(0,0,200))
                            .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30))

            );
            scene.lights.add( //
                    new SpotLight(new Color(700, 400, 400), new Point(-50, 40, 115), new Vector(1, -1, -2)) //
                            .setkL(4E-4).setkQ(2E-5));
            scene.lights.add( //
                    new DirectionalLight(new Color(400, 400, 400), new Vector(1, 0, 0)));

            camera.setImageWriter(new ImageWriter("antiAliasingPicture", 600, 600)) //
                    .renderImage() //
                    .writeToImage();


        }
}
