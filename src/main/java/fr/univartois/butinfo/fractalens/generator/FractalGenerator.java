package fr.univartois.butinfo.fractalens.generator;

import fr.univartois.butinfo.fractalens.complex.IComplex;
import fr.univartois.butinfo.fractalens.complex.IComplexPlane;
import fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence;
import fr.univartois.butinfo.fractalens.image.BufferedImageAdapter;
import fr.univartois.butinfo.fractalens.image.IFractalImage;
import fr.univartois.butinfo.fractalens.image.IPalette;

import java.awt.Color;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.function.BiFunction;

/**
 * The FractalGenerator class implements the IFractalGenerator interface and provides
 * methods for generating and saving fractal images.
 *
 * @author FractaLens Team
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
    private final IFractalImage image;
    private final IComplexPlane plane;
    private final IPalette palette;
    private final BiFunction<IComplex,Integer, IComplexSequence> sequence;
    private final int maxIterations;
    private final String path;

    /**
     * Constructs a new FractalGenerator with the specified parameters.
     *
     * @param plane The complex plane to use.
     * @param palette The color palette.
     * @param sequence The sequence factory function.
     * @param maxIterations The maximum number of iterations.
     * @param path The file path for the output image.
     */
    public FractalGenerator(IComplexPlane plane, IPalette palette, BiFunction<IComplex, Integer, IComplexSequence> sequence, int maxIterations, String path) {
        this.image = new BufferedImageAdapter(800, 800);
        this.plane = plane;
        this.palette = palette;
        this.sequence = sequence;
        this.maxIterations = maxIterations;
        this.path = path;
    }

    /**
     * Generates the fractal image by computing the color for each pixel.
     *
     * @return The generated fractal image.
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
     * Computes the color of a pixel in the fractal image.
     *
     * @param row The row of the pixel.
     * @param column The column of the pixel.
     *
     * @return The computed color for the pixel.
     */
    @Override
    public Color computePixelColor(int row, int column) {
        IComplex z0 = plane.asComplex(row, column);
        IComplexSequence seq = sequence.apply(z0, maxIterations);

        int iterations = 0;
        var iterator = seq.iterator();

        // On utilise l'itérateur explicitement pour éviter d'avoir une variable "z" inutilisée
        while (iterator.hasNext()) {
            iterator.next();
            iterations++;
        }

        return palette.getColor(iterations);
    }

    /**
     * Saves the fractal image to a file.
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
