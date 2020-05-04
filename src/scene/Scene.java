package scene;

import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

public class Scene {
    private String _name;
    private Color _background;
    private elements.AmbientLight _ambientLight;
    private Geometries _geometries;
    private elements.Camera _camera;
    private double _distance;

    public Scene(String name) {
        _name = name;
        _geometries = new Geometries();
    }

    public Color getBackground() { return _background; }

    public String getName() { return _name; }

    public elements.AmbientLight getAmbientLight() { return _ambientLight; }

    public Geometries getGeometries() { return _geometries; }

    public elements.Camera getCamera() { return _camera; }

    public double getDistance() { return _distance; }

    public void setBackground(Color background) { this._background = background; }

    public void setAmbientLight(elements.AmbientLight ambientLight) { this._ambientLight = ambientLight; }

    public void setCamera(elements.Camera camera) { this._camera = camera; }

    public void setDistance(double distance) { this._distance = distance; }

    public void addGeometries(Intersectable... geometries) { this._geometries.add(geometries); }
}
