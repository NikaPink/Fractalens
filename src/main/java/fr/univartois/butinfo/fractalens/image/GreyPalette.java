package fr.univartois.butinfo.fractalens.image;

import java.awt.Color;

/**
 * The GreyPalette class creates shades of grey based on the ratio between the pixel's iteration count
 * and the maximum number of iterations.
 *
 * @author FractaLens Team
 */
public class GreyPalette implements IPalette {

    /**
     * The maximum number of iterations.
     */
    private final int maxIteration;

    /**
     * Constructs a new GreyPalette with the given maximum iteration limit.
     *
     * @param maxIteration The maximum number of iterations.
     */
    public GreyPalette(int maxIteration) {
        this.maxIteration = maxIteration;
    }

    /**
     * Computes the grey color corresponding to the given iteration count.
     *
     * @param iterationNumber The number of iterations for the pixel.
     *
     * @return The grey color for the pixel.
     */
    @Override
    public Color getColor(int iterationNumber) {
        float c = 1 - ((float) iterationNumber / maxIteration);
        return new Color(c, c, c);
    }
}
