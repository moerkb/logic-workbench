(ns logic.kern
  (:use [blancas.kern core expr]))

stop

#_(
  pseudocode grammar
  
  or     := and "|" and
  and    := unexp "&" unexp
  unexp  := "!"? exp
  exp    := atom | "(" or ")"
  
  atom   := "c" digit*
)

(declare or-parser)

(def atom-parser (<+> letter (many1 digit)))
(def atom (<$> symbol atom-parser))

(def exp (<|> atom (<< (>> (one-of* "(") or-parser) (one-of* ")"))))

(def not-sym (>> (token* "!") (return #(list 'not %))))
(def unexp (prefix1 exp not-sym))

(def and-sym (>> (token* "&") (return #(list 'and %1 %2))))
(def and-parser (chainl1 unexp and-sym))

(def or-sym (>> (token* "|") (return #(list 'or %1 %2))))
(def or-parser (chainl1 and-parser or-sym))


; examples
(parse or-parser "c1&!(c2|!c3)&(c4&!c5)")
(run or-parser "(((((c1)))))")

(time (run or-parser sudoku))
