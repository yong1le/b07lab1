public class Polynomial {
  double coefficients[];

  public Polynomial() {
    coefficients = new double[1];
  }

  public Polynomial(double arg[]) {
    coefficients = arg;
  }

  public Polynomial add(Polynomial toAdd) {
    int length = Math.max(this.coefficients.length, toAdd.coefficients.length);

    double newCoefficients[] = new double[length];

    for (int i = 0; i < length; i++) {
      if (i < coefficients.length) {
        newCoefficients[i] += this.coefficients[i];
      }
      if (i < toAdd.coefficients.length) {
        newCoefficients[i] += toAdd.coefficients[i];
      }
    }

    return new Polynomial(newCoefficients);
  }

  public double evaluate(double x) {
    double ans = 0.0;
    for (int i = 0; i < coefficients.length; i++) {
      ans += coefficients[i] * Math.pow(x, i);
    }
    return ans;
  }

  public boolean hasRoot(double x) {
    return this.evaluate(x) == 0;
  }

}
