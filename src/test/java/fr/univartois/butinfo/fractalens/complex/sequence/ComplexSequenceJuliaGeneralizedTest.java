package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link ComplexSequenceJuliaGeneralized}.
 *
 * @author FractaLens Team
 */
public class ComplexSequenceJuliaGeneralizedTest {

    /**
     * Helper method to create a complex number.
     *
     * @param realPart The real part.
     * @param imaginaryPart The imaginary part.
     *
     * @return The created complex number.
     */
    private static IComplex createComplex(double realPart, double imaginaryPart) {
        return new Complex(realPart, imaginaryPart);
    }

    /**
     * Helper method to create a generalized Julia sequence.
     *
     * @param first The initial term.
     * @param constant The constant c.
     * @param nbTerms The maximum number of terms.
     * @param p The power exponent.
     *
     * @return The created sequence.
     */
    private static IComplexSequence createJuliaGeneralizedSequence(IComplex first, IComplex constant, int nbTerms, int p) {
        return new ComplexSequenceJuliaGeneralized(first, constant, nbTerms, p);
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence#computeNext(fr.univartois.butinfo.fractalens.complex.IComplex)}.
     */
    @Test
    void testComputeNext() {
        IComplex z0 = createComplex(2, 0);
        IComplex c = createComplex(0, 0);
        IComplexSequence julia = createJuliaGeneralizedSequence(z0, c, 3, 3);

        IComplex z1 = createComplex(8, 0);
        assertEquals(z1, julia.computeNext(z0));

        IComplex z2 = createComplex(512, 0);
        assertEquals(z2, julia.computeNext(z1));

        IComplex z3 = createComplex(134217728, 0);
        assertEquals(z3, julia.computeNext(z2));
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence#iterator()}
     * when the sequence converges.
     */
    @Test
    void testIteratorOnConvergence() {
        IComplex z0 = createComplex(0.5, 0);
        IComplex c = createComplex(0, 0);
        int nbTerms = 50;

        IComplexSequence julia = createJuliaGeneralizedSequence(z0, c, nbTerms, 2);
        int nbIterations = 0;

        for (var _ : julia) {
            nbIterations++;
        }

        assertEquals(nbTerms, nbIterations);
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence#iterator()}
     * when the sequence diverges.
     */
    @Test
    void testIteratorOnDivergence() {
        IComplex z0 = createComplex(2, 0);
        IComplex c = createComplex(0.5, 0.5);
        int nbTerms = 50;

        IComplexSequence julia = createJuliaGeneralizedSequence(z0, c, nbTerms, 2);
        int nbIterations = 0;

        for (var _ : julia) {
            nbIterations++;
        }

        assertTrue(nbIterations < nbTerms);
    }

}
