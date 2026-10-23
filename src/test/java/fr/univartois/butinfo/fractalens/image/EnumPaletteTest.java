package fr.univartois.butinfo.fractalens.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link EnumPalette} enumeration.
 *
 * @author FractaLens Team
 */
@DisplayName("Unit tests for EnumPalette")
class EnumPaletteTest {

    @Test
    @DisplayName("Verify creation of GREY_PALETTE")
    void testCreateGreyPalette() {
        IPalette palette = EnumPalette.GREY_PALETTE.create(255);
        assertNotNull(palette, "GREY_PALETTE must not be null");
    }

    @Test
    @DisplayName("Verify creation of COSMIC_PALETTE")
    void testCreateCosmicPalette() {
        IPalette palette = EnumPalette.COSMIC_PALETTE.create(255);
        assertNotNull(palette, "COSMIC_PALETTE must not be null");
    }

    @Test
    @DisplayName("Verify creation of AURORA_PALETTE")
    void testCreateAuroraPalette() {
        IPalette palette = EnumPalette.AURORA_PALETTE.create(250);
        assertNotNull(palette, "AURORA_PALETTE must not be null");
        assertEquals(java.awt.Color.BLACK, palette.getColor(250),
                "The fractal interior (250 iterations) must be black");
    }

    @Test
    @DisplayName("Verify creation of GRADIENT_BLACK_WHITE_PALETTE")
    void testCreateGradientBlackWhitePalette() {
        IPalette palette = EnumPalette.GRADIENT_BLACK_WHITE_PALETTE.create(255);
        assertNotNull(palette, "GRADIENT_BLACK_WHITE_PALETTE must not be null");
    }

    @Test
    @DisplayName("Verify creation of GRADIENT_RED_BLACK_PALETTE")
    void testCreateGradientRedBlackPalette() {
        IPalette palette = EnumPalette.GRADIENT_RED_BLACK_PALETTE.create(255);
        assertNotNull(palette, "GRADIENT_RED_BLACK_PALETTE must not be null");
    }

    @ParameterizedTest
    @EnumSource(EnumPalette.class)
    @DisplayName("Verify that all palettes return a non-null instance for various max values")
    void testAllPalettesCreation(EnumPalette enumPalette) {
        IPalette palette = enumPalette.create(100);
        assertNotNull(palette, "Palette " + enumPalette.name() + " must return a non-null object");
    }

    @Test
    @DisplayName("Verify standard enum methods (values and valueOf)")
    void testEnumMethods() {
        EnumPalette[] palettes = EnumPalette.values();
        assertEquals(9, palettes.length, "There must be exactly 9 palettes in the enumeration");

        assertEquals(EnumPalette.GREY_PALETTE, EnumPalette.valueOf("GREY_PALETTE"));
        assertEquals(EnumPalette.COSMIC_PALETTE, EnumPalette.valueOf("COSMIC_PALETTE"));
        assertEquals(EnumPalette.AURORA_PALETTE, EnumPalette.valueOf("AURORA_PALETTE"));
    }
}
