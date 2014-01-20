(ns logic.kern
  (:use [blancas.kern core expr]
        [blancas.kern.lexer java-style]))

#_(
  minimal sudoku pseudocode grammar
  
  or     := and "|" and
  and    := unexp "&" unexp
  unexp  := "!"? exp
  
  exp    := atom | "(" or ")"
  
  atom   := "c" digit*
)

(declare or-parser)

(def special-chars (one-of* "_{}\\."))
(def ident-parser (<+>
                    (<|>
                      letter
                      special-chars)
                    (many (<|>
                            letter
                            special-chars
                            digit))))

(def ident (<$> symbol ident-parser))

(def exp (<|> 
           ident
           (<< (>> (one-of* "(") or-parser) (one-of* ")"))
           (<< (>> (one-of* "[") or-parser) (one-of* "]"))))

(def not-sym (>> 
               (token "!" "not") 
               (return #(list 'not %))))

(def unexp (prefix1 exp not-sym))

(def and-sym (>>
               (token "&" "and")
               (return #(list 'and %1 %2))))
(def and-parser (chainl1 unexp and-sym))

(def or-sym (>> 
              (token "|" "or")
              (return #(list 'or %1 %2))))
(def or-parser (chainl1 and-parser or-sym))


; examples
(run or-parser "a & b | c")
;(run or-parser "(((((c1)))))")

;(time (parse-data or-parser rules))
