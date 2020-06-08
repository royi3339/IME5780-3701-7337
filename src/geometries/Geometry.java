package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * implements the Geometry abstract class, which implements the {@link Intersectable} interface.
 *
 * @author Royi Alishayev idan darmoni
 */
public abstract class Geometry implements Intersectable {
    protected Color _emission;
    protected Material _material;


    /**
     * <b> {@link Geometry} with {@link Color} and {@link Material} constructor </b>
     *
     * @param emission <b> the emission {@link Color} of the {@link Geometry} </b>
     * @param material <b> the {@link Material} of the {@link Geometry} </b>
     */
    public Geometry(Color emission, Material material) {
        _emission = new Color(emission);
        _material = material;
    }

    /**
     * <b> {@link Geometry} with {@link Color} constructor </b>
     *
     * @param emission <b> the emission {@link Color} of the {@link Geometry} </b>
     */
    public Geometry(Color emission) { this(emission, new Material(0, 0, 0)); }

    /**
     * <b> {@link Geometry} default constructor </b>
     */
    public Geometry() { this(Color.BLACK); }

    /**
     * @param p <b> a {@link Point3D} on the {@link Geometry} object </b>
     * @return {@link Vector} <b> normal </b>
     */
    public abstract Vector getNormal(Point3D p);

    /**
     * @return {@link Color} <b> the emission </b>
     */
    public Color getEmission() { return _emission; }

    /**
     * @return {@link Material} <b> the {@link Material} of the {@link Geometry} </b>
     */
    public Material getMaterial() { return _material; }

    /**
     * setting the KR
     *
     * @param kr <b> the reflection factor of the {@link Material} </b>
     * @return {@link Geometry} <b> for concatenation </b>
     */
    public Geometry setKr(double kr) {
        _material.setKr(kr);
        return this;
    }

    /**
     * setting the KT
     *
     * @param kt <b> the transparency factor of the {@link Material} </b>
     * @return {@link Geometry} <b> for concatenation </b>
     */
    public Geometry setKt(double kt) {
        _material.setKt(kt);
        return this;
    }
}
