package incanter;

import cern.jet.random.tdouble.*;
import cern.jet.random.tdouble.engine.*;

/**
 * Weibull Distribution. See the 
 * <A HREF="http://www.itl.nist.gov/div898/handbook/eda/section3/eda3668.htm"> math definition</A>.
 * <p>
 * <tt>p(x) = shape/scale*(x/scale)^(shape-1)*exp{-(x/scale)^shape}</tt> for <tt>x &gt;= scale</tt>, <tt>shape &gt; 0</tt>.
 * <p>
 * Note that mean is infinite if <tt>shape <= 1</tt> and variance is infinite if
 * <tt>shape <= 2</tt>.
 * <p>
 * Instance methods operate on a user supplied uniform random number generator; they are unsynchronized.
 * <dt>
 * Static methods operate on a default uniform random number generator; they are synchronized.
 * <p>
 *
 * @author Hongbo Liu hongbol@winlab.rutgers.edu
 * @version 0.1
 */
public class Weibull extends AbstractContinousDoubleDistribution { 

  protected double scale;
  protected double shape;
      
  // The uniform random number generated shared by all <b>static</b> methods.
  protected static Weibull shared = new Weibull(1.0, 1.5, makeDefaultGenerator());

  /**
   * Constructs a Pareto distribution.
   */
  public Weibull(double a, double b, DoubleRandomEngine randomGenerator) {
    setRandomGenerator(randomGenerator);
    this.scale = a;
    this.shape = b;
  }

  /**
   * Returns the cumulative distribution function.
   */
  public double cdf(double x) {
    if (x < 0)
      return 0.0;
    return 1.0 - Math.exp(-Math.pow(x/scale,shape));
  }

  /**
   * Returns a random number from the distribution.
   */
  public double nextDouble() {
    return scale*Math.pow(-Math.log(1-randomGenerator.raw()), 1/shape);
  }

  /**
   * Returns a random number from the distribution; bypasses the internal 
   state.*/
  public double nextDouble(double a, double b) {
    return a*Math.pow(-Math.log(1-randomGenerator.raw()), 1/b);
  }

  /**
   * Returns the probability distribution function.
   */
  public double pdf(double x) {
    if (x < 0)
      return 0.0;
    return shape/scale*Math.pow(x/scale, shape-1)
      *Math.exp(-Math.pow(x/scale,shape));
  }

  /**
   * Sets the parameters.
   */
  public void setState(double a, double b) {
    this.scale = a;
    this.shape = b;
  }

  /**
   * Returns a random number from the distribution with the given
   * scale = k, and shape = alpha.
   */
  public static double staticNextDouble(double a, double b) {
    synchronized (shared) {
      return shared.nextDouble(a,b);
    }
  }

  /**
   * Returns a String representation of the receiver.
   */
  public String toString() {
    return this.getClass().getName() + "(" + scale + ", " + shape + ")";
  }

}

