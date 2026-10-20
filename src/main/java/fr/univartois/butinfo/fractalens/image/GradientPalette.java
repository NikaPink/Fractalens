package fr.univartois.butinfo.fractalens.image;

import java.awt.*;

/**
 * Palette for a gradient
 */
public class GradientPalette implements IPalette{

    /**
     * The first color
     */
    private Color c1;
    /**
     * The other color
     */
    private Color c2;
    /**
     * The maximum number of iteration
     */
    private int maxIteration;

    /**
     * Constructor
     * @param c1 first color
     * @param c2 second color
     * @param maxIt maximum number of iteration
     */
    public GradientPalette(Color c1,Color c2,int maxIt){
        this.c1=c1;
        this.c2=c2;
        this.maxIteration=maxIt;
    }

    /**
     * The color for the pixel
     * @param iterationNumber number of iteration
     * @return the color
     */
    @Override
    public Color getColor(int iterationNumber) {
        int r= (int) ( c1.getRed() + ((float) iterationNumber / maxIteration) * (c2.getRed() - c1.getRed()));
        int g= (int) (c1.getGreen() + ((float) iterationNumber / maxIteration) * (c2.getGreen() - c1.getGreen()));
        int b= (int) (c1.getBlue() + ((float) iterationNumber / maxIteration) * (c2.getBlue() - c1.getBlue()));
        return new Color(r,g,b);
    }

}
