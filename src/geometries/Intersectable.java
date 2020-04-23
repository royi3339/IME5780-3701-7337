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
     * checking the Points which intersections with the object, and with the given Ray, and return a List of those points.
     * <p>
     * if there is no intersections, will return null.
     *
     * @param ray <b> the Ray we will find his intersections </b>
     * @return List<Point3D> <b> the intersections points </b>
     */
    List<Point3D> findIntersections(Ray ray);
}
