package fr.univartois.butinfo.fractalens.complex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * The ZoomableComplexPlaneTest tests the decorator of {@link IComplexPlane} allowing to
 * zoom on a part of the complex plane.
 */
class ZoomableComplexPlaneTest {

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
     * Creates a new complex plane.
     *
     * @param height The height of the complex plane.
     * @param width The width of the complex plane.
     * @return The created complex plane.
     */
    private static IComplexPlane createPlane(int height, int width) {
        return new ComplexPlane(height, width);
    }

    /**
     * Test method for
     * when the zoom factor is 1, in which case the decorated plane behaves like the base
     * plane.
     */
    @Test
    void testAsComplexWithUnitZoomFactor() {
        IComplexPlane plane = new ZoomableComplexPlane(createPlane(600, 800), 1);

        assertEquals(createComplex(-400, 300), plane.asComplex(0, 0));
        assertEquals(createComplex(0, 300), plane.asComplex(0, 400));
        assertEquals(createComplex(0, 0), plane.asComplex(300, 400));
        assertEquals(createComplex(399, -299), plane.asComplex(599, 799));
    }

    /**
     * Test method for
     * when the zoom factor is smaller than 1 (a zoom on the image), like the factor 0.004
     * used to generate the Julia fractal.
     */
    @Test
    void testAsComplexWithZoomIn() {
        IComplexPlane plane = new ZoomableComplexPlane(createPlane(600, 800), 0.004);

        // -400 * 0.004 = -1.6 and 300 * 0.004 = 1.2.
        assertEquals(createComplex(-1.6, 1.2), plane.asComplex(0, 0));

        // The center of the image is still the origin of the complex plane.
        assertEquals(createComplex(0, 0), plane.asComplex(300, 400));

        // 399 * 0.004 = 1.596 and -299 * 0.004 = -1.196.
        assertEquals(createComplex(1.596, -1.196), plane.asComplex(599, 799));
    }

    /**
     * Test method for
     * when the zoom factor is greater than 1, which reduces the image (each pixel stands
     * for more than one unit in the complex plane).
     */
    @Test
    void testAsComplexWithZoomOut() {
        IComplexPlane plane = new ZoomableComplexPlane(createPlane(600, 800), 2);

        assertEquals(createComplex(-800, 600), plane.asComplex(0, 0));
        assertEquals(createComplex(0, 0), plane.asComplex(300, 400));
        assertEquals(createComplex(798, -598), plane.asComplex(599, 799));
    }

    /**
     * Test method for
     * with a negative zoom factor, which flips the complex plane with respect to the
     * origin.
     */
    @Test
    void testAsComplexWithNegativeZoomFactor() {
        IComplexPlane plane = new ZoomableComplexPlane(createPlane(600, 800), -1);

        assertEquals(createComplex(400, -300), plane.asComplex(0, 0));
        assertEquals(createComplex(0, 0), plane.asComplex(300, 400));
    }

    /**
     * Test method for
     * when several zoom decorators are nested: the zoom factors are multiplied.
     */
    @Test
    void testAsComplexWithNestedDecorators() {
        IComplexPlane plane = new ZoomableComplexPlane(
                new ZoomableComplexPlane(createPlane(600, 800), 0.5), 0.5);

        // The resulting zoom factor is 0.5 * 0.5 = 0.25.
        assertEquals(createComplex(-100, 75), plane.asComplex(0, 0));
        assertEquals(createComplex(0, 0), plane.asComplex(300, 400));
    }

    /**
     * Test method for
     * on an image whose dimensions are not even, to check the behavior of the
     * coordinates of the decorated plane.
     */
    @Test
    void testAsComplexWithOddDimensions() {
        IComplexPlane plane = new ZoomableComplexPlane(createPlane(501, 701), 0.5);

        assertEquals(createComplex(-175.25, 125.25), plane.asComplex(0, 0));

        // 700 - 350.5 = 349.5 and 250.5 - 500 = -249.5, both multiplied by 0.5.
        assertEquals(createComplex(174.75, -124.75), plane.asComplex(500, 700));
    }

    // ========================================================================
    // TESTS POUR zoomAnimation(double zoomMultiplier)
    // ========================================================================

    @Test
    void testZoomAnimationValid() {
        ZoomableComplexPlane plane = new ZoomableComplexPlane(createPlane(600, 800), 10.0);

        plane.zoomAnimation(0.5);

        // Le zoom initial était de 10.0. Après * 0.5, le nouveau zoom est de 5.0.
        // asComplex(0,0) pour 600x800 renvoie de base (-400, 300).
        // Avec un zoom de 5.0, ça doit faire (-2000, 1500).
        assertEquals(createComplex(-2000, 1500), plane.asComplex(0, 0));
    }

    @Test
    void testZoomAnimationLimitOne() {
        ZoomableComplexPlane plane = new ZoomableComplexPlane(createPlane(600, 800), 10.0);

        plane.zoomAnimation(1.0);

        // Le zoom reste à 10.0. Donc -400 * 10 = -4000 et 300 * 10 = 3000.
        assertEquals(createComplex(-4000, 3000), plane.asComplex(0, 0));
    }

    @Test
    void testZoomAnimationLimitZero() {
        ZoomableComplexPlane plane = new ZoomableComplexPlane(createPlane(600, 800), 10.0);

        plane.zoomAnimation(0.0);

        // Le zoom devient 0.0. Donc toutes les coordonnées valent (0, 0).
        assertEquals(createComplex(0, 0), plane.asComplex(0, 0));
    }

    @Test
    void testZoomAnimationTooHighThrowsException() {
        ZoomableComplexPlane plane = new ZoomableComplexPlane(createPlane(600, 800), 1.0);

        assertThrows(IllegalArgumentException.class, () -> {
            plane.zoomAnimation(1.1);
        });
    }

    @Test
    void testZoomAnimationNegativeThrowsException() {
        ZoomableComplexPlane plane = new ZoomableComplexPlane(createPlane(600, 800), 1.0);

        assertThrows(IllegalArgumentException.class, () -> {
            plane.zoomAnimation(-0.5);
        });
    }
}
