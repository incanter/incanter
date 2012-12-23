# What's new in Incanter #

## Changes since 1.4.0 ##

Major changes are:
 * The `incanter.sql` module was added to allow load datasets from databases using ClojureQL.
 * Support for parametric plots in `incanter.chart` module
 * Bugfixes

## Changes since 1.3.0 ##

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

