package renderer;

import elements.Light;
import elements.LightSource;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
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
     * <b> {@link Render} constructor. </b>
     *
     * @param imageWriter <b> the {@link ImageWriter} of the {@link Render} </b>
     * @param scene       <b> the {@link Scene} of the {@link Render} </b>
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
     * @param intersection <b> the given {@link GeoPoint} </b>
     * @return {@link Color} <b> of the given {@link Point3D} </b>
     */
    private Color calcColor(GeoPoint intersection) {

        Color color = _scene.getAmbientLight().getIntensity();          // kA * iA
        color = color.add(intersection.geometry.getEmission());         // (kA * iA) + iE

        double kd = intersection.geometry.getMaterial().getKD();
        double ks = intersection.geometry.getMaterial().getKS();
        int nSh = intersection.geometry.getMaterial().getNShininess();

        Vector l, r;
        Vector n = intersection.geometry.getNormal(intersection.point);
        Vector v = _scene.getCamera().getVto();
        Color iL;

        for (LightSource lS : _scene.getLights()) {
            l = lS.getL(intersection.point).normalized();
            r = l.subtract(n.scale(2 * l.dotProduct(n)));

            if (l.dotProduct(n) * v.dotProduct(n) > 0) {
                iL = lS.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, l, n, iL), calcSpecular(ks, r, v, nSh, iL));
            }
        }
        return color;
    }

    /**
     * calculating the diffusive.
     *
     * @param kd <b> the diffuse's value factor of the Material </b>
     * @param l  <b> the normalized {@link Vector} of the {@link Light} </b>
     * @param n  <b> the normalized orthogonal {@link Vector} to the object </b>
     * @param iL <b> the light intensity of the {@link Light} at the specific intersection point with the object </b>
     * @return {@link Color} <b> the diffusive {@link Color} </b>
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color iL) {
        return iL.scale(kd * Math.abs(l.dotProduct(n)));
    }

    /**
     * calculating the specular.
     *
     * @param ks  <b> the specular's value factor of the Material </b>
     * @param r   <b>  </b>
     * @param v   <b>  </b>
     * @param nSh <b> the object’s shininess </b>
     * @param iL  <b> the light intensity of the {@link Light} at the specific intersection point with the object </b>
     * @return {@link Color} <b> the specular {@link Color} </b>
     */
    private Color calcSpecular(double ks, Vector r, Vector v, int nSh, Color iL) {
        return iL.scale(ks * (Math.pow(r.dotProduct(v), nSh)));
    }

    /**
     * @param intersectionPoints <b> the {@link GeoPoint} List of the intersections points </b>
     * @return {@link GeoPoint} <b> the most closest point to the camera's point </b>
     */
    public GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
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
     * @param color    <b> the {@link Color} of the grid </b>
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
