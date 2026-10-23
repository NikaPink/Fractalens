package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * The ComplexSequenceMandelbrot class represents Mandelbrot's sequence, i.e. a
 * {@link ComplexJuliaSequence} whose first term is also its constant.
 *
 * @author FractaLens Team
 */
public class ComplexSequenceMandelbrot extends ComplexJuliaSequence {

    /**
     * Creates a new ComplexSequenceMandelbrot.
     *
     * @param c The point of the plane, used both as the first term and the constant.
     * @param max The maximum number of terms to compute.
     */
    public ComplexSequenceMandelbrot(IComplex c, int max) {
        super(c, c, max);
    }

}
