package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.complex.*;
import fr.univartois.butinfo.fractalens.complex.sequence.*;
import fr.univartois.butinfo.fractalens.generator.*;
import fr.univartois.butinfo.fractalens.image.*;

import java.awt.*;
import java.util.function.BiFunction;

public class Main {

    public static void firstFractale(){
        int maxIterations = 250;
        Complex c = new Complex(-0.4, 0.6);
        double zoomFactor = 0.001;
        Complex translation = new Complex(0.1, 0.6);

        //création du plan zoomé et déplacé
        ComplexPlane plan = new ComplexPlane(600, 800);
        ZoomableComplexPlane planZoom = new ZoomableComplexPlane(plan, zoomFactor);
        TranslationComplexPlane translatePlan = new TranslationComplexPlane(planZoom,translation);

        Color c1 = new Color(255,255,0);
        Color c2 = new Color(255,0,0);
        IPalette palette = new GradientPalette(c1,c2,maxIterations);

        BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory =
                (z0, maxIter) -> new ComplexSequence(z0, c, maxIter);

        //génération de la fractale et sauvegarde
        FractalGenerator generator = new FractalGenerator(translatePlan,palette,sequenceFactory,maxIterations,"fractale_1.png");
        generator.generateImage();
        generator.saveImage();
    }

    public static void secondFractale(){
        int maxIterations = 250;
        Complex c = new Complex(0.285, 0.01);
        double zoomFactor = 0.001;
        Complex translation = new Complex(-0.47, -0.21);

        //création du plan zoomé et déplacé
        ComplexPlane plan = new ComplexPlane(600, 800);
        ZoomableComplexPlane planZoom = new ZoomableComplexPlane(plan, zoomFactor);
        TranslationComplexPlane translatePlan = new TranslationComplexPlane(planZoom,translation);

        IPalette palette = new GreyPalette(maxIterations);

        BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory =
                (z0, maxIter) -> new ComplexSequence(z0, c, maxIter);

        //génération de la fractale et sauvegarde
        FractalGenerator generator = new FractalGenerator(translatePlan,palette,sequenceFactory,maxIterations,"fractale_2.png");
        generator.generateImage();
        generator.saveImage();
    }


    public static void main(String[] args) {
        firstFractale();
        secondFractale();

    }
}
