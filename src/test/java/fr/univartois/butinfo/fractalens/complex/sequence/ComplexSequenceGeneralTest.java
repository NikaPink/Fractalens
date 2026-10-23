/*
 * FractaLens - A Fractal Generator Library Developed in Lens.
 * Copyright (c) 2022-2026 Romain Wallon - Université d'Artois.
 * ... (en-tête habituel) ...
 */

package fr.univartois.butinfo.fractalens.complex.sequence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;
import java.util.Iterator;
import java.util.function.BinaryOperator;
import org.junit.jupiter.api.Test;

/**
 * The ComplexSequenceGeneralTest tests the generic sequence implementation.
 *
 * @author TonNom
 *
 * @version 0.1.0
 */
class ComplexSequenceGeneralTest {

    /**
     * Creates a new complex number for testing.
     *
     * @param realPart The real part of the complex number.
     * @param imaginaryPart The imaginary part of the complex number.
     *
     * @return They created complex number.
     */
    private static IComplex createComplex(double realPart, double imaginaryPart) {
        return new Complex(realPart, imaginaryPart);
    }

    /**
     * Test method for computeNext verifying that the provided lambda is executed.
     */
    @Test
    void testComputeNextWithSimpleLambda() {
        IComplex z0 = createComplex(1.5, 2.0);

        // Une lambda factice qui additionne simplement z0 et prev
        BinaryOperator<IComplex> additionLambda = (initial, prev) -> prev.add(initial);
        ComplexSequenceGeneral sequence = new ComplexSequenceGeneral(z0, 100, additionLambda);

        IComplex previous = createComplex(3.0, 4.0);
        IComplex next = sequence.computeNext(previous);

        // Attendu : (3.0 + 4.0i) + (1.5 + 2.0i) = 4.5 + 6.0i
        assertEquals(4.5, next.getRealPart(), 0.001);
        assertEquals(6.0, next.getImaginaryPart(), 0.001);
    }

    /**
     * Test method for computeNext verifying the Burning Ship logic.
     */
    @Test
    void testComputeNextWithBurningShip() {
        IComplex z0 = createComplex(0, 0);
        IComplex c = createComplex(-0.5, 0.2);

        // Formule du Burning Ship : z_{n+1} = (|Re(z_n)| + i|Im(z_n)|)^2 + c
        BinaryOperator<IComplex> burningShipLambda = (initial, prev) -> {
            IComplex absComplex = createComplex(
                    Math.abs(prev.getRealPart()),
                    Math.abs(prev.getImaginaryPart())
            );
            return absComplex.pow(2).add(c);
        };

        ComplexSequenceGeneral sequence = new ComplexSequenceGeneral(z0, 100, burningShipLambda);

        // Test avec des valeurs négatives pour vérifier l'effet de la valeur absolue
        IComplex previous = createComplex(-2.0, 1.0);

        // Calcul mathématique attendu : 
        // 1. Valeur absolue : |-2| + i|1| = 2 + 1i
        // 2. Puissance 2 : (2 + 1i)^2 = 4 + 4i - 1 = 3 + 4i
        // 3. Ajout de c : (3 + 4i) + (-0.5 + 0.2i) = 2.5 + 4.2i
        IComplex next = sequence.computeNext(previous);

        assertEquals(2.5, next.getRealPart(), 0.001);
        assertEquals(4.2, next.getImaginaryPart(), 0.001);
    }

    /**
     * Test method for the iterator instantiation.
     */
    @Test
    void testIterator() {
        IComplex z0 = createComplex(0, 0);
        // Lambda qui renvoie juste le terme initial (sans importance pour ce test)
        ComplexSequenceGeneral sequence = new ComplexSequenceGeneral(z0, 100, (initial, prev) -> initial);

        Iterator<IComplex> iterator = sequence.iterator();

        assertNotNull(iterator, "The iterator should not be null.");
    }

    /**
     * Test method for the generalized Burning Ship sequence.
     */
    @Test
    void testComputeNextWithGeneralizedBurningShip() {
        IComplex z0 = createComplex(0.5, -0.5);
        int p = 3;

        ComplexSequenceGeneral sequence = ComplexSequenceGeneral.burningShipGeneralized(z0, 100, p);
        IComplex previous = createComplex(-1.0, 2.0);
        IComplex next = sequence.computeNext(previous);

        assertEquals(-10.5, next.getRealPart(), 0.001);
        assertEquals(-2.5, next.getImaginaryPart(), 0.001);
    }

    @Test
    void testComputeNextPower2() {
        // Arrange : p = 2, c = 1 + 1i
        IComplex z0 = new Complex(0, 0);
        Complex c = new Complex(1, 1);
        int maxIter = 50;
        int p = 2;

        ComplexSequenceGeneral sequence = ComplexSequenceGeneral.tricornGeneralized(z0, c, maxIter, p);

        // Act & Assert 1 : prev = 0 + 0i
        // abs = 0+0i -> conj = 0-0i -> pow(2) = 0 -> add(c) = 1+1i
        IComplex z1 = sequence.computeNext(z0);
        assertEquals(1.0, z1.getRealPart(), 0.0001);
        assertEquals(1.0, z1.getImaginaryPart(), 0.0001);

        // Act & Assert 2 : prev = 1 + 1i
        // abs = 1+1i -> conj = 1-1i -> pow(2) = -2i -> add(1+1i) = 1-1i
        IComplex z2 = sequence.computeNext(z1);
        assertEquals(1.0, z2.getRealPart(), 0.0001);
        assertEquals(-1.0, z2.getImaginaryPart(), 0.0001);
    }

    @Test
    void testComputeNextWithNegativeValues() {
        // Ce test vérifie que le Math.abs() fonctionne correctement avant le conjugué
        IComplex z0 = new Complex(0, 0);
        Complex c = new Complex(0.5, 0.5);
        ComplexSequenceGeneral sequence = ComplexSequenceGeneral.tricornGeneralized(z0, c, 50, 2);

        // prev = -2 + 3i
        // abs = 2+3i -> conj = 2-3i
        // pow(2) = (2-3i)^2 = 4 - 12i - 9 = -5 - 12i
        // add(0.5+0.5i) = -4.5 - 11.5i
        IComplex prev = new Complex(-2, 3);
        IComplex result = sequence.computeNext(prev);

        assertEquals(-4.5, result.getRealPart(), 0.0001);
        assertEquals(-11.5, result.getImaginaryPart(), 0.0001);
    }

    @Test
    void testComputeNextPower3() {
        // Ce test vérifie que la puissance p arbitraire fonctionne (ici p=3)
        IComplex z0 = new Complex(0, 0);
        Complex c = new Complex(0, 0); // c est nul pour simplifier le calcul
        ComplexSequenceGeneral sequence = ComplexSequenceGeneral.tricornGeneralized(z0, c, 50, 3);

        // prev = -1 + 2i
        // abs = 1+2i -> conj = 1-2i
        // pow(3) = (1-2i)^3 = (1 - 4i - 4) * (1-2i) = (-3-4i) * (1-2i) = -3 + 6i - 4i - 8 = -11 + 2i
        IComplex prev = new Complex(-1, 2);
        IComplex result = sequence.computeNext(prev);

        assertEquals(-11.0, result.getRealPart(), 0.0001);
        assertEquals(2.0, result.getImaginaryPart(), 0.0001);
    }

    /**
     * Test method for the static burningShip sequence generator.
     */
    @Test
    void testBurningShipStaticMethod() {
        IComplex z0 = createComplex(0.1, 0.1);

        ComplexSequenceGeneral sequence = ComplexSequenceGeneral.burningShip(z0, 50);

        // prev = -2 + 3i
        // 1. Valeur absolue : |-2| + i|3| = 2 + 3i
        // 2. Puissance 2   : (2 + 3i)^2 = 4 - 9 + 12i = -5 + 12i
        // 3. Ajout de z0   : (-5 + 12i) + (0.1 + 0.1i) = -4.9 + 12.1i
        IComplex previous = createComplex(-2.0, 3.0);
        IComplex next = sequence.computeNext(previous);

        assertEquals(-4.9, next.getRealPart(), 0.001);
        assertEquals(12.1, next.getImaginaryPart(), 0.001);
    }

    /**
     * Test method for the Black Snowflake sequence.
     */
    @Test
    void testBlackSnowFlake() {
        IComplex z0 = createComplex(0.5, 0.5);
        ComplexSequenceGeneral sequence = ComplexSequenceGeneral.blackSnowFlake(z0, 50);

        // prev = 1 + 1i
        // 1. prev^2 = (1 + 1i)^2 = 1 - 1 + 2i = 0 + 2i
        // 2. prev^4 = (2i)^2 = -4 + 0i
        // 3. prev^4 + prev^2 + z0 = -4 + 2i + 0.5 + 0.5i = -3.5 + 2.5i
        IComplex previous = createComplex(1.0, 1.0);
        IComplex next = sequence.computeNext(previous);

        assertEquals(-3.5, next.getRealPart(), 0.001);
        assertEquals(2.5, next.getImaginaryPart(), 0.001);
    }

    /**
     * Test method to check edge cases like origin (0, 0) for Black Snowflake.
     */
    @Test
    void testBlackSnowFlakeWithOrigin() {
        IComplex z0 = createComplex(1.0, -1.0);
        ComplexSequenceGeneral sequence = ComplexSequenceGeneral.blackSnowFlake(z0, 50);

        IComplex previous = createComplex(0.0, 0.0);
        IComplex next = sequence.computeNext(previous);

        // 0^4 + 0^2 + z0 = z0
        assertEquals(1.0, next.getRealPart(), 0.001);
        assertEquals(-1.0, next.getImaginaryPart(), 0.001);
    }
}

