package frecuencias;

public class NumeroComplejo {

    private double real;
    private double imaginaria;

    public NumeroComplejo(double real, double imaginaria) {
        this.real = real;
        this.imaginaria = imaginaria;
    }

    public NumeroComplejo(NumeroComplejo complejo) {
        this(complejo.getParteReal(), complejo.getParteImaginaria());
    }

    /**
     * @return the real
     */
    public double getParteReal() {
        return real;
    }

    /**
     * @return the imaginaria
     */
    public double getParteImaginaria() {
        return imaginaria;
    }

    public NumeroComplejo sumar(NumeroComplejo segundo) {
        //obtiene la referencia al objeto
        NumeroComplejo primero = this;
        double real = primero.real + segundo.real;
        double imag = primero.imaginaria + segundo.imaginaria;
        return new NumeroComplejo(real, imag);
    }

    public NumeroComplejo producto(double multiplicador) {
        return new NumeroComplejo(real * multiplicador, imaginaria * multiplicador);
    }

    public NumeroComplejo multiplicar(NumeroComplejo a, NumeroComplejo b) {
        double real = (a.real * b.real) - (a.imaginaria * b.imaginaria);
        double imaginaria = (a.real * b.imaginaria) + (a.imaginaria * b.real);
        return new NumeroComplejo(real, imaginaria);
    }
}
