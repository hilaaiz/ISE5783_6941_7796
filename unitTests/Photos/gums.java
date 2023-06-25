package Photos;

import geometries.*;
import lighting.AmbientLight;
import lighting.PointLight;
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
    private Camera camera = new Camera(new Point(2000, 600, 400), new Vector(-1, 0, 0), new Vector(0, 0, 1)) //
            .setVPSize(200, 200).setVPDistance(260) //
            .setRayTracer(new RayTracerBasic(scene));

    //sphere to gum
    private Sphere makingStandartGums(Double centerX, Double centerY, Double centerZ, Color color){

        return (Sphere)(new Sphere(40,new Point(centerX,centerY,centerZ))
                .setEmission(color)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30)));
    }
    private Sphere makingShinyGums(double r, Double centerX, Double centerY, Double centerZ, Color color){

        return (Sphere)(new Sphere(r,new Point(centerX,centerY,centerZ))
                .setEmission(color)
                .setMaterial(new Material().setkD(0).setkR(0.2).setShininess(50)));
    }


    @Test
    public void gumsP(){

        //region scene
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE),new Double3(0.10)));
        Scene scene1 = scene.setBackground(new Color(0, 0, 0));
        //endregion

        //region lights
        PointLight pointLight = new PointLight(new Color(WHITE),
                new Point(800, 900, 2000));

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

        //region poligons
        scene.geometries.add(

                new Polygon(v1,v2,v3,v4).setEmission(new Color(BLACK))
                        .setMaterial(new Material().setkD(0).setkR(0.2).setShininess(50)),
//                         new Color(BLACK)).setMaterial(new Material().setkD(0).setkR(0.2).setShininess(50)),
                new Polygon(v1,v2,b1,b2).setEmission(florColor)
                        .setMaterial(new Material().setkS(0.2).setkD(0.1).setkT(0.9).setShininess(1)),
                new Polygon(v2,v3,c1,b1).setEmission(florColor)
                        .setMaterial(new Material().setkS(0.2).setkD(0.1).setkT(0.9).setShininess(1)),
                new Polygon(v1,v4,c2,b2).setEmission(florColor)
                        .setMaterial(new Material().setkS(0.2).setkD(0.1).setkT(0.9).setShininess(1))
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
                .setMaterial( new Material().setkD(0.2).setkS(0.8).setShininess(100).setkT(0.9));
        //תחתית הצנצנת
        Cylinder cylinder1= (Cylinder) new Cylinder(radius, new Ray(new Point(500,600,0),new Vector(0,0,1)),10)
                .setEmission(new Color(0, 0, 0))
                .setMaterial( new Material().setkD(0.1).setkS( 0.2).setShininess(1).setkT(1.0));

        //המכסה של הצנצנת
        Cylinder cylinder2= (Cylinder) new Cylinder(radius+10, new Ray(new Point(500,600,startHigh+sumLoop1*hige1),new Vector(0,0,1)),50)
                .setEmission(new Color(188, 48, 152))
                .setMaterial( new Material().setkD(0.8).setkS( 0.1).setShininess(50).setkT(0.5));

        scene.geometries.add(
                cylinder,cylinder1,cylinder2);


        for(int i=0;i<sumLoop1;i++){
            radius-=0.3;
            cylinder= (Cylinder) new Cylinder
                    (radius,new Ray(new Point(500,600,startHigh+(i*hige1)),new Vector(0,0,1)),hige1)
                    .setEmission(new Color(0,0,0))
                    .setMaterial( new Material().setkD(0.2).setkS(0.8).setShininess(100).setkT(0.9));
            scene.geometries.add(cylinder);
        }
        //endregion

        //region gums outside the jar
        scene.geometries.add(

                new Sphere(50,new Point(200,1000,50)).setEmission(yellow2Gum)
                        .setMaterial(new Material().setkD(0).setkR(0.2).setShininess(50)),
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

        //region gums in jar

        //to place the gums in the jar
        //X = Xc + r * 2 * cos(theta)
        //Y = Yc + r * 2 * sin(theta)
        //Xc is the X center of the cylinder
        //Yc is the Y center of the cylinder
        //r is the radius of the spheres

        scene.geometries.add(

                //makingStandartGums(580D,720D,120D,pinkGum),//ϴ=0 |1.1.1

                // region floor 0
                makingStandartGums(580D,600D,50D,pinkGum),//ϴ=0 |1.0.0
                makingStandartGums(540D,669.2820323,50D,blue2Gum),//ϴ= π/3 |2.0.0
                makingStandartGums(460D,669.2820323,50D,yellowGum), //ϴ= 2π/3 |3.0.0
                makingStandartGums(420D,600D,50D,redGum), //ϴ= π |4.0.0
                makingStandartGums(460D,530.7179677,50D,pinkGum), //ϴ= 4π/3 |5.0.0
                makingStandartGums(540D,530.7179677,50D,yellow2Gum), //ϴ= 5π/3 |6.0.0
                makingStandartGums(580D,680D,50D,pinkGum),//ϴ=0 |1.1.0
                makingStandartGums(540D,749.2820323,50D,blue2Gum),//ϴ= π/3 |2.1.0
                makingStandartGums(460D,749.2820323,50D,yellowGum), //ϴ= 2π/3  |3.1.0
                makingStandartGums(420D,520D,50D,redGum), //ϴ= π |4.-1.0
                makingStandartGums(460D,450.7179677,50D,blue1Gum), //ϴ= 4π/3 |5.-1.0
                makingStandartGums(540D,450.7179677,50D,yellow2Gum),//ϴ= 5π/3 |6.-1.0
                makingStandartGums(610D,510D,50D,pinkGum),//ϴ=0 |0001.-1.0 *
                makingStandartGums(630D,640D,50D,perpleGum),//ϴ=0 |0.0.0 *
                makingStandartGums(645D,565D,50D,greenGum),//ϴ=0 |0.1.0 *
                makingStandartGums(390D,690D,50D,redGum), //ϴ= π |0004.1.0 *
                makingStandartGums(370D,570D,50D,perpleGum), //ϴ= π |0004.2.0 *
                makingStandartGums(345D,630D,50D,blue1Gum),//ϴ= π  |0004.3.0 *
                //endregion

                // region floor 1
                makingStandartGums(580D,600D,130D,blue2Gum),//ϴ=0 |1.0.1
                makingStandartGums(540D,669.2820323,130D,pinkGum),//ϴ= π/3 |2.0.1
                makingStandartGums(460D,669.2820323,130D,perpleGum), //ϴ= 2π/3 |3.0.1
                makingStandartGums(420D,600D,130D,redGum), //ϴ= π |4.0.1
                makingStandartGums(460D,530.7179677,130D,blue2Gum), //ϴ= 4π/3 |5.0.1
                makingStandartGums(540D,530.7179677,130D,perpleGum), //ϴ= 5π/3 |6.0.1
                makingStandartGums(580D,680D,130D,blue2Gum),//ϴ=0 |1.1.1
                makingStandartGums(540D,749.2820323,130D,pinkGum),//ϴ= π/3 |2.1.1
                makingStandartGums(460D,749.2820323,130D,perpleGum), //ϴ= 2π/3  |3.1.1
                makingStandartGums(420D,520D,130D,redGum), //ϴ= π |4.-1.1
                makingStandartGums(460D,450.7179677,130D,pinkGum), //ϴ= 4π/3 |5.-1.1
                makingStandartGums(540D,450.7179677,130D,perpleGum),//ϴ= 5π/3 |6.-1.1
                makingStandartGums(620D,500D,130D,blue2Gum),//ϴ=0 |0001.-1.1 *
                makingStandartGums(630D,640D,130D,redGum),//ϴ=0 |0.0.1 *
                makingStandartGums(645D,565D,130D,yellowGum),//ϴ=0 |0.1.1 *
                makingStandartGums(390D,690D,130D,redGum), //ϴ= π |0004.1.1 *
                makingStandartGums(370D,570D,130D,yellowGum), //ϴ= π |0004.2.1 *
                makingStandartGums(345D,630D,130D,pinkGum),//ϴ= π  |0004.3.1 *
                //endregion

                // region floor 2
                makingStandartGums(580D,600D,210D,greenGum),//ϴ=0 |1.0.2
                makingStandartGums(540D,669.2820323,210D,blue2Gum),//ϴ= π/3 |2.0.2
                makingStandartGums(460D,669.2820323,210D,yellowGum), //ϴ= 2π/3 |3.0.2
                makingStandartGums(420D,600D,210D,redGum), //ϴ= π |4.0.2
                makingStandartGums(460D,530.7179677,210D,pinkGum), //ϴ= 4π/3 |5.0.2
                makingStandartGums(540D,530.7179677,210D,yellow2Gum), //ϴ= 5π/3 |6.0.2
                makingStandartGums(580D,680D,210D,pinkGum),//ϴ=0 |1.1.2
                makingStandartGums(540D,749.2820323,210D,blue2Gum),//ϴ= π/3 |2.1.2
                makingStandartGums(460D,749.2820323,210D,yellowGum), //ϴ= 2π/3  |3.1.2
                makingStandartGums(420D,520D,210D,redGum), //ϴ= π |4.-1.2
                makingStandartGums(460D,450.7179677,210D,blue1Gum), //ϴ= 4π/3 |5.-1.2
                makingStandartGums(540D,450.7179677,210D,yellow2Gum),//ϴ= 5π/3 |6.-1.2
                makingStandartGums(610D,510D,210D,pinkGum),//ϴ=0 |0001.-1.2 *
                makingStandartGums(630D,640D,210D,perpleGum),//ϴ=0 |0.0.2 *
                makingStandartGums(645D,565D,210D,blue1Gum),//ϴ=0 |0.1.2 *
                makingStandartGums(390D,690D,210D,redGum), //ϴ= π |0004.1.2 *
                makingStandartGums(370D,570D,210D,perpleGum), //ϴ= π |0004.2.2 *
                makingStandartGums(345D,630D,210D,blue1Gum),//ϴ= π  |0004.3.2 *
                //endregion

                // region floor 3
                makingStandartGums(580D,600D,290D,blue2Gum),//ϴ=0 |1.0.3
                makingStandartGums(540D,669.2820323,290D,pinkGum),//ϴ= π/3 |2.0.3
                makingStandartGums(460D,669.2820323,290D,perpleGum), //ϴ= 2π/3 |3.0.3
                makingStandartGums(420D,600D,290D,redGum), //ϴ= π |4.0.3
                makingStandartGums(460D,530.7179677,290D,blue2Gum), //ϴ= 4π/3 |5.0.3
                makingStandartGums(540D,530.7179677,290D,perpleGum), //ϴ= 5π/3 |6.0.3
                makingStandartGums(580D,680D,290D,blue2Gum),//ϴ=0 |1.1.3
                makingStandartGums(540D,749.2820323,290D,pinkGum),//ϴ= π/3 |2.1.3
                makingStandartGums(460D,749.2820323,290D,perpleGum), //ϴ= 2π/3  |3.1.3
                makingStandartGums(420D,520D,290D,redGum), //ϴ= π |4.-1.3
                makingStandartGums(460D,450.7179677,290D,pinkGum), //ϴ= 4π/3 |5.-1.3
                makingStandartGums(540D,450.7179677,290D,perpleGum),//ϴ= 5π/3 |6.-1.3
                makingStandartGums(620D,500D,290D,blue2Gum),//ϴ=0 |0001.-1.3 *
                makingStandartGums(630D,640D,290D,yellowGum),//ϴ=0 |0.0.3 *
                makingStandartGums(645D,565D,290D,redGum),//ϴ=0 |0.1.3 *
                makingStandartGums(390D,690D,290D,redGum), //ϴ= π |0004.1.3 *
                makingStandartGums(370D,570D,290D,yellowGum), //ϴ= π |0004.2.3 *
                makingStandartGums(345D,630D,290D,pinkGum),//ϴ= π  |0004.3.3 *
                //endregion

                // region floor 4
                makingStandartGums(580D,600D,370D,pinkGum),//ϴ=0 |1.0.4
                makingStandartGums(540D,669.2820323,370D,blue2Gum),//ϴ= π/3 |2.0.4
                makingStandartGums(460D,669.2820323,370D,yellow2Gum), //ϴ= 2π/3 |3.0.4
                makingStandartGums(420D,600D,370D,redGum), //ϴ= π |4.0.4
                makingStandartGums(460D,530.7179677,370D,pinkGum), //ϴ= 4π/3 |5.0.4
                makingStandartGums(540D,530.7179677,370D,yellow2Gum), //ϴ= 5π/3 |6.0.4
                makingStandartGums(580D,680D,370D,pinkGum),//ϴ=0 |1.1.4
                makingStandartGums(540D,749.2820323,370D,blue2Gum),//ϴ= π/3 |2.1.4
                makingStandartGums(460D,749.2820323,370D,yellowGum), //ϴ= 2π/3  |3.1.4
                makingStandartGums(420D,520D,370D,redGum), //ϴ= π |4.-1.4
                makingStandartGums(460D,450.7179677,370D,blue1Gum), //ϴ= 4π/3 |5.-1.4
                // makingStandartGums(540D,450.7179677,370D,yellow2Gum),//ϴ= 5π/3 |6.-1.4
                // makingStandartGums(610D,510D,370D,pinkGum),//ϴ=0 |0001.-1.4 *
                makingStandartGums(630D,640D,370D,perpleGum),//ϴ=0 |0.0.4 *
                // makingStandartGums(645D,565D,370D,greenGum),//ϴ=0 |0.1.4 *
                makingStandartGums(390D,690D,370D,redGum), //ϴ= π |0004.1.4 *
                makingStandartGums(370D,570D,370D,perpleGum), //ϴ= π |0004.2.4 *
                makingStandartGums(345D,630D,370D,blue1Gum)//ϴ= π  |0004.3.4 *
                //endregion

                // makingStandartGums(580D,720D,440D,yellow2Gum)//ϴ= π/3 |2.1.4

        );
        //endregion

        //region buildPhoto
        camera.setAntiAliasingFactor(2);
        camera.setImageWriter(new ImageWriter("antiAliasingPicture2", 600, 600)) //
                .renderImage() //
                .writeToImage();
        //endregion

    }
}