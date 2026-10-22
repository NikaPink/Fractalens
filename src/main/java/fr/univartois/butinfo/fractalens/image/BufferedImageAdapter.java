package fr.univartois.butinfo.fractalens.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.imageio.ImageIO;

/**
 * The {@code BufferedImageAdapter} class adapts a {@link BufferedImage} to the
 * {@link IFractalImage} interface, allowing fractal drawing algorithms to interact with
 * standard AWT images[cite: 40, 74, 75].
 *
 * @author YourName
 *
 * @version 0.1.0
 */
public class BufferedImageAdapter implements IFractalImage {

    /**
     * The adapted buffered image instance.
     */
    private final BufferedImage image;

    /**
     * Constructs a new adapter wrapping an existing {@link BufferedImage}.
     *
     * @param image The buffered image to adapt[cite: 74].
     */
    public BufferedImageAdapter(BufferedImage image) {
        this.image = image;
    }

    /**
     * Constructs a new adapter by instantiating a default RGB {@link BufferedImage} with
     * the specified dimensions[cite: 74].
     *
     * @param width The width of the image, measured in pixels[cite: 74, 75].
     * @param height The height of the image, measured in pixels[cite: 74, 75].
     */
    public BufferedImageAdapter(int width, int height) {
        this(new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB));
    }

    /**
     * Gives the height of this image, measured in pixels[cite: 75].
     *
     * @return The height of this image[cite: 75].
     */
    @Override
    public int getHeight() {
        return image.getHeight();
    }

    /**
     * Gives the width of this image, measured in pixels[cite: 75].
     *
     * @return The width of this image[cite: 75].
     */
    @Override
    public int getWidth() {
        return image.getWidth();
    }

    /**
     * Updates the color of the pixel at the given position in this image[cite: 75].
     *
     * @param row The row (y-axis) of the pixel to update[cite: 40, 75].
     * @param column The column (x-axis) of the pixel to update[cite: 40, 75].
     * @param color The new color for the pixel[cite: 75].
     */
    @Override
    public void setColor(int row, int column, Color color) {
        image.setRGB(column, row, color.getRGB());
    }

    /**
     * Saves this image to a file in PNG format[cite: 74, 75].
     *
     * @param path The path of the file in which to save this image[cite: 75].
     *
     * @throws IOException If an I/O error occurs when writing the file[cite: 75].
     */
    @Override
    public void saveAs(String path) throws IOException {
        File file = new File(path);
        String[] str = path.split("\\.");
        String extension = str[str.length - 1];

        ImageIO.write(image, extension, file);
    }

    /**
     * Returns the underlying {@link java.awt.image.BufferedImage} wrapped by this fractal image.
     * This method allows interoperability with standard Java external libraries,
     * such as the GIF encoder, which require native image formats.
     *
     * @return The underlying BufferedImage instance.
     */
    @Override
    public BufferedImage asBufferedImage() {
        return image;
    }
}
