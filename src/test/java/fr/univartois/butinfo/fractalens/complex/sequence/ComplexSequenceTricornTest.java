/*
 * FractaLens - A Fractal Generator Library Developed in Lens.
 *
 * Copyright (c) 2022-2026 Romain Wallon - Université d'Artois.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fr.univartois.butinfo.fractalens.complex.sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * The ComplexSequenceTricornTest tests the implementation of {@link IComplexSequence}
 * for Tricorn's sequence.
 *
 * @author Romain Wallon
 * @version 0.1.0
 */
class ComplexSequenceTricornTest {

    /**
     * Creates a new complex number.
     *
     * @param realPart The real part of the complex number.
     * @param imaginaryPart The imaginary part of the complex number.
     * @return The created complex number.
     */
    private static IComplex createComplex(double realPart, double imaginaryPart) {
        return new Complex(realPart, imaginaryPart);
    }

    /**
     * Creates a new sequence implementing Tricorn's sequence.
     *
     * @param first The first term of the sequence.
     * @param constant The constant number in the definition of the Tricorn sequence.
     * @param nbTerms The maximum number of terms to compute.
     * @return The created Tricorn sequence.
     */
    private static IComplexSequence createTricornSequence(
            IComplex first, IComplex constant, int nbTerms) {
        return new ComplexSequenceTricorn(first, constant, nbTerms);
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence#computeNext(fr.univartois.butinfo.fractalens.complex.IComplex)}.
     */
    @Test
    void testComputeNext() {
        // Test avec une partie imaginaire non nulle pour vérifier le conjugué
        // z0 = 1 + 2i, c = 0.5 - 1i
        // conj(z0) = 1 - 2i
        // (1 - 2i)^2 = 1 - 4 - 4i = -3 - 4i
        // z1 = (-3 - 4i) + (0.5 - 1i) = -2.5 - 5i
        IComplex z0 = createComplex(1, 2);
        IComplex c = createComplex(0.5, -1);
        IComplexSequence tricorn = createTricornSequence(z0, c, 50);

        IComplex z1 = createComplex(-2.5, -5);
        assertEquals(z1, tricorn.computeNext(z0));

        // conj(z1) = -2.5 + 5i
        // (-2.5 + 5i)^2 = 6.25 - 25 - 25i = -18.75 - 25i
        // z2 = (-18.75 - 25i) + (0.5 - 1i) = -18.25 - 26i
        IComplex z2 = createComplex(-18.25, -26);
        assertEquals(z2, tricorn.computeNext(z1));
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence#iterator()}
     * when the sequence converges.
     */
    @Test
    void testIteratorOnConvergence() {
        IComplex z0 = createComplex(0.1, 0.1);
        IComplex c = createComplex(0, 0);
        int nbTerms = 50;

        IComplexSequence tricorn = createTricornSequence(z0, c, nbTerms);
        int nbIterations = 0;

        for (var _ : tricorn) {
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
        IComplex z0 = createComplex(2, 2);
        IComplex c = createComplex(1, 1);
        int nbTerms = 50;

        IComplexSequence tricorn = createTricornSequence(z0, c, nbTerms);
        int nbIterations = 0;

        for (var _ : tricorn) {
            nbIterations++;
        }

        assertTrue(nbIterations < nbTerms);
    }
}
