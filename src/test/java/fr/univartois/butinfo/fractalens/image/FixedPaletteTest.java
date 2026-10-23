package fr.univartois.butinfo.fractalens.image;

import java.awt.Color;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for the {@link FixedPalette} class.
 *
 * @author Fractalens Team
 */
class FixedPaletteTest {

    private List<Color> sampleColors;
    private FixedPalette palette;

    @BeforeEach
    void setUp() {
        sampleColors = List.of(
                Color.RED,
                Color.GREEN,
                Color.BLUE
        );
        palette = new FixedPalette(sampleColors);
    }

    @Test
    @DisplayName("getColor should not return null")
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
    @DisplayName("getColor should return exact color for indices inside list boundaries")
    void testGetColorInBounds() {
        assertEquals(sampleColors.get(0), palette.getColor(0));
        assertEquals(sampleColors.get(1), palette.getColor(1));
        assertEquals(sampleColors.get(2), palette.getColor(2));
    }

    @ParameterizedTest
    @CsvSource({
            "3, 0",
            "4, 1",
            "5, 2",
            "6, 0",
            "10, 1"
    })
    @DisplayName("getColor should cycle through colors using modulo when iteration >= colors.size()")
    void testGetColorModuloCycle(int iterationNumber, int expectedIndex) {
        Color expectedColor = sampleColors.get(expectedIndex);
        assertEquals(expectedColor, palette.getColor(iterationNumber));
    }
}
