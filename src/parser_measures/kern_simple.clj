(ns parser_measures.kern_simple
  (:require 
    [blancas.kern [core :refer :all] [expr :refer :all]]))

#_(
  pseudocode grammar
  
  or := and "|" and
  and := unexp "&" unexp
  unexp := "!"? exp
  exp := atom | "(" or ")"
  
  atom := "c" digit*
)

(declare or-parser)

(def atom-parser (<+> letter (many1 digit)))
(def atoms (<$> symbol atom-parser))

(def exp (<|> atoms (<< (>> (one-of* "(") (fwd or-parser)) (one-of* ")"))))

(def not-sym (>> (token* "!") (return #(list 'not %))))
(def unexp (prefix1 exp not-sym))

(def and-sym (>> (token* "&") (return #(list 'and %1 %2))))
(def and-parser (chainl1 unexp and-sym))

(def or-sym (>> (token* "|") (return #(list 'or %1 %2))))
(def or-parser (chainl1 and-parser or-sym))

(def start-parser or-parser)

; call function
(defn kern-parse [parse-string]
  "Takes a formula and parses it to clojure code with the kern parser."
  (let [res (parse-data start-parser parse-string)]
    (if (:ok res)
      (:value res)
      (do
        (println "An error occurred while parsing.")
        false))))

; examples
;(parse-data start-parser "a xor !(c !<- (false !-> b))")
