import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Polynomial {
  double coefficients[];
  int exponents[];

  public Polynomial() {
    coefficients = null;
    exponents = null;
  }

  public Polynomial(double coeff[], int exp[]) {
    coefficients = coeff;
    exponents = exp;
  }

  public Polynomial(File file) throws IOException {
    Scanner s = new Scanner(file);
    String line = s.nextLine();
    line = line.replaceAll("-", "+-");
    String[] splitted = line.split("\\+");

    coefficients = new double[splitted.length];
    exponents = new int[splitted.length];

    for (int i = 0; i < splitted.length; i++) {
      String[] subArr = splitted[i].split("x");

      coefficients[i] = Double.parseDouble(subArr[0]);
      if (subArr.length == 1) {
        exponents[i] = 0;
      } else {
        exponents[i] = Integer.parseInt(subArr[1]);
      }
    }
    s.close();
  }

  public Polynomial add(Polynomial toAdd) {
    int length = this.getDistinctExponents(toAdd);
    double[] newCoeff = new double[length];
    int[] newExp = new int[length];

    // Set values of calling object
    for (int i = 0; i < coefficients.length; i++) {
      newCoeff[i] = coefficients[i];
      newExp[i] = exponents[i];
    }

    int offset = 0;
    for (int i = 0; i < toAdd.coefficients.length; i++) {
      for (int j = 0; j < coefficients.length; j++) {
        if (newExp[j] == toAdd.exponents[i]) {
          newCoeff[j] += toAdd.coefficients[i];
          break;
        }
      }
      // newCoeff[offset]
    }

    return new Polynomial(newCoeff, newExp);
  }

  public double evaluate(double x) {
    double ans = 0.0;
    for (int i = 0; i < coefficients.length; i++) {
      ans += coefficients[i] * Math.pow(x, exponents[i]);
    }
    return ans;
  }

  public boolean hasRoot(double x) {
    return this.evaluate(x) == 0;
  }

  public Polynomial multiply(Polynomial toMult) {
    // Idea: Create array of self.length x toMult.length and use
    // 2 nested for loops to multiply coefficients and add exponents
    // Add helper function to collapse the array into a smaller array
    return new Polynomial();
  }

  public void saveToFile(String filename) throws IOException {
    File f = new File(filename);
    if (!f.exists()) f.createNewFile();
    FileWriter fw = new FileWriter(f);
    if (coefficients == null || exponents == null) {
      fw.close();
      return;
    }
    if (coefficients.length != exponents.length) {
      fw.close();
      return;
    }

    String poly = "";
    for (int i = 0; i < coefficients.length; i++) {
      poly += String.valueOf(coefficients[i]);
      if (exponents[i] != 0) {
        poly += "x" + String.valueOf(exponents[i]);
      }
      if (i != coefficients.length - 1) {
        poly += "+";
      }
    }
    poly = poly.replaceAll("\\+-", "-");
    fw.write(poly);
    fw.close();
  }

  // Returns the number of distinct exponents between the calling
  // object and argument object
  private int getDistinctExponents(Polynomial other) {
    int distinct = exponents.length;
    boolean isUnique = true;
    for (int i : other.exponents) {
      isUnique = true;
      for (int j : exponents) {
        if (i == j) {
          isUnique = false;
          break;
        }
      }
      if (isUnique) distinct += 1;
    }

    return distinct;
  }
}
