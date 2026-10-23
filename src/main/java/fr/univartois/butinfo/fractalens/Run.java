package fr.univartois.butinfo.fractalens;
import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.sequence.EnumComplexSequence;
import fr.univartois.butinfo.fractalens.generator.FractalGenerator;
import fr.univartois.butinfo.fractalens.generator.FractalGeneratorComposite;
import fr.univartois.butinfo.fractalens.image.EnumPalette;
import picocli.CommandLine;
import picocli.CommandLine.Option;

@CommandLine.Command(name = "fractal", mixinStandardHelpOptions = true)
public class Run implements Runnable{
    @Option(names = {"-f", "--fractal-name"}, description = "Julia,Mandlebrot,Tricorn,BurningShip")
    private EnumComplexSequence name;
    @Option(names = {"-n", "--nb-iterations"}, description = "Maximal number of iterations")
    private int maxIteration;
    @Option(names = {"-h", "--height"}, description = "Height of the image")
    private int height;
    @Option(names = {"-w", "--width"}, description = "Width of the image")
    private int width;
    @Option(names = {"-s", "--scale"}, description = "The zoom")
    private double scale;
    @Option(names = {"-x", "--focus-x"}, description = "Real part of the center of the image")
    private double xCenter;
    @Option(names = {"-y", "--focus-y"}, description = "Imaginary part of the center of the image")
    private double yCenter;
    @Option(names = {"-p", "--palette-name"}, description = "Name of the palette")
    private EnumPalette paletteName;
    @Option(names = {"-t", "--type"}, description = "Unique, Translation or Zoom")
    private String type;
    @Option(names = {"-i", "--image-count"}, description = "Number of image in case of an animation")
    private int imageCount;
    @Option(names = {"-o", "--output"}, description = "Name of the file for the fractal")
    private String outputFile;
    @Option(names = {"-a", "--reel"}, description = "Real part for z0 the sequence")
    private double a;
    @Option(names = {"-b", "--imaginary"}, description = "Imaginary part for z0 the sequence")
    private double b;
    /**@Option(names = {"-cR", "--cReel"}, description = "Real part for constante of the sequence")
    private double cA;
    @Option(names = {"-cI", "--cImaginary"}, description = "Imaginary part for constante of the sequence")
    private double cB;*/

    @Override
    public void run() {
        System.out.println("aaaaaaaaaaa");
        if (type.equals("Zoom")){
            FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                    .withCenter(new Complex(xCenter, yCenter))
                    .withMaxIterations(maxIteration)
                    .withPalette(paletteName.create(maxIteration))
                    .withSequenceFactory(name.getFunction(new Complex(a, b)))
                    .withOutputFile(outputFile)
                    .withSize(width,height)
                    .withZoom(scale);
            FractalGeneratorComposite generator = builder.zoomAnimation(imageCount,scale,90);
            generator.generateImage();
            generator.saveImage();
        }
        else if(type.equals("Translation")){
                FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                        .withCenter(new Complex(xCenter, yCenter))
                        .withMaxIterations(maxIteration)
                        .withPalette(paletteName.create(maxIteration))
                        .withSequenceFactory(name.getFunction(new Complex(a, b)))
                        .withOutputFile(outputFile)
                        .withSize(width,height)
                        .withZoom(scale);
            FractalGeneratorComposite generator = builder.buildSpin(name,new Complex(a, b),imageCount,90,outputFile);
            generator.generateImage();
                generator.saveImage();
            }
        else{
            FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                    .withCenter(new Complex(xCenter, yCenter))
                    .withMaxIterations(maxIteration)
                    .withPalette(paletteName.create(maxIteration))
                    .withSequenceFactory(name.getFunction(new Complex(a, b)))
                    .withOutputFile(outputFile)
                    .withSize(width,height)
                    .withZoom(scale);
            FractalGenerator generator = builder.build();
            generator.generateImage();
            generator.saveImage();
        }
    }

    public static void main(String[] args) {
        new CommandLine(new Run()).execute(args);
    }

}
