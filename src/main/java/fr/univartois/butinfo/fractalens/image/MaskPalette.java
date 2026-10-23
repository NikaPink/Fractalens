package fr.univartois.butinfo.fractalens.image;

import java.awt.Color;

/**
 * The MaskPalette class is a decorator for a palette, which only keeps some of the
 * red, green and blue components of the colors given by the decorated palette.
 *
 * @author FractaLens Team
 */
public class MaskPalette implements IPalette {

    /**
     * The palette decorated by this mask.
     */
    private final IPalette palette;

    /**
     * Whether the red component is kept.
     */
    private final boolean red;

    /**
     * Whether the green component is kept.
     */
    private final boolean green;

    /**
     * Whether the blue component is kept.
     */
    private final boolean blue;

    /**
     * Constructor.
     *
     * @param palette The palette to decorate.
     * @param red Whether the red component is kept.
     * @param green Whether the green component is kept.
     * @param blue Whether the blue component is kept.
     */
    public MaskPalette(IPalette palette, boolean red, boolean green, boolean blue) {
        this.palette = palette;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Gives the color of the decorated palette, in which the removed components are
     * set to 0.
     *
     * @param iterationNumber The number of iterations.
     *
     * @return The masked color.
     */
    @Override
    public Color getColor(int iterationNumber) {
        Color color = palette.getColor(iterationNumber);
        return new Color(
                red ? color.getRed() : 0,
                green ? color.getGreen() : 0,
                blue ? color.getBlue() : 0);
    }
}
