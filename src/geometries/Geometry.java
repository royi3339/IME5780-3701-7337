package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * implements the Geometry interface.
 *
 * @author Royi Alishayev idan darmoni
 */
public interface Geometry extends Intersectable {
    /**
     * @param p <b> a Point3D on the Geometry object </b>
     * @return Vector <b> normal </b>
     */
    Vector getNormal(Point3D p);
}
