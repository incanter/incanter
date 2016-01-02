{:namespaces
 ({:doc nil,
   :name "incanter.backstage.zoo-commons",
   :wiki-url
   "http://incanter.github.com/incanter/backstage.zoo-commons-api.html",
   :source-url nil}
  {:doc
   "This is library provides functions for performing\nbasic Bayesian modeling and inference.\n",
   :author "David Edgar Liebke",
   :name "incanter.bayes",
   :wiki-url "http://incanter.github.com/incanter/bayes-api.html",
   :source-url nil}
  {:doc nil,
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
   "This is the core numerics library for Incanter.\nIt provides functions for vector- and matrix-based\nmathematical operations and the core data manipulation\nfunctions for Incanter.\n\nThis library is built on Parallel Colt\n(http://sites.google.com/site/piotrwendykier/software/parallelcolt)\nan extension of the Colt numerics library\n(http://acs.lbl.gov/~hoschek/colt/).\n",
   :author "David Edgar Liebke",
   :name "incanter.core",
   :wiki-url "http://incanter.github.com/incanter/core-api.html",
   :source-url nil}
  {:doc nil,
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
  {:doc nil,
   :name "incanter.internal",
   :wiki-url "http://incanter.github.com/incanter/internal-api.html",
   :source-url nil}
  {:doc
   "Library for reading and writing Incanter datasets and matrices.",
   :name "incanter.io",
   :wiki-url "http://incanter.github.com/incanter/io-api.html",
   :source-url nil}
  {:doc
   "This library is used to render LaTex Math equations, based\non the jLateXMath library, and applying them incanter.charts as annotations\nand subtitles.\n         ",
   :author "David Edgar Liebke",
   :name "incanter.latex",
   :wiki-url "http://incanter.github.com/incanter/latex-api.html",
   :source-url nil}
  {:doc
   "A simple library that provides functions for persisting \nIncanter data structures using MongoDB.\n\nUse incanter.mongodb in combination with the somnium.congomongo library. \nFor usage examples, see the Congomongo README at http://github.com/somnium/congomongo, \nand the examples/blog/mongodb_datasets.clj file in the Incanter distribution. \n\nHere are Somnium's descriptions of Congomongo's functions:\n\n  (mongo! & args) : Creates a Mongo object and sets the default database.\n     Keyword arguments include:\n     :host -> defaults to localhost\n     :port -> defaults to 27017\n     :db   -> defaults to nil (you'll have to set it anyway, might as well do it now.)\n\n  (get-coll coll) : Returns a DBCollection object\n\n  (fetch coll & options) : Fetches objects from a collection. Optional arguments include\n   :where  -> takes a query map\n   :only   -> takes an array of keys to retrieve\n   :as     -> what to return, defaults to :clojure, can also be :json or :mongo\n   :from   -> argument type, same options as above\n   :one?   -> defaults to false, use fetch-one as a shortcut\n   :count? -> defaults to false, use fetch-count as a shortcut\n\n  (fetch-one coll & options) : same as (fetch collection :one? true)\n\n  (fetch-count coll & options) : same as (fetch collection :count? true)\n\n  (insert! coll obj & options) : Inserts a map into collection. Will not overwrite existing maps.\n   Takes optional from and to keyword arguments. To insert\n   as a side-effect only specify :to as nil.\n\n  (mass-insert! coll objs & options) : Inserts a sequence of maps.\n\n  (update! coll old new & options) : Alters/inserts a map in a collection. Overwrites existing objects.\n   The shortcut forms need a map with valid :_id and :_ns fields or\n   a collection and a map with a valid :_id field.\n\n  (destroy! coll query-map) : Removes map from collection. Takes a collection name and\n    a query map\n\n  (add-index! coll fields & options) : Adds an index on the collection for the specified fields if it does not exist.\n    Options include:\n    :unique -> defaults to false\n    :force  -> defaults to true\n\n  (drop-index! coll fields) : Drops an index on the collection for the specified fields\n\n  (drop-all-indexes! coll) : Drops all indexes from a collection\n\n  (get-indexes coll & options) : Get index information on collection\n\n  (drop-database title) : drops a database from the mongo server\n\n  (set-database title) : atomically alters the current database\n\n  (databases) : List databases on the mongo server\n\n  (collections) : Returns the set of collections stored in the current database\n\n  (drop-collection coll) : Permanently deletes a collection. Use with care.",
   :author "David Edgar Liebke",
   :name "incanter.mongodb",
   :wiki-url "http://incanter.github.com/incanter/mongodb-api.html",
   :source-url nil}
  {:doc nil,
   :name "incanter.optimize",
   :wiki-url "http://incanter.github.com/incanter/optimize-api.html",
   :source-url nil}
  {:doc
   "This library currently has only a single function, save-pdf, which saves\ncharts as a PDF file. To build this namespace make sure the you have the iText\nlibrary (http://itextpdf.com/) as a declared dependency in your pom.xml or\nproject.clj file:\n[com.lowagie/itext \"1.4\"] ",
   :name "incanter.pdf",
   :wiki-url "http://incanter.github.com/incanter/pdf-api.html",
   :source-url nil}
  {:doc nil,
   :name "incanter.som",
   :wiki-url "http://incanter.github.com/incanter/som-api.html",
   :source-url nil}
  {:doc "SQL module for interacting with databases.",
   :name "incanter.sql",
   :wiki-url "http://incanter.github.com/incanter/sql-api.html",
   :source-url nil}
  {:doc
   "This is the core statistical library for Incanter.\nIt provides probability functions (cdf, pdf, quantile),\nrandom number generation, statistical tests, basic\nmodeling functions, similarity/association measures,\nand more.\n\nThis library is built on Parallel Colt \n(http://sites.google.com/site/piotrwendykier/software/parallelcolt),\nan extension of the Colt numerics library \n(http://acs.lbl.gov/~hoschek/colt/).\n",
   :author "David Edgar Liebke and Bradford Cross",
   :name "incanter.stats",
   :wiki-url "http://incanter.github.com/incanter/stats-api.html",
   :source-url nil}
  {:doc
   "A library for performing symbolic math, a port from SICP (http://mitpress.mit.edu/sicp/).",
   :author "Bryce Nyeggen, with modifications by David Edgar Liebke",
   :name "incanter.symbolic",
   :wiki-url "http://incanter.github.com/incanter/symbolic-api.html",
   :source-url nil}
  {:doc
   "This is a port of Zoo from R in order to create the basis\nof a library for time series data.\n\nThis library is built on Parallel Colt \n(http://sites.google.com/site/piotrwendykier/software/parallelcolt),\nan extension of the Colt numerics library \n(http://acs.lbl.gov/~hoschek/colt/).\n",
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
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/bayes.clj",
   :name "sample-model-params",
   :file "modules/incanter-core/src/incanter/bayes.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/bayes.clj#L35",
   :line 35,
   :var-type "function",
   :arglists ([size {:keys [x y coefs residuals]}]),
   :doc
   " Returns a sample of the given size of the the parameters (coefficients and\nerror variance) of the given linear-model. The sample is generated using\nGibbs sampling.\n\nSee also:\n  incanter.stats/linear-model\n\nExamples:\n  (use '(incanter core datasets stats charts bayes))\n\n  (def ols-data (to-matrix (get-dataset :survey)))\n  (def x (sel ols-data (range 0 2313) (range 1 10)))\n  (def y (sel ols-data (range 0 2313) 10))\n  (def lm (linear-model y x :intercept false))\n  (def param-samp (sample-model-params 5000 lm))\n\n  ;; view trace plots\n  (view (trace-plot (:var param-samp )))\n  (view (trace-plot (sel (:coefs param-samp) :cols 0)))\n\n  ;; view histograms\n  (view (histogram (:var param-samp)))\n  (view (histogram (sel (:coefs param-samp) :cols 0)))\n\n  ;; calculate statistics\n  (map mean (trans (:coefs param-samp)))\n  (map median (trans (:coefs param-samp)))\n  (map sd (trans (:coefs param-samp)))\n\n  ;; show the 95% bayesian confidence interval for the first coefficient\n  (quantile (sel (:coefs param-samp) :cols 0) :probs [0.025 0.975])",
   :namespace "incanter.bayes",
   :wiki-url
   "http://incanter.github.com/incanter//bayes-api.html#incanter.bayes/sample-model-params"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/bayes.clj",
   :name "sample-multinomial-params",
   :file "modules/incanter-core/src/incanter/bayes.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/bayes.clj#L95",
   :line 95,
   :var-type "function",
   :arglists ([size counts]),
   :doc
   " Returns a sample of multinomial proportion parameters.\nThe counts are assumed to have a multinomial distribution.\nA uniform prior distribution is assigned to the multinomial vector\ntheta, then the posterior distribution of theta is\nproportional to a dirichlet distribution with parameters\n(plus counts 1).\n\n\nExamples:\n  (use '(incanter core stats bayes charts))\n\n  (def  samp-props (sample-multinomial-params 1000 [727 583 137]))\n\n  ;; view means, 95% CI, and histograms of the proportion parameters\n  (mean (sel samp-props :cols 0))\n  (quantile (sel samp-props :cols 0) :probs [0.0275 0.975])\n  (view (histogram (sel samp-props :cols 0)))\n  (mean (sel samp-props :cols 1))\n  (quantile (sel samp-props :cols 1) :probs [0.0275 0.975])\n  (view (histogram (sel samp-props :cols 1)))\n  (mean (sel samp-props :cols 2))\n  (quantile (sel samp-props :cols 2) :probs [0.0275 0.975])\n  (view (histogram (sel samp-props :cols 2)))\n\n  ;; view  a histogram of the difference in proportions between the first\n  ;; two candidates\n  (view (histogram (minus (sel samp-props :cols 0) (sel samp-props :cols 1))))",
   :namespace "incanter.bayes",
   :wiki-url
   "http://incanter.github.com/incanter//bayes-api.html#incanter.bayes/sample-multinomial-params"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/bayes.clj",
   :name "sample-mvn-params",
   :file "modules/incanter-core/src/incanter/bayes.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/bayes.clj#L133",
   :line 133,
   :var-type "function",
   :arglists ([size y & options]),
   :doc
   "Returns samples of means (sampled from an mvn distribution) and vectorized covariance\n matrices (sampled from an inverse-wishart distribution) for the given mvn data.\n\n Arguments:\n   size -- the number of samples to return\n   y -- the data used to estimate the parameters\n\n\n Returns map with following fields:\n   :means\n   :sigmas\n\n\n Examples:\n\n   (use '(incanter core stats bayes charts))\n   (def y (sample-mvn 500 :mean [0 0] :sigma (identity-matrix 2)))\n   (def samp (sample-mvn-params 1000 y))\n\n   (map mean (trans (:means samp)))\n   (symmetric-matrix (map mean (trans (:sigmas samp))) :lower false)\n\n   (view (histogram (sel (:means samp) :cols 0) :x-label \"mean 1\"))\n   (view (histogram (sel (:means samp) :cols 1) :x-label \"mean 2\"))\n   (view (histogram (sel (:sigmas samp) :cols 1) :x-label \"covariance\"))\n   (view (histogram (sel (:sigmas samp) :cols 0) :x-label \"variance 1\"))\n   (view (histogram (sel (:sigmas samp) :cols 2) :x-label \"variance 2\"))\n\n   (map #(quantile % :probs [0.025 0.0975]) (trans (:means samp)))\n   (map #(quantile % :probs [0.025 0.0975]) (trans (:sigmas samp)))\n\n\n\n\n   (use '(incanter core stats bayes charts))\n   (def y (sample-mvn 500 :sigma (symmetric-matrix [10 5 10]) :mean [5 2]))\n   (def samp (sample-mvn-params 1000 y))\n   (symmetric-matrix (map mean (trans (:sigmas samp))) :lower false)\n   (map mean (trans (:means samp)))\n\n\n",
   :namespace "incanter.bayes",
   :wiki-url
   "http://incanter.github.com/incanter//bayes-api.html#incanter.bayes/sample-mvn-params"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/bayes.clj",
   :name "sample-proportions",
   :file "modules/incanter-core/src/incanter/bayes.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/bayes.clj#L88",
   :line 88,
   :var-type "function",
   :arglists ([size counts]),
   :doc
   " sample-proportions has been renamed sample-multinomial-params",
   :namespace "incanter.bayes",
   :wiki-url
   "http://incanter.github.com/incanter//bayes-api.html#incanter.bayes/sample-proportions"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a5d2d5afca3244cc534f6d3b426850f5418473c1/modules/incanter-core/src/incanter/censored.clj",
   :name "censored-mean-lower",
   :file "modules/incanter-core/src/incanter/censored.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a5d2d5afca3244cc534f6d3b426850f5418473c1/modules/incanter-core/src/incanter/censored.clj#L56",
   :line 56,
   :var-type "function",
   :arglists ([a mu sigma]),
   :doc
   " Returns the mean of a normal distribution (with mean mu and standard\ndeviation sigma) with the lower tail censored at 'a'",
   :namespace "incanter.censored",
   :wiki-url
   "http://incanter.github.com/incanter//censored-api.html#incanter.censored/censored-mean-lower"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a5d2d5afca3244cc534f6d3b426850f5418473c1/modules/incanter-core/src/incanter/censored.clj",
   :name "censored-mean-two-sided",
   :file "modules/incanter-core/src/incanter/censored.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a5d2d5afca3244cc534f6d3b426850f5418473c1/modules/incanter-core/src/incanter/censored.clj#L21",
   :line 21,
   :var-type "function",
   :arglists ([a b mu sigma]),
   :doc
   " Returns the mean of a normal distribution (with mean mu and standard\ndeviation sigma) with the lower tail censored at 'a' and the upper\ntail censored at 'b'",
   :namespace "incanter.censored",
   :wiki-url
   "http://incanter.github.com/incanter//censored-api.html#incanter.censored/censored-mean-two-sided"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a5d2d5afca3244cc534f6d3b426850f5418473c1/modules/incanter-core/src/incanter/censored.clj",
   :name "censored-mean-upper",
   :file "modules/incanter-core/src/incanter/censored.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a5d2d5afca3244cc534f6d3b426850f5418473c1/modules/incanter-core/src/incanter/censored.clj#L84",
   :line 84,
   :var-type "function",
   :arglists ([b mu sigma]),
   :doc
   " Returns the mean of a normal distribution (with mean mu and standard\ndeviation sigma) with the upper tail censored at 'b'",
   :namespace "incanter.censored",
   :wiki-url
   "http://incanter.github.com/incanter//censored-api.html#incanter.censored/censored-mean-upper"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a5d2d5afca3244cc534f6d3b426850f5418473c1/modules/incanter-core/src/incanter/censored.clj",
   :name "censored-variance-lower",
   :file "modules/incanter-core/src/incanter/censored.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a5d2d5afca3244cc534f6d3b426850f5418473c1/modules/incanter-core/src/incanter/censored.clj#L67",
   :line 67,
   :var-type "function",
   :arglists ([a mu sigma]),
   :doc
   " Returns the variance of a normal distribution (with mean mu and standard\ndeviation sigma) with the lower tail censored at 'a'",
   :namespace "incanter.censored",
   :wiki-url
   "http://incanter.github.com/incanter//censored-api.html#incanter.censored/censored-variance-lower"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a5d2d5afca3244cc534f6d3b426850f5418473c1/modules/incanter-core/src/incanter/censored.clj",
   :name "censored-variance-two-sided",
   :file "modules/incanter-core/src/incanter/censored.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a5d2d5afca3244cc534f6d3b426850f5418473c1/modules/incanter-core/src/incanter/censored.clj#L36",
   :line 36,
   :var-type "function",
   :arglists ([a b mu sigma]),
   :doc
   " Returns the variance of a normal distribution (with mean mu and standard\ndeviation sigma) with the lower tail censored at 'a' and the upper\ntail censored at 'b'",
   :namespace "incanter.censored",
   :wiki-url
   "http://incanter.github.com/incanter//censored-api.html#incanter.censored/censored-variance-two-sided"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a5d2d5afca3244cc534f6d3b426850f5418473c1/modules/incanter-core/src/incanter/censored.clj",
   :name "censored-variance-upper",
   :file "modules/incanter-core/src/incanter/censored.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a5d2d5afca3244cc534f6d3b426850f5418473c1/modules/incanter-core/src/incanter/censored.clj#L94",
   :line 94,
   :var-type "function",
   :arglists ([b mu sigma]),
   :doc
   " Returns the variance of a normal distribution (with mean mu and standard\ndeviation sigma) with the upper tail censored at 'b'",
   :namespace "incanter.censored",
   :wiki-url
   "http://incanter.github.com/incanter//censored-api.html#incanter.censored/censored-variance-upper"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a5d2d5afca3244cc534f6d3b426850f5418473c1/modules/incanter-core/src/incanter/censored.clj",
   :name "truncated-variance",
   :file "modules/incanter-core/src/incanter/censored.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a5d2d5afca3244cc534f6d3b426850f5418473c1/modules/incanter-core/src/incanter/censored.clj#L111",
   :line 111,
   :var-type "function",
   :arglists
   ([&
     {:keys [mean sd a b],
      :or {mean 0, sd 1, a NEGATIVE_INFINITY, b POSITIVE_INFINITY}}]),
   :doc
   " Returns the variance of a normal distribution truncated at a and b.\n\nOptions:\n  :mean (default 0) mean of untruncated normal distribution\n  :sd (default 1) standard deviation of untruncated normal distribution\n  :a (default -infinity) lower truncation point\n  :b (default +infinity) upper truncation point\n\nExamples:\n\n  (use '(incanter core stats))\n  (truncated-variance :a -1.96 :b 1.96)\n  (truncated-variance :a 0)\n  (truncated-variance :b 0)\n\n  (use 'incanter.charts)\n  (def x (range -3 3 0.1))\n  (def plot (xy-plot x (map #(truncated-variance :a %) x)))\n  (view plot)\n  (add-lines plot x (map #(truncated-variance :b %) x))\n\n  (def samp (sample-normal 10000))\n  (add-lines plot x (map #(variance (filter (fn [s] (> s %)) samp)) x))\n  (add-lines plot x (map #(variance (mult samp (indicator (fn [s] (> s %)) samp))) x))\n\n\nReferences:\n  DeMaris, A. (2004) Regression with social data: modeling continuous and limited response variables.\n    Wiley-IEEE.\n\n  http://en.wikipedia.org/wiki/Truncated_normal_distribution",
   :namespace "incanter.censored",
   :wiki-url
   "http://incanter.github.com/incanter//censored-api.html#incanter.censored/truncated-variance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-box-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L399",
   :line 399,
   :var-type "macro",
   :arglists ([chart x & options]),
   :doc
   "\nAdds an additional box to an existing box-plot, returns the modified chart object.\n\nOptions:\n  :series-label (default x expression)\n\nExamples:\n\n    (use '(incanter core charts stats))\n    (doto (box-plot (sample-normal 1000) :legend true)\n          view\n          (add-box-plot (sample-normal 1000 :sd 2))\n          (add-box-plot (sample-gamma 1000)))\n\n\n   (with-data (get-dataset :iris)\n     (doto (box-plot :Sepal.Length :legend true)\n       (add-box-plot :Petal.Length)\n       (add-box-plot :Sepal.Width)\n       (add-box-plot :Petal.Width)\n       view))\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-box-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-categories",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L467",
   :line 467,
   :var-type "macro",
   :arglists ([chart categories values & options]),
   :doc
   "\nAdds an additional categories to an existing bar-chart or line-chart, returns the modified chart object.\n\nOptions:\n  :group-by\n  :series-label\n\nExamples:\n\n    (use '(incanter core charts stats datasets))\n    (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))\n    (def years (mapcat identity (repeat 4 [2007 2008 2009])))\n    (def x (sample-uniform 12 :integers true :max 100))\n    (def plot (bar-chart years x :group-by seasons :legend true))\n    (view plot)\n    (add-categories plot years [10 20 40] :series-label \"winter-break\")\n\n    (add-categories plot\n                       (plus 3 years)\n                       (sample-uniform 12 :integers true :max 100)\n                       :group-by seasons)\n\n    (def plot2 (line-chart years x :group-by seasons :legend true))\n    (view plot2)\n    (add-categories plot2 (plus 3 years) (sample-uniform 12 :integers true :max 100) :group-by seasons)\n\n    (with-data (get-dataset :iris)\n      (doto (line-chart :Species :Sepal.Length\n                        :data ($rollup mean :Sepal.Length :Species)\n                        :legend true)\n        (add-categories :Species :Sepal.Width :data ($rollup mean :Sepal.Width :Species))\n        (add-categories :Species :Petal.Length :data ($rollup mean :Petal.Length :Species))\n        (add-categories :Species :Petal.Width :data ($rollup mean :Petal.Width :Species))\n        view))\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-categories"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-function",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L655",
   :line 655,
   :var-type "macro",
   :arglists ([chart function min-range max-range & options]),
   :doc
   " Adds a xy-plot of the given function to the given chart, returning\na modified version of the chart.\n\nOptions:\n  :series-label (default x expression)\n  :step-size (default (/ (- max-range min-range) 500))\n\nSee also:\n  function-plot, view, save, add-function, add-points, add-lines\n\n\nExamples:\n\n  (use '(incanter core stats charts))\n\n  ;; plot the sine and cosine functions\n  (doto (function-plot sin (- Math/PI) Math/PI)\n        (add-function cos (- Math/PI) Math/PI)\n        view)\n\n\n  ;; plot two normal pdf functions\n  (doto (function-plot pdf-normal -3 3 :legend true)\n        (add-function (fn [x] (pdf-normal x :mean 0.5 :sd 0.5)) -3 3)\n        view)\n\n\n  ;; plot a user defined function and its derivative\n  (use '(incanter core charts optimize))\n\n  ;; define the function, x^3 + 2x^2 + 2x + 3\n  (defn cubic [x] (+ (* x x x) (* 2 x x) (* 2 x) 3))\n\n  ;; use the derivative function to get a function\n  ;; that approximates its derivative\n  (def deriv-cubic (derivative cubic))\n\n  ;; plot the cubic function and its derivative\n  (doto (function-plot cubic -10 10)\n        (add-function deriv-cubic -10 10)\n        view)",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-function"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-histogram",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L343",
   :line 343,
   :var-type "macro",
   :arglists ([chart x & options]),
   :doc
   "\nAdds a histogram to an existing histogram plot, returns the modified\nchart object.\n\nOptions:\n  :nbins (default 10) number of bins for histogram\n  :series-label (default x expression)\n\nExamples:\n\n  (use '(incanter core charts stats datasets))\n  (doto (histogram (sample-normal 1000)\n                   :legend true)\n        view\n        (add-histogram (sample-normal 1000 :sd 0.5)))\n\n\n  (with-data (get-dataset :iris)\n    (doto (histogram :Sepal.Length :legend true)\n      (add-histogram :Petal.Length)\n      view))\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-histogram"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-image",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L2820",
   :line 2820,
   :var-type "function",
   :arglists ([chart x y img & options]),
   :doc
   " Adds an image to the chart at the given coordinates.\n\nArguments:\n  chart -- the chart to add the polygon to.\n  x, y -- the coordinates to place the image\n  img -- a java.awt.Image object\n\n\nExamples:\n  (use '(incanter core charts latex))\n\n   (doto (function-plot sin -10 10)\n    (add-image 0 0 (latex \"\\\\frac{(a+b)^2} {(a-b)^2}\"))\n    view)",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-image"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-lines",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L584",
   :line 584,
   :var-type "macro",
   :arglists ([chart x y & options]),
   :doc
   " Plots lines on the given scatter or line plot of the (x,y) points.\nEquivalent to R's lines function, returns the modified chart object.\n\nOptions:\n  :series-label (default x expression)\n  :points (default false)\n  :auto-sort (default true) sort data by x\n\n\nExamples:\n\n  (use '(incanter core stats io datasets charts))\n  (def cars (to-matrix (get-dataset :cars)))\n  (def y (sel cars :cols 0))\n  (def x (sel cars :cols 1))\n  (def plot1 (scatter-plot x y :legend true))\n  (view plot1)\n\n  ;; add regression line to scatter plot\n  (def lm1 (linear-model y x))\n  (add-lines plot1 x (:fitted lm1))\n\n  ;; model the data without an intercept\n  (def lm2 (linear-model y x :intercept false))\n  (add-lines plot1 x (:fitted lm2))\n\n\n  ;; Clojure's doto macro can be used to build a chart\n  (doto (histogram (sample-normal 1000) :density true)\n        (add-lines (range -3 3 0.05) (pdf-normal (range -3 3 0.05)))\n        view)\n\n\n  (with-data (get-dataset :iris)\n      (doto (xy-plot :Sepal.Width :Sepal.Length :legend true)\n            (add-lines :Petal.Width :Petal.Length)\n            view))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-lines"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-parametric",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L725",
   :line 725,
   :var-type "macro",
   :arglists ([chart function min-range max-range & options]),
   :doc
   " Adds a xy-plot of the given parametric function to the given chart, returning\na modified version of the chart.\nFunction takes 1 argument t and returns point [x y].\n\nOptions:\n  :series-label (default function expression)\n  :step-size (default (/ (- max-range min-range) 500))\n\nSee also:\n  parametric-plot, view, save, add-function, add-points, add-lines\n\n\nExamples:\n\n  (use '(incanter core charts))\n\n  ;;; Plot square with circle inside.\n  (defn circle [t] [(cos t) (sin t)])\n  (doto (xy-plot [1 -1 -1 1 1] [1 1 -1 -1 1] :auto-sort false)\n        (add-parametric circle 0 (* 2 Math/PI))\n        (view))",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-parametric"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-pointer",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L2623",
   :line 2623,
   :var-type "function",
   :arglists ([chart x y & options]),
   :doc
   " Adds an arrow annotation to the given chart.\n\nArguments:\n  chart -- the chart to annotate\n  x, y -- the coordinate to add the annotation\n\n\nOptions:\n    :text -- (default \"\") text to include at the end of the arrow\n    :angle -- (default :nw) either a number indicating the angle of the arrow\n              or a keyword indicating a direction (:north :nw :west :sw :south\n              :se :east :ne)\n\n\nExamples:\n\n  (use '(incanter core charts))\n  (def x (range (* -2 Math/PI) (* 2 Math/PI) 0.01))\n  (def plot (xy-plot x (sin x)))\n  (view plot)\n  ;; annotate the plot\n  (doto plot\n    (add-pointer (- Math/PI) (sin (- Math/PI)) :text \"(-pi, (sin -pi))\")\n    (add-pointer Math/PI (sin Math/PI) :text \"(pi, (sin pi))\" :angle :ne)\n    (add-pointer (* 1/2 Math/PI) (sin (* 1/2 Math/PI)) :text \"(pi/2, (sin pi/2))\" :angle :south))\n\n  ;; try the different angle options\n  (add-pointer plot 0 0 :text \"north\" :angle :north)\n  (add-pointer plot 0 0 :text \"nw\" :angle :nw)\n  (add-pointer plot 0 0 :text \"ne\" :angle :ne)\n  (add-pointer plot 0 0 :text \"west\" :angle :west)\n  (add-pointer plot 0 0 :text \"east\" :angle :east)\n  (add-pointer plot 0 0 :text \"south\" :angle :south)\n  (add-pointer plot 0 0 :text \"sw\" :angle :sw)\n  (add-pointer plot 0 0 :text \"se\" :angle :se)",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-pointer"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-points",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L786",
   :line 786,
   :var-type "macro",
   :arglists ([chart x y & options]),
   :doc
   " Plots points on the given scatter-plot or xy-plot of the (x,y) points.\nEquivalent to R's lines function, returns the modified chart object.\n\nOptions:\n  :series-label (default x expression)\n\nExamples:\n\n  (use '(incanter core stats io datasets charts))\n  (def cars (to-matrix (get-dataset :cars)))\n  (def y (sel cars :cols 0))\n  (def x (sel cars :cols 1))\n\n  ;; add regression line to scatter plot\n  (def lm1 (linear-model y x))\n  ;; model the data without an intercept\n  (def lm2 (linear-model y x :intercept false))\n\n  (doto (xy-plot x (:fitted lm1) :legend true)\n        view\n        (add-points x y)\n        (add-lines x (:fitted lm2)))\n\n\n  (with-data (get-dataset :iris)\n    (doto (scatter-plot :Sepal.Length :Sepal.Width :data ($where {:Species \"setosa\"}))\n          (add-points :Sepal.Length :Sepal.Width :data ($where {:Species \"versicolor\"}))\n          (add-points :Sepal.Length :Sepal.Width :data ($where {:Species \"virginica\"}))\n          view))\n\n  ;; of course this chart can be achieved in a single line:\n  (view (scatter-plot :Sepal.Length :Sepal.Width :group-by :Species :data (get-dataset :iris)))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-points"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-polygon",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L2757",
   :line 2757,
   :var-type "function",
   :arglists ([chart coords & options]),
   :doc
   " Adds a polygon outline defined by a given coordinates. The last coordinate will\nclose with the first. If only two points are given, it will plot a line.\n\nArguments:\n  chart -- the chart to add the polygon to.\n  coords -- a list of coords (an n-by-2 matrix can also be used)\n\n\nExamples:\n  (use '(incanter core stats charts))\n  (def x (range -3 3 0.01))\n  (def plot (xy-plot x (pdf-normal x)))\n  (view plot)\n\n  ;; add polygon to the chart\n  (add-polygon plot [[-1.96 0] [1.96 0] [1.96 0.4] [-1.96 0.4]])\n  ;; the coordinates can also be passed in a matrix\n  ;; (def points (matrix [[-1.96 0] [1.96 0] [1.96 0.4] [-1.96 0.4]]))\n  ;; (add-polygon plot points)\n  ;; add a text annotation\n  (add-text plot -1.25 0.35 \"95% Conf Interval\")\n\n  ;; PCA chart example\n  (use '(incanter core stats charts datasets))\n  ;; load the iris dataset\n  (def iris (to-matrix (get-dataset :iris)))\n  ;; run the pca\n  (def pca (principal-components (sel iris :cols (range 4))))\n  ;; extract the first two principal components\n  (def pc1 (sel (:rotation pca) :cols 0))\n  (def pc2 (sel (:rotation pca) :cols 1))\n\n  ;; project the first four dimension of the iris data onto the first\n  ;; two principal components\n  (def x1 (mmult (sel iris :cols (range 4)) pc1))\n  (def x2 (mmult (sel iris :cols (range 4)) pc2))\n\n  ;; now plot the transformed data, coloring each species a different color\n  (def plot (scatter-plot x1 x2\n                          :group-by (sel iris :cols 4)\n                          :x-label \"PC1\" :y-label \"PC2\" :title \"Iris PCA\"))\n\n  (view plot)\n  ;; put box around the first group\n  (add-polygon plot [[-3.2 -6.3] [-2 -6.3] [-2 -3.78] [-3.2 -3.78]])\n  ;; add some text annotations\n  (add-text plot -2.5 -6.5 \"Setosa\")\n  (add-text plot -5 -5.5 \"Versicolor\")\n  (add-text plot -8 -5.5 \"Virginica\")",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-polygon"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-subtitle",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L3544",
   :line 3544,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "Adds a JFreeChart title object to a chart as a subtitle.\n\nExamples:\n  (use '(incanter core charts latex))\n\n  (doto (function-plot sin -10 10)\n    (add-subtitle \"subtitle\")\n    (add-subtitle (latex \" \\\\frac{(a+b)^2} {(a-b)^2}\"))\n    view)",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-subtitle"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "add-text",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L2712",
   :line 2712,
   :var-type "function",
   :arglists ([chart x y text & options]),
   :doc
   " Adds a text annotation centered at the given coordinates.\n\nArguments:\n  chart -- the chart to annotate\n  x, y -- the coordinates to center the text\n  text -- the text to add\n\n\nExamples:\n\n  ;; PCA chart example\n  (use '(incanter core stats charts datasets))\n  ;; load the iris dataset\n  (def iris (to-matrix (get-dataset :iris)))\n  ;; run the pca\n  (def pca (principal-components (sel iris :cols (range 4))))\n  ;; extract the first two principal components\n  (def pc1 (sel (:rotation pca) :cols 0))\n  (def pc2 (sel (:rotation pca) :cols 1))\n\n  ;; project the first four dimension of the iris data onto the first\n  ;; two principal components\n  (def x1 (mmult (sel iris :cols (range 4)) pc1))\n  (def x2 (mmult (sel iris :cols (range 4)) pc2))\n\n  ;; now plot the transformed data, coloring each species a different color\n  (def plot (scatter-plot x1 x2\n                          :group-by (sel iris :cols 4)\n                          :x-label \"PC1\" :y-label \"PC2\" :title \"Iris PCA\"))\n  (view plot)\n  ;; add some text annotations\n  (add-text plot -2.5 -6.5 \"Setosa\")\n  (add-text plot -5 -5.5 \"Versicolor\")\n  (add-text plot -8 -5.5 \"Virginica\")",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/add-text"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "area-chart",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L1828",
   :line 1828,
   :var-type "macro",
   :arglists ([categories values & options]),
   :doc
   " Returns a JFreeChart object representing an area-chart of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nArguments:\n  categories -- a sequence of categories\n  values -- a sequence of numeric values\n\nOptions:\n  :title (default '') main title\n  :x-label (default 'Categories')\n  :y-label (default 'Value')\n  :series-label\n  :legend (default false) prints legend\n  :vertical (default true) the orientation of the plot\n  :group-by (default nil) -- a vector of values used to group the values into\n                             series within each category.\n\n\nSee also:\n  view and save\n\nExamples:\n\n\n  (use '(incanter core stats charts datasets))\n\n  (with-data (get-dataset :co2)\n    (view (area-chart :Type :uptake\n                     :title \"CO2 Uptake\"\n                     :group-by :Treatment\n                     :x-label \"Grass Types\" :y-label \"Uptake\"\n                    :legend true)))\n\n\n  (def data (get-dataset :airline-passengers))\n  (view (area-chart :year :passengers :group-by :month :legend true :data data))\n\n  (with-data  (get-dataset :airline-passengers)\n    (view (area-chart :month :passengers :group-by :year :legend true)))\n\n\n  (def data (get-dataset :austres))\n  (view data)\n  (def plot (area-chart :year :population :group-by :quarter :legend true :data data))\n  (view plot)\n  (save plot \"/tmp/austres_plot.png\" :width 1000)\n  (view \"file:///tmp/austres_plot.png\")\n\n\n  (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))\n  (def years (mapcat identity (repeat 4 [2007 2008 2009])))\n  (def values (sample-uniform 12 :integers true :max 100))\n  (view (area-chart years values :group-by seasons :legend true))\n\n  (view (area-chart [\"a\" \"b\" \"c\"] [10 20 30]))\n  (view (area-chart [\"a\" \"a\" \"b\" \"b\" \"c\" \"c\" ] [10 20 30 10 40 20]\n                   :legend true\n                   :group-by [\"I\" \"II\" \"I\" \"II\" \"I\" \"II\"]))\n\n  ;; add a series label\n  (def plot (area-chart [\"a\" \"b\" \"c\"] [10 20 30] :legend true :series-label \"s1\"))\n  (view plot)\n  (add-categories plot [\"a\" \"b\" \"c\"] [5 25 40] :series-label \"s2\")\n\n  (view (area-chart (sample \"abcdefghij\" :size 10 :replacement true)\n                   (sample-uniform 10 :max 50) :legend true))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/area-chart"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "bar-chart",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L1688",
   :line 1688,
   :var-type "macro",
   :arglists ([categories values & options]),
   :doc
   " Returns a JFreeChart object representing a bar-chart of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nArguments:\n  categories -- a sequence of categories\n  values -- a sequence of numeric values\n\nOptions:\n  :title (default 'Histogram') main title\n  :x-label (default 'Categories')\n  :y-label (default 'Value')\n  :series-label\n  :legend (default false) prints legend\n  :vertical (default true) the orientation of the plot\n  :group-by (default nil) -- a vector of values used to group the values into\n                             series within each category.\n\n\nSee also:\n  view and save\n\nExamples:\n\n\n  (use '(incanter core stats charts datasets))\n\n  (with-data (get-dataset :co2)\n    (view (bar-chart :Type :uptake\n                     :title \"CO2 Uptake\"\n                     :group-by :Treatment\n                     :x-label \"Grass Types\" :y-label \"Uptake\"\n                    :legend true)))\n\n\n  (def data (get-dataset :airline-passengers))\n  (view (bar-chart :year :passengers :group-by :month :legend true :data data))\n\n  (with-data  (get-dataset :airline-passengers)\n    (view (bar-chart :month :passengers :group-by :year :legend true)))\n\n\n  (def data (get-dataset :austres))\n  (view data)\n  (def plot (bar-chart :year :population :group-by :quarter :legend true :data data))\n  (view plot)\n  (save plot \"/tmp/austres_plot.png\" :width 1000)\n  (view \"file:///tmp/austres_plot.png\")\n\n\n  (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))\n  (def years (mapcat identity (repeat 4 [2007 2008 2009])))\n  (def values (sample-uniform 12 :integers true :max 100))\n  (view (bar-chart years values :group-by seasons :legend true))\n\n  (view (bar-chart [\"a\" \"b\" \"c\"] [10 20 30]))\n  (view (bar-chart [\"a\" \"a\" \"b\" \"b\" \"c\" \"c\" ] [10 20 30 10 40 20]\n                   :legend true\n                   :group-by [\"I\" \"II\" \"I\" \"II\" \"I\" \"II\"]))\n\n  ;; add a series label\n  (def plot (bar-chart [\"a\" \"b\" \"c\"] [10 20 30] :legend true :series-label \"s1\"))\n  (view plot)\n  (add-categories plot [\"a\" \"b\" \"c\"] [5 25 40] :series-label \"s2\")\n\n  (view (bar-chart (sample \"abcdefghij\" :size 10 :replacement true)\n                   (sample-uniform 10 :max 50) :legend true))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/bar-chart"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "bland-altman-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L2929",
   :line 2929,
   :var-type "function",
   :arglists ([x1 x2 & options]),
   :doc
   "\n\nExamples:\n\n  (use '(incanter core datasets charts))\n  (def flow-meter (to-matrix (get-dataset :flow-meter)))\n  (def x1 (sel flow-meter :cols 1))\n  (def x2 (sel flow-meter :cols 3))\n  (view (bland-altman-plot x1 x2))\n\n  (with-data (get-dataset :flow-meter)\n    (view (bland-altman-plot \"Wright 1st PEFR\" \"Mini Wright 1st PEFR\")))\n\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Bland-Altman_plot\n  http://www-users.york.ac.uk/~mb55/meas/ba.htm",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/bland-altman-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "box-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L2307",
   :line 2307,
   :var-type "macro",
   :arglists ([x & options]),
   :doc
   " Returns a JFreeChart object representing a box-plot of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nOptions:\n  :title (default 'Histogram') main title\n  :x-label (default x expression)\n  :y-label (default 'Frequency')\n  :legend (default false) prints legend\n  :series-label (default x expression)\n  :group-by (default nil) -- a vector of values used to group the x values into series.\n\nSee also:\n  view and save\n\nExamples:\n\n  (use '(incanter core stats charts))\n  (def gamma-box-plot (box-plot (sample-gamma 1000 :shape 1 :rate 2)\n                        :title \"Gamma Boxplot\"\n                        :legend true))\n  (view gamma-box-plot)\n  (add-box-plot gamma-box-plot (sample-gamma 1000 :shape 2 :rate 2))\n  (add-box-plot gamma-box-plot (sample-gamma 1000 :shape 3 :rate 2))\n\n  ;; use the group-by options\n  (use '(incanter core stats datasets charts))\n  (with-data (get-dataset :iris)\n    (view (box-plot :Petal.Length :group-by :Species :legend true))\n    (view (box-plot :Petal.Width :group-by :Species :legend true))\n    (view (box-plot :Sepal.Length :group-by :Species :legend true))\n    (view (box-plot :Sepal.Width :group-by :Species :legend true)))\n\n  ;; see INCANTER_HOME/examples/probability_plots.clj for more examples of plots\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/box-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "clear-background",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L874",
   :line 874,
   :var-type "function",
   :arglists ([chart]),
   :doc
   " Sets the alpha level (transparency) of the plot's background to zero\nremoving the default grid, returns the modified chart object.\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/clear-background"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "dynamic-scatter-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L3212",
   :line 3212,
   :var-type "macro",
   :arglists ([[& slider-bindings] expression & options]),
   :doc
   " Returns an scatter-plot bound to sliders (which tend to appear behind the chart).\nSee the sliders macro for more information.\n\n\nExamples:\n\n(use '(incanter core stats charts))\n\n(let [x (range -3 3 0.1)]\n  (view (dynamic-scatter-plot [mean (range -3 3 0.1)\n                               sd (range 0.1 10 0.1)]\n          [x (pdf-normal x :mean mean :sd sd)]\n          :title \"Normal PDF Plot\")))\n\n\n (let [x (range -3 3 0.1)]\n   (view (dynamic-scatter-plot [mean (range -3 3 0.1)\n                                sd (range 0.1 10 0.1)]\n          (for [xi x] [xi (pdf-normal xi :mean mean :sd sd)])\n          :title \"Normal PDF Plot\")))",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/dynamic-scatter-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "dynamic-xy-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L3181",
   :line 3181,
   :var-type "macro",
   :arglists ([[& slider-bindings] expression & options]),
   :doc
   " Returns an xy-plot bound to sliders (which tend to appear behind the chart).\nSee the sliders macro for more information.\n\n\nExamples:\n\n(use '(incanter core stats charts))\n\n(let [x (range -3 3 0.1)]\n  (view (dynamic-xy-plot [mean (range -3 3 0.1)\n                          sd (range 0.1 10 0.1)]\n          [x (pdf-normal x :mean mean :sd sd)]\n          :title \"Normal PDF Plot\")))\n\n (let [x (range -3 3 0.1)]\n   (view (dynamic-xy-plot [mean (range -3 3 0.1)\n                           sd (range 0.1 10 0.1)]\n          (for [xi x] [xi (pdf-normal xi :mean mean :sd sd)])\n          :title \"Normal PDF Plot\")))",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/dynamic-xy-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "function-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L2389",
   :line 2389,
   :var-type "macro",
   :arglists ([function min-range max-range & options]),
   :doc
   " Returns a xy-plot object of the given function over the range indicated\nby the min-range and max-range arguments. Use the 'view' function to\ndisplay the chart, or the 'save' function to write it to a file.\n\nOptions:\n  :title (default 'Histogram') main title\n  :x-label (default x expression)\n  :y-label (default 'Frequency')\n  :legend (default false) prints legend\n  :series-label (default x expression)\n  :step-size (default (/ (- max-range min-range) 500))\n\nSee also:\n  view, save, add-points, add-lines\n\n\nExamples:\n\n  (use '(incanter core stats charts))\n\n  (view (function-plot sin (- Math/PI) Math/PI))\n  (view (function-plot pdf-normal -3 3))\n\n  (defn cubic [x] (+ (* x x x) (* 2 x x) (* 2 x) 3))\n  (view (function-plot cubic -10 10))",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/function-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "get-series",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L3010",
   :line 3010,
   :var-type "function",
   :arglists ([chart] [chart series-idx]),
   :doc "get-series",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/get-series"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "heat-map",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L2572",
   :line 2572,
   :var-type "macro",
   :arglists ([function x-min x-max y-min y-max & options]),
   :doc
   "\nExamples:\n  (use '(incanter core charts))\n  (defn f [x y] (sin (sqrt (plus (sq x) (sq y)))))\n  (view (heat-map f -10 10 -15 15))\n  (view (heat-map f -10 10 -10 10 :color? false))\n\n  (defn f2 [x y] (plus (sq x) (sq y)))\n  (view (heat-map f2 -10 10 -10 10))\n  (view (heat-map f2 -10 10 -10 10 :color? false))\n\n  (use 'incanter.stats)\n  (defn f3 [x y] (pdf-normal (sqrt (plus (sq x) (sq y)))))\n  (view (heat-map f3 -3 3 -3 3 :x-label \"x1\" :y-label \"x2\" :z-label \"pdf\"))\n  (view (heat-map f3 -3 3 -3 3 :color? false))\n\n  (defn f4 [x y] (minus (sq x) (sq y)))\n  (view (heat-map f4 -10 10 -10 10))\n  (view (heat-map f4 -10 10 -10 10 :color? false))\n\n\n  (use '(incanter core stats charts))\n  (let [data [[0 5 1 2]\n                [0 10 1.9 1]\n                [15 0 0.5 1.5]\n                [18 10 4.5 2.1]]\n        diffusion (fn [x y]\n                    (sum (map #(pdf-normal (euclidean-distance [x y] (take 2 %))\n                                           :mean (nth % 2) :sd (last %))\n                              data)))]\n    (view (heat-map diffusion -5 20 -5 20)))",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/heat-map"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "histogram",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L1440",
   :line 1440,
   :var-type "macro",
   :arglists ([x & options]),
   :doc
   " Returns a JFreeChart object representing the histogram of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nOptions:\n  :nbins (default 10) number of bins\n  :density (default false) if false, plots frequency, otherwise density\n  :title (default 'Histogram') main title\n  :x-label (default x expression)\n  :y-label (default 'Frequency')\n  :legend (default false) prints legend\n  :series-label (default x expression)\n\n\nSee also:\n  view, save, add-histogram\n\nExamples:\n\n  (use '(incanter core charts stats))\n  (view (histogram (sample-normal 1000)))\n\n  # plot a density histogram\n  (def hist (histogram (sample-normal 1000) :density true))\n  (view hist)\n\n  # add a normal density line to the plot\n  (def x (range -4 4 0.01))\n  (add-lines hist x (pdf-normal x))\n\n  # plot some gamma data\n  (def gam-hist (histogram (sample-gamma 1000) :density true :nbins 30))\n  (view gam-hist)\n  (def x (range 0 8 0.01))\n  (add-lines gam-hist x (pdf-gamma x))\n\n  (use 'incanter.datasets)\n  (def iris (get-dataset :iris))\n  (view (histogram :Sepal.Width :data iris))\n\n  (with-data (get-dataset :iris)\n    (view (histogram :Petal.Length)))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/histogram"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "line-chart",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L1547",
   :line 1547,
   :var-type "macro",
   :arglists ([categories values & options]),
   :doc
   " Returns a JFreeChart object representing a line-chart of the given values and categories.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nArguments:\n  categories -- a sequence of categories\n  values -- a sequence of numeric values\n\nOptions:\n  :title (default 'Histogram') main title\n  :x-label (default 'Categories')\n  :y-label (default 'Value')\n  :legend (default false) prints legend\n  :series-label\n  :group-by (default nil) -- a vector of values used to group the values into\n                             series within each category.\n  :gradient? (default false) -- use gradient on bars\n\n\nSee also:\n  view and save\n\nExamples:\n\n  (use '(incanter core stats charts datasets))\n\n  (def data (get-dataset :airline-passengers))\n  (def years (sel data :cols 0))\n  (def months (sel data :cols 2))\n  (def passengers (sel data :cols 1))\n  (view (line-chart years passengers :group-by months :legend true))\n  (view (line-chart months passengers :group-by years :legend true))\n\n\n  (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))\n  (def years (mapcat identity (repeat 4 [2007 2008 2009])))\n  (def x (sample-uniform 12 :integers true :max 100))\n  (view (line-chart years x :group-by seasons :legend true))\n\n  (view (line-chart [\"a\" \"b\" \"c\" \"d\" \"e\" \"f\"] [10 20 30 10 40 20]))\n\n  (view (line-chart (sample \"abcdefghij\" :size 10 :replacement true)\n                       (sample-uniform 10 :max 50) :legend true))\n\n  ;; add a series label\n  (def plot (line-chart [\"a\" \"b\" \"c\"] [10 20 30] :legend true :series-label \"s1\"))\n  (view plot)\n  (add-categories plot [\"a\" \"b\" \"c\"] [5 25 40] :series-label \"s2\")\n\n\n  (view (line-chart :year :passengers :group-by :month :legend true :data data))\n\n  (view (line-chart :month :passengers :group-by :year :legend true :data data))\n\n  (with-data data\n    (view (line-chart :month :passengers :group-by :year :legend true)))\n\n  (with-data (->> ($rollup :sum :passengers :year (get-dataset :airline-passengers))\n                  ($order :year :asc))\n    (view (line-chart :year :passengers)))\n\n  (with-data (->> ($rollup :sum :passengers :month (get-dataset :airline-passengers))\n                  ($order :passengers :asc))\n    (view (line-chart :month :passengers)))\n\n\n  (with-data ($rollup :sum :passengers :month (get-dataset :airline-passengers))\n    (view (line-chart :month :passengers)))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/line-chart"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "parametric-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L2459",
   :line 2459,
   :var-type "macro",
   :arglists ([function min-range max-range & options]),
   :doc
   " Returns a xy-plot object of the given parametric function over the range indicated\nby the min-range and max-range arguments. Use the 'view' function to\ndisplay the chart, or the 'save' function to write it to a file.\nFunction must take 1 argument - parameter t and return point [x y].\n\nOptions:\n  :title (default '') main title\n  :x-label (default 'min-x < x < max-x')\n  :y-label (default 'min-y < y < max-y')\n  :legend (default false) prints legend\n  :series-label (default function expression)\n  :step-size (default (/ (- max-range min-range) 500))\n\nSee also:\n  view, save, add-parametric, function-plot\n\n\nExamples:\n\n  (use '(incanter core charts))\n\n  (defn circle [t] [(cos t) (sin t)])\n  (view (parametric-plot circle (- Math/PI) Math/PI))\n\n  (defn spiral [t] [(* t (cos t)) (* t (sin t))])\n  (view (parametric-plot spiral 0 (* 6 Math/PI)))",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/parametric-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "pie-chart",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L2213",
   :line 2213,
   :var-type "macro",
   :arglists ([categories values & options]),
   :doc
   " Returns a JFreeChart object representing a pie-chart of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nArguments:\n  categories -- a sequence of categories\n  values -- a sequence of numeric values\n\nOptions:\n  :title (default 'Histogram') main title\n  :legend (default false) prints legend\n\n\nSee also:\n  view and save\n\nExamples:\n\n\n  (use '(incanter core stats charts datasets))\n\n  (view (pie-chart [\"a\" \"b\" \"c\"] [10 20 30]))\n\n   (view (pie-chart (sample \"abcdefghij\" :size 10 :replacement true)\n                   (sample-uniform 10 :max 50) :legend true))\n\n\n   (with-data (->> (get-dataset :hair-eye-color)\n                   ($rollup :sum :count [:hair :eye]))\n     (view $data)\n     (view (pie-chart :hair :count :title \"Hair Color\"))\n     (view (pie-chart :eye :count :title \"Eye Color\")))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/pie-chart"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "qq-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L2890",
   :line 2890,
   :var-type "function",
   :arglists ([x & options]),
   :doc
   "\nReturns a QQ-Plot object. Use the 'view' function to display it.\n\nReferences:\n  http://en.wikipedia.org/wiki/QQ_plot\n\nExamples:\n\n  (use '(incanter core stats charts datasets))\n  (view (qq-plot (sample-normal 100)))\n  (view (qq-plot (sample-exp 100)))\n  (view (qq-plot (sample-gamma 100)))\n\n  (with-data (get-dataset :iris)\n    (view (qq-plot :Sepal.Length)))",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/qq-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "scatter-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L1328",
   :line 1328,
   :var-type "macro",
   :arglists ([] [x y & options]),
   :doc
   " Returns a JFreeChart object representing a scatter-plot of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nOptions:\n  :title (default 'Histogram') main title\n  :x-label (default x expression)\n  :y-label (default 'Frequency')\n  :legend (default false) prints legend\n  :series-label (default x expression)\n  :group-by (default nil) -- a vector of values used to group the x and y values into series.\n  :density? (default false) -- chart will represent density instead of frequency.\n  :nbins (default 10) -- number of bins (i.e. bars)\n  :gradient? (default false) -- use gradient on bars\n\nSee also:\n  view, save, add-points, add-lines\n\nExamples:\n\n  (use '(incanter core stats charts datasets))\n  ;; create some data\n  (def mvn-samp (sample-mvn 1000 :mean [7 5] :sigma (matrix [[2 1.5] [1.5 3]])))\n\n  ;; create scatter-plot of points\n  (def mvn-plot (scatter-plot (sel mvn-samp :cols 0) (sel mvn-samp :cols 1)))\n  (view mvn-plot)\n\n  ;; add regression line to scatter plot\n  (def x (sel mvn-samp :cols 0))\n  (def y (sel mvn-samp :cols 1))\n  (def lm (linear-model y x))\n  (add-lines mvn-plot x (:fitted lm))\n\n  ;; use :group-by option\n  (use '(incanter core stats datasets charts))\n  ;; load the :iris dataset\n  (def iris (get-dataset :iris))\n  ;; plot the first two columns grouped by the fifth column\n  (view (scatter-plot ($ :Sepal.Width iris) ($ :Sepal.Length iris) :group-by ($ :Species iris)))\n\n  (view (scatter-plot :Sepal.Length :Sepal.Width :data (get-dataset :iris)))\n\n  (view (scatter-plot :Sepal.Length :Sepal.Width :group-by :Species :data (get-dataset :iris)))\n\n  (with-data (get-dataset :iris)\n     (view (scatter-plot :Sepal.Length :Sepal.Width)))\n\n  (with-data (get-dataset :iris)\n     (view (scatter-plot :Sepal.Length :Sepal.Width :group-by :Species)))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/scatter-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-alpha",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L846",
   :line 846,
   :var-type "function",
   :arglists ([chart alpha]),
   :doc
   " Sets the alpha level (transparency) of the plot's foreground\nreturns the modified chart object.\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-alpha"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-background-alpha",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L860",
   :line 860,
   :var-type "function",
   :arglists ([chart alpha]),
   :doc
   " Sets the alpha level (transparency) of the plot's background\nreturns the modified chart object.\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-background-alpha"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-background-default",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L63",
   :line 63,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "\n\nExamples:\n  (use '(incanter core stats charts datasets))\n\n  (doto (histogram (sample-normal 1000) :title (str :Test-Tittle))\n    set-theme-bw\n    view)\n\n\n  (doto (histogram (sample-normal 1000))\n    set-background-default\n    (add-histogram (sample-normal 1000 :mean 1))\n    view)\n\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    view)\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    (set-stroke :dash 5)\n    (add-points (plus ($ :speed (get-dataset :cars)) 5) (plus ($ :dist (get-dataset :cars)) 10))\n    view)\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-background-default\n    (set-stroke :dash 5)\n    (add-function sin 0 25)\n    view)\n\n\n  (doto (xy-plot :speed :dist :data (get-dataset :cars) :legend true)\n    set-background-default\n    view)\n\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-background-default\n    view)\n\n\n  (doto (box-plot (sample-gamma 1000 :shape 1 :rate 2)\n                  :legend true)\n    view set-background-default\n    (add-box-plot (sample-gamma 1000 :shape 2 :rate 2))\n    (add-box-plot (sample-gamma 1000 :shape 3 :rate 2)))\n\n\n  (doto (bar-chart [:a :b :c] [10 20 30] :legend true)\n    view\n    set-background-default\n    (add-categories [:a :b :c] [5 25 40]))\n\n\n  (doto (line-chart [:a :b :c] [10 20 30] :legend true)\n    view\n    set-background-default\n    (add-categories [:a :b :c] [5 25 40]))\n\n  ;; time-series-plot\n  (def epoch 0)\n  (defn num-years-to-milliseconds [x]\n    (* 365 24 60 60 1000 x))\n  (def dates (map num-years-to-milliseconds (range 100)))\n  (def chart1 (time-series-plot dates (range 100)))\n  (def cw1 (view chart1))\n  (add-lines chart1 dates (mult 1/2 (range 100)))\n\n  (def chart2 (time-series-plot (take 10 dates) (mult 1/2 (range 10))))\n  (def cw2 (view chart2))",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-background-default"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-stroke",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L3247",
   :line 3247,
   :var-type "function",
   :arglists ([chart & options]),
   :doc
   "\nExamples:\n  (use '(incanter core charts))\n\n  (doto (line-chart [:a :b :c :d] [10 20 5 35])\n    (set-stroke :width 4 :dash 5)\n    view)\n\n  (doto (line-chart [:a :b :c :d] [10 20 5 35])\n    (add-categories [:a :b :c :d] [20 5 30 15])\n    (set-stroke :width 4 :dash 5)\n    (set-stroke :series 1 :width 2 :dash 10)\n    view)\n\n\n  (doto (function-plot sin -10 10 :step-size 0.1)\n    (set-stroke :width 3 :dash 5)\n    view)\n\n  (doto (line-chart [:a :b :c :d] [10 20 5 35])\n    (add-categories [:a :b :c :d] [20 5 30 15])\n    (set-stroke :series 0 :width 4 :dash 5)\n    (set-stroke :series 1 :width 4 :dash 5 :cap java.awt.BasicStroke/CAP_SQUARE))",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-stroke"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-stroke-color",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L3292",
   :line 3292,
   :var-type "function",
   :arglists ([chart color & options]),
   :doc
   "\nExamples:\n  (use '(incanter core charts))\n\n  (doto (line-chart [:a :b :c :d] [10 20 5 35])\n    (set-stroke :width 4 :dash 5)\n    (set-stroke-color java.awt.Color/blue)\n    view)\n\n  (doto (xy-plot [1 2 3] [4 5 6])\n    (add-points [1 2 3] [4.1 5.1 6.1])\n    (set-stroke-color java.awt.Color/black :series 0)\n    (set-stroke-color java.awt.Color/red :series 1))\n\n  (doto (function-plot sin -10 10 :step-size 0.1)\n    (set-stroke :width 3 :dash 5)\n    (set-stroke-color java.awt.Color/gray)\n    view)",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-stroke-color"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-theme",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L216",
   :line 216,
   :var-type "function",
   :arglists ([chart theme]),
   :doc
   "  Changes the chart theme.\n\nArguments:\n  chart -- an Incanter/JFreeChart object\n  theme -- either a keyword indicating one of the built-in themes, or a JFreeChart ChartTheme object.\n\nBuilt-in Themes:\n  :default\n  :dark\n\nExamples:\n\n  (use '(incanter core charts))\n  (def chart (function-plot sin -4 4))\n  (view chart)\n  ;; change the theme of chart to :dark\n  (set-theme chart :dark)\n  ;; change it back to the default\n  (set-theme chart :default)\n\n\n  ;; Example using JFreeTheme\n  (use '(incanter core stats charts datasets))\n\n  (import '(org.jfree.chart StandardChartTheme)\n          '(org.jfree.chart.plot DefaultDrawingSupplier)\n          '(java.awt Color))\n\n  (def all-red-theme\n    (doto\n      (StandardChartTheme/createJFreeTheme)\n      (.setDrawingSupplier\n      (proxy [DefaultDrawingSupplier] []\n        (getNextPaint [] Color/red)))))\n\n  (def data (get-dataset :airline-passengers))\n\n  (def chart (bar-chart :month :passengers :group-by :year :legend true :data data))\n\n  (doto chart\n    ;; has no effect\n    (set-theme all-red-theme)\n    view)\n\n\n References:\n    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/StandardChartTheme.html\n    http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/ChartTheme.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-theme"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-theme-bw",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L146",
   :line 146,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "\n\nExamples:\n  (use '(incanter core stats charts datasets))\n\n  (doto (histogram (sample-normal 1000))\n    set-theme-bw\n    view)\n\n\n  (doto (histogram (sample-normal 1000))\n    set-theme-bw\n    (add-histogram (sample-normal 1000 :mean 1))\n    view)\n\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    view)\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    (set-stroke :dash 5)\n    (add-points (plus ($ :speed (get-dataset :cars)) 5) (plus ($ :dist (get-dataset :cars)) 10))\n    view)\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    (set-stroke :dash 5)\n    (add-function sin 0 25)\n    view)\n\n\n  (doto (xy-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    view)\n\n\n  (doto (scatter-plot :speed :dist :data (get-dataset :cars))\n    set-theme-bw\n    (add-lines :speed :dist :data (get-dataset :cars))\n    view)\n\n\n  (doto (box-plot (sample-gamma 1000 :shape 1 :rate 2)\n                  :legend true)\n    view\n    (add-box-plot (sample-gamma 1000 :shape 2 :rate 2))\n    (add-box-plot (sample-gamma 1000 :shape 3 :rate 2))\n    set-theme-bw)\n\n\n  (doto (bar-chart [:a :b :c] [10 20 30] :legend true)\n    view\n    set-theme-bw\n    (add-categories [:a :b :c] [5 25 40]))\n\n\n  (doto (line-chart [:a :b :c] [10 20 30] :legend true)\n    view\n    set-theme-bw\n    (add-categories [:a :b :c] [5 25 40]))",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-theme-bw"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-title",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L889",
   :line 889,
   :var-type "function",
   :arglists ([chart title]),
   :doc
   " Sets the main title of the plot, returns the modified chart object.\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-title"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-x-label",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L902",
   :line 902,
   :var-type "function",
   :arglists ([chart label]),
   :doc
   " Sets the label of the x-axis, returns the modified chart object.\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-x-label"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-x-range",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L929",
   :line 929,
   :var-type "function",
   :arglists ([chart lower upper]),
   :doc
   " Sets the range of the x-axis on the given chart.\n\nExamples:\n\n  (use '(incanter core charts datasets))\n\n  (def chart (xy-plot :speed :dist :data (get-dataset :cars)))\n  (view chart)\n  (set-x-range chart 10 20)",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-x-range"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-y-label",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L915",
   :line 915,
   :var-type "function",
   :arglists ([chart label]),
   :doc
   " Sets the label of the y-axis, returns the modified chart object.\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-y-label"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "set-y-range",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L949",
   :line 949,
   :var-type "function",
   :arglists ([chart lower upper]),
   :doc
   " Sets the range of the y-axis on the given chart.\n\nExamples:\n\n  (use '(incanter core charts datasets))\n\n  (def chart (xy-plot :speed :dist :data (get-dataset :cars)))\n  (view chart)\n  (set-y-range chart 10 60)",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/set-y-range"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "slider",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L3042",
   :line 3042,
   :var-type "function",
   :arglists
   ([updater-fn slider-values]
    [updater-fn slider-values slider-label]),
   :doc
   "\n\nExamples:\n  (use '(incanter core stats charts))\n\n  (def pdf-chart (function-plot pdf-normal -3 3))\n  (view pdf-chart)\n  (add-function pdf-chart pdf-normal -3 3)\n\n  (let [x (range -3 3 0.1)]\n    (slider #(set-data pdf-chart [x (pdf-normal x :sd %)]) (range 0.1 10 0.1)))\n\n  (let [x (range -3 3 0.1)]\n    (slider #(set-data pdf-chart [x (pdf-normal x :sd %)]) (range 0.1 10 0.1) \"sd\"))",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/slider"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "sliders",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L3119",
   :line 3119,
   :var-type "macro",
   :arglists ([[& slider-bindings] body]),
   :doc
   " Creates one slider control for each of the given sequence bindings.\nEach slider calls the given expression when manipulated.\n\n\nExamples:\n(use '(incanter core stats charts))\n\n;; manipulate a normal pdf\n(let [x (range -3 3 0.1)]\n  (def pdf-chart (xy-plot))\n  (view pdf-chart)\n  (sliders [mean (range -3 3 0.1)\n            stdev (range 0.1 10 0.1)]\n    (set-data pdf-chart [x (pdf-normal x :mean mean :sd stdev)])))\n\n\n\n;; manipulate a gamma pdf\n(let [x (range 0 20 0.1)]\n  (def pdf-chart (xy-plot))\n  (view pdf-chart)\n  (sliders [rate (range 0.1 10 0.1)\n            shape (range 0.1 10 0.1)]\n    (set-data pdf-chart [x (pdf-gamma x :rate rate :shape shape)])))\n\n\n\n;; find the start values of a non-linear model function\n(use '(incanter core charts datasets))\n;; create model function used in the following data-sorcery post:\n;; http://data-sorcery.org/2009/06/06/fitting-non-linear-models/\n\n(defn f [theta x]\n  (let [[b1 b2 b3] theta]\n    (div (exp (mult (minus b1) x)) (plus b2 (mult b3 x)))))\n\n\n(with-data (get-dataset :chwirut)\n  (view $data)\n  (def chart (scatter-plot ($ :x) ($ :y)))\n  (view chart)\n  (add-lines chart ($ :x) (f [0 0.01 0] ($ :x)))\n\n  ;; manipulate the model line to find some good start values.\n  ;; give the index of the line data (i.e. 1) to set-data.\n  (let [x ($ :x)]\n    (sliders [b1 (range 0 2 0.01)\n              b2 (range 0.01 2 0.01)\n              b3 (range 0 2 0.01)]\n      (set-data chart [x (f [b1 b2 b3] x)] 1))))",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/sliders"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "sliders*",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L3088",
   :line 3088,
   :var-type "function",
   :arglists
   ([f [& slider-values]] [f [& slider-values] [& slider-labels]]),
   :doc
   "sliders*\n\nExamples:\n(use '(incanter core stats charts))\n\n(let [x (range -3 3 0.1)]\n  (do\n    (def pdf-chart (xy-plot x (pdf-normal x :mean -3 :sd 0.1)))\n    (view pdf-chart)\n    (sliders* #(set-data pdf-chart [x (pdf-normal x :mean %1 :sd %2)])\n             [(range -3 3 0.1) (range 0.1 10 0.1)]\n             [\"mean\" \"sd\"])))",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/sliders*"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "stacked-area-chart",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L1966",
   :line 1966,
   :var-type "macro",
   :arglists ([categories values & options]),
   :doc
   " Returns a JFreeChart object representing an stacked-area-chart of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nArguments:\n  categories -- a sequence of categories\n  values -- a sequence of numeric values\n\nOptions:\n  :title (default '') main title\n  :x-label (default 'Categories')\n  :y-label (default 'Value')\n  :series-label\n  :legend (default false) prints legend\n  :vertical (default true) the orientation of the plot\n  :group-by (default nil) -- a vector of values used to group the values into\n                             series within each category.\n\n\nSee also:\n  view and save\n\nExamples:\n\n\n  (use '(incanter core stats charts datasets))\n\n  (with-data (get-dataset :co2)\n    (view (stacked-area-chart :Type :uptake\n                     :title \"CO2 Uptake\"\n                     :group-by :Treatment\n                     :x-label \"Grass Types\" :y-label \"Uptake\"\n                    :legend true)))\n\n\n  (def data (get-dataset :airline-passengers))\n  (view (stacked-area-chart :year :passengers :group-by :month :legend true :data data))\n\n  (with-data  (get-dataset :airline-passengers)\n    (view (stacked-area-chart :month :passengers :group-by :year :legend true)))\n\n\n  (def data (get-dataset :austres))\n  (view data)\n  (def plot (stacked-area-chart :year :population :group-by :quarter :legend true :data data))\n  (view plot)\n  (save plot \"/tmp/austres_plot.png\" :width 1000)\n  (view \"file:///tmp/austres_plot.png\")\n\n\n  (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))\n  (def years (mapcat identity (repeat 4 [2007 2008 2009])))\n  (def values (sample-uniform 12 :integers true :max 100))\n  (view (stacked-area-chart years values :group-by seasons :legend true))\n\n  (view (stacked-area-chart [\"a\" \"a\" \"b\" \"b\" \"c\" \"c\" ] [10 20 30 10 40 20]\n                   :legend true\n                   :group-by [\"I\" \"II\" \"I\" \"II\" \"I\" \"II\"]))\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/stacked-area-chart"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "stacked-bar-chart",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L2094",
   :line 2094,
   :var-type "macro",
   :arglists ([categories values & options]),
   :doc
   " Returns a JFreeChart object representing an stacked-bar-chart of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nArguments:\n  categories -- a sequence of categories\n  values -- a sequence of numeric values\n\nOptions:\n  :title (default '') main title\n  :x-label (default 'Categories')\n  :y-label (default 'Value')\n  :series-label\n  :legend (default false) prints legend\n  :vertical (default true) the orientation of the plot\n  :group-by (default nil) -- a vector of values used to group the values into\n                             series within each category.\n\n\nSee also:\n  view and save\n\nExamples:\n\n\n  (use '(incanter core stats charts datasets))\n\n  (with-data (get-dataset :co2)\n    (view (stacked-bar-chart :Type :uptake\n                     :title \"CO2 Uptake\"\n                     :group-by :Treatment\n                     :x-label \"Grass Types\" :y-label \"Uptake\"\n                    :legend true)))\n\n\n  (def data (get-dataset :airline-passengers))\n  (view (stacked-bar-chart :year :passengers :group-by :month :legend true :data data))\n\n  (with-data  (get-dataset :airline-passengers)\n    (view (stacked-bar-chart :month :passengers :group-by :year :legend true)))\n\n\n  (def data (get-dataset :austres))\n  (view data)\n  (def plot (stacked-bar-chart :year :population :group-by :quarter :legend true :data data))\n  (view plot)\n  (save plot \"/tmp/austres_plot.png\" :width 1000)\n  (view \"file:///tmp/austres_plot.png\")\n\n\n  (def seasons (mapcat identity (repeat 3 [\"winter\" \"spring\" \"summer\" \"fall\"])))\n  (def years (mapcat identity (repeat 4 [2007 2008 2009])))\n  (def values (sample-uniform 12 :integers true :max 100))\n  (view (stacked-bar-chart years values :group-by seasons :legend true))\n\n  (view (stacked-bar-chart [\"a\" \"b\" \"c\"] [10 20 30]))\n  (view (stacked-bar-chart [\"a\" \"a\" \"b\" \"b\" \"c\" \"c\" ] [10 20 30 10 40 20]\n                   :legend true\n                   :group-by [\"I\" \"II\" \"I\" \"II\" \"I\" \"II\"]))\n\n  ;; add a series label\n  (def plot (stacked-bar-chart [\"a\" \"b\" \"c\"] [10 20 30] :legend true :series-label \"s1\"))\n  (view plot)\n  (add-categories plot [\"a\" \"b\" \"c\"] [5 25 40] :series-label \"s2\")\n\n  (view (stacked-bar-chart (sample \"abcdefghij\" :size 10 :replacement true)\n                   (sample-uniform 10 :max 50) :legend true))\n\n\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/stacked-bar-chart"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "time-series-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L1215",
   :line 1215,
   :var-type "macro",
   :arglists ([x y & options]),
   :doc
   " Returns a JFreeChart object representing a time series plot of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file. Sequence passed in for the x axis should be\nnumber of milliseconds from the epoch (1 January 1970).\n\nOptions:\n  :data (default nil) If the :data option is provided a dataset,\n                      column names can be used instead of sequences\n                      of data as arguments to xy-plot.\n  :title (default 'Time Series Plot') main title\n  :x-label (default x expression)\n  :y-label (default y expression)\n  :legend (default false) prints legend\n  :series-label (default x expression)\n  :group-by (default nil) -- a vector of values used to group the x and y values into series.\n\nSee also:\n  view, save, add-points, add-lines\n\nExamples:\n\n  (use '(incanter core stats charts chrono))\n\n  ;; plot numbers against years starting with 1900\n  (def dates (map #(-> (joda-date (+ 1900 %) 1 1 12 0 0 0 (time-zone 0))\n                       .getMillis)\n                  (range 100)))\n  (def y (range 100))\n  (view (time-series-plot dates y\n                          :x-label \"Year\"))\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/time-series-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "trace-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L2849",
   :line 2849,
   :var-type "function",
   :arglists ([x & options]),
   :doc
   " Returns a trace-plot object, use the 'view' function to display it.\n\nExamples:\n  (use '(incanter core datasets stats bayes charts))\n  (def ols-data (to-matrix (get-dataset :survey)))\n  (def x (sel ols-data (range 0 2313) (range 1 10)))\n  (def y (sel ols-data (range 0 2313) 10))\n  (def sample-params (sample-model-params 5000 (linear-model y x :intercept false)))\n  (view (trace-plot (:var sample-params)))\n\n  (view (trace-plot (sel (:coefs sample-params) :cols 0)))",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/trace-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj",
   :name "xy-plot",
   :file "modules/incanter-charts/src/incanter/charts.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/0c2b437335388bd2868d7efb1fa1b3336031f18a/modules/incanter-charts/src/incanter/charts.clj#L1056",
   :line 1056,
   :var-type "macro",
   :arglists ([] [x y & options]),
   :doc
   " Returns a JFreeChart object representing a xy-plot of the given data.\nUse the 'view' function to display the chart, or the 'save' function\nto write it to a file.\n\nOptions:\n  :data (default nil) If the :data option is provided a dataset,\n                      column names can be used instead of sequences\n                      of data as arguments to xy-plot.\n  :title (default 'XY Plot') main title\n  :x-label (default x expression)\n  :y-label (default 'Frequency')\n  :legend (default false) prints legend\n  :series-label (default x expression)\n  :group-by (default nil) -- a vector of values used to group the x and y values into series.\n  :points (default false) includes point-markers\n  :auto-sort (default true) sort data by x\n\nSee also:\n  view, save, add-points, add-lines\n\nExamples:\n\n  (use '(incanter core stats charts))\n\n  ;; plot the cosine function\n  (def x (range -1 5 0.01))\n  (def y (cos (mult 2 Math/PI x)))\n  (view (xy-plot x y))\n\n  ;; plot gamma pdf with different parameters\n  (def x2 (range 0 20 0.1))\n  (def gamma-plot (xy-plot x2 (pdf-gamma x2 :shape 1 :rate 2)\n                             :legend true\n                             :title \"Gamma PDF\"\n                             :y-label \"Density\"))\n  (view gamma-plot)\n  (add-lines gamma-plot x2 (pdf-gamma x2 :shape 2 :rate 2))\n  (add-lines gamma-plot x2 (pdf-gamma x2 :shape 3 :rate 2))\n  (add-lines gamma-plot x2 (pdf-gamma x2 :shape 5 :rate 1))\n  (add-lines gamma-plot x2 (pdf-gamma x2 :shape 9 :rate 0.5))\n\n  ;; use :group-by option\n  (use '(incanter core charts datasets))\n\n  (with-data (get-dataset :chick-weight)\n    (view (xy-plot :Time :weight :group-by :Chick)))\n\n\n  ;; see INCANTER_HOME/examples/probability_plots.clj for more examples of plots\n\nReferences:\n  http://www.jfree.org/jfreechart/api/javadoc/\n  http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/JFreeChart.html",
   :namespace "incanter.charts",
   :wiki-url
   "http://incanter.github.com/incanter//charts-api.html#incanter.charts/xy-plot"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "$",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1509",
   :line 1509,
   :var-type "function",
   :arglists ([cols] [arg1 arg2] [rows cols data]),
   :doc
   "An alias to (sel (second args) :cols (first args)). If given only a single argument,\nit will use the $data binding for the first argument, which is set with\nthe with-data macro.\n\nExamples:\n  (use '(incanter core stats charts datasets))\n\n  (def cars (get-dataset :cars))\n  ($ :speed cars)\n\n\n  (with-data cars\n    (def lm (linear-model ($ :dist) ($ :speed)))\n    (doto (scatter-plot ($ :speed) ($ :dist))\n      view\n      (add-lines ($ :speed) (:fitted lm))))\n\n  ;; standardize speed and dist and append the standardized variables to the original dataset\n  (with-data (get-dataset :cars)\n    (view (conj-cols $data\n                     (sweep (sweep ($ :speed)) :stat sd :fun div)\n                     (sweep (sweep ($ :dist)) :stat sd :fun div))))\n\n  (with-data (get-dataset :iris)\n    (view $data)\n    (view ($ [:Sepal.Length :Sepal.Width :Species]))\n    (view ($ [:not :Petal.Width :Petal.Length]))\n    (view ($ 0 [:not :Petal.Width :Petal.Length])))\n\n\n   (use 'incanter.core)\n   (def mat (matrix (range 9) 3))\n   (view mat)\n   ($ 2 2 mat)\n   ($ [0 2] 2 mat)\n   ($ :all 1 mat)\n   ($ 1 mat)\n   ($ [:not 1] mat)\n   ($ 0 :all mat)\n   ($ [0 2] [0 2] mat)\n   ($ [:not 1] [:not 1] mat)\n   ($ [:not 1] :all mat)\n   ($ [0 2] [:not 1] mat)\n   ($ [0 2] [:not 1 2] mat)\n   ($ [0 2] [:not (range 2)] mat)\n   ($ [:not (range 2)] [0 2] mat)\n\n   (with-data mat\n     ($ 0 0))\n   (with-data mat\n     ($ [0 2] 2 mat))\n   (with-data mat\n     ($ :all 1))\n   (with-data mat\n     ($ [0 2] [0 2]))\n   (with-data mat\n     ($ [:not 1] :all))\n   (with-data mat\n     ($ [0 2] [:not 1]))\n\n\n   (use 'incanter.datasets)\n   (view (get-dataset :cars))\n   ($ (range 5) 0 (get-dataset :cars))\n   ($ (range 5) :all (get-dataset :cars))\n   ($ :all (range 2) (get-dataset :cars))\n\n   ($ (range 5) :dist (get-dataset :cars))\n   ($ [:not (range 5)] 0 (get-dataset :cars))\n   ($ [:not 0 1 2 3 4] 0 (get-dataset :cars))\n   (with-data (get-dataset :cars)\n     ($ 0 :dist))\n\n   (with-data (get-dataset :hair-eye-color)\n     (view $data)\n     (view ($ [:not :gender])))",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/$"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "$=",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2666",
   :line 2666,
   :var-type "macro",
   :arglists ([& equation]),
   :doc
   "Formula macro translates from infix to prefix\n\n\nExamples:\n\n (use 'incanter.core)\n ($= 7 + 8)\n ($= [1 2 3] + [4 5 6])\n ($= [1 2 3] + (sin [4 5 6]))\n ($= [1 2 3] <*> (trans [1 2 3]))\n ($= [1 2 3] * [1 2 3])\n ($= [1 2 3] <x> [1 2 3])\n ($= 9 * 8 ** 3)\n ($= (sin Math/PI) * 10)\n\n ($= 10 + 20 * (4 - 5) / 6)\n\n ($= 20 * (4 - 5) / 6)\n\n (let [x 10\n       y -5]\n   ($= x + y / -10))\n\n ($= 3 ** 3)\n\n ($= [1 2 3] * [1 2 3])\n ($= [1 2 3] / (sq [1 2 3]) + [5 6 7])\n\n ($= (sqrt 5 * 5 + 3 * 3))\n ($= (sq [1 2 3] + [1 2 3]))\n ($= ((5 + 4) * 5))\n ($= ((5 + 4 * (3 - 4)) / (5 + 8) * 6))\n ($= [1 2 3] + 5)\n ($= (matrix [[1 2] [4 5]]) + 6)\n ($= (trans [[1 2] [4 5]]) + 6)\n\n ($= (trans [[1 2] [4 5]]) <*> (matrix [[1 2] [4 5]]))\n\n\n (use '(incanter core charts))\n (defn f [x] ($= x ** 2 + 3 * x + 5))\n (f 5)\n (view (function-plot f -10 10))\n (view (function-plot #($= % ** 2 + 3 * % + 5) -10 10))\n (view (function-plot (fn [x] ($= x ** 2 + 3 * x + 5)) -10 10))\n (let [x (range -10 10 0.1)]\n   (view (xy-plot x ($= x ** 3 - 5 * x ** 2 + 3 * x + 5))))\n\n ($= (5 + 7))\n ($= (trans [1 2 3 4]) <*> [1 2 3 4])\n ($= [1 2 3 4] <*> (trans [1 2 3 4]))\n\n ($= [1 2 3 4] <*> (trans [1 2 3 4]))\n ($= [1 2 3 4] <x> (trans [1 2 3 4]))\n\n\n ;; kronecker product example\n ($= (matrix [[1 2] [3 4] [5 6]]) <x> 4)\n ($= (matrix [[1 2] [3 4] [5 6]]) <x> (matrix [[1 2] [3 4]]))\n ($= [1 2 3 4] <x> 4)\n\n ($= 3 > (5 * 2/7))\n\n (use '(incanter core datasets charts))\n (with-data (get-dataset :cars)\n   (doto (scatter-plot :speed :dist :data ($where ($fn [speed dist] ($= dist / speed < 2))))\n     (add-points :speed :dist :data ($where ($fn [speed dist] ($= dist / speed >= 2))))\n     (add-lines ($ :speed) ($= 2 * ($ :speed)))\n     view))",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/$="}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "$data",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L55",
   :dynamic true,
   :line 55,
   :var-type "var",
   :arglists nil,
   :doc
   "This variable is bound to a dataset when the with-data macro is used.\nfunctions like $ and $where can use $data as a default argument.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/$data"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "$fn",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1771",
   :line 1771,
   :var-type "macro",
   :arglists ([col-bindings body]),
   :doc
   " A simple macro used as syntactic sugar for defining predicate functions to be used\nin the $where function. The supplied arguments should be column names of a dataset.\nThis macro performs map destructuring on the arguments.\n\nFor instance,\n($fn [speed] (< speed 10)) => (fn [{:keys [speed]}] (< speed 10))\n\nExamples:\n  (use '(incanter core datasets))\n  (view ($where ($fn [speed dist] (or (> speed 20) (< dist 10))) (get-dataset :cars)))\n\n  (view ($where ($fn [speed dist] (< (/ dist speed) 2)) (get-dataset :cars)))\n\n  (use '(incanter core datasets charts))\n  (with-data (get-dataset :cars)\n    (doto (scatter-plot :speed :dist :data ($where ($fn [speed dist] (< (/ dist speed) 2))))\n      (add-points :speed :dist :data ($where ($fn [speed dist] (>= (/ dist speed) 2))))\n      (add-lines ($ :speed) (mult 2 ($ :speed)))\n      view))\n\n\n  (let [passed? ($fn [speed dist] (< (/ dist speed) 2))\n        failed? (complement passed?)]\n    (with-data (get-dataset :cars)\n      (doto (scatter-plot :speed :dist :data ($where passed?))\n        (add-points :speed :dist :data ($where failed?))\n        (add-lines ($ :speed) (mult 2 ($ :speed)))\n        view)))\n\n\n  (use '(incanter core stats charts))\n  (let [above-sine? ($fn [col-0 col-1] (> col-1 (sin col-0)))\n        below-sine? (complement above-sine?)]\n    (with-data (conj-cols (sample-uniform 1000 :min -5 :max 5)\n                          (sample-uniform 1000 :min -1 :max 1))\n      (doto (function-plot sin -5 5)\n        (add-points :col-0 :col-1 :data ($where above-sine?))\n        (add-points :col-0 :col-1 :data ($where below-sine?))\n        view)))\n\n\n  (view ($where ($fn [] (> (rand) 0.9)) (get-dataset :cars)))\n\n  (view ($where ($fn [Species] ($in Species #{\"virginica\" \"setosa\"})) (get-dataset :iris)))",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/$fn"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "$group-by",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1823",
   :line 1823,
   :var-type "function",
   :arglists ([cols] [cols data]),
   :doc
   "Returns a map of datasets keyed by a query-map corresponding the group.\n\nExamples:\n\n  (use '(incanter core datasets))\n  ($group-by :Species (get-dataset :iris))\n\n  ($group-by [:hair :eye] (get-dataset :hair-eye-color))\n\n  (with-data (get-dataset :hair-eye-color)\n    ($group-by [:hair :eye]))",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/$group-by"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "$join",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1918",
   :line 1918,
   :var-type "function",
   :arglists
   ([[left-keys right-keys] left-data]
    [[left-keys right-keys] left-data right-data]),
   :doc
   "\nReturns a dataset created by right-joining two datasets.\nThe join is based on one or more columns in the datasets.\nIf used within the body of the with-data macro, the second\ndataset is optional, defaulting the the dataset bound to $data.\n\n\nExamples:\n  (use '(incanter core stats datasets charts))\n  (def iris (get-dataset :iris))\n\n\n\n  (def lookup (dataset [:species :species-key] [[\"setosa\" :setosa]\n                                                [\"versicolor\" :versicolor]\n                                                [\"virginica\" :virginica]]))\n  (view ($join [:species :Species] lookup iris))\n\n (def hair-eye-color (get-dataset :hair-eye-color))\n (def lookup2 (conj-cols ($ [:hair :eye :gender] hair-eye-color) (range (nrow hair-eye-color))))\n (view ($join [[:col-0 :col-1 :col-2] [:hair :eye :gender]] lookup2 hair-eye-color))\n\n (with-data hair-eye-color\n   (view ($join [[:col-0 :col-1 :col-2] [:hair :eye :gender]] lookup2)))\n\n\n (def lookup3 (dataset [:gender :hair :hair-gender] [[\"male\" \"black\" :male-black]\n                                                     [\"male\" \"brown\" :male-brown]\n                                                     [\"male\" \"red\" :male-red]\n                                                     [\"male\" \"blond\" :male-blond]\n                                                     [\"female\" \"black\" :female-black]\n                                                     [\"female\" \"brown\" :female-brown]\n                                                     [\"female\" \"red\" :female-red]\n                                                     [\"female\" \"blond\" :female-blond]]))\n\n (view ($join [[:gender :hair] [:gender :hair]] lookup3 hair-eye-color))\n\n (use 'incanter.charts)\n (with-data (->>  (get-dataset :hair-eye-color)\n                  ($where {:hair {:in #{\"brown\" \"blond\"}}})\n                  ($rollup :sum :count [:hair :gender])\n                  ($join [[:gender :hair] [:gender :hair]] lookup3)\n                  ($order :count :desc))\n     (view $data)\n     (view (bar-chart :hair :count :group-by :gender :legend true)))",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/$join"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "$map",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1882",
   :line 1882,
   :var-type "function",
   :arglists ([fun col-keys data] [fun col-keys]),
   :doc
   "This function returns a sequence resulting from mapping the given function over\n  the value(s) for the given column key(s) of the given dataset.\n  Like other '$*' functions, it will use $data as the default dataset\n  if none is provided, where $data is set using the with-data macro.\n\nExamples:\n\n  (use '(incanter core datasets))\n  (def cars (get-dataset :cars))\n\n  ($map (fn [s] (/ s)) :speed cars)\n  ($map (fn [s d] (/ s d)) [:speed :dist] cars)\n\n  (map (fn [s d] (/ s d)) ($ :speed cars) ($ :speed cars))\n\n  (with-data (get-dataset :cars)\n    (view ($map (fn [s] (/ s)) :speed))\n    (view ($map (fn [s d] (/ s d)) [:speed :dist])))\n\n  ;; calculate the speed to dist ratio and append as new column to dataset\n  (with-data (get-dataset :cars)\n    (conj-cols $data ($map (fn [s d] (/ s d)) [:speed :dist])))",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/$map"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "$order",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1742",
   :line 1742,
   :var-type "function",
   :arglists ([cols order] [cols order data]),
   :doc
   " Sorts a dataset by the given columns in either ascending (:asc)\n  or descending (:desc) order. If used within a the body of\n  the with-data macro, the data argument is optional, defaulting\n  to the dataset bound to the variable $data.\n\n  Examples:\n\n  (use '(incanter core charts datasets))\n  (def iris (get-datset :iris))\n  (view ($order :Sepal.Length :asc iris))\n  (view ($order [:Sepal.Width :Sepal.Length] :desc iris))\n\n  (with-data (get-dataset :iris)\n    (view ($order [:Petal.Length :Sepal.Length] :desc)))\n\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/$order"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "$rollup",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1653",
   :line 1653,
   :var-type "function",
   :arglists
   ([summary-fun col-name group-by]
    [summary-fun col-name group-by data]),
   :doc
   "Returns a dataset that uses the given summary function (or function identifier keyword)\nto rollup the given column based on a set of group-by columns. The summary function\nshould accept a single sequence of values and return a single summary value. Alternatively,\nyou can provide a keyword identifier of a set of built-in functions including:\n\n  :max -- the maximum value of the data in each group\n  :min -- the minimum value of the data in each group\n  :sum -- the sum of the data in each group\n  :count -- the number of elements in each group\n  :mean -- the mean of the data in each group\n\n\n Like the other '$' dataset functions, $rollup will use the dataset bound to $data\n (see the with-data macro) if a dataset is not provided as an argument.\n\n Examples:\n\n   (use '(incanter core datasets))\n\n   (def iris (get-dataset :iris))\n   ($rollup :mean :Sepal.Length :Species iris)\n   ($rollup :count :Sepal.Length :Species iris)\n   ($rollup :max :Sepal.Length :Species iris)\n   ($rollup :min :Sepal.Length :Species iris)\n\n   ;; The following is an example using a custom function, but since all the\n   ;; iris measurements are positive, the built-in mean function could have\n   ;; been used instead.\n\n   (use 'incanter.stats)\n   ($rollup #(mean (abs %)) :Sepal.Width :Species iris)\n\n   ($rollup sd :Sepal.Length :Species iris)\n   ($rollup variance :Sepal.Length :Species iris)\n   ($rollup median :Sepal.Length :Species iris)\n\n   (def hair-eye-color (get-dataset :hair-eye-color))\n   ($rollup :mean :count [:hair :eye] hair-eye-color)\n\n   (use 'incanter.charts)\n   (with-data ($rollup :mean :Sepal.Length :Species iris)\n     (view (bar-chart :Species :Sepal.Length)))\n\n    ;; the following examples use the built-in data set called hair-eye-color.\n\n    (with-data ($rollup :mean :count [:hair :eye] hair-eye-color)\n      (view (bar-chart :hair :count :group-by :eye :legend true)))\n\n    (with-data (->>  (get-dataset :hair-eye-color)\n                     ($where {:hair {:in #{\"brown\" \"blond\"}}})\n                     ($rollup :sum :count [:hair :eye])\n                     ($order :count :desc))\n      (view $data)\n      (view (bar-chart :hair :count :group-by :eye :legend true)))",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/$rollup"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "$where",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1621",
   :line 1621,
   :var-type "function",
   :arglists ([query-map] [query-map data]),
   :doc
   "An alias to (query-dataset (second args) (first args)). If given only a single argument,\nit will use the $data binding for the first argument, which is set with\nthe with-data macro.\n\nExamples:\n\n  (use '(incanter core datasets))\n\n  (def cars (get-dataset :cars))\n  ($where {:speed 10} cars)\n\n  ;; use the with-data macro and the one arg version of $where\n  (with-data cars\n    (view ($where {:speed {:$gt -10 :$lt 10}}))\n    (view ($where {:dist {:$in #{10 12 16}}}))\n    (view ($where {:dist {:$nin #{10 12 16}}})))\n\n  ;; create a dataset where :speed greater than 10 or less than -10\n  (with-data (get-dataset :cars)\n    (view (-> ($where {:speed {:$gt 20}})\n                    (conj-rows ($where {:speed {:$lt 10}})))))",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/$where"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "->Dataset",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L60",
   :line 60,
   :var-type "function",
   :arglists ([column-names rows]),
   :doc "Positional factory function for class incanter.core.Dataset.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/->Dataset"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "abs",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L534",
   :line 534,
   :var-type "function",
   :arglists ([A]),
   :doc
   "Returns the absolute value of the elements in the given matrix, sequence or number.\nEquivalent to R's abs function.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/abs"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "acos",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L558",
   :line 558,
   :var-type "function",
   :arglists ([A]),
   :doc
   "Returns the arc cosine of the elements in the given matrix, sequence or number.\nEquivalent to R's acos function.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/acos"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "asin",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L546",
   :line 546,
   :var-type "function",
   :arglists ([A]),
   :doc
   "Returns the arc sine of the elements in the given matrix, sequence or number.\nEquivalent to R's asin function.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/asin"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "atan",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L570",
   :line 570,
   :var-type "function",
   :arglists ([A]),
   :doc
   "Returns the arc tangent of the elements in the given matrix, sequence or number.\nEquivalent to R's atan function.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/atan"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "atan2",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L491",
   :line 491,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "Returns the atan2 of the elements in the given matrices, sequences or numbers.\nEquivalent to R's atan2 function.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/atan2"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "beta",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2251",
   :line 2251,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nEquivalent to R's beta function.\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Gamma.html",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/beta"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "bind-columns",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L357",
   :line 357,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "   Returns the matrix resulting from concatenating the given matrices\nand/or sequences by their columns. Equivalent to R's cbind.\n\nExamples:\n  (def A (matrix [[1 2 3]\n                 [4 5 6]\n                 [7 8 9]]))\n\n  (def B (matrix [10 11 12]))\n\n  (bind-columns A B)\n\n  (bind-columns [1 2 3 4] [5 6 7 8])",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/bind-columns"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "bind-rows",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L318",
   :line 318,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "   Returns the matrix resulting from concatenating the given matrices\nand/or sequences by their rows. Equivalent to R's rbind.\n\nExamples:\n  (def A (matrix [[1 2 3]\n                 [4 5 6]\n                 [7 8 9]]))\n\n  (def B (matrix [[10 11 12]\n                  [13 14 15]]))\n\n  (bind-rows A B)\n\n  (bind-rows [1 2 3 4] [5 6 7 8])",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/bind-rows"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "block-diag",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2756",
   :line 2756,
   :var-type "function",
   :arglists ([blocks]),
   :doc "Blocks should be a sequence of matrices.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/block-diag"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "block-matrix",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2761",
   :line 2761,
   :var-type "function",
   :arglists ([blocks]),
   :doc
   "Blocks should be a nested sequence of matrices. Each element of the sequence should be a block row.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/block-matrix"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "categorical-var",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2137",
   :line 2137,
   :var-type "function",
   :arglists
   ([& {:keys [data ordered? labels levels], :or {ordered? false}}]),
   :doc
   " Returns a categorical variable based on the values in the given collection.\nEquivalent to R's factor function.\n\nOptions:\n  :data (default nil) factors will be extracted from the given data.\n  :ordered? (default false) indicates that the variable is ordinal.\n  :labels (default (sort (into #{} data)))\n  :levels (range (count labels))\n\nExamples:\n  (categorical-var :data [:a :a :c :b :a :c :c])\n  (categorical-var :labels [:a :b :c])\n  (categorical-var :labels [:a :b :c] :levels [10 20 30])\n  (categorical-var :levels [1 2 3])",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/categorical-var"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "choose",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L593",
   :line 593,
   :var-type "function",
   :arglists ([n k]),
   :doc
   "\nReturns number of k-combinations (each of size k) from a set S with\nn elements (size n), which is the binomial coefficient (also known\nas the 'choose function') [wikipedia]\n      choose = n!/(k!(n - k)!)\n\nEquivalent to R's choose function.\n\nExamples:\n  (choose 25 6) ; => 2,598,960\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/math/tdouble/DoubleArithmetic.html\n  http://en.wikipedia.org/wiki/Combination",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/choose"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "col-names",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1435",
   :line 1435,
   :var-type "function",
   :arglists ([data] [data colnames]),
   :doc
   "If given a dataset, it returns its column names. If given a dataset and a sequence\nof column names, it returns a dataset with the given column names.\n\nExamples:\n  (use '(incanter core datasets))\n  (def data (get-dataset :cars))\n  (col-names data)\n\n  (def renamed-data (col-names data [:x1 :x2]))\n  (col-names renamed-data)\n\n\n ",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/col-names"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "condition",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1005",
   :line 1005,
   :var-type "function",
   :arglists ([mat]),
   :doc
   " Returns the two norm condition number, which is max(S) / min(S), where S is the diagonal matrix of singular values from an SVD decomposition.\n\n\nExamples:\n\n(use 'incanter.core)\n(def foo (matrix (range 9) 3))\n(condition foo)\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Condition_number\n  http://incanter.org/docs/parallelcolt/api/cern/colt/matrix/tdouble/algo/decomposition/DoubleSingularValueDecompositionDC.html",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/condition"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "conj-cols",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1455",
   :line 1455,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "Returns a dataset created by merging the given datasets and/or collections.\nThere must be the same number of rows in each dataset and/or\n collections.  Column names may be changed in order to prevent\n naming conflicts in the conjed dataset.\n\n Examples:\n   (use '(incanter core datasets))\n   (def cars (get-dataset :cars))\n   (def x (sel cars :cols 0))\n   (view (conj-cols cars cars))\n   (view (conj-cols cars x))\n   (view (conj-cols (range (nrow cars)) cars))\n   (view (conj-cols (range 10) (range 10)))\n   (view (conj-cols {:a 1 :b 2} {:c 1 :d 2}))",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/conj-cols"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "conj-rows",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1484",
   :line 1484,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "Returns a dataset created by combining the rows of the given datasets and/or collections.\n\nExamples:\n\n  (use '(incanter core datasets))\n  (def cars (get-dataset :cars))\n  (view (conj-rows (to-dataset (range 5)) (to-dataset (range 5 10))))\n  (view (conj-rows cars cars))\n  (view (conj-rows [[1 2] [3 4]] [[5 6] [7 8]]))\n  (view (conj-rows [{:a 1 :b 2} {:a 3 :b 4}] [[5 6] [7 8]]))\n  (view (conj-rows (to-dataset [{:a 1 :b 2} {:a 3 :b 4}]) [[5 6] [7 8]]))\n  (conj-rows (range 5) (range 5 10))",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/conj-rows"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "copy",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L645",
   :line 645,
   :var-type "function",
   :arglists ([mat]),
   :doc "Returns a copy of the given matrix.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/copy"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "cos",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L552",
   :line 552,
   :var-type "function",
   :arglists ([A]),
   :doc
   "Returns the cosine of the elements in the given matrix, sequence or number.\nEquivalent to R's cos function.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/cos"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "cumulative-sum",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L817",
   :line 817,
   :var-type "function",
   :arglists ([coll]),
   :doc
   " Returns a sequence of cumulative sum for the given collection. For instance\n  The first value equals the first value of the argument, the second value is\n  the sum of the first two arguments, the third is the sum of the first three\n  arguments, etc.\n\n  Examples:\n    (use 'incanter.core)\n    (cumulative-sum (range 100))\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/cumulative-sum"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "data-table",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2503",
   :line 2503,
   :var-type "function",
   :arglists ([data]),
   :doc "Creates a javax.swing.JTable given an Incanter dataset.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/data-table"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "dataset",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1150",
   :line 1150,
   :var-type "function",
   :arglists ([column-names & data]),
   :doc
   " Returns a map of type incanter.core.dataset constructed from the given column-names and\ndata. The data is either a sequence of sequences or a sequence of hash-maps.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/dataset"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "dataset?",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L110",
   :line 110,
   :var-type "function",
   :arglists ([obj]),
   :doc " Determines if obj is of type incanter.core.Dataset.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/dataset?"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "decomp-cholesky",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L845",
   :line 845,
   :var-type "function",
   :arglists ([mat]),
   :doc
   " Returns the Cholesky decomposition of the given matrix. Equivalent to R's\nchol function.\n\nReturns:\n  a matrix of the triangular factor (note: the result from\n  cern.colt.matrix.linalg.DenseDoubleCholeskyDecomposition is transposed so\n  that it matches the result return from R's chol function.\n\n\n\nExamples:\n\n(use '(incanter core stats charts datasets))\n;; load the iris dataset\n(def iris (to-matrix (get-dataset :iris)))\n;; take the Cholesky decomposition of the correlation matrix of the iris data.\n(decomp-cholesky (correlation iris))\n\n\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/colt/matrix/tdouble/algo/decomposition/DoubleCholeskyDecomposition.html\n  http://en.wikipedia.org/wiki/Cholesky_decomposition",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/decomp-cholesky"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "decomp-eigenvalue",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L918",
   :line 918,
   :var-type "function",
   :arglists ([mat]),
   :doc
   " Returns the Eigenvalue Decomposition of the given matrix. Equivalent to R's eig function.\n\nReturns:\n  a map containing:\n    :values -- vector of eigenvalues\n    :vectors -- the matrix of eigenvectors\n\n\nExamples:\n\n(use 'incanter.core)\n(def foo (matrix (range 9) 3))\n(decomp-eigenvalue foo)\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Eigenvalue_decomposition\n  http://incanter.org/docs/parallelcolt/api/cern/colt/matrix/tdouble/algo/decomposition/DoubleEigenvalueDecomposition.html",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/decomp-eigenvalue"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "decomp-lu",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L944",
   :line 944,
   :var-type "function",
   :arglists ([mat]),
   :doc
   " Returns the LU decomposition of the given matrix.\n\n\nExamples:\n\n(use 'incanter.core)\n(def foo (matrix (range 9) 3))\n(decomp-lu foo)\n\n\nReturns:\n  a map containing:\n    :L -- the lower triangular factor\n    :U -- the upper triangular factor\n\nReferences:\n  http://en.wikipedia.org/wiki/LU_decomposition\n  http://incanter.org/docs/parallelcolt/api/cern/colt/matrix/tdouble/algo/decomposition/DoubleLUDecomposition.html",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/decomp-lu"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "decomp-qr",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L970",
   :line 970,
   :var-type "function",
   :arglists ([mat & {:keys [type], :or {type :full}}]),
   :doc
   " Returns the QR decomposition of the given matrix. Equivalent to R's qr function.\n\nOptional parameters:\n  :type -- one of :full, :compact.  default is :full\n    if :full, returns the full QR decomposition\n    if :compact, returns the compact (economy) QR decomposition\n\nReturns:\n  a map containing:\n    :Q -- orthogonal factors\n    :R -- the upper triangular factors\n\nExamples:\n\n(use 'incanter.core)\n(def foo (matrix (range 9) 3))\n(decomp-qr foo)\n(decomp-qr foo :type :full)\n(decomp-qr foo :type :compact)\n\nReferences:\n  http://en.wikipedia.org/wiki/QR_decomposition\n  http://incanter.org/docs/parallelcolt/api/cern/colt/matrix/tdouble/algo/decomposition/DenseDoubleQRDecomposition.html",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/decomp-qr"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "decomp-svd",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L875",
   :line 875,
   :var-type "function",
   :arglists ([mat & {:keys [type], :or {type :full}}]),
   :doc
   " Returns the Singular Value Decomposition (SVD) of the given matrix. Equivalent to\nR's svd function.\n\nOptional parameters:\n  :type -- one of :full, :compact, or :values.  default is :full\n    if :full, returns the full SVD\n    if :compact, returns the compact SVD\n    if :values, only the singular values are calculated\n\nReturns:\n  a map containing:\n    :S -- the diagonal matrix of singular values S (the diagonal in vector form)\n    :U -- the left singular vectors U\n    :V -- the right singular vectors V\n\nExamples:\n\n(use 'incanter.core)\n(def foo (matrix (range 9) 3))\n(decomp-foo foo)\n(decomp-foo foo :type :full)\n(decomp-foo foo :type :compact)\n(decomp-foo foo :type :values)\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Singular_value_decomposition\n  http://incanter.org/docs/parallelcolt/api/cern/colt/matrix/tdouble/algo/decomposition/DoubleSingularValueDecompositionDC.html",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/decomp-svd"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "deshape",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1994",
   :line 1994,
   :var-type "function",
   :arglists
   ([& {:keys [data remove-na group-by merge], :or {remove-na true}}]),
   :doc
   " Returns a dataset where the columns identified by :merge are collapsed into\ntwo columns called :variable and :value. The values in these columns are grouped\nby the columns identified by :group-by.\n\nExamples:\n\n  (use '(incanter core charts datasets))\n  (with-data (->> (deshape :merge [:Ahmadinejad :Rezai :Karrubi :Mousavi]\n                            :group-by :Region\n                            :data (get-dataset :iran-election))\n                  ($order :value :desc))\n    (view $data)\n    (view (bar-chart :variable :value :group-by :Region :legend true))\n\n    (view (bar-chart :Region :value :group-by :variable\n                     :legend true :vertical false))\n\n    (view (bar-chart :Region :value :legend true :vertical false\n                     :data ($order :value :desc ($rollup :sum :value :Region)))))\n\n\n\n    (def data (to-dataset [{:subject \"John Smith\" :time 1 :age 33 :weight 90 :height 1.87}\n                           {:subject \"Mary Smith\" :time 1 :height 1.54}]))\n    (view data)\n    (view (deshape :group-by [:subject :time] :merge [:age :weight :height] :data data))\n    (view (deshape :merge [:age :weight :height] :data data))\n    (view (deshape :group-by [:subject :time] :data data))\n\n    (view (deshape :merge [:age :weight :height] :remove-na false :data data))",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/deshape"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "det",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L730",
   :line 730,
   :var-type "function",
   :arglists ([mat]),
   :doc
   " Returns the determinant of the given matrix. Equivalent\nto R's det function.\n\nReferences:\n  http://en.wikipedia.org/wiki/LU_decomposition\n  http://incanter.org/docs/parallelcolt/api/cern/colt/matrix/tdouble/algo/decomposition/DoubleLUDecomposition.html",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/det"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "diag",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L153",
   :line 153,
   :var-type "function",
   :arglists ([m]),
   :doc
   "   If given a matrix, diag returns a sequence of its diagonal elements.\nIf given a sequence, it returns a matrix with the sequence's elements\non its diagonal. Equivalent to R's diag function.\n\nExamples:\n  (diag [1 2 3 4])\n\n  (def A (matrix [[1 2 3]\n                  [4 5 6]\n                  [7 8 9]]))\n  (diag A)",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/diag"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "diagonal-blocks",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2776",
   :line 2776,
   :var-type "function",
   :arglists ([matrix partitions]),
   :doc "Partitions should be a sequence of [start,size] pairs.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/diagonal-blocks"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "dim",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L136",
   :line 136,
   :var-type "function",
   :arglists ([mat]),
   :doc
   " Returns a vector with the number of rows and columns of the given matrix. ",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/dim"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "div",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L461",
   :line 461,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "   Performs element-by-element division on multiple matrices, sequences\nand/or numbers. Equivalent to R's / operator.\n\nExamples:\n\n  (def A (matrix [[1 2 3]\n                  [4 5 6]\n                  [7 8 9]]))\n  (div A A A)\n  (div A 2)\n  (div 2 A)\n  (div [1 2 3] [1 2 3])\n  (div [1 2 3] 2)\n  (div 2 [1 2 3])\n\n  (div [1 2 3]) ; returns [1 1/2 13]",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/div"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "exp",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L528",
   :line 528,
   :var-type "function",
   :arglists ([A]),
   :doc
   "Returns the exponential of the elements in the given matrix, sequence or number.\nEquivalent to R's exp function.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/exp"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "factorial",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L576",
   :line 576,
   :var-type "function",
   :arglists ([k]),
   :doc
   "\nReturns the factorial of k (k must be a positive integer). Equivalent to R's\nfactorial function.\n\nExamples:\n  (factorial 6)\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/math/tdouble/DoubleArithmetic.html\n  http://en.wikipedia.org/wiki/Factorial",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/factorial"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "gamma",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2241",
   :line 2241,
   :var-type "function",
   :arglists ([x]),
   :doc
   "\nEquivalent to R's gamma function.\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Gamma.html",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/gamma"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "get-categories",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2061",
   :line 2061,
   :var-type "function",
   :arglists ([cols data]),
   :doc
   "\nGiven a dataset and one or more column keys, returns the set of categories for them.\n\nExamples:\n\n  (use '(incanter core datasets))\n  (get-categories :eye (get-dataset :hair-eye-color))\n  (get-categories [:eye :hair] (get-dataset :hair-eye-color))",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/get-categories"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "grid-apply",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2640",
   :line 2640,
   :var-type "function",
   :arglists ([f x-min x-max y-min y-max]),
   :doc
   " Applies the given function f, that accepts two arguments, to a grid\ndefined by rectangle bounded x-min, y-min, x-max, y-max and returns a\nsequence of three sequences representing the cartesian product of x and y\nand z calculated by applying f to the combinations of x and y.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/grid-apply"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "group-on",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1085",
   :line 1085,
   :var-type "function",
   :arglists ([mat on-cols & {:keys [cols except-cols]}]),
   :doc
   " Groups the given matrix by the values in the columns indicated by the\n'on-cols' argument, returning a sequence of matrices. The returned\nmatrices are sorted by the value of the group column ONLY when there\nis only a single (non-vector) on-col argument.\n\nExamples:\n\n  (use '(incanter core datasets))\n  (def plant-growth (to-matrix (get-dataset :plant-growth)))\n  (group-on plant-growth 1)\n  ;; only return the first column\n  (group-on plant-growth 1 :cols 0)\n  ;; don't return the second column\n  (group-on plant-growth 1 :except-cols 1)\n\n  (def plant-growth-dummies (to-matrix (get-dataset :plant-growth) :dummies true))\n  (group-on plant-growth-dummies [1 2])\n  ;; return only the first column\n  (group-on plant-growth-dummies [1 2] :cols 0)\n  ;; don't return the last two columns\n  (group-on plant-growth-dummies [1 2] :except-cols [1 2])\n\n  ;; plot the plant groups\n  (use 'incanter.charts)\n  ;; can use destructuring if you know the number of groups\n  ;; groups are sorted only if the group is based on a single column value\n  (let [[ctrl trt1 trt2] (group-on plant-growth 1 :cols 0)]\n    (doto (box-plot ctrl)\n          (add-box-plot trt1)\n          (add-box-plot trt2)\n          view))",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/group-on"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "half-vectorize",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L772",
   :line 772,
   :var-type "function",
   :arglists ([mat]),
   :doc
   " Returns the half-vectorization (i.e. vech) of the given matrix.\n  The half-vectorization, vech(A), of a symmetric nxn matrix A\n  is the n(n+1)/2 x 1 column vector obtained by vectorizing only\n  the upper triangular part of A.\n\n  For instance:\n    (= (half-vectorize (matrix [[a b] [b d]])) (matrix [a b d]))\n\n  Examples:\n    (def A (matrix [[1 2] [2 4]]))\n    (half-vectorize A)\n\n  References:\n    http://en.wikipedia.org/wiki/Vectorization_(mathematics)\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/half-vectorize"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "head",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1614",
   :line 1614,
   :var-type "function",
   :arglists ([len mat] [mat]),
   :doc
   "Returns the head of the dataset. 10 or full dataset by default.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/head"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "identity-matrix",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L143",
   :line 143,
   :var-type "function",
   :arglists ([n]),
   :doc
   "   Returns an n-by-n identity matrix.\n\nExamples:\n  (identity-matrix 4)",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/identity-matrix"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "incomplete-beta",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2261",
   :line 2261,
   :var-type "function",
   :arglists ([x a b]),
   :doc
   "\nReturns the non-regularized incomplete beta value.\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Gamma.html",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/incomplete-beta"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "kronecker",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L678",
   :line 678,
   :var-type "function",
   :arglists ([& args]),
   :doc
   " Returns the Kronecker product of the given arguments.\n\nExamples:\n\n  (def x (matrix (range 6) 2))\n  (def y (matrix (range 4) 2))\n  (kronecker 4 x)\n  (kronecker x 4)\n  (kronecker x y)",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/kronecker"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "length",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1064",
   :line 1064,
   :var-type "function",
   :arglists ([coll]),
   :doc
   " A version of count that works on collections, matrices, and numbers.\n  The length of a number is one, the length of a collection is its count\n  and the length of a matrix is the number of elements it contains (nrow*ncol).\n  Equivalent to R's length function.\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/length"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "log",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L510",
   :line 510,
   :var-type "function",
   :arglists ([A]),
   :doc
   "Returns the natural log of the elements in the given matrix, sequence or number.\nEquivalent to R's log function.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/log"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "log10",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L522",
   :line 522,
   :var-type "function",
   :arglists ([A]),
   :doc
   "Returns the log base 10 of the elements in the given matrix, sequence or number.\nEquivalent to R's log10 function.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/log10"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "log2",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L516",
   :line 516,
   :var-type "function",
   :arglists ([A]),
   :doc
   "Returns the log base 2 of the elements in the given matrix, sequence or number.\nEquivalent to R's log2 function.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/log2"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "make-unique",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1416",
   :line 1416,
   :var-type "function",
   :arglists ([coll] [coll seen]),
   :doc
   "Take a sequence of keywords and make them unique by possibly\naltering later ones.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/make-unique"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "map->Dataset",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L60",
   :line 60,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.core.Dataset, taking a map of keywords to field values.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/map->Dataset"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "matrix",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L63",
   :line 63,
   :var-type "function",
   :arglists ([data] [data ncol] [init-val rows cols]),
   :doc
   "\nReturns an instance of an incanter.Matrix, which is an extension of\ncern.colt.matrix.tdouble.impl.DenseColDoubleMatrix2D that implements the Clojure\ninterface clojure.lang.ISeq. Therefore Clojure sequence operations can\nbe applied to matrices. A matrix consists of a sequence of rows, where\neach row is a one-dimensional row matrix. One-dimensional matrices are\nin turn, sequences of numbers. Equivalent to R's matrix function.\n\nExamples:\n  (def A (matrix [[1 2 3] [4 5 6] [7 8 9]])) ; produces a 3x3 matrix\n  (def A2 (matrix [1 2 3 4 5 6 7 8 9] 3)) ; produces the same 3x3 matrix\n  (def B (matrix [1 2 3 4 5 6 7 8 9])) ; produces a 9x1 column vector\n\n  (first A) ; produces a row matrix [1 2 3]\n  (rest A) ; produces a sub matrix [[4 5 6] [7 8 9]]\n  (first (first A)) ; produces 1.0\n  (rest (first A)) ; produces a row matrix [2 3]\n\n  ; since (plus row1 row2) adds the two rows element-by-element\n  (reduce plus A) ; produces the sums of the columns\n\n  ; and since (sum row1) sums the elements of the row\n  (map sum A) ; produces the sums of the rows\n\n  ; you can filter the rows using Clojure's filter function\n  (filter #(> (nth % 1) 4) A) ; returns the rows where the second column is greater than 4.\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/colt/matrix/tdouble/DoubleMatrix2D.html",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/matrix"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "matrix-map",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1854",
   :line 1854,
   :var-type "function",
   :arglists ([f m] [f m & ms]),
   :doc
   "Like clojure.core/map, but will work on matrices of any dimension:\n1 x 1 (like e.g. a Double), 1 x n, n x 1, and n x m\n\n Examples:\n   (use '(incanter core))\n   (def mat (matrix (range 9) 3))\n   (matrix-map #(mod % 2) mat)\n   (matrix-map #(mod % 2) (first mat))\n   (matrix-map #(mod % 2) ($ 1 0 mat))\n   (matrix-map #(mod % 2) [1 2 3 4])\n   (matrix-map #(mod % 2) 9)",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/matrix-map"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "matrix?",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L105",
   :line 105,
   :var-type "function",
   :arglists ([obj]),
   :doc " Test if obj is 'derived' incanter.Matrix.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/matrix?"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "minus",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L413",
   :line 413,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "   Performs element-by-element subtraction on multiple matrices, sequences\nand/or numbers. If only a single argument is provided, returns the\nnegative of the given matrix, sequence, or number. Equivalent to R's - operator.\n\n\nExamples:\n\n  (def A (matrix [[1 2 3]\n                  [4 5 6]\n                  [7 8 9]]))\n  (minus A)\n  (minus A A A)\n  (minus A 2)\n  (minus 2 A)\n  (minus [1 2 3] [1 2 3])\n  (minus [1 2 3] 2)\n  (minus 2 [1 2 3])\n  (minus [1 2 3])",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/minus"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "mmult",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L650",
   :line 650,
   :var-type "function",
   :arglists ([& args]),
   :doc
   " Returns the matrix resulting from the matrix multiplication of the\n  the given arguments. Equivalent to R's %*% operator.\n\n  Examples:\n\n    (def A (matrix [[1 2 3]\n                    [4 5 6]\n                    [7 8 9]]))\n    (mmult A (trans A))\n    (mmult A (trans A) A)\n\n  References:\n    http://en.wikipedia.org/wiki/Matrix_multiplication\n    http://incanter.org/docs/parallelcolt/api/cern/colt/matrix/tdouble/DoubleMatrix2D.html\n\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/mmult"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "mult",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L440",
   :line 440,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "   Performs element-by-element multiplication on multiple matrices, sequences\nand/or numbers. Equivalent to R's * operator.\n\nExamples:\n\n  (def A (matrix [[1 2 3]\n                  [4 5 6]\n                  [7 8 9]]))\n  (mult A A A)\n  (mult A 2)\n  (mult 2 A)\n  (mult [1 2 3] [1 2 3])\n  (mult [1 2 3] 2)\n  (mult 2 [1 2 3])",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/mult"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "plus",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L392",
   :line 392,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "   Performs element-by-element addition on multiple matrices, sequences\nand/or numbers. Equivalent to R's + operator.\n\nExamples:\n\n  (def A (matrix [[1 2 3]\n                  [4 5 6]\n                  [7 8 9]]))\n  (plus A A A)\n  (plus A 2)\n  (plus 2 A)\n  (plus [1 2 3] [1 2 3])\n  (plus [1 2 3] 2)\n  (plus 2 [1 2 3])",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/plus"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "pow",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L485",
   :line 485,
   :var-type "function",
   :arglists ([& args]),
   :doc
   " This is an element-by-element exponent function, raising the first argument\nby the exponents in the remaining arguments. Equivalent to R's ^ operator.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/pow"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "prod",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L807",
   :line 807,
   :var-type "function",
   :arglists ([x]),
   :doc "Returns the product of the given sequence.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/prod"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "query-dataset",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1259",
   :line 1259,
   :var-type "function",
   :arglists ([data query-map]),
   :doc
   "Queries the given dataset using the query-map, returning a new dataset.\n The query-map uses the the dataset's column-names as keys and a\n simple variant of the MongoDB query language.\n\n For instance, given a dataset with two columns, :x and :category,  to query\n for rows where :x equals 10, use the following query-map: {:x 10}.\n\n To indicate that :x should be between 10 and 20, use {:x {:$gt 10 :$lt 20}}.\n\n To indicate that :category should also be either :red, :green, or :blue, use :$in\n {:x {:$gt 10 :$lt 20} :y {:$in #{:green :blue :red}}}\n\n And to indicate that :category should not include :red, :green, or :blue, use :$nin\n {:x {:$gt 10 :$lt 20} :y {:$nin #{:green :blue :red}}}\n\n The available query terms include :$gt, :$lt, :$gte, :$lte, :$eq, :$ne, :$in, :$nin, $fn.\n\n A row predicate function can be used instead of a query-map. The function must accept\n a map, representing a row of the dataset, and return a boolean value indicating whether\n the row should be included in the new dataset.\n\nExamples:\n   (use '(incanter core datasets))\n   (def cars (get-dataset :cars))\n\n   (view (query-dataset cars {:speed 10}))\n   (view (query-dataset cars {:speed {:$in #{17 14 19}}}))\n   (view (query-dataset cars {:speed {:$lt 20 :$gt 10}}))\n   (view (query-dataset cars {:speed {:$fn #(> (log %) 3)}}))\n\n   ;; use a row predicate function instead of a query map.\n   (view (query-dataset cars (fn [row] (> (/ (row \"speed\") (row \"dist\")) 1/2))))",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/query-dataset"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "query-to-pred",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1208",
   :line 1208,
   :var-type "function",
   :arglists ([query-map]),
   :doc
   "Given a query-map, it returns a function that accepts a hash-map and returns true if it\nsatisfies the conditions specified in the provided query-map.\n\nExamples:\n\n  (use 'incanter.core)\n  (def pred (query-to-pred {:x 5 :y 7}))\n  (pred {:x 5 :y 7 :z :d})\n\n  (def pred (query-to-pred {:x 5 :y {:$gt 5 :$lt 10}}))\n  (pred {:x 5 :y 7 :z :d})\n\n  (def pred (query-to-pred {:z {:$in #{:a :b}}}))\n  (pred {:x 5 :y 7 :z :d})",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/query-to-pred"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "quit",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2553",
   :line 2553,
   :var-type "function",
   :arglists ([]),
   :doc " Exits the Clojure shell.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/quit"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "rank",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1024",
   :line 1024,
   :var-type "function",
   :arglists ([mat]),
   :doc
   " Returns the effective numerical matrix rank, which is the number of nonnegligible singular values.\n\n\nExamples:\n\n(use 'incanter.core)\n(def foo (matrix (range 9) 3))\n(rank foo)\n\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Matrix_rank\n  http://incanter.org/docs/parallelcolt/api/cern/colt/matrix/tdouble/algo/decomposition/DoubleSingularValueDecompositionDC.html",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/rank"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "regularized-beta",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2273",
   :line 2273,
   :var-type "function",
   :arglists ([x a b]),
   :doc
   "\nReturns the regularized incomplete beta value. Equivalent to R's pbeta function.\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Gamma.html\n  http://en.wikipedia.org/wiki/Regularized_incomplete_beta_function\n  http://mathworld.wolfram.com/RegularizedBetaFunction.html",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/regularized-beta"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "save",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2575",
   :line 2575,
   :var-type "multimethod",
   :arglists nil,
   :doc
   " Save is a multi-function that is used to write matrices, datasets and\ncharts (in png format) to a file.\n\nArguments:\n  obj -- is a matrix, dataset, or chart object\n  filename -- the filename to create.\n\nMatrix and dataset options:\n  :delim (default \\,) column delimiter\n  :header (default nil) an sequence of strings to be used as header line\n      for matrices the default value is nil, for datasets, the default is\n      the dataset's column-names array.\n  :append (default false) determines whether this given file should be\n      appended to. If true, a header will not be written to the file again.\n\nChart options:\n  :width (default 500)\n  :height (default 400)\n\n\nMatrix Examples:\n\n  (use '(incanter core io))\n  (def A (matrix (range 12) 3)) ; creates a 3x4 matrix\n  (save A \"A.dat\") ; writes A to the file A.dat, with no header and comma delimited\n  (save A \"A.dat\" :delim \\tab) ; writes A to the file A.dat, with no header and tab delimited\n\n  ;; writes A to the file A.dat, with a header and tab delimited\n  (save A \"A.dat\" :delim \\, :header [\"col1\" \"col2\" \"col3\"])\n\n\nDataset Example:\n\n  (use '(incanter core io datasets))\n  ;; read the iris sample dataset, and save it to a file.\n  (def iris (get-dataset :iris))\n  (save iris \"iris.dat\")\n\n\nChart Example:\n\n  (use '(incanter core io stats charts))\n  (save (histogram (sample-normal 1000)) \"hist.png\")\n\n  ;; chart example using java.io.OutputStream instead of filename\n  (use '(incanter core stats charts))\n  (import 'java.io.FileOutputStream)\n  (def fos (FileOutputStream. \"/tmp/hist.png\"))\n  (def hist (histogram (sample-normal 1000)))\n  (save hist fos)\n  (.close fos)\n\n  (view \"file:///tmp/hist.png\")",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/save"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "sel",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L213",
   :line 213,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "\nReturns an element or subset of the given matrix, or dataset.\n\nArgument:\n  a matrix object or dataset.\n\nOptions:\n  :rows (default true)\n    returns all rows by default, can pass a row index or sequence of row indices\n  :cols (default true)\n    returns all columns by default, can pass a column index or sequence of column indices\n  :except-rows (default nil) can pass a row index or sequence of row indices to exclude\n  :except-cols (default nil) can pass a column index or sequence of column indices to exclude\n  :filter (default nil)\n    a function can be provided to filter the rows of the matrix\n\nExamples:\n  (use 'incanter.datasets)\n  (def iris (to-matrix (get-dataset :iris)))\n  (sel iris 0 0) ; first element\n  (sel iris :rows 0 :cols 0) ; also first element\n  (sel iris :cols 0) ; first column of all rows\n  (sel iris :cols [0 2]) ; first and third column of all rows\n  (sel iris :rows (range 10) :cols (range 2)) ; first two columns of the first 10 rows\n  (sel iris :rows (range 10)) ; all columns of the first 10 rows\n\n  ;; exclude rows or columns\n  (sel iris :except-rows (range 10)) ; all columns of all but the first 10 rows\n  (sel iris :except-cols 1) ; all columns except the second\n\n  ;; return only the first 10 even rows\n  (sel iris :rows (range 10) :filter #(even? (int (nth % 0))))\n  ;; select rows where distance (third column) is greater than 50\n  (sel iris :filter #(> (nth % 2) 4))\n\n  ;; examples with datasets\n  (use 'incanter.datasets)\n  (def us-arrests (get-dataset :us-arrests))\n  (sel us-arrests :cols \"State\")\n  (sel us-arrests :cols :State)\n\n  (sel us-arrests :cols [\"State\" \"Murder\"])\n  (sel us-arrests :cols [:State :Murder])",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/sel"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "separate-blocks",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2769",
   :line 2769,
   :var-type "function",
   :arglists ([matrix partitions]),
   :doc "Partitions should be a sequence of [start,size] pairs.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/separate-blocks"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "set-data",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2515",
   :line 2515,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "\n\nExamples:\n\n  (use '(incanter core charts datasets))\n\n  (def data (get-dataset :iris))\n  (def table (data-table data))\n  (view table)\n  ;; now view only a subset of the data\n  (set-data table ($where {:Petal.Length {:gt 6}} data))\n\n\n  ;; use sliders to dynamically select the query values\n  (let [data (get-dataset :iris)\n        table (data-table data)]\n    (view table)\n    (sliders [species [\"setosa\" \"virginica\" \"versicolor\"]\n              min-petal-length (range 0 8 0.1)]\n      (set-data table ($where {:Species species\n                               :Petal.Length {:gt min-petal-length}}\n                              data))))",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/set-data"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "sin",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L540",
   :line 540,
   :var-type "function",
   :arglists ([A]),
   :doc
   "Returns the sine of the elements in the given matrix, sequence or number.\nEquivalent to R's sin function.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/sin"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "solve",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L711",
   :line 711,
   :var-type "function",
   :arglists ([A & B]),
   :doc
   " Returns a matrix solution if A is square, least squares solution otherwise.\nEquivalent to R's solve function.\n\nExamples:\n  (solve (matrix [[2 0 0] [0 2 0] [0 0 2]]))\n\nReferences:\n  http://en.wikipedia.org/wiki/Matrix_inverse",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/solve"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "solve-quadratic",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2291",
   :line 2291,
   :var-type "function",
   :arglists ([a b c]),
   :doc
   "\nReturns a vector with the solution to x from the quadratic\nequation, a*x^2 + b*x + c.\n\nArguments:\n  a, b, c: coefficients of a qaudratic equation.\n\nExamples:\n  ;; -2*x^2 + 7*x + 15\n  (quadratic-formula -2 7 15)\n  ;; x^2 + -2*x + 1\n  (quadratic-formula 1 -2 1)\n\nReferences:\n  http://en.wikipedia.org/wiki/Quadratic_formula",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/solve-quadratic"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "sq",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L504",
   :line 504,
   :var-type "function",
   :arglists ([A]),
   :doc
   "Returns the square of the elements in the given matrix, sequence or number.\nEquivalent to R's sq function.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/sq"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "sqrt",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L498",
   :line 498,
   :var-type "function",
   :arglists ([A]),
   :doc
   "Returns the square-root of the elements in the given matrix, sequence or number.\nEquivalent to R's sqrt function.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/sqrt"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "sum",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L800",
   :line 800,
   :var-type "function",
   :arglists ([x]),
   :doc "Returns the sum of the given sequence.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/sum"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "sum-of-squares",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L793",
   :line 793,
   :var-type "function",
   :arglists ([x]),
   :doc "Returns the sum-of-squares of the given sequence.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/sum-of-squares"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "symmetric-matrix",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2318",
   :line 2318,
   :var-type "function",
   :arglists ([data & {:keys [lower], :or {lower true}}]),
   :doc
   "\nReturns a symmetric matrix from the given data, which represents the lower triangular elements\nordered by row. This is not the inverse of half-vectorize which returns a vector of the upper-triangular\nvalues, unless the :lower option is set to false.\n\nOptions:\n  :lower (default true) -- lower-triangular. Set :lower to false to reverse the half-vectorize function.\n\nExamples:\n\n  (use 'incanter.core)\n  (symmetric-matrix [1\n                     2 3\n                     4 5 6\n                     7 8 9 10])\n\n\n  (half-vectorize\n    (symmetric-matrix [1\n                       2 3\n                       4 5 6\n                       7 8 9 10] :lower false))",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/symmetric-matrix"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "tan",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L564",
   :line 564,
   :var-type "function",
   :arglists ([A]),
   :doc
   "Returns the tangent of the elements in the given matrix, sequence or number.\nEquivalent to R's tan function.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/tan"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "to-dataset",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1359",
   :line 1359,
   :var-type "function",
   :arglists ([obj & {:keys [transpose]}]),
   :doc
   "Returns a dataset containing the given values.\n\nExamples:\n\n  (use 'incanter.core)\n  (to-dataset 1)\n  (to-dataset :a)\n  (to-dataset [:a])\n  (to-dataset (range 10))\n  (to-dataset (range 10) :transpose true)\n  (to-dataset [[1 2] [3 4] [5 6]])\n  (to-dataset {:a 1 :b 2 :c 3})\n  (to-dataset {\"a\" 1 \"b\" 2 \"c\" 3})\n  (to-dataset [{:a 1 :b 2} {:a 1 :b 2}])\n  (to-dataset [{\"a\" 1 \"b\" 2 \"c\" 3} {\"a\" 1 \"b\" 2 \"c\" 3}])",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/to-dataset"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "to-labels",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2177",
   :line 2177,
   :var-type "function",
   :arglists ([coll cat-var]),
   :doc "\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/to-labels"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "to-levels",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2167",
   :line 2167,
   :var-type "function",
   :arglists ([coll & options]),
   :doc "\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/to-levels"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "to-list",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L618",
   :line 618,
   :var-type "multimethod",
   :arglists nil,
   :doc
   " Returns a list-of-lists if the given matrix is two-dimensional\nand a flat list if the matrix is one-dimensional.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/to-list"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "to-map",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2104",
   :line 2104,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "Takes a dataset or matrix and returns a hash-map where the keys are\n keyword versions of the column names, for datasets, or numbers, for\n matrices, and the values are sequence of the column values.\n\nExamples:\n  (use '(incanter core datasets))\n\n  (to-map (get-dataset :cars))\n\n  (to-map (matrix (range 9) 3))",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/to-map"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "to-matrix",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2216",
   :line 2216,
   :var-type "function",
   :arglists ([dataset & {:keys [dummies], :or {dummies false}}]),
   :doc
   "Converts a dataset into a matrix. Equivalent to R's as.matrix function\n for datasets.\n\nOptions:\n  :dummies (default false) -- if true converts non-numeric variables into sets\n                              of binary dummy variables, otherwise converts\n                              them into numeric codes.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/to-matrix"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "to-vect",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1050",
   :line 1050,
   :var-type "function",
   :arglists ([mat]),
   :doc
   " Returns a vector-of-vectors if the given matrix is two-dimensional\nand a flat vector if the matrix is one-dimensional. This is a bit\nslower than the to-list function. ",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/to-vect"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "trace",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L741",
   :line 741,
   :var-type "function",
   :arglists ([mat]),
   :doc
   " Returns the trace of the given matrix.\n\nReferences:\n  http://en.wikipedia.org/wiki/Matrix_trace\n  http://incanter.org/docs/parallelcolt/api/cern/colt/matrix/tdouble/algo/DenseDoubleAlgebra.html",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/trace"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "trans",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L178",
   :line 178,
   :var-type "function",
   :arglists ([mat]),
   :doc
   "   Returns the transpose of the given matrix. Equivalent to R's t function\n\nExamples:\n  (def A (matrix [[1 2 3]\n                 [4 5 6]\n                 [7 8 9]]))\n\n  (trans A)",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/trans"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "transform-col",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L1985",
   :line 1985,
   :var-type "function",
   :arglists ([dataset column f & args]),
   :doc
   " Apply function f & args to the specified column of dataset and replace the column\nwith the resulting new values.",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/transform-col"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "vectorize",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L752",
   :line 752,
   :var-type "function",
   :arglists ([mat]),
   :doc
   " Returns the vectorization (i.e. vec) of the given matrix.\n  The vectorization of an m-by-n matrix A, denoted by vec(A)\n  is the m*n-by-1 column vector obtain by stacking the columns\n  of the matrix A on top of one another.\n\n  For instance:\n    (= (vectorize (matrix [[a b] [c d]])) (matrix [a c b d]))\n\n  Examples:\n    (def A (matrix [[1 2] [3 4]]))\n    (vectorize A)\n\n  References:\n    http://en.wikipedia.org/wiki/Vectorization_(mathematics)\n",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/vectorize"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "view",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2362",
   :line 2362,
   :var-type "multimethod",
   :arglists nil,
   :doc
   " This is a general 'view' function. When given an Incanter matrix/dataset\nor a Clojure numeric collection, it will display it in a Java Swing\nJTable. When given an Incanter chart object, it will display it in a new\nwindow. When given a URL string, it will open the location with the\nplatform's default web browser.\n\nWhen viewing charts, a :width (default 500) and :height (default 400)\noption can be provided.\n\nWhen viewing an incanter.processing sketch, set the :exit-on-close option\nto true (default is false) to kill the animation processes when you\nclose the window (this will also kill your REPL or Swank server),\notherwise those processing will continue to run in the background.\n\n\n\nExamples:\n\n  (use '(incanter core stats datasets charts))\n\n  ;; view matrices\n  (def rand-mat (matrix (sample-normal 100) 4))\n  (view rand-mat)\n\n  ;; view numeric collections\n  (view [1 2 3 4 5])\n  (view (sample-normal 100))\n\n  ;; view Incanter datasets\n  (view (get-dataset :iris))\n\n  ;; convert dataset to matrix, changing Species names to numeric codes\n  (view (to-matrix (get-dataset :iris)))\n\n  ;; convert dataset to matrix, changing Species names to dummy variables\n  (view (to-matrix (get-dataset :iris) :dummies true))\n\n  ;; view a chart\n  (view (histogram (sample-normal 1000)) :width 700 :height 700)\n\n  ;; view a URL\n  (view \"http://incanter.org\")\n\n  ;; view a PNG file\n  (save (histogram (sample-normal 1000)) \"/tmp/norm_hist.png\")\n  (view \"file:///tmp/norm_hist.png\")",
   :namespace "incanter.core",
   :wiki-url
   "http://incanter.github.com/incanter//core-api.html#incanter.core/view"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj",
   :name "with-data",
   :file "modules/incanter-core/src/incanter/core.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/core.clj#L2080",
   :line 2080,
   :var-type "macro",
   :arglists ([data-binding & body]),
   :doc
   "Binds the given data to $data and executes the body.\n Typically used with the $ and $where functions.\n\nExamples:\n  (use '(incanter core stats charts datasets))\n\n  (with-data  (get-dataset :cars)\n    (def lm (linear-model ($ :dist) ($ :speed)))\n    (doto (scatter-plot ($ :speed) ($ :dist))\n              (add-lines ($ :speed) (:fitted lm))\n               view))\n\n   ;; create a dataset where :speed greater than 10 or less than -10\n   (with-data (get-dataset :cars)\n     (view (-> ($where {:speed {:$gt 20}})\n                     (conj-rows ($where {:speed {:$lt 10}})))))",
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
   "https://github.com/incanter/incanter/raw/8d46e5b20b772aebe766f44bbeb6c052cb72e003/modules/incanter-io/src/incanter/datasets.clj",
   :name "get-dataset",
   :file "modules/incanter-io/src/incanter/datasets.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8d46e5b20b772aebe766f44bbeb6c052cb72e003/modules/incanter-io/src/incanter/datasets.clj#L91",
   :line 91,
   :var-type "function",
   :arglists
   ([dataset-key
     &
     {:keys [incanter-home from-repo],
      :or
      {incanter-home
       (or (getProperty "incanter.home") (getenv "INCANTER_HOME")),
       from-repo true}}]),
   :doc
   " Returns the sample dataset associated with the given key. Most datasets\nare from R's sample data sets, as are the descriptions below.\n\nOptions:\n\n  :incanter-home -- if the incanter.home property is not set when the JVM is\n                    started (using -Dincanter.home) or there is no INCANTER_HOME\n                    environment variable set, use the :incanter-home options to \n                    provide the parent directory of the sample data directory.\n\n  :from-repo (default false) -- If true, retrieves the dataset from the online repository \n                     instead of locally, it will do this by default if incanter-home is not set.\n\n\nDatasets:\n\n  :iris -- the Fisher's or Anderson's Iris data set gives the\n           measurements in centimeters of the variables sepal\n           length and width and petal length and width,\n           respectively, for 50 flowers from each of 3 species\n           of iris.\n\n  :cars -- The data give the speed of cars and the distances taken\n            to stop. Note that the data were recorded in the 1920s.\n\n  :survey -- survey data used in Scott Lynch's 'Introduction to Applied Bayesian Statistics\n             and Estimation for Social Scientists'\n\n  :us-arrests -- This data set contains statistics, in arrests per 100,000\n                 residents for assault, murder, and rape in each of the 50 US\n                 states in 1973. Also given is the percent of the population living\n                 in urban areas.\n\n  :flow-meter -- flow meter data used in Bland Altman Lancet paper.\n\n  :co2 -- has 84 rows and 5 columns of data from an experiment on the cold tolerance\n          of the grass species _Echinochloa crus-galli_.\n\n  :chick-weight -- has 578 rows and 4 columns from an experiment on the effect of diet\n                   on early growth of chicks.\n\n  :plant-growth -- Results from an experiment to compare yields (as measured by dried\n                   weight of plants) obtained under a control and two different\n                   treatment conditions.\n\n  :pontius -- These data are from a NIST study involving calibration of load cells.\n              The response variable (y) is the deflection and the predictor variable\n              (x) is load.\n              See http://www.itl.nist.gov/div898/strd/lls/data/Pontius.shtml\n\n  :filip -- NIST data set for linear regression certification,\n            see http://www.itl.nist.gov/div898/strd/lls/data/Filip.shtml\n\n  :longely -- This classic dataset of labor statistics was one of the first used to\n              test the accuracy of least squares computations. The response variable\n              (y) is the Total Derived Employment and the predictor variables are GNP\n              Implicit Price Deflator with Year 1954 = 100 (x1), Gross National Product\n              (x2), Unemployment (x3), Size of Armed Forces (x4), Non-Institutional\n              Population Age 14 & Over (x5), and Year (x6).\n              See http://www.itl.nist.gov/div898/strd/lls/data/Longley.shtml\n\n  :Chwirut -- These data are the result of a NIST study involving ultrasonic calibration.\n              The response variable is ultrasonic response, and the predictor variable is\n              metal distance.\n              See http://www.itl.nist.gov/div898/strd/nls/data/LINKS/DATA/Chwirut1.dat\n\n  :thurstone -- test data for non-linear least squares.\n\n  :austres -- Quarterly Time Series of the Number of Australian Residents\n\n  :hair-eye-color -- Hair and eye color of sample of students\n\n  :airline-passengers -- Monthly Airline Passenger Numbers 1949-1960\n\n  :math-prog -- Pass/fail results for a high school mathematics assessment test\n                and a freshmen college programming course.\n\n  :iran-election -- Vote counts for 30 provinces from the 2009 Iranian election.\n\n Examples:\n   (def data (get-dataset :cars))\n   (def data2 (get-dataset :cars :incanter.home \"/usr/local/packages/incanter\"))",
   :namespace "incanter.datasets",
   :wiki-url
   "http://incanter.github.com/incanter//datasets-api.html#incanter.datasets/get-dataset"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "->Beta-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L497",
   :line 497,
   :var-type "function",
   :arglists ([alpha beta]),
   :doc
   "Positional factory function for class incanter.distributions.Beta-rec.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->Beta-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "->Binomial-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L528",
   :line 528,
   :var-type "function",
   :arglists ([n p]),
   :doc
   "Positional factory function for class incanter.distributions.Binomial-rec.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->Binomial-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "->ChiSquare-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L556",
   :line 556,
   :var-type "function",
   :arglists ([df]),
   :doc
   "Positional factory function for class incanter.distributions.ChiSquare-rec.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->ChiSquare-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "->Combination",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L336",
   :line 336,
   :var-type "function",
   :arglists ([n k u]),
   :doc
   "Positional factory function for class incanter.distributions.Combination.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->Combination"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "->DoubleUniform-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L767",
   :line 767,
   :var-type "function",
   :arglists ([min max]),
   :doc
   "Positional factory function for class incanter.distributions.DoubleUniform-rec.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->DoubleUniform-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "->Exponential-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L583",
   :line 583,
   :var-type "function",
   :arglists ([rate]),
   :doc
   "Positional factory function for class incanter.distributions.Exponential-rec.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->Exponential-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "->F",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L609",
   :line 609,
   :var-type "function",
   :arglists ([df1 df2]),
   :doc
   "Positional factory function for class incanter.distributions.F.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->F"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "->Gamma-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L650",
   :line 650,
   :var-type "function",
   :arglists ([shape rate]),
   :doc
   "Positional factory function for class incanter.distributions.Gamma-rec.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->Gamma-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "->NegativeBinomial-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L681",
   :line 681,
   :var-type "function",
   :arglists ([size prob]),
   :doc
   "Positional factory function for class incanter.distributions.NegativeBinomial-rec.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->NegativeBinomial-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "->Normal-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L455",
   :line 455,
   :var-type "function",
   :arglists ([mean sd]),
   :doc
   "Positional factory function for class incanter.distributions.Normal-rec.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->Normal-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "->Poisson-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L711",
   :line 711,
   :var-type "function",
   :arglists ([lambda]),
   :doc
   "Positional factory function for class incanter.distributions.Poisson-rec.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->Poisson-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "->StudentT-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L738",
   :line 738,
   :var-type "function",
   :arglists ([df]),
   :doc
   "Positional factory function for class incanter.distributions.StudentT-rec.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->StudentT-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "->UniformInt",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L249",
   :line 249,
   :var-type "function",
   :arglists ([start end]),
   :doc
   "Positional factory function for class incanter.distributions.UniformInt.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/->UniformInt"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "beta-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L508",
   :line 508,
   :var-type "function",
   :arglists ([] [alpha beta]),
   :doc
   "Returns a Beta distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  alpha      (default 1)\n  beta       (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Beta.html\n  http://en.wikipedia.org/wiki/Beta_distribution\n\nExample:\n  (pdf (beta-distribution 1 2) 0.5)",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/beta-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "binomial-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L536",
   :line 536,
   :var-type "function",
   :arglists ([] [n p]),
   :doc
   "Returns a Binomial distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  size       (default 1)\n  prob       (default 1/2)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Binomial.html\n  http://en.wikipedia.org/wiki/Binomial_distribution\n\nExample:\n  (pdf (binomial-distribution 20 1/4) 10)",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/binomial-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "chisq-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L564",
   :line 564,
   :var-type "function",
   :arglists ([] [df]),
   :doc
   "Returns a Chi-square distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  df         (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/ChiSquare.html\n  http://en.wikipedia.org/wiki/Chi_square_distribution\n\nExample:\n  (pdf (chisq-distribution 2) 5.0)",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/chisq-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "combination-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L346",
   :line 346,
   :var-type "function",
   :arglists ([n k]),
   :doc
   "\n\tCreate a distribution of all the k-sized combinations of n integers.\n\tCan be considered a multivariate distribution over k-dimensions, where\n\teach dimension is a discrete random variable on the (0, n] range (though\n\tthese variables are decidedly non-independent).\n\n\tA draw from this distribution can also be considered a sample without\n\treplacement from any finite set, where the values in the returned\n\tvector represent the indices of the items in the set.\n\n\tArguments:\n\t\tn\t\tThe number of possible items from which to select.\n\t\tk\t\tThe size of a sample (without replacement) to draw.\n\n\tSee also:\n\t\ttest-statistic-distribution, integer-distribution, pdf, cdf, draw, support\n\n\tReferences:\n\t\thttp://en.wikipedia.org/wiki/Combination\n\n\tExamples:\n\t\t",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/combination-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "exponential-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L591",
   :line 591,
   :var-type "function",
   :arglists ([] [rate]),
   :doc
   "Returns a Exponential distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  rate       (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Exponential.html\n  http://en.wikipedia.org/wiki/Exponential_distribution\n\nExample:\n  (pdf (exponential-distribution 1/2) 2.0)",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/exponential-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "f-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L631",
   :line 631,
   :var-type "function",
   :arglists ([] [df1 df2]),
   :doc
   "Returns a F-distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  df1        (default 1)\n  df2        (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://en.wikipedia.org/wiki/F_distribution\n  http://mathworld.wolfram.com/F-Distribution.html\n\nExample:\n  (pdf (f-distribution 5 2) 1.0)",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/f-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "gamma-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L661",
   :line 661,
   :var-type "function",
   :arglists ([] [shape rate]),
   :doc
   "Returns a Gamma distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  shape      (default 1)\n  rate       (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Gamma.html\n  http://en.wikipedia.org/wiki/Gamma_distribution\n\nExample:\n  (pdf (gamma-distribution 1 2) 10)",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/gamma-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "integer-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L267",
   :line 267,
   :var-type "function",
   :arglists ([] [end] [start end]),
   :doc
   "\n  Create a uniform distribution over a set of integers over\n\tthe (start, end] interval. An alternative method of creating\n\ta distribution would be to just use a sequence of integers\n\t(e.g. (draw (range 100000))). For large sequences, like the one\n\tin the example, using a sequence will be require realizing the\n\tentire sequence before a draw can be taken. This less efficient than\n\tcomputing random draws based on the end points of the distribution.\n\n\tArguments:\n\t\tstart\tThe lowest end of the interval, such that (>= (draw d) start)\n\t\t\t\t\tis always true. (Default 0)\n\t\tend\t\tThe value at the upper end of the interval, such that\n\t\t\t\t\t(> end (draw d)) is always true. Note the strict inequality.\n\t\t\t\t\t(Default 1)\n\n\tSee also:\n\t\tpdf, cdf, draw, support\n\n\tReferences:\n\t\thttp://en.wikipedia.org/wiki/Uniform_distribution_(discrete)\n\n\tExamples:\n\t\t(pdf (integer-distribution 0 10) 3) ; returns 1/10 for any value\n\t\t(draw (integer-distribution -5 5))\n\t\t(draw (integer-distribution (bit-shift-left 2 1000))) ; probably a very large value",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/integer-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->Beta-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L497",
   :line 497,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.Beta-rec, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->Beta-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->Binomial-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L528",
   :line 528,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.Binomial-rec, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->Binomial-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->ChiSquare-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L556",
   :line 556,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.ChiSquare-rec, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->ChiSquare-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->Combination",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L336",
   :line 336,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.Combination, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->Combination"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->DoubleUniform-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L767",
   :line 767,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.DoubleUniform-rec, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->DoubleUniform-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->Exponential-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L583",
   :line 583,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.Exponential-rec, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->Exponential-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->F",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L609",
   :line 609,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.F, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->F"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->Gamma-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L650",
   :line 650,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.Gamma-rec, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->Gamma-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->NegativeBinomial-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L681",
   :line 681,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.NegativeBinomial-rec, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->NegativeBinomial-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->Normal-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L455",
   :line 455,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.Normal-rec, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->Normal-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->Poisson-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L711",
   :line 711,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.Poisson-rec, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->Poisson-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->StudentT-rec",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L738",
   :line 738,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.StudentT-rec, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->StudentT-rec"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "map->UniformInt",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L249",
   :line 249,
   :var-type "function",
   :arglists ([m#]),
   :doc
   "Factory function for class incanter.distributions.UniformInt, taking a map of keywords to field values.",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/map->UniformInt"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "neg-binomial-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L691",
   :line 691,
   :var-type "function",
   :arglists ([] [size prob]),
   :doc
   "Returns a Negative binomial distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  size       (default 10)\n  prob       (default 1/2)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/NegativeBinomial.html\n  http://en.wikipedia.org/wiki/Negative_binomial_distribution\n\nExample:\n  (pdf (neg-binomial-distribution 20 1/2) 10)",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/neg-binomial-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "normal-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L470",
   :line 470,
   :var-type "function",
   :arglists ([] [mean sd]),
   :doc
   "\n\tReturns a Normal distribution that implements the\n  incanter.distributions.Distribution protocol.\n\n\tArguments:\n\t\tmean\tThe mean of the distribution. One of two parameters\n\t\t\t\t\tthat summarize the Normal distribution (default 0).\n\t\tsd\t\tThe standard deviation of the distribution.\n\t\t\t\t \tThe second parameter that describes the Normal (default 1).\n\n  See also:\n      Distribution, pdf, cdf, draw, support\n\n  References:\n      http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Normal.html\n      http://en.wikipedia.org/wiki/Normal_distribution\n\n  Example:\n      (pdf (normal-distribution -2 (sqrt 0.5)) 1.96)",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/normal-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "poisson-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L719",
   :line 719,
   :var-type "function",
   :arglists ([] [lambda]),
   :doc
   "Returns a Poisson distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  lambda     (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Poisson.html\n  http://en.wikipedia.org/wiki/Poisson_distribution\n\nExample:\n  (pdf (poisson-distribution 10) 5)",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/poisson-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "roulette-wheel",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L194",
   :line 194,
   :var-type "function",
   :arglists ([freqs]),
   :doc
   "Perform a roulette wheel selection given a list of frequencies",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/roulette-wheel"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "t-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L748",
   :line 748,
   :var-type "function",
   :arglists ([] [df]),
   :doc
   "Returns a Student-t distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  df         (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/StudentT.html\n  http://en.wikipedia.org/wiki/Student-t_distribution\n\nExample:\n  (pdf (t-distribution 10) 1.2)",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/t-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "test-statistic-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L377",
   :line 377,
   :var-type "function",
   :arglists ([test-statistic n k]),
   :doc
   "\n\tCreate a distribution of the test-statistic over the possible\n\trandom samples of treatment units from the possible units.\n\n\tThere are two methods for generating the distribution. The\n\tfirst method is enumerating all possible randomizations and\n\tperforming the test statistic on each. This gives the exact\n\tdistribution, but is only feasible for small problems.\n\n\tThe second method uses a combination-distribution to sample\n\tfor the space of possible treatment assignments and applies\n\tthe test statistic the sampled randomizations. While the\n\tresulting distribution is not exact, it is tractable for\n\tlarger problems.\n\n\tThe algorithm automatically chooses between the two methods\n\tby computing the number of possible randomizations and\n\tcomparing it to *test-statistic-iterations*. If the exact\n\tdistribution requires fewer than *test-statistic-iterations*\n\tthe enumeration method is used. Otherwise, it draws\n\t*test-statistic-iterations* total samples for the simulated\n\tmethod.\n\n\tBy default, the algorithm uses parallel computation. This is\n\tcontrolled by the function *test-statistic-map*, which is\n\tbound to pmap by default. Bind it to map to use a single\n\tthread for computation.\n\n\tArguments:\n\t\ttest-statistic\tA function that takes two vectors and summarizes\n\t\t\t\tthe difference between them\n\t\tn\t\tThe number of total units in the pool\n\t\tk\t  The number of treatment units per sample\n\n\tSee also:\n\t\tcombination-distribution, pdf, cdf, draw, support\n\n\tReferences:\n\t\thttp://en.wikipedia.org/wiki/Sampling_distribution\n\t\thttp://en.wikipedia.org/wiki/Exact_test\n\t\thttp://en.wikipedia.org/wiki/Randomization_test\n\t\thttp://en.wikipedia.org/wiki/Lady_tasting_tea\n\n\tExamples:\n\t\t",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/test-statistic-distribution"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "uniform-distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L777",
   :line 777,
   :var-type "function",
   :arglists ([] [min max]),
   :doc
   "Returns a Uniform distribution that implements the incanter.distributions.Distribution protocol.\n\nArguments:\n  min        (default 0)\n  max        (default 1)\n\nSee also:\n  Distribution, pdf, cdf, draw, support\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/DoubleUniform.html\n  http://en.wikipedia.org/wiki/Uniform_distribution\n\nExample:\n  (pdf (uniform-distribution 1.0 10.0) 5)",
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
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj",
   :name "Distribution",
   :file "modules/incanter-core/src/incanter/distributions.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/distributions.clj#L30",
   :line 30,
   :var-type "protocol",
   :arglists nil,
   :doc
   "\n  The distribution protocol defines operations on probability distributions.\n\tDistributions may be univariate (defined over scalars) or multivariate\n\t(defined over vectors). Distributions may be discrete or continuous.\n\n\tFor a list of types that implement the protocol run (extenders Distribution).\n\tImplementations are provided for the various Clojure collection datatypes.\n\tSee the example below for using the distribution methods on these types.\n\n\tSee also:\n\t\tpdf, cdf, draw, support\n\n\tReferences:\n\t\thttp://en.wikipedia.org/wiki/Probability_distribution\n\n\tExamples:\n\t  (support [1 3 4 2 1 3 4 2]) ; returns the set #{1 2 3 4}\n\t\t(draw [1 3 4 2 1 3 4 2]) ; returns a value from #{1 2 3 4}\n\t\t(pdf [2 1 2] 1) ; returns the value 1/3\n\t\t(cdf [2 1 2 3] 2) ; returns the value 3/4 ",
   :namespace "incanter.distributions",
   :wiki-url
   "http://incanter.github.com/incanter//distributions-api.html#incanter.distributions/Distribution"}
  {:name "cdf",
   :doc
   "\n  A function of the incanter.distribution.Distribution protocol.\n\n  Returns the value of the cumulative density function for the\n  distribution d at support v.\n\n\tSee also:\n\t  Distribution, pdf, draw, support\n\n\tReferences:\n\t  http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\n\tExamples:\n\t\t(cdf [2 1 2 3] 2) ; returns the value 3/4 ",
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
   "\n  A function of the incanter.distribution.Distribution protocol.\n\n  Returns a randomly drawn value from the support of distribution d. \n\n\tSee also:\n\t  Distribution, pdf, cdf, support\n\n\tExamples:\n\t\t(draw [1 3 4 2 1 3 4 2]) ; returns a value from #{1 2 3 4}",
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
   "\n  A function of the incanter.distribution.Distribution protocol.\n\n  Returns the value of the probability density/mass function for the\n  distribution d at support v.\n\n\tSee also:\n\t  Distribution, cdf, draw, support\n\n\tReferences:\n\t  http://en.wikipedia.org/wiki/Probability_density_function\n\n\tExamples:\n\t\t(pdf [2 1 2] 1) ; returns the value 1/3",
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
   "\n\t**** EXPERIMENTAL ****\n  A function of the incanter.distribution.Distribution protocol.\n\n  Returns the support of the probability distribution d.\n\tFor discrete distributions, the support is a set (i.e. #{1 2 3}).\n\tFor continuous distributions, the support is a 2 element vector\n\tdescribing the range. For example, the uniform distribution over\n\tthe unit interval would return the vector [0 1].\n\n\tThis function is marked as experimental to note that the output\n\tformat might need to adapt to more complex support structures.\n\tFor example, what would best describe a mixture of continuous\n\tdistributions?\n\n\tSee also:\n\t  Distribution, pdf, draw, support\n\n\tReferences:\n\t  http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\n\tExamples:\n\t\t(cdf [2 1 2 3] 2) ; returns the value 3/4 ",
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
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-excel/src/incanter/excel.clj",
   :name "read-xls",
   :file "modules/incanter-excel/src/incanter/excel.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-excel/src/incanter/excel.clj#L97",
   :line 97,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "Read an Excel file into a dataset. Note: cells containing formulas will be\nempty upon import.  Can read both older and newer Excel file formats, uses the filename suffix\nor :override-format option.\n\nOptions are:\n:sheet either a String for the tab name or an int for the sheet index -- defaults to 0\n:header-keywords convert the incoming header line to keywords -- defaults to false (no conversion)\n:override-format If nil use the filename suffix to guess the Excel file format.  If :xls\nor :xlsx override the suffix check.\n:all-sheets? true to try to read in all sheets of data (false by default).\n\n Examples:\n   (use '(incanter core io excel))\n   (view (read-xls \"http://incanter.org/data/aus-airline-passengers.xls\"))\n\n   (use '(incanter core charts excel))\n   ;; read .xls file of Australian airline passenger data from the 1950s.\n   (with-data (read-xls \"http://incanter.org/data/aus-airline-passengers.xls\")\n   (view $data)\n   ;; time-series-plot needs time in millisecs\n   ;; create a function, to-millis, to convert a sequence of Date objects\n   ;; to a sequence of milliseconds\n   (let [to-millis (fn [dates] (map #(.getTime %) dates))] \n     (view (time-series-plot (to-millis ($ :date)) ($ :passengers)))))",
   :namespace "incanter.excel",
   :wiki-url
   "http://incanter.github.com/incanter//excel-api.html#incanter.excel/read-xls"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-excel/src/incanter/excel.clj",
   :name "save-xls",
   :file "modules/incanter-excel/src/incanter/excel.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-excel/src/incanter/excel.clj#L26",
   :line 26,
   :var-type "multimethod",
   :arglists nil,
   :doc
   "Save a dataset to an Excel file.  Can save in both older and newer\nExcel formats, uses the filename suffix or :override-format option.\n\nBy passing in a collection of datasets and names it is possible to write more than\none sheet at a time: e.g.\n  (save-xls [\"first sheet\" dataset1 \"second\" dataset2] my-file)\n\nOptions are:\n:sheet defaults to \"dataset\" if not provided.\n:use-bold defaults to true.  Set the header line in bold.\n:override-format If nil use the filename suffix to guess the Excel file format.\nIf :xls or :xlsx override the suffix check.\n\nExamples:\n  (use '(incanter core datasets excel))\n  (save-xls (get-dataset :cars) \"/tmp/cars.xls\")",
   :namespace "incanter.excel",
   :wiki-url
   "http://incanter.github.com/incanter//excel-api.html#incanter.excel/save-xls"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/077cde547363afe675fd8400de11dda0a4b47952/modules/incanter-core/src/incanter/infix.clj",
   :name "defop",
   :file "modules/incanter-core/src/incanter/infix.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/077cde547363afe675fd8400de11dda0a4b47952/modules/incanter-core/src/incanter/infix.clj#L42",
   :line 42,
   :var-type "function",
   :arglists ([op prec & [trans]]),
   :doc "Define operators for formula macro",
   :namespace "incanter.infix",
   :wiki-url
   "http://incanter.github.com/incanter//infix-api.html#incanter.infix/defop"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/077cde547363afe675fd8400de11dda0a4b47952/modules/incanter-core/src/incanter/infix.clj",
   :name "formula",
   :file "modules/incanter-core/src/incanter/infix.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/077cde547363afe675fd8400de11dda0a4b47952/modules/incanter-core/src/incanter/infix.clj#L118",
   :line 118,
   :var-type "macro",
   :arglists ([& equation]),
   :doc "Convert from infix notation to prefix notation",
   :namespace "incanter.infix",
   :wiki-url
   "http://incanter.github.com/incanter//infix-api.html#incanter.infix/formula"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/077cde547363afe675fd8400de11dda0a4b47952/modules/incanter-core/src/incanter/infix.clj",
   :name "infix-to-prefix",
   :file "modules/incanter-core/src/incanter/infix.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/077cde547363afe675fd8400de11dda0a4b47952/modules/incanter-core/src/incanter/infix.clj#L99",
   :line 99,
   :var-type "function",
   :arglists ([col]),
   :doc "Convert from infix notation to prefix notation",
   :namespace "incanter.infix",
   :wiki-url
   "http://incanter.github.com/incanter//infix-api.html#incanter.infix/infix-to-prefix"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8c131af06db2b5c90515e466bb8c629497b92d4e/modules/incanter-core/src/incanter/internal.clj",
   :name "hint",
   :file "modules/incanter-core/src/incanter/internal.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8c131af06db2b5c90515e466bb8c629497b92d4e/modules/incanter-core/src/incanter/internal.clj#L56",
   :line 56,
   :var-type "macro",
   :arglists ([type body]),
   :doc "Applies a type hint to a body",
   :namespace "incanter.internal",
   :wiki-url
   "http://incanter.github.com/incanter//internal-api.html#incanter.internal/hint"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8c131af06db2b5c90515e466bb8c629497b92d4e/modules/incanter-core/src/incanter/internal.clj",
   :name "is-matrix",
   :file "modules/incanter-core/src/incanter/internal.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8c131af06db2b5c90515e466bb8c629497b92d4e/modules/incanter-core/src/incanter/internal.clj#L29",
   :line 29,
   :var-type "function",
   :arglists ([obj]),
   :doc
   " Test if obj is 'derived' from ::matrix (e.g. class incanter.Matrix).",
   :namespace "incanter.internal",
   :wiki-url
   "http://incanter.github.com/incanter//internal-api.html#incanter.internal/is-matrix"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/01e8a07796e449199730123155573a99d6ab945f/modules/incanter-io/src/incanter/io.clj",
   :name "read-dataset",
   :file "modules/incanter-io/src/incanter/io.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/01e8a07796e449199730123155573a99d6ab945f/modules/incanter-io/src/incanter/io.clj#L40",
   :line 40,
   :var-type "function",
   :arglists
   ([filename
     &
     {:keys [delim keyword-headers quote skip header compress-delim],
      :or
      {delim \,,
       quote \",
       skip 0,
       header false,
       keyword-headers true}}]),
   :doc
   "\n  Returns a dataset read from a file or a URL.\n\n  Options:\n    :delim (default \\,), other options (\\tab \\space \\|  etc)\n    :quote (default \\\") character used for quoting strings\n    :skip (default 0) the number of lines to skip at the top of the file.\n    :header (default false) indicates the file has a header line\n    :compress-delim (default true if delim = \\space, false otherwise) means\n                    compress multiple adjacent delimiters into a single delimiter\n",
   :namespace "incanter.io",
   :wiki-url
   "http://incanter.github.com/incanter//io-api.html#incanter.io/read-dataset"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/fdc0bfd15d1319048cc5d7d3f680b99d9fc39103/modules/incanter-latex/src/incanter/latex.clj",
   :name "add-latex",
   :file "modules/incanter-latex/src/incanter/latex.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/fdc0bfd15d1319048cc5d7d3f680b99d9fc39103/modules/incanter-latex/src/incanter/latex.clj#L84",
   :line 84,
   :var-type "function",
   :arglists
   ([chart x y latex-str & {:keys [color], :or {color darkGray}}]),
   :doc
   " Adds an LaTeX equation annotation to the chart at the given x,y coordinates.\n\nArguments:\n  chart -- the chart to add the polygon to.\n  x, y -- the coordinates to place the image\n  latex-str -- a string of latex code\n\n\nOptions:\n  :color (default java.awt.Color/darkGray) -- the text color\n\n\nExamples:\n  (use '(incanter core charts stats latex))   \n\n    (doto (function-plot pdf-normal -3 3)\n      (add-latex 0 0.1 \"f(x)=\\\\frac{1}{\\\\sqrt{2\\\\pi \\\\sigma^2}} e^{\\\\frac{-(x - \\\\mu)^2}{2 \\\\sigma^2}}\")\n      view)",
   :namespace "incanter.latex",
   :wiki-url
   "http://incanter.github.com/incanter//latex-api.html#incanter.latex/add-latex"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/fdc0bfd15d1319048cc5d7d3f680b99d9fc39103/modules/incanter-latex/src/incanter/latex.clj",
   :name "add-latex-subtitle",
   :file "modules/incanter-latex/src/incanter/latex.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/fdc0bfd15d1319048cc5d7d3f680b99d9fc39103/modules/incanter-latex/src/incanter/latex.clj#L61",
   :line 61,
   :var-type "function",
   :arglists
   ([chart latex-str & {:keys [color], :or {color darkGray}}]),
   :doc
   " Adds the given LaTeX equation as a subtitle to the chart.\n\n\nOptions:\n  :color (default java.awt.Color/darkGray) -- the text color\n\n\nExamples:\n  (use '(incanter core charts stats latex))\n\n  (doto (function-plot pdf-normal -3 3)\n    (add-latex-subtitle \"f(x)=\\\\frac{1}{\\\\sqrt{2\\\\pi \\\\sigma^2}} e^{\\\\frac{-(x - \\\\mu)^2}{2 \\\\sigma^2}}\")\n    view)",
   :namespace "incanter.latex",
   :wiki-url
   "http://incanter.github.com/incanter//latex-api.html#incanter.latex/add-latex-subtitle"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/fdc0bfd15d1319048cc5d7d3f680b99d9fc39103/modules/incanter-latex/src/incanter/latex.clj",
   :name "latex",
   :file "modules/incanter-latex/src/incanter/latex.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/fdc0bfd15d1319048cc5d7d3f680b99d9fc39103/modules/incanter-latex/src/incanter/latex.clj#L13",
   :line 13,
   :var-type "function",
   :arglists
   ([latex-txt
     &
     {:keys [color background border],
      :or {color black, background white, border [5 5 5 5]}}]),
   :doc
   " Returns the given LaTeX equation rendered as an java.awt.Image.\n\nOptions:\n  :color (default java.awt.Color/black) -- the text color\n  :background (default java.awt.Clolor/white) -- the background color\n  :border (default [5 5 5 5]) -- image border\n\nExamples:\n  (use '(incanter core charts stats latex))\n\n  (def latex-img (latex \"\\\\frac{(a+b)^2} {(a-b)^2}\"))\n  (save latex-img \"/tmp/latex-example1.png\")\n  (view \"file:///tmp/latex-example1.png\")\n\n  (view (latex \"f(x)=\\\\frac {1} {\\\\sqrt {2\\\\pi \\\\sigma ^2}} e^{\\\\frac {-(x - \\\\mu)^2}{2 \\\\sigma ^2}}\"))\n\n  (view (latex \"\\\\begin{pmatrix}\n                 a & b & c \\\\\\\\\n                 d & e & f \\\\\\\\\n                 g & h & i\n                 \\\\end{pmatrix}\"))",
   :namespace "incanter.latex",
   :wiki-url
   "http://incanter.github.com/incanter//latex-api.html#incanter.latex/latex"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/fdc0bfd15d1319048cc5d7d3f680b99d9fc39103/modules/incanter-latex/src/incanter/latex.clj",
   :name "to-latex",
   :file "modules/incanter-latex/src/incanter/latex.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/fdc0bfd15d1319048cc5d7d3f680b99d9fc39103/modules/incanter-latex/src/incanter/latex.clj#L111",
   :line 111,
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
   "Convert an Incanter Matrix into a string of LaTeX commands to render it.\n\nOptions:\n  :mxtype (default pmatrix) -- the type of matrix to output, see LaTeX documentation for other options.\nExample:\n    (use '(incanter core latex))\n    (view (latex (to-latex (matrix [[1 0][0 1]]))))",
   :namespace "incanter.latex",
   :wiki-url
   "http://incanter.github.com/incanter//latex-api.html#incanter.latex/to-latex"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/077cde547363afe675fd8400de11dda0a4b47952/modules/incanter-mongodb/src/incanter/mongodb.clj",
   :name "fetch-dataset",
   :file "modules/incanter-mongodb/src/incanter/mongodb.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/077cde547363afe675fd8400de11dda0a4b47952/modules/incanter-mongodb/src/incanter/mongodb.clj#L72",
   :line 72,
   :var-type "function",
   :arglists ([& args]),
   :doc
   "Queries a MongoDB database, accepting the same arguments as \nsomnium.congomongo/fetch, but returning an Incanter dataset instead \nof a sequence of maps.\n\nExamples:\n\n   (use '(incanter core datasets mongodb))\n   (use 'somnium.congomongo)\n\n   ;; first load some sample data\n   (def data (get-dataset :airline-passengers))\n   (view data)\n\n   ;; a MongoDB server must be running on the localhost on the default port\n   ;; for the following steps.\n\n   (mongo! :db \"mydb\")\n   (mass-insert! :airline-data (:rows data))\n\n   ;; and then retrieve it\n   ;; notice that the retrieved data set has two additional columns,  :_id :_ns\n   (view (fetch-dataset :airline-data))",
   :namespace "incanter.mongodb",
   :wiki-url
   "http://incanter.github.com/incanter//mongodb-api.html#incanter.mongodb/fetch-dataset"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/077cde547363afe675fd8400de11dda0a4b47952/modules/incanter-mongodb/src/incanter/mongodb.clj",
   :name "insert-dataset",
   :file "modules/incanter-mongodb/src/incanter/mongodb.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/077cde547363afe675fd8400de11dda0a4b47952/modules/incanter-mongodb/src/incanter/mongodb.clj#L104",
   :line 104,
   :var-type "function",
   :arglists ([mongodb-coll dataset]),
   :doc
   "Inserts the rows of the Incanter dataset into the given MongoDB collection.\n\nExamples:\n\n(use '(incanter core datasets mongodb))\n(use 'somnium.congomongo)\n\n(def data (get-dataset :airline-passengers))\n(view data)\n\n;; a MongoDB server must be running on the localhost on the default port\n;; for the following steps.\n\n(mongo! :db \"mydb\")\n(mass-insert! :airline-data (:rows data))\n\n;; notice that the retrieved data set has two additional columns,  :_id :_ns\n(view (fetch-dataset :airline-data))",
   :namespace "incanter.mongodb",
   :wiki-url
   "http://incanter.github.com/incanter//mongodb-api.html#incanter.mongodb/insert-dataset"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/optimize.clj",
   :name "derivative",
   :file "modules/incanter-core/src/incanter/optimize.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/optimize.clj#L67",
   :line 67,
   :var-type "function",
   :arglists ([f & {:keys [dx], :or {dx 1.0E-4}}]),
   :doc
   "\nReturns a function that approximates the derivative of the given function.\n\nOptions:\n  :dx (default 0.0001)\n\nExamples:\n\n  (use '(incanter core optimize charts stats))\n  (defn cube [x] (* x x x))\n  (def cube-deriv (derivative cube))\n  (cube-deriv 2) ; value: 12.000600010022566\n  (cube-deriv 3) ; value: 27.00090001006572\n  (cube-deriv 4) ; value: 48.00120000993502\n\n  (def x (range -3 3 0.1))\n  (def plot (xy-plot x (map cube x)))\n  (view plot)\n  (add-lines plot x (map cube-deriv x))\n\n  ;; get the second derivative function\n  (def cube-deriv2 (derivative cube-deriv))\n  (add-lines plot x (map cube-deriv2 x))\n\n  ;; plot the normal pdf and its derivatives\n  (def plot (xy-plot x (pdf-normal x)))\n  (view plot)\n  (def pdf-deriv (derivative pdf-normal))\n  (add-lines plot x (pdf-deriv x))\n\n  ;; plot the second derivative function\n  (def pdf-deriv2 (derivative pdf-deriv))\n  (add-lines plot x (pdf-deriv2 x))",
   :namespace "incanter.optimize",
   :wiki-url
   "http://incanter.github.com/incanter//optimize-api.html#incanter.optimize/derivative"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/optimize.clj",
   :name "gradient",
   :file "modules/incanter-core/src/incanter/optimize.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/optimize.clj#L243",
   :line 243,
   :var-type "function",
   :arglists ([f start & {:keys [tol dx], :or {tol 1.0E-4}}]),
   :doc
   "\nReturns a function that calculates a 5-point approximation to\nthe gradient of the given function. The vector of start values are\nused to determine the number of parameters required by the function, and\nto scale the step-size. The generated function accepts a vector of\nparameter values and a vector of x data points and returns a matrix,\nwhere each row is the gradient evaluated at the corresponding x value.\n\nExamples:\n\n  (use '(incanter core optimize datasets charts))\n  (defn f [theta x]\n    (+ (nth theta 0)\n          (div (* x (- (nth theta 1) (nth theta 0)))\n               (+ (nth theta 2) x))))\n\n  (def start [20 200 100])\n  (def data (to-matrix (get-dataset :thurstone)))\n  (def x (sel data :cols 1))\n  (def y (sel data :cols 0))\n  ;; view the data\n  (view (scatter-plot x y))\n\n  (def grad (gradient f start))\n  (time (doall (grad start x)))",
   :namespace "incanter.optimize",
   :wiki-url
   "http://incanter.github.com/incanter//optimize-api.html#incanter.optimize/gradient"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/optimize.clj",
   :name "hessian",
   :file "modules/incanter-core/src/incanter/optimize.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/optimize.clj#L290",
   :line 290,
   :var-type "function",
   :arglists ([f start & {:keys [tol dx], :or {tol 1.0E-4}}]),
   :doc
   " Returns a function that calculates an approximation to the Hessian matrix\nof the given function. The vector of start values are used to determine\nthe number of parameters required by the function, and to scale the\nstep-size. The generated function accepts a vector of\nparameter values and a vector of x data points and returns a matrix,\nwhere each row with p*(p+1)/2 columns, one for each unique entry in\nthe Hessian evaluated at the corresponding x value.\n\nExamples:\n\n  (use '(incanter core optimize datasets charts))\n  (defn f [theta x]\n    (+ (nth theta 0)\n          (div (* x (- (nth theta 1) (nth theta 0)))\n               (+ (nth theta 2) x))))\n\n  (def start [20 200 100])\n  (def data (to-matrix (get-dataset :thurstone)))\n  (def x (sel data :cols 1))\n  (def y (sel data :cols 0))\n  ;; view the data\n  (view (scatter-plot x y))\n\n  (time (def hess (hessian f start)))\n  (time (doall (hess start x)))",
   :namespace "incanter.optimize",
   :wiki-url
   "http://incanter.github.com/incanter//optimize-api.html#incanter.optimize/hessian"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/optimize.clj",
   :name "integrate",
   :file "modules/incanter-core/src/incanter/optimize.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/optimize.clj#L25",
   :line 25,
   :var-type "function",
   :arglists ([f a b]),
   :doc
   " Integrate a function f from a to b\n\n\nExamples:\n  (defn f1 [x] 1)\n  (defn f2 [x] (Math/pow x 2))\n  (defn f3 [x] (* x (Math/exp (Math/pow x 2))))\n\n  (integrate f1 0 5)\n  (integrate f2 0 1)\n  (integrate f3 0 1)\n\n  ;; normal distribution\n  (def std 1)\n  (def mu 0)\n  (defn normal [x]\n    (/ 1\n      (* (* std (Math/sqrt (* 2 Math/PI)))\n        (Math/exp (/ (Math/pow (- (- x mu)) 2)\n        (* 2 (Math/pow std 2)))))))\n\n  (integrate normal 1.96 10)\n\n\nReference:\n  http://jng.imagine27.com/articles/2009-04-09-161839_integral_calculus_in_lambda_calculus_lisp.html\n  http://iam.elbenshira.com/archives/151_integral-calculus-in-haskell/",
   :namespace "incanter.optimize",
   :wiki-url
   "http://incanter.github.com/incanter//optimize-api.html#incanter.optimize/integrate"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/optimize.clj",
   :name "non-linear-model",
   :file "modules/incanter-core/src/incanter/optimize.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/47d21afb39a5e4075efd783a958c295990085d9d/modules/incanter-core/src/incanter/optimize.clj#L577",
   :line 577,
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
   "\nDetermine the nonlinear least-squares estimates of the\nparameters of a nonlinear model.\nBased on R's nls (non-linear least squares) function.\n\nArguments:\n  f -- model function, takes two arguments the first a list of parameters\n       that are to be estimated, and an x value.\n  y -- sequence of dependent data\n  x -- sequence of independent data\n  start -- start values for the parameters to be estimated\n\nOptions:\n  :method (default :gauss-newton) other option :newton-raphson\n  :tol (default 1E-5)\n  :max-iter (default 200)\n\nReturns: a hash-map containing the following fields:\n  :method -- the method used\n  :coefs  -- the parameter estimates\n  :gradient  -- the estimated gradient\n  :hessian -- the estimated hessian, if available\n  :iterations -- the number of iterations performed\n  :fitted -- the fitted values of y (i.e. y-hat)\n  :rss -- the residual sum-of-squares\n  :x -- the independent data values\n  :y -- the dependent data values\n\n\nExamples:\n\n  ;; example 1\n  (use '(incanter core optimize datasets charts))\n  ;; define the Michaelis-Menton model function\n  ;; y = a + (b - a)*x/(c + x)\n  (defn f [theta x]\n    (let [[a b c] theta]\n      (plus a (div (mult x (minus b a)) (plus c x)))))\n\n  (def start [20 200 100])\n  (def data (to-matrix (get-dataset :thurstone)))\n  (def x (sel data :cols 1))\n  (def y (sel data :cols 0))\n  ;; view the data\n  (def plot (scatter-plot x y))\n  (view plot)\n\n  (def nlm (non-linear-model f y x start))\n  (add-lines plot x (:fitted nlm))\n\n\n  ;; example 2\n  (use '(incanter core optimize datasets charts))\n  ;; Chwirut data set from NIST\n  ;; http://www.itl.nist.gov/div898/strd/nls/data/LINKS/DATA/Chwirut1.dat\n  (def data (to-matrix (get-dataset :chwirut)))\n  (def x (sel data :cols 1))\n  (def y (sel data :cols 0))\n\n  ;; define model function: y = exp(-b1*x)/(b2+b3*x) + e\n  (defn f [theta x]\n    (let [[b1 b2 b3] theta]\n      (div (exp (mult (minus b1) x)) (plus b2 (mult b3 x)))))\n\n  (def plot (scatter-plot x y :legend true))\n  (view plot)\n\n  ;; the newton-raphson algorithm fails to converge to the correct solution\n  ;; using first set of start values from NIST, but the default gauss-newton\n  ;; algorithm converges to the correct solution.\n\n  (def start1 [0.1 0.01 0.02])\n  (add-lines plot x (f start1 x))\n  (def nlm1 (non-linear-model f y x start1))\n  (add-lines plot x (:fitted nlm1))\n\n  ;; both algorithms converges with second set of start values from NIST\n  (def start2 [0.15 0.008 0.010])\n  (add-lines plot x (f start2 x))\n  (def nlm2 (non-linear-model f y x start2))\n  (add-lines plot x (:fitted nlm2))",
   :namespace "incanter.optimize",
   :wiki-url
   "http://incanter.github.com/incanter//optimize-api.html#incanter.optimize/non-linear-model"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/a7dff8a041ae1a123ebd35d05802af4cf88c8488/modules/incanter-pdf/src/incanter/pdf.clj",
   :name "save-pdf",
   :file "modules/incanter-pdf/src/incanter/pdf.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/a7dff8a041ae1a123ebd35d05802af4cf88c8488/modules/incanter-pdf/src/incanter/pdf.clj#L19",
   :line 19,
   :var-type "function",
   :arglists
   ([chart
     filename
     &
     {:keys [width height], :or {width 500, height 400}}]),
   :doc
   " Save a chart object as a pdf document.\n\nArguments:\n  chart\n  filename\n\nOptions:\n  :width (default 500)\n  :height (defualt 400)\n\nExamples:\n\n  (use '(incanter core charts pdf))\n  (save-pdf (function-plot sin -4 4) \"./pdf-chart.pdf\")",
   :namespace "incanter.pdf",
   :wiki-url
   "http://incanter.github.com/incanter//pdf-api.html#incanter.pdf/save-pdf"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/9b0206d6fa2ec0f986b801b8e3eebedbef618260/modules/incanter-core/src/incanter/som.clj",
   :name "som-batch-train",
   :file "modules/incanter-core/src/incanter/som.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/9b0206d6fa2ec0f986b801b8e3eebedbef618260/modules/incanter-core/src/incanter/som.clj#L136",
   :line 136,
   :var-type "function",
   :arglists
   ([data
     &
     {:keys [alpha beta cycles], :or {alpha 0.5, beta 3, cycles 10}}]),
   :doc
   " Performs BL-SOM (batch-learning self organizing map) learning on\nthe given data, returning a hashmap containing resulting BL-SOM\nvalues.\n\n\nArguments:\n  data -- data matrix\n\nOptions:\n  :cycles -- number of cycles of learning\n  :alpha -- initial value of alpha learning parameter\n  :beta -- initial value of beta learning parameter\n\n\nReturns: A hashmap containing the following fields:\n\n  :fit -- array of fitness values for each cycle of SOM learning\n  :weights -- hashmap of weight vectors, keyed by lattice indices\n  :sets -- hashmap mapping data elements to lattice nodes\n           (key lattice index) (value list of row indices from data)\n  :dims -- dimensions of SOM lattice\n  :data-means -- column means of input data matrix\n\n\nExamples:\n\n  (use '(incanter core som stats charts datasets))\n  (def data (to-matrix (sel (get-dataset :iris)\n                         :cols [\"Sepal.Length\" \"Sepal.Width\" \"Petal.Length\" \"Petal.Width\"])))\n\n  (def som (som-batch-train data :cycles 10 :alpha 0.5 :beta 3))\n\n  ;; plot the fitness for each cycle of training\n  (view (xy-plot (range (count (:fit som))) (:fit som)))\n  ;; view indices of data items in each cell\n  (:sets som)\n  ;; view the species in each cell\n  (doseq [rws (vals (:sets som))]\n    (println (sel (get-dataset :iris) :cols \"Species\" :rows rws) \\newline))\n\n  ;; plot the means of the data vectors in each cell/cluster\n  (def cell-means (map #(map mean (trans (sel data :rows ((:sets som) %)))) (keys (:sets som))))\n  (def x (range (ncol data)))\n  (doto (xy-plot x (first cell-means))\n        view\n        (add-lines x (nth cell-means 1))\n        (add-lines x (nth cell-means 2)))\n\n\nReferences:\n\n  http://en.wikipedia.org/wiki/Self-organizing_map",
   :namespace "incanter.som",
   :wiki-url
   "http://incanter.github.com/incanter//som-api.html#incanter.som/som-batch-train"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/24ba79422aa4080310c702f383b23278479bc549/modules/incanter-sql/src/incanter/sql.clj",
   :name "read-dataset",
   :file "modules/incanter-sql/src/incanter/sql.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/24ba79422aa4080310c702f383b23278479bc549/modules/incanter-sql/src/incanter/sql.clj#L33",
   :line 33,
   :var-type "function",
   :arglists ([cql-statement]),
   :doc "Lazily read a dataset for the given ClojureQL query.",
   :namespace "incanter.sql",
   :wiki-url
   "http://incanter.github.com/incanter//sql-api.html#incanter.sql/read-dataset"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "auto-correlation",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1556",
   :line 1556,
   :var-type "function",
   :arglists ([x lag] [x lag mean variance]),
   :doc
   "\nReturns the auto-correlation of x with given lag, mean, and variance.\nIf no mean or variance is provided, the they are calculated from x.\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/auto-correlation"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "benford-test",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2541",
   :line 2541,
   :var-type "function",
   :arglists ([coll]),
   :doc
   "\nPerforms Benford's Law test using chisq-test.\n\nArgument:\ncoll: -- a sequence of numbers\n\nReturns:\n  :X-sq -- the Pearson X-squared test statistics\n  :p-value -- the p-value for the test statistic\n  :df -- the degress of freedom\n\nReference:\nhttp://data-sorcery.org/2009/06/21/chi-square-goodness-of-fit/\nhttp://en.wikipedia.org/wiki/Benford%27s_Law",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/benford-test"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "bootstrap",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1714",
   :line 1714,
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
   " Returns a bootstrap sample of the given statistic on the given data.\n\nArguments:\n  data -- vector of data to resample from\n  statistic -- a function that returns a value given a vector of data\n\nOptions:\n  :size -- the number of bootstrap samples to return\n  :smooth -- (default false) smoothing option\n  :smooth-sd -- (default (/ (sqrt (count data)))) determines the standard\n                deviation of the noise to use for smoothing\n  :replacement -- (default true) determines if sampling of the data\n                  should be done with replacement\n\n\nReferences:\n  1. Clifford E. Lunneborg, Data Analysis by Resampling Concepts and Applications, 2000, pages 105-117\n  2. http://en.wikipedia.org/wiki/Bootstrapping_(statistics)\n\n\nExamples:\n\n  ;; example from Data Analysis by Resampling Concepts and Applications\n  ;; Clifford E. Lunneborg (pages 119-122)\n\n  (use '(incanter core stats charts))\n\n  ;; weights (in grams) of 50 randoincanter. sampled bags of preztels\n  (def weights [464 447 446 454 450 457 450 442\n                433 452 449 454 450 438 448 449\n                457 451 456 452 450 463 464 453\n                452 447 439 449 468 443 433 460\n                452 447 447 446 450 459 466 433\n                445 453 454 446 464 450 456 456\n                447 469])\n\n  ;; calculate the sample median, 450\n  (median weights)\n\n  ;; generate bootstrap sample\n  (def t* (bootstrap weights median :size 2000))\n\n  ;; view histogram of bootstrap histogram\n  (view (histogram t*))\n\n  ;; calculate the mean of the bootstrap median ~ 450.644\n  (mean t*)\n\n  ;; calculate the standard error ~ 1.083\n  (def se (sd t*))\n\n  ;; 90% standard normal CI ~ (448.219 451.781)\n  (plus (median weights) (mult (quantile-normal [0.05 0.95]) se))\n\n  ;; 90% symmetric percentile CI ~ (449.0 452.5)\n  (quantile t* :probs [0.05 0.95])\n\n\n  ;; 90% non-symmetric percentile CI ~ (447.5 451.0)\n  (minus (* 2 (median weights)) (quantile t* :probs [0.95 0.05]))\n\n  ;; calculate bias\n  (- (mean t*) (median weights)) ;; ~ 0.644\n\n  ;; example with smoothing\n  ;; Newcomb's speed of light data\n\n  (use '(incanter core stats charts))\n\n  ;; A numeric vector giving the Third Series of measurements of the\n  ;; passage time of light recorded by Newcomb in 1882. The given\n  ;; values divided by 1000 plus 24 give the time in millionths of a\n  ;; second for light to traverse a known distance. The 'true' value is\n  ;; now considered to be 33.02.\n\n  (def speed-of-light [28 -44  29  30  24  28  37  32  36  27  26  28  29\n                       26  27  22  23  20  25 25  36  23  31  32  24  27\n                       33  16  24  29  36  21  28  26  27  27  32  25 28\n                       24  40  21  31  32  28  26  30  27  26  24  32  29\n                       34  -2  25  19  36 29  30  22  28  33  39  25  16  23])\n\n  ;; view histogram of data to see outlier observations\n  (view (histogram speed-of-light :nbins 30))\n\n  (def samp (bootstrap speed-of-light median :size 10000))\n  (view (histogram samp :density true :nbins 30))\n  (mean samp)\n  (quantile samp :probs [0.025 0.975])\n\n  (def smooth-samp (bootstrap speed-of-light median :size 10000 :smooth true))\n  (view (histogram smooth-samp :density true :nbins 30))\n  (mean smooth-samp)\n  (quantile smooth-samp :probs [0.025 0.975])",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/bootstrap"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "category-col-summarizer",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2589",
   :line 2589,
   :var-type "function",
   :arglists ([col ds]),
   :doc
   "Returns a summarizer function which takes a category column and returns a list of the top 5 columns by volume, and a \ncount of remaining rows",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/category-col-summarizer"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-beta",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L465",
   :line 465,
   :var-type "function",
   :arglists
   ([x
     &
     {:keys [alpha beta lower-tail?],
      :or {alpha 1, beta 1, lower-tail? false}}]),
   :doc
   " Returns the Beta cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's pbeta function.\n\nOptions:\n  :alpha (default 1)\n  :beta (default 1)\n  :lower-tail (default true)\n\nSee also:\n    pdf-beta and sample-beta\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Beta.html\n    http://en.wikipedia.org/wiki/Beta_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-beta 0.5 :alpha 1 :beta 2)\n    (cdf-beta 0.5 :alpha 1 :beta 2 :lower-tail false)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-beta"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-binomial",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1126",
   :line 1126,
   :var-type "function",
   :arglists
   ([x
     &
     {:keys [size prob lower-tail?],
      :or {size 1, prob 1/2, lower-tail? true}}]),
   :doc
   " Returns the Binomial cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's pbinom\n\nOptions:\n  :size (default 1)\n  :prob (default 1/2)\n  :lower-tail (default true)\n\nSee also:\n    pdf-binomial and sample-binomial\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Binomial.html\n    http://en.wikipedia.org/wiki/Binomial_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-binomial 10 :prob 1/4 :size 20)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-binomial"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-chisq",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L726",
   :line 726,
   :var-type "function",
   :arglists
   ([x & {:keys [df lower-tail?], :or {df 1, lower-tail? true}}]),
   :doc
   " Returns the Chi Square cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's pchisq function.\n\nOptions:\n  :df (default 1)\n  :lower-tail (default true)\n\nSee also:\n    pdf-chisq and sample-chisq\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/ChiSquare.html\n    http://en.wikipedia.org/wiki/Chi_square_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-chisq 5.0 :df 2)\n    (cdf-chisq 5.0 :df 2 :lower-tail false)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-chisq"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-empirical",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1406",
   :line 1406,
   :var-type "function",
   :arglists ([x]),
   :doc
   " Returns a step-function representing the empirical cdf of the given data.\nEquivalent to R's ecdf function.\n\nThe following description is from the ecdf help in R: The e.c.d.f.\n(empirical cumulative distribution function) Fn is a step function\nwith jumps i/n at observation values, where i is the number of tied\nobservations at that value.  Missing values are ignored.\n\nFor observations 'x'= (x1,x2, ... xn), Fn is the fraction of\nobservations less or equal to t, i.e.,\n\nFn(t) = #{x_i <= t} / n  =  1/n sum(i=1,n) Indicator(xi <= t).\n\n\nExamples:\n  (use '(incanter core stats charts))\n\n  (def exam1 [192 160 183 136 162 165 181 188 150 163 192 164 184\n              189 183 181 188 191 190 184 171 177 125 192 149 188\n              154 151 159 141 171 153 169 168 168 157 160 190 166 150])\n\n  ;; the ecdf function returns an empirical cdf function for the given data\n  (def ecdf (cdf-empirical exam1))\n\n  ;; plot the data's empirical cdf\n  (view (scatter-plot exam1 (map ecdf exam1)))",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-empirical"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-exp",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L929",
   :line 929,
   :var-type "function",
   :arglists ([x & {:keys [rate], :or {rate 1}}]),
   :doc
   " Returns the Exponential cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's pexp\n\nOptions:\n  :rate (default 1)\n\nSee also:\n    pdf-exp and sample-exp\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Exponential.html\n    http://en.wikipedia.org/wiki/Exponential_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-exp 2.0 :rate 1/2)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-exp"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-f",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L138",
   :line 138,
   :var-type "function",
   :arglists
   ([x
     &
     {:keys [df1 df2 lower-tail?],
      :or {df1 1, df2 1, lower-tail? true}}]),
   :doc
   " Returns the F-distribution cdf of the given value, x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's pf function.\n\nOptions:\n  :df1 (default 1)\n  :df2 (default 1)\n  :lower-tail? (default true)\n\nSee also:\n    pdf-f and quantile-f\n\nReferences:\n    http://en.wikipedia.org/wiki/F_distribution\n    http://mathworld.wolfram.com/F-Distribution.html\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-f 1.0 :df1 5 :df2 2)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-f"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-gamma",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L639",
   :line 639,
   :var-type "function",
   :arglists
   ([x
     &
     {:keys [shape rate lower-tail?],
      :or {shape 1, rate 1, lower-tail? true}}]),
   :doc
   " Returns the Gamma cdf for the given value of x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's pgamma function.\n\nOptions:\n  :shape (default 1)\n  :rate (default 1)\n  :lower-tail (default true)\n\nSee also:\n    pdf-gamma and sample-gamma\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Gamma.html\n    http://en.wikipedia.org/wiki/Gamma_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-gamma 10 :shape 1 :rate 2)\n    (cdf-gamma 3 :shape 1 :lower-tail false)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-gamma"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-neg-binomial",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1346",
   :line 1346,
   :var-type "function",
   :arglists
   ([x
     &
     {:keys [size prob lower-tail?],
      :or {size 10, prob 1/2, lower-tail? true}}]),
   :doc
   " Returns the Negative Binomial cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's dnbinom\n\nOptions:\n  :size (default 10)\n  :prob (default 1/2)\n  :lower-tail? (default true)\n\nSee also:\n    cdf-neg-binomial and sample-neg-binomial\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/NegativeBinomial.html\n    http://en.wikipedia.org/wiki/Negative_binomial_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-neg-binomial 10 :prob 1/2 :size 20)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-neg-binomial"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-normal",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L209",
   :line 209,
   :var-type "function",
   :arglists ([x & {:keys [mean sd], :or {mean 0, sd 1}}]),
   :doc
   " Returns the Normal cdf of the given value, x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's pnorm function.\n\nOptions:\n  :mean (default 0)\n  :sd (default 1)\n\nSee also:\n    pdf-normal, quantile-normal, sample-normal\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Normal.html\n    http://en.wikipedia.org/wiki/Normal_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-normal 1.96 :mean -2 :sd (sqrt 0.5))",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-normal"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-poisson",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1261",
   :line 1261,
   :var-type "function",
   :arglists
   ([x
     &
     {:keys [lambda lower-tail?], :or {lambda 1, lower-tail? true}}]),
   :doc
   " Returns the Poisson cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's ppois\n\nOptions:\n  :lambda (default 1)\n  :lower-tail (default true)\n\nSee also:\n    cdf-poisson and sample-poisson\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Poisson.html\n    http://en.wikipedia.org/wiki/Poisson_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-poisson 5 :lambda 10)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-poisson"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-t",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L809",
   :line 809,
   :var-type "function",
   :arglists
   ([x & {:keys [df lower-tail?], :or {df 1, lower-tail? true}}]),
   :doc
   " Returns the Student's t cdf for the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's pt function.\n\nOptions:\n  :df (default 1)\n\nSee also:\n    pdf-t, quantile-t, and sample-t\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/StudentT.html\n    http://en.wikipedia.org/wiki/Student-t_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-t 1.2 :df 10)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-t"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-uniform",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L378",
   :line 378,
   :var-type "function",
   :arglists ([x & {:keys [min max], :or {min 0.0, max 1.0}}]),
   :doc
   " Returns the Uniform cdf of the given value of x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's punif function.\n\nOptions:\n  :min (default 0)\n  :max (default 1)\n\nSee also:\n    pdf-uniform and sample-uniform\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/DoubleUniform.html\n    http://en.wikipedia.org/wiki/Uniform_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-uniform 5)\n    (cdf-uniform 5 :min 1 :max 10)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-uniform"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "cdf-weibull",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L554",
   :line 554,
   :var-type "function",
   :arglists ([x & options]),
   :doc
   " Returns the Weibull cdf for the given value of x. It will return a sequence\nof values, if x is a sequence.\n\nOptions:\n  :shape (default 1)\n  :scale (default 1)\n\nSee also:\n    pdf-weibull and sample-weibull\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Distributions.html\n    http://en.wikipedia.org/wiki/Weibull_distribution\n    http://en.wikipedia.org/wiki/Cumulative_distribution_function\n\nExample:\n    (cdf-weibull 10 :shape 1 :scale 0.2)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cdf-weibull"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "chebyshev-distance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3110",
   :line 3110,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "In the limiting case of Lp reaching infinity we obtain the Chebyshev distance.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/chebyshev-distance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "chisq-test",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2378",
   :line 2378,
   :var-type "function",
   :arglists
   ([& {:keys [x y correct table probs freq], :or {correct true}}]),
   :doc
   "\nPerforms chi-squared contingency table tests and goodness-of-fit tests.\n\nIf the optional argument :y is not provided then a goodness-of-fit test\nis performed. In this case, the hypothesis tested is whether the\npopulation probabilities equal those in :probs, or are all equal if\n:probs is not given.\n\nIf :y is provided, it must be a sequence of integers that is the\nsame length as x. A contingency table is computed from x and :y.\nThen, Pearson's chi-squared test of the null hypothesis that the joint\ndistribution of the cell counts in a 2-dimensional contingency\ntable is the product of the row and column marginals is performed.\nBy default the Yates' continuity correction for 2x2 contingency\ntables is performed, this can be disabled by setting the :correct\noption to false.\n\n\nOptions:\n  :x -- a sequence of numbers.\n  :y -- a sequence of numbers\n  :table -- a contingency table. If one dimensional, the test is a goodness-of-fit\n  :probs (when (nil? y) -- (repeat n-levels (/ n-levels)))\n  :freq (default nil) -- if given, these are rescaled to probabilities\n  :correct (default true) -- use Yates' correction for continuity for 2x2 contingency tables\n\n\nReturns:\n  :X-sq -- the Pearson X-squared test statistics\n  :p-value -- the p-value for the test statistic\n  :df -- the degress of freedom\n\n\nExamples:\n  (use '(incanter core stats))\n  (chisq-test :x [1 2 3 2 3 2 4 3 5]) ;; X-sq 2.6667\n  ;; create a one-dimensional table of this data\n  (def table (matrix [1 3 3 1 1]))\n  (chisq-test :table table) ;; X-sq 2.6667\n  (chisq-test :table (trans table)) ;; throws exception\n\n  (chisq-test :x [1 0 0 0  1 1 1 0 0 1 0 0 1 1 1 1]) ;; 0.25\n\n  (use '(incanter core stats datasets))\n  (def math-prog (to-matrix (get-dataset :math-prog)))\n  (def x (sel math-prog :cols 1))\n  (def y (sel math-prog :cols 2))\n  (chisq-test :x x :y y) ;; X-sq = 1.24145, df=1, p-value = 0.26519\n  (chisq-test :x x :y y :correct false) ;; X-sq = 2.01094, df=1, p-value = 0.15617\n\n  (def table (matrix [[31 12] [9 8]]))\n  (chisq-test :table table) ;; X-sq = 1.24145, df=1, p-value = 0.26519\n  (chisq-test :table table :correct false) ;; X-sq = 2.01094, df=1, p-value = 0.15617\n  ;; use the detabulate function to create data rows corresponding to the table\n  (def detab (detabulate :table table))\n  (chisq-test :x (sel detab :cols 0) :y (sel detab :cols 1))\n\n  ;; look at the hair-eye-color data\n  ;; turn the count data for males into a contingency table\n  (def male (matrix (sel (get-dataset :hair-eye-color) :cols 3 :rows (range 16)) 4))\n  (chisq-test :table male) ;; X-sq = 41.280, df = 9, p-value = 4.44E-6\n  ;; turn the count data for females into a contingency table\n  (def female (matrix (sel (get-dataset :hair-eye-color) :cols 3 :rows (range 16 32)) 4))\n  (chisq-test :table female) ;; X-sq = 106.664, df = 9, p-value = 7.014E-19,\n\n\n  ;; supply probabilities to goodness-of-fit test\n  (def table [89 37 30 28 2])\n  (def probs [0.40 0.20 0.20 0.19 0.01])\n  (chisq-test :table table :probs probs) ;; X-sq = 5.7947, df = 4, p-value = 0.215\n\n  ;; use frequencies instead of probabilities\n  (def freq [40 20 20 15 5])\n  (chisq-test :table table :freq freq) ;; X-sq = 9.9901, df = 4, p-value = 0.04059\n\n\n\nReferences:\n  http://www.itl.nist.gov/div898/handbook/eda/section3/eda35f.htm\n  http://en.wikipedia.org/wiki/Pearson's_chi-square_test\n  http://en.wikipedia.org/wiki/Yates'_chi-square_test",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/chisq-test"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "choose-singletype-col-summarizer",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2597",
   :line 2597,
   :var-type "function",
   :arglists ([col-type]),
   :doc "Takes in a type, and returns a suitable column summarizer",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/choose-singletype-col-summarizer"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "correlation",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1532",
   :line 1532,
   :var-type "function",
   :arglists ([x y] [mat]),
   :doc
   "\nReturns the sample correlation of x and y, or the correlation\nmatrix of the given matrix.\n\nExamples:\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Correlation",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/correlation"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "correlation-linearity-test",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2881",
   :line 2881,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Correlation_ratio\n\nIt is worth noting that if the relationship between values of  and values of overline y_x is linear (which is certainly true when there are only two possibilities for x) this will give the same result as the square of the correlation coefficient, otherwise the correlation ratio will be larger in magnitude. It can therefore be used for judging non-linear relationships.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/correlation-linearity-test"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "correlation-ratio",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2843",
   :line 2843,
   :var-type "function",
   :arglists ([& xs]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Correlation_ratio\n\nIn statistics, the correlation ratio is a measure of the relationship between the statistical dispersion within individual categories and the dispersion across the whole population or sample. i.e. the weighted variance of the category means divided by the variance of all samples.\n\nExample\n\nSuppose there is a distribution of test scores in three topics (categories):\n\n    * Algebra: 45, 70, 29, 15 and 21 (5 scores)\n    * Geometry: 40, 20, 30 and 42 (4 scores)\n    * Statistics: 65, 95, 80, 70, 85 and 73 (6 scores).\n\nThen the subject averages are 36, 33 and 78, with an overall average of 52.\n\nThe sums of squares of the differences from the subject averages are 1952 for Algebra, 308 for Geometry and 600 for Statistics, adding to 2860, while the overall sum of squares of the differences from the overall average is 9640. The difference between these of 6780 is also the weighted sum of the square of the differences between the subject averages and the overall average:\n\n    5(36 − 52)2 + 4(33 − 52)2 + 6(78 − 52)2 = 6780\n\nThis gives\n\n    eta^2 =6780/9640=0.7033\n\nsuggesting that most of the overall dispersion is a result of differences between topics, rather than within topics. Taking the square root\n\n    eta = sqrt 6780/9640=0.8386\n\nObserve that for η = 1 the overall sample dispersion is purely due to dispersion among the categories and not at all due to dispersion within the individual categories. For a quick comprehension simply imagine all Algebra, Geometry, and Statistics scores being the same respectively, e.g. 5 times 36, 4 times 33, 6 times 78.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/correlation-ratio"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "cosine-similarity",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3130",
   :line 3130,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Cosine_similarity\nhttp://www.appliedsoftwaredesign.com/cosineSimilarityCalculator.php\n\nThe Cosine Similarity of two vectors a and b is the ratio: a dot b / ||a|| ||b||\n\nLet d1 = {2 4 3 1 6}\nLet d2 = {3 5 1 2 5}\n\nCosine Similarity (d1, d2) =  dot(d1, d2) / ||d1|| ||d2||\n\ndot(d1, d2) = (2)*(3) + (4)*(5) + (3)*(1) + (1)*(2) + (6)*(5) = 61\n\n||d1|| = sqrt((2)^2 + (4)^2 + (3)^2 + (1)^2 + (6)^2) = 8.12403840464\n\n||d2|| = sqrt((3)^2 + (5)^2 + (1)^2 + (2)^2 + (5)^2) = 8\n\nCosine Similarity (d1, d2) = 61 / (8.12403840464) * (8)\n                           = 61 / 64.9923072371\n                           = 0.938572618717",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cosine-similarity"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "covariance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1481",
   :line 1481,
   :var-type "function",
   :arglists ([x y] [mat]),
   :doc
   "\nReturns the sample covariance of x and y.\n\nExamples:\n  ;; create some data that covaries\n  (def x (sample-normal 100))\n  (def err (sample-normal 100))\n  (def y (plus (mult 5 x) err))\n  ;; estimate the covariance of x and y\n  (covariance x y)\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Covariance",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/covariance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "cumulative-mean",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1839",
   :line 1839,
   :var-type "function",
   :arglists ([coll]),
   :doc
   " Returns a sequence of cumulative means for the given collection. For instance\n  The first value equals the first value of the argument, the second value is\n  the mean of the first two arguments, the third is the mean of the first three\n  arguments, etc.\n\n  Examples:\n    (cumulative-mean (sample-normal 100))\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/cumulative-mean"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "detabulate",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2702",
   :line 2702,
   :var-type "function",
   :arglists ([& {:keys [table row-labels col-labels]}]),
   :doc
   " Take a contingency table of counts and returns a matrix of observations.\n\nExamples:\n\n  (use '(incanter core stats datasets))\n\n  (def by-gender (group-on (get-dataset :hair-eye-color) 2))\n  (def table (matrix (sel (first by-gender) :cols 3) 4))\n\n  (detabulate :table table)\n  (tabulate (detabulate :table table))\n\n  ;; example 2\n  (def data (matrix [[1 0]\n                     [1 1]\n                     [1 1]\n                     [1 0]\n                     [0 0]\n                     [1 1]\n                     [1 1]\n                     [1 0]\n                     [1 1]]))\n  (tabulate data)\n\n  (tabulate (detabulate :table (:table (tabulate data))))",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/detabulate"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "dice-coefficient",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3208",
   :line 3208,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Dice%27s_coefficient\nDice's coefficient (also known as the Dice coefficient) is a similarity measure related to the Jaccard index.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/dice-coefficient"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "dice-coefficient-str",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3234",
   :line 3234,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Dice%27s_coefficient\n\nWhen taken as a string similarity measure, the coefficient may be calculated for two strings, x and y using bigrams.  here nt is the number of character bigrams found in both strings, nx is the number of bigrams in string x and ny is the number of bigrams in string y. For example, to calculate the similarity between:\n\n    night\n    nacht\n\nWe would find the set of bigrams in each word:\n\n    {ni,ig,gh,ht}\n    {na,ac,ch,ht}\n\nEach set has four elements, and the intersection of these two sets has only one element: ht.\n\nPlugging this into the formula, we calculate, s = (2 · 1) / (4 + 4) = 0.25.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/dice-coefficient-str"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "discordant-pairs",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2990",
   :line 2990,
   :var-type "function",
   :arglists ([a b]),
   :doc "http://en.wikipedia.org/wiki/Discordant_pairs",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/discordant-pairs"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "euclidean-distance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3103",
   :line 3103,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "http://en.wikipedia.org/wiki/Euclidean_distance\n\nthe Euclidean distance or Euclidean metric is the ordinary distance between two points that one would measure with a ruler, and is given by the Pythagorean formula. By using this formula as distance, Euclidean space (or even any inner product space) becomes a metric space. The associated norm is called the Euclidean norm. Older literature refers to the metric as Pythagorean metric.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/euclidean-distance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "f-test",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2253",
   :line 2253,
   :var-type "function",
   :arglists ([x y]),
   :doc
   "\nTest for different variances between 2 samples\n\n  Argument:\n    x : 1st sample to test\n    y : 2nd sample to test\n\n  Options:\n\n  References:\n    http://en.wikipedia.org/wiki/F-test\n    http://people.richland.edu/james/lecture/m170/ch13-f.html",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/f-test"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "gamma-coefficient",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3013",
   :line 3013,
   :var-type "function",
   :arglists ([]),
   :doc
   "\nhttp://www.statsdirect.com/help/nonparametric_methods/kend.htm\n\nThe gamma coefficient is given as a measure of association that is highly resistant to tied data (Goodman and Kruskal, 1963):",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/gamma-coefficient"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "hamming-distance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3259",
   :line 3259,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "http://en.wikipedia.org/wiki/Hamming_distance\n\nIn information theory, the Hamming distance between two strings of equal length is the number of positions at which the corresponding symbols are different. Put another way, it measures the minimum number of substitutions required to change one string into the other, or the number of errors that transformed one string into the other.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/hamming-distance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "indicator",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L71",
   :line 71,
   :var-type "function",
   :arglists ([pred coll]),
   :doc
   "\nReturns a sequence of ones and zeros, where ones\nare returned when the given predicate is true for\ncorresponding element in the given collection, and\nzero otherwise.\n\nExamples:\n  (use 'incanter.stats)\n\n  (indicator #(neg? %) (sample-normal 10))\n\n  ;; return the sum of the positive values in a normal sample\n  (def x (sample-normal 100))\n  (sum (mult x (indicator #(pos? %) x)))",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/indicator"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "jaccard-distance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3197",
   :line 3197,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Jaccard_index\n\nThe Jaccard distance, which measures dissimilarity between sample sets, is complementary to the Jaccard coefficient and is obtained by subtracting the Jaccard coefficient from 1, or, equivalently, by dividing the difference of the sizes of the union and the intersection of two sets by the size of the union.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/jaccard-distance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "jaccard-index",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3186",
   :line 3186,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Jaccard_index\n\nThe Jaccard index, also known as the Jaccard similarity coefficient (originally coined coefficient de communauté by Paul Jaccard), is a statistic used for comparing the similarity and diversity of sample sets.\n\nThe Jaccard coefficient measures similarity between sample sets, and is defined as the size of the intersection divided by the size of the union of the sample sets.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/jaccard-index"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "kendalls-tau",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2941",
   :line 2941,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Kendall_tau_rank_correlation_coefficient\nhttp://www.statsdirect.com/help/nonparametric_methods/kend.htm\nhttp://mail.scipy.org/pipermail/scipy-dev/2009-March/011589.html\nbest explanation and example is in \"cluster analysis for researchers\" page 165.\nhttp://www.amazon.com/Cluster-Analysis-Researchers-Charles-Romesburg/dp/1411606175",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/kendalls-tau"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "kendalls-w",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3022",
   :line 3022,
   :var-type "function",
   :arglists ([]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Kendall%27s_W\nhttp://faculty.chass.ncsu.edu/garson/PA765/friedman.htm\n\nSuppose that object i is given the rank ri,j by judge number j, where there are in total n objects and m judges. Then the total rank given to object i is\n\n    Ri = sum Rij\n\nand the mean value of these total ranks is\n\n    Rbar = 1/2 m (n + 1)\n\nThe sum of squared deviations, S, is defined as\n\n    S=sum1-n (Ri - Rbar)\n\nand then Kendall's W is defined as[1]\n\n    W= 12S / m^2(n^3-n)\n\nIf the test statistic W is 1, then all the survey respondents have been unanimous, and each respondent has assigned the same order to the list of concerns. If W is 0, then there is no overall trend of agreement among the respondents, and their responses may be regarded as essentially random. Intermediate values of W indicate a greater or lesser degree of unanimity among the various responses.\n\nLegendre[2] discusses a variant of the W statistic which accommodates ties in the rankings and also describes methods of making significance tests based on W.\n\n [{:observation [1 2 3]} {} ... {}] -> W",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/kendalls-w"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "kurtosis",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1593",
   :line 1593,
   :var-type "function",
   :arglists ([x]),
   :doc
   "\nReturns the kurtosis of the data, x. \"Kurtosis is a measure of the \"peakedness\"\nof the probability distribution of a real-valued random variable. Higher kurtosis\nmeans more of the variance is due to infrequent extreme deviations, as opposed to\nfrequent modestly-sized deviations.\" (Wikipedia)\n\nExamples:\n\n  (kurtosis (sample-normal 100000)) ;; approximately 0\n  (kurtosis (sample-gamma 100000)) ;; approximately 6\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Kurtosis",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/kurtosis"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "lee-distance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3274",
   :line 3274,
   :var-type "function",
   :arglists ([a b q]),
   :doc
   "http://en.wikipedia.org/wiki/Lee_distance\n\nIn coding theory, the Lee distance is a distance between two strings x1x2...xn and y1y2...yn of equal length n over the q-ary alphabet {0,1,…,q-1} of size q >= 2. It is metric.\n\nIf q = 2 or q = 3 the Lee distance coincides with the Hamming distance.\n\nThe metric space induced by the Lee distance is a discrete analog of the elliptic space.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/lee-distance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "levenshtein-distance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3321",
   :line 3321,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Levenshtein_distance\n\ninternal representation is a table d with m+1 rows and n+1 columns\n\nwhere m is the length of a and m is the length of b.\n\nIn information theory and computer science, the Levenshtein distance is a metric for measuring the amount of difference between two sequences (i.e., the so called edit distance). The Levenshtein distance between two strings is given by the minimum number of operations needed to transform one string into the other, where an operation is an insertion, deletion, or substitution of a single character.\n\nFor example, the Levenshtein distance between \"kitten\" and \"sitting\" is 3, since the following three edits change one into the other, and there is no way to do it with fewer than three edits:\n\n   1. kitten → sitten (substitution of 's' for 'k')\n   2. sitten → sittin (substitution of 'i' for 'e')\n   3. sittin → sitting (insert 'g' at the end).\n\nThe Levenshtein distance has several simple upper and lower bounds that are useful in applications which compute many of them and compare them. These include:\n\n    * It is always at least the difference of the sizes of the two strings.\n    * It is at most the length of the longer string.\n    * It is zero if and only if the strings are identical.\n    * If the strings are the same size, the Hamming distance is an upper bound on the Levenshtein distance.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/levenshtein-distance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "linear-model",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2013",
   :line 2013,
   :var-type "function",
   :arglists ([y x & {:keys [intercept], :or {intercept true}}]),
   :doc
   "\nReturns the results of performing a OLS linear regression of y on x.\n\nArguments:\n  y is a vector (or sequence) of values for the dependent variable\n  x is a vector or matrix of values for the independent variables\n\nOptions:\n  :intercept (default true) indicates weather an intercept term should be included\n\nReturns:\n  a map, of type ::linear-model, containing:\n    :design-matrix -- a matrix containing the independent variables, and an intercept columns\n    :coefs -- the regression coefficients\n    :t-tests -- t-test values of coefficients\n    :t-probs -- p-values for t-test values of coefficients\n    :coefs-ci -- 95% percentile confidence interval\n    :fitted -- the predicted values of y\n    :residuals -- the residuals of each observation\n    :std-errors -- the standard errors of the coeffients\n    :sse -- the sum of squared errors, also called the residual sum of squares\n    :ssr -- the regression sum of squares, also called the explained sum of squares\n    :sst -- the total sum of squares (proportional to the sample variance)\n    :r-square -- coefficient of determination\n\nExamples:\n  (use '(incanter core stats datasets charts))\n  (def iris (to-matrix (get-dataset :iris) :dummies true))\n  (def y (sel iris :cols 0))\n  (def x (sel iris :cols (range 1 6)))\n  (def iris-lm (linear-model y x)) ; with intercept term\n\n  (keys iris-lm) ; see what fields are included\n  (:coefs iris-lm)\n  (:sse iris-lm)\n  (quantile (:residuals iris-lm))\n  (:r-square iris-lm)\n  (:adj-r-square iris-lm)\n  (:f-stat iris-lm)\n  (:f-prob iris-lm)\n  (:df iris-lm)\n\n  (def x1 (range 0.0 3 0.1))\n  (view (xy-plot x1 (cdf-f x1 :df1 4 :df2 144)))\n\n\nReferences:\n  http://en.wikipedia.org/wiki/OLS_Regression\n  http://en.wikipedia.org/wiki/Coefficient_of_determination",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/linear-model"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "mahalanobis-distance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3422",
   :line 3422,
   :var-type "function",
   :arglists ([x & {:keys [y W centroid]}]),
   :doc
   "Returns the Mahalanobis distance between x, which is \n  either a vector or matrix of row vectors, and the \n  centroid of the observations in the matrix :y.\n \n Arguments:\n   x -- either a vector or a matrix of row vectors\n \n Options:\n   :y -- Defaults to x, must be a matrix of row vectors which will be used to calculate a centroid\n   :W -- Defaults to (solve (covariance y)), if an identity matrix is provided, the mahalanobis-distance\n         function will be equal to the Euclidean distance.\n   :centroid -- Defaults to (map mean (trans y))\n\n\n References:\n   http://en.wikipedia.org/wiki/Mahalanobis_distance\n\n\n Examples:\n\n   (use '(incanter core stats charts))\n\n   ;; generate some multivariate normal data with a single outlier.\n   (def data (bind-rows\n               (bind-columns \n                 (sample-mvn 100 \n                             :sigma (matrix [[1 0.9] \n                                             [0.9 1]])))\n               [-1.75 1.75]))\n\n   ;; view a scatter plot of the data\n   (let [[x y] (trans data)]\n     (doto (scatter-plot x y)\n       (add-points [(mean x)] [(mean y)])\n       (add-pointer -1.75 1.75 :text \"Outlier\")\n       (add-pointer (mean x) (mean y) :text \"Centroid\")\n       view))\n\n   ;; calculate the distances of each point from the centroid.\n   (def dists (mahalanobis-distance data))\n   ;; view a bar-chart of the distances\n   (view (bar-chart (range 102) dists))\n\n   ;; Now contrast with the Euclidean distance.\n   (def dists (mahalanobis-distance data :W (matrix [[1 0] [0 1]])))\n   ;; view a bar-chart of the distances\n   (view (bar-chart (range 102) dists))\n\n\n   ;; another example\n   (mahalanobis-distance [-1.75 1.75] :y data)\n   (mahalanobis-distance [-1.75 1.75] \n                     :y data \n                     :W (matrix [[1 0] \n                                 [0 1]]))\n",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/mahalanobis-distance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "manhattan-distance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3119",
   :line 3119,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "http://en.wikipedia.org/wiki/Manhattan_distance\n\nusual metric of Euclidean geometry is replaced by a new metric in which the distance between two points is the sum of the (absolute) differences of their coordinates. The taxicab metric is also known as rectilinear distance, L1 distance or l1 norm (see Lp space), city block distance, Manhattan distance, or Manhattan length",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/manhattan-distance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "mean",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1446",
   :line 1446,
   :var-type "function",
   :arglists ([x]),
   :doc
   "\nReturns the mean of the data, x.\n\nExamples:\n  (mean (sample-normal 100))\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Mean",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/mean"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "median",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1575",
   :line 1575,
   :var-type "function",
   :arglists ([x]),
   :doc
   "\nReturns the median of the data, x.\n\nExamples:\n  (median (sample-normal 100))\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Median",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/median"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "minkowski-distance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3083",
   :line 3083,
   :var-type "function",
   :arglists ([a b p]),
   :doc
   "http://en.wikipedia.org/wiki/Minkowski_distance\nhttp://en.wikipedia.org/wiki/Lp_space\n\nThe Minkowski distance is a metric on Euclidean space which can be considered as a generalization of both the Euclidean distance and the Manhattan distance.\n\nMinkowski distance is typically used with p being 1 or 2. The latter is the Euclidean distance, while the former is sometimes known as the Manhattan distance.\n\nIn the limiting case of p reaching infinity we obtain the Chebyshev distance.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/minkowski-distance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "n-grams",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3220",
   :line 3220,
   :var-type "function",
   :arglists ([n s]),
   :doc
   "returns a set of the unique n-grams in a string.\n\nthis is using actual sets here, discards duplicate n-grams?",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/n-grams"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "normalized-kendall-tau-distance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3000",
   :line 3000,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Kendall_tau_distance\n\nKendall tau distance is the total number of discordant pairs.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/normalized-kendall-tau-distance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "numeric-col-summarizer",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2582",
   :line 2582,
   :var-type "function",
   :arglists ([col ds]),
   :doc
   "Returns a summarizer function which takes a purely numeric column with no non-numeric values",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/numeric-col-summarizer"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "odds-ratio",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2823",
   :line 2823,
   :var-type "function",
   :arglists ([p1 p2]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Odds_ratio\n\nDefinition in terms of group-wise odds\n\nThe odds ratio is the ratio of the odds of an event occurring in one group to the odds of it occurring in another group, or to a sample-based estimate of that ratio.\n\n\nSuppose that in a sample of 100 men, 90 have drunk wine in the previous week, while in a sample of 100 women only 20 have drunk wine in the same period. The odds of a man drinking wine are 90 to 10, or 9:1, while the odds of a woman drinking wine are only 20 to 80, or 1:4 = 0.25:1. The odds ratio is thus 9/0.25, or 36, showing that men are much more likely to drink wine than women. \n\nRelation to statistical independence\n\nIf X and Y are independent, their joint probabilities can be expressed in terms of their marginal probabilities.  In this case, the odds ratio equals one, and conversely the odds ratio can only equal one if the joint probabilities can be factored in this way. Thus the odds ratio equals one if and only if X and Y are independent.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/odds-ratio"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "pairings",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2977",
   :line 2977,
   :var-type "function",
   :arglists ([a b]),
   :doc "confusing ass name.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pairings"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "pairs",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2965",
   :line 2965,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "returns unique pairs of a and b where members of a and b can not be paired with the corresponding slot in the other list.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pairs"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-beta",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L439",
   :line 439,
   :var-type "function",
   :arglists ([x & {:keys [alpha beta], :or {alpha 1, beta 1}}]),
   :doc
   " Returns the Beta pdf of the given value of x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's dbeta function.\n\nOptions:\n  :alpha (default 1)\n  :beta (default 1)\n\nSee also:\n    cdf-beta and sample-beta\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Beta.html\n    http://en.wikipedia.org/wiki/Beta_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-beta 0.5 :alpha 1 :beta 2)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-beta"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-binomial",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1099",
   :line 1099,
   :var-type "function",
   :arglists ([x & {:keys [size prob], :or {size 1, prob 1/2}}]),
   :doc
   " Returns the Binomial pdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's dbinom\n\nOptions:\n  :size (default 1)\n  :prob (default 1/2)\n\nSee also:\n    cdf-binomial and sample-binomial\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Binomial.html\n    http://en.wikipedia.org/wiki/Binomial_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-binomial 10 :prob 1/4 :size 20)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-binomial"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-chisq",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L700",
   :line 700,
   :var-type "function",
   :arglists ([x & {:keys [df], :or {df 1}}]),
   :doc
   " Returns the Chi Square pdf of the given value of x.  It will return a sequence\nof values, if x is a sequence. Same as R's dchisq function.\n\nOptions:\n  :df (default 1)\n\nSee also:\n    cdf-chisq and sample-chisq\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/ChiSquare.html\n    http://en.wikipedia.org/wiki/Chi_square_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-chisq 5.0 :df 2)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-chisq"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-exp",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L904",
   :line 904,
   :var-type "function",
   :arglists ([x & {:keys [rate], :or {rate 1}}]),
   :doc
   " Returns the Exponential pdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's dexp\n\nOptions:\n  :rate (default 1)\n\nSee also:\n    cdf-exp and sample-exp\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Exponential.html\n    http://en.wikipedia.org/wiki/Exponential_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-exp 2.0 :rate 1/2)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-exp"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-f",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L104",
   :line 104,
   :var-type "function",
   :arglists ([x & {:keys [df1 df2], :or {df1 1, df2 1}}]),
   :doc
   " Returns the F pdf of the given value, x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's df function.\n\nOptions:\n  :df1 (default 1)\n  :df2 (default 1)\n\nSee also:\n    cdf-f and quantile-f\n\nReferences:\n    http://en.wikipedia.org/wiki/F_distribution\n    http://mathworld.wolfram.com/F-Distribution.html\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-f 1.0 :df1 5 :df2 2)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-f"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-gamma",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L612",
   :line 612,
   :var-type "function",
   :arglists ([x & {:keys [shape rate], :or {shape 1, rate 1}}]),
   :doc
   " Returns the Gamma pdf for the given value of x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's dgamma function.\n\nOptions:\n  :shape (default 1)\n  :rate (default 1)\n\nSee also:\n    cdf-gamma and sample-gamma\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Gamma.html\n    http://en.wikipedia.org/wiki/Gamma_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-gamma 10 :shape 1 :rate 2)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-gamma"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-neg-binomial",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1318",
   :line 1318,
   :var-type "function",
   :arglists ([x & {:keys [size prob], :or {size 10, prob 1/2}}]),
   :doc
   " Returns the Negative Binomial pdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's dnbinom\n\nOptions:\n  :size (default 10)\n  :prob (default 1/2)\n\nSee also:\n    cdf-neg-binomial and sample-neg-binomial\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/NegativeBinomial.html\n    http://en.wikipedia.org/wiki/Negative_binomial_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-neg-binomial 10 :prob 1/2 :size 20)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-neg-binomial"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-normal",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L182",
   :line 182,
   :var-type "function",
   :arglists ([x & {:keys [mean sd], :or {mean 0, sd 1}}]),
   :doc
   " Returns the Normal pdf of the given value, x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's dnorm function.\n\nOptions:\n  :mean (default 0)\n  :sd (default 1)\n\nSee also:\n    cdf-normal, quantile-normal, sample-normal\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Normal.html\n    http://en.wikipedia.org/wiki/Normal_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-normal 1.96 :mean -2 :sd (sqrt 0.5))",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-normal"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-poisson",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1235",
   :line 1235,
   :var-type "function",
   :arglists ([x & {:keys [lambda], :or {lambda 1}}]),
   :doc
   " Returns the Poisson pdf of the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's dpois\n\nOptions:\n  :lambda (default 1)\n\nSee also:\n    cdf-poisson and sample-poisson\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Poisson.html\n    http://en.wikipedia.org/wiki/Poisson_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-poisson 5 :lambda 10)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-poisson"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-t",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L784",
   :line 784,
   :var-type "function",
   :arglists ([x & {:keys [df], :or {df 1}}]),
   :doc
   " Returns the Student's t pdf for the given value of x. It will return a sequence\nof values, if x is a sequence. Same as R's dt function.\n\nOptions:\n  :df (default 1)\n\nSee also:\n    cdf-t, quantile-t, and sample-t\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/StudentT.html\n    http://en.wikipedia.org/wiki/Student-t_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-t 1.2 :df 10)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-t"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-uniform",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L351",
   :line 351,
   :var-type "function",
   :arglists ([x & {:keys [min max], :or {min 0.0, max 1.0}}]),
   :doc
   " Returns the Uniform pdf of the given value of x. It will return a sequence\nof values, if x is a sequence. This is equivalent to R's dunif function.\n\nOptions:\n  :min (default 0)\n  :max (default 1)\n\nSee also:\n    cdf-uniform and sample-uniform\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/DoubleUniform.html\n    http://en.wikipedia.org/wiki/Uniform_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-uniform 5)\n    (pdf-uniform 5 :min 1 :max 10)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-uniform"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "pdf-weibull",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L526",
   :line 526,
   :var-type "function",
   :arglists ([x & options]),
   :doc
   " Returns the Weibull pdf for the given value of x. It will return a sequence\nof values, if x is a sequence.\n\nOptions:\n    :scale (default 1)\n    :shape (default 1)\n\nSee also:\n    cdf-weibull and sample-weibull\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Distributions.html\n    http://en.wikipedia.org/wiki/Weibull_distribution\n    http://en.wikipedia.org/wiki/Probability_density_function\n\nExample:\n    (pdf-weibull 2 :alpha 1 :beta 0.5)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/pdf-weibull"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "permute",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1879",
   :line 1879,
   :var-type "function",
   :arglists ([x] [x y]),
   :doc
   " If provided a single argument, returns a permuted version of the\ngiven collection. (permute x) is the same as (sample x).\n\nIf provided two arguments, returns two lists that are permutations\nacross the given collections. In other words, each of the new collections\nwill contain elements from both of the given collections. Useful for\npermutation tests or randomization tests.\n\nExamples:\n\n  (permute (range 10))\n  (permute (range 10) (range 10 20))",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/permute"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "predict",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2811",
   :line 2811,
   :var-type "function",
   :arglists ([model x]),
   :doc
   "Takes a linear-model and an x value (either a scalar or vector)\nand returns the predicted value based on the linear-model.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/predict"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "principal-components",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2644",
   :line 2644,
   :var-type "function",
   :arglists ([x & options]),
   :doc
   "\nPerforms a principal components analysis on the given data matrix.\nEquivalent to R's prcomp function.\n\nReturns:\n  A map with the following fields:\n  :std-dev -- the standard deviations of the principal components\n      (i.e. the square roots of the eigenvalues of the correlation\n      matrix, though the calculation is actually done with the\n      singular values of the data matrix.\n  :rotation -- the matrix of variable loadings (i.e. a matrix\n      whose columns contain the eigenvectors).\n\n\nExamples:\n\n  (use '(incanter core stats charts datasets))\n  ;; load the iris dataset\n  (def iris (to-matrix (get-dataset :iris)))\n  ;; run the pca\n  (def pca (principal-components (sel iris :cols (range 4))))\n  ;; extract the first two principal components\n  (def pc1 (sel (:rotation pca) :cols 0))\n  (def pc2 (sel (:rotation pca) :cols 1))\n\n  ;; project the first four dimension of the iris data onto the first\n  ;; two principal components\n  (def x1 (mmult (sel iris :cols (range 4)) pc1))\n  (def x2 (mmult (sel iris :cols (range 4)) pc2))\n\n  ;; now plot the transformed data, coloring each species a different color\n  (doto (scatter-plot (sel x1 :rows (range 50)) (sel x2 :rows (range 50))\n                      :x-label \"PC1\" :y-label \"PC2\" :title \"Iris PCA\")\n        (add-points (sel x1 :rows (range 50 100)) (sel x2 :rows (range 50 100)))\n        (add-points (sel x1 :rows (range 100 150)) (sel x2 :rows (range 100 150)))\n        view)\n\n\n  ;; alternatively, the :group-by option can be used in scatter-plot\n  (view (scatter-plot x1 x2\n                      :group-by (sel iris :cols 4)\n                      :x-label \"PC1\" :y-label \"PC2\" :title \"Iris PCA\"))\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Principal_component_analysis",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/principal-components"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "product-marginal-test",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3064",
   :line 3064,
   :var-type "function",
   :arglists ([j]),
   :doc
   "the joint PMF of independent variables is equal to the product of their marginal PMFs.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/product-marginal-test"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "quantile",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1633",
   :line 1633,
   :var-type "function",
   :arglists
   ([x
     &
     {:keys [probs],
      :or
      {probs
       (DoubleArrayList. (double-array [0.0 0.25 0.5 0.75 1.0]))}}]),
   :doc
   "\nReturns the quantiles of the data, x. By default it returns the min,\n25th-percentile, 50th-percentile, 75th-percentile, and max value.\n\nOptions:\n  :probs (default [0.0 0.25 0.5 0.75 1.0])\n\nExamples:\n  (quantile (sample-normal 100))\n  (quantile (sample-normal 100) :probs [0.025 0.975])\n  (quantile (sample-normal 100) :probs 0.975)\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Quantile",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/quantile"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "quantile-normal",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L235",
   :line 235,
   :var-type "function",
   :arglists ([probability & {:keys [mean sd], :or {mean 0, sd 1}}]),
   :doc
   " Returns the inverse of the Normal CDF for the given probability.\nIt will return a sequence of values, if given a sequence of\nprobabilities. This is equivalent to R's qnorm function.\n\nOptions:\n  :mean (default 0)\n  :sd (default 1)\n\nReturns:\n  a value x, where (cdf-normal x) = probability\n\nSee also:\n    pdf-normal, cdf-normal, and sample-normal\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Probability.html\n    http://en.wikipedia.org/wiki/Normal_distribution\n    http://en.wikipedia.org/wiki/Quantile\n\nExample:\n    (quantile-normal 0.975)\n    (quantile-normal [0.025 0.975] :mean -2 :sd (sqrt 0.5))",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/quantile-normal"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "quantile-t",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L836",
   :line 836,
   :var-type "function",
   :arglists ([probability & {:keys [df], :or {df 1}}]),
   :doc
   " Returns the inverse of the Student's t CDF for the given probability\n(i.e. the quantile).  It will return a sequence of values, if x is\na sequence of probabilities. This is equivalent to R's qt function.\n\nOptions:\n  :df (default 1)\n\nReturns:\n  a value x, where (cdf-t x) = probability\n\nSee also:\n   pdf-t, cdf-t, and sample-t\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/Probability.html\n    http://en.wikipedia.org/wiki/Student-t_distribution\n    http://en.wikipedia.org/wiki/Quantile\n\nExample:\n    (quantile-t 0.975)\n    (quantile-t [0.025 0.975] :df 25)\n    (def df [1 2 3 4 5 6 7 8 9 10 20 50 100 1000])\n    (map #(quantile-t 0.025 :df %) df)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/quantile-t"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "rank-index",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2891",
   :line 2891,
   :var-type "function",
   :arglists ([x]),
   :doc
   "\ngiven a seq, returns a map where the keys are the values of the seq and the values are the positional rank of each member o the seq.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/rank-index"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sample",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1663",
   :line 1663,
   :var-type "multimethod",
   :arglists nil,
   :doc
   " Returns a sample of the given size from the given collection. If replacement\nis set to false it returns a set, otherwise it returns a list.\n\nArguments:\n  coll -- collection or dataset to be sampled from\n\nOptions:\n  :size -- (default (count x) sample size\n  :replacement (default true) -- sample with replacement\n\n\nExamples:\n  (sample (range 10)) ; permutation of numbers zero through ten\n  (sample [:red :green :blue] :size 10) ; choose 10 items that are either :red, :green, or :blue.\n  (sample (seq \"abcdefghijklmnopqrstuvwxyz\")  :size 4 :replacement false) ; choose 4 random letters.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-beta",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L498",
   :line 498,
   :var-type "function",
   :arglists ([size & {:keys [alpha beta], :or {alpha 1, beta 1}}]),
   :doc
   " Returns a sample of the given size from a Beta distribution.\nThis is equivalent to R's rbeta function.\n\nOptions:\n  :alpha (default 1)\n  :beta (default 1)\n  These default values produce a Uniform distribution.\n\nSee also:\n    pdf-beta and cdf-beta\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Beta.html\n    http://en.wikipedia.org/wiki/Beta_distribution\n\nExample:\n    (sample-beta 1000 :alpha 1 :beta 2)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-beta"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-binomial",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1156",
   :line 1156,
   :var-type "function",
   :arglists ([size & {:keys [size prob], :or {size 1, prob 1/2}}]),
   :doc
   " Returns a sample of the given size from a Binomial distribution.\nSame as R's rbinom\n\nOptions:\n  :size (default 1)\n  :prob (default 1/2)\n\nSee also:\n    cdf-binomial and sample-binomial\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Binomial.html\n    http://en.wikipedia.org/wiki/Binomial_distribution\n\nExample:\n    (sample-binomial 1000 :prob 1/4 :size 20)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-binomial"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-chisq",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L756",
   :line 756,
   :var-type "function",
   :arglists ([size & {:keys [df], :or {df 1}}]),
   :doc
   " Returns a sample of the given size from a Chi Square distribution\nSame as R's rchisq function.\n\nOptions:\n  :df (default 1)\n\nSee also:\n    pdf-chisq and cdf-chisq\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/ChiSquare.html\n    http://en.wikipedia.org/wiki/Chi_square_distribution\n\nExample:\n    (sample-chisq 1000 :df 2)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-chisq"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-dirichlet",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1048",
   :line 1048,
   :var-type "function",
   :arglists ([size alpha]),
   :doc
   "\nExamples:\n  (use '(incanter core stats charts))\n\n  ;; a total of 1447 adults were polled to indicate their preferences for\n  ;; candidate 1 (y1=727), candidate 2 (y2=583), or some other candidate or no\n  ;; preference (y3=137).\n\n  ;; the counts y1, y2, and y3 are assumed to have a multinomial distribution\n  ;; If a uniform prior distribution is assigned to the multinomial vector\n  ;; theta = (th1, th2, th3), then the posterior distribution of theta is\n  ;; proportional to g(theta) = th1^y1 * th2^y2 * th3^y3, which is a\n  ;; dirichlet distribution with parameters (y1+1, y2+1, y3+1)\n  (def  theta (sample-dirichlet 1000 [(inc 727) (inc 583) (inc 137)]))\n  ;; view means, 95% CI, and histograms of the proportion parameters\n  (mean (sel theta :cols 0))\n  (quantile (sel theta :cols 0) :probs [0.0275 0.975])\n  (view (histogram (sel theta :cols 0)))\n  (mean (sel theta :cols 1))\n  (quantile (sel theta :cols 1) :probs [0.0275 0.975])\n  (view (histogram (sel theta :cols 1)))\n  (mean (sel theta :cols 2))\n  (quantile (sel theta :cols 2) :probs [0.0275 0.975])\n  (view (histogram (sel theta :cols 2)))\n\n  ;; view  a histogram of the difference in proportions between the first\n  ;; two candidates\n  (view (histogram (minus (sel theta :cols 0) (sel theta :cols 1))))",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-dirichlet"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-exp",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L954",
   :line 954,
   :var-type "function",
   :arglists ([size & {:keys [rate], :or {rate 1}}]),
   :doc
   " Returns a sample of the given size from a Exponential distribution.\nSame as R's rexp\n\nOptions:\n  :rate (default 1)\n\nSee also:\n    pdf-exp, and cdf-exp\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Exponential.html\n    http://en.wikipedia.org/wiki/Exponential_distribution\n\nExample:\n    (sample-exp 1000 :rate 1/2)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-exp"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-gamma",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L670",
   :line 670,
   :var-type "function",
   :arglists ([size & {:keys [shape rate], :or {shape 1, rate 1}}]),
   :doc
   " Returns a sample of the given size from a Gamma distribution.\nThis is equivalent to R's rgamma function.\n\nOptions:\n  :shape (default 1)\n  :rate (default 1)\n\nSee also:\n    pdf-gamma, cdf-gamma, and quantile-gamma\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Gamma.html\n    http://en.wikipedia.org/wiki/Gamma_distribution\n\nExample:\n    (sample-gamma 1000 :shape 1 :rate 2)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-gamma"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-inv-wishart",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1020",
   :line 1020,
   :var-type "function",
   :arglists ([& {:keys [scale p df], :or {p 2}}]),
   :doc
   "\nReturns a p-by-p symmetric distribution drawn from an inverse-Wishart distribution\n\nOptions:\n  :p (default 2) -- number of dimensions of resulting matrix\n  :df (default p) -- degree of freedoms (aka n), df <= p\n  :scale (default (identity-matrix p)) -- positive definite matrix (aka V)\n\nExamples:\n  (use 'incanter.stats)\n  (sample-inv-wishart :df 10  :p 4)\n\n  ;; calculate the mean of 1000 wishart matrices, should equal (mult df scale)\n  (div (reduce plus (for [_ (range 1000)] (sample-wishart :p 4))) 1000)\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Inverse-Wishart_distribution",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-inv-wishart"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-multinomial",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1186",
   :line 1186,
   :var-type "function",
   :arglists
   ([size & {:keys [probs categories], :or {probs [0.5 0.5]}}]),
   :doc
   " Returns a sequence representing a sample from a multinomial distribution.\n\nArguments: size -- number of values to return\n\nOptions:\n  :categories (default [0 1]) -- the values returned\n  :probs (default [0.5 0.5]) -- the probabilities associated with each category\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Multinomial_distribution#Sampling_from_a_multinomial_distribution\n\n\nExamples:\n  (use '(incanter core stats charts))\n\n  (sample-multinomial 10)\n  (sample-multinomial 10 :probs [0.25 0.5 0.25])\n\n  ;; estimate sample proportions\n  (def sample-size 1000.0)\n  (def categories [:red :yellow :blue :green])\n  (def data (to-dataset (sample-multinomial sample-size \n                                            :categories categories\n                                            :probs [0.5 0.25 0.2 0.05])))\n\n  ;; check the sample proportions\n  (view (pie-chart categories\n                   (map #(div (count ($ :col-0 ($where {:col-0 %} data))) \n                              sample-size)\n                        categories)))\n                    ",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-multinomial"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-mvn",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L296",
   :line 296,
   :var-type "function",
   :arglists ([size & {:keys [mean sigma]}]),
   :doc
   " Returns a sample of the given size from a Multivariate Normal\ndistribution. This is equivalent to R's mvtnorm::rmvnorm function.\n\nArguments:\n  size -- the size of the sample to return\n\nOptions:\n  :mean (default (repeat (ncol sigma) 0))\n  :sigma (default (identity-matrix (count mean)))\n\n\nExamples:\n\n  (use '(incanter core stats charts))\n  (def mvn-samp (sample-mvn 1000 :mean [7 5] :sigma (matrix [[2 1.5] [1.5 3]])))\n  (covariance mvn-samp)\n  (def means (map mean (trans mvn-samp)))\n\n  ;; plot scatter-plot of points\n  (def mvn-plot (scatter-plot (sel mvn-samp :cols 0) (sel mvn-samp :cols 1)))\n  (view mvn-plot)\n  ;; add centroid to plot\n  (add-points mvn-plot [(first means)] [(second means)])\n\n  ;; add regression line to scatter plot\n  (def x (sel mvn-samp :cols 0))\n  (def y (sel mvn-samp :cols 1))\n  (def lm (linear-model y x))\n  (add-lines mvn-plot x (:fitted lm))\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Multivariate_normal",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-mvn"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-neg-binomial",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1377",
   :line 1377,
   :var-type "function",
   :arglists ([nsize & {:keys [size prob], :or {size 10, prob 1/2}}]),
   :doc
   " Returns a sample of the given size from a Negative Binomial distribution.\nSame as R's rnbinom\n\nOptions:\n  :size (default 10)\n  :prob (default 1/2)\n\nSee also:\n    pdf-neg-binomial and cdf-neg-binomial\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/NegativeBinomial.html\n    http://en.wikipedia.org/wiki/Negative_binomial_distribution\n\nExample:\n    (sample-neg-binomial 1000 :prob 1/2 :size 20)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-neg-binomial"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-normal",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L267",
   :line 267,
   :var-type "function",
   :arglists ([size & {:keys [mean sd], :or {mean 0, sd 1}}]),
   :doc
   " Returns a sample of the given size from a Normal distribution\nThis is equivalent to R's rnorm function.\n\nOptions:\n  :mean (default 0)\n  :sd (default 1)\n\nSee also:\n    pdf-normal, cdf-normal, quantile-normal\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Normal.html\n    http://en.wikipedia.org/wiki/Normal_distribution\n\nExample:\n    (sample-normal 1000 :mean -2 :sd (sqrt 0.5))",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-normal"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-permutations",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1907",
   :line 1907,
   :var-type "function",
   :arglists ([n x] [n x y]),
   :doc
   " If provided a two arguments (n x), it returns a list of n permutations\nof x. If provided three (n x y) arguments, returns a list with two with n permutations of\neach arguments, where each permutation is drawn from the pooled arguments.\n\nArguments:\n  n -- number of randomized versions of the original two groups to return\n  x -- group 1\n  y -- (default nil) group 2\n\n\nExamples:\n\n  (use '(incanter core stats))\n  (sample-permutations 10 (range 10))\n  (sample-permutations 10 (range 10) (range 10 20))\n\n  ;; extended example with plant-growth data\n  (use '(incanter core stats datasets charts))\n\n  ;; load the plant-growth dataset\n  (def data (to-matrix (get-dataset :plant-growth)))\n\n  ;; break the first column of the data into groups based on treatment (second column).\n  (def groups (group-on data 1 :cols 0))\n\n  ;; define a function for the statistic of interest\n  (defn means-diff [x y] (minus (mean x) (mean y)))\n\n  ;; calculate the difference in sample means between the two groups\n  (def samp-mean-diff (means-diff (first groups) (second groups))) ;; 0.371\n\n  ;; create 500 permuted versions of the original two groups\n  (def permuted-groups (sample-permutations 1000 (first groups) (second groups)))\n\n  ;; calculate the difference of means of the 500 samples\n  (def permuted-means-diffs1 (map means-diff (first permuted-groups) (second permuted-groups)))\n\n  ;; use an indicator function that returns 1 when the randomized means diff is greater\n  ;; than the original sample mean, and zero otherwise. Then take the mean of this sequence\n  ;; of ones and zeros. That is the proportion of times you would see a value more extreme\n  ;; than the sample mean (i.e. the p-value).\n  (mean (indicator #(> % samp-mean-diff) permuted-means-diffs1)) ;; 0.088\n\n  ;; calculate the 95% confidence interval of the null hypothesis. If the\n  ;; sample difference in means is outside of this range, that is evidence\n  ;; that the two means are statistically significantly different.\n  (quantile permuted-means-diffs1 :probs [0.025 0.975]) ;; (-0.606 0.595)\n\n  ;; Plot a histogram of the permuted-means-diffs using the density option,\n  ;; instead of the default frequency, and then add a normal pdf curve with\n  ;; the mean and sd of permuted-means-diffs data for a visual comparison.\n  (doto (histogram permuted-means-diffs1 :density true)\n        (add-lines (range -1 1 0.01) (pdf-normal (range -1 1 0.01)\n                                                 :mean (mean permuted-means-diffs1)\n                                                 :sd (sd permuted-means-diffs1)))\n        view)\n\n  ;; compare the means of treatment 2 and control\n  (def permuted-groups (sample-permutations 1000 (first groups) (last groups)))\n  (def permuted-means-diffs2 (map means-diff (first permuted-groups) (second permuted-groups)))\n  (def samp-mean-diff (means-diff (first groups) (last groups))) ;; -0.4939\n  (mean (indicator #(< % samp-mean-diff) permuted-means-diffs2)) ;; 0.022\n  (quantile permuted-means-diffs2 :probs [0.025 0.975]) ;; (-0.478 0.466)\n\n  ;; compare the means of treatment 1 and treatment 2\n  (def permuted-groups (sample-permutations 1000 (second groups) (last groups)))\n  (def permuted-means-diffs3 (map means-diff (first permuted-groups) (second permuted-groups)))\n  (def samp-mean-diff (means-diff (second groups) (last groups))) ;; -0.865\n  (mean (indicator #(< % samp-mean-diff) permuted-means-diffs3)) ;;  0.002\n  (quantile permuted-means-diffs3 :probs [0.025 0.975]) ;; (-0.676 0.646)\n\n  (doto (box-plot permuted-means-diffs1)\n        (add-box-plot permuted-means-diffs2)\n        (add-box-plot permuted-means-diffs3)\n        view)\n\n\n  Further Reading:\n    http://en.wikipedia.org/wiki/Resampling_(statistics)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-permutations"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-poisson",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1290",
   :line 1290,
   :var-type "function",
   :arglists ([size & {:keys [lambda], :or {lambda 1}}]),
   :doc
   " Returns a sample of the given size from a Poisson distribution.\nSame as R's rpois\n\nOptions:\n  :lambda (default 1)\n\nSee also:\n    pdf-poisson and cdf-poisson\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Poisson.html\n    http://en.wikipedia.org/wiki/Poisson_distribution\n\nExample:\n    (sample-poisson 1000 :lambda 10)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-poisson"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-t",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L874",
   :line 874,
   :var-type "function",
   :arglists ([size & {:keys [df], :or {df 1}}]),
   :doc
   " Returns a sample of the given size from a Student's t distribution.\nSame as R's rt function.\n\nOptions:\n  :df (default 1)\n\nSee also:\n    pdf-t, cdf-t, and quantile-t\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/StudentT.html\n    http://en.wikipedia.org/wiki/Student-t_distribution\n\nExample:\n    (cdf-t 1000 :df 10)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-t"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-uniform",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L405",
   :line 405,
   :var-type "function",
   :arglists
   ([size
     &
     {:keys [min max integers],
      :or {min 0.0, max 1.0, integers false}}]),
   :doc
   " Returns a sample of the given size from a Uniform distribution.\nThis is equivalent to R's runif function.\n\nOptions:\n  :min (default 0)\n  :max (default 1)\n  :integers (default false)\n\nSee also:\n    pdf-uniform and cdf-uniform\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/DoubleUniform.html\n    http://en.wikipedia.org/wiki/Uniform_distribution\n\nExample:\n    (sample-uniform 1000)\n    (sample-uniform 1000 :min 1 :max 10)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-uniform"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-weibull",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L582",
   :line 582,
   :var-type "function",
   :arglists ([size & options]),
   :doc
   " Returns a sample of the given size from a Weibull distribution\n\nOptions:\n  :shape (default 1)\n  :scale (default 1)\n\nSee also:\n    pdf-weibull, cdf-weibull\n\nReferences:\n    http://incanter.org/docs/parallelcolt/api/cern/jet/random/tdouble/Distributions.html\n    http://en.wikipedia.org/wiki/Weibull_distribution\n\nExample:\n    (sample-weibull 1000 :shape 1 :scale 0.2)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-weibull"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sample-wishart",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L984",
   :line 984,
   :var-type "function",
   :arglists ([& {:keys [scale p df], :or {p 2}}]),
   :doc
   "\nReturns a p-by-p symmetric distribution drawn from a Wishart distribution\n\nOptions:\n  :p (default 2) -- number of dimensions of resulting matrix\n  :df (default p) -- degree of freedoms (aka n), df <= p\n  :scale (default (identity-matrix p)) -- positive definite matrix (aka V)\n\nExamples:\n  (use 'incanter.stats)\n  (sample-wishart :df 10  :p 4)\n\n  ;; calculate the mean of 1000 wishart matrices, should equal (mult df scale)\n  (div (reduce plus (for [_ (range 1000)] (sample-wishart :p 4))) 1000)\n\n\nReferences:\n  http://en.wikipedia.org/wiki/Wishart_distribution#",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sample-wishart"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "scalar-abs",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L46",
   :line 46,
   :var-type "function",
   :arglists ([x]),
   :doc "Fast absolute value function",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/scalar-abs"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sd",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1513",
   :line 1513,
   :var-type "function",
   :arglists ([x]),
   :doc
   "\nReturns the sample standard deviation of the data, x. Equivalent to\nR's sd function.\n\nExamples:\n  (sd (sample-normal 100))\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Standard_deviation",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sd"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "simple-ci",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2242",
   :line 2242,
   :var-type "function",
   :arglists ([coll]),
   :doc "Get the confidence interval for the data.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/simple-ci"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "simple-p-value",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2234",
   :line 2234,
   :var-type "function",
   :arglists ([coll mu]),
   :doc "Returns the p-value for the data contained in coll.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/simple-p-value"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "simple-regression",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2795",
   :line 2795,
   :var-type "function",
   :arglists ([y x & {:keys [intercept], :or {intercept true}}]),
   :doc
   "A stripped version of linear-model that returns a map containing only\nthe coefficients.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/simple-regression"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "simple-t-test",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2225",
   :line 2225,
   :var-type "function",
   :arglists ([coll mu]),
   :doc "Perform a simple t-test on the data contained in coll.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/simple-t-test"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "skewness",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1614",
   :line 1614,
   :var-type "function",
   :arglists ([x]),
   :doc
   "\nReturns the skewness of the data, x. \"Skewness is a measure of the asymmetry\nof the probability distribution of a real-valued random variable.\" (Wikipedia)\n\nExamples:\n\n  (skewness (sample-normal 100000)) ;; approximately 0\n  (skewness (sample-gamma 100000)) ;; approximately 2\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Skewness",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/skewness"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sorensen-index",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3295",
   :line 3295,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/S%C3%B8rensen_similarity_index#cite_note-4\n\nThe Sørensen index, also known as Sørensen’s similarity coefficient, is a statistic used for comparing the similarity of two samples. where A and B are the species numbers in samples A and B, respectively, and C is the number of species shared by the two samples. \n\n The Sørensen index is identical to Dice's coefficient which is always in [0, 1] range. Sørensen index used as a distance measure, 1 − QS, is identical to Hellinger distance and Bray–Curtis dissimilarity.\n\nThe Sørensen coefficient is mainly useful for ecological community data (e.g. Looman & Campbell, 1960[3]). Justification for its use is primarily empirical rather than theoretical (although it can be justified theoretically as the intersection of two fuzzy sets[4]). As compared to Euclidean distance, Sørensen distance retains sensitivity in more heterogeneous data sets and gives less weight to outliers\n\nThis function assumes you pass in a and b as sets.\n\nThe sorensen index extended to abundance instead of incidence of species is called the Czekanowski index.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sorensen-index"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "spearmans-rho",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2898",
   :line 2898,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\nhttp://en.wikipedia.org/wiki/Spearman%27s_rank_correlation_coefficient\n\nIn statistics, Spearman's rank correlation coefficient or Spearman's rho, is a non-parametric measure of correlation – that is, it assesses how well an arbitrary monotonic function could describe the relationship between two variables, without making any other assumptions about the particular nature of the relationship between the variables. Certain other measures of correlation are parametric in the sense of being based on possible relationships of a parameterised form, such as a linear relationship.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/spearmans-rho"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "square-devs-from-mean",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2772",
   :line 2772,
   :var-type "function",
   :arglists ([x] [x m]),
   :doc
   "takes either a sample or a sample and a precalculated mean.\n\nreturns the squares of the difference between each observation and the sample mean.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/square-devs-from-mean"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sum-of-square-devs-from-mean",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2785",
   :line 2785,
   :var-type "function",
   :arglists ([x] [x m]),
   :doc
   "takes either a sample or a sample and a precalculated mean.\n\nreturns the sum of the squares of the difference between each observation and the sample mean.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sum-of-square-devs-from-mean"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sum-variance-test",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3054",
   :line 3054,
   :var-type "function",
   :arglists ([vs]),
   :doc
   "the variance of the sum of n independent variables is equal to the sum of their variances.\n\n(variance-independence-test [[1 2 3 4] [1 2 3 4]]) -> 5/2",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sum-variance-test"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "summarizable?",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2622",
   :line 2622,
   :var-type "function",
   :arglists ([col ds]),
   :doc
   "Takes in a column name (or number) and a dataset. Returns true if the column can be summarized, and false otherwise",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/summarizable?"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "summarizer-fn",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2609",
   :line 2609,
   :var-type "function",
   :arglists ([col ds]),
   :doc
   "Takes in a column (number or name) and a dataset. Returns a function to summarize the column if summarizable, and a \nstring describing why the column can't be summarized in the event that it can't",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/summarizer-fn"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "summary",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2628",
   :line 2628,
   :var-type "function",
   :arglists ([ds]),
   :doc
   "Takes in a dataset. Returns a summary of that dataset (as a map of maps), having automatically figured out the relevant \ndatatypes of columns. Will be slightly forgiving of mangled data in columns.",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/summary"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "sweep",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1852",
   :line 1852,
   :var-type "function",
   :arglists ([x & {:keys [stat fun], :or {stat mean, fun minus}}]),
   :doc
   " Return an array obtained from an input array by sweeping out a\nsummary statistic. Based to R's sweep function.\n\n  Arguments:\n    x is an sequence\n\n\n  Options:\n        :stat (default mean) the statistic to sweep out\n        :fun (defaul minus) the function used to sweep the stat out\n\n  Example:\n\n    (use '(incanter core stats))\n\n    (def x (sample-normal 30 :mean 10 :sd 5))\n    (sweep x) ;; center the data around mean\n    (sweep x :stat sd :fun div) ;; divide data by its sd",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/sweep"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "t-test",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2133",
   :line 2133,
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
   "\nArgument:\n  x : sample to test\n\nOptions:\n  :y (default nil)\n  :mu (default (mean y) or 0) population mean\n  :alternative (default :two-sided) other choices :less :greater\n  :var-equal TODO (default false) variance equal\n  :paired TODO (default false) paired test\n  :conf-level (default 0.95) for returned confidence interval\n\nExamples:\n\n  (t-test (range 1 11) :mu 0)\n  (t-test (range 1 11) :mu 0 :alternative :less)\n  (t-test (range 1 11) :mu 0 :alternative :greater)\n\n  (t-test (range 1 11) :y (range 7 21))\n  (t-test (range 1 11) :y (range 7 21) :alternative :less)\n  (t-test (range 1 11) :y (range 7 21) :alternative :greater)\n  (t-test (range 1 11) :y (conj (range 7 21) 200))\n\nReferences:\n  http://en.wikipedia.org/wiki/T_test\n  http://www.socialresearchmethods.net/kb/stat_t.php",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/t-test"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "tabulate",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2278",
   :line 2278,
   :var-type "function",
   :arglists ([x & options]),
   :doc
   " Cross-tabulates the values of the given numeric matrix.\n\nReturns a hash-map with the following fields:\n  :table -- the table of counts for each combination of values,\n            this table is only returned if x has two-columns\n  :levels -- a sequence of sequences, where each sequence list\n             the levels (possible values) of the corresponding\n             column of x.\n  :margins -- a sequence of sequences, where each sequence\n              represents the marginal total for each level\n              of the corresponding column of x.\n  :counts -- a hash-map, where vectors of unique combinations\n             of the cross-tabulated levels are the keys and the\n             values are the total count of each combination.\n  :N  -- the grand-total for the contingency table\n\n\nExamples:\n\n  (use '(incanter core stats))\n  (tabulate [1 2 3 2 3 2 4 3 5])\n  (tabulate (sample-poisson 100 :lambda 5))\n\n  (use '(incanter core stats datasets))\n  (def math-prog (to-matrix (get-dataset :math-prog)))\n  (tabulate (sel math-prog :cols [1 2]))\n\n\n  (def data (matrix [[1 0 1]\n                     [1 1 1]\n                     [1 1 1]\n                     [1 0 1]\n                     [0 0 0]\n                     [1 1 1]\n                     [1 1 1]\n                     [1 0 1]\n                     [1 1 0]]))\n  (tabulate data)\n\n\n  (def data (matrix [[1 0]\n                     [1 1]\n                     [1 1]\n                     [1 0]\n                     [0 0]\n                     [1 1]\n                     [1 1]\n                     [1 0]\n                     [1 1]]))\n  (tabulate data)",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/tabulate"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "tanimoto-coefficient",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L3165",
   :line 3165,
   :var-type "function",
   :arglists ([a b]),
   :doc
   "\n\nhttp://en.wikipedia.org/wiki/Jaccard_index\n\nThe cosine similarity metric may be extended such that it yields the Jaccard coefficient in the case of binary attributes. This is the Tanimoto coefficient. ",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/tanimoto-coefficient"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "variance",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L1464",
   :line 1464,
   :var-type "function",
   :arglists ([x]),
   :doc
   "\nReturns the sample variance of the data, x. Equivalent to R's var function.\n\nExamples:\n  (variance (sample-normal 100))\n\nReferences:\n  http://incanter.org/docs/parallelcolt/api/cern/jet/stat/tdouble/DoubleDescriptive.html\n  http://en.wikipedia.org/wiki/Sample_variance#Population_variance_and_sample_variance",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/variance"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj",
   :name "within",
   :file "modules/incanter-core/src/incanter/stats.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/8faacac9079e7c79d79a06ec52a9a5e9e270f3b6/modules/incanter-core/src/incanter/stats.clj#L2765",
   :line 2765,
   :var-type "function",
   :arglists ([z x y]),
   :doc "\ny is within z of x in metric space.  ",
   :namespace "incanter.stats",
   :wiki-url
   "http://incanter.github.com/incanter//stats-api.html#incanter.stats/within"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/d187ba4a7cec9b8e311988abcb6130ba75f32305/modules/incanter-core/src/incanter/symbolic.clj",
   :name "deriv",
   :file "modules/incanter-core/src/incanter/symbolic.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/d187ba4a7cec9b8e311988abcb6130ba75f32305/modules/incanter-core/src/incanter/symbolic.clj#L141",
   :line 141,
   :var-type "macro",
   :arglists ([exp v] [exp v degree]),
   :doc
   "Macro for symbolic differentiation. with 2 args, takes 1st degree deriv.\nwith 3, takes arbitrary degrees. contains all deriv rules for basic funcs.\n\n\nExamples:\n\n  (use '(incanter core symbolic))\n\n  (deriv (+ x 3) x) ; => 1\n  (deriv (* x y) x) ; => y\n  (deriv (* (* x y) (+ x 3)) x) ; => (+ (* (+ x 3) y) (* x y))\n  (deriv (* (* x y) (+ x 3)) y) ; => (* (+ x 3) x)\n\n  (deriv (* x y (+ x 3)) x) ; => (+ (* y (+ x 3)) (* y x))\n  (deriv (* x y (+ x 3)) y) ; => (* (+ x 3) x)\n\n  (deriv (sin x) x) ; => (cos x)\n  (deriv (cos x) x) ; => (* -1 (sin x))\n\n  (deriv (sin (* x y)) y) ; => (* x (cos (* x y)))\n\n  (deriv (pow x 3) x) ; => (* 3 (pow x 2))\n  (deriv (** x 3) x) ; => (* 3 (pow x 2))\n\n  (deriv (pow x 3) x 2) ; => (* 3 (* 2 x))\n\n  (deriv (* x y (+ x 3)) x 2) ; => (+ y y)\n  (deriv (* x y (+ x 3)) x 3) ; => 0\n\n  (deriv (+ (* 3 x) (* 8 x)) x) ; => 11\n\n\n\n  ;; NOT WORKING YET\n\n  (deriv (/ 1 x) x) ; => (* (deriv* (* (x)) x) (* -1 (pow (* (x)) -2)))\n                                        ^-- need to fix",
   :namespace "incanter.symbolic",
   :wiki-url
   "http://incanter.github.com/incanter//symbolic-api.html#incanter.symbolic/deriv"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/d187ba4a7cec9b8e311988abcb6130ba75f32305/modules/incanter-core/src/incanter/symbolic.clj",
   :name "deriv*",
   :file "modules/incanter-core/src/incanter/symbolic.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/d187ba4a7cec9b8e311988abcb6130ba75f32305/modules/incanter-core/src/incanter/symbolic.clj#L75",
   :line 75,
   :var-type "function",
   :arglists ([exp v] [exp vr degree]),
   :doc
   "main sub-function for differentiation. with 2 args, takes 1st degree deriv.\nwith 3, takes arbitrary degrees. contains all deriv rules for basic funcs.\n\n\nExamples:\n\n  (use '(incanter core symbolic))\n\n  (deriv* '(+ x 3) 'x)\n  (deriv* '(* x y) 'x)\n  (deriv* '(* (* x y) '(+ x 3)) x)\n  (deriv* '(* (* x y) (+ x 3)) 'y)\n\n  (deriv* '(* x y (+ x 3)) 'x)\n  (deriv* '(* x y (+ x 3)) 'y)\n\n  (deriv* '(* x y (+ x 3)) 'x 2)\n  (deriv* '(* x y (+ x 3)) 'x 3)",
   :namespace "incanter.symbolic",
   :wiki-url
   "http://incanter.github.com/incanter//symbolic-api.html#incanter.symbolic/deriv*"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/d187ba4a7cec9b8e311988abcb6130ba75f32305/modules/incanter-core/src/incanter/symbolic.clj",
   :name "deriv-fn",
   :file "modules/incanter-core/src/incanter/symbolic.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/d187ba4a7cec9b8e311988abcb6130ba75f32305/modules/incanter-core/src/incanter/symbolic.clj#L264",
   :line 264,
   :var-type "macro",
   :arglists ([[& args] expr v] [[& args] expr v degree]),
   :doc
   "\n\nExamples:\n  (use '(incanter core symbolic))\n\n  (deriv-fn [x y] (+ (* x y) x) x)\n\n  ((deriv-fn [x y] (+ (* x y) x) x) 5 9)\n\n  (use 'incanter.charts)\n  (doto (function-plot sin -5 5)\n     (add-function (deriv-fn [x] (sin x) x) -5 5)\n     (add-function (deriv-fn [x] (sin x) x 2) -5 5)\n     view)\n\n  (let [f (fn [x] (pow x 2))\n        df (deriv-fn [x] (pow x 2) x)]\n    (doto (function-plot f -5 5)\n      (add-function df -5 5)\n      view))\n\n\n  (let [f (fn [x] (pow x 3))\n        df (deriv-fn [x] (pow x 3) x)]\n    (doto (function-plot f -5 5)\n      (add-function df -5 5)\n      view))\n\n\n  ;; NOT WORKING YET\n\n  (let [f (fn [x] (/ 1 x ))\n        df (deriv-fn [x] (/ 1 x) x)]\n    (doto (function-plot f 0.5 5)\n      (add-function df 0.5 5)\n      view))",
   :namespace "incanter.symbolic",
   :wiki-url
   "http://incanter.github.com/incanter//symbolic-api.html#incanter.symbolic/deriv-fn"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/d187ba4a7cec9b8e311988abcb6130ba75f32305/modules/incanter-core/src/incanter/symbolic.clj",
   :name "deriv-fn*",
   :file "modules/incanter-core/src/incanter/symbolic.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/d187ba4a7cec9b8e311988abcb6130ba75f32305/modules/incanter-core/src/incanter/symbolic.clj#L237",
   :line 237,
   :var-type "function",
   :arglists ([[& args] expr v] [[& args] expr v degree]),
   :doc
   "\n\nExamples:\n  (use '(incanter core symbolic))\n\n  (deriv-fn* '[x y] '(+ (* x y) x) 'x)\n\n  ((deriv-fn* '[x y] '(+ (* x y) x) 'x) 5 9)",
   :namespace "incanter.symbolic",
   :wiki-url
   "http://incanter.github.com/incanter//symbolic-api.html#incanter.symbolic/deriv-fn*"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "$$",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj#L174",
   :line 174,
   :var-type "function",
   :arglists ([ind ts] [ind cols ts] [ind-1 ind-2 cols ts]),
   :doc
   "This is the equivalent of :: in xts.  That is, it slices  out the timeseries between ind-1 and ind-2.  These are any values that can be coerced into clj-time values.",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/$$"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "aligned?",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj#L124",
   :line 124,
   :var-type "function",
   :arglists ([& zs]),
   :doc "Is the :index column identical for all zs.",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/aligned?"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "coredata",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj#L98",
   :line 98,
   :var-type "function",
   :arglists ([x]),
   :doc
   "Return the :rows of a dataset, with :index dissoc'd.\nIntended to be used internally time series function to get at data.",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/coredata"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "lag",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj#L198",
   :line 198,
   :var-type "function",
   :arglists ([z] [z n]),
   :doc
   "Return the timeseries lagged by n units or 1 if not specified. No time calculations\nare made in the index column.  The output timeseries is of the same length as the input.",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/lag"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "roll-apply",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj#L55",
   :line 55,
   :var-type "function",
   :arglists ([f n coll]),
   :doc
   "\n  A generic function for applying a function to rolling window of a collection.\n\n  Arguments:\n  f -- function to be applied\n  n -- size of rolling window\n  coll -- collection of data\n",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/roll-apply"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "roll-max",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj#L76",
   :line 76,
   :var-type "function",
   :arglists ([n coll]),
   :doc "\n  Returns the rolling max of the previous n elements.\n",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/roll-max"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "roll-mean",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj#L41",
   :line 41,
   :var-type "function",
   :arglists ([n coll]),
   :doc
   "\n  Returns the unweighted mean of the previous n data points.\n\n  References: \n  http://en.wikipedia.org/wiki/Moving_average#Simple_moving_average\n  http://www.learningclojure.com/2010/03/moving-average-of-list.html\n",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/roll-mean"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "roll-median",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj#L69",
   :line 69,
   :var-type "function",
   :arglists ([n coll]),
   :doc "\n  Returns the rolling median of the previous n elements.\n",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/roll-median"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "roll-min",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj#L83",
   :line 83,
   :var-type "function",
   :arglists ([n coll]),
   :doc "\n  Returns the rolling min of the previous n elements.\n",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/roll-min"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "within-zoo?",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj#L150",
   :line 150,
   :var-type "function",
   :arglists ([t z]),
   :doc "Is t between the first and last indices.",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/within-zoo?"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "zoo",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj#L157",
   :line 157,
   :var-type "function",
   :arglists ([x] [x index-col]),
   :doc
   "Return the given dataset as a zoo value which is simply a dataset\nthat contains an column of clj-time values specified by index-col, default :index.\nThat column must contain values that can be coerced into Jodas using the TimeCoercible Protocol.",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/zoo"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "zoo-apply",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj#L213",
   :line 213,
   :var-type "function",
   :arglists ([f n zoo column & args]),
   :doc
   "Behave as for roll-apply but accept a zoo and a single column upon which to roll-apply f. Returns a zoo of the same length as input zoo with pre-pended nils",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/zoo-apply"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "zoo-row-map",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj#L233",
   :line 233,
   :var-type "function",
   :arglists ([f & zs]),
   :doc
   "Accept a number of aligned zoo object and pass them row-wise into f, return a zoo. f must accept and return maps.  The :index column is stripped out before f is applied, and then replaced afterwards with the :index of the first.",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/zoo-row-map"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "zoo-row-map-",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj#L224",
   :line 224,
   :var-type "function",
   :arglists ([f & zs]),
   :doc
   "Accept a number of aligned zoo object and pass them row-wise into f, return a seq of maps of the output of the output. f must accept and return maps.  The :index column is stripped out before f is applied, and then replaced afterwards.",
   :namespace "incanter.zoo",
   :wiki-url
   "http://incanter.github.com/incanter//zoo-api.html#incanter.zoo/zoo-row-map-"}
  {:raw-source-url
   "https://github.com/incanter/incanter/raw/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj",
   :name "zoo-row-map-occupied",
   :file "modules/incanter-zoo/src/incanter/zoo.clj",
   :source-url
   "https://github.com/incanter/incanter/blob/f8ec625b2d085ac1a5f486ec97c997e56ff92505/modules/incanter-zoo/src/incanter/zoo.clj#L243",
   :line 243,
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
