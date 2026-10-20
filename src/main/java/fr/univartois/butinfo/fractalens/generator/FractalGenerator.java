package fr.univartois.butinfo.fractalens.generator;

import fr.univartois.butinfo.fractalens.complex.IComplex;
import fr.univartois.butinfo.fractalens.complex.IComplexPlane;
import fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence;
import fr.univartois.butinfo.fractalens.image.IFractalImage;
import fr.univartois.butinfo.fractalens.image.IPalette;

import java.awt.Color;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.function.BiFunction;

/**
 * The FractalGenerator class implements the IFractalGenerator interface and provides
 * methods for generating and saving fractal images.
 */
public class FractalGenerator implements IFractalGenerator {

    /**
     * The image on which the fractal is drawn.
     * The plane is the complex plane.
     * The palette is the color palette.
     * The sequence is the sequence of complex numbers.
     * The maxIterations is the maximum number of iterations.
     * The path is the path to the image file.
     *
     */
    private IFractalImage image;
    private IComplexPlane plane;
    private IPalette palette;
    private BiFunction<IComplex,Integer, IComplexSequence> sequence;
    private int maxIterations;
    private String path;

    /**
     * Constructor.
     * @param image
     * @param plane
     * @param palette
     * @param sequence
     * @param maxIterations
     * @param path
     */
    public FractalGenerator(IFractalImage image, IComplexPlane plane, IPalette palette, BiFunction<IComplex,Integer, IComplexSequence> sequence, int maxIterations, String path) {
        this.image = image;
        this.plane = plane;
        this.palette = palette;
        this.sequence = sequence;
        this.maxIterations = maxIterations;
        this.path = path;
    }

    /**
     * Generate the fractal image.
     * @return
     */
    @Override
    public IFractalImage generateImage() {
        for (int row = 0; row < image.getHeight(); row++) {
            for (int column = 0; column < image.getWidth(); column++) {
                image.setColor(row, column, computePixelColor(row, column));
            }
        }
        return image;
    }

    /**
     * Compute the color of a pixel in the fractal image.
     * @param row The row of the pixel.
     * @param column The column of the pixel.
     *
     * @return
     */
    @Override
    public Color computePixelColor(int row, int column) {
        IComplex z0 = plane.asComplex(row, column);
        IComplexSequence seq = sequence.apply(z0, maxIterations);
        int iterations = 0;
        for (IComplex z : seq) {
            iterations++;
        }
        return palette.getColor(iterations);
    }

    /**
     * Save the fractal image to a file.
     */
    @Override
    public void saveImage() {
        try {
            image.saveAs(path);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
