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
     * @return The created complex number.
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
        // 2. Puissance 2   : (2 + 1i)^2 = 4 + 4i - 1 = 3 + 4i
        // 3. Ajout de c    : (3 + 4i) + (-0.5 + 0.2i) = 2.5 + 4.2i
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
}

