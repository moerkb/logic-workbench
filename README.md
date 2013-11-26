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
| negated and | nand | binary | ```clj (not (and a b))``` |
| or | or | n-ary | (see clj code |
| negated or | nor | binary | (not (or a b)) |
| implication | impl | binary | (or (not a) b) |
| negated implication | nimpl | binary | (not (impl a b)) |
| from, if, converted implication | cimpl | binary | (or (not b) a) |
| negated from, if, cimpl | ncimpl | binary | (not (cimpl a b)) |
| equivalent | equiv | binary | (or (and a b) (and (not a) (not b))) |
| exclusive or | xor | binary | (not (equiv a b)) |

### Flattening formulas

### Truth tables

### CNF transformation

### Tseitin transformation

### Dimacs file format transformation

### SAT solving

not implemented yet

## License

Copyright © 2013 Markus Bader

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
