import java.util.ArrayList;
import java.util.List;

public class Polynome implements NumberInterface {
    private List<NumberInterface> coefficients;

    public Polynome() {
        this.coefficients = new ArrayList<>();
    }

    public Polynome(List<NumberInterface> coefficients) {
        this.coefficients = new ArrayList<>(coefficients);
    }

    public Polynome(Polynome other) {
        this.coefficients = new ArrayList<>(other.coefficients);
    }

    @Override
    public NumberInterface add(NumberInterface other) {
        if (!(other instanceof Polynome)) {
            throw new IllegalArgumentException("Incompatible type");
        }
        Polynome otherPolynome = (Polynome) other;
        List<NumberInterface> newCoefficients = new ArrayList<>();
        for (int i = 0; i < Math.max(this.coefficients.size(), otherPolynome.coefficients.size()); i++) {
            NumberInterface coeff1 = i < this.coefficients.size() ? this.coefficients.get(i) : new Rational(0, 1);
            NumberInterface coeff2 = i < otherPolynome.coefficients.size() ? otherPolynome.coefficients.get(i) : new Rational(0, 1);
            newCoefficients.add(coeff1.add(coeff2));
        }
        return new Polynome(newCoefficients);
    }

    @Override
    public NumberInterface subtract(NumberInterface other) {
        if (!(other instanceof Polynome)) {
            throw new IllegalArgumentException("Incompatible type");
        }
        Polynome otherPolynome = (Polynome) other;
        List<NumberInterface> newCoefficients = new ArrayList<>();
        for (int i = 0; i < Math.max(this.coefficients.size(), otherPolynome.coefficients.size()); i++) {
            NumberInterface coeff1 = i < this.coefficients.size() ? this.coefficients.get(i) : new Rational(0, 1);
            NumberInterface coeff2 = i < otherPolynome.coefficients.size() ? otherPolynome.coefficients.get(i) : new Rational(0, 1);
            newCoefficients.add(coeff1.subtract(coeff2));
        }
        return new Polynome(newCoefficients);
    }

    @Override
    public NumberInterface multiply(NumberInterface other) {
        if (!(other instanceof Polynome)) {
            throw new IllegalArgumentException("Incompatible type");
        }
        Polynome otherPolynome = (Polynome) other;
        List<NumberInterface> newCoefficients = new ArrayList<>();
        for (int i = 0; i < this.coefficients.size() + otherPolynome.coefficients.size() - 1; i++) {
            newCoefficients.add(new Rational(0, 1));
        }
        for (int i = 0; i < this.coefficients.size(); i++) {
            for (int j = 0; j < otherPolynome.coefficients.size(); j++) {
                newCoefficients.set(i + j, newCoefficients.get(i + j).add(this.coefficients.get(i).multiply(otherPolynome.coefficients.get(j))));
            }
        }
        return new Polynome(newCoefficients);
    }

    @Override
    public NumberInterface divide(NumberInterface other) {
        throw new UnsupportedOperationException("Polynomial division is not supported yet.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = coefficients.size() - 1; i >= 0; i--) {
            if (i < coefficients.size() - 1 && !coefficients.get(i).toString().startsWith("-")) {
                sb.append("+");
            }
            sb.append(coefficients.get(i).toString());
            if (i > 0) {
                sb.append("x^").append(i).append(" ");
            }
        }
        return sb.toString();
    }

    public NumberInterface evaluate(NumberInterface x) {
        NumberInterface result = new Rational(0, 1);
        for (int i = 0; i < coefficients.size(); i++) {
            NumberInterface term = coefficients.get(i);
            for (int j = 0; j < i; j++) {
                term = term.multiply(x);
            }
            result = result.add(term);
        }
        return result;
    }

    public Polynome derivative() {
        List<NumberInterface> newCoefficients = new ArrayList<>();
        for (int i = 1; i < coefficients.size(); i++) {
            newCoefficients.add(coefficients.get(i).multiply(new Unsigned(i)));
        }
        return new Polynome(newCoefficients);
    }

    public Polynome integrate() {
        List<NumberInterface> newCoefficients = new ArrayList<>();
        newCoefficients.add(new Rational(0, 1)); 
        for (int i = 0; i < coefficients.size(); i++) {
            newCoefficients.add(coefficients.get(i).divide(new Unsigned(i + 1)));
        }
        return new Polynome(newCoefficients);
    }

    public NumberInterface definiteIntegral(NumberInterface a, NumberInterface b) {
        Polynome indefiniteIntegral = integrate();
        return indefiniteIntegral.evaluate(b).subtract(indefiniteIntegral.evaluate(a));
    }

    public NumberInterface solveLinear() {
        if (coefficients.size() != 2) {
            throw new IllegalArgumentException("The polynomial is not linear.");
        }
        NumberInterface a = coefficients.get(1); 
        NumberInterface b = coefficients.get(0); 

        if (a.toString().equals("0")) {
            throw new IllegalArgumentException("The equation is not solvable (a = 0).");
        }
        return b.divide(a).multiply(new Rational(-1, 1));
    }

    public List<NumberInterface> solveQuadratic() {
        if (coefficients.size() != 3) {
            throw new IllegalArgumentException("The polynomial is not quadratic.");
        }
        NumberInterface a = coefficients.get(2); 
        NumberInterface b = coefficients.get(1); 
        NumberInterface c = coefficients.get(0); 

        if (a.toString().equals("0")) {
            throw new IllegalArgumentException("The equation is not solvable (a = 0).");
        }

        NumberInterface discriminant = b.multiply(b).subtract(a.multiply(c).multiply(new Rational(4, 1)));
        if (discriminant.toString().equals("0")) {
            List<NumberInterface> solutions = new ArrayList<>();
            solutions.add(b.multiply(new Rational(-1, 1)).divide(a.multiply(new Rational(2, 1))));
            return solutions;
        } else if (discriminant.toString().charAt(0) == '-') {
            throw new IllegalArgumentException("The equation has no real solutions.");
        } else {
            List<NumberInterface> solutions = new ArrayList<>();
            NumberInterface sqrtDiscriminant = sqrt(discriminant);
            solutions.add(b.multiply(new Rational(-1, 1)).add(sqrtDiscriminant).divide(a.multiply(new Rational(2, 1))));
            solutions.add(b.multiply(new Rational(-1, 1)).subtract(sqrtDiscriminant).divide(a.multiply(new Rational(2, 1))));
            return solutions;
        }
    }

    private NumberInterface sqrt(NumberInterface value) {
        double val = Double.parseDouble(value.toString());
        return new Rational((int) Math.sqrt(val), 1);
    }
}
