package scene;

import elements.LightSource;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;
import elements.AmbientLight;
import elements.Camera;


import java.util.LinkedList;
import java.util.List;

/**
 * implements the Scene class.
 *
 * @author Royi Alishayev idan darmoni
 */
public class Scene {
    private String _name;
    private Color _background;
    private AmbientLight _ambientLight;
    private Geometries _geometries;
    private elements.Camera _camera;
    private double _distance;

    private List<LightSource> _lights;

    /**
     * <b> {@link Scene} constructor. </b>
     *
     * @param name <b> of the {@link Scene} </b>
     */
    public Scene(String name) {
        _name = name;
        _geometries = new Geometries();
        _lights = new LinkedList<LightSource>();
    }

    /**
     * @return List<LightSource> <b> return the List of the {@link LightSource} </b>
     */
    public List<LightSource> getLights() { return _lights; }

    /**
     * adding a List of {@link LightSource} to the List<LightSource> of the {@link Scene}
     *
     * @param lights <b> a List of {@link LightSource} </b>
     */
    public void addLights(LightSource... lights) {
        for (LightSource light : lights) { _lights.add(light); }
    }

    /**
     * @return {@link Color} <b> of the background </b>
     */
    public Color getBackground() { return _background; }

    /**
     * @return String <b> the name of the {@link Scene} </b>
     */
    public String getName() { return _name; }

    /**
     * @return {@link AmbientLight} <b> the ambient light of the {@link Scene} </b>
     */
    public AmbientLight getAmbientLight() { return _ambientLight; }

    /**
     * @return {@link Geometries} <b> the {@link Geometries} objects of the {@link Scene} </b>
     */
    public Geometries getGeometries() { return _geometries; }

    /**
     * @return {@link Camera} <b> the {@link Camera} of the {@link Scene} </b>
     */
    public Camera getCamera() { return _camera; }

    /**
     * @return double <b> the distance value of the view plane from the {@link Camera} </b>
     */
    public double getDistance() { return _distance; }

    /**
     * @param background <b> the new {@link Color} we want to be at the background </b>
     */
    public void setBackground(Color background) { this._background = background; }

    /**
     * @param ambientLight <b> the new {@link AmbientLight} </b>
     */
    public void setAmbientLight(AmbientLight ambientLight) { this._ambientLight = ambientLight; }

    /**
     * @param camera <b> the new {@link Camera} </b>
     */
    public void setCamera(Camera camera) { this._camera = camera; }

    /**
     * @param distance <b> the new distance value of the view plane from the {@link Camera} </b>
     */
    public void setDistance(double distance) { this._distance = distance; }

    /**
     * adding a List of {@link Intersectable} to the {@link Scene}
     *
     * @param geometries <b> a List of {@link Intersectable} </b>
     */
    public void addGeometries(Intersectable... geometries) { this._geometries.add(geometries); }
}
