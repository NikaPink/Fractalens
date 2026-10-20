package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.complex.*;
import fr.univartois.butinfo.fractalens.complex.sequence.*;
import fr.univartois.butinfo.fractalens.generator.*;
import fr.univartois.butinfo.fractalens.image.*;

import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {

        int maxIterations = 100;
        double zoomFactor = 1.0;
        Complex translation = new Complex(0, 0);

        //création du plan zoomé et déplacé
        ComplexPlane plan = new ComplexPlane(600, 800);
        ZoomableComplexPlane planZoom = new ZoomableComplexPlane(plan, zoomFactor);
        TranslationComplexPlane translatePlan = new TranslationComplexPlane(planZoom,translation);

        IPalette palette = new GreyPalette(maxIterations);

        BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory =
                (z0, maxIter) -> new ComplexSequence(z0, new Complex(-0.4, 0.6), maxIter);
        FractalGenerator generator = new FractalGenerator(planZoom,palette,sequenceFactory,maxIterations,"julia.png");

        //génération de la fractale et sauvegarde
        generator.generateImage();
        generator.saveImage();
    }
}
