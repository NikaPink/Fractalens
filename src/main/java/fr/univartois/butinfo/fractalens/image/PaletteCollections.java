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

    static final List<Color> emeraldAbyssPalette = List.of(
            new Color(  2,   7,  16),
            new Color(  3,  13,  27),
            new Color(  4,  21,  38),
            new Color(  5,  30,  48),
            new Color(  5,  40,  57),
            new Color(  5,  51,  64),
            new Color(  5,  62,  69),
            new Color(  5,  74,  72),

            new Color(  5,  85,  77),
            new Color(  5,  96,  81),
            new Color(  6, 108,  86),
            new Color(  7, 120,  91),
            new Color(  9, 132,  96),
            new Color( 12, 145, 102),
            new Color( 16, 158, 109),
            new Color( 21, 171, 116),

            new Color( 27, 183, 123),
            new Color( 35, 195, 131),
            new Color( 45, 207, 140),
            new Color( 58, 218, 151),
            new Color( 75, 228, 163),
            new Color( 95, 237, 177),
            new Color(120, 245, 193),
            new Color(151, 251, 211),

            new Color(179, 255, 226),
            new Color(199, 255, 236),
            new Color(216, 255, 243),
            new Color(232, 255, 249),
            new Color(245, 255, 253),
            new Color(218, 255, 255),
            new Color(186, 249, 255),
            new Color(150, 239, 255),

            new Color(116, 226, 255),
            new Color( 81, 211, 255),
            new Color( 51, 194, 251),
            new Color( 30, 175, 239),
            new Color( 19, 155, 223),
            new Color( 14, 135, 205),
            new Color( 14, 115, 184),
            new Color( 17,  96, 161),

            new Color( 20,  79, 139),
            new Color( 22,  64, 118),
            new Color( 22,  52,  99),
            new Color( 21,  42,  81),
            new Color( 19,  33,  64),
            new Color( 16,  26,  49),
            new Color( 12,  20,  37),
            new Color(  8,  15,  28),

            new Color(  5,  20,  30),
            new Color(  4,  31,  38),
            new Color(  4,  44,  46),
            new Color(  4,  58,  53),
            new Color(  5,  73,  60),
            new Color(  7,  89,  68),
            new Color( 10, 106,  77),
            new Color( 15, 124,  88),

            new Color( 21, 142, 101),
            new Color( 29, 157, 116),
            new Color( 30, 143, 122),
            new Color( 27, 117, 119),
            new Color( 20,  86, 104),
            new Color( 13,  55,  78),
            new Color(  7,  28,  46),
            new Color(  2,   7,  16)
    );


    static final List<Color> nebulaDreamPalette = List.of(
            new Color(  2,   3,  15),
            new Color(  5,   9,  29),
            new Color(  8,  17,  45),
            new Color( 12,  27,  62),
            new Color( 16,  39,  79),
            new Color( 20,  52,  96),
            new Color( 23,  66, 112),
            new Color( 25,  81, 127),

            new Color( 26,  98, 144),
            new Color( 27, 116, 160),
            new Color( 30, 136, 176),
            new Color( 37, 157, 190),
            new Color( 51, 179, 202),
            new Color( 77, 199, 212),
            new Color(111, 218, 223),
            new Color(150, 234, 234),

            new Color(183, 246, 242),
            new Color(207, 255, 247),
            new Color(224, 255, 235),
            new Color(205, 255, 211),
            new Color(169, 247, 187),
            new Color(126, 231, 165),
            new Color( 88, 211, 151),
            new Color( 58, 188, 145),

            new Color( 35, 163, 140),
            new Color( 23, 140, 137),
            new Color( 20, 119, 137),
            new Color( 26, 101, 143),
            new Color( 39,  84, 151),
            new Color( 57,  69, 160),
            new Color( 79,  59, 169),
            new Color(104,  54, 177),

            new Color(130,  53, 183),
            new Color(154,  57, 187),
            new Color(177,  65, 187),
            new Color(198,  78, 183),
            new Color(216,  95, 180),
            new Color(231, 115, 181),
            new Color(241, 139, 190),
            new Color(247, 164, 203),

            new Color(250, 187, 218),
            new Color(250, 205, 231),
            new Color(244, 216, 245),
            new Color(229, 207, 255),
            new Color(208, 187, 255),
            new Color(183, 163, 249),
            new Color(157, 140, 231),
            new Color(132, 119, 207),

            new Color(109, 100, 183),
            new Color( 88,  84, 158),
            new Color( 70,  71, 133),
            new Color( 54,  60, 109),
            new Color( 41,  49,  87),
            new Color( 30,  38,  67),
            new Color( 19,  27,  47),
            new Color( 10,  15,  30),

            new Color(  7,  12,  27),
            new Color(  9,  19,  39),
            new Color( 13,  31,  54),
            new Color( 19,  48,  72),
            new Color( 26,  67,  92),
            new Color( 25,  81, 127),
            new Color( 12,  27,  62),
            new Color(  2,   3,  15)
    );

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
