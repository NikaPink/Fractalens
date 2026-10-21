package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;

public class ComplexSequenceTricorn implements IComplexSequence  {

    IComplex c;
    IComplex z0;
    int max;

    public ComplexSequenceTricorn(IComplex prem, IComplex c, int nbmax) {
        this.c = c;
        z0 = prem;
        max = nbmax;
    }

    @Override
    public IComplex computeNext(IComplex previous) {
        IComplex conjugate = previous.conjugate();
        return (conjugate.multiply(conjugate)).add(c);
    }

    @Override
    public Iterator<IComplex> iterator() {
        return new PathComplexSequenceIterator(this,max,z0);
    }

}
