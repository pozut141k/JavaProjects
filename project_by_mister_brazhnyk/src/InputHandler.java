import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class InputHandler {
    public static List<NumberInterface> readCoefficientsFromConsole() {
        Scanner scanner = new Scanner(System.in);
        List<NumberInterface> coefficients = new ArrayList<>();
        System.out.println("Enter the number of coefficients:");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter numerator for coefficient " + i + ":");
            int numerator = scanner.nextInt();
            System.out.println("Enter denominator for coefficient " + i + ":");
            int denominator = scanner.nextInt();
            coefficients.add(new Rational(numerator, denominator));
        }
        return coefficients;
    }

    public static List<NumberInterface> readCoefficientsFromFile(String fileName) {
        List<NumberInterface> coefficients = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {
                int numerator = scanner.nextInt();
                int denominator = scanner.nextInt();
                coefficients.add(new Rational(numerator, denominator));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
        return coefficients;
    }
}
