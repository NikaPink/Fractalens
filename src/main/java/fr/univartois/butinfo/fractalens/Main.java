package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.complex.*;
import fr.univartois.butinfo.fractalens.complex.sequence.*;
import fr.univartois.butinfo.fractalens.generator.*;
import fr.univartois.butinfo.fractalens.image.*;

import java.awt.*;
import java.util.List;
import java.util.function.BiFunction;

public class Main {

    static List<Color> cosmicPalette = List.of(
            new Color(4, 4, 20),      // 0: Vide abyssal (presque noir)
            new Color(20, 10, 50),    // 1: Violet très sombre
            new Color(55, 15, 90),    // 2: Prune profond
            new Color(100, 20, 110),  // 3: Magenta foncé
            new Color(160, 30, 110),  // 4: Rose framboise
            new Color(220, 50, 90),   // 5: Rose vif
            new Color(250, 100, 60),  // 6: Orange corail
            new Color(255, 170, 40),  // 7: Jaune doré
            new Color(255, 230, 100), // 8: Jaune clair
            new Color(255, 255, 220), // 9: Blanc éclatant (cœur lumineux)
            new Color(180, 240, 255), // 10: Cyan très clair
            new Color(100, 200, 255), // 11: Bleu ciel neon
            new Color(40, 140, 220),  // 12: Bleu azur
            new Color(15, 80, 170),   // 13: Bleu roi
            new Color(10, 40, 100),   // 14: Bleu sombre profond
            new Color(6, 20, 55)      // 15: Transition douce vers le noir
    );

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
                (z0, maxIter) -> new ComplexJuliaSequence(z0, c, maxIter);

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

        IPalette palette = new GradientPalette(new Color(180,57,12),new Color(4,7,58),maxIterations);

        //IPalette paletteInv= new MaskPalette(palette,true,true,false);

        IPalette paletteInv = new ComplementaryColor(palette);

        BiFunction<IComplex, Integer, IComplexSequence> sequenceFactory =
                (z0, maxIter) -> new ComplexJuliaSequence(z0, c, maxIter);

        //génération de la fractale et sauvegarde
        FractalGenerator generator = new FractalGenerator(translatePlan,paletteInv,sequenceFactory,maxIterations,"fractale_2.png");
        generator.generateImage();
        generator.saveImage();
    }


    public static void main(String[] args) {
        firstFractale();
        secondFractale();

    }
}
