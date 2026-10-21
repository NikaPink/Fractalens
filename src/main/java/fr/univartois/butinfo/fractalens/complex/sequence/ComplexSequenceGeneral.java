package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;
import java.util.function.BinaryOperator;

public class ComplexSequenceGeneral implements IComplexSequence {

    private IComplex z0;
    private int max;
    private BinaryOperator<IComplex> formula;

    public ComplexSequenceGeneral(IComplex z0,int max, BinaryOperator<IComplex> formula) {
        this.z0=z0;
        this.max=max;
        this.formula=formula;
    }

    @Override
    public IComplex computeNext(IComplex previous) {
        return formula.apply(z0, previous);
    }

    @Override
    public Iterator<IComplex> iterator() {
        return new PathComplexSequenceIterator(this,max,z0);
    }

    /**
     * Creates a sequence for the Burning Ship fractal: z(n+1) = (|Re(z_n)| +
     * i|Im(z_n)|)^2 + c, starting from z0.
     *
     * @param z0 The first term of the sequence (usually the pixel).
     * @param c The constant number of the sequence.
     * @param maxIteration The maximum number of iterations.
     *
     * @return The created Burning Ship sequence.
     */
    public static ComplexSequenceGeneral burningShip(IComplex z0, IComplex c, int maxIteration) {
        return new ComplexSequenceGeneral(z0, maxIteration, (initial, prev) -> {
            IComplex absComplex = new Complex(
                    Math.abs(prev.getRealPart()),
                    Math.abs(prev.getImaginaryPart()));
            return absComplex.multiply(absComplex).add(c);
        });
    }

}
