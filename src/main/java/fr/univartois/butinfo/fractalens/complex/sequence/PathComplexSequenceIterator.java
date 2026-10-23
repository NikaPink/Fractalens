package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The PathComplexSequenceIterator class provides an iterator over terms of an {@link IComplexSequence}.
 * The iteration stops when either the maximum number of iterations is reached or the modulus exceeds 2 (divergence threshold).
 *
 * @author FractaLens Team
 */
public class PathComplexSequenceIterator implements Iterator<IComplex> {

    /**
     * The sequence whose terms are generated.
     */
    private final IComplexSequence sequence;

    /**
     * The maximum number of iterations allowed.
     */
    private final int maxIterations;

    /**
     * The current term in the iteration.
     */
    private IComplex current;

    /**
     * The number of terms evaluated so far.
     */
    private int count;

    /**
     * Constructs an iterator for the given sequence starting at the initial term.
     *
     * @param sequence The sequence to iterate over.
     * @param maxIterations The maximum number of terms to compute.
     * @param initial The initial term of the sequence.
     */
    public PathComplexSequenceIterator(IComplexSequence sequence, int maxIterations, IComplex initial) {
        this.current = initial;
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
