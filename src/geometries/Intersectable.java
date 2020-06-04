package geometries;

import primitives.Ray;
import primitives.Point3D;

import java.util.List;

/**
 * implements the Intersectable interface.
 *
 * @author Royi Alishayev idan darmoni
 */
public interface Intersectable {
    /**
     * checking the Points which intersections with the object, and with the given {@link Ray}, and return a List of those points.
     * <p>
     * if there is no intersections, will return null.
     *
     * @param ray <b> the {@link Ray} we will find his intersections </b>
     * @return List<Point3D> <b> the intersections points </b>
     */
    default List<GeoPoint> findIntersections(Ray ray) { return findIntersections(ray, Double.POSITIVE_INFINITY); }

    /**
     * @param ray
     * @param maxDistance <b> the range of the checking of the {@link Ray} </b>
     * @return
     */
    List<GeoPoint> findIntersections(Ray ray, double maxDistance);

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
         * @param o<b> the other object </b>
         * @return boolean <b> true / false </b>
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) { return true; }
            if (!(o instanceof GeoPoint)) { return false; }
            GeoPoint geoPoint = (GeoPoint) o;
            return point.equals(geoPoint.point) && geometry == geoPoint.geometry;
        }

        @Override
        public String toString() { return "GeoPoint: " + "point = " + point + "geometry = " + geometry; }
    }
}
