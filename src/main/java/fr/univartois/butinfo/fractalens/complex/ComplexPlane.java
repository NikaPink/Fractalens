package fr.univartois.butinfo.fractalens.complex;

/**
 * The ComplexPlane class represents a plane in the complex plane, linking image pixels to complex coordinates.
 *
 * @author FractaLens Team
 */
public class ComplexPlane implements IComplexPlane {

    /** The width of the image. */
    private final int width;

    /** The height of the image. */
    private final int height;

    /**
     * Constructs a new complex plane with given height and width dimensions.
     *
     * @param height The height of the complex plane in pixels.
     * @param width The width of the complex plane in pixels.
     */
    public ComplexPlane(int height, int width) {
        this.width = width;
        this.height = height;
    }

    /**
     * Creates a complex number from a pixel position in the image.
     *
     * @param row The row of the pixel in the image.
     * @param column The column of the pixel in the image.
     *
     * @return The complex number at the given position.
     */
    @Override
    public IComplex asComplex(int row, int column) {
        double a = column - ((double) width / 2);
        double b = ((double) height / 2) - row;
        return new Complex(a, b);
    }
}
