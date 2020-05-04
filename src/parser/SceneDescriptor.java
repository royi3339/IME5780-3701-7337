package parser;

import java.util.List;
import java.util.Map;

public class SceneDescriptor {

    private Map<String, String> _sceneAttributes;
    private Map<String, String> _cameraAttributes;
    private Map<String, String> _ambientLightAttributes;
    private List<Map<String, String>> _spheres;
    private List<Map<String, String>> _triangles;

    public void InitializeFromXMLstring(String xmlText) { System.out.println("change");}
}
