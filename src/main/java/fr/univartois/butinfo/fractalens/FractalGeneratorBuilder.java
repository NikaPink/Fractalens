package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.complex.*;
import fr.univartois.butinfo.fractalens.complex.sequence.EnumComplexSequence;
import fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence;
import fr.univartois.butinfo.fractalens.generator.FractalGenerator;
import fr.univartois.butinfo.fractalens.generator.FractalGeneratorComposite;
import fr.univartois.butinfo.fractalens.image.IPalette;

import java.util.function.BiFunction;

/**
 * The FractalGeneratorBuilder class implements the Builder design pattern
 * to facilitate the configuration and instantiation of a FractalGenerator.
 * It provides a fluent interface to set up the fractal's visual and mathematical properties.
 *
 * @author TonNom
 *
 * @version 0.1.0
 */
public class FractalGeneratorBuilder {

    /** The width of the generated image. */
    private int width = 800;

    /** The height of the generated image. */
    private int height = 800;

    /** The zoom factor applied to the complex plane. */
    private double zoom = 1.0;

    /** The central point of the generated complex plane. */
    private Complex center = new Complex(0, 0);

    /** The maximum number of iterations before divergence. */
    private int maxIterations = 100;

    /** The color palette used to render the fractal. */
    private IPalette palette;

    /** The factory used to instantiate the complex sequence for each pixel. */
    private BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory;

    /** The name of the output image file. */
    private String outputFile = "fractale.png";

    /** The composite*/
    private FractalGeneratorComposite composite;
    /**
     * Sets the dimensions of the generated image.
     *
     * @param width The width of the image.
     * @param height The height of the image.
     *
     * @return The current builder instance for method chaining.
     */
    public FractalGeneratorBuilder withSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Sets the zoom factor for the fractal generation.
     *
     * @param zoom The zoom factor to apply.
     *
     * @return The current builder instance for method chaining.
     */
    public FractalGeneratorBuilder withZoom(double zoom) {
        this.zoom = zoom;
        return this;
    }

    /**
     * Sets the central point of the fractal's view.
     *
     * @param center The complex number representing the center of the plane.
     *
     * @return The current builder instance for method chaining.
     */
    public FractalGeneratorBuilder withCenter(Complex center) {
        this.center = center;
        return this;
    }

    /**
     * Sets the maximum number of iterations for the fractal sequence.
     *
     * @param maxIterations The maximum number of iterations.
     *
     * @return The current builder instance for method chaining.
     */
    public FractalGeneratorBuilder withMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    /**
     * Sets the color palette used to render the fractal.
     *
     * @param palette The palette to apply.
     *
     * @return The current builder instance for method chaining.
     */
    public FractalGeneratorBuilder withPalette(IPalette palette) {
        this.palette = palette;
        return this;
    }

    /**
     * Sets the factory responsible for generating the mathematical sequence.
     *
     * @param sequenceFactory A BiFunction creating an IComplexSequence from an initial complex and a maximum iteration count.
     *
     * @return The current builder instance for method chaining.
     */
    public FractalGeneratorBuilder withSequenceFactory(BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory) {
        this.sequenceFactory = sequenceFactory;
        return this;
    }

    /**
     * Sets the output file name for the generated fractal image.
     *
     * @param outputFile The name or path of the output file.
     *
     * @return The current builder instance for method chaining.
     */
    public FractalGeneratorBuilder withOutputFile(String outputFile) {
        this.outputFile = outputFile;
        return this;
    }


    /**
     * Builds and returns the configured FractalGenerator.
     * This method orchestrates the creation of the underlying complex planes (base, zoom, and translation).
     *
     * @return A fully configured FractalGenerator ready to generate an image.
     *
     * @throws IllegalStateException If the palette or the sequence factory has not been configured.
     */
    public FractalGenerator build() {
        if (palette == null || sequenceFactory == null) {
            throw new IllegalStateException("La palette et la séquence doivent être configurées.");
        }

        // Assemblage des décorateurs pour le plan complexe
        ComplexPlane plan = new ComplexPlane(height, width);
        ZoomableComplexPlane planZoom = new ZoomableComplexPlane(plan, zoom);
        TranslationComplexPlane translatePlan = new TranslationComplexPlane(planZoom, center);

        return new FractalGenerator(translatePlan, palette, sequenceFactory, maxIterations, outputFile);
    }

    /**
     * Builds a composite generator for a zoom animation.
     *
     * @param nbFrames       Number of frames to generate (must be > 0).
     * @param zoomMultiplier Zoom multiplier applied at each frame (between 0 and 1).
     * @param delay          Delay between frames in milliseconds.
     * @return The configured FractalGeneratorComposite.
     */
    public FractalGeneratorComposite zoomAnimation(int nbFrames, double zoomMultiplier, int delay) {
        if (zoomMultiplier < 0 || zoomMultiplier > 1 || nbFrames <= 0) {
            throw new IllegalArgumentException("Invalid zoom multiplier or frame count.");
        }

        FractalGeneratorComposite composite = new FractalGeneratorComposite(delay, outputFile);
        double originalZoom = this.zoom;

        for (int i = 0; i < nbFrames; i++) {
            composite.addGenerator(this.build());
            this.zoom *= zoomMultiplier;
        }

        this.zoom = originalZoom;
        return composite;
    }

    /**
     * Builds an animated fractal where the constant c turns around the origin:
     * one frame per angle theta between 0 and 2*PI.
     *
     * @param sequence The sequence to animate (must use the constant c).
     * @param c The constant before rotation.
     * @param nbFrames The number of frames of the animation.
     * @param delay The delay in milliseconds between two frames.
     * @param gifPath The path of the output GIF file.
     *
     * @return A composite generating every frame of the animation.
     *
     * @throws IllegalArgumentException If the sequence does not use the constant c.
     */
    public FractalGeneratorComposite buildSpin(EnumComplexSequence sequence, IComplex c, int nbFrames, int delay, String gifPath){
        if (!sequence.isRotatable()) {
            throw new IllegalArgumentException("La séquence " + sequence + " n'utilise pas de constante c, elle ne peut pas tourner.");
        }
        FractalGeneratorComposite composite = new FractalGeneratorComposite(delay, gifPath);
        for(int i = 0; i < nbFrames; i++){
            double theta = 2*Math.PI*i/nbFrames;
            IComplex rotated = c.multiply(new Complex(Math.cos(theta), Math.sin(theta)));
            withSequenceFactory(sequence.getFunction(rotated));
            composite.addGenerator(build());
        }
        return composite;
    }
}
