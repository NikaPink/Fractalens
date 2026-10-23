package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;

/**
 * The ComplexJuliaSequence class represents a mathematical sequence used for generating Julia fractals.
 * The sequence is defined as z_{n+1} = z_n^2 + c.
 *
 * @author FractaLens Team
 */
public class ComplexJuliaSequence implements IComplexSequence {

    /**
     * The complex constant c added at each iteration.
     */
    private final IComplex c;

    /**
     * The first term of the sequence (initial value z0).
     */
    private final IComplex z0;

    /**
     * The maximum number of iterations.
     */
    private final int max;

    /**
     * Constructs a new ComplexJuliaSequence.
     *
     * @param first The first term of the sequence.
     * @param c The complex constant c.
     * @param maxIterations The maximum number of iterations.
     */
    public ComplexJuliaSequence(IComplex first, IComplex c, int maxIterations) {
        this.c = c;
        this.z0 = first;
        this.max = maxIterations;
        iterator();
    }

    /**
     * Computes the next term of the sequence from the previous one: z_{n+1} = z_n^2 + c.
     *
     * @param previous The previous value in the sequence.
     *
     * @return The next value in the sequence.
     */
    @Override
    public IComplex computeNext(IComplex previous) {
        return (previous.multiply(previous)).add(c);
    }

    /**
     * Creates an iterator for iterating over the terms of the sequence.
     *
     * @return A new iterator for this complex sequence.
     */
    @Override
    public Iterator<IComplex> iterator() {
        return new PathComplexSequenceIterator(this, max, z0);
    }
}
