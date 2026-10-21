package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;

public class ComplexSequence implements IComplexSequence{
    private final IComplex c;
    private final IComplex z0;
    private final int max;

    public ComplexSequence(IComplex prem,IComplex c,int nbmax){
        this.c=c;
        z0=prem;
        max=nbmax;
    }
    @Override
    public IComplex computeNext(IComplex previous) {
        return (previous.multiply(previous)).add(c);

    }

    @Override
    public Iterator<IComplex> iterator() {
        return new PathComplexSequenceIterator(this,max,z0);
    }


}
