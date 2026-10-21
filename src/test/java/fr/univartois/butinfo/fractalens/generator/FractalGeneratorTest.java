package fr.univartois.butinfo.fractalens.generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.ComplexPlane;
import fr.univartois.butinfo.fractalens.complex.IComplex;
import fr.univartois.butinfo.fractalens.complex.sequence.ComplexSequence;
import fr.univartois.butinfo.fractalens.image.IPalette;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FractalGeneratorTest {

    /**
     * Creates a new fractal generator for Julia's sequence with c = 0.
     * With this constant, the sequence does not diverge when |z0| <= 1.
     * The palette gives black when the sequence does not diverge, and white otherwise.
     * The generator creates an image of 800 x 600 pixels, so the plane has the same size:
     * the pixel (row, column) is then the complex number (column - 400) + (300 - row)i.
     *
     * @param path The path of the file in which the image is saved.
     *
     * @return The created fractal generator.
     */
    private static IFractalGenerator createGenerator(String path) {
        IComplex c = new Complex(0, 0);
        int maxIterations = 50;
        IPalette palette = n -> (n == maxIterations) ? Color.BLACK : Color.WHITE;
        return new FractalGenerator(new ComplexPlane(600, 800), palette,
                (z0, nbTerms) -> new ComplexSequence(z0, c, nbTerms), maxIterations, path);
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.generator.IFractalGenerator#computePixelColor(int, int)}.
     */
    @Test
    void testComputePixelColor() {
        IFractalGenerator generator = createGenerator("test.png");

        assertEquals(Color.BLACK, generator.computePixelColor(300, 400));

        assertEquals(Color.BLACK, generator.computePixelColor(300, 401));

        assertEquals(Color.WHITE, generator.computePixelColor(300, 402));

        assertEquals(Color.WHITE, generator.computePixelColor(0, 0));
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.generator.IFractalGenerator#generateImage()}.
     * The image is created by the generator, so it is read back from the saved file.
     *
     * @param tempDir A temporary directory provided by JUnit.
     *
     * @throws IOException If the saved image cannot be read.
     */
    @Test
    void testGenerateImage(@TempDir Path tempDir) throws IOException {
        File file = tempDir.resolve("fractal.png").toFile();
        IFractalGenerator generator = createGenerator(file.getAbsolutePath());

        generator.generateImage();
        generator.saveImage();
        BufferedImage buf = ImageIO.read(file);

        assertEquals(Color.BLACK.getRGB(), buf.getRGB(400, 300));
        assertEquals(Color.BLACK.getRGB(), buf.getRGB(401, 300));
        assertEquals(Color.WHITE.getRGB(), buf.getRGB(402, 300));
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
        IFractalGenerator generator = createGenerator(file.getAbsolutePath());

        generator.generateImage();
        generator.saveImage();

        assertTrue(file.exists());
    }

}
