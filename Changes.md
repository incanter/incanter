# What's new in Incanter #

## Changes for 1.5.x

- Incanter-core matrix using native BLAS through jBLAS/Clatrix
- Updated dependencies:
    - [Clatrix](https://github.com/Quantisan/clatrix): 0.2.1

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

