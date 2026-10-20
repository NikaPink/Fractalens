package fr.univartois.butinfo.fractalens.complex;

public class ZoomableComplexPlane implements IComplexPlane {

    /** The original complex plane, decorated */
    private final IComplexPlane plane;

    /** The scale factor applied to the plan */
    private final double zoomFactor;

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
}
