package geometries;

import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * implements the Geometries class, which implements the {@link Intersectable} class.
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
     * @param geometries <b> the {@link Intersectable} collection of the geometries </b>
     */
    public void add(Intersectable... geometries) {
        for (int i = 0; i < geometries.length; i++) { intersectables.add(geometries[i]); }
    }

    /**
     * @param ray <b> the {@link Ray} we will find his intersections </b>
     * @return List<GeoPoint> <b> the intersections points </b>
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> lst, result = new LinkedList<GeoPoint>();

        for (int i = 0; i < intersectables.size(); i++) {
            lst = intersectables.get(i).findIntersections(ray);
            if (lst != null) { result.addAll(lst); }
        }

        if (isZero(result.size())) { return null; }
        return result;
    }
}
