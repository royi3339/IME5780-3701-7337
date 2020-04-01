package primitives;

/**
 * implements the Vector class.
 *
 * @author Royi Alishayev
 */
public class Vector {
    private Point3D _head;

    /**
     * <b> Vector constructor. </b>
     *
     * @param x <b> the first Coordinate of the Vector </b>
     * @param y <b> the second Coordinate of the Vector </b>
     * @param z <b> the third Coordinate of the Vector </b>
     * @throws IllegalArgumentException if the Coordinate are a ZERO.
     */
    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        _head = new Point3D(x, y, z);
        if (_head.equals(Point3D.ZERO))
            throw new IllegalArgumentException("Error ! ! ! ZERO Vector entered");
    }

    /**
     * <b> Vector constructor. </b>
     *
     * @param x <b> the first double </b>
     * @param y <b> the second double </b>
     * @param z <b> the third double </b>
     * @throws IllegalArgumentException if the doubles are a ZERO.
     */
    public Vector(double x, double y, double z) {
        _head = new Point3D(x, y, z);
        if (_head.equals(Point3D.ZERO))
            throw new IllegalArgumentException("Error ! ! ! ZERO Vector entered");
    }

    /**
     * <b> Vector copy constructor. </b>
     *
     * @param p <b> the Point3D </b>
     * @throws IllegalArgumentException if the p Point3D is a ZERO.
     */
    public Vector(Point3D p) {
        _head = new Point3D(p.getX(), p.getY(), p.getZ());
        if (p.equals(Point3D.ZERO))
            throw new IllegalArgumentException("Error ! ! ! ZERO Vector entered");
    }

    /**
     * <b> Vector copy constructor. </b>
     *
     * @param v <b> the Vector </b>
     */
    public Vector(Vector v) { _head = new Point3D(v.getHead()); }


    /**
     * @return Point3D <b> head </b>
     */
    public Point3D getHead() { return _head; }

    /**
     * @return double <b> x </b>
     */
    public double getPointX() { return _head.getX(); }

    /**
     * @return double <b> y </b>
     */
    public double getPointY() { return _head.getY(); }

    /**
     * @return double <b> z </b>
     */
    public double getPointZ() { return _head.getZ(); }

    /**
     * @param v <b> the Vector that will be subtract </b>
     * @return Vector <b> subtracted Vector </b>
     */
    public Vector subtract(Vector v) {
        return new Vector(_head.getX() - v.getPointX(), _head.getY() - v.getPointY(), _head.getZ() - v.getPointZ());
    }

    /**
     * @param v <b> the Vector that will be add </b>
     * @return Vector <b> added Vector </b>
     */
    public Vector add(Vector v) {
        return new Vector(_head.getX() + v.getPointX(), _head.getY() + v.getPointY(), _head.getZ() + v.getPointZ());
    }

    /**
     * @param num <b> the double that will multiply the Vector </b>
     * @return Vector <b> multiplied Vector </b>
     */
    public Vector scale(double num) {
        return new Vector(num * _head.getX(), num * _head.getY(), num * _head.getZ());
    }

    /**
     * @param v <b> Vector </b>
     * @return double <b> the sum of 2 multiplied Vectors </b>
     */
    public double dotProduct(Vector v) {
        double x1 = _head.getX();
        double x2 = v.getPointX();
        double y1 = _head.getY();
        double y2 = v.getPointY();
        double z1 = _head.getZ();
        double z2 = v.getPointZ();
        return (x1 * x2) + (y1 * y2) + (z1 * z2);
    }

    /**
     * @param v <b> Vector </b>
     * @return Vector <b> the normal Vector, of 2 Vector </b>
     */
    public Vector crossProduct(Vector v) {
        double x1 = _head.getX();
        double x2 = v.getPointX();
        double y1 = _head.getY();
        double y2 = v.getPointY();
        double z1 = _head.getZ();
        double z2 = v.getPointZ();
        return new Vector(y1 * z2 - z1 * y2, -(x1 * z2 - z1 * x2), x1 * y2 - y1 * x2);
    }

    /**
     * @return double <b> squared length of the Vector </b>
     */
    public double lengthSquared() { return this.dotProduct(this); }

    /**
     * @return double <b> length of the Vector </b>
     */
    public double length() { return Math.sqrt(lengthSquared()); }

    /**
     * @return Vector <b> normalize the Vector himself </b>
     */
    public Vector normalize() {
        double d = 1 / (this.length());
        this._head = new Point3D(_head.getX() * d, _head.getY() * d, _head.getZ() * d);
        return this;
    }

    /**
     * @return Vector <b> normalize the Vector, without change the origin </b>
     */
    public Vector normalized() {
        return new Vector(this).normalize();
    }

    /**
     * @param obj <b> the other Object </b>
     * @return boolean <b> true / false </b>
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Vector)) return false;
        Vector oth = (Vector) obj;
        return _head.equals(oth._head);
    }

    /**
     * @return String <b> the info </b>
     */
    @Override
    public String toString() { return "Vector = " + _head.toString(); }
}