package fr.univartois.butinfo.fractalens.image;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

/**
 * The ComplementaryColorTest tests the decorator of {@link IPalette} returning
 * complementary colors.
 */
class ComplementaryColorTest {

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.image.IPalette#getColor(int)}
     * when the decorated palette is a constant color.
     */
    @Test
    void testGetColorWithConstantPalette() {

        IPalette constant = n -> Color.RED;
        IPalette palette = new ComplementaryColor(constant);

        // The complementary of RED (255, 0, 0) is (0, 255, 255).
        assertEquals(new Color(0, 255, 255), palette.getColor(0));
        assertEquals(new Color(0, 255, 255), palette.getColor(42));
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.image.IPalette#getColor(int)}
     * when the decorated palette is a grey palette.
     */
    @Test
    void testGetColorWithGreyPalette() {

        IPalette palette = new ComplementaryColor(new GreyPalette(50));

        // A converging point reaches maxIterations: the grey palette gives black (0, 0,
        // 0), whose complementary is white.
        assertEquals(Color.WHITE, palette.getColor(50));

        // A diverging point stops quickly: the grey palette gives a light grey, whose
        // complementary is dark.
        Color complementary = palette.getColor(5);
        int expected = 255 - Math.round(255 * (1 - 5 / 50f));
        assertEquals(new Color(expected, expected, expected), complementary);
    }

    /**
     * Test method for the case where two complementary decorators are nested:
     * applying the complementary twice gives back the original color.
     */
    @Test
    void testNestedDecorators() {

        IPalette palette = new ComplementaryColor(
                new ComplementaryColor(n -> new Color(42, 128, 200)));

        assertEquals(new Color(42, 128, 200), palette.getColor(10));
    }

}
