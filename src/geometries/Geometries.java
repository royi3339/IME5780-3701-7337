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
        intersectables = new ArrayList<Intersectable>();
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
        List<Point3D> lst, result = new ArrayList<Point3D>();
        for (int i = 0; i < intersectables.size(); i++) {
            lst = intersectables.get(i).findIntersections(ray);
            if (lst != null) {
                result.addAll(lst);
            }
        }

        if (result.size() == 0)
            return null;
        return result;
    }
}
