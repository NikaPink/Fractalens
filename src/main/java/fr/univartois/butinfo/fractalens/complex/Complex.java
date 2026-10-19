package fr.univartois.butinfo.fractalens.complex;

public class Complex implements IComplex
{
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    @Override
    public double getRealPart() {

        return real;
    }

    @Override
    public double getImaginaryPart() {

        return imaginary;
    }

    @Override
    public double abs() {

        return 0;
    }

    @Override
    public IComplex negate() {

        return null;
    }

    @Override
    public IComplex conjugate() {

        return null;
    }

    @Override
    public IComplex add(IComplex other) {

        return null;
    }

    @Override
    public IComplex subtract(IComplex other) {

        return null;
    }

    @Override
    public IComplex multiply(double value) {

        return null;
    }

    @Override
    public IComplex multiply(IComplex other) {

        return null;
    }

    @Override
    public IComplex divide(IComplex other) {

        return null;
    }

}
