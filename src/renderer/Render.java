package renderer;

import elements.Camera;
import elements.Light;
import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.List;

import static primitives.Util.*;

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

    private int _threads = 3;
    private final int SPARE_THREADS = 2;
    private boolean _print = true;

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
     * Pixel is an internal helper class whose objects are associated with a Render object that
     * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
     * its progress.<br/>
     * There is a main follow up object and several secondary objects - one in each thread.
     *
     * @author Dan
     */
    private class Pixel {
        private long _maxRows = 0;
        private long _maxCols = 0;
        private long _pixels = 0;
        public volatile int row = 0;
        public volatile int col = -1;
        private long _counter = 0;
        private int _percents = 0;
        private long _nextCounter = 0;

        /**
         * The constructor for initializing the main follow up Pixel object
         *
         * @param maxRows the amount of pixel rows
         * @param maxCols the amount of pixel columns
         */
        public Pixel(int maxRows, int maxCols) {
            _maxRows = maxRows;
            _maxCols = maxCols;
            _pixels = maxRows * maxCols;
            _nextCounter = _pixels / 100;
            if (Render.this._print) System.out.println("\r" + _percents + "%");
        }

        /**
         * Default constructor for secondary Pixel objects
         */
        public Pixel() {}

        /**
         * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
         * critical section for all the threads, and main Pixel object data is the shared data of this critical
         * section.<br/>
         * The function provides next pixel number each call.
         *
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
         * finished, any other value - the progress percentage (only when it changes)
         */
        private synchronized int nextP(Pixel target) {
            ++col;
            ++_counter;
            if (col < _maxCols) {
                target.row = this.row;
                target.col = this.col;
                if (_counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            ++row;
            if (row < _maxRows) {
                col = 0;
                if (_counter == _nextCounter) {
                    ++_percents;
                    _nextCounter = _pixels * (_percents + 1) / 100;
                    return _percents;
                }
                return 0;
            }
            return -1;
        }

        /**
         * Public function for getting next pixel number into secondary Pixel object.
         * The function prints also progress percentage in the console window.
         *
         * @param target target secondary Pixel object to copy the row/column of the next pixel
         * @return true if the work still in progress, -1 if it's done
         */
        public boolean nextPixel(Pixel target) {
            int percents = nextP(target);
            if (percents > 0)
                if (Render.this._print) System.out.println("\r" + percents + "%");
            if (percents >= 0)
                return true;
            if (Render.this._print) System.out.println("\r" + 100 + "%");
            return false;
        }
    }

    /**
     * creating the image, with the objects.
     *
     * @param effect <b> the number of the {@link Ray}s that we want to send,
     *               <p> when effect == 0 will be send only a single {@link Ray} </b>
     */
    public void renderImage(int effect) {
        java.awt.Color background = _scene.getBackground().getColor(); ////////////////////////

        Camera camera = _scene.getCamera();
        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();
        double distance = _scene.getDistance();
        double width = _imageWriter.getWidth();
        double height = _imageWriter.getHeight();

        // Generate threads
        final Pixel thePixel = new Pixel(nX, nY);
        Thread[] threads = new Thread[_threads];
        for (int th = _threads - 1; th >= 0; --th) {
            threads[th] = new Thread(() -> {
                Pixel pixel = new Pixel();
                while (thePixel.nextPixel(pixel)) {
                    Ray ray = camera.constructRayThroughPixel(nX, nY, pixel.col, pixel.row, distance, width, height);
                    GeoPoint closestPoint = findClosestIntersection(ray);
                    _imageWriter.writePixel(pixel.col, pixel.row, closestPoint == null ? background : calcColor(closestPoint, ray, effect).getColor());
                }
            });
        }
        // Start threads
        for (Thread thread : threads) thread.start();

        // Wait for all threads to finish
        for (Thread thread : threads) try { thread.join(); } catch (Exception e) { }
        if (_print) System.out.println("\r100%\n");
    }

    /**
     * Set multithreading <br>
     * - if the parameter is 0 - number of cores less 2 is taken
     *
     * @param threads number of threads
     * @return the Render object itself
     */
    public Render setMultithreading(int threads) {
        if (threads < 0) { throw new IllegalArgumentException("Multithreading patameter must be 0 or higher"); }
        if (threads != 0) { _threads = threads;} else {
            int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
            if (cores <= 2) { _threads = 1; } else {
                _threads = cores;
            }
        }
        return this;
    }

    /**
     * Set debug printing on
     *
     * @return the Render object itself
     */
    public Render setDebugPrint() {
        _print = true;
        return this;
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
     * calculating the {@link Color} at a {@link Point3D}
     *
     * @param geoPoint <b> the given {@link GeoPoint} </b>
     * @param inRay    <b> the {@link Ray} that we check in this step os the recursion </b>
     * @param effect   <b> the feature which controlling on the option of a single {@link Ray} of beam of {@link Ray}s, and the amount of the {@link Ray}s </b>
     * @return {@link Color} <b> of the given {@link Point3D} </b>
     */
    private Color calcColor(GeoPoint geoPoint, Ray inRay, int effect) {
        return _scene.getAmbientLight().getIntensity().add(calcColor(geoPoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0, effect)); // ka * ia + calcColor
    }

    /**
     * calculating the {@link Color} at a {@link Point3D}
     *
     * @param geoPoint <b> the given {@link GeoPoint} </b>
     * @param inRay    <b> the {@link Ray} that we check in this step os the recursion </b>
     * @param level    <b> the parameter of the recursion </b>
     * @param k        <b> the multiple value of the all previous kr </b>
     * @param effect   <b> the feature which controlling on the option of a single {@link Ray} of beam of {@link Ray}s, and the amount of the {@link Ray}s </b>
     * @return {@link Color} <b> of the given {@link GeoPoint} {@link Point3D} intersection </b>
     */
    private Color calcColor(GeoPoint geoPoint, Ray inRay, int level, double k, int effect) {
        if (geoPoint == null) { return _scene.getBackground(); }
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
        if (level == 1) { return Color.BLACK; }

        double kr = geoPoint.geometry.getMaterial().getKR();            // the reflection factor
        double glurry = geoPoint.geometry.getMaterial().getGlurry();
        double kkr = k * kr;
        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay(n, geoPoint.point, inRay);
            color = color.add(callRecursiveCalcColor(reflectedRay, level, kkr, kr, effect, glurry, n));
        }

        double kt = geoPoint.geometry.getMaterial().getKT();            // the transparency (refraction) factor
        double kkt = k * kt;
        if (kkt > MIN_CALC_COLOR_K) {
            Ray refractedRay = constructRefractedRay(n, geoPoint.point, inRay);
            color = color.add(callRecursiveCalcColor(refractedRay, level, kkt, kt, effect, glurry, n));
        }
        return color;
    }

    /**
     * refactoring to the calcColor method to be a recursive with the effect feature,
     * <p>
     * which can work with a single {@link Ray}, or with a beam of {@link Ray}s.
     *
     * @param inRay   <b> the {@link Ray} that we check in this step os the recursion </b>
     * @param level   <b> the parameter of the recursion </b>
     * @param kk      <b> the value of the kkt or of the kkr respectively </b>
     * @param k       <b> the value of the kt or of the kr respectively </b>
     * @param effect  <b> the feature of our Project, decide with or without the "Blurry&Glossy", and the amount of the {@link Ray}s </b>
     * @param texture <b> the value of the Blurry/Glossy of the Object </b>
     * @param normal  <b> the normal {@link Vector} </b>
     * @return {@link Color} <b> of the given {@link GeoPoint} {@link Point3D} intersection </b>
     */
    private Color callRecursiveCalcColor(Ray inRay, int level, double kk, double k, int effect, double texture, Vector normal) {
        Color color = Color.BLACK;
        List<Ray> rayBeam = inRay.getRayBeam(texture, normal, effect);
        for (Ray ray : rayBeam) {
            GeoPoint rPoint = findClosestIntersection(ray);
            Color c = calcColor(rPoint, ray, level - 1, kk, effect).scale(k);
            color = color.add(c);
        }
        color = color.scale(1d / rayBeam.size());
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
     * not in use !!!!!!!!!!!!!!!!!!!!
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
     * provided number between 1 to 0 includes that present the factor of the transparency.
     *
     * @param ls       <b> the given {@link LightSource} </b>
     * @param l        <b> the forward {@link Vector} of the {@link LightSource} </b>
     * @param n        <b> the normal {@link Vector} of the intersection point to the surface </b>
     * @param geoPoint <b> the intersection point </b>
     * @return double <b> number between 1 to 0 </b>
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

/*
// Generate threads
        Thread[] threads = new Thread[_threads];
        for (int th = _threads - 1; th >= 0; --th) {
            threads[th] = new Thread(() -> {
                for (int i = 0; i < nY; i++) { // i is pixel row number
                    for (int j = 0; j < nX; j++) { //j is pixel in the row number
                        Ray ray = camera.constructRayThroughPixel(nX, nY, j, i, distance, width, height);
                        GeoPoint closestPoint = findClosestIntersection(ray);
                        _imageWriter.writePixel(j, i, calcColor(closestPoint, ray, effect).getColor());
                    }
                }
            });
        }
 */
