package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;

public class ComplexSequenceMandelbrot implements IComplexSequence {

    IComplex z0;
    int max;

    public ComplexSequenceMandelbrot(IComplex z0, int max, int nbmax) {
        this.z0=z0;
        this.max=max;
        iterator();
    }
    @Override
    public IComplex computeNext(IComplex previous) {
        return (previous.multiply(previous).add(z0));
    }

    @Override
    public Iterator<IComplex> iterator() {
        return new PathComplexSequenceIterator(this,max,z0);
    }
}
