(ns logic.kern
  (:require 
    [clojure.repl :refer :all]
    [blancas.kern [core :refer :all] [expr :refer :all]]
    [blancas.kern.lexer :as lex]))

; custom lexer
(def special-chars (one-of* "_{}\\."))

(def mpa-style
  (assoc lex/java-style
    :identifier-start (<|> letter special-chars) 
    :identifier-letter (<|> letter special-chars digit)
    :reserved-names ["and" "nand" "or" "nor" "impl" "nimpl" "if" "nif" "iff" "xor"
                     "false" "False" "0" "true" "True" "1"]))

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

(def true-parser
  (>>
    (token "true" "True" "1")
    (return true)))

(def false-parser
  (>>
    (token "false" "False" "0")
    (return false)))

(declare start-parser)

(def exp (<|> 
           (parens (fwd start-parser))
           (brackets (fwd start-parser))
           true-parser
           false-parser
           ident))

(def not-sym (>> 
               (token "!" "not") 
               (return #(list 'not %))))
(def unexp (prefix1 exp not-sym))

(def nand-sym (>>
                (token "!&" "nand")
                (return #(list 'nand %1 %2))))
(def nand-parser (chainl1 unexp nand-sym))

(def and-sym (>>
               (token "&" "and")
               (return #(list 'and %1 %2))))
(def and-parser (chainl1 nand-parser and-sym))

(def nor-sym (>> 
               (token "!|" "nor")
               (return #(list 'nor %1 %2))))
(def nor-parser (chainl1 and-parser nor-sym))

(def or-sym (>> 
              (token "|" "or")
              (return #(list 'or %1 %2))))
(def or-parser (chainl1 nor-parser or-sym))

(def if-sym (>>
              (token "<-" "if")
              (return #(list 'cimpl %1 %2))))
(def if-parser (chainr1 or-parser if-sym))

(def nif-sym (>>
               (token "!<-" "nif")
               (return #(list 'ncimpl %1 %2))))
(def nif-parser (chainr1 if-parser nif-sym))

(def impl-sym (>>
                (token "->" "impl")
                (return #(list 'impl %1 %2))))
(def impl-parser (chainr1 nif-parser impl-sym))

(def nimpl-sym (>>
                 (token "!->" "nimpl")
                 (return #(list 'nimpl %1 %2))))
(def nimpl-parser (chainr1 impl-parser nimpl-sym))

(def equiv-sym (>> 
                 (token "<->" "iff")
                 (return #(list 'equiv %1 %2))))
(def equiv-parser (chainl1 nimpl-parser equiv-sym))

(def xor-sym (>> 
               (token "^" "xor")
               (return #(list 'xor %1 %2))))
(def xor-parser (chainl1 equiv-parser xor-sym))

(def start-parser xor-parser)

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
