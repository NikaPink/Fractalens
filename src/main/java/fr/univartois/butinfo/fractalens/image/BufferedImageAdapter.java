package fr.univartois.butinfo.fractalens.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BufferedImageAdapter implements IFractalImage {

    private BufferedImage image;

    public BufferedImageAdapter(int width, int height) {
        image  = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public void setColor(int row, int column, Color color) {
        image.setRGB(row, column, color.getRGB());
    }

    @Override
    public void saveAs(String path) throws IOException {

    }

}
