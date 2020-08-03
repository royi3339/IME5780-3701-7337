package unittests;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import geometries.*;
import org.junit.Test;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import java.util.List;

/**
 * implements the BoundingBoxTest class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class BoundingBoxTest {

    /**
     * Test number 1. (the mirror with gray)
     */
    @Test
    public void BoundingBoxTest1() {

        Scene scene = new Scene("project2 Test scene");
 /*       scene.setDistance(10000);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        Camera c1 = new Camera(new Point3D(-150, -4620, 90), new Vector(1300, 5800, 150), new Vector(-5800, 1300, 0)); // picture #1
        List<Camera> cameraList = List.of(c1);

        //          creating 5 Point3D to the pyramid with a Square base.
        Point3D p1 = new Point3D(850, -750, -200);
        Point3D p2 = new Point3D(850, -750, -400);
        Point3D p3 = new Point3D(850, -950, -400);
        Point3D p4 = new Point3D(850, -950, -200);
        Point3D p5 = new Point3D(950, -850, -300);

        scene.addGeometries(
                //          creating a 3 Spheres which the third Sphere is a mirror.

                new Sphere(new Color(java.awt.Color.darkGray), new Material(0.25, 0.75, 20, 0, 0.7, 50), 400, new Point3D(1100, 0, 200))    //מראה
                , new Geometries(
                        new Sphere(new Color(20, 20, 20), new Material(0.25, 0.25, 20, 0.7, 0, 27), 200, new Point3D(900, -850, -300))    // אפור
                        , new Sphere(new Color(95, 0, 50), new Material(0.25, 0.75, 20, 0.5, 0), 200, new Point3D(900, -1600, 0))    // וורוד

                        //          creating a pyramid with a Square base.
                        , new Geometries(
                        new Polygon(new Color(java.awt.Color.GREEN), new Material(0.25, 0.75, 20, 0.5, 0), p1, p2, p3, p4)
                        , new Triangle(new Color(java.awt.Color.YELLOW), new Material(0.25, 0.75, 20, 0.5, 0), p1, p2, p5)
                        , new Triangle(new Color(java.awt.Color.RED), new Material(0.25, 0.75, 20, 0.5, 0), p2, p3, p5)
                        , new Triangle(new Color(java.awt.Color.BLUE), new Material(0.25, 0.75, 20, 0.5, 0), p3, p4, p5)
                        , new Triangle(new Color(java.awt.Color.GREEN), new Material(0.25, 0.75, 20, 0.5, 0), p4, p1, p5)))
                //          creating a 4 infinity mirrors.
                ,
                new Plane(new Color(20, 20, 20), new Material(0.5, 0, 0, 0, 0.7), new Point3D(0, 0, 0), new Vector(0, 1, 0)) // קדמי
                , new Plane(new Color(20, 20, 20), new Material(0, 0, 0, 0, 1), new Point3D(1000, -4621, 0), new Point3D(-800, -4621, 1000), new Point3D(0, -4621, 0)) // אחורי
                , new Plane(new Color(java.awt.Color.black), new Material(0.2, 0, 20, 0, 0), new Point3D(1100, 0, 0), new Vector(-1, 0, 0)) // תחתון

        );
        scene.addLights(new DirectionalLight(new Color(255, 255, 255), new Vector(1, 0, 0)));
                */
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
                new Sphere(new Color(20, 20, 20), new Material(0.25, 0.25, 20, 0.7, 0, 27), 200, new Point3D(900, -850, -300))    // אפור
                , new Sphere(new Color(95, 0, 50), new Material(0.25, 0.75, 20, 0.5, 0, 47), 200, new Point3D(900, -1600, 0))    // וורוד
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
        str = "#project#2 test 1### ";
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

            scene.createHierarchy(2);
            Render render = new Render(imageWriter, scene).setSuperSampling(10).setMultithreading(3).setBoundingBoxEffect(true);
            render.renderImage();
            render.writeToImage();
            i++;
        }
    }

    /**
     * Test number 2. (the blue)
     */
    @Test
    public void BoundingBoxTest2() {

        Scene scene = new Scene("##project 2 test 2###");
        scene.setDistance(10000);
        scene.setBackground(new Color(java.awt.Color.cyan));
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

                //          creating a 2 infinity mirrors.
                , new Plane(new Color(20, 20, 20), new Material(0.5, 0, 0, 0, 0.7), new Point3D(0, 0, 0), new Vector(0, 1, 0)) // קדמי

                //          creating a pyramid with a Square base.
                , new Geometries(
                        new Polygon(new Color(java.awt.Color.GREEN), new Material(0.25, 0.75, 20, 0.5, 0), p1, p2, p3, p4)
                        , new Triangle(new Color(java.awt.Color.YELLOW), new Material(0.25, 0.75, 20, 0.5, 0), p1, p2, p5)
                        , new Triangle(new Color(java.awt.Color.RED), new Material(0.25, 0.75, 20, 0.5, 0), p2, p3, p5)
                        , new Triangle(new Color(java.awt.Color.BLUE), new Material(0.25, 0.75, 20, 0.5, 0), p3, p4, p5)
                        , new Triangle(new Color(java.awt.Color.GREEN), new Material(0.25, 0.75, 20, 0.5, 0), p4, p1, p5))
        );
        scene.addLights(new DirectionalLight(new Color(255, 255, 255), new Vector(1, 0, 0)));
        ImageWriter imageWriter = new ImageWriter(" #project 2 test 2###", 4000, 4000, 750, 750);

        scene.createHierarchy(5);
        Render render = new Render(imageWriter, scene).setSuperSampling(30).setMultithreading(3).setBoundingBoxEffect(true);

        render.renderImage();
        render.writeToImage();
    }

    /**
     * Test number 3. (the million balls)
     */
    @Test
    public void BoundingBoxTest3() {

        Scene scene = new Scene("##project 2 testingggggggg 2###");
        scene.setDistance(10000);
        scene.setBackground(new Color(java.awt.Color.cyan));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.setCamera(new Camera(new Point3D(-1000, 500, 500), new Vector(1, 0, 0), new Vector(0, 0, 1)));

        for (int i = 0; i < 1000; i += 40) {
            //     Geometries g = new Geometries();
            for (int j = 0; j < 1000; j += 40) {
                /*g.add*/
                scene.addGeometries(new Sphere(new Color(120, 20, 20), new Material(0.25, 0.75, 20, 0.5, 0, 15), 15, new Point3D(900, i, j)));
            }
            //    scene.addGeometries(g);
        }
        scene.addLights(new DirectionalLight(new Color(255, 255, 255), new Vector(1, 0, 0)));
        ImageWriter imageWriter = new ImageWriter(" #project 2 test 3###", 4000, 4000, 750, 750);

        scene.createHierarchy(4);
        Render render = new Render(imageWriter, scene).setSuperSampling(15).setMultithreading(3).setBoundingBoxEffect(true);

        render.renderImage();
        render.writeToImage();
    }
}