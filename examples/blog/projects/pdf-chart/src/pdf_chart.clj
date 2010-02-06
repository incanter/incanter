(ns pdf-chart)


(use '(incanter core charts pdf))

(save-pdf (function-plot sin -4 4) "./pdf-chart.pdf")


