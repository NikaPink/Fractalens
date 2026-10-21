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

    public static ComplexSequenceGeneral burningShip(IComplex z0, int maxIteration){
        return new ComplexSequenceGeneral(z0,maxIteration,(IComplex initial,IComplex prev) -> {
            double real = Math.abs(prev.getRealPart());
            double imaginary = Math.abs(prev.getImaginaryPart());

            IComplex absComplex = new Complex(real, imaginary);

            return absComplex.multiply(absComplex).add(z0);
        }
                );
    }

}
