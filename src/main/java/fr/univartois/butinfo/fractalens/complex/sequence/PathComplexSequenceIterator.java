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

    /**
     * Checks whether there are more terms to compute in the sequence.
     * The iteration continues while the iteration limit is not reached and |current| &lt;= 2.
     *
     * @return {@code true} if the iteration has more elements; {@code false} otherwise.
     */
    @Override
    public boolean hasNext() {
        return count < maxIterations && current.abs() <= 2;
    }

    /**
     * Computes and returns the next term in the complex sequence.
     *
     * @return The next complex number in the sequence.
     *
     * @throws NoSuchElementException If the iteration has no more elements.
     */
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
