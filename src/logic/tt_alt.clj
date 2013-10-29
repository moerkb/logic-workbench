;   Copyright (c) Burkhardt Renz THM. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns mpa.core
  (:use mpa.util)
  (:use [clojure.math.combinatorics :only [selections]]))

; unary operator
; (not ...) see clojure.core

; binary operators

; (and ...) see clojure.core

(defn nand [p q]
  (not (and p q)))
  
; (or ...) see clojure.core  
  
(defn nor [p q]
  (not (or p q)))
  
(defn impl [p q]
  (or (not p) q))

(defn nimpl [p q]
  (not (impl p q)))
  
; converse implication aka if
(defn cimpl [p q]
  (impl q p))

(defn ncimpl [p q]
  (not (cimpl p q)))

(defn iff [p q]
  (=  p q))

(defn xor [p q]
  (not (iff p q)))
  
; helper
(defn- error [type s]
  (throw (IllegalArgumentException. (format "- %s: '%s'." type s))))

; global set of operators
(def phiops #{'not 'and 'nand 'or 'nor 'impl 'nimpl 'cimpl 'ncimpl 'iff 'xor})

; phivar? decides whether a symbol is a propositional variable
(defn phivar? [item]
    (and (symbol? item) (not-any? #(= item %) phiops)))

; besser zwei Funktionen, die eine mit Fehlerprüfung, die andere ohne
; dann muss bei phival normalerweise nicht der dispatch-mechanismus
; verwendet werden
(defn phival-dispatch [phi _]
  (cond
    (and (list? phi) (not (empty? phi))) :valid
    (phivar? phi) :valid
    :else :invalid))

(defmulti phival phival-dispatch)

; value of a formula phi under a given valuation vmap
(defmethod phival :valid [phi valuation]
  (try
    (eval `(let ~valuation ~phi))
    (catch Exception e (error "Valuation not possible" (str phi " with " valuation)))
    ))
  
(defmethod phival :invalid [phi valuation]
  (error "Not a valid formula" (str phi)))

; the set of atoms of formula phi
(defn- phivar-set [phi]
  (apply sorted-set (filter phivar? (flatten phi))))

(defn- get-atom-set-old [phi]
  (set (filter keyword? (flatten phi))))

; print header of truth table
(defn- print-tt-head [vset]
  (do
    (doseq [item vset]
      (print (format "%-6s" item))))
    (println "phi"))

; print line of truth table
(defn- print-tt-line [valuation phival]
  (do
    (doseq [aval (take-nth 2 (next valuation))]
      (print (format "%-6s" aval)))
    (println phival)))

; print truth table of formula phi
(defn print-tt [phi]
  (let [vset (phivar-set phi), vset-cnt (count vset)]
    (do
      (print-tt-head vset)
      (doseq [comb (selections [false true] vset-cnt)]
        (let [valuation (vec (interleave vset comb))]
          (print-tt-line valuation (phival phi valuation))))
    )))
      