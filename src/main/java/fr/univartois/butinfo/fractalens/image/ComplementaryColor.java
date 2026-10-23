package fr.univartois.butinfo.fractalens.image;

import java.awt.Color;

/**
 * The ComplementaryColor class is a decorator of {@link IPalette} that returns the
 * complementary of the color returned by the decorated palette.
 *
 * @author FractaLens Team
 */
public class ComplementaryColor implements IPalette {

    /**
     * The decorated color palette.
     */
    private final IPalette palette;

    /**
     * Constructs a new complementary color palette.
     *
     * @param palette The color palette to decorate.
     */
    public ComplementaryColor(IPalette palette) {
        this.palette = palette;
    }

    /**
     * Gives the complementary of the color of the decorated palette.
     *
     * @param iterationNumber The number of iterations for the pixel.
     *
     * @return The complementary color for the pixel.
     */
    @Override
    public Color getColor(int iterationNumber) {
        Color color = palette.getColor(iterationNumber);
        return new Color(
                255 - color.getRed(),
                255 - color.getGreen(),
                255 - color.getBlue());
    }

}
