package fr.univartois.butinfo.fractalens.complex.sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.univartois.butinfo.fractalens.complex.Complex;
import org.junit.jupiter.api.Test;

import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * The ComplexSequenceMandelbrotGeneralizedTest tests the implementation of
 * {@link IComplexSequence} for the generalized Mandelbrot's sequence.
 *
 * @version 0.1.0
 */
class ComplexSequenceMandelbrotGeneralizedTest {

    /**
     * Creates a new complex number.
     *
     * @param realPart The real part of the complex number.
     * @param imaginaryPart The imaginary part of the complex number.
     *
     * @return The created complex number.
     */
    private static IComplex createComplex(double realPart, double imaginaryPart) {
        return new Complex(realPart, imaginaryPart);
    }

    /**
     * Creates a new sequence implementing the generalized Mandelbrot's sequence.
     *
     * @param constant The point of the plane, which is both the constant number in the
     *        definition of the sequence and its first term.
     * @param nbTerms The maximum number of terms to compute.
     * @param power The power applied to the previous term.
     *
     * @return The created generalized Mandelbrot's sequence.
     */
    private static IComplexSequence createMandelbrotGeneralizedSequence(IComplex constant,
            int nbTerms, int power) {
        return new ComplexSequenceMandelbrotGeneralized(constant, nbTerms, power);
    }

    /**
     * Test method for
     * {@link IComplexSequence#computeNext(IComplex)}
     * with power 2, which must behave like Mandelbrot's sequence.
     */
    @Test
    void testComputeNextPower2() {
        IComplex c = createComplex(0, 1);
        IComplexSequence mandelbrot = createMandelbrotGeneralizedSequence(c, 50, 2);

        IComplex z1 = createComplex(-1, 1);
        assertEquals(z1, mandelbrot.computeNext(c));

        IComplex z2 = createComplex(0, -1);
        assertEquals(z2, mandelbrot.computeNext(z1));

        IComplex z3 = createComplex(-1, 1);
        assertEquals(z3, mandelbrot.computeNext(z2));
    }

    /**
     * Test method for
     * {@link IComplexSequence#computeNext(IComplex)}
     * with power 3.
     */
    @Test
    void testComputeNextPower3() {
        IComplex c = createComplex(2, 0);
        IComplexSequence mandelbrot = createMandelbrotGeneralizedSequence(c, 50, 3);

        IComplex z1 = createComplex(10, 0);
        assertEquals(z1, mandelbrot.computeNext(c));

        IComplex z2 = createComplex(1002, 0);
        assertEquals(z2, mandelbrot.computeNext(z1));
    }

    /**
     * Test method for
     * {@link IComplexSequence#iterator()}
     * when the sequence converges.
     */
    @Test
    void testIteratorOnConvergence() {
        IComplex c = createComplex(0, 1);
        int nbTerms = 50;

        IComplexSequence mandelbrot = createMandelbrotGeneralizedSequence(c, nbTerms, 3);
        int nbIterations = 0;

        for (var _ : mandelbrot) {
            nbIterations++;
        }

        assertEquals(nbTerms, nbIterations);
    }

    /**
     * Test method for
     * {@link IComplexSequence#iterator()}
     * when the sequence diverges.
     */
    @Test
    void testIteratorOnDivergence() {
        IComplex c = createComplex(2, 0);
        int nbTerms = 50;

        IComplexSequence mandelbrot = createMandelbrotGeneralizedSequence(c, nbTerms, 3);
        int nbIterations = 0;

        for (var _ : mandelbrot) {
            nbIterations++;
        }

        assertTrue(nbIterations < nbTerms);
    }

}
