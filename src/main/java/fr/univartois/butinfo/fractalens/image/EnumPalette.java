package fr.univartois.butinfo.fractalens.image;

import java.awt.Color;
import java.util.function.IntFunction;

/**
 * The EnumPalette enumeration provides predefined color palette configurations
 * for rendering fractals.
 *
 * @author FractaLens Team
 */
public enum EnumPalette {

    /**
     * Grey scale palette.
     */
    GREY_PALETTE(max -> new GreyPalette(max)),

    /**
     * Cosmic fixed palette.
     */
    COSMIC_PALETTE(max -> new FixedPalette(PaletteCollections.cosmicPalette)),

    /**
     * Aurora fixed palette.
     */
    AURORA_PALETTE(max -> new FixedPalette(PaletteCollections.auroraPalette)),

    /**
     * Black and white gradient palette.
     */
    GRADIENT_BLACK_WHITE_PALETTE(max -> new GradientPalette(Color.white, Color.black, max)),

    /**
     * Red and black gradient palette.
     */
    GRADIENT_RED_BLACK_PALETTE(max -> new GradientPalette(Color.red, Color.black, max)),

    /**
     * Green and orange gradient palette.
     */
    GRADIENT_GREEN_ORANGE_PALETTE(max -> new GradientPalette(Color.green, Color.orange, max)),

    /**
     * Pink and red gradient palette.
     */
    GRADIENT_PINK_RED_PALETTE(max -> new GradientPalette(Color.pink, Color.red, max)),

    /**
     * Green and blue gradient palette.
     */
    GRADIENT_GREEN_BLUE_PALETTE(max -> new GradientPalette(Color.green, Color.blue, max)),

    /**
     * Black and green gradient palette.
     */
    GRADIENT_BLACK_GREEN_PALETTE(max -> new GradientPalette(Color.black, Color.green, max));

    /**
     * The factory function creating the palette for a given maximum iteration count.
     */
    private final IntFunction<IPalette> palette;

    /**
     * Constructs a new palette enumeration constant.
     *
     * @param palette The function instantiating the palette.
     */
    EnumPalette(IntFunction<IPalette> palette) {
        this.palette = palette;
    }

    /**
     * Creates the color palette instance configured with the maximum number of iterations.
     *
     * @param max The maximum number of iterations.
     *
     * @return The configured {@link IPalette} instance.
     */
    public IPalette create(int max) {
        return palette.apply(max);
    }
}
