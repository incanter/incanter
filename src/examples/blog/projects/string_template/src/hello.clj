(ns hello
  (:gen-class)
  (:use (incanter core stats charts datasets))
  (:import [org.antlr.stringtemplate StringTemplate]))


(defn apply-template [#^String txt  #^java.util.Map context]
  (let [t (StringTemplate. txt)]
    (.setAttributes t context)
    (.toString t)))


(defn to-html
""
  ([data]
    (let [html "<table>
                  <tr>$colnames:{col | <th>$col$</th>}$</tr>
                  $rows:{cols | <tr>$cols:{col |<td>$col$</td>}$</tr>\n}$
                </table>\n"]
      (apply-template html {"colnames" (col-names data), "rows" (to-list data)}))))



(defn -main [& args]
  (println (str "<html><body>\n" 
                (to-html (get-dataset :iris)) 
                "</body></html>\n")))



(def template-str 
"
<html>
<body>
<ol>
  $names:{n | <li>$n$</li>\n}$
</ol>
<ol>
  $namekeys,namevalues:{k,v| <li>$k$, $v$</li>\n}$
</ol>
<table>
<tr>$colnames:{col | <th>$col$</th>}$</tr>
$rows:{cols | <tr>$cols:{col |<td>$col$</td>}$</tr>\n}$
</table>
</body>
</html>
")


(defn -main-orig [& args]
  ;(view (function-plot sin -4 4)))
  ;(println (template "Hello $user$. Today is $date$" {"user" "Joe" "date" (java.util.Date.)})))
  ;(println (template "Hello $user; separator=\",\"$. Today is $date$" 
                     ;{"user" ["Joe" "David"] "date" (java.util.Date.)})))
  (let [name-map {"Mike" "value 1", "David" "value 2", "Victor" "value 3", "Todd" "value 4"}
        row #^java.util.Map {"name" "David" "value" "value 1"}
        data (get-dataset :iris)]
    (println (apply-template template-str
                       {"names" ["Mike" "David" "Victor" "Todd" "Cesar"] "date" (java.util.Date.)
                        "rows" (to-list data)
                        "colnames" (col-names data)
                        "namekeys" (keys name-map) "namevalues" (vals name-map)}))))


