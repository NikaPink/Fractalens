package fr.univartois.butinfo.fractalens.image;

import java.awt.Color;
import java.util.List;

/**
 * The FixedPalette class maps each iteration count to a specific color from a fixed list of colors.
 *
 * @author FractaLens Team
 */
public class FixedPalette implements IPalette {

    /**
     * The fixed list of colors representing the palette steps.
     */
    private final List<Color> colors;

    /**
     * Constructs a new FixedPalette with the provided list of colors.
     *
     * @param colors The list of colors.
     */
    public FixedPalette(List<Color> colors) {
        this.colors = colors;
    }

    /**
     * If the iteration number is less than or equal to zero, the first color is returned.
     * If it exceeds or equals the palette size, the last color is returned.
     *
     * @param iterationNumber The number of iterations computed for a pixel.
     *
     * @return The corresponding {@link Color} from the palette.
     */
    @Override
    public Color getColor(int iterationNumber) {
        if (iterationNumber <= 0) {
            return colors.get(0);
        }
        if (iterationNumber >= colors.size()) {
            return colors.get(iterationNumber % colors.size());
        }
        return colors.get(iterationNumber);
    }
}
