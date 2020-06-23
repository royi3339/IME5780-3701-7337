package unittests;

import geometries.Plane;
import geometries.Polygon;
import org.junit.Test;

import elements.*;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.*;
import scene.Scene;

import java.util.List;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 *
 * @author dzilb && Royi Alishayev idan darmoni
 */
public class ReflectionRefractionTests {
    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(Color.BLACK, 0));

        scene.addGeometries(
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.4, 0.3, 100, 0.3, 0), 50,
                        new Point3D(0, 0, 50)),
                new Sphere(new Color(java.awt.Color.RED), new Material(0.5, 0.5, 100), 25, new Point3D(0, 0, 50)));

        scene.addLights(new SpotLight(new Color(1000, 600, 0), new Point3D(-100, 100, -500), new Vector(-1, 1, 2), 1,
                0.0004, 0.0000006));

        ImageWriter imageWriter = new ImageWriter("twoSpheres", 150, 150, 500, 500);
        Render render = new Render(imageWriter, scene).setSuperSampling(0);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheresOnMirrors() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -10000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(10000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.addGeometries(
                new Sphere(new Color(0, 0, 100), new Material(0.25, 0.25, 20, 0.5, 0, 0.001), 400, new Point3D(-950, 900, 1000)),
                new Sphere(new Color(100, 20, 20), new Material(0.25, 0.25, 20), 200, new Point3D(-950, 900, 1000)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1), new Point3D(1500, 1500, 1500),
                        new Point3D(-1500, -1500, 1500), new Point3D(670, -670, -3000)),
                new Triangle(new Color(20, 20, 20), new Material(0, 0, 0, 0, 0.5), new Point3D(1500, 1500, 1500),
                        new Point3D(-1500, -1500, 1500), new Point3D(-1500, 1500, 2000)));

        scene.addLights(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, 750, 150),
                new Vector(-1, 1, 4), 1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("twoSpheresMirrored", 2500, 2500, 500, 500);
        //     Render render = new Render(imageWriter, scene).setSuperSampling();
        Render render = new Render(imageWriter, scene).setSuperSampling(0).setMultithreading(1).setDebugPrint();
        ;

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a partially transparent Sphere
     * producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(1000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries( //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
                new Triangle(Color.BLACK, new Material(0.5, 0.5, 60), //
                        new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
                new Sphere(new Color(java.awt.Color.BLUE), new Material(0.2, 0.2, 30, 0.6, 0), // )
                        30, new Point3D(60, -50, 50)));

        scene.addLights(new SpotLight(new Color(700, 400, 400), //
                new Point3D(60, -50, 0), new Vector(0, 0, 1), 1, 4E-5, 2E-7));

        ImageWriter imageWriter = new ImageWriter("shadow with transparency", 200, 200, 600, 600);
        Render render = new Render(imageWriter, scene).setSuperSampling(0);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * creating a picture with num of objects.
     */
    @Test
    public void numOfObjectsTest() {
        Scene scene = new Scene("Objects Test scene");
        scene.setDistance(10000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        //          creating 3 Cameras with others looking view, for the bonus
        Camera c1 = new Camera(new Point3D(-150, -4620, 90), new Vector(1300, 5800, 150), new Vector(-5800, 1300, 0)); // picture #1
        Camera c2 = new Camera(new Point3D(0, -450, -7000), new Vector(20, 7, 100), new Vector(-100, 0, 20)); // picture #2
        Camera c3 = new Camera(new Point3D(0, -450, -5900), new Vector(20, 200, 100), new Vector(-100, 0, 20)); // picture #3
        List<Camera> cameraList = List.of(c1, c2, c3);

        //          creating 5 Point3D to the pyramid with a Square base.
        Point3D p1 = new Point3D(850, -750, -200);
        Point3D p2 = new Point3D(850, -750, -400);
        Point3D p3 = new Point3D(850, -950, -400);
        Point3D p4 = new Point3D(850, -950, -200);
        Point3D p5 = new Point3D(950, -850, -300);

        scene.addGeometries(
                //          creating a 3 Spheres which the third Sphere is a mirror.
                new Sphere(new Color(20, 20, 20), new Material(0.25, 0.25, 20, 0.7, 0), 200, new Point3D(900, -850, -300))    // אפור
                , new Sphere(new Color(95, 0, 50), new Material(0.25, 0.75, 20, 0.5, 0), 200, new Point3D(900, -1600, 0))    // וורוד
                , new Sphere(new Color(java.awt.Color.darkGray), new Material(0.25, 0.75, 20, 0, 0.7), 400, new Point3D(1100, 0, 200))    //מראה

                //          creating a 4 infinity mirrors.
                , new Plane(new Color(20, 20, 20), new Material(0.5, 0, 0, 0, 0.7), new Point3D(0, 0, 0), new Vector(0, 1, 0)) // קדמי
                , new Plane(new Color(40, 40, 40), new Material(0, 0, 0, 0, 0.7), new Point3D(0, 0, 200), new Vector(0, 0, -1)) // צדדי
                , new Plane(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1), new Point3D(1000, -4621, 0), new Point3D(-800, -4621, 1000), new Point3D(0, -4621, 0)) // אחורי
                , new Plane(new Color(java.awt.Color.black), new Material(0.2, 0, 20, 0, 0), new Point3D(1100, 0, 0), new Vector(-1, 0, 0)) // תחתון

                //          creating a pyramid with a Square base.
                , new Polygon(new Color(java.awt.Color.GREEN), new Material(0.25, 0.75, 20, 0.5, 0), p1, p2, p3, p4)
                , new Triangle(new Color(java.awt.Color.YELLOW), new Material(0.25, 0.75, 20, 0.5, 0), p1, p2, p5)
                , new Triangle(new Color(java.awt.Color.RED), new Material(0.25, 0.75, 20, 0.5, 0), p2, p3, p5)
                , new Triangle(new Color(java.awt.Color.BLUE), new Material(0.25, 0.75, 20, 0.5, 0), p3, p4, p5)
                , new Triangle(new Color(java.awt.Color.GREEN), new Material(0.25, 0.75, 20, 0.5, 0), p4, p1, p5)
        );
        scene.addLights(new DirectionalLight(new Color(255, 255, 255), new Vector(1, 0, 0)));

        String str;
        int nX, nY;
        str = "#Darmon_&&_Royi_Test";
        //   nX = 650;
        nX = 750;
        // nY = 960;
        nY = 750;
        //  }
        int i = 1;
        ImageWriter imageWriter;
        for (Camera camera : cameraList) {
            scene.setCamera(camera);
            if (i == 1) {
                imageWriter = new ImageWriter(str + i, 8000, 8000, nX, nY);
                // imageWriter = new ImageWriter(str + i, 4000, 4000, nX, nY);
            } else {
                imageWriter = new ImageWriter(str + i, 8000, 8000, nY, nX);
            }
            Render render = new Render(imageWriter, scene).setSuperSampling(0);
            render.renderImage();
            render.writeToImage();
            i++;
        }
    }

    /**
     * creating a picture with Glossy effect.
     */
    @Test
    public void MiniProjectGlossyTest() {
        Scene scene = new Scene("Mini Project Glossy Test scene");
        scene.setDistance(10000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(1, 0, 0), new Vector(0, 0, 1)));
        scene.addGeometries(
                new Polygon(new Color(20, 20, 20), new Material(0.5, 0.25, 0, 0, 1, 5), new Point3D(200, 0, 0), new Point3D(200, 200, 0), new Point3D(200, 200, -200), new Point3D(200, 0, -200)) // שמאל תחתון
                , new Polygon(new Color(20, 20, 20), new Material(0.5, 0.25, 0, 0, 1), new Point3D(200, 0, 200), new Point3D(200, 200, 200), new Point3D(200, 200, 0), new Point3D(200, 0, 0)) // שמאל עליון
                , new Polygon(new Color(20, 20, 20), new Material(0.5, 0.25, 0, 0, 1, 30), new Point3D(200, 0, 200), new Point3D(200, -200, 200), new Point3D(200, -200, 0), new Point3D(200, 0, 0)) // ימין עליון
                , new Sphere(new Color(120, 250, 0), new Material(0.25, 0.25, 20, 0, 0), 250, new Point3D(-500, 0, 0))
        );

        scene.addLights(new PointLight(new Color(255, 255, 255), new Point3D(-1, 1, 10), 1, 0.0005, 0.0005));
        ImageWriter imageWriter = new ImageWriter("Mini Project Glossy #1", 8000, 8000, 500, 500);
        Render render = new Render(imageWriter, scene).setSuperSampling(1000);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * creating a picture with Blurry effect.
     */
/*    @Test
    public void MiniProjectBlurryTest() {
        Scene scene = new Scene("Mini Project Blurry Test scene");
        scene.setDistance(10000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.setCamera(new Camera(new Point3D(-150, -4620, 90), new Vector(1300, 5800, 150), new Vector(-5800, 1300, 0)));

        //          creating 5 Point3D to the pyramid with a Square base.
        Point3D p1 = new Point3D(850, -750, -200);
        Point3D p2 = new Point3D(850, -750, -400);
        Point3D p3 = new Point3D(850, -950, -400);
        Point3D p4 = new Point3D(850, -950, -200);
        Point3D p5 = new Point3D(950, -850, -300);

        scene.addGeometries(
                //          creating a 3 Spheres which the third Sphere is a mirror.
                new Sphere(new Color(20, 20, 20), new Material(0.25, 0.25, 20, 0.7, 0, 20), 200, new Point3D(900, -850, -300))    // אפור
                , new Sphere(new Color(95, 0, 50), new Material(0.25, 0.75, 20, 0.5, 0, 0), 200, new Point3D(900, -1600, 0))    // וורוד
                , new Sphere(new Color(java.awt.Color.darkGray), new Material(0.25, 0.75, 20, 0, 0.7, 0), 400, new Point3D(1100, 0, 200))    //מראה

                //          creating a 2 infinity mirrors.
                , new Plane(new Color(20, 20, 20), new Material(0.5, 0, 0, 0, 0.7), new Point3D(0, 0, 0), new Vector(0, 1, 0)) // קדמי
                , new Plane(new Color(40, 40, 40), new Material(0, 0, 0, 0, 0.7, 15), new Point3D(0, 0, 200), new Vector(0, 0, -1)) // צדדי

                //          creating a pyramid with a Square base.
                , new Polygon(new Color(java.awt.Color.GREEN), new Material(0.25, 0.75, 20, 0.5, 0), p1, p2, p3, p4)
                , new Triangle(new Color(java.awt.Color.YELLOW), new Material(0.25, 0.75, 20, 0.5, 0), p1, p2, p5)
                , new Triangle(new Color(java.awt.Color.RED), new Material(0.25, 0.75, 20, 0.5, 0), p2, p3, p5)
                , new Triangle(new Color(java.awt.Color.BLUE), new Material(0.25, 0.75, 20, 0.5, 0), p3, p4, p5)
                , new Triangle(new Color(java.awt.Color.GREEN), new Material(0.25, 0.75, 20, 0.5, 0), p4, p1, p5)
        );
        scene.addLights(new DirectionalLight(new Color(255, 255, 255), new Vector(1, 0, 0)));
        ImageWriter imageWriter = new ImageWriter("Mini Project Blurry #1", 4000, 4000, 750, 750);

        Render render = new Render(imageWriter, scene).setSuperSampling(50);

        render.renderImage();
        render.writeToImage();
    }           */
}


