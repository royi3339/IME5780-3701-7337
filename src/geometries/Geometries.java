package geometries;

import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * implements the Geometries class, which implements the {@link Intersectable} class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Geometries implements Intersectable {
    private List<Intersectable> intersectables;

    /**
     * <b> {@link Geometries} default constructor. </b>
     */
    public Geometries() {
        intersectables = new ArrayList<Intersectable>();
    }

    /**
     * <b> {@link Geometries} constructor. </b>
     *
     * @param geometries <b> the Intersectable collection of the geometries </b>
     */
    public Geometries(Intersectable... geometries) {
        this();
        add(geometries);
    }

    /**
     * adding a List of {@link Intersectable} to the intersectables's List.
     *
     * @param geometries <b> the {@link Intersectable} collection of the geometries </b>
     */
    public void add(Intersectable... geometries) {
        for (Intersectable intersectable : geometries) { intersectables.add(intersectable); }
    }

    /**
     * @param ray         <b> the {@link Ray} we will find his intersections </b>
     * @param maxDistance <b> the range of the distance checking of the {@link Ray} </b>
     * @return List<GeoPoint> <b> the intersections points </b>
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> lst, result = new LinkedList<GeoPoint>();

        for (Intersectable intersectable : intersectables) {
            lst = intersectable.findIntersections(ray, maxDistance);
            if (lst != null) { result.addAll(lst); }
        }
        if (isZero(result.size())) { return null; }
        return result;
    }
}
