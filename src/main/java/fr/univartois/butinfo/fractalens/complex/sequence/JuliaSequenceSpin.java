package fr.univartois.butinfo.fractalens.complex.sequence;

import fr.univartois.butinfo.fractalens.complex.Complex;
import fr.univartois.butinfo.fractalens.complex.IComplex;

/**
 * Julia sequence whose constant c is rotated by an angle theta:
 * z_{n+1} = z_n^2 + c * (cos(theta) + i sin(theta)).
 */
public class JuliaSequenceSpin extends ComplexJuliaSequence {

    /**
     * constructor
     *
     * @param prem first term of the sequence
     * @param c constante c (before rotation)
     * @param theta rotation angle, between 0 and 2*PI
     * @param nbmax maximum number of iteration
     */
    public JuliaSequenceSpin(IComplex prem, IComplex c, double theta, int nbmax) {
        super(prem, rotate(c, theta), nbmax);
    }

    /**
     * Multiplies c by cos(theta) + i sin(theta).
     *
     * @param c the constant to rotate
     * @param theta the rotation angle
     * @return the rotated constant
     */
    private static IComplex rotate(IComplex c, double theta) {
        return c.multiply(new Complex(Math.cos(theta), Math.sin(theta)));
    }
}
