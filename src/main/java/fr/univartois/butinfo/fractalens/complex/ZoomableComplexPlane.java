package fr.univartois.butinfo.fractalens.complex;

public class ZoomableComplexPlane implements IComplexPlane {

    /** The original complex plane, decorated */
    private final IComplexPlane plane;

    /** The scale factor applied to the plan */
    private double zoomFactor;

    /**
     * Constructs a zoomable complex plane from an existing plane
     * @param plane The base complex plane to be decorated
     * @param zoomFactor The zoom factor to apply to the complex numbers
     */
    public ZoomableComplexPlane(IComplexPlane plane, double zoomFactor) {
        this.plane = plane;
        this.zoomFactor = zoomFactor;
    }

    /**
     * Obtains the complex number from the original plane and multiplies it by the zoom factor
     * @param row The row of the pixel in the image
     * @param column The column of the pixel in the image
     * @return The complex number corresponding to the pixel, adjusted by the zoom
     */
    @Override
    public IComplex asComplex(int row, int column) {
        return plane.asComplex(row, column).multiply(zoomFactor);
    }

    /**     *
     * @param zoomMultiplier The multiplier to apply to the current zoom factor.
     *                       It must be between 0 and 1 (inclusive).
     * @throws IllegalArgumentException If the value of {@code zoomMultiplier} is
     *                                  strictly less than 0 or strictly greater than 1.
     */
    public void zoomAnimation(double zoomMultiplier) {
        if (zoomMultiplier < 0 || zoomMultiplier > 1) {
            throw new IllegalArgumentException("Zoom multiplier must be between 0 and 1");
        }
        zoomFactor = zoomFactor * zoomMultiplier;
    }
}
