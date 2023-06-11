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

  public Polynomial add(Polynomial toAdd) {
    // check arrays are valid
    if (!(this.isValid() && toAdd.isValid())) {
      return new Polynomial();
    }

    // If arrays only has distinct exponents
    double[] newCoeff = new double[coefficients.length + toAdd.coefficients.length];
    int[] newExp = new int[exponents.length + toAdd.exponents.length];

    int index = 0;
    for (int i = 0; i < coefficients.length; i++) {
      newCoeff[index] = coefficients[i];
      newExp[index] = exponents[i];
      index++;
    }

    // This flag keeps track of whether we already added our coefficient
    // at a previous index
    boolean isNew = true;
    for (int i = 0; i < toAdd.coefficients.length; i++) {
      isNew = true; // refresh the flag for every term
      for (int j = 0; j < index; j++) {
        if (newExp[j] == toAdd.exponents[i]) {
          isNew = false;
          newCoeff[j] += toAdd.coefficients[i];
          break;
        }
      }
      if (isNew) {
        newCoeff[index] = toAdd.coefficients[i];
        newExp[index] = toAdd.exponents[i];
        index++;
      }
    }
    Polynomial p = new Polynomial(newCoeff, newExp);

    // remove all the zero coefficients at the end
    p.removeZeros();
    return p;
  }

  public double evaluate(double x) {
    if (!isValid()) {
      return 0.0;
    }

    double ans = 0.0;
    for (int i = 0; i < coefficients.length; i++) {
      ans += coefficients[i] * Math.pow(x, exponents[i]);
    }
    return ans;
  }

  public boolean hasRoot(double x) {
    return this.evaluate(x) == 0;
  }

  ///////////////////////////////////////////////////////////////////////////
  // LAB 2 NEW METHODS //////////////////////////////////////////////////////
  ///////////////////////////////////////////////////////////////////////////

  public Polynomial(File file) throws IOException {
    Scanner s = new Scanner(file);
    // this way, we have a convenient token to split by
    String line = s.nextLine().replaceAll("-", "+-");

    // happens when first coefficient is negative
    if (line.charAt(0) == '+') {
      line = line.replaceFirst("\\+-", "-");
    }

    String[] splitted = line.split("\\+");

    coefficients = new double[splitted.length];
    exponents = new int[splitted.length];

    for (int i = 0; i < splitted.length; i++) {
      String[] subArr = splitted[i].split("x");

      // For an input like x2, x3, -x1
      if (subArr[0].equals("")) coefficients[i] = 1.0;
      else if (subArr[0].equals("-")) coefficients[i] = -1.0;
      else coefficients[i] = Double.parseDouble(subArr[0]);

      // For an input of 3x, 4x
      if (subArr.length == 1) exponents[i] = 0;
      else exponents[i] = Integer.parseInt(subArr[1]);
    }
    s.close();
  }

  public Polynomial multiply(Polynomial toMult) {
    if (!(isValid() && toMult.isValid())) {
      return new Polynomial();
    }

    double[] newCoeff = new double[coefficients.length * toMult.coefficients.length];
    int[] newExp = new int[exponents.length * toMult.exponents.length];

    boolean isNew = true;
    int index = 0;

    for (int i = 0; i < exponents.length; i++) {
      for (int j = 0; j < toMult.coefficients.length; j++) {
        isNew = true;
        double curCoeff = coefficients[i] * toMult.coefficients[j];
        int curExp = exponents[i] + toMult.exponents[j];

        // For every multiplied term, check if the exponent is already existant
        for (int k = 0; k < index; k++) {
          if (newExp[k] == curExp) {
            isNew = false;
            newCoeff[k] += curCoeff;
            break;
          }
        }

        if (isNew) {
          newCoeff[index] = curCoeff;
          newExp[index] = curExp;
          index++;
        }
      }
    }

    Polynomial p = new Polynomial(newCoeff, newExp);
    p.removeZeros();

    return p;
  }

  public void saveToFile(String filename) throws IOException {
    File f = new File(filename);
    if (!f.exists()) f.createNewFile();
    FileWriter fw = new FileWriter(f);

    // First check the polynomial is valid
    if (!isValid()) {
      fw.close();
      return;
    }

    String poly = "";
    for (int i = 0; i < coefficients.length; i++) {
      poly += String.valueOf(coefficients[i]);
      // The 0 exponent should not be there
      if (exponents[i] != 0) {
        poly += "x" + String.valueOf(exponents[i]);
      }
      // Never add the plus sign at the end of the string
      if (i != coefficients.length - 1) {
        poly += "+";
      }
    }
    poly = poly.replaceAll("\\+-", "-");

    fw.write(poly);
    fw.close();
  }

  public void removeZeros() {
    int length = getValidLength();
    double[] newCoeff = new double[length];
    int[] newExp = new int[length];

    int index = 0;
    for (int i = 0; i < coefficients.length; i++) {
      if (coefficients[i] != 0) {
        newCoeff[index] = coefficients[i];
        newExp[index] = exponents[i];
        index++;
      }
    }

    coefficients = newCoeff;
    exponents = newExp;
  }

  public boolean isValid() {
    if (coefficients == null || exponents == null) return false;
    if (coefficients.length != exponents.length) return false;

    return true;
  }

  private int getValidLength() {
    int length = 0;
    for (double a : coefficients) {
      if (a != 0) {
        length++;
      }
    }
    return length;
  }
}
