package unittests;

import elements.AmbientLight;
import elements.Camera;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Color;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import java.util.List;

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

        render.renderImage();
        render.printGrid(50, java.awt.Color.YELLOW);
        render.writeToImage();
    }

    /**
     * Test method for
     * {@link Render#getClosestPoint(List)}
     */
    /*
    @Test         // render.getClosestPoint() must be public if we want to test this method !
    public void getClosestPointTest() {
        ImageWriter imageWriter = new ImageWriter("renderTest", 800, 500, 16, 10);
        Scene scene = new Scene("sceneTest");
        scene.setCamera(new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(1, 0, 0)));
        Render render = new Render(imageWriter, scene);
        render.getClosestPoint(List.of(new Point3D(0, 0, 1), new Point3D(0, 4, 3), new Point3D(12, 0, -13)));
        assertEquals("it is should be the closest Point !", new Point3D(0, 0, 1), p2);
    }       */
}