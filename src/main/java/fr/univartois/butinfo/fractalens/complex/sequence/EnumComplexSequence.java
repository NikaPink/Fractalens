package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * The EnumComplexSequence enumeration provides standard fractal complex sequence factories.
 *
 * @author FractaLens Team
 */
public enum EnumComplexSequence {

    /**
     * Represents the classic Julia sequence.
     */
    JULIA(true, c -> (z0, maxIterations) -> new ComplexJuliaSequence(z0, c, maxIterations)),

    /**
     * Represents the generalized Julia sequence with exponent 3.
     */
    JULIA_GENERALIZED_3(true, c -> (z0, maxIterations) -> new ComplexSequenceJuliaGeneralized(z0, c, maxIterations, 3)),

    /**
     * Represents the generalized Julia sequence with exponent 5.
     */
    JULIA_GENERALIZED_5(true, c -> (z0, maxIterations) -> new ComplexSequenceJuliaGeneralized(z0, c, maxIterations, 5)),

    /**
     * Represents the classic Mandelbrot sequence.
     */
    MANDELBROT(false, c -> (z0, maxIterations) -> new ComplexSequenceMandelbrot(z0, maxIterations)),

    /**
     * Represents the generalized Mandelbrot sequence with exponent 3.
     */
    MANDELBROT_GENERALIZED_3(false, c -> (z0, maxIterations) -> new ComplexSequenceMandelbrotGeneralized(z0, maxIterations, 3)),

    /**
     * Represents the generalized Mandelbrot sequence with exponent 5.
     */
    MANDELBROT_GENERALIZED_5(false, c -> (z0, maxIterations) -> new ComplexSequenceMandelbrotGeneralized(z0, maxIterations, 5)),

    /**
     * Represents the Tricorn (or Mandelbar) sequence.
     */
    TRICORN(true, c -> (z0, maxIterations) -> new ComplexSequenceTricorn(z0, c, maxIterations)),

    /**
     * Represents the Burning Ship sequence.
     */
    BURNING_SHIP(false, c -> (z0, maxIterations) -> ComplexSequenceGeneral.burningShip(z0, c, maxIterations)),

    /**
     * Represents the Black Snowflake sequence.
     */
    BLACK_SNOWFLAKE(false, c -> (z0, maxIterations) -> ComplexSequenceGeneral.blackSnowFlake(z0, maxIterations));

    /**
     * Whether the sequence uses the constant c, and can therefore be rotated.
     */
    private final boolean rotatable;

    /**
     * The factory function that binds the sequence to its specific instantiation logic.
     */
    private final Function<IComplex, BiFunction<IComplex, Integer, IComplexSequence>> factory;

    /**
     * Constructs a new sequence enumeration constant with its specific factory.
     *
     * @param rotatable Whether the sequence uses the constant c.
     * @param factory The factory function creating the sequence from c, z0 and maxIterations.
     */
    EnumComplexSequence(boolean rotatable,
            Function<IComplex, BiFunction<IComplex, Integer, IComplexSequence>> factory) {
        this.rotatable = rotatable;
        this.factory = factory;
    }

    /**
     * Tells whether this sequence uses the constant c, so that it can be animated by rotating c.
     *
     * @return true if the sequence uses c, false otherwise.
     */
    public boolean isRotatable() {
        return rotatable;
    }

    /**
     * Retrieves the builder function for the sequence, initialized with the given complex constant.
     *
     * @param c The complex constant associated with the sequence.
     *
     * @return The sequence factory function.
     */
    public BiFunction<IComplex, Integer, IComplexSequence> getFunction(IComplex c) {
        return factory.apply(c);
    }

}
