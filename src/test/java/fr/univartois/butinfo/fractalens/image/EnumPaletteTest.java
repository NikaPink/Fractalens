package fr.univartois.butinfo.fractalens.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests unitaires pour EnumPalette")
class EnumPaletteTest {

    @Test
    @DisplayName("Vérne la création de la palette GREY_PALETTE")
    void testCreateGreyPalette() {
        IPalette palette = EnumPalette.GREY_PALETTE.create(255);
        assertNotNull(palette, "La palette GREY_PALETTE ne doit pas être nulle");
        // Vous pouvez ajouter des vérifications spécifiques à GreyPalette si nécessaire
    }

    @Test
    @DisplayName("Vérifie la création de la palette COSMIC_PALETTE")
    void testCreateCosmicPalette() {
        IPalette palette = EnumPalette.COSMIC_PALETTE.create(255);
        assertNotNull(palette, "La palette COSMIC_PALETTE ne doit pas être nulle");
    }

    @Test
    @DisplayName("Vérifie la création de la palette AURORA_PALETTE")
    void testCreateAuroraPalette() {
        IPalette palette = EnumPalette.AURORA_PALETTE.create(250);
        assertNotNull(palette, "La palette AURORA_PALETTE ne doit pas être nulle");
        assertEquals(java.awt.Color.BLACK, palette.getColor(250),
                "L'intérieur de la fractale (250 itérations) doit être noir");
    }

    @Test
    @DisplayName("Vérifie la création de la palette GRADIENT_NOIR_BLANC_PALETTE")
    void testCreateGradientNoirBlancPalette() {
        IPalette palette = EnumPalette.GRADIENT_NOIR_BLANC_PALETTE.create(255);
        assertNotNull(palette, "La palette GRADIENT_NOIR_BLANC_PALETTE ne doit pas être nulle");
    }

    @Test
    @DisplayName("Vérifie la création de la palette GRADIENT_ROUGE_NOIR_PALETTE")
    void testCreateGradientRougeNoirPalette() {
        IPalette palette = EnumPalette.GRADIENT_ROUGE_NOIR_PALETTE.create(255);
        assertNotNull(palette, "La palette GRADIENT_ROUGE_NOIR_PALETTE ne doit pas être nulle");
    }

    @ParameterizedTest
    @EnumSource(EnumPalette.class)
    @DisplayName("Vérifie que toutes les palettes retournent une instance non nulle pour différentes valeurs de max")
    void testAllPalettesCreation(EnumPalette enumPalette) {
        IPalette palette = enumPalette.create(100);
        assertNotNull(palette, "La palette " + enumPalette.name() + " doit retourner un objet non null");
    }

    @Test
    @DisplayName("Vérifie les méthodes standard des énumérations (values et valueOf)")
    void testEnumMethods() {
        EnumPalette[] palettes = EnumPalette.values();
        assertEquals(9, palettes.length, "Il doit y avoir exactement 9 palettes dans l'énumération");

        assertEquals(EnumPalette.GREY_PALETTE, EnumPalette.valueOf("GREY_PALETTE"));
        assertEquals(EnumPalette.COSMIC_PALETTE, EnumPalette.valueOf("COSMIC_PALETTE"));
        assertEquals(EnumPalette.AURORA_PALETTE, EnumPalette.valueOf("AURORA_PALETTE"));
    }
}
