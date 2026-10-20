package fr.univartois.butinfo.fractalens.complex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TranslationComplexPlaneTest {

    /**
     * Creates a new complex number.
     *
     * @param realPart The real part of the complex number.
     * @param imaginaryPart The imaginary part of the complex number.
     *
     * @return The created complex number.
     */
    private static Complex createComplex(double realPart, double imaginaryPart) {
        return new Complex(realPart, imaginaryPart);
    }

    /**
     * Creates a new translated complex plane.
     *
     * @param plane The underlying plane to decorate.
     * @param translation The translation offset.
     *
     * @return The decorated complex plane.
     */
    private static IComplexPlane createTranslatedPlane(IComplexPlane plane, Complex translation) {
        return new TranslationComplexPlane(plane, translation);
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.complex.TranslationComplexPlane#asComplex(int, int)}
     * with a zero translation.
     */
    @Test
    void testAsComplexWithZeroTranslation() {
        IComplexPlane basePlane = (row, col) -> createComplex(col, row);
        IComplexPlane translatedPlane = createTranslatedPlane(basePlane, createComplex(0, 0));

        IComplex result = translatedPlane.asComplex(10, 25);
        assertEquals(25, result.getRealPart(), 0.001);
        assertEquals(10, result.getImaginaryPart(), 0.001);
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.complex.TranslationComplexPlane#asComplex(int, int)}
     * with a positive translation offset.
     */
    @Test
    void testAsComplexWithPositiveTranslation() {
        IComplexPlane basePlane = (row, col) -> createComplex(col, row);
        Complex offset = createComplex(1.5, 2.75);
        IComplexPlane translatedPlane = createTranslatedPlane(basePlane, offset);

        IComplex result = translatedPlane.asComplex(10, 20);
        assertEquals(21.5, result.getRealPart(), 0.001);
        assertEquals(12.75, result.getImaginaryPart(), 0.001);
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.complex.TranslationComplexPlane#asComplex(int, int)}
     * with negative values.
     */
    @Test
    void testAsComplexWithNegativeTranslation() {
        IComplexPlane basePlane = (row, col) -> createComplex(col, row);
        Complex offset = createComplex(-0.4, -0.6);
        IComplexPlane translatedPlane = createTranslatedPlane(basePlane, offset);

        IComplex result = translatedPlane.asComplex(5, 15);
        assertEquals(14.6, result.getRealPart(), 0.001);
        assertEquals(4.4, result.getImaginaryPart(), 0.001);
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.complex.TranslationComplexPlane#asComplex(int, int)}
     * verifying the translation of the center point of an image.
     */
    @Test
    void testAsComplexCenterPoint() {
        // Base plane centered at (0, 0) for a 600x800 image
        IComplexPlane basePlane = (row, col) -> createComplex(col - 400.0, 300.0 - row);
        Complex centerOffset = createComplex(0.1, 0.6);
        IComplexPlane translatedPlane = createTranslatedPlane(basePlane, centerOffset);

        // Center pixel (row=300, col=400) must equal the translation offset
        IComplex center = translatedPlane.asComplex(300, 400);
        assertEquals(0.1, center.getRealPart(), 0.001);
        assertEquals(0.6, center.getImaginaryPart(), 0.001);
    }
}
