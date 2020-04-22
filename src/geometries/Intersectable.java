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
     * @param ray <b> the Ray we will find his intersections </b>
     * @return List<Point3D> <b> find the intersections </b>
     */
    List<Point3D> findIntersections(Ray ray);
}
