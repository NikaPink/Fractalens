package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.IComplex;

import java.util.function.BiFunction;
import java.util.function.Function;


public enum EnumComplexSequence {

    JULIA(c -> (z0, maxIterations) -> new ComplexJuliaSequence(z0, c, maxIterations)),

    MANDELBROT(c -> (z0, maxIterations) -> new ComplexSequenceMandelbrot(z0, maxIterations)),

    TRICORN(c -> (z0, maxIterations) -> new ComplexSequenceTricorn(z0, c, maxIterations)),

    BURNING_SHIP(c -> (z0, maxIterations) -> ComplexSequenceGeneral.burningShip(z0, c, maxIterations)),


    BLACK_SNOWFLAKE(c -> (z0, maxIterations) -> ComplexSequenceGeneral.blackSnowFlake(z0, maxIterations));


    private final Function<IComplex, BiFunction<IComplex, Integer, IComplexSequence>> factory;

    EnumComplexSequence(
            Function<IComplex, BiFunction<IComplex, Integer, IComplexSequence>> factory) {
        this.factory = factory;
    }


    public BiFunction<IComplex, Integer, IComplexSequence> getFunction(IComplex c) {
        return factory.apply(c);
    }

}
