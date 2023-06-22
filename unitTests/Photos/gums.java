package Photos;

import geometries.*;
import lighting.AmbientLight;
import lighting.PointLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;

public class gums {


    private Scene scene = new Scene("Test scene");
    private Camera camera = new Camera(new Point(2000, 600, 400), new Vector(-1, 0, 0), new Vector(0, 0, 1)) //
            .setVPSize(200, 200).setVPDistance(260) //
            .setRayTracer(new RayTracerBasic(scene));

    @Test
    public void gumsP(){

        //region scene
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE),new Double3(0.10)));
        Scene scene1 = scene.setBackground(new Color(0, 0, 0));
        //endregion

        //region colors
        Color florColor=new Color(108, 174, 255);
        Color pinkGum=new Color(249, 136, 201);
        Color pink2Gum=new Color(227, 96, 202);
        Color yellowGum=new Color(239, 223, 112);
        Color yellow2Gum=new Color(250, 187, 1);
        Color blue1Gum=new Color(73, 205, 233);
        Color blue2Gum=new Color(30, 205, 233);
        Color perpleGum=new Color(209, 135, 221);
        Color redGum=new Color(237, 73, 99);
        Color greenGum=new Color(27, 179, 122);
        //endregion

        //region lights
        PointLight pointLight = new PointLight(new Color(WHITE),
                new Point(800, 900, 1100));

        scene.lights.add(pointLight);
        //endregion

        //region points
        //points to square
        Point v1=new Point(0,100,0);
        Point v2=new Point(0,1100,0);
        Point v3=new Point(1000,1100,0);
        Point v4=new Point(1000,100,0);

        //points to back block
        Point b1=new Point(0,1100,1200);
        Point b2=new Point(0,100,1200);

        //points for walls
        Point c1=new Point(1000,1100,1200);
        Point c2=new Point(1000,100,1200);
        //endregion

        //region poligons
        scene.geometries.add(

                new Polygon(v1,v2,v3,v4).setEmission(florColor)
                        .setMaterial(new Material().setkS(0.2).setkD(0.1).setkT(0.9).setShininess(1)),
                new Polygon(v1,v2,b1,b2).setEmission(new Color(75, 86, 87))
                        .setMaterial(new Material().setkS(0.2).setkD(0.1).setkT(0.9).setShininess(1)),
                new Polygon(v2,v3,c1,b1).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setkD(0).setkR(0.2).setShininess(50)),
                new Polygon(v1,v4,c2,b2).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setkD(0).setkR(0.2).setShininess(50))
        );
        //endregion

        //region parameters for cylinders
        int radius=200;
        int highTemp=300;
        int sumLoop1=15;
        int sumLoop2=15;
        int hige1=5;
        int hige2=1;
        int startHigh=400;
        //endregion

        //region cylinders
        //צנצנת ראשית
        Cylinder cylinder= (Cylinder) new Cylinder(radius, new Ray(new Point(500,600,0),new Vector(0,0,1)),startHigh)
                .setEmission(new Color(0,0,0))
                .setMaterial( new Material().setkD(0.1).setkS(0.5).setShininess(100).setkT(0.8).setkR(0.0));
        //תחתית הצנצנת
        Cylinder cylinder1= (Cylinder) new Cylinder(radius, new Ray(new Point(500,600,0),new Vector(0,0,1)),10)
                .setEmission(new Color(0, 0, 0))
                .setMaterial( new Material().setkD(0.1).setkS( 0.2).setShininess(1).setkT(1.0).setkR(0.0));

        //המכסה של הצנצנת
        Cylinder cylinder2= (Cylinder) new Cylinder(radius+10, new Ray(new Point(500,600,startHigh+sumLoop1*hige1),new Vector(0,0,1)),30)
                .setEmission(new Color(188, 48, 152))
                .setMaterial( new Material().setkD(0.8).setkS( 0.1).setShininess(50).setkT(0.5).setkR(0.0));

        scene.geometries.add(
                cylinder,cylinder1,cylinder2);


        for(int i=0;i<sumLoop1;i++){
            radius-=0.3;
            cylinder= (Cylinder) new Cylinder
                    (radius,new Ray(new Point(500,600,startHigh+(i*hige1)),new Vector(0,0,1)),hige1)
                    .setEmission(new Color(5, 5, 5))
                    .setMaterial( new Material().setkD(0.4).setkS(0.6).setShininess(50).setkT(1.0).setkR(0.0));
            scene.geometries.add(cylinder);
        }
        //endregion

        //region gums
        scene.geometries.add(
                new Sphere(40,new Point(500,600,50)).setEmission(blue1Gum) //
                    .setMaterial(new Material().setkD(0.5).setkS(50).setShininess(30)),
                new Sphere(40,new Point(520,540,50)).setEmission(pinkGum)
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)),
                new Sphere(40,new Point(650,670,50)).setEmission(yellowGum)
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)),
                new Sphere(50,new Point(200,1000,50)).setEmission(yellow2Gum)
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)),
                new Sphere(40,new Point(850,310,50)).setEmission(pink2Gum)
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)),
                new Sphere(50,new Point(600,280,50)).setEmission(yellow2Gum)
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)),
                new Sphere(45,new Point(800,220,50)).setEmission(greenGum)
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)),
                new Sphere(50,new Point(400,900,50)).setEmission(blue2Gum)
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)),
                new Sphere(50,new Point(600,950,50)).setEmission(redGum)
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30))
                );
        //endregion

        //region buildPhoto
        //camera.setAntiAliasingFactor(9);
        camera.setImageWriter(new ImageWriter("antiAliasingPicture", 600, 600)) //
                .renderImage() //
                .writeToImage();
        //endregion

    }
}