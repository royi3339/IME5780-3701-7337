package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

/**
 * implements the Geometries class, which implements the Intersectable class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Geometries implements Intersectable {
    private List<Intersectable> intersectables;

    /**
     * <b> Geometries default constructor. </b>
     */
    public Geometries() {
        intersectables = null;
    }

    /**
     * <b> Geometries constructor. </b>
     *
     * @param geometries <b> the Intersectable collection of the geometries </b>
     */
    public Geometries(Intersectable... geometries) {
        this();
        add(geometries);
    }

    /**
     * @param geometries <b> the Intersectable collection of the geometries </b>
     */
    public void add(Intersectable... geometries) {
        if (intersectables == null)
            intersectables = new ArrayList<Intersectable>();
        for (int i = 0; i < geometries.length; i++) {
            intersectables.add(geometries[i]);
        }
    }

    /**
     * @param ray <b> the Ray we will find his intersections </b>
     * @return List<Point3D> <b> find the intersections (null) </b>
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}
