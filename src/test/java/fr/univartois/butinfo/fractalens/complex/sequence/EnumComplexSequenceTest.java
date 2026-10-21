package fr.univartois.butinfo.fractalens.complex.sequence;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

class EnumComplexSequenceTest {

    // Des valeurs simples qui serviront pour tous les tests
    IComplex c = new Complex(0, 0);
    IComplex z0 = new Complex(0, 0);
    int maxIter = 10;

    @Test
    void testJulia() {
        IComplexSequence seq = EnumComplexSequence.JULIA.getFunction(c).apply(z0, maxIter);
        assertTrue(seq instanceof ComplexJuliaSequence);
    }

    @Test
    void testMandelbrot() {
        IComplexSequence seq = EnumComplexSequence.MANDELBROT.getFunction(c).apply(z0, maxIter);
        assertTrue(seq instanceof ComplexSequenceMandelbrot);
    }

    @Test
    void testTricorn() {
        IComplexSequence seq = EnumComplexSequence.TRICORN.getFunction(c).apply(z0, maxIter);
        assertTrue(seq instanceof ComplexSequenceTricorn);
    }

    @Test
    void testBurningShip() {
        IComplexSequence seq = EnumComplexSequence.BURNING_SHIP.getFunction(c).apply(z0, maxIter);
        assertTrue(seq instanceof ComplexSequenceGeneral);
    }

    @Test
    void testBlackSnowflake() {
        IComplexSequence seq = EnumComplexSequence.BLACK_SNOWFLAKE.getFunction(c).apply(z0, maxIter);
        assertTrue(seq instanceof ComplexSequenceGeneral);
    }
}
