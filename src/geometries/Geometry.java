package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * implements the Geometry interface.
 *
 * @author Royi Alishayev idan darmoni
 */
public interface Geometry {
    /**
     * @param p <b> a Point3D on the Geometry object </b>
     * @return Vector <b> normal </b>
     */
    Vector getNormal(Point3D p);
}
