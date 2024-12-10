public class Rational implements NumberInterface {
    private int numerator;
    private int denominator;

    public Rational(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    private void simplify() {
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    @Override
    public NumberInterface add(NumberInterface other) {
        if (!(other instanceof Rational)) {
            throw new IllegalArgumentException("Incompatible type");
        }
        Rational o = (Rational) other;
        int newNumerator = this.numerator * o.denominator + o.numerator * this.denominator;
        int newDenominator = this.denominator * o.denominator;
        return new Rational(newNumerator, newDenominator);
    }

    @Override
    public NumberInterface subtract(NumberInterface other) {
        if (!(other instanceof Rational)) {
            throw new IllegalArgumentException("Incompatible type");
        }
        Rational o = (Rational) other;
        int newNumerator = this.numerator * o.denominator - o.numerator * this.denominator;
        int newDenominator = this.denominator * o.denominator;
        return new Rational(newNumerator, newDenominator);
    }

    @Override
    public NumberInterface multiply(NumberInterface other) {
        if (!(other instanceof Rational)) {
            throw new IllegalArgumentException("Incompatible type");
        }
        Rational o = (Rational) other;
        int newNumerator = this.numerator * o.numerator;
        int newDenominator = this.denominator * o.denominator;
        return new Rational(newNumerator, newDenominator);
    }

    @Override
    public NumberInterface divide(NumberInterface other) {
        if (!(other instanceof Rational)) {
            throw new IllegalArgumentException("Incompatible type");
        }
        Rational o = (Rational) other;
        int newNumerator = this.numerator * o.denominator;
        int newDenominator = this.denominator * o.numerator;
        return new Rational(newNumerator, newDenominator);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    public double toDouble() {
        return (double) numerator / denominator;
    }
}
