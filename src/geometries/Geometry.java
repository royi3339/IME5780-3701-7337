package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * implements the Geometry interface.
 *
 * @author Royi Alishayev idan darmoni
 */
public abstract class Geometry implements Intersectable {

    protected Color _emmission;

    /**
     * <b> Geometry constructor </b>
     *
     * @param emmission <b> the emmission Color of the Geometry </b>
     */
    public Geometry(Color emmission) { _emmission = new Color(emmission); }

    /**
     * <b> Geometry default constructor </b>
     */
    public Geometry() { _emmission = Color.BLACK; }

    /**
     * @param p <b> a Point3D on the Geometry object </b>
     * @return Vector <b> normal </b>
     */
    public abstract Vector getNormal(Point3D p);

    /**
     * @return Color <b> the emmission </b>
     */
    public Color getEmmission() { return _emmission; }
}
