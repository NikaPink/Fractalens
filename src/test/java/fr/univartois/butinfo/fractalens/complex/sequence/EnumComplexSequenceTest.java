package fr.univartois.butinfo.fractalens.complex.sequence;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

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
        assertInstanceOf(ComplexJuliaSequence.class, seq);
    }

    /**
     * Tests that MANDELBROT produces a ComplexSequenceMandelbrot instance.
     */
    @Test
    void testMandelbrot() {
        IComplexSequence seq = EnumComplexSequence.MANDELBROT.getFunction(c).apply(z0, maxIter);
        assertInstanceOf(ComplexSequenceMandelbrot.class, seq);
    }

    /**
     * Tests that TRICORN produces a ComplexSequenceTricorn instance.
     */
    @Test
    void testTricorn() {
        IComplexSequence seq = EnumComplexSequence.TRICORN.getFunction(c).apply(z0, maxIter);
        assertInstanceOf(ComplexSequenceTricorn.class, seq);
    }

    /**
     * Tests that BURNING_SHIP produces a ComplexSequenceGeneral instance.
     */
    @Test
    void testBurningShip() {
        IComplexSequence seq = EnumComplexSequence.BURNING_SHIP.getFunction(c).apply(z0, maxIter);
        assertInstanceOf(ComplexSequenceGeneral.class, seq);
    }

    /**
     * Tests that BLACK_SNOWFLAKE produces a ComplexSequenceGeneral instance.
     */
    @Test
    void testBlackSnowflake() {
        IComplexSequence seq = EnumComplexSequence.BLACK_SNOWFLAKE.getFunction(c).apply(z0, maxIter);
        assertInstanceOf(ComplexSequenceGeneral.class, seq);
    }
}
