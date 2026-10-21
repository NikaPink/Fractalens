package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;

public class ComplexJuliaSequence implements IComplexSequence{
    /**
     * Constante c
     */
    private  IComplex c;
    /**
     * First term of the sequence
     */
    private IComplex z0;

    /**
     * Maximum number of iteration
     */
    private int max;

    /**
     * constructor
     * @param prem
     * @param c
     * @param nbmax
     */
    public ComplexJuliaSequence(IComplex prem,IComplex c,int nbmax){
        this.c=c;
        z0=prem;
        max=nbmax;
        iterator();
    }

    /**
     *
     * @param previous The previous value in the sequence.
     *
     * @return the current value in the sequence
     */
    @Override
    public IComplex computeNext(IComplex previous) {
        return (previous.multiply(previous)).add(c);

    }

    /**
     * create an iterator for the sequence
     * @return a new iterator
     */
    @Override
    public Iterator<IComplex> iterator() {
        return new PathComplexSequenceIterator(this,max,z0);
    }


}
