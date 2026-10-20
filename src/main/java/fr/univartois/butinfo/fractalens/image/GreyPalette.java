package fr.univartois.butinfo.fractalens.image;

import java.awt.*;

public class GreyPalette implements IPalette{

    @Override
    /**
     * @param iterationNumber int number of iteration
     *@param maxIteration int maximum number of iteration
     * @return color gray for the pixel
     * */
    public Color getColor(int iterationNumber, int maxIteration) {
        float c= 1 - ((float) iterationNumber / maxIteration);//ratio
        return new Color(c,c,c);
    }
}
