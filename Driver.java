import java.io.File;
import java.io.IOException;
public class Driver {
  public static void main(String[] args) throws IOException{
    // Polynomial p = new Polynomial();
    // System.out.println(p.evaluate(3));
    // double[] c1 = { 6, 0, 0, 5 };
    // Polynomial p1 = new Polynomial(c1);
    // double[] c2 = { 0, -2, 0, 0, -9 };
    // Polynomial p2 = new Polynomial(c2);
    // Polynomial s = p1.add(p2);
    // System.out.println("s(0.1) = " + s.evaluate(0.1));
    // if (s.hasRoot(1))
    //   System.out.println("1 is a root of s");
    // else
    //   System.out.println("1 is not a root of s");
    //
    // double[] c1 = {5, 3, 7};
    // double[] c2 = {6, -2, 5};
    // int[] a = {0, 2, 8};
    // int[] b = {0, 1, 3};
    // Polynomial f = new Polynomial(c1, a);
    // Polynomial g = new Polynomial(c2, b);
    // double[] c = f.add(g).coefficients;
    // for (double i : c) {
    //   System.out.print(i + " ");
    // }
    // System.out.println();
    // int[] d = f.add(g).exponents;
    // for (double i : d) {
    //   System.out.print(i + " ");
    // }
    File f = new File("poly.txt");
    Polynomial p = new Polynomial(f);
    p.saveToFile("test.txt");
    
    // for (double i : p.coefficients) {
    //   System.out.print(i + " ");
    // }
    // System.out.println();
    // for (int i : p.exponents) {
    //   System.out.print(i + " ");
    // }

  }
}
