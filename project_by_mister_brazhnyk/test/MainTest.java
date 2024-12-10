import java.math.BigInteger;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        Rational r1 = new Rational(3, 4);
        Rational r2 = new Rational(2, 3);
        System.out.println("Rational Addition: " + r1.add(r2).toString());
        System.out.println("Rational Subtraction: " + r1.subtract(r2).toString());
        System.out.println("Rational Multiplication: " + r1.multiply(r2).toString());
        System.out.println("Rational Division: " + r1.divide(r2).toString());

        Unsigned u1 = new Unsigned(10);
        Unsigned u2 = new Unsigned(15);
        System.out.println("Unsigned Addition: " + u1.add(u2).toString());
        System.out.println("Unsigned Subtraction: " + u1.subtract(u2).toString());
        System.out.println("Unsigned Multiplication: " + u1.multiply(u2).toString());
        System.out.println("Unsigned Division: " + u1.divide(u2).toString());

        BigUnsigned bu1 = new BigUnsigned(BigInteger.valueOf(1000000000000L));
        BigUnsigned bu2 = new BigUnsigned(BigInteger.valueOf(2000000000000L));
        System.out.println("BigUnsigned Addition: " + bu1.add(bu2).toString());
        System.out.println("BigUnsigned Subtraction: " + bu1.subtract(bu2).toString());
        System.out.println("BigUnsigned Multiplication: " + bu1.multiply(bu2).toString());
        System.out.println("BigUnsigned Division: " + bu1.divide(bu2).toString());

        List<NumberInterface> coefficients = List.of(new Rational(2, 1), new Rational(3, 1), new Rational(1, 1));
        Polynome p = new Polynome(coefficients);
        System.out.println("Polynomial: " + p.toString());
        System.out.println("Polynomial Value at x = 1: " + p.evaluate(new Rational(1, 1)).toString());
        System.out.println("Polynomial Derivative: " + p.derivative().toString());
        System.out.println("Polynomial Integral: " + p.integrate().toString());

        List<NumberInterface> quadCoefficients = List.of(new Rational(-3, 1), new Rational(2, 1), new Rational(1, 1));
        Polynome quad = new Polynome(quadCoefficients);
        System.out.println("Quadratic Polynomial: " + quad.toString());
        System.out.println("Quadratic Polynomial Solutions: " + quad.solveQuadratic());
    }
}
