package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PathComplexSequence implements Iterator<IComplex> {
    private final IComplexSequence sequence;
    private final int maxIterations;
    private IComplex current;
    private int count;

    public PathComplexSequence(IComplexSequence sequence, int maxIterations,IComplex prem) {
        this.current=prem;
        this.sequence = sequence;
        this.maxIterations = maxIterations;
    }

    @Override
    public boolean hasNext() {
        return count < maxIterations && current.abs() <= 2;
    }

    @Override
    public IComplex next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        IComplex result = current;
        current = sequence.computeNext(current);
        count++;
        return result;
    }

}
