#!/usr/bin/env bin/clj


(use 'clojure.contrib.gen-html-docs)
(generate-documentation-to-file "./docs/api/index.html" ['incanter.core 
                                                         'incanter.stats 
                                                         'incanter.charts 
                                                         'incanter.io 
                                                         'incanter.chrono
                                                         'incanter.classification
                                                         'incanter.incremental-stats
                                                         'incanter.information-theory
                                                         'incanter.bayes 
                                                         'incanter.datasets 
                                                         'incanter.optimize 
                                                         'incanter.censored])

(generate-documentation-to-file "./docs/api/processing.html" ['incanter.processing])


