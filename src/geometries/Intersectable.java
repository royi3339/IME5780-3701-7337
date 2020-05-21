package geometries;

import primitives.Ray;
import primitives.Point3D;
import primitives.Vector;

import java.util.List;
import java.util.Objects;

/**
 * implements the Intersectable interface.
 *
 * @author Royi Alishayev idan darmoni
 */
public interface Intersectable {
    /**
     * checking the Points which intersections with the object, and with the given Ray, and return a List of those points.
     * <p>
     * if there is no intersections, will return null.
     *
     * @param ray <b> the Ray we will find his intersections </b>
     * @return List<Point3D> <b> the intersections points </b>
     */
    List<GeoPoint> findIntersections(Ray ray);

    /**
     * the List of the Geometry points
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        /**
         * <b> GeoPoint constructor </b>
         *
         * @param g <b> the Geometry of the GeoPoint </b>
         * @param p <b> the Point3D of the GeoPoint </b>
         */
        public GeoPoint(Geometry g, Point3D p) {
            geometry = g;                   // to check if its working ! ! !
            point = new Point3D(p);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint)) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return point.equals(geoPoint.point) && geometry.equals(geoPoint.geometry);
        }
    }
}
