package fr.univartois.butinfo.fractalens.complex;

public class ZoomableComplexPlane implements IComplexPlane {

    /** Le plan complexe d'origine décoré */
    private final IComplexPlane plane;

    /** Le facteur d'échelle appliqué au plan */
    private final double zoomFactor;

    /**
     * Construit un plan complexe zoomable à partir d'un plan existant
     * @param plane Le plan complexe de base à décorer
     * @param zoomFactor Le facteur de zoom à appliquer aux nombres complexes
     */
    public ZoomableComplexPlane(IComplexPlane plane, double zoomFactor) {
        this.plane = plane;
        this.zoomFactor = zoomFactor;
    }

    /**
     * Obteint le nombre complexe du plan d'origine et le multiplie par le facteur de zoom
     * @param row La ligne du pixel dans l'image
     * @param column La colonne du pixel dans l'image
     * @return Le nombre complexe correspondant au pixel, ajusté avec le zoom
     */
    @Override
    public IComplex asComplex(int row, int column) {
        return plane.asComplex(row, column).multiply(zoomFactor);
    }
}
