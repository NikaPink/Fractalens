package fr.univartois.butinfo.fractalens.image;

import java.awt.Color;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for the {@link FixedPalette} class.
 *
 * @author Fractalens Team
 * @version 1.0
 */
class FixedPaletteTest {

    private List<Color> sampleColors;
    private FixedPalette palette;

    @BeforeEach
    void setUp() {
        // Initialisation d'une liste de 3 couleurs de test
        sampleColors = List.of(
                Color.RED,   // Index 0
                Color.GREEN, // Index 1
                Color.BLUE   // Index 2
        );
        palette = new FixedPalette(sampleColors);
    }

    @Test
    @DisplayName("getColor should not return null for valid instances")
    void testGetColorNotNull() {
        assertNotNull(palette.getColor(0));
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 0})
    @DisplayName("getColor should return the first color when iteration count is <= 0")
    void testGetColorNonPositiveIterations(int iteration) {
        assertEquals(sampleColors.get(0), palette.getColor(iteration));
    }

    @Test
    @DisplayName("getColor should return the exact color corresponding to the given iteration index")
    void testGetColorValidIntermediateIndex() {
        assertEquals(sampleColors.get(0), palette.getColor(0));
        assertEquals(sampleColors.get(1), palette.getColor(1));
        assertEquals(sampleColors.get(2), palette.getColor(2));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 10, 100})
    @DisplayName("getColor should return the last color when iteration count >= colors.size()")
    void testGetColorExceedsListSize(int iteration) {
        assertEquals(sampleColors.get(sampleColors.size() - 1), palette.getColor(iteration));
    }
}
