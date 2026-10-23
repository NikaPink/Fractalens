package fr.univartois.butinfo.fractalens.complex;

import java.util.Objects;

/**
 * The Complex class represents an immutable complex number with real and imaginary parts.
 * It provides standard arithmetic operations such as addition, subtraction, multiplication,
 * division, and exponentiation.
 *
 * @author FractaLens Team
 */
public class Complex implements IComplex {

    /**
     * The real part of the complex number.
     */
    private final double real;

    /**
     * The imaginary part of the complex number.
     */
    private final double imaginary;

    /**
     * Constructs a new complex number with the specified real and imaginary parts.
     *
     * @param real The real part.
     * @param imaginary The imaginary part.
     */
    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Gives the real part of this complex number.
     *
     * @return The real part of this complex number.
     */
    @Override
    public double getRealPart() {
        return real;
    }

    /**
     * Gives the imaginary part of this complex number.
     *
     * @return The imaginary part of this complex number.
     */
    @Override
    public double getImaginaryPart() {
        return imaginary;
    }

    /**
     * Gives the modulus (absolute value) of this complex number.
     *
     * @return The modulus of this complex number.
     */
    @Override
    public double abs() {
        return Math.pow((real * real) + (imaginary * imaginary), 0.5);
    }

    /**
     * Gives the opposite (negation) of this complex number.
     *
     * @return A new complex number representing {@code -this}.
     */
    @Override
    public IComplex negate() {
        return new Complex(-real, -imaginary);
    }

    /**
     * Gives the complex conjugate of this complex number.
     *
     * @return A new complex number representing the conjugate of this complex number.
     */
    @Override
    public IComplex conjugate() {
        return new Complex(real, -imaginary);
    }

    /**
     * Adds this complex number to another one.
     *
     * @param other The complex number to add to this one.
     *
     * @return The sum {@code this + other}.
     */
    @Override
    public IComplex add(IComplex other) {
        return new Complex(real + other.getRealPart(), imaginary + other.getImaginaryPart());
    }

    /**
     * Subtracts another complex number from this one.
     *
     * @param other The complex number to subtract from this one.
     *
     * @return The difference {@code this - other}.
     */
    @Override
    public IComplex subtract(IComplex other) {
        return new Complex(real - other.getRealPart(), imaginary - other.getImaginaryPart());
    }

    /**
     * Multiplies this complex number by a real scalar value.
     *
     * @param value The value by which to multiply this number.
     *
     * @return The result of {@code this * value}.
     */
    @Override
    public IComplex multiply(double value) {
        return new Complex(real * value, imaginary * value);
    }

    /**
     * Multiplies this complex number by another one.
     *
     * @param other The complex number to multiply this one with.
     *
     * @return The product {@code this * other}.
     */
    @Override
    public IComplex multiply(IComplex other) {
        return new Complex(
                real * other.getRealPart() - imaginary * other.getImaginaryPart(),
                real * other.getImaginaryPart() + imaginary * other.getRealPart()
        );
    }

    /**
     * Divides this complex number by another one.
     *
     * @param other The complex number to divide this one by.
     *
     * @return The quotient {@code this / other}.
     */
    @Override
    public IComplex divide(IComplex other) {
        double denominator = other.getImaginaryPart() * other.getImaginaryPart() + other.getRealPart() * other.getRealPart();
        IComplex numerator = multiply(other.conjugate());
        return new Complex(numerator.getRealPart() / denominator, numerator.getImaginaryPart() / denominator);
    }

    /**
     * Returns a string representation of this complex number.
     *
     * @return A string in the format "real + imaginaryi".
     */
    @Override
    public String toString() {
        return real + " + " + imaginary + "i";
    }

    /**
     * Checks whether this complex number is equal to another object within a 1e-9 tolerance.
     *
     * @param obj The object to compare with.
     *
     * @return {@code true} if {@code obj} is an {@link IComplex} with equivalent real and imaginary parts; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IComplex other)) {
            return false;
        }
        return Math.abs(this.real - other.getRealPart()) < 1e-9
               && Math.abs(this.imaginary - other.getImaginaryPart()) < 1e-9;
    }

    /**
     * Computes the hash code of this complex number based on its real and imaginary parts.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(real, imaginary);
    }

    /**
     * Computes the power of this complex number: {@code this^p}.
     *
     * @param p The exponent to which this complex number is raised (non-negative integer).
     *
     * @return A new complex number representing {@code this^p}.
     */
    @Override
    public IComplex pow(int p) {
        if (p == 0) {
            return new Complex(1, 0);
        }
        if (p == 1) {
            return this;
        }
        IComplex c = this;
        for (int i = 2; i <= p; i++) {
            c = c.multiply(this);
        }
        return c;
    }
}
