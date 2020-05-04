package renderer;

import geometries.Intersectable;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class Render {
    private Scene _scene;
    private ImageWriter _imageWriter;

    public Render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;
        this._scene = scene;
    }

    public Render(String name) {

    }


    /**
     * creating the image, with the objects.
     */
    public void renderImage() {
        elements.Camera camera = _scene.getCamera();
        Intersectable geometries = _scene.getGeometries();
        java.awt.Color background = _scene.getBackground().getColor();
        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();
        double distance = _scene.getDistance();
        double width = _imageWriter.getWidth();
        double height = _imageWriter.getHeight();

        for (int i = 0; i < _imageWriter.getNy(); i++) {// i is pixel row number
            for (int j = 0; j < _imageWriter.getNx(); j++) {//j is pixel in the row number
                Ray ray = camera.constructRayThroughPixel(nX, nY, j, i, distance, width, height);
                List<Point3D> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints == null) {
                    _imageWriter.writePixel(j, i, background);
                } else {
                    Point3D closestPoint = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(j, i, calcColor(closestPoint).getColor());
                }
            }
        }
    }

    /**
     * @param point <b> the given Point3D </b>
     * @return Color <b> of the given Point3D </b>
     */
    private primitives.Color calcColor(Point3D point) { return _scene.getAmbientLight().getIntensity(); }

    /**
     * @param intersectionPoints <b> the Point3D's List of the intersections points </b>
     * @return Point3D <b> the most closest point to the camera point </b>
     */
    private Point3D getClosestPoint(List<Point3D> intersectionPoints) {
        if (intersectionPoints == null) { return null; }
        Point3D begin = _scene.getCamera().getP();
        double minDistance = begin.distance(intersectionPoints.get(0));
        Point3D minPoint = new Point3D(intersectionPoints.get(0));
        for (Point3D point : intersectionPoints) {
            double distance = begin.distance(point);
            if (distance < minDistance) {
                minDistance = distance;
                minPoint = new Point3D(point);
            }
        }
        return minPoint;
    }

    /**
     * create a grid to an exist image.
     *
     * @param interval <b> the interval of the grid </b>
     * @param color    <b> the Color of the grid </b>
     */
    public void printGrid(int interval, java.awt.Color color) {
        for (int i = 0; i < _imageWriter.getNy(); i++) {
            for (int j = 0; j < _imageWriter.getNx(); j++) {
                if (i % interval == 0 || j % interval == 0) {
                    this._imageWriter.writePixel(i, j, color);
                }
            }
        }
    }

    /**
     * Function writeToImage produces unoptimized jpeg file of
     * the image according to pixel color matrix in the directory
     * of the project
     */
    public void writeToImage() { _imageWriter.writeToImage(); }
}
