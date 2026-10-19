package fr.univartois.butinfo.fractalens.complex;

/**
 * The ComplexPlane class represents a plane in the complex plane.
 */
public class ComplexPlane implements IComplexPlane{

    private final int width;
    private final int height;

    /**
     * Constructor.
     * @param height
     * @param width
     */
    public ComplexPlane(int height, int width) {
        this.width = width;
        this.height = height;
    }

    /**
     *Creates a complex number from a pixel position in the image.
     *
     * @param row The row of the pixel in the image.
     * @param column The column of the pixel in the image.
     *
     * @return the complex number at the given position.
     */
    @Override
    public IComplex asComplex(int row, int column) {
        double a = column - ((double)width/2);
        double b = ((double)height/2)-row;
        return new Complex(a, b);
    }
}
