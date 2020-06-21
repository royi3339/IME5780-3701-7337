package unittests;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import primitives.Color;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

/**
 * Test rendering abasic image
 *
 * @author Dan
 */
public class RenderTests {

    /**
     * Produce a scene with basic 3D model and render it into a jpeg image with a
     * grid
     */
    @Test
    public void basicRenderTwoColorTest() {
        final int EFFECT = 0;

        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(100);
        scene.setBackground(new Color(75, 127, 90));
        scene.setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1));

        scene.addGeometries(new Sphere(50, new Point3D(0, 0, 100)));

        scene.addGeometries(
                new Triangle(new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),
                new Triangle(new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),
                new Triangle(new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),
                new Triangle(new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100)));

        ImageWriter imageWriter = new ImageWriter("base render test", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage(EFFECT);
        render.printGrid(50, java.awt.Color.YELLOW);
        render.writeToImage();
    }

    @Test
    public void basicRenderMultiColorTest() {
        final int EFFECT = 0;

        Scene scene = new Scene("Test scene");
        scene.setCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0)));
        scene.setDistance(100);
        scene.setBackground(Color.BLACK);
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.2));

        scene.addGeometries(new Sphere(50, new Point3D(0, 0, 100)));

        scene.addGeometries(
                new Triangle(new Color(java.awt.Color.BLUE),
                        new Point3D(100, 0, 100), new Point3D(0, 100, 100), new Point3D(100, 100, 100)),      // lower right
                new Triangle(
                        new Point3D(100, 0, 100), new Point3D(0, -100, 100), new Point3D(100, -100, 100)),    // upper right
                new Triangle(new Color(java.awt.Color.RED),
                        new Point3D(-100, 0, 100), new Point3D(0, 100, 100), new Point3D(-100, 100, 100)),    // lower left
                new Triangle(new Color(java.awt.Color.GREEN),
                        new Point3D(-100, 0, 100), new Point3D(0, -100, 100), new Point3D(-100, -100, 100))); // upper left

        ImageWriter imageWriter = new ImageWriter("color render test", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage(EFFECT);
        render.printGrid(50, java.awt.Color.WHITE);
        render.writeToImage();
    }

    /**
     * not active, because the method is private.
     * <p>
     * Test method for
     * {@link Render#findClosestIntersection(Ray)}
     */
/*
    @Test         // render.findClosestIntersection() must be public if we want to test this method !
    public void findClosestIntersectionTest() {
        ImageWriter imageWriter = new ImageWriter("renderTest", 800, 500, 16, 10);
        Scene scene = new Scene("sceneTest");
        scene.setCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(1, 0, 0)));
        Render render = new Render(imageWriter, scene);
        Sphere sphere = new Sphere(9, new Point3D(0,0 , 33));
        scene.addGeometries(sphere);
        GeoPoint g = new GeoPoint(sphere, new Point3D(0, 0, 24));
        GeoPoint p = render.findClosestIntersection(new Ray(scene.getCamera().getP(), scene.getCamera().getVto()));
        assertEquals("it is should be the closest Point !", g, p);
    }*/
}
