import java.io.File;
import java.io.IOException;

public class Driver {
  public static void main(String[] args) throws IOException {
    double[] c1, c2;
    int[] a1, a2;

    // Testing with NULL polynomials
    Polynomial p1 = new Polynomial();
    Polynomial p2 = new Polynomial();
    Polynomial p3 = p1.add(p2);
    p3.saveToFile("01-add-null.txt"); // should be empty file
    p3 = p2.multiply(p1);
    p3.saveToFile("02-mult-null.txt"); // should be empty file

    // Testing with one NULL polynomial
    c1 = new double[] {10, 3, 4};
    a1 = new int[] {0, 2, 5};
    p1 = new Polynomial(c1, a1);
    p2 = new Polynomial();
    p3 = p1.add(p2);
    p3.saveToFile("03-add-1null.txt"); // should be empty file
    p3 = p2.multiply(p1);
    p3.saveToFile("04-add-1null.txt"); // should be empty file

    // Testing with a "zero" polynomial
    c2 = new double[1];
    a2 = new int[] {1};
    p2 = new Polynomial(c2, a2);
    p3 = p1.add(p2);
    p3.saveToFile("05-add-zero.txt"); // should give original p1
    p3 = p2.multiply(p1);
    p3.saveToFile("06-mult-zero.txt"); // should be empty file

    // Testing with functions of diff exp, from file
    File f1 = new File("90-diff-exp.txt");
    File f2 = new File("91-diff-exp.txt");
    p1 = new Polynomial(f1);
    p2 = new Polynomial(f2);
    p3 = p1.add(p2);
    p3.saveToFile("07-add-diff.txt");
    p3 = p2.multiply(p1);
    p3.saveToFile("08-mult-diff.txt");

    // Testing with functions of same exp, from file
    f1 = new File("92-same-exp.txt");
    f2 = new File("93-same-exp.txt");
    p1 = new Polynomial(f1);
    p2 = new Polynomial(f2);
    p3 = p1.add(p2);
    p3.saveToFile("09-add-same.txt");
    p3 = p2.multiply(p1);
    p3.saveToFile("10-mult-same.txt");

    // Checking roots and evaluate function;
    c1 = new double[] {0};
    a1 = new int[] {1};
    p1 = new Polynomial(c1, a1);
    System.out.println(p1.evaluate(3));

    c1 = new double[] {0, 5, 0, 6};
    a1 = new int[] {2, 4, 3, 1};
    c2 = new double[] {-2, -9};
    a2 = new int[] {2, 5};
    p1 = new Polynomial(c1, a1);
    p2 = new Polynomial(c2, a2);
    Polynomial s = p1.add(p2);
    System.out.println("s(0.1) = " + s.evaluate(0.1));
    if (s.hasRoot(1)) System.out.println("1 is a root of s");
    else System.out.println("1 is not a root of s");
  }
}
