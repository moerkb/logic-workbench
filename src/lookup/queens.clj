(ns mpa.queens
  (:use [clojure.java.io :as io])
  (:use [clojure.string  :as str :only [join]])
  )

; the problem is encoded by variables v[x,y] (x (range 1 n), y (range 1 n)
; where v[x,y] = true iff a queen is placed on this field

; each variable v[x,y] (x = 1..n, y = 1..n) has an integer code for dimacs
(defn varcode [n x y]
  (+ (* n (dec x)) (dec y) 1))

; calculate number of clauses for n > 1
; n^3 + {\sum_{k=2}^{n-1} 2k(k-1)}
(defn num-clauses [n]
  (let [diag-cl (apply + (map #(* 2 % (- % 1)) (range 2 n)))]
    (+ (* n n n) diag-cl)))

; makes clause for at least one varcode in vec v is true"
; (min-one [2 4 6] -> "2 4 6 0 \n"
(defn min-one [v]
  (str (str/join " " v) " 0 \n"))

; makes clause for "not both varcodes in vec v of length 2 are true"
; (not-both [3 7] -> "-3 -7 0 \n"
(defn not-both [v]
  (str (str/join " " (vec (map #(* -1 %) v))) " 0 \n"))
  ;(let [vec0 (conj (vec (map #(* -1 %) v)) 0)]
  ;  (apply str (interpose " " vec0))))
    
; makes clauses for "at most one varcode in vec v is true"
; (max-one [4 2 7] -> "-4 -2 0 \n-4 -7 0\n -2 -7 0 \n"
(defn max-one [v]
  (let [n (count v)]
    (str/join (for [i (range (dec n)) j (range (inc i) n)] (not-both [(v i) (v j)])))))

; makes vectors for rows
(defn- rows-vec [n]
  (let [r (range 1 (+ (* n n) 1))]
  (vec (map vec (partition n r)))))


; makes vectors for columns
(defn- cols-vec [n]
  (let [m (rows-vec n)]
    (vec (apply map vector m))))
 
; makes vectors for diagonals
(defn- diag-a-vec[n]
  (vec 
    (for [i (range 1 n)]
      (vec (for [k (range (inc (- n i)))]
        (varcode n (+ i k) (+ 1 k)))))))

(defn- diag-b-vec[n]
  (vec 
    (for [i (range 2 n)]
      (vec (for [k (range i)]
        (varcode n (- i k) (- n k)))))))

(defn- diag-c-vec[n]
  (vec 
    (for [j (range 2 n)]
      (vec (for [k (range j)]
        (varcode n (+ 1 k) (- j k)))))))

(defn- diag-d-vec[n]
  (vec 
    (for [j (range 1 n)]
      (vec (for [k (range (inc (- n j)))]
        (varcode n (- n k) (+ j k)))))))

; min-one for rows 
; (from the rest of the constraints follows min-one for the columns)
(defn- write-min-one [wrt n]
  (do
    (.write wrt (str/join (map min-one (rows-vec n))))
  ))

; max-one for rows, columns and diagonals
(defn- write-max-one [wrt n]
  (do
    (.write wrt (str/join (map max-one (rows-vec n))))
    (.write wrt (str/join (map max-one (cols-vec n))))
    (.write wrt (str/join (map max-one (diag-a-vec n))))
    (.write wrt (str/join (map max-one (diag-b-vec n))))
    (.write wrt (str/join (map max-one (diag-c-vec n))))
    (.write wrt (str/join (map max-one (diag-d-vec n))))
  ))

; head
(defn- write-head [wrt n]
  (do
    (.write wrt (str "c " n "-queens problem in cnf dimacs format\nc by Burkhardt Renz THM\n"))
  ))

; index
(defn- write-index [wrt n]
  (doseq [x (range 1 (inc n)) y (range 1 (inc n))]
    (.write wrt (str "c " [x y] " " (varcode n x y) "\n"))
  ))

; prolog
(defn- write-prolog [wrt n]
  (do
    (.write wrt (str "p cnf " (* n n) " " (num-clauses n) "\n"))
  ))

; writes dimac file for n queens problem in file named filename
(defn write-nqueens-dimacs [n filename]
  (with-open [wrt (io/writer filename)]
    (do
      (write-head   wrt n)
      (write-index  wrt n)
      (write-prolog wrt n)
      (write-min-one  wrt n)
      (write-max-one  wrt n)
    )))
    