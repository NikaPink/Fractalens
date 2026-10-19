package fr.univartois.butinfo.fractalens.complex;

import fr.univartois.butinfo.fractalens.complex.sequence.IComplexSequence;

import java.util.Iterator;

public class PathComplexSequence implements Iterator<IComplex> {
    private final IComplexSequence sequence;
    private final int maxIterations;
    private IComplex current;
    private int count;

    public PathComplexSequence(IComplexSequence sequence,
            int maxIterations) {

        this.sequence = sequence;
        this.maxIterations = maxIterations;
    }

    @Override
    public boolean hasNext() {

        return false;
    }

    @Override
    public IComplex next() {

        return null;
    }

}
