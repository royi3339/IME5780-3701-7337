package unittests;

import org.junit.Test;
import renderer.ImageWriter;

import java.awt.*;

public class ImageWriterTests {

    @Test
    public void writeToImageTest() {
        ImageWriter image = new ImageWriter("blue", 1000, 1600, 800, 500);
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 500; j++) {
                if (i % 50 == 0 || j % 50 == 0) {
                    image.writePixel(i, j, new Color(45, 87, 100));
                } else {
                    image.writePixel(i, j, new Color(3, 3, 39));
                }
            }
        }
        image.writeToImage();
    }

}