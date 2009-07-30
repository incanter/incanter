;; incanter.el

(defvar incanter-root (concat
                       (file-name-directory
                        (or (buffer-file-name) load-file-name))
                       "../")
  "Directory containing the incanter installation.")

(defun incanter ()
  "Start or switch to an incanter session with SLIME."
  (interactive)
  (if (get-buffer "*incanter*")
      (switch-to-buffer "*incanter*")
    (setq swank-clojure-binary nil
          swank-clojure-jar-path (expand-file-name "lib/clojure.jar" incanter-root)
          swank-clojure-extra-classpaths
          (let ((lib (expand-file-name "lib" incanter-root)))
            (if (file-exists-p lib)
                (directory-files lib t ".jar$"))))
    (slime)))

;; TODO:
;; * auto-use relevant incanter namespaces?
;; * set buffer name to *incanter*
;; * point to some slime setup docs