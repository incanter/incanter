# What's new in Incanter #

## Changes for 1.9.3 ##

### Main changes ###

* Added support for Clojure 1.9.0

### Improvements ###

 * Weibull distribution is now implemented directly in Clojure instead of using Java implementation.

### Bugfixes ###

 * #388 - the `itext` library was linked to broken version of Boncy Castle library;
 * #377 - scatter-plot-matrix broken since 1.5.7
 * #374 - heat-map produces wierd visual artifacts
 * #364 - No method in multimethod `set-data`

## Changes for 1.9.2 ##

This release includes multiple bug fixes & improvements, together with update of
dependencies to fresh versions.

Please note that Clojure 1.9.0 isn't supported yet because of the problems with ClojureQL.

## Changes for 1.9.0 ##

This is first preview of the Incanter 2.0.  This release incorporates the code implemented
as part of the GSoC 2014 project "Incanter and core.matrix integration" done by Aleksandr
Sorokoumov, aka Gerrrr. (For full list of changes made during GSoC, please look to
[wiki](https://github.com/incanter/incanter/wiki/Incanter-2.0-change-log)).

Please, note that this release includes only part of changes planned for Incanter 2.0

### Main changes ###

 * Incanter now uses only core.matrix functions with `:vectorz` as default
   implementations.  You can use another implementation by adding corresponding dependency
   to your project file & selecting implementation using the `set-current-implementation`
   function.
 * Incanter's dataset is now based on the Dataset from core.matrix that supports both
   labeled columns & rows.  Dataset is also a matrix now. Matrix functions work on
   datasets;
 * Many functions are now deprecated in favor of corresponding functions from
   core.matrix. These functions have `:deprecated` flag in meta-information, for full list
   please look to
   [wiki](https://github.com/incanter/incanter/wiki/Incanter-2.0-change-log).

### Breaking changes ###

 * `incanter.core/matrix` called on seq will produce vector object instead of row-matrix;
 * Matrices are not treated as sequences anymore, so calling `first`, `rest`, `map`,
   `reduce`, `filter`, etc on matrices is not generally supported (although might still
   work on some implementations, e.g. clatrix). It's recommended to use corresponding
   functions from core.matrix;
 * API of the functions `decomp-cholesky`, `decomp-svd`, `decomp-eigenvalue`, `decomp-lu`,
   `decomp-qr` is changed to match core.matrix's implementation. See
   [core.matrix wiki](https://github.com/mikera/core.matrix/wiki/Linear-algebra-API-proposal)
   for more details;
 * `incanter.core/dataset` API is changed. Now it accepts column names and seq of rows,
   matrix, map of columns or seq of row maps as arguments;
 * For datasets, the consecutive integers (.e.g 0, 1, 2, ...) are now used as default
   column names;
 * `incanter.core/sel` does not support automatic cast between strings and keywords
   anymore;
 * 2 arguments version of `incanter.core/col-names` removed. Please use
   `clojure.core.matrix.dataset/select-columns` instead.

### Improvements ###

 * Instead of using scripts to perform task in every subdirectory, you can now use
   `lein-sub` & `lein-modules` plugins to do the same.  Just execute `lein sub test` or
   `lein modules install` to perform testing, or installing of every Incanter's module;
 * Clojure 1.6.0 is used.

### Bugfixes ###

 * `kendalls-tau` now returns correct value.

### Known issues

 * `incanter.stats/sample-wishart` throws error during execution;
 * For Clatrix-based implementations, it's not always possible to construct matrix from
   result of `map`/`filter`/...

## Changes for 1.5.7 ##

### Bugfixes ###

 * Make compatible with Clojure 1.7.0 & higher by excluding the new `update` function
   (Issue #321);
 * Fix for Issue #294: xy-plot draws wrong legends when the number of lines > 8;
 * PR ##302: Handle edge case in binary search where a single element vector would result
   in it getting stuck in an infinite loop;

## Changes for 1.5.6 ##

### Bugfixes ###

 * `median` returns `Double/NaN` when it receives empty list (PR #263);
 * `sel` returns dataset when `:rows :all` specified, independent on size/structure of the
   dataset (PR #259);
 * Fixed calculation of `kurtosis` (PR #260);
 * Fixed plots legend on `group-by` when repeated rows for the legend column are present
   (PR #253);
 * Fix for problems with gamma distribution (Issue #245);
 * Fix for `rank-index` function (PR #261, #262).
 
### Enhancements ###

 * New functions in `incanter.charts` to work with series (PR #278):
   * `has-series?` - checks, does the chart have the series with given name;
   * `remove-series` - removes given series from chart;
   * `extend-line` - adds new data to existing series, or creates new series if it doesn't exist.
 * `save-svg` can also accept the `OutputStream`, not only the file name (PR #279).

## Changes for 1.5.5 ##

### Bugfixes ###

 * Fix the repl scripts (issue #215)
 * Correctly handle `from-repo` parameter of `get-dataset` function
 * Correctly scale parameter in `sample-model-params`
 * Columns are explicitely casted to str in `read-dataset`
 * Correct different problems found by Eastwood lint tool
 * Correctly handle missing values when using `log-axis` (issue #210)
 * BFGS minimization routine uses `gradient-fn` to estimate `f-prime` when it is not
   provided.  This also entailed changing gradient-fn to accept matrices as well as
   vectors.
 * `xy-plot` and `time-series-plot` modified to take into account `group-by` names
   correctly for legend labels (issue #216)
 * `draw` for `uniform-distribution` uses specified min & max parameters
 * Correlation coefficient now 0.0 (not NaN) with constant vector
 * Fix for linear model adjusted R-squared (issue #194)
 * Catch divide by zero exceptions when calculating `rho-k` in `fmin-bfgs`
 * Correctly handle `:legend` for `time-series-plot`
 * Fix for `minus` for a single argument (issue #195)
 * `chisq-test` works correctly when `:x` is one sample collection

### Enhancements ###

 * New functions in `incanter.core`:
   * `aggregate` performs the aggregation of data on given fields (issue #223)
   * `get-column-id` returns keyword version of column-key if convenient 
 * New functions in `incanter.stats`:
   * implementation of `gamma-coefficient` function
   * `concordant-pairs` function
 * `read-dataset` now accepts the `:comment-char` parameter that specifies the commentary
   character

### Breaking changes

 * (issue #245) Incanter used the 'rate' as parameter name, although in reality this was a
   'scale' parameter (https://en.wikipedia.org/wiki/Gamma_distribution).  For `pdf-gamma`,
   `cdf-gamma` & `sample-gamma` functions the new parameter `:scale` was introduced
   (equivalent to the old `:rate` parameter), and `:rate` parameter is now the
   `1/:scale`...

## Changes for 1.5.4 ##

Made an error during deployment of 1.5.3, so 1.5.4 was released to fix this problem.
Don't use 1.5.3!

## Changes for 1.5.3 ##

### Bugfixes ###

 * Issue 183: the `pow` & `atan2` functions weren't implemented for matrices & datasets.
 * `read-dataset` now converts empty fields to `nil`, or user-supplied value (see Issue 182).
 * Documentation improvements.

### Enhancements ###

 * New functions in `incanter.charts`:
   * `set-point-size` to control size of points on scatter plots.
 * New functions in `incanter.core`:
   * `rename-cols` allows to rename columns of dataset
   * `replace-column` replaces data in column of dataset with new values
   * `add-column` allows easier to add new column to dataset
   * `add-derived-column` adds a column to a dataset that is a function of existing
     columns
   * `melt` implements part of functionality of R's `melt` function from `reshape`
     package.

## Changes for 1.5.2 ##

### Bugfixes ###

 * Issue 168: the `view` function wasn't defined for `Matrix` class
 * Issue 161: maximal idx for slider wasn't correctly calculated
 * The `sel` function on `nil` was implemented, preventing from getting errors when there
   was no data specified in the `$data` variable
 * Issue 169: metadata wasn't added to `ncol` & `nrow` functions
 * Issue 164: `to-vect` was implemented only for `Matrix` class, now it works with any
   support data type
 * Issue 165: `sel` returns a dataset even if result has one row, and we're selecting
   columns.

### Enhancements ###

 * Issue 166: You can use logarithmic axes (with different bases) in Incanter charts.  See
   issue for more details

## Changes for 1.5.1 ##

### Bugfixes ###

 * Issue 157: when `transform-with` was used with Matrix, then source data was modified instead of working on copy of data
 * Issue 160: when 2-arguments version of `solve` was used, the exception was thrown

## Changes for 1.5.0 ##

### Enhancements ###

 * incanter-core's matrix uses native BLAS through jBLAS/Clatrix - this greatly improves performance (on 64-bit Linux see "Known issues" section).
 * several interpolation functions were added to incanter-core module (as `incanter.interpolation` namespace).
 * a new option is added to `heat-map` - `:include-zero?`.
 * the `tail` function was added.
 * new function `reorder-columns` for a dataset that changes the order of appearance of the datset columns. It does not alter the row order.
 * `save` will print data to standard output if `"-"` is specified as file name.
 * `sel` and other functions (`$`, `head`, `tail`, etc.) can be used with lists (`java.util.List`). 
 * the `toeplitz` function was added to generate Toeplitz matrix for given vector.
 * the `scatter-plot-matrix` function was added to `incanter.chart` module.
 * `incanter.optimize` was extended with `minimize` and `maximise` functions for performing unconstrained nonlinear optimization using the BFGS algorithm.
 * the new `incanter.svg` module provides `save-svg` function to output charts to SVG files.  Include `incanter-svg` as dependency to use this functionality.

### Many bugfixes ###

 * for function & parametric plots, line is finished in max-range point.
 * permutation matrix is returned in LU decomposition.
 * `linear-model` now correctly calculates t-probs.
 * fixed division by zero in `linear-model` for some data.

### Modified behavior ###

 * `sel` will return dataset when `:rows` or `:cols` are non-numbers - this changes
   previous behaviour when list was returned if only one row or col was specified.
 * `mult` & `mmult` always return matrices, even if it's 1x1 matrix.

### Known issues ###

 * `conj`'ing of matrix & vector doesn't work with new Clatrix - you can either use `bind-rows`, or wrap vector into another vector:
    
    (def M (matrix [[0 1] [2 3] [4 5]]))
    (conj M [6 7])      ; => doesn't work
    (bind-rows M [6 7]) ; => works
    (conj M [[6 7]])    ; => works

 * `decomp-qr` performs only full QR decomposition, and the `:type` parameter is ignored.
 * On 64-bit Linux you need to install libgfortran3 package. See [jblas wiki](https://github.com/mikiobraun/jblas/wiki/Missing-Libraries) for more details

### Updated dependencies ###

 * [Clatrix](https://github.com/Quantisan/clatrix): 0.3.0
 * Clojure: 1.5.1
 * JLine: 2.11

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

