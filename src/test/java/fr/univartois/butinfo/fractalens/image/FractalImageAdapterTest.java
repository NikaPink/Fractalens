package fr.univartois.butinfo.fractalens.image;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * The FractalImageTest tests the adapter implementation of {@link IFractalImage}.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
class FractalImageAdapterTest {

    /**
     * Creates a new fractal image adapting the given {@link BufferedImage}.
     *
     * @param image The buffered image to adapt.
     *
     * @return The created fractal image.
     */
    private static IFractalImage createImage(BufferedImage image) {
        return new BufferedImageAdapter(image);
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.image.IFractalImage#getHeight()}.
     */
    @Test
    void testGetHeight() {
        BufferedImage buf1 = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        IFractalImage img1 = createImage(buf1);
        assertEquals(600, img1.getHeight());

        BufferedImage buf2 = new BufferedImage(100, 250, BufferedImage.TYPE_INT_RGB);
        IFractalImage img2 = createImage(buf2);
        assertEquals(250, img2.getHeight());

        BufferedImage buf3 = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        IFractalImage img3 = createImage(buf3);
        assertEquals(1, img3.getHeight());
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.image.IFractalImage#getWidth()}.
     */
    @Test
    void testGetWidth() {
        BufferedImage buf1 = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        IFractalImage img1 = createImage(buf1);
        assertEquals(800, img1.getWidth());

        BufferedImage buf2 = new BufferedImage(100, 250, BufferedImage.TYPE_INT_RGB);
        IFractalImage img2 = createImage(buf2);
        assertEquals(100, img2.getWidth());

        BufferedImage buf3 = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        IFractalImage img3 = createImage(buf3);
        assertEquals(1, img3.getWidth());
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.image.IFractalImage#setColor(int, int, java.awt.Color)}.
     */
    @Test
    void testSetColor() {
        BufferedImage bufferedImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        IFractalImage image = createImage(bufferedImage);

        image.setColor(0, 0, Color.RED);
        assertEquals(Color.RED.getRGB(), bufferedImage.getRGB(0, 0));

        image.setColor(0, 799, Color.GREEN);
        assertEquals(Color.GREEN.getRGB(), bufferedImage.getRGB(799, 0));

        image.setColor(599, 0, Color.BLUE);
        assertEquals(Color.BLUE.getRGB(), bufferedImage.getRGB(0, 599));

        image.setColor(599, 799, Color.WHITE);
        assertEquals(Color.WHITE.getRGB(), bufferedImage.getRGB(799, 599));

        Color customColor = new Color(42, 128, 200);
        image.setColor(150, 700, customColor);
        assertEquals(customColor.getRGB(), bufferedImage.getRGB(700, 150));
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.image.IFractalImage#saveAs(java.lang.String)}.
     *
     * @param tempDir The temporary directory used to store test files.
     *
     * @throws IOException If an I/O error occurs when writing or reading the file.
     */
    @Test
    void testSaveAs(@TempDir Path tempDir) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);
        IFractalImage image = createImage(bufferedImage);

        Color customColor = new Color(15, 240, 90);
        image.setColor(25, 175, customColor);

        File pngFile = tempDir.resolve("fractal.png").toFile();
        image.saveAs(pngFile.getAbsolutePath());

        assertTrue(pngFile.exists());
        assertTrue(pngFile.length() > 0);

        BufferedImage loadedPng = ImageIO.read(pngFile);
        assertNotNull(loadedPng);
        assertEquals(200, loadedPng.getWidth());
        assertEquals(100, loadedPng.getHeight());
        assertEquals(customColor.getRGB(), loadedPng.getRGB(175, 25));
    }

}
