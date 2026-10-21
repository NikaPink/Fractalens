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
    COSMIC_PALETTE(max  -> new FixedPalette(Main.cosmicPalette)),
    /**
     * Gradient palette
     */
    GRADIENT_NOIR_BLANC_PALETTE(max -> new GradientPalette( Color.white,Color.black,max)),
    /**
     * Gradient palette
     */
    GRADIENT_ROUGE_NOIR_PALETTE(max -> new GradientPalette(Color.red, Color.black,max));;

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
