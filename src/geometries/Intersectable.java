package geometries;

import primitives.Ray;
import primitives.Point3D;
import primitives.Vector;

import java.util.List;

/**
 * implements the Intersectable abstract class.
 *
 * @author Royi Alishayev idan darmoni
 */
public abstract class Intersectable {

    private static boolean boxEffect;
    protected BoundingBox box;

    /**
     * checking the Points which intersections with the object, and with the given {@link Ray}, and return a List of those points.
     * <p>
     * if there is no intersections, will return null.
     *
     * @param ray <b> the {@link Ray} we will find his intersections </b>
     * @return List<Point3D> <b> the intersections points </b>
     */
    /* default */
    public List<GeoPoint> findIntersections(Ray ray) { return findIntersections(ray, Double.POSITIVE_INFINITY); }

    /**
     * checking the Points which intersections with the Bounding Box, and with the given {@link Ray}, and return a List of those points.
     * <p>
     * in range of the given distance.
     * <p>
     * if there is no intersections, will return null.
     *
     * @param ray         <b> the {@link Ray} we will find his intersections </b>
     * @param maxDistance <b> the range of the checking of the {@link Ray} </b>
     * @return List<Point3D> <b> the intersections points </b>
     */
    public List<GeoPoint> findBoxIntersections(Ray ray, double maxDistance) {
        if (boxEffect && !box.intersects(ray)) { return null; }
        return findIntersections(ray, maxDistance);
    }

    /**
     * checking the Points which intersections with the Bounding Box, and with the given {@link Ray}, and return a List of those points.
     * <p>
     * if there is no intersections, will return null.
     *
     * @param ray <b> the {@link Ray} we will find his intersections </b>
     * @return List<Point3D> <b> the intersections points </b>
     */
    public List<GeoPoint> findBoxIntersections(Ray ray) { return findBoxIntersections(ray, Double.POSITIVE_INFINITY); }

    /**
     * checking the Points which intersections with the object, and with the given {@link Ray}, and return a List of those points.
     * <p>
     * in range of the given distance.
     * <p>
     * if there is no intersections, will return null.
     *
     * @param ray         <b> the {@link Ray} we will find his intersections </b>
     * @param maxDistance <b> the range of the checking of the {@link Ray} </b>
     * @return List<Point3D> <b> the intersections points </b>
     */
    public abstract List<GeoPoint> findIntersections(Ray ray, double maxDistance);


    /**
     * setting the Bounding Box effect on or off.
     *
     * @param boxEffect <b> the feature of the Bounding Box effect, true / false </b>
     */
    public static void setBoxEffect(boolean boxEffect) { Intersectable.boxEffect = boxEffect; }

    /**
     * the GeoPoint's static class implements
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        /**
         * <b> {@link GeoPoint} constructor </b>
         *
         * @param g <b> the {@link Geometry} of the {@link GeoPoint} </b>
         * @param p <b> the {@link Point3D} of the {@link GeoPoint} </b>
         */
        public GeoPoint(Geometry g, Point3D p) {
            geometry = g;
            point = new Point3D(p);
        }

        /**
         * @param o <b> the other object </b>
         * @return boolean <b> true / false </b>
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) { return true; }
            if (!(o instanceof GeoPoint)) { return false; }
            GeoPoint geoPoint = (GeoPoint) o;
            return point.equals(geoPoint.point) && geometry == geoPoint.geometry;
        }

        //  @Override               helper for the Test....
        //public String toString() { return "GeoPoint: " + "point = " + point + "geometry = " + geometry; } // helper for the tests
    }

    /**
     * @return {@link BoundingBox} <b> return the {@link BoundingBox} </b>
     */
    public BoundingBox getBox() { return box; }

    /**
     * @return double <b> the center of the X axis, of the {@link BoundingBox} </b>
     */
    public double xCenter() { return (box.x1 + box.x2) / 2; }

    /**
     * @return double <b> the center of the Y axis, of the {@link BoundingBox} </b>
     */
    public double yCenter() { return (box.y1 + box.y2) / 2; }

    /**
     * @return double <b> the center of the Z axis, of the {@link BoundingBox} </b>
     */
    public double zCenter() { return (box.z1 + box.z2) / 2; }

    /**
     * the BoundingBox's static class implements
     */
    public static class BoundingBox {

        static final double DELTA = 0.05;

        private double x1;
        private double x2;
        private double y1;
        private double y2;
        private double z1;
        private double z2;

