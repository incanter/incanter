;; from the following blog post:
;; http://incanter-blog.org/2009/06/14/chi-square-test/

;; Chi-square test of independence:
;; testing the independence of eye and hair color
;; for both males and females

(use '(incanter core stats charts datasets))

(def by-gender (group-by (get-dataset :hair-eye-color) 2))

(def male-data (first by-gender))
(view male-data)

(def female-data (second by-gender))
(view female-data)

(def m-hair (sel male-data :cols 0))
(def m-eye (sel male-data :cols 1))
(def m-count (sel male-data :cols 3))

(view (bar-chart m-hair m-count 
                 :group-by m-eye 
                 :legend true 
                 :title "Male Hair and Eye Color"
                 :x-label "Hair Color"        
                 :y-label "Number of males"))

(def f-hair (sel female-data :cols 0))
(def f-eye (sel female-data :cols 1))
(def f-count (sel female-data :cols 3))

(view (bar-chart f-hair f-count 
                 :group-by f-eye 
                 :legend true 
                 :title "Female Hair and Eye Color"
                 :x-label "Hair Color"        
                 :y-label "Number of females"))

;; make contingency tables
(def m-table (matrix m-count 4))
(def f-table (matrix f-count 4))

(def m-test (chisq-test :table m-table))
(def f-test (chisq-test :table f-table))

(:X-sq m-test)
(:p-value m-test)
(:df m-test)

(:X-sq f-test)
(:p-value f-test)
(:df f-test)


;; In addition to contingency tables, you can pass raw data to the chisq-test.
;; the math-prog data set includes three columns: student_id, high school math 
;; proficiency test pass/fail results, and freshman college programming course
;; pass/fail results.

;; This example will test whether there is any correlation between
;; the pass/fail results of a high school mathematics proficiency test and
;; a freshman college programming course

(def math-prog (to-matrix (get-dataset :math-prog)))
(def math (sel math-prog :cols 1))
(def prog (sel math-prog :cols 2))
(def math-prog-test (chisq-test :x math :y prog))

;; X-sq = 1.24145, df=1, p-value = 0.26519
;; can't reject null hypothesis, the the 
;; results of the math exam is independent of
;; the pass/fail results of the college programming
;; course.

(:X-sq math-prog-test)
(:df math-prog-test)
(:p-value math-prog-test)



