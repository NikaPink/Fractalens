package fr.univartois.butinfo.fractalens.image;

import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link GradientPalette}.
 *
 * @author FractaLens Team
 */
class GradientPaletteTest {

    /**
     * Tests the color interpolation computed by {@link GradientPalette#getColor(int)}.
     */
    @Test
    void testGradientPalette() {
        GradientPalette p = new GradientPalette(new Color(0, 8, 9), new Color(110, 45, 12), 50);
        int r, g, b;
        r = (int) (0 + 1 * (110 - 0));
        g = (int) (8 + 1 * (45 - 8));
        b = (int) (9 + 1 * (12 - 9));
        assertEquals(new Color(r, g, b), p.getColor(50));
        r = (int) (0 + 0.5 * (110 - 0));
        g = (int) (8 + 0.5 * (45 - 8));
        b = (int) (9 + 0.5 * (12 - 9));
        assertEquals(new Color(r, g, b), p.getColor(25));
        r = (int) (0 + 0.6 * (110 - 0));
        g = (int) (8 + 0.6 * (45 - 8));
        b = (int) (9 + 0.6 * (12 - 9));
        assertEquals(new Color(r, g, b), p.getColor(30));
    }

}
