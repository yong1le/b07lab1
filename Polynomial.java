import java.io.File;

public class Polynomial {
  double coefficients[];
  int exponents[];

  public Polynomial() {
    coefficients = new double[1];
    exponents = new int[1];
  }

  public Polynomial(double coeff[], int exp[]) {
    coefficients = coeff;
    exponents = exp;
  }

  public Polynomial(File file) {}

  public Polynomial add(Polynomial toAdd) {
    int length = this.getDistinctExponents(toAdd);

    // Need to be calling these a lot
    int selfLength = coefficients.length;
    int otherLength = toAdd.coefficients.length;

    double[] newCoeff = new double[length];
    int[] newExp = new int[length];

    // Used to keep track of which term we are at for each poly
    int selfIndex = 0;
    int otherIndex = 0;

    // Add each term from smallest exponent to largest exponent
    for (int i = 0; i < length; i++) {

      // When exponents of both terms are the same
      if (selfIndex < selfLength
          && otherIndex < otherLength
          && exponents[selfIndex] == toAdd.exponents[otherIndex]) {
        newCoeff[i] = coefficients[selfIndex] + toAdd.coefficients[otherIndex];
        newExp[i] = exponents[selfIndex];
        selfIndex++;
        otherIndex++;

        // Case 1: When we finish adding all terms from calling object
        // Case 2: When argument object has the smaller exponent
      } else if (selfIndex >= selfLength
          || otherIndex < otherLength && exponents[selfIndex] > toAdd.exponents[otherIndex]) {
        newCoeff[i] = toAdd.coefficients[otherIndex];
        newExp[i] = toAdd.exponents[otherIndex];
        otherIndex++;

        // Case 1: When we finish adding all terms from argument object
        // Case 2: When
      } else if (otherIndex >= otherLength
          || selfIndex < selfLength && exponents[selfIndex] < toAdd.exponents[otherIndex]) {
        newCoeff[i] = coefficients[selfIndex];
        newExp[i] = exponents[selfIndex];
        selfIndex++;
      }
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

  public void saveToFile(File file) {}

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

  private void addToIndex(int[] arr, int index, int value) {
    arr[index] += value;
  }
}
