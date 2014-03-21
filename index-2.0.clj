{:namespaces
 ({:source-url nil,
   :wiki-url "http://liebke.github.com/incanter/bayes-api.html",
   :name "incanter.bayes",
   :author "David Edgar Liebke",
   :doc
   "This is library provides functions for performing\nbasic Bayesian modeling and inference.\n"}
  {:source-url nil,
   :wiki-url "http://liebke.github.com/incanter/censored-api.html",
   :name "incanter.censored",
   :doc
   "Statistical functions for work with 'censored' (truncated) distributions."}
  {:source-url nil,
   :wiki-url "http://liebke.github.com/incanter/charts-api.html",
   :name "incanter.charts",
   :author "David Edgar Liebke",
   :doc
   "This is the core charting library for Incanter.\nIt provides basic scatter plots, histograms, box plots\nxy plots, bar charts, line charts, as well as\nspecialized charts like trace plots and Bland-Altman\nplots.\n\nThis library is built on the JFreeChart library\n(http://www.jfree.org/jfreechart/).\n"}
  {:source-url nil,
   :wiki-url "http://liebke.github.com/incanter/core-api.html",
   :name "incanter.core",
   :author "David Edgar Liebke",
   :doc
   "This is the core numerics library for Incanter.\nIt provides functions for vector- and matrix-based\nmathematical operations and the core data manipulation\nfunctions for Incanter.\n\nThis library is built on Clatrix (https://github.com/tel/clatrix)\nand Parallel Colt\n(http://sites.google.com/site/piotrwendykier/software/parallelcolt)\nan extension of the Colt numerics library\n(http://acs.lbl.gov/~hoschek/colt/).\n"}
  {:source-url nil,
   :wiki-url "http://liebke.github.com/incanter/datasets-api.html",
   :name "incanter.datasets",
   :doc
   "Provides access to different datasets that are bundled with Incanter."}
  {:source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter/distributions-api.html",
   :name "incanter.distributions",
   :author "Mark M. Fredrickson and William Leung",
   :doc
   "Probability functions (pdf, cdf, draw, etc.) for common distributions, and for collections, sets, and maps."}
  {:source-url nil,
   :wiki-url "http://liebke.github.com/incanter/excel-api.html",
   :name "incanter.excel",
   :author "David James Humphreys",
   :doc
   "Excel module for reading and writing Incanter datasets.  Recognizes both old and new\nExcel file formats (.xls and .xlsx)."}
  {:source-url nil,
   :wiki-url "http://liebke.github.com/incanter/infix-api.html",
   :name "incanter.infix",
   :author "J. Bester",
   :doc
   "Library for converting infix mathematical formula to prefix expressions"}
  {:source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter/interpolation-api.html",
   :name "incanter.interpolation",
   :author "Nikita Beloglazov",
   :doc
   "Interpolation and approximation of collection of points..\nSupported types: linear, polynomial, cubic spline,\ncubic hermite spline, B-spline, linear least squares.\nSupports 1-dimensional and 2-dimensional interpolations."}
  {:source-url nil,
   :wiki-url "http://liebke.github.com/incanter/io-api.html",
   :name "incanter.io",
   :doc
   "Library for reading and writing Incanter datasets and matrices."}
  {:source-url nil,
   :wiki-url "http://liebke.github.com/incanter/latex-api.html",
   :name "incanter.latex",
   :author "David Edgar Liebke",
   :doc
   "This library is used to render LaTex Math equations, based\non the jLateXMath library, and applying them incanter.charts as annotations\nand subtitles."}
  {:source-url nil,
   :wiki-url "http://liebke.github.com/incanter/mongodb-api.html",
   :name "incanter.mongodb",
   :author "David Edgar Liebke",
   :doc
   "A simple library that provides functions for persisting\nIncanter data structures using MongoDB.\n\nUse incanter.mongodb in combination with the somnium.congomongo library.\nFor usage examples, see the Congomongo README at http://github.com/somnium/congomongo,\nand the examples/blog/mongodb_datasets.clj file in the Incanter distribution.\n\nHere are Somnium's descriptions of Congomongo's functions:\n\n  (mongo! & args) : Creates a Mongo object and sets the default database.\n     Keyword arguments include:\n     :host -> defaults to localhost\n     :port -> defaults to 27017\n     :db   -> defaults to nil (you'll have to set it anyway, might as well do it now.)\n\n  (get-coll coll) : Returns a DBCollection object\n\n  (fetch coll & options) : Fetches objects from a collection. Optional arguments include\n   :where  -> takes a query map\n   :only   -> takes an array of keys to retrieve\n   :as     -> what to return, defaults to :clojure, can also be :json or :mongo\n   :from   -> argument type, same options as above\n   :one?   -> defaults to false, use fetch-one as a shortcut\n   :count? -> defaults to false, use fetch-count as a shortcut\n\n  (fetch-one coll & options) : same as (fetch collection :one? true)\n\n  (fetch-count coll & options) : same as (fetch collection :count? true)\n\n  (insert! coll obj & options) : Inserts a map into collection. Will not overwrite existing maps.\n   Takes optional from and to keyword arguments. To insert\n   as a side-effect only specify :to as nil.\n\n  (mass-insert! coll objs & options) : Inserts a sequence of maps.\n\n  (update! coll old new & options) : Alters/inserts a map in a collection. Overwrites existing objects.\n   The shortcut forms need a map with valid :_id and :_ns fields or\n   a collection and a map with a valid :_id field.\n\n  (destroy! coll query-map) : Removes map from collection. Takes a collection name and\n    a query map\n\n  (add-index! coll fields & options) : Adds an index on the collection for the specified fields if it does not exist.\n    Options include:\n    :unique -> defaults to false\n    :force  -> defaults to true\n\n  (drop-index! coll fields) : Drops an index on the collection for the specified fields\n\n  (drop-all-indexes! coll) : Drops all indexes from a collection\n\n  (get-indexes coll & options) : Get index information on collection\n\n  (drop-database title) : drops a database from the mongo server\n\n  (set-database title) : atomically alters the current database\n\n  (databases) : List databases on the mongo server\n\n  (collections) : Returns the set of collections stored in the current database\n\n  (drop-collection coll) : Permanently deletes a collection. Use with care."}
  {:source-url nil,
   :wiki-url "http://liebke.github.com/incanter/optimize-api.html",
   :name "incanter.optimize",
   :doc "Optimization-relates functions."}
  {:source-url nil,
   :wiki-url "http://liebke.github.com/incanter/pdf-api.html",
   :name "incanter.pdf",
   :doc
   "This library currently has only a single function, save-pdf, which saves\ncharts as a PDF file."}
  {:source-url nil,
   :wiki-url "http://liebke.github.com/incanter/som-api.html",
   :name "incanter.som",
   :doc "Self-Organizing-Map Neural Network Library."}
  {:source-url nil,
   :wiki-url "http://liebke.github.com/incanter/sql-api.html",
   :name "incanter.sql",
   :doc "SQL module for interacting with databases."}
  {:source-url nil,
   :wiki-url "http://liebke.github.com/incanter/stats-api.html",
   :name "incanter.stats",
   :author "David Edgar Liebke and Bradford Cross",
   :doc
   "This is the core statistical library for Incanter.\nIt provides probability functions (cdf, pdf, quantile),\nrandom number generation, statistical tests, basic\nmodeling functions, similarity/association measures,\nand more.\n\nThis library is built on Parallel Colt\n(http://sites.google.com/site/piotrwendykier/software/parallelcolt),\nan extension of the Colt numerics library\n(http://acs.lbl.gov/~hoschek/colt/).\n"}
  {:source-url nil,
   :wiki-url "http://liebke.github.com/incanter/svg-api.html",
   :name "incanter.svg",
   :doc
   "This library currently has only a single function, save-svg, which saves\ncharts as an SVG file."}
  {:source-url nil,
   :wiki-url "http://liebke.github.com/incanter/symbolic-api.html",
   :name "incanter.symbolic",
   :author "Bryce Nyeggen, with modifications by David Edgar Liebke",
   :doc
   "A library for performing symbolic math, a port from SICP (http://mitpress.mit.edu/sicp/)."}
  {:source-url nil,
   :wiki-url "http://liebke.github.com/incanter/zoo-api.html",
   :name "incanter.zoo",
   :author "David Edgar Liebke",
   :doc
   "This is a port of Zoo from R in order to create the basis\nof a library for time series data.\n\nThis library is built on Parallel Colt\n(http://sites.google.com/site/piotrwendykier/software/parallelcolt),\nan extension of the Colt numerics library\n(http://acs.lbl.gov/~hoschek/colt/)."}
  {:source-url nil,
   :wiki-url "http://liebke.github.com/incanter/excel.cells-api.html",
   :name "incanter.excel.cells",
   :doc "Functions for reading and writing to cells."}
  {:source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter/excel.workbook-api.html",
   :name "incanter.excel.workbook",
   :doc nil}),
 :vars
 ({:arglists ([size {:keys [x y coefs residuals]}]),
   :name "sample-model-params",
   :namespace "incanter.bayes",
   :source-url
   "https://github.com/liebke/incanter/blob/496cb8a2fd28e8eaec83dc70aca0453fe4fe5b6a/modules/incanter-core/src/incanter/bayes.clj#L35",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/496cb8a2fd28e8eaec83dc70aca0453fe4fe5b6a/modules/incanter-core/src/incanter/bayes.clj",
   :wiki-url
   "http://liebke.github.com/incanter//bayes-api.html#incanter.bayes/sample-model-params",
   :doc
   "\nReturns a sample of the given size of the the parameters (coefficients and\nerror variance) of the given linear-model. The sample is generated using\nGibbs sampling.\n\nSee also:\n  incanter.stats/linear-model\n\nExamples:\n  (use '(incanter core datasets stats charts bayes))\n\n  (def ols-data (to-matrix (get-dataset :survey)))\n  (def x (sel ols-data (range 0 2313) (range 1 10)))\n  (def y (sel ols-data (range 0 2313) 10))\n  (def lm (linear-model y x :intercept false))\n  (def param-samp (sample-model-params 5000 lm))\n\n  ;; view trace plots\n  (view (trace-plot (:var param-samp )))\n  (view (trace-plot (sel (:coefs param-samp) :cols 0)))\n\n  ;; view histograms\n  (view (histogram (:var param-samp)))\n  (view (histogram (sel (:coefs param-samp) :cols 0)))\n\n  ;; calculate statistics\n  (map mean (trans (:coefs param-samp)))\n  (map median (trans (:coefs param-samp)))\n  (map sd (trans (:coefs param-samp)))\n\n  ;; show the 95% bayesian confidence interval for the first coefficient\n  (quantile (sel (:coefs param-samp) :cols 0) :probs [0.025 0.975])\n",
   :var-type "function",
   :line 35,
   :file "modules/incanter-core/src/incanter/bayes.clj"}
  {:arglists ([size counts]),
   :name "sample-multinomial-params",
   :namespace "incanter.bayes",
   :source-url
   "https://github.com/liebke/incanter/blob/496cb8a2fd28e8eaec83dc70aca0453fe4fe5b6a/modules/incanter-core/src/incanter/bayes.clj#L95",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/496cb8a2fd28e8eaec83dc70aca0453fe4fe5b6a/modules/incanter-core/src/incanter/bayes.clj",
   :wiki-url
   "http://liebke.github.com/incanter//bayes-api.html#incanter.bayes/sample-multinomial-params",
   :doc
   "\nReturns a sample of multinomial proportion parameters.\nThe counts are assumed to have a multinomial distribution.\nA uniform prior distribution is assigned to the multinomial vector\ntheta, then the posterior distribution of theta is\nproportional to a dirichlet distribution with parameters\n(plus counts 1).\n\n\nExamples:\n  (use '(incanter core stats bayes charts))\n\n  (def  samp-props (sample-multinomial-params 1000 [727 583 137]))\n\n  ;; view means, 95% CI, and histograms of the proportion parameters\n  (mean (sel samp-props :cols 0))\n  (quantile (sel samp-props :cols 0) :probs [0.0275 0.975])\n  (view (histogram (sel samp-props :cols 0)))\n  (mean (sel samp-props :cols 1))\n  (quantile (sel samp-props :cols 1) :probs [0.0275 0.975])\n  (view (histogram (sel samp-props :cols 1)))\n  (mean (sel samp-props :cols 2))\n  (quantile (sel samp-props :cols 2) :probs [0.0275 0.975])\n  (view (histogram (sel samp-props :cols 2)))\n\n  ;; view  a histogram of the difference in proportions between the first\n  ;; two candidates\n  (view (histogram (minus (sel samp-props :cols 0) (sel samp-props :cols 1))))\n",
   :var-type "function",
   :line 95,
   :file "modules/incanter-core/src/incanter/bayes.clj"}
  {:arglists ([size y & options]),
   :name "sample-mvn-params",
   :namespace "incanter.bayes",
   :source-url
   "https://github.com/liebke/incanter/blob/496cb8a2fd28e8eaec83dc70aca0453fe4fe5b6a/modules/incanter-core/src/incanter/bayes.clj#L131",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/496cb8a2fd28e8eaec83dc70aca0453fe4fe5b6a/modules/incanter-core/src/incanter/bayes.clj",
   :wiki-url
   "http://liebke.github.com/incanter//bayes-api.html#incanter.bayes/sample-mvn-params",
   :doc
   "\nReturns samples of means (sampled from an mvn distribution) and vectorized covariance\nmatrices (sampled from an inverse-wishart distribution) for the given mvn data.\n\nArguments:\n  size -- the number of samples to return\n  y -- the data used to estimate the parameters\n\n\nReturns map with following fields:\n  :means\n  :sigmas\n\n\nExamples:\n\n  (use '(incanter core stats bayes charts))\n  (def y (sample-mvn 500 :mean [0 0] :sigma (identity-matrix 2)))\n  (def samp (sample-mvn-params 1000 y))\n\n  (map mean (trans (:means samp)))\n  (symmetric-matrix (map mean (trans (:sigmas samp))) :lower false)\n\n  (view (histogram (sel (:means samp) :cols 0) :x-label \"mean 1\"))\n  (view (histogram (sel (:means samp) :cols 1) :x-label \"mean 2\"))\n  (view (histogram (sel (:sigmas samp) :cols 1) :x-label \"covariance\"))\n  (view (histogram (sel (:sigmas samp) :cols 0) :x-label \"variance 1\"))\n  (view (histogram (sel (:sigmas samp) :cols 2) :x-label \"variance 2\"))\n\n  (map #(quantile % :probs [0.025 0.0975]) (trans (:means samp)))\n  (map #(quantile % :probs [0.025 0.0975]) (trans (:sigmas samp)))\n\n\n\n\n  (use '(incanter core stats bayes charts))\n  (def y (sample-mvn 500 :sigma (symmetric-matrix [10 5 10]) :mean [5 2]))\n  (def samp (sample-mvn-params 1000 y))\n  (symmetric-matrix (map mean (trans (:sigmas samp))) :lower false)\n  (map mean (trans (:means samp)))\n",
   :var-type "function",
   :line 131,
   :file "modules/incanter-core/src/incanter/bayes.clj"}
  {:arglists ([size counts]),
   :name "sample-proportions",
   :namespace "incanter.bayes",
   :source-url
   "https://github.com/liebke/incanter/blob/496cb8a2fd28e8eaec83dc70aca0453fe4fe5b6a/modules/incanter-core/src/incanter/bayes.clj#L88",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/496cb8a2fd28e8eaec83dc70aca0453fe4fe5b6a/modules/incanter-core/src/incanter/bayes.clj",
   :wiki-url
   "http://liebke.github.com/incanter//bayes-api.html#incanter.bayes/sample-proportions",
   :doc
   "sample-proportions has been renamed sample-multinomial-params",
   :var-type "function",
   :line 88,
   :file "modules/incanter-core/src/incanter/bayes.clj"}
  {:arglists ([a mu sigma]),
   :name "censored-mean-lower",
   :namespace "incanter.censored",
   :source-url
   "https://github.com/liebke/incanter/blob/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj#L59",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj",
   :wiki-url
   "http://liebke.github.com/incanter//censored-api.html#incanter.censored/censored-mean-lower",
   :doc
   "\nReturns the mean of a normal distribution (with mean mu and standard\ndeviation sigma) with the lower tail censored at 'a'\n",
   :var-type "function",
   :line 59,
   :file "modules/incanter-core/src/incanter/censored.clj"}
  {:arglists ([a b mu sigma]),
   :name "censored-mean-two-sided",
   :namespace "incanter.censored",
   :source-url
   "https://github.com/liebke/incanter/blob/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj#L22",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj",
   :wiki-url
   "http://liebke.github.com/incanter//censored-api.html#incanter.censored/censored-mean-two-sided",
   :doc
   "\nReturns the mean of a normal distribution (with mean mu and standard\ndeviation sigma) with the lower tail censored at 'a' and the upper\ntail censored at 'b'\n",
   :var-type "function",
   :line 22,
   :file "modules/incanter-core/src/incanter/censored.clj"}
  {:arglists ([b mu sigma]),
   :name "censored-mean-upper",
   :namespace "incanter.censored",
   :source-url
   "https://github.com/liebke/incanter/blob/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj#L89",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj",
   :wiki-url
   "http://liebke.github.com/incanter//censored-api.html#incanter.censored/censored-mean-upper",
   :doc
   "\nReturns the mean of a normal distribution (with mean mu and standard\ndeviation sigma) with the upper tail censored at 'b'\n",
   :var-type "function",
   :line 89,
   :file "modules/incanter-core/src/incanter/censored.clj"}
  {:arglists ([a mu sigma]),
   :name "censored-variance-lower",
   :namespace "incanter.censored",
   :source-url
   "https://github.com/liebke/incanter/blob/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj#L71",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj",
   :wiki-url
   "http://liebke.github.com/incanter//censored-api.html#incanter.censored/censored-variance-lower",
   :doc
   "\nReturns the variance of a normal distribution (with mean mu and standard\ndeviation sigma) with the lower tail censored at 'a'\n",
   :var-type "function",
   :line 71,
   :file "modules/incanter-core/src/incanter/censored.clj"}
  {:arglists ([a b mu sigma]),
   :name "censored-variance-two-sided",
   :namespace "incanter.censored",
   :source-url
   "https://github.com/liebke/incanter/blob/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj#L38",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj",
   :wiki-url
   "http://liebke.github.com/incanter//censored-api.html#incanter.censored/censored-variance-two-sided",
   :doc
   "\nReturns the variance of a normal distribution (with mean mu and standard\ndeviation sigma) with the lower tail censored at 'a' and the upper\ntail censored at 'b'\n",
   :var-type "function",
   :line 38,
   :file "modules/incanter-core/src/incanter/censored.clj"}
  {:arglists ([b mu sigma]),
   :name "censored-variance-upper",
   :namespace "incanter.censored",
   :source-url
   "https://github.com/liebke/incanter/blob/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj#L100",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj",
   :wiki-url
   "http://liebke.github.com/incanter//censored-api.html#incanter.censored/censored-variance-upper",
   :doc
   "\nReturns the variance of a normal distribution (with mean mu and standard\ndeviation sigma) with the upper tail censored at 'b'\n",
   :var-type "function",
   :line 100,
   :file "modules/incanter-core/src/incanter/censored.clj"}
  {:arglists
   ([&
     {:keys [mean sd a b],
      :or
      {mean 0,
       sd 1,
       a Double/NEGATIVE_INFINITY,
       b Double/POSITIVE_INFINITY}}]),
   :name "truncated-variance",
   :namespace "incanter.censored",
   :source-url
   "https://github.com/liebke/incanter/blob/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj#L118",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/ae5a82125e364e1a27314abb0856bc23f5473766/modules/incanter-core/src/incanter/censored.clj",
   :wiki-url
   "http://liebke.github.com/incanter//censored-api.html#incanter.censored/truncated-variance",
   :doc
   "\nReturns the variance of a normal distribution truncated at a and b.\n\nOptions:\n  :mean (default 0) mean of untruncated normal distribution\n  :sd (default 1) standard deviation of untruncated normal distribution\n  :a (default -infinity) lower truncation point\n  :b (default +infinity) upper truncation point\n\nExamples:\n\n  (use '(incanter core stats))\n  (truncated-variance :a -1.96 :b 1.96)\n  (truncated-variance :a 0)\n  (truncated-variance :b 0)\n\n  (use 'incanter.charts)\n  (def x (range -3 3 0.1))\n  (def plot (xy-plot x (map #(truncated-variance :a %) x)))\n  (view plot)\n  (add-lines plot x (map #(truncated-variance :b %) x))\n\n  (def samp (sample-normal 10000))\n  (add-lines plot x (map #(variance (filter (fn [s] (> s %)) samp)) x))\n  (add-lines plot x (map #(variance (mult samp (indicator (fn [s] (> s %)) samp))) x))\n\n\nReferences:\n  DeMaris, A. (2004) Regression with social data: modeling continuous and limited response variables.\n    Wiley-IEEE.\n\n  http://en.wikipedia.org/wiki/Truncated_normal_distribution\n",
   :var-type "function",
   :line 118,
   :file "modules/incanter-core/src/incanter/censored.clj"}
  {:arglists ([chart x & options]),
   :name "add-box-plot",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L410",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/add-box-plot",
   :doc
   "\nAdds an additional box to an existing box-plot, returns the modified chart object.\n\nOptions:\n  :series-label (default x expression)\n\nExamples:\n\n  (use '(incanter core charts stats datasets))\n  (doto (box-plot (sample-normal 1000) :legend true)\n        view\n        (add-box-plot (sample-normal 1000 :sd 2))\n        (add-box-plot (sample-gamma 1000)))\n\n\n  (with-data (get-dataset :iris)\n    (doto (box-plot :Sepal.Length :legend true)\n      (add-box-plot :Petal.Length)\n      (add-box-plot :Sepal.Width)\n      (add-box-plot :Petal.Width)\n      view))\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :var-type "macro",
   :line 410,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([chart categories values & options]),
   :name "add-categories",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L473",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/add-categories",
   :doc
   "\nAdds an additional categories to an existing bar-chart or line-chart, returns the modified chart object.\n\nOptions:\n  :group-by\n  :series-label\n\nExamples:\n\n  (use '(incanter core charts stats datasets))\n  (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))\n  (def years (mapcat identity (repeat 4 [2007 2008 2009])))\n  (def x (sample-uniform 12 :integers true :max 100))\n  (def plot (bar-chart years x :group-by seasons :legend true))\n  (view plot)\n  (add-categories plot years [10 20 40] :series-label \"winter-break\")\n\n  (add-categories plot\n                  (plus 3 years)\n                  (sample-uniform 12 :integers true :max 100)\n                  :group-by seasons)\n\n  (def plot2 (line-chart years x :group-by seasons :legend true))\n    (view plot2)\n    (add-categories plot2 (plus 3 years) (sample-uniform 12 :integers true :max 100) :group-by seasons)\n\n    (with-data (get-dataset :iris)\n      (doto (line-chart :Species :Sepal.Length\n                        :data ($rollup mean :Sepal.Length :Species)\n                        :legend true)\n        (add-categories :Species :Sepal.Width :data ($rollup mean :Sepal.Width :Species))\n        (add-categories :Species :Petal.Length :data ($rollup mean :Petal.Length :Species))\n        (add-categories :Species :Petal.Width :data ($rollup mean :Petal.Width :Species))\n        view))\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :var-type "macro",
   :line 473,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([chart function min-range max-range & options]),
   :name "add-function",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L653",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/add-function",
   :doc
   "\nAdds a xy-plot of the given function to the given chart, returning\na modified version of the chart.\n\nOptions:\n  :series-label (default x expression)\n  :step-size (default (/ (- max-range min-range) 500))\n\nSee also:\n  function-plot, view, save, add-function, add-points, add-lines\n\n\nExamples:\n\n  (use '(incanter core stats charts))\n\n  ;; plot the sine and cosine functions\n  (doto (function-plot sin (- Math/PI) Math/PI)\n        (add-function cos (- Math/PI) Math/PI)\n        view)\n\n\n  ;; plot two normal pdf functions\n  (doto (function-plot pdf-normal -3 3 :legend true)\n        (add-function (fn [x] (pdf-normal x :mean 0.5 :sd 0.5)) -3 3)\n        view)\n\n\n  ;; plot a user defined function and its derivative\n  (use '(incanter core charts optimize))\n\n  ;; define the function, x^3 + 2x^2 + 2x + 3\n  (defn cubic [x] (+ (* x x x) (* 2 x x) (* 2 x) 3))\n\n  ;; use the derivative function to get a function\n  ;; that approximates its derivative\n  (def deriv-cubic (derivative cubic))\n\n  ;; plot the cubic function and its derivative\n  (doto (function-plot cubic -10 10)\n        (add-function deriv-cubic -10 10)\n        view)\n\n",
   :var-type "macro",
   :line 653,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([chart x & options]),
   :name "add-histogram",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L359",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/add-histogram",
   :doc
   "\nAdds a histogram to an existing histogram plot, returns the modified\nchart object.\n\nOptions:\n  :nbins (default 10) number of bins for histogram\n  :series-label (default x expression)\n\nExamples:\n\n  (use '(incanter core charts stats datasets))\n  (doto (histogram (sample-normal 1000)\n                   :legend true)\n        view\n        (add-histogram (sample-normal 1000 :sd 0.5)))\n\n\n  (with-data (get-dataset :iris)\n    (doto (histogram :Sepal.Length :legend true)\n      (add-histogram :Petal.Length)\n      view))\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :var-type "macro",
   :line 359,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([chart x y img & options]),
   :name "add-image",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L3182",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/add-image",
   :doc
   "\nAdds an image to the chart at the given coordinates.\n\nArguments:\n  chart -- the chart to add the polygon to.\n  x, y -- the coordinates to place the image\n  img -- a java.awt.Image object\n\n\nExamples:\n  (use '(incanter core charts latex))\n\n   (doto (function-plot sin -10 10)\n    (add-image 0 0 (latex \"\\\\frac{(a+b)^2} {(a-b)^2}\"))\n    view)\n\n",
   :var-type "function",
   :line 3182,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([chart x y & options]),
   :name "add-lines",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L585",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/add-lines",
   :doc
   "\nPlots lines on the given scatter or line plot (xy-plot) of the (x,y) points.\nEquivalent to R's lines function, returns the modified chart object.\n\nOptions:\n  :series-label (default x expression)\n  :points (default false)\n  :auto-sort (default true) sort data by x\n\n\nExamples:\n\n  (use '(incanter core stats io datasets charts))\n  (def cars (to-matrix (get-dataset :cars)))\n  (def y (sel cars :cols 0))\n  (def x (sel cars :cols 1))\n  (def plot1 (scatter-plot x y :legend true))\n  (view plot1)\n\n  ;; add regression line to scatter plot\n  (def lm1 (linear-model y x))\n  (add-lines plot1 x (:fitted lm1))\n\n  ;; model the data without an intercept\n  (def lm2 (linear-model y x :intercept false))\n  (add-lines plot1 x (:fitted lm2))\n\n\n  ;; Clojure's doto macro can be used to build a chart\n  (doto (histogram (sample-normal 1000) :density true)\n        (add-lines (range -3 3 0.05) (pdf-normal (range -3 3 0.05)))\n        view)\n\n\n  (with-data (get-dataset :iris)\n      (doto (xy-plot :Sepal.Width :Sepal.Length :legend true)\n            (add-lines :Petal.Width :Petal.Length)\n            view))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n\n",
   :var-type "macro",
   :line 585,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([chart function min-range max-range & options]),
   :name "add-parametric",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L719",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/add-parametric",
   :doc
   "\nAdds a xy-plot of the given parametric function to the given chart, returning\na modified version of the chart.\nFunction takes 1 argument t and returns point [x y].\n\nOptions:\n  :series-label (default function expression)\n  :step-size (default (/ (- max-range min-range) 500))\n\nSee also:\n  parametric-plot, view, save, add-function, add-points, add-lines\n\n\nExamples:\n\n  (use '(incanter core charts))\n\n  ;;; Plot square with circle inside.\n  (defn circle [t] [(cos t) (sin t)])\n  (doto (xy-plot [1 -1 -1 1 1] [1 1 -1 -1 1] :auto-sort false)\n        (add-parametric circle 0 (* 2 Math/PI))\n        (view))\n",
   :var-type "macro",
   :line 719,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([chart x y & options]),
   :name "add-pointer",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L2988",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/add-pointer",
   :doc
   "\nAdds an arrow annotation to the given chart.\n\nArguments:\n  chart -- the chart to annotate\n  x, y -- the coordinate to add the annotation\n\n\nOptions:\n    :text -- (default \"\") text to include at the end of the arrow\n    :angle -- (default :nw) either a number indicating the angle of the arrow\n              or a keyword indicating a direction (:north :nw :west :sw :south\n              :se :east :ne)\n\n\nExamples:\n\n  (use '(incanter core charts))\n  (def x (range (* -2 Math/PI) (* 2 Math/PI) 0.01))\n  (def plot (xy-plot x (sin x)))\n  (view plot)\n  ;; annotate the plot\n  (doto plot\n    (add-pointer (- Math/PI) (sin (- Math/PI)) :text \"(-pi, (sin -pi))\")\n    (add-pointer Math/PI (sin Math/PI) :text \"(pi, (sin pi))\" :angle :ne)\n    (add-pointer (* 1/2 Math/PI) (sin (* 1/2 Math/PI)) :text \"(pi/2, (sin pi/2))\" :angle :south))\n\n  ;; try the different angle options\n  (add-pointer plot 0 0 :text \"north\" :angle :north)\n  (add-pointer plot 0 0 :text \"nw\" :angle :nw)\n  (add-pointer plot 0 0 :text \"ne\" :angle :ne)\n  (add-pointer plot 0 0 :text \"west\" :angle :west)\n  (add-pointer plot 0 0 :text \"east\" :angle :east)\n  (add-pointer plot 0 0 :text \"south\" :angle :south)\n  (add-pointer plot 0 0 :text \"sw\" :angle :sw)\n  (add-pointer plot 0 0 :text \"se\" :angle :se)\n\n\n",
   :var-type "function",
   :line 2988,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([chart x y & options]),
   :name "add-points",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L777",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/add-points",
   :doc
   "\nPlots points on the given scatter-plot or xy-plot of the (x,y) points.\nEquivalent to R's lines function, returns the modified chart object.\n\nOptions:\n  :series-label (default x expression)\n\nExamples:\n\n  (use '(incanter core stats io datasets charts))\n  (def cars (to-matrix (get-dataset :cars)))\n  (def y (sel cars :cols 0))\n  (def x (sel cars :cols 1))\n\n  ;; add regression line to scatter plot\n  (def lm1 (linear-model y x))\n  ;; model the data without an intercept\n  (def lm2 (linear-model y x :intercept false))\n\n  (doto (xy-plot x (:fitted lm1) :legend true)\n        view\n        (add-points x y)\n        (add-lines x (:fitted lm2)))\n\n\n  (with-data (get-dataset :iris)\n    (doto (scatter-plot :Sepal.Length :Sepal.Width :data ($where {:Species \"setosa\"}))\n          (add-points :Sepal.Length :Sepal.Width :data ($where {:Species \"versicolor\"}))\n          (add-points :Sepal.Length :Sepal.Width :data ($where {:Species \"virginica\"}))\n          view))\n\n  ;; of course this chart can be achieved in a single line:\n  (view (scatter-plot :Sepal.Length :Sepal.Width :group-by :Species :data (get-dataset :iris)))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n\n",
   :var-type "macro",
   :line 777,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([chart coords & options]),
   :name "add-polygon",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L3120",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/add-polygon",
   :doc
   "\nAdds a polygon outline defined by a given coordinates. The last coordinate will\nclose with the first. If only two points are given, it will plot a line.\n\nArguments:\n  chart -- the chart to add the polygon to.\n  coords -- a list of coords (an n-by-2 matrix can also be used)\n\n\nExamples:\n  (use '(incanter core stats charts))\n  (def x (range -3 3 0.01))\n  (def plot (xy-plot x (pdf-normal x)))\n  (view plot)\n\n  ;; add polygon to the chart\n  (add-polygon plot [[-1.96 0] [1.96 0] [1.96 0.4] [-1.96 0.4]])\n  ;; the coordinates can also be passed in a matrix\n  ;; (def points (matrix [[-1.96 0] [1.96 0] [1.96 0.4] [-1.96 0.4]]))\n  ;; (add-polygon plot points)\n  ;; add a text annotation\n  (add-text plot -1.25 0.35 \"95% Conf Interval\")\n\n  ;; PCA chart example\n  (use '(incanter core stats charts datasets))\n  ;; load the iris dataset\n  (def iris (to-matrix (get-dataset :iris)))\n  ;; run the pca\n  (def pca (principal-components (sel iris :cols (range 4))))\n  ;; extract the first two principal components\n  (def pc1 (sel (:rotation pca) :cols 0))\n  (def pc2 (sel (:rotation pca) :cols 1))\n\n  ;; project the first four dimension of the iris data onto the first\n  ;; two principal components\n  (def x1 (mmult (sel iris :cols (range 4)) pc1))\n  (def x2 (mmult (sel iris :cols (range 4)) pc2))\n\n  ;; now plot the transformed data, coloring each species a different color\n  (def plot (scatter-plot x1 x2\n                          :group-by (sel iris :cols 4)\n                          :x-label \"PC1\" :y-label \"PC2\" :title \"Iris PCA\"))\n\n  (view plot)\n  ;; put box around the first group\n  (add-polygon plot [[-3.2 -6.3] [-2 -6.3] [-2 -3.78] [-3.2 -3.78]])\n  ;; add some text annotations\n  (add-text plot -2.5 -6.5 \"Setosa\")\n  (add-text plot -5 -5.5 \"Versicolor\")\n  (add-text plot -8 -5.5 \"Virginica\")\n\n\n\n",
   :var-type "function",
   :line 3120,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:file "modules/incanter-charts/src/incanter/charts.clj",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L3932",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/add-subtitle",
   :namespace "incanter.charts",
   :line 3932,
   :var-type "multimethod",
   :doc
   "\nAdds a JFreeChart title object to a chart as a subtitle.\n\nExamples:\n  (use '(incanter core charts latex))\n\n  (doto (function-plot sin -10 10)\n    (add-subtitle \"subtitle\")\n    (add-subtitle (latex \" \\\\frac{(a+b)^2} {(a-b)^2}\"))\n    view)\n\n",
   :name "add-subtitle"}
  {:arglists ([chart x y text & options]),
   :name "add-text",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L3075",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/add-text",
   :doc
   "\nAdds a text annotation centered at the given coordinates.\n\nArguments:\n  chart -- the chart to annotate\n  x, y -- the coordinates to center the text\n  text -- the text to add\n\n\nExamples:\n\n  ;; PCA chart example\n  (use '(incanter core stats charts datasets))\n  ;; load the iris dataset\n  (def iris (to-matrix (get-dataset :iris)))\n  ;; run the pca\n  (def pca (principal-components (sel iris :cols (range 4))))\n  ;; extract the first two principal components\n  (def pc1 (sel (:rotation pca) :cols 0))\n  (def pc2 (sel (:rotation pca) :cols 1))\n\n  ;; project the first four dimension of the iris data onto the first\n  ;; two principal components\n  (def x1 (mmult (sel iris :cols (range 4)) pc1))\n  (def x2 (mmult (sel iris :cols (range 4)) pc2))\n\n  ;; now plot the transformed data, coloring each species a different color\n  (def plot (scatter-plot x1 x2\n                          :group-by (sel iris :cols 4)\n                          :x-label \"PC1\" :y-label \"PC2\" :title \"Iris PCA\"))\n  (view plot)\n  ;; add some text annotations\n  (add-text plot -2.5 -6.5 \"Setosa\")\n  (add-text plot -5 -5.5 \"Versicolor\")\n  (add-text plot -8 -5.5 \"Virginica\")\n\n",
   :var-type "function",
   :line 3075,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([categories values & options]),
   :name "area-chart",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L2176",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/area-chart",
   :doc
   "Returns a JFreeChart object representing an area-chart of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nArguments:\n  categories -- a sequence of categories\n  values -- a sequence of numeric values\n\nOptions:\n  :title (default '') main title\n  :x-label (default 'Categories')\n  :y-label (default 'Value')\n  :series-label\n  :legend (default false) prints legend\n  :vertical (default true) the orientation of the plot\n  :group-by (default nil) -- a vector of values used to group the values into\n                             series within each category.\n\n\nSee also:\n  view and save\n\nExamples:\n\n\n  (use '(incanter core stats charts datasets))\n\n  (with-data (get-dataset :co2)\n    (view (area-chart :Type :uptake\n                     :title \"CO2 Uptake\"\n                     :group-by :Treatment\n                     :x-label \"Grass Types\" :y-label \"Uptake\"\n                    :legend true)))\n\n\n  (def data (get-dataset :airline-passengers))\n  (view (area-chart :year :passengers :group-by :month :legend true :data data))\n\n  (with-data  (get-dataset :airline-passengers)\n    (view (area-chart :month :passengers :group-by :year :legend true)))\n\n\n  (def data (get-dataset :austres))\n  (view data)\n  (def plot (area-chart :year :population :group-by :quarter :legend true :data data))\n  (view plot)\n  (save plot \"/tmp/austres_plot.png\" :width 1000)\n  (view \"file:///tmp/austres_plot.png\")\n\n\n  (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))\n  (def years (mapcat identity (repeat 4 [2007 2008 2009])))\n  (def values (sample-uniform 12 :integers true :max 100))\n  (view (area-chart years values :group-by seasons :legend true))\n\n  (view (area-chart [\"a\" \"b\" \"c\"] [10 20 30]))\n  (view (area-chart [\"a\" \"a\" \"b\" \"b\" \"c\" \"c\" ] [10 20 30 10 40 20]\n                   :legend true\n                   :group-by [\"I\" \"II\" \"I\" \"II\" \"I\" \"II\"]))\n\n  ;; add a series label\n  (def plot (area-chart [\"a\" \"b\" \"c\"] [10 20 30] :legend true :series-label \"s1\"))\n  (view plot)\n  (add-categories plot [\"a\" \"b\" \"c\"] [5 25 40] :series-label \"s2\")\n\n  (view (area-chart (sample \"abcdefghij\" :size 10 :replacement true)\n                   (sample-uniform 10 :max 50) :legend true))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :var-type "macro",
   :line 2176,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([categories values & options]),
   :name "bar-chart",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L2037",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/bar-chart",
   :doc
   "\nReturns a JFreeChart object representing a bar-chart of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nArguments:\n  categories -- a sequence of categories\n  values -- a sequence of numeric values\n\nOptions:\n  :title (default '') main title\n  :x-label (default 'Categories')\n  :y-label (default 'Value')\n  :series-label\n\n:legend (default false) prints legend\n  :vertical (default true) the orientation of the plot\n  :group-by (default nil) -- a vector of values used to group the values into\n                             series within each category.\n\n\nSee also:\n  view and save\n\nExamples:\n\n\n  (use '(incanter core stats charts datasets))\n\n  (with-data (get-dataset :co2)\n    (view (bar-chart :Type :uptake\n                     :title \"CO2 Uptake\"\n                     :group-by :Treatment\n                     :x-label \"Grass Types\" :y-label \"Uptake\"\n                    :legend true)))\n\n\n  (def data (get-dataset :airline-passengers))\n  (view (bar-chart :year :passengers :group-by :month :legend true :data data))\n\n  (with-data  (get-dataset :airline-passengers)\n    (view (bar-chart :month :passengers :group-by :year :legend true)))\n\n\n  (def data (get-dataset :austres))\n  (view data)\n  (def plot (bar-chart :year :population :group-by :quarter :legend true :data data))\n  (view plot)\n  (save plot \"/tmp/austres_plot.png\" :width 1000)\n  (view \"file:///tmp/austres_plot.png\")\n\n\n  (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))\n  (def years (mapcat identity (repeat 4 [2007 2008 2009])))\n  (def values (sample-uniform 12 :integers true :max 100))\n  (view (bar-chart years values :group-by seasons :legend true))\n\n  (view (bar-chart [\"a\" \"b\" \"c\"] [10 20 30]))\n  (view (bar-chart [\"a\" \"a\" \"b\" \"b\" \"c\" \"c\" ] [10 20 30 10 40 20]\n                   :legend true\n                   :group-by [\"I\" \"II\" \"I\" \"II\" \"I\" \"II\"]))\n\n  ;; add a series label\n  (def plot (bar-chart [\"a\" \"b\" \"c\"] [10 20 30] :legend true :series-label \"s1\"))\n  (view plot)\n  (add-categories plot [\"a\" \"b\" \"c\"] [5 25 40] :series-label \"s2\")\n\n  (view (bar-chart (sample \"abcdefghij\" :size 10 :replacement true)\n                   (sample-uniform 10 :max 50) :legend true))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :var-type "macro",
   :line 2037,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([x1 x2 & options]),
   :name "bland-altman-plot",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L3299",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/bland-altman-plot",
   :doc
   "\nOptions:\n  :data (default nil) If the :data option is provided a dataset,\n                      column names can be used instead of sequences\n                      of data for arguments x1 and x2.\n\nExamples:\n\n  (use '(incanter core datasets charts))\n  (def flow-meter (to-matrix (get-dataset :flow-meter)))\n  (def x1 (sel flow-meter :cols 1))\n  (def x2 (sel flow-meter :cols 3))\n  (view (bland-altman-plot x1 x2))\n\n  (with-data (get-dataset :flow-meter)\n    (view (bland-altman-plot (keyword \"Wright 1st PEFR\")\n                             (keyword \"Mini Wright 1st PEFR\"))))\n\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Bland-Altman_plot\n  http://www-users.york.ac.uk/~mb55/meas/ba.htm\n\n",
   :var-type "function",
   :line 3299,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([x & options]),
   :name "box-plot",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L2653",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/box-plot",
   :doc
   "\nReturns a JFreeChart object representing a box-plot of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nOptions:\n  :title (default '') main title\n  :x-label (default x expression)\n  :y-label (default 'Frequency')\n  :legend (default false) prints legend\n  :series-label (default x expression)\n  :group-by (default nil) -- a vector of values used to group the x values into series.\n\nSee also:\n  view and save\n\nExamples:\n\n  (use '(incanter core stats charts))\n  (def gamma-box-plot (box-plot (sample-gamma 1000 :shape 1 :rate 2)\n                        :title \"Gamma Boxplot\"\n                        :legend true))\n  (view gamma-box-plot)\n  (add-box-plot gamma-box-plot (sample-gamma 1000 :shape 2 :rate 2))\n  (add-box-plot gamma-box-plot (sample-gamma 1000 :shape 3 :rate 2))\n\n  ;; use the group-by options\n  (use '(incanter core stats datasets charts))\n  (with-data (get-dataset :iris)\n    (view (box-plot :Petal.Length :group-by :Species :legend true))\n    (view (box-plot :Petal.Width :group-by :Species :legend true))\n    (view (box-plot :Sepal.Length :group-by :Species :legend true))\n    (view (box-plot :Sepal.Width :group-by :Species :legend true)))\n\n  ;; see INCANTER_HOME/examples/probability_plots.clj for more examples of plots\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :var-type "macro",
   :line 2653,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([& options]),
   :name "candle-stick-plot",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L1260",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/candle-stick-plot",
   :doc
   "\nProduces a candle stick chart\n\nOptions:\n  :data (default nil) If the :data option is provided a dataset,\n                      column names can be used instead of sequences\n                      of data as arguments to xy-plot.\n  :date Key for accessing the underlying date series (defaults to :date)\n  :high Key for accessing high value data (defaults to :high)\n  :low Key for accessing low value data (defaults to :low)\n  :open Key for accessing open value data (defaults to :open)\n  :close Key for accessing close value data (defaults to :close)\n  :volume Key for accessing volume data (defaults to :volume). Volume data is optional\n  :title (default 'Candle Stick Plot') main title\n  :time-label (default empty)\n  :value-label (default empty)\n  :legend (default false) prints legend\n  :series-label (default empty)\n\n Example:\n   ;; use default mappings so the dataset must have\n   ;; :date, :high, :low, :open, :close and :volume keys\n   (candle-stick-plot :data <dataset>)\n   ;; more customization\n   (candle-stick-plot\n     :data dataset\n     :high :HighPrice\n     :low :LowPrice\n     :open :StartOfDay\n     :close :CoB\n     :volume :TransactionVolume\n     :legend true\n     :time-label \"CoB date\"\n     :value-label \"Price\"\n     :series-label \"Price time series\"\n     :title \"Price information\")\n",
   :var-type "macro",
   :line 1260,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([chart]),
   :name "clear-background",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L959",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/clear-background",
   :doc
   "\nSets the alpha level (transparency) of the plot's background to zero\nremoving the default grid, returns the modified chart object.\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :var-type "function",
   :line 959,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([[& slider-bindings] expression & options]),
   :name "dynamic-scatter-plot",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L3590",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/dynamic-scatter-plot",
   :doc
   "\nReturns an scatter-plot bound to sliders (which tend to appear behind the chart).\nSee the sliders macro for more information.\n\n\nExamples:\n\n(use '(incanter core stats charts))\n\n(let [x (range -3 3 0.1)]\n  (view (dynamic-scatter-plot [mean (range -3 3 0.1)\n                               sd (range 0.1 10 0.1)]\n          [x (pdf-normal x :mean mean :sd sd)]\n          :title \"Normal PDF Plot\")))\n\n\n (let [x (range -3 3 0.1)]\n   (view (dynamic-scatter-plot [mean (range -3 3 0.1)\n                                sd (range 0.1 10 0.1)]\n          (for [xi x] [xi (pdf-normal xi :mean mean :sd sd)])\n          :title \"Normal PDF Plot\")))\n\n",
   :var-type "macro",
   :line 3590,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([[& slider-bindings] expression & options]),
   :name "dynamic-xy-plot",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L3557",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/dynamic-xy-plot",
   :doc
   "\nReturns an xy-plot bound to sliders (which tend to appear behind the chart).\nSee the sliders macro for more information.\n\n\nExamples:\n\n  (use '(incanter core stats charts))\n\n  (let [x (range -3 3 0.1)]\n  (view (dynamic-xy-plot [mean (range -3 3 0.1)\n                          sd (range 0.1 10 0.1)]\n                         [x (pdf-normal x :mean mean :sd sd)]\n                         :title \"Normal PDF Plot\")))\n\n (let [x (range -3 3 0.1)]\n   (view (dynamic-xy-plot [mean (range -3 3 0.1)\n                           sd (range 0.1 10 0.1)]\n          (for [xi x] [xi (pdf-normal xi :mean mean :sd sd)])\n          :title \"Normal PDF Plot\")))\n\n\n",
   :var-type "macro",
   :line 3557,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([function min-range max-range & options]),
   :name "function-plot",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L2732",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/function-plot",
   :doc
   "\nReturns a xy-plot object of the given function over the range indicated\nby the min-range and max-range arguments. Use the 'view' function to\ndisplay the chart, or the 'save' function to write it to a file.\n\nOptions:\n  :title (default '') main title\n  :x-label (default x expression)\n  :y-label (default 'Frequency')\n  :legend (default false) prints legend\n  :series-label (default x expression)\n  :step-size (default (/ (- max-range min-range) 500))\n\nSee also:\n  view, save, add-points, add-lines\n\n\nExamples:\n\n  (use '(incanter core stats charts))\n\n  (view (function-plot sin (- Math/PI) Math/PI))\n  (view (function-plot pdf-normal -3 3))\n\n  (defn cubic [x] (+ (* x x x) (* 2 x x) (* 2 x) 3))\n  (view (function-plot cubic -10 10))\n\n",
   :var-type "macro",
   :line 2732,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([chart] [chart series-idx]),
   :name "get-series",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L3381",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/get-series",
   :doc "get-series",
   :var-type "function",
   :line 3381,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([function x-min x-max y-min y-max & options]),
   :name "heat-map",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L2914",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/heat-map",
   :doc
   "\nUsage: (heat-map function x-min x-max y-min y-max & options)\n\nReturns a JFreeChart object representing a heat map of the function across\nthe given x and y ranges. Use the 'view' function to display the chart, or\nthe 'save' function to write it to a file.\n\nArguments:\n  function -- a function that takes two scalar arguments and returns a scalar\n  x-min    -- lower bound for the first value of the function\n  x-max    -- upper bound for the first value of the function\n  y-min    -- lower bound for the second value of the function\n  y-max    -- upper bound for the second value of the function\n\nOptions:\n  :title\n  :x-label (default 'x-min < x < x-max')\n  :y-label (default 'y-min < y < y-max')\n  :z-label -- defaults to function's name\n  :color? (default true) -- should the plot be in color or not?\n  :include-zero? (default true) -- should the plot include the origin if it\n                                   is not in the ranges specified?\n\nExamples:\n  (use '(incanter core charts))\n  (defn f [x y] (sin (sqrt (plus (sq x) (sq y)))))\n  (view (heat-map f -10 10 -15 15))\n  (view (heat-map f -10 10 -10 10 :color? false))\n  (view (heat-map f 5 10 5 10 :include-zero? false))\n\n  (defn f2 [x y] (plus (sq x) (sq y)))\n  (view (heat-map f2 -10 10 -10 10))\n  (view (heat-map f2 -10 10 -10 10 :color? false))\n\n  (use 'incanter.stats)\n  (defn f3 [x y] (pdf-normal (sqrt (plus (sq x) (sq y)))))\n  (view (heat-map f3 -3 3 -3 3 :x-label \"x1\" :y-label \"x2\" :z-label \"pdf\"))\n  (view (heat-map f3 -3 3 -3 3 :color? false))\n\n  (defn f4 [x y] (minus (sq x) (sq y)))\n  (view (heat-map f4 -10 10 -10 10))\n  (view (heat-map f4 -10 10 -10 10 :color? false))\n\n\n  (use '(incanter core stats charts))\n  (let [data [[0 5 1 2]\n                [0 10 1.9 1]\n                [15 0 0.5 1.5]\n                [18 10 4.5 2.1]]\n        diffusion (fn [x y]\n                    (sum (map #(pdf-normal (euclidean-distance [x y] (take 2 %))\n                                           :mean (nth % 2) :sd (last %))\n                              data)))]\n    (view (heat-map diffusion -5 20 -5 20)))\n\n",
   :var-type "macro",
   :line 2914,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([x & options]),
   :name "histogram",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L1790",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/histogram",
   :doc
   "\nReturns a JFreeChart object representing the histogram of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nOptions:\n  :nbins (default 10) number of bins\n  :density (default false) if false, plots frequency, otherwise density\n  :title (default 'Histogram') main title\n  :x-label (default x expression)\n  :y-label (default 'Frequency')\n  :legend (default false) prints legend\n  :series-label (default x expression)\n\n\nSee also:\n  view, save, add-histogram\n\nExamples:\n\n  (use '(incanter core charts stats))\n  (view (histogram (sample-normal 1000)))\n\n  # plot a density histogram\n  (def hist (histogram (sample-normal 1000) :density true))\n  (view hist)\n\n  # add a normal density line to the plot\n  (def x (range -4 4 0.01))\n  (add-lines hist x (pdf-normal x))\n\n  # plot some gamma data\n  (def gam-hist (histogram (sample-gamma 1000) :density true :nbins 30))\n  (view gam-hist)\n  (def x (range 0 8 0.01))\n  (add-lines gam-hist x (pdf-gamma x))\n\n  (use 'incanter.datasets)\n  (def iris (get-dataset :iris))\n  (view (histogram :Sepal.Width :data iris))\n\n  (with-data (get-dataset :iris)\n    (view (histogram :Petal.Length)))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :var-type "macro",
   :line 1790,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([categories values & options]),
   :name "line-chart",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L1896",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/line-chart",
   :doc
   "\nReturns a JFreeChart object representing a line-chart of the given values and categories.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nArguments:\n  categories -- a sequence of categories\n  values -- a sequence of numeric values\n\nOptions:\n  :title (default '') main title\n  :x-label (default 'Categories')\n  :y-label (default 'Value')\n  :legend (default false) prints legend\n  :series-label\n  :group-by (default nil) -- a vector of values used to group the values into\n                             series within each category.\n  :gradient? (default false) -- use gradient on bars\n\n\nSee also:\n  view and save\n\nExamples:\n\n  (use '(incanter core stats charts datasets))\n\n  (def data (get-dataset :airline-passengers))\n  (def years (sel data :cols 0))\n  (def months (sel data :cols 2))\n  (def passengers (sel data :cols 1))\n  (view (line-chart years passengers :group-by months :legend true))\n  (view (line-chart months passengers :group-by years :legend true))\n\n\n  (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))\n  (def years (mapcat identity (repeat 4 [2007 2008 2009])))\n  (def x (sample-uniform 12 :integers true :max 100))\n  (view (line-chart years x :group-by seasons :legend true))\n\n  (view (line-chart [\"a\" \"b\" \"c\" \"d\" \"e\" \"f\"] [10 20 30 10 40 20]))\n\n  (view (line-chart (sample \"abcdefghij\" :size 10 :replacement true)\n                       (sample-uniform 10 :max 50) :legend true))\n\n  ;; add a series label\n  (def plot (line-chart [\"a\" \"b\" \"c\"] [10 20 30] :legend true :series-label \"s1\"))\n  (view plot)\n  (add-categories plot [\"a\" \"b\" \"c\"] [5 25 40] :series-label \"s2\")\n\n\n  (view (line-chart :year :passengers :group-by :month :legend true :data data))\n\n  (view (line-chart :month :passengers :group-by :year :legend true :data data))\n\n  (with-data data\n    (view (line-chart :month :passengers :group-by :year :legend true)))\n\n  (with-data (->> ($rollup :sum :passengers :year (get-dataset :airline-passengers))\n                  ($order :year :asc))\n    (view (line-chart :year :passengers)))\n\n  (with-data (->> ($rollup :sum :passengers :month (get-dataset :airline-passengers))\n                  ($order :passengers :asc))\n    (view (line-chart :month :passengers)))\n\n\n  (with-data ($rollup :sum :passengers :month (get-dataset :airline-passengers))\n    (view (line-chart :month :passengers)))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :var-type "macro",
   :line 1896,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([& options]),
   :name "log-axis",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L827",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/log-axis",
   :doc
   "\nCreate a logarithmic axis.\n\nNote: By default, values smaller than 0.5 are rounded to 0.5 to prevent strange behavior that\nhappens for values close to 0.\n\nOptions:\n  :base (default 10) base of the logarithm; typically 2 or 10\n  :label (default none) the label of the axis\n  :int-ticks? (default true) if true, use normal numbers instead of\n     <base>^<exponent>, i.e. 1 instead of f.ex. 10^0.0\n  :smallest-value (default: 0.5) Set the smallest value represented by the axis, set to 0.0 to 'reset'\n\nSee also:\n  set-axis\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/axis/LogAxis.html\n\n",
   :var-type "function",
   :line 827,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([function min-range max-range & options]),
   :name "parametric-plot",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L2799",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/parametric-plot",
   :doc
   "\nReturns a xy-plot object of the given parametric function over the range indicated\nby the min-range and max-range arguments. Use the 'view' function to\ndisplay the chart, or the 'save' function to write it to a file.\nFunction must take 1 argument - parameter t and return point [x y].\n\nOptions:\n  :title (default '') main title\n  :x-label (default 'min-x < x < max-x')\n  :y-label (default 'min-y < y < max-y')\n  :legend (default false) prints legend\n  :series-label (default function expression)\n  :step-size (default (/ (- max-range min-range) 500))\n\nSee also:\n  view, save, add-parametric, function-plot\n\n\nExamples:\n\n  (use '(incanter core charts))\n\n  (defn circle [t] [(cos t) (sin t)])\n  (view (parametric-plot circle (- Math/PI) Math/PI))\n\n  (defn spiral [t] [(* t (cos t)) (* t (sin t))])\n  (view (parametric-plot spiral 0 (* 6 Math/PI)))\n",
   :var-type "macro",
   :line 2799,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([categories values & options]),
   :name "pie-chart",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L2558",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/pie-chart",
   :doc
   "\nReturns a JFreeChart object representing a pie-chart of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nArguments:\n  categories -- a sequence of categories\n  values -- a sequence of numeric values\n\nOptions:\n  :title (default '') main title\n  :legend (default false) prints legend\n\n\nSee also:\n  view and save\n\nExamples:\n\n\n  (use '(incanter core stats charts datasets))\n\n  (view (pie-chart [\"a\" \"b\" \"c\"] [10 20 30]))\n\n   (view (pie-chart (sample \"abcdefghij\" :size 10 :replacement true)\n                   (sample-uniform 10 :max 50) :legend true))\n\n\n   (with-data (->> (get-dataset :hair-eye-color)\n                   ($rollup :sum :count [:hair :eye]))\n     (view $data)\n     (view (pie-chart :hair :count :title \"Hair Color\"))\n     (view (pie-chart :eye :count :title \"Eye Color\")))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :var-type "macro",
   :line 2558,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([x & options]),
   :name "qq-plot",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L3259",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/qq-plot",
   :doc
   "\nReturns a QQ-Plot object. Use the 'view' function to display it.\n\nOptions:\n  :data (default nil) If the :data option is provided a dataset,\n                      a column name can be used instead of a sequence\n                      of data for argument x.\n\nReferences:\n  http://en.wikipedia.org/wiki/QQ_plot\n\nExamples:\n\n  (use '(incanter core stats charts datasets))\n  (view (qq-plot (sample-normal 100)))\n  (view (qq-plot (sample-exp 100)))\n  (view (qq-plot (sample-gamma 100)))\n\n  (with-data (get-dataset :iris)\n    (view (qq-plot :Sepal.Length)))\n\n",
   :var-type "function",
   :line 3259,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([] [x y & options]),
   :name "scatter-plot",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L1429",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/scatter-plot",
   :doc
   "\nReturns a JFreeChart object representing a scatter-plot of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nOptions:\n  :title (default '') main title\n  :x-label (default x expression)\n  :y-label (default 'Frequency')\n  :legend (default false) prints legend\n  :series-label (default x expression)\n  :group-by (default nil) -- a vector of values used to group the x and y values into series.\n  :density? (default false) -- chart will represent density instead of frequency.\n  :nbins (default 10) -- number of bins (i.e. bars)\n  :gradient? (default false) -- use gradient on bars\n\nSee also:\n  view, save, add-points, add-lines\n\nExamples:\n\n  (use '(incanter core stats charts datasets))\n  ;; create some data\n  (def mvn-samp (sample-mvn 1000 :mean [7 5] :sigma (matrix [[2 1.5] [1.5 3]])))\n\n  ;; create scatter-plot of points\n  (def mvn-plot (scatter-plot (sel mvn-samp :cols 0) (sel mvn-samp :cols 1)))\n  (view mvn-plot)\n\n  ;; add regression line to scatter plot\n  (def x (sel mvn-samp :cols 0))\n  (def y (sel mvn-samp :cols 1))\n  (def lm (linear-model y x))\n  (add-lines mvn-plot x (:fitted lm))\n\n  ;; use :group-by option\n  (use '(incanter core stats datasets charts))\n  ;; load the :iris dataset\n  (def iris (get-dataset :iris))\n  ;; plot the first two columns grouped by the fifth column\n  (view (scatter-plot ($ :Sepal.Width iris) ($ :Sepal.Length iris) :group-by ($ :Species iris)))\n\n  (view (scatter-plot :Sepal.Length :Sepal.Width :data (get-dataset :iris)))\n\n  (view (scatter-plot :Sepal.Length :Sepal.Width :group-by :Species :data (get-dataset :iris)))\n\n  (with-data (get-dataset :iris)\n     (view (scatter-plot :Sepal.Length :Sepal.Width)))\n\n  (with-data (get-dataset :iris)\n     (view (scatter-plot :Sepal.Length :Sepal.Width :group-by :Species)))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :var-type "macro",
   :line 1429,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([& opts]),
   :name "scatter-plot-matrix",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L1694",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/scatter-plot-matrix",
   :doc
   "\nReturns a JFreeChart object displaying a scatter plot matrix for the given data.\nUse the 'view' function to display the chart or 'save' to write it to a file.\n\nUse:\n  (scatter-plot-matrix & options)\n  (scatter-plot-matrix data & options)\n\nOptions:\n  :data data (default $data) the data set for the plot.\n  :title s (default \"Scatter Plot Matrix\").\n  :nbins n (default 10) number of bins (ie. bars) in histogram.\n  :group-by grp (default nil) name of the column for grouping data.\n  :only-first n (default 6) show only the first n most correlating columns of the data set.\n  :only-triangle b (default false) shows only the upper triangle of the plot matrix.\n\nExamples:\n  (use '(incanter core stats charts datasets pdf))\n  (view (scatter-plot-matrix (get-dataset :iris) :nbins 20 :group-by :Species ))\n  (with-data (get-dataset :iris) (view (scatter-plot-matrix :nbins 20 :group-by :Species )))\n  (view (scatter-plot-matrix (get-dataset :chick-weight) :group-by :Diet :nbins 20))\n\n  ;;;Input examples for Iris\n  ;; Input dataset examples: Incanter data repo, local file, remote file (url)\n  (def iris (get-dataset :iris))\n  (def iris (read-dataset \"data/iris.dat\" :delim \\space :header true)) ; relative to project home\n  (def iris (read-dataset \"https://raw.github.com/liebke/incanter/master/data/iris.dat\" :delim \\space :header true))\n  ;; Filter dataset to specific columns only\n  (def iris ($ [:Sepal.Length :Sepal.Width :Petal.Length :Petal.Width :Species] (get-dataset :iris)))\n  (def iris (sel (get-dataset :iris) :cols [:Sepal.Length :Sepal.Width :Petal.Length :Petal.Width :Species]))\n\n  ;;; Scatter plot matrix examples\n  ;; Using default options\n  (def iris-spm (scatter-plot-matrix iris :group-by :Species))\n  ;; filter to metrics only, no categorical dimension for grouping\n  (def iris-spm (scatter-plot-matrix :data ($ [:Sepal.Length :Sepal.Width :Petal.Length :Petal.Width] iris)))\n\n  ;; Using more options\n  (def iris-spm (scatter-plot-matrix iris\n                                     :title \"Iris Scatter Plot Matrix\"\n                                     :bins 20 ; number of histogram bars\n                                     :group-by :Species\n                                     :only-first 4 ; most correlating columns\n                                     :only-triangle false))\n\n  ;;;Output examples\n  ;; View on Display\n  (view iris-spm :width 1280 :height 800)\n  ;; Save as PDF\n  (save-pdf  iris-spm \"out/iris-spm.pdf\" :width 2560 :height 1600)\n  ;; Save as PNG\n  (save iris-spm \"out/iris-spm.png\" :width 2560 :height 1600)\n\n  ;; Airline dataset\n  (def airline ($ [:year :passengers :month] (read-dataset \"https://raw.github.com/liebke/incanter/master/data/airline_passengers.csv\" :header true)))\n  (def airline-spm (scatter-plot-matrix airline  :group-by :month :bins 20 :title \"Airline Scatter Plot Matrix\"))\n  (view airline-spm)\n  ;; Chick-weight dataset\n  (view (scatter-plot-matrix (get-dataset :chick-weight) :group-by :Diet :bins 20 :title \"Chick-weight Scatter Plot Matrix\" ))\n",
   :var-type "function",
   :line 1694,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([chart alpha]),
   :name "set-alpha",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L930",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/set-alpha",
   :doc
   "\nSets the alpha level (transparency) of the plot's foreground\nreturns the modified chart object.\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :var-type "function",
   :line 930,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:file "modules/incanter-charts/src/incanter/charts.clj",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L865",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/set-axis",
   :namespace "incanter.charts",
   :line 865,
   :var-type "multimethod",
   :doc
   "\nSet the selected axis of the chart, returning the chart.\n(Beware: the axis' label will replace axis label set previously on the chart.)\n\nArguments:\n  chart - the JFreeChart object whose axis to change\n  dimension - depends on the plot type for plots with mutliple axes\n               f.ex. :x or :y for an XYPlot (x is the domain axis, y the range one)\n  axis - the axis to set, an instance of ValueAxis\n\nSee also:\n  log-axis\n\nNote:\n  Not applicable to DialPlot MeterPlot PiePlot MultiplePiePlot CompassPlot WaferMapPlot SpiderWebPlot\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/axis/ValueAxis.html\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/plot/XYPlot.html\n\nExamples:\n\n  (use '(incanter core charts))\n\n  (view\n    (doto (function-plot #(Math/pow 10 %) 0 5)\n          (set-axis :x (log-axis :base 10, :label \"log(x)\"))))\n",
   :name "set-axis"}
  {:arglists ([chart alpha]),
   :name "set-background-alpha",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L944",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/set-background-alpha",
   :doc
   "\nSets the alpha level (transparency) of the plot's background\nreturns the modified chart object.\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :var-type "function",
   :line 944,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:file "modules/incanter-charts/src/incanter/charts.clj",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L76",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/set-background-default",
   :namespace "incanter.charts",
   :line 76,
   :var-type "multimethod",
   :doc
   "\nExamples:\n  (use '(incanter core stats charts datasets))\n\n  (doto (histogram (sample-normal 1000) :title (str :Test-Tittle))\n    set-theme-bw\n    view)\n\n\n  (doto (histogram (sample-normal 1000))\n    set-background-default\n    (add-histogram (sample-normal 1000 :mean 1))\n    view)\n\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    view)\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    (set-stroke :dash 5)\n    (add-points (plus ($ :speed (get-dataset :cars)) 5) (plus ($ :dist (get-dataset :cars)) 10))\n    view)\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-background-default\n    (set-stroke :dash 5)\n    (add-function sin 0 25)\n    view)\n\n\n  (doto (xy-plot :speed :dist :data (get-dataset :cars) :legend true)\n    set-background-default\n    view)\n\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-background-default\n    view)\n\n\n  (doto (box-plot (sample-gamma 1000 :shape 1 :rate 2)\n                  :legend true)\n    view set-background-default\n    (add-box-plot (sample-gamma 1000 :shape 2 :rate 2))\n    (add-box-plot (sample-gamma 1000 :shape 3 :rate 2)))\n\n\n  (doto (bar-chart [:a :b :c] [10 20 30] :legend true)\n    view\n    set-background-default\n    (add-categories [:a :b :c] [5 25 40]))\n\n\n  (doto (line-chart [:a :b :c] [10 20 30] :legend true)\n    view\n    set-background-default\n    (add-categories [:a :b :c] [5 25 40]))\n\n  ;; time-series-plot\n  (def epoch 0)\n  (defn num-years-to-milliseconds [x]\n    (* 365 24 60 60 1000 x))\n  (def dates (map num-years-to-milliseconds (range 100)))\n  (def chart1 (time-series-plot dates (range 100)))\n  (def cw1 (view chart1))\n  (add-lines chart1 dates (mult 1/2 (range 100)))\n\n  (def chart2 (time-series-plot (take 10 dates) (mult 1/2 (range 10))))\n  (def cw2 (view chart2))\n",
   :name "set-background-default"}
  {:arglists
   ([chart
     point-size
     &
     {:keys [series dataset], :or {series :all, dataset 0}}]),
   :name "set-point-size",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L3699",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/set-point-size",
   :doc
   "Set the point size of a scatter plot. Use series option to apply\npoint-size to only one series.",
   :var-type "function",
   :line 3699,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([chart & options]),
   :name "set-stroke",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L3626",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/set-stroke",
   :doc
   "\nExamples:\n  (use '(incanter core charts))\n\n  (doto (line-chart [:a :b :c :d] [10 20 5 35])\n    (set-stroke :width 4 :dash 5)\n    view)\n\n  (doto (line-chart [:a :b :c :d] [10 20 5 35])\n    (add-categories [:a :b :c :d] [20 5 30 15])\n    (set-stroke :width 4 :dash 5)\n    (set-stroke :series 1 :width 2 :dash 10)\n    view)\n\n\n  (doto (function-plot sin -10 10 :step-size 0.1)\n    (set-stroke :width 3 :dash 5)\n    view)\n\n  (doto (line-chart [:a :b :c :d] [10 20 5 35])\n    (add-categories [:a :b :c :d] [20 5 30 15])\n    (set-stroke :series 0 :width 4 :dash 5)\n    (set-stroke :series 1 :width 4 :dash 5 :cap java.awt.BasicStroke/CAP_SQUARE))\n",
   :var-type "function",
   :line 3626,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([chart color & options]),
   :name "set-stroke-color",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L3671",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/set-stroke-color",
   :doc
   "\nExamples:\n  (use '(incanter core charts))\n\n  (doto (line-chart [:a :b :c :d] [10 20 5 35])\n    (set-stroke :width 4 :dash 5)\n    (set-stroke-color java.awt.Color/blue)\n    view)\n\n  (doto (xy-plot [1 2 3] [4 5 6])\n    (add-points [1 2 3] [4.1 5.1 6.1])\n    (set-stroke-color java.awt.Color/black :series 0)\n    (set-stroke-color java.awt.Color/red :series 1))\n\n  (doto (function-plot sin -10 10 :step-size 0.1)\n    (set-stroke :width 3 :dash 5)\n    (set-stroke-color java.awt.Color/gray)\n    view)\n\n",
   :var-type "function",
   :line 3671,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([chart theme]),
   :name "set-theme",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L224",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/set-theme",
   :doc
   "\nChanges the chart theme.\n\nArguments:\n  chart -- an Incanter/JFreeChart object\n  theme -- either a keyword indicating one of the built-in themes, or a JFreeChart ChartTheme object.\n\nBuilt-in Themes:\n  :default\n  :dark\n\nExamples:\n\n  (use '(incanter core charts))\n  (def chart (function-plot sin -4 4))\n  (view chart)\n  ;; change the theme of chart to :dark\n  (set-theme chart :dark)\n  ;; change it back to the default\n  (set-theme chart :default)\n\n  ;; Example using JFreeTheme\n  (use '(incanter core stats charts datasets))\n\n  (import '(org.jfree.chart StandardChartTheme)\n          '(org.jfree.chart.plot DefaultDrawingSupplier)\n          '(java.awt Color))\n\n  (def all-red-theme\n    (doto\n      (StandardChartTheme/createJFreeTheme)\n      (.setDrawingSupplier\n      (proxy [DefaultDrawingSupplier] []\n        (getNextPaint [] Color/red)))))\n\n  (def data (get-dataset :airline-passengers))\n\n  (def chart (bar-chart :month :passengers :group-by :year :legend true :data data))\n\n  (doto chart\n    ;; has no effect\n    (set-theme all-red-theme)\n    view)\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/StandardChartTheme.html\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/ChartTheme.html\n\n",
   :var-type "function",
   :line 224,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:file "modules/incanter-charts/src/incanter/charts.clj",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L156",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/set-theme-bw",
   :namespace "incanter.charts",
   :line 156,
   :var-type "multimethod",
   :doc
   "\n\nExamples:\n  (use '(incanter core stats charts datasets))\n\n  (doto (histogram (sample-normal 1000))\n    set-theme-bw\n    view)\n\n\n  (doto (histogram (sample-normal 1000))\n    set-theme-bw\n    (add-histogram (sample-normal 1000 :mean 1))\n    view)\n\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    view)\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    (set-stroke :dash 5)\n    (add-points (plus ($ :speed (get-dataset :cars)) 5) (plus ($ :dist (get-dataset :cars)) 10))\n    view)\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    (set-stroke :dash 5)\n    (add-function sin 0 25)\n    view)\n\n\n  (doto (xy-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    view)\n\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    (add-lines :speed :dist :data (get-dataset :cars))\n    view)\n\n\n  (doto (box-plot (sample-gamma 1000 :shape 1 :rate 2)\n                  :legend true)\n    view\n    (add-box-plot (sample-gamma 1000 :shape 2 :rate 2))\n    (add-box-plot (sample-gamma 1000 :shape 3 :rate 2))\n    set-theme-bw)\n\n\n  (doto (bar-chart [:a :b :c] [10 20 30] :legend true)\n    view\n    set-theme-bw\n    (add-categories [:a :b :c] [5 25 40]))\n\n\n  (doto (line-chart [:a :b :c] [10 20 30] :legend true)\n    view\n    set-theme-bw\n    (add-categories [:a :b :c] [5 25 40]))\n\n\n",
   :name "set-theme-bw"}
  {:arglists ([chart title]),
   :name "set-title",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L973",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/set-title",
   :doc
   "\nSets the main title of the plot, returns the modified chart object.\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :var-type "function",
   :line 973,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([chart label]),
   :name "set-x-label",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L987",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/set-x-label",
   :doc
   "\nSets the label of the x-axis, returns the modified chart object.\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :var-type "function",
   :line 987,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([chart lower upper]),
   :name "set-x-range",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L1015",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/set-x-range",
   :doc
   "\nSets the range of the x-axis on the given chart.\n\nExamples:\n\n  (use '(incanter core charts datasets))\n\n  (def chart (xy-plot :speed :dist :data (get-dataset :cars)))\n  (view chart)\n  (set-x-range chart 10 20)\n\n",
   :var-type "function",
   :line 1015,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([chart label]),
   :name "set-y-label",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L1001",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/set-y-label",
   :doc
   "\nSets the label of the y-axis, returns the modified chart object.\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :var-type "function",
   :line 1001,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([chart lower upper]),
   :name "set-y-range",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L1036",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/set-y-range",
   :doc
   "\nSets the range of the y-axis on the given chart.\n\nExamples:\n\n  (use '(incanter core charts datasets))\n\n  (def chart (xy-plot :speed :dist :data (get-dataset :cars)))\n  (view chart)\n  (set-y-range chart 10 60)\n\n",
   :var-type "function",
   :line 1036,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists
   ([updater-fn slider-values]
    [updater-fn slider-values slider-label]),
   :name "slider",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L3421",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/slider",
   :doc
   "\nExamples:\n  (use '(incanter core stats charts))\n\n  (def pdf-chart (function-plot pdf-normal -3 3))\n  (view pdf-chart)\n  (add-function pdf-chart pdf-normal -3 3)\n\n  (let [x (range -3 3 0.1)]\n    (slider #(set-data pdf-chart [x (pdf-normal x :sd %)]) (range 0.1 10 0.1)))\n\n  (let [x (range -3 3 0.1)]\n    (slider #(set-data pdf-chart [x (pdf-normal x :sd %)]) (range 0.1 10 0.1) \"sd\"))\n",
   :var-type "function",
   :line 3421,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([[& slider-bindings] body]),
   :name "sliders",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L3496",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/sliders",
   :doc
   "\nCreates one slider control for each of the given sequence bindings.\nEach slider calls the given expression when manipulated.\n\n\nExamples:\n  (use '(incanter core stats charts))\n\n  ;; manipulate a normal pdf\n  (let [x (range -3 3 0.1)]\n    (def pdf-chart (xy-plot))\n    (view pdf-chart)\n    (sliders [mean (range -3 3 0.1)\n              stdev (range 0.1 10 0.1)]\n      (set-data pdf-chart [x (pdf-normal x :mean mean :sd stdev)])))\n\n\n  ;; manipulate a gamma pdf\n  (let [x (range 0 20 0.1)]\n    (def pdf-chart (xy-plot))\n    (view pdf-chart)\n    (sliders [rate (range 0.1 10 0.1)\n              shape (range 0.1 10 0.1)]\n             (set-data pdf-chart [x (pdf-gamma x :rate rate :shape shape)])))\n\n\n\n  ;; find the start values of a non-linear model function\n  (use '(incanter core charts datasets))\n  ;; create model function used in the following data-sorcery post:\n  ;; http://data-sorcery.org/2009/06/06/fitting-non-linear-models/\n\n  (defn f [theta x]\n    (let [[b1 b2 b3] theta]\n      (div (exp (mult (minus b1) x)) (plus b2 (mult b3 x)))))\n\n  (with-data (get-dataset :chwirut)\n    (view $data)\n    (def chart (scatter-plot ($ :x) ($ :y)))\n    (view chart)\n    (add-lines chart ($ :x) (f [0 0.01 0] ($ :x)))\n\n    ;; manipulate the model line to find some good start values.\n    ;; give the index of the line data (i.e. 1) to set-data.\n    (let [x ($ :x)]\n      (sliders [b1 (range 0 2 0.01)\n                b2 (range 0.01 2 0.01)\n                b3 (range 0 2 0.01)]\n        (set-data chart [x (f [b1 b2 b3] x)] 1))))\n\n",
   :var-type "macro",
   :line 3496,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists
   ([f [& slider-values]] [f [& slider-values] [& slider-labels]]),
   :name "sliders*",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L3466",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/sliders*",
   :doc
   "sliders*\n\nExamples:\n  (use '(incanter core stats charts))\n\n  (let [x (range -3 3 0.1)]\n    (do\n      (def pdf-chart (xy-plot x (pdf-normal x :mean -3 :sd 0.1)))\n      (view pdf-chart)\n      (sliders* #(set-data pdf-chart [x (pdf-normal x :mean %1 :sd %2)])\n               [(range -3 3 0.1) (range 0.1 10 0.1)]\n               [\"mean\" \"sd\"])))\n",
   :var-type "function",
   :line 3466,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([categories values & options]),
   :name "stacked-area-chart",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L2313",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/stacked-area-chart",
   :doc
   "\nReturns a JFreeChart object representing an stacked-area-chart of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nArguments:\n  categories -- a sequence of categories\n  values -- a sequence of numeric values\n\nOptions:\n  :title (default '') main title\n  :x-label (default 'Categories')\n  :y-label (default 'Value')\n  :series-label\n  :legend (default false) prints legend\n  :vertical (default true) the orientation of the plot\n  :group-by (default nil) -- a vector of values used to group the values into\n                             series within each category.\n\n\nSee also:\n  view and save\n\nExamples:\n\n\n  (use '(incanter core stats charts datasets))\n\n  (with-data (get-dataset :co2)\n    (view (stacked-area-chart :Type :uptake\n                     :title \"CO2 Uptake\"\n                     :group-by :Treatment\n                     :x-label \"Grass Types\" :y-label \"Uptake\"\n                    :legend true)))\n\n\n  (def data (get-dataset :airline-passengers))\n  (view (stacked-area-chart :year :passengers :group-by :month :legend true :data data))\n\n  (with-data  (get-dataset :airline-passengers)\n    (view (stacked-area-chart :month :passengers :group-by :year :legend true)))\n\n\n  (def data (get-dataset :austres))\n  (view data)\n  (def plot (stacked-area-chart :year :population :group-by :quarter :legend true :data data))\n  (view plot)\n  (save plot \"/tmp/austres_plot.png\" :width 1000)\n  (view \"file:///tmp/austres_plot.png\")\n\n\n  (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))\n  (def years (mapcat identity (repeat 4 [2007 2008 2009])))\n  (def values (sample-uniform 12 :integers true :max 100))\n  (view (stacked-area-chart years values :group-by seasons :legend true))\n\n  (view (stacked-area-chart [\"a\" \"a\" \"b\" \"b\" \"c\" \"c\" ] [10 20 30 10 40 20]\n                   :legend true\n                   :group-by [\"I\" \"II\" \"I\" \"II\" \"I\" \"II\"]))\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :var-type "macro",
   :line 2313,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([categories values & options]),
   :name "stacked-bar-chart",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L2440",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/stacked-bar-chart",
   :doc
   "\nReturns a JFreeChart object representing an stacked-bar-chart of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nArguments:\n  categories -- a sequence of categories\n  values -- a sequence of numeric values\n\nOptions:\n  :title (default '') main title\n  :x-label (default 'Categories')\n  :y-label (default 'Value')\n  :series-label\n  :legend (default false) prints legend\n  :vertical (default true) the orientation of the plot\n  :group-by (default nil) -- a vector of values used to group the values into\n                             series within each category.\n\n\nSee also:\n  view and save\n\nExamples:\n\n\n  (use '(incanter core stats charts datasets))\n\n  (with-data (get-dataset :co2)\n    (view (stacked-bar-chart :Type :uptake\n                     :title \"CO2 Uptake\"\n                     :group-by :Treatment\n                     :x-label \"Grass Types\" :y-label \"Uptake\"\n                    :legend true)))\n\n\n  (def data (get-dataset :airline-passengers))\n  (view (stacked-bar-chart :year :passengers :group-by :month :legend true :data data))\n\n  (with-data  (get-dataset :airline-passengers)\n    (view (stacked-bar-chart :month :passengers :group-by :year :legend true)))\n\n\n  (def data (get-dataset :austres))\n  (view data)\n  (def plot (stacked-bar-chart :year :population :group-by :quarter :legend true :data data))\n  (view plot)\n  (save plot \"/tmp/austres_plot.png\" :width 1000)\n  (view \"file:///tmp/austres_plot.png\")\n\n\n  (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))\n  (def years (mapcat identity (repeat 4 [2007 2008 2009])))\n  (def values (sample-uniform 12 :integers true :max 100))\n  (view (stacked-bar-chart years values :group-by seasons :legend true))\n\n  (view (stacked-bar-chart [\"a\" \"b\" \"c\"] [10 20 30]))\n  (view (stacked-bar-chart [\"a\" \"a\" \"b\" \"b\" \"c\" \"c\" ] [10 20 30 10 40 20]\n                   :legend true\n                   :group-by [\"I\" \"II\" \"I\" \"II\" \"I\" \"II\"]))\n\n  ;; add a series label\n  (def plot (stacked-bar-chart [\"a\" \"b\" \"c\"] [10 20 30] :legend true :series-label \"s1\"))\n  (view plot)\n  (add-categories plot [\"a\" \"b\" \"c\"] [5 25 40] :series-label \"s2\")\n\n  (view (stacked-bar-chart (sample \"abcdefghij\" :size 10 :replacement true)\n                   (sample-uniform 10 :max 50) :legend true))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :var-type "macro",
   :line 2440,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([x y & options]),
   :name "time-series-plot",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L1315",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/time-series-plot",
   :doc
   "\nReturns a JFreeChart object representing a time series plot of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file. Sequence passed in for the x axis should be\nnumber of milliseconds from the epoch (1 January 1970).\n\nOptions:\n  :data (default nil) If the :data option is provided a dataset,\n                      column names can be used instead of sequences\n                      of data as arguments to xy-plot.\n  :title (default '') main title\n  :x-label (default x expression)\n  :y-label (default y expression)\n  :legend (default false) prints legend\n  :series-label (default x expression)\n  :group-by (default nil) -- a vector of values used to group the x and y values into series.\n\nSee also:\n  view, save, add-points, add-lines\n\nExamples:\n\n  (use '(incanter core stats charts))\n  (require '[clj-time.core :refer [date-time]])\n\n  ;; plot numbers against years starting with 1900\n  (def dates (map #(-> (date-time (+ 1900 %))\n                       .getMillis)\n                  (range 100)))\n  (def y (range 100))\n  (view (time-series-plot dates y\n                          :x-label \"Year\"))\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :var-type "macro",
   :line 1315,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([x & options]),
   :name "trace-plot",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L3210",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/trace-plot",
   :doc
   "\nReturns a trace-plot object, use the 'view' function to display it.\n\nOptions:\n  :data (default nil) If the :data option is provided a dataset,\n                      a column name can be used instead of a sequence\n                      of data for argument x.\n  :title (default 'Trace Plot') main title\n  :x-label (default 'Iteration')\n  :y-label (default 'Value')\n  :series-label (default 'Value')\n\n  Examples:\n    (use '(incanter core datasets stats bayes charts))\n    (def ols-data (to-matrix (get-dataset :survey)))\n    (def x (sel ols-data (range 0 2313) (range 1 10)))\n    (def y (sel ols-data (range 0 2313) 10))\n    (def sample-params (sample-model-params 5000 (linear-model y x :intercept false)))\n    (view (trace-plot (:var sample-params)))\n\n    (view (trace-plot (sel (:coefs sample-params) :cols 0)))\n\n",
   :var-type "function",
   :line 3210,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([] [x y & options]),
   :name "xy-plot",
   :namespace "incanter.charts",
   :source-url
   "https://github.com/liebke/incanter/blob/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj#L1154",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/2821ba96b44a1bbea97fb186beb3ef24809493e6/modules/incanter-charts/src/incanter/charts.clj",
   :wiki-url
   "http://liebke.github.com/incanter//charts-api.html#incanter.charts/xy-plot",
   :doc
   "\nReturns a JFreeChart object representing a xy-plot of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nOptions:\n  :data (default nil) If the :data option is provided a dataset,\n                      column names can be used instead of sequences\n                      of data as arguments to xy-plot.\n  :title (default 'XY Plot') main title\n  :x-label (default x expression)\n  :y-label (default 'Frequency')\n  :legend (default false) prints legend\n  :series-label (default x expression)\n  :group-by (default nil) -- a vector of values used to group the x and y values into series.\n  :points (default false) includes point-markers\n  :auto-sort (default true) sort data by x\n\nSee also:\n  view, save, add-points, add-lines\n\nExamples:\n\n  (use '(incanter core stats charts))\n\n  ;; plot the cosine function\n  (def x (range -1 5 0.01))\n  (def y (cos (mult 2 Math/PI x)))\n  (view (xy-plot x y))\n\n  ;; plot gamma pdf with different parameters\n  (def x2 (range 0 20 0.1))\n  (def gamma-plot (xy-plot x2 (pdf-gamma x2 :shape 1 :rate 2)\n                             :legend true\n                             :title \"Gamma PDF\"\n                             :y-label \"Density\"))\n  (view gamma-plot)\n  (add-lines gamma-plot x2 (pdf-gamma x2 :shape 2 :rate 2))\n  (add-lines gamma-plot x2 (pdf-gamma x2 :shape 3 :rate 2))\n  (add-lines gamma-plot x2 (pdf-gamma x2 :shape 5 :rate 1))\n  (add-lines gamma-plot x2 (pdf-gamma x2 :shape 9 :rate 0.5))\n\n  ;; use :group-by option\n  (use '(incanter core charts datasets))\n\n  (with-data (get-dataset :chick-weight)\n    (view (xy-plot :Time :weight :group-by :Chick)))\n\n\n  ;; see INCANTER_HOME/examples/probability_plots.clj for more examples of plots\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html\n\n",
   :var-type "macro",
   :line 1154,
   :file "modules/incanter-charts/src/incanter/charts.clj"}
  {:arglists ([cols] [arg1 arg2] [rows cols data]),
   :name "$",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1555",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/$",
   :doc
   "\nAn alias to (sel (second args) :cols (first args)). If given only a single argument,\nit will use the $data binding for the first argument, which is set with\nthe with-data macro.\n\nExamples:\n  (use '(incanter core stats charts datasets))\n\n  (def cars (get-dataset :cars))\n  ($ :speed cars)\n\n\n  (with-data cars\n    (def lm (linear-model ($ :dist) ($ :speed)))\n    (doto (scatter-plot ($ :speed) ($ :dist))\n      view\n      (add-lines ($ :speed) (:fitted lm))))\n\n  ;; standardize speed and dist and append the standardized variables to the original dataset\n  (with-data (get-dataset :cars)\n    (view (conj-cols $data\n                     (sweep (sweep ($ :speed)) :stat sd :fun div)\n                     (sweep (sweep ($ :dist)) :stat sd :fun div))))\n\n  (with-data (get-dataset :iris)\n    (view $data)\n    (view ($ [:Sepal.Length :Sepal.Width :Species]))\n    (view ($ [:not :Petal.Width :Petal.Length]))\n    (view ($ 0 [:not :Petal.Width :Petal.Length])))\n\n\n   (use 'incanter.core)\n   (def mat (matrix (range 9) 3))\n   (view mat)\n   ($ 2 2 mat)\n   ($ [0 2] 2 mat)\n   ($ :all 1 mat)\n   ($ 1 mat)\n   ($ [:not 1] mat)\n   ($ 0 :all mat)\n   ($ [0 2] [0 2] mat)\n   ($ [:not 1] [:not 1] mat)\n   ($ [:not 1] :all mat)\n   ($ [0 2] [:not 1] mat)\n   ($ [0 2] [:not 1 2] mat)\n   ($ [0 2] [:not (range 2)] mat)\n   ($ [:not (range 2)] [0 2] mat)\n\n   (with-data mat\n     ($ 0 0))\n   (with-data mat\n     ($ [0 2] 2 mat))\n   (with-data mat\n     ($ :all 1))\n   (with-data mat\n     ($ [0 2] [0 2]))\n   (with-data mat\n     ($ [:not 1] :all))\n   (with-data mat\n     ($ [0 2] [:not 1]))\n\n\n   (use 'incanter.datasets)\n   (view (get-dataset :cars))\n   ($ (range 5) 0 (get-dataset :cars))\n   ($ (range 5) :all (get-dataset :cars))\n   ($ :all (range 2) (get-dataset :cars))\n\n   ($ (range 5) :dist (get-dataset :cars))\n   ($ [:not (range 5)] 0 (get-dataset :cars))\n   ($ [:not 0 1 2 3 4] 0 (get-dataset :cars))\n   (with-data (get-dataset :cars)\n     ($ 0 :dist))\n\n   (with-data (get-dataset :hair-eye-color)\n     (view $data)\n     (view ($ [:not :gender])))\n",
   :var-type "function",
   :line 1555,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([& equation]),
   :name "$=",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2822",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/$=",
   :doc
   "\nFormula macro translates from infix to prefix\n\n\nExamples:\n\n  (use 'incanter.core)\n  ($= 7 + 8)\n  ($= [1 2 3] + [4 5 6])\n  ($= [1 2 3] + (sin [4 5 6]))\n  ($= [1 2 3] <*> (trans [1 2 3]))\n  ($= [1 2 3] * [1 2 3])\n  ($= [1 2 3] <x> [1 2 3])\n  ($= 9 * 8 ** 3)\n  ($= (sin Math/PI) * 10)\n\n  ($= 10 + 20 * (4 - 5) / 6)\n\n  ($= 20 * (4 - 5) / 6)\n\n  (let [x 10\n        y -5]\n    ($= x + y / -10))\n\n  ($= 3 ** 3)\n\n  ($= [1 2 3] * [1 2 3])\n  ($= [1 2 3] / (sq [1 2 3]) + [5 6 7])\n\n  ($= (sqrt 5 * 5 + 3 * 3))\n  ($= (sq [1 2 3] + [1 2 3]))\n  ($= ((5 + 4) * 5))\n  ($= ((5 + 4 * (3 - 4)) / (5 + 8) * 6))\n  ($= [1 2 3] + 5)\n  ($= (matrix [[1 2] [4 5]]) + 6)\n  ($= (trans [[1 2] [4 5]]) + 6)\n\n  ($= (trans [[1 2] [4 5]]) <*> (matrix [[1 2] [4 5]]))\n\n\n  (use '(incanter core charts))\n  (defn f [x] ($= x ** 2 + 3 * x + 5))\n  (f 5)\n  (view (function-plot f -10 10))\n  (view (function-plot #($= % ** 2 + 3 * % + 5) -10 10))\n  (view (function-plot (fn [x] ($= x ** 2 + 3 * x + 5)) -10 10))\n  (let [x (range -10 10 0.1)]\n    (view (xy-plot x ($= x ** 3 - 5 * x ** 2 + 3 * x + 5))))\n\n  ($= (5 + 7))\n  ($= (trans [1 2 3 4]) <*> [1 2 3 4])\n  ($= [1 2 3 4] <*> (trans [1 2 3 4]))\n\n  ($= [1 2 3 4] <*> (trans [1 2 3 4]))\n  ($= [1 2 3 4] <x> (trans [1 2 3 4]))\n\n\n  ;; kronecker product example\n  ($= (matrix [[1 2] [3 4] [5 6]]) <x> 4)\n  ($= (matrix [[1 2] [3 4] [5 6]]) <x> (matrix [[1 2] [3 4]]))\n  ($= [1 2 3 4] <x> 4)\n\n  ($= 3 > (5 * 2/7))\n\n  (use '(incanter core datasets charts))\n  (with-data (get-dataset :cars)\n    (doto (scatter-plot :speed :dist :data ($where ($fn [speed dist] ($= dist / speed < 2))))\n      (add-points :speed :dist :data ($where ($fn [speed dist] ($= dist / speed >= 2))))\n      (add-lines ($ :speed) ($= 2 * ($ :speed)))\n      view))\n\n",
   :var-type "macro",
   :line 2822,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:name "$data",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L47",
   :dynamic true,
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/$data",
   :doc
   "This variable is bound to a dataset when the with-data macro is used.\nfunctions like $ and $where can use $data as a default argument.",
   :var-type "var",
   :line 47,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([col-bindings body]),
   :name "$fn",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1831",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/$fn",
   :doc
   "\nA simple macro used as syntactic sugar for defining predicate functions to be used\nin the $where function. The supplied arguments should be column names of a dataset.\nThis macro performs map destructuring on the arguments.\n\nFor instance,\n($fn [speed] (< speed 10)) => (fn [{:keys [speed]}] (< speed 10))\n\nExamples:\n  (use '(incanter core datasets))\n  (view ($where ($fn [speed dist] (or (> speed 20) (< dist 10))) (get-dataset :cars)))\n\n  (view ($where ($fn [speed dist] (< (/ dist speed) 2)) (get-dataset :cars)))\n\n  (use '(incanter core datasets charts))\n  (with-data (get-dataset :cars)\n    (doto (scatter-plot :speed :dist :data ($where ($fn [speed dist] (< (/ dist speed) 2))))\n      (add-points :speed :dist :data ($where ($fn [speed dist] (>= (/ dist speed) 2))))\n      (add-lines ($ :speed) (mult 2 ($ :speed)))\n      view))\n\n\n  (let [passed? ($fn [speed dist] (< (/ dist speed) 2))\n        failed? (complement passed?)]\n    (with-data (get-dataset :cars)\n      (doto (scatter-plot :speed :dist :data ($where passed?))\n        (add-points :speed :dist :data ($where failed?))\n        (add-lines ($ :speed) (mult 2 ($ :speed)))\n        view)))\n\n\n  (use '(incanter core stats charts))\n  (let [above-sine? ($fn [col-0 col-1] (> col-1 (sin col-0)))\n        below-sine? (complement above-sine?)]\n    (with-data (conj-cols (sample-uniform 1000 :min -5 :max 5)\n                          (sample-uniform 1000 :min -1 :max 1))\n      (doto (function-plot sin -5 5)\n        (add-points :col-0 :col-1 :data ($where above-sine?))\n        (add-points :col-0 :col-1 :data ($where below-sine?))\n        view)))\n\n\n  (view ($where ($fn [] (> (rand) 0.9)) (get-dataset :cars)))\n\n  (view ($where ($fn [Species] ($in Species #{\"virginica\" \"setosa\"})) (get-dataset :iris)))\n",
   :var-type "macro",
   :line 1831,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([cols] [cols data]),
   :name "$group-by",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1883",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/$group-by",
   :doc
   "\nReturns a map of datasets keyed by a query-map corresponding the group.\n\nExamples:\n\n  (use '(incanter core datasets))\n  ($group-by :Species (get-dataset :iris))\n\n  ($group-by [:hair :eye] (get-dataset :hair-eye-color))\n\n  (with-data (get-dataset :hair-eye-color)\n    ($group-by [:hair :eye]))\n",
   :var-type "function",
   :line 1883,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists
   ([[left-keys right-keys] left-data]
    [[left-keys right-keys] left-data right-data]),
   :name "$join",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1971",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/$join",
   :doc
   "\nReturns a dataset created by right-joining two datasets.\nThe join is based on one or more columns in the datasets.\nIf used within the body of the with-data macro, the second\ndataset is optional, defaulting the the dataset bound to $data.\n\n\nExamples:\n  (use '(incanter core stats datasets charts))\n  (def iris (get-dataset :iris))\n\n\n\n  (def lookup (dataset [:species :species-key] [[\"setosa\" :setosa]\n                                                [\"versicolor\" :versicolor]\n                                                [\"virginica\" :virginica]]))\n  (view ($join [:species :Species] lookup iris))\n\n (def hair-eye-color (get-dataset :hair-eye-color))\n (def lookup2 (conj-cols ($ [:hair :eye :gender] hair-eye-color) (range (nrow hair-eye-color))))\n (view ($join [[:col-0 :col-1 :col-2] [:hair :eye :gender]] lookup2 hair-eye-color))\n\n (with-data hair-eye-color\n   (view ($join [[:col-0 :col-1 :col-2] [:hair :eye :gender]] lookup2)))\n\n\n (def lookup3 (dataset [:gender :hair :hair-gender] [[\"male\" \"black\" :male-black]\n                                                     [\"male\" \"brown\" :male-brown]\n                                                     [\"male\" \"red\" :male-red]\n                                                     [\"male\" \"blond\" :male-blond]\n                                                     [\"female\" \"black\" :female-black]\n                                                     [\"female\" \"brown\" :female-brown]\n                                                     [\"female\" \"red\" :female-red]\n                                                     [\"female\" \"blond\" :female-blond]]))\n\n (view ($join [[:gender :hair] [:gender :hair]] lookup3 hair-eye-color))\n\n (use 'incanter.charts)\n (with-data (->>  (get-dataset :hair-eye-color)\n                  ($where {:hair {:in #{\"brown\" \"blond\"}}})\n                  ($rollup :sum :count [:hair :gender])\n                  ($join [[:gender :hair] [:gender :hair]] lookup3)\n                  ($order :count :desc))\n     (view $data)\n     (view (bar-chart :hair :count :group-by :gender :legend true)))\n",
   :var-type "function",
   :line 1971,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([fun col-keys data] [fun col-keys]),
   :name "$map",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1936",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/$map",
   :doc
   "\nThis function returns a sequence resulting from mapping the given function over\nthe value(s) for the given column key(s) of the given dataset.\nLike other '$*' functions, it will use $data as the default dataset\nif none is provided, where $data is set using the with-data macro.\n\nExamples:\n\n  (use '(incanter core datasets))\n  (def cars (get-dataset :cars))\n\n  ($map (fn [s] (/ s)) :speed cars)\n  ($map (fn [s d] (/ s d)) [:speed :dist] cars)\n\n  (map (fn [s d] (/ s d)) ($ :speed cars) ($ :speed cars))\n\n  (with-data (get-dataset :cars)\n    (view ($map (fn [s] (/ s)) :speed))\n    (view ($map (fn [s d] (/ s d)) [:speed :dist])))\n\n  ;; calculate the speed to dist ratio and append as new column to dataset\n  (with-data (get-dataset :cars)\n    (conj-cols $data ($map (fn [s d] (/ s d)) [:speed :dist])))\n",
   :var-type "function",
   :line 1936,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([cols order] [cols order data]),
   :name "$order",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1801",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/$order",
   :doc
   "\nSorts a dataset by the given columns in either ascending (:asc)\nor descending (:desc) order. If used within a the body of\nthe with-data macro, the data argument is optional, defaulting\nto the dataset bound to the variable $data.\n\nExamples:\n\n  (use '(incanter core charts datasets))\n  (def iris (get-datset :iris))\n  (view ($order :Sepal.Length :asc iris))\n  (view ($order [:Sepal.Width :Sepal.Length] :desc iris))\n\n  (with-data (get-dataset :iris)\n    (view ($order [:Petal.Length :Sepal.Length] :desc)))\n\n",
   :var-type "function",
   :line 1801,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists
   ([summary-fun col-name group-by]
    [summary-fun col-name group-by data]),
   :name "$rollup",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1713",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/$rollup",
   :doc
   "\nReturns a dataset that uses the given summary function (or function identifier keyword)\nto rollup the given column based on a set of group-by columns. The summary function\nshould accept a single sequence of values and return a single summary value. Alternatively,\nyou can provide a keyword identifier of a set of built-in functions including:\n\n:max -- the maximum value of the data in each group\n:min -- the minimum value of the data in each group\n:sum -- the sum of the data in each group\n:count -- the number of elements in each group\n:mean -- the mean of the data in each group\n\n\nLike the other '$' dataset functions, $rollup will use the dataset bound to $data\n(see the with-data macro) if a dataset is not provided as an argument.\n\nExamples:\n\n  (use '(incanter core datasets))\n\n  (def iris (get-dataset :iris))\n  ($rollup :mean :Sepal.Length :Species iris)\n  ($rollup :count :Sepal.Length :Species iris)\n  ($rollup :max :Sepal.Length :Species iris)\n  ($rollup :min :Sepal.Length :Species iris)\n\n  ;; The following is an example using a custom function, but since all the\n  ;; iris measurements are positive, the built-in mean function could have\n  ;; been used instead.\n\n  (use 'incanter.stats)\n  ($rollup #(mean (abs %)) :Sepal.Width :Species iris)\n\n  ($rollup sd :Sepal.Length :Species iris)\n  ($rollup variance :Sepal.Length :Species iris)\n  ($rollup median :Sepal.Length :Species iris)\n\n  (def hair-eye-color (get-dataset :hair-eye-color))\n  ($rollup :mean :count [:hair :eye] hair-eye-color)\n\n  (use 'incanter.charts)\n  (with-data ($rollup :mean :Sepal.Length :Species iris)\n    (view (bar-chart :Species :Sepal.Length)))\n\n   ;; the following examples use the built-in data set called hair-eye-color.\n\n   (with-data ($rollup :mean :count [:hair :eye] hair-eye-color)\n     (view (bar-chart :hair :count :group-by :eye :legend true)))\n\n   (with-data (->>  (get-dataset :hair-eye-color)\n                    ($where {:hair {:in #{\"brown\" \"blond\"}}})\n                    ($rollup :sum :count [:hair :eye])\n                    ($order :count :desc))\n     (view $data)\n     (view (bar-chart :hair :count :group-by :eye :legend true)))\n",
   :var-type "function",
   :line 1713,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([query-map] [query-map data]),
   :name "$where",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1682",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/$where",
   :doc
   "\nAn alias to (query-dataset (second args) (first args)). If given only a single argument,\nit will use the $data binding for the first argument, which is set with\nthe with-data macro.\n\nExamples:\n\n  (use '(incanter core datasets))\n\n  (def cars (get-dataset :cars))\n  ($where {:speed 10} cars)\n\n  ;; use the with-data macro and the one arg version of $where\n  (with-data cars\n    (view ($where {:speed {:$gt -10 :$lt 10}}))\n    (view ($where {:dist {:$in #{10 12 16}}}))\n    (view ($where {:dist {:$nin #{10 12 16}}})))\n\n  ;; create a dataset where :speed greater than 10 or less than -10\n  (with-data (get-dataset :cars)\n    (view (-> ($where {:speed {:$gt 20}})\n                    (conj-rows ($where {:speed {:$lt 10}})))))\n",
   :var-type "function",
   :line 1682,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([column-names rows]),
   :name "->Dataset",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L52",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/->Dataset",
   :doc "Positional factory function for class incanter.core.Dataset.",
   :var-type "function",
   :line 52,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([A]),
   :name "abs",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L576",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/abs",
   :doc
   "\nReturns the absolute value of the elements in the given matrix, sequence or number.\nEquivalent to R's abs function.\n",
   :var-type "function",
   :line 576,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([A]),
   :name "acos",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L608",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/acos",
   :doc
   "\nReturns the arc cosine of the elements in the given matrix, sequence or number.\nEquivalent to R's acos function.",
   :var-type "function",
   :line 608,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([column-name values] [column-name values data]),
   :name "add-column",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2072",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/add-column",
   :doc "Adds a column, with given values, to a dataset.",
   :var-type "function",
   :line 2072,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists
   ([column-name from-columns f] [column-name from-columns f data]),
   :name "add-derived-column",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2084",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/add-derived-column",
   :doc
   "\nThis function adds a column to a dataset that is a function of\nexisting columns. If no dataset is provided, $data (bound by the\nwith-data macro) will be used. f should be a function of the\nfrom-columns, with arguments in that order.\n\nExamples:\n  (use '(incanter core datasets))\n  (def cars (get-dataset :cars))\n\n  (add-derived-column :dist-over-speed [:dist :speed] (fn [d s] (/ d s)) cars)\n\n  (with-data (get-dataset :cars)\n    (view (add-derived-column :speed**-1 [:speed] #(/ 1.0 %))))",
   :var-type "function",
   :line 2084,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([A]),
   :name "asin",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L592",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/asin",
   :doc
   "\nReturns the arc sine of the elements in the given matrix, sequence or number.\nEquivalent to R's asin function.\n",
   :var-type "function",
   :line 592,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([A]),
   :name "atan",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L623",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/atan",
   :doc
   "\nReturns the arc tangent of the elements in the given matrix, sequence or number.\nEquivalent to R's atan function.\n",
   :var-type "function",
   :line 623,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([& args]),
   :name "atan2",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L519",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/atan2",
   :doc
   "\nReturns the atan2 of the elements in the given matrices, sequences or numbers.\nEquivalent to R's atan2 function.\n",
   :var-type "function",
   :line 519,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([a b]),
   :name "beta",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2391",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/beta",
   :doc
   "\nEquivalent to R's beta function.\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Gamma.html\n",
   :var-type "function",
   :line 2391,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([& args]),
   :name "bind-columns",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L369",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/bind-columns",
   :doc
   "\nReturns the matrix resulting from concatenating the given matrices\nand/or sequences by their columns. Equivalent to R's cbind.\n\nExamples:\n(def A (matrix [[1 2 3]\n                [4 5 6]\n                [7 8 9]]))\n\n(def B (matrix [10 11 12]))\n\n(bind-columns A B)\n\n(bind-columns [1 2 3 4] [5 6 7 8])\n",
   :var-type "function",
   :line 369,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([& args]),
   :name "bind-rows",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L330",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/bind-rows",
   :doc
   "\nReturns the matrix resulting from concatenating the given matrices\nand/or sequences by their rows. Equivalent to R's rbind.\n\nExamples:\n(def A (matrix [[1 2 3]\n                [4 5 6]\n                [7 8 9]]))\n\n(def B (matrix [[10 11 12]\n                [13 14 15]]))\n\n(bind-rows A B)\n\n(bind-rows [1 2 3 4] [5 6 7 8])\n",
   :var-type "function",
   :line 330,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists
   ([& {:keys [data ordered? labels levels], :or {ordered? false}}]),
   :name "categorical-var",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2279",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/categorical-var",
   :doc
   "\nReturns a categorical variable based on the values in the given collection.\nEquivalent to R's factor function.\n\nOptions:\n  :data (default nil) factors will be extracted from the given data.\n  :ordered? (default false) indicates that the variable is ordinal.\n  :labels (default (sort (into #{} data)))\n  :levels (range (count labels))\n\nExamples:\n  (categorical-var :data [:a :a :c :b :a :c :c])\n  (categorical-var :labels [:a :b :c])\n  (categorical-var :labels [:a :b :c] :levels [10 20 30])\n  (categorical-var :levels [1 2 3])\n\n",
   :var-type "function",
   :line 2279,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([n k]),
   :name "choose",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L648",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/choose",
   :doc
   "\nReturns number of k-combinations (each of size k) from a set S with\nn elements (size n), which is the binomial coefficient (also known\nas the 'choose function') [wikipedia]\n      choose = n!/(k!(n - k)!)\n\nEquivalent to R's choose function.\n\nExamples:\n  (choose 25 6) ; => 177,100\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/math/tdouble/DoubleArithmetic.html\n  http://en.wikipedia.org/wiki/Combination\n",
   :var-type "function",
   :line 648,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([data] [data colnames]),
   :name "col-names",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1481",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/col-names",
   :doc
   "\nIf given a dataset, it returns its column names. If given a dataset and a sequence\nof column names, it returns a dataset with the given column names.\n\nExamples:\n  (use '(incanter core datasets))\n  (def data (get-dataset :cars))\n  (col-names data)\n\n  (def renamed-data (col-names data [:x1 :x2]))\n  (col-names renamed-data)\n\n",
   :var-type "function",
   :line 1481,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([mat]),
   :name "condition",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1066",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/condition",
   :doc
   "\nReturns the two norm condition number, which is max(S) / min(S), where S is the diagonal matrix of singular values from an SVD decomposition.\n\n\nExamples:\n  (use 'incanter.core)\n  (def foo (matrix (range 9) 3))\n  (condition foo)\n\nReferences:\n  http://en.wikipedia.org/wiki/Condition_number\n",
   :var-type "function",
   :line 1066,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([& args]),
   :name "conj-cols",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1501",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/conj-cols",
   :doc
   "\nReturns a dataset created by merging the given datasets and/or collections.\nThere must be the same number of rows in each dataset and/or\ncollections.  Column names may be changed in order to prevent\nnaming conflicts in the conjed dataset.\n\nExamples:\n  (use '(incanter core datasets))\n  (def cars (get-dataset :cars))\n  (def x (sel cars :cols 0))\n  (view (conj-cols cars cars))\n  (view (conj-cols cars x))\n  (view (conj-cols (range (nrow cars)) cars))\n  (view (conj-cols (range 10) (range 10)))\n  (view (conj-cols {:a 1 :b 2} {:c 1 :d 2}))\n",
   :var-type "function",
   :line 1501,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([& args]),
   :name "conj-rows",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1530",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/conj-rows",
   :doc
   "\nReturns a dataset created by combining the rows of the given datasets and/or collections.\n\nExamples:\n\n  (use '(incanter core datasets))\n  (def cars (get-dataset :cars))\n  (view (conj-rows (to-dataset (range 5)) (to-dataset (range 5 10))))\n  (view (conj-rows cars cars))\n  (view (conj-rows [[1 2] [3 4]] [[5 6] [7 8]]))\n  (view (conj-rows [{:a 1 :b 2} {:a 3 :b 4}] [[5 6] [7 8]]))\n  (view (conj-rows (to-dataset [{:a 1 :b 2} {:a 3 :b 4}]) [[5 6] [7 8]]))\n  (conj-rows (range 5) (range 5 10))\n",
   :var-type "function",
   :line 1530,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([mat]),
   :name "copy",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L718",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/copy",
   :doc "Returns a copy of the given matrix.",
   :var-type "function",
   :line 718,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([A]),
   :name "cos",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L600",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/cos",
   :doc
   "\nReturns the cosine of the elements in the given matrix, sequence or number.\nEquivalent to R's cos function.\n",
   :var-type "function",
   :line 600,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([coll]),
   :name "cumulative-sum",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L877",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/cumulative-sum",
   :doc
   "\nReturns a sequence of cumulative sum for the given collection. For instance\nThe first value equals the first value of the argument, the second value is\nthe sum of the first two arguments, the third is the sum of the first three\narguments, etc.\n\nExamples:\n  (use 'incanter.core)\n  (cumulative-sum (range 100))\n",
   :var-type "function",
   :line 877,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([data]),
   :name "data-table",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2652",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/data-table",
   :doc "Creates a javax.swing.JTable given an Incanter dataset.",
   :var-type "function",
   :line 2652,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([column-names & data]),
   :name "dataset",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1190",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/dataset",
   :doc
   "\nReturns a map of type incanter.core.dataset constructed from the given column-names and\ndata. The data is either a sequence of sequences or a sequence of hash-maps.\n",
   :var-type "function",
   :line 1190,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([obj]),
   :name "dataset?",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L98",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/dataset?",
   :doc "Determines if obj is of type incanter.core.Dataset.",
   :var-type "function",
   :line 98,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([mat]),
   :name "decomp-cholesky",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L906",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/decomp-cholesky",
   :doc
   "\nReturns the Cholesky decomposition of the given matrix. Equivalent to R's\nchol function.\n\nReturns:\na matrix of the triangular factor (note: the result from\ncern.colt.matrix.linalg.DenseDoubleCholeskyDecomposition is transposed so\nthat it matches the result return from R's chol function.\n\n\n\nExamples:\n\n(use '(incanter core stats charts datasets))\n;; load the iris dataset\n(def iris (to-matrix (get-dataset :iris)))\n;; take the Cholesky decomposition of the correlation matrix of the iris data.\n(decomp-cholesky (correlation iris))\n\nReferences:\n  http://en.wikipedia.org/wiki/Cholesky_decomposition\n",
   :var-type "function",
   :line 906,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([mat]),
   :name "decomp-eigenvalue",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L975",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/decomp-eigenvalue",
   :doc
   "\nReturns the Eigenvalue Decomposition of the given matrix. Equivalent to R's eig function.\n\nReturns:\na map containing:\n:values -- vector of eigenvalues\n:vectors -- the matrix of eigenvectors\n\nExamples:\n\n(use 'incanter.core)\n(def foo (matrix (range 9) 3))\n(decomp-eigenvalue foo)\n\nReferences:\nhttp://en.wikipedia.org/wiki/Eigenvalue_decomposition\n",
   :var-type "function",
   :line 975,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([mat]),
   :name "decomp-lu",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L999",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/decomp-lu",
   :doc
   "\nReturns the LU decomposition of the given matrix.\n\nExamples:\n\n(use 'incanter.core)\n(def foo (matrix (range 9) 3))\n(decomp-lu foo)\n\n\nReturns:\n  a map containing:\n    :L -- the lower triangular factor\n    :U -- the upper triangular factor\n    :P -- the permutation matrix\n\nReferences:\n  http://en.wikipedia.org/wiki/LU_decomposition\n  http://mikiobraun.github.io/jblas/javadoc/org/jblas/Decompose.LUDecomposition.html\n",
   :var-type "function",
   :line 999,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([m & {:keys [type]}]),
   :name "decomp-qr",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1035",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/decomp-qr",
   :doc
   "\nReturns the QR decomposition of the given matrix. Equivalent to R's qr function.\n\nOptional parameters:\n:type -- possible values: :full.  default is :full\nif :full, returns the full QR decomposition\n\nReturns:\na map containing:\n:Q -- orthogonal factors\n:R -- the upper triangular factors\n\nExamples:\n\n(use 'incanter.core)\n(def foo (matrix (range 9) 3))\n(decomp-qr foo)\n(decomp-qr foo :type :full)\n\nReferences:\nhttp://en.wikipedia.org/wiki/QR_decomposition\n\n",
   :var-type "function",
   :line 1035,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([mat & {:keys [type], :or {type :full}}]),
   :name "decomp-svd",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L934",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/decomp-svd",
   :doc
   "\nReturns the Singular Value Decomposition (SVD) of the given matrix. Equivalent to\nR's svd function.\n\nOptional parameters:\n:type -- one of :full, :compact, or :values.  default is :full\nif :full, returns the full SVD\nif :compact, returns the compact SVD\nif :values, only the singular values are calculated\n\nReturns:\na map containing:\n:S -- the diagonal matrix of singular values S (the diagonal in vector form)\n:U -- the left singular vectors U\n:V -- the right singular vectors V\n\nExamples:\n\n(use 'incanter.core)\n(def foo (matrix (range 9) 3))\n(decomp-svd foo)\n(decomp-svd foo :type :full)\n(decomp-svd foo :type :compact)\n(decomp-svd foo :type :values)\n\n\nReferences:\nhttp://en.wikipedia.org/wiki/Singular_value_decomposition\n",
   :var-type "function",
   :line 934,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists
   ([& {:keys [data remove-na group-by merge], :or {remove-na true}}]),
   :name "deshape",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2123",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/deshape",
   :doc
   "\nReturns a dataset where the columns identified by :merge are collapsed into\ntwo columns called :variable and :value. The values in these columns are grouped\nby the columns identified by :group-by.\n\nExamples:\n\n  (use '(incanter core charts datasets))\n  (with-data (->> (deshape :merge [:Ahmadinejad :Rezai :Karrubi :Mousavi]\n                            :group-by :Region\n                            :data (get-dataset :iran-election))\n                  ($order :value :desc))\n    (view $data)\n    (view (bar-chart :variable :value :group-by :Region :legend true))\n\n    (view (bar-chart :Region :value :group-by :variable\n                     :legend true :vertical false))\n\n    (view (bar-chart :Region :value :legend true :vertical false\n                     :data ($order :value :desc ($rollup :sum :value :Region)))))\n\n\n\n    (def data (to-dataset [{:subject \"John Smith\" :time 1 :age 33 :weight 90 :height 1.87}\n                           {:subject \"Mary Smith\" :time 1 :height 1.54}]))\n    (view data)\n    (view (deshape :group-by [:subject :time] :merge [:age :weight :height] :data data))\n    (view (deshape :merge [:age :weight :height] :data data))\n    (view (deshape :group-by [:subject :time] :data data))\n\n    (view (deshape :merge [:age :weight :height] :remove-na false :data data))\n",
   :var-type "function",
   :line 2123,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([mat]),
   :name "det",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L789",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/det",
   :doc
   "\nReturns the determinant of the given matrix. Equivalent\nto R's det function.\n\nReferences:\n  http://en.wikipedia.org/wiki/LU_decomposition\n",
   :var-type "function",
   :line 789,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([m]),
   :name "diag",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L137",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/diag",
   :doc
   "If given a matrix, diag returns a sequence of its diagonal elements.\nIf given a sequence, it returns a matrix with the sequence's elements\non its diagonal. Equivalent to R's diag function.\n\nExamples:\n(diag [1 2 3 4])\n\n(def A (matrix [[1 2 3]\n[4 5 6]\n[7 8 9]]))\n(diag A)",
   :var-type "function",
   :line 137,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([mat]),
   :name "dim",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L119",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/dim",
   :doc
   "Returns a vector with the number of rows and columns of the given matrix.",
   :var-type "function",
   :line 119,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([& args]),
   :name "div",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L454",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/div",
   :doc
   "\nPerforms element-by-element division on multiple matrices, sequences\nand/or numbers. Equivalent to R's / operator.\n\nExamples:\n\n(def A (matrix [[1 2 3]\n                [4 5 6]\n                [7 8 9]]))\n(div A A A)\n(div A 2)\n(div 2 A)\n(div [1 2 3] [1 2 3])\n(div [1 2 3] 2)\n(div 2 [1 2 3])\n\n(div [1 2 3]) ; returns [1 1/2 13]\n",
   :var-type "function",
   :line 454,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([A]),
   :name "exp",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L569",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/exp",
   :doc
   "\nReturns the exponential of the elements in the given matrix, sequence or number.\nEquivalent to R's exp function.",
   :var-type "function",
   :line 569,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([k]),
   :name "factorial",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L631",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/factorial",
   :doc
   "\nReturns the factorial of k (k must be a positive integer). Equivalent to R's\nfactorial function.\n\nExamples:\n  (factorial 6)\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/math/tdouble/DoubleArithmetic.html\n  http://en.wikipedia.org/wiki/Factorial\n\n",
   :var-type "function",
   :line 631,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([x]),
   :name "gamma",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2381",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/gamma",
   :doc
   "\nEquivalent to R's gamma function.\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Gamma.html\n",
   :var-type "function",
   :line 2381,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([cols data]),
   :name "get-categories",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2186",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/get-categories",
   :doc
   "\nGiven a dataset and one or more column keys, returns the set of categories for them.\n\nExamples:\n\n  (use '(incanter core datasets))\n  (get-categories :eye (get-dataset :hair-eye-color))\n  (get-categories [:eye :hair] (get-dataset :hair-eye-color))\n",
   :var-type "function",
   :line 2186,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([f x-min x-max y-min y-max]),
   :name "grid-apply",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2795",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/grid-apply",
   :doc
   "\nApplies the given function f, that accepts two arguments, to a grid\ndefined by rectangle bounded x-min, y-min, x-max, y-max and returns a\nsequence of three sequences representing the cartesian product of x and y\nand z calculated by applying f to the combinations of x and y.\n",
   :var-type "function",
   :line 2795,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([mat on-cols & {:keys [cols except-cols]}]),
   :name "group-on",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1125",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/group-on",
   :doc
   "\nGroups the given matrix by the values in the columns indicated by the\n'on-cols' argument, returning a sequence of matrices. The returned\nmatrices are sorted by the value of the group column ONLY when there\nis only a single (non-vector) on-col argument.\n\nExamples:\n\n  (use '(incanter core datasets))\n  (def plant-growth (to-matrix (get-dataset :plant-growth)))\n  (group-on plant-growth 1)\n  ;; only return the first column\n  (group-on plant-growth 1 :cols 0)\n  ;; don't return the second column\n  (group-on plant-growth 1 :except-cols 1)\n\n  (def plant-growth-dummies (to-matrix (get-dataset :plant-growth) :dummies true))\n  (group-on plant-growth-dummies [1 2])\n  ;; return only the first column\n  (group-on plant-growth-dummies [1 2] :cols 0)\n  ;; don't return the last two columns\n  (group-on plant-growth-dummies [1 2] :except-cols [1 2])\n\n  ;; plot the plant groups\n  (use 'incanter.charts)\n  ;; can use destructuring if you know the number of groups\n  ;; groups are sorted only if the group is based on a single column value\n  (let [[ctrl trt1 trt2] (group-on plant-growth 1 :cols 0)]\n    (doto (box-plot ctrl)\n          (add-box-plot trt1)\n          (add-box-plot trt2)\n          view))\n",
   :var-type "function",
   :line 1125,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([mat]),
   :name "half-vectorize",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L831",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/half-vectorize",
   :doc
   "\nReturns the half-vectorization (i.e. vech) of the given matrix.\nThe half-vectorization, vech(A), of a symmetric nxn matrix A\nis the n(n+1)/2 x 1 column vector obtained by vectorizing only\nthe upper triangular part of A.\n\nFor instance:\n  (= (half-vectorize (matrix [[a b] [b d]])) (matrix [a b d]))\n\nExamples:\n  (def A (matrix [[1 2] [2 4]]))\n  (half-vectorize A)\n\nReferences:\n  http://en.wikipedia.org/wiki/Vectorization_(mathematics)\n",
   :var-type "function",
   :line 831,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([len mat] [mat]),
   :name "head",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1660",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/head",
   :doc
   "Returns the head of the dataset. 10 or full dataset by default.",
   :var-type "function",
   :line 1660,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([n]),
   :name "identity-matrix",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L126",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/identity-matrix",
   :doc
   "\nReturns an n-by-n identity matrix.\n\nExamples:\n(identity-matrix 4)\n",
   :var-type "function",
   :line 126,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([x a b]),
   :name "incomplete-beta",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2401",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/incomplete-beta",
   :doc
   "\nReturns the non-regularized incomplete beta value.\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Gamma.html\n",
   :var-type "function",
   :line 2401,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([& args]),
   :name "kronecker",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L742",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/kronecker",
   :doc
   "\nReturns the Kronecker product of the given arguments.\n\nExamples:\n\n  (def x (matrix (range 6) 2))\n  (def y (matrix (range 4) 2))\n  (kronecker 4 x)\n  (kronecker x 4)\n  (kronecker x y)\n",
   :var-type "function",
   :line 742,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([coll]),
   :name "length",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1107",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/length",
   :doc
   "\nA version of count that works on collections, matrices, and numbers.\nThe length of a number is one, the length of a collection is its count\nand the length of a matrix is the number of elements it contains (nrow*ncol).\nEquivalent to R's length function.\n",
   :var-type "function",
   :line 1107,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([A]),
   :name "log",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L543",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/log",
   :doc
   "\nReturns the natural log of the elements in the given matrix, sequence or number.\nEquivalent to R's log function.\n",
   :var-type "function",
   :line 543,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([A]),
   :name "log10",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L561",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/log10",
   :doc
   "\nReturns the log base 10 of the elements in the given matrix, sequence or number.\nEquivalent to R's log10 function.\n",
   :var-type "function",
   :line 561,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([A]),
   :name "log2",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L551",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/log2",
   :doc
   "\nReturns the log base 2 of the elements in the given matrix, sequence or number.\nEquivalent to R's log2 function.\n",
   :var-type "function",
   :line 551,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([coll] [coll seen]),
   :name "make-unique",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1459",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/make-unique",
   :doc
   "\nTake a sequence of keywords and make them unique by possibly\naltering later ones.\n",
   :var-type "function",
   :line 1459,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([m__5818__auto__]),
   :name "map->Dataset",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L52",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/map->Dataset",
   :doc
   "Factory function for class incanter.core.Dataset, taking a map of keywords to field values.",
   :var-type "function",
   :line 52,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([data] [data ncol] [init-val rows cols]),
   :name "matrix",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L56",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/matrix",
   :doc
   "\nReturns an instance of an incanter.Matrix, which is an extension of\nClatrix matrix that implements the Clojure interface\nclojure.lang.ISeq. Therefore Clojure sequence operations can be\napplied to matrices. A matrix consists of a sequence of rows, where\neach row is a one-dimensional row matrix. One-dimensional matrices are\nin turn, sequences of numbers. Equivalent to R's matrix function.\n\nExamples:\n  (def A (matrix [[1 2 3] [4 5 6] [7 8 9]])) ; produces a 3x3 matrix\n  (def A2 (matrix [1 2 3 4 5 6 7 8 9] 3)) ; produces the same 3x3 matrix\n  (def B (matrix [1 2 3 4 5 6 7 8 9])) ; produces a 9x1 column vector\n\n  (first A) ; produces a row matrix [1 2 3]\n  (rest A) ; produces a sub matrix [[4 5 6] [7 8 9]]\n  (first (first A)) ; produces 1.0\n  (rest (first A)) ; produces a row matrix [2 3]\n\n  ; since (plus row1 row2) adds the two rows element-by-element\n  (reduce plus A) ; produces the sums of the columns\n\n  ; and since (sum row1) sums the elements of the row\n  (map sum A) ; produces the sums of the rows\n\n  ; you can filter the rows using Clojure's filter function\n  (filter #(> (nth % 1) 4) A) ; returns the rows where the second column is greater than 4.\n",
   :var-type "function",
   :line 56,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([f m] [f m & ms]),
   :name "matrix-map",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1907",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/matrix-map",
   :doc
   "\nLike clojure.core/map, but will work on matrices of any dimension:\n1 x 1 (like e.g. a Double), 1 x n, n x 1, and n x m\n\nExamples:\n  (use '(incanter core))\n  (def mat (matrix (range 9) 3))\n  (matrix-map #(mod % 2) mat)\n  (matrix-map #(mod % 2) (first mat))\n  (matrix-map #(mod % 2) ($ 1 0 mat))\n  (matrix-map #(mod % 2) [1 2 3 4])\n  (matrix-map #(mod % 2) 9)\n",
   :var-type "function",
   :line 1907,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([obj]),
   :name "matrix?",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L94",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/matrix?",
   :doc "Test if obj is 'derived' clatrix.core.Matrix",
   :var-type "function",
   :line 94,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([dataset pivot-key]),
   :name "melt",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2256",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/melt",
   :doc
   "\nMelt an object into a form suitable for easy casting, like a melt function in R.\nOnly accepts one pivot key for now. e.g.\n\n  (use '(incanter core charts datasets))\n  (view (with-data (melt (get-dataset :flow-meter) :Subject)\n            (line-chart :Subject :value :group-by :variable :legend true)))\n\nSee http://www.statmethods.net/management/reshape.html for more examples.",
   :var-type "function",
   :line 2256,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([& args]),
   :name "minus",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L412",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/minus",
   :doc
   "\nPerforms element-by-element subtraction on multiple matrices, sequences\nand/or numbers. If only a single argument is provided, returns the negative\nof the given matrix, sequence, or number. Equivalent to R's - operator.\n\nExamples:\n  (def A (matrix [[1 2 3]\n                  [4 5 6]\n                  [7 8 9]]))\n  (minus A)\n  (minus A A A)\n  (minus A 2)\n  (minus 2 A)\n  (minus [1 2 3] [1 2 3])\n  (minus [1 2 3] 2)\n  (minus 2 [1 2 3])\n  (minus [1 2 3])\n",
   :var-type "function",
   :line 412,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([& args]),
   :name "mmult",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L722",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/mmult",
   :doc
   "\nReturns the matrix resulting from the matrix multiplication of the\nthe given arguments. Equivalent to R's %*% operator.\n\nExamples:\n\n  (def A (matrix [[1 2 3]\n                  [4 5 6]\n                  [7 8 9]]))\n  (mmult A (trans A))\n  (mmult A (trans A) A)\n\nReferences:\n  http://en.wikipedia.org/wiki/Matrix_multiplication\n",
   :var-type "function",
   :line 722,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([& args]),
   :name "mult",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L434",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/mult",
   :doc
   "\nPerforms element-by-element multiplication on multiple matrices, sequences\nand/or numbers. Equivalent to R's * operator.\n\nExamples:\n\n(def A (matrix [[1 2 3]\n                [4 5 6]\n                [7 8 9]]))\n(mult A A A)\n(mult A 2)\n(mult 2 A)\n(mult [1 2 3] [1 2 3])\n(mult [1 2 3] 2)\n(mult 2 [1 2 3])\n",
   :var-type "function",
   :line 434,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([mat]),
   :name "ncol",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L111",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/ncol",
   :doc
   "Returns the number of columns in the given matrix. Equivalent to R's ncol function.",
   :var-type "function",
   :line 111,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([mat]),
   :name "nrow",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L102",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/nrow",
   :doc
   "Returns the number of rows in the given matrix. Equivalent to R's nrow function.",
   :var-type "function",
   :line 102,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([& args]),
   :name "plus",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L392",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/plus",
   :doc
   "\nPerforms element-by-element addition on multiple matrices, sequences\nand/or numbers. Equivalent to R's + operator.\n\nExamples:\n\n(def A (matrix [[1 2 3]\n                [4 5 6]\n                [7 8 9]]))\n(plus A A A)\n(plus A 2)\n(plus 2 A)\n(plus [1 2 3] [1 2 3])\n(plus [1 2 3] 2)\n(plus 2 [1 2 3])\n",
   :var-type "function",
   :line 392,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([& args]),
   :name "pow",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L511",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/pow",
   :doc
   "\nThis is an element-by-element exponent function, raising the first argument\nby the exponents in the remaining arguments. Equivalent to R's ^ operator.\n",
   :var-type "function",
   :line 511,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([x]),
   :name "prod",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L867",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/prod",
   :doc "Returns the product of the given sequence.",
   :var-type "function",
   :line 867,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([data query-map]),
   :name "query-dataset",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1300",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/query-dataset",
   :doc
   "\nQueries the given dataset using the query-map, returning a new dataset.\nThe query-map uses the the dataset's column-names as keys and a\nsimple variant of the MongoDB query language.\n\nFor instance, given a dataset with two columns, :x and :category,  to query\nfor rows where :x equals 10, use the following query-map: {:x 10}.\n\nTo indicate that :x should be between 10 and 20, use {:x {:$gt 10 :$lt 20}}.\n\nTo indicate that :category should also be either :red, :green, or :blue, use :$in\n{:x {:$gt 10 :$lt 20} :y {:$in #{:green :blue :red}}}\n\nAnd to indicate that :category should not include :red, :green, or :blue, use :$nin\n{:x {:$gt 10 :$lt 20} :y {:$nin #{:green :blue :red}}}\n\nThe available query terms include :$gt, :$lt, :$gte, :$lte, :$eq, :$ne, :$in, :$nin, $fn.\n\nA row predicate function can be used instead of a query-map. The function must accept\na map, representing a row of the dataset, and return a boolean value indicating whether\nthe row should be included in the new dataset.\n\nExamples:\n  (use '(incanter core datasets))\n  (def cars (get-dataset :cars))\n\n  (view (query-dataset cars {:speed 10}))\n  (view (query-dataset cars {:speed {:$in #{17 14 19}}}))\n  (view (query-dataset cars {:speed {:$lt 20 :$gt 10}}))\n  (view (query-dataset cars {:speed {:$fn #(> (log %) 3)}}))\n\n  ;; use a row predicate function instead of a query map.\n  (view (query-dataset cars (fn [row] (> (/ (row \"speed\") (row \"dist\")) 1/2))))\n",
   :var-type "function",
   :line 1300,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([query-map]),
   :name "query-to-pred",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1249",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/query-to-pred",
   :doc
   "\nGiven a query-map, it returns a function that accepts a hash-map and returns true if it\nsatisfies the conditions specified in the provided query-map.\n\nExamples:\n\n  (use 'incanter.core)\n  (def pred (query-to-pred {:x 5 :y 7}))\n  (pred {:x 5 :y 7 :z :d})\n\n  (def pred (query-to-pred {:x 5 :y {:$gt 5 :$lt 10}}))\n  (pred {:x 5 :y 7 :z :d})\n\n  (def pred (query-to-pred {:z {:$in #{:a :b}}}))\n  (pred {:x 5 :y 7 :z :d})\n",
   :var-type "function",
   :line 1249,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([]),
   :name "quit",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2701",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/quit",
   :doc "Exits the Clojure shell.",
   :var-type "function",
   :line 2701,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([mat]),
   :name "rank",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1084",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/rank",
   :doc
   "\nReturns the effective numerical matrix rank, which is the number of nonnegligible singular values.\n\nExamples:\n\n(use 'incanter.core)\n(def foo (matrix (range 9) 3))\n(rank foo)\n\nReferences:\nhttp://en.wikipedia.org/wiki/Matrix_rank\n",
   :var-type "function",
   :line 1084,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([x a b]),
   :name "regularized-beta",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2413",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/regularized-beta",
   :doc
   "\nReturns the regularized incomplete beta value. Equivalent to R's pbeta function.\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Gamma.html\n  http://en.wikipedia.org/wiki/Regularized_incomplete_beta_function\n  http://mathworld.wolfram.com/RegularizedBetaFunction.html\n",
   :var-type "function",
   :line 2413,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([col-map] [col-map data]),
   :name "rename-cols",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2040",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/rename-cols",
   :doc
   "\nRename columns based on col-map of old-col new-col-name pairs.  If\nold-col is a number it is taken as a 0 based index for the column to\nreplace\n\nExample:\n (use '(incanter core datasets))\n (rename-cols {:Sepal.Length :s.length 3 :p.width} (get-dataset :iris))\n",
   :var-type "function",
   :line 2040,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([dset cols]),
   :name "reorder-columns",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2934",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/reorder-columns",
   :doc
   "\nProduce a new dataset with the columns in the specified order.\nReturns nil if no valid column names are given.",
   :var-type "function",
   :line 2934,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([column-name values] [column-name values data]),
   :name "replace-column",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2062",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/replace-column",
   :doc "Replaces a column in a dataset with new values.",
   :var-type "function",
   :line 2062,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([x] [x y] [x y & more]),
   :name "safe-div",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L478",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/safe-div",
   :doc
   "\nDivideByZero safe alternative to clojures / function,\ndetects divide by zero and returns Infinity, -Infinity or NaN as appropriate.\nNote: Does not work on matrices, only primitive types\n",
   :var-type "function",
   :line 478,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:file "modules/incanter-core/src/incanter/core.clj",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2727",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/save",
   :namespace "incanter.core",
   :line 2727,
   :var-type "multimethod",
   :doc
   "\nSave is a multi-function that is used to write matrices, datasets and\ncharts (in png format) to a file.\n\nArguments:\n  obj -- is a matrix, dataset, or chart object\n  filename -- the filename to create.\n\nMatrix and dataset options:\n  :delim (default \\,) column delimiter\n  :header (default nil) an sequence of strings to be used as header line\n      for matrices the default value is nil, for datasets, the default is\n      the dataset's column-names array.\n  :append (default false) determines whether this given file should be\n      appended to. If true, a header will not be written to the file again.\n  If the filename is exactly \"-\" then *out* the matrix/dataset will be\n      written to *out*\n\nChart options:\n  :width (default 500)\n  :height (default 400)\n\n\nMatrix Examples:\n\n  (use '(incanter core io))\n  (def A (matrix (range 12) 3)) ; creates a 3x4 matrix\n  (save A \"A.dat\") ; writes A to the file A.dat, with no header and comma delimited\n  (save A \"A.dat\" :delim \\tab) ; writes A to the file A.dat, with no header and tab delimited\n\n  ;; writes A to the file A.dat, with a header and tab delimited\n  (save A \"A.dat\" :delim \\, :header [\"col1\" \"col2\" \"col3\"])\n\n\nDataset Example:\n\n  (use '(incanter core io datasets))\n  ;; read the iris sample dataset, and save it to a file.\n  (def iris (get-dataset :iris))\n  (save iris \"iris.dat\")\n\n\nChart Example:\n\n  (use '(incanter core io stats charts))\n  (save (histogram (sample-normal 1000)) \"hist.png\")\n\n  ;; chart example using java.io.OutputStream instead of filename\n  (use '(incanter core stats charts))\n  (import 'java.io.FileOutputStream)\n  (def fos (FileOutputStream. \"/tmp/hist.png\"))\n  (def hist (histogram (sample-normal 1000)))\n  (save hist fos)\n  (.close fos)\n\n  (view \"file:///tmp/hist.png\")\n\n\n",
   :name "save"}
  {:file "modules/incanter-core/src/incanter/core.clj",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L184",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/sel",
   :namespace "incanter.core",
   :line 184,
   :var-type "multimethod",
   :doc
   "\nReturns an element or subset of the given matrix, dataset, or list.\nIf the column or row is specified as an atomic object (index or name), then\nthe result will be returned as a list (only values from selected column or row).\n\nArgument:\n  a matrix object, dataset, or list.\n\nOptions:\n  :rows (default true)\n    returns all rows by default, can pass a row index or sequence of row indices\n  :cols (default true)\n    returns all columns by default, can pass a column index or sequence of column indices\n  :except-rows (default nil) can pass a row index or sequence of row indices to exclude\n  :except-cols (default nil) can pass a column index or sequence of column indices to exclude\n  :filter (default nil)\n    a function can be provided to filter the rows of the matrix\n\nExamples:\n  (use 'incanter.datasets)\n  (def iris (to-matrix (get-dataset :iris)))\n  (sel iris 0 0) ; first element\n  (sel iris :rows 0 :cols 0) ; also first element\n  (sel iris :cols 0) ; first column of all rows\n  (sel iris :cols [0 2]) ; first and third column of all rows\n  (sel iris :rows (range 10) :cols (range 2)) ; first two columns of the first 10 rows\n  (sel iris :rows (range 10)) ; all columns of the first 10 rows\n\n  ;; exclude rows or columns\n  (sel iris :except-rows (range 10)) ; all columns of all but the first 10 rows\n  (sel iris :except-cols 1) ; all columns except the second\n\n  ;; return only the first 10 even rows\n  (sel iris :rows (range 10) :filter #(even? (int (nth % 0))))\n  ;; select rows where distance (third column) is greater than 50\n  (sel iris :filter #(> (nth % 2) 4))\n\n  ;; examples with datasets\n  (use 'incanter.datasets)\n  (def us-arrests (get-dataset :us-arrests))\n  (sel us-arrests :cols \"State\")\n  (sel us-arrests :cols :State)\n\n  (sel us-arrests :cols [\"State\" \"Murder\"])\n  (sel us-arrests :cols [:State :Murder])\n",
   :name "sel"}
  {:file "modules/incanter-core/src/incanter/core.clj",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2664",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/set-data",
   :namespace "incanter.core",
   :line 2664,
   :var-type "multimethod",
   :doc
   "\nExamples:\n\n  (use '(incanter core charts datasets))\n\n  (def data (get-dataset :iris))\n  (def table (data-table data))\n  (view table)\n  ;; now view only a subset of the data\n  (set-data table ($where {:Petal.Length {:gt 6}} data))\n\n\n  ;; use sliders to dynamically select the query values\n  (let [data (get-dataset :iris)\n        table (data-table data)]\n    (view table)\n    (sliders [species [\"setosa\" \"virginica\" \"versicolor\"]\n              min-petal-length (range 0 8 0.1)]\n      (set-data table ($where {:Species species\n                               :Petal.Length {:gt min-petal-length}}\n                              data))))\n\n",
   :name "set-data"}
  {:arglists ([A]),
   :name "sin",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L584",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/sin",
   :doc
   "\nReturns the sine of the elements in the given matrix, sequence or number.\nEquivalent to R's sin function.\n",
   :var-type "function",
   :line 584,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([A B] [A]),
   :name "solve",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L773",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/solve",
   :doc
   "\nReturns a matrix solution if A is square, least squares solution otherwise.\nEquivalent to R's solve function.\n\nExamples:\n  (solve (matrix [[2 0 0] [0 2 0] [0 0 2]]))\n\nReferences:\n  http://en.wikipedia.org/wiki/Matrix_inverse\n",
   :var-type "function",
   :line 773,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([a b c]),
   :name "solve-quadratic",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2431",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/solve-quadratic",
   :doc
   "\nReturns a vector with the solution to x from the quadratic\nequation, a*x^2 + b*x + c.\n\nArguments:\n  a, b, c: coefficients of a qaudratic equation.\n\nExamples:\n  ;; -2*x^2 + 7*x + 15\n  (quadratic-formula -2 7 15)\n  ;; x^2 + -2*x + 1\n  (quadratic-formula 1 -2 1)\n\nReferences:\n  http://en.wikipedia.org/wiki/Quadratic_formula\n\n",
   :var-type "function",
   :line 2431,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([A]),
   :name "sq",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L535",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/sq",
   :doc
   "\nReturns the square of the elements in the given matrix, sequence or number.\nEquivalent to R's sq function.\n",
   :var-type "function",
   :line 535,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([A]),
   :name "sqrt",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L527",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/sqrt",
   :doc
   "\nReturns the square-root of the elements in the given matrix, sequence or number.\nEquivalent to R's sqrt function.\n",
   :var-type "function",
   :line 527,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([x]),
   :name "sum",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L860",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/sum",
   :doc "Returns the sum of the given sequence.",
   :var-type "function",
   :line 860,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([x]),
   :name "sum-of-squares",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L853",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/sum-of-squares",
   :doc "Returns the sum-of-squares of the given sequence.",
   :var-type "function",
   :line 853,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([data & {:keys [lower], :or {lower true}}]),
   :name "symmetric-matrix",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2458",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/symmetric-matrix",
   :doc
   "\nReturns a symmetric matrix from the given data, which represents the lower triangular elements\nordered by row. This is not the inverse of half-vectorize which returns a vector of the upper-triangular\nvalues, unless the :lower option is set to false.\n\nOptions:\n  :lower (default true) -- lower-triangular. Set :lower to false to reverse the half-vectorize function.\n\nExamples:\n\n  (use 'incanter.core)\n  (symmetric-matrix [1\n                     2 3\n                     4 5 6\n                     7 8 9 10])\n\n\n  (half-vectorize\n    (symmetric-matrix [1\n                       2 3\n                       4 5 6\n                       7 8 9 10] :lower false))\n",
   :var-type "function",
   :line 2458,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([len mat] [mat]),
   :name "tail",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1671",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/tail",
   :doc
   "Returns the tail of the dataset. 10 or full dataset by default.",
   :var-type "function",
   :line 1671,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([A]),
   :name "tan",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L615",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/tan",
   :doc
   "\nReturns the tangent of the elements in the given matrix, sequence or number.\nEquivalent to R's tan function.\n",
   :var-type "function",
   :line 615,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([obj & {:keys [transpose]}]),
   :name "to-dataset",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L1402",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/to-dataset",
   :doc
   "\nReturns a dataset containing the given values.\n\nExamples:\n\n  (use 'incanter.core)\n  (to-dataset 1)\n  (to-dataset :a)\n  (to-dataset [:a])\n  (to-dataset (range 10))\n  (to-dataset (range 10) :transpose true)\n  (to-dataset [[1 2] [3 4] [5 6]])\n  (to-dataset {:a 1 :b 2 :c 3})\n  (to-dataset {\"a\" 1 \"b\" 2 \"c\" 3})\n  (to-dataset [{:a 1 :b 2} {:a 1 :b 2}])\n  (to-dataset [{\"a\" 1 \"b\" 2 \"c\" 3} {\"a\" 1 \"b\" 2 \"c\" 3}])\n",
   :var-type "function",
   :line 1402,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:file "modules/incanter-core/src/incanter/core.clj",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L672",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/to-list",
   :namespace "incanter.core",
   :line 672,
   :var-type "multimethod",
   :doc
   "\nReturns a list-of-lists if the given matrix is two-dimensional\nand a flat list if the matrix is one-dimensional.\n",
   :name "to-list"}
  {:file "modules/incanter-core/src/incanter/core.clj",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2227",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/to-map",
   :namespace "incanter.core",
   :line 2227,
   :var-type "multimethod",
   :doc
   "\nTakes a dataset or matrix and returns a hash-map where the keys are\nkeyword versions of the column names, for datasets, or numbers, for\nmatrices, and the values are sequence of the column values.\n\nExamples:\n  (use '(incanter core datasets))\n\n  (to-map (get-dataset :cars))\n\n  (to-map (matrix (range 9) 3))\n\n",
   :name "to-map"}
  {:arglists ([dataset & {:keys [dummies], :or {dummies false}}]),
   :name "to-matrix",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2355",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/to-matrix",
   :doc
   "\nConverts a dataset into a matrix. Equivalent to R's as.matrix function\nfor datasets.\n\nOptions:\n  :dummies (default false) -- if true converts non-numeric variables into sets\n                              of binary dummy variables, otherwise converts\n                              them into numeric codes.\n",
   :var-type "function",
   :line 2355,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:file "modules/incanter-core/src/incanter/core.clj",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L695",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/to-vect",
   :namespace "incanter.core",
   :line 695,
   :var-type "multimethod",
   :doc
   "\nReturns a vector-of-vectors if the given matrix is two-dimensional\nand a flat vector if the matrix is one-dimensional. This is a bit\nslower than the to-list function\n",
   :name "to-vect"}
  {:arglists ([x]),
   :name "toeplitz",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2499",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/toeplitz",
   :doc
   "\nReturns the Toeplitz matrix for the given vector, which form the first row of the matrix\n",
   :var-type "function",
   :line 2499,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([mat]),
   :name "trace",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L800",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/trace",
   :doc
   "\nReturns the trace of the given matrix.\n\nReferences:\nhttp://en.wikipedia.org/wiki/Matrix_trace\n",
   :var-type "function",
   :line 800,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([mat]),
   :name "trans",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L153",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/trans",
   :doc
   "\nReturns the transpose of the given matrix. Equivalent to R's t function\n\nExamples:\n  (def A (matrix [[1 2 3]\n                  [4 5 6]\n                  [7 8 9]]))\n  (trans A)\n",
   :var-type "function",
   :line 153,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([dataset column f & args]),
   :name "transform-col",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2112",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/transform-col",
   :doc
   "\nApply function f & args to the specified column of dataset and replace the column\nwith the resulting new values.\n",
   :var-type "function",
   :line 2112,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:arglists ([mat]),
   :name "vectorize",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L810",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/vectorize",
   :doc
   "\nReturns the vectorization (i.e. vec) of the given matrix.\nThe vectorization of an m-by-n matrix A, denoted by vec(A)\nis the m*n-by-1 column vector obtain by stacking the columns\nof the matrix A on top of one another.\n\nFor instance:\n  (= (vectorize (matrix [[a b] [c d]])) (matrix [a c b d]))\n\nExamples:\n  (def A (matrix [[1 2] [3 4]]))\n  (vectorize A)\n\nReferences:\n  http://en.wikipedia.org/wiki/Vectorization_(mathematics)\n",
   :var-type "function",
   :line 810,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:file "modules/incanter-core/src/incanter/core.clj",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2514",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/view",
   :namespace "incanter.core",
   :line 2514,
   :var-type "multimethod",
   :doc
   "\nThis is a general 'view' function. When given an Incanter matrix/dataset\nor a Clojure numeric collection, it will display it in a Java Swing\nJTable. When given an Incanter chart object, it will display it in a new\nwindow. When given a URL string, it will open the location with the\nplatform's default web browser.\n\nWhen viewing charts, a :width (default 500) and :height (default 400)\noption can be provided.\n\nWhen viewing an incanter.processing sketch, set the :exit-on-close option\nto true (default is false) to kill the animation processes when you\nclose the window (this will also kill your REPL or Swank server),\notherwise those processing will continue to run in the background.\n\n\n\nExamples:\n\n  (use '(incanter core stats datasets charts))\n\n  ;; view matrices\n  (def rand-mat (matrix (sample-normal 100) 4))\n  (view rand-mat)\n\n  ;; view numeric collections\n  (view [1 2 3 4 5])\n  (view (sample-normal 100))\n\n  ;; view Incanter datasets\n  (view (get-dataset :iris))\n\n  ;; convert dataset to matrix, changing Species names to numeric codes\n  (view (to-matrix (get-dataset :iris)))\n\n  ;; convert dataset to matrix, changing Species names to dummy variables\n  (view (to-matrix (get-dataset :iris) :dummies true))\n\n  ;; view a chart\n  (view (histogram (sample-normal 1000)) :width 700 :height 700)\n\n  ;; view a URL\n  (view \"http://incanter.org\")\n\n  ;; view a PNG file\n  (save (histogram (sample-normal 1000)) \"/tmp/norm_hist.png\")\n  (view \"file:///tmp/norm_hist.png\")\n",
   :name "view"}
  {:arglists ([data-binding & body]),
   :name "with-data",
   :namespace "incanter.core",
   :source-url
   "https://github.com/liebke/incanter/blob/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj#L2203",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/982264d791dbac88f589ae8e28093f33f5d24489/modules/incanter-core/src/incanter/core.clj",
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/with-data",
   :doc
   "\nBinds the given data to $data and executes the body.\nTypically used with the $ and $where functions.\n\nExamples:\n  (use '(incanter core stats charts datasets))\n\n  (with-data  (get-dataset :cars)\n    (def lm (linear-model ($ :dist) ($ :speed)))\n    (doto (scatter-plot ($ :speed) ($ :dist))\n              (add-lines ($ :speed) (:fitted lm))\n               view))\n\n   ;; create a dataset where :speed greater than 10 or less than -10\n   (with-data (get-dataset :cars)\n     (view (-> ($where {:speed {:$gt 20}})\n                     (conj-rows ($where {:speed {:$lt 10}})))))\n",
   :var-type "macro",
   :line 2203,
   :file "modules/incanter-core/src/incanter/core.clj"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter//core-api.html#incanter.core/Dataset",
   :namespace "incanter.core",
   :var-type "record",
   :name "Dataset"}
  {:arglists
   ([dataset-key
     &
     {:keys [incanter-home from-repo],
      :or
      {incanter-home
       (or
        (System/getProperty "incanter.home")
        (System/getenv "INCANTER_HOME")),
       from-repo false}}]),
   :name "get-dataset",
   :namespace "incanter.datasets",
   :source-url
   "https://github.com/liebke/incanter/blob/4e73be3e9b03036a3a46c0127252eb3cd5076d1d/modules/incanter-io/src/incanter/datasets.clj#L92",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/4e73be3e9b03036a3a46c0127252eb3cd5076d1d/modules/incanter-io/src/incanter/datasets.clj",
   :wiki-url
   "http://liebke.github.com/incanter//datasets-api.html#incanter.datasets/get-dataset",
   :doc
   "\nReturns the sample dataset associated with the given key. Most datasets\nare from R's sample data sets, as are the descriptions below.\n\nOptions:\n\n  :incanter-home -- if the incanter.home property is not set when the JVM is\n                    started (using -Dincanter.home) or there is no INCANTER_HOME\n                    environment variable set, use the :incanter-home options to\n                    provide the parent directory of the sample data directory.\n\n  :from-repo (default false) -- If true, retrieves the dataset from the online repository\n                     instead of locally, it will do this by default if incanter-home is not set.\n\n\nDatasets:\n\n  :iris -- the Fisher's or Anderson's Iris data set gives the\n           measurements in centimeters of the variables sepal\n           length and width and petal length and width,\n           respectively, for 50 flowers from each of 3 species\n           of iris.\n\n  :cars -- The data give the speed of cars and the distances taken\n            to stop. Note that the data were recorded in the 1920s.\n\n  :survey -- survey data used in Scott Lynch's 'Introduction to Applied Bayesian Statistics\n             and Estimation for Social Scientists'\n\n  :us-arrests -- This data set contains statistics, in arrests per 100,000\n                 residents for assault, murder, and rape in each of the 50 US\n                 states in 1973. Also given is the percent of the population living\n                 in urban areas.\n\n  :flow-meter -- flow meter data used in Bland Altman Lancet paper.\n\n  :co2 -- has 84 rows and 5 columns of data from an experiment on the cold tolerance\n          of the grass species _Echinochloa crus-galli_.\n\n  :chick-weight -- has 578 rows and 4 columns from an experiment on the effect of diet\n                   on early growth of chicks.\n\n  :plant-growth -- Results from an experiment to compare yields (as measured by dried\n                   weight of plants) obtained under a control and two different\n                   treatment conditions.\n\n  :pontius -- These data are from a NIST study involving calibration of load cells.\n              The response variable (y) is the deflection and the predictor variable\n              (x) is load.\n              See http://www.itl.nist.gov/div898/strd/lls/data/Pontius.shtml\n\n  :filip -- NIST data set for linear regression certification,\n            see http://www.itl.nist.gov/div898/strd/lls/data/Filip.shtml\n\n  :longely -- This classic dataset of labor statistics was one of the first used to\n              test the accuracy of least squares computations. The response variable\n              (y) is the Total Derived Employment and the predictor variables are GNP\n              Implicit Price Deflator with Year 1954 = 100 (x1), Gross National Product\n              (x2), Unemployment (x3), Size of Armed Forces (x4), Non-Institutional\n              Population Age 14 & Over (x5), and Year (x6).\n              See http://www.itl.nist.gov/div898/strd/lls/data/Longley.shtml\n\n  :Chwirut -- These data are the result of a NIST study involving ultrasonic calibration.\n              The response variable is ultrasonic response, and the predictor variable is\n              metal distance.\n              See http://www.itl.nist.gov/div898/strd/nls/data/LINKS/DATA/Chwirut1.dat\n\n  :thurstone -- test data for non-linear least squares.\n\n  :austres -- Quarterly Time Series of the Number of Australian Residents\n\n  :hair-eye-color -- Hair and eye color of sample of students\n\n  :airline-passengers -- Monthly Airline Passenger Numbers 1949-1960\n\n  :math-prog -- Pass/fail results for a high school mathematics assessment test\n                and a freshmen college programming course.\n\n  :iran-election -- Vote counts for 30 provinces from the 2009 Iranian election.\n\n Examples:\n   (def data (get-dataset :cars))\n   (def data2 (get-dataset :cars :incanter.home \"/usr/local/packages/incanter\"))\n\n",
   :var-type "function",
   :line 92,
   :file "modules/incanter-io/src/incanter/datasets.clj"}
  {:arglists ([alpha beta]),
   :name "->Beta-rec",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L497",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/->Beta-rec",
   :doc
   "Positional factory function for class incanter.distributions.Beta-rec.",
   :var-type "function",
   :line 497,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([n p]),
   :name "->Binomial-rec",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L530",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/->Binomial-rec",
   :doc
   "Positional factory function for class incanter.distributions.Binomial-rec.",
   :var-type "function",
   :line 530,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([df]),
   :name "->ChiSquare-rec",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L561",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/->ChiSquare-rec",
   :doc
   "Positional factory function for class incanter.distributions.ChiSquare-rec.",
   :var-type "function",
   :line 561,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([n k u]),
   :name "->Combination",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L342",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/->Combination",
   :doc
   "Positional factory function for class incanter.distributions.Combination.",
   :var-type "function",
   :line 342,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([min max]),
   :name "->DoubleUniform-rec",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L788",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/->DoubleUniform-rec",
   :doc
   "Positional factory function for class incanter.distributions.DoubleUniform-rec.",
   :var-type "function",
   :line 788,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([rate]),
   :name "->Exponential-rec",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L591",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/->Exponential-rec",
   :doc
   "Positional factory function for class incanter.distributions.Exponential-rec.",
   :var-type "function",
   :line 591,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([df1 df2]),
   :name "->F",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L620",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/->F",
   :doc
   "Positional factory function for class incanter.distributions.F.",
   :var-type "function",
   :line 620,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([shape rate]),
   :name "->Gamma-rec",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L662",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/->Gamma-rec",
   :doc
   "Positional factory function for class incanter.distributions.Gamma-rec.",
   :var-type "function",
   :line 662,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([size prob]),
   :name "->NegativeBinomial-rec",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L694",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/->NegativeBinomial-rec",
   :doc
   "Positional factory function for class incanter.distributions.NegativeBinomial-rec.",
   :var-type "function",
   :line 694,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([mean sd]),
   :name "->Normal-rec",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L456",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/->Normal-rec",
   :doc
   "Positional factory function for class incanter.distributions.Normal-rec.",
   :var-type "function",
   :line 456,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([lambda]),
   :name "->Poisson-rec",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L727",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/->Poisson-rec",
   :doc
   "Positional factory function for class incanter.distributions.Poisson-rec.",
   :var-type "function",
   :line 727,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([df]),
   :name "->StudentT-rec",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L757",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/->StudentT-rec",
   :doc
   "Positional factory function for class incanter.distributions.StudentT-rec.",
   :var-type "function",
   :line 757,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([start end]),
   :name "->UniformInt",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L252",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/->UniformInt",
   :doc
   "Positional factory function for class incanter.distributions.UniformInt.",
   :var-type "function",
   :line 252,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([] [alpha beta]),
   :name "beta-distribution",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L508",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/beta-distribution",
   :doc
   "\nReturns a Beta distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  alpha      (default 1)\n  beta       (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Beta.html\n  http://en.wikipedia.org/wiki/Beta_distribution\n\nExample:\n  (pdf (beta-distribution 1 2) 0.5)\n",
   :var-type "function",
   :line 508,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([] [n p]),
   :name "binomial-distribution",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L539",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/binomial-distribution",
   :doc
   "\nReturns a Binomial distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  size       (default 1)\n  prob       (default 1/2)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Binomial.html\n  http://en.wikipedia.org/wiki/Binomial_distribution\n\nExample:\n  (pdf (binomial-distribution 20 1/4) 10)\n",
   :var-type "function",
   :line 539,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([] [df]),
   :name "chisq-distribution",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L570",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/chisq-distribution",
   :doc
   "\nReturns a Chi-square distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  df         (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/ChiSquare.html\n  http://en.wikipedia.org/wiki/Chi_square_distribution\n\nExample:\n  (pdf (chisq-distribution 2) 5.0)\n",
   :var-type "function",
   :line 570,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([n k]),
   :name "combination-distribution",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L351",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/combination-distribution",
   :doc
   "\nCreate a distribution of all the k-sized combinations of n integers.\nCan be considered a multivariate distribution over k-dimensions, where\neach dimension is a discrete random variable on the (0, n] range (though\nthese variables are decidedly non-independent).\n\nA draw from this distribution can also be considered a sample without\nreplacement from any finite set, where the values in the returned\nvector represent the indices of the items in the set.\n\nArguments:\n  n\t  The number of possible items from which to select.\n  k\t  The size of a sample (without replacement) to draw.\n\nSee also:\n  test-statistic-distribution, integer-distribution, pdf, cdf, draw, support\n\nReferences:\n  http://en.wikipedia.org/wiki/Combination\n\n",
   :var-type "function",
   :line 351,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([] [rate]),
   :name "exponential-distribution",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L600",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/exponential-distribution",
   :doc
   "\nReturns a Exponential distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  rate       (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Exponential.html\n  http://en.wikipedia.org/wiki/Exponential_distribution\n\nExample:\n  (pdf (exponential-distribution 1/2) 2.0)\n",
   :var-type "function",
   :line 600,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([] [df1 df2]),
   :name "f-distribution",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L641",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/f-distribution",
   :doc
   "\nReturns a F-distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  df1        (default 1)\n  df2        (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://en.wikipedia.org/wiki/F_distribution\n  http://mathworld.wolfram.com/F-Distribution.html\n\nExample:\n  (pdf (f-distribution 5 2) 1.0)\n",
   :var-type "function",
   :line 641,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([] [shape rate]),
   :name "gamma-distribution",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L672",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/gamma-distribution",
   :doc
   "\nReturns a Gamma distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  shape      (default 1)\n  rate       (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Gamma.html\n  http://en.wikipedia.org/wiki/Gamma_distribution\n\nExample:\n  (pdf (gamma-distribution 1 2) 10)\n",
   :var-type "function",
   :line 672,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([] [end] [start end]),
   :name "integer-distribution",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L269",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/integer-distribution",
   :doc
   "\nCreate a uniform distribution over a set of integers over\nthe (start, end] interval. An alternative method of creating\na distribution would be to just use a sequence of integers\n(e.g. (draw (range 100000))). For large sequences, like the one\nin the example, using a sequence will be require realizing the\nentire sequence before a draw can be taken. This less efficient than\ncomputing random draws based on the end points of the distribution.\n\nArguments:\nstart\tThe lowest end of the interval, such that (>= (draw d) start)\n      is always true. (Default 0)\n  end\tThe value at the upper end of the interval, such that\n        (> end (draw d)) is always true. Note the strict inequality.\n        (Default 1)\n\nSee also:\n  pdf, cdf, draw, support\n\nReferences:\n  http://en.wikipedia.org/wiki/Uniform_distribution_(discrete)\n\nExamples:\n  (pdf (integer-distribution 0 10) 3) ; returns 1/10 for any value\n  (draw (integer-distribution -5 5))\n  (draw (integer-distribution (bit-shift-left 2 1000))) ; probably a very large value\n",
   :var-type "function",
   :line 269,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([m__5818__auto__]),
   :name "map->Beta-rec",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L497",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/map->Beta-rec",
   :doc
   "Factory function for class incanter.distributions.Beta-rec, taking a map of keywords to field values.",
   :var-type "function",
   :line 497,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([m__5818__auto__]),
   :name "map->Binomial-rec",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L530",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/map->Binomial-rec",
   :doc
   "Factory function for class incanter.distributions.Binomial-rec, taking a map of keywords to field values.",
   :var-type "function",
   :line 530,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([m__5818__auto__]),
   :name "map->ChiSquare-rec",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L561",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/map->ChiSquare-rec",
   :doc
   "Factory function for class incanter.distributions.ChiSquare-rec, taking a map of keywords to field values.",
   :var-type "function",
   :line 561,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([m__5818__auto__]),
   :name "map->Combination",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L342",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/map->Combination",
   :doc
   "Factory function for class incanter.distributions.Combination, taking a map of keywords to field values.",
   :var-type "function",
   :line 342,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([m__5818__auto__]),
   :name "map->DoubleUniform-rec",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L788",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/map->DoubleUniform-rec",
   :doc
   "Factory function for class incanter.distributions.DoubleUniform-rec, taking a map of keywords to field values.",
   :var-type "function",
   :line 788,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([m__5818__auto__]),
   :name "map->Exponential-rec",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L591",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/map->Exponential-rec",
   :doc
   "Factory function for class incanter.distributions.Exponential-rec, taking a map of keywords to field values.",
   :var-type "function",
   :line 591,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([m__5818__auto__]),
   :name "map->F",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L620",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/map->F",
   :doc
   "Factory function for class incanter.distributions.F, taking a map of keywords to field values.",
   :var-type "function",
   :line 620,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([m__5818__auto__]),
   :name "map->Gamma-rec",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L662",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/map->Gamma-rec",
   :doc
   "Factory function for class incanter.distributions.Gamma-rec, taking a map of keywords to field values.",
   :var-type "function",
   :line 662,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([m__5818__auto__]),
   :name "map->NegativeBinomial-rec",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L694",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/map->NegativeBinomial-rec",
   :doc
   "Factory function for class incanter.distributions.NegativeBinomial-rec, taking a map of keywords to field values.",
   :var-type "function",
   :line 694,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([m__5818__auto__]),
   :name "map->Normal-rec",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L456",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/map->Normal-rec",
   :doc
   "Factory function for class incanter.distributions.Normal-rec, taking a map of keywords to field values.",
   :var-type "function",
   :line 456,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([m__5818__auto__]),
   :name "map->Poisson-rec",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L727",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/map->Poisson-rec",
   :doc
   "Factory function for class incanter.distributions.Poisson-rec, taking a map of keywords to field values.",
   :var-type "function",
   :line 727,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([m__5818__auto__]),
   :name "map->StudentT-rec",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L757",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/map->StudentT-rec",
   :doc
   "Factory function for class incanter.distributions.StudentT-rec, taking a map of keywords to field values.",
   :var-type "function",
   :line 757,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([m__5818__auto__]),
   :name "map->UniformInt",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L252",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/map->UniformInt",
   :doc
   "Factory function for class incanter.distributions.UniformInt, taking a map of keywords to field values.",
   :var-type "function",
   :line 252,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([] [size prob]),
   :name "neg-binomial-distribution",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L705",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/neg-binomial-distribution",
   :doc
   "\nReturns a Negative binomial distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  size       (default 10)\n  prob       (default 1/2)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/NegativeBinomial.html\n  http://en.wikipedia.org/wiki/Negative_binomial_distribution\n\nExample:\n  (pdf (neg-binomial-distribution 20 1/2) 10)\n",
   :var-type "function",
   :line 705,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([] [mean sd]),
   :name "normal-distribution",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L470",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/normal-distribution",
   :doc
   "\nReturns a Normal distribution that implements the\nincanter.distributions.Distribution protocol.\n\nArguments:\n  mean\tThe mean of the distribution. One of two parameters\n        that summarize the Normal distribution (default 0).\n  sd    The standard deviation of the distribution.\n        The second parameter that describes the Normal (default 1).\n\nSee also:\n    Distribution, pdf, cdf, draw, support\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Normal.html\n    http://en.wikipedia.org/wiki/Normal_distribution\n\nExample:\n    (pdf (normal-distribution -2 (sqrt 0.5)) 1.96)\n",
   :var-type "function",
   :line 470,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([] [lambda]),
   :name "poisson-distribution",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L736",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/poisson-distribution",
   :doc
   "\nReturns a Poisson distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  lambda     (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Poisson.html\n  http://en.wikipedia.org/wiki/Poisson_distribution\n\nExample:\n  (pdf (poisson-distribution 10) 5)\n",
   :var-type "function",
   :line 736,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([freqs]),
   :name "roulette-wheel",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L198",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/roulette-wheel",
   :doc
   "Perform a roulette wheel selection given a list of frequencies",
   :var-type "function",
   :line 198,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([] [df]),
   :name "t-distribution",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L767",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/t-distribution",
   :doc
   "\nReturns a Student-t distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  df         (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/StudentT.html\n  http://en.wikipedia.org/wiki/Student-t_distribution\n\nExample:\n  (pdf (t-distribution 10) 1.2)\n",
   :var-type "function",
   :line 767,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([test-statistic n k]),
   :name "test-statistic-distribution",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L380",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/test-statistic-distribution",
   :doc
   "\nCreate a distribution of the test-statistic over the possible\nrandom samples of treatment units from the possible units.\n\nThere are two methods for generating the distribution. The\nfirst method is enumerating all possible randomizations and\nperforming the test statistic on each. This gives the exact\ndistribution, but is only feasible for small problems.\n\nThe second method uses a combination-distribution to sample\nfor the space of possible treatment assignments and applies\nthe test statistic the sampled randomizations. While the\nresulting distribution is not exact, it is tractable for\nlarger problems.\n\nThe algorithm automatically chooses between the two methods\nby computing the number of possible randomizations and\ncomparing it to *test-statistic-iterations*. If the exact\ndistribution requires fewer than *test-statistic-iterations*\nthe enumeration method is used. Otherwise, it draws\n*test-statistic-iterations* total samples for the simulated\nmethod.\n\nBy default, the algorithm uses parallel computation. This is\ncontrolled by the function *test-statistic-map*, which is\nbound to pmap by default. Bind it to map to use a single\nthread for computation.\n\nArguments:\n  test-statistic\tA function that takes two vectors and summarizes\n      the difference between them\n  n\t  The number of total units in the pool\n  k\t  The number of treatment units per sample\n\nSee also:\n  combination-distribution, pdf, cdf, draw, support\n\nReferences:\n  http://en.wikipedia.org/wiki/Sampling_distribution\n  http://en.wikipedia.org/wiki/Exact_test\n  http://en.wikipedia.org/wiki/Randomization_test\n  http://en.wikipedia.org/wiki/Lady_tasting_tea\n\n",
   :var-type "function",
   :line 380,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:arglists ([] [min max]),
   :name "uniform-distribution",
   :namespace "incanter.distributions",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L799",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/uniform-distribution",
   :doc
   "\nReturns a Uniform distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  min        (default 0)\n  max        (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/DoubleUniform.html\n  http://en.wikipedia.org/wiki/Uniform_distribution\n\nExample:\n  (pdf (uniform-distribution 1.0 10.0) 5)\n",
   :var-type "function",
   :line 799,
   :file "modules/incanter-core/src/incanter/distributions.clj"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/Beta-rec",
   :namespace "incanter.distributions",
   :var-type "record",
   :name "Beta-rec"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/Binomial-rec",
   :namespace "incanter.distributions",
   :var-type "record",
   :name "Binomial-rec"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/ChiSquare-rec",
   :namespace "incanter.distributions",
   :var-type "record",
   :name "ChiSquare-rec"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/Combination",
   :namespace "incanter.distributions",
   :var-type "record",
   :name "Combination"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/DoubleUniform-rec",
   :namespace "incanter.distributions",
   :var-type "record",
   :name "DoubleUniform-rec"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/Exponential-rec",
   :namespace "incanter.distributions",
   :var-type "record",
   :name "Exponential-rec"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/F",
   :namespace "incanter.distributions",
   :var-type "record",
   :name "F"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/Gamma-rec",
   :namespace "incanter.distributions",
   :var-type "record",
   :name "Gamma-rec"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/NegativeBinomial-rec",
   :namespace "incanter.distributions",
   :var-type "record",
   :name "NegativeBinomial-rec"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/Normal-rec",
   :namespace "incanter.distributions",
   :var-type "record",
   :name "Normal-rec"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/Poisson-rec",
   :namespace "incanter.distributions",
   :var-type "record",
   :name "Poisson-rec"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/StudentT-rec",
   :namespace "incanter.distributions",
   :var-type "record",
   :name "StudentT-rec"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/UniformInt",
   :namespace "incanter.distributions",
   :var-type "record",
   :name "UniformInt"}
  {:file "modules/incanter-core/src/incanter/distributions.clj",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/distributions.clj#L31",
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/Distribution",
   :namespace "incanter.distributions",
   :line 31,
   :var-type "protocol",
   :doc
   "\nThe distribution protocol defines operations on probability distributions.\nDistributions may be univariate (defined over scalars) or multivariate\n(defined over vectors). Distributions may be discrete or continuous.\n\nFor a list of types that implement the protocol run (extenders Distribution).\nImplementations are provided for the various Clojure collection datatypes.\nSee the example below for using the distribution methods on these types.\n\nSee also:\n  pdf, cdf, draw, support\n\nReferences:\n  http://en.wikipedia.org/wiki/Probability_distribution\n\nExamples:\n  (support [1 3 4 2 1 3 4 2]) ; returns the set #{1 2 3 4}\n  (draw [1 3 4 2 1 3 4 2]) ; returns a value from #{1 2 3 4}\n  (pdf [2 1 2] 1) ; returns the value 1/3\n(cdf [2 1 2 3] 2) ; returns the value 3/4\n",
   :name "Distribution"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/cdf",
   :namespace "incanter.distributions",
   :var-type "function",
   :arglists ([d v]),
   :doc
   "\nA function of the incanter.distribution.Distribution protocol.\n\nReturns the value of the cumulative density function for the\ndistribution d at support v.\n\nSee also:\n  Distribution, pdf, draw, support\n\nReferences:\n  http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExamples:\n  (cdf [2 1 2 3] 2) ; returns the value 3/4 ",
   :name "cdf"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/draw",
   :namespace "incanter.distributions",
   :var-type "function",
   :arglists ([d]),
   :doc
   "\nA function of the incanter.distribution.Distribution protocol.\n\nReturns a randomly drawn value from the support of distribution d.\n\nSee also:\n  Distribution, pdf, cdf, support\n\nExamples:\n  (draw [1 3 4 2 1 3 4 2]) ; returns a value from #{1 2 3 4}",
   :name "draw"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/mean",
   :namespace "incanter.distributions",
   :var-type "function",
   :arglists ([d]),
   :doc "mean",
   :name "mean"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/pdf",
   :namespace "incanter.distributions",
   :var-type "function",
   :arglists ([d v]),
   :doc
   "\nA function of the incanter.distribution.Distribution protocol.\n\nReturns the value of the probability density/mass function for the\ndistribution d at support v.\n\nSee also:\n  Distribution, cdf, draw, support\n\nReferences:\n  http://en.wikipedia.org/wiki/Probability_density_function\n\nExamples:\n  (pdf [2 1 2] 1) ; returns the value 1/3",
   :name "pdf"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/support",
   :namespace "incanter.distributions",
   :var-type "function",
   :arglists ([d]),
   :doc
   "\n**** EXPERIMENTAL ****\nA function of the incanter.distribution.Distribution protocol.\n\nReturns the support of the probability distribution d.\nFor discrete distributions, the support is a set (i.e. #{1 2 3}).\nFor continuous distributions, the support is a 2 element vector\ndescribing the range. For example, the uniform distribution over\nthe unit interval would return the vector [0 1].\n\nThis function is marked as experimental to note that the output\nformat might need to adapt to more complex support structures.\nFor example, what would best describe a mixture of continuous\ndistributions?\n\nSee also:\n  Distribution, pdf, draw, support\n\nReferences:\n  http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExamples:\n  (cdf [2 1 2 3] 2) ; returns the value 3/4 ",
   :name "support"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://liebke.github.com/incanter//distributions-api.html#incanter.distributions/variance",
   :namespace "incanter.distributions",
   :var-type "function",
   :arglists ([d]),
   :doc "variance",
   :name "variance"}
  {:file "modules/incanter-excel/src/incanter/excel.clj",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/886e8f3afa579f699aa329f963b615c99478de91/modules/incanter-excel/src/incanter/excel.clj",
   :source-url
   "https://github.com/liebke/incanter/blob/886e8f3afa579f699aa329f963b615c99478de91/modules/incanter-excel/src/incanter/excel.clj#L95",
   :wiki-url
   "http://liebke.github.com/incanter//excel-api.html#incanter.excel/read-xls",
   :namespace "incanter.excel",
   :line 95,
   :var-type "multimethod",
   :doc
   "Read an Excel file into a dataset. Note: cells containing formulas will be\nempty upon import.  Can read both older and newer Excel file formats, uses the filename suffix\nor :override-format option.\n\nOptions are:\n:sheet either a String for the tab name or an int for the sheet index -- defaults to 0\n:header-keywords convert the incoming header line to keywords -- defaults to false (no conversion)\n:override-format If nil use the filename suffix to guess the Excel file format.  If :xls\nor :xlsx override the suffix check.\n:all-sheets? true to try to read in all sheets of data (false by default).\n\n Examples:\n   (use '(incanter core io excel))\n   (view (read-xls \"http://incanter.org/data/aus-airline-passengers.xls\"))\n\n   (use '(incanter core charts excel))\n   ;; read .xls file of Australian airline passenger data from the 1950s.\n   (with-data (read-xls \"http://incanter.org/data/aus-airline-passengers.xls\")\n   (view $data)\n   ;; time-series-plot needs time in millisecs\n   ;; create a function, to-millis, to convert a sequence of Date objects\n   ;; to a sequence of milliseconds\n   (let [to-millis (fn [dates] (map #(.getTime %) dates))] \n     (view (time-series-plot (to-millis ($ :date)) ($ :passengers)))))",
   :name "read-xls"}
  {:file "modules/incanter-excel/src/incanter/excel.clj",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/886e8f3afa579f699aa329f963b615c99478de91/modules/incanter-excel/src/incanter/excel.clj",
   :source-url
   "https://github.com/liebke/incanter/blob/886e8f3afa579f699aa329f963b615c99478de91/modules/incanter-excel/src/incanter/excel.clj#L24",
   :wiki-url
   "http://liebke.github.com/incanter//excel-api.html#incanter.excel/save-xls",
   :namespace "incanter.excel",
   :line 24,
   :var-type "multimethod",
   :doc
   "Save a dataset to an Excel file.  Can save in both older and newer\nExcel formats, uses the filename suffix or :override-format option.\n\nBy passing in a collection of datasets and names it is possible to write more than\none sheet at a time: e.g.\n  (save-xls [\"first sheet\" dataset1 \"second\" dataset2] my-file)\n\nOptions are:\n:sheet defaults to \"dataset\" if not provided.\n:use-bold defaults to true.  Set the header line in bold.\n:override-format If nil use the filename suffix to guess the Excel file format.\nIf :xls or :xlsx override the suffix check.\n\nExamples:\n  (use '(incanter core datasets excel))\n  (save-xls (get-dataset :cars) \"/tmp/cars.xls\")",
   :name "save-xls"}
  {:arglists ([op prec & [trans]]),
   :name "defop",
   :namespace "incanter.infix",
   :source-url
   "https://github.com/liebke/incanter/blob/20e3960ecaec11ccce900a871fba06d401fec802/modules/incanter-core/src/incanter/infix.clj#L41",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/20e3960ecaec11ccce900a871fba06d401fec802/modules/incanter-core/src/incanter/infix.clj",
   :wiki-url
   "http://liebke.github.com/incanter//infix-api.html#incanter.infix/defop",
   :doc "Define operators for formula macro",
   :var-type "function",
   :line 41,
   :file "modules/incanter-core/src/incanter/infix.clj"}
  {:arglists ([& equation]),
   :name "formula",
   :namespace "incanter.infix",
   :source-url
   "https://github.com/liebke/incanter/blob/20e3960ecaec11ccce900a871fba06d401fec802/modules/incanter-core/src/incanter/infix.clj#L115",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/20e3960ecaec11ccce900a871fba06d401fec802/modules/incanter-core/src/incanter/infix.clj",
   :wiki-url
   "http://liebke.github.com/incanter//infix-api.html#incanter.infix/formula",
   :doc "Convert from infix notation to prefix notation",
   :var-type "macro",
   :line 115,
   :file "modules/incanter-core/src/incanter/infix.clj"}
  {:arglists ([col]),
   :name "infix-to-prefix",
   :namespace "incanter.infix",
   :source-url
   "https://github.com/liebke/incanter/blob/20e3960ecaec11ccce900a871fba06d401fec802/modules/incanter-core/src/incanter/infix.clj#L96",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/20e3960ecaec11ccce900a871fba06d401fec802/modules/incanter-core/src/incanter/infix.clj",
   :wiki-url
   "http://liebke.github.com/incanter//infix-api.html#incanter.infix/infix-to-prefix",
   :doc "Convert from infix notation to prefix notation",
   :var-type "function",
   :line 96,
   :file "modules/incanter-core/src/incanter/infix.clj"}
  {:arglists ([points type & options]),
   :name "interpolate",
   :namespace "incanter.interpolation",
   :source-url
   "https://github.com/liebke/incanter/blob/cfa59efaf6df081e2bab2a2b0eb6abdf9863f455/modules/incanter-core/src/incanter/interpolation.clj#L22",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/cfa59efaf6df081e2bab2a2b0eb6abdf9863f455/modules/incanter-core/src/incanter/interpolation.clj",
   :wiki-url
   "http://liebke.github.com/incanter//interpolation-api.html#incanter.interpolation/interpolate",
   :doc
   "\nBuilds a function that interpolates given collection of points.\nhttp://en.wikipedia.org/wiki/Interpolation\nhttp://en.wikipedia.org/wiki/Linear_least_squares_(mathematics)\n\nArguments:\n  points -- collection of points. Each point is a collection [x y].\n  type -- type of interpolation - :linear, :polynomial, :cubic, :cubic-hermite, :linear-least-squares.\n          For most cases you should use :cubic or :cubic-hermite - they usually give best results.\n          Check http://en.wikipedia.org/wiki/Interpolation for brief explanation of each kind.\n\nOptions:\n  :boundaries - valid only for :cubic interpolation. Defines boundary condition for cubic spline.\n                Possible values - :natural and :closed.\n                Let's S - spline, a- leftmost point, b- rightmost point.\n                :natural - S''(a) = S''(b) = 0\n                :closed - S'(a) = S'(b), S''(a) = S''(b) . This type of boundary conditions may be\n                useful if you want to get periodic or closed curve.\n                Default value is :natural\n\n  :derivatives - valid only for :cubic-hermite. Defines first derivatives for spline.\n                 If not specified derivatives will be approximated from points.\n\nOptions for linear least squares:\n  :basis - type of basis functions. There are 2 built-in bases: chebushev polynomials and b-splines (:polynomial and :b-spline).\n           You also can supply your own basis. It should be a function that takes x and returns collection [f1(x) f2(x) ... fn(x)].\n           Example of custom basis of 2 functions (1 and  x*x): (interpolate :linear-least-squares :basis (fn [x] [1 (* x x)]))\n           Default value is :chebyshev\n\n  :n - number of functions in basis if you use built-in basis.\n       Note that if n is greater that number of points then you might get singular matrix and exception.\n       Default value is 4.\n\n  :degree - degree of b-spline if you use :b-spline basis.\n            Default value is 3.\n\nExamples:\n\n  (def points [[0 0] [1 5] [2 0] [3 5]])\n  (def linear (interpolate points :linear))\n  (linear 0) => 0.0\n  (linear 1) => 5.0\n  (linear 1.5) => 2.5\n\n  ; Specify boundary conditions\n  (interpolate points :cubic :boundaries :closed)\n",
   :var-type "function",
   :line 22,
   :file "modules/incanter-core/src/incanter/interpolation.clj"}
  {:arglists ([grid type & options]),
   :name "interpolate-grid",
   :namespace "incanter.interpolation",
   :source-url
   "https://github.com/liebke/incanter/blob/cfa59efaf6df081e2bab2a2b0eb6abdf9863f455/modules/incanter-core/src/incanter/interpolation.clj#L181",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/cfa59efaf6df081e2bab2a2b0eb6abdf9863f455/modules/incanter-core/src/incanter/interpolation.clj",
   :wiki-url
   "http://liebke.github.com/incanter//interpolation-api.html#incanter.interpolation/interpolate-grid",
   :doc
   "\nInterpolates 2-dimensional grid. Returns function f that takes 2 arguments: x and y.\nBy default function interpolates on [0,1]x[0,1].\n\nArguments:\n  grid -- collection of collection of numbers to be interpolated.\n          If you need to interpolate vectors - interpolate each component by separate interpolator.\n  type -- type of interpolation. Available: :bilinear, :polynomial, :bicubic, :bicubic-hermite, :b-surface, :linear-least-squares\n\nCommon options:\n  :x-range, :y-range - range of possible x and y.\n                       By default :x-range = [0 1] and :y-range = [0 1]\n                       :b-surface ignores this option and always uses [0, 1] x [0, 1]\n\n  :xs, :ys - coordinates of grid points. Size of xs and ys must be consistent with grid size.\n             If you have grid 4x3 then xs must have size 3 and ys - 4.\n\n  Note that (:x-range, :y-range) and (:xs, :ys) both do same job - they specify coordinates of points in grid.\n  So you should use only one of them or none at all.\n\nType specific options:\n  :boundaries - valid only for :cubic interpolation. Defines boundary condition for bicubic spline.\n                Possible values - :natural and :closed.\n                Default - :natural. Check documentation of 'interpolate' method for more explanation.\n\n  :degree - valid only for :b-spline. Degree of a B-spline. Default 3. Degree will be reduced if there are too few points.\n\n  :basis - defines basis for :linear-least-squares. It has 1 predefined basis :polynomial. :polynomial basis\n           contains functions: (1, x, y, x^2, xy, y^2, x^3, ...)\n           You can specify how many functions basis contains by using :n option.\n           You can also specify custom basis. Custom basis is a function that takes 2 arguments - x and y, and returns collection of values.\n           Example: basis that contains only 2-degree polynomials: (fn [x y] [(* x x) (* x y) (* y y)])\n\n  :n - defines how many functions polynomial contains. Example: 1 - basis is (1), 3 - basis is (1, x, y), 5 - basis is (1, x, y, x^2, x*y)\n\nExamples:\n\n(def grid [[0 1 0]\n           [1 2 1]\n           [0 1 0]])\n(def interpolator (interpolate-grid grid :bilinear))\n(interpolator   0   0) => 0\n(interpolator 1/2 1/2) => 2\n(interpolator 1/2   1) => 1\n(interpolator 1/4   0) => 1/2\n\n\n; Specify x-range and y-range\n(def interpolator (interpolate-grid grid :bilinear :x-range [0 10] :y-range [-5 5]))\n(interpolator  0  -5) => 0\n(interpolator  5   0) => 2\n(interpolator 10   5) => 0\n\n; Specify xs and ys\n(def interpolator (interpolate-grid grid :bilinear :xs [0 1 2] :ys [0 10 100]))\n(interpolator  0   0) => 0\n(interpolator  1  10) => 2\n(interpolator  2 100) => 0",
   :var-type "function",
   :line 181,
   :file "modules/incanter-core/src/incanter/interpolation.clj"}
  {:arglists ([points type & options]),
   :name "interpolate-parametric",
   :namespace "incanter.interpolation",
   :source-url
   "https://github.com/liebke/incanter/blob/cfa59efaf6df081e2bab2a2b0eb6abdf9863f455/modules/incanter-core/src/incanter/interpolation.clj#L83",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/cfa59efaf6df081e2bab2a2b0eb6abdf9863f455/modules/incanter-core/src/incanter/interpolation.clj",
   :wiki-url
   "http://liebke.github.com/incanter//interpolation-api.html#incanter.interpolation/interpolate-parametric",
   :doc
   "\nBuilds a parametric function that interpolates given collection of points.\nParametric function represents a curve that go through all points. By default domain is [0, 1].\n\nArguments:\n  points -- collection of points. Each point either a single value or collection of values.\n  type -- type of interpolation - :linear, :polynomial, :cubic, :cubic-hermite, :b-spline, :linear-least-squares.\n\nOptions:\n  :range -- defines range for parameter t.\n            Default value is [0, 1]. f(0) = points[0], f(1) = points[n].\n\n  :boundaries -- valid only for :cubic interpolation.\n                 Defines boundary condition for cubic spline. Possible values - :natural and :closed.\n                 Let's S - spline, a- leftmost point, b- rightmost point.\n                 :natural - S''(a) = S''(b) = 0\n                 :closed - S'(a) = S'(b), S''(a) = S''(b) . This type of boundary conditions may be useful\n                 if you want to get periodic or closed curve\n\n                 Default value is :natural\n\n  :derivatives - valid only for :cubic-hermite. Defines first derivatives for spline.\n                 If not specified derivatives will be approximated from points.\n\n  :degree - valid only for :b-spline. Degree of a B-spline. Default 3. Degree will be reduced if there are too few points.\n\nOptions for linear least squares:\n  See documentation for interpolate function.\n\nExamples:\n\n(def points [[0 0]\n             [0 1]\n             [1 1]\n             [3 5]\n             [2 9]])\n(def cubic (interpolate-parametric points :cubic))\n(cubic 0) => [0.0 0.0]\n(cubic 1) => [2.0 9.0]\n(cubic 0.5) => [1.0 1.0]\n\n; Specify custom :range\n(def cubic (interpolate-parametric points :cubic :range [-10 10))\n(cubic -10) => [0.0 0.0]\n(cubic 0) => [1.0 1.0]\n",
   :var-type "function",
   :line 83,
   :file "modules/incanter-core/src/incanter/interpolation.clj"}
  {:arglists
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
   :name "read-dataset",
   :namespace "incanter.io",
   :source-url
   "https://github.com/liebke/incanter/blob/41f5b9d4a2b10ba140338129eb0daeb54d601524/modules/incanter-io/src/incanter/io.clj#L41",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/41f5b9d4a2b10ba140338129eb0daeb54d601524/modules/incanter-io/src/incanter/io.clj",
   :wiki-url
   "http://liebke.github.com/incanter//io-api.html#incanter.io/read-dataset",
   :doc
   "\nReturns a dataset read from a file or a URL.\n\nOptions:\n  :delim (default \\,), other options (\\tab \\space \\|  etc)\n  :quote (default \\\") character used for quoting strings\n  :skip (default 0) the number of lines to skip at the top of the file.\n  :header (default false) indicates the file has a header line\n  :compress-delim (default true if delim = \\space, false otherwise) means\n                  compress multiple adjacent delimiters into a single delimiter.\n  :empty-field-value (default nil) indicates the interpretation of an empty field.\n  :comment-char (default nil) skip commented lines (\"#\", \"%\", \";\", etc)\n",
   :var-type "function",
   :line 41,
   :file "modules/incanter-io/src/incanter/io.clj"}
  {:arglists
   ([chart
     x
     y
     latex-str
     &
     {:keys [color], :or {color java.awt.Color/darkGray}}]),
   :name "add-latex",
   :namespace "incanter.latex",
   :source-url
   "https://github.com/liebke/incanter/blob/dc52656f6091a4f68f2e53caf80261c102a516e3/modules/incanter-latex/src/incanter/latex.clj#L79",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/dc52656f6091a4f68f2e53caf80261c102a516e3/modules/incanter-latex/src/incanter/latex.clj",
   :wiki-url
   "http://liebke.github.com/incanter//latex-api.html#incanter.latex/add-latex",
   :doc
   "\nAdds an LaTeX equation annotation to the chart at the given x,y coordinates.\n\nArguments:\n  chart -- the chart to add the polygon to.\n  x, y -- the coordinates to place the image\n  latex-str -- a string of latex code\n\n\nOptions:\n  :color (default java.awt.Color/darkGray) -- the text color\n\n\nExamples:\n  (use '(incanter core charts stats latex))\n\n    (doto (function-plot pdf-normal -3 3)\n      (add-latex 0 0.1 \"f(x)=\\\\frac{1}{\\\\sqrt{2\\\\pi \\\\sigma^2}} e^{\\\\frac{-(x - \\\\mu)^2}{2 \\\\sigma^2}}\")\n      view)\n",
   :var-type "function",
   :line 79,
   :file "modules/incanter-latex/src/incanter/latex.clj"}
  {:arglists
   ([chart
     latex-str
     &
     {:keys [color], :or {color java.awt.Color/darkGray}}]),
   :name "add-latex-subtitle",
   :namespace "incanter.latex",
   :source-url
   "https://github.com/liebke/incanter/blob/dc52656f6091a4f68f2e53caf80261c102a516e3/modules/incanter-latex/src/incanter/latex.clj#L59",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/dc52656f6091a4f68f2e53caf80261c102a516e3/modules/incanter-latex/src/incanter/latex.clj",
   :wiki-url
   "http://liebke.github.com/incanter//latex-api.html#incanter.latex/add-latex-subtitle",
   :doc
   "\nAdds the given LaTeX equation as a subtitle to the chart.\n\nOptions:\n  :color (default java.awt.Color/darkGray) -- the text color\n\n\nExamples:\n  (use '(incanter core charts stats latex))\n\n  (doto (function-plot pdf-normal -3 3)\n    (add-latex-subtitle \"f(x)=\\\\frac{1}{\\\\sqrt{2\\\\pi \\\\sigma^2}} e^{\\\\frac{-(x - \\\\mu)^2}{2 \\\\sigma^2}}\")\n    view)\n",
   :var-type "function",
   :line 59,
   :file "modules/incanter-latex/src/incanter/latex.clj"}
  {:arglists
   ([latex-txt
     &
     {:keys [color background border],
      :or
      {color java.awt.Color/black,
       background java.awt.Color/white,
       border [5 5 5 5]}}]),
   :name "latex",
   :namespace "incanter.latex",
   :source-url
   "https://github.com/liebke/incanter/blob/dc52656f6091a4f68f2e53caf80261c102a516e3/modules/incanter-latex/src/incanter/latex.clj#L12",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/dc52656f6091a4f68f2e53caf80261c102a516e3/modules/incanter-latex/src/incanter/latex.clj",
   :wiki-url
   "http://liebke.github.com/incanter//latex-api.html#incanter.latex/latex",
   :doc
   "\nReturns the given LaTeX equation rendered as an java.awt.Image.\n\nOptions:\n  :color (default java.awt.Color/black) -- the text color\n  :background (default java.awt.Clolor/white) -- the background color\n  :border (default [5 5 5 5]) -- image border\n\nExamples:\n  (use '(incanter core charts stats latex))\n\n  (def latex-img (latex \"\\\\frac{(a+b)^2} {(a-b)^2}\"))\n  (save latex-img \"/tmp/latex-example1.png\")\n  (view \"file:///tmp/latex-example1.png\")\n\n  (view (latex \"f(x)=\\\\frac {1} {\\\\sqrt {2\\\\pi \\\\sigma ^2}} e^{\\\\frac {-(x - \\\\mu)^2}{2 \\\\sigma ^2}}\"))\n\n  (view (latex \"\\\\begin{pmatrix}\n                 a & b & c \\\\\\\\\n                 d & e & f \\\\\\\\\n                 g & h & i\n                 \\\\end{pmatrix}\"))\n",
   :var-type "function",
   :line 12,
   :file "modules/incanter-latex/src/incanter/latex.clj"}
  {:arglists
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
   :name "to-latex",
   :namespace "incanter.latex",
   :source-url
   "https://github.com/liebke/incanter/blob/dc52656f6091a4f68f2e53caf80261c102a516e3/modules/incanter-latex/src/incanter/latex.clj#L106",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/dc52656f6091a4f68f2e53caf80261c102a516e3/modules/incanter-latex/src/incanter/latex.clj",
   :wiki-url
   "http://liebke.github.com/incanter//latex-api.html#incanter.latex/to-latex",
   :doc
   "\nConvert an Incanter Matrix into a string of LaTeX commands to render it.\n\nOptions:\n  :mxtype (default pmatrix) -- the type of matrix to output, see LaTeX documentation for other options.\nExample:\n  (use '(incanter core latex))\n  (view (latex (to-latex (matrix [[1 0][0 1]]))))\n",
   :var-type "function",
   :line 106,
   :file "modules/incanter-latex/src/incanter/latex.clj"}
  {:arglists ([& args]),
   :name "fetch-dataset",
   :namespace "incanter.mongodb",
   :source-url
   "https://github.com/liebke/incanter/blob/129dadf7b50f51175cb820c5f3b927e8b8095d91/modules/incanter-mongodb/src/incanter/mongodb.clj#L72",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/129dadf7b50f51175cb820c5f3b927e8b8095d91/modules/incanter-mongodb/src/incanter/mongodb.clj",
   :wiki-url
   "http://liebke.github.com/incanter//mongodb-api.html#incanter.mongodb/fetch-dataset",
   :doc
   "\nQueries a MongoDB database, accepting the same arguments as\nsomnium.congomongo/fetch, but returning an Incanter dataset instead\nof a sequence of maps.\n\nExamples:\n\n (use '(incanter core datasets mongodb))\n (use 'somnium.congomongo)\n\n ;; first load some sample data\n (def data (get-dataset :airline-passengers))\n (view data)\n\n ;; a MongoDB server must be running on the localhost on the default port\n ;; for the following steps.\n\n (mongo! :db \"mydb\")\n (mass-insert! :airline-data (:rows data))\n\n ;; and then retrieve it\n ;; notice that the retrieved data set has two additional columns,  :_id :_ns\n (view (fetch-dataset :airline-data))\n\n",
   :var-type "function",
   :line 72,
   :file "modules/incanter-mongodb/src/incanter/mongodb.clj"}
  {:arglists ([mongodb-coll dataset]),
   :name "insert-dataset",
   :namespace "incanter.mongodb",
   :source-url
   "https://github.com/liebke/incanter/blob/129dadf7b50f51175cb820c5f3b927e8b8095d91/modules/incanter-mongodb/src/incanter/mongodb.clj#L105",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/129dadf7b50f51175cb820c5f3b927e8b8095d91/modules/incanter-mongodb/src/incanter/mongodb.clj",
   :wiki-url
   "http://liebke.github.com/incanter//mongodb-api.html#incanter.mongodb/insert-dataset",
   :doc
   "\nInserts the rows of the Incanter dataset into the given MongoDB collection.\n\nExamples:\n\n  (use '(incanter core datasets mongodb))\n  (use 'somnium.congomongo)\n\n  (def data (get-dataset :airline-passengers))\n  (view data)\n\n  ;; a MongoDB server must be running on the localhost on the default port\n  ;; for the following steps.\n\n  (mongo! :db \"mydb\")\n  (mass-insert! :airline-data (:rows data))\n\n  ;; notice that the retrieved data set has two additional columns,  :_id :_ns\n  (view (fetch-dataset :airline-data))\n",
   :var-type "function",
   :line 105,
   :file "modules/incanter-mongodb/src/incanter/mongodb.clj"}
  {:arglists ([f & {:keys [dx], :or {dx 1.0E-4}}]),
   :name "derivative",
   :namespace "incanter.optimize",
   :source-url
   "https://github.com/liebke/incanter/blob/77f7d3f4de2400d2d5fa0b7a285d046dbce450ca/modules/incanter-core/src/incanter/optimize.clj#L66",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/77f7d3f4de2400d2d5fa0b7a285d046dbce450ca/modules/incanter-core/src/incanter/optimize.clj",
   :wiki-url
   "http://liebke.github.com/incanter//optimize-api.html#incanter.optimize/derivative",
   :doc
   "\nReturns a function that approximates the derivative of the given function.\n\nOptions:\n  :dx (default 0.0001)\n\nExamples:\n\n  (use '(incanter core optimize charts stats))\n  (defn cube [x] (* x x x))\n  (def cube-deriv (derivative cube))\n  (cube-deriv 2) ; value: 12.000600010022566\n  (cube-deriv 3) ; value: 27.00090001006572\n  (cube-deriv 4) ; value: 48.00120000993502\n\n  (def x (range -3 3 0.1))\n  (def plot (xy-plot x (map cube x)))\n  (view plot)\n  (add-lines plot x (map cube-deriv x))\n\n  ;; get the second derivative function\n  (def cube-deriv2 (derivative cube-deriv))\n  (add-lines plot x (map cube-deriv2 x))\n\n  ;; plot the normal pdf and its derivatives\n  (def plot (xy-plot x (pdf-normal x)))\n  (view plot)\n  (def pdf-deriv (derivative pdf-normal))\n  (add-lines plot x (pdf-deriv x))\n\n  ;; plot the second derivative function\n  (def pdf-deriv2 (derivative pdf-deriv))\n  (add-lines plot x (pdf-deriv2 x))\n",
   :var-type "function",
   :line 66,
   :file "modules/incanter-core/src/incanter/optimize.clj"}
  {:arglists ([f start & {:keys [tol dx], :or {tol 1.0E-4}}]),
   :name "gradient",
   :namespace "incanter.optimize",
   :source-url
   "https://github.com/liebke/incanter/blob/77f7d3f4de2400d2d5fa0b7a285d046dbce450ca/modules/incanter-core/src/incanter/optimize.clj#L232",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/77f7d3f4de2400d2d5fa0b7a285d046dbce450ca/modules/incanter-core/src/incanter/optimize.clj",
   :wiki-url
   "http://liebke.github.com/incanter//optimize-api.html#incanter.optimize/gradient",
   :doc
   "\nReturns a function that calculates a 5-point approximation to\nthe gradient of the given function. The vector of start values are\nused to determine the number of parameters required by the function, and\nto scale the step-size. The generated function accepts a vector of\nparameter values and a vector of x data points and returns a matrix,\nwhere each row is the gradient evaluated at the corresponding x value.\n\nExamples:\n\n  (use '(incanter core optimize datasets charts))\n  (defn f [theta x]\n    (+ (nth theta 0)\n          (div (* x (- (nth theta 1) (nth theta 0)))\n               (+ (nth theta 2) x))))\n\n  (def start [20 200 100])\n  (def data (get-dataset :thurstone))\n  (def x (sel data :cols 1))\n  (def y (sel data :cols 0))\n  ;; view the data\n  (view (scatter-plot x y))\n\n  (def grad (gradient f start))\n  (time (doall (grad start x)))\n",
   :var-type "function",
   :line 232,
   :file "modules/incanter-core/src/incanter/optimize.clj"}
  {:arglists ([f start & {:keys [tol dx], :or {tol 1.0E-4}}]),
   :name "hessian",
   :namespace "incanter.optimize",
   :source-url
   "https://github.com/liebke/incanter/blob/77f7d3f4de2400d2d5fa0b7a285d046dbce450ca/modules/incanter-core/src/incanter/optimize.clj#L278",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/77f7d3f4de2400d2d5fa0b7a285d046dbce450ca/modules/incanter-core/src/incanter/optimize.clj",
   :wiki-url
   "http://liebke.github.com/incanter//optimize-api.html#incanter.optimize/hessian",
   :doc
   "\nReturns a function that calculates an approximation to the Hessian matrix\nof the given function. The vector of start values are used to determine\nthe number of parameters required by the function, and to scale the\nstep-size. The generated function accepts a vector of\nparameter values and a vector of x data points and returns a matrix,\nwhere each row with p*(p+1)/2 columns, one for each unique entry in\nthe Hessian evaluated at the corresponding x value.\n\nExamples:\n\n  (use '(incanter core optimize datasets charts))\n  (defn f [theta x]\n    (+ (nth theta 0)\n          (div (* x (- (nth theta 1) (nth theta 0)))\n               (+ (nth theta 2) x))))\n\n  (def start [20 200 100])\n  (def data (get-dataset :thurstone))\n  (def x (sel data :cols 1))\n  (def y (sel data :cols 0))\n  ;; view the data\n  (view (scatter-plot x y))\n\n  (time (def hess (hessian f start)))\n  (time (doall (hess start x)))\n",
   :var-type "function",
   :line 278,
   :file "modules/incanter-core/src/incanter/optimize.clj"}
  {:arglists ([f a b]),
   :name "integrate",
   :namespace "incanter.optimize",
   :source-url
   "https://github.com/liebke/incanter/blob/77f7d3f4de2400d2d5fa0b7a285d046dbce450ca/modules/incanter-core/src/incanter/optimize.clj#L26",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/77f7d3f4de2400d2d5fa0b7a285d046dbce450ca/modules/incanter-core/src/incanter/optimize.clj",
   :wiki-url
   "http://liebke.github.com/incanter//optimize-api.html#incanter.optimize/integrate",
   :doc
   "\nIntegrate a function f from a to b\n\nExamples:\n  (defn f1 [x] 1)\n  (defn f2 [x] (Math/pow x 2))\n  (defn f3 [x] (* x (Math/exp (Math/pow x 2))))\n\n  (integrate f1 0 5)\n  (integrate f2 0 1)\n  (integrate f3 0 1)\n\n  ;; normal distribution\n  (def std 1)\n  (def mu 0)\n  (defn normal [x]\n    (/ 1\n      (* (* std (Math/sqrt (* 2 Math/PI)))\n        (Math/exp (/ (Math/pow (- (- x mu)) 2)\n        (* 2 (Math/pow std 2)))))))\n\n  (integrate normal 1.96 10)\n\n\nReference:\n  http://jng.imagine27.com/articles/2009-04-09-161839_integral_calculus_in_lambda_calculus_lisp.html\n  http://iam.elbenshira.com/archives/151_integral-calculus-in-haskell/\n",
   :var-type "function",
   :line 26,
   :file "modules/incanter-core/src/incanter/optimize.clj"}
  {:arglists
   ([f
     y
     x
     start
     &
     {:keys [max-iter tol method],
      :or {max-iter 200, tol 1.0E-5, method :gauss-newton}}]),
   :name "non-linear-model",
   :namespace "incanter.optimize",
   :source-url
   "https://github.com/liebke/incanter/blob/77f7d3f4de2400d2d5fa0b7a285d046dbce450ca/modules/incanter-core/src/incanter/optimize.clj#L550",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/77f7d3f4de2400d2d5fa0b7a285d046dbce450ca/modules/incanter-core/src/incanter/optimize.clj",
   :wiki-url
   "http://liebke.github.com/incanter//optimize-api.html#incanter.optimize/non-linear-model",
   :doc
   "\nDetermine the nonlinear least-squares estimates of the\nparameters of a nonlinear model.\nBased on R's nls (non-linear least squares) function.\n\nArguments:\n  f -- model function, takes two arguments the first a list of parameters\n       that are to be estimated, and an x value.\n  y -- sequence of dependent data\n  x -- sequence of independent data\n  start -- start values for the parameters to be estimated\n\nOptions:\n  :method (default :gauss-newton) other option :newton-raphson\n  :tol (default 1E-5)\n  :max-iter (default 200)\n\nReturns: a hash-map containing the following fields:\n  :method -- the method used\n  :coefs  -- the parameter estimates\n  :gradient  -- the estimated gradient\n  :hessian -- the estimated hessian, if available\n  :iterations -- the number of iterations performed\n  :fitted -- the fitted values of y (i.e. y-hat)\n  :rss -- the residual sum-of-squares\n  :x -- the independent data values\n  :y -- the dependent data values\n\n\nExamples:\n\n  ;; example 1\n  (use '(incanter core optimize datasets charts))\n  ;; define the Michaelis-Menton model function\n  ;; y = a + (b - a)*x/(c + x)\n  (defn f [theta x]\n    (let [[a b c] theta]\n      (plus a (div (mult x (minus b a)) (plus c x)))))\n\n  (def start [20 200 100])\n  (def data (get-dataset :thurstone))\n  (def x (sel data :cols 1))\n  (def y (sel data :cols 0))\n  ;; view the data\n  (def plot (scatter-plot x y))\n  (view plot)\n\n  (def nlm (non-linear-model f y x start))\n  (add-lines plot x (:fitted nlm))\n\n\n  ;; example 2\n  (use '(incanter core optimize datasets charts))\n  ;; Chwirut data set from NIST\n  ;; http://www.itl.nist.gov/div898/strd/nls/data/LINKS/DATA/Chwirut1.dat\n  (def data (get-dataset :chwirut))\n  (def x (sel data :cols 1))\n  (def y (sel data :cols 0))\n\n  ;; define model function: y = exp(-b1*x)/(b2+b3*x) + e\n  (defn f [theta x]\n    (let [[b1 b2 b3] theta]\n      (div (exp (mult (minus b1) x)) (plus b2 (mult b3 x)))))\n\n  (def plot (scatter-plot x y :legend true))\n  (view plot)\n\n  ;; the newton-raphson algorithm fails to converge to the correct solution\n  ;; using first set of start values from NIST, but the default gauss-newton\n  ;; algorithm converges to the correct solution.\n\n  (def start1 [0.1 0.01 0.02])\n  (add-lines plot x (f start1 x))\n  (def nlm1 (non-linear-model f y x start1))\n  (add-lines plot x (:fitted nlm1))\n\n  ;; both algorithms converges with second set of start values from NIST\n  (def start2 [0.15 0.008 0.010])\n  (add-lines plot x (f start2 x))\n  (def nlm2 (non-linear-model f y x start2))\n  (add-lines plot x (:fitted nlm2))\n",
   :var-type "function",
   :line 550,
   :file "modules/incanter-core/src/incanter/optimize.clj"}
  {:arglists
   ([chart
     filename
     &
     {:keys [width height], :or {width 500, height 400}}]),
   :name "save-pdf",
   :namespace "incanter.pdf",
   :source-url
   "https://github.com/liebke/incanter/blob/15a5e6a3a04731f87eff6129fd68bb2503840f78/modules/incanter-pdf/src/incanter/pdf.clj#L16",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/15a5e6a3a04731f87eff6129fd68bb2503840f78/modules/incanter-pdf/src/incanter/pdf.clj",
   :wiki-url
   "http://liebke.github.com/incanter//pdf-api.html#incanter.pdf/save-pdf",
   :doc
   "\nSave a chart object as a pdf document.\n\nArguments:\n  chart\n  filename\n\nOptions:\n  :width (default 500)\n  :height (defualt 400)\n\nExamples:\n\n  (use '(incanter core charts pdf))\n  (save-pdf (function-plot sin -4 4) \"./pdf-chart.pdf\")\n",
   :var-type "function",
   :line 16,
   :file "modules/incanter-pdf/src/incanter/pdf.clj"}
  {:arglists
   ([data
     &
     {:keys [alpha beta cycles], :or {alpha 0.5, beta 3, cycles 10}}]),
   :name "som-batch-train",
   :namespace "incanter.som",
   :source-url
   "https://github.com/liebke/incanter/blob/feaedb2d9d9bdf978a41f77f50a5cb1f31ff09f0/modules/incanter-core/src/incanter/som.clj#L135",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/feaedb2d9d9bdf978a41f77f50a5cb1f31ff09f0/modules/incanter-core/src/incanter/som.clj",
   :wiki-url
   "http://liebke.github.com/incanter//som-api.html#incanter.som/som-batch-train",
   :doc
   "\nPerforms BL-SOM (batch-learning self organizing map) learning on\nthe given data, returning a hashmap containing resulting BL-SOM\nvalues.\n\n\nArguments:\n  data -- data matrix\n\nOptions:\n  :cycles -- number of cycles of learning\n  :alpha -- initial value of alpha learning parameter\n  :beta -- initial value of beta learning parameter\n\n\nReturns: A hashmap containing the following fields:\n\n  :fit -- array of fitness values for each cycle of SOM learning\n  :weights -- hashmap of weight vectors, keyed by lattice indices\n  :sets -- hashmap mapping data elements to lattice nodes\n           (key lattice index) (value list of row indices from data)\n  :dims -- dimensions of SOM lattice\n  :data-means -- column means of input data matrix\n\n\nExamples:\n\n  (use '(incanter core som stats charts datasets))\n  (def data (to-matrix (sel (get-dataset :iris)\n                         :cols [\"Sepal.Length\" \"Sepal.Width\" \"Petal.Length\" \"Petal.Width\"])))\n\n  (def som (som-batch-train data :cycles 10 :alpha 0.5 :beta 3))\n\n  ;; plot the fitness for each cycle of training\n  (view (xy-plot (range (count (:fit som))) (:fit som)))\n  ;; view indices of data items in each cell\n  (:sets som)\n  ;; view the species in each cell\n  (doseq [rws (vals (:sets som))]\n    (println (sel (get-dataset :iris) :cols \"Species\" :rows rws) \\newline))\n\n  ;; plot the means of the data vectors in each cell/cluster\n  (def cell-means (map #(map mean (trans (sel data :rows ((:sets som) %)))) (keys (:sets som))))\n  (def x (range (ncol data)))\n  (doto (xy-plot x (first cell-means))\n        view\n        (add-lines x (nth cell-means 1))\n        (add-lines x (nth cell-means 2)))\n\n\nReferences:\n\n  http://en.wikipedia.org/wiki/Self-organizing_map\n",
   :var-type "function",
   :line 135,
   :file "modules/incanter-core/src/incanter/som.clj"}
  {:arglists ([cql-statement]),
   :name "read-dataset",
   :namespace "incanter.sql",
   :source-url
   "https://github.com/liebke/incanter/blob/7774fbe6ab83158595c14eb0e436f7c096f7b442/modules/incanter-sql/src/incanter/sql.clj#L32",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/7774fbe6ab83158595c14eb0e436f7c096f7b442/modules/incanter-sql/src/incanter/sql.clj",
   :wiki-url
   "http://liebke.github.com/incanter//sql-api.html#incanter.sql/read-dataset",
   :doc "Lazily read a dataset for the given ClojureQL query.",
   :var-type "function",
   :line 32,
   :file "modules/incanter-sql/src/incanter/sql.clj"}
  {:arglists ([x lag] [x lag mean variance]),
   :name "auto-correlation",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1586",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/auto-correlation",
   :doc
   "\nReturns the auto-correlation of x with given lag, mean, and variance.\nIf no mean or variance is provided, the they are calculated from x.\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n",
   :var-type "function",
   :line 1586,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([coll]),
   :name "benford-test",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2562",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/benford-test",
   :doc
   "\nPerforms Benford's Law test using chisq-test.\n\nArgument:\ncoll: -- a sequence of numbers\n\nReturns:\n  :X-sq -- the Pearson X-squared test statistics\n  :p-value -- the p-value for the test statistic\n  :df -- the degress of freedom\n\nReference:\nhttp://data-sorcery.org/2009/06/21/chi-square-goodness-of-fit/\nhttp://en.wikipedia.org/wiki/Benford%27s_Law\n",
   :var-type "function",
   :line 2562,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists
   ([data
     statistic
     &
     {:keys [size replacement smooth? smooth-sd],
      :or
      {replacement true,
       smooth? false,
       smooth-sd (/ (sqrt (count data)))}}]),
   :name "bootstrap",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1739",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/bootstrap",
   :doc
   "\nReturns a bootstrap sample of the given statistic on the given data.\n\nArguments:\n  data -- vector of data to resample from\n  statistic -- a function that returns a value given a vector of data\n\nOptions:\n  :size -- the number of bootstrap samples to return\n  :smooth -- (default false) smoothing option\n  :smooth-sd -- (default (/ (sqrt (count data)))) determines the standard\n                deviation of the noise to use for smoothing\n  :replacement -- (default true) determines if sampling of the data\n                  should be done with replacement\n\n\nReferences:\n  1. Clifford E. Lunneborg, Data Analysis by Resampling Concepts and Applications, 2000, pages 105-117\n  2. http://en.wikipedia.org/wiki/Bootstrapping_(statistics)\n\n\nExamples:\n\n  ;; example from Data Analysis by Resampling Concepts and Applications\n  ;; Clifford E. Lunneborg (pages 119-122)\n\n  (use '(incanter core stats charts))\n\n  ;; weights (in grams) of 50 randoincanter. sampled bags of preztels\n  (def weights [464 447 446 454 450 457 450 442\n                433 452 449 454 450 438 448 449\n                457 451 456 452 450 463 464 453\n                452 447 439 449 468 443 433 460\n                452 447 447 446 450 459 466 433\n                445 453 454 446 464 450 456 456\n                447 469])\n\n  ;; calculate the sample median, 450\n  (median weights)\n\n  ;; generate bootstrap sample\n  (def t* (bootstrap weights median :size 2000))\n\n  ;; view histogram of bootstrap histogram\n  (view (histogram t*))\n\n  ;; calculate the mean of the bootstrap median ~ 450.644\n  (mean t*)\n\n  ;; calculate the standard error ~ 1.083\n  (def se (sd t*))\n\n  ;; 90% standard normal CI ~ (448.219 451.781)\n  (plus (median weights) (mult (quantile-normal [0.05 0.95]) se))\n\n  ;; 90% symmetric percentile CI ~ (449.0 452.5)\n  (quantile t* :probs [0.05 0.95])\n\n\n  ;; 90% non-symmetric percentile CI ~ (447.5 451.0)\n  (minus (* 2 (median weights)) (quantile t* :probs [0.95 0.05]))\n\n  ;; calculate bias\n  (- (mean t*) (median weights)) ;; ~ 0.644\n\n  ;; example with smoothing\n  ;; Newcomb's speed of light data\n\n  (use '(incanter core stats charts))\n\n  ;; A numeric vector giving the Third Series of measurements of the\n  ;; passage time of light recorded by Newcomb in 1882. The given\n  ;; values divided by 1000 plus 24 give the time in millionths of a\n  ;; second for light to traverse a known distance. The 'true' value is\n  ;; now considered to be 33.02.\n\n  (def speed-of-light [28 -44  29  30  24  28  37  32  36  27  26  28  29\n                       26  27  22  23  20  25 25  36  23  31  32  24  27\n                       33  16  24  29  36  21  28  26  27  27  32  25 28\n                       24  40  21  31  32  28  26  30  27  26  24  32  29\n                       34  -2  25  19  36 29  30  22  28  33  39  25  16  23])\n\n  ;; view histogram of data to see outlier observations\n  (view (histogram speed-of-light :nbins 30))\n\n  (def samp (bootstrap speed-of-light median :size 10000))\n  (view (histogram samp :density true :nbins 30))\n  (mean samp)\n  (quantile samp :probs [0.025 0.975])\n\n  (def smooth-samp (bootstrap speed-of-light median :size 10000 :smooth true))\n  (view (histogram smooth-samp :density true :nbins 30))\n  (mean smooth-samp)\n  (quantile smooth-samp :probs [0.025 0.975])\n",
   :var-type "function",
   :line 1739,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([col ds]),
   :name "category-col-summarizer",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2610",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/category-col-summarizer",
   :doc
   "Returns a summarizer function which takes a category column and returns a list of the top 5 columns by volume, and a\ncount of remaining rows",
   :var-type "function",
   :line 2610,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists
   ([x
     &
     {:keys [alpha beta lower-tail?],
      :or {alpha 1, beta 1, lower-tail? false}}]),
   :name "cdf-beta",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L476",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/cdf-beta",
   :doc
   "\nReturns the Beta cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's pbeta function.\n\nOptions:\n  :alpha (default 1)\n  :beta (default 1)\n  :lower-tail (default true)\n\nSee also:\n    pdf-beta and sample-beta\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Beta.html\n    http://en.wikipedia.org/wiki/Beta_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-beta 0.5 :alpha 1 :beta 2)\n    (cdf-beta 0.5 :alpha 1 :beta 2 :lower-tail false)\n",
   :var-type "function",
   :line 476,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists
   ([x
     &
     {:keys [size prob lower-tail?],
      :or {size 1, prob 1/2, lower-tail? true}}]),
   :name "cdf-binomial",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1151",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/cdf-binomial",
   :doc
   "\nReturns the Binomial cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's pbinom\n\nOptions:\n  :size (default 1)\n  :prob (default 1/2)\n  :lower-tail (default true)\n\nSee also:\n    pdf-binomial and sample-binomial\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Binomial.html\n    http://en.wikipedia.org/wiki/Binomial_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-binomial 10 :prob 1/4 :size 20)\n",
   :var-type "function",
   :line 1151,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists
   ([x & {:keys [df lower-tail?], :or {df 1, lower-tail? true}}]),
   :name "cdf-chisq",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L744",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/cdf-chisq",
   :doc
   "\nReturns the Chi Square cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's pchisq function.\n\nOptions:\n  :df (default 1)\n  :lower-tail (default true)\n\nSee also:\n    pdf-chisq and sample-chisq\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/ChiSquare.html\n    http://en.wikipedia.org/wiki/Chi_square_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-chisq 5.0 :df 2)\n    (cdf-chisq 5.0 :df 2 :lower-tail false)\n",
   :var-type "function",
   :line 744,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x]),
   :name "cdf-empirical",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1437",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/cdf-empirical",
   :doc
   "\nReturns a step-function representing the empirical cdf of the given data.\nEquivalent to R's ecdf function.\n\nThe following description is from the ecdf help in R: The e.c.d.f.\n(empirical cumulative distribution function) Fn is a step function\nwith jumps i/n at observation values, where i is the number of tied\nobservations at that value.  Missing values are ignored.\n\nFor observations 'x'= (x1,x2, ... xn), Fn is the fraction of\nobservations less or equal to t, i.e.,\n\nFn(t) = #{x_i <= t} / n  =  1/n sum(i=1,n) Indicator(xi <= t).\n\n\nExamples:\n  (use '(incanter core stats charts))\n\n  (def exam1 [192 160 183 136 162 165 181 188 150 163 192 164 184\n              189 183 181 188 191 190 184 171 177 125 192 149 188\n              154 151 159 141 171 153 169 168 168 157 160 190 166 150])\n\n  ;; the ecdf function returns an empirical cdf function for the given data\n  (def ecdf (cdf-empirical exam1))\n\n  ;; plot the data's empirical cdf\n  (view (scatter-plot exam1 (map ecdf exam1)))\n",
   :var-type "function",
   :line 1437,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x & {:keys [rate], :or {rate 1}}]),
   :name "cdf-exp",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L954",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/cdf-exp",
   :doc
   "\nReturns the Exponential cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's pexp\n\nOptions:\n  :rate (default 1)\n\nSee also:\n    pdf-exp and sample-exp\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Exponential.html\n    http://en.wikipedia.org/wiki/Exponential_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-exp 2.0 :rate 1/2)\n",
   :var-type "function",
   :line 954,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists
   ([x
     &
     {:keys [df1 df2 lower-tail?],
      :or {df1 1, df2 1, lower-tail? true}}]),
   :name "cdf-f",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L141",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/cdf-f",
   :doc
   "\nReturns the F-distribution cdf of the given value, x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's pf function.\n\nOptions:\n  :df1 (default 1)\n  :df2 (default 1)\n  :lower-tail? (default true)\n\nSee also:\n    pdf-f and quantile-f\n\nReferences:\n    http://en.wikipedia.org/wiki/F_distribution\n    http://mathworld.wolfram.com/F-Distribution.html\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-f 1.0 :df1 5 :df2 2)\n",
   :var-type "function",
   :line 141,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists
   ([x
     &
     {:keys [shape rate lower-tail?],
      :or {shape 1, rate 1, lower-tail? true}}]),
   :name "cdf-gamma",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L654",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/cdf-gamma",
   :doc
   "\nReturns the Gamma cdf for the given value of x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's pgamma function.\n\nOptions:\n  :shape (default 1)\n  :rate (default 1)\n  :lower-tail (default true)\n\nSee also:\n    pdf-gamma and sample-gamma\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Gamma.html\n    http://en.wikipedia.org/wiki/Gamma_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-gamma 10 :shape 1 :rate 2)\n    (cdf-gamma 3 :shape 1 :lower-tail false)\n",
   :var-type "function",
   :line 654,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists
   ([x
     &
     {:keys [size prob lower-tail?],
      :or {size 10, prob 1/2, lower-tail? true}}]),
   :name "cdf-neg-binomial",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1376",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/cdf-neg-binomial",
   :doc
   "\nReturns the Negative Binomial cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's dnbinom\n\nOptions:\n  :size (default 10)\n  :prob (default 1/2)\n  :lower-tail? (default true)\n\nSee also:\n    pdf-neg-binomial and sample-neg-binomial\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/NegativeBinomial.html\n    http://en.wikipedia.org/wiki/Negative_binomial_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-neg-binomial 10 :prob 1/2 :size 20)\n",
   :var-type "function",
   :line 1376,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x & {:keys [mean sd], :or {mean 0, sd 1}}]),
   :name "cdf-normal",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L214",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/cdf-normal",
   :doc
   "\nReturns the Normal cdf of the given value, x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's pnorm function.\n\nOptions:\n  :mean (default 0)\n  :sd (default 1)\n\nSee also:\n    pdf-normal, quantile-normal, sample-normal\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Normal.html\n    http://en.wikipedia.org/wiki/Normal_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-normal 1.96 :mean -2 :sd (sqrt 0.5))\n",
   :var-type "function",
   :line 214,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists
   ([x
     &
     {:keys [lambda lower-tail?], :or {lambda 1, lower-tail? true}}]),
   :name "cdf-poisson",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1288",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/cdf-poisson",
   :doc
   "\nReturns the Poisson cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's ppois\n\nOptions:\n  :lambda (default 1)\n  :lower-tail (default true)\n\nSee also:\n    pdf-poisson and sample-poisson\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Poisson.html\n    http://en.wikipedia.org/wiki/Poisson_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-poisson 5 :lambda 10)\n",
   :var-type "function",
   :line 1288,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists
   ([x & {:keys [df lower-tail?], :or {df 1, lower-tail? true}}]),
   :name "cdf-t",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L830",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/cdf-t",
   :doc
   "\nReturns the Student's t cdf for the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's pt function.\n\nOptions:\n  :df (default 1)\n\nSee also:\n    pdf-t, quantile-t, and sample-t\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/StudentT.html\n    http://en.wikipedia.org/wiki/Student-t_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-t 1.2 :df 10)\n",
   :var-type "function",
   :line 830,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x & {:keys [min max], :or {min 0.0, max 1.0}}]),
   :name "cdf-uniform",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L386",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/cdf-uniform",
   :doc
   "\nReturns the Uniform cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's punif function.\n\nOptions:\n  :min (default 0)\n  :max (default 1)\n\nSee also:\n    pdf-uniform and sample-uniform\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/DoubleUniform.html\n    http://en.wikipedia.org/wiki/Uniform_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-uniform 5)\n    (cdf-uniform 5 :min 1 :max 10)\n",
   :var-type "function",
   :line 386,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x & options]),
   :name "cdf-weibull",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L566",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/cdf-weibull",
   :doc
   "\nReturns the Weibull cdf for the given value of x. It will return a sequence\nof values, if x is a sequence.\n\nOptions:\n  :shape (default 1)\n  :scale (default 1)\n\nSee also:\n    pdf-weibull and sample-weibull\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Distributions.html\n    http://en.wikipedia.org/wiki/Weibull_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-weibull 10 :shape 1 :scale 0.2)\n",
   :var-type "function",
   :line 566,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b]),
   :name "chebyshev-distance",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3186",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/chebyshev-distance",
   :doc
   "In the limiting case of Lp reaching infinity we obtain the Chebyshev distance.",
   :var-type "function",
   :line 3186,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists
   ([& {:keys [x y correct table probs freq], :or {correct true}}]),
   :name "chisq-test",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2399",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/chisq-test",
   :doc
   "\nPerforms chi-squared contingency table tests and goodness-of-fit tests.\n\nIf the optional argument :y is not provided then a goodness-of-fit test\nis performed. In this case, the hypothesis tested is whether the\npopulation probabilities equal those in :probs, or are all equal if\n:probs is not given.\n\nIf :y is provided, it must be a sequence of integers that is the\nsame length as x. A contingency table is computed from x and :y.\nThen, Pearson's chi-squared test of the null hypothesis that the joint\ndistribution of the cell counts in a 2-dimensional contingency\ntable is the product of the row and column marginals is performed.\nBy default the Yates' continuity correction for 2x2 contingency\ntables is performed, this can be disabled by setting the :correct\noption to false.\n\n\nOptions:\n  :x -- a sequence of numbers.\n  :y -- a sequence of numbers\n  :table -- a contingency table. If one dimensional, the test is a goodness-of-fit\n  :probs (when (nil? y) -- (repeat n-levels (/ n-levels)))\n  :freq (default nil) -- if given, these are rescaled to probabilities\n  :correct (default true) -- use Yates' correction for continuity for 2x2 contingency tables\n\n\nReturns:\n  :X-sq -- the Pearson X-squared test statistics\n  :p-value -- the p-value for the test statistic\n  :df -- the degress of freedom\n\n\nExamples:\n  (use '(incanter core stats))\n  (chisq-test :x [1 2 3 2 3 2 4 3 5]) ;; X-sq 2.6667\n  ;; create a one-dimensional table of this data\n  (def table (matrix [1 3 3 1 1]))\n  (chisq-test :table table) ;; X-sq 2.6667\n  (chisq-test :table (trans table)) ;; throws exception\n\n  (chisq-test :x [1 0 0 0  1 1 1 0 0 1 0 0 1 1 1 1]) ;; 0.25\n\n  (use '(incanter core stats datasets))\n  (def math-prog (to-matrix (get-dataset :math-prog)))\n  (def x (sel math-prog :cols 1))\n  (def y (sel math-prog :cols 2))\n  (chisq-test :x x :y y) ;; X-sq = 1.24145, df=1, p-value = 0.26519\n  (chisq-test :x x :y y :correct false) ;; X-sq = 2.01094, df=1, p-value = 0.15617\n\n  (def table (matrix [[31 12] [9 8]]))\n  (chisq-test :table table) ;; X-sq = 1.24145, df=1, p-value = 0.26519\n  (chisq-test :table table :correct false) ;; X-sq = 2.01094, df=1, p-value = 0.15617\n  ;; use the detabulate function to create data rows corresponding to the table\n  (def detab (detabulate :table table))\n  (chisq-test :x (sel detab :cols 0) :y (sel detab :cols 1))\n\n  ;; look at the hair-eye-color data\n  ;; turn the count data for males into a contingency table\n  (def male (matrix (sel (get-dataset :hair-eye-color) :cols 3 :rows (range 16)) 4))\n  (chisq-test :table male) ;; X-sq = 41.280, df = 9, p-value = 4.44E-6\n  ;; turn the count data for females into a contingency table\n  (def female (matrix (sel (get-dataset :hair-eye-color) :cols 3 :rows (range 16 32)) 4))\n  (chisq-test :table female) ;; X-sq = 106.664, df = 9, p-value = 7.014E-19,\n\n\n  ;; supply probabilities to goodness-of-fit test\n  (def table [89 37 30 28 2])\n  (def probs [0.40 0.20 0.20 0.19 0.01])\n  (chisq-test :table table :probs probs) ;; X-sq = 5.7947, df = 4, p-value = 0.215\n\n  ;; use frequencies instead of probabilities\n  (def freq [40 20 20 15 5])\n  (chisq-test :table table :freq freq) ;; X-sq = 9.9901, df = 4, p-value = 0.04059\n\n\n\nReferences:\n  http://www.itl.nist.gov/div898/handbook/eda/section3/eda35f.htm\n  http://en.wikipedia.org/wiki/Pearson's_chi-square_test\n  http://en.wikipedia.org/wiki/Yates'_chi-square_test\n",
   :var-type "function",
   :line 2399,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([col-type]),
   :name "choose-singletype-col-summarizer",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2618",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/choose-singletype-col-summarizer",
   :doc "Takes in a type, and returns a suitable column summarizer",
   :var-type "function",
   :line 2618,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x y] [mat]),
   :name "correlation",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1560",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/correlation",
   :doc
   "\nReturns the sample correlation of x and y, or the correlation\nmatrix of the given matrix.\n\nExamples:\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Correlation\n",
   :var-type "function",
   :line 1560,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b]),
   :name "correlation-linearity-test",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2928",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/correlation-linearity-test",
   :doc
   "\nhttp://en.wikipedia.org/wiki/Correlation_ratio\nIt is worth noting that if the relationship between values of  and values of\noverline y_x is linear (which is certainly true when there are only two\npossibilities for x) this will give the same result as the square of the\ncorrelation coefficient, otherwise the correlation ratio will be larger in magnitude.\nIt can therefore be used for judging non-linear relationships.\n",
   :var-type "function",
   :line 2928,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([& xs]),
   :name "correlation-ratio",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2878",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/correlation-ratio",
   :doc
   "\nhttp://en.wikipedia.org/wiki/Correlation_ratio\n\nIn statistics, the correlation ratio is a measure of the relationship between\nthe statistical dispersion within individual categories and the\ndispersion across the whole population or sample. i.e. the weighted variance\nof the category means divided by the variance of all samples.\n\nExample\n\nSuppose there is a distribution of test scores in three topics (categories):\n\n  * Algebra: 45, 70, 29, 15 and 21 (5 scores)\n  * Geometry: 40, 20, 30 and 42 (4 scores)\n  * Statistics: 65, 95, 80, 70, 85 and 73 (6 scores).\n\nThen the subject averages are 36, 33 and 78, with an overall average of 52.\n\nThe sums of squares of the differences from the subject averages are 1952\nfor Algebra, 308 for Geometry and 600 for Statistics, adding to 2860,\nwhile the overall sum of squares of the differences from the overall average\nis 9640. The difference between these of 6780 is also the weighted sum of the\nsquare of the differences between the subject averages and the overall average:\n\n  5(36 − 52)2 + 4(33 − 52)2 + 6(78 − 52)2 = 6780\n\nThis gives\n\n  eta^2 =6780/9640=0.7033\n\nsuggesting that most of the overall dispersion is a result of differences\nbetween topics, rather than within topics. Taking the square root\n\n  eta = sqrt 6780/9640=0.8386\n\nObserve that for η = 1 the overall sample dispersion is purely due to dispersion\namong the categories and not at all due to dispersion within the individual\ncategories. For a quick comprehension simply imagine all Algebra, Geometry,\nand Statistics scores being the same respectively, e.g. 5 times 36, 4 times 33, 6 times 78.\n",
   :var-type "function",
   :line 2878,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b]),
   :name "cosine-similarity",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3212",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/cosine-similarity",
   :doc
   "\nhttp://en.wikipedia.org/wiki/Cosine_similarity\nhttp://www.appliedsoftwaredesign.com/cosineSimilarityCalculator.php\n\nThe Cosine Similarity of two vectors a and b is the ratio: a dot b / ||a|| ||b||\n\nLet d1 = {2 4 3 1 6}\nLet d2 = {3 5 1 2 5}\n\nCosine Similarity (d1, d2) =  dot(d1, d2) / ||d1|| ||d2||\n\ndot(d1, d2) = (2)*(3) + (4)*(5) + (3)*(1) + (1)*(2) + (6)*(5) = 61\n\n||d1|| = sqrt((2)^2 + (4)^2 + (3)^2 + (1)^2 + (6)^2) = 8.12403840464\n\n||d2|| = sqrt((3)^2 + (5)^2 + (1)^2 + (2)^2 + (5)^2) = 8\n\nCosine Similarity (d1, d2) = 61 / (8.12403840464) * (8)\n                           = 61 / 64.9923072371\n                           = 0.938572618717\n",
   :var-type "function",
   :line 3212,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x y] [mat]),
   :name "covariance",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1511",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/covariance",
   :doc
   "\nReturns the sample covariance of x and y.\n\nExamples:\n  ;; create some data that covaries\n  (def x (sample-normal 100))\n  (def err (sample-normal 100))\n  (def y (plus (mult 5 x) err))\n  ;; estimate the covariance of x and y\n  (covariance x y)\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Covariance\n",
   :var-type "function",
   :line 1511,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([coll]),
   :name "cumulative-mean",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1863",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/cumulative-mean",
   :doc
   "\nReturns a sequence of cumulative means for the given collection. For instance\nThe first value equals the first value of the argument, the second value is\nthe mean of the first two arguments, the third is the mean of the first three\narguments, etc.\n\nExamples:\n  (cumulative-mean (sample-normal 100))\n",
   :var-type "function",
   :line 1863,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([& {:keys [table row-labels col-labels]}]),
   :name "detabulate",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2727",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/detabulate",
   :doc
   "\nTake a contingency table of counts and returns a matrix of observations.\n\nExamples:\n\n  (use '(incanter core stats datasets))\n\n  (def by-gender (group-on (get-dataset :hair-eye-color) 2))\n  (def table (matrix (sel (first by-gender) :cols 3) 4))\n\n  (detabulate :table table)\n  (tabulate (detabulate :table table))\n\n  ;; example 2\n  (def data (matrix [[1 0]\n                     [1 1]\n                     [1 1]\n                     [1 0]\n                     [0 0]\n                     [1 1]\n                     [1 1]\n                     [1 0]\n                     [1 1]]))\n  (tabulate data)\n\n  (tabulate (detabulate :table (:table (tabulate data))))\n",
   :var-type "function",
   :line 2727,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b]),
   :name "dice-coefficient",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3297",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/dice-coefficient",
   :doc
   "\nhttp://en.wikipedia.org/wiki/Dice%27s_coefficient\nDice's coefficient (also known as the Dice coefficient)\nis a similarity measure related to the Jaccard index.\n",
   :var-type "function",
   :line 3297,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b]),
   :name "dice-coefficient-str",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3323",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/dice-coefficient-str",
   :doc
   "\nhttp://en.wikipedia.org/wiki/Dice%27s_coefficient\n\nWhen taken as a string similarity measure, the coefficient\nmay be calculated for two strings, x and y using bigrams.\nHere nt is the number of character bigrams found in both strings,\nnx is the number of bigrams in string x and\nny is the number of bigrams in string y.\nFor example, to calculate the similarity between:\n\n  night\n  nacht\n\nWe would find the set of bigrams in each word:\n\n  {ni,ig,gh,ht}\n  {na,ac,ch,ht}\n\nEach set has four elements, and the intersection of these two sets has only one element: ht.\n\nPlugging this into the formula, we calculate, s = (2 · 1) / (4 + 4) = 0.25.\n",
   :var-type "function",
   :line 3323,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b]),
   :name "discordant-pairs",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3050",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/discordant-pairs",
   :doc "http://en.wikipedia.org/wiki/Discordant_pairs",
   :var-type "function",
   :line 3050,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b]),
   :name "euclidean-distance",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3172",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/euclidean-distance",
   :doc
   "\nhttp://en.wikipedia.org/wiki/Euclidean_distance\n\nthe Euclidean distance or Euclidean metric is the ordinary distance\nbetween two points that one would measure with a ruler, and is\ngiven by the Pythagorean formula. By using this formula as distance,\nEuclidean space (or even any inner product space) becomes a metric space.\nThe associated norm is called the Euclidean norm.\nOlder literature refers to the metric as Pythagorean metric.\n",
   :var-type "function",
   :line 3172,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x y]),
   :name "f-test",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2275",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/f-test",
   :doc
   "\nTest for different variances between 2 samples\n\nArgument:\n  x : 1st sample to test\n  y : 2nd sample to test\n\nOptions:\n\nReferences:\n  http://en.wikipedia.org/wiki/F-test\n  http://people.richland.edu/james/lecture/m170/ch13-f.html\n",
   :var-type "function",
   :line 2275,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([]),
   :name "gamma-coefficient",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3072",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/gamma-coefficient",
   :doc
   "\nhttp://www.statsdirect.com/help/nonparametric_methods/kend.htm\nThe gamma coefficient is given as a measure of association that\nis highly resistant to tied data (Goodman and Kruskal, 1963)\n",
   :var-type "function",
   :line 3072,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b]),
   :name "hamming-distance",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3353",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/hamming-distance",
   :doc
   "\nhttp://en.wikipedia.org/wiki/Hamming_distance\n\nIn information theory, the Hamming distance between two strings of equal\nlength is the number of positions at which the corresponding symbols are different.\nPut another way, it measures the minimum number of\nsubstitutions required to change one string into the other,\nor the number of errors that transformed one string into the other.\n",
   :var-type "function",
   :line 3353,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([pred coll]),
   :name "indicator",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L73",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/indicator",
   :doc
   "\nReturns a sequence of ones and zeros, where ones\nare returned when the given predicate is true for\ncorresponding element in the given collection, and\nzero otherwise.\n\nExamples:\n  (use 'incanter.stats)\n\n  (indicator #(neg? %) (sample-normal 10))\n\n  ;; return the sum of the positive values in a normal sample\n  (def x (sample-normal 100))\n  (sum (mult x (indicator #(pos? %) x)))\n",
   :var-type "function",
   :line 73,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b]),
   :name "jaccard-distance",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3283",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/jaccard-distance",
   :doc
   "\nhttp://en.wikipedia.org/wiki/Jaccard_index\nThe Jaccard distance, which measures dissimilarity between sample sets,\nis complementary to the Jaccard coefficient and is obtained by subtracting\nthe Jaccard coefficient from 1, or, equivalently, by dividing the difference\nof the sizes of the union and the intersection of two sets by the size of the union.\n",
   :var-type "function",
   :line 3283,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b]),
   :name "jaccard-index",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3267",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/jaccard-index",
   :doc
   "\nhttp://en.wikipedia.org/wiki/Jaccard_index\n\nThe Jaccard index, also known as the Jaccard similarity coefficient\n(originally coined coefficient de communauté by Paul Jaccard), is a\nstatistic used for comparing the similarity and diversity of sample sets.\n\nThe Jaccard coefficient measures similarity between sample sets,\nand is defined as the size of the intersection divided by the\nsize of the union of the sample sets.\n",
   :var-type "function",
   :line 3267,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b]),
   :name "kendalls-tau",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2998",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/kendalls-tau",
   :doc
   "\nhttp://en.wikipedia.org/wiki/Kendall_tau_rank_correlation_coefficient\nhttp://www.statsdirect.com/help/nonparametric_methods/kend.htm\nhttp://mail.scipy.org/pipermail/scipy-dev/2009-March/011589.html\nbest explanation and example is in \"cluster analysis for researchers\" page 165.\nhttp://www.amazon.com/Cluster-Analysis-Researchers-Charles-Romesburg/dp/1411606175\n",
   :var-type "function",
   :line 2998,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([]),
   :name "kendalls-w",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3080",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/kendalls-w",
   :doc
   "\nhttp://en.wikipedia.org/wiki/Kendall%27s_W\nhttp://faculty.chass.ncsu.edu/garson/PA765/friedman.htm\n\nSuppose that object i is given the rank ri,j by judge number j, where there\nare in total n objects and m judges. Then the total rank given to object i is\n\n  Ri = sum Rij\n\nand the mean value of these total ranks is\n\n  Rbar = 1/2 m (n + 1)\n\nThe sum of squared deviations, S, is defined as\n\n  S=sum1-n (Ri - Rbar)\n\nand then Kendall's W is defined as[1]\n\n  W= 12S / m^2(n^3-n)\n\nIf the test statistic W is 1, then all the survey respondents have been\nunanimous, and each respondent has assigned the same order to the list\nof concerns. If W is 0, then there is no overall trend of agreement among\nthe respondents, and their responses may be regarded as essentially random.\nIntermediate values of W indicate a greater or lesser degree of unanimity\namong the various responses.\n\nLegendre[2] discusses a variant of the W statistic which accommodates ties\nin the rankings and also describes methods of making significance tests based on W.\n\n[{:observation [1 2 3]} {} ... {}] -> W\n",
   :var-type "function",
   :line 3080,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x]),
   :name "kurtosis",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1621",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/kurtosis",
   :doc
   "\nReturns the kurtosis of the data, x. \"Kurtosis is a measure of the \"peakedness\"\nof the probability distribution of a real-valued random variable. Higher kurtosis\nmeans more of the variance is due to infrequent extreme deviations, as opposed to\nfrequent modestly-sized deviations.\" (Wikipedia)\n\nExamples:\n\n  (kurtosis (sample-normal 100000)) ;; approximately 0\n  (kurtosis (sample-gamma 100000)) ;; approximately 6\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Kurtosis\n",
   :var-type "function",
   :line 1621,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b q]),
   :name "lee-distance",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3374",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/lee-distance",
   :doc
   "\nhttp://en.wikipedia.org/wiki/Lee_distance\n\nIn coding theory, the Lee distance is a distance between\ntwo strings x1x2...xn and y1y2...yn of equal length n\nover the q-ary alphabet {0,1,…,q-1} of size q >= 2. It is metric.\n\nIf q = 2 or q = 3 the Lee distance coincides with the Hamming distance.\n\nThe metric space induced by the Lee distance is a discrete analog of the elliptic space.\n",
   :var-type "function",
   :line 3374,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b]),
   :name "levenshtein-distance",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3436",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/levenshtein-distance",
   :doc
   "\nhttp://en.wikipedia.org/wiki/Levenshtein_distance\n\ninternal representation is a table d with m+1 rows and n+1 columns\n\nwhere m is the length of a and m is the length of b.\n\nIn information theory and computer science, the Levenshtein distance\nis a metric for measuring the amount of difference between two sequences\n(i.e., the so called edit distance).\nThe Levenshtein distance between two strings is given by the minimum number\nof operations needed to transform one string into the other,\nwhere an operation is an insertion, deletion, or substitution of a single character.\n\nFor example, the Levenshtein distance between \"kitten\" and \"sitting\" is 3,\nsince the following three edits change one into the other,\nand there is no way to do it with fewer than three edits:\n\n 1. kitten → sitten (substitution of 's' for 'k')\n 2. sitten → sittin (substitution of 'i' for 'e')\n 3. sittin → sitting (insert 'g' at the end).\n\nThe Levenshtein distance has several simple upper and lower bounds that are useful\nin applications which compute many of them and compare them. These include:\n\n  * It is always at least the difference of the sizes of the two strings.\n  * It is at most the length of the longer string.\n  * It is zero if and only if the strings are identical.\n  * If the strings are the same size, the Hamming distance is an upper bound on the Levenshtein distance.\n",
   :var-type "function",
   :line 3436,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([y x & {:keys [intercept], :or {intercept true}}]),
   :name "linear-model",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2037",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/linear-model",
   :doc
   "\nReturns the results of performing a OLS linear regression of y on x.\n\nArguments:\n  y is a vector (or sequence) of values for the dependent variable\n  x is a vector or matrix of values for the independent variables\n\nOptions:\n  :intercept (default true) indicates weather an intercept term should be included\n\nReturns:\n  a map, of type ::linear-model, containing:\n    :design-matrix -- a matrix containing the independent variables, and an intercept columns\n    :coefs -- the regression coefficients\n    :t-tests -- t-test values of coefficients\n    :t-probs -- p-values for t-test values of coefficients\n    :coefs-ci -- 95% percentile confidence interval\n    :fitted -- the predicted values of y\n    :residuals -- the residuals of each observation\n    :std-errors -- the standard errors of the coeffients\n    :sse -- the sum of squared errors, also called the residual sum of squares\n    :ssr -- the regression sum of squares, also called the explained sum of squares\n    :sst -- the total sum of squares (proportional to the sample variance)\n    :r-square -- coefficient of determination\n\nExamples:\n  (use '(incanter core stats datasets charts))\n  (def iris (to-matrix (get-dataset :iris) :dummies true))\n  (def y (sel iris :cols 0))\n  (def x (sel iris :cols (range 1 6)))\n  (def iris-lm (linear-model y x)) ; with intercept term\n\n  (keys iris-lm) ; see what fields are included\n  (:coefs iris-lm)\n  (:sse iris-lm)\n  (quantile (:residuals iris-lm))\n  (:r-square iris-lm)\n  (:adj-r-square iris-lm)\n  (:f-stat iris-lm)\n  (:f-prob iris-lm)\n  (:df iris-lm)\n\n  (def x1 (range 0.0 3 0.1))\n  (view (xy-plot x1 (cdf-f x1 :df1 4 :df2 144)))\n\n\nReferences:\n  http://en.wikipedia.org/wiki/OLS_Regression\n  http://en.wikipedia.org/wiki/Coefficient_of_determination\n",
   :var-type "function",
   :line 2037,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x & {:keys [y W centroid]}]),
   :name "mahalanobis-distance",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3544",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/mahalanobis-distance",
   :doc
   "\nReturns the Mahalanobis distance between x, which is\n either a vector or matrix of row vectors, and the\n centroid of the observations in the matrix :y.\n\nArguments:\n  x -- either a vector or a matrix of row vectors\n\nOptions:\n  :y -- Defaults to x, must be a matrix of row vectors which will be used to calculate a centroid\n  :W -- Defaults to (solve (covariance y)), if an identity matrix is provided, the mahalanobis-distance\n        function will be equal to the Euclidean distance.\n  :centroid -- Defaults to (map mean (trans y))\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Mahalanobis_distance\n\n\nExamples:\n\n  (use '(incanter core stats charts))\n\n  ;; generate some multivariate normal data with a single outlier.\n  (def data (bind-rows\n              (bind-columns\n                (sample-mvn 100\n                            :sigma (matrix [[1 0.9]\n                                            [0.9 1]])))\n              [-1.75 1.75]))\n\n  ;; view a scatter plot of the data\n  (let [[x y] (trans data)]\n    (doto (scatter-plot x y)\n      (add-points [(mean x)] [(mean y)])\n      (add-pointer -1.75 1.75 :text \"Outlier\")\n      (add-pointer (mean x) (mean y) :text \"Centroid\")\n      view))\n\n  ;; calculate the distances of each point from the centroid.\n  (def dists (map first (mahalanobis-distance data)))\n  ;; view a bar-chart of the distances\n  (view (bar-chart (range 102) dists))\n\n  ;; Now contrast with the Euclidean distance.\n  (def dists (map first (mahalanobis-distance data :W (matrix [[1 0] [0 1]]))))\n  ;; view a bar-chart of the distances\n  (view (bar-chart (range 102) dists))\n\n\n  ;; another example\n  (mahalanobis-distance [-1.75 1.75] :y data)\n  (mahalanobis-distance [-1.75 1.75]\n                    :y data\n                    :W (matrix [[1 0]\n                                [0 1]]))\n",
   :var-type "function",
   :line 3544,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b]),
   :name "manhattan-distance",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3195",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/manhattan-distance",
   :doc
   "\nhttp://en.wikipedia.org/wiki/Manhattan_distance\n\nusual metric of Euclidean geometry is replaced by a new metric in which\nthe distance between two points is the sum of the (absolute) differences\nof their coordinates. The taxicab metric is also known as rectilinear distance,\nL1 distance or l1 norm (see Lp space), city block distance,\nManhattan distance, or Manhattan length\n",
   :var-type "function",
   :line 3195,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x]),
   :name "mean",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1477",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/mean",
   :doc
   "\nReturns the mean of the data, x.\n\nExamples:\n  (mean (sample-normal 100))\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Mean\n",
   :var-type "function",
   :line 1477,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x]),
   :name "median",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1604",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/median",
   :doc
   "\nReturns the median of the data, x.\n\nExamples:\n  (median (sample-normal 100))\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Median\n",
   :var-type "function",
   :line 1604,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b p]),
   :name "minkowski-distance",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3148",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/minkowski-distance",
   :doc
   "\nhttp://en.wikipedia.org/wiki/Minkowski_distance\nhttp://en.wikipedia.org/wiki/Lp_space\n\nThe Minkowski distance is a metric on Euclidean space which can be considered\nas a generalization of both the Euclidean distance and the Manhattan distance.\n\nMinkowski distance is typically used with p being 1 or 2. The latter is the\nEuclidean distance, while the former is sometimes known as the Manhattan distance.\n\nIn the limiting case of p reaching infinity we obtain the Chebyshev distance.\n",
   :var-type "function",
   :line 3148,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([n s]),
   :name "n-grams",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3310",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/n-grams",
   :doc
   "\nReturns a set of the unique n-grams in a string.\nthis is using actual sets here, discards duplicate n-grams?\n",
   :var-type "function",
   :line 3310,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b]),
   :name "normalized-kendall-tau-distance",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3060",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/normalized-kendall-tau-distance",
   :doc
   "\nhttp://en.wikipedia.org/wiki/Kendall_tau_distance\nKendall tau distance is the total number of discordant pairs.\n",
   :var-type "function",
   :line 3060,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([col ds]),
   :name "numeric-col-summarizer",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2603",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/numeric-col-summarizer",
   :doc
   "Returns a summarizer function which takes a purely numeric column with no non-numeric values",
   :var-type "function",
   :line 2603,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([p1 p2]),
   :name "odds-ratio",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2848",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/odds-ratio",
   :doc
   "\nhttp://en.wikipedia.org/wiki/Odds_ratio\n\nDefinition in terms of group-wise odds\n\nThe odds ratio is the ratio of the odds of an event occurring in one group\nto the odds of it occurring in another group, or to a sample-based estimate of that ratio.\n\n\nSuppose that in a sample of 100 men, 90 have drunk wine in the previous week,\nwhile in a sample of 100 women only 20 have drunk wine in the same period.\nThe odds of a man drinking wine are 90 to 10, or 9:1,\nwhile the odds of a woman drinking wine are only 20 to 80, or 1:4 = 0.25:1.\nThe odds ratio is thus 9/0.25, or 36, showing that men are much more likely\nto drink wine than women.\n\nRelation to statistical independence\n\nIf X and Y are independent, their joint probabilities can be expressed in\nterms of their marginal probabilities. In this case, the odds ratio equals one,\nand conversely the odds ratio can only equal one if the joint probabilities\ncan be factored in this way. Thus the odds ratio equals one if and only if\nX and Y are independent.\n",
   :var-type "function",
   :line 2848,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b]),
   :name "pairings",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3037",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/pairings",
   :doc "confusing ass name.",
   :var-type "function",
   :line 3037,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b]),
   :name "pairs",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3022",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/pairs",
   :doc
   "\nReturns unique pairs of a and b where members of a and b can not\nbe paired with the corresponding slot in the other list.\n",
   :var-type "function",
   :line 3022,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x & {:keys [alpha beta], :or {alpha 1, beta 1}}]),
   :name "pdf-beta",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L449",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/pdf-beta",
   :doc
   "\nReturns the Beta pdf of the given value of x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's dbeta function.\n\nOptions:\n  :alpha (default 1)\n  :beta (default 1)\n\nSee also:\n    cdf-beta and sample-beta\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Beta.html\n    http://en.wikipedia.org/wiki/Beta_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-beta 0.5 :alpha 1 :beta 2)\n",
   :var-type "function",
   :line 449,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x & {:keys [size prob], :or {size 1, prob 1/2}}]),
   :name "pdf-binomial",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1123",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/pdf-binomial",
   :doc
   "\nReturns the Binomial pdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's dbinom\n\nOptions:\n  :size (default 1)\n  :prob (default 1/2)\n\nSee also:\n    cdf-binomial and sample-binomial\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Binomial.html\n    http://en.wikipedia.org/wiki/Binomial_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-binomial 10 :prob 1/4 :size 20)\n",
   :var-type "function",
   :line 1123,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x & {:keys [df], :or {df 1}}]),
   :name "pdf-chisq",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L717",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/pdf-chisq",
   :doc
   "\nReturns the Chi Square pdf of the given value of x.  It will return a sequence\nof values, if x is a sequence. Same as R's dchisq function.\n\nOptions:\n  :df (default 1)\n\nSee also:\n    cdf-chisq and sample-chisq\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/ChiSquare.html\n    http://en.wikipedia.org/wiki/Chi_square_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-chisq 5.0 :df 2)\n",
   :var-type "function",
   :line 717,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x & {:keys [rate], :or {rate 1}}]),
   :name "pdf-exp",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L928",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/pdf-exp",
   :doc
   "\nReturns the Exponential pdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's dexp\n\nOptions:\n  :rate (default 1)\n\nSee also:\n    cdf-exp and sample-exp\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Exponential.html\n    http://en.wikipedia.org/wiki/Exponential_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-exp 2.0 :rate 1/2)\n",
   :var-type "function",
   :line 928,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x & {:keys [df1 df2], :or {df1 1, df2 1}}]),
   :name "pdf-f",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L106",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/pdf-f",
   :doc
   "\nReturns the F pdf of the given value, x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's df function.\n\nOptions:\n  :df1 (default 1)\n  :df2 (default 1)\n\nSee also:\n    cdf-f and quantile-f\n\nReferences:\n    http://en.wikipedia.org/wiki/F_distribution\n    http://mathworld.wolfram.com/F-Distribution.html\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-f 1.0 :df1 5 :df2 2)\n",
   :var-type "function",
   :line 106,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x & {:keys [shape rate], :or {shape 1, rate 1}}]),
   :name "pdf-gamma",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L626",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/pdf-gamma",
   :doc
   "\nReturns the Gamma pdf for the given value of x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's dgamma function.\n\nOptions:\n  :shape (default 1)\n  :rate (default 1)\n\nSee also:\n    cdf-gamma and sample-gamma\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Gamma.html\n    http://en.wikipedia.org/wiki/Gamma_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-gamma 10 :shape 1 :rate 2)\n",
   :var-type "function",
   :line 626,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x & {:keys [size prob], :or {size 10, prob 1/2}}]),
   :name "pdf-neg-binomial",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1347",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/pdf-neg-binomial",
   :doc
   "\nReturns the Negative Binomial pdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's dnbinom\n\nOptions:\n  :size (default 10)\n  :prob (default 1/2)\n\nSee also:\n    cdf-neg-binomial and sample-neg-binomial\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/NegativeBinomial.html\n    http://en.wikipedia.org/wiki/Negative_binomial_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-neg-binomial 10 :prob 1/2 :size 20)\n",
   :var-type "function",
   :line 1347,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x & {:keys [mean sd], :or {mean 0, sd 1}}]),
   :name "pdf-normal",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L186",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/pdf-normal",
   :doc
   "\nReturns the Normal pdf of the given value, x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's dnorm function.\n\nOptions:\n  :mean (default 0)\n  :sd (default 1)\n\nSee also:\n    cdf-normal, quantile-normal, sample-normal\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Normal.html\n    http://en.wikipedia.org/wiki/Normal_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-normal 1.96 :mean -2 :sd (sqrt 0.5))\n",
   :var-type "function",
   :line 186,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x & {:keys [lambda], :or {lambda 1}}]),
   :name "pdf-poisson",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1261",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/pdf-poisson",
   :doc
   "\nReturns the Poisson pdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's dpois\n\nOptions:\n  :lambda (default 1)\n\nSee also:\n    cdf-poisson and sample-poisson\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Poisson.html\n    http://en.wikipedia.org/wiki/Poisson_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-poisson 5 :lambda 10)\n",
   :var-type "function",
   :line 1261,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x & {:keys [df], :or {df 1}}]),
   :name "pdf-t",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L804",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/pdf-t",
   :doc
   "\nReturns the Student's t pdf for the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's dt function.\n\nOptions:\n  :df (default 1)\n\nSee also:\n    cdf-t, quantile-t, and sample-t\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/StudentT.html\n    http://en.wikipedia.org/wiki/Student-t_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-t 1.2 :df 10)\n",
   :var-type "function",
   :line 804,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x & {:keys [min max], :or {min 0.0, max 1.0}}]),
   :name "pdf-uniform",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L358",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/pdf-uniform",
   :doc
   "\nReturns the Uniform pdf of the given value of x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's dunif function.\n\nOptions:\n  :min (default 0)\n  :max (default 1)\n\nSee also:\n    cdf-uniform and sample-uniform\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/DoubleUniform.html\n    http://en.wikipedia.org/wiki/Uniform_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-uniform 5)\n    (pdf-uniform 5 :min 1 :max 10)\n",
   :var-type "function",
   :line 358,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x & options]),
   :name "pdf-weibull",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L537",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/pdf-weibull",
   :doc
   "\nReturns the Weibull pdf for the given value of x. It will return a sequence\nof values, if x is a sequence.\n\nOptions:\n    :scale (default 1)\n    :shape (default 1)\n\nSee also:\n    cdf-weibull and sample-weibull\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Distributions.html\n    http://en.wikipedia.org/wiki/Weibull_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-weibull 2 :alpha 1 :beta 0.5)\n",
   :var-type "function",
   :line 537,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x] [x y]),
   :name "permute",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1904",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/permute",
   :doc
   "\nIf provided a single argument, returns a permuted version of the\ngiven collection. (permute x) is the same as (sample x).\n\nIf provided two arguments, returns two lists that are permutations\nacross the given collections. In other words, each of the new collections\nwill contain elements from both of the given collections. Useful for\npermutation tests or randomization tests.\n\nExamples:\n\n  (permute (range 10))\n  (permute (range 10) (range 10 20))\n",
   :var-type "function",
   :line 1904,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([model x]),
   :name "predict",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2833",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/predict",
   :doc
   "Takes a linear-model and an x value (either a scalar or vector)\nand returns the predicted value based on the linear-model.",
   :var-type "function",
   :line 2833,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x & options]),
   :name "principal-components",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2670",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/principal-components",
   :doc
   "\nPerforms a principal components analysis on the given data matrix.\nEquivalent to R's prcomp function.\n\nReturns:\n  A map with the following fields:\n  :std-dev -- the standard deviations of the principal components\n      (i.e. the square roots of the eigenvalues of the correlation\n      matrix, though the calculation is actually done with the\n      singular values of the data matrix.\n  :rotation -- the matrix of variable loadings (i.e. a matrix\n      whose columns contain the eigenvectors).\n\n\nExamples:\n\n  (use '(incanter core stats charts datasets))\n  ;; load the iris dataset\n  (def iris (to-matrix (get-dataset :iris)))\n  ;; run the pca\n  (def pca (principal-components (sel iris :cols (range 4))))\n  ;; extract the first two principal components\n  (def pc1 (sel (:rotation pca) :cols 0))\n  (def pc2 (sel (:rotation pca) :cols 1))\n\n  ;; project the first four dimension of the iris data onto the first\n  ;; two principal components\n  (def x1 (mmult (sel iris :cols (range 4)) pc1))\n  (def x2 (mmult (sel iris :cols (range 4)) pc2))\n\n  ;; now plot the transformed data, coloring each species a different color\n  (doto (scatter-plot (sel x1 :rows (range 50)) (sel x2 :rows (range 50))\n                      :x-label \"PC1\" :y-label \"PC2\" :title \"Iris PCA\")\n        (add-points (sel x1 :rows (range 50 100)) (sel x2 :rows (range 50 100)))\n        (add-points (sel x1 :rows (range 100 150)) (sel x2 :rows (range 100 150)))\n        view)\n\n\n  ;; alternatively, the :group-by option can be used in scatter-plot\n  (view (scatter-plot x1 x2\n                      :group-by (sel iris :cols 4)\n                      :x-label \"PC1\" :y-label \"PC2\" :title \"Iris PCA\"))\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Principal_component_analysis\n",
   :var-type "function",
   :line 2670,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([j]),
   :name "product-marginal-test",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3131",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/product-marginal-test",
   :doc
   "the joint PMF of independent variables is equal to the product of their marginal PMFs.",
   :var-type "function",
   :line 3131,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists
   ([x
     &
     {:keys [probs],
      :or
      {probs
       (DoubleArrayList. (double-array [0.0 0.25 0.5 0.75 1.0]))}}]),
   :name "quantile",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1659",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/quantile",
   :doc
   "\nReturns the quantiles of the data, x. By default it returns the min,\n25th-percentile, 50th-percentile, 75th-percentile, and max value.\n\nOptions:\n  :probs (default [0.0 0.25 0.5 0.75 1.0])\n\nExamples:\n  (quantile (sample-normal 100))\n  (quantile (sample-normal 100) :probs [0.025 0.975])\n  (quantile (sample-normal 100) :probs 0.975)\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Quantile\n",
   :var-type "function",
   :line 1659,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([probability & {:keys [mean sd], :or {mean 0, sd 1}}]),
   :name "quantile-normal",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L241",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/quantile-normal",
   :doc
   "\nReturns the inverse of the Normal CDF for the given probability.\nIt will return a sequence of values, if given a sequence of\nprobabilities. This is equivalent to R's qnorm function.\n\nOptions:\n  :mean (default 0)\n  :sd (default 1)\n\nReturns:\n  a value x, where (cdf-normal x) = probability\n\nSee also:\n    pdf-normal, cdf-normal, and sample-normal\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Probability.html\n    http://en.wikipedia.org/wiki/Normal_distribution\n    http://en.wikipedia.org/wiki/Quantile\n\nExample:\n    (quantile-normal 0.975)\n    (quantile-normal [0.025 0.975] :mean -2 :sd (sqrt 0.5))\n",
   :var-type "function",
   :line 241,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([probability & {:keys [df], :or {df 1}}]),
   :name "quantile-t",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L858",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/quantile-t",
   :doc
   "\nReturns the inverse of the Student's t CDF for the given probability\n(i.e. the quantile).  It will return a sequence of values, if x is\na sequence of probabilities. This is equivalent to R's qt function.\n\nOptions:\n  :df (default 1)\n\nReturns:\n  a value x, where (cdf-t x) = probability\n\nSee also:\n   pdf-t, cdf-t, and sample-t\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Probability.html\n    http://en.wikipedia.org/wiki/Student-t_distribution\n    http://en.wikipedia.org/wiki/Quantile\n\nExample:\n    (quantile-t 0.975)\n    (quantile-t [0.025 0.975] :df 25)\n    (def df [1 2 3 4 5 6 7 8 9 10 20 50 100 1000])\n    (map #(quantile-t 0.025 :df %) df)\n",
   :var-type "function",
   :line 858,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x]),
   :name "rank-index",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2941",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/rank-index",
   :doc
   "\nGiven a seq, returns a map where the keys are the values of the seq\nand the values are the positional rank of each member o the seq.\n",
   :var-type "function",
   :line 2941,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:file "modules/incanter-core/src/incanter/stats.clj",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1688",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sample",
   :namespace "incanter.stats",
   :line 1688,
   :var-type "multimethod",
   :doc
   "\nReturns a sample of the given size from the given collection. If replacement\nis set to false it returns a set, otherwise it returns a list.\n\nArguments:\n  coll -- collection or dataset to be sampled from\n\nOptions:\n  :size -- (default (count x) sample size\n  :replacement (default true) -- sample with replacement\n\n\nExamples:\n  (sample (range 10)) ; permutation of numbers zero through ten\n  (sample [:red :green :blue] :size 10) ; choose 10 items that are either :red, :green, or :blue.\n  (sample (seq \"abcdefghijklmnopqrstuvwxyz\")  :size 4 :replacement false) ; choose 4 random letters.\n",
   :name "sample"}
  {:arglists ([size & {:keys [alpha beta], :or {alpha 1, beta 1}}]),
   :name "sample-beta",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L508",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sample-beta",
   :doc
   "\nReturns a sample of the given size from a Beta distribution.\nThis is equivalent to R's rbeta function.\n\nOptions:\n  :alpha (default 1)\n  :beta (default 1)\n  These default values produce a Uniform distribution.\n\nSee also:\n    pdf-beta and cdf-beta\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Beta.html\n    http://en.wikipedia.org/wiki/Beta_distribution\n\nExample:\n    (sample-beta 1000 :alpha 1 :beta 2)\n",
   :var-type "function",
   :line 508,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists
   ([samplesize & {:keys [size prob], :or {size 1, prob 1/2}}]),
   :name "sample-binomial",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1182",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sample-binomial",
   :doc
   "\nReturns a sample of the given size from a Binomial distribution.\nSame as R's rbinom\n\nOptions:\n  :size (default 1)\n  :prob (default 1/2)\n\nSee also:\n    pdf-binomial and cdf-binomial\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Binomial.html\n    http://en.wikipedia.org/wiki/Binomial_distribution\n\nExample:\n    (sample-binomial 1000 :prob 1/4 :size 20)\n",
   :var-type "function",
   :line 1182,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([size & {:keys [df], :or {df 1}}]),
   :name "sample-chisq",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L775",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sample-chisq",
   :doc
   "\nReturns a sample of the given size from a Chi Square distribution\nSame as R's rchisq function.\n\nOptions:\n  :df (default 1)\n\nSee also:\n    pdf-chisq and cdf-chisq\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/ChiSquare.html\n    http://en.wikipedia.org/wiki/Chi_square_distribution\n\nExample:\n    (sample-chisq 1000 :df 2)\n",
   :var-type "function",
   :line 775,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([size alpha]),
   :name "sample-dirichlet",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1073",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sample-dirichlet",
   :doc
   "\nExamples:\n  (use '(incanter core stats charts))\n\n  ;; a total of 1447 adults were polled to indicate their preferences for\n  ;; candidate 1 (y1=727), candidate 2 (y2=583), or some other candidate or no\n  ;; preference (y3=137).\n\n  ;; the counts y1, y2, and y3 are assumed to have a multinomial distribution\n  ;; If a uniform prior distribution is assigned to the multinomial vector\n  ;; theta = (th1, th2, th3), then the posterior distribution of theta is\n  ;; proportional to g(theta) = th1^y1 * th2^y2 * th3^y3, which is a\n  ;; dirichlet distribution with parameters (y1+1, y2+1, y3+1)\n  (def  theta (sample-dirichlet 1000 [(inc 727) (inc 583) (inc 137)]))\n  ;; view means, 95% CI, and histograms of the proportion parameters\n  (mean (sel theta :cols 0))\n  (quantile (sel theta :cols 0) :probs [0.0275 0.975])\n  (view (histogram (sel theta :cols 0)))\n  (mean (sel theta :cols 1))\n  (quantile (sel theta :cols 1) :probs [0.0275 0.975])\n  (view (histogram (sel theta :cols 1)))\n  (mean (sel theta :cols 2))\n  (quantile (sel theta :cols 2) :probs [0.0275 0.975])\n  (view (histogram (sel theta :cols 2)))\n\n  ;; view  a histogram of the difference in proportions between the first\n  ;; two candidates\n  (view (histogram (minus (sel theta :cols 0) (sel theta :cols 1))))\n",
   :var-type "function",
   :line 1073,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([size & {:keys [rate], :or {rate 1}}]),
   :name "sample-exp",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L980",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sample-exp",
   :doc
   "\nReturns a sample of the given size from a Exponential distribution.\nSame as R's rexp\n\nOptions:\n  :rate (default 1)\n\nSee also:\n    pdf-exp and cdf-exp\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Exponential.html\n    http://en.wikipedia.org/wiki/Exponential_distribution\n\nExample:\n    (sample-exp 1000 :rate 1/2)\n",
   :var-type "function",
   :line 980,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([size & {:keys [shape rate], :or {shape 1, rate 1}}]),
   :name "sample-gamma",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L686",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sample-gamma",
   :doc
   "\nReturns a sample of the given size from a Gamma distribution.\nThis is equivalent to R's rgamma function.\n\nOptions:\n  :shape (default 1)\n  :rate (default 1)\n\nSee also:\n    pdf-gamma, cdf-gamma, and quantile-gamma\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Gamma.html\n    http://en.wikipedia.org/wiki/Gamma_distribution\n\nExample:\n    (sample-gamma 1000 :shape 1 :rate 2)\n",
   :var-type "function",
   :line 686,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([& {:keys [scale p df], :or {p 2}}]),
   :name "sample-inv-wishart",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1046",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sample-inv-wishart",
   :doc
   "\nReturns a p-by-p symmetric distribution drawn from an inverse-Wishart distribution\n\nOptions:\n  :p (default 2) -- number of dimensions of resulting matrix\n  :df (default p) -- degree of freedoms (aka n), df <= p\n  :scale (default (identity-matrix p)) -- positive definite matrix (aka V)\n\nExamples:\n  (use 'incanter.stats)\n  (sample-inv-wishart :df 10  :p 4)\n\n  ;; calculate the mean of 1000 wishart matrices, should equal (mult df scale)\n  (div (reduce plus (for [_ (range 1000)] (sample-wishart :p 4))) 1000)\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Inverse-Wishart_distribution\n",
   :var-type "function",
   :line 1046,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists
   ([size & {:keys [probs categories], :or {probs [0.5 0.5]}}]),
   :name "sample-multinomial",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1213",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sample-multinomial",
   :doc
   "\nReturns a sequence representing a sample from a multinomial distribution.\n\nArguments: size -- number of values to return\n\nOptions:\n  :categories (default [0 1]) -- the values returned\n  :probs (default [0.5 0.5]) -- the probabilities associated with each category\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Multinomial_distribution#Sampling_from_a_multinomial_distribution\n\n\nExamples:\n  (use '(incanter core stats charts))\n\n  (sample-multinomial 10)\n  (sample-multinomial 10 :probs [0.25 0.5 0.25])\n\n  ;; estimate sample proportions\n  (def sample-size 1000.0)\n  (def categories [:red :yellow :blue :green])\n  (def data (to-dataset (sample-multinomial sample-size\n                                            :categories categories\n                                            :probs [0.5 0.25 0.2 0.05])))\n\n  ;; check the sample proportions\n  (view (pie-chart categories\n                   (map #(div (count ($ :col-0 ($where {:col-0 %} data)))\n                              sample-size)\n                        categories)))\n",
   :var-type "function",
   :line 1213,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([size & {:keys [mean sigma]}]),
   :name "sample-mvn",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L304",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sample-mvn",
   :doc
   "\nReturns a sample of the given size from a Multivariate Normal\ndistribution. This is equivalent to R's mvtnorm::rmvnorm function.\n\nArguments:\n  size -- the size of the sample to return\n\nOptions:\n  :mean (default (repeat (ncol sigma) 0))\n  :sigma (default (identity-matrix (count mean)))\n\n\nExamples:\n\n  (use '(incanter core stats charts))\n  (def mvn-samp (sample-mvn 1000 :mean [7 5] :sigma (matrix [[2 1.5] [1.5 3]])))\n  (covariance mvn-samp)\n  (def means (map mean (trans mvn-samp)))\n\n  ;; plot scatter-plot of points\n  (def mvn-plot (scatter-plot (sel mvn-samp :cols 0) (sel mvn-samp :cols 1)))\n  (view mvn-plot)\n  ;; add centroid to plot\n  (add-points mvn-plot [(first means)] [(second means)])\n\n  ;; add regression line to scatter plot\n  (def x (sel mvn-samp :cols 0))\n  (def y (sel mvn-samp :cols 1))\n  (def lm (linear-model y x))\n  (add-lines mvn-plot x (:fitted lm))\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Multivariate_normal\n",
   :var-type "function",
   :line 304,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists
   ([samplesize & {:keys [size prob], :or {size 10, prob 1/2}}]),
   :name "sample-neg-binomial",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1407",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sample-neg-binomial",
   :doc
   "\nReturns a sample of the given size from a Negative Binomial distribution.\nSame as R's rnbinom\n\nOptions:\n  :size (default 10)\n  :prob (default 1/2)\n\nSee also:\n    pdf-neg-binomial and cdf-neg-binomial\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/NegativeBinomial.html\n    http://en.wikipedia.org/wiki/Negative_binomial_distribution\n\nExample:\n    (sample-neg-binomial 1000 :prob 1/2 :size 20)\n",
   :var-type "function",
   :line 1407,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([size & {:keys [mean sd], :or {mean 0, sd 1}}]),
   :name "sample-normal",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L274",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sample-normal",
   :doc
   "\nReturns a sample of the given size from a Normal distribution\nThis is equivalent to R's rnorm function.\n\nOptions:\n  :mean (default 0)\n  :sd (default 1)\n\nSee also:\n    pdf-normal, cdf-normal, quantile-normal\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Normal.html\n    http://en.wikipedia.org/wiki/Normal_distribution\n\nExample:\n    (sample-normal 1000 :mean -2 :sd (sqrt 0.5))\n",
   :var-type "function",
   :line 274,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([n x] [n x y]),
   :name "sample-permutations",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1931",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sample-permutations",
   :doc
   "\nIf provided a two arguments (n x), it returns a list of n permutations\nof x. If provided three (n x y) arguments, returns a list with two with n permutations of\neach arguments, where each permutation is drawn from the pooled arguments.\n\nArguments:\n  n -- number of randomized versions of the original two groups to return\n  x -- group 1\n  y -- (default nil) group 2\n\n\nExamples:\n\n  (use '(incanter core stats))\n  (sample-permutations 10 (range 10))\n  (sample-permutations 10 (range 10) (range 10 20))\n\n  ;; extended example with plant-growth data\n  (use '(incanter core stats datasets charts))\n\n  ;; load the plant-growth dataset\n  (def data (to-matrix (get-dataset :plant-growth)))\n\n  ;; break the first column of the data into groups based on treatment (second column).\n  (def groups (group-on data 1 :cols 0))\n\n  ;; define a function for the statistic of interest\n  (defn means-diff [x y] (minus (mean x) (mean y)))\n\n  ;; calculate the difference in sample means between the two groups\n  (def samp-mean-diff (means-diff (first groups) (second groups))) ;; 0.371\n\n  ;; create 500 permuted versions of the original two groups\n  (def permuted-groups (sample-permutations 1000 (first groups) (second groups)))\n\n  ;; calculate the difference of means of the 500 samples\n  (def permuted-means-diffs1 (map means-diff (first permuted-groups) (second permuted-groups)))\n\n  ;; use an indicator function that returns 1 when the randomized means diff is greater\n  ;; than the original sample mean, and zero otherwise. Then take the mean of this sequence\n  ;; of ones and zeros. That is the proportion of times you would see a value more extreme\n  ;; than the sample mean (i.e. the p-value).\n  (mean (indicator #(> % samp-mean-diff) permuted-means-diffs1)) ;; 0.088\n\n  ;; calculate the 95% confidence interval of the null hypothesis. If the\n  ;; sample difference in means is outside of this range, that is evidence\n  ;; that the two means are statistically significantly different.\n  (quantile permuted-means-diffs1 :probs [0.025 0.975]) ;; (-0.606 0.595)\n\n  ;; Plot a histogram of the permuted-means-diffs using the density option,\n  ;; instead of the default frequency, and then add a normal pdf curve with\n  ;; the mean and sd of permuted-means-diffs data for a visual comparison.\n  (doto (histogram permuted-means-diffs1 :density true)\n        (add-lines (range -1 1 0.01) (pdf-normal (range -1 1 0.01)\n                                                 :mean (mean permuted-means-diffs1)\n                                                 :sd (sd permuted-means-diffs1)))\n        view)\n\n  ;; compare the means of treatment 2 and control\n  (def permuted-groups (sample-permutations 1000 (first groups) (last groups)))\n  (def permuted-means-diffs2 (map means-diff (first permuted-groups) (second permuted-groups)))\n  (def samp-mean-diff (means-diff (first groups) (last groups))) ;; -0.4939\n  (mean (indicator #(< % samp-mean-diff) permuted-means-diffs2)) ;; 0.022\n  (quantile permuted-means-diffs2 :probs [0.025 0.975]) ;; (-0.478 0.466)\n\n  ;; compare the means of treatment 1 and treatment 2\n  (def permuted-groups (sample-permutations 1000 (second groups) (last groups)))\n  (def permuted-means-diffs3 (map means-diff (first permuted-groups) (second permuted-groups)))\n  (def samp-mean-diff (means-diff (second groups) (last groups))) ;; -0.865\n  (mean (indicator #(< % samp-mean-diff) permuted-means-diffs3)) ;;  0.002\n  (quantile permuted-means-diffs3 :probs [0.025 0.975]) ;; (-0.676 0.646)\n\n  (doto (box-plot permuted-means-diffs1)\n        (add-box-plot permuted-means-diffs2)\n        (add-box-plot permuted-means-diffs3)\n        view)\n\n\n  Further Reading:\n    http://en.wikipedia.org/wiki/Resampling_(statistics)\n",
   :var-type "function",
   :line 1931,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([size & {:keys [lambda], :or {lambda 1}}]),
   :name "sample-poisson",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1318",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sample-poisson",
   :doc
   "\nReturns a sample of the given size from a Poisson distribution.\nSame as R's rpois\n\nOptions:\n  :lambda (default 1)\n\nSee also:\n    pdf-poisson and cdf-poisson\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Poisson.html\n    http://en.wikipedia.org/wiki/Poisson_distribution\n\nExample:\n    (sample-poisson 1000 :lambda 10)\n",
   :var-type "function",
   :line 1318,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([size & {:keys [df], :or {df 1}}]),
   :name "sample-t",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L897",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sample-t",
   :doc
   "\nReturns a sample of the given size from a Student's t distribution.\nSame as R's rt function.\n\nOptions:\n  :df (default 1)\n\nSee also:\n    pdf-t, cdf-t, and quantile-t\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/StudentT.html\n    http://en.wikipedia.org/wiki/Student-t_distribution\n\nExample:\n    (cdf-t 1000 :df 10)\n",
   :var-type "function",
   :line 897,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists
   ([size
     &
     {:keys [min max integers],
      :or {min 0.0, max 1.0, integers false}}]),
   :name "sample-uniform",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L414",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sample-uniform",
   :doc
   "\nReturns a sample of the given size from a Uniform distribution.\nThis is equivalent to R's runif function.\n\nOptions:\n  :min (default 0)\n  :max (default 1)\n  :integers (default false)\n\nSee also:\n    pdf-uniform and cdf-uniform\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/DoubleUniform.html\n    http://en.wikipedia.org/wiki/Uniform_distribution\n\nExample:\n    (sample-uniform 1000)\n    (sample-uniform 1000 :min 1 :max 10)\n",
   :var-type "function",
   :line 414,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([size & options]),
   :name "sample-weibull",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L595",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sample-weibull",
   :doc
   "\nReturns a sample of the given size from a Weibull distribution\n\nOptions:\n  :shape (default 1)\n  :scale (default 1)\n\nSee also:\n    pdf-weibull, cdf-weibull\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Distributions.html\n    http://en.wikipedia.org/wiki/Weibull_distribution\n\nExample:\n    (sample-weibull 1000 :shape 1 :scale 0.2)\n",
   :var-type "function",
   :line 595,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([& {:keys [scale p df], :or {p 2}}]),
   :name "sample-wishart",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1011",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sample-wishart",
   :doc
   "\nReturns a p-by-p symmetric distribution drawn from a Wishart distribution\n\nOptions:\n  :p (default 2) -- number of dimensions of resulting matrix\n  :df (default p) -- degree of freedoms (aka n), df <= p\n  :scale (default (identity-matrix p)) -- positive definite matrix (aka V)\n\nExamples:\n  (use 'incanter.stats)\n  (sample-wishart :df 10  :p 4)\n\n  ;; calculate the mean of 1000 wishart matrices, should equal (mult df scale)\n  (div (reduce plus (for [_ (range 1000)] (sample-wishart :p 4))) 1000)\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Wishart_distribution\n",
   :var-type "function",
   :line 1011,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x]),
   :name "scalar-abs",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L46",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/scalar-abs",
   :doc "Fast absolute value function",
   :var-type "function",
   :line 46,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x]),
   :name "sd",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1541",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sd",
   :doc
   "\nReturns the sample standard deviation of the data, x. Equivalent to\nR's sd function.\n\nExamples:\n  (sd (sample-normal 100))\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Standard_deviation\n",
   :var-type "function",
   :line 1541,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([coll]),
   :name "simple-ci",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2264",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/simple-ci",
   :doc "Get the confidence interval for the data.",
   :var-type "function",
   :line 2264,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([coll mu]),
   :name "simple-p-value",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2256",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/simple-p-value",
   :doc "Returns the p-value for the data contained in coll.",
   :var-type "function",
   :line 2256,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([y x & {:keys [intercept], :or {intercept true}}]),
   :name "simple-regression",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2817",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/simple-regression",
   :doc
   "A stripped version of linear-model that returns a map containing only\nthe coefficients.",
   :var-type "function",
   :line 2817,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([coll mu]),
   :name "simple-t-test",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2247",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/simple-t-test",
   :doc "Perform a simple t-test on the data contained in coll.",
   :var-type "function",
   :line 2247,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x]),
   :name "skewness",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1641",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/skewness",
   :doc
   "\nReturns the skewness of the data, x. \"Skewness is a measure of the asymmetry\nof the probability distribution of a real-valued random variable.\" (Wikipedia)\n\nExamples:\n\n  (skewness (sample-normal 100000)) ;; approximately 0\n  (skewness (sample-gamma 100000)) ;; approximately 2\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Skewness\n",
   :var-type "function",
   :line 1641,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b]),
   :name "sorensen-index",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3399",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sorensen-index",
   :doc
   "\nhttp://en.wikipedia.org/wiki/S%C3%B8rensen_similarity_index#cite_note-4\n\nThe Sørensen index, also known as Sørensen’s similarity coefficient,\nis a statistic used for comparing the similarity of two samples.\nwhere A and B are the species numbers in samples A and B, respectively,\nand C is the number of species shared by the two samples.\n\nThe Sørensen index is identical to Dice's coefficient which is always in [0, 1] range.\nSørensen index used as a distance measure, 1 − QS, is identical\nto Hellinger distance and Bray–Curtis dissimilarity.\n\nThe Sørensen coefficient is mainly useful for ecological community data\n(e.g. Looman & Campbell, 1960[3]). Justification for its use is primarily\nempirical rather than theoretical\n(although it can be justified theoretically as the intersection of two fuzzy sets[4]).\nAs compared to Euclidean distance, Sørensen distance retains sensitivity\nin more heterogeneous data sets and gives less weight to outliers\n\nThis function assumes you pass in a and b as sets.\n\nThe sorensen index extended to abundance instead of incidence of species is called the Czekanowski index.\n",
   :var-type "function",
   :line 3399,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b]),
   :name "spearmans-rho",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2949",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/spearmans-rho",
   :doc
   "\nhttp://en.wikipedia.org/wiki/Spearman%27s_rank_correlation_coefficient\n\nIn statistics, Spearman's rank correlation coefficient or Spearman's rho,\nis a non-parametric measure of correlation – that is, it assesses how well\nan arbitrary monotonic function could describe the relationship between two\nvariables, without making any other assumptions about the particular nature\nof the relationship between the variables. Certain other measures of correlation\nare parametric in the sense of being based on possible relationships of a\nparameterised form, such as a linear relationship.\n",
   :var-type "function",
   :line 2949,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x] [x m]),
   :name "square-devs-from-mean",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2795",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/square-devs-from-mean",
   :doc
   "takes either a sample or a sample and a precalculated mean.\nreturns the squares of the difference between each observation and the sample mean.",
   :var-type "function",
   :line 2795,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x] [x m]),
   :name "sum-of-square-devs-from-mean",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2807",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sum-of-square-devs-from-mean",
   :doc
   "takes either a sample or a sample and a precalculated mean.\n\nreturns the sum of the squares of the difference between each observation and the sample mean.",
   :var-type "function",
   :line 2807,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([vs]),
   :name "sum-variance-test",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3119",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sum-variance-test",
   :doc
   "\nThe variance of the sum of n independent variables is equal\nto the sum of their variances.\n\n (variance-independence-test [[1 2 3 4] [1 2 3 4]]) -> 5/2\n",
   :var-type "function",
   :line 3119,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([col ds]),
   :name "summarizable?",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2646",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/summarizable?",
   :doc
   "Takes in a column name (or number) and a dataset. Returns true if the column can be summarized, and false otherwise",
   :var-type "function",
   :line 2646,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([col ds]),
   :name "summarizer-fn",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2630",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/summarizer-fn",
   :doc
   "\nTakes in a column (number or name) and a dataset. Returns a function\nto summarize the column if summarizable, and a string describing why\nthe column can't be summarized in the event that it can't\n",
   :var-type "function",
   :line 2630,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([ds]),
   :name "summary",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2652",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/summary",
   :doc
   "\nTakes in a dataset. Returns a summary of that dataset (as a map of maps),\nhaving automatically figured out the relevant datatypes of columns.\nWill be slightly forgiving of mangled data in columns.",
   :var-type "function",
   :line 2652,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x & {:keys [stat fun], :or {stat mean, fun minus}}]),
   :name "sweep",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1877",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/sweep",
   :doc
   "\nReturn an array obtained from an input array by sweeping out a\nsummary statistic. Based to R's sweep function.\n\nArguments:\n  x is an sequence\n\n\nOptions:\n      :stat (default mean) the statistic to sweep out\n      :fun (defaul minus) the function used to sweep the stat out\n\nExample:\n\n  (use '(incanter core stats))\n\n  (def x (sample-normal 30 :mean 10 :sd 5))\n  (sweep x) ;; center the data around mean\n  (sweep x :stat sd :fun div) ;; divide data by its sd\n",
   :var-type "function",
   :line 1877,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists
   ([x
     &
     {:keys [y mu paired conf-level alternative var-equal],
      :or
      {paired false,
       alternative :two-sided,
       conf-level 0.95,
       var-equal false}}]),
   :name "t-test",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2155",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/t-test",
   :doc
   "\nArgument:\n  x : sample to test\n\nOptions:\n  :y (default nil)\n  :mu (default (mean y) or 0) population mean\n  :alternative (default :two-sided) other choices :less :greater\n  :var-equal TODO (default false) variance equal\n  :paired TODO (default false) paired test\n  :conf-level (default 0.95) for returned confidence interval\n\nExamples:\n\n  (t-test (range 1 11) :mu 0)\n  (t-test (range 1 11) :mu 0 :alternative :less)\n  (t-test (range 1 11) :mu 0 :alternative :greater)\n\n  (t-test (range 1 11) :y (range 7 21))\n  (t-test (range 1 11) :y (range 7 21) :alternative :less)\n  (t-test (range 1 11) :y (range 7 21) :alternative :greater)\n  (t-test (range 1 11) :y (conj (range 7 21) 200))\n\nReferences:\n  http://en.wikipedia.org/wiki/T_test\n  http://www.socialresearchmethods.net/kb/stat_t.php\n",
   :var-type "function",
   :line 2155,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x & options]),
   :name "tabulate",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2299",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/tabulate",
   :doc
   "\nCross-tabulates the values of the given numeric matrix.\n\nReturns a hash-map with the following fields:\n  :table -- the table of counts for each combination of values,\n            this table is only returned if x has two-columns\n  :levels -- a sequence of sequences, where each sequence list\n             the levels (possible values) of the corresponding\n             column of x.\n  :margins -- a sequence of sequences, where each sequence\n              represents the marginal total for each level\n              of the corresponding column of x.\n  :counts -- a hash-map, where vectors of unique combinations\n             of the cross-tabulated levels are the keys and the\n             values are the total count of each combination.\n  :N  -- the grand-total for the contingency table\n\n\nExamples:\n\n  (use '(incanter core stats))\n  (tabulate [1 2 3 2 3 2 4 3 5])\n  (tabulate (sample-poisson 100 :lambda 5))\n\n  (use '(incanter core stats datasets))\n  (def math-prog (to-matrix (get-dataset :math-prog)))\n  (tabulate (sel math-prog :cols [1 2]))\n\n\n  (def data (matrix [[1 0 1]\n                     [1 1 1]\n                     [1 1 1]\n                     [1 0 1]\n                     [0 0 0]\n                     [1 1 1]\n                     [1 1 1]\n                     [1 0 1]\n                     [1 1 0]]))\n  (tabulate data)\n\n\n  (def data (matrix [[1 0]\n                     [1 1]\n                     [1 1]\n                     [1 0]\n                     [0 0]\n                     [1 1]\n                     [1 1]\n                     [1 0]\n                     [1 1]]))\n  (tabulate data)\n",
   :var-type "function",
   :line 2299,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([a b]),
   :name "tanimoto-coefficient",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L3246",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/tanimoto-coefficient",
   :doc
   "\nhttp://en.wikipedia.org/wiki/Jaccard_index\n\nThe cosine similarity metric may be extended such that it yields the\nJaccard coefficient in the case of binary attributes.\nThis is the Tanimoto coefficient. ",
   :var-type "function",
   :line 3246,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([x]),
   :name "variance",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L1494",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/variance",
   :doc
   "\nReturns the sample variance of the data, x. Equivalent to R's var function.\n\nExamples:\n  (variance (sample-normal 100))\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Sample_variance#Population_variance_and_sample_variance\n",
   :var-type "function",
   :line 1494,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists ([z x y]),
   :name "within",
   :namespace "incanter.stats",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj#L2790",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/stats.clj",
   :wiki-url
   "http://liebke.github.com/incanter//stats-api.html#incanter.stats/within",
   :doc "y is within z of x in metric space.",
   :var-type "function",
   :line 2790,
   :file "modules/incanter-core/src/incanter/stats.clj"}
  {:arglists
   ([chart
     filename
     &
     {:keys [width height], :or {width 500, height 400}}]),
   :name "save-svg",
   :namespace "incanter.svg",
   :source-url
   "https://github.com/liebke/incanter/blob/4cd07fa339dd6e91f0f6f4d30057671005431d2d/modules/incanter-svg/src/incanter/svg.clj#L13",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/4cd07fa339dd6e91f0f6f4d30057671005431d2d/modules/incanter-svg/src/incanter/svg.clj",
   :wiki-url
   "http://liebke.github.com/incanter//svg-api.html#incanter.svg/save-svg",
   :doc
   "\nSave a chart object as an SVG document.\n\nArguments:\n  chart\n  filename\n\nOptions:\n  :width (default 500)\n  :height (default 400)\n\nExamples:\n\n  (use '(incanter core charts svg))\n  (save-svg (function-plot sin -4 4) \"./svg-chart.svg\")\n\n",
   :var-type "function",
   :line 13,
   :file "modules/incanter-svg/src/incanter/svg.clj"}
  {:arglists ([exp v] [exp v degree]),
   :name "deriv",
   :namespace "incanter.symbolic",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/symbolic.clj#L144",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/symbolic.clj",
   :wiki-url
   "http://liebke.github.com/incanter//symbolic-api.html#incanter.symbolic/deriv",
   :doc
   "\nMacro for symbolic differentiation. with 2 args, takes 1st degree deriv.\nwith 3, takes arbitrary degrees. contains all deriv rules for basic funcs.\n\n\nExamples:\n\n  (use '(incanter core symbolic))\n\n  (deriv (+ x 3) x) ; => 1\n  (deriv (* x y) x) ; => y\n  (deriv (* (* x y) (+ x 3)) x) ; => (+ (* (+ x 3) y) (* x y))\n  (deriv (* (* x y) (+ x 3)) y) ; => (* (+ x 3) x)\n\n  (deriv (* x y (+ x 3)) x) ; => (+ (* y (+ x 3)) (* y x))\n  (deriv (* x y (+ x 3)) y) ; => (* (+ x 3) x)\n\n  (deriv (sin x) x) ; => (cos x)\n  (deriv (cos x) x) ; => (* -1 (sin x))\n\n  (deriv (sin (* x y)) y) ; => (* x (cos (* x y)))\n\n  (deriv (pow x 3) x) ; => (* 3 (pow x 2))\n  (deriv (** x 3) x) ; => (* 3 (pow x 2))\n\n  (deriv (pow x 3) x 2) ; => (* 3 (* 2 x))\n\n  (deriv (* x y (+ x 3)) x 2) ; => (+ y y)\n  (deriv (* x y (+ x 3)) x 3) ; => 0\n\n  (deriv (+ (* 3 x) (* 8 x)) x) ; => 11\n\n\n\n  ;; NOT WORKING YET\n\n  (deriv (/ 1 x) x) ; => (* (deriv* (* (x)) x) (* -1 (pow (* (x)) -2)))\n                                        ^-- need to fix",
   :var-type "macro",
   :line 144,
   :file "modules/incanter-core/src/incanter/symbolic.clj"}
  {:arglists ([exp v] [exp vr degree]),
   :name "deriv*",
   :namespace "incanter.symbolic",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/symbolic.clj#L79",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/symbolic.clj",
   :wiki-url
   "http://liebke.github.com/incanter//symbolic-api.html#incanter.symbolic/deriv*",
   :doc
   "\nMain sub-function for differentiation. with 2 args, takes 1st degree deriv.\nwith 3, takes arbitrary degrees. contains all deriv rules for basic funcs.\n\n\nExamples:\n\n  (use '(incanter core symbolic))\n\n  (deriv* '(+ x 3) 'x)\n  (deriv* '(* x y) 'x)\n  (deriv* '(* (* x y) '(+ x 3)) x)\n  (deriv* '(* (* x y) (+ x 3)) 'y)\n\n  (deriv* '(* x y (+ x 3)) 'x)\n  (deriv* '(* x y (+ x 3)) 'y)\n\n  (deriv* '(* x y (+ x 3)) 'x 2)\n  (deriv* '(* x y (+ x 3)) 'x 3)\n",
   :var-type "function",
   :line 79,
   :file "modules/incanter-core/src/incanter/symbolic.clj"}
  {:arglists ([[& args] expr v] [[& args] expr v degree]),
   :name "deriv-fn",
   :namespace "incanter.symbolic",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/symbolic.clj#L262",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/symbolic.clj",
   :wiki-url
   "http://liebke.github.com/incanter//symbolic-api.html#incanter.symbolic/deriv-fn",
   :doc
   "\nExamples:\n  (use '(incanter core symbolic))\n\n  (deriv-fn [x y] (+ (* x y) x) x)\n\n  ((deriv-fn [x y] (+ (* x y) x) x) 5 9)\n\n  (use 'incanter.charts)\n  (doto (function-plot sin -5 5)\n     (add-function (deriv-fn [x] (sin x) x) -5 5)\n     (add-function (deriv-fn [x] (sin x) x 2) -5 5)\n     view)\n\n  (let [f (fn [x] (pow x 2))\n        df (deriv-fn [x] (pow x 2) x)]\n    (doto (function-plot f -5 5)\n      (add-function df -5 5)\n      view))\n\n\n  (let [f (fn [x] (pow x 3))\n        df (deriv-fn [x] (pow x 3) x)]\n    (doto (function-plot f -5 5)\n      (add-function df -5 5)\n      view))\n\n\n  ;; NOT WORKING YET\n\n  (let [f (fn [x] (/ 1 x ))\n        df (deriv-fn [x] (/ 1 x) x)]\n    (doto (function-plot f 0.5 5)\n      (add-function df 0.5 5)\n      view))\n",
   :var-type "macro",
   :line 262,
   :file "modules/incanter-core/src/incanter/symbolic.clj"}
  {:arglists ([[& args] expr v] [[& args] expr v degree]),
   :name "deriv-fn*",
   :namespace "incanter.symbolic",
   :source-url
   "https://github.com/liebke/incanter/blob/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/symbolic.clj#L236",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/70de03d51f21ea4002c8a24549a1aa360a062754/modules/incanter-core/src/incanter/symbolic.clj",
   :wiki-url
   "http://liebke.github.com/incanter//symbolic-api.html#incanter.symbolic/deriv-fn*",
   :doc
   "\nExamples:\n  (use '(incanter core symbolic))\n\n  (deriv-fn* '[x y] '(+ (* x y) x) 'x)\n\n  ((deriv-fn* '[x y] '(+ (* x y) x) 'x) 5 9)\n",
   :var-type "function",
   :line 236,
   :file "modules/incanter-core/src/incanter/symbolic.clj"}
  {:arglists ([ind ts] [ind cols ts] [ind-1 ind-2 cols ts]),
   :name "$$",
   :namespace "incanter.zoo",
   :source-url
   "https://github.com/liebke/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L186",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :wiki-url
   "http://liebke.github.com/incanter//zoo-api.html#incanter.zoo/$$",
   :doc
   "\nThis is the equivalent of :: in xts. That is, it slices out\nthe timeseries between ind-1 and ind-2. These are any values\nthat can be coerced into clj-time values.\n",
   :var-type "function",
   :line 186,
   :file "modules/incanter-zoo/src/incanter/zoo.clj"}
  {:arglists ([& zs]),
   :name "aligned?",
   :namespace "incanter.zoo",
   :source-url
   "https://github.com/liebke/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L125",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :wiki-url
   "http://liebke.github.com/incanter//zoo-api.html#incanter.zoo/aligned?",
   :doc "Is the :index column identical for all zs.",
   :var-type "function",
   :line 125,
   :file "modules/incanter-zoo/src/incanter/zoo.clj"}
  {:arglists ([x]),
   :name "coredata",
   :namespace "incanter.zoo",
   :source-url
   "https://github.com/liebke/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L97",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :wiki-url
   "http://liebke.github.com/incanter//zoo-api.html#incanter.zoo/coredata",
   :doc
   "\nReturn the :rows of a dataset, with :index dissoc'd.\nIntended to be used internally time series function to get at data.\n",
   :var-type "function",
   :line 97,
   :file "modules/incanter-zoo/src/incanter/zoo.clj"}
  {:arglists ([z] [z n]),
   :name "lag",
   :namespace "incanter.zoo",
   :source-url
   "https://github.com/liebke/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L216",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :wiki-url
   "http://liebke.github.com/incanter//zoo-api.html#incanter.zoo/lag",
   :doc
   "\nReturn the timeseries lagged by n units or 1 if not specified.\nNo time calculations are made in the index column. The output\ntimeseries is of the same length as the input.\n",
   :var-type "function",
   :line 216,
   :file "modules/incanter-zoo/src/incanter/zoo.clj"}
  {:arglists ([f n coll]),
   :name "roll-apply",
   :namespace "incanter.zoo",
   :source-url
   "https://github.com/liebke/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L54",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :wiki-url
   "http://liebke.github.com/incanter//zoo-api.html#incanter.zoo/roll-apply",
   :doc
   "\nA generic function for applying a function to rolling window of a collection.\n\nArguments:\nf -- function to be applied\nn -- size of rolling window\ncoll -- collection of data\n",
   :var-type "function",
   :line 54,
   :file "modules/incanter-zoo/src/incanter/zoo.clj"}
  {:arglists ([n coll]),
   :name "roll-max",
   :namespace "incanter.zoo",
   :source-url
   "https://github.com/liebke/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L75",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :wiki-url
   "http://liebke.github.com/incanter//zoo-api.html#incanter.zoo/roll-max",
   :doc "\nReturns the rolling max of the previous n elements.\n",
   :var-type "function",
   :line 75,
   :file "modules/incanter-zoo/src/incanter/zoo.clj"}
  {:arglists ([n coll]),
   :name "roll-mean",
   :namespace "incanter.zoo",
   :source-url
   "https://github.com/liebke/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L40",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :wiki-url
   "http://liebke.github.com/incanter//zoo-api.html#incanter.zoo/roll-mean",
   :doc
   "\nReturns the unweighted mean of the previous n data points.\n\nReferences:\nhttp://en.wikipedia.org/wiki/Moving_average#Simple_moving_average\nhttp://www.learningclojure.com/2010/03/moving-average-of-list.html\n",
   :var-type "function",
   :line 40,
   :file "modules/incanter-zoo/src/incanter/zoo.clj"}
  {:arglists ([n coll]),
   :name "roll-median",
   :namespace "incanter.zoo",
   :source-url
   "https://github.com/liebke/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L68",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :wiki-url
   "http://liebke.github.com/incanter//zoo-api.html#incanter.zoo/roll-median",
   :doc "\nReturns the rolling median of the previous n elements.\n",
   :var-type "function",
   :line 68,
   :file "modules/incanter-zoo/src/incanter/zoo.clj"}
  {:arglists ([n coll]),
   :name "roll-min",
   :namespace "incanter.zoo",
   :source-url
   "https://github.com/liebke/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L82",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :wiki-url
   "http://liebke.github.com/incanter//zoo-api.html#incanter.zoo/roll-min",
   :doc "\nReturns the rolling min of the previous n elements.\n",
   :var-type "function",
   :line 82,
   :file "modules/incanter-zoo/src/incanter/zoo.clj"}
  {:arglists ([t z]),
   :name "within-zoo?",
   :namespace "incanter.zoo",
   :source-url
   "https://github.com/liebke/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L151",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :wiki-url
   "http://liebke.github.com/incanter//zoo-api.html#incanter.zoo/within-zoo?",
   :doc "Is t between the first and last indices.",
   :var-type "function",
   :line 151,
   :file "modules/incanter-zoo/src/incanter/zoo.clj"}
  {:arglists ([x] [x index-col]),
   :name "zoo",
   :namespace "incanter.zoo",
   :source-url
   "https://github.com/liebke/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L158",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :wiki-url
   "http://liebke.github.com/incanter//zoo-api.html#incanter.zoo/zoo",
   :doc
   "\nReturn the given dataset as a zoo value which is simply a dataset\nthat contains an column of clj-time values specified by index-col,\ndefault :index. That column must contain values that can be coerced\ninto Jodas using the TimeCoercible Protocol.\n",
   :var-type "function",
   :line 158,
   :file "modules/incanter-zoo/src/incanter/zoo.clj"}
  {:arglists ([f n zoo column & args]),
   :name "zoo-apply",
   :namespace "incanter.zoo",
   :source-url
   "https://github.com/liebke/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L234",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :wiki-url
   "http://liebke.github.com/incanter//zoo-api.html#incanter.zoo/zoo-apply",
   :doc
   "\nBehave as for roll-apply but accept a zoo and a single column\nupon which to roll-apply f. Returns a zoo of the same length\nas input zoo with pre-pended nils\n",
   :var-type "function",
   :line 234,
   :file "modules/incanter-zoo/src/incanter/zoo.clj"}
  {:arglists ([f & zs]),
   :name "zoo-row-map",
   :namespace "incanter.zoo",
   :source-url
   "https://github.com/liebke/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L263",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :wiki-url
   "http://liebke.github.com/incanter//zoo-api.html#incanter.zoo/zoo-row-map",
   :doc
   "\nAccept a number of aligned zoo object and pass them row-wise\ninto f, return a zoo. f must accept and return maps. The :index\ncolumn is stripped out before f is applied, and then replaced\nafterwards with the :index of the first.\n",
   :var-type "function",
   :line 263,
   :file "modules/incanter-zoo/src/incanter/zoo.clj"}
  {:arglists ([f & zs]),
   :name "zoo-row-map-",
   :namespace "incanter.zoo",
   :source-url
   "https://github.com/liebke/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L249",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :wiki-url
   "http://liebke.github.com/incanter//zoo-api.html#incanter.zoo/zoo-row-map-",
   :doc
   "\nAccept a number of aligned zoo object and pass them row-wise into f,\nreturn a seq of maps of the output of the output.\nf must accept and return maps. The :index column is stripped out before\nf is applied, and then replaced afterwards.\n",
   :var-type "function",
   :line 249,
   :file "modules/incanter-zoo/src/incanter/zoo.clj"}
  {:arglists ([f & s]),
   :name "zoo-row-map-occupied",
   :namespace "incanter.zoo",
   :source-url
   "https://github.com/liebke/incanter/blob/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj#L278",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/62062b0f3353d34dfd8f08ce4d748be35e4ec6a4/modules/incanter-zoo/src/incanter/zoo.clj",
   :wiki-url
   "http://liebke.github.com/incanter//zoo-api.html#incanter.zoo/zoo-row-map-occupied",
   :doc
   "zoo-row-map- and remove the empties. This returns a seq of maps",
   :var-type "function",
   :line 278,
   :file "modules/incanter-zoo/src/incanter/zoo.clj"}
  {:file "modules/incanter-excel/src/incanter/excel/cells.clj",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/bccaf39c8258cedf1a6522a52aa6d9129b22e458/modules/incanter-excel/src/incanter/excel/cells.clj",
   :source-url
   "https://github.com/liebke/incanter/blob/bccaf39c8258cedf1a6522a52aa6d9129b22e458/modules/incanter-excel/src/incanter/excel/cells.clj#L14",
   :wiki-url
   "http://liebke.github.com/incanter//excel-api.html#incanter.excel.cells/get-cell-formula-value",
   :namespace "incanter.excel.cells",
   :line 14,
   :var-type "multimethod",
   :doc
   "Get the value after the evaluating the formula.  See http://poi.apache.org/spreadsheet/eval.html#Evaluate",
   :name "get-cell-formula-value"}
  {:file "modules/incanter-excel/src/incanter/excel/cells.clj",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/bccaf39c8258cedf1a6522a52aa6d9129b22e458/modules/incanter-excel/src/incanter/excel/cells.clj",
   :source-url
   "https://github.com/liebke/incanter/blob/bccaf39c8258cedf1a6522a52aa6d9129b22e458/modules/incanter-excel/src/incanter/excel/cells.clj#L20",
   :wiki-url
   "http://liebke.github.com/incanter//excel-api.html#incanter.excel.cells/get-cell-value",
   :namespace "incanter.excel.cells",
   :line 20,
   :var-type "multimethod",
   :doc "Get the cell value depending on the cell type.",
   :name "get-cell-value"}
  {:file "modules/incanter-excel/src/incanter/excel/workbook.clj",
   :raw-source-url
   "https://github.com/liebke/incanter/raw/bccaf39c8258cedf1a6522a52aa6d9129b22e458/modules/incanter-excel/src/incanter/excel/workbook.clj",
   :source-url
   "https://github.com/liebke/incanter/blob/bccaf39c8258cedf1a6522a52aa6d9129b22e458/modules/incanter-excel/src/incanter/excel/workbook.clj#L8",
   :wiki-url
   "http://liebke.github.com/incanter//excel-api.html#incanter.excel.workbook/get-workbook-sheet",
   :namespace "incanter.excel.workbook",
   :line 8,
   :var-type "multimethod",
   :doc
   "Retrieve the Excel workbook based on either the index or the sheet name.",
   :name "get-workbook-sheet"})}
