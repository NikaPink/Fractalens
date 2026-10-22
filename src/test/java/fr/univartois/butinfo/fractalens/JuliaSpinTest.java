package fr.univartois.butinfo.fractalens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;
import fr.univartois.butinfo.fractalens.complex.sequence.EnumComplexSequence;
import fr.univartois.butinfo.fractalens.generator.FractalGeneratorComposite;
import fr.univartois.butinfo.fractalens.generator.IFractalGenerator;
import fr.univartois.butinfo.fractalens.image.IPalette;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * The JuliaSpinTest tests {@link FractalGeneratorBuilder#buildSpin}, which creates one
 * frame per angle theta, the constant c being multiplied by cos(theta) + i sin(theta).
 */
class JuliaSpinTest {

    /**
     * The maximum number of iterations (at most 255 to be encoded in a color).
     */
    private static final int MAX_ITERATIONS = 50;

    /**
     * The constant c used in the tests.
     */
    private static final IComplex C = new Complex(0.285, 0.01);

    /**
     * A palette encoding the number of iterations in the color, so that two pixels
     * have the same color if and only if they need the same number of iterations.
     */
    private static final IPalette ITERATIONS_PALETTE = iter -> new Color(iter, iter, iter);

    /**
     * Creates a builder with the configuration shared by all the tests.
     *
     * @return The configured builder.
     */
    private static FractalGeneratorBuilder createBuilder() {
        return new FractalGeneratorBuilder()
                .withSize(800, 800)
                .withZoom(0.005)
                .withMaxIterations(MAX_ITERATIONS)
                .withPalette(ITERATIONS_PALETTE);
    }

    /**
     * Creates the generator of a classic Julia fractal for the given constant.
     *
     * @param c The constant of Julia's sequence.
     *
     * @return The created generator.
     */
    private static IFractalGenerator createJulia(IComplex c) {
        return createBuilder()
                .withSequenceFactory(EnumComplexSequence.JULIA.getFunction(c))
                .build();
    }

    /**
     * Computes the colors of a sample of pixels of the given generator.
     *
     * @param generator The generator to sample.
     *
     * @return The colors of the sampled pixels.
     */
    private static List<Color> samplePixels(IFractalGenerator generator) {
        List<Color> colors = new ArrayList<>();
        for (int row = 0; row < 800; row += 40) {
            for (int column = 0; column < 800; column += 40) {
                colors.add(generator.computePixelColor(row, column));
            }
        }
        return colors;
    }

    /**
     * Checks that the composite contains one generator per frame.
     */
    @Test
    void testNumberOfFrames() {
        FractalGeneratorComposite spin = createBuilder()
                .buildSpin(EnumComplexSequence.JULIA, C, 12, 50, "spin.gif");

        assertEquals(12, spin.getGenerators().size());
    }

    /**
     * Checks that the first frame (theta = 0) is the classic Julia fractal.
     */
    @Test
    void testFirstFrameIsNotRotated() {
        FractalGeneratorComposite spin = createBuilder()
                .buildSpin(EnumComplexSequence.JULIA, C, 4, 50, "spin.gif");

        assertEquals(samplePixels(createJulia(C)),
                samplePixels(spin.getGenerators().get(0)));
    }

    /**
     * Checks that each frame uses c * (cos(theta) + i sin(theta)), with 4 frames
     * (theta = 0, PI/2, PI and 3PI/2, i.e. c, ic, -c and -ic).
     */
    @Test
    void testFramesUseRotatedConstant() {
        FractalGeneratorComposite spin = createBuilder()
                .buildSpin(EnumComplexSequence.JULIA, C, 4, 50, "spin.gif");
        List<IComplex> expected = List.of(
                C,
                C.multiply(new Complex(0, 1)),
                C.multiply(new Complex(-1, 0)),
                C.multiply(new Complex(0, -1)));

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(samplePixels(createJulia(expected.get(i))),
                    samplePixels(spin.getGenerators().get(i)),
                    "Frame " + i + " does not use the expected constant.");
        }
    }

    /**
     * Checks that the frames are actually different from each other.
     */
    @Test
    void testFramesAreDifferent() {
        FractalGeneratorComposite spin = createBuilder()
                .buildSpin(EnumComplexSequence.JULIA, C, 4, 50, "spin.gif");

        assertNotEquals(samplePixels(spin.getGenerators().get(0)),
                samplePixels(spin.getGenerators().get(1)));
    }

    /**
     * Checks that a sequence which does not use c cannot be animated.
     */
    @Test
    void testSpinThrowsWhenNotRotatable() {
        FractalGeneratorBuilder builder = createBuilder();

        assertThrows(IllegalArgumentException.class,
                () -> builder.buildSpin(EnumComplexSequence.MANDELBROT, C, 4, 50, "spin.gif"));
    }

}
