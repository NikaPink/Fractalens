package fr.univartois.butinfo.fractalens.image;

import java.awt.Color;

/**
 * The GradientPalette class generates a linear color gradient between two given colors.
 *
 * @author FractaLens Team
 */
public class GradientPalette implements IPalette {

    /**
     * The first color of the gradient (starting color).
     */
    private final Color c1;

    /**
     * The second color of the gradient (ending color).
     */
    private final Color c2;

    /**
     * The maximum number of iterations.
     */
    private final int maxIteration;

    /**
     * Constructs a new GradientPalette.
     *
     * @param c1 The first color.
     * @param c2 The second color.
     * @param maxIteration The maximum number of iterations.
     */
    public GradientPalette(Color c1, Color c2, int maxIteration) {
        this.c1 = c1;
        this.c2 = c2;
        this.maxIteration = maxIteration;
    }

    /**
     * Computes the interpolated color for the given iteration number.
     *
     * @param iterationNumber The iteration count for the pixel.
     *
     * @return The interpolated {@link Color}.
     */
    @Override
    public Color getColor(int iterationNumber) {
        int r = (int) (c1.getRed() + ((float) iterationNumber / maxIteration) * (c2.getRed() - c1.getRed()));
        int g = (int) (c1.getGreen() + ((float) iterationNumber / maxIteration) * (c2.getGreen() - c1.getGreen()));
        int b = (int) (c1.getBlue() + ((float) iterationNumber / maxIteration) * (c2.getBlue() - c1.getBlue()));
        return new Color(r, g, b);
    }

}
