package fr.univartois.butinfo.fractalens;

import fr.univartois.butinfo.fractalens.complex.*;
import fr.univartois.butinfo.fractalens.complex.sequence.*;
import fr.univartois.butinfo.fractalens.generator.*;
import fr.univartois.butinfo.fractalens.image.*;

import java.awt.*;
import java.util.List;
import java.util.function.BiFunction;


public class Main {



    public static void firstFractale(){
        int maxIterations = 250;
        Color c1 = new Color(255,255,0);
        Color c2 = new Color(255,0,0);
        IPalette palette = new GradientPalette(c1,c2,maxIterations);
        Complex c = new Complex(0.1, 0.6);


        FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                .withCenter(c)
                .withMaxIterations(maxIterations)
                .withPalette(palette)
                .withSequenceFactory(EnumComplexSequence.JULIA.getFunction(c))
                .withOutputFile("fractale.png")
                .withSize(800,800)
                .withZoom(0.001);
        FractalGenerator generator = builder.build();
        generator.generateImage();
        generator.saveImage();
    }

    public static void secondFractale(){
        int maxIterations = 250;
        Complex c = new Complex(0.285, 0.01);
        IPalette palette = new GradientPalette(new Color(180,57,12),new Color(4,7,58),maxIterations);
        IPalette paletteInv = new ComplementaryColor(palette);

        FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                .withCenter(c)
                .withMaxIterations(maxIterations)
                .withPalette(paletteInv)
                .withSequenceFactory(EnumComplexSequence.JULIA.getFunction(c))
                .withOutputFile("fractale_2.png")
                .withSize(800,800)
                .withZoom(0.001);
        FractalGenerator generator = builder.build();
        generator.generateImage();
        generator.saveImage();
    }

    public static void mandelbrotFractale() {
        int maxIterations = 250;
        IPalette palette = new GradientPalette(new Color(48, 95, 40), new Color(210, 175, 255), maxIterations);
        Complex c = new Complex(0, 0);

        FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                .withCenter(c)
                .withMaxIterations(maxIterations)
                .withPalette(palette)
                .withSequenceFactory(EnumComplexSequence.JULIA.getFunction(c))
                .withOutputFile("fractale_Mandelbrot.png")
                .withSize(600,800)
                .withZoom(0.001);
        FractalGenerator generator = builder.build();
        generator.generateImage();
        generator.saveImage();
    }

    public static void burningShipFractale() {
        int maxIterations = 64;
        Complex c = new Complex(-0.4, 0.6);

        IPalette palette = new GradientPalette(new Color(0, 0, 0), new Color(255, 140, 0), maxIterations);

        FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                .withCenter(c)
                .withMaxIterations(maxIterations)
                .withPalette(palette)
                .withSequenceFactory(EnumComplexSequence.BURNING_SHIP.getFunction(c))
                .withOutputFile("fractale_Burning_Ship.png")
                .withSize(800,800)
                .withZoom(0.00013);
        FractalGenerator generator = builder.build();
        generator.generateImage();
        generator.saveImage();
    }

    public static void snowFlakeFractale() {
        int maxIterations = 300;

        // Coordonnées corrigées pour taper exactement sur la pointe de la branche à 60°
        Complex c = new Complex(0.525, 0.90);
        // On utilise la FixedPalette avec ta liste cosmicPalette !
        // Son effet modulo garantit des anneaux de couleurs spectaculaires.
        IPalette palette = new FixedPalette(cosmicPalette);

        FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                .withCenter(c)
                .withMaxIterations(maxIterations)
                .withPalette(palette)
                .withSequenceFactory(EnumComplexSequence.BLACK_SNOWFLAKE.getFunction(c))
                .withOutputFile("fractale_Black_Snow_Flake.png")
                .withSize(800,800)
                .withZoom(0.00015);
        FractalGenerator generator = builder.build();
        generator.generateImage();
        generator.saveImage();
    }

    public static void main(String[] args) {
        firstFractale();
        secondFractale();
        mandelbrotFractale();
        burningShipFractale();
        snowFlakeFractale();
    }
}
