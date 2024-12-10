import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose input mode: 1 for console, 2 for file");
        int mode = scanner.nextInt();
        List<NumberInterface> coefficients;

        if (mode == 1) {
            coefficients = InputHandler.readCoefficientsFromConsole();
        } else {
            System.out.println("Enter the file name:");
            String fileName = scanner.next();
            coefficients = InputHandler.readCoefficientsFromFile(fileName);
        }

        Polynome p = new Polynome(coefficients);
        System.out.println("Polynomial: " + p.toString());
        System.out.println("Polynomial Value at x = 1: " + p.evaluate(new Rational(1, 1)).toString());
        System.out.println("Polynomial Derivative: " + p.derivative().toString());
        System.out.println("Polynomial Integral: " + p.integrate().toString());

        if (coefficients.size() == 2) {
            System.out.println("Linear Polynomial Solution: " + p.solveLinear().toString());
        } else if (coefficients.size() == 3) {
            System.out.println("Quadratic Polynomial Solutions: " + p.solveQuadratic());
        }
    }
}
