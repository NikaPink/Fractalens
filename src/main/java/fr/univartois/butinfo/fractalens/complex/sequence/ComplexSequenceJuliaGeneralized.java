package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;

public class ComplexSequenceJuliaGeneralized implements IComplexSequence{
    IComplex c;
    IComplex z0;
    int max;
    int p;

    public ComplexSequenceJuliaGeneralized(IComplex prem,IComplex c,int nbmax,int p){
        this.c=c;
        z0=prem;
        max=nbmax;
        this.p=p;
        iterator();
    }
    @Override
    public IComplex computeNext(IComplex previous) {
        return (previous.pow(p)).add(c);
    }

    @Override
    public Iterator<IComplex> iterator() {
        return new PathComplexSequenceIterator(this,max,z0);
    }

}
