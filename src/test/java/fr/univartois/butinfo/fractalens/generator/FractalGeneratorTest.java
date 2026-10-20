package fr.univartois.butinfo.fractalens.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.ComplexPlane;
import fr.univartois.butinfo.fractalens.complex.IComplex;
import fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequence;
import fr.univartois.butinfo.fractalens.image.BufferedImageAdapter;
import fr.univartois.butinfo.fractalens.image.IFractalImage;
import fr.univartois.butinfo.fractalens.image.IPalette;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FractalGeneratorTest {

    /**
     * Creates a new fractal generator for Julia's sequence with c = 0.
     * With this constant, the sequence does not diverge when |z0| <= 1.
     * The palette gives black when the sequence does not diverge, and white otherwise.
     *
     * @param image The image on which the fractal is drawn.
     * @param path The path of the file in which the image is saved.
     *
     * @return The created fractal generator.
     */
    private static IFractalGenerator createGenerator(IFractalImage image, String path) {
        IComplex c = new Complex(0, 0);
        IPalette palette = (max) -> (Color.getColor(n) == 0) ? Color.BLACK : Color.WHITE;
        return new FractalGenerator(image,
                new ComplexPlane(image.getHeight(), image.getWidth()), palette,
                (z0, nbTerms) -> new ComplexSequence(z0, c, nbTerms), 50, path);
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.generator.IFractalGenerator#computePixelColor(int, int)}.
     */
    @Test
    void testComputePixelColor() {
        IFractalImage image = new BufferedImageAdapter(6, 4);
        IFractalGenerator generator = createGenerator(image, "test.png");

        assertEquals(Color.BLACK, generator.computePixelColor(2, 3));

        assertEquals(Color.BLACK, generator.computePixelColor(2, 4));

        assertEquals(Color.WHITE, generator.computePixelColor(2, 5));

        assertEquals(Color.WHITE, generator.computePixelColor(0, 0));
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.generator.IFractalGenerator#generateImage()}.
     */
    @Test
    void testGenerateImage() {
        BufferedImage buf = new BufferedImage(6, 4, BufferedImage.TYPE_INT_RGB);
        IFractalGenerator generator = createGenerator(new BufferedImageAdapter(buf), "test.png");

        generator.generateImage();
        assertEquals(Color.BLACK.getRGB(), buf.getRGB(3, 2));
        assertEquals(Color.BLACK.getRGB(), buf.getRGB(4, 2));
        assertEquals(Color.WHITE.getRGB(), buf.getRGB(5, 2));
        assertEquals(Color.WHITE.getRGB(), buf.getRGB(0, 0));
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.generator.IFractalGenerator#saveImage()}.
     *
     * @param tempDir A temporary directory provided by JUnit.
     */
    @Test
    void testSaveImage(@TempDir Path tempDir) {
        File file = tempDir.resolve("fractal.png").toFile();
        IFractalGenerator generator = createGenerator(
                new BufferedImageAdapter(6, 4), file.getAbsolutePath());

        generator.generateImage();
        generator.saveImage();

        assertTrue(file.exists());
    }

}
