(ns examples.four-queens
  (:require [logic.util :refer :all]))

(def rules
"(d1a -> !(d1b | d1c | d1d |
		  d2a | d3a | d4a |
		  d2b | d3c | d4d))
&
(d2a -> !(d2b | d2c | d2d |
		  d1a | d3a | d4a |
		  d1b | d3b | d4c))
&
(d3a -> !(d3b | d3c | d3d |
		  d1a | d2a | d4a |
		  d1c | d2b | d4b))
&
(d4a -> !(d4b | d4c | d4d |
		  d1a | d2a | d3a |
		  d1d | d2c | d3b))
&
(d1b -> !(d1a | d1c | d1d |
		  d2b | d3b | d4b |
		  d2a | d2c | d3d))
&
(d2b -> !(d2a | d2c | d2d |
		  d1b | d3b | d4b |
		  d1a | d1c | d3a | d3c | d4d))
&
(d3b -> !(d3a | d3c | d3d |
		  d1b | d2b | d4b |
		  d1d | d2a | d2c | d4a | d4c))
&
(d4b -> !(d4a | d4c | d4d |
		  d1b | d2b | d3b |
		  d3a | d3c | d2d))
&
(d1c -> !(d1a | d1b | d1d |
		  d2c | d3c | d4c |
		  d2b | d2d | d3a))
&
(d2c -> !(d2a | d2b | d2d |
		  d1c | d3c | d4c |
		  d1b | d1d | d3b | d3d | d4a))
&
(d3c -> !(d3a | d3b | d3d |
		  d1c | d2c | d4c |
		  d1a | d2b | d2d | d4b | d4d))
&
(d4c -> !(d4a | d4b | d4d |
		  d1c | d2c | d3c |
		  d2a | d3b | d3d))
&
(d1d -> !(d1a | d1b | d1c |
		  d2d | d3d | d4d |
		  d2c | d3b | d4a))
&
(d2d -> !(d2a | d2b | d2c |
		  d1d | d3d | d4d |
		  d1c | d3c | d4b))
&
(d3d -> !(d3a | d3b | d3c |
		  d1d | d2d | d4d |
		  d1b | d2c | d4c))
&
(d4d -> !(d4a | d4b | d4c |
		  d1d | d2d | d3d |
		  d1a | d2b | d3c))
&
(d1a | d1b | d1c | d1d)
&
(d2a | d2b | d2c | d2d)
&
(d3a | d3b | d3c | d3d)
&
(d4a | d4b | d4c | d4d)
&
(d1a | d2a | d3a | d4a)
&
(d1b | d2b | d3b | d4b)
&
(d1c | d2c | d3c | d4c)
&
(d1d | d2d | d3d | d4d)")

stop

(time (def parsed (javaCCparse rules)))
; ~ 2 - 10 msecs

(time (def parsed (clojure-formula rules)))
; ~ 100 msecs

(do (time (transform-tseitin parsed)) nil)

(do (time (transform-cnf parsed)) nil)

; performance test
(def extreme (reduce #(str %1 "&" %2) (repeat 30 rules)))
(time (def extreme-parsed (javaCCparse extreme)))

(do (time (transform-tseitin extreme-parsed)) nil)
(do (time (transform-cnf extreme-parsed)) nil)
