package scene;

import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

/**
 * implements the Scene class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Scene {
    private String _name;
    private Color _background;
    private elements.AmbientLight _ambientLight;
    private Geometries _geometries;
    private elements.Camera _camera;
    private double _distance;

    /**
     * <b> Scene constructor. </b>
     *
     * @param name <b> of the Scene </b>
     */
    public Scene(String name) {
        _name = name;
        _geometries = new Geometries();
    }

    /**
     * @return {@link Color} <b> of the background </b>
     */
    public Color getBackground() { return _background; }

    /**
     * @return String <b> the name of the Scene </b>
     */
    public String getName() { return _name; }

    /**
     * @return AmbientLight <b> the ambient light of te Scene </b>
     */
    public elements.AmbientLight getAmbientLight() { return _ambientLight; }

    /**
     * @return {@link Geometries} <b> the {@link Geometries} objects of the Scene </b>
     */
    public Geometries getGeometries() { return _geometries; }

    /**
     * @return Camera <b> the Camera of the Scene </b>
     */
    public elements.Camera getCamera() { return _camera; }

    /**
     * @return double <b> the distance value of the view plane from the Camera </b>
     */
    public double getDistance() { return _distance; }

    /**
     * @param background <b> the new {@link Color} we want to be at the background </b>
     */
    public void setBackground(Color background) { this._background = background; }

    /**
     * @param ambientLight <b> the new AmbientLight </b>
     */
    public void setAmbientLight(elements.AmbientLight ambientLight) { this._ambientLight = ambientLight; }

    /**
     * @param camera <b> the new Camera </b>
     */
    public void setCamera(elements.Camera camera) { this._camera = camera; }

    /**
     * @param distance <b> the new distance value of the view plane from the Camera </b>
     */
    public void setDistance(double distance) { this._distance = distance; }

    /**
     * adding a List of geometries to the Scene
     *
     * @param geometries <b> a List of {@link Intersectable} </b>
     */
    public void addGeometries(Intersectable... geometries) { this._geometries.add(geometries); }
}
