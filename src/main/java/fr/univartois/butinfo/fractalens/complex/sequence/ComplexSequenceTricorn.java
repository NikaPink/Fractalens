package fr.univartois.butinfo.fractalens.complex.sequence;


import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;

/**
 * The ComplexSequenceTricorn class represents the Tricorn (or Mandelbar) sequence,
 * defined by the formula: z_{n+1} = conj(z_n)^2 + c.
 *
 * @author FractaLens Team
 */
public class ComplexSequenceTricorn implements IComplexSequence {

    /**
     * The complex constant of the sequence.
     */
    private final IComplex c;

    /**
     * The first term of the sequence (initial value).
     */
    private final IComplex z0;

    /**
     * The maximum number of terms to compute for this sequence.
     */
    private final int max;

    /**
     * Constructs a new Tricorn sequence.
     *
     * @param first The first term of the sequence (z0).
     * @param c     The complex constant added at each iteration.
     * @param maxIterations The maximum number of iterations.
     */
    public ComplexSequenceTricorn(IComplex first, IComplex c, int maxIterations) {
        this.c = c;
        this.z0 = first;
        this.max = maxIterations;
    }

    /**
     * Computes the next term of the Tricorn sequence.
     * The calculation performed is the square of the previous term's conjugate,
     * to which the constant c is added.
     *
     * @param previous The previous term in the sequence.
     * @return The calculated next term.
     */
    @Override
    public IComplex computeNext(IComplex previous) {
        IComplex conjugate = previous.conjugate();
        return (conjugate.multiply(conjugate)).add(c);
    }

    /**
     * Returns an iterator to traverse the terms of the sequence.
     *
     * @return An iterator over the complex numbers of the sequence.
     */
    @Override
    public Iterator<IComplex> iterator() {
        return new PathComplexSequenceIterator(this, max, z0);
    }

}
