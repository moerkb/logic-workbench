(ns parser_measures.kern_simple
  (:require 
    [blancas.kern [core :refer :all] [expr :refer :all]]
    [blancas.kern.lexer :as lex]))

; custom lexer
(def special-chars (one-of* "_"))

(def mpa-style
  (assoc lex/java-style
    :identifier-start (<|> letter special-chars) 
    :identifier-letter (<|> letter special-chars digit)))

(def- rec (lex/make-parsers mpa-style))

(def trim (:trim rec))
(def lexeme (:lexeme rec))
(def sym (:sym rec))
(def new-line (:new-line rec))
(def one-of (:one-of rec))
(def none-of (:none-of rec))
(def token (:token rec))
(def word (:word rec))
(def identifier (:identifier rec))
(def field (:field rec))
(def char-lit (:char-lit rec))
(def string-lit (:string-lit rec))
(def dec-lit (:dec-lit rec))
(def oct-lit (:oct-lit rec))
(def hex-lit (:hex-lit rec))
(def float-lit (:float-lit rec))
(def bool-lit (:bool-lit rec))
(def nil-lit (:nil-lit rec))
(def parens (:parens rec))
(def braces (:braces rec))
(def angles (:angles rec))
(def brackets (:brackets rec))
(def semi (:semi rec))
(def comma (:comma rec))
(def colon (:colon rec))
(def dot (:dot rec))
(def semi-sep (:semi-sep rec))
(def semi-sep1 (:semi-sep1 rec))
(def comma-sep (:comma-sep rec))
(def comma-sep1 (:comma-sep1 rec))

; the actual parser
(def ident
  (bind [id identifier]
    (return (symbol id))))

(declare start-parser)

(def exp (<|> 
           (parens (fwd start-parser))
           ident))

(def not-sym (>> 
               (token "!") 
               (return #(list 'not %))))
(def unexp (prefix1 exp not-sym))

(def and-sym (>>
               (token "&")
               (return #(list 'and %1 %2))))
(def and-parser (chainl1 unexp and-sym))

(def or-sym (>> 
              (token "|")
              (return #(list 'or %1 %2))))
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
