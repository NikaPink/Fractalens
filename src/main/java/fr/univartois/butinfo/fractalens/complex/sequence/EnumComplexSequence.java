package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.function.BiFunction;
import java.util.function.Function;

public enum EnumComplexSequence {

    /**
     * Represents the Julia sequence.
     */
    JULIA(c -> (z0, maxIterations) -> new ComplexJuliaSequence(z0, c, maxIterations)),

    /**
     * Represents the Mandelbrot sequence.
     */
    MANDELBROT(c -> (z0, maxIterations) -> new ComplexSequenceMandelbrot(z0, maxIterations)),

    /**
     * Represents the Tricorn (or Mandelbar) sequence.
     */
    TRICORN(c -> (z0, maxIterations) -> new ComplexSequenceTricorn(z0, c, maxIterations)),

    /**
     * Represents the Burning Ship sequence.
     */
    BURNING_SHIP(c -> (z0, maxIterations) -> ComplexSequenceGeneral.burningShip(z0, c, maxIterations)),

    /**
     * Represents the Black Snowflake sequence.
     */
    BLACK_SNOWFLAKE(c -> (z0, maxIterations) -> ComplexSequenceGeneral.blackSnowFlake(z0, maxIterations));

    /**
     * The factory function that binds the sequence to its specific instantiation logic.
     */
    private final Function<IComplex, BiFunction<IComplex, Integer, IComplexSequence>> factory;

    /**
     * Constructs a new sequence enumeration constant with its specific factory.
     */
    EnumComplexSequence(
            Function<IComplex, BiFunction<IComplex, Integer, IComplexSequence>> factory) {
        this.factory = factory;
    }

    /**
     * Retrieves the builder function for the sequence, initialized with the given complex constant.
     *
     * @param c The complex constant associated with the sequence.
     */
    public BiFunction<IComplex, Integer, IComplexSequence> getFunction(IComplex c) {
        return factory.apply(c);
    }

}
