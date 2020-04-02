package primitives;

/**
 * implements the Ray class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Ray {
    private Point3D _head;
    private Vector _direction;

    /**
     * <b> Ray constructor. </b>
     *
     * @param h <b> the head Point3D of the Ray </b>
     * @param d <b> the direction Vector of the Ray </b>
     */
    public Ray(Point3D h, Vector d) {
        _head = new Point3D(h);
        _direction = new Vector(d.normalized());
    }

    /**
     * <b> Ray copy constructor. </b>
     *
     * @param r <b> the Ray </b>
     */
    public Ray(Ray r) {
        _head = r._head;
        _direction = r._direction;
    }

    /**
     * @return Point3D <b> head </b>
     */
    public Point3D getHead() { return _head; }

    /**
     * @return Vector <b> direction </b>
     */
    public Vector getDirection() {return _direction;}

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
}