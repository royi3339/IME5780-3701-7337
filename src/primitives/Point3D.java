package primitives;

/**
 * implements the Point3D class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Point3D {
    private Coordinate _x;
    private Coordinate _y;
    private Coordinate _z;

    public static final Point3D ZERO = new Point3D(0, 0, 0);

    /**
     * <b> Point3D constructor. </b>
     *
     * @param x <b> the first {@link Coordinate} of the Point3D </b>
     * @param y <b> the second {@link Coordinate} of the Point3D </b>
     * @param z <b> the third {@link Coordinate} of the Point3D </b>
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        _x = new Coordinate(x.get());
        _y = new Coordinate(y.get());
        _z = new Coordinate(z.get());
    }

    /**
     * <b> Point3D constructor. </b>
     *
     * @param x <b> the first double </b>
     * @param y <b> the second double </b>
     * @param z <b> the third double </b>
     */
    public Point3D(double x, double y, double z) {
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }

    /**
     * <b> Point3D copy constructor. </b>
     *
     * @param p <b> the Point3D </b>
     */
    public Point3D(Point3D p) {
        _x = new Coordinate(p._x);
        _y = new Coordinate(p._y);
        _z = new Coordinate(p._z);
    }

    /**
     * @return double <b> x </b>
     */
    public double getX() { return _x.get(); }

    /**
     * @return double <b> y </b>
     */
    public double getY() { return _y.get(); }

    /**
     * @return double <b> z </b>
     */
    public double getZ() { return _z.get(); }

    /**
     * @param other <b> the Point3D that will be subtract </b>
     * @return {@link Vector} <b> the subtracted {@link Vector} </b>
     */
    public Vector subtract(Point3D other) {
        return new Vector(_x.get() - other.getX(), _y.get() - other.getY(), _z.get() - other.getZ());
    }

    /**
     * @param v <b> the {@link Vector} that will be add </b>
     * @return Point3D <b> added Point3D </b>
     */
    public Point3D add(Vector v) {
        return new Point3D(_x.get() + v.getPointX(), _y.get() + v.getPointY(), _z.get() + v.getPointZ());
    }

    /**
     * @param p <b> the other Point3D </b>
     * @return double <b> squared distance </b>
     */
    public double distanceSquared(Point3D p) {
        double dx = (p.getX() - this.getX());
        double dy = (p.getY() - this.getY());
        double dz = (p.getZ() - this.getZ());
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * @param p <b> the other Point3D </b>
     * @return double <b> distance </b>
     */
    public double distance(Point3D p) { return Math.sqrt(distanceSquared(p)); }

    /**
     * @param obj <b> the other Object </b>
     * @return boolean <b> true / false </b>
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point3D)) return false;
        Point3D oth = (Point3D) obj;
        return _x.equals(oth._x) && _y.equals(oth._y) && _z.equals(oth._z);
    }

    /**
     * @return String <b> the info </b>
     */
    @Override
    public String toString() {
        return "(X = " + getX() + ", Y = " + getY() + ", Z = " + getZ() + ")";
    }
}