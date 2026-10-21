package fr.univartois.butinfo.fractalens.image;

import fr.univartois.butinfo.fractalens.Main;

import java.awt.*;
import java.util.function.IntFunction;

public enum EnumPalette {
    /**
     * Grey palette
     */
    GREY_PALETTE(max -> new GreyPalette(max)),
    /**
     * Cosmic palette
     */
    COSMIC_PALETTE(max  -> new FixedPalette(PaletteCollections.cosmicPalette)),

    /**
     * Aurora palette
     */
    AURORA_PALETTE(max  -> new FixedPalette(PaletteCollections.auroraPalette)),
    /**
     * Gradient palette
     */
    GRADIENT_NOIR_BLANC_PALETTE(max -> new GradientPalette( Color.white,Color.black,max)),
    /**
     * Gradient palette
     */
    GRADIENT_ROUGE_NOIR_PALETTE(max -> new GradientPalette(Color.red, Color.black,max)),
    /**
     * Gradient palette
     */
    GRADIENT_VERT_ORANGE_PALETTE(max -> new GradientPalette( Color.green,Color.orange,max)),
    /**
     * Gradient palette
     */
    GRADIENT_PINK_RED_PALETTE(max -> new GradientPalette(Color.pink, Color.red,max)),
    /**
     * Gradient palette
     */
    GRADIENT_GREEN_BLUE_PALETTE(max -> new GradientPalette( Color.green,Color.blue,max)),
    /**
     * Gradient palette
     */
    GRADIENT_NOIR_VERT_PALETTE(max -> new GradientPalette(Color.black, Color.green,max));
    /**
     * palette
     */
    private IntFunction< IPalette> palette;
    EnumPalette(IntFunction<IPalette> palette){
        this.palette=palette;
    }

    /**
     * Create the palette
     * @param max
     * @return
     */
    public IPalette create(int max) {
        return palette.apply(max);
    }
}
