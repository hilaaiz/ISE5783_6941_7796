package sampling;

import geometries.*;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import java.awt.geom.RoundRectangle2D;

import static java.awt.Color.*;

public class gums {


    private Scene scene = new Scene("Test scene");
    private Camera camera = new Camera(new Point(2000, 600, 400),
            new Vector(-1, 0, 0), new Vector(0, 0, 1)) //
            .setVPSize(200, 200).setVPDistance(250) //
            .setRayTracer(new RayTracerBasic(scene));

    private Sphere makingGums( Double centerX, Double centerY, Double centerZ){

        return (Sphere) (new Sphere(50,new Point(centerX,centerY,centerZ))
                       .setEmission(new Color(128, 223, 255))
                       .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)));
    }
    @Test
    public void gumsP(){

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE),new Double3(0.10)));
        Scene scene1 = scene.setBackground(new Color(0, 0, 0));

        //colors
        Color florColor=new Color(142, 186, 249);
        Color pink= new Color(255, 230, 230);


        //lights
        PointLight pointLight = new PointLight(new Color(WHITE),
                new Point(700, 700, 120));

        scene.lights.add(pointLight);

        //points to square
        Point v1=new Point(0,100,1);
        Point v2=new Point(0,1100,1);
        Point v3=new Point(1000,1100,0);
        Point v4=new Point(1000,100,0);

        //points to back block
        Point b3=new Point(0,1100,1000);
        Point b4=new Point(0,100,1000);

        scene.geometries.add(

                new Triangle(v1,v2,v4).setEmission(florColor)
                        .setMaterial(new Material().setkS(0.6).setkR(0.2).setkT(0.7).setShininess(15))
                ,new Triangle(v2,v4,v3).setEmission(florColor)
                        .setMaterial(new Material().setkS(0.6).setkR(0.2).setkT(0.7).setShininess(15)),
                new Triangle(v1,v2,b4).setEmission(new Color(black))
                        .setMaterial(new Material().setkD(0).setkR(0.2).setShininess(50))
                ,new Triangle(b3,b4,v2).setEmission(new Color(black))
                        .setMaterial(new Material().setkD(0).setkR(0.2).setkT(0.6).setShininess(50))
        );

        //glass
        scene.geometries.add(
                new Cylinder(200, new Ray(new Point(500,600,0),new Vector(0,0,1)),300)
                        .setEmission(new Color(0,0,0))
                        .setMaterial( new Material().setkD(0.2).setkS( 0.8).setShininess(100).setkT(0.9))
        );

        //gums
//        scene.geometries.add(
//                new Sphere(50,new Point(500,600,50)).setEmission(new Color(128, 223, 255)) //
//                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)),
//                new Sphere(50,new Point(500,600,150)).setEmission(new Color(BLUE)) //
//                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)),
//                new Sphere(50,new Point(500,600,250)).setEmission(pink) //
//                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)),
//
//                new Sphere(50,new Point(400,600,50)).setEmission(new Color(BLUE)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)),
//                new Sphere(50,new Point(400,600,150)).setEmission(pink) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)),
//                new Sphere(50,new Point(400,600,250)).setEmission(new Color(BLUE)) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)),
//
//                new Sphere(50,new Point(390,500,50)).setEmission(pink) //
//                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30))
////                new Sphere(50,new Point(400,600,150)).setEmission(new Color(BLUE)) //
////                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)),
////                new Sphere(50,new Point(400,600,250)).setEmission(new Color(BLUE)) //
////                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30))
//
//        );

        //
        //X = Xc + r * 2 * cos(theta)
        //Y = Yc + r * 2 * sin(theta)

        scene.geometries.add(
                makingGums(600D,600D,50D), //ϴ=0
                makingGums(550D,686.6025404,50D),//ϴ= π/3
                makingGums(450D,686.6025404,50D), //ϴ= 2π/3
                makingGums(400D,600D,50D), //ϴ= π
                makingGums(450D, 513.3974596,50D), //ϴ= 4π/3
                makingGums(550D,513.3974596,50D) //ϴ= 5π/3
        );

//camera.setAntiAliasingFactor(9);

        camera.setImageWriter(new ImageWriter("antiAliasingPicture2", 600, 600)) //
                .renderImage() //
                .writeToImage();
        }
}