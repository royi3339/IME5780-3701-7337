package renderer;

import elements.Light;
import elements.LightSource;
import geometries.Intersectable;
import primitives.*;
import scene.Scene;

import java.util.List;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * implements the Render class.
 * <p>
 * gets a {@link Scene}, and a {@link ImageWriter} and rendering those to an image.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Render {
    private Scene _scene;
    private ImageWriter _imageWriter;

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

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
                GeoPoint closestPoint = findClosestIntersection(ray);
                _imageWriter.writePixel(j, i, closestPoint == null ? background : calcColor(closestPoint, ray).getColor());
            }
        }
    }

    /**
     * helper method checking if the sing of doubles are the same.
     *
     * @param a <b> the first number </b>
     * @param b <b> the second number </b>
     * @return boolean <b> if the sign of a and b are the same: return true, else: return false <b>
     */
    private boolean sameSign(double a, double b) {
        if ((a < 0 && b < 0) || (a > 0 && b > 0)) { return true; }
        return false;
    }

    /**
     * @param n     <b> the normal {@link Vector} </b>
     * @param point <b> the given {@link Point3D} that we checking </b>
     * @param ray   <b> the given {@link Ray} that we checking </b>
     * @return {@link Ray} <b> return the Reflection {@link Ray} </b>
     */
    private Ray constructReflectedRay(Vector n, Point3D point, Ray ray) {
        Vector v = ray.getDirection();
        Vector r = v.subtract(n.scale(2 * v.dotProduct(n)));
        return new Ray(point, r, n);
    }

    /**
     * @param n     <b> the normal {@link Vector} </b>
     * @param point <b> the given {@link Point3D} that we checking </b>
     * @param ray   <b> the given {@link Ray} that we checking </b>
     * @return {@link Ray} <b> return the Refraction {@link Ray} </b>
     */
    private Ray constructRefractedRay(Vector n, Point3D point, Ray ray) { return new Ray(point, ray.getDirection(), n); }

    /**
     * find the most closest {@link GeoPoint} intersection to a {@link Ray}.
     *
     * @param ray <b> the {@link Ray} that we checking the closest point for </b>
     * @return {@link GeoPoint} <b> the most closest point to the {@link Ray}'s start point </b>
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(ray);
        if (intersections == null) { return null; }
        Point3D p0 = ray.getHead();
        double minDistance = p0.distance(intersections.get(0).point);
        GeoPoint closestPoint = intersections.get(0);
        for (GeoPoint geoPoint : intersections) {
            double distance = p0.distance(geoPoint.point);
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = geoPoint;
            }
        }
        return closestPoint;
    }

    /**
     * @param intersection <b> the given {@link GeoPoint} </b>
     * @param inRay        <b> the {@link Ray} that we check in this step os the recursion </b>
     * @return {@link Color} <b> of the given {@link Point3D} </b>
     */
    private Color calcColor(GeoPoint geoPoint, Ray inRay) {
        return _scene.getAmbientLight().getIntensity().add(calcColor(geoPoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0)); // ka * ia + calcColor
    }

    /**
     * @param geoPoint <b> the given {@link GeoPoint} </b>
     * @param inRay    <b> the {@link Ray} that we check in this step os the recursion </b>
     * @param level    <b> the parameter of the recursion </b>
     * @param k        <b> the multiple value of the all previous kr </b>
     * @return {@link Color} <b> of the given {@link Point3D} </b>
     */
    private Color calcColor(GeoPoint geoPoint, Ray inRay, int level, double k) {
        if (level == 1 || k < MIN_CALC_COLOR_K) { return Color.BLACK; }

        double kd = geoPoint.geometry.getMaterial().getKD();
        double ks = geoPoint.geometry.getMaterial().getKS();
        int nSh = geoPoint.geometry.getMaterial().getNShininess();

        Vector l;
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Vector v = _scene.getCamera().getVto();
        Color iL;

        Color color = geoPoint.geometry.getEmission(); // iE
        for (LightSource lS : _scene.getLights()) {
            l = lS.getL(geoPoint.point);

            double ln = alignZero(l.dotProduct(n));
            double vn = alignZero(v.dotProduct(n));
            if (sameSign(ln, vn)) {
                double ktr = transparency(lS, l, n, geoPoint);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    iL = lS.getIntensity(geoPoint.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, l, n, iL), calcSpecular(ks, l, n, v, nSh, iL));
                }
            }
        }
        double kr = geoPoint.geometry.getMaterial().getKR();            // the reflection factor
        double kkr = k * kr;
        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay(n, geoPoint.point, inRay);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint != null) {
                color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
            }
        }
        double kt = geoPoint.geometry.getMaterial().getKT();            // the transparency (refraction) factor
        double kkt = k * kt;
        if (kkt > MIN_CALC_COLOR_K) {
            Ray refractedRay = constructRefractedRay(n, geoPoint.point, inRay);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint != null) {
                color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
            }
        }
        return color;
    }

    /**
     * calculating the diffusive.
     *
     * @param kd <b> the diffuse's attenuation factor of the Material </b>
     * @param l  <b> the normalized {@link Vector} of the {@link Light} </b>
     * @param n  <b> the normalized orthogonal {@link Vector} to the object </b>
     * @param iL <b> the light intensity of the {@link Light} at the specific intersection point with the object </b>
     * @return {@link Color} <b> the diffusive {@link Color} </b>
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color iL) {
        double ln = l.dotProduct(n);
        if (ln < 0) { ln *= -1; }
        return iL.scale(kd * ln);
    }

    /**
     * calculating the specular.
     *
     * @param ks  <b> the specular's value factor of the Material </b>
     * @param l   <b> the normalized {@link Vector} of the {@link Light} </b>
     * @param n   <b> the normalized orthogonal {@link Vector} to the object </b>
     * @param v   <b> the direction {@link Vector} of the Camera </b>
     * @param nSh <b> the object’s shininess of the Material </b>
     * @param iL  <b> the light intensity of the {@link Light} at the specific intersection point with the object </b>
     * @return {@link Color} <b> the specular {@link Color} </b>
     */
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nSh, Color iL) {
        Vector r = l.subtract(n.scale(2 * l.dotProduct(n)));
        double vr = alignZero(v.dotProduct(r));
        if (vr >= 0) { return Color.BLACK; }
        return iL.scale(ks * (Math.pow(-vr, nSh)));
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

    /**
     * not in use
     *
     * @param light    <b> the given {@link LightSource} </b>
     * @param l        <b> the forward {@link Vector} of the {@link LightSource} </b>
     * @param n        <b> the normal {@link Vector} of the intersection point to the surface </b>
     * @param geoPoint <b> the intersection point </b>
     * @return boolean <b> true if there is no other object in the way, else return false </b>
     */
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geoPoint) {
        Vector shadeDirection = l.scale(-1); // from point to light source

        Ray shadeRay = new Ray(geoPoint.point, shadeDirection, n);

        double lightDistance = light.getDistance(geoPoint.point);

        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(shadeRay, lightDistance);
        if (intersections == null) { return true; }

        for (GeoPoint gp : intersections) {
            if (isZero(gp.geometry.getMaterial().getKT())) { // check if at least one of the point is "atum" in Hebrew.
                return false;
            }
        }
        return true;
    }

    /**
     * @param ls       <b> the given {@link LightSource} </b>
     * @param l        <b> the forward {@link Vector} of the {@link LightSource} </b>
     * @param n        <b> the normal {@link Vector} of the intersection point to the surface </b>
     * @param geoPoint <b> the intersection point </b>
     * @return double <b> number between 1 to 0 includes that present the factor of the transparency </b>
     */
    private double transparency(LightSource ls, Vector l, Vector n, GeoPoint geoPoint) {
        Vector shadeDirection = l.scale(-1); // from point to light source

        Ray shadeRay = new Ray(geoPoint.point, shadeDirection, n);

        double lightDistance = ls.getDistance(geoPoint.point);

        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(shadeRay, lightDistance);
        if (intersections == null) { return 1d; }

        double ktr = 1d;
        for (GeoPoint gp : intersections) {
            ktr *= gp.geometry.getMaterial().getKT();
            if (ktr < MIN_CALC_COLOR_K) { return 0d; }
        }
        return ktr;
    }
}