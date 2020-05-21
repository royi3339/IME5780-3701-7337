package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.isZero;

/**
 * implements the Camera class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Camera {
    private Point3D _p0;
    private Vector _vUp;
    private Vector _vTo;
    private Vector _vRight;

    /**
     * <b> Camera constructor. </b>
     *
     * @param l  <b> the location {@link Point3D} of the Camera </b>
     * @param up <b> the up {@link Vector} of the Camera </b>
     * @param to <b> the toward {@link Vector} of the Camera </b>
     */
    public Camera(Point3D l, Vector to, Vector up) {
        _p0 = new Point3D(l);
        if (!isZero(up.dotProduct(to)))
            throw new IllegalArgumentException("Error ! ! ! the Vectors not orthogonal !");
        _vUp = up.normalized();
        _vTo = to.normalized();
        _vRight = to.crossProduct(up).normalized();
    }

    /**
     * @return {@link Point3D} <b> the location {@link Point3D} of the Camera </b>
     */
    public Point3D getP() { return _p0; }

    /**
     * @return {@link Vector} <b> the up {@link Vector} of the Camera </b>
     */
    public Vector getVup() { return _vUp; }

    /**
     * @return {@link Vector} <b> the toward {@link Vector} of the Camera </b>
     */
    public Vector getVto() { return _vTo; }

    /**
     * @return {@link Vector} <b> the right {@link Vector} of the Camera </b>
     */
    public Vector getVright() { return _vRight; }

    /**
     * @param nX             <b> the size of the X axis </b>
     * @param nY             <b> the size of the Y axis </b>
     * @param j              <b> the value of the X index </b>
     * @param i              <b> the value of the Y index </b>
     * @param screenDistance <b> the distance from the {@link Camera} till the view plane </b>
     * @param screenWidth    <b> the width screen of the view plane </b>
     * @param screenHeight   <b> the height screen of the view plane </b>
     * @return {@link Ray} <b> the {@link Ray} that goes through the pixel </b>
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i, double screenDistance, double screenWidth, double screenHeight) {
        double rY = screenHeight / nY;
        double rX = screenWidth / nX;
        double yI = (i - (nY - 1) / 2d) * rY;
        double xJ = (j - (nX - 1) / 2d) * rX;
        Point3D p = _p0.add(_vTo.scale(screenDistance));
        if (!isZero(xJ)) { p = p.add(_vRight.scale(xJ)); }
        if (!isZero(yI)) { p = p.add(_vUp.scale(-yI)); }
        return new Ray(_p0, p.subtract(_p0));
    }
}
