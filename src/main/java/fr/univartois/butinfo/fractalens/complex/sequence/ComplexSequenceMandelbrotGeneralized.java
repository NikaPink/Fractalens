package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * The ComplexSequenceMandelbrotGeneralized class represents the generalized Mandelbrot's
 * sequence, i.e. a {@link ComplexSequenceJuliaGeneralized} whose first term is also its
 * constant.
 *
 * @author FractaLens Team
 */
public class ComplexSequenceMandelbrotGeneralized extends ComplexSequenceJuliaGeneralized {

    /**
     * Creates a new ComplexSequenceMandelbrotGeneralized.
     *
     * @param c The point of the plane, used both as the first term and the constant.
     * @param maxTerms The maximum number of terms to compute.
     * @param power The power applied to the previous term.
     */
    public ComplexSequenceMandelbrotGeneralized(IComplex c, int maxTerms, int power) {
        super(c, c, maxTerms, power);
    }

}
