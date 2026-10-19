package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;

public class ComplexSequence implements IComplexSequence{
    IComplex c;
    Iterator<IComplex> i;
    IComplex z0;
    int max;

    public ComplexSequence(IComplex prem,IComplex c,int nbmax){
        this.c=c;
        z0=prem;
        max=nbmax;
        i=iterator();
    }
    @Override
    public IComplex computeNext(IComplex previous) {
        return (previous.multiply(previous)).add(c);

    }

    @Override
    public Iterator<IComplex> iterator() {
        return new PathComplexSequence(this,max,z0);
    }


}
