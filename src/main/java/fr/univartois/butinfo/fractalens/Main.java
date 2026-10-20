package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.complex.*;
import fr.univartois.butinfo.fractalens.complex.sequence.*;
import fr.univartois.butinfo.fractalens.generator.*;
import fr.univartois.butinfo.fractalens.image.*;

import java.util.function.BiFunction;

public class  Main {
    public static void main(String[] args) {

        int maxIterations = 100;

        ComplexPlane plan = new ComplexPlane(600, 800);
        IPalette palette = new GreyPalette(maxIterations);
        ZoomableComplexPlane planZoom = new ZoomableComplexPlane(plan, 0.004);

        BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory =
                (z0, maxIter) -> new ComplexSequence(z0, new Complex(-0.4, 0.6), maxIter);


        FractalGenerator generator = new FractalGenerator(planZoom,palette,sequenceFactory,maxIterations,"julia.png");
        generator.generateImage();
        generator.saveImage();
    }
}
