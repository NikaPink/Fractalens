package fr.univartois.butinfo.fractalens.complex.sequence;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * Unit tests for {@link EnumComplexSequence}.
 *
 * @author FractaLens Team
 */
class EnumComplexSequenceTest {

    // Simple default values used across tests
    IComplex c = new Complex(0, 0);
    IComplex z0 = new Complex(0, 0);
    int maxIter = 10;

    /**
     * Tests that JULIA produces a ComplexJuliaSequence instance.
     */
    @Test
    void testJulia() {
        IComplexSequence seq = EnumComplexSequence.JULIA.getFunction(c).apply(z0, maxIter);
        assertTrue(seq instanceof ComplexJuliaSequence);
    }

    /**
     * Tests that MANDELBROT produces a ComplexSequenceMandelbrot instance.
     */
    @Test
    void testMandelbrot() {
        IComplexSequence seq = EnumComplexSequence.MANDELBROT.getFunction(c).apply(z0, maxIter);
        assertTrue(seq instanceof ComplexSequenceMandelbrot);
    }

    /**
     * Tests that TRICORN produces a ComplexSequenceTricorn instance.
     */
    @Test
    void testTricorn() {
        IComplexSequence seq = EnumComplexSequence.TRICORN.getFunction(c).apply(z0, maxIter);
        assertTrue(seq instanceof ComplexSequenceTricorn);
    }

    /**
     * Tests that BURNING_SHIP produces a ComplexSequenceGeneral instance.
     */
    @Test
    void testBurningShip() {
        IComplexSequence seq = EnumComplexSequence.BURNING_SHIP.getFunction(c).apply(z0, maxIter);
        assertTrue(seq instanceof ComplexSequenceGeneral);
    }

    /**
     * Tests that BLACK_SNOWFLAKE produces a ComplexSequenceGeneral instance.
     */
    @Test
    void testBlackSnowflake() {
        IComplexSequence seq = EnumComplexSequence.BLACK_SNOWFLAKE.getFunction(c).apply(z0, maxIter);
        assertTrue(seq instanceof ComplexSequenceGeneral);
    }
}
