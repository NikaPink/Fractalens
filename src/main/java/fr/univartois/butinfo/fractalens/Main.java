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
        FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                .withCenter(new Complex(0.1, 0.6))
                .withMaxIterations(250)
                .withPalette(EnumPalette.AURORA_PALETTE.create(250))
                .withSequenceFactory(EnumComplexSequence.JULIA.getFunction(new Complex(0.1, 0.6)))
                .withOutputFile("fractale_1.png")
                .withSize(800,800)
                .withZoom(0.001);
        FractalGenerator generator = builder.build();
        generator.generateImage();
        generator.saveImage();
    }

    public static void secondFractale(){

        FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                .withCenter(new Complex(-0.47, -0.21))
                .withMaxIterations(250)
                .withPalette(EnumPalette.GRADIENT_PINK_RED_PALETTE.create(250))
                .withSequenceFactory(EnumComplexSequence.JULIA.getFunction(new Complex(0.285, 0.01)))
                .withOutputFile("fractale_2.png")
                .withSize(800,800)
                .withZoom(0.001);
        FractalGenerator generator = builder.build();
        generator.generateImage();
        generator.saveImage();
    }

    public static void mandelbrotFractale() {

        FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                .withCenter(new Complex(-0.75, 0.0))
                .withMaxIterations(250)
                .withPalette(EnumPalette.GRADIENT_ROUGE_NOIR_PALETTE.create(250))
                .withSequenceFactory(EnumComplexSequence.MANDELBROT.getFunction(new Complex(0.0, 0.0)))
                .withOutputFile("fractale_Mandelbrot.png")
                .withSize(800,600)
                .withZoom(0.004);
        FractalGenerator generator = builder.build();
        generator.generateImage();
        generator.saveImage();
    }

    public static void burningShipFractale() {
        FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                .withCenter(new Complex(-1.755, -0.03))
                .withMaxIterations(64)
                .withPalette(EnumPalette.GRADIENT_NOIR_VERT_PALETTE.create(64))
                .withSequenceFactory(EnumComplexSequence.BURNING_SHIP.getFunction(new Complex(-0.4, 0.6)))
                .withOutputFile("fractale_Burning_Ship.png")
                .withSize(800,800)
                .withZoom(0.00013);
        FractalGenerator generator = builder.build();
        generator.generateImage();
        generator.saveImage();
    }

    public static void snowFlakeFractale() {
        Complex c = new Complex(0.525, 0.90);

        FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                .withCenter(new Complex(0.525, 0.90))
                .withMaxIterations(250)
                .withPalette(EnumPalette.COSMIC_PALETTE.create(250))
                .withSequenceFactory(EnumComplexSequence.BLACK_SNOWFLAKE.getFunction(new Complex(0.525, 0.90)))
                .withOutputFile("fractale_Black_Snow_Flake.png")
                .withSize(800,800)
                .withZoom(0.00015);
        FractalGenerator generator = builder.build();
        generator.generateImage();
        generator.saveImage();
    }


    public static void firstFractaleZoom(){
        FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                .withCenter(new Complex(-0.743643887037151, 0.131825904205330))
                .withMaxIterations(250)
                .withPalette(EnumPalette.AURORA_PALETTE.create(250))
                .withSequenceFactory(EnumComplexSequence.MANDELBROT.getFunction(new Complex(0.1, 0.6)))
                .withOutputFile("fractale_1.gif")
                .withSize(800,800)
                .withZoom(0.001);
        FractalGeneratorComposite generator = builder.zoomAnimation(100,0.9,90);
        generator.generateImage();
        generator.saveImage();
    }

    public static void firstFractaleSpin(){
        FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                .withCenter(new Complex(0, 0))
                .withMaxIterations(250)
                .withPalette(EnumPalette.COSMIC_PALETTE.create(250))
                .withSequenceFactory(EnumComplexSequence.JULIA.getFunction(new Complex(0.7885, 0)))
                .withOutputFile("fractale_2.gif")
                .withSize(800,800)
                .withZoom(0.004);
        FractalGeneratorComposite generator = builder.buildSpin(EnumComplexSequence.JULIA,new Complex(0.7885, 0),100,90,"fractale_2.gif");
        generator.generateImage();
        generator.saveImage();
    }

    public static void main(String[] args) {
        firstFractaleZoom();
        firstFractaleSpin();
    }
}
