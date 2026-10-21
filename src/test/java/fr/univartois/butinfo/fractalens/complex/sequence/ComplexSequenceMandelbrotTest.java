package fr.univartois.butinfo.fractalens.complex.sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.univartois.butinfo.fractalens.complex.Complex;
import org.junit.jupiter.api.Test;

import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * The ComplexSequenceMandelbrotTest tests the implementation of {@link IComplexSequence}
 * for Mandelbrot's sequence.
 *
 * @version 0.1.0
 */
class ComplexSequenceMandelbrotTest {

    /**
     * Creates a new complex number.
     *
     * @param realPart The real part of the complex number.
     * @param imaginaryPart The imaginary part of the complex number.
     *
     * @return The created complex number.
     */
    private static IComplex createComplex(double realPart, double imaginaryPart) {
        return new Complex(realPart,imaginaryPart);
    }

    /**
     * Creates a new sequence implementing Mandelbrot's sequence.
     *
     * @param constant The point of the plane, which is both the constant number in the
     *        definition of the sequence and its first term.
     * @param nbTerms The maximum number of terms to compute.
     *
     * @return The created Mandelbrot's sequence.
     */
    private static IComplexSequence createMandelbrotSequence(IComplex constant, int nbTerms) {
        return new ComplexSequenceMandelbrot(constant, nbTerms);
    }

    /**
     * Test method for
     * {@link IComplexSequence#computeNext(IComplex)}.
     */
    @Test
    void testComputeNext() {
        IComplex c = createComplex(0, 1);
        IComplexSequence mandelbrot = createMandelbrotSequence(c, 50);

        IComplex z1 = createComplex(-1, 1);
        assertEquals(z1, mandelbrot.computeNext(c));

        IComplex z2 = createComplex(0, -1);
        assertEquals(z2, mandelbrot.computeNext(z1));

        IComplex z3 = createComplex(-1, 1);
        assertEquals(z3, mandelbrot.computeNext(z2));
    }

    /**
     * Test method for
     * {@link IComplexSequence#iterator()}
     * when the sequence converges.
     */
    @Test
    void testIteratorOnConvergence() {
        IComplex c = createComplex(-1, 0);
        int nbTerms = 50;

        IComplexSequence mandelbrot = createMandelbrotSequence(c, nbTerms);
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

        IComplexSequence mandelbrot = createMandelbrotSequence(c, nbTerms);
        int nbIterations = 0;

        for (var _ : mandelbrot) {
            nbIterations++;
        }

        assertTrue(nbIterations < nbTerms);
    }

}
