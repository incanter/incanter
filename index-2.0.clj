{:namespaces
 ({:doc
   "This is library provides functions for performing\nbasic Bayesian modeling and inference.\n",
   :author "David Edgar Liebke",
   :name "incanter.bayes",
   :wiki-url "http://incanter.github.com/incanter/bayes-api.html",
   :source-url nil}
  {:doc
   "Statistical functions for work with 'censored' (truncated) distributions.",
   :name "incanter.censored",
   :wiki-url "http://incanter.github.com/incanter/censored-api.html",
   :source-url nil}
  {:doc
   "This is the core charting library for Incanter.\nIt provides basic scatter plots, histograms, box plots\nxy plots, bar charts, line charts, as well as\nspecialized charts like trace plots and Bland-Altman\nplots.\n\nThis library is built on the JFreeChart library\n(http://www.jfree.org/jfreechart/).\n",
   :author "David Edgar Liebke",
   :name "incanter.charts",
   :wiki-url "http://incanter.github.com/incanter/charts-api.html",
   :source-url nil}
  {:doc
   "This is the core numerics library for Incanter.\nIt provides functions for vector- and matrix-based\nmathematical operations and the core data manipulation\nfunctions for Incanter.\n\nThis library is built on Clatrix (https://github.com/tel/clatrix)\nand Parallel Colt\n(http://sites.google.com/site/piotrwendykier/software/parallelcolt)\nan extension of the Colt numerics library\n(http://acs.lbl.gov/~hoschek/colt/).\n",
   :author "David Edgar Liebke",
   :name "incanter.core",
   :wiki-url "http://incanter.github.com/incanter/core-api.html",
   :source-url nil}
  {:doc
   "Provides access to different datasets that are bundled with Incanter.",
   :name "incanter.datasets",
   :wiki-url "http://incanter.github.com/incanter/datasets-api.html",
   :source-url nil}
  {:doc
   "Probability functions (pdf, cdf, draw, etc.) for common distributions, and for collections, sets, and maps.",
   :author "Mark M. Fredrickson and William Leung",
   :name "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter/distributions-api.html",
   :source-url nil}
  {:doc
   "Excel module for reading and writing Incanter datasets.  Recognizes both old and new\nExcel file formats (.xls and .xlsx).",
   :author "David James Humphreys",
   :name "incanter.excel",
   :wiki-url "http://incanter.github.com/incanter/excel-api.html",
   :source-url nil}
  {:doc
   "Library for converting infix mathematical formula to prefix expressions",
   :author "J. Bester",
   :name "incanter.infix",
   :wiki-url "http://incanter.github.com/incanter/infix-api.html",
   :source-url nil}
  {:doc
   "Interpolation and approximation of collection of points..\nSupported types: linear, polynomial, cubic spline,\ncubic hermite spline, B-spline, linear least squares.\nSupports 1-dimensional and 2-dimensional interpolations.",
   :author "Nikita Beloglazov",
   :name "incanter.interpolation",
   :wiki-url
   "http://incanter.github.com/incanter/interpolation-api.html",
   :source-url nil}
  {:doc
   "Library for reading and writing Incanter datasets and matrices.",
   :name "incanter.io",
   :wiki-url "http://incanter.github.com/incanter/io-api.html",
   :source-url nil}
  {:doc
   "This library is used to render LaTex Math equations, based\non the jLateXMath library, and applying them incanter.charts as annotations\nand subtitles.",
   :author "David Edgar Liebke",
   :name "incanter.latex",
   :wiki-url "http://incanter.github.com/incanter/latex-api.html",
   :source-url nil}
  {:doc
   "A simple library that provides functions for persisting\nIncanter data structures using MongoDB.\n\nUse incanter.mongodb in combination with the somnium.congomongo library.\nFor usage examples, see the Congomongo README at http://github.com/somnium/congomongo,\nand the examples/blog/mongodb_datasets.clj file in the Incanter distribution.\n\nHere are Somnium's descriptions of Congomongo's functions:\n\n  (mongo! & args) : Creates a Mongo object and sets the default database.\n     Keyword arguments include:\n     :host -> defaults to localhost\n     :port -> defaults to 27017\n     :db   -> defaults to nil (you'll have to set it anyway, might as well do it now.)\n\n  (get-coll coll) : Returns a DBCollection object\n\n  (fetch coll & options) : Fetches objects from a collection. Optional arguments include\n   :where  -> takes a query map\n   :only   -> takes an array of keys to retrieve\n   :as     -> what to return, defaults to :clojure, can also be :json or :mongo\n   :from   -> argument type, same options as above\n   :one?   -> defaults to false, use fetch-one as a shortcut\n   :count? -> defaults to false, use fetch-count as a shortcut\n\n  (fetch-one coll & options) : same as (fetch collection :one? true)\n\n  (fetch-count coll & options) : same as (fetch collection :count? true)\n\n  (insert! coll obj & options) : Inserts a map into collection. Will not overwrite existing maps.\n   Takes optional from and to keyword arguments. To insert\n   as a side-effect only specify :to as nil.\n\n  (mass-insert! coll objs & options) : Inserts a sequence of maps.\n\n  (update! coll old new & options) : Alters/inserts a map in a collection. Overwrites existing objects.\n   The shortcut forms need a map with valid :_id and :_ns fields or\n   a collection and a map with a valid :_id field.\n\n  (destroy! coll query-map) : Removes map from collection. Takes a collection name and\n    a query map\n\n  (add-index! coll fields & options) : Adds an index on the collection for the specified fields if it does not exist.\n    Options include:\n    :unique -> defaults to false\n    :force  -> defaults to true\n\n  (drop-index! coll fields) : Drops an index on the collection for the specified fields\n\n  (drop-all-indexes! coll) : Drops all indexes from a collection\n\n  (get-indexes coll & options) : Get index information on collection\n\n  (drop-database title) : drops a database from the mongo server\n\n  (set-database title) : atomically alters the current database\n\n  (databases) : List databases on the mongo server\n\n  (collections) : Returns the set of collections stored in the current database\n\n  (drop-collection coll) : Permanently deletes a collection. Use with care.",
   :author "David Edgar Liebke",
   :name "incanter.mongodb",
   :wiki-url "http://incanter.github.com/incanter/mongodb-api.html",
   :source-url nil}
  {:doc "Optimization-relates functions.",
   :name "incanter.optimize",
   :wiki-url "http://incanter.github.com/incanter/optimize-api.html",
   :source-url nil}
  {:doc
   "This library currently has only a single function, save-pdf, which saves\ncharts as a PDF file.",
   :name "incanter.pdf",
   :wiki-url "http://incanter.github.com/incanter/pdf-api.html",
   :source-url nil}
  {:doc "Self-Organizing-Map Neural Network Library.",
   :name "incanter.som",
   :wiki-url "http://incanter.github.com/incanter/som-api.html",
   :source-url nil}
  {:doc "SQL module for interacting with databases.",
   :name "incanter.sql",
   :wiki-url "http://incanter.github.com/incanter/sql-api.html",
   :source-url nil}
  {:doc
   "This is the core statistical library for Incanter.\nIt provides probability functions (cdf, pdf, quantile),\nrandom number generation, statistical tests, basic\nmodeling functions, similarity/association measures,\nand more.\n\nThis library is built on Parallel Colt\n(http://sites.google.com/site/piotrwendykier/software/parallelcolt),\nan extension of the Colt numerics library\n(http://acs.lbl.gov/~hoschek/colt/).\n",
   :author "David Edgar Liebke and Bradford Cross",
   :name "incanter.stats",
   :wiki-url "http://incanter.github.com/incanter/stats-api.html",
   :source-url nil}
  {:doc
   "This library currently has only a single function, save-svg, which saves\ncharts as an SVG file.",
   :name "incanter.svg",
   :wiki-url "http://incanter.github.com/incanter/svg-api.html",
   :source-url nil}
  {:doc
   "A library for performing symbolic math, a port from SICP (http://mitpress.mit.edu/sicp/).",
   :author "Bryce Nyeggen, with modifications by David Edgar Liebke",
   :name "incanter.symbolic",
   :wiki-url "http://incanter.github.com/incanter/symbolic-api.html",
   :source-url nil}
  {:doc
   "This is a port of Zoo from R in order to create the basis\nof a library for time series data.\n\nThis library is built on Parallel Colt\n(http://sites.google.com/site/piotrwendykier/software/parallelcolt),\nan extension of the Colt numerics library\n(http://acs.lbl.gov/~hoschek/colt/).",
   :author "David Edgar Liebke",
   :name "incanter.zoo",
   :wiki-url "http://incanter.github.com/incanter/zoo-api.html",
   :source-url nil}
  {:doc "Functions for reading and writing to cells.",
   :name "incanter.excel.cells",
   :wiki-url
   "http://incanter.github.com/incanter/excel.cells-api.html",
   :source-url nil}
  {:doc nil,
   :name "incanter.excel.workbook",
   :wiki-url
   "http://incanter.github.com/incanter/excel.workbook-api.html",
   :source-url nil}),
 :vars
 ({:raw-source-url
   "https://github.com/incanter/incanter/raw/f23547637a9ee1d901725f591cfc09a1a2dc46c1/modules/incanter-core/src/incanter/bayes.clj",
   :name "sample-model-params",
   :file "modules/incanter-core/src/incanter/bayes.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/f23547637a9ee1d901725f591cfc09a1a2dc46c1/modules/incanter-core/src/incanter/bayes.clj#L35",
   :line 35,
   :var-type "function",
   :arglists ([size {:keys [x y coefs residuals]}]),
   :doc
   "\nReturns a sample of the given size of the parameters (coefficients and\nerror variance) of the given linear-model. The sample is generated using\nGibbs sampling.\n\nSee also:\n  incanter.stats/linear-model\n\nExamples:\n  (use '(incanter core datasets stats charts bayes))\n\n  (def ols-data (to-matrix (get-dataset :survey)))\n  (def x (sel ols-data (range 0 2313) (range 1 10)))\n  (def y (sel ols-data (range 0 2313) 10))\n  (def lm (linear-model y x :intercept false))\n  (def param-samp (sample-model-params 5000 lm))\n\n  ;; view trace plots\n  (view (trace-plot (:var param-samp )))\n  (view (trace-plot (sel (:coefs param-samp) :cols 0)))\n\n  ;; view histograms\n  (view (histogram (:var param-samp)))\n  (view (histogram (sel (:coefs param-samp) :cols 0)))\n\n  ;; calculate statistics\n  (map mean (trans (:coefs param-samp)))\n  (map median (trans (:coefs param-samp)))\n  (map sd (trans (:coefs param-samp)))\n\n  ;; show the 95% bayesian confidence interval for the first coefficient\n  (quantile (sel (:coefs param-samp) :cols 0) :probs [0.025 0.975])\n",
   :namespace "incanter.bayes",
   :wiki-url
   "http://incanter.github.com/incanter//bayes-api.html#incanter.bayes/sample-model-params"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/f23547637a9ee1d901725f591cfc09a1a2dc46c1/modules/incanter-core/src/incanter/bayes.clj",
   :name "sample-multinomial-params",
   :file "modules/incanter-core/src/incanter/bayes.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/f23547637a9ee1d901725f591cfc09a1a2dc46c1/modules/incanter-core/src/incanter/bayes.clj#L95",
   :line 95,
   :var-type "function",
   :arglists ([size counts]),
   :doc
   "\nReturns a sample of multinomial proportion parameters.\nThe counts are assumed to have a multinomial distribution.\nA uniform prior distribution is assigned to the multinomial vector\ntheta, then the posterior distribution of theta is\nproportional to a dirichlet distribution with parameters\n(plus counts 1).\n\n\nExamples:\n  (use '(incanter core stats bayes charts))\n\n  (def  samp-props (sample-multinomial-params 1000 [727 583 137]))\n\n  ;; view means, 95% CI, and histograms of the proportion parameters\n  (mean (sel samp-props :cols 0))\n  (quantile (sel samp-props :cols 0) :probs [0.0275 0.975])\n  (view (histogram (sel samp-props :cols 0)))\n  (mean (sel samp-props :cols 1))\n  (quantile (sel samp-props :cols 1) :probs [0.0275 0.975])\n  (view (histogram (sel samp-props :cols 1)))\n  (mean (sel samp-props :cols 2))\n  (quantile (sel samp-props :cols 2) :probs [0.0275 0.975])\n  (view (histogram (sel samp-props :cols 2)))\n\n  ;; view  a histogram of the difference in proportions between the first\n  ;; two candidates\n  (view (histogram (minus (sel samp-props :cols 0) (sel samp-props :cols 1))))\n",
   :namespace "incanter.bayes",
   :wiki-url
   "http://incanter.github.com/incanter//bayes-api.html#incanter.bayes/sample-multinomial-params"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/f23547637a9ee1d901725f591cfc09a1a2dc46c1/modules/incanter-core/src/incanter/bayes.clj",
   :name "sample-mvn-params",
   :file "modules/incanter-core/src/incanter/bayes.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/f23547637a9ee1d901725f591cfc09a1a2dc46c1/modules/incanter-core/src/incanter/bayes.clj#L131",
   :line 131,
   :var-type "function",
   :arglists ([size y & options]),
   :doc
   "\nReturns samples of means (sampled from an mvn distribution) and vectorized covariance\nmatrices (sampled from an inverse-wishart distribution) for the given mvn data.\n\nArguments:\n  size -- the number of samples to return\n  y -- the data used to estimate the parameters\n\n\nReturns map with following fields:\n  :means\n  :sigmas\n\n\nExamples:\n\n  (use '(incanter core stats bayes charts))\n  (def y (sample-mvn 500 :mean [0 0] :sigma (identity-matrix 2)))\n  (def samp (sample-mvn-params 1000 y))\n\n  (map mean (trans (:means samp)))\n  (symmetric-matrix (map mean (trans (:sigmas samp))) :lower false)\n\n  (view (histogram (sel (:means samp) :cols 0) :x-label \"mean 1\"))\n  (view (histogram (sel (:means samp) :cols 1) :x-label \"mean 2\"))\n  (view (histogram (sel (:sigmas samp) :cols 1) :x-label \"covariance\"))\n  (view (histogram (sel (:sigmas samp) :cols 0) :x-label \"variance 1\"))\n  (view (histogram (sel (:sigmas samp) :cols 2) :x-label \"variance 2\"))\n\n  (map #(quantile % :probs [0.025 0.0975]) (trans (:means samp)))\n  (map #(quantile % :probs [0.025 0.0975]) (trans (:sigmas samp)))\n\n\n\n\n  (use '(incanter core stats bayes charts))\n  (def y (sample-mvn 500 :sigma (symmetric-matrix [10 5 10]) :mean [5 2]))\n  (def samp (sample-mvn-params 1000 y))\n  (symmetric-matrix (map mean (trans (:sigmas samp))) :lower false)\n  (map mean (trans (:means samp)))\n",
   :namespace "incanter.bayes",
   :wiki-url
   "http://incanter.github.com/incanter//bayes-api.html#incanter.bayes/sample-mvn-params"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/f23547637a9ee1d901725f591cfc09a1a2dc46c1/modules/incanter-core/src/incanter/bayes.clj",
   :name "sample-proportions",
   :file "modules/incanter-core/src/incanter/bayes.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/f23547637a9ee1d901725f591cfc09a1a2dc46c1/modules/incanter-core/src/incanter/bayes.clj#L88",
   :line 88,
   :var-type "function",
   :arglists ([size counts]),
   :doc
   "sample-proportions has been renamed sample-multinomial-params",
   :namespace "incanter.bayes",
   :wiki-url
   "http://incanter.github.com/incanter//bayes-api.html#incanter.bayes/sample-proportions"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj",
   :name "censored-mean-lower",
   :file "modules/incanter-core/src/incanter/censored.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj#L59",
   :line 59,
   :var-type "function",
   :arglists ([a mu sigma]),
   :doc
   "\nReturns the mean of a normal distribution (with mean mu and standard\ndeviation sigma) with the lower tail censored at 'a'\n",
   :namespace "incanter.censored",
   :wiki-url
   "http://incanter.github.com/incanter//censored-api.html#incanter.censored/censored-mean-lower"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj",
   :name "censored-mean-two-sided",
   :file "modules/incanter-core/src/incanter/censored.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj#L22",
   :line 22,
   :var-type "function",
   :arglists ([a b mu sigma]),
   :doc
   "\nReturns the mean of a normal distribution (with mean mu and standard\ndeviation sigma) with the lower tail censored at 'a' and the upper\ntail censored at 'b'\n",
   :namespace "incanter.censored",
   :wiki-url
   "http://incanter.github.com/incanter//censored-api.html#incanter.censored/censored-mean-two-sided"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj",
   :name "censored-mean-upper",
   :file "modules/incanter-core/src/incanter/censored.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj#L89",
   :line 89,
   :var-type "function",
   :arglists ([b mu sigma]),
   :doc
   "\nReturns the mean of a normal distribution (with mean mu and standard\ndeviation sigma) with the upper tail censored at 'b'\n",
   :namespace "incanter.censored",
   :wiki-url
   "http://incanter.github.com/incanter//censored-api.html#incanter.censored/censored-mean-upper"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj",
   :name "censored-variance-lower",
   :file "modules/incanter-core/src/incanter/censored.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj#L71",
   :line 71,
   :var-type "function",
   :arglists ([a mu sigma]),
   :doc
   "\nReturns the variance of a normal distribution (with mean mu and standard\ndeviation sigma) with the lower tail censored at 'a'\n",
   :namespace "incanter.censored",
   :wiki-url
   "http://incanter.github.com/incanter//censored-api.html#incanter.censored/censored-variance-lower"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj",
   :name "censored-variance-two-sided",
   :file "modules/incanter-core/src/incanter/censored.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj#L38",
   :line 38,
   :var-type "function",
   :arglists ([a b mu sigma]),
   :doc
   "\nReturns the variance of a normal distribution (with mean mu and standard\ndeviation sigma) with the lower tail censored at 'a' and the upper\ntail censored at 'b'\n",
   :namespace "incanter.censored",
   :wiki-url
   "http://incanter.github.com/incanter//censored-api.html#incanter.censored/censored-variance-two-sided"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj",
   :name "censored-variance-upper",
   :file "modules/incanter-core/src/incanter/censored.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj#L100",
   :line 100,
   :var-type "function",
   :arglists ([b mu sigma]),
   :doc
   "\nReturns the variance of a normal distribution (with mean mu and standard\ndeviation sigma) with the upper tail censored at 'b'\n",
   :namespace "incanter.censored",
   :wiki-url
   "http://incanter.github.com/incanter//censored-api.html#incanter.censored/censored-variance-upper"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj",
   :name "truncated-variance",
   :file "modules/incanter-core/src/incanter/censored.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj#L118",
   :line 118,
   :var-type "function",
   :arglists
   ([&
     {:keys [mean sd a b],
      :or {mean 0, sd 1, a NEGATIVE_INFINITY, b POSITIVE_INFINITY}}]),
   :doc
   "\nReturns the variance of a normal distribution truncated at a and b.\n\nOptions:\n  :mean (default 0) mean of untruncated normal distribution\n  :sd (default 1) standard deviation of untruncated normal distribution\n  :a (default -infinity) lower truncation point\n  :b (default +infinity) upper truncation point\n\nExamples:\n\n  (use '(incanter core stats))\n  (truncated-variance :a -1.96 :b 1.96)\n  (truncated-variance :a 0)\n  (truncated-variance :b 0)\n\n  (use 'incanter.charts)\n  (def x (range -3 3 0.1))\n  (def plot (xy-plot x (map #(truncated-variance :a %) x)))\n  (view plot)\n  (add-lines plot x (map #(truncated-variance :b %) x))\n\n  (def samp (sample-normal 10000))\n  (add-lines plot x (map #(variance (filter (fn [s] (> s %)) samp)) x))\n  (add-lines plot x (map #(variance (mult samp (indicator (fn [s] (> s %)) samp))) x))\n\n\nReferences:\n  DeMaris, A. (2004) Regression with social data: modeling continuous and limited response variables.\n    Wiley-IEEE.\n\n  http://en.wikipedia.org/wiki/Truncated_normal_distribution\n",
   :namespace "incanter.censored",
   :wiki-url
   "http://incanter.github.com/incanter//censored-api.html#incanter.censored/truncated-variance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-box-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L431",
   :line 431,
   :var-type "macro",
   :arglists ([chart x & options]),
   :doc
   "\nAdds an additional box to an existing box-plot, returns the modified chart object.\n\nOptions:\n  :series-label (default x expression)\n\nExamples:\n\n  (use '(incanter core charts stats datasets))\n  (doto (box-plot (sample-normal 1000) :legend true)\n        view\n        (add-box-plot (sample-normal 1000 :sd 2))\n        (add-box-plot (sample-gamma 1000)))\n\n\n  (with-data (get-dataset :iris)\n    (doto (box-plot :Sepal.Length :legend true)\n      (add-box-plot :Petal.Length)\n      (add-box-plot :Sepal.Width)\n      (add-box-plot :Petal.Width)\n      view))\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-box-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-categories",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L494",
   :line 494,
   :var-type "macro",
   :arglists ([chart categories values & options]),
   :doc
   "\nAdds an additional categories to an existing bar-chart or line-chart, returns the modified chart object.\n\nOptions:\n  :group-by\n  :series-label\n\nExamples:\n\n  (use '(incanter core charts stats datasets))\n  (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))\n  (def years (mapcat identity (repeat 4 [2007 2008 2009])))\n  (def x (sample-uniform 12 :integers true :max 100))\n  (def plot (bar-chart years x :group-by seasons :legend true))\n  (view plot)\n  (add-categories plot years [10 20 40] :series-label \"winter-break\")\n\n  (add-categories plot\n                  (plus 3 years)\n                  (sample-uniform 12 :integers true :max 100)\n                  :group-by seasons)\n\n  (def plot2 (line-chart years x :group-by seasons :legend true))\n    (view plot2)\n    (add-categories plot2 (plus 3 years) (sample-uniform 12 :integers true :max 100) :group-by seasons)\n\n    (with-data (get-dataset :iris)\n      (doto (line-chart :Species :Sepal.Length\n                        :data ($rollup mean :Sepal.Length :Species)\n                        :legend true)\n        (add-categories :Species :Sepal.Width :data ($rollup mean :Sepal.Width :Species))\n        (add-categories :Species :Petal.Length :data ($rollup mean :Petal.Length :Species))\n        (add-categories :Species :Petal.Width :data ($rollup mean :Petal.Width :Species))\n        view))\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-categories"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-function",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L699",
   :line 699,
   :var-type "macro",
   :arglists ([chart function min-range max-range & options]),
   :doc
   "\nAdds a xy-plot of the given function to the given chart, returning\na modified version of the chart.\n\nOptions:\n  :series-label (default x expression)\n  :step-size (default (/ (- max-range min-range) 500))\n\nSee also:\n  function-plot, view, save, add-function, add-points, add-lines\n\n\nExamples:\n\n  (use '(incanter core stats charts))\n\n  ;; plot the sine and cosine functions\n  (doto (function-plot sin (- Math/PI) Math/PI)\n        (add-function cos (- Math/PI) Math/PI)\n        view)\n\n\n  ;; plot two normal pdf functions\n  (doto (function-plot pdf-normal -3 3 :legend true)\n        (add-function (fn [x] (pdf-normal x :mean 0.5 :sd 0.5)) -3 3)\n        view)\n\n\n  ;; plot a user defined function and its derivative\n  (use '(incanter core charts optimize))\n\n  ;; define the function, x^3 + 2x^2 + 2x + 3\n  (defn cubic [x] (+ (* x x x) (* 2 x x) (* 2 x) 3))\n\n  ;; use the derivative function to get a function\n  ;; that approximates its derivative\n  (def deriv-cubic (derivative cubic))\n\n  ;; plot the cubic function and its derivative\n  (doto (function-plot cubic -10 10)\n        (add-function deriv-cubic -10 10)\n        view)\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-function"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-histogram",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L380",
   :line 380,
   :var-type "macro",
   :arglists ([chart x & options]),
   :doc
   "\nAdds a histogram to an existing histogram plot, returns the modified\nchart object.\n\nOptions:\n  :nbins (default 10) number of bins for histogram\n  :series-label (default x expression)\n\nExamples:\n\n  (use '(incanter core charts stats datasets))\n  (doto (histogram (sample-normal 1000)\n                   :legend true)\n        view\n        (add-histogram (sample-normal 1000 :sd 0.5)))\n\n\n  (with-data (get-dataset :iris)\n    (doto (histogram :Sepal.Length :legend true)\n      (add-histogram :Petal.Length)\n      view))\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-histogram"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-image",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L3236",
   :line 3236,
   :var-type "function",
   :arglists ([chart x y img & options]),
   :doc
   "\nAdds an image to the chart at the given coordinates.\n\nArguments:\n  chart -- the chart to add the polygon to.\n  x, y -- the coordinates to place the image\n  img -- a java.awt.Image object\n\n\nExamples:\n  (use '(incanter core charts latex))\n\n   (doto (function-plot sin -10 10)\n    (add-image 0 0 (latex \"\\\\frac{(a+b)^2} {(a-b)^2}\"))\n    view)\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-image"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-lines",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L631",
   :line 631,
   :var-type "macro",
   :arglists ([chart x y & options]),
   :doc
   "\nPlots lines on the given scatter or line plot (xy-plot) of the (x,y) points.\nEquivalent to R's lines function, returns the modified chart object.\n\nOptions:\n  :series-label (default x expression)\n  :points (default false)\n  :auto-sort (default true) sort data by x\n\n\nExamples:\n\n  (use '(incanter core stats io datasets charts))\n  (def cars (to-matrix (get-dataset :cars)))\n  (def y (sel cars :cols 0))\n  (def x (sel cars :cols 1))\n  (def plot1 (scatter-plot x y :legend true))\n  (view plot1)\n\n  ;; add regression line to scatter plot\n  (def lm1 (linear-model y x))\n  (add-lines plot1 x (:fitted lm1))\n\n  ;; model the data without an intercept\n  (def lm2 (linear-model y x :intercept false))\n  (add-lines plot1 x (:fitted lm2))\n\n\n  ;; Clojure's doto macro can be used to build a chart\n  (doto (histogram (sample-normal 1000) :density true)\n        (add-lines (range -3 3 0.05) (pdf-normal (range -3 3 0.05)))\n        view)\n\n\n  (with-data (get-dataset :iris)\n      (doto (xy-plot :Sepal.Width :Sepal.Length :legend true)\n            (add-lines :Petal.Width :Petal.Length)\n            view))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-lines"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-parametric",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L765",
   :line 765,
   :var-type "macro",
   :arglists ([chart function min-range max-range & options]),
   :doc
   "\nAdds a xy-plot of the given parametric function to the given chart, returning\na modified version of the chart.\nFunction takes 1 argument t and returns point [x y].\n\nOptions:\n  :series-label (default function expression)\n  :step-size (default (/ (- max-range min-range) 500))\n\nSee also:\n  parametric-plot, view, save, add-function, add-points, add-lines\n\n\nExamples:\n\n  (use '(incanter core charts))\n\n  ;;; Plot square with circle inside.\n  (defn circle [t] [(cos t) (sin t)])\n  (doto (xy-plot [1 -1 -1 1 1] [1 1 -1 -1 1] :auto-sort false)\n        (add-parametric circle 0 (* 2 Math/PI))\n        (view))\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-parametric"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-pointer",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L3042",
   :line 3042,
   :var-type "function",
   :arglists ([chart x y & options]),
   :doc
   "\nAdds an arrow annotation to the given chart.\n\nArguments:\n  chart -- the chart to annotate\n  x, y -- the coordinate to add the annotation\n\n\nOptions:\n    :text -- (default \"\") text to include at the end of the arrow\n    :angle -- (default :nw) either a number indicating the angle of the arrow\n              or a keyword indicating a direction (:north :nw :west :sw :south\n              :se :east :ne)\n\n\nExamples:\n\n  (use '(incanter core charts))\n  (def x (range (* -2 Math/PI) (* 2 Math/PI) 0.01))\n  (def plot (xy-plot x (sin x)))\n  (view plot)\n  ;; annotate the plot\n  (doto plot\n    (add-pointer (- Math/PI) (sin (- Math/PI)) :text \"(-pi, (sin -pi))\")\n    (add-pointer Math/PI (sin Math/PI) :text \"(pi, (sin pi))\" :angle :ne)\n    (add-pointer (* 1/2 Math/PI) (sin (* 1/2 Math/PI)) :text \"(pi/2, (sin pi/2))\" :angle :south))\n\n  ;; try the different angle options\n  (add-pointer plot 0 0 :text \"north\" :angle :north)\n  (add-pointer plot 0 0 :text \"nw\" :angle :nw)\n  (add-pointer plot 0 0 :text \"ne\" :angle :ne)\n  (add-pointer plot 0 0 :text \"west\" :angle :west)\n  (add-pointer plot 0 0 :text \"east\" :angle :east)\n  (add-pointer plot 0 0 :text \"south\" :angle :south)\n  (add-pointer plot 0 0 :text \"sw\" :angle :sw)\n  (add-pointer plot 0 0 :text \"se\" :angle :se)\n\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-pointer"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-points",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L823",
   :line 823,
   :var-type "macro",
   :arglists ([chart x y & options]),
   :doc
   "\nPlots points on the given scatter-plot or xy-plot of the (x,y) points.\nEquivalent to R's lines function, returns the modified chart object.\n\nOptions:\n  :series-label (default x expression)\n\nExamples:\n\n  (use '(incanter core stats io datasets charts))\n  (def cars (to-matrix (get-dataset :cars)))\n  (def y (sel cars :cols 0))\n  (def x (sel cars :cols 1))\n\n  ;; add regression line to scatter plot\n  (def lm1 (linear-model y x))\n  ;; model the data without an intercept\n  (def lm2 (linear-model y x :intercept false))\n\n  (doto (xy-plot x (:fitted lm1) :legend true)\n        view\n        (add-points x y)\n        (add-lines x (:fitted lm2)))\n\n\n  (with-data (get-dataset :iris)\n    (doto (scatter-plot :Sepal.Length :Sepal.Width :data ($where {:Species \"setosa\"}))\n          (add-points :Sepal.Length :Sepal.Width :data ($where {:Species \"versicolor\"}))\n          (add-points :Sepal.Length :Sepal.Width :data ($where {:Species \"virginica\"}))\n          view))\n\n  ;; of course this chart can be achieved in a single line:\n  (view (scatter-plot :Sepal.Length :Sepal.Width :group-by :Species :data (get-dataset :iris)))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-points"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-polygon",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L3174",
   :line 3174,
   :var-type "function",
   :arglists ([chart coords & options]),
   :doc
   "\nAdds a polygon outline defined by a given coordinates. The last coordinate will\nclose with the first. If only two points are given, it will plot a line.\n\nArguments:\n  chart -- the chart to add the polygon to.\n  coords -- a list of coords (an n-by-2 matrix can also be used)\n\n\nExamples:\n  (use '(incanter core stats charts))\n  (def x (range -3 3 0.01))\n  (def plot (xy-plot x (pdf-normal x)))\n  (view plot)\n\n  ;; add polygon to the chart\n  (add-polygon plot [[-1.96 0] [1.96 0] [1.96 0.4] [-1.96 0.4]])\n  ;; the coordinates can also be passed in a matrix\n  ;; (def points (matrix [[-1.96 0] [1.96 0] [1.96 0.4] [-1.96 0.4]]))\n  ;; (add-polygon plot points)\n  ;; add a text annotation\n  (add-text plot -1.25 0.35 \"95% Conf Interval\")\n\n  ;; PCA chart example\n  (use '(incanter core stats charts datasets))\n  ;; load the iris dataset\n  (def iris (to-matrix (get-dataset :iris)))\n  ;; run the pca\n  (def pca (principal-components (sel iris :cols (range 4))))\n  ;; extract the first two principal components\n  (def pc1 (sel (:rotation pca) :cols 0))\n  (def pc2 (sel (:rotation pca) :cols 1))\n\n  ;; project the first four dimension of the iris data onto the first\n  ;; two principal components\n  (def x1 (mmult (sel iris :cols (range 4)) pc1))\n  (def x2 (mmult (sel iris :cols (range 4)) pc2))\n\n  ;; now plot the transformed data, coloring each species a different color\n  (def plot (scatter-plot x1 x2\n                          :group-by (sel iris :cols 4)\n                          :x-label \"PC1\" :y-label \"PC2\" :title \"Iris PCA\"))\n\n  (view plot)\n  ;; put box around the first group\n  (add-polygon plot [[-3.2 -6.3] [-2 -6.3] [-2 -3.78] [-3.2 -3.78]])\n  ;; add some text annotations\n  (add-text plot -2.5 -6.5 \"Setosa\")\n  (add-text plot -5 -5.5 \"Versicolor\")\n  (add-text plot -8 -5.5 \"Virginica\")\n\n\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-polygon"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-subtitle",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L3986",
   :line 3986,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "\nAdds a JFreeChart title object to a chart as a subtitle.\n\nExamples:\n  (use '(incanter core charts latex))\n\n  (doto (function-plot sin -10 10)\n    (add-subtitle \"subtitle\")\n    (add-subtitle (latex \" \\\\frac{(a+b)^2} {(a-b)^2}\"))\n    view)\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-subtitle"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-text",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L3129",
   :line 3129,
   :var-type "function",
   :arglists ([chart x y text & options]),
   :doc
   "\nAdds a text annotation centered at the given coordinates.\n\nArguments:\n  chart -- the chart to annotate\n  x, y -- the coordinates to center the text\n  text -- the text to add\n\n\nExamples:\n\n  ;; PCA chart example\n  (use '(incanter core stats charts datasets))\n  ;; load the iris dataset\n  (def iris (to-matrix (get-dataset :iris)))\n  ;; run the pca\n  (def pca (principal-components (sel iris :cols (range 4))))\n  ;; extract the first two principal components\n  (def pc1 (sel (:rotation pca) :cols 0))\n  (def pc2 (sel (:rotation pca) :cols 1))\n\n  ;; project the first four dimension of the iris data onto the first\n  ;; two principal components\n  (def x1 (mmult (sel iris :cols (range 4)) pc1))\n  (def x2 (mmult (sel iris :cols (range 4)) pc2))\n\n  ;; now plot the transformed data, coloring each species a different color\n  (def plot (scatter-plot x1 x2\n                          :group-by (sel iris :cols 4)\n                          :x-label \"PC1\" :y-label \"PC2\" :title \"Iris PCA\"))\n  (view plot)\n  ;; add some text annotations\n  (add-text plot -2.5 -6.5 \"Setosa\")\n  (add-text plot -5 -5.5 \"Versicolor\")\n  (add-text plot -8 -5.5 \"Virginica\")\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-text"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "area-chart",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L2230",
   :line 2230,
   :var-type "macro",
   :arglists ([categories values & options]),
   :doc
   "Returns a JFreeChart object representing an area-chart of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nArguments:\n  categories -- a sequence of categories\n  values -- a sequence of numeric values\n\nOptions:\n  :title (default '') main title\n  :x-label (default 'Categories')\n  :y-label (default 'Value')\n  :series-label\n  :legend (default false) prints legend\n  :vertical (default true) the orientation of the plot\n  :group-by (default nil) -- a vector of values used to group the values into\n                             series within each category.\n\n\nSee also:\n  view and save\n\nExamples:\n\n\n  (use '(incanter core stats charts datasets))\n\n  (with-data (get-dataset :co2)\n    (view (area-chart :Type :uptake\n                     :title \"CO2 Uptake\"\n                     :group-by :Treatment\n                     :x-label \"Grass Types\" :y-label \"Uptake\"\n                    :legend true)))\n\n\n  (def data (get-dataset :airline-passengers))\n  (view (area-chart :year :passengers :group-by :month :legend true :data data))\n\n  (with-data  (get-dataset :airline-passengers)\n    (view (area-chart :month :passengers :group-by :year :legend true)))\n\n\n  (def data (get-dataset :austres))\n  (view data)\n  (def plot (area-chart :year :population :group-by :quarter :legend true :data data))\n  (view plot)\n  (save plot \"/tmp/austres_plot.png\" :width 1000)\n  (view \"file:///tmp/austres_plot.png\")\n\n\n  (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))\n  (def years (mapcat identity (repeat 4 [2007 2008 2009])))\n  (def values (sample-uniform 12 :integers true :max 100))\n  (view (area-chart years values :group-by seasons :legend true))\n\n  (view (area-chart [\"a\" \"b\" \"c\"] [10 20 30]))\n  (view (area-chart [\"a\" \"a\" \"b\" \"b\" \"c\" \"c\" ] [10 20 30 10 40 20]\n                   :legend true\n                   :group-by [\"I\" \"II\" \"I\" \"II\" \"I\" \"II\"]))\n\n  ;; add a series label\n  (def plot (area-chart [\"a\" \"b\" \"c\"] [10 20 30] :legend true :series-label \"s1\"))\n  (view plot)\n  (add-categories plot [\"a\" \"b\" \"c\"] [5 25 40] :series-label \"s2\")\n\n  (view (area-chart (sample \"abcdefghij\" :size 10 :replacement true)\n                   (sample-uniform 10 :max 50) :legend true))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/area-chart"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "bar-chart",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L2091",
   :line 2091,
   :var-type "macro",
   :arglists ([categories values & options]),
   :doc
   "\nReturns a JFreeChart object representing a bar-chart of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nArguments:\n  categories -- a sequence of categories\n  values -- a sequence of numeric values\n\nOptions:\n  :title (default '') main title\n  :x-label (default 'Categories')\n  :y-label (default 'Value')\n  :series-label\n\n:legend (default false) prints legend\n  :vertical (default true) the orientation of the plot\n  :group-by (default nil) -- a vector of values used to group the values into\n                             series within each category.\n\n\nSee also:\n  view and save\n\nExamples:\n\n\n  (use '(incanter core stats charts datasets))\n\n  (with-data (get-dataset :co2)\n    (view (bar-chart :Type :uptake\n                     :title \"CO2 Uptake\"\n                     :group-by :Treatment\n                     :x-label \"Grass Types\" :y-label \"Uptake\"\n                    :legend true)))\n\n\n  (def data (get-dataset :airline-passengers))\n  (view (bar-chart :year :passengers :group-by :month :legend true :data data))\n\n  (with-data  (get-dataset :airline-passengers)\n    (view (bar-chart :month :passengers :group-by :year :legend true)))\n\n\n  (def data (get-dataset :austres))\n  (view data)\n  (def plot (bar-chart :year :population :group-by :quarter :legend true :data data))\n  (view plot)\n  (save plot \"/tmp/austres_plot.png\" :width 1000)\n  (view \"file:///tmp/austres_plot.png\")\n\n\n  (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))\n  (def years (mapcat identity (repeat 4 [2007 2008 2009])))\n  (def values (sample-uniform 12 :integers true :max 100))\n  (view (bar-chart years values :group-by seasons :legend true))\n\n  (view (bar-chart [\"a\" \"b\" \"c\"] [10 20 30]))\n  (view (bar-chart [\"a\" \"a\" \"b\" \"b\" \"c\" \"c\" ] [10 20 30 10 40 20]\n                   :legend true\n                   :group-by [\"I\" \"II\" \"I\" \"II\" \"I\" \"II\"]))\n\n  ;; add a series label\n  (def plot (bar-chart [\"a\" \"b\" \"c\"] [10 20 30] :legend true :series-label \"s1\"))\n  (view plot)\n  (add-categories plot [\"a\" \"b\" \"c\"] [5 25 40] :series-label \"s2\")\n\n  (view (bar-chart (sample \"abcdefghij\" :size 10 :replacement true)\n                   (sample-uniform 10 :max 50) :legend true))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/bar-chart"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "bland-altman-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L3353",
   :line 3353,
   :var-type "function",
   :arglists ([x1 x2 & options]),
   :doc
   "\nOptions:\n  :data (default nil) If the :data option is provided a dataset,\n                      column names can be used instead of sequences\n                      of data for arguments x1 and x2.\n\nExamples:\n\n  (use '(incanter core datasets charts))\n  (def flow-meter (to-matrix (get-dataset :flow-meter)))\n  (def x1 (sel flow-meter :cols 1))\n  (def x2 (sel flow-meter :cols 3))\n  (view (bland-altman-plot x1 x2))\n\n  (with-data (get-dataset :flow-meter)\n    (view (bland-altman-plot (keyword \"Wright 1st PEFR\")\n                             (keyword \"Mini Wright 1st PEFR\"))))\n\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Bland-Altman_plot\n  http://www-users.york.ac.uk/~mb55/meas/ba.htm\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/bland-altman-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "box-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L2707",
   :line 2707,
   :var-type "macro",
   :arglists ([x & options]),
   :doc
   "\nReturns a JFreeChart object representing a box-plot of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nOptions:\n  :title (default '') main title\n  :x-label (default x expression)\n  :y-label (default 'Frequency')\n  :legend (default false) prints legend\n  :series-label (default x expression)\n  :group-by (default nil) -- a vector of values used to group the x values into series.\n\nSee also:\n  view and save\n\nExamples:\n\n  (use '(incanter core stats charts))\n  (def gamma-box-plot (box-plot (sample-gamma 1000 :shape 1 :scale 2)\n                        :title \"Gamma Boxplot\"\n                        :legend true))\n  (view gamma-box-plot)\n  (add-box-plot gamma-box-plot (sample-gamma 1000 :shape 2 :scale 2))\n  (add-box-plot gamma-box-plot (sample-gamma 1000 :shape 3 :scale 2))\n\n  ;; use the group-by options\n  (use '(incanter core stats datasets charts))\n  (with-data (get-dataset :iris)\n    (view (box-plot :Petal.Length :group-by :Species :legend true))\n    (view (box-plot :Petal.Width :group-by :Species :legend true))\n    (view (box-plot :Sepal.Length :group-by :Species :legend true))\n    (view (box-plot :Sepal.Width :group-by :Species :legend true)))\n\n  ;; see INCANTER_HOME/examples/probability_plots.clj for more examples of plots\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/box-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "candle-stick-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L1314",
   :line 1314,
   :var-type "macro",
   :arglists ([& options]),
   :doc
   "\nProduces a candle stick chart\n\nOptions:\n  :data (default nil) If the :data option is provided a dataset,\n                      column names can be used instead of sequences\n                      of data as arguments to xy-plot.\n  :date Key for accessing the underlying date series (defaults to :date)\n  :high Key for accessing high value data (defaults to :high)\n  :low Key for accessing low value data (defaults to :low)\n  :open Key for accessing open value data (defaults to :open)\n  :close Key for accessing close value data (defaults to :close)\n  :volume Key for accessing volume data (defaults to :volume). Volume data is optional\n  :title (default 'Candle Stick Plot') main title\n  :time-label (default empty)\n  :value-label (default empty)\n  :legend (default false) prints legend\n  :series-label (default empty)\n\n Example:\n   ;; use default mappings so the dataset must have\n   ;; :date, :high, :low, :open, :close and :volume keys\n   (candle-stick-plot :data <dataset>)\n   ;; more customization\n   (candle-stick-plot\n     :data dataset\n     :high :HighPrice\n     :low :LowPrice\n     :open :StartOfDay\n     :close :CoB\n     :volume :TransactionVolume\n     :legend true\n     :time-label \"CoB date\"\n     :value-label \"Price\"\n     :series-label \"Price time series\"\n     :title \"Price information\")\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/candle-stick-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "clear-background",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L1005",
   :line 1005,
   :var-type "function",
   :arglists ([chart]),
   :doc
   "\nSets the alpha level (transparency) of the plot's background to zero\nremoving the default grid, returns the modified chart object.\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/clear-background"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "dynamic-scatter-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L3644",
   :line 3644,
   :var-type "macro",
   :arglists ([[& slider-bindings] expression & options]),
   :doc
   "\nReturns an scatter-plot bound to sliders (which tend to appear behind the chart).\nSee the sliders macro for more information.\n\n\nExamples:\n\n(use '(incanter core stats charts))\n\n(let [x (range -3 3 0.1)]\n  (view (dynamic-scatter-plot [mean (range -3 3 0.1)\n                               sd (range 0.1 10 0.1)]\n          [x (pdf-normal x :mean mean :sd sd)]\n          :title \"Normal PDF Plot\")))\n\n\n (let [x (range -3 3 0.1)]\n   (view (dynamic-scatter-plot [mean (range -3 3 0.1)\n                                sd (range 0.1 10 0.1)]\n          (for [xi x] [xi (pdf-normal xi :mean mean :sd sd)])\n          :title \"Normal PDF Plot\")))\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/dynamic-scatter-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "dynamic-xy-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L3611",
   :line 3611,
   :var-type "macro",
   :arglists ([[& slider-bindings] expression & options]),
   :doc
   "\nReturns an xy-plot bound to sliders (which tend to appear behind the chart).\nSee the sliders macro for more information.\n\n\nExamples:\n\n  (use '(incanter core stats charts))\n\n  (let [x (range -3 3 0.1)]\n  (view (dynamic-xy-plot [mean (range -3 3 0.1)\n                          sd (range 0.1 10 0.1)]\n                         [x (pdf-normal x :mean mean :sd sd)]\n                         :title \"Normal PDF Plot\")))\n\n (let [x (range -3 3 0.1)]\n   (view (dynamic-xy-plot [mean (range -3 3 0.1)\n                           sd (range 0.1 10 0.1)]\n          (for [xi x] [xi (pdf-normal xi :mean mean :sd sd)])\n          :title \"Normal PDF Plot\")))\n\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/dynamic-xy-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "extend-line",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L578",
   :line 578,
   :var-type "function",
   :arglists ([chart x y & options]),
   :doc
   " Add new data set to an exiting series if it already exists,\notherwise, data set will be added to a newly created series. ",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/extend-line"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "function-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L2786",
   :line 2786,
   :var-type "macro",
   :arglists ([function min-range max-range & options]),
   :doc
   "\nReturns a xy-plot object of the given function over the range indicated\nby the min-range and max-range arguments. Use the 'view' function to\ndisplay the chart, or the 'save' function to write it to a file.\n\nOptions:\n  :title (default '') main title\n  :x-label (default x expression)\n  :y-label (default 'Frequency')\n  :legend (default false) prints legend\n  :series-label (default x expression)\n  :step-size (default (/ (- max-range min-range) 500))\n\nSee also:\n  view, save, add-points, add-lines\n\n\nExamples:\n\n  (use '(incanter core stats charts))\n\n  (view (function-plot sin (- Math/PI) Math/PI))\n  (view (function-plot pdf-normal -3 3))\n\n  (defn cubic [x] (+ (* x x x) (* 2 x x) (* 2 x) 3))\n  (view (function-plot cubic -10 10))\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/function-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "get-series",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L3435",
   :line 3435,
   :var-type "function",
   :arglists ([chart] [chart series-idx]),
   :doc "get-series",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/get-series"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "has-series?",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L355",
   :line 355,
   :var-type "function",
   :arglists ([chart series-label]),
   :doc "Test to see if a chart has a series name series-lab",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/has-series?"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "heat-map",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L2968",
   :line 2968,
   :var-type "macro",
   :arglists ([function x-min x-max y-min y-max & options]),
   :doc
   "\nUsage: (heat-map function x-min x-max y-min y-max & options)\n\nReturns a JFreeChart object representing a heat map of the function across\nthe given x and y ranges. Use the 'view' function to display the chart, or\nthe 'save' function to write it to a file.\n\nArguments:\n  function -- a function that takes two scalar arguments and returns a scalar\n  x-min    -- lower bound for the first value of the function\n  x-max    -- upper bound for the first value of the function\n  y-min    -- lower bound for the second value of the function\n  y-max    -- upper bound for the second value of the function\n\nOptions:\n  :title\n  :x-label (default 'x-min < x < x-max')\n  :y-label (default 'y-min < y < y-max')\n  :z-label -- defaults to function's name\n  :color? (default true) -- should the plot be in color or not?\n  :include-zero? (default true) -- should the plot include the origin if it\n                                   is not in the ranges specified?\n\nExamples:\n  (use '(incanter core charts))\n  (defn f [x y] (sin (sqrt (plus (sq x) (sq y)))))\n  (view (heat-map f -10 10 -15 15))\n  (view (heat-map f -10 10 -10 10 :color? false))\n  (view (heat-map f 5 10 5 10 :include-zero? false))\n\n  (defn f2 [x y] (plus (sq x) (sq y)))\n  (view (heat-map f2 -10 10 -10 10))\n  (view (heat-map f2 -10 10 -10 10 :color? false))\n\n  (use 'incanter.stats)\n  (defn f3 [x y] (pdf-normal (sqrt (plus (sq x) (sq y)))))\n  (view (heat-map f3 -3 3 -3 3 :x-label \"x1\" :y-label \"x2\" :z-label \"pdf\"))\n  (view (heat-map f3 -3 3 -3 3 :color? false))\n\n  (defn f4 [x y] (minus (sq x) (sq y)))\n  (view (heat-map f4 -10 10 -10 10))\n  (view (heat-map f4 -10 10 -10 10 :color? false))\n\n\n  (use '(incanter core stats charts))\n  (let [data [[0 5 1 2]\n                [0 10 1.9 1]\n                [15 0 0.5 1.5]\n                [18 10 4.5 2.1]]\n        diffusion (fn [x y]\n                    (sum (map #(pdf-normal (euclidean-distance [x y] (take 2 %))\n                                           :mean (nth % 2) :sd (last %))\n                              data)))]\n    (view (heat-map diffusion -5 20 -5 20)))\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/heat-map"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "histogram",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L1844",
   :line 1844,
   :var-type "macro",
   :arglists ([x & options]),
   :doc
   "\nReturns a JFreeChart object representing the histogram of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nOptions:\n  :nbins (default 10) number of bins\n  :density (default false) if false, plots frequency, otherwise density\n  :title (default 'Histogram') main title\n  :x-label (default x expression)\n  :y-label (default 'Frequency')\n  :legend (default false) prints legend\n  :series-label (default x expression)\n\n\nSee also:\n  view, save, add-histogram\n\nExamples:\n\n  (use '(incanter core charts stats))\n  (view (histogram (sample-normal 1000)))\n\n  # plot a density histogram\n  (def hist (histogram (sample-normal 1000) :density true))\n  (view hist)\n\n  # add a normal density line to the plot\n  (def x (range -4 4 0.01))\n  (add-lines hist x (pdf-normal x))\n\n  # plot some gamma data\n  (def gam-hist (histogram (sample-gamma 1000) :density true :nbins 30))\n  (view gam-hist)\n  (def x (range 0 8 0.01))\n  (add-lines gam-hist x (pdf-gamma x))\n\n  (use 'incanter.datasets)\n  (def iris (get-dataset :iris))\n  (view (histogram :Sepal.Width :data iris))\n\n  (with-data (get-dataset :iris)\n    (view (histogram :Petal.Length)))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/histogram"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "line-chart",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L1950",
   :line 1950,
   :var-type "macro",
   :arglists ([categories values & options]),
   :doc
   "\nReturns a JFreeChart object representing a line-chart of the given values and categories.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nArguments:\n  categories -- a sequence of categories\n  values -- a sequence of numeric values\n\nOptions:\n  :title (default '') main title\n  :x-label (default 'Categories')\n  :y-label (default 'Value')\n  :legend (default false) prints legend\n  :series-label\n  :group-by (default nil) -- a vector of values used to group the values into\n                             series within each category.\n  :gradient? (default false) -- use gradient on bars\n\n\nSee also:\n  view and save\n\nExamples:\n\n  (use '(incanter core stats charts datasets))\n\n  (def data (get-dataset :airline-passengers))\n  (def years (sel data :cols 0))\n  (def months (sel data :cols 2))\n  (def passengers (sel data :cols 1))\n  (view (line-chart years passengers :group-by months :legend true))\n  (view (line-chart months passengers :group-by years :legend true))\n\n\n  (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))\n  (def years (mapcat identity (repeat 4 [2007 2008 2009])))\n  (def x (sample-uniform 12 :integers true :max 100))\n  (view (line-chart years x :group-by seasons :legend true))\n\n  (view (line-chart [\"a\" \"b\" \"c\" \"d\" \"e\" \"f\"] [10 20 30 10 40 20]))\n\n  (view (line-chart (sample \"abcdefghij\" :size 10 :replacement true)\n                       (sample-uniform 10 :max 50) :legend true))\n\n  ;; add a series label\n  (def plot (line-chart [\"a\" \"b\" \"c\"] [10 20 30] :legend true :series-label \"s1\"))\n  (view plot)\n  (add-categories plot [\"a\" \"b\" \"c\"] [5 25 40] :series-label \"s2\")\n\n\n  (view (line-chart :year :passengers :group-by :month :legend true :data data))\n\n  (view (line-chart :month :passengers :group-by :year :legend true :data data))\n\n  (with-data data\n    (view (line-chart :month :passengers :group-by :year :legend true)))\n\n  (with-data (->> ($rollup :sum :passengers :year (get-dataset :airline-passengers))\n                  ($order :year :asc))\n    (view (line-chart :year :passengers)))\n\n  (with-data (->> ($rollup :sum :passengers :month (get-dataset :airline-passengers))\n                  ($order :passengers :asc))\n    (view (line-chart :month :passengers)))\n\n\n  (with-data ($rollup :sum :passengers :month (get-dataset :airline-passengers))\n    (view (line-chart :month :passengers)))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/line-chart"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "log-axis",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L873",
   :line 873,
   :var-type "function",
   :arglists ([& options]),
   :doc
   "\nCreate a logarithmic axis.\n\nNote: By default, values smaller than 0.5 are rounded to 0.5 to prevent strange behavior that\nhappens for values close to 0.\n\nOptions:\n  :base (default 10) base of the logarithm; typically 2 or 10\n  :label (default none) the label of the axis\n  :int-ticks? (default true) if true, use normal numbers instead of\n     <base>^<exponent>, i.e. 1 instead of f.ex. 10^0.0\n  :smallest-value (default: 0.5) Set the smallest value represented by the axis, set to 0.0 to 'reset'\n\nSee also:\n  set-axis\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/axis/LogAxis.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/log-axis"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "parametric-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L2853",
   :line 2853,
   :var-type "macro",
   :arglists ([function min-range max-range & options]),
   :doc
   "\nReturns a xy-plot object of the given parametric function over the range indicated\nby the min-range and max-range arguments. Use the 'view' function to\ndisplay the chart, or the 'save' function to write it to a file.\nFunction must take 1 argument - parameter t and return point [x y].\n\nOptions:\n  :title (default '') main title\n  :x-label (default 'min-x < x < max-x')\n  :y-label (default 'min-y < y < max-y')\n  :legend (default false) prints legend\n  :series-label (default function expression)\n  :step-size (default (/ (- max-range min-range) 500))\n\nSee also:\n  view, save, add-parametric, function-plot\n\n\nExamples:\n\n  (use '(incanter core charts))\n\n  (defn circle [t] [(cos t) (sin t)])\n  (view (parametric-plot circle (- Math/PI) Math/PI))\n\n  (defn spiral [t] [(* t (cos t)) (* t (sin t))])\n  (view (parametric-plot spiral 0 (* 6 Math/PI)))\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/parametric-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "pie-chart",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L2612",
   :line 2612,
   :var-type "macro",
   :arglists ([categories values & options]),
   :doc
   "\nReturns a JFreeChart object representing a pie-chart of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nArguments:\n  categories -- a sequence of categories\n  values -- a sequence of numeric values\n\nOptions:\n  :title (default '') main title\n  :legend (default false) prints legend\n\n\nSee also:\n  view and save\n\nExamples:\n\n\n  (use '(incanter core stats charts datasets))\n\n  (view (pie-chart [\"a\" \"b\" \"c\"] [10 20 30]))\n\n   (view (pie-chart (sample \"abcdefghij\" :size 10 :replacement true)\n                   (sample-uniform 10 :max 50) :legend true))\n\n\n   (with-data (->> (get-dataset :hair-eye-color)\n                   ($rollup :sum :count [:hair :eye]))\n     (view $data)\n     (view (pie-chart :hair :count :title \"Hair Color\"))\n     (view (pie-chart :eye :count :title \"Eye Color\")))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/pie-chart"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "qq-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L3313",
   :line 3313,
   :var-type "function",
   :arglists ([x & options]),
   :doc
   "\nReturns a QQ-Plot object. Use the 'view' function to display it.\n\nOptions:\n  :data (default nil) If the :data option is provided a dataset,\n                      a column name can be used instead of a sequence\n                      of data for argument x.\n\nReferences:\n  http://en.wikipedia.org/wiki/QQ_plot\n\nExamples:\n\n  (use '(incanter core stats charts datasets))\n  (view (qq-plot (sample-normal 100)))\n  (view (qq-plot (sample-exp 100)))\n  (view (qq-plot (sample-gamma 100)))\n\n  (with-data (get-dataset :iris)\n    (view (qq-plot :Sepal.Length)))\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/qq-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "remove-series",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L344",
   :line 344,
   :var-type "function",
   :arglists ([chart series-label]),
   :doc
   "Remove an existing series speicified by series-lab.\nIf the series does not exist it return nil",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/remove-series"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "scatter-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L1483",
   :line 1483,
   :var-type "macro",
   :arglists ([] [x y & options]),
   :doc
   "\nReturns a JFreeChart object representing a scatter-plot of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nOptions:\n  :title (default '') main title\n  :x-label (default x expression)\n  :y-label (default 'Frequency')\n  :legend (default false) prints legend\n  :series-label (default x expression)\n  :group-by (default nil) -- a vector of values used to group the x and y values into series.\n  :density? (default false) -- chart will represent density instead of frequency.\n  :nbins (default 10) -- number of bins (i.e. bars)\n  :gradient? (default false) -- use gradient on bars\n\nSee also:\n  view, save, add-points, add-lines\n\nExamples:\n\n  (use '(incanter core stats charts datasets))\n  ;; create some data\n  (def mvn-samp (sample-mvn 1000 :mean [7 5] :sigma (matrix [[2 1.5] [1.5 3]])))\n\n  ;; create scatter-plot of points\n  (def mvn-plot (scatter-plot (sel mvn-samp :cols 0) (sel mvn-samp :cols 1)))\n  (view mvn-plot)\n\n  ;; add regression line to scatter plot\n  (def x (sel mvn-samp :cols 0))\n  (def y (sel mvn-samp :cols 1))\n  (def lm (linear-model y x))\n  (add-lines mvn-plot x (:fitted lm))\n\n  ;; use :group-by option\n  (use '(incanter core stats datasets charts))\n  ;; load the :iris dataset\n  (def iris (get-dataset :iris))\n  ;; plot the first two columns grouped by the fifth column\n  (view (scatter-plot ($ :Sepal.Width iris) ($ :Sepal.Length iris) :group-by ($ :Species iris)))\n\n  (view (scatter-plot :Sepal.Length :Sepal.Width :data (get-dataset :iris)))\n\n  (view (scatter-plot :Sepal.Length :Sepal.Width :group-by :Species :data (get-dataset :iris)))\n\n  (with-data (get-dataset :iris)\n     (view (scatter-plot :Sepal.Length :Sepal.Width)))\n\n  (with-data (get-dataset :iris)\n     (view (scatter-plot :Sepal.Length :Sepal.Width :group-by :Species)))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/scatter-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "scatter-plot-matrix",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L1748",
   :line 1748,
   :var-type "function",
   :arglists ([& opts]),
   :doc
   "\nReturns a JFreeChart object displaying a scatter plot matrix for the given data.\nUse the 'view' function to display the chart or 'save' to write it to a file.\n\nUse:\n  (scatter-plot-matrix & options)\n  (scatter-plot-matrix data & options)\n\nOptions:\n  :data data (default $data) the data set for the plot.\n  :title s (default \"Scatter Plot Matrix\").\n  :nbins n (default 10) number of bins (ie. bars) in histogram.\n  :group-by grp (default nil) name of the column for grouping data.\n  :only-first n (default 6) show only the first n most correlating columns of the data set.\n  :only-triangle b (default false) shows only the upper triangle of the plot matrix.\n\nExamples:\n  (use '(incanter core stats charts datasets pdf))\n  (view (scatter-plot-matrix (get-dataset :iris) :nbins 20 :group-by :Species ))\n  (with-data (get-dataset :iris) (view (scatter-plot-matrix :nbins 20 :group-by :Species )))\n  (view (scatter-plot-matrix (get-dataset :chick-weight) :group-by :Diet :nbins 20))\n\n  ;;;Input examples for Iris\n  ;; Input dataset examples: Incanter data repo, local file, remote file (url)\n  (def iris (get-dataset :iris))\n  (def iris (read-dataset \"data/iris.dat\" :delim \\space :header true)) ; relative to project home\n  (def iris (read-dataset \"https://raw.github.com/liebke/incanter/master/data/iris.dat\" :delim \\space :header true))\n  ;; Filter dataset to specific columns only\n  (def iris ($ [:Sepal.Length :Sepal.Width :Petal.Length :Petal.Width :Species] (get-dataset :iris)))\n  (def iris (sel (get-dataset :iris) :cols [:Sepal.Length :Sepal.Width :Petal.Length :Petal.Width :Species]))\n\n  ;;; Scatter plot matrix examples\n  ;; Using default options\n  (def iris-spm (scatter-plot-matrix iris :group-by :Species))\n  ;; filter to metrics only, no categorical dimension for grouping\n  (def iris-spm (scatter-plot-matrix :data ($ [:Sepal.Length :Sepal.Width :Petal.Length :Petal.Width] iris)))\n\n  ;; Using more options\n  (def iris-spm (scatter-plot-matrix iris\n                                     :title \"Iris Scatter Plot Matrix\"\n                                     :bins 20 ; number of histogram bars\n                                     :group-by :Species\n                                     :only-first 4 ; most correlating columns\n                                     :only-triangle false))\n\n  ;;;Output examples\n  ;; View on Display\n  (view iris-spm :width 1280 :height 800)\n  ;; Save as PDF\n  (save-pdf  iris-spm \"out/iris-spm.pdf\" :width 2560 :height 1600)\n  ;; Save as PNG\n  (save iris-spm \"out/iris-spm.png\" :width 2560 :height 1600)\n\n  ;; Airline dataset\n  (def airline ($ [:year :passengers :month] (read-dataset \"https://raw.github.com/liebke/incanter/master/data/airline_passengers.csv\" :header true)))\n  (def airline-spm (scatter-plot-matrix airline  :group-by :month :bins 20 :title \"Airline Scatter Plot Matrix\"))\n  (view airline-spm)\n  ;; Chick-weight dataset\n  (view (scatter-plot-matrix (get-dataset :chick-weight) :group-by :Diet :bins 20 :title \"Chick-weight Scatter Plot Matrix\" ))\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/scatter-plot-matrix"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-alpha",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L976",
   :line 976,
   :var-type "function",
   :arglists ([chart alpha]),
   :doc
   "\nSets the alpha level (transparency) of the plot's foreground\nreturns the modified chart object.\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-alpha"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-axis",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L911",
   :line 911,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "\nSet the selected axis of the chart, returning the chart.\n(Beware: the axis' label will replace axis label set previously on the chart.)\n\nArguments:\n  chart - the JFreeChart object whose axis to change\n  dimension - depends on the plot type for plots with mutliple axes\n               f.ex. :x or :y for an XYPlot (x is the domain axis, y the range one)\n  axis - the axis to set, an instance of ValueAxis\n\nSee also:\n  log-axis\n\nNote:\n  Not applicable to DialPlot MeterPlot PiePlot MultiplePiePlot CompassPlot WaferMapPlot SpiderWebPlot\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/axis/ValueAxis.html\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/plot/XYPlot.html\n\nExamples:\n\n  (use '(incanter core charts))\n\n  (view\n    (doto (function-plot #(Math/pow 10 %) 0 5)\n          (set-axis :x (log-axis :base 10, :label \"log(x)\"))))\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-axis"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-background-alpha",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L990",
   :line 990,
   :var-type "function",
   :arglists ([chart alpha]),
   :doc
   "\nSets the alpha level (transparency) of the plot's background\nreturns the modified chart object.\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-background-alpha"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-background-default",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L77",
   :line 77,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "\nExamples:\n  (use '(incanter core stats charts datasets))\n\n  (doto (histogram (sample-normal 1000) :title (str :Test-Tittle))\n    set-theme-bw\n    view)\n\n\n  (doto (histogram (sample-normal 1000))\n    set-background-default\n    (add-histogram (sample-normal 1000 :mean 1))\n    view)\n\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    view)\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    (set-stroke :dash 5)\n    (add-points (plus ($ :speed (get-dataset :cars)) 5) (plus ($ :dist (get-dataset :cars)) 10))\n    view)\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-background-default\n    (set-stroke :dash 5)\n    (add-function sin 0 25)\n    view)\n\n\n  (doto (xy-plot :speed :dist :data (get-dataset :cars) :legend true)\n    set-background-default\n    view)\n\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-background-default\n    view)\n\n\n  (doto (box-plot (sample-gamma 1000 :shape 1 :scale 2)\n                  :legend true)\n    view set-background-default\n    (add-box-plot (sample-gamma 1000 :shape 2 :scale 2))\n    (add-box-plot (sample-gamma 1000 :shape 3 :scale 2)))\n\n\n  (doto (bar-chart [:a :b :c] [10 20 30] :legend true)\n    view\n    set-background-default\n    (add-categories [:a :b :c] [5 25 40]))\n\n\n  (doto (line-chart [:a :b :c] [10 20 30] :legend true)\n    view\n    set-background-default\n    (add-categories [:a :b :c] [5 25 40]))\n\n  ;; time-series-plot\n  (def epoch 0)\n  (defn num-years-to-milliseconds [x]\n    (* 365 24 60 60 1000 x))\n  (def dates (map num-years-to-milliseconds (range 100)))\n  (def chart1 (time-series-plot dates (range 100)))\n  (def cw1 (view chart1))\n  (add-lines chart1 dates (mult 1/2 (range 100)))\n\n  (def chart2 (time-series-plot (take 10 dates) (mult 1/2 (range 10))))\n  (def cw2 (view chart2))\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-background-default"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-point-size",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L3753",
   :line 3753,
   :var-type "function",
   :arglists
   ([chart
     point-size
     &
     {:keys [series dataset], :or {series :all, dataset 0}}]),
   :doc
   "Set the point size of a scatter plot. Use series option to apply\npoint-size to only one series.",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-point-size"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-stroke",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L3680",
   :line 3680,
   :var-type "function",
   :arglists ([chart & options]),
   :doc
   "\nExamples:\n  (use '(incanter core charts))\n\n  (doto (line-chart [:a :b :c :d] [10 20 5 35])\n    (set-stroke :width 4 :dash 5)\n    view)\n\n  (doto (line-chart [:a :b :c :d] [10 20 5 35])\n    (add-categories [:a :b :c :d] [20 5 30 15])\n    (set-stroke :width 4 :dash 5)\n    (set-stroke :series 1 :width 2 :dash 10)\n    view)\n\n\n  (doto (function-plot sin -10 10 :step-size 0.1)\n    (set-stroke :width 3 :dash 5)\n    view)\n\n  (doto (line-chart [:a :b :c :d] [10 20 5 35])\n    (add-categories [:a :b :c :d] [20 5 30 15])\n    (set-stroke :series 0 :width 4 :dash 5)\n    (set-stroke :series 1 :width 4 :dash 5 :cap java.awt.BasicStroke/CAP_SQUARE))\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-stroke"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-stroke-color",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L3725",
   :line 3725,
   :var-type "function",
   :arglists ([chart color & options]),
   :doc
   "\nExamples:\n  (use '(incanter core charts))\n\n  (doto (line-chart [:a :b :c :d] [10 20 5 35])\n    (set-stroke :width 4 :dash 5)\n    (set-stroke-color java.awt.Color/blue)\n    view)\n\n  (doto (xy-plot [1 2 3] [4 5 6])\n    (add-points [1 2 3] [4.1 5.1 6.1])\n    (set-stroke-color java.awt.Color/black :series 0)\n    (set-stroke-color java.awt.Color/red :series 1))\n\n  (doto (function-plot sin -10 10 :step-size 0.1)\n    (set-stroke :width 3 :dash 5)\n    (set-stroke-color java.awt.Color/gray)\n    view)\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-stroke-color"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-theme",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L225",
   :line 225,
   :var-type "function",
   :arglists ([chart theme]),
   :doc
   "\nChanges the chart theme.\n\nArguments:\n  chart -- an Incanter/JFreeChart object\n  theme -- either a keyword indicating one of the built-in themes, or a JFreeChart ChartTheme object.\n\nBuilt-in Themes:\n  :default\n  :dark\n\nExamples:\n\n  (use '(incanter core charts))\n  (def chart (function-plot sin -4 4))\n  (view chart)\n  ;; change the theme of chart to :dark\n  (set-theme chart :dark)\n  ;; change it back to the default\n  (set-theme chart :default)\n\n  ;; Example using JFreeTheme\n  (use '(incanter core stats charts datasets))\n\n  (import '(org.jfree.chart StandardChartTheme)\n          '(org.jfree.chart.plot DefaultDrawingSupplier)\n          '(java.awt Color))\n\n  (def all-red-theme\n    (doto\n      (StandardChartTheme/createJFreeTheme)\n      (.setDrawingSupplier\n      (proxy [DefaultDrawingSupplier] []\n        (getNextPaint [] Color/red)))))\n\n  (def data (get-dataset :airline-passengers))\n\n  (def chart (bar-chart :month :passengers :group-by :year :legend true :data data))\n\n  (doto chart\n    ;; has no effect\n    (set-theme all-red-theme)\n    view)\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/StandardChartTheme.html\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/ChartTheme.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-theme"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-theme-bw",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L157",
   :line 157,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "\n\nExamples:\n  (use '(incanter core stats charts datasets))\n\n  (doto (histogram (sample-normal 1000))\n    set-theme-bw\n    view)\n\n\n  (doto (histogram (sample-normal 1000))\n    set-theme-bw\n    (add-histogram (sample-normal 1000 :mean 1))\n    view)\n\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    view)\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    (set-stroke :dash 5)\n    (add-points (plus ($ :speed (get-dataset :cars)) 5) (plus ($ :dist (get-dataset :cars)) 10))\n    view)\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    (set-stroke :dash 5)\n    (add-function sin 0 25)\n    view)\n\n\n  (doto (xy-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    view)\n\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    (add-lines :speed :dist :data (get-dataset :cars))\n    view)\n\n\n  (doto (box-plot (sample-gamma 1000 :shape 1 :scale 2)\n                  :legend true)\n    view\n    (add-box-plot (sample-gamma 1000 :shape 2 :scale 2))\n    (add-box-plot (sample-gamma 1000 :shape 3 :scale 2))\n    set-theme-bw)\n\n\n  (doto (bar-chart [:a :b :c] [10 20 30] :legend true)\n    view\n    set-theme-bw\n    (add-categories [:a :b :c] [5 25 40]))\n\n\n  (doto (line-chart [:a :b :c] [10 20 30] :legend true)\n    view\n    set-theme-bw\n    (add-categories [:a :b :c] [5 25 40]))\n\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-theme-bw"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-title",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L1019",
   :line 1019,
   :var-type "function",
   :arglists ([chart title]),
   :doc
   "\nSets the main title of the plot, returns the modified chart object.\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-title"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-x-label",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L1033",
   :line 1033,
   :var-type "function",
   :arglists ([chart label]),
   :doc
   "\nSets the label of the x-axis, returns the modified chart object.\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-x-label"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-x-range",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L1061",
   :line 1061,
   :var-type "function",
   :arglists ([chart lower upper]),
   :doc
   "\nSets the range of the x-axis on the given chart.\n\nExamples:\n\n  (use '(incanter core charts datasets))\n\n  (def chart (xy-plot :speed :dist :data (get-dataset :cars)))\n  (view chart)\n  (set-x-range chart 10 20)\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-x-range"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-y-label",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L1047",
   :line 1047,
   :var-type "function",
   :arglists ([chart label]),
   :doc
   "\nSets the label of the y-axis, returns the modified chart object.\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-y-label"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-y-range",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L1082",
   :line 1082,
   :var-type "function",
   :arglists ([chart lower upper]),
   :doc
   "\nSets the range of the y-axis on the given chart.\n\nExamples:\n\n  (use '(incanter core charts datasets))\n\n  (def chart (xy-plot :speed :dist :data (get-dataset :cars)))\n  (view chart)\n  (set-y-range chart 10 60)\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-y-range"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "slider",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L3475",
   :line 3475,
   :var-type "function",
   :arglists
   ([updater-fn slider-values]
    [updater-fn slider-values slider-label]),
   :doc
   "\nExamples:\n  (use '(incanter core stats charts))\n\n  (def pdf-chart (function-plot pdf-normal -3 3))\n  (view pdf-chart)\n  (add-function pdf-chart pdf-normal -3 3)\n\n  (let [x (range -3 3 0.1)]\n    (slider #(set-data pdf-chart [x (pdf-normal x :sd %)]) (range 0.1 10 0.1)))\n\n  (let [x (range -3 3 0.1)]\n    (slider #(set-data pdf-chart [x (pdf-normal x :sd %)]) (range 0.1 10 0.1) \"sd\"))\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/slider"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "sliders",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L3550",
   :line 3550,
   :var-type "macro",
   :arglists ([[& slider-bindings] body]),
   :doc
   "\nCreates one slider control for each of the given sequence bindings.\nEach slider calls the given expression when manipulated.\n\n\nExamples:\n  (use '(incanter core stats charts))\n\n  ;; manipulate a normal pdf\n  (let [x (range -3 3 0.1)]\n    (def pdf-chart (xy-plot))\n    (view pdf-chart)\n    (sliders [mean (range -3 3 0.1)\n              stdev (range 0.1 10 0.1)]\n      (set-data pdf-chart [x (pdf-normal x :mean mean :sd stdev)])))\n\n\n  ;; manipulate a gamma pdf\n  (let [x (range 0 20 0.1)]\n    (def pdf-chart (xy-plot))\n    (view pdf-chart)\n    (sliders [scale (range 0.1 10 0.1)\n              shape (range 0.1 10 0.1)]\n             (set-data pdf-chart [x (pdf-gamma x :scale scale :shape shape)])))\n\n\n\n  ;; find the start values of a non-linear model function\n  (use '(incanter core charts datasets))\n  ;; create model function used in the following data-sorcery post:\n  ;; http://data-sorcery.org/2009/06/06/fitting-non-linear-models/\n\n  (defn f [theta x]\n    (let [[b1 b2 b3] theta]\n      (div (exp (mult (minus b1) x)) (plus b2 (mult b3 x)))))\n\n  (with-data (get-dataset :chwirut)\n    (view $data)\n    (def chart (scatter-plot ($ :x) ($ :y)))\n    (view chart)\n    (add-lines chart ($ :x) (f [0 0.01 0] ($ :x)))\n\n    ;; manipulate the model line to find some good start values.\n    ;; give the index of the line data (i.e. 1) to set-data.\n    (let [x ($ :x)]\n      (sliders [b1 (range 0 2 0.01)\n                b2 (range 0.01 2 0.01)\n                b3 (range 0 2 0.01)]\n        (set-data chart [x (f [b1 b2 b3] x)] 1))))\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/sliders"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "sliders*",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L3520",
   :line 3520,
   :var-type "function",
   :arglists
   ([f [& slider-values]] [f [& slider-values] [& slider-labels]]),
   :doc
   "sliders*\n\nExamples:\n  (use '(incanter core stats charts))\n\n  (let [x (range -3 3 0.1)]\n    (do\n      (def pdf-chart (xy-plot x (pdf-normal x :mean -3 :sd 0.1)))\n      (view pdf-chart)\n      (sliders* #(set-data pdf-chart [x (pdf-normal x :mean %1 :sd %2)])\n               [(range -3 3 0.1) (range 0.1 10 0.1)]\n               [\"mean\" \"sd\"])))\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/sliders*"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "stacked-area-chart",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L2367",
   :line 2367,
   :var-type "macro",
   :arglists ([categories values & options]),
   :doc
   "\nReturns a JFreeChart object representing an stacked-area-chart of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nArguments:\n  categories -- a sequence of categories\n  values -- a sequence of numeric values\n\nOptions:\n  :title (default '') main title\n  :x-label (default 'Categories')\n  :y-label (default 'Value')\n  :series-label\n  :legend (default false) prints legend\n  :vertical (default true) the orientation of the plot\n  :group-by (default nil) -- a vector of values used to group the values into\n                             series within each category.\n\n\nSee also:\n  view and save\n\nExamples:\n\n\n  (use '(incanter core stats charts datasets))\n\n  (with-data (get-dataset :co2)\n    (view (stacked-area-chart :Type :uptake\n                     :title \"CO2 Uptake\"\n                     :group-by :Treatment\n                     :x-label \"Grass Types\" :y-label \"Uptake\"\n                    :legend true)))\n\n\n  (def data (get-dataset :airline-passengers))\n  (view (stacked-area-chart :year :passengers :group-by :month :legend true :data data))\n\n  (with-data  (get-dataset :airline-passengers)\n    (view (stacked-area-chart :month :passengers :group-by :year :legend true)))\n\n\n  (def data (get-dataset :austres))\n  (view data)\n  (def plot (stacked-area-chart :year :population :group-by :quarter :legend true :data data))\n  (view plot)\n  (save plot \"/tmp/austres_plot.png\" :width 1000)\n  (view \"file:///tmp/austres_plot.png\")\n\n\n  (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))\n  (def years (mapcat identity (repeat 4 [2007 2008 2009])))\n  (def values (sample-uniform 12 :integers true :max 100))\n  (view (stacked-area-chart years values :group-by seasons :legend true))\n\n  (view (stacked-area-chart [\"a\" \"a\" \"b\" \"b\" \"c\" \"c\" ] [10 20 30 10 40 20]\n                   :legend true\n                   :group-by [\"I\" \"II\" \"I\" \"II\" \"I\" \"II\"]))\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/stacked-area-chart"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "stacked-bar-chart",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L2494",
   :line 2494,
   :var-type "macro",
   :arglists ([categories values & options]),
   :doc
   "\nReturns a JFreeChart object representing an stacked-bar-chart of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nArguments:\n  categories -- a sequence of categories\n  values -- a sequence of numeric values\n\nOptions:\n  :title (default '') main title\n  :x-label (default 'Categories')\n  :y-label (default 'Value')\n  :series-label\n  :legend (default false) prints legend\n  :vertical (default true) the orientation of the plot\n  :group-by (default nil) -- a vector of values used to group the values into\n                             series within each category.\n\n\nSee also:\n  view and save\n\nExamples:\n\n\n  (use '(incanter core stats charts datasets))\n\n  (with-data (get-dataset :co2)\n    (view (stacked-bar-chart :Type :uptake\n                     :title \"CO2 Uptake\"\n                     :group-by :Treatment\n                     :x-label \"Grass Types\" :y-label \"Uptake\"\n                    :legend true)))\n\n\n  (def data (get-dataset :airline-passengers))\n  (view (stacked-bar-chart :year :passengers :group-by :month :legend true :data data))\n\n  (with-data  (get-dataset :airline-passengers)\n    (view (stacked-bar-chart :month :passengers :group-by :year :legend true)))\n\n\n  (def data (get-dataset :austres))\n  (view data)\n  (def plot (stacked-bar-chart :year :population :group-by :quarter :legend true :data data))\n  (view plot)\n  (save plot \"/tmp/austres_plot.png\" :width 1000)\n  (view \"file:///tmp/austres_plot.png\")\n\n\n  (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))\n  (def years (mapcat identity (repeat 4 [2007 2008 2009])))\n  (def values (sample-uniform 12 :integers true :max 100))\n  (view (stacked-bar-chart years values :group-by seasons :legend true))\n\n  (view (stacked-bar-chart [\"a\" \"b\" \"c\"] [10 20 30]))\n  (view (stacked-bar-chart [\"a\" \"a\" \"b\" \"b\" \"c\" \"c\" ] [10 20 30 10 40 20]\n                   :legend true\n                   :group-by [\"I\" \"II\" \"I\" \"II\" \"I\" \"II\"]))\n\n  ;; add a series label\n  (def plot (stacked-bar-chart [\"a\" \"b\" \"c\"] [10 20 30] :legend true :series-label \"s1\"))\n  (view plot)\n  (add-categories plot [\"a\" \"b\" \"c\"] [5 25 40] :series-label \"s2\")\n\n  (view (stacked-bar-chart (sample \"abcdefghij\" :size 10 :replacement true)\n                   (sample-uniform 10 :max 50) :legend true))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/stacked-bar-chart"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "time-series-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L1369",
   :line 1369,
   :var-type "macro",
   :arglists ([x y & options]),
   :doc
   "\nReturns a JFreeChart object representing a time series plot of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file. Sequence passed in for the x axis should be\nnumber of milliseconds from the epoch (1 January 1970).\n\nOptions:\n  :data (default nil) If the :data option is provided a dataset,\n                      column names can be used instead of sequences\n                      of data as arguments to xy-plot.\n  :title (default '') main title\n  :x-label (default x expression)\n  :y-label (default y expression)\n  :legend (default false) prints legend\n  :series-label (default x expression)\n  :group-by (default nil) -- a vector of values used to group the x and y values into series.\n\nSee also:\n  view, save, add-points, add-lines\n\nExamples:\n\n  (use '(incanter core stats charts))\n  (require '[clj-time.core :refer [date-time]])\n\n  ;; plot numbers against years starting with 1900\n  (def dates (map #(-> (date-time (+ 1900 %))\n                       .getMillis)\n                  (range 100)))\n  (def y (range 100))\n  (view (time-series-plot dates y\n                          :x-label \"Year\"))\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/time-series-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "trace-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L3264",
   :line 3264,
   :var-type "function",
   :arglists ([x & options]),
   :doc
   "\nReturns a trace-plot object, use the 'view' function to display it.\n\nOptions:\n  :data (default nil) If the :data option is provided a dataset,\n                      a column name can be used instead of a sequence\n                      of data for argument x.\n  :title (default 'Trace Plot') main title\n  :x-label (default 'Iteration')\n  :y-label (default 'Value')\n  :series-label (default 'Value')\n\n  Examples:\n    (use '(incanter core datasets stats bayes charts))\n    (def ols-data (to-matrix (get-dataset :survey)))\n    (def x (sel ols-data (range 0 2313) (range 1 10)))\n    (def y (sel ols-data (range 0 2313) 10))\n    (def sample-params (sample-model-params 5000 (linear-model y x :intercept false)))\n    (view (trace-plot (:var sample-params)))\n\n    (view (trace-plot (sel (:coefs sample-params) :cols 0)))\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/trace-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj",
   :name "xy-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/606a3b32e7c44a32241f55ca189ed38037854c92/modules/incanter-charts/src/incanter/charts.clj#L1208",
   :line 1208,
   :var-type "macro",
   :arglists ([] [x y & options]),
   :doc
   "\nReturns a JFreeChart object representing a xy-plot of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nOptions:\n  :data (default nil) If the :data option is provided a dataset,\n                      column names can be used instead of sequences\n                      of data as arguments to xy-plot.\n  :title (default 'XY Plot') main title\n  :x-label (default x expression)\n  :y-label (default 'Frequency')\n  :legend (default false) prints legend\n  :series-label (default x expression)\n  :group-by (default nil) -- a vector of values used to group the x and y values into series.\n  :points (default false) includes point-markers\n  :auto-sort (default true) sort data by x\n\nSee also:\n  view, save, add-points, add-lines\n\nExamples:\n\n  (use '(incanter core stats charts))\n\n  ;; plot the cosine function\n  (def x (range -1 5 0.01))\n  (def y (cos (mult 2 Math/PI x)))\n  (view (xy-plot x y))\n\n  ;; plot gamma pdf with different parameters\n  (def x2 (range 0 20 0.1))\n  (def gamma-plot (xy-plot x2 (pdf-gamma x2 :shape 1 :scale 2)\n                             :legend true\n                             :title \"Gamma PDF\"\n                             :y-label \"Density\"))\n  (view gamma-plot)\n  (add-lines gamma-plot x2 (pdf-gamma x2 :shape 2 :scale 2))\n  (add-lines gamma-plot x2 (pdf-gamma x2 :shape 3 :scale 2))\n  (add-lines gamma-plot x2 (pdf-gamma x2 :shape 5 :scale 1))\n  (add-lines gamma-plot x2 (pdf-gamma x2 :shape 9 :scale 0.5))\n\n  ;; use :group-by option\n  (use '(incanter core charts datasets))\n\n  (with-data (get-dataset :chick-weight)\n    (view (xy-plot :Time :weight :group-by :Chick)))\n\n\n  ;; see INCANTER_HOME/examples/probability_plots.clj for more examples of plots\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/xy-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "$",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1563",
   :line 1563,
   :var-type "function",
   :arglists ([cols] [arg1 arg2] [rows cols data]),
   :doc
   "\nAn alias to (sel (second args) :cols (first args)). If given only a single argument,\nit will use the $data binding for the first argument, which is set with\nthe with-data macro.\n\nExamples:\n  (use '(incanter core stats charts datasets))\n\n  (def cars (get-dataset :cars))\n  ($ :speed cars)\n\n\n  (with-data cars\n    (def lm (linear-model ($ :dist) ($ :speed)))\n    (doto (scatter-plot ($ :speed) ($ :dist))\n      view\n      (add-lines ($ :speed) (:fitted lm))))\n\n  ;; standardize speed and dist and append the standardized variables to the original dataset\n  (with-data (get-dataset :cars)\n    (view (conj-cols $data\n                     (sweep (sweep ($ :speed)) :stat sd :fun div)\n                     (sweep (sweep ($ :dist)) :stat sd :fun div))))\n\n  (with-data (get-dataset :iris)\n    (view $data)\n    (view ($ [:Sepal.Length :Sepal.Width :Species]))\n    (view ($ [:not :Petal.Width :Petal.Length]))\n    (view ($ 0 [:not :Petal.Width :Petal.Length])))\n\n\n   (use 'incanter.core)\n   (def mat (matrix (range 9) 3))\n   (view mat)\n   ($ 2 2 mat)\n   ($ [0 2] 2 mat)\n   ($ :all 1 mat)\n   ($ 1 mat)\n   ($ [:not 1] mat)\n   ($ 0 :all mat)\n   ($ [0 2] [0 2] mat)\n   ($ [:not 1] [:not 1] mat)\n   ($ [:not 1] :all mat)\n   ($ [0 2] [:not 1] mat)\n   ($ [0 2] [:not 1 2] mat)\n   ($ [0 2] [:not (range 2)] mat)\n   ($ [:not (range 2)] [0 2] mat)\n\n   (with-data mat\n     ($ 0 0))\n   (with-data mat\n     ($ [0 2] 2 mat))\n   (with-data mat\n     ($ :all 1))\n   (with-data mat\n     ($ [0 2] [0 2]))\n   (with-data mat\n     ($ [:not 1] :all))\n   (with-data mat\n     ($ [0 2] [:not 1]))\n\n\n   (use 'incanter.datasets)\n   (view (get-dataset :cars))\n   ($ (range 5) 0 (get-dataset :cars))\n   ($ (range 5) :all (get-dataset :cars))\n   ($ :all (range 2) (get-dataset :cars))\n\n   ($ (range 5) :dist (get-dataset :cars))\n   ($ [:not (range 5)] 0 (get-dataset :cars))\n   ($ [:not 0 1 2 3 4] 0 (get-dataset :cars))\n   (with-data (get-dataset :cars)\n     ($ 0 :dist))\n\n   (with-data (get-dataset :hair-eye-color)\n     (view $data)\n     (view ($ [:not :gender])))\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/$"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "$=",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2850",
   :line 2850,
   :var-type "macro",
   :arglists ([& equation]),
   :doc
   "\nFormula macro translates from infix to prefix\n\n\nExamples:\n\n  (use 'incanter.core)\n  ($= 7 + 8)\n  ($= [1 2 3] + [4 5 6])\n  ($= [1 2 3] + (sin [4 5 6]))\n  ($= [1 2 3] <*> (trans [1 2 3]))\n  ($= [1 2 3] * [1 2 3])\n  ($= [1 2 3] <x> [1 2 3])\n  ($= 9 * 8 ** 3)\n  ($= (sin Math/PI) * 10)\n\n  ($= 10 + 20 * (4 - 5) / 6)\n\n  ($= 20 * (4 - 5) / 6)\n\n  (let [x 10\n        y -5]\n    ($= x + y / -10))\n\n  ($= 3 ** 3)\n\n  ($= [1 2 3] * [1 2 3])\n  ($= [1 2 3] / (sq [1 2 3]) + [5 6 7])\n\n  ($= (sqrt 5 * 5 + 3 * 3))\n  ($= (sq [1 2 3] + [1 2 3]))\n  ($= ((5 + 4) * 5))\n  ($= ((5 + 4 * (3 - 4)) / (5 + 8) * 6))\n  ($= [1 2 3] + 5)\n  ($= (matrix [[1 2] [4 5]]) + 6)\n  ($= (trans [[1 2] [4 5]]) + 6)\n\n  ($= (trans [[1 2] [4 5]]) <*> (matrix [[1 2] [4 5]]))\n\n\n  (use '(incanter core charts))\n  (defn f [x] ($= x ** 2 + 3 * x + 5))\n  (f 5)\n  (view (function-plot f -10 10))\n  (view (function-plot #($= % ** 2 + 3 * % + 5) -10 10))\n  (view (function-plot (fn [x] ($= x ** 2 + 3 * x + 5)) -10 10))\n  (let [x (range -10 10 0.1)]\n    (view (xy-plot x ($= x ** 3 - 5 * x ** 2 + 3 * x + 5))))\n\n  ($= (5 + 7))\n  ($= (trans [1 2 3 4]) <*> [1 2 3 4])\n  ($= [1 2 3 4] <*> (trans [1 2 3 4]))\n\n  ($= [1 2 3 4] <*> (trans [1 2 3 4]))\n  ($= [1 2 3 4] <x> (trans [1 2 3 4]))\n\n\n  ;; kronecker product example\n  ($= (matrix [[1 2] [3 4] [5 6]]) <x> 4)\n  ($= (matrix [[1 2] [3 4] [5 6]]) <x> (matrix [[1 2] [3 4]]))\n  ($= [1 2 3 4] <x> 4)\n\n  ($= 3 > (5 * 2/7))\n\n  (use '(incanter core datasets charts))\n  (with-data (get-dataset :cars)\n    (doto (scatter-plot :speed :dist :data ($where ($fn [speed dist] ($= dist / speed < 2))))\n      (add-points :speed :dist :data ($where ($fn [speed dist] ($= dist / speed >= 2))))\n      (add-lines ($ :speed) ($= 2 * ($ :speed)))\n      view))\n\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/$="}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "$data",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L48",
   :dynamic true,
   :line 48,
   :var-type "var",
   :arglists nil,
   :doc
   "This variable is bound to a dataset when the with-data macro is used.\nfunctions like $ and $where can use $data as a default argument.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/$data"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "$fn",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1839",
   :line 1839,
   :var-type "macro",
   :arglists ([col-bindings body]),
   :doc
   "\nA simple macro used as syntactic sugar for defining predicate functions to be used\nin the $where function. The supplied arguments should be column names of a dataset.\nThis macro performs map destructuring on the arguments.\n\nFor instance,\n($fn [speed] (< speed 10)) => (fn [{:keys [speed]}] (< speed 10))\n\nExamples:\n  (use '(incanter core datasets))\n  (view ($where ($fn [speed dist] (or (> speed 20) (< dist 10))) (get-dataset :cars)))\n\n  (view ($where ($fn [speed dist] (< (/ dist speed) 2)) (get-dataset :cars)))\n\n  (use '(incanter core datasets charts))\n  (with-data (get-dataset :cars)\n    (doto (scatter-plot :speed :dist :data ($where ($fn [speed dist] (< (/ dist speed) 2))))\n      (add-points :speed :dist :data ($where ($fn [speed dist] (>= (/ dist speed) 2))))\n      (add-lines ($ :speed) (mult 2 ($ :speed)))\n      view))\n\n\n  (let [passed? ($fn [speed dist] (< (/ dist speed) 2))\n        failed? (complement passed?)]\n    (with-data (get-dataset :cars)\n      (doto (scatter-plot :speed :dist :data ($where passed?))\n        (add-points :speed :dist :data ($where failed?))\n        (add-lines ($ :speed) (mult 2 ($ :speed)))\n        view)))\n\n\n  (use '(incanter core stats charts))\n  (let [above-sine? ($fn [col-0 col-1] (> col-1 (sin col-0)))\n        below-sine? (complement above-sine?)]\n    (with-data (conj-cols (sample-uniform 1000 :min -5 :max 5)\n                          (sample-uniform 1000 :min -1 :max 1))\n      (doto (function-plot sin -5 5)\n        (add-points :col-0 :col-1 :data ($where above-sine?))\n        (add-points :col-0 :col-1 :data ($where below-sine?))\n        view)))\n\n\n  (view ($where ($fn [] (> (rand) 0.9)) (get-dataset :cars)))\n\n  (view ($where ($fn [Species] ($in Species #{\"virginica\" \"setosa\"})) (get-dataset :iris)))\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/$fn"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "$group-by",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1891",
   :line 1891,
   :var-type "function",
   :arglists ([cols] [cols data]),
   :doc
   "\nReturns a map of datasets keyed by a query-map corresponding the group.\n\nExamples:\n\n  (use '(incanter core datasets))\n  ($group-by :Species (get-dataset :iris))\n\n  ($group-by [:hair :eye] (get-dataset :hair-eye-color))\n\n  (with-data (get-dataset :hair-eye-color)\n    ($group-by [:hair :eye]))\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/$group-by"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "$join",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1979",
   :line 1979,
   :var-type "function",
   :arglists
   ([[left-keys right-keys] left-data]
    [[left-keys right-keys] left-data right-data]),
   :doc
   "\nReturns a dataset created by right-joining two datasets.\nThe join is based on one or more columns in the datasets.\nIf used within the body of the with-data macro, the second\ndataset is optional, defaulting the the dataset bound to $data.\n\n\nExamples:\n  (use '(incanter core stats datasets charts))\n  (def iris (get-dataset :iris))\n\n\n\n  (def lookup (dataset [:species :species-key] [[\"setosa\" :setosa]\n                                                [\"versicolor\" :versicolor]\n                                                [\"virginica\" :virginica]]))\n  (view ($join [:species :Species] lookup iris))\n\n  (def hair-eye-color (get-dataset :hair-eye-color))\n  (def lookup2 (conj-cols ($ [:hair :eye :gender] hair-eye-color) (range (nrow hair-eye-color))))\n  (view ($join [[:col-0 :col-1 :col-2] [:hair :eye :gender]] lookup2 hair-eye-color))\n\n  (with-data hair-eye-color\n    (view ($join [[:col-0 :col-1 :col-2] [:hair :eye :gender]] lookup2)))\n\n\n  (def lookup3 (dataset [:gender :hair :hair-gender] [[\"male\" \"black\" :male-black]\n                                                      [\"male\" \"brown\" :male-brown]\n                                                      [\"male\" \"red\" :male-red]\n                                                      [\"male\" \"blond\" :male-blond]\n                                                      [\"female\" \"black\" :female-black]\n                                                      [\"female\" \"brown\" :female-brown]\n                                                      [\"female\" \"red\" :female-red]\n                                                      [\"female\" \"blond\" :female-blond]]))\n\n  (view ($join [[:gender :hair] [:gender :hair]] lookup3 hair-eye-color))\n\n  (use 'incanter.charts)\n  (with-data (->>  (get-dataset :hair-eye-color)\n                   ($where {:hair {:in #{\"brown\" \"blond\"}}})\n                   ($rollup :sum :count [:hair :gender])\n                   ($join [[:gender :hair] [:gender :hair]] lookup3)\n                   ($order :count :desc))\n      (view $data)\n      (view (bar-chart :hair :count :group-by :gender :legend true)))\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/$join"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "$map",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1944",
   :line 1944,
   :var-type "function",
   :arglists ([fun col-keys data] [fun col-keys]),
   :doc
   "\nThis function returns a sequence resulting from mapping the given function over\nthe value(s) for the given column key(s) of the given dataset.\nLike other '$*' functions, it will use $data as the default dataset\nif none is provided, where $data is set using the with-data macro.\n\nExamples:\n\n  (use '(incanter core datasets))\n  (def cars (get-dataset :cars))\n\n  ($map (fn [s] (/ s)) :speed cars)\n  ($map (fn [s d] (/ s d)) [:speed :dist] cars)\n\n  (map (fn [s d] (/ s d)) ($ :speed cars) ($ :speed cars))\n\n  (with-data (get-dataset :cars)\n    (view ($map (fn [s] (/ s)) :speed))\n    (view ($map (fn [s d] (/ s d)) [:speed :dist])))\n\n  ;; calculate the speed to dist ratio and append as new column to dataset\n  (with-data (get-dataset :cars)\n    (conj-cols $data ($map (fn [s d] (/ s d)) [:speed :dist])))\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/$map"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "$order",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1809",
   :line 1809,
   :var-type "function",
   :arglists ([cols order] [cols order data]),
   :doc
   "\nSorts a dataset by the given columns in either ascending (:asc)\nor descending (:desc) order. If used within a the body of\nthe with-data macro, the data argument is optional, defaulting\nto the dataset bound to the variable $data.\n\nExamples:\n\n  (use '(incanter core charts datasets))\n  (def iris (get-datset :iris))\n  (view ($order :Sepal.Length :asc iris))\n  (view ($order [:Sepal.Width :Sepal.Length] :desc iris))\n\n  (with-data (get-dataset :iris)\n    (view ($order [:Petal.Length :Sepal.Length] :desc)))\n\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/$order"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "$rollup",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1721",
   :line 1721,
   :var-type "function",
   :arglists
   ([summary-fun col-name group-by]
    [summary-fun col-name group-by data]),
   :doc
   "\nReturns a dataset that uses the given summary function (or function identifier keyword)\nto rollup the given column based on a set of group-by columns. The summary function\nshould accept a single sequence of values and return a single summary value. Alternatively,\nyou can provide a keyword identifier of a set of built-in functions including:\n\n:max -- the maximum value of the data in each group\n:min -- the minimum value of the data in each group\n:sum -- the sum of the data in each group\n:count -- the number of elements in each group\n:mean -- the mean of the data in each group\n\n\nLike the other '$' dataset functions, $rollup will use the dataset bound to $data\n(see the with-data macro) if a dataset is not provided as an argument.\n\nExamples:\n\n  (use '(incanter core datasets))\n\n  (def iris (get-dataset :iris))\n  ($rollup :mean :Sepal.Length :Species iris)\n  ($rollup :count :Sepal.Length :Species iris)\n  ($rollup :max :Sepal.Length :Species iris)\n  ($rollup :min :Sepal.Length :Species iris)\n\n  ;; The following is an example using a custom function, but since all the\n  ;; iris measurements are positive, the built-in mean function could have\n  ;; been used instead.\n\n  (use 'incanter.stats)\n  ($rollup #(mean (abs %)) :Sepal.Width :Species iris)\n\n  ($rollup sd :Sepal.Length :Species iris)\n  ($rollup variance :Sepal.Length :Species iris)\n  ($rollup median :Sepal.Length :Species iris)\n\n  (def hair-eye-color (get-dataset :hair-eye-color))\n  ($rollup :mean :count [:hair :eye] hair-eye-color)\n\n  (use 'incanter.charts)\n  (with-data ($rollup :mean :Sepal.Length :Species iris)\n    (view (bar-chart :Species :Sepal.Length)))\n\n   ;; the following examples use the built-in data set called hair-eye-color.\n\n   (with-data ($rollup :mean :count [:hair :eye] hair-eye-color)\n     (view (bar-chart :hair :count :group-by :eye :legend true)))\n\n   (with-data (->>  (get-dataset :hair-eye-color)\n                    ($where {:hair {:in #{\"brown\" \"blond\"}}})\n                    ($rollup :sum :count [:hair :eye])\n                    ($order :count :desc))\n     (view $data)\n     (view (bar-chart :hair :count :group-by :eye :legend true)))\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/$rollup"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "$where",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1690",
   :line 1690,
   :var-type "function",
   :arglists ([query-map] [query-map data]),
   :doc
   "\nAn alias to (query-dataset (second args) (first args)). If given only a single argument,\nit will use the $data binding for the first argument, which is set with\nthe with-data macro.\n\nExamples:\n\n  (use '(incanter core datasets))\n\n  (def cars (get-dataset :cars))\n  ($where {:speed 10} cars)\n\n  ;; use the with-data macro and the one arg version of $where\n  (with-data cars\n    (view ($where {:speed {:$gt -10 :$lt 10}}))\n    (view ($where {:dist {:$in #{10 12 16}}}))\n    (view ($where {:dist {:$nin #{10 12 16}}})))\n\n  ;; create a dataset where :speed greater than 10 or less than -10\n  (with-data (get-dataset :cars)\n    (view (-> ($where {:speed {:$gt 20}})\n                    (conj-rows ($where {:speed {:$lt 10}})))))\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/$where"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "->Dataset",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L53",
   :line 53,
   :var-type "function",
   :arglists ([column-names rows]),
   :doc "Positional factory function for class incanter.core.Dataset.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/->Dataset"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "abs",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L577",
   :line 577,
   :var-type "function",
   :arglists ([A]),
   :doc
   "\nReturns the absolute value of the elements in the given matrix, sequence or number.\nEquivalent to R's abs function.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/abs"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "acos",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L609",
   :line 609,
   :var-type "function",
   :arglists ([A]),
   :doc
   "\nReturns the arc cosine of the elements in the given matrix, sequence or number.\nEquivalent to R's acos function.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/acos"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "add-column",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2100",
   :line 2100,
   :var-type "function",
   :arglists ([column-name values] [column-name values data]),
   :doc "Adds a column, with given values, to a dataset.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/add-column"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "add-derived-column",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2112",
   :line 2112,
   :var-type "function",
   :arglists
   ([column-name from-columns f] [column-name from-columns f data]),
   :doc
   "\nThis function adds a column to a dataset that is a function of\nexisting columns. If no dataset is provided, $data (bound by the\nwith-data macro) will be used. f should be a function of the\nfrom-columns, with arguments in that order.\n\nExamples:\n  (use '(incanter core datasets))\n  (def cars (get-dataset :cars))\n\n  (add-derived-column :dist-over-speed [:dist :speed] (fn [d s] (/ d s)) cars)\n\n  (with-data (get-dataset :cars)\n    (view (add-derived-column :speed**-1 [:speed] #(/ 1.0 %))))",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/add-derived-column"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "aggregate",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2043",
   :line 2043,
   :var-type "function",
   :arglists
   ([fields
     group-by
     &
     {:keys [dataset rollup-fun], :or {rollup-fun :sum}}]),
   :doc
   "\nPerforms the aggregation of the data in given dataset using the specified rollup function.\nThe fields parameter defines column(s) on which the rollup will happen, and group-by\nspecifies the column(s) for joining the results.  The fields & group-by parameters could be\nsingle values or collections.  The dataset is provided by the :dataset parameter, if it's not\nprovided, then the $data is used.  The rollup function is provided by :rollup-fun parameter,\nif it's not provided, then the :sum is used.\n\n  (aggregate [:uptake :conc] :Type :dataset (get-dataset :co2))\n  (aggregate [:uptake :conc] [:Type] :dataset (get-dataset :co2) :rollup-fun :min)",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/aggregate"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "asin",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L593",
   :line 593,
   :var-type "function",
   :arglists ([A]),
   :doc
   "\nReturns the arc sine of the elements in the given matrix, sequence or number.\nEquivalent to R's asin function.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/asin"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "atan",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L624",
   :line 624,
   :var-type "function",
   :arglists ([A]),
   :doc
   "\nReturns the arc tangent of the elements in the given matrix, sequence or number.\nEquivalent to R's atan function.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/atan"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "atan2",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L520",
   :line 520,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "\nReturns the atan2 of the elements in the given matrices, sequences or numbers.\nEquivalent to R's atan2 function.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/atan2"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "beta",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2419",
   :line 2419,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nEquivalent to R's beta function.\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Gamma.html\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/beta"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "bind-columns",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L370",
   :line 370,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "\nReturns the matrix resulting from concatenating the given matrices\nand/or sequences by their columns. Equivalent to R's cbind.\n\nExamples:\n(def A (matrix [[1 2 3]\n                [4 5 6]\n                [7 8 9]]))\n\n(def B (matrix [10 11 12]))\n\n(bind-columns A B)\n\n(bind-columns [1 2 3 4] [5 6 7 8])\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/bind-columns"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "bind-rows",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L331",
   :line 331,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "\nReturns the matrix resulting from concatenating the given matrices\nand/or sequences by their rows. Equivalent to R's rbind.\n\nExamples:\n(def A (matrix [[1 2 3]\n                [4 5 6]\n                [7 8 9]]))\n\n(def B (matrix [[10 11 12]\n                [13 14 15]]))\n\n(bind-rows A B)\n\n(bind-rows [1 2 3 4] [5 6 7 8])\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/bind-rows"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "categorical-var",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2307",
   :line 2307,
   :var-type "function",
   :arglists
   ([& {:keys [data ordered? labels levels], :or {ordered? false}}]),
   :doc
   "\nReturns a categorical variable based on the values in the given collection.\nEquivalent to R's factor function.\n\nOptions:\n  :data (default nil) factors will be extracted from the given data.\n  :ordered? (default false) indicates that the variable is ordinal.\n  :labels (default (sort (into #{} data)))\n  :levels (range (count labels))\n\nExamples:\n  (categorical-var :data [:a :a :c :b :a :c :c])\n  (categorical-var :labels [:a :b :c])\n  (categorical-var :labels [:a :b :c] :levels [10 20 30])\n  (categorical-var :levels [1 2 3])\n\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/categorical-var"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "choose",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L649",
   :line 649,
   :var-type "function",
   :arglists ([n k]),
   :doc
   "\nReturns number of k-combinations (each of size k) from a set S with\nn elements (size n), which is the binomial coefficient (also known\nas the 'choose function') [wikipedia]\n      choose = n!/(k!(n - k)!)\n\nEquivalent to R's choose function.\n\nExamples:\n  (choose 25 6) ; => 177,100\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/math/tdouble/DoubleArithmetic.html\n  http://en.wikipedia.org/wiki/Combination\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/choose"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "col-names",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1489",
   :line 1489,
   :var-type "function",
   :arglists ([data] [data colnames]),
   :doc
   "\nIf given a dataset, it returns its column names. If given a dataset and a sequence\nof column names, it returns a dataset with the given column names.\n\nExamples:\n  (use '(incanter core datasets))\n  (def data (get-dataset :cars))\n  (col-names data)\n\n  (def renamed-data (col-names data [:x1 :x2]))\n  (col-names renamed-data)\n\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/col-names"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "condition",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1067",
   :line 1067,
   :var-type "function",
   :arglists ([mat]),
   :doc
   "\nReturns the two norm condition number, which is max(S) / min(S), where S is the diagonal matrix of singular values from an SVD decomposition.\n\n\nExamples:\n  (use 'incanter.core)\n  (def foo (matrix (range 9) 3))\n  (condition foo)\n\nReferences:\n  http://en.wikipedia.org/wiki/Condition_number\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/condition"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "conj-cols",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1509",
   :line 1509,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "\nReturns a dataset created by merging the given datasets and/or collections.\nThere must be the same number of rows in each dataset and/or\ncollections.  Column names may be changed in order to prevent\nnaming conflicts in the conjed dataset.\n\nExamples:\n  (use '(incanter core datasets))\n  (def cars (get-dataset :cars))\n  (def x (sel cars :cols 0))\n  (view (conj-cols cars cars))\n  (view (conj-cols cars x))\n  (view (conj-cols (range (nrow cars)) cars))\n  (view (conj-cols (range 10) (range 10)))\n  (view (conj-cols {:a 1 :b 2} {:c 1 :d 2}))\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/conj-cols"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "conj-rows",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1538",
   :line 1538,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "\nReturns a dataset created by combining the rows of the given datasets and/or collections.\n\nExamples:\n\n  (use '(incanter core datasets))\n  (def cars (get-dataset :cars))\n  (view (conj-rows (to-dataset (range 5)) (to-dataset (range 5 10))))\n  (view (conj-rows cars cars))\n  (view (conj-rows [[1 2] [3 4]] [[5 6] [7 8]]))\n  (view (conj-rows [{:a 1 :b 2} {:a 3 :b 4}] [[5 6] [7 8]]))\n  (view (conj-rows (to-dataset [{:a 1 :b 2} {:a 3 :b 4}]) [[5 6] [7 8]]))\n  (conj-rows (range 5) (range 5 10))\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/conj-rows"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "copy",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L719",
   :line 719,
   :var-type "function",
   :arglists ([mat]),
   :doc "Returns a copy of the given matrix.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/copy"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "cos",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L601",
   :line 601,
   :var-type "function",
   :arglists ([A]),
   :doc
   "\nReturns the cosine of the elements in the given matrix, sequence or number.\nEquivalent to R's cos function.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/cos"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "cumulative-sum",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L878",
   :line 878,
   :var-type "function",
   :arglists ([coll]),
   :doc
   "\nReturns a sequence of cumulative sum for the given collection. For instance\nThe first value equals the first value of the argument, the second value is\nthe sum of the first two arguments, the third is the sum of the first three\narguments, etc.\n\nExamples:\n  (use 'incanter.core)\n  (cumulative-sum (range 100))\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/cumulative-sum"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "data-table",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2680",
   :line 2680,
   :var-type "function",
   :arglists ([data]),
   :doc "Creates a javax.swing.JTable given an Incanter dataset.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/data-table"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "dataset",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1191",
   :line 1191,
   :var-type "function",
   :arglists ([column-names & data]),
   :doc
   "\nReturns a map of type incanter.core.dataset constructed from the given column-names and\ndata. The data is either a sequence of sequences or a sequence of hash-maps.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/dataset"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "dataset?",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L99",
   :line 99,
   :var-type "function",
   :arglists ([obj]),
   :doc "Determines if obj is of type incanter.core.Dataset.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/dataset?"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "decomp-cholesky",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L907",
   :line 907,
   :var-type "function",
   :arglists ([mat]),
   :doc
   "\nReturns the Cholesky decomposition of the given matrix. Equivalent to R's\nchol function.\n\nReturns:\na matrix of the triangular factor (note: the result from\ncern.colt.matrix.linalg.DenseDoubleCholeskyDecomposition is transposed so\nthat it matches the result return from R's chol function.\n\n\n\nExamples:\n\n(use '(incanter core stats charts datasets))\n;; load the iris dataset\n(def iris (to-matrix (get-dataset :iris)))\n;; take the Cholesky decomposition of the correlation matrix of the iris data.\n(decomp-cholesky (correlation iris))\n\nReferences:\n  http://en.wikipedia.org/wiki/Cholesky_decomposition\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/decomp-cholesky"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "decomp-eigenvalue",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L976",
   :line 976,
   :var-type "function",
   :arglists ([mat]),
   :doc
   "\nReturns the Eigenvalue Decomposition of the given matrix. Equivalent to R's eig function.\n\nReturns:\na map containing:\n:values -- vector of eigenvalues\n:vectors -- the matrix of eigenvectors\n\nExamples:\n\n(use 'incanter.core)\n(def foo (matrix (range 9) 3))\n(decomp-eigenvalue foo)\n\nReferences:\nhttp://en.wikipedia.org/wiki/Eigenvalue_decomposition\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/decomp-eigenvalue"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "decomp-lu",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1000",
   :line 1000,
   :var-type "function",
   :arglists ([mat]),
   :doc
   "\nReturns the LU decomposition of the given matrix.\n\nExamples:\n\n(use 'incanter.core)\n(def foo (matrix (range 9) 3))\n(decomp-lu foo)\n\n\nReturns:\n  a map containing:\n    :L -- the lower triangular factor\n    :U -- the upper triangular factor\n    :P -- the permutation matrix\n\nReferences:\n  http://en.wikipedia.org/wiki/LU_decomposition\n  http://mikiobraun.github.io/jblas/javadoc/org/jblas/Decompose.LUDecomposition.html\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/decomp-lu"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "decomp-qr",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1036",
   :line 1036,
   :var-type "function",
   :arglists ([m & {:keys [type]}]),
   :doc
   "\nReturns the QR decomposition of the given matrix. Equivalent to R's qr function.\n\nOptional parameters:\n:type -- possible values: :full.  default is :full\nif :full, returns the full QR decomposition\n\nReturns:\na map containing:\n:Q -- orthogonal factors\n:R -- the upper triangular factors\n\nExamples:\n\n(use 'incanter.core)\n(def foo (matrix (range 9) 3))\n(decomp-qr foo)\n(decomp-qr foo :type :full)\n\nReferences:\nhttp://en.wikipedia.org/wiki/QR_decomposition\n\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/decomp-qr"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "decomp-svd",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L935",
   :line 935,
   :var-type "function",
   :arglists ([mat & {:keys [type], :or {type :full}}]),
   :doc
   "\nReturns the Singular Value Decomposition (SVD) of the given matrix. Equivalent to\nR's svd function.\n\nOptional parameters:\n:type -- one of :full, :compact, or :values.  default is :full\nif :full, returns the full SVD\nif :compact, returns the compact SVD\nif :values, only the singular values are calculated\n\nReturns:\na map containing:\n:S -- the diagonal matrix of singular values S (the diagonal in vector form)\n:U -- the left singular vectors U\n:V -- the right singular vectors V\n\nExamples:\n\n(use 'incanter.core)\n(def foo (matrix (range 9) 3))\n(decomp-svd foo)\n(decomp-svd foo :type :full)\n(decomp-svd foo :type :compact)\n(decomp-svd foo :type :values)\n\n\nReferences:\nhttp://en.wikipedia.org/wiki/Singular_value_decomposition\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/decomp-svd"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "deshape",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2151",
   :line 2151,
   :var-type "function",
   :arglists
   ([& {:keys [data remove-na group-by merge], :or {remove-na true}}]),
   :doc
   "\nReturns a dataset where the columns identified by :merge are collapsed into\ntwo columns called :variable and :value. The values in these columns are grouped\nby the columns identified by :group-by.\n\nExamples:\n\n  (use '(incanter core charts datasets))\n  (with-data (->> (deshape :merge [:Ahmadinejad :Rezai :Karrubi :Mousavi]\n                            :group-by :Region\n                            :data (get-dataset :iran-election))\n                  ($order :value :desc))\n    (view $data)\n    (view (bar-chart :variable :value :group-by :Region :legend true))\n\n    (view (bar-chart :Region :value :group-by :variable\n                     :legend true :vertical false))\n\n    (view (bar-chart :Region :value :legend true :vertical false\n                     :data ($order :value :desc ($rollup :sum :value :Region)))))\n\n\n\n    (def data (to-dataset [{:subject \"John Smith\" :time 1 :age 33 :weight 90 :height 1.87}\n                           {:subject \"Mary Smith\" :time 1 :height 1.54}]))\n    (view data)\n    (view (deshape :group-by [:subject :time] :merge [:age :weight :height] :data data))\n    (view (deshape :merge [:age :weight :height] :data data))\n    (view (deshape :group-by [:subject :time] :data data))\n\n    (view (deshape :merge [:age :weight :height] :remove-na false :data data))\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/deshape"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "det",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L790",
   :line 790,
   :var-type "function",
   :arglists ([mat]),
   :doc
   "\nReturns the determinant of the given matrix. Equivalent\nto R's det function.\n\nReferences:\n  http://en.wikipedia.org/wiki/LU_decomposition\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/det"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "diag",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L138",
   :line 138,
   :var-type "function",
   :arglists ([m]),
   :doc
   "If given a matrix, diag returns a sequence of its diagonal elements.\nIf given a sequence, it returns a matrix with the sequence's elements\non its diagonal. Equivalent to R's diag function.\n\nExamples:\n(diag [1 2 3 4])\n\n(def A (matrix [[1 2 3]\n[4 5 6]\n[7 8 9]]))\n(diag A)",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/diag"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "dim",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L120",
   :line 120,
   :var-type "function",
   :arglists ([mat]),
   :doc
   "Returns a vector with the number of rows and columns of the given matrix.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/dim"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "div",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L455",
   :line 455,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "\nPerforms element-by-element division on multiple matrices, sequences\nand/or numbers. Equivalent to R's / operator.\n\nExamples:\n\n(def A (matrix [[1 2 3]\n                [4 5 6]\n                [7 8 9]]))\n(div A A A)\n(div A 2)\n(div 2 A)\n(div [1 2 3] [1 2 3])\n(div [1 2 3] 2)\n(div 2 [1 2 3])\n\n(div [1 2 3]) ; returns [1 1/2 13]\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/div"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "exp",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L570",
   :line 570,
   :var-type "function",
   :arglists ([A]),
   :doc
   "\nReturns the exponential of the elements in the given matrix, sequence or number.\nEquivalent to R's exp function.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/exp"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "factorial",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L632",
   :line 632,
   :var-type "function",
   :arglists ([k]),
   :doc
   "\nReturns the factorial of k (k must be a positive integer). Equivalent to R's\nfactorial function.\n\nExamples:\n  (factorial 6)\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/math/tdouble/DoubleArithmetic.html\n  http://en.wikipedia.org/wiki/Factorial\n\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/factorial"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "gamma",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2409",
   :line 2409,
   :var-type "function",
   :arglists ([x]),
   :doc
   "\nEquivalent to R's gamma function.\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Gamma.html\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/gamma"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "get-categories",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2214",
   :line 2214,
   :var-type "function",
   :arglists ([cols data]),
   :doc
   "\nGiven a dataset and one or more column keys, returns the set of categories for them.\n\nExamples:\n\n  (use '(incanter core datasets))\n  (get-categories :eye (get-dataset :hair-eye-color))\n  (get-categories [:eye :hair] (get-dataset :hair-eye-color))\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/get-categories"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "grid-apply",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2823",
   :line 2823,
   :var-type "function",
   :arglists ([f x-min x-max y-min y-max]),
   :doc
   "\nApplies the given function f, that accepts two arguments, to a grid\ndefined by rectangle bounded x-min, y-min, x-max, y-max and returns a\nsequence of three sequences representing the cartesian product of x and y\nand z calculated by applying f to the combinations of x and y.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/grid-apply"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "group-on",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1126",
   :line 1126,
   :var-type "function",
   :arglists ([mat on-cols & {:keys [cols except-cols]}]),
   :doc
   "\nGroups the given matrix by the values in the columns indicated by the\n'on-cols' argument, returning a sequence of matrices. The returned\nmatrices are sorted by the value of the group column ONLY when there\nis only a single (non-vector) on-col argument.\n\nExamples:\n\n  (use '(incanter core datasets))\n  (def plant-growth (to-matrix (get-dataset :plant-growth)))\n  (group-on plant-growth 1)\n  ;; only return the first column\n  (group-on plant-growth 1 :cols 0)\n  ;; don't return the second column\n  (group-on plant-growth 1 :except-cols 1)\n\n  (def plant-growth-dummies (to-matrix (get-dataset :plant-growth) :dummies true))\n  (group-on plant-growth-dummies [1 2])\n  ;; return only the first column\n  (group-on plant-growth-dummies [1 2] :cols 0)\n  ;; don't return the last two columns\n  (group-on plant-growth-dummies [1 2] :except-cols [1 2])\n\n  ;; plot the plant groups\n  (use 'incanter.charts)\n  ;; can use destructuring if you know the number of groups\n  ;; groups are sorted only if the group is based on a single column value\n  (let [[ctrl trt1 trt2] (group-on plant-growth 1 :cols 0)]\n    (doto (box-plot ctrl)\n          (add-box-plot trt1)\n          (add-box-plot trt2)\n          view))\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/group-on"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "half-vectorize",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L832",
   :line 832,
   :var-type "function",
   :arglists ([mat]),
   :doc
   "\nReturns the half-vectorization (i.e. vech) of the given matrix.\nThe half-vectorization, vech(A), of a symmetric nxn matrix A\nis the n(n+1)/2 x 1 column vector obtained by vectorizing only\nthe upper triangular part of A.\n\nFor instance:\n  (= (half-vectorize (matrix [[a b] [b d]])) (matrix [a b d]))\n\nExamples:\n  (def A (matrix [[1 2] [2 4]]))\n  (half-vectorize A)\n\nReferences:\n  http://en.wikipedia.org/wiki/Vectorization_(mathematics)\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/half-vectorize"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "head",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1668",
   :line 1668,
   :var-type "function",
   :arglists ([len mat] [mat]),
   :doc
   "Returns the head of the dataset. 10 or full dataset by default.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/head"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "identity-matrix",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L127",
   :line 127,
   :var-type "function",
   :arglists ([n]),
   :doc
   "\nReturns an n-by-n identity matrix.\n\nExamples:\n(identity-matrix 4)\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/identity-matrix"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "incomplete-beta",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2429",
   :line 2429,
   :var-type "function",
   :arglists ([x a b]),
   :doc
   "\nReturns the non-regularized incomplete beta value.\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Gamma.html\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/incomplete-beta"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "kronecker",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L743",
   :line 743,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "\nReturns the Kronecker product of the given arguments.\n\nExamples:\n\n  (def x (matrix (range 6) 2))\n  (def y (matrix (range 4) 2))\n  (kronecker 4 x)\n  (kronecker x 4)\n  (kronecker x y)\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/kronecker"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "length",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1108",
   :line 1108,
   :var-type "function",
   :arglists ([coll]),
   :doc
   "\nA version of count that works on collections, matrices, and numbers.\nThe length of a number is one, the length of a collection is its count\nand the length of a matrix is the number of elements it contains (nrow*ncol).\nEquivalent to R's length function.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/length"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "log",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L544",
   :line 544,
   :var-type "function",
   :arglists ([A]),
   :doc
   "\nReturns the natural log of the elements in the given matrix, sequence or number.\nEquivalent to R's log function.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/log"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "log10",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L562",
   :line 562,
   :var-type "function",
   :arglists ([A]),
   :doc
   "\nReturns the log base 10 of the elements in the given matrix, sequence or number.\nEquivalent to R's log10 function.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/log10"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "log2",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L552",
   :line 552,
   :var-type "function",
   :arglists ([A]),
   :doc
   "\nReturns the log base 2 of the elements in the given matrix, sequence or number.\nEquivalent to R's log2 function.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/log2"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "make-unique",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1467",
   :line 1467,
   :var-type "function",
   :arglists ([coll] [coll seen]),
   :doc
   "\nTake a sequence of keywords and make them unique by possibly\naltering later ones.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/make-unique"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "map->Dataset",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L53",
   :line 53,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.core.Dataset, taking a map of keywords to field values.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/map->Dataset"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "matrix",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L57",
   :line 57,
   :var-type "function",
   :arglists ([data] [data ncol] [init-val rows cols]),
   :doc
   "\nReturns an instance of an incanter.Matrix, which is an extension of\nClatrix matrix that implements the Clojure interface\nclojure.lang.ISeq. Therefore Clojure sequence operations can be\napplied to matrices. A matrix consists of a sequence of rows, where\neach row is a one-dimensional row matrix. One-dimensional matrices are\nin turn, sequences of numbers. Equivalent to R's matrix function.\n\nExamples:\n  (def A (matrix [[1 2 3] [4 5 6] [7 8 9]])) ; produces a 3x3 matrix\n  (def A2 (matrix [1 2 3 4 5 6 7 8 9] 3)) ; produces the same 3x3 matrix\n  (def B (matrix [1 2 3 4 5 6 7 8 9])) ; produces a 9x1 column vector\n\n  (first A) ; produces a row matrix [1 2 3]\n  (rest A) ; produces a sub matrix [[4 5 6] [7 8 9]]\n  (first (first A)) ; produces 1.0\n  (rest (first A)) ; produces a row matrix [2 3]\n\n  ; since (plus row1 row2) adds the two rows element-by-element\n  (reduce plus A) ; produces the sums of the columns\n\n  ; and since (sum row1) sums the elements of the row\n  (map sum A) ; produces the sums of the rows\n\n  ; you can filter the rows using Clojure's filter function\n  (filter #(> (nth % 1) 4) A) ; returns the rows where the second column is greater than 4.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/matrix"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "matrix-map",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1915",
   :line 1915,
   :var-type "function",
   :arglists ([f m] [f m & ms]),
   :doc
   "\nLike clojure.core/map, but will work on matrices of any dimension:\n1 x 1 (like e.g. a Double), 1 x n, n x 1, and n x m\n\nExamples:\n  (use '(incanter core))\n  (def mat (matrix (range 9) 3))\n  (matrix-map #(mod % 2) mat)\n  (matrix-map #(mod % 2) (first mat))\n  (matrix-map #(mod % 2) ($ 1 0 mat))\n  (matrix-map #(mod % 2) [1 2 3 4])\n  (matrix-map #(mod % 2) 9)\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/matrix-map"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "matrix?",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L95",
   :line 95,
   :var-type "function",
   :arglists ([obj]),
   :doc "Test if obj is 'derived' clatrix.core.Matrix",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/matrix?"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "melt",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2284",
   :line 2284,
   :var-type "function",
   :arglists ([dataset pivot-key]),
   :doc
   "\nMelt an object into a form suitable for easy casting, like a melt function in R.\nOnly accepts one pivot key for now. e.g.\n\n  (use '(incanter core charts datasets))\n  (view (with-data (melt (get-dataset :flow-meter) :Subject)\n            (line-chart :Subject :value :group-by :variable :legend true)))\n\nSee http://www.statmethods.net/management/reshape.html for more examples.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/melt"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "minus",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L413",
   :line 413,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "\nPerforms element-by-element subtraction on multiple matrices, sequences\nand/or numbers. If only a single argument is provided, returns the negative\nof the given matrix, sequence, or number. Equivalent to R's - operator.\n\nExamples:\n  (def A (matrix [[1 2 3]\n                  [4 5 6]\n                  [7 8 9]]))\n  (minus A)\n  (minus A A A)\n  (minus A 2)\n  (minus 2 A)\n  (minus [1 2 3] [1 2 3])\n  (minus [1 2 3] 2)\n  (minus 2 [1 2 3])\n  (minus [1 2 3])\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/minus"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "mmult",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L723",
   :line 723,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "\nReturns the matrix resulting from the matrix multiplication of the\nthe given arguments. Equivalent to R's %*% operator.\n\nExamples:\n\n  (def A (matrix [[1 2 3]\n                  [4 5 6]\n                  [7 8 9]]))\n  (mmult A (trans A))\n  (mmult A (trans A) A)\n\nReferences:\n  http://en.wikipedia.org/wiki/Matrix_multiplication\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/mmult"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "mult",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L435",
   :line 435,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "\nPerforms element-by-element multiplication on multiple matrices, sequences\nand/or numbers. Equivalent to R's * operator.\n\nExamples:\n\n(def A (matrix [[1 2 3]\n                [4 5 6]\n                [7 8 9]]))\n(mult A A A)\n(mult A 2)\n(mult 2 A)\n(mult [1 2 3] [1 2 3])\n(mult [1 2 3] 2)\n(mult 2 [1 2 3])\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/mult"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "ncol",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L112",
   :line 112,
   :var-type "function",
   :arglists ([mat]),
   :doc
   "Returns the number of columns in the given matrix. Equivalent to R's ncol function.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/ncol"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "nrow",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L103",
   :line 103,
   :var-type "function",
   :arglists ([mat]),
   :doc
   "Returns the number of rows in the given matrix. Equivalent to R's nrow function.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/nrow"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "plus",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L393",
   :line 393,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "\nPerforms element-by-element addition on multiple matrices, sequences\nand/or numbers. Equivalent to R's + operator.\n\nExamples:\n\n(def A (matrix [[1 2 3]\n                [4 5 6]\n                [7 8 9]]))\n(plus A A A)\n(plus A 2)\n(plus 2 A)\n(plus [1 2 3] [1 2 3])\n(plus [1 2 3] 2)\n(plus 2 [1 2 3])\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/plus"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "pow",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L512",
   :line 512,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "\nThis is an element-by-element exponent function, raising the first argument\nby the exponents in the remaining arguments. Equivalent to R's ^ operator.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/pow"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "prod",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L868",
   :line 868,
   :var-type "function",
   :arglists ([x]),
   :doc "Returns the product of the given sequence.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/prod"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "query-dataset",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1309",
   :line 1309,
   :var-type "function",
   :arglists ([data query-map]),
   :doc
   "\nQueries the given dataset using the query-map, returning a new dataset.\nThe query-map uses the the dataset's column-names as keys and a\nsimple variant of the MongoDB query language.\n\nFor instance, given a dataset with two columns, :x and :category,  to query\nfor rows where :x equals 10, use the following query-map: {:x 10}.\n\nTo indicate that :x should be between 10 and 20, use {:x {:$gt 10 :$lt 20}}.\n\nTo indicate that :category should also be either :red, :green, or :blue, use :$in\n{:x {:$gt 10 :$lt 20} :y {:$in #{:green :blue :red}}}\n\nAnd to indicate that :category should not include :red, :green, or :blue, use :$nin\n{:x {:$gt 10 :$lt 20} :y {:$nin #{:green :blue :red}}}\n\nThe available query terms include :$gt, :$lt, :$gte, :$lte, :$eq, :$ne, :$in, :$nin, $fn.\n\nA row predicate function can be used instead of a query-map. The function must accept\na map, representing a row of the dataset, and return a boolean value indicating whether\nthe row should be included in the new dataset.\n\nExamples:\n  (use '(incanter core datasets))\n  (def cars (get-dataset :cars))\n\n  (view (query-dataset cars {:speed 10}))\n  (view (query-dataset cars {:speed {:$in #{17 14 19}}}))\n  (view (query-dataset cars {:speed {:$lt 20 :$gt 10}}))\n  (view (query-dataset cars {:speed {:$fn #(> (log %) 3)}}))\n\n  ;; use a row predicate function instead of a query map.\n  (view (query-dataset cars (fn [row] (> (/ (row \"speed\") (row \"dist\")) 1/2))))\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/query-dataset"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "query-to-pred",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1258",
   :line 1258,
   :var-type "function",
   :arglists ([query-map]),
   :doc
   "\nGiven a query-map, it returns a function that accepts a hash-map and returns true if it\nsatisfies the conditions specified in the provided query-map.\n\nExamples:\n\n  (use 'incanter.core)\n  (def pred (query-to-pred {:x 5 :y 7}))\n  (pred {:x 5 :y 7 :z :d})\n\n  (def pred (query-to-pred {:x 5 :y {:$gt 5 :$lt 10}}))\n  (pred {:x 5 :y 7 :z :d})\n\n  (def pred (query-to-pred {:z {:$in #{:a :b}}}))\n  (pred {:x 5 :y 7 :z :d})\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/query-to-pred"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "quit",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2729",
   :line 2729,
   :var-type "function",
   :arglists ([]),
   :doc "Exits the Clojure shell.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/quit"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "rank",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1085",
   :line 1085,
   :var-type "function",
   :arglists ([mat]),
   :doc
   "\nReturns the effective numerical matrix rank, which is the number of nonnegligible singular values.\n\nExamples:\n\n(use 'incanter.core)\n(def foo (matrix (range 9) 3))\n(rank foo)\n\nReferences:\nhttp://en.wikipedia.org/wiki/Matrix_rank\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/rank"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "regularized-beta",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2441",
   :line 2441,
   :var-type "function",
   :arglists ([x a b]),
   :doc
   "\nReturns the regularized incomplete beta value. Equivalent to R's pbeta function.\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Gamma.html\n  http://en.wikipedia.org/wiki/Regularized_incomplete_beta_function\n  http://mathworld.wolfram.com/RegularizedBetaFunction.html\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/regularized-beta"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "rename-cols",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2068",
   :line 2068,
   :var-type "function",
   :arglists ([col-map] [col-map data]),
   :doc
   "\nRename columns based on col-map of old-col new-col-name pairs.  If\nold-col is a number it is taken as a 0 based index for the column to\nreplace\n\nExample:\n (use '(incanter core datasets))\n (rename-cols {:Sepal.Length :s.length 3 :p.width} (get-dataset :iris))\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/rename-cols"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "reorder-columns",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2962",
   :line 2962,
   :var-type "function",
   :arglists ([dset cols]),
   :doc
   "\nProduce a new dataset with the columns in the specified order.\nReturns nil if no valid column names are given.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/reorder-columns"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "replace-column",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2090",
   :line 2090,
   :var-type "function",
   :arglists ([column-name values] [column-name values data]),
   :doc "Replaces a column in a dataset with new values.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/replace-column"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "safe-div",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L479",
   :line 479,
   :var-type "function",
   :arglists ([x] [x y] [x y & more]),
   :doc
   "\nDivideByZero safe alternative to clojures / function,\ndetects divide by zero and returns Infinity, -Infinity or NaN as appropriate.\nNote: Does not work on matrices, only primitive types\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/safe-div"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "save",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2755",
   :line 2755,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "\nSave is a multi-function that is used to write matrices, datasets and\ncharts (in png format) to a file.\n\nArguments:\n  obj -- is a matrix, dataset, or chart object\n  filename -- the filename to create.\n\nMatrix and dataset options:\n  :delim (default \\,) column delimiter\n  :header (default nil) an sequence of strings to be used as header line\n      for matrices the default value is nil, for datasets, the default is\n      the dataset's column-names array.\n  :append (default false) determines whether this given file should be\n      appended to. If true, a header will not be written to the file again.\n  If the filename is exactly \"-\" then *out* the matrix/dataset will be\n      written to *out*\n\nChart options:\n  :width (default 500)\n  :height (default 400)\n\n\nMatrix Examples:\n\n  (use '(incanter core io))\n  (def A (matrix (range 12) 3)) ; creates a 3x4 matrix\n  (save A \"A.dat\") ; writes A to the file A.dat, with no header and comma delimited\n  (save A \"A.dat\" :delim \\tab) ; writes A to the file A.dat, with no header and tab delimited\n\n  ;; writes A to the file A.dat, with a header and tab delimited\n  (save A \"A.dat\" :delim \\, :header [\"col1\" \"col2\" \"col3\"])\n\n\nDataset Example:\n\n  (use '(incanter core io datasets))\n  ;; read the iris sample dataset, and save it to a file.\n  (def iris (get-dataset :iris))\n  (save iris \"iris.dat\")\n\n\nChart Example:\n\n  (use '(incanter core io stats charts))\n  (save (histogram (sample-normal 1000)) \"hist.png\")\n\n  ;; chart example using java.io.OutputStream instead of filename\n  (use '(incanter core stats charts))\n  (import 'java.io.FileOutputStream)\n  (def fos (FileOutputStream. \"/tmp/hist.png\"))\n  (def hist (histogram (sample-normal 1000)))\n  (save hist fos)\n  (.close fos)\n\n  (view \"file:///tmp/hist.png\")\n\n\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/save"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "sel",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L185",
   :line 185,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "\nReturns an element or subset of the given matrix, dataset, or list.\nIf the column or row is specified as an atomic object (index or name), then\nthe result will be returned as a list (only values from selected column or row).\n\nArgument:\n  a matrix object, dataset, or list.\n\nOptions:\n  :rows (default true)\n    returns all rows by default, can pass a row index or sequence of row indices\n  :cols (default true)\n    returns all columns by default, can pass a column index or sequence of column indices\n  :except-rows (default nil) can pass a row index or sequence of row indices to exclude\n  :except-cols (default nil) can pass a column index or sequence of column indices to exclude\n  :filter-fn (default nil)\n    a function can be provided to filter the rows of the matrix\n\nExamples:\n  (use 'incanter.datasets)\n  (def iris (to-matrix (get-dataset :iris)))\n  (sel iris 0 0) ; first element\n  (sel iris :rows 0 :cols 0) ; also first element\n  (sel iris :cols 0) ; first column of all rows\n  (sel iris :cols [0 2]) ; first and third column of all rows\n  (sel iris :rows (range 10) :cols (range 2)) ; first two columns of the first 10 rows\n  (sel iris :rows (range 10)) ; all columns of the first 10 rows\n\n  ;; exclude rows or columns\n  (sel iris :except-rows (range 10)) ; all columns of all but the first 10 rows\n  (sel iris :except-cols 1) ; all columns except the second\n\n  ;; return only the first 10 even rows\n  (sel iris :rows (range 10) :filter-fn #(even? (int (nth % 0))))\n  ;; select rows where distance (third column) is greater than 50\n  (sel iris :filter #(> (nth % 2) 4))\n\n  ;; examples with datasets\n  (use 'incanter.datasets)\n  (def us-arrests (get-dataset :us-arrests))\n  (sel us-arrests :cols \"State\")\n  (sel us-arrests :cols :State)\n\n  (sel us-arrests :cols [\"State\" \"Murder\"])\n  (sel us-arrests :cols [:State :Murder])\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/sel"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "set-data",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2692",
   :line 2692,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "\nExamples:\n\n  (use '(incanter core charts datasets))\n\n  (def data (get-dataset :iris))\n  (def table (data-table data))\n  (view table)\n  ;; now view only a subset of the data\n  (set-data table ($where {:Petal.Length {:gt 6}} data))\n\n\n  ;; use sliders to dynamically select the query values\n  (let [data (get-dataset :iris)\n        table (data-table data)]\n    (view table)\n    (sliders [species [\"setosa\" \"virginica\" \"versicolor\"]\n              min-petal-length (range 0 8 0.1)]\n      (set-data table ($where {:Species species\n                               :Petal.Length {:gt min-petal-length}}\n                              data))))\n\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/set-data"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "sin",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L585",
   :line 585,
   :var-type "function",
   :arglists ([A]),
   :doc
   "\nReturns the sine of the elements in the given matrix, sequence or number.\nEquivalent to R's sin function.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/sin"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "solve",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L774",
   :line 774,
   :var-type "function",
   :arglists ([A B] [A]),
   :doc
   "\nReturns a matrix solution if A is square, least squares solution otherwise.\nEquivalent to R's solve function.\n\nExamples:\n  (solve (matrix [[2 0 0] [0 2 0] [0 0 2]]))\n\nReferences:\n  http://en.wikipedia.org/wiki/Matrix_inverse\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/solve"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "solve-quadratic",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2459",
   :line 2459,
   :var-type "function",
   :arglists ([a b c]),
   :doc
   "\nReturns a vector with the solution to x from the quadratic\nequation, a*x^2 + b*x + c.\n\nArguments:\n  a, b, c: coefficients of a qaudratic equation.\n\nExamples:\n  ;; -2*x^2 + 7*x + 15\n  (quadratic-formula -2 7 15)\n  ;; x^2 + -2*x + 1\n  (quadratic-formula 1 -2 1)\n\nReferences:\n  http://en.wikipedia.org/wiki/Quadratic_formula\n\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/solve-quadratic"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "sq",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L536",
   :line 536,
   :var-type "function",
   :arglists ([A]),
   :doc
   "\nReturns the square of the elements in the given matrix, sequence or number.\nEquivalent to R's sq function.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/sq"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "sqrt",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L528",
   :line 528,
   :var-type "function",
   :arglists ([A]),
   :doc
   "\nReturns the square-root of the elements in the given matrix, sequence or number.\nEquivalent to R's sqrt function.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/sqrt"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "sum",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L861",
   :line 861,
   :var-type "function",
   :arglists ([x]),
   :doc "Returns the sum of the given sequence.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/sum"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "sum-of-squares",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L854",
   :line 854,
   :var-type "function",
   :arglists ([x]),
   :doc "Returns the sum-of-squares of the given sequence.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/sum-of-squares"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "symmetric-matrix",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2486",
   :line 2486,
   :var-type "function",
   :arglists ([data & {:keys [lower], :or {lower true}}]),
   :doc
   "\nReturns a symmetric matrix from the given data, which represents the lower triangular elements\nordered by row. This is not the inverse of half-vectorize which returns a vector of the upper-triangular\nvalues, unless the :lower option is set to false.\n\nOptions:\n  :lower (default true) -- lower-triangular. Set :lower to false to reverse the half-vectorize function.\n\nExamples:\n\n  (use 'incanter.core)\n  (symmetric-matrix [1\n                     2 3\n                     4 5 6\n                     7 8 9 10])\n\n\n  (half-vectorize\n    (symmetric-matrix [1\n                       2 3\n                       4 5 6\n                       7 8 9 10] :lower false))\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/symmetric-matrix"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "tail",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1679",
   :line 1679,
   :var-type "function",
   :arglists ([len mat] [mat]),
   :doc
   "Returns the tail of the dataset. 10 or full dataset by default.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/tail"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "tan",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L616",
   :line 616,
   :var-type "function",
   :arglists ([A]),
   :doc
   "\nReturns the tangent of the elements in the given matrix, sequence or number.\nEquivalent to R's tan function.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/tan"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "to-dataset",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L1410",
   :line 1410,
   :var-type "function",
   :arglists ([obj & {:keys [transpose]}]),
   :doc
   "\nReturns a dataset containing the given values.\n\nExamples:\n\n  (use 'incanter.core)\n  (to-dataset 1)\n  (to-dataset :a)\n  (to-dataset [:a])\n  (to-dataset (range 10))\n  (to-dataset (range 10) :transpose true)\n  (to-dataset [[1 2] [3 4] [5 6]])\n  (to-dataset {:a 1 :b 2 :c 3})\n  (to-dataset {\"a\" 1 \"b\" 2 \"c\" 3})\n  (to-dataset [{:a 1 :b 2} {:a 1 :b 2}])\n  (to-dataset [{\"a\" 1 \"b\" 2 \"c\" 3} {\"a\" 1 \"b\" 2 \"c\" 3}])\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/to-dataset"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "to-list",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L673",
   :line 673,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "\nReturns a list-of-lists if the given matrix is two-dimensional\nand a flat list if the matrix is one-dimensional.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/to-list"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "to-map",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2255",
   :line 2255,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "\nTakes a dataset or matrix and returns a hash-map where the keys are\nkeyword versions of the column names, for datasets, or numbers, for\nmatrices, and the values are sequence of the column values.\n\nExamples:\n  (use '(incanter core datasets))\n\n  (to-map (get-dataset :cars))\n\n  (to-map (matrix (range 9) 3))\n\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/to-map"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "to-matrix",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2383",
   :line 2383,
   :var-type "function",
   :arglists ([dataset & {:keys [dummies], :or {dummies false}}]),
   :doc
   "\nConverts a dataset into a matrix. Equivalent to R's as.matrix function\nfor datasets.\n\nOptions:\n  :dummies (default false) -- if true converts non-numeric variables into sets\n                              of binary dummy variables, otherwise converts\n                              them into numeric codes.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/to-matrix"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "to-vect",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L696",
   :line 696,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "\nReturns a vector-of-vectors if the given matrix is two-dimensional\nand a flat vector if the matrix is one-dimensional. This is a bit\nslower than the to-list function\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/to-vect"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "toeplitz",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2527",
   :line 2527,
   :var-type "function",
   :arglists ([x]),
   :doc
   "\nReturns the Toeplitz matrix for the given vector, which form the first row of the matrix\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/toeplitz"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "trace",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L801",
   :line 801,
   :var-type "function",
   :arglists ([mat]),
   :doc
   "\nReturns the trace of the given matrix.\n\nReferences:\nhttp://en.wikipedia.org/wiki/Matrix_trace\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/trace"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "trans",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L154",
   :line 154,
   :var-type "function",
   :arglists ([mat]),
   :doc
   "\nReturns the transpose of the given matrix. Equivalent to R's t function\n\nExamples:\n  (def A (matrix [[1 2 3]\n                  [4 5 6]\n                  [7 8 9]]))\n  (trans A)\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/trans"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "transform-col",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2140",
   :line 2140,
   :var-type "function",
   :arglists ([dataset column f & args]),
   :doc
   "\nApply function f & args to the specified column of dataset and replace the column\nwith the resulting new values.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/transform-col"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "vectorize",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L811",
   :line 811,
   :var-type "function",
   :arglists ([mat]),
   :doc
   "\nReturns the vectorization (i.e. vec) of the given matrix.\nThe vectorization of an m-by-n matrix A, denoted by vec(A)\nis the m*n-by-1 column vector obtain by stacking the columns\nof the matrix A on top of one another.\n\nFor instance:\n  (= (vectorize (matrix [[a b] [c d]])) (matrix [a c b d]))\n\nExamples:\n  (def A (matrix [[1 2] [3 4]]))\n  (vectorize A)\n\nReferences:\n  http://en.wikipedia.org/wiki/Vectorization_(mathematics)\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/vectorize"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "view",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2542",
   :line 2542,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "\nThis is a general 'view' function. When given an Incanter matrix/dataset\nor a Clojure numeric collection, it will display it in a Java Swing\nJTable. When given an Incanter chart object, it will display it in a new\nwindow. When given a URL string, it will open the location with the\nplatform's default web browser.\n\nWhen viewing charts, a :width (default 500) and :height (default 400)\noption can be provided.\n\nWhen viewing an incanter.processing sketch, set the :exit-on-close option\nto true (default is false) to kill the animation processes when you\nclose the window (this will also kill your REPL or Swank server),\notherwise those processing will continue to run in the background.\n\n\n\nExamples:\n\n  (use '(incanter core stats datasets charts))\n\n  ;; view matrices\n  (def rand-mat (matrix (sample-normal 100) 4))\n  (view rand-mat)\n\n  ;; view numeric collections\n  (view [1 2 3 4 5])\n  (view (sample-normal 100))\n\n  ;; view Incanter datasets\n  (view (get-dataset :iris))\n\n  ;; convert dataset to matrix, changing Species names to numeric codes\n  (view (to-matrix (get-dataset :iris)))\n\n  ;; convert dataset to matrix, changing Species names to dummy variables\n  (view (to-matrix (get-dataset :iris) :dummies true))\n\n  ;; view a chart\n  (view (histogram (sample-normal 1000)) :width 700 :height 700)\n\n  ;; view a URL\n  (view \"http://incanter.org\")\n\n  ;; view a PNG file\n  (save (histogram (sample-normal 1000)) \"/tmp/norm_hist.png\")\n  (view \"file:///tmp/norm_hist.png\")\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/view"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj",
   :name "with-data",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a8cab5ea7cb61b2a7bf600c7910c575ad7551934/modules/incanter-core/src/incanter/core.clj#L2231",
   :line 2231,
   :var-type "macro",
   :arglists ([data-binding & body]),
   :doc
   "\nBinds the given data to $data and executes the body.\nTypically used with the $ and $where functions.\n\nExamples:\n  (use '(incanter core stats charts datasets))\n\n  (with-data  (get-dataset :cars)\n    (def lm (linear-model ($ :dist) ($ :speed)))\n    (doto (scatter-plot ($ :speed) ($ :dist))\n              (add-lines ($ :speed) (:fitted lm))\n               view))\n\n   ;; create a dataset where :speed greater than 10 or less than -10\n   (with-data (get-dataset :cars)\n     (view (-> ($where {:speed {:$gt 20}})\n                     (conj-rows ($where {:speed {:$lt 10}})))))\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/with-data"}
  {:name "Dataset",
   :var-type "record",
   :namespace "incanter.core",
   :arglists nil,
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/Dataset",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/b2536728626dbf109884bd3fb9c98d4cbc04a362/modules/incanter-io/src/incanter/datasets.clj",
   :name "get-dataset",
   :file "modules/incanter-io/src/incanter/datasets.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/b2536728626dbf109884bd3fb9c98d4cbc04a362/modules/incanter-io/src/incanter/datasets.clj#L92",
   :line 92,
   :var-type "function",
   :arglists
   ([dataset-key
     &
     {:keys [incanter-home from-repo],
      :or
      {incanter-home
       (or (getProperty "incanter.home") (getenv "INCANTER_HOME")),
       from-repo false}}]),
   :doc
   "\nReturns the sample dataset associated with the given key. Most datasets\nare from R's sample data sets, as are the descriptions below.\n\nOptions:\n\n  :incanter-home -- if the incanter.home property is not set when the JVM is\n                    started (using -Dincanter.home) or there is no INCANTER_HOME\n                    environment variable set, use the :incanter-home options to\n                    provide the parent directory of the sample data directory.\n\n  :from-repo (default false) -- If true, retrieves the dataset from the online repository\n                     instead of locally, it will do this by default if incanter-home is not set.\n\n\nDatasets:\n\n  :iris -- the Fisher's or Anderson's Iris data set gives the\n           measurements in centimeters of the variables sepal\n           length and width and petal length and width,\n           respectively, for 50 flowers from each of 3 species\n           of iris.\n\n  :cars -- The data give the speed of cars and the distances taken\n            to stop. Note that the data were recorded in the 1920s.\n\n  :survey -- survey data used in Scott Lynch's 'Introduction to Applied Bayesian Statistics\n             and Estimation for Social Scientists'\n\n  :us-arrests -- This data set contains statistics, in arrests per 100,000\n                 residents for assault, murder, and rape in each of the 50 US\n                 states in 1973. Also given is the percent of the population living\n                 in urban areas.\n\n  :flow-meter -- flow meter data used in Bland Altman Lancet paper.\n\n  :co2 -- has 84 rows and 5 columns of data from an experiment on the cold tolerance\n          of the grass species _Echinochloa crus-galli_.\n\n  :chick-weight -- has 578 rows and 4 columns from an experiment on the effect of diet\n                   on early growth of chicks.\n\n  :plant-growth -- Results from an experiment to compare yields (as measured by dried\n                   weight of plants) obtained under a control and two different\n                   treatment conditions.\n\n  :pontius -- These data are from a NIST study involving calibration of load cells.\n              The response variable (y) is the deflection and the predictor variable\n              (x) is load.\n              See http://www.itl.nist.gov/div898/strd/lls/data/Pontius.shtml\n\n  :filip -- NIST data set for linear regression certification,\n            see http://www.itl.nist.gov/div898/strd/lls/data/Filip.shtml\n\n  :longely -- This classic dataset of labor statistics was one of the first used to\n              test the accuracy of least squares computations. The response variable\n              (y) is the Total Derived Employment and the predictor variables are GNP\n              Implicit Price Deflator with Year 1954 = 100 (x1), Gross National Product\n              (x2), Unemployment (x3), Size of Armed Forces (x4), Non-Institutional\n              Population Age 14 & Over (x5), and Year (x6).\n              See http://www.itl.nist.gov/div898/strd/lls/data/Longley.shtml\n\n  :Chwirut -- These data are the result of a NIST study involving ultrasonic calibration.\n              The response variable is ultrasonic response, and the predictor variable is\n              metal distance.\n              See http://www.itl.nist.gov/div898/strd/nls/data/LINKS/DATA/Chwirut1.dat\n\n  :thurstone -- test data for non-linear least squares.\n\n  :austres -- Quarterly Time Series of the Number of Australian Residents\n\n  :hair-eye-color -- Hair and eye color of sample of students\n\n  :airline-passengers -- Monthly Airline Passenger Numbers 1949-1960\n\n  :math-prog -- Pass/fail results for a high school mathematics assessment test\n                and a freshmen college programming course.\n\n  :iran-election -- Vote counts for 30 provinces from the 2009 Iranian election.\n\n Examples:\n   (def data (get-dataset :cars))\n   (def data2 (get-dataset :cars :incanter.home \"/usr/local/packages/incanter\"))\n\n",
   :namespace "incanter.datasets",
   :wiki-url
   "http://incanter.github.com/incanter//datasets-api.html#incanter.datasets/get-dataset"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "->Beta-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L497",
   :line 497,
   :var-type "function",
   :arglists ([alpha beta]),
   :doc
   "Positional factory function for class incanter.distributions.Beta-rec.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->Beta-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "->Binomial-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L530",
   :line 530,
   :var-type "function",
   :arglists ([n p]),
   :doc
   "Positional factory function for class incanter.distributions.Binomial-rec.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->Binomial-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "->ChiSquare-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L561",
   :line 561,
   :var-type "function",
   :arglists ([df]),
   :doc
   "Positional factory function for class incanter.distributions.ChiSquare-rec.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->ChiSquare-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "->Combination",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L342",
   :line 342,
   :var-type "function",
   :arglists ([n k u]),
   :doc
   "Positional factory function for class incanter.distributions.Combination.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->Combination"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "->DoubleUniform-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L788",
   :line 788,
   :var-type "function",
   :arglists ([min max]),
   :doc
   "Positional factory function for class incanter.distributions.DoubleUniform-rec.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->DoubleUniform-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "->Exponential-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L591",
   :line 591,
   :var-type "function",
   :arglists ([rate]),
   :doc
   "Positional factory function for class incanter.distributions.Exponential-rec.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->Exponential-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "->F",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L620",
   :line 620,
   :var-type "function",
   :arglists ([df1 df2]),
   :doc
   "Positional factory function for class incanter.distributions.F.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->F"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "->Gamma-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L662",
   :line 662,
   :var-type "function",
   :arglists ([shape scale]),
   :doc
   "Positional factory function for class incanter.distributions.Gamma-rec.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->Gamma-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "->NegativeBinomial-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L694",
   :line 694,
   :var-type "function",
   :arglists ([size prob]),
   :doc
   "Positional factory function for class incanter.distributions.NegativeBinomial-rec.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->NegativeBinomial-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "->Normal-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L456",
   :line 456,
   :var-type "function",
   :arglists ([mean sd]),
   :doc
   "Positional factory function for class incanter.distributions.Normal-rec.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->Normal-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "->Poisson-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L727",
   :line 727,
   :var-type "function",
   :arglists ([lambda]),
   :doc
   "Positional factory function for class incanter.distributions.Poisson-rec.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->Poisson-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "->StudentT-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L757",
   :line 757,
   :var-type "function",
   :arglists ([df]),
   :doc
   "Positional factory function for class incanter.distributions.StudentT-rec.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->StudentT-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "->UniformInt",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L252",
   :line 252,
   :var-type "function",
   :arglists ([start end]),
   :doc
   "Positional factory function for class incanter.distributions.UniformInt.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->UniformInt"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "beta-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L508",
   :line 508,
   :var-type "function",
   :arglists ([] [alpha beta]),
   :doc
   "\nReturns a Beta distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  alpha      (default 1)\n  beta       (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Beta.html\n  http://en.wikipedia.org/wiki/Beta_distribution\n\nExample:\n  (pdf (beta-distribution 1 2) 0.5)\n",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/beta-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "binomial-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L539",
   :line 539,
   :var-type "function",
   :arglists ([] [n p]),
   :doc
   "\nReturns a Binomial distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  size       (default 1)\n  prob       (default 1/2)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Binomial.html\n  http://en.wikipedia.org/wiki/Binomial_distribution\n\nExample:\n  (pdf (binomial-distribution 20 1/4) 10)\n",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/binomial-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "chisq-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L570",
   :line 570,
   :var-type "function",
   :arglists ([] [df]),
   :doc
   "\nReturns a Chi-square distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  df         (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/ChiSquare.html\n  http://en.wikipedia.org/wiki/Chi_square_distribution\n\nExample:\n  (pdf (chisq-distribution 2) 5.0)\n",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/chisq-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "combination-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L351",
   :line 351,
   :var-type "function",
   :arglists ([n k]),
   :doc
   "\nCreate a distribution of all the k-sized combinations of n integers.\nCan be considered a multivariate distribution over k-dimensions, where\neach dimension is a discrete random variable on the (0, n] range (though\nthese variables are decidedly non-independent).\n\nA draw from this distribution can also be considered a sample without\nreplacement from any finite set, where the values in the returned\nvector represent the indices of the items in the set.\n\nArguments:\n  n\t  The number of possible items from which to select.\n  k\t  The size of a sample (without replacement) to draw.\n\nSee also:\n  test-statistic-distribution, integer-distribution, pdf, cdf, draw, support\n\nReferences:\n  http://en.wikipedia.org/wiki/Combination\n\n",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/combination-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "exponential-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L600",
   :line 600,
   :var-type "function",
   :arglists ([] [rate]),
   :doc
   "\nReturns a Exponential distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  rate       (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Exponential.html\n  http://en.wikipedia.org/wiki/Exponential_distribution\n\nExample:\n  (pdf (exponential-distribution 1/2) 2.0)\n",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/exponential-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "f-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L641",
   :line 641,
   :var-type "function",
   :arglists ([] [df1 df2]),
   :doc
   "\nReturns a F-distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  df1        (default 1)\n  df2        (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://en.wikipedia.org/wiki/F_distribution\n  http://mathworld.wolfram.com/F-Distribution.html\n\nExample:\n  (pdf (f-distribution 5 2) 1.0)\n",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/f-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "gamma-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L672",
   :line 672,
   :var-type "function",
   :arglists ([] [shape scale]),
   :doc
   "\nReturns a Gamma distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  shape (k)  (default 1)\n  scale (θ)  (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Gamma.html\n  http://en.wikipedia.org/wiki/Gamma_distribution\n\nExample:\n  (pdf (gamma-distribution 1 2) 10)\n",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/gamma-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "integer-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L269",
   :line 269,
   :var-type "function",
   :arglists ([] [end] [start end]),
   :doc
   "\nCreate a uniform distribution over a set of integers over\nthe (start, end] interval. An alternative method of creating\na distribution would be to just use a sequence of integers\n(e.g. (draw (range 100000))). For large sequences, like the one\nin the example, using a sequence will be require realizing the\nentire sequence before a draw can be taken. This less efficient than\ncomputing random draws based on the end points of the distribution.\n\nArguments:\nstart\tThe lowest end of the interval, such that (>= (draw d) start)\n      is always true. (Default 0)\n  end\tThe value at the upper end of the interval, such that\n        (> end (draw d)) is always true. Note the strict inequality.\n        (Default 1)\n\nSee also:\n  pdf, cdf, draw, support\n\nReferences:\n  http://en.wikipedia.org/wiki/Uniform_distribution_(discrete)\n\nExamples:\n  (pdf (integer-distribution 0 10) 3) ; returns 1/10 for any value\n  (draw (integer-distribution -5 5))\n  (draw (integer-distribution (bit-shift-left 2 1000))) ; probably a very large value\n",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/integer-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->Beta-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L497",
   :line 497,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.Beta-rec, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->Beta-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->Binomial-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L530",
   :line 530,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.Binomial-rec, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->Binomial-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->ChiSquare-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L561",
   :line 561,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.ChiSquare-rec, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->ChiSquare-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->Combination",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L342",
   :line 342,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.Combination, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->Combination"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->DoubleUniform-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L788",
   :line 788,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.DoubleUniform-rec, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->DoubleUniform-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->Exponential-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L591",
   :line 591,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.Exponential-rec, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->Exponential-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->F",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L620",
   :line 620,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.F, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->F"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->Gamma-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L662",
   :line 662,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.Gamma-rec, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->Gamma-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->NegativeBinomial-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L694",
   :line 694,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.NegativeBinomial-rec, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->NegativeBinomial-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->Normal-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L456",
   :line 456,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.Normal-rec, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->Normal-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->Poisson-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L727",
   :line 727,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.Poisson-rec, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->Poisson-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->StudentT-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L757",
   :line 757,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.StudentT-rec, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->StudentT-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->UniformInt",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L252",
   :line 252,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.UniformInt, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->UniformInt"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "neg-binomial-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L705",
   :line 705,
   :var-type "function",
   :arglists ([] [size prob]),
   :doc
   "\nReturns a Negative binomial distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  size       (default 10)\n  prob       (default 1/2)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/NegativeBinomial.html\n  http://en.wikipedia.org/wiki/Negative_binomial_distribution\n\nExample:\n  (pdf (neg-binomial-distribution 20 1/2) 10)\n",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/neg-binomial-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "normal-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L470",
   :line 470,
   :var-type "function",
   :arglists ([] [mean sd]),
   :doc
   "\nReturns a Normal distribution that implements the\nincanter.distributions.Distribution protocol.\n\nArguments:\n  mean\tThe mean of the distribution. One of two parameters\n        that summarize the Normal distribution (default 0).\n  sd    The standard deviation of the distribution.\n        The second parameter that describes the Normal (default 1).\n\nSee also:\n    Distribution, pdf, cdf, draw, support\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Normal.html\n    http://en.wikipedia.org/wiki/Normal_distribution\n\nExample:\n    (pdf (normal-distribution -2 (sqrt 0.5)) 1.96)\n",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/normal-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "poisson-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L736",
   :line 736,
   :var-type "function",
   :arglists ([] [lambda]),
   :doc
   "\nReturns a Poisson distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  lambda     (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Poisson.html\n  http://en.wikipedia.org/wiki/Poisson_distribution\n\nExample:\n  (pdf (poisson-distribution 10) 5)\n",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/poisson-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "roulette-wheel",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L198",
   :line 198,
   :var-type "function",
   :arglists ([freqs]),
   :doc
   "Perform a roulette wheel selection given a list of frequencies",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/roulette-wheel"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "t-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L767",
   :line 767,
   :var-type "function",
   :arglists ([] [df]),
   :doc
   "\nReturns a Student-t distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  df         (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/StudentT.html\n  http://en.wikipedia.org/wiki/Student-t_distribution\n\nExample:\n  (pdf (t-distribution 10) 1.2)\n",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/t-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "test-statistic-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L380",
   :line 380,
   :var-type "function",
   :arglists ([test-statistic n k]),
   :doc
   "\nCreate a distribution of the test-statistic over the possible\nrandom samples of treatment units from the possible units.\n\nThere are two methods for generating the distribution. The\nfirst method is enumerating all possible randomizations and\nperforming the test statistic on each. This gives the exact\ndistribution, but is only feasible for small problems.\n\nThe second method uses a combination-distribution to sample\nfor the space of possible treatment assignments and applies\nthe test statistic the sampled randomizations. While the\nresulting distribution is not exact, it is tractable for\nlarger problems.\n\nThe algorithm automatically chooses between the two methods\nby computing the number of possible randomizations and\ncomparing it to *test-statistic-iterations*. If the exact\ndistribution requires fewer than *test-statistic-iterations*\nthe enumeration method is used. Otherwise, it draws\n*test-statistic-iterations* total samples for the simulated\nmethod.\n\nBy default, the algorithm uses parallel computation. This is\ncontrolled by the function *test-statistic-map*, which is\nbound to pmap by default. Bind it to map to use a single\nthread for computation.\n\nArguments:\n  test-statistic\tA function that takes two vectors and summarizes\n      the difference between them\n  n\t  The number of total units in the pool\n  k\t  The number of treatment units per sample\n\nSee also:\n  combination-distribution, pdf, cdf, draw, support\n\nReferences:\n  http://en.wikipedia.org/wiki/Sampling_distribution\n  http://en.wikipedia.org/wiki/Exact_test\n  http://en.wikipedia.org/wiki/Randomization_test\n  http://en.wikipedia.org/wiki/Lady_tasting_tea\n\n",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/test-statistic-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "uniform-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L799",
   :line 799,
   :var-type "function",
   :arglists ([] [min max]),
   :doc
   "\nReturns a Uniform distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  min        (default 0)\n  max        (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/DoubleUniform.html\n  http://en.wikipedia.org/wiki/Uniform_distribution\n\nExample:\n  (pdf (uniform-distribution 1.0 10.0) 5)\n",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/uniform-distribution"}
  {:name "Beta-rec",
   :var-type "record",
   :namespace "incanter.distributions",
   :arglists nil,
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/Beta-rec",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "Binomial-rec",
   :var-type "record",
   :namespace "incanter.distributions",
   :arglists nil,
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/Binomial-rec",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "ChiSquare-rec",
   :var-type "record",
   :namespace "incanter.distributions",
   :arglists nil,
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/ChiSquare-rec",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "Combination",
   :var-type "record",
   :namespace "incanter.distributions",
   :arglists nil,
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/Combination",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "DoubleUniform-rec",
   :var-type "record",
   :namespace "incanter.distributions",
   :arglists nil,
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/DoubleUniform-rec",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "Exponential-rec",
   :var-type "record",
   :namespace "incanter.distributions",
   :arglists nil,
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/Exponential-rec",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "F",
   :var-type "record",
   :namespace "incanter.distributions",
   :arglists nil,
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/F",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "Gamma-rec",
   :var-type "record",
   :namespace "incanter.distributions",
   :arglists nil,
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/Gamma-rec",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "NegativeBinomial-rec",
   :var-type "record",
   :namespace "incanter.distributions",
   :arglists nil,
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/NegativeBinomial-rec",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "Normal-rec",
   :var-type "record",
   :namespace "incanter.distributions",
   :arglists nil,
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/Normal-rec",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "Poisson-rec",
   :var-type "record",
   :namespace "incanter.distributions",
   :arglists nil,
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/Poisson-rec",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "StudentT-rec",
   :var-type "record",
   :namespace "incanter.distributions",
   :arglists nil,
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/StudentT-rec",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "UniformInt",
   :var-type "record",
   :namespace "incanter.distributions",
   :arglists nil,
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/UniformInt",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj",
   :name "Distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8b31ba6e6ccbfaadc7f375a9851fbdceb6fc99e9/modules/incanter-core/src/incanter/distributions.clj#L31",
   :line 31,
   :var-type "protocol",
   :arglists nil,
   :doc
   "\nThe distribution protocol defines operations on probability distributions.\nDistributions may be univariate (defined over scalars) or multivariate\n(defined over vectors). Distributions may be discrete or continuous.\n\nFor a list of types that implement the protocol run (extenders Distribution).\nImplementations are provided for the various Clojure collection datatypes.\nSee the example below for using the distribution methods on these types.\n\nSee also:\n  pdf, cdf, draw, support\n\nReferences:\n  http://en.wikipedia.org/wiki/Probability_distribution\n\nExamples:\n  (support [1 3 4 2 1 3 4 2]) ; returns the set #{1 2 3 4}\n  (draw [1 3 4 2 1 3 4 2]) ; returns a value from #{1 2 3 4}\n  (pdf [2 1 2] 1) ; returns the value 1/3\n(cdf [2 1 2 3] 2) ; returns the value 3/4\n",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/Distribution"}
  {:name "cdf",
   :doc
   "\nA function of the incanter.distribution.Distribution protocol.\n\nReturns the value of the cumulative density function for the\ndistribution d at support v.\n\nSee also:\n  Distribution, pdf, draw, support\n\nReferences:\n  http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExamples:\n  (cdf [2 1 2 3] 2) ; returns the value 3/4 ",
   :var-type "function",
   :namespace "incanter.distributions",
   :arglists ([d v]),
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/cdf",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "draw",
   :doc
   "\nA function of the incanter.distribution.Distribution protocol.\n\nReturns a randomly drawn value from the support of distribution d.\n\nSee also:\n  Distribution, pdf, cdf, support\n\nExamples:\n  (draw [1 3 4 2 1 3 4 2]) ; returns a value from #{1 2 3 4}",
   :var-type "function",
   :namespace "incanter.distributions",
   :arglists ([d]),
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/draw",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "mean",
   :doc "mean",
   :var-type "function",
   :namespace "incanter.distributions",
   :arglists ([d]),
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/mean",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "pdf",
   :doc
   "\nA function of the incanter.distribution.Distribution protocol.\n\nReturns the value of the probability density/mass function for the\ndistribution d at support v.\n\nSee also:\n  Distribution, cdf, draw, support\n\nReferences:\n  http://en.wikipedia.org/wiki/Probability_density_function\n\nExamples:\n  (pdf [2 1 2] 1) ; returns the value 1/3",
   :var-type "function",
   :namespace "incanter.distributions",
   :arglists ([d v]),
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/pdf",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "support",
   :doc
   "\n**** EXPERIMENTAL ****\nA function of the incanter.distribution.Distribution protocol.\n\nReturns the support of the probability distribution d.\nFor discrete distributions, the support is a set (i.e. #{1 2 3}).\nFor continuous distributions, the support is a 2 element vector\ndescribing the range. For example, the uniform distribution over\nthe unit interval would return the vector [0 1].\n\nThis function is marked as experimental to note that the output\nformat might need to adapt to more complex support structures.\nFor example, what would best describe a mixture of continuous\ndistributions?\n\nSee also:\n  Distribution, pdf, draw, support\n\nReferences:\n  http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExamples:\n  (cdf [2 1 2 3] 2) ; returns the value 3/4 ",
   :var-type "function",
   :namespace "incanter.distributions",
   :arglists ([d]),
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/support",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "variance",
   :doc "variance",
   :var-type "function",
   :namespace "incanter.distributions",
   :arglists ([d]),
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/variance",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/886e8f3afa579f699aa329f963b615c99478de91/modules/incanter-excel/src/incanter/excel.clj",
   :name "read-xls",
   :file "modules/incanter-excel/src/incanter/excel.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/886e8f3afa579f699aa329f963b615c99478de91/modules/incanter-excel/src/incanter/excel.clj#L95",
   :line 95,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "Read an Excel file into a dataset. Note: cells containing formulas will be\nempty upon import.  Can read both older and newer Excel file formats, uses the filename suffix\nor :override-format option.\n\nOptions are:\n:sheet either a String for the tab name or an int for the sheet index -- defaults to 0\n:header-keywords convert the incoming header line to keywords -- defaults to false (no conversion)\n:override-format If nil use the filename suffix to guess the Excel file format.  If :xls\nor :xlsx override the suffix check.\n:all-sheets? true to try to read in all sheets of data (false by default).\n\n Examples:\n   (use '(incanter core io excel))\n   (view (read-xls \"http://incanter.org/data/aus-airline-passengers.xls\"))\n\n   (use '(incanter core charts excel))\n   ;; read .xls file of Australian airline passenger data from the 1950s.\n   (with-data (read-xls \"http://incanter.org/data/aus-airline-passengers.xls\")\n   (view $data)\n   ;; time-series-plot needs time in millisecs\n   ;; create a function, to-millis, to convert a sequence of Date objects\n   ;; to a sequence of milliseconds\n   (let [to-millis (fn [dates] (map #(.getTime %) dates))] \n     (view (time-series-plot (to-millis ($ :date)) ($ :passengers)))))",
   :namespace "incanter.excel",
   :wiki-url
   "http://incanter.github.com/incanter//excel-api.html#incanter.excel/read-xls"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/886e8f3afa579f699aa329f963b615c99478de91/modules/incanter-excel/src/incanter/excel.clj",
   :name "save-xls",
   :file "modules/incanter-excel/src/incanter/excel.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/886e8f3afa579f699aa329f963b615c99478de91/modules/incanter-excel/src/incanter/excel.clj#L24",
   :line 24,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "Save a dataset to an Excel file.  Can save in both older and newer\nExcel formats, uses the filename suffix or :override-format option.\n\nBy passing in a collection of datasets and names it is possible to write more than\none sheet at a time: e.g.\n  (save-xls [\"first sheet\" dataset1 \"second\" dataset2] my-file)\n\nOptions are:\n:sheet defaults to \"dataset\" if not provided.\n:use-bold defaults to true.  Set the header line in bold.\n:override-format If nil use the filename suffix to guess the Excel file format.\nIf :xls or :xlsx override the suffix check.\n\nExamples:\n  (use '(incanter core datasets excel))\n  (save-xls (get-dataset :cars) \"/tmp/cars.xls\")",
   :namespace "incanter.excel",
   :wiki-url
   "http://incanter.github.com/incanter//excel-api.html#incanter.excel/save-xls"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/20e3960ecaec11ccce900a871fba06d401fec802/modules/incanter-core/src/incanter/infix.clj",
   :name "defop",
   :file "modules/incanter-core/src/incanter/infix.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/20e3960ecaec11ccce900a871fba06d401fec802/modules/incanter-core/src/incanter/infix.clj#L41",
   :line 41,
   :var-type "function",
   :arglists ([op prec & [trans]]),
   :doc "Define operators for formula macro",
   :namespace "incanter.infix",
   :wiki-url
   "http://incanter.github.com/incanter//infix-api.html#incanter.infix/defop"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/20e3960ecaec11ccce900a871fba06d401fec802/modules/incanter-core/src/incanter/infix.clj",
   :name "formula",
   :file "modules/incanter-core/src/incanter/infix.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/20e3960ecaec11ccce900a871fba06d401fec802/modules/incanter-core/src/incanter/infix.clj#L115",
   :line 115,
   :var-type "macro",
   :arglists ([& equation]),
   :doc "Convert from infix notation to prefix notation",
   :namespace "incanter.infix",
   :wiki-url
   "http://incanter.github.com/incanter//infix-api.html#incanter.infix/formula"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/20e3960ecaec11ccce900a871fba06d401fec802/modules/incanter-core/src/incanter/infix.clj",
   :name "infix-to-prefix",
   :file "modules/incanter-core/src/incanter/infix.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/20e3960ecaec11ccce900a871fba06d401fec802/modules/incanter-core/src/incanter/infix.clj#L96",
   :line 96,
   :var-type "function",
   :arglists ([col]),
   :doc "Convert from infix notation to prefix notation",
   :namespace "incanter.infix",
   :wiki-url
   "http://incanter.github.com/incanter//infix-api.html#incanter.infix/infix-to-prefix"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/e5545c0d55757c7302712adc031ec8c5023bc1e9/modules/incanter-core/src/incanter/interpolation.clj",
   :name "interpolate",
   :file "modules/incanter-core/src/incanter/interpolation.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/e5545c0d55757c7302712adc031ec8c5023bc1e9/modules/incanter-core/src/incanter/interpolation.clj#L25",
   :line 25,
   :var-type "function",
   :arglists ([points type & options]),
   :doc
   "\nBuilds a function that interpolates given collection of points.\nhttp://en.wikipedia.org/wiki/Interpolation\nhttp://en.wikipedia.org/wiki/Linear_least_squares_(mathematics)\n\nArguments:\n  points -- collection of points. Each point is a collection [x y].\n  type -- type of interpolation - :linear, :polynomial, :cubic, :cubic-hermite, :linear-least-squares.\n          For most cases you should use :cubic or :cubic-hermite - they usually give best results.\n          Check http://en.wikipedia.org/wiki/Interpolation for brief explanation of each kind.\n\nOptions:\n  :boundaries - valid only for :cubic interpolation. Defines boundary condition for cubic spline.\n                Possible values - :natural and :closed.\n                Let's S - spline, a- leftmost point, b- rightmost point.\n                :natural - S''(a) = S''(b) = 0\n                :closed - S'(a) = S'(b), S''(a) = S''(b) . This type of boundary conditions may be\n                useful if you want to get periodic or closed curve.\n                Default value is :natural\n\n  :derivatives - valid only for :cubic-hermite. Defines first derivatives for spline.\n                 If not specified derivatives will be approximated from points.\n\nOptions for linear least squares:\n  :basis - type of basis functions. There are 2 built-in bases: chebushev polynomials and b-splines (:polynomial and :b-spline).\n           You also can supply your own basis. It should be a function that takes x and returns collection [f1(x) f2(x) ... fn(x)].\n           Example of custom basis of 2 functions (1 and  x*x): (interpolate :linear-least-squares :basis (fn [x] [1 (* x x)]))\n           Default value is :chebyshev\n\n  :n - number of functions in basis if you use built-in basis.\n       Note that if n is greater that number of points then you might get singular matrix and exception.\n       Default value is 4.\n\n  :degree - degree of b-spline if you use :b-spline basis.\n            Default value is 3.\n\nExamples:\n\n  (def points [[0 0] [1 5] [2 0] [3 5]])\n  (def linear (interpolate points :linear))\n  (linear 0) => 0.0\n  (linear 1) => 5.0\n  (linear 1.5) => 2.5\n\n  ; Specify boundary conditions\n  (interpolate points :cubic :boundaries :closed)\n",
   :namespace "incanter.interpolation",
   :wiki-url
   "http://incanter.github.com/incanter//interpolation-api.html#incanter.interpolation/interpolate"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/e5545c0d55757c7302712adc031ec8c5023bc1e9/modules/incanter-core/src/incanter/interpolation.clj",
   :name "interpolate-grid",
   :file "modules/incanter-core/src/incanter/interpolation.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/e5545c0d55757c7302712adc031ec8c5023bc1e9/modules/incanter-core/src/incanter/interpolation.clj#L185",
   :line 185,
   :var-type "function",
   :arglists ([grid type & options]),
   :doc
   "\nInterpolates 2-dimensional grid. Returns function f that takes 2 arguments: x and y.\nBy default function interpolates on [0,1]x[0,1].\n\nArguments:\n  grid -- collection of collection of numbers to be interpolated.\n          If you need to interpolate vectors - interpolate each component by separate interpolator.\n  type -- type of interpolation. Available: :bilinear, :polynomial, :bicubic, :bicubic-hermite, :b-surface, :linear-least-squares\n\nCommon options:\n  :x-range, :y-range - range of possible x and y.\n                       By default :x-range = [0 1] and :y-range = [0 1]\n                       :b-surface ignores this option and always uses [0, 1] x [0, 1]\n\n  :xs, :ys - coordinates of grid points. Size of xs and ys must be consistent with grid size.\n             If you have grid 4x3 then xs must have size 3 and ys - 4.\n\n  Note that (:x-range, :y-range) and (:xs, :ys) both do same job - they specify coordinates of points in grid.\n  So you should use only one of them or none at all.\n\nType specific options:\n  :boundaries - valid only for :cubic interpolation. Defines boundary condition for bicubic spline.\n                Possible values - :natural and :closed.\n                Default - :natural. Check documentation of 'interpolate' method for more explanation.\n\n  :degree - valid only for :b-spline. Degree of a B-spline. Default 3. Degree will be reduced if there are too few points.\n\n  :basis - defines basis for :linear-least-squares. It has 1 predefined basis :polynomial. :polynomial basis\n           contains functions: (1, x, y, x^2, xy, y^2, x^3, ...)\n           You can specify how many functions basis contains by using :n option.\n           You can also specify custom basis. Custom basis is a function that takes 2 arguments - x and y, and returns collection of values.\n           Example: basis that contains only 2-degree polynomials: (fn [x y] [(* x x) (* x y) (* y y)])\n\n  :n - defines how many functions polynomial contains. Example: 1 - basis is (1), 3 - basis is (1, x, y), 5 - basis is (1, x, y, x^2, x*y)\n\nExamples:\n\n(def grid [[0 1 0]\n           [1 2 1]\n           [0 1 0]])\n(def interpolator (interpolate-grid grid :bilinear))\n(interpolator   0   0) => 0\n(interpolator 1/2 1/2) => 2\n(interpolator 1/2   1) => 1\n(interpolator 1/4   0) => 1/2\n\n\n; Specify x-range and y-range\n(def interpolator (interpolate-grid grid :bilinear :x-range [0 10] :y-range [-5 5]))\n(interpolator  0  -5) => 0\n(interpolator  5   0) => 2\n(interpolator 10   5) => 0\n\n; Specify xs and ys\n(def interpolator (interpolate-grid grid :bilinear :xs [0 1 2] :ys [0 10 100]))\n(interpolator  0   0) => 0\n(interpolator  1  10) => 2\n(interpolator  2 100) => 0",
   :namespace "incanter.interpolation",
   :wiki-url
   "http://incanter.github.com/incanter//interpolation-api.html#incanter.interpolation/interpolate-grid"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/e5545c0d55757c7302712adc031ec8c5023bc1e9/modules/incanter-core/src/incanter/interpolation.clj",
   :name "interpolate-parametric",
   :file "modules/incanter-core/src/incanter/interpolation.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/e5545c0d55757c7302712adc031ec8c5023bc1e9/modules/incanter-core/src/incanter/interpolation.clj#L87",
   :line 87,
   :var-type "function",
   :arglists ([points type & options]),
   :doc
   "\nBuilds a parametric function that interpolates given collection of points.\nParametric function represents a curve that go through all points. By default domain is [0, 1].\n\nArguments:\n  points -- collection of points. Each point either a single value or collection of values.\n  type -- type of interpolation - :linear, :polynomial, :cubic, :cubic-hermite, :b-spline, :linear-least-squares.\n\nOptions:\n  :range -- defines range for parameter t.\n            Default value is [0, 1]. f(0) = points[0], f(1) = points[n].\n\n  :boundaries -- valid only for :cubic interpolation.\n                 Defines boundary condition for cubic spline. Possible values - :natural and :closed.\n                 Let's S - spline, a- leftmost point, b- rightmost point.\n                 :natural - S''(a) = S''(b) = 0\n                 :closed - S'(a) = S'(b), S''(a) = S''(b) . This type of boundary conditions may be useful\n                 if you want to get periodic or closed curve\n\n                 Default value is :natural\n\n  :derivatives - valid only for :cubic-hermite. Defines first derivatives for spline.\n                 If not specified derivatives will be approximated from points.\n\n  :degree - valid only for :b-spline. Degree of a B-spline. Default 3. Degree will be reduced if there are too few points.\n\nOptions for linear least squares:\n  See documentation for interpolate function.\n\nExamples:\n\n(def points [[0 0]\n             [0 1]\n             [1 1]\n             [3 5]\n             [2 9]])\n(def cubic (interpolate-parametric points :cubic))\n(cubic 0) => [0.0 0.0]\n(cubic 1) => [2.0 9.0]\n(cubic 0.5) => [1.0 1.0]\n\n; Specify custom :range\n(def cubic (interpolate-parametric points :cubic :range [-10 10]))\n(cubic -10) => [0.0 0.0]\n(cubic 0) => [1.0 1.0]\n",
   :namespace "incanter.interpolation",
   :wiki-url
   "http://incanter.github.com/incanter//interpolation-api.html#incanter.interpolation/interpolate-parametric"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/9585202af06418742683817d4656cd52d5d00a6e/modules/incanter-io/src/incanter/io.clj",
   :name "read-dataset",
   :file "modules/incanter-io/src/incanter/io.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/9585202af06418742683817d4656cd52d5d00a6e/modules/incanter-io/src/incanter/io.clj#L41",
   :line 41,
   :var-type "function",
   :arglists
   ([filename
     &
     {:keys
      [delim
       keyword-headers
       quote
       skip
       header
       compress-delim
       empty-field-value
       comment-char],
      :or
      {delim \,,
       quote \",
       skip 0,
       header false,
       keyword-headers true}}]),
   :doc
   "\nReturns a dataset read from a file or a URL.\n\nOptions:\n  :delim (default \\,), other options (\\tab \\space \\|  etc)\n  :quote (default \\\") character used for quoting strings\n  :skip (default 0) the number of lines to skip at the top of the file.\n  :header (default false) indicates the file has a header line\n  :compress-delim (default true if delim = \\space, false otherwise) means\n                  compress multiple adjacent delimiters into a single delimiter.\n  :empty-field-value (default nil) indicates the interpretation of an empty field.\n  :comment-char (default nil) skip commented lines (\"#\", \"%\", \";\", etc)\n",
   :namespace "incanter.io",
   :wiki-url
   "http://incanter.github.com/incanter//io-api.html#incanter.io/read-dataset"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/7f7dc7cffabbdbfcdec3d6aa06631d24b0f05148/modules/incanter-latex/src/incanter/latex.clj",
   :name "add-latex",
   :file "modules/incanter-latex/src/incanter/latex.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/7f7dc7cffabbdbfcdec3d6aa06631d24b0f05148/modules/incanter-latex/src/incanter/latex.clj#L79",
   :line 79,
   :var-type "function",
   :arglists
   ([chart x y latex-str & {:keys [color], :or {color darkGray}}]),
   :doc
   "\nAdds an LaTeX equation annotation to the chart at the given x,y coordinates.\n\nArguments:\n  chart -- the chart to add the polygon to.\n  x, y -- the coordinates to place the image\n  latex-str -- a string of latex code\n\n\nOptions:\n  :color (default java.awt.Color/darkGray) -- the text color\n\n\nExamples:\n  (use '(incanter core charts stats latex))\n\n    (doto (function-plot pdf-normal -3 3)\n      (add-latex 0 0.1 \"f(x)=\\\\frac{1}{\\\\sqrt{2\\\\pi \\\\sigma^2}} e^{\\\\frac{-(x - \\\\mu)^2}{2 \\\\sigma^2}}\")\n      view)\n",
   :namespace "incanter.latex",
   :wiki-url
   "http://incanter.github.com/incanter//latex-api.html#incanter.latex/add-latex"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/7f7dc7cffabbdbfcdec3d6aa06631d24b0f05148/modules/incanter-latex/src/incanter/latex.clj",
   :name "add-latex-subtitle",
   :file "modules/incanter-latex/src/incanter/latex.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/7f7dc7cffabbdbfcdec3d6aa06631d24b0f05148/modules/incanter-latex/src/incanter/latex.clj#L59",
   :line 59,
   :var-type "function",
   :arglists
   ([chart latex-str & {:keys [color], :or {color darkGray}}]),
   :doc
   "\nAdds the given LaTeX equation as a subtitle to the chart.\n\nOptions:\n  :color (default java.awt.Color/darkGray) -- the text color\n\n\nExamples:\n  (use '(incanter core charts stats latex))\n\n  (doto (function-plot pdf-normal -3 3)\n    (add-latex-subtitle \"f(x)=\\\\frac{1}{\\\\sqrt{2\\\\pi \\\\sigma^2}} e^{\\\\frac{-(x - \\\\mu)^2}{2 \\\\sigma^2}}\")\n    view)\n",
   :namespace "incanter.latex",
   :wiki-url
   "http://incanter.github.com/incanter//latex-api.html#incanter.latex/add-latex-subtitle"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/7f7dc7cffabbdbfcdec3d6aa06631d24b0f05148/modules/incanter-latex/src/incanter/latex.clj",
   :name "latex",
   :file "modules/incanter-latex/src/incanter/latex.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/7f7dc7cffabbdbfcdec3d6aa06631d24b0f05148/modules/incanter-latex/src/incanter/latex.clj#L12",
   :line 12,
   :var-type "function",
   :arglists
   ([latex-txt
     &
     {:keys [color background border],
      :or {color black, background white, border [5 5 5 5]}}]),
   :doc
   "\nReturns the given LaTeX equation rendered as an java.awt.Image.\n\nOptions:\n  :color (default java.awt.Color/black) -- the text color\n  :background (default java.awt.Clolor/white) -- the background color\n  :border (default [5 5 5 5]) -- image border\n\nExamples:\n  (use '(incanter io core charts stats latex))\n\n  (def latex-img (latex \"\\\\frac{(a+b)^2} {(a-b)^2}\"))\n  (save latex-img \"/tmp/latex-example1.png\")\n  (view \"file:///tmp/latex-example1.png\")\n\n  (view (latex \"f(x)=\\\\frac {1} {\\\\sqrt {2\\\\pi \\\\sigma ^2}} e^{\\\\frac {-(x - \\\\mu)^2}{2 \\\\sigma ^2}}\"))\n\n  (view (latex \"\\\\begin{pmatrix}\n                 a & b & c \\\\\\\\\n                 d & e & f \\\\\\\\\n                 g & h & i\n                 \\\\end{pmatrix}\"))\n",
   :namespace "incanter.latex",
   :wiki-url
   "http://incanter.github.com/incanter//latex-api.html#incanter.latex/latex"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/7f7dc7cffabbdbfcdec3d6aa06631d24b0f05148/modules/incanter-latex/src/incanter/latex.clj",
   :name "to-latex",
   :file "modules/incanter-latex/src/incanter/latex.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/7f7dc7cffabbdbfcdec3d6aa06631d24b0f05148/modules/incanter-latex/src/incanter/latex.clj#L106",
   :line 106,
   :var-type "function",
   :arglists
   ([mx
     &
     {:keys
      [mxtype
       preamble
       col-just
       row-names-tex-cmd
       hline
       table-newline
       table-newline-suppress-last
       newline],
      :or
      {mxtype "pmatrix",
       preamble "",
       col-just [],
       row-names-tex-cmd [""],
       hline false,
       table-newline "\\\\",
       table-newline-suppress-last false,
       newline ""}}]),
   :doc
   "\nConvert an Incanter Matrix into a string of LaTeX commands to render it.\n\nOptions:\n  :mxtype (default pmatrix) -- the type of matrix to output, see LaTeX documentation for other options.\nExample:\n  (use '(incanter core latex))\n  (view (latex (to-latex (matrix [[1 0][0 1]]))))\n",
   :namespace "incanter.latex",
   :wiki-url
   "http://incanter.github.com/incanter//latex-api.html#incanter.latex/to-latex"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/129dadf7b50f51175cb820c5f3b927e8b8095d91/modules/incanter-mongodb/src/incanter/mongodb.clj",
   :name "fetch-dataset",
   :file "modules/incanter-mongodb/src/incanter/mongodb.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/129dadf7b50f51175cb820c5f3b927e8b8095d91/modules/incanter-mongodb/src/incanter/mongodb.clj#L72",
   :line 72,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "\nQueries a MongoDB database, accepting the same arguments as\nsomnium.congomongo/fetch, but returning an Incanter dataset instead\nof a sequence of maps.\n\nExamples:\n\n (use '(incanter core datasets mongodb))\n (use 'somnium.congomongo)\n\n ;; first load some sample data\n (def data (get-dataset :airline-passengers))\n (view data)\n\n ;; a MongoDB server must be running on the localhost on the default port\n ;; for the following steps.\n\n (mongo! :db \"mydb\")\n (mass-insert! :airline-data (:rows data))\n\n ;; and then retrieve it\n ;; notice that the retrieved data set has two additional columns,  :_id :_ns\n (view (fetch-dataset :airline-data))\n\n",
   :namespace "incanter.mongodb",
   :wiki-url
   "http://incanter.github.com/incanter//mongodb-api.html#incanter.mongodb/fetch-dataset"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/129dadf7b50f51175cb820c5f3b927e8b8095d91/modules/incanter-mongodb/src/incanter/mongodb.clj",
   :name "insert-dataset",
   :file "modules/incanter-mongodb/src/incanter/mongodb.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/129dadf7b50f51175cb820c5f3b927e8b8095d91/modules/incanter-mongodb/src/incanter/mongodb.clj#L105",
   :line 105,
   :var-type "function",
   :arglists ([mongodb-coll dataset]),
   :doc
   "\nInserts the rows of the Incanter dataset into the given MongoDB collection.\n\nExamples:\n\n  (use '(incanter core datasets mongodb))\n  (use 'somnium.congomongo)\n\n  (def data (get-dataset :airline-passengers))\n  (view data)\n\n  ;; a MongoDB server must be running on the localhost on the default port\n  ;; for the following steps.\n\n  (mongo! :db \"mydb\")\n  (mass-insert! :airline-data (:rows data))\n\n  ;; notice that the retrieved data set has two additional columns,  :_id :_ns\n  (view (fetch-dataset :airline-data))\n",
   :namespace "incanter.mongodb",
   :wiki-url
   "http://incanter.github.com/incanter//mongodb-api.html#incanter.mongodb/insert-dataset"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/7f7dc7cffabbdbfcdec3d6aa06631d24b0f05148/modules/incanter-core/src/incanter/optimize.clj",
   :name "derivative",
   :file "modules/incanter-core/src/incanter/optimize.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/7f7dc7cffabbdbfcdec3d6aa06631d24b0f05148/modules/incanter-core/src/incanter/optimize.clj#L68",
   :line 68,
   :var-type "function",
   :arglists ([f & {:keys [dx], :or {dx 1.0E-4}}]),
   :doc
   "\nReturns a function that approximates the derivative of the given function.\n\nOptions:\n  :dx (default 0.0001)\n\nExamples:\n\n  (use '(incanter core optimize charts stats))\n  (defn cube [x] (* x x x))\n  (def cube-deriv (derivative cube))\n  (cube-deriv 2) ; value: 12.000600010022566\n  (cube-deriv 3) ; value: 27.00090001006572\n  (cube-deriv 4) ; value: 48.00120000993502\n\n  (def x (range -3 3 0.1))\n  (def plot (xy-plot x (map cube x)))\n  (view plot)\n  (add-lines plot x (map cube-deriv x))\n\n  ;; get the second derivative function\n  (def cube-deriv2 (derivative cube-deriv))\n  (add-lines plot x (map cube-deriv2 x))\n\n  ;; plot the normal pdf and its derivatives\n  (def plot (xy-plot x (pdf-normal x)))\n  (view plot)\n  (def pdf-deriv (derivative pdf-normal))\n  (add-lines plot x (pdf-deriv x))\n\n  ;; plot the second derivative function\n  (def pdf-deriv2 (derivative pdf-deriv))\n  (add-lines plot x (pdf-deriv2 x))\n",
   :namespace "incanter.optimize",
   :wiki-url
   "http://incanter.github.com/incanter//optimize-api.html#incanter.optimize/derivative"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/7f7dc7cffabbdbfcdec3d6aa06631d24b0f05148/modules/incanter-core/src/incanter/optimize.clj",
   :name "gradient",
   :file "modules/incanter-core/src/incanter/optimize.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/7f7dc7cffabbdbfcdec3d6aa06631d24b0f05148/modules/incanter-core/src/incanter/optimize.clj#L234",
   :line 234,
   :var-type "function",
   :arglists ([f start & {:keys [tol dx], :or {tol 1.0E-4}}]),
   :doc
   "\nReturns a function that calculates a 5-point approximation to\nthe gradient of the given function. The vector of start values are\nused to determine the number of parameters required by the function, and\nto scale the step-size. The generated function accepts a vector of\nparameter values and a vector of x data points and returns a matrix,\nwhere each row is the gradient evaluated at the corresponding x value.\n\nExamples:\n\n  (use '(incanter core optimize datasets charts))\n  (defn f [theta x]\n    (+ (nth theta 0)\n          (div (* x (- (nth theta 1) (nth theta 0)))\n               (+ (nth theta 2) x))))\n\n  (def start [20 200 100])\n  (def data (get-dataset :thurstone))\n  (def x (sel data :cols 1))\n  (def y (sel data :cols 0))\n  ;; view the data\n  (view (scatter-plot x y))\n\n  (def grad (gradient f start))\n  (time (doall (grad start x)))\n",
   :namespace "incanter.optimize",
   :wiki-url
   "http://incanter.github.com/incanter//optimize-api.html#incanter.optimize/gradient"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/7f7dc7cffabbdbfcdec3d6aa06631d24b0f05148/modules/incanter-core/src/incanter/optimize.clj",
   :name "hessian",
   :file "modules/incanter-core/src/incanter/optimize.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/7f7dc7cffabbdbfcdec3d6aa06631d24b0f05148/modules/incanter-core/src/incanter/optimize.clj#L280",
   :line 280,
   :var-type "function",
   :arglists ([f start & {:keys [tol dx], :or {tol 1.0E-4}}]),
   :doc
   "\nReturns a function that calculates an approximation to the Hessian matrix\nof the given function. The vector of start values are used to determine\nthe number of parameters required by the function, and to scale the\nstep-size. The generated function accepts a vector of\nparameter values and a vector of x data points and returns a matrix,\nwhere each row with p*(p+1)/2 columns, one for each unique entry in\nthe Hessian evaluated at the corresponding x value.\n\nExamples:\n\n  (use '(incanter core optimize datasets charts))\n  (defn f [theta x]\n    (+ (nth theta 0)\n          (div (* x (- (nth theta 1) (nth theta 0)))\n               (+ (nth theta 2) x))))\n\n  (def start [20 200 100])\n  (def data (get-dataset :thurstone))\n  (def x (sel data :cols 1))\n  (def y (sel data :cols 0))\n  ;; view the data\n  (view (scatter-plot x y))\n\n  (time (def hess (hessian f start)))\n  (time (doall (hess start x)))\n",
   :namespace "incanter.optimize",
   :wiki-url
   "http://incanter.github.com/incanter//optimize-api.html#incanter.optimize/hessian"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/7f7dc7cffabbdbfcdec3d6aa06631d24b0f05148/modules/incanter-core/src/incanter/optimize.clj",
   :name "integrate",
   :file "modules/incanter-core/src/incanter/optimize.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/7f7dc7cffabbdbfcdec3d6aa06631d24b0f05148/modules/incanter-core/src/incanter/optimize.clj#L26",
   :line 26,
   :var-type "function",
   :arglists ([f a b]),
   :doc
   "\nIntegrate a function f from a to b\n\nExamples:\n  (use '(incanter optimize))\n\n  (defn f1 [x] 1)\n  (defn f2 [x] (Math/pow x 2))\n  (defn f3 [x] (* x (Math/exp (Math/pow x 2))))\n\n  (integrate f1 0 5)\n  (integrate f2 0 1)\n  (integrate f3 0 1)\n\n  ;; normal distribution\n  (def std 1)\n  (def mu 0)\n  (defn normal [x]\n    (/ 1\n      (* (* std (Math/sqrt (* 2 Math/PI)))\n        (Math/exp (/ (Math/pow (- (- x mu)) 2)\n        (* 2 (Math/pow std 2)))))))\n\n  (integrate normal 1.96 10)\n\n\nReference:\n  http://jng.imagine27.com/articles/2009-04-09-161839_integral_calculus_in_lambda_calculus_lisp.html\n  http://iam.elbenshira.com/archives/151_integral-calculus-in-haskell/\n",
   :namespace "incanter.optimize",
   :wiki-url
   "http://incanter.github.com/incanter//optimize-api.html#incanter.optimize/integrate"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/7f7dc7cffabbdbfcdec3d6aa06631d24b0f05148/modules/incanter-core/src/incanter/optimize.clj",
   :name "non-linear-model",
   :file "modules/incanter-core/src/incanter/optimize.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/7f7dc7cffabbdbfcdec3d6aa06631d24b0f05148/modules/incanter-core/src/incanter/optimize.clj#L552",
   :line 552,
   :var-type "function",
   :arglists
   ([f
     y
     x
     start
     &
     {:keys [max-iter tol method],
      :or {max-iter 200, tol 1.0E-5, method :gauss-newton}}]),
   :doc
   "\nDetermine the nonlinear least-squares estimates of the\nparameters of a nonlinear model.\nBased on R's nls (non-linear least squares) function.\n\nArguments:\n  f -- model function, takes two arguments the first a list of parameters\n       that are to be estimated, and an x value.\n  y -- sequence of dependent data\n  x -- sequence of independent data\n  start -- start values for the parameters to be estimated\n\nOptions:\n  :method (default :gauss-newton) other option :newton-raphson\n  :tol (default 1E-5)\n  :max-iter (default 200)\n\nReturns: a hash-map containing the following fields:\n  :method -- the method used\n  :coefs  -- the parameter estimates\n  :gradient  -- the estimated gradient\n  :hessian -- the estimated hessian, if available\n  :iterations -- the number of iterations performed\n  :fitted -- the fitted values of y (i.e. y-hat)\n  :rss -- the residual sum-of-squares\n  :x -- the independent data values\n  :y -- the dependent data values\n\n\nExamples:\n\n  ;; example 1\n  (use '(incanter core optimize datasets charts))\n  ;; define the Michaelis-Menton model function\n  ;; y = a + (b - a)*x/(c + x)\n  (defn f [theta x]\n    (let [[a b c] theta]\n      (plus a (div (mult x (minus b a)) (plus c x)))))\n\n  (def start [20 200 100])\n  (def data (get-dataset :thurstone))\n  (def x (sel data :cols 1))\n  (def y (sel data :cols 0))\n  ;; view the data\n  (def plot (scatter-plot x y))\n  (view plot)\n\n  (def nlm (non-linear-model f y x start))\n  (add-lines plot x (:fitted nlm))\n\n\n  ;; example 2\n  (use '(incanter core optimize datasets charts))\n  ;; Chwirut data set from NIST\n  ;; http://www.itl.nist.gov/div898/strd/nls/data/LINKS/DATA/Chwirut1.dat\n  (def data (get-dataset :chwirut))\n  (def x (sel data :cols 1))\n  (def y (sel data :cols 0))\n\n  ;; define model function: y = exp(-b1*x)/(b2+b3*x) + e\n  (defn f [theta x]\n    (let [[b1 b2 b3] theta]\n      (div (exp (mult (minus b1) x)) (plus b2 (mult b3 x)))))\n\n  (def plot (scatter-plot x y :legend true))\n  (view plot)\n\n  ;; the newton-raphson algorithm fails to converge to the correct solution\n  ;; using first set of start values from NIST, but the default gauss-newton\n  ;; algorithm converges to the correct solution.\n\n  (def start1 [0.1 0.01 0.02])\n  (add-lines plot x (f start1 x))\n  (def nlm1 (non-linear-model f y x start1))\n  (add-lines plot x (:fitted nlm1))\n\n  ;; both algorithms converges with second set of start values from NIST\n  (def start2 [0.15 0.008 0.010])\n  (add-lines plot x (f start2 x))\n  (def nlm2 (non-linear-model f y x start2))\n  (add-lines plot x (:fitted nlm2))\n",
   :namespace "incanter.optimize",
   :wiki-url
   "http://incanter.github.com/incanter//optimize-api.html#incanter.optimize/non-linear-model"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/15a5e6a3a04731f87eff6129fd68bb2503840f78/modules/incanter-pdf/src/incanter/pdf.clj",
   :name "save-pdf",
   :file "modules/incanter-pdf/src/incanter/pdf.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/15a5e6a3a04731f87eff6129fd68bb2503840f78/modules/incanter-pdf/src/incanter/pdf.clj#L16",
   :line 16,
   :var-type "function",
   :arglists
   ([chart
     filename
     &
     {:keys [width height], :or {width 500, height 400}}]),
   :doc
   "\nSave a chart object as a pdf document.\n\nArguments:\n  chart\n  filename\n\nOptions:\n  :width (default 500)\n  :height (defualt 400)\n\nExamples:\n\n  (use '(incanter core charts pdf))\n  (save-pdf (function-plot sin -4 4) \"./pdf-chart.pdf\")\n",
   :namespace "incanter.pdf",
   :wiki-url
   "http://incanter.github.com/incanter//pdf-api.html#incanter.pdf/save-pdf"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/feaedb2d9d9bdf978a41f77f50a5cb1f31ff09f0/modules/incanter-core/src/incanter/som.clj",
   :name "som-batch-train",
   :file "modules/incanter-core/src/incanter/som.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/feaedb2d9d9bdf978a41f77f50a5cb1f31ff09f0/modules/incanter-core/src/incanter/som.clj#L135",
   :line 135,
   :var-type "function",
   :arglists
   ([data
     &
     {:keys [alpha beta cycles], :or {alpha 0.5, beta 3, cycles 10}}]),
   :doc
   "\nPerforms BL-SOM (batch-learning self organizing map) learning on\nthe given data, returning a hashmap containing resulting BL-SOM\nvalues.\n\n\nArguments:\n  data -- data matrix\n\nOptions:\n  :cycles -- number of cycles of learning\n  :alpha -- initial value of alpha learning parameter\n  :beta -- initial value of beta learning parameter\n\n\nReturns: A hashmap containing the following fields:\n\n  :fit -- array of fitness values for each cycle of SOM learning\n  :weights -- hashmap of weight vectors, keyed by lattice indices\n  :sets -- hashmap mapping data elements to lattice nodes\n           (key lattice index) (value list of row indices from data)\n  :dims -- dimensions of SOM lattice\n  :data-means -- column means of input data matrix\n\n\nExamples:\n\n  (use '(incanter core som stats charts datasets))\n  (def data (to-matrix (sel (get-dataset :iris)\n                         :cols [\"Sepal.Length\" \"Sepal.Width\" \"Petal.Length\" \"Petal.Width\"])))\n\n  (def som (som-batch-train data :cycles 10 :alpha 0.5 :beta 3))\n\n  ;; plot the fitness for each cycle of training\n  (view (xy-plot (range (count (:fit som))) (:fit som)))\n  ;; view indices of data items in each cell\n  (:sets som)\n  ;; view the species in each cell\n  (doseq [rws (vals (:sets som))]\n    (println (sel (get-dataset :iris) :cols \"Species\" :rows rws) \\newline))\n\n  ;; plot the means of the data vectors in each cell/cluster\n  (def cell-means (map #(map mean (trans (sel data :rows ((:sets som) %)))) (keys (:sets som))))\n  (def x (range (ncol data)))\n  (doto (xy-plot x (first cell-means))\n        view\n        (add-lines x (nth cell-means 1))\n        (add-lines x (nth cell-means 2)))\n\n\nReferences:\n\n  http://en.wikipedia.org/wiki/Self-organizing_map\n",
   :namespace "incanter.som",
   :wiki-url
   "http://incanter.github.com/incanter//som-api.html#incanter.som/som-batch-train"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/7774fbe6ab83158595c14eb0e436f7c096f7b442/modules/incanter-sql/src/incanter/sql.clj",
   :name "read-dataset",
   :file "modules/incanter-sql/src/incanter/sql.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/7774fbe6ab83158595c14eb0e436f7c096f7b442/modules/incanter-sql/src/incanter/sql.clj#L32",
   :line 32,
   :var-type "function",
   :arglists ([cql-statement]),
   :doc "Lazily read a dataset for the given ClojureQL query.",
   :namespace "incanter.sql",
   :wiki-url
   "http://incanter.github.com/incanter//sql-api.html#incanter.sql/read-dataset"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "auto-correlation",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1586",
   :line 1586,
   :var-type "function",
   :arglists ([x lag] [x lag mean variance]),
   :doc
   "\nReturns the auto-correlation of x with given lag, mean, and variance.\nIf no mean or variance is provided, the they are calculated from x.\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/auto-correlation"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "benford-test",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2563",
   :line 2563,
   :var-type "function",
   :arglists ([coll]),
   :doc
   "\nPerforms Benford's Law test using chisq-test.\n\nArgument:\ncoll: -- a sequence of numbers\n\nReturns:\n  :X-sq -- the Pearson X-squared test statistics\n  :p-value -- the p-value for the test statistic\n  :df -- the degress of freedom\n\nReference:\nhttp://data-sorcery.org/2009/06/21/chi-square-goodness-of-fit/\nhttp://en.wikipedia.org/wiki/Benford%27s_Law\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/benford-test"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "bootstrap",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1740",
   :line 1740,
   :var-type "function",
   :arglists
   ([data
     statistic
     &
     {:keys [size replacement smooth? smooth-sd],
      :or
      {replacement true,
       smooth? false,
       smooth-sd (/ (sqrt (count data)))}}]),
   :doc
   "\nReturns a bootstrap sample of the given statistic on the given data.\n\nArguments:\n  data -- vector of data to resample from\n  statistic -- a function that returns a value given a vector of data\n\nOptions:\n  :size -- the number of bootstrap samples to return\n  :smooth -- (default false) smoothing option\n  :smooth-sd -- (default (/ (sqrt (count data)))) determines the standard\n                deviation of the noise to use for smoothing\n  :replacement -- (default true) determines if sampling of the data\n                  should be done with replacement\n\n\nReferences:\n  1. Clifford E. Lunneborg, Data Analysis by Resampling Concepts and Applications, 2000, pages 105-117\n  2. http://en.wikipedia.org/wiki/Bootstrapping_(statistics)\n\n\nExamples:\n\n  ;; example from Data Analysis by Resampling Concepts and Applications\n  ;; Clifford E. Lunneborg (pages 119-122)\n\n  (use '(incanter core stats charts))\n\n  ;; weights (in grams) of 50 randoincanter. sampled bags of preztels\n  (def weights [464 447 446 454 450 457 450 442\n                433 452 449 454 450 438 448 449\n                457 451 456 452 450 463 464 453\n                452 447 439 449 468 443 433 460\n                452 447 447 446 450 459 466 433\n                445 453 454 446 464 450 456 456\n                447 469])\n\n  ;; calculate the sample median, 450\n  (median weights)\n\n  ;; generate bootstrap sample\n  (def t* (bootstrap weights median :size 2000))\n\n  ;; view histogram of bootstrap histogram\n  (view (histogram t*))\n\n  ;; calculate the mean of the bootstrap median ~ 450.644\n  (mean t*)\n\n  ;; calculate the standard error ~ 1.083\n  (def se (sd t*))\n\n  ;; 90% standard normal CI ~ (448.219 451.781)\n  (plus (median weights) (mult (quantile-normal [0.05 0.95]) se))\n\n  ;; 90% symmetric percentile CI ~ (449.0 452.5)\n  (quantile t* :probs [0.05 0.95])\n\n\n  ;; 90% non-symmetric percentile CI ~ (447.5 451.0)\n  (minus (* 2 (median weights)) (quantile t* :probs [0.95 0.05]))\n\n  ;; calculate bias\n  (- (mean t*) (median weights)) ;; ~ 0.644\n\n  ;; example with smoothing\n  ;; Newcomb's speed of light data\n\n  (use '(incanter core stats charts))\n\n  ;; A numeric vector giving the Third Series of measurements of the\n  ;; passage time of light recorded by Newcomb in 1882. The given\n  ;; values divided by 1000 plus 24 give the time in millionths of a\n  ;; second for light to traverse a known distance. The 'true' value is\n  ;; now considered to be 33.02.\n\n  (def speed-of-light [28 -44  29  30  24  28  37  32  36  27  26  28  29\n                       26  27  22  23  20  25 25  36  23  31  32  24  27\n                       33  16  24  29  36  21  28  26  27  27  32  25 28\n                       24  40  21  31  32  28  26  30  27  26  24  32  29\n                       34  -2  25  19  36 29  30  22  28  33  39  25  16  23])\n\n  ;; view histogram of data to see outlier observations\n  (view (histogram speed-of-light :nbins 30))\n\n  (def samp (bootstrap speed-of-light median :size 10000))\n  (view (histogram samp :density true :nbins 30))\n  (mean samp)\n  (quantile samp :probs [0.025 0.975])\n\n  (def smooth-samp (bootstrap speed-of-light median :size 10000 :smooth true))\n  (view (histogram smooth-samp :density true :nbins 30))\n  (mean smooth-samp)\n  (quantile smooth-samp :probs [0.025 0.975])\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/bootstrap"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "category-col-summarizer",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2611",
   :line 2611,
   :var-type "function",
   :arglists ([col ds]),
   :doc
   "Returns a summarizer function which takes a category column and returns a list of the top 5 columns by volume, and a\ncount of remaining rows",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/category-col-summarizer"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-beta",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L477",
   :line 477,
   :var-type "function",
   :arglists
   ([x
     &
     {:keys [alpha beta lower-tail?],
      :or {alpha 1, beta 1, lower-tail? false}}]),
   :doc
   "\nReturns the Beta cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's pbeta function.\n\nOptions:\n  :alpha (default 1)\n  :beta (default 1)\n  :lower-tail (default true)\n\nSee also:\n    pdf-beta and sample-beta\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Beta.html\n    http://en.wikipedia.org/wiki/Beta_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-beta 0.5 :alpha 1 :beta 2)\n    (cdf-beta 0.5 :alpha 1 :beta 2 :lower-tail false)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-beta"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-binomial",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1151",
   :line 1151,
   :var-type "function",
   :arglists
   ([x
     &
     {:keys [size prob lower-tail?],
      :or {size 1, prob 1/2, lower-tail? true}}]),
   :doc
   "\nReturns the Binomial cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Equivalent to R's pbinom.\n\nOptions:\n  :size (default 1)\n  :prob (default 1/2)\n  :lower-tail (default true)\n\nSee also:\n    pdf-binomial and sample-binomial\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Binomial.html\n    http://en.wikipedia.org/wiki/Binomial_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-binomial 10 :prob 1/4 :size 20)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-binomial"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-chisq",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L744",
   :line 744,
   :var-type "function",
   :arglists
   ([x & {:keys [df lower-tail?], :or {df 1, lower-tail? true}}]),
   :doc
   "\nReturns the Chi Square cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Equivalent to R's pchisq function.\n\nOptions:\n  :df (default 1)\n  :lower-tail (default true)\n\nSee also:\n    pdf-chisq and sample-chisq\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/ChiSquare.html\n    http://en.wikipedia.org/wiki/Chi_square_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-chisq 5.0 :df 2)\n    (cdf-chisq 5.0 :df 2 :lower-tail false)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-chisq"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-empirical",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1437",
   :line 1437,
   :var-type "function",
   :arglists ([x]),
   :doc
   "\nReturns a step-function representing the empirical cdf of the given data.\nEquivalent to R's ecdf function.\n\nThe following description is from the ecdf help in R: The e.c.d.f.\n(empirical cumulative distribution function) Fn is a step function\nwith jumps i/n at observation values, where i is the number of tied\nobservations at that value.  Missing values are ignored.\n\nFor observations 'x'= (x1,x2, ... xn), Fn is the fraction of\nobservations less or equal to t, i.e.,\n\nFn(t) = #{x_i <= t} / n  =  1/n sum(i=1,n) Indicator(xi <= t).\n\n\nExamples:\n  (use '(incanter core stats charts))\n\n  (def exam1 [192 160 183 136 162 165 181 188 150 163 192 164 184\n              189 183 181 188 191 190 184 171 177 125 192 149 188\n              154 151 159 141 171 153 169 168 168 157 160 190 166 150])\n\n  ;; the ecdf function returns an empirical cdf function for the given data\n  (def ecdf (cdf-empirical exam1))\n\n  ;; plot the data's empirical cdf\n  (view (scatter-plot exam1 (map ecdf exam1)))\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-empirical"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-exp",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L954",
   :line 954,
   :var-type "function",
   :arglists ([x & {:keys [rate], :or {rate 1}}]),
   :doc
   "\nReturns the Exponential cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Equivalent to R's pexp.\n\nOptions:\n  :rate (default 1)\n\nSee also:\n    pdf-exp and sample-exp\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Exponential.html\n    http://en.wikipedia.org/wiki/Exponential_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-exp 2.0 :rate 1/2)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-exp"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-f",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L142",
   :line 142,
   :var-type "function",
   :arglists
   ([x
     &
     {:keys [df1 df2 lower-tail?],
      :or {df1 1, df2 1, lower-tail? true}}]),
   :doc
   "\nReturns the F-distribution cdf of the given value, x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's pf function.\n\nOptions:\n  :df1 (default 1)\n  :df2 (default 1)\n  :lower-tail? (default true)\n\nSee also:\n    pdf-f and quantile-f\n\nReferences:\n    http://en.wikipedia.org/wiki/F_distribution\n    http://mathworld.wolfram.com/F-Distribution.html\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-f 1.0 :df1 5 :df2 2)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-f"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-gamma",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L655",
   :line 655,
   :var-type "function",
   :arglists
   ([x
     &
     {:keys [shape scale rate lower-tail?],
      :or {shape 1, lower-tail? true}}]),
   :doc
   "\nReturns the Gamma cdf for the given value of x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's pgamma function.\n\nOptions:\n  :shape (k) (default 1)\n  :scale (θ) (default 1 or 1/rate, if :rate is specified)\n  :rate  (β) (default 1/scale, if :scale is specified)\n  :lower-tail (default true)\n\nSee also:\n    pdf-gamma and sample-gamma\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Gamma.html\n    http://en.wikipedia.org/wiki/Gamma_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-gamma 10 :shape 1 :scale 2)\n    (cdf-gamma 3 :shape 1 :lower-tail false)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-gamma"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-neg-binomial",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1376",
   :line 1376,
   :var-type "function",
   :arglists
   ([x
     &
     {:keys [size prob lower-tail?],
      :or {size 10, prob 1/2, lower-tail? true}}]),
   :doc
   "\nReturns the Negative Binomial cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Equivalent to R's dnbinom.\n\nOptions:\n  :size (default 10)\n  :prob (default 1/2)\n  :lower-tail? (default true)\n\nSee also:\n    pdf-neg-binomial and sample-neg-binomial\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/NegativeBinomial.html\n    http://en.wikipedia.org/wiki/Negative_binomial_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-neg-binomial 10 :prob 1/2 :size 20)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-neg-binomial"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-normal",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L215",
   :line 215,
   :var-type "function",
   :arglists ([x & {:keys [mean sd], :or {mean 0, sd 1}}]),
   :doc
   "\nReturns the Normal cdf of the given value, x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's pnorm function.\n\nOptions:\n  :mean (default 0)\n  :sd (default 1)\n\nSee also:\n    pdf-normal, quantile-normal, sample-normal\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Normal.html\n    http://en.wikipedia.org/wiki/Normal_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-normal 1.96 :mean -2 :sd (sqrt 0.5))\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-normal"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-poisson",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1288",
   :line 1288,
   :var-type "function",
   :arglists
   ([x
     &
     {:keys [lambda lower-tail?], :or {lambda 1, lower-tail? true}}]),
   :doc
   "\nReturns the Poisson cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Equivalent R's ppois.\n\nOptions:\n  :lambda (default 1)\n  :lower-tail (default true)\n\nSee also:\n    pdf-poisson and sample-poisson\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Poisson.html\n    http://en.wikipedia.org/wiki/Poisson_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-poisson 5 :lambda 10)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-poisson"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-t",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L830",
   :line 830,
   :var-type "function",
   :arglists
   ([x & {:keys [df lower-tail?], :or {df 1, lower-tail? true}}]),
   :doc
   "\nReturns the Student's t cdf for the given value of x. It will return a sequence\nof values, if x is a sequence. Equivalent to R's pt function.\n\nOptions:\n  :df (default 1)\n\nSee also:\n    pdf-t, quantile-t, and sample-t\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/StudentT.html\n    http://en.wikipedia.org/wiki/Student-t_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-t 1.2 :df 10)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-t"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-uniform",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L387",
   :line 387,
   :var-type "function",
   :arglists ([x & {:keys [min max], :or {min 0.0, max 1.0}}]),
   :doc
   "\nReturns the Uniform cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's punif function.\n\nOptions:\n  :min (default 0)\n  :max (default 1)\n\nSee also:\n    pdf-uniform and sample-uniform\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/DoubleUniform.html\n    http://en.wikipedia.org/wiki/Uniform_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-uniform 5)\n    (cdf-uniform 5 :min 1 :max 10)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-uniform"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-weibull",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L567",
   :line 567,
   :var-type "function",
   :arglists ([x & options]),
   :doc
   "\nReturns the Weibull cdf for the given value of x. It will return a sequence\nof values, if x is a sequence.\n\nOptions:\n  :shape (default 1)\n  :scale (default 1)\n\nSee also:\n    pdf-weibull and sample-weibull\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Distributions.html\n    http://en.wikipedia.org/wiki/Weibull_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-weibull 10 :shape 1 :scale 0.2)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-weibull"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "chebyshev-distance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3207",
   :line 3207,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "In the limiting case of Lp reaching infinity we obtain the Chebyshev distance.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/chebyshev-distance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "chisq-test",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2400",
   :line 2400,
   :var-type "function",
   :arglists
   ([& {:keys [x y correct table probs freq], :or {correct true}}]),
   :doc
   "\nPerforms chi-squared contingency table tests and goodness-of-fit tests.\n\nIf the optional argument :y is not provided then a goodness-of-fit test\nis performed. In this case, the hypothesis tested is whether the\npopulation probabilities equal those in :probs, or are all equal if\n:probs is not given.\n\nIf :y is provided, it must be a sequence of integers that is the\nsame length as x. A contingency table is computed from x and :y.\nThen, Pearson's chi-squared test of the null hypothesis that the joint\ndistribution of the cell counts in a 2-dimensional contingency\ntable is the product of the row and column marginals is performed.\nBy default the Yates' continuity correction for 2x2 contingency\ntables is performed, this can be disabled by setting the :correct\noption to false.\n\n\nOptions:\n  :x -- a sequence of numbers.\n  :y -- a sequence of numbers\n  :table -- a contingency table. If one dimensional, the test is a goodness-of-fit\n  :probs (when (nil? y) -- (repeat n-levels (/ n-levels)))\n  :freq (default nil) -- if given, these are rescaled to probabilities\n  :correct (default true) -- use Yates' correction for continuity for 2x2 contingency tables\n\n\nReturns:\n  :X-sq -- the Pearson X-squared test statistics\n  :p-value -- the p-value for the test statistic\n  :df -- the degress of freedom\n\n\nExamples:\n  (use '(incanter core stats))\n  (chisq-test :x [1 2 3 2 3 2 4 3 5]) ;; X-sq 2.6667\n  ;; create a one-dimensional table of this data\n  (def table (matrix [1 3 3 1 1]))\n  (chisq-test :table table) ;; X-sq 2.6667\n  (chisq-test :table (trans table)) ;; throws exception\n\n  (chisq-test :x [1 0 0 0  1 1 1 0 0 1 0 0 1 1 1 1]) ;; 0.25\n\n  (use '(incanter core stats datasets))\n  (def math-prog (to-matrix (get-dataset :math-prog)))\n  (def x (sel math-prog :cols 1))\n  (def y (sel math-prog :cols 2))\n  (chisq-test :x x :y y) ;; X-sq = 1.24145, df=1, p-value = 0.26519\n  (chisq-test :x x :y y :correct false) ;; X-sq = 2.01094, df=1, p-value = 0.15617\n\n  (def table (matrix [[31 12] [9 8]]))\n  (chisq-test :table table) ;; X-sq = 1.24145, df=1, p-value = 0.26519\n  (chisq-test :table table :correct false) ;; X-sq = 2.01094, df=1, p-value = 0.15617\n  ;; use the detabulate function to create data rows corresponding to the table\n  (def detab (detabulate :table table))\n  (chisq-test :x (sel detab :cols 0) :y (sel detab :cols 1))\n\n  ;; look at the hair-eye-color data\n  ;; turn the count data for males into a contingency table\n  (def male (matrix (sel (get-dataset :hair-eye-color) :cols 3 :rows (range 16)) 4))\n  (chisq-test :table male) ;; X-sq = 41.280, df = 9, p-value = 4.44E-6\n  ;; turn the count data for females into a contingency table\n  (def female (matrix (sel (get-dataset :hair-eye-color) :cols 3 :rows (range 16 32)) 4))\n  (chisq-test :table female) ;; X-sq = 106.664, df = 9, p-value = 7.014E-19,\n\n\n  ;; supply probabilities to goodness-of-fit test\n  (def table [89 37 30 28 2])\n  (def probs [0.40 0.20 0.20 0.19 0.01])\n  (chisq-test :table table :probs probs) ;; X-sq = 5.7947, df = 4, p-value = 0.215\n\n  ;; use frequencies instead of probabilities\n  (def freq [40 20 20 15 5])\n  (chisq-test :table table :freq freq) ;; X-sq = 9.9901, df = 4, p-value = 0.04059\n\n\n\nReferences:\n  http://www.itl.nist.gov/div898/handbook/eda/section3/eda35f.htm\n  http://en.wikipedia.org/wiki/Pearson's_chi-square_test\n  http://en.wikipedia.org/wiki/Yates'_chi-square_test\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/chisq-test"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "choose-singletype-col-summarizer",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2619",
   :line 2619,
   :var-type "function",
   :arglists ([col-type]),
   :doc "Takes in a type, and returns a suitable column summarizer",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/choose-singletype-col-summarizer"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "concordant-pairs",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3058",
   :line 3058,
   :var-type "function",
   :arglists ([a b]),
   :doc "http://en.wikipedia.org/wiki/Concordant_pairs",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/concordant-pairs"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "concordant?",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3048",
   :line 3048,
   :var-type "function",
   :arglists ([[[a1 b1] [a2 b2] & more]]),
   :doc
   "\nGiven two pairs of numbers, checks if they are concordant.\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/concordant?"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "correlation",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1560",
   :line 1560,
   :var-type "function",
   :arglists ([x y] [mat]),
   :doc
   "\nReturns the sample correlation of x and y, or the correlation\nmatrix of the given matrix.\n\nExamples:\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Correlation\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/correlation"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "correlation-linearity-test",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2929",
   :line 2929,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Correlation_ratio\nIt is worth noting that if the relationship between values of  and values of\noverline y_x is linear (which is certainly true when there are only two\npossibilities for x) this will give the same result as the square of the\ncorrelation coefficient, otherwise the correlation ratio will be larger in magnitude.\nIt can therefore be used for judging non-linear relationships.\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/correlation-linearity-test"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "correlation-ratio",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2879",
   :line 2879,
   :var-type "function",
   :arglists ([& xs]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Correlation_ratio\n\nIn statistics, the correlation ratio is a measure of the relationship between\nthe statistical dispersion within individual categories and the\ndispersion across the whole population or sample. i.e. the weighted variance\nof the category means divided by the variance of all samples.\n\nExample\n\nSuppose there is a distribution of test scores in three topics (categories):\n\n  * Algebra: 45, 70, 29, 15 and 21 (5 scores)\n  * Geometry: 40, 20, 30 and 42 (4 scores)\n  * Statistics: 65, 95, 80, 70, 85 and 73 (6 scores).\n\nThen the subject averages are 36, 33 and 78, with an overall average of 52.\n\nThe sums of squares of the differences from the subject averages are 1952\nfor Algebra, 308 for Geometry and 600 for Statistics, adding to 2860,\nwhile the overall sum of squares of the differences from the overall average\nis 9640. The difference between these of 6780 is also the weighted sum of the\nsquare of the differences between the subject averages and the overall average:\n\n  5(36 − 52)2 + 4(33 − 52)2 + 6(78 − 52)2 = 6780\n\nThis gives\n\n  eta^2 =6780/9640=0.7033\n\nsuggesting that most of the overall dispersion is a result of differences\nbetween topics, rather than within topics. Taking the square root\n\n  eta = sqrt 6780/9640=0.8386\n\nObserve that for η = 1 the overall sample dispersion is purely due to dispersion\namong the categories and not at all due to dispersion within the individual\ncategories. For a quick comprehension simply imagine all Algebra, Geometry,\nand Statistics scores being the same respectively, e.g. 5 times 36, 4 times 33, 6 times 78.\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/correlation-ratio"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "cosine-similarity",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3233",
   :line 3233,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Cosine_similarity\nhttp://www.appliedsoftwaredesign.com/cosineSimilarityCalculator.php\n\nThe Cosine Similarity of two vectors a and b is the ratio: a dot b / ||a|| ||b||\n\nLet d1 = {2 4 3 1 6}\nLet d2 = {3 5 1 2 5}\n\nCosine Similarity (d1, d2) =  dot(d1, d2) / ||d1|| ||d2||\n\ndot(d1, d2) = (2)*(3) + (4)*(5) + (3)*(1) + (1)*(2) + (6)*(5) = 61\n\n||d1|| = sqrt((2)^2 + (4)^2 + (3)^2 + (1)^2 + (6)^2) = 8.12403840464\n\n||d2|| = sqrt((3)^2 + (5)^2 + (1)^2 + (2)^2 + (5)^2) = 8\n\nCosine Similarity (d1, d2) = 61 / (8.12403840464) * (8)\n                           = 61 / 64.9923072371\n                           = 0.938572618717\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cosine-similarity"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "covariance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1511",
   :line 1511,
   :var-type "function",
   :arglists ([x y] [mat]),
   :doc
   "\nReturns the sample covariance of x and y.\n\nExamples:\n  ;; create some data that covaries\n  (def x (sample-normal 100))\n  (def err (sample-normal 100))\n  (def y (plus (mult 5 x) err))\n  ;; estimate the covariance of x and y\n  (covariance x y)\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Covariance\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/covariance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "cumulative-mean",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1864",
   :line 1864,
   :var-type "function",
   :arglists ([coll]),
   :doc
   "\nReturns a sequence of cumulative means for the given collection. For instance\nThe first value equals the first value of the argument, the second value is\nthe mean of the first two arguments, the third is the mean of the first three\narguments, etc.\n\nExamples:\n  (cumulative-mean (sample-normal 100))\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cumulative-mean"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "detabulate",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2728",
   :line 2728,
   :var-type "function",
   :arglists ([& {:keys [table row-labels col-labels]}]),
   :doc
   "\nTake a contingency table of counts and returns a matrix of observations.\n\nExamples:\n\n  (use '(incanter core stats datasets))\n\n  (def by-gender (group-on (get-dataset :hair-eye-color) 2))\n  (def table (matrix (sel (first by-gender) :cols 3) 4))\n\n  (detabulate :table table)\n  (tabulate (detabulate :table table))\n\n  ;; example 2\n  (def data (matrix [[1 0]\n                     [1 1]\n                     [1 1]\n                     [1 0]\n                     [0 0]\n                     [1 1]\n                     [1 1]\n                     [1 0]\n                     [1 1]]))\n  (tabulate data)\n\n  (tabulate (detabulate :table (:table (tabulate data))))\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/detabulate"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "dice-coefficient",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3318",
   :line 3318,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Dice%27s_coefficient\nDice's coefficient (also known as the Dice coefficient)\nis a similarity measure related to the Jaccard index.\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/dice-coefficient"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "dice-coefficient-str",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3344",
   :line 3344,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Dice%27s_coefficient\n\nWhen taken as a string similarity measure, the coefficient\nmay be calculated for two strings, x and y using bigrams.\nHere nt is the number of character bigrams found in both strings,\nnx is the number of bigrams in string x and\nny is the number of bigrams in string y.\nFor example, to calculate the similarity between:\n\n  night\n  nacht\n\nWe would find the set of bigrams in each word:\n\n  {ni,ig,gh,ht}\n  {na,ac,ch,ht}\n\nEach set has four elements, and the intersection of these two sets has only one element: ht.\n\nPlugging this into the formula, we calculate, s = (2 · 1) / (4 + 4) = 0.25.\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/dice-coefficient-str"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "discordant-pairs",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3068",
   :line 3068,
   :var-type "function",
   :arglists ([a b]),
   :doc "http://en.wikipedia.org/wiki/Discordant_pairs",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/discordant-pairs"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "euclidean-distance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3193",
   :line 3193,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Euclidean_distance\n\nthe Euclidean distance or Euclidean metric is the ordinary distance\nbetween two points that one would measure with a ruler, and is\ngiven by the Pythagorean formula. By using this formula as distance,\nEuclidean space (or even any inner product space) becomes a metric space.\nThe associated norm is called the Euclidean norm.\nOlder literature refers to the metric as Pythagorean metric.\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/euclidean-distance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "f-test",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2276",
   :line 2276,
   :var-type "function",
   :arglists ([x y]),
   :doc
   "\nTest for different variances between 2 samples\n\nArgument:\n  x : 1st sample to test\n  y : 2nd sample to test\n\nOptions:\n\nReferences:\n  http://en.wikipedia.org/wiki/F-test\n  http://people.richland.edu/james/lecture/m170/ch13-f.html\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/f-test"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "gamma-coefficient",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3090",
   :line 3090,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://www.statsdirect.com/help/nonparametric_methods/kend.htm\nThe gamma coefficient is given as a measure of association that\nis highly resistant to tied data (Goodman and Kruskal, 1963)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/gamma-coefficient"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "hamming-distance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3374",
   :line 3374,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Hamming_distance\n\nIn information theory, the Hamming distance between two strings of equal\nlength is the number of positions at which the corresponding symbols are different.\nPut another way, it measures the minimum number of\nsubstitutions required to change one string into the other,\nor the number of errors that transformed one string into the other.\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/hamming-distance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "indicator",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L74",
   :line 74,
   :var-type "function",
   :arglists ([pred coll]),
   :doc
   "\nReturns a sequence of ones and zeros, where ones\nare returned when the given predicate is true for\ncorresponding element in the given collection, and\nzero otherwise.\n\nExamples:\n  (use 'incanter.stats)\n\n  (indicator #(neg? %) (sample-normal 10))\n\n  ;; return the sum of the positive values in a normal sample\n  (def x (sample-normal 100))\n  (sum (mult x (indicator #(pos? %) x)))\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/indicator"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "jaccard-distance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3304",
   :line 3304,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Jaccard_index\nThe Jaccard distance, which measures dissimilarity between sample sets,\nis complementary to the Jaccard coefficient and is obtained by subtracting\nthe Jaccard coefficient from 1, or, equivalently, by dividing the difference\nof the sizes of the union and the intersection of two sets by the size of the union.\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/jaccard-distance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "jaccard-index",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3288",
   :line 3288,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Jaccard_index\n\nThe Jaccard index, also known as the Jaccard similarity coefficient\n(originally coined coefficient de communauté by Paul Jaccard), is a\nstatistic used for comparing the similarity and diversity of sample sets.\n\nThe Jaccard coefficient measures similarity between sample sets,\nand is defined as the size of the intersection divided by the\nsize of the union of the sample sets.\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/jaccard-index"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "kendalls-tau",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3000",
   :line 3000,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Kendall_tau_rank_correlation_coefficient\nhttp://www.statsdirect.com/help/nonparametric_methods/kend.htm\nhttp://mail.scipy.org/pipermail/scipy-dev/2009-March/011589.html\nbest explanation and example is in \"cluster analysis for researchers\" page 165.\nhttp://www.amazon.com/Cluster-Analysis-Researchers-Charles-Romesburg/dp/1411606175\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/kendalls-tau"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "kendalls-w",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3101",
   :line 3101,
   :var-type "function",
   :arglists ([]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Kendall%27s_W\nhttp://faculty.chass.ncsu.edu/garson/PA765/friedman.htm\n\nSuppose that object i is given the rank ri,j by judge number j, where there\nare in total n objects and m judges. Then the total rank given to object i is\n\n  Ri = sum Rij\n\nand the mean value of these total ranks is\n\n  Rbar = 1/2 m (n + 1)\n\nThe sum of squared deviations, S, is defined as\n\n  S=sum1-n (Ri - Rbar)\n\nand then Kendall's W is defined as[1]\n\n  W= 12S / m^2(n^3-n)\n\nIf the test statistic W is 1, then all the survey respondents have been\nunanimous, and each respondent has assigned the same order to the list\nof concerns. If W is 0, then there is no overall trend of agreement among\nthe respondents, and their responses may be regarded as essentially random.\nIntermediate values of W indicate a greater or lesser degree of unanimity\namong the various responses.\n\nLegendre[2] discusses a variant of the W statistic which accommodates ties\nin the rankings and also describes methods of making significance tests based on W.\n\n[{:observation [1 2 3]} {} ... {}] -> W\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/kendalls-w"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "kurtosis",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1622",
   :line 1622,
   :var-type "function",
   :arglists ([x]),
   :doc
   "\nReturns the kurtosis of the data, x. \"Kurtosis is a measure of the \"peakedness\"\nof the probability distribution of a real-valued random variable. Higher kurtosis\nmeans more of the variance is due to infrequent extreme deviations, as opposed to\nfrequent modestly-sized deviations.\" (Wikipedia)\n\nExamples:\n\n  (kurtosis (sample-normal 100000)) ;; approximately 0\n  (kurtosis (sample-gamma 100000)) ;; approximately 6\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Kurtosis\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/kurtosis"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "lee-distance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3395",
   :line 3395,
   :var-type "function",
   :arglists ([a b q]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Lee_distance\n\nIn coding theory, the Lee distance is a distance between\ntwo strings x1x2...xn and y1y2...yn of equal length n\nover the q-ary alphabet {0,1,…,q-1} of size q >= 2. It is metric.\n\nIf q = 2 or q = 3 the Lee distance coincides with the Hamming distance.\n\nThe metric space induced by the Lee distance is a discrete analog of the elliptic space.\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/lee-distance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "levenshtein-distance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3457",
   :line 3457,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Levenshtein_distance\n\ninternal representation is a table d with m+1 rows and n+1 columns\n\nwhere m is the length of a and m is the length of b.\n\nIn information theory and computer science, the Levenshtein distance\nis a metric for measuring the amount of difference between two sequences\n(i.e., the so called edit distance).\nThe Levenshtein distance between two strings is given by the minimum number\nof operations needed to transform one string into the other,\nwhere an operation is an insertion, deletion, or substitution of a single character.\n\nFor example, the Levenshtein distance between \"kitten\" and \"sitting\" is 3,\nsince the following three edits change one into the other,\nand there is no way to do it with fewer than three edits:\n\n 1. kitten → sitten (substitution of 's' for 'k')\n 2. sitten → sittin (substitution of 'i' for 'e')\n 3. sittin → sitting (insert 'g' at the end).\n\nThe Levenshtein distance has several simple upper and lower bounds that are useful\nin applications which compute many of them and compare them. These include:\n\n  * It is always at least the difference of the sizes of the two strings.\n  * It is at most the length of the longer string.\n  * It is zero if and only if the strings are identical.\n  * If the strings are the same size, the Hamming distance is an upper bound on the Levenshtein distance.\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/levenshtein-distance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "linear-model",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2038",
   :line 2038,
   :var-type "function",
   :arglists ([y x & {:keys [intercept], :or {intercept true}}]),
   :doc
   "\nReturns the results of performing a OLS linear regression of y on x.\n\nArguments:\n  y is a vector (or sequence) of values for the dependent variable\n  x is a vector or matrix of values for the independent variables\n\nOptions:\n  :intercept (default true) indicates weather an intercept term should be included\n\nReturns:\n  a map, of type ::linear-model, containing:\n    :design-matrix -- a matrix containing the independent variables, and an intercept columns\n    :coefs -- the regression coefficients\n    :t-tests -- t-test values of coefficients\n    :t-probs -- p-values for t-test values of coefficients\n    :coefs-ci -- 95% percentile confidence interval\n    :fitted -- the predicted values of y\n    :residuals -- the residuals of each observation\n    :std-errors -- the standard errors of the coeffients\n    :sse -- the sum of squared errors, also called the residual sum of squares\n    :ssr -- the regression sum of squares, also called the explained sum of squares\n    :sst -- the total sum of squares (proportional to the sample variance)\n    :r-square -- coefficient of determination\n\nExamples:\n  (use '(incanter core stats datasets charts))\n  (def iris (to-matrix (get-dataset :iris) :dummies true))\n  (def y (sel iris :cols 0))\n  (def x (sel iris :cols (range 1 6)))\n  (def iris-lm (linear-model y x)) ; with intercept term\n\n  (keys iris-lm) ; see what fields are included\n  (:coefs iris-lm)\n  (:sse iris-lm)\n  (quantile (:residuals iris-lm))\n  (:r-square iris-lm)\n  (:adj-r-square iris-lm)\n  (:f-stat iris-lm)\n  (:f-prob iris-lm)\n  (:df iris-lm)\n\n  (def x1 (range 0.0 3 0.1))\n  (view (xy-plot x1 (cdf-f x1 :df1 4 :df2 144)))\n\n\nReferences:\n  http://en.wikipedia.org/wiki/OLS_Regression\n  http://en.wikipedia.org/wiki/Coefficient_of_determination\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/linear-model"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "mahalanobis-distance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3565",
   :line 3565,
   :var-type "function",
   :arglists ([x & {:keys [y W centroid]}]),
   :doc
   "\nReturns the Mahalanobis distance between x, which is\n either a vector or matrix of row vectors, and the\n centroid of the observations in the matrix :y.\n\nArguments:\n  x -- either a vector or a matrix of row vectors\n\nOptions:\n  :y -- Defaults to x, must be a matrix of row vectors which will be used to calculate a centroid\n  :W -- Defaults to (solve (covariance y)), if an identity matrix is provided, the mahalanobis-distance\n        function will be equal to the Euclidean distance.\n  :centroid -- Defaults to (map mean (trans y))\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Mahalanobis_distance\n\n\nExamples:\n\n  (use '(incanter core stats charts))\n\n  ;; generate some multivariate normal data with a single outlier.\n  (def data (bind-rows\n              (bind-columns\n                (sample-mvn 100\n                            :sigma (matrix [[1 0.9]\n                                            [0.9 1]])))\n              [-1.75 1.75]))\n\n  ;; view a scatter plot of the data\n  (let [[x y] (trans data)]\n    (doto (scatter-plot x y)\n      (add-points [(mean x)] [(mean y)])\n      (add-pointer -1.75 1.75 :text \"Outlier\")\n      (add-pointer (mean x) (mean y) :text \"Centroid\")\n      view))\n\n  ;; calculate the distances of each point from the centroid.\n  (def dists (map first (mahalanobis-distance data)))\n  ;; view a bar-chart of the distances\n  (view (bar-chart (range 102) dists))\n\n  ;; Now contrast with the Euclidean distance.\n  (def dists (map first (mahalanobis-distance data :W (matrix [[1 0] [0 1]]))))\n  ;; view a bar-chart of the distances\n  (view (bar-chart (range 102) dists))\n\n\n  ;; another example\n  (mahalanobis-distance [-1.75 1.75] :y data)\n  (mahalanobis-distance [-1.75 1.75]\n                    :y data\n                    :W (matrix [[1 0]\n                                [0 1]]))\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/mahalanobis-distance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "manhattan-distance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3216",
   :line 3216,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Manhattan_distance\n\nusual metric of Euclidean geometry is replaced by a new metric in which\nthe distance between two points is the sum of the (absolute) differences\nof their coordinates. The taxicab metric is also known as rectilinear distance,\nL1 distance or l1 norm (see Lp space), city block distance,\nManhattan distance, or Manhattan length\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/manhattan-distance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "mean",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1477",
   :line 1477,
   :var-type "function",
   :arglists ([x]),
   :doc
   "\nReturns the mean of the data, x.\n\nExamples:\n  (mean (sample-normal 100))\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Mean\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/mean"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "median",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1604",
   :line 1604,
   :var-type "function",
   :arglists ([x]),
   :doc
   "\nReturns the median of the data, x.\n\nExamples:\n  (median (sample-normal 100))\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Median\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/median"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "minkowski-distance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3169",
   :line 3169,
   :var-type "function",
   :arglists ([a b p]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Minkowski_distance\nhttp://en.wikipedia.org/wiki/Lp_space\n\nThe Minkowski distance is a metric on Euclidean space which can be considered\nas a generalization of both the Euclidean distance and the Manhattan distance.\n\nMinkowski distance is typically used with p being 1 or 2. The latter is the\nEuclidean distance, while the former is sometimes known as the Manhattan distance.\n\nIn the limiting case of p reaching infinity we obtain the Chebyshev distance.\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/minkowski-distance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "n-grams",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3331",
   :line 3331,
   :var-type "function",
   :arglists ([n s]),
   :doc
   "\nReturns a set of the unique n-grams in a string.\nthis is using actual sets here, discards duplicate n-grams?\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/n-grams"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "normalized-kendall-tau-distance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3078",
   :line 3078,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Kendall_tau_distance\nKendall tau distance is the total number of discordant pairs.\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/normalized-kendall-tau-distance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "numeric-col-summarizer",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2604",
   :line 2604,
   :var-type "function",
   :arglists ([col ds]),
   :doc
   "Returns a summarizer function which takes a purely numeric column with no non-numeric values",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/numeric-col-summarizer"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "odds-ratio",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2849",
   :line 2849,
   :var-type "function",
   :arglists ([p1 p2]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Odds_ratio\n\nDefinition in terms of group-wise odds\n\nThe odds ratio is the ratio of the odds of an event occurring in one group\nto the odds of it occurring in another group, or to a sample-based estimate of that ratio.\n\n\nSuppose that in a sample of 100 men, 90 have drunk wine in the previous week,\nwhile in a sample of 100 women only 20 have drunk wine in the same period.\nThe odds of a man drinking wine are 90 to 10, or 9:1,\nwhile the odds of a woman drinking wine are only 20 to 80, or 1:4 = 0.25:1.\nThe odds ratio is thus 9/0.25, or 36, showing that men are much more likely\nto drink wine than women.\n\nRelation to statistical independence\n\nIf X and Y are independent, their joint probabilities can be expressed in\nterms of their marginal probabilities. In this case, the odds ratio equals one,\nand conversely the odds ratio can only equal one if the joint probabilities\ncan be factored in this way. Thus the odds ratio equals one if and only if\nX and Y are independent.\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/odds-ratio"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "pairings",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3039",
   :line 3039,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nCreates pairs by matching a1 with b1, a2 with b2, etc. and returns\nall pairs of those pairs without matching a pair with itself.\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pairings"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "pairs",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3024",
   :line 3024,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nReturns unique pairs of a and b where members of a and b can not\nbe paired with the corresponding slot in the other list.\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pairs"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-beta",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L450",
   :line 450,
   :var-type "function",
   :arglists ([x & {:keys [alpha beta], :or {alpha 1, beta 1}}]),
   :doc
   "\nReturns the Beta pdf of the given value of x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's dbeta function.\n\nOptions:\n  :alpha (default 1)\n  :beta (default 1)\n\nSee also:\n    cdf-beta and sample-beta\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Beta.html\n    http://en.wikipedia.org/wiki/Beta_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-beta 0.5 :alpha 1 :beta 2)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-beta"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-binomial",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1123",
   :line 1123,
   :var-type "function",
   :arglists ([x & {:keys [size prob], :or {size 1, prob 1/2}}]),
   :doc
   "\nReturns the Binomial pdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Equivalent to R's dbinom.\n\nOptions:\n  :size (default 1)\n  :prob (default 1/2)\n\nSee also:\n    cdf-binomial and sample-binomial\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Binomial.html\n    http://en.wikipedia.org/wiki/Binomial_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-binomial 10 :prob 1/4 :size 20)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-binomial"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-chisq",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L717",
   :line 717,
   :var-type "function",
   :arglists ([x & {:keys [df], :or {df 1}}]),
   :doc
   "\nReturns the Chi Square pdf of the given value of x.  It will return a sequence\nof values, if x is a sequence. Equivalent to R's dchisq function.\n\nOptions:\n  :df (default 1)\n\nSee also:\n    cdf-chisq and sample-chisq\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/ChiSquare.html\n    http://en.wikipedia.org/wiki/Chi_square_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-chisq 5.0 :df 2)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-chisq"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-exp",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L928",
   :line 928,
   :var-type "function",
   :arglists ([x & {:keys [rate], :or {rate 1}}]),
   :doc
   "\nReturns the Exponential pdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Equivalent to R's dexp.\n\nOptions:\n  :rate (default 1)\n\nSee also:\n    cdf-exp and sample-exp\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Exponential.html\n    http://en.wikipedia.org/wiki/Exponential_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-exp 2.0 :rate 1/2)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-exp"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-f",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L107",
   :line 107,
   :var-type "function",
   :arglists ([x & {:keys [df1 df2], :or {df1 1, df2 1}}]),
   :doc
   "\nReturns the F pdf of the given value, x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's df function.\n\nOptions:\n  :df1 (default 1)\n  :df2 (default 1)\n\nSee also:\n    cdf-f and quantile-f\n\nReferences:\n    http://en.wikipedia.org/wiki/F_distribution\n    http://mathworld.wolfram.com/F-Distribution.html\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-f 1.0 :df1 5 :df2 2)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-f"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-gamma",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L627",
   :line 627,
   :var-type "function",
   :arglists ([x & {:keys [shape scale rate], :or {shape 1}}]),
   :doc
   "\nReturns the Gamma pdf for the given value of x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's dgamma function.\n\nOptions:\n  :shape (k) (default 1)\n  :scale (θ) (default 1 or 1/rate, if :rate is specified)\n  :rate  (β) (default 1/scale, if :scale is specified)\n\nSee also:\n    cdf-gamma and sample-gamma\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Gamma.html\n    http://en.wikipedia.org/wiki/Gamma_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-gamma 10 :shape 1 :scale 2)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-gamma"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-neg-binomial",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1347",
   :line 1347,
   :var-type "function",
   :arglists ([x & {:keys [size prob], :or {size 10, prob 1/2}}]),
   :doc
   "\nReturns the Negative Binomial pdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Equivalent to R's dnbinom.\n\nOptions:\n  :size (default 10)\n  :prob (default 1/2)\n\nSee also:\n    cdf-neg-binomial and sample-neg-binomial\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/NegativeBinomial.html\n    http://en.wikipedia.org/wiki/Negative_binomial_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-neg-binomial 10 :prob 1/2 :size 20)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-neg-binomial"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-normal",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L187",
   :line 187,
   :var-type "function",
   :arglists ([x & {:keys [mean sd], :or {mean 0, sd 1}}]),
   :doc
   "\nReturns the Normal pdf of the given value, x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's dnorm function.\n\nOptions:\n  :mean (default 0)\n  :sd (default 1)\n\nSee also:\n    cdf-normal, quantile-normal, sample-normal\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Normal.html\n    http://en.wikipedia.org/wiki/Normal_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-normal 1.96 :mean -2 :sd (sqrt 0.5))\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-normal"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-poisson",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1261",
   :line 1261,
   :var-type "function",
   :arglists ([x & {:keys [lambda], :or {lambda 1}}]),
   :doc
   "\nReturns the Poisson pdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Equivalent to R's dpois.\n\nOptions:\n  :lambda (default 1)\n\nSee also:\n    cdf-poisson and sample-poisson\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Poisson.html\n    http://en.wikipedia.org/wiki/Poisson_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-poisson 5 :lambda 10)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-poisson"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-t",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L804",
   :line 804,
   :var-type "function",
   :arglists ([x & {:keys [df], :or {df 1}}]),
   :doc
   "\nReturns the Student's t pdf for the given value of x. It will return a sequence\nof values, if x is a sequence. Equivalent to R's dt function.\n\nOptions:\n  :df (default 1)\n\nSee also:\n    cdf-t, quantile-t, and sample-t\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/StudentT.html\n    http://en.wikipedia.org/wiki/Student-t_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-t 1.2 :df 10)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-t"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-uniform",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L359",
   :line 359,
   :var-type "function",
   :arglists ([x & {:keys [min max], :or {min 0.0, max 1.0}}]),
   :doc
   "\nReturns the Uniform pdf of the given value of x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's dunif function.\n\nOptions:\n  :min (default 0)\n  :max (default 1)\n\nSee also:\n    cdf-uniform and sample-uniform\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/DoubleUniform.html\n    http://en.wikipedia.org/wiki/Uniform_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-uniform 5)\n    (pdf-uniform 5 :min 1 :max 10)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-uniform"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-weibull",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L538",
   :line 538,
   :var-type "function",
   :arglists ([x & options]),
   :doc
   "\nReturns the Weibull pdf for the given value of x. It will return a sequence\nof values, if x is a sequence.\n\nOptions:\n    :scale (default 1)\n    :shape (default 1)\n\nSee also:\n    cdf-weibull and sample-weibull\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Distributions.html\n    http://en.wikipedia.org/wiki/Weibull_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-weibull 2 :alpha 1 :beta 0.5)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-weibull"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "permute",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1905",
   :line 1905,
   :var-type "function",
   :arglists ([x] [x y]),
   :doc
   "\nIf provided a single argument, returns a permuted version of the\ngiven collection. (permute x) is the same as (sample x).\n\nIf provided two arguments, returns two lists that are permutations\nacross the given collections. In other words, each of the new collections\nwill contain elements from both of the given collections. Useful for\npermutation tests or randomization tests.\n\nExamples:\n\n  (permute (range 10))\n  (permute (range 10) (range 10 20))\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/permute"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "predict",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2834",
   :line 2834,
   :var-type "function",
   :arglists ([model x]),
   :doc
   "Takes a linear-model and an x value (either a scalar or vector)\nand returns the predicted value based on the linear-model.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/predict"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "principal-components",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2671",
   :line 2671,
   :var-type "function",
   :arglists ([x & options]),
   :doc
   "\nPerforms a principal components analysis on the given data matrix.\nEquivalent to R's prcomp function.\n\nReturns:\n  A map with the following fields:\n  :std-dev -- the standard deviations of the principal components\n      (i.e. the square roots of the eigenvalues of the correlation\n      matrix, though the calculation is actually done with the\n      singular values of the data matrix.\n  :rotation -- the matrix of variable loadings (i.e. a matrix\n      whose columns contain the eigenvectors).\n\n\nExamples:\n\n  (use '(incanter core stats charts datasets))\n  ;; load the iris dataset\n  (def iris (to-matrix (get-dataset :iris)))\n  ;; run the pca\n  (def pca (principal-components (sel iris :cols (range 4))))\n  ;; extract the first two principal components\n  (def pc1 (sel (:rotation pca) :cols 0))\n  (def pc2 (sel (:rotation pca) :cols 1))\n\n  ;; project the first four dimension of the iris data onto the first\n  ;; two principal components\n  (def x1 (mmult (sel iris :cols (range 4)) pc1))\n  (def x2 (mmult (sel iris :cols (range 4)) pc2))\n\n  ;; now plot the transformed data, coloring each species a different color\n  (doto (scatter-plot (sel x1 :rows (range 50)) (sel x2 :rows (range 50))\n                      :x-label \"PC1\" :y-label \"PC2\" :title \"Iris PCA\")\n        (add-points (sel x1 :rows (range 50 100)) (sel x2 :rows (range 50 100)))\n        (add-points (sel x1 :rows (range 100 150)) (sel x2 :rows (range 100 150)))\n        view)\n\n\n  ;; alternatively, the :group-by option can be used in scatter-plot\n  (view (scatter-plot x1 x2\n                      :group-by (sel iris :cols 4)\n                      :x-label \"PC1\" :y-label \"PC2\" :title \"Iris PCA\"))\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Principal_component_analysis\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/principal-components"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "product-marginal-test",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3152",
   :line 3152,
   :var-type "function",
   :arglists ([j]),
   :doc
   "the joint PMF of independent variables is equal to the product of their marginal PMFs.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/product-marginal-test"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "quantile",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1660",
   :line 1660,
   :var-type "function",
   :arglists
   ([x
     &
     {:keys [probs],
      :or
      {probs
       (DoubleArrayList. (double-array [0.0 0.25 0.5 0.75 1.0]))}}]),
   :doc
   "\nReturns the quantiles of the data, x. By default it returns the min,\n25th-percentile, 50th-percentile, 75th-percentile, and max value.\n\nOptions:\n  :probs (default [0.0 0.25 0.5 0.75 1.0])\n\nExamples:\n  (quantile (sample-normal 100))\n  (quantile (sample-normal 100) :probs [0.025 0.975])\n  (quantile (sample-normal 100) :probs 0.975)\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Quantile\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/quantile"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "quantile-normal",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L242",
   :line 242,
   :var-type "function",
   :arglists ([probability & {:keys [mean sd], :or {mean 0, sd 1}}]),
   :doc
   "\nReturns the inverse of the Normal CDF for the given probability.\nIt will return a sequence of values, if given a sequence of\nprobabilities. This is equivalent to R's qnorm function.\n\nOptions:\n  :mean (default 0)\n  :sd (default 1)\n\nReturns:\n  a value x, where (cdf-normal x) = probability\n\nSee also:\n    pdf-normal, cdf-normal, and sample-normal\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Probability.html\n    http://en.wikipedia.org/wiki/Normal_distribution\n    http://en.wikipedia.org/wiki/Quantile\n\nExample:\n    (quantile-normal 0.975)\n    (quantile-normal [0.025 0.975] :mean -2 :sd (sqrt 0.5))\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/quantile-normal"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "quantile-t",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L858",
   :line 858,
   :var-type "function",
   :arglists ([probability & {:keys [df], :or {df 1}}]),
   :doc
   "\nReturns the inverse of the Student's t CDF for the given probability\n(i.e. the quantile).  It will return a sequence of values, if x is\na sequence of probabilities. This is equivalent to R's qt function.\n\nOptions:\n  :df (default 1)\n\nReturns:\n  a value x, where (cdf-t x) = probability\n\nSee also:\n   pdf-t, cdf-t, and sample-t\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Probability.html\n    http://en.wikipedia.org/wiki/Student-t_distribution\n    http://en.wikipedia.org/wiki/Quantile\n\nExample:\n    (quantile-t 0.975)\n    (quantile-t [0.025 0.975] :df 25)\n    (def df [1 2 3 4 5 6 7 8 9 10 20 50 100 1000])\n    (map #(quantile-t 0.025 :df %) df)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/quantile-t"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "rank-index",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2942",
   :line 2942,
   :var-type "function",
   :arglists ([x]),
   :doc
   "\nGiven a seq, returns a map where the keys are the values of the seq\nand the values are the positional rank of each member o the seq.\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/rank-index"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sample",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1689",
   :line 1689,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "\nReturns a sample of the given size from the given collection. If replacement\nis set to false it returns a set, otherwise it returns a list.\n\nArguments:\n  coll -- collection or dataset to be sampled from\n\nOptions:\n  :size -- (default (count x) sample size\n  :replacement (default true) -- sample with replacement\n\n\nExamples:\n  (sample (range 10)) ; permutation of numbers zero through ten\n  (sample [:red :green :blue] :size 10) ; choose 10 items that are either :red, :green, or :blue.\n  (sample (seq \"abcdefghijklmnopqrstuvwxyz\")  :size 4 :replacement false) ; choose 4 random letters.\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-beta",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L509",
   :line 509,
   :var-type "function",
   :arglists ([size & {:keys [alpha beta], :or {alpha 1, beta 1}}]),
   :doc
   "\nReturns a sample of the given size from a Beta distribution.\nThis is equivalent to R's rbeta function.\n\nOptions:\n  :alpha (default 1)\n  :beta (default 1)\n  These default values produce a Uniform distribution.\n\nSee also:\n    pdf-beta and cdf-beta\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Beta.html\n    http://en.wikipedia.org/wiki/Beta_distribution\n\nExample:\n    (sample-beta 1000 :alpha 1 :beta 2)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-beta"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-binomial",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1182",
   :line 1182,
   :var-type "function",
   :arglists
   ([samplesize & {:keys [size prob], :or {size 1, prob 1/2}}]),
   :doc
   "\nReturns a sample of the given size from a Binomial distribution.\nEquivalent to R's rbinom.\n\nOptions:\n  :size (default 1)\n  :prob (default 1/2)\n\nSee also:\n    pdf-binomial and cdf-binomial\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Binomial.html\n    http://en.wikipedia.org/wiki/Binomial_distribution\n\nExample:\n    (sample-binomial 1000 :prob 1/4 :size 20)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-binomial"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-chisq",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L775",
   :line 775,
   :var-type "function",
   :arglists ([size & {:keys [df], :or {df 1}}]),
   :doc
   "\nReturns a sample of the given size from a Chi Square distribution\nEquivalent to R's rchisq function.\n\nOptions:\n  :df (default 1)\n\nSee also:\n    pdf-chisq and cdf-chisq\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/ChiSquare.html\n    http://en.wikipedia.org/wiki/Chi_square_distribution\n\nExample:\n    (sample-chisq 1000 :df 2)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-chisq"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-dirichlet",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1073",
   :line 1073,
   :var-type "function",
   :arglists ([size alpha]),
   :doc
   "\nExamples:\n  (use '(incanter core stats charts))\n\n  ;; a total of 1447 adults were polled to indicate their preferences for\n  ;; candidate 1 (y1=727), candidate 2 (y2=583), or some other candidate or no\n  ;; preference (y3=137).\n\n  ;; the counts y1, y2, and y3 are assumed to have a multinomial distribution\n  ;; If a uniform prior distribution is assigned to the multinomial vector\n  ;; theta = (th1, th2, th3), then the posterior distribution of theta is\n  ;; proportional to g(theta) = th1^y1 * th2^y2 * th3^y3, which is a\n  ;; dirichlet distribution with parameters (y1+1, y2+1, y3+1)\n  (def  theta (sample-dirichlet 1000 [(inc 727) (inc 583) (inc 137)]))\n  ;; view means, 95% CI, and histograms of the proportion parameters\n  (mean (sel theta :cols 0))\n  (quantile (sel theta :cols 0) :probs [0.0275 0.975])\n  (view (histogram (sel theta :cols 0)))\n  (mean (sel theta :cols 1))\n  (quantile (sel theta :cols 1) :probs [0.0275 0.975])\n  (view (histogram (sel theta :cols 1)))\n  (mean (sel theta :cols 2))\n  (quantile (sel theta :cols 2) :probs [0.0275 0.975])\n  (view (histogram (sel theta :cols 2)))\n\n  ;; view  a histogram of the difference in proportions between the first\n  ;; two candidates\n  (view (histogram (minus (sel theta :cols 0) (sel theta :cols 1))))\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-dirichlet"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-exp",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L980",
   :line 980,
   :var-type "function",
   :arglists ([size & {:keys [rate], :or {rate 1}}]),
   :doc
   "\nReturns a sample of the given size from a Exponential distribution.\nEquivalent to R's rexp.\n\nOptions:\n  :rate (default 1)\n\nSee also:\n    pdf-exp and cdf-exp\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Exponential.html\n    http://en.wikipedia.org/wiki/Exponential_distribution\n\nExample:\n    (sample-exp 1000 :rate 1/2)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-exp"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-gamma",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L687",
   :line 687,
   :var-type "function",
   :arglists ([size & {:keys [shape scale rate], :or {shape 1}}]),
   :doc
   "\nReturns a sample of the given size from a Gamma distribution.\nThis is equivalent to R's rgamma function.\n\nOptions:\n  :shape (k) (default 1)\n  :scale (θ) (default 1 or 1/rate, if :rate is specified)\n  :rate  (β) (default 1/scale, if :scale is specified)\n\nSee also:\n    pdf-gamma, cdf-gamma, and quantile-gamma\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Gamma.html\n    http://en.wikipedia.org/wiki/Gamma_distribution\n\nExample:\n    (sample-gamma 1000 :shape 1 :scale 2)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-gamma"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-inv-wishart",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1046",
   :line 1046,
   :var-type "function",
   :arglists ([& {:keys [scale p df], :or {p 2}}]),
   :doc
   "\nReturns a p-by-p symmetric distribution drawn from an inverse-Wishart distribution\n\nOptions:\n  :p (default 2) -- number of dimensions of resulting matrix\n  :df (default p) -- degree of freedoms (aka n), df <= p\n  :scale (default (identity-matrix p)) -- positive definite matrix (aka V)\n\nExamples:\n  (use 'incanter.stats)\n  (sample-inv-wishart :df 10  :p 4)\n\n  ;; calculate the mean of 1000 wishart matrices, should equal (mult df scale)\n  (div (reduce plus (for [_ (range 1000)] (sample-wishart :p 4))) 1000)\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Inverse-Wishart_distribution\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-inv-wishart"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-multinomial",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1213",
   :line 1213,
   :var-type "function",
   :arglists
   ([size & {:keys [probs categories], :or {probs [0.5 0.5]}}]),
   :doc
   "\nReturns a sequence representing a sample from a multinomial distribution.\n\nArguments: size -- number of values to return\n\nOptions:\n  :categories (default [0 1]) -- the values returned\n  :probs (default [0.5 0.5]) -- the probabilities associated with each category\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Multinomial_distribution#Sampling_from_a_multinomial_distribution\n\n\nExamples:\n  (use '(incanter core stats charts))\n\n  (sample-multinomial 10)\n  (sample-multinomial 10 :probs [0.25 0.5 0.25])\n\n  ;; estimate sample proportions\n  (def sample-size 1000.0)\n  (def categories [:red :yellow :blue :green])\n  (def data (to-dataset (sample-multinomial sample-size\n                                            :categories categories\n                                            :probs [0.5 0.25 0.2 0.05])))\n\n  ;; check the sample proportions\n  (view (pie-chart categories\n                   (map #(div (count ($ :col-0 ($where {:col-0 %} data)))\n                              sample-size)\n                        categories)))\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-multinomial"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-mvn",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L305",
   :line 305,
   :var-type "function",
   :arglists ([size & {:keys [mean sigma]}]),
   :doc
   "\nReturns a sample of the given size from a Multivariate Normal\ndistribution. This is equivalent to R's mvtnorm::rmvnorm function.\n\nArguments:\n  size -- the size of the sample to return\n\nOptions:\n  :mean (default (repeat (ncol sigma) 0))\n  :sigma (default (identity-matrix (count mean)))\n\n\nExamples:\n\n  (use '(incanter core stats charts))\n  (def mvn-samp (sample-mvn 1000 :mean [7 5] :sigma (matrix [[2 1.5] [1.5 3]])))\n  (covariance mvn-samp)\n  (def means (map mean (trans mvn-samp)))\n\n  ;; plot scatter-plot of points\n  (def mvn-plot (scatter-plot (sel mvn-samp :cols 0) (sel mvn-samp :cols 1)))\n  (view mvn-plot)\n  ;; add centroid to plot\n  (add-points mvn-plot [(first means)] [(second means)])\n\n  ;; add regression line to scatter plot\n  (def x (sel mvn-samp :cols 0))\n  (def y (sel mvn-samp :cols 1))\n  (def lm (linear-model y x))\n  (add-lines mvn-plot x (:fitted lm))\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Multivariate_normal\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-mvn"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-neg-binomial",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1407",
   :line 1407,
   :var-type "function",
   :arglists
   ([samplesize & {:keys [size prob], :or {size 10, prob 1/2}}]),
   :doc
   "\nReturns a sample of the given size from a Negative Binomial distribution.\nEquivalent to R's rnbinom.\n\nOptions:\n  :size (default 10)\n  :prob (default 1/2)\n\nSee also:\n    pdf-neg-binomial and cdf-neg-binomial\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/NegativeBinomial.html\n    http://en.wikipedia.org/wiki/Negative_binomial_distribution\n\nExample:\n    (sample-neg-binomial 1000 :prob 1/2 :size 20)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-neg-binomial"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-normal",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L275",
   :line 275,
   :var-type "function",
   :arglists ([size & {:keys [mean sd], :or {mean 0, sd 1}}]),
   :doc
   "\nReturns a sample of the given size from a Normal distribution\nThis is equivalent to R's rnorm function.\n\nOptions:\n  :mean (default 0)\n  :sd (default 1)\n\nSee also:\n    pdf-normal, cdf-normal, quantile-normal\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Normal.html\n    http://en.wikipedia.org/wiki/Normal_distribution\n\nExample:\n    (sample-normal 1000 :mean -2 :sd (sqrt 0.5))\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-normal"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-permutations",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1932",
   :line 1932,
   :var-type "function",
   :arglists ([n x] [n x y]),
   :doc
   "\nIf provided a two arguments (n x), it returns a list of n permutations\nof x. If provided three (n x y) arguments, returns a list with two with n permutations of\neach arguments, where each permutation is drawn from the pooled arguments.\n\nArguments:\n  n -- number of randomized versions of the original two groups to return\n  x -- group 1\n  y -- (default nil) group 2\n\n\nExamples:\n\n  (use '(incanter core stats))\n  (sample-permutations 10 (range 10))\n  (sample-permutations 10 (range 10) (range 10 20))\n\n  ;; extended example with plant-growth data\n  (use '(incanter core stats datasets charts))\n\n  ;; load the plant-growth dataset\n  (def data (to-matrix (get-dataset :plant-growth)))\n\n  ;; break the first column of the data into groups based on treatment (second column).\n  (def groups (group-on data 1 :cols 0))\n\n  ;; define a function for the statistic of interest\n  (defn means-diff [x y] (minus (mean x) (mean y)))\n\n  ;; calculate the difference in sample means between the two groups\n  (def samp-mean-diff (means-diff (first groups) (second groups))) ;; 0.371\n\n  ;; create 500 permuted versions of the original two groups\n  (def permuted-groups (sample-permutations 1000 (first groups) (second groups)))\n\n  ;; calculate the difference of means of the 500 samples\n  (def permuted-means-diffs1 (map means-diff (first permuted-groups) (second permuted-groups)))\n\n  ;; use an indicator function that returns 1 when the randomized means diff is greater\n  ;; than the original sample mean, and zero otherwise. Then take the mean of this sequence\n  ;; of ones and zeros. That is the proportion of times you would see a value more extreme\n  ;; than the sample mean (i.e. the p-value).\n  (mean (indicator #(> % samp-mean-diff) permuted-means-diffs1)) ;; 0.088\n\n  ;; calculate the 95% confidence interval of the null hypothesis. If the\n  ;; sample difference in means is outside of this range, that is evidence\n  ;; that the two means are statistically significantly different.\n  (quantile permuted-means-diffs1 :probs [0.025 0.975]) ;; (-0.606 0.595)\n\n  ;; Plot a histogram of the permuted-means-diffs using the density option,\n  ;; instead of the default frequency, and then add a normal pdf curve with\n  ;; the mean and sd of permuted-means-diffs data for a visual comparison.\n  (doto (histogram permuted-means-diffs1 :density true)\n        (add-lines (range -1 1 0.01) (pdf-normal (range -1 1 0.01)\n                                                 :mean (mean permuted-means-diffs1)\n                                                 :sd (sd permuted-means-diffs1)))\n        view)\n\n  ;; compare the means of treatment 2 and control\n  (def permuted-groups (sample-permutations 1000 (first groups) (last groups)))\n  (def permuted-means-diffs2 (map means-diff (first permuted-groups) (second permuted-groups)))\n  (def samp-mean-diff (means-diff (first groups) (last groups))) ;; -0.4939\n  (mean (indicator #(< % samp-mean-diff) permuted-means-diffs2)) ;; 0.022\n  (quantile permuted-means-diffs2 :probs [0.025 0.975]) ;; (-0.478 0.466)\n\n  ;; compare the means of treatment 1 and treatment 2\n  (def permuted-groups (sample-permutations 1000 (second groups) (last groups)))\n  (def permuted-means-diffs3 (map means-diff (first permuted-groups) (second permuted-groups)))\n  (def samp-mean-diff (means-diff (second groups) (last groups))) ;; -0.865\n  (mean (indicator #(< % samp-mean-diff) permuted-means-diffs3)) ;;  0.002\n  (quantile permuted-means-diffs3 :probs [0.025 0.975]) ;; (-0.676 0.646)\n\n  (doto (box-plot permuted-means-diffs1)\n        (add-box-plot permuted-means-diffs2)\n        (add-box-plot permuted-means-diffs3)\n        view)\n\n\n  Further Reading:\n    http://en.wikipedia.org/wiki/Resampling_(statistics)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-permutations"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-poisson",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1318",
   :line 1318,
   :var-type "function",
   :arglists ([size & {:keys [lambda], :or {lambda 1}}]),
   :doc
   "\nReturns a sample of the given size from a Poisson distribution.\nEquivalent to R's rpois.\n\nOptions:\n  :lambda (default 1)\n\nSee also:\n    pdf-poisson and cdf-poisson\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Poisson.html\n    http://en.wikipedia.org/wiki/Poisson_distribution\n\nExample:\n    (sample-poisson 1000 :lambda 10)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-poisson"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-t",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L897",
   :line 897,
   :var-type "function",
   :arglists ([size & {:keys [df], :or {df 1}}]),
   :doc
   "\nReturns a sample of the given size from a Student's t distribution.\nEquivalent to R's rt function.\n\nOptions:\n  :df (default 1)\n\nSee also:\n    pdf-t, cdf-t, and quantile-t\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/StudentT.html\n    http://en.wikipedia.org/wiki/Student-t_distribution\n\nExample:\n    (cdf-t 1000 :df 10)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-t"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-uniform",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L415",
   :line 415,
   :var-type "function",
   :arglists
   ([size
     &
     {:keys [min max integers],
      :or {min 0.0, max 1.0, integers false}}]),
   :doc
   "\nReturns a sample of the given size from a Uniform distribution.\nThis is equivalent to R's runif function.\n\nOptions:\n  :min (default 0)\n  :max (default 1)\n  :integers (default false)\n\nSee also:\n    pdf-uniform and cdf-uniform\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/DoubleUniform.html\n    http://en.wikipedia.org/wiki/Uniform_distribution\n\nExample:\n    (sample-uniform 1000)\n    (sample-uniform 1000 :min 1 :max 10)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-uniform"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-weibull",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L596",
   :line 596,
   :var-type "function",
   :arglists ([size & options]),
   :doc
   "\nReturns a sample of the given size from a Weibull distribution\n\nOptions:\n  :shape (default 1)\n  :scale (default 1)\n\nSee also:\n    pdf-weibull, cdf-weibull\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Distributions.html\n    http://en.wikipedia.org/wiki/Weibull_distribution\n\nExample:\n    (sample-weibull 1000 :shape 1 :scale 0.2)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-weibull"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-wishart",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1011",
   :line 1011,
   :var-type "function",
   :arglists ([& {:keys [scale p df], :or {p 2}}]),
   :doc
   "\nReturns a p-by-p symmetric distribution drawn from a Wishart distribution\n\nOptions:\n  :p (default 2) -- number of dimensions of resulting matrix\n  :df (default p) -- degree of freedoms (aka n), df <= p\n  :scale (default (identity-matrix p)) -- positive definite matrix (aka V)\n\nExamples:\n  (use 'incanter.stats)\n  (sample-wishart :df 10  :p 4)\n\n  ;; calculate the mean of 1000 wishart matrices, should equal (mult df scale)\n  (div (reduce plus (for [_ (range 1000)] (sample-wishart :p 4))) 1000)\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Wishart_distribution\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-wishart"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "scalar-abs",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L47",
   :line 47,
   :var-type "function",
   :arglists ([x]),
   :doc "Fast absolute value function",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/scalar-abs"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sd",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1541",
   :line 1541,
   :var-type "function",
   :arglists ([x]),
   :doc
   "\nReturns the sample standard deviation of the data, x. Equivalent to\nR's sd function.\n\nExamples:\n  (sd (sample-normal 100))\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Standard_deviation\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sd"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "simple-ci",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2265",
   :line 2265,
   :var-type "function",
   :arglists ([coll]),
   :doc "Get the confidence interval for the data.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/simple-ci"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "simple-p-value",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2257",
   :line 2257,
   :var-type "function",
   :arglists ([coll mu]),
   :doc "Returns the p-value for the data contained in coll.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/simple-p-value"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "simple-regression",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2818",
   :line 2818,
   :var-type "function",
   :arglists ([y x & {:keys [intercept], :or {intercept true}}]),
   :doc
   "A stripped version of linear-model that returns a map containing only\nthe coefficients.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/simple-regression"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "simple-t-test",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2248",
   :line 2248,
   :var-type "function",
   :arglists ([coll mu]),
   :doc "Perform a simple t-test on the data contained in coll.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/simple-t-test"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "skewness",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1642",
   :line 1642,
   :var-type "function",
   :arglists ([x]),
   :doc
   "\nReturns the skewness of the data, x. \"Skewness is a measure of the asymmetry\nof the probability distribution of a real-valued random variable.\" (Wikipedia)\n\nExamples:\n\n  (skewness (sample-normal 100000)) ;; approximately 0\n  (skewness (sample-gamma 100000)) ;; approximately 2\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Skewness\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/skewness"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sorensen-index",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3420",
   :line 3420,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/S%C3%B8rensen_similarity_index#cite_note-4\n\nThe Sørensen index, also known as Sørensen’s similarity coefficient,\nis a statistic used for comparing the similarity of two samples.\nwhere A and B are the species numbers in samples A and B, respectively,\nand C is the number of species shared by the two samples.\n\nThe Sørensen index is identical to Dice's coefficient which is always in [0, 1] range.\nSørensen index used as a distance measure, 1 − QS, is identical\nto Hellinger distance and Bray–Curtis dissimilarity.\n\nThe Sørensen coefficient is mainly useful for ecological community data\n(e.g. Looman & Campbell, 1960[3]). Justification for its use is primarily\nempirical rather than theoretical\n(although it can be justified theoretically as the intersection of two fuzzy sets[4]).\nAs compared to Euclidean distance, Sørensen distance retains sensitivity\nin more heterogeneous data sets and gives less weight to outliers\n\nThis function assumes you pass in a and b as sets.\n\nThe sorensen index extended to abundance instead of incidence of species is called the Czekanowski index.\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sorensen-index"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "spearmans-rho",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2951",
   :line 2951,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Spearman%27s_rank_correlation_coefficient\n\nIn statistics, Spearman's rank correlation coefficient or Spearman's rho,\nis a non-parametric measure of correlation – that is, it assesses how well\nan arbitrary monotonic function could describe the relationship between two\nvariables, without making any other assumptions about the particular nature\nof the relationship between the variables. Certain other measures of correlation\nare parametric in the sense of being based on possible relationships of a\nparameterised form, such as a linear relationship.\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/spearmans-rho"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "square-devs-from-mean",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2796",
   :line 2796,
   :var-type "function",
   :arglists ([x] [x m]),
   :doc
   "takes either a sample or a sample and a precalculated mean.\nreturns the squares of the difference between each observation and the sample mean.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/square-devs-from-mean"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sum-of-square-devs-from-mean",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2808",
   :line 2808,
   :var-type "function",
   :arglists ([x] [x m]),
   :doc
   "takes either a sample or a sample and a precalculated mean.\n\nreturns the sum of the squares of the difference between each observation and the sample mean.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sum-of-square-devs-from-mean"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sum-variance-test",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3140",
   :line 3140,
   :var-type "function",
   :arglists ([vs]),
   :doc
   "\nThe variance of the sum of n independent variables is equal\nto the sum of their variances.\n\n (variance-independence-test [[1 2 3 4] [1 2 3 4]]) -> 5/2\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sum-variance-test"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "summarizable?",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2647",
   :line 2647,
   :var-type "function",
   :arglists ([col ds]),
   :doc
   "Takes in a column name (or number) and a dataset. Returns true if the column can be summarized, and false otherwise",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/summarizable?"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "summarizer-fn",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2631",
   :line 2631,
   :var-type "function",
   :arglists ([col ds]),
   :doc
   "\nTakes in a column (number or name) and a dataset. Returns a function\nto summarize the column if summarizable, and a string describing why\nthe column can't be summarized in the event that it can't\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/summarizer-fn"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "summary",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2653",
   :line 2653,
   :var-type "function",
   :arglists ([ds]),
   :doc
   "\nTakes in a dataset. Returns a summary of that dataset (as a map of maps),\nhaving automatically figured out the relevant datatypes of columns.\nWill be slightly forgiving of mangled data in columns.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/summary"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "sweep",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1878",
   :line 1878,
   :var-type "function",
   :arglists ([x & {:keys [stat fun], :or {stat mean, fun minus}}]),
   :doc
   "\nReturn an array obtained from an input array by sweeping out a\nsummary statistic. Based to R's sweep function.\n\nArguments:\n  x is an sequence\n\n\nOptions:\n      :stat (default mean) the statistic to sweep out\n      :fun (defaul minus) the function used to sweep the stat out\n\nExample:\n\n  (use '(incanter core stats))\n\n  (def x (sample-normal 30 :mean 10 :sd 5))\n  (sweep x) ;; center the data around mean\n  (sweep x :stat sd :fun div) ;; divide data by its sd\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sweep"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "t-test",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2156",
   :line 2156,
   :var-type "function",
   :arglists
   ([x
     &
     {:keys [y mu paired conf-level alternative var-equal],
      :or
      {paired false,
       alternative :two-sided,
       conf-level 0.95,
       var-equal false}}]),
   :doc
   "\nArgument:\n  x : sample to test\n\nOptions:\n  :y (default nil)\n  :mu (default (mean y) or 0) population mean\n  :alternative (default :two-sided) other choices :less :greater\n  :var-equal TODO (default false) variance equal\n  :paired TODO (default false) paired test\n  :conf-level (default 0.95) for returned confidence interval\n\nExamples:\n\n  (t-test (range 1 11) :mu 0)\n  (t-test (range 1 11) :mu 0 :alternative :less)\n  (t-test (range 1 11) :mu 0 :alternative :greater)\n\n  (t-test (range 1 11) :y (range 7 21))\n  (t-test (range 1 11) :y (range 7 21) :alternative :less)\n  (t-test (range 1 11) :y (range 7 21) :alternative :greater)\n  (t-test (range 1 11) :y (conj (range 7 21) 200))\n\nReferences:\n  http://en.wikipedia.org/wiki/T_test\n  http://www.socialresearchmethods.net/kb/stat_t.php\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/t-test"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "tabulate",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2300",
   :line 2300,
   :var-type "function",
   :arglists ([x & options]),
   :doc
   "\nCross-tabulates the values of the given numeric matrix.\n\nReturns a hash-map with the following fields:\n  :table -- the table of counts for each combination of values,\n            this table is only returned if x has two-columns\n  :levels -- a sequence of sequences, where each sequence list\n             the levels (possible values) of the corresponding\n             column of x.\n  :margins -- a sequence of sequences, where each sequence\n              represents the marginal total for each level\n              of the corresponding column of x.\n  :counts -- a hash-map, where vectors of unique combinations\n             of the cross-tabulated levels are the keys and the\n             values are the total count of each combination.\n  :N  -- the grand-total for the contingency table\n\n\nExamples:\n\n  (use '(incanter core stats))\n  (tabulate [1 2 3 2 3 2 4 3 5])\n  (tabulate (sample-poisson 100 :lambda 5))\n\n  (use '(incanter core stats datasets))\n  (def math-prog (to-matrix (get-dataset :math-prog)))\n  (tabulate (sel math-prog :cols [1 2]))\n\n\n  (def data (matrix [[1 0 1]\n                     [1 1 1]\n                     [1 1 1]\n                     [1 0 1]\n                     [0 0 0]\n                     [1 1 1]\n                     [1 1 1]\n                     [1 0 1]\n                     [1 1 0]]))\n  (tabulate data)\n\n\n  (def data (matrix [[1 0]\n                     [1 1]\n                     [1 1]\n                     [1 0]\n                     [0 0]\n                     [1 1]\n                     [1 1]\n                     [1 0]\n                     [1 1]]))\n  (tabulate data)\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/tabulate"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "tanimoto-coefficient",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L3267",
   :line 3267,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Jaccard_index\n\nThe cosine similarity metric may be extended such that it yields the\nJaccard coefficient in the case of binary attributes.\nThis is the Tanimoto coefficient. ",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/tanimoto-coefficient"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "variance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L1494",
   :line 1494,
   :var-type "function",
   :arglists ([x]),
   :doc
   "\nReturns the sample variance of the data, x. Equivalent to R's var function.\n\nExamples:\n  (variance (sample-normal 100))\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Sample_variance#Population_variance_and_sample_variance\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/variance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj",
   :name "within",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/3869182531cf0dde31396ca8a819fb1583ba5ed9/modules/incanter-core/src/incanter/stats.clj#L2791",
   :line 2791,
   :var-type "function",
   :arglists ([z x y]),
   :doc "y is within z of x in metric space.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/within"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/559d16abd0a7811e6b1e3965d5ebbb823821b5bb/modules/incanter-svg/src/incanter/svg.clj",
   :name "save-svg",
   :file "modules/incanter-svg/src/incanter/svg.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/559d16abd0a7811e6b1e3965d5ebbb823821b5bb/modules/incanter-svg/src/incanter/svg.clj#L13",
   :line 13,
   :var-type "function",
   :arglists
   ([chart
     filename
     &
     {:keys [width height], :or {width 500, height 400}}]),
   :doc
   "\nSave a chart object as an SVG document.\n\nAs with incanter.core/save, a java.io.OutputStream can be used in place of a\nfilename.\n\nArguments:\n  chart\n  filename\n\nOptions:\n  :width (default 500)\n  :height (default 400)\n\nExamples:\n\n  (use '(incanter core charts svg))\n  (save-svg (function-plot sin -4 4) \"./svg-chart.svg\")\n\n",
   :namespace "incanter.svg",
   :wiki-url
   "http://incanter.github.com/incanter//svg-api.html#incanter.svg/save-svg"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/symbolic.clj",
   :name "deriv",
   :file "modules/incanter-core/src/incanter/symbolic.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/symbolic.clj#L144",
   :line 144,
   :var-type "macro",
   :arglists ([exp v] [exp v degree]),
   :doc
   "\nMacro for symbolic differentiation. with 2 args, takes 1st degree deriv.\nwith 3, takes arbitrary degrees. contains all deriv rules for basic funcs.\n\n\nExamples:\n\n  (use '(incanter core symbolic))\n\n  (deriv (+ x 3) x) ; => 1\n  (deriv (* x y) x) ; => y\n  (deriv (* (* x y) (+ x 3)) x) ; => (+ (* (+ x 3) y) (* x y))\n  (deriv (* (* x y) (+ x 3)) y) ; => (* (+ x 3) x)\n\n  (deriv (* x y (+ x 3)) x) ; => (+ (* y (+ x 3)) (* y x))\n  (deriv (* x y (+ x 3)) y) ; => (* (+ x 3) x)\n\n  (deriv (sin x) x) ; => (cos x)\n  (deriv (cos x) x) ; => (* -1 (sin x))\n\n  (deriv (sin (* x y)) y) ; => (* x (cos (* x y)))\n\n  (deriv (pow x 3) x) ; => (* 3 (pow x 2))\n  (deriv (** x 3) x) ; => (* 3 (pow x 2))\n\n  (deriv (pow x 3) x 2) ; => (* 3 (* 2 x))\n\n  (deriv (* x y (+ x 3)) x 2) ; => (+ y y)\n  (deriv (* x y (+ x 3)) x 3) ; => 0\n\n  (deriv (+ (* 3 x) (* 8 x)) x) ; => 11\n\n\n\n  ;; NOT WORKING YET\n\n  (deriv (/ 1 x) x) ; => (* (deriv* (* (x)) x) (* -1 (pow (* (x)) -2)))\n                                        ^-- need to fix",
   :namespace "incanter.symbolic",
   :wiki-url
   "http://incanter.github.com/incanter//symbolic-api.html#incanter.symbolic/deriv"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/symbolic.clj",
   :name "deriv*",
   :file "modules/incanter-core/src/incanter/symbolic.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/symbolic.clj#L79",
   :line 79,
   :var-type "function",
   :arglists ([exp v] [exp vr degree]),
   :doc
   "\nMain sub-function for differentiation. with 2 args, takes 1st degree deriv.\nwith 3, takes arbitrary degrees. contains all deriv rules for basic funcs.\n\n\nExamples:\n\n  (use '(incanter core symbolic))\n\n  (deriv* '(+ x 3) 'x)\n  (deriv* '(* x y) 'x)\n  (deriv* '(* (* x y) '(+ x 3)) x)\n  (deriv* '(* (* x y) (+ x 3)) 'y)\n\n  (deriv* '(* x y (+ x 3)) 'x)\n  (deriv* '(* x y (+ x 3)) 'y)\n\n  (deriv* '(* x y (+ x 3)) 'x 2)\n  (deriv* '(* x y (+ x 3)) 'x 3)\n",
   :namespace "incanter.symbolic",
   :wiki-url
   "http://incanter.github.com/incanter//symbolic-api.html#incanter.symbolic/deriv*"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/symbolic.clj",
   :name "deriv-fn",
   :file "modules/incanter-core/src/incanter/symbolic.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/symbolic.clj#L262",
   :line 262,
   :var-type "macro",
   :arglists ([[& args] expr v] [[& args] expr v degree]),
   :doc
   "\nExamples:\n  (use '(incanter core symbolic))\n\n  (deriv-fn [x y] (+ (* x y) x) x)\n\n  ((deriv-fn [x y] (+ (* x y) x) x) 5 9)\n\n  (use 'incanter.charts)\n  (doto (function-plot sin -5 5)\n     (add-function (deriv-fn [x] (sin x) x) -5 5)\n     (add-function (deriv-fn [x] (sin x) x 2) -5 5)\n     view)\n\n  (let [f (fn [x] (pow x 2))\n        df (deriv-fn [x] (pow x 2) x)]\n    (doto (function-plot f -5 5)\n      (add-function df -5 5)\n      view))\n\n\n  (let [f (fn [x] (pow x 3))\n        df (deriv-fn [x] (pow x 3) x)]\n    (doto (function-plot f -5 5)\n      (add-function df -5 5)\n      view))\n\n\n  ;; NOT WORKING YET\n\n  (let [f (fn [x] (/ 1 x ))\n        df (deriv-fn [x] (/ 1 x) x)]\n    (doto (function-plot f 0.5 5)\n      (add-function df 0.5 5)\n      view))\n",
   :namespace "incanter.symbolic",
   :wiki-url
   "http://incanter.github.com/incanter//symbolic-api.html#incanter.symbolic/deriv-fn"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/symbolic.clj",
   :name "deriv-fn*",
   :file "modules/incanter-core/src/incanter/symbolic.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/symbolic.clj#L236",
   :line 236,
   :var-type "function",
   :arglists ([[& args] expr v] [[& args] expr v degree]),
   :doc
   "\nExamples:\n  (use '(incanter core symbolic))\n\n  (deriv-fn* '[x y] '(+ (* x y) x) 'x)\n\n  ((deriv-fn* '[x y] '(+ (* x y) x) 'x) 5 9)\n",
   :namespace "incanter.symbolic",
   :wiki-url
   "http://incanter.github.com/incanter//symbolic-api.html#incanter.symbolic/deriv-fn*"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "$$",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L186",
   :line 186,
   :var-type "function",
   :arglists ([ind ts] [ind cols ts] [ind-1 ind-2 cols ts]),
   :doc
   "\nThis is the equivalent of :: in xts. That is, it slices out\nthe timeseries between ind-1 and ind-2. These are any values\nthat can be coerced into clj-time values.\n",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/$$"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "aligned?",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L125",
   :line 125,
   :var-type "function",
   :arglists ([& zs]),
   :doc "Is the :index column identical for all zs.",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/aligned?"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "coredata",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L97",
   :line 97,
   :var-type "function",
   :arglists ([x]),
   :doc
   "\nReturn the :rows of a dataset, with :index dissoc'd.\nIntended to be used internally time series function to get at data.\n",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/coredata"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "lag",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L216",
   :line 216,
   :var-type "function",
   :arglists ([z] [z n]),
   :doc
   "\nReturn the timeseries lagged by n units or 1 if not specified.\nNo time calculations are made in the index column. The output\ntimeseries is of the same length as the input.\n",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/lag"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "roll-apply",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L54",
   :line 54,
   :var-type "function",
   :arglists ([f n coll]),
   :doc
   "\nA generic function for applying a function to rolling window of a collection.\n\nArguments:\nf -- function to be applied\nn -- size of rolling window\ncoll -- collection of data\n",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/roll-apply"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "roll-max",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L75",
   :line 75,
   :var-type "function",
   :arglists ([n coll]),
   :doc "\nReturns the rolling max of the previous n elements.\n",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/roll-max"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "roll-mean",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L40",
   :line 40,
   :var-type "function",
   :arglists ([n coll]),
   :doc
   "\nReturns the unweighted mean of the previous n data points.\n\nReferences:\nhttp://en.wikipedia.org/wiki/Moving_average#Simple_moving_average\nhttp://www.learningclojure.com/2010/03/moving-average-of-list.html\n",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/roll-mean"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "roll-median",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L68",
   :line 68,
   :var-type "function",
   :arglists ([n coll]),
   :doc "\nReturns the rolling median of the previous n elements.\n",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/roll-median"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "roll-min",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L82",
   :line 82,
   :var-type "function",
   :arglists ([n coll]),
   :doc "\nReturns the rolling min of the previous n elements.\n",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/roll-min"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "within-zoo?",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L151",
   :line 151,
   :var-type "function",
   :arglists ([t z]),
   :doc "Is t between the first and last indices.",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/within-zoo?"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "zoo",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L158",
   :line 158,
   :var-type "function",
   :arglists ([x] [x index-col]),
   :doc
   "\nReturn the given dataset as a zoo value which is simply a dataset\nthat contains an column of clj-time values specified by index-col,\ndefault :index. That column must contain values that can be coerced\ninto Jodas using the TimeCoercible Protocol.\n",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/zoo"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "zoo-apply",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L234",
   :line 234,
   :var-type "function",
   :arglists ([f n zoo column & args]),
   :doc
   "\nBehave as for roll-apply but accept a zoo and a single column\nupon which to roll-apply f. Returns a zoo of the same length\nas input zoo with pre-pended nils\n",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/zoo-apply"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "zoo-row-map",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L263",
   :line 263,
   :var-type "function",
   :arglists ([f & zs]),
   :doc
   "\nAccept a number of aligned zoo object and pass them row-wise\ninto f, return a zoo. f must accept and return maps. The :index\ncolumn is stripped out before f is applied, and then replaced\nafterwards with the :index of the first.\n",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/zoo-row-map"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "zoo-row-map-",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L249",
   :line 249,
   :var-type "function",
   :arglists ([f & zs]),
   :doc
   "\nAccept a number of aligned zoo object and pass them row-wise into f,\nreturn a seq of maps of the output of the output.\nf must accept and return maps. The :index column is stripped out before\nf is applied, and then replaced afterwards.\n",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/zoo-row-map-"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "zoo-row-map-occupied",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L278",
   :line 278,
   :var-type "function",
   :arglists ([f & s]),
   :doc
   "zoo-row-map- and remove the empties. This returns a seq of maps",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/zoo-row-map-occupied"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/bccaf39c8258cedf1a6522a52aa6d9129b22e458/modules/incanter-excel/src/incanter/excel/cells.clj",
   :name "get-cell-formula-value",
   :file "modules/incanter-excel/src/incanter/excel/cells.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/bccaf39c8258cedf1a6522a52aa6d9129b22e458/modules/incanter-excel/src/incanter/excel/cells.clj#L14",
   :line 14,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "Get the value after the evaluating the formula.  See http://poi.apache.org/spreadsheet/eval.html#Evaluate",
   :namespace "incanter.excel.cells",
   :wiki-url
   "http://incanter.github.com/incanter//excel-api.html#incanter.excel.cells/get-cell-formula-value"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/bccaf39c8258cedf1a6522a52aa6d9129b22e458/modules/incanter-excel/src/incanter/excel/cells.clj",
   :name "get-cell-value",
   :file "modules/incanter-excel/src/incanter/excel/cells.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/bccaf39c8258cedf1a6522a52aa6d9129b22e458/modules/incanter-excel/src/incanter/excel/cells.clj#L20",
   :line 20,
   :var-type "multimethod",
   :arglists nil,
   :doc "Get the cell value depending on the cell type.",
   :namespace "incanter.excel.cells",
   :wiki-url
   "http://incanter.github.com/incanter//excel-api.html#incanter.excel.cells/get-cell-value"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/bccaf39c8258cedf1a6522a52aa6d9129b22e458/modules/incanter-excel/src/incanter/excel/workbook.clj",
   :name "get-workbook-sheet",
   :file "modules/incanter-excel/src/incanter/excel/workbook.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/bccaf39c8258cedf1a6522a52aa6d9129b22e458/modules/incanter-excel/src/incanter/excel/workbook.clj#L8",
   :line 8,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "Retrieve the Excel workbook based on either the index or the sheet name.",
   :namespace "incanter.excel.workbook",
   :wiki-url
   "http://incanter.github.com/incanter//excel-api.html#incanter.excel.workbook/get-workbook-sheet"})}
