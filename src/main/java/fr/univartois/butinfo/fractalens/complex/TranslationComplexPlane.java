package fr.univartois.butinfo.fractalens.complex;

/**
 * The TranslationComplexPlane class decorates an {@link IComplexPlane} to apply
 * a translation offset to each complex coordinate.
 *
 * @author FractaLens Team
 */
public class TranslationComplexPlane implements IComplexPlane {

    /** The original complex plane, decorated. */
    private final IComplexPlane plane;

    /** The complex translation offset applied to the plane. */
    private final Complex translation;

    /**
     * Constructs a translated complex plane from an existing plane.
     *
     * @param plane The base complex plane to be decorated.
     * @param translation The complex offset to add to the complex numbers.
     */
    public TranslationComplexPlane(IComplexPlane plane, Complex translation) {
        this.plane = plane;
        this.translation = translation;
    }

    /**
     * Obtains the complex number from the original plane and adds the translation offset.
     *
     * @param row The row of the pixel in the image.
     * @param column The column of the pixel in the image.
     * @return The complex number corresponding to the pixel, adjusted by the translation.
     */
    @Override
    public IComplex asComplex(int row, int column) {
        return plane.asComplex(row, column).add(translation);
    }
}