        /**
         * <b> {@link BoundingBox} constructor </b>
         *
         * @param x1 <b> the #X1 limit of the {@link BoundingBox} </b>
         * @param x2 <b> the #X2 limit of the {@link BoundingBox} </b>
         * @param y1 <b> the #Y1 limit of the {@link BoundingBox} </b>
         * @param y2 <b> the #Y2 limit of the {@link BoundingBox} </b>
         * @param z1 <b> the #Z1 limit of the {@link BoundingBox} </b>
         * @param z2 <b> the #Z2 limit of the {@link BoundingBox} </b>
         */
        public BoundingBox(double x1, double x2, double y1, double y2, double z1, double z2) {
            if (x1 == x2) {
                x1 -= DELTA;
                x2 += DELTA;
            }

            if (y1 == y2) {
                y1 -= DELTA;
                y2 += DELTA;
            }

            if (z1 == z2) {
                z1 -= DELTA;
                z2 += DELTA;
            }

            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
            this.z1 = z1;
            this.z2 = z2;
        }

        /**
         * <b> {@link BoundingBox} default constructor </b>
         */
        public BoundingBox() {
            this(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY,
                    Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        }

        /**
         * @param x1 <b> setting the x1 parameter, with the given one </b>
         */
        public void setX1(double x1) { this.x1 = x1; }

        /**
         * @param x2 <b> setting the x2 parameter, with the given one </b>
         */
        public void setX2(double x2) { this.x2 = x2; }

        /**
         * @param y1 <b> setting the y1 parameter, with the given one </b>
         */
        public void setY1(double y1) { this.y1 = y1; }

        /**
         * @param y2 <b> setting the y2 parameter, with the given one </b>
         */
        public void setY2(double y2) { this.y2 = y2; }

        /**
         * @param z1 <b> setting the z1 parameter, with the given one </b>
         */
        public void setZ1(double z1) { this.z1 = z1; }

        /**
         * @param z2 <b> setting the z2 parameter, with the given one </b>
         */
        public void setZ2(double z2) { this.z2 = z2; }

        /**
         * @return double <b> getting the X1 parameter </b>
         */
        public double getX1() { return x1; }

        /**
         * @return double <b> getting the X2 parameter </b>
         */
        public double getX2() { return x2; }

        /**
         * @return double <b> getting the Y1 parameter </b>
         */
        public double getY1() { return y1; }

        /**
         * @return double <b> getting the Y2 parameter </b>
         */
        public double getY2() { return y2; }

        /**
         * @return double <b> getting the Z1 parameter </b>
         */
        public double getZ1() { return z1; }

        /**
         * @return double <b> getting the Z2 parameter </b>
         */
        public double getZ2() { return z2; }

        /**
         * checks if a ray intersects the box around a geometry
         *
         * @param ray <b> the {@link Ray} that we want to check for an intersection with </b>
         * @retur boolean <b> true / false </b>
         */
        public boolean intersects(Ray ray) {
            Vector rayVector = ray.getDirection();
            Point3D rayPoint = ray.getHead();
            double pX = rayPoint.getX();
            double pY = rayPoint.getY();
            double pZ = rayPoint.getZ();
            double vX = rayVector.getPointX();
            double vY = rayVector.getPointY();
            double vZ = rayVector.getPointZ();

            double tXmin = (x1 - pX) / vX;
            double tXmax = (x2 - pX) / vX;
            if (tXmin > tXmax) {
                double temp = tXmin;
                tXmin = tXmax;
                tXmax = temp;
            }

            double tYmin = (y1 - pY) / vY;
            double tYmax = (y2 - pY) / vY;
            if (tYmin > tYmax) {
                double temp = tYmin;
                tYmin = tYmax;
                tYmax = temp;
            }

            if ((tXmin > tYmax) || (tYmin > tXmax)) { return false; }

            if (tYmin > tXmin) { tXmin = tYmin; }
            if (tYmax < tXmax) { tXmax = tYmax; }

            double tZmin = (z1 - pZ) / vZ;
            double tZmax = (z2 - pZ) / vZ;
            if (tZmin > tZmax) {
                double temp = tZmin;
                tZmin = tZmax;
                tZmax = temp;
            }

            return ((tXmin <= tZmax) && (tZmin <= tXmax));
        }

        /**
         * check whether the {@link BoundingBox} of the Geometries is a infinity or not,
         * <p>
         * if it is infinity - return True, else return False.
         *
         * @return boolean <b> true / false </b>
         */
        public boolean isInfinity() {
            return x2 == Double.POSITIVE_INFINITY ||
                    y2 == Double.POSITIVE_INFINITY ||
                    z2 == Double.POSITIVE_INFINITY ||
                    x1 == Double.NEGATIVE_INFINITY ||
                    y1 == Double.NEGATIVE_INFINITY ||
                    z1 == Double.NEGATIVE_INFINITY;
        }
    }
}
