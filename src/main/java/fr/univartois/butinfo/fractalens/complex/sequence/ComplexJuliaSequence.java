package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;

public class ComplexJuliaSequence implements IComplexSequence{
    IComplex c;
    IComplex z0;
    int max;

    public ComplexJuliaSequence(IComplex prem,IComplex c,int nbmax){
        this.c=c;
        z0=prem;
        max=nbmax;
        iterator();
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
