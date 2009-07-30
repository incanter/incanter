;;; incanter.el --- Initiate an Incanter statistics session

;; Copyright (C) 2009 Phil Hagelberg
;;
;; Author: Phil Hagelberg
;; URL: http://github.com/liebke/incanter
;; Version: 0.1
;; Keywords: statistics
;; Created: 2009-07-29

;; This file is not part of GNU Emacs.

;;; Commentary:

;; The `incanter' function starts or switches to a SLIME session with
;; Incanter loaded. 

;; You'll need clojure-mode and SLIME installed. Install clojure-mode
;; manually or through ELPA (http://tromey.com/elpa) and then hit M-x
;; clojure install to get SLIME configured.

;;; Code:

(defvar incanter-root (concat
                       (file-name-directory
                        (or (buffer-file-name) load-file-name))
                       "../")
  "Directory containing the incanter installation.")

(defun incanter ()
  "Start or switch to an incanter session with SLIME."
  (interactive)
  (if (not (functionp 'clojure-mode))
      (error "You need clojure-mode and SLIME installed. See http://technomancy.us/126")
    (if (get-buffer "*incanter*")
      (switch-to-buffer "*incanter*")
    (setq swank-clojure-binary nil
          swank-clojure-jar-path (expand-file-name "lib/clojure.jar" incanter-root)
          swank-clojure-extra-classpaths
          (append (list (expand-file-name "incanter.jar" incanter-root))
           (let ((lib (expand-file-name "lib" incanter-root)))
             (if (file-exists-p lib)
                 (directory-files lib t ".jar$")))))
    (add-hook 'slime-connected-hook 'incanter-init)
    (slime))))

(defun incanter-init ()
  (save-excursion 
    (switch-to-buffer "*slime-repl clojure*")
    (rename-buffer "*incanter*")
    (slime-eval-async `(swank:eval-and-grab-output
                        "(use '(incanter core stats charts))"))))

(provide 'incanter) ;;; incanter.el ends here
