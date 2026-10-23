package fr.univartois.butinfo.fractalens.image;

import java.awt.Color;
import java.util.List;

/**
 * Each iteration count maps to a specific color in the palette.
 *
 * @author Fractalens Team
 * @version 1.0
 */
public class FixedPalette implements IPalette {
    private List<Color> colors;

    public FixedPalette(List<Color> colors) {
        this.colors = colors;
    }

    /**
     * If the iteration number is less than or equal to zero, the first color is returned.
     * If it exceeds or equals the palette size, the last color is returned.
     *
     * @param iterationNumber the number of iterations computed for a pixel
     * @return the corresponding {@link Color} from the palette
     */
    @Override
    public Color getColor(int iterationNumber) {
        if (iterationNumber <= 0) {
            return colors.get(0);
        }
        if (iterationNumber >= colors.size()) {
            return colors.get(iterationNumber%colors.size());
        }
        return colors.get(iterationNumber);
    }
}
