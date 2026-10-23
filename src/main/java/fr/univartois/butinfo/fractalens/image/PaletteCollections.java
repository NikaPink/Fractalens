package fr.univartois.butinfo.fractalens.image;

import java.awt.Color;
import java.util.List;

/**
 * The PaletteCollections class provides predefined color lists used by {@link FixedPalette}.
 *
 * @author FractaLens Team
 */
public final class PaletteCollections {

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private PaletteCollections() {
        // Utility class
    }

    /**
     * Fixed list of colors forming the cosmic color theme.
     */
    static final List<Color> cosmicPalette = List.of(
            new Color(4, 4, 20),      // 0: Abyssal void (almost black)
            new Color(20, 10, 50),    // 1: Very dark violet
            new Color(55, 15, 90),    // 2: Deep plum
            new Color(100, 20, 110),  // 3: Dark magenta
            new Color(160, 30, 110),  // 4: Raspberry pink
            new Color(220, 50, 90),   // 5: Vivid pink
            new Color(250, 100, 60),  // 6: Coral orange
            new Color(255, 170, 40),  // 7: Golden yellow
            new Color(255, 230, 100), // 8: Light yellow
            new Color(255, 255, 220), // 9: Bright white (radiant core)
            new Color(180, 240, 255), // 10: Very light cyan
            new Color(100, 200, 255), // 11: Neon sky blue
            new Color(40, 140, 220),  // 12: Azure blue
            new Color(15, 80, 170),   // 13: Royal blue
            new Color(10, 40, 100),   // 14: Deep dark blue
            new Color(6, 20, 55)      // 15: Smooth transition back to black
    );

    /**
     * Fixed list of 25 colors forming the aurora color theme.
     * With 25 colors, 250 % 25 == 0, so that the interior of a 250-iteration fractal remains black.
     */
    static final List<Color> auroraPalette = List.of(
            new Color(0, 0, 0),       // 0: Black (fractal interior)
            new Color(3, 10, 25),     // 1: Polar night
            new Color(5, 20, 45),     // 2: Night blue
            new Color(6, 35, 65),     // 3: Icy night blue
            new Color(8, 55, 85),     // 4: Petrol blue
            new Color(10, 80, 105),   // 5: Duck blue
            new Color(12, 110, 120),  // 6: Deep teal
            new Color(18, 140, 128),  // 7: Teal
            new Color(35, 170, 135),  // 8: Emerald green
            new Color(70, 200, 145),  // 9: Aurora green
            new Color(120, 225, 160), // 10: Mint green
            new Color(180, 245, 190), // 11: Pale green halo
            new Color(230, 255, 235), // 12: Icy white (peak light)
            new Color(210, 230, 255), // 13: Very light blue
            new Color(175, 190, 250), // 14: Lavender
            new Color(150, 150, 240), // 15: Periwinkle
            new Color(135, 115, 225), // 16: Light violet
            new Color(125, 85, 205),  // 17: Aurora violet
            new Color(115, 60, 180),  // 18: Amethyst
            new Color(100, 40, 150),  // 19: Violet
            new Color(80, 28, 120),   // 20: Deep violet
            new Color(60, 20, 95),    // 21: Plum
            new Color(40, 14, 70),    // 22: Dark indigo
            new Color(22, 10, 48),    // 23: Night indigo
            new Color(10, 6, 30)      // 24: Return towards night
    );
}
