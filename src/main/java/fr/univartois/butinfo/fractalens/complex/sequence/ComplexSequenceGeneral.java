package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.Iterator;
import java.util.function.BinaryOperator;

/**
 * The ComplexSequenceGeneral class represents a generic complex sequence
 * whose mathematical behavior is defined by a provided binary operator.
 *
 * @author TonNom
 *
 * @version 0.1.0
 */
public class ComplexSequenceGeneral implements IComplexSequence {

    /**
     * The initial term of the sequence.
     */
    private final IComplex z0;

    /**
     * The maximum number of iterations before considering that the sequence diverges.
     */
    private final int max;

    /**
     * The mathematical function used to compute the next term of the sequence.
     */
    private final BinaryOperator<IComplex> formula;

    /**
     * Constructs a new generic complex sequence.
     *
     * @param z0 The initial term of the sequence.
     * @param max The maximum number of iterations.
     * @param formula The mathematical function to compute the next term.
     */
    public ComplexSequenceGeneral(IComplex z0, int max, BinaryOperator<IComplex> formula) {
        this.z0 = z0;
        this.max = max;
        this.formula = formula;
    }

    /**
     * Computes the next term of the sequence using the provided mathematical formula.
     *
     * @param previous The previous term in the sequence.
     *
     * @return The next term of the sequence.
     */
    @Override
    public IComplex computeNext(IComplex previous) {
        return formula.apply(z0, previous);
    }

    /**
     * Returns an iterator to iterate over the terms of the sequence.
     *
     * @return A new iterator for this complex sequence.
     */
    @Override
    public Iterator<IComplex> iterator() {
        return new PathComplexSequenceIterator(this,max,z0);
    }

    /**
     * Creates a Burning Ship sequence. The sequence is defined by the formula:
     * z_{n+1} = (|Re(z_n)| + i|Im(z_n)|)^2 + z_0.
     *
     * @param z0 The initial term of the sequence.
     * @param c
     * @param maxIteration The maximum number of iterations.
     *
     * @return A new instance of ComplexSequenceGeneral configured for the
     * Burning Ship fractal.
     */
    public static ComplexSequenceGeneral burningShip(IComplex z0, Complex c, int maxIteration) {
        return new ComplexSequenceGeneral(
                z0,
                maxIteration,
                (IComplex initial, IComplex prev) -> {
                    double real = Math.abs(prev.getRealPart());
                    double imaginary = Math.abs(prev.getImaginaryPart());

                    IComplex absComplex = new Complex(real, imaginary);

                    return absComplex.multiply(absComplex).add(initial);
                }
        );
    }

    /**
     * Creates a generalized Burning Ship sequence.
     * The sequence is defined by the formula: z_{n+1} = (|Re(z_n)| + i|Im(z_n)|)^p + z_0.
     *
     * @param z0 The initial term of the sequence.
     * @param maxIteration The maximum number of iterations.
     * @param p The exponent to which the complex number is raised.
     *
     * @return A new instance of ComplexSequenceGeneral configured for the generalized Burning Ship fractal.
     */
    public static ComplexSequenceGeneral burningShipGeneralized(IComplex z0, int maxIteration, int p) {
        return new ComplexSequenceGeneral(
                z0,
                maxIteration,
                (IComplex initial, IComplex prev) -> {
                    double real = Math.abs(prev.getRealPart());
                    double imaginary = Math.abs(prev.getImaginaryPart());

                    IComplex absComplex = new Complex(real, imaginary);

                    return absComplex.pow(p).add(initial);
                }
        );
    }


    /**
     * Creates a Black Snowflake sequence.
     * The sequence is defined by the formula: z_{n+1} = z_n^4 + z_n^2 + z_0.
     *
     * @param z0 The initial term of the sequence.
     * @param maxIteration The maximum number of iterations.
     *
     * @return A new instance of ComplexSequenceGeneral configured for the Black Snowflake fractal.
     */
    public static ComplexSequenceGeneral blackSnowFlake(IComplex z0, int maxIteration){
        return new ComplexSequenceGeneral(z0,maxIteration,(IComplex initial,IComplex prev) -> {
            IComplex complex = new Complex(prev.getRealPart(), prev.getImaginaryPart());

            return complex.pow(4).add(complex.pow(2)).add(z0);
        }
        );
    }

}
