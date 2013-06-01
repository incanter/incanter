# What's new in Incanter #

## Changes for 1.5.0 ##

### Enhancements ###

 - incanter-core's matrix uses native BLAS through jBLAS/Clatrix - this greatly improves performance.
 - several interpolation functions were added to incanter-core module (as `incanter.interpolation` namespace).
 - a new option is added to `heat-map` - `:include-zero?`.
 - the `tail` function was added.
 - new function `reorder-columns` for a dataset that changes the order of appearance of the datset columns. It does not alter the row order.
 - `save` will print data to standard output if `"-"` is specified as file name.
 - `sel` and other functions (`$`, `head`, `tail`, etc.) can be used with lists (`java.util.List`). 
 - the `toeplitz` function was added to generate Toeplitz matrix for given vector.
 - the `scatter-plot-matrix` function was added to `incanter.chart` module
 - `incanter.optimize` was extended with `minimize` and `maximise` functions for performing unconstrained nonlinear optimization using the BFGS algorithm. 

### Many bugfixes ###
 - for function & parametric plots, line is finished in max-range point.
 - permutation matrix is returned in LU decomposition.
 - `sel` will return dataset when `:rows` or `:cols` are non-numbers - this changes
   previous behaviour when list was returned if only one row or col was specified
 - `linear-model` now correctly calculates t-probs

### Updated dependencies ###

 - [Clatrix](https://github.com/Quantisan/clatrix): 0.3.0
 - Clojure: 1.5.1
 - JLine: 2.11

## Changes for 1.4.x ##

Major changes are:
 * The `incanter.sql` module was added to allow load datasets from databases using ClojureQL.
 * Support for parametric plots in `incanter.chart` module
 * Bugfixes

## Changes for 1.3.x ##

Major changes are:

 * Switch to Leiningen 2 for development
 * Incanter-processing was removed. It's recommended to use [Quil](https://github.com/quil/quil) instead
 * Updated versions of dependencies:
   * Clojure: 1.4.0
   * Parallelcolt: 0.10.0
   * `math.combinatorics`: 0.0.3
   * Apache POI (for `incanter.excel`): 3.8
   * `clj-time`: 0.4.4
   * Congomongo: 0.3.3
 * `incanter.core/get-input-stream` & `incanter.core/get-input-reader` are removed in
   favor of `input-stream` & `reader` from `clojure.java.io`
 * Many bugfixes -- thank you for all people who sent us pull requests!

