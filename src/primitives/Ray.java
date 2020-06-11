package primitives;

import java.util.List;

/**
 * implements the Ray class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Ray {
    private Point3D _head;
    private Vector _direction;

    /**
     * the value of the checking range
     */
    private static final double DELTA = 0.1;
    private static final int NUM_OF_RAYS_IN_BEAM = 50;

    /**
     * <b> {@link Ray} constructor. </b>
     *
     * @param h <b> the head {@link Point3D} of the {@link Ray} </b>
     * @param d <b> the direction {@link Vector} of the {@link Ray} </b>
     */
    public Ray(Point3D h, Vector d) {
        _head = new Point3D(h);
        _direction = new Vector(d.normalized());
    }


    /**
     * <b> {@link Ray} copy constructor. </b>
     *
     * @param r <b> the {@link Ray} </b>
     */
    public Ray(Ray r) {
        this(r._head, r._direction);
    }

    /**
     * <b> {@link Ray} constructor. </b>
     *
     * @param h      <b> the head {@link Point3D} of the {@link Ray} </b>
     * @param d      <b> the direction {@link Vector} of the {@link Ray} </b>
     * @param normal <b> the normal {@link Vector} </b>>
     */
    public Ray(Point3D h, Vector d, Vector normal) {
        Vector delta = normal.normalized().scale(normal.dotProduct(d) > 0 ? DELTA : -DELTA);
        _head = h.add(delta);
        _direction = d;
    }

    /**
     * @return {@link Point3D} <b> head </b>
     */
    public Point3D getHead() {
        return _head;
    }

    /**
     * @return {@link Vector} <b> normalized direction {@link Vector} </b>
     */
    public Vector getDirection() {
        return _direction;
    }

    /**
     * @param obj <b> the other Object </b>
     * @return boolean <b> true / false </b>
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray oth = (Ray) obj;
        return _head.equals(oth._head) && _direction.equals(oth._direction);
    }

    /**
     * @return String <b> the info </b>
     */
    @Override
    public String toString() {
        return "Ray:\t" + "head = " + _head.toString() + ", direction = " + _direction.toString();
    }

    /**
     * @param t <b> the double of the size </b>
     * @return {@link Point3D} <b> the {@link Point3D} on the axis of {@link Ray} with the t distance </b>
     */
    public Point3D getPoint(double t) {
        Point3D p;
        Point3D p0 = _head;
        Vector v = _direction;
        p = p0.add(v.scale(t));
        return p;
    }

    public List<Ray> getRayBeam(double r) {
        Vector v = _direction;
        Vector vX = _direction.getOrthogonal().normalize();
        Vector vZ = v.crossProduct(vX).normalize();

        double d = 1;


        return null;
    }
}