# Logic Workbench with Clojure

This repository holds the work of a research project of the University of Applied Sciences Mittelhessen (http://www.thm.de). It is a kind of playground for logical functions like parsing formulas and printing truth tables. It shall later replace the MPA (http://homepages.thm.de/~hg11260/mpa.html).

## Features

+ Parse an infix formula to a prefix formula/clojure code (e.g. `"A and (B or C)"` becomes `(and A (or B C))`).
+ A couple of macros to serve all common logic functions (nand, nor, impl, nipml, cimpl, ncimpl, equiv, xor).
+ Generate a truth table as a relation.
+ Print truth tables with up to 10 variables.
+ Generate the cnf (conjunctive normal form) of a formula, including all substeps (free from implications, build nnf).
+ Perform the tseitin transformation (generating a cnf with linear complexity).
+ 'Flattening' functions `and` and `or` (e.g. `(and A (and B C))` becomes `(and A B C)`).
+ Creating the dimacs file format for a cnf formula.
+ SAT Solving using SAT4J.
+ A GUI to work with infix formulas.

## Dependencies

The logic workbench is developed and testes with Clojure 1.3.0, but it might work with earlier versions as well.

It needs a couple of libraries such as Seesaw. They are all acquired in project.clj and are available through the Leiningen/Maven repository (that means you do not need to install anything manually).

## GUI and REPL

There are two different way in which this program can be accessed - depending on the user's knowledge and needs.

There is a *GUI*, that works with infix formulas only. So it is suitable for people not familiar with clojure and who want to have an easy, ready-to-go tool to work with. By now, you need to load gui/main.clj in your REPL, but a all-in-one JAR is coming soon. You will not get much help for the GUI on this site, but by the build in help system (Menu: Help or press F1).

The other, underlying layer is REPL-based. These are the functions that are used by the GUI, but even more. Read through this page to get started and to get an idea of what the application can do for you.

## Usage

This project is in process. Therefore the following might not be complete at all, but shall serve as both - guide and documentation.

### Accessing the functions 

But the source files in your classpath and require or use the namespace 'logic.util', e.g.

```clj
(require [logic.util :as log])
; or
(use [logic.util])
```
All important functions are defined in this namespaces. In case you wonder: the name is 'util' and not 'core' to prevent confusion with 'core.logic'.

### A simple example

As a first example, you might try:

```clj
> (truth-table-parse "A and B")

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

The function `truth-table-parse` takes a formula and prints the truth table for it.

### The two type of formulas: infix and prefix/clojure code

There are two different types of formulas that can be handled with this library, and it is important to keep track of it.

All functions (like cnf transformation, for example) basically work with the prefix formulas (clojure code), but some exist with the postfix '-parse', which automatically parse an infix formula and invokes the function on it (e.g. truth-table-parse).

#### Infix formulas

The first one is an infix form and has an own syntax. Just to give you an idea, here are a couple of them:

```
A and B
A & B
!A
!(A <-> (B <- !C) nor D
```

Such a formula can be transformed into prefix clojure code with the function `clojure-formula` like this:

```clj
> (clojure-formula "A and B")

(and A B)
```

In case you need to do it manually, the parsing is done with `parse`:

```clj
> (parse "A and B")

(and A B)
```

#### Grammar for infix formulas

The atoms for a formula are either booleans or variables. 

A *variable* begins with a letter or a special char, and continues with either of them or a digit. A letter may consist of the characters `A-Z a-z Ä ä Ö ö Ü ü`, and a special char is one of `{}.\ _`.

The *boolean constants* can be written like this:

+ true: `True, true, T, t, 1`
+ false: `False, false, F, f, 0`

All formulas can be grouped with a matching pair of parenthesis `( )` or brackets `[ ]`.

If more arguments than two are connected with a binary operator (there are no n-ary operators here), they are nested corresponding to their associativity:

```clj
> (clojure-formula "A and B and C")

(and (and A B) C)
```

This is the syntax for binary functions, descending order of precedence:

| Function | Symbols | Associativity |
|----------|---------|---------------|
| not | `!`, `not` | - |
| and | `&`, `and` | left |
| not and | `!&`, `nand` | left |
| or | <code>&#124;</code>, `or` | left |
| not or | <code>!&#124;</code>, `nor` | left |
| from, if, converted implication | `<-`, `if` | right |
| not from, if, conv. impl. | `!<-`, `nif` | right |
| implication | `->`, `impl` | right |
| not implication | `!->`, `nimpl` | right |
| equivalent | `<->`, `iff` | left |
| exclusive or | `^`, `xor` | left |

#### Prefix formulas / clojure code

The second type of formulas would be what infix code can be parsed to: direct clojure code. A few examples:

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

A truth table itself is a list of vectors repesenting a relation. However, it is embedded in a hash map that contains some other information. The name of the function is `generate-truth-table`, and accepts a formula in clojure code.

```clj
> (generate-truth-table '(equiv A B))

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
> (generate-truth-table '(equiv A B) :lines :true-only)

{:symbols [A B :result], 
 :formula (equiv A B), 
 :lines :true-only, 
 :table ([true true true] [false false true])}
 
> (generate-truth-table '(equiv A B) :lines :false-only)

{:symbols [A B :result], 
 :formula (equiv A B), 
 :lines :false-only, 
 :table ([true false false] [false true false])}
```

The function to print a truth table without producing the truth table relation before (automatically done) is `truth-table` for a prefix formula, and `truth-table-parse` for an infix one:

```clj
> (truth-table '(impl A B))

Truth table for formula: phi
Parsed clojure code: (impl A B)
A B Φ 
T T T      
T F F      
F T T      
F F T      
nil

> (truth-table-parse "A -> B")

Truth table for formula: A -> B
Parsed clojure code: (impl A B)
A B Φ 
T T T      
T F F      
F T T      
F F T      
nil
```

Both functions can handle up to 10 variables and accept the keyword `:lines` as the function `generate-truth-table` does (it is a straight forward of the parameter):

```clj
> (truth-table-parse "A <-> B" :lines :all)

Truth table for formula: A <-> B
Parsed clojure code: (equiv A B)
Showing lines: all
A B Φ 
T T T      
T F F      
F T F      
F F T      
nil

> (truth-table-parse "A <-> B" :lines :true-only)

Truth table for formula: A <-> B
Parsed clojure code: (equiv A B)
Showing lines: true only
A B Φ 
T T T      
F F T      
nil

> (truth-table-parse "A <-> B" :lines :false-only)

Truth table for formula: A <-> B
Parsed clojure code: (equiv A B)
Showing lines: false only
A B Φ 
T F F      
F T F      
nil
```

### CNF transformation

The function `transform-cnf` takes a formula in prefix clojure code and produces the conjunctive normal form of it:

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

Just like `transform-cnf`, the function `transform-tseitin` creates a cnf, but with linear complexity by using the tseitin transformation:
It is recommended, to use the tseitin transformation for at least larger formulas, when using the sat solver. It uses an increasing number with `tseitin-prefix` (defined in util.clj) as its prefix. 

```clj
> (transform-tseitin '(or A (and B (impl C D))))

{:formula (or A (and B (impl C D))), 
 :subs {t_1 (and (or (not t_1) t_2 t_3) (or t_1 (not t_2)) (or t_1 (not t_3))), 
        t_4 B, 
        t_5 (and (or t_5 t_6) (or t_5 (not t_7)) (or (not t_5) (not t_6) t_7)), 
        t_7 D, 
        t_6 C, 
        t_3 (and (or t_3 (not t_4) (not t_5)) (or (not t_3) t_4) (or (not t_3) t_5)), 
        t_2 A}, 
 :lits #{t_2 t_6 t_7 t_4}, 
 :tseitin-formula (and (or (not t_1) t_2 t_3) 
                       (or t_1 (not t_2)) 
                       (or t_1 (not t_3)) 
                       (or t_5 t_6) 
                       (or t_5 (not t_7)) 
                       (or (not t_5) (not t_6) t_7) 
                       (or t_3 (not t_4) (not t_5)) 
                       (or (not t_3) t_4) 
                       (or (not t_3) t_5) t_1)}
```

The returned map has the following content:

| Key | Example | Description |
|-----|---------|-------------|
| :formula | `(or A (and B (impl C D)))` | The original formula |
| :subs | `{t_1 A, t_2 B, t_3 (and ...)` | A substitution for the tseitin symbols. |
| :lits | `{t_1 t_2}` | A set of all tseitin symbols that are literals. |
| :tseitin-formula | `(and (or t_1 t_2) ...)` | The final tseitin formula. |

### Dimacs file format transformation

When having a formula in cnf (either by using `transform-cnf` or `transform-tseitin`), the function `generate-dimacs` takes this formula, and produces a string representing this formula in the dimacs file format. This is needed by most sat solvers.

```clj
; println is only used to show \n as an actual new line.
> (println (generate-dimacs (transform-tseitin '(or A (and B (impl C D))))))

c dimacs file for the logic formula
c (and (or (not t_2935) ...  ; (shortened to keep it readable here)
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

```

It is also possible to just receive the clauses as lists via `generate-dimacs-clauses`:

```clj
> (generate-dimacs-clauses (:tseitin-formula (transform-tseitin '(equiv a b))))

{:formula (and (or (not t_1) t_2 ; ... shortened here, 
 :num-vars 3, 
 :num-clauses 5, 
 :clauses #{#{1} #{-1 2 -3} #{-1 -2 3} #{1 -2 -3} #{1 2 3}}, 
 :subs {1 t_1, 2 t_2, 3 t_3}}
```

It returns a hash map, containing the following information:

| Key | Example | Description |
|-----|---------|-------------|
| `:formula` | `(and (or a b) (or b c))` | The original cnf formula, these dimacs clauses are for. |
| `:num-vars` | `3` | The total number of different variables in the formula. |
| `:num-clauses` | `2` | The total number of clauses in the cnf formula. |
| `:clauses` | `#{#{3} #{1 -2 -3}}` | The actual clauses as a set of sets. |
| `:subs` | `{1 a, 3 t_2894}` | A map containing all substitutions (number to actual symbol) |

### SAT solving

The currently used SAT solver is SAT4J (http://www.sat4j.org). They are used with the functions `sat-solve` and `sat-solve-dimacs`, which take a dimacs map like this:

```clj
=> (sat-solve-dimacs (generate-dimacs-clauses '(impl (and a b) c)))

[1 -1 2 3]
```

The first element of the returned vector indicates if the formula is satisfiable: 1 means it is, 0 means it is unsatisfiable.

Right now, we only get the numbers for the correct solution, as they are generated in the dimacs process. To resubstitute the variable names we got - guess what - another function:

```clj
=> (sat-solve (generate-dimacs-clauses '(impl (and a b) c)))

[1 (not a) b c]
```

Again, the first element shows if the proposition is satisfiable.

You can use the functions `sat-solve-find-all-results`, `sat-solve-find-all-results-formula` and `sat-solve-dimacs-find-all-results` to get all results, but be careful! You usually use the SAT solver for pretty big formulas and calculating all possible satisfying combinations can take a very, very long time. 

## License

Copyright © 2013 Markus Bader

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
