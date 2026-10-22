package fr.univartois.butinfo.fractalens.complex.sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;
import org.junit.jupiter.api.Test;

/**
 * The JuliaSequeunceSpinTest tests the implementation of {@link JuliaSequenceSpin},
 * where the constant c is multiplied by cos(theta) + i sin(theta).
 */
class JuliaSequeunceSpinTest {

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
     * Creates a new sequence implementing Julia's spin sequence.
     *
     * @param first The first term of the sequence.
     * @param constant The constant number (before rotation).
     * @param theta The rotation angle applied to the constant.
     * @param nbTerms The maximum number of terms to compute.
     *
     * @return The created Julia's spin sequence.
     */
    private static IComplexSequence createJuliaSpinSequence(
            IComplex first, IComplex constant, double theta, int nbTerms) {
        return new JuliaSequenceSpin(first, constant, theta, nbTerms);
    }

    /**
     * With theta = 0, the sequence must behave like a classic Julia sequence.
     */
    @Test
    void testComputeNextWithoutRotation() {
        IComplex z0 = createComplex(0.5, 0);
        IComplex c = createComplex(0.25, 0.5);
        IComplexSequence spin = createJuliaSpinSequence(z0, c, 0, 50);
        IComplexSequence julia = new ComplexJuliaSequence(z0, c, 50);

        IComplex z1 = julia.computeNext(z0);
        assertEquals(z1, spin.computeNext(z0));
        assertEquals(julia.computeNext(z1), spin.computeNext(z1));
    }

    /**
     * Checks that the constant is rotated by the right angle (z0 = 0 gives c').
     */
    @Test
    void testRotationOfConstant() {
        IComplex zero = createComplex(0, 0);
        IComplex c = createComplex(1, 0);

        assertEquals(createComplex(1, 0),
                createJuliaSpinSequence(zero, c, 0, 50).computeNext(zero));
        assertEquals(createComplex(0, 1),
                createJuliaSpinSequence(zero, c, Math.PI / 2, 50).computeNext(zero));
        assertEquals(createComplex(-1, 0),
                createJuliaSpinSequence(zero, c, Math.PI, 50).computeNext(zero));
        assertEquals(createComplex(0, -1),
                createJuliaSpinSequence(zero, c, 3 * Math.PI / 2, 50).computeNext(zero));
        assertEquals(createComplex(1, 0),
                createJuliaSpinSequence(zero, c, 2 * Math.PI, 50).computeNext(zero));
    }

    /**
     * Checks z_{n+1} = z_n^2 + c * (cos(theta) + i sin(theta)) on a non trivial term.
     */
    @Test
    void testComputeNext() {
        IComplex z0 = createComplex(1, 1);
        IComplex c = createComplex(1, 0);
        IComplexSequence spin = createJuliaSpinSequence(z0, c, Math.PI / 2, 50);

        // (1 + i)^2 = 2i, and c' = i, so z1 = 3i.
        IComplex z1 = createComplex(0, 3);
        assertEquals(z1, spin.computeNext(z0));

        // (3i)^2 = -9, so z2 = -9 + i.
        IComplex z2 = createComplex(-9, 1);
        assertEquals(z2, spin.computeNext(z1));
    }

    /**
     * Test method for {@link IComplexSequence#iterator()} when the sequence converges.
     */
    @Test
    void testIteratorOnConvergence() {
        IComplex z0 = createComplex(0.5, 0);
        IComplex c = createComplex(0, 0);
        int nbTerms = 50;

        IComplexSequence spin = createJuliaSpinSequence(z0, c, Math.PI / 3, nbTerms);
        int nbIterations = 0;

        for (var _ : spin) {
            nbIterations++;
        }

        assertEquals(nbTerms, nbIterations);
    }

    /**
     * Test method for {@link IComplexSequence#iterator()} when the sequence diverges.
     */
    @Test
    void testIteratorOnDivergence() {
        IComplex z0 = createComplex(2, 0);
        IComplex c = createComplex(0.5, 0.5);
        int nbTerms = 50;

        IComplexSequence spin = createJuliaSpinSequence(z0, c, Math.PI / 4, nbTerms);
        int nbIterations = 0;

        for (var _ : spin) {
            nbIterations++;
        }

        assertTrue(nbIterations < nbTerms);
    }

}
