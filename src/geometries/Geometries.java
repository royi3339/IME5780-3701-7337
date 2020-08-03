package geometries;

import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;

/**
 * implements the Geometries class, which extends the {@link Intersectable} abstract class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Geometries extends Intersectable {
    private List<Intersectable> _intersectables;

    /**
     * <b> {@link Geometries} default constructor. </b>
     */
    public Geometries() {
        _intersectables = new ArrayList<Intersectable>();
        box = new BoundingBox(0, 0, 0, 0, 0, 0);
    }

    /**
     * <b> {@link Geometries} constructor. </b>
     *
     * @param geometries <b> the Intersectable collection of the geometries </b>
     */
    public Geometries(Intersectable... geometries) {
        this();
        box = null;
        add(geometries);
    }

    /**
     * adding a List of {@link Intersectable} to the intersectables's List.
     *
     * @param geometries <b> the {@link Intersectable} collection of the geometries </b>
     */
    public void add(Intersectable... geometries) {
        if (geometries.length == 0) { return; }
        if (box == null) {
            box = new BoundingBox(geometries[0].box.getX1(), geometries[0].box.getX2(), geometries[0].box.getY1()
                    , geometries[0].box.getY2(), geometries[0].box.getZ1(), geometries[0].box.getZ2());
        }
        for (Intersectable intersectable : geometries) { _intersectables.add(intersectable); }
        for (int i = 0; i < geometries.length; i++) {
            BoundingBox tempBox = geometries[i].box;
            if (tempBox.getX1() < box.getX1()) { box.setX1(tempBox.getX1()); }
            if (tempBox.getX2() > box.getX2()) { box.setX2(tempBox.getX2()); }
            if (tempBox.getY1() < box.getY1()) { box.setY1(tempBox.getY1()); }
            if (tempBox.getY2() > box.getY2()) { box.setY2(tempBox.getY2()); }
            if (tempBox.getZ1() < box.getZ1()) { box.setZ1(tempBox.getZ1()); }
            if (tempBox.getZ2() > box.getZ2()) { box.setZ2(tempBox.getZ2()); }
        }
    }

    /**
     * checking the Points which intersections with the object, and with the Bounding Box, and with the given {@link Ray},
     * and return a List of those points.
     * <p>
     * in range of the given distance.
     * <p>
     * if there is no intersections, will return null.
     *
     * @param ray         <b> the {@link Ray} we will find his intersections </b>
     * @param maxDistance <b> the range of the distance checking of the {@link Ray} </b>
     * @return List<GeoPoint> <b> the intersections points </b>
     */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double maxDistance) {
        List<GeoPoint> lst, result = new LinkedList<GeoPoint>();

        for (Intersectable intersectable : _intersectables) {
            lst = intersectable.findBoxIntersections(ray, maxDistance);
            if (lst != null) { result.addAll(lst); }
        }
        if (isZero(result.size())) { return null; }
        return result;
    }

    /**
     * method that gets a number the number of the recursive...
     * which implements the Tree of our Bounding Box, excluded {@link Plane}.
     *
     * @param depthOfHierarchy <b> the depth of recursion </b>
     */
    public void createHierarchy(int depthOfHierarchy) {

        Intersectable infinityGeometries = new Geometries();
        Intersectable finiteGeometries = new Geometries();

        // Remove all the infinity geometries before we send the Intersectable to the recursion
        for (Intersectable geo : this._intersectables) {
            if (geo.getBox().isInfinity()) {
                ((Geometries) infinityGeometries).add(geo);
            } else { ((Geometries) finiteGeometries).add(geo); }
        }

        this._intersectables.clear();
        this.box = null;

        for (Intersectable geo : ((Geometries) finiteGeometries)._intersectables) { this.add(geo); }

        this.createHierarchyRec(depthOfHierarchy);

        for (Intersectable geo : ((Geometries) infinityGeometries)._intersectables) { this.add(geo); }
    }


    /**
     * recursive method which helping with the depthOfHierarchy,
     * for the hierarchy to build the Bounding Box properly.
     *
     * @param depthOfHierarchy <b> the depth of recursion </b>
     */
    public void createHierarchyRec(int depthOfHierarchy) {

        Intersectable[] voxels = new Intersectable[8];
        for (int i = 0; i < 8; i++) { voxels[i] = null; }

        // Insert any Geometries in the Geometries t the right voxel
        for (int i = 0; i < _intersectables.size(); i++) {
            if (_intersectables.get(i).zCenter() < this.zCenter()) {
                if (_intersectables.get(i).yCenter() < this.yCenter()) {
                    if (_intersectables.get(i).xCenter() < this.xCenter()) {
                        if (voxels[0] == null) { voxels[0] = new Geometries(); }
                        ((Geometries) voxels[0]).add(_intersectables.get(i));
                    } else {
                        if (voxels[1] == null) { voxels[1] = new Geometries(); }
                        ((Geometries) voxels[1]).add(_intersectables.get(i));
                    }
                } else {
                    if (_intersectables.get(i).xCenter() > this.xCenter()) {
                        if (voxels[2] == null) { voxels[2] = new Geometries(); }
                        ((Geometries) voxels[2]).add(_intersectables.get(i));
                    } else {
                        if (voxels[3] == null) { voxels[3] = new Geometries(); }
                        ((Geometries) voxels[3]).add(_intersectables.get(i));
                    }
                }
            } else {
                if (_intersectables.get(i).yCenter() < this.yCenter()) {
                    if (_intersectables.get(i).xCenter() > this.xCenter()) {
                        if (voxels[4] == null) { voxels[4] = new Geometries(); }
                        ((Geometries) voxels[4]).add(_intersectables.get(i));
                    } else {
                        if (voxels[5] == null) { voxels[5] = new Geometries(); }
                        ((Geometries) voxels[5]).add(_intersectables.get(i));
                    }
                } else {
                    if (_intersectables.get(i).xCenter() > this.xCenter()) {
                        if (voxels[6] == null) { voxels[6] = new Geometries(); }
                        ((Geometries) voxels[6]).add(_intersectables.get(i));
                    } else {
                        if (voxels[7] == null) { voxels[7] = new Geometries(); }
                        ((Geometries) voxels[7]).add(_intersectables.get(i));
                    }
                }
            }
        }

        _intersectables.clear();
        for (int i = 0; i < 8; i++) {
            if (voxels[i] != null) {
                if (((Geometries) voxels[i])._intersectables.size() == 1) {
                    _intersectables.add(((Geometries) voxels[i])._intersectables.get(0));
                } else {
                    if (depthOfHierarchy > 1) { ((Geometries) voxels[i]).createHierarchyRec(depthOfHierarchy - 1); }
                    _intersectables.add(voxels[i]);
                }
            }
        }
    }
}
