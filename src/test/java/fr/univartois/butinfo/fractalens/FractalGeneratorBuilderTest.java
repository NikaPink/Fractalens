package fr.univartois.butinfo.fractalens;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;
import fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence;
import fr.univartois.butinfo.fractalens.generator.FractalGenerator;
import fr.univartois.butinfo.fractalens.generator.FractalGeneratorComposite;
import fr.univartois.butinfo.fractalens.image.IPalette;
import java.awt.Color;
import java.util.function.BiFunction;
import org.junit.jupiter.api.Test;

/**
 * The FractalGeneratorBuilderTest tests the Builder pattern implementation
 * for configuring a FractalGenerator.
 *
 * @author TonNom
 *
 * @version 0.1.0
 */
class FractalGeneratorBuilderTest {

    /**
     * Test method verifying that the builder successfully creates a FractalGenerator
     * when all parameters are provided.
     */
    @Test
    void testBuildValidGenerator() {
        IPalette dummyPalette = iter -> Color.BLACK;
        BiFunction<IComplex, Integer, IComplexSequence> dummySequence = (z0, max) -> null;

        FractalGeneratorBuilder builder = new FractalGeneratorBuilder();
        FractalGenerator generator = builder
                .withSize(800, 600)
                .withCenter(new Complex(0, 0))
                .withZoom(0.001)
                .withMaxIterations(250)
                .withPalette(dummyPalette)
                .withSequenceFactory(dummySequence)
                .withOutputFile("test_fractal.png")
                .build();

        assertNotNull(generator, "The built FractalGenerator should not be null.");
    }

    /**
     * Test method verifying the chainability of the builder.
     * Each configuration method must return the builder instance itself.
     */
    @Test
    void testBuilderReturnsItself() {
        FractalGeneratorBuilder builder = new FractalGeneratorBuilder();

        FractalGeneratorBuilder returnedBuilder = builder.withMaxIterations(100);

        assertNotNull(returnedBuilder);
        assertEquals(builder, returnedBuilder, "The builder methods must return 'this' to allow chaining.");
    }

    /**
     * Test method verifying that an exception is thrown if mandatory parameters are missing.
     */
    @Test
    void testBuildThrowsExceptionWhenIncomplete() {
        FractalGeneratorBuilder builder = new FractalGeneratorBuilder();

        builder.withSize(800, 800);

        assertThrows(IllegalStateException.class, builder::build,
                "Building with missing mandatory parameters should throw an exception.");
    }


    /**
     * Test method verifying that zoomAnimation creates a composite with the requested number of frames.
     */
    @Test
    void testZoomAnimationValid() {
        IPalette dummyPalette = iter -> Color.BLACK;
        BiFunction<IComplex, Integer, IComplexSequence> dummySequence = (z0, max) -> null;

        FractalGeneratorBuilder builder = new FractalGeneratorBuilder()
                .withPalette(dummyPalette)
                .withSequenceFactory(dummySequence);

        FractalGeneratorComposite composite = builder.zoomAnimation(3, 0.5, 100);

        assertNotNull(composite);
        assertEquals(3, composite.getGenerators().size());
    }

    /**
     * Test method verifying that zoomAnimation throws an exception for an invalid number of frames.
     */
    @Test
    void testZoomAnimationInvalidFrameCount() {
        FractalGeneratorBuilder builder = new FractalGeneratorBuilder();

        assertThrows(IllegalArgumentException.class, () -> builder.zoomAnimation(0, 0.5, 100));
    }

    /**
     * Test method verifying that zoomAnimation throws an exception for invalid zoom multipliers.
     */
    @Test
    void testZoomAnimationInvalidZoomMultiplier() {
        FractalGeneratorBuilder builder = new FractalGeneratorBuilder();

        assertThrows(IllegalArgumentException.class, () -> builder.zoomAnimation(3, -0.1, 100));
        assertThrows(IllegalArgumentException.class, () -> builder.zoomAnimation(3, 1.5, 100));
    }
}
