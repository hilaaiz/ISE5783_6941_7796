package MP2;

import geometries.Cylinder;
import geometries.Polygon;
import geometries.Sphere;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;

public class MP2 {

    private Polygon squareSetCoefficents (Point corner1,Point corner2,Point corner3,Point corner4){
        return (Polygon)(new Polygon(corner1,corner2,corner3,corner4).setEmission(new Color(20,20,20))
                .setMaterial( new Material().setkD(0.2).setkS(0.8).setShininess(100).setkT(0.9)));
    }

    @Test
    public void photo(){

        //region initial
        Scene scene = new Scene("Test scene");
        Camera camera = new Camera(new Point(2500, 850, 500), new Vector(-1, 0, 0), new Vector(0, 0, 1)) //
                .setVPSize(200, 200).setVPDistance(260) //
                .setRayTracer(new RayTracerBasic(scene));
        //endregion

        //region points
        //points to square
        Point v1=new Point(0,100,0);
        Point v2=new Point(0,1600,0);
        Point v3=new Point(2000,1600,0);
        Point v4=new Point(2000,100,0);

        //points to back block
        Point b1=new Point(0,1600,1200);
        Point b2=new Point(0,100,1200);

        //points for walls
        Point c1=new Point(2000,1600,1200);
        Point c2=new Point(2000,100,1200);
        //endregion

        //region colors
        Color paperColor1=new Color(256, 171, 155);
        Color paperColor12=new Color(226, 141, 125);
        Color paperColor2=new Color(165, 211, 148);
        Color paperColor22=new Color(195, 241, 178);
        Color wallColor=new Color(101, 60, 61);
        Color LightBrown=new Color(224, 202, 155);
        Color Brown=new Color(194, 168, 125);
        Color black=new Color(0, 0, 0);
        Color floor = new Color(88, 154, 235);
        //endregion

        //region lights
        PointLight pointLight = new PointLight(new Color(gray),
                new Point(800, 900, 1100));

        PointLight pointTrayRight = new PointLight(new Color(WHITE),
                new Point (300,1420,1));

        PointLight pointTrayLeft = new PointLight(new Color(2102, 153, 51),//2102, 153, 51 Sparkling color
                new Point (300,620,1));

        SpotLight Spot = new SpotLight(new Color(gray), c2, new Vector(-1,1,-1));

        DirectionalLight Spoty = new DirectionalLight(new Color(gray),new Vector(1,1,0));
        DirectionalLight Direction = new DirectionalLight(new Color(50,50,50),new Vector(-1,1,0));

        scene.lights.add(pointLight);
        scene.lights.add(Spot);
        scene.lights.add(Spoty);
        scene.lights.add(pointTrayRight);
        scene.lights.add(pointTrayLeft);
        scene.lights.add(Direction);
        //endregion

        //region polygons
        scene.geometries.add(

                new Polygon(v1,v2,v3,v4).setEmission(new Color(101, 60, 61))
                        .setMaterial(new Material().setkS(0.2).setkD(0.1).setkT(0.9).setShininess(1)),
                new Polygon(v1,v2,b1,b2).setEmission(new Color(101, 60, 61))
                        .setMaterial(new Material().setkS(0.2).setkD(0.1).setkT(0.9).setShininess(1)),
                new Polygon(v2,v3,c1,b1).setEmission(wallColor)
                        .setMaterial(new Material().setkS(0.2).setkD(0.1).setkT(0.9).setShininess(1)),
                new Polygon(v1,v4,c2,b2).setEmission(wallColor)
                        .setMaterial(new Material().setkS(0.2).setkD(0.1).setkT(0.9).setShininess(1))
        );
        //endregion

        //region bottles

        //region left bottle
        int radius=100;
        int radius1=40;
        int radius2=50;
        int radius3=39;
        int startHigh=400;
        int high2=100;
        int high3=10;
        int high4=30;
        int high5=60;
        double tempHigh=0;
        Point battelPoint=new Point(600,450,0);

        //תחתית הבקבוק
        Cylinder cylinder= (Cylinder) new Cylinder(radius+5, new Ray(battelPoint,new Vector(0,0,1)),10)
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //פס קישוט תחתון
        Cylinder c113= (Cylinder) new Cylinder(radius+0.2,new Ray(battelPoint.add(0,0,10),
                new Vector(0,0,1)),4)
                .setEmission(Brown)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //הבקבוק
        Cylinder cylinder1= (Cylinder) new Cylinder(radius, new Ray(battelPoint,new Vector(0,0,1)),startHigh)
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //החלק העליון של הבקבוק
        Sphere sphere= (Sphere) new Sphere(radius,battelPoint.add(0,0,startHigh))
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));


        //החלק העליון
        tempHigh=startHigh+radius-10;
        Cylinder cylinder2= (Cylinder) new Cylinder(radius1, new Ray(battelPoint.add(0,0,tempHigh),new Vector(0,0,1)),high2)
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //פס קישוט חלק עליון
        Cylinder c114= (Cylinder) new Cylinder(radius1+0.2,new Ray(battelPoint.add(0,0,tempHigh+8),new Vector(0,0,1)),4)
                .setEmission(Brown)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //פס קישוט חלק עליון
        Cylinder c115= (Cylinder) new Cylinder(radius1+0.2,new Ray(battelPoint.add(0,0,tempHigh+16),new Vector(0,0,1)),4)
                .setEmission(Brown)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //טבעת ראשונה
        tempHigh+=high2;
        Cylinder cylinder3= (Cylinder) new Cylinder(radius1+5, new Ray(battelPoint.add(0,0,tempHigh),new Vector(0,0,1)),high3)
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //טבעת עבה יותר
        tempHigh+=10;
        Cylinder cylinder4= (Cylinder) new Cylinder(radius2, new Ray(battelPoint.add(0,0,tempHigh),new Vector(0,0,1)),high4)
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //פקק
        tempHigh+=30;
        Cylinder cylinder10= (Cylinder) new Cylinder(radius3, new Ray(battelPoint.add(0,0,tempHigh),new Vector(0,0,1)),high5)
                .setEmission(Brown)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));


        //פתק
        Cylinder cylinder5= (Cylinder) new Cylinder(radius+2, new Ray(battelPoint.add(0,0,190),new Vector(0,0,1)),140)
                .setEmission(LightBrown)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));

        //פס קישוט על הפתק
        Cylinder cylinder6= (Cylinder) new Cylinder(radius+2.2, new Ray(battelPoint.add(0,0,220),new Vector(0,0,1)),5)
                .setEmission(Brown)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));

        //פס קישוט על הפתק
        Cylinder cylinder7= (Cylinder) new Cylinder(radius+2.2, new Ray(battelPoint.add(0,0,295),new Vector(0,0,1)),5)
                .setEmission(Brown)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));

        //פס קישוט על הפתק
        Cylinder cylinder8= (Cylinder) new Cylinder(radius+2.2, new Ray(battelPoint.add(0,0,230),new Vector(0,0,1)),10)
                .setEmission(Brown)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));

        //פס קישוט על הפתק
        Cylinder cylinder9= (Cylinder) new Cylinder(radius+2.2, new Ray(battelPoint.add(0,0,280),new Vector(0,0,1)),10)
                .setEmission(Brown)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));


        scene.geometries.add(
                c113,c114,c115,cylinder,cylinder1,sphere,cylinder2,cylinder3,cylinder4,cylinder5,cylinder6,cylinder7,cylinder8,cylinder9,cylinder10);
        //endregion

        //region right bottle on the
        radius=100;
        radius1=40;
        radius2=50;
        //radius3=39;
        startHigh=400;
        high2=100;
        high3=10;
        high4=30;
        high5=40;
        tempHigh=0;
        battelPoint=new Point(420,1250,13);

        //תחתית הבקבוק
        Cylinder c21= (Cylinder) new Cylinder(radius+5, new Ray(battelPoint,new Vector(0,0,1)),10)
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //פס קישוט תחתון
        Cylinder c213= (Cylinder) new Cylinder(radius+0.2,new Ray(battelPoint.add(0,0,10),new Vector(0,0,1)),4)
                .setEmission(Brown)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //הבקבוק
        Cylinder c22= (Cylinder) new Cylinder(radius, new Ray(battelPoint,new Vector(0,0,1)),startHigh)
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //החלק העליון של הבקבוק
        Sphere s2= (Sphere) new Sphere(radius,battelPoint.add(0,0,startHigh))
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));


        //החלק העליון
        tempHigh=startHigh+radius-10;
        Cylinder c23= (Cylinder) new Cylinder(radius1, new Ray(battelPoint.add(0,0,tempHigh),new Vector(0,0,1)),high2)
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //טבעת ראשונה
        tempHigh+=high2;
        Cylinder c24= (Cylinder) new Cylinder(radius1+5, new Ray(battelPoint.add(0,0,tempHigh),new Vector(0,0,1)),high3)
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //טבעת עבה יותר
        tempHigh+=10;
        Cylinder c25= (Cylinder) new Cylinder(radius2, new Ray(battelPoint.add(0,0,tempHigh),new Vector(0,0,1)),high4)
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //פקק
        tempHigh+=30;
        Cylinder c26= (Cylinder) new Cylinder(radius1-1, new Ray(battelPoint.add(0,0,tempHigh),new Vector(0,0,1)),high5)
                .setEmission(Brown)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //פתק
        Cylinder c27= (Cylinder) new Cylinder(radius+2, new Ray(battelPoint.add(0,0,190),new Vector(0,0,1)),140)
                .setEmission(paperColor1)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));

        //פס קישוט על הפתק
        Cylinder c28= (Cylinder) new Cylinder(radius+2.2, new Ray(battelPoint.add(0,0,220),new Vector(0,0,1)),5)
                .setEmission(paperColor12)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));

        //פס קישוט על הפתק
        Cylinder c29= (Cylinder) new Cylinder(radius+2.2, new Ray(battelPoint.add(0,0,295),new Vector(0,0,1)),5)
                .setEmission(paperColor12)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));

        //פס קישוט על הפתק
        Cylinder c210= (Cylinder) new Cylinder(radius+2.2, new Ray(battelPoint.add(0,0,230),new Vector(0,0,1)),10)
                .setEmission(paperColor12)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));

        //פס קישוט על הפתק
        Cylinder c211= (Cylinder) new Cylinder(radius+2.2, new Ray(battelPoint.add(0,0,280),new Vector(0,0,1)),10)
                .setEmission(paperColor12)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));


        scene.geometries.add(
                c21,c22,s2,c23,c24,c25,c26,c27,c28,c29,c210,c211,c213);
        //endregion

        //region middle bottle on the
        radius=90;
        radius1=30;
        radius2=40;
        startHigh=350;
        high2=100;
        high3=10;
        high4=30;
        high5=65;
        tempHigh=0;
        battelPoint=new Point(600,1070,13);

        //תחתית הבקבוק
        Cylinder c31= (Cylinder) new Cylinder(radius+5, new Ray(battelPoint,new Vector(0,0,1)),10)
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //פס קישוט תחתון
        Cylinder c313= (Cylinder) new Cylinder(radius+0.2,new Ray(battelPoint.add(0,0,10),new Vector(0,0,1)),4)
                .setEmission(Brown)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //הבקבוק
        Cylinder c32= (Cylinder) new Cylinder(radius, new Ray(battelPoint,new Vector(0,0,1)),startHigh)
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //החלק העליון של הבקבוק
        Sphere s3= (Sphere) new Sphere(radius,battelPoint.add(0,0,startHigh))
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));


        //החלק העליון
        tempHigh=startHigh+radius-10;
        Cylinder c33= (Cylinder) new Cylinder(radius1, new Ray(battelPoint.add(0,0,tempHigh),new Vector(0,0,1)),high2)
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //טבעת ראשונה
        tempHigh+=high2;
        Cylinder c34= (Cylinder) new Cylinder(radius1+5, new Ray(battelPoint.add(0,0,tempHigh),new Vector(0,0,1)),high3)
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //טבעת עבה יותר
        tempHigh+=10;
        Cylinder c35= (Cylinder) new Cylinder(radius2, new Ray(battelPoint.add(0,0,tempHigh),new Vector(0,0,1)),high4)
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //פקק
        tempHigh+=30;
        Cylinder c36= (Cylinder) new Cylinder(radius1-1, new Ray(battelPoint.add(0,0,tempHigh),new Vector(0,0,1)),high5)
                .setEmission(Brown)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //פתק
        Cylinder c37= (Cylinder) new Cylinder(radius+2, new Ray(battelPoint.add(0,0,140),new Vector(0,0,1)),140)
                .setEmission(paperColor2)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));

        //פס קישוט על הפתק
        Cylinder c38= (Cylinder) new Cylinder(radius+2.2, new Ray(battelPoint.add(0,0,170),new Vector(0,0,1)),5)
                .setEmission(paperColor22)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));

        //פס קישוט על הפתק
        Cylinder c39= (Cylinder) new Cylinder(radius+2.2, new Ray(battelPoint.add(0,0,245),new Vector(0,0,1)),5)
                .setEmission(paperColor22)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));

        //פס קישוט על הפתק
        Cylinder c310= (Cylinder) new Cylinder(radius+2.2, new Ray(battelPoint.add(0,0,180),new Vector(0,0,1)),10)
                .setEmission(paperColor22)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));

        //פס קישוט על הפתק
        Cylinder c311= (Cylinder) new Cylinder(radius+2.2, new Ray(battelPoint.add(0,0,230),new Vector(0,0,1)),10)
                .setEmission(paperColor22)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));


        scene.geometries.add(
                c31,c32,s3,c33,c34,c35,c36,c37,c38,c39,c310,c311,c313
        );
        //endregion

        //region left bottle on the
        radius=120;
        radius1=40;
        radius2=50;
        //radius3=39;
        startHigh=230;
        high2=120;
        high3=10;
        high4=30;
        high5=35;
        tempHigh=0;
        battelPoint=new Point(700,800,13);

        //תחתית הבקבוק
        Cylinder c41= (Cylinder) new Cylinder(radius+5, new Ray(battelPoint,
                new Vector(0,0,1)),10)
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        Cylinder c413= (Cylinder) new Cylinder(radius+0.2,new Ray(battelPoint.add(0,0,10),
                new Vector(0,0,1)),4)
                .setEmission(Brown)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //הבקבוק
        Cylinder c42= (Cylinder) new Cylinder(radius, new Ray(battelPoint,new Vector(0,0,1)),startHigh)
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //החלק העליון של הבקבוק
        Sphere s4= (Sphere) new Sphere(radius,battelPoint.add(0,0,startHigh))
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));


        //החלק העליון
        tempHigh=startHigh+radius-10;
        Cylinder c43= (Cylinder) new Cylinder(radius1, new Ray(battelPoint.add(0,0,tempHigh),
                new Vector(0,0,1)),high2)
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //טבעת ראשונה
        tempHigh+=high2;
        Cylinder c44= (Cylinder) new Cylinder(radius1+5, new Ray(battelPoint.add(0,0,tempHigh),
                new Vector(0,0,1)),high3)
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //טבעת עבה יותר
        tempHigh+=10;
        Cylinder c45= (Cylinder) new Cylinder(radius2, new Ray(battelPoint.add(0,0,tempHigh),
                new Vector(0,0,1)),high4)
                .setEmission(black)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //פקק
        tempHigh+=30;
        Cylinder c46= (Cylinder) new Cylinder(radius1-1, new Ray(battelPoint.add(0,0,tempHigh),
                new Vector(0,0,1)),high5)
                .setEmission(Brown)
                .setMaterial( new Material().setkD(0).setkR(0.2).setShininess(50));

        //פתק
        Cylinder c47= (Cylinder) new Cylinder(radius+2, new Ray(battelPoint.add(0,0,100),
                new Vector(0,0,1)),100)
                .setEmission(LightBrown)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));

        //פס קישוט על הפתק
        Cylinder c48= (Cylinder) new Cylinder(radius+2.2, new Ray(battelPoint.add(0,0,120),
                new Vector(0,0,1)),5)
                .setEmission(Brown)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));

        //פס קישוט על הפתק
        Cylinder c49= (Cylinder) new Cylinder(radius+2.2, new Ray(battelPoint.add(0,0,175),
                new Vector(0,0,1)),5)
                .setEmission(Brown)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));

        //פס קישוט על הפתק
        Cylinder c410= (Cylinder) new Cylinder(radius+2.2, new Ray(battelPoint.add(0,0,130),
                new Vector(0,0,1)),10)
                .setEmission(Brown)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));

        //פס קישוט על הפתק
        Cylinder c411= (Cylinder) new Cylinder(radius+2.2, new Ray(battelPoint.add(0,0,160),
                new Vector(0,0,1)),10)
                .setEmission(Brown)
                .setMaterial(new Material().setkD(0.5).setkS(0.5).setShininess(30));


        scene.geometries.add(
                c41,c42,s4,c43,c44,c45,c46,c47,c48,c49,c410,c411,c413);
        //endregion

        //endregion

        //region tray

        //corners of polygon
        Point corner1= new Point (300,620,1);
        Point corner2= new Point (300,1420,1);
        Point corner3= new Point (900,1420,1);
        Point corner4= new Point (900,620,1);

        Point cornerUp1= new Point (300,620,13);
        Point cornerUp2= new Point (300,1420,13);
        Point cornerUp3= new Point (900,1420,13);
        Point cornerUp4= new Point (900,620,13);

        //handles
        Point handles1= new Point (300,620,53);
        Point handles2= new Point (300,1420,53);
        Point handles3= new Point (900,1420,53);
        Point handles4= new Point (900,620,53);

        Point lefthandles1= new Point (300,632,13);
        Point lefthandles2= new Point (900,632,13);
        Point lefthandles3= new Point (900,632,53);
        Point lefthandles4= new Point (300,632,53);

        Point righthandles1= new Point (300,1408,13);
        Point righthandles2= new Point (900,1408,13);
        Point righthandles3= new Point (900,1408,53);
        Point righthandles4= new Point (300,1408,53);



        scene.geometries.add(

                //bottom tray
                squareSetCoefficents(corner1,corner2,corner3,corner4),
                squareSetCoefficents(cornerUp1,cornerUp2,cornerUp3,cornerUp4),
                squareSetCoefficents(corner1,corner2,cornerUp2,cornerUp1),
                squareSetCoefficents(corner2,corner3,cornerUp3,cornerUp2),
                squareSetCoefficents(corner4,corner3,cornerUp3,cornerUp4),
                squareSetCoefficents(corner1,corner4,cornerUp4,cornerUp1),

                //tray handles
                squareSetCoefficents(cornerUp1,lefthandles1,lefthandles4,handles1),//1
                squareSetCoefficents(cornerUp1,handles1,handles4,cornerUp4),//2
                squareSetCoefficents(cornerUp4, lefthandles2,lefthandles3,handles4),//3
                squareSetCoefficents(lefthandles1,lefthandles2,lefthandles3,lefthandles4),//4
                squareSetCoefficents(handles1,lefthandles4,lefthandles3,handles4),//5

                squareSetCoefficents(righthandles1,cornerUp2,handles2,righthandles4),//1
                squareSetCoefficents(righthandles1,righthandles4,righthandles3,righthandles2),//2
                squareSetCoefficents(righthandles2, cornerUp3,handles3,righthandles3),//3
                squareSetCoefficents(cornerUp2,cornerUp3,handles3,handles2),//4
                squareSetCoefficents(righthandles4,handles2,handles3,righthandles3) //5

        );
        //endregion

        camera.setAntiAliasingFactor(9);
        camera.setImageWriter(new ImageWriter("buttleTrayPictureAnti", 600, 600)) //
                .renderImage() //
                .writeToImage();

//        camera.setUseAdaptive(true).setMaxAdaptiveLevel(3);
//        camera.setImageWriter(new ImageWriter("buttleTrayPicture", 600, 600)) //
//                .renderImage() //
//                .writeToImage();
    }
}