# Logical Workbench with Clojure

This repository holds the work of a research project of the Technische Hochschule Mittelhessen (http://www.thm.de). It is a kind of playground for logical functions like parsing formulas and printing truth tables. It shall later replace the MPA (http://homepages.thm.de/~hg11260/mpa.html).

## Features

+ Parse a 'human-readable' formula to clojure code (e.g. `"A and (B or C)"` becomes `(and A (or B C))`).
+ A couple of macros to serve all common logic functions (nand, nor, impl, nipml, cimpl, ncimpl, equiv, xor).
+ Generate a truth table as a relation.
+ Print truth tables with up to 10 variables.
+ Generate the cnf (conjunctive normal form) of a formula, including all substeps (free from implications, build nnf).
+ Perform the tseitin transformation (generating a cnf with linear complexity).
+ 'Flattening' functions `and` and `or` (e.g. `(and A (and B C))` becomes `(and A B C)`).
+ Creating the dimacs file format for a cnf formula.

## Dependencies

The logical workbench is developed and testes with Clojure 1.3.0, but it might work with earlier versions as well.

Besides, it need the following libraries:

+ [instaparse](https://github.com/Engelberg/instaparse)
+ [math.combinatorics](https://github.com/clojure/math.combinatorics)

They are all available through the Leiningen/Maven repository.

## Usage

This project is in process. Therefore the following might not be complete at all, but shall serve as both - guide and documentation.

### Accessing the functions 

But the source files in your classpath and require or use the namespace 'logic.util', e.g.

```clj
(require [logic.util :as log])
; or
(use [logic.util])
```
All important functions are defined in this namespaces. In case you might wonder: the name is 'util' and not 'core' to prevent confusion with 'core.logic'.

### A simple example

As a first example, you might try:

```clj
> (truth-table "A and B")

Truth table for formula: A and B
Parsed clojure code: (and A B)
Showing lines: all
A B Φ 
T T T      
T F F      
F T F      
F F F      
nil
```

The function `truth-table` takes a formula and prints the truth table for it.

### The two type of formulas: human-readable and clojure code

There are two different types of formulas that can be handled with this library, and it is important to keep track of it.

All functions (like cnf transformation, for example) basically work with the second type (clojure code), but all 'big' ones will also have a wrapper that automatically parses a human-readable formula and invokes the function on it.

#### Human-readable formulas

The first one is - how we call it - a 'human-readable' form (this term is not really good, because the other type is also easily readable by humans). It uses infix operators and has an own syntax. Just to give you an idea, here are a couple of them:

```
A and B
A & B
!A
!(A <-> (B <- !C) nor D
```

Such a formula can be transformed into clojure code with the function `create-ast` like this:

```clj
> (create-ast "A and B")
(and A B)
```

In case you need to do it manually, the parsing is done with `logic-parse` and the returned ast is transformed with `transform-ast`:

```clj
> (transform-ast (logic-parse "A and B"))
(and A B)
```

#### Grammar for human-readable formulas

The atoms for a formula are either booleans or variables. 

A *variable* begins with a letter or a special char, and continues with either of them or a digit. A letter may consist of the characters `A-Z a-z Ä ä Ö ö Ü ü`, and a special char is one of `{}.\_`.

The *boolean constants* can be written like this:

+ true: `True, true, T, t, 1`
+ false: `False, false, F, f, `

All formulas can be grouped with a matching pair of parenthesis `( )` or brackets `[ ]`.

If more arguments than two are connected with a binary operator (there are no n-ary operators here), they are nested corresponding to their associativity:

```clj
> (create-ast "A and B and C")
(and (and A B) C)
```

This is the syntax for binary functions, descending order of precedence:

| Function | Symbols | Associativity |
|----------|---------|---------------|
| not | !, not | - |
| and | &, and | left |
| not and | !&, nand | left |
| or | &#124;, or | left |
| not or | !&#124;, nor | left |
| from, if, converted implication | <-, if | right |
| not from, if, conv. impl. | !<-, nif | right |
| implication | ->, impl | right |
| not implication | !->, nimpl | right |
| equivalent | <->, iff | left |
| exclusive or | ^, xor | left |

#### Clojure code

The second type of formulas would be what human-readable code transforms to: direct clojure code. A few examples:

```clj
(and A B)
(or A B)
(or A B C D E)
(impl C (equiv (not A) B))
```

Be aware that these forms - of course - need to be quoted:

```clj
(fancy-function '(and (or a b) c))
```

For `and, or, not` the standard clojure macros are used; all other logical functions are new macros, that expand to either `and, or, not`, or a combination of them. Here is the complete list:

| Logical function | Macro name | Arity | Expands to (called (f a b)) |
|------------------|------------|-------|-----------------------------|
| negation | not | unary | (see clj code) |
| and | and | n-ary | (see clj code) |
| negated and | nand | binary | `(not (and a b))` |
| or | or | n-ary | (see clj code) |
| negated or | nor | binary | `(not (or a b))` |
| implication | impl | binary | `(or (not a) b)` |
| negated implication | nimpl | binary | `(not (impl a b))` |
| from, if, converted implication | cimpl | binary | `(or (not b) a)` |
| negated from, if, cimpl | ncimpl | binary | `(not (cimpl a b))` |
| equivalent | equiv | binary | `(or (and a b) (and (not a) (not b)))` |
| exclusive or | xor | binary | `(not (equiv a b))` |

### Flattening formulas

While the functions `and` and `or` (and only they) have an arbitrary arity, it might be useful to 'flatten' a formula, like this:

```clj
> (flatten-ast '(and (and A B) C))
(and A B C)
```

The function `flatten-ast` recursively flattens nested forms of n-ary functions.

### Truth tables

A truth table itself is a list of vectors repesenting a relation. However, it is embedded in a hash map that contains some other information. The name of the function is `code-to-truth-table`, and accepts a formula in clojure code.

```clj
> (code-to-truth-table '(equiv A B))

{:symbols [A B :result], 
 :formula (equiv A B), 
 :lines :all, 
 :table ([true true true] [true false false] [false true false] [false false true])}
```

The keys are defined like this:

| Key | Example | Description |
|-----|---------|-------------|
| `:symbols` | `[A B :result]` | The name of the variables of the formula. The order is the same as they appear in `:table`. `:result` holds the value for the valuation (so it can be matched in the order of `:table`). |
| `:formula` | `(equiv A B)` | The formula this truth table is made for (always a clojure formula). |
| `:lines` | `:all` | One of `:all`, `:true-only` or `:false-only`. If not `:all`, only lines are contained where the result is true (or false correspondingly). See below. |
| `:table` | `([true false false])` | The actual truth table; a list of vectors, which represent the rows of the table. The order of the values correspond to the one of `:symbols`. |

The function can produce truth tables where only rows with the result true (or false) can be shown. This is done with the parameter `:lines` that accepts `:all` (default), `:true-only` or `:false-only`:

 ```clj
> (code-to-truth-table '(equiv A B) :lines :true-only)

{:symbols [A B :result], 
 :formula (equiv A B), 
 :lines :true-only, 
 :table ([true true true] [false false true])}
 
> (code-to-truth-table '(equiv A B) :lines :false-only)

{:symbols [A B :result], 
 :formula (equiv A B), 
 :lines :false-only, 
 :table ([true false false] [false true false])}
```

The function to print a truth table without producing the truth table relation before (automatically done) is `tt` for a clojure formula, and `truth-table` for a human-readable one:

```clj
> (tt '(impl A B))

Truth table for formula: phi
Parsed clojure code: (impl A B)
A B Φ 
T T T      
T F F      
F T T      
F F T      
nil

> (truth-table "A -> B")

Truth table for formula: A -> B
Parsed clojure code: (impl A B)
A B Φ 
T T T      
T F F      
F T T      
F F T      
nil
```

Both functions can handle up to 10 variables and accept the keyword `:lines` as the function `code-to-truth-table` does (it is a straight forward of the parameter):

```clj
> (truth-table "A <-> B" :lines :all)

Truth table for formula: A <-> B
Parsed clojure code: (equiv A B)
Showing lines: all
A B Φ 
T T T      
T F F      
F T F      
F F T      
nil

> (truth-table "A <-> B" :lines :true-only)

Truth table for formula: A <-> B
Parsed clojure code: (equiv A B)
Showing lines: true only
A B Φ 
T T T      
F F T      
nil

> (truth-table "A <-> B" :lines :false-only)

Truth table for formula: A <-> B
Parsed clojure code: (equiv A B)
Showing lines: false only
A B Φ 
T F F      
F T F      
nil
```

### CNF transformation

The function `transform-cnf` takes a formula in clojure code and produces the conjunctive normal form of it:

```clj
> (transform-cnf '(or A (and B (impl C D))))

(and (or A B) (or (not C) D A))
```

It sequentially calls the following functions:

+ `impl-free` removes all composed macros such that only `and`, `or` and `not` appear in the formula afterwards.
+ `nnf` takes an impl-free formula and produces the negation normal form. That means only atoms are negated, but not formulas with subformulas (`(or (not A) (not B))` is nnf, while `(not (and A B))` is not). It also removes double negations (`(not (not A))` becomes `A`).
+ `cnf` takes an impl-free nnf formula and finally produces the cnf. It uses the function `distr`, which can apply the distributive form to change an 'and formula' to an 'or formula'.
+ `flatten-ast` removes nested functions of the same type.

So the above function call `(transform-cnf '(or A (and B (impl C D))))` would stepwise do this:

```clj
> (impl-free '(or A (and B (impl C D))))

(or A (and B (or (not C) D)))

> (nnf (impl-free '(or A (and B (impl C D)))))

(or A (and B (or (not C) D)))  ; does nothing in this case

> (cnf (nnf (impl-free '(or A (and B (impl C D))))))

(and (or A B) (or A (or (not C) D)))

> (flatten-ast (cnf (nnf (impl-free '(or A (and B (impl C D)))))))

(and (or A B) (or (not C) D A))
```

### Tseitin transformation

Just like `transform-cnf`, the function `tseitin-transform` creates a cnf, but with linear complexity by using the tseitin transformation:

```clj
> (tseitin-transform '(or A (and B (impl C D))))

(and 
    (or (not t_2851) A t_2852) 
    (or t_2851 (not A)) 
    (or t_2851 (not t_2852)) 
    (or t_2853 C) 
    (or t_2853 (not D)) 
    (or (not t_2853) (not C) D) 
    (or t_2852 (not B) (not t_2853)) 
    (or (not t_2852) B) 
    (or (not t_2852) t_2853) 
    t_2851)
```

It is recommended, to use the tseitin-transformation for at least larger formulas, when using the sat solver. It uses the `gensym` function with the value of `tseitin-prefix` (defined in util.clj) as its prefix. 

### Dimacs file format transformation

When having a formula in cnf (either by using `transform-cnf` or `tseitin-transform`), the function `generate-dimcas` takes this formula, and produces a string representing this formula in the dimacs file format. This is needed by most sat solvers.

```clj
; println is only used to show \n as an actual new line.
> (println (create-dimacs (tseitin-transform '(or A (and B (impl C D))))))

c dimacs file for the logic formula
c (and (or (not t_2935) ...  ; (shortened to keep it readable)
c 
p cnf 7 10
-5 1 6 0
5 -1 0
5 -6 0
7 3 0
7 -4 0
-7 -3 4 0
6 -2 -7 0
-6 2 0
-6 7 0
5 0

nil
```

### SAT solving

not implemented yet

## License

Copyright © 2013 Markus Bader

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
