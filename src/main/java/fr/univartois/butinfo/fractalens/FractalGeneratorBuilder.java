package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.complex.*;
import fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence;
import fr.univartois.butinfo.fractalens.generator.FractalGenerator;
import fr.univartois.butinfo.fractalens.image.IPalette;

import java.util.function.BiFunction;

public class FractalGeneratorBuilder {
    private int width = 800;
    private int height = 800;
    private double zoom = 1.0;
    private Complex center = new Complex(0, 0);
    private int maxIterations = 100;
    private IPalette palette;
    private BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory;
    private String outputFile = "fractale.png";

    public FractalGeneratorBuilder withSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public FractalGeneratorBuilder withZoom(double zoom) {
        this.zoom = zoom;
        return this;
    }

    public FractalGeneratorBuilder withCenter(Complex center) {
        this.center = center;
        return this;
    }

    public FractalGeneratorBuilder withMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    public FractalGeneratorBuilder withPalette(IPalette palette) {
        this.palette = palette;
        return this;
    }

    public FractalGeneratorBuilder withSequenceFactory(BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory) {
        this.sequenceFactory = sequenceFactory;
        return this;
    }

    public FractalGeneratorBuilder withOutputFile(String outputFile) {
        this.outputFile = outputFile;
        return this;
    }

    public FractalGenerator build() {
        if (palette == null || sequenceFactory == null) {
            throw new IllegalStateException("La palette et la séquence doivent être configurées.");
        }

        ComplexPlane plan = new ComplexPlane(height, width);
        ZoomableComplexPlane planZoom = new ZoomableComplexPlane(plan, zoom);
        TranslationComplexPlane translatePlan = new TranslationComplexPlane(planZoom, center);

        return new FractalGenerator(translatePlan, palette, sequenceFactory, maxIterations, outputFile);
    }
}
