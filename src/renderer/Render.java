package renderer;

import geometries.Intersectable;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;

import geometries.Intersectable.GeoPoint;

/**
 * implements the Render class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Render {
    private Scene _scene;
    private ImageWriter _imageWriter;

    /**
     * <b> Render constructor. </b>
     *
     * @param imageWriter <b> the ImageWriter of the Render </b>
     * @param scene       <b> the Scene of the Render </b>
     */
    public Render(ImageWriter imageWriter, Scene scene) {
        this._imageWriter = imageWriter;
        this._scene = scene;
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

        for (int i = 0; i < nY; i++) { // i is pixel row number
            for (int j = 0; j < nX; j++) { //j is pixel in the row number
                Ray ray = camera.constructRayThroughPixel(nX, nY, j, i, distance, width, height);
                List<GeoPoint> intersectionPoints = geometries.findIntersections(ray);
                if (intersectionPoints == null) {
                    _imageWriter.writePixel(j, i, background);
                } else {
                    GeoPoint closestPoint = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(j, i, calcColor(closestPoint).getColor());
                }
            }
        }
    }

    /**
     * @param geoPoint <b> the given GeoPoint </b>
     * @return Color <b> of the given Point3D </b>
     */
    private primitives.Color calcColor(GeoPoint geoPoint) { return _scene.getAmbientLight().getIntensity(); }

    /**
     * @param intersectionPoints <b> the GeoPoint List of the intersections points </b>
     * @return GeoPoint <b> the most closest point to the camera point </b>
     */
    private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
        Point3D begin = _scene.getCamera().getP();
        double minDistance = begin.distance(intersectionPoints.get(0).point);
        GeoPoint minPoint = intersectionPoints.get(0);
        for (GeoPoint geoPoint : intersectionPoints) {
            double distance = begin.distance(geoPoint.point);
            if (distance < minDistance) {
                minDistance = distance;
                minPoint = geoPoint;
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
     * the image, according to pixel color matrix in the directory
     * of the project.
     */
    public void writeToImage() { _imageWriter.writeToImage(); }
}
