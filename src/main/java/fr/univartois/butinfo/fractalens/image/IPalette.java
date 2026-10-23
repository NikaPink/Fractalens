package fr.univartois.butinfo.fractalens.image;

import java.awt.Color;

/**
 * The IPalette interface defines a color palette that associates an iteration count with a {@link Color}.
 *
 * @author FractaLens Team
 */
public interface IPalette {

    /**
     * Determines the color corresponding to a given number of iterations.
     *
     * @param iterationNumber The number of iterations computed for a pixel.
     *
     * @return The color associated with the given iteration count.
     */
    Color getColor(int iterationNumber);

}
