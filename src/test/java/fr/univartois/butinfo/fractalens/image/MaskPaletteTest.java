package fr.univartois.butinfo.fractalens.image;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link MaskPalette}.
 *
 * @author FractaLens Team
 */
class MaskPaletteTest {

    /**
     * A palette always giving the same color, so that each component is easy to check.
     */
    private static final IPalette FIXED = n -> new Color(100, 150, 200);

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.image.MaskPalette#getColor(int)}
     * when only one component is kept.
     */
    @Test
    void testGetColorOneComponent() {
        assertEquals(new Color(100, 0, 0),
                new MaskPalette(FIXED, true, false, false).getColor(10));
        assertEquals(new Color(0, 150, 0),
                new MaskPalette(FIXED, false, true, false).getColor(10));
        assertEquals(new Color(0, 0, 200),
                new MaskPalette(FIXED, false, false, true).getColor(10));
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.image.MaskPalette#getColor(int)}
     * when several components are kept.
     */
    @Test
    void testGetColorSeveralComponents() {
        assertEquals(new Color(0, 150, 200),
                new MaskPalette(FIXED, false, true, true).getColor(10));

        assertEquals(new Color(100, 150, 0),
                new MaskPalette(FIXED, true, true, false).getColor(10));
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.image.MaskPalette#getColor(int)}
     * when all the components are kept, or none of them.
     */
    @Test
    void testGetColorAllOrNothing() {
        assertEquals(new Color(100, 150, 200),
                new MaskPalette(FIXED, true, true, true).getColor(10));
        assertEquals(Color.BLACK,
                new MaskPalette(FIXED, false, false, false).getColor(10));
    }

    /**
     * Test method for
     * {@link fr.univartois.butinfo.fractalens.image.MaskPalette#getColor(int)}
     * when decorating the grey palette.
     */
    @Test
    void testGetColorOnGreyPalette() {
        IPalette red = new MaskPalette(new GreyPalette(50), true, false, false);

        assertEquals(new Color(255, 0, 0), red.getColor(0));

        assertEquals(Color.BLACK, red.getColor(50));
    }

}
