#!/usr/bin/env bin/clj


(use 'clojure.contrib.gen-html-docs)
(generate-documentation-to-file "./docs/api/index.html" ['incanter.matrix 'incanter.stats 'incanter.io 'incanter.charts])


