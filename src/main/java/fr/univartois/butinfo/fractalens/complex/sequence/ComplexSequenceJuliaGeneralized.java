package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;

/**
 * The ComplexSequenceJuliaGeneralized class represents a generalized Julia sequence
 * where the previous term is raised to an arbitrary power p: z_{n+1} = z_n^p + c.
 *
 * @author FractaLens Team
 */
public class ComplexSequenceJuliaGeneralized implements IComplexSequence {

    /**
     * The complex constant c.
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
     * The power exponent applied to each term.
     */
    private final int p;

    /**
     * Constructs a new generalized Julia sequence.
     *
     * @param first The first term of the sequence.
     * @param c The complex constant c.
     * @param maxIterations The maximum number of iterations.
     * @param p The power exponent applied to each term.
     */
    public ComplexSequenceJuliaGeneralized(IComplex first, IComplex c, int maxIterations, int p) {
        this.c = c;
        this.z0 = first;
        this.max = maxIterations;
        this.p = p;
        iterator();
    }

    /**
     * Computes the next term of the sequence from the previous one: z_{n+1} = z_n^p + c.
     *
     * @param previous The previous value in the sequence.
     *
     * @return The next value in the sequence.
     */
    @Override
    public IComplex computeNext(IComplex previous) {
        return (previous.pow(p)).add(c);
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
