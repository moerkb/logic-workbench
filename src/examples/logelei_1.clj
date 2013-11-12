(ns examples.logelei-1
  (:require [logic.util :refer :all]))

stop

;; phi::Anne-Bettina-Claudia
;
; Einfache Logelei, aus der ZEIT:
;
; Anna sagt: "Bettina lügt".
; Bettina sagt: "Claudia lügt".
; Claudia sagt: "Anne und Bettina lügen".
; Wer lügt denn nun? 
;

; (a <-> !b) &
; (b <-> !c) &
; (c <-> !a&!b)

(def phi1 '(and
             (equiv a (not b))
             (equiv b (not c))
             (equiv c (and (not a) (not b)))))

(tt phi1)

;; phi::Kuckucksuhren
;
; Logelei aus dem ZEIT Magazin Nr.9/2008 21.Februar 2008

; Nach § 3 der Kuckucksuhrenverkaufsverordnung (KucWO) ist an jeder echten
; Kuckucksuhr eine wahre Aussage und an jeder unechten Kuckucksuhr eine
; falsche Aussage anzubringen. 

; Herr Huggelhuber hat seine Kuckucksuhren deshalb wie folgt beschriftet: 

; Kuckucksuhr Nr. l: 
;   »Wenn Kuckucksuhr Nr. 3 echt ist, dann ist auch Kuckucksuhr Nr. 2 echt.« 
; Kuckucksuhr Nr. 2: 
;   »Wenn Kuckucksuhr Nr. 4 unecht ist, dann ist auch Kuckucksuhr Nr. 7 unecht.«
; Kuckucksuhr Nr. 3: 
;   »Wenn Kuckucksuhr Nr. 2 unecht ist, dann ist Kuckucksuhr Nr. 7 echt.« 
; Kuckucksuhr Nr. 4: 
;   »Wenn Kuckucksuhr Nr. 6 unecht ist, dann ist Kuckucksuhr Nr. 7 echt.« 
; Kuckucksuhr Nr. 5: 
;   »Wenn Kuckucksuhr Nr. l echt ist, dann ist Kuckucksuhr Nr. 3 unecht.« 
; Kuckucksuhr Nr. 6: 
;   »Wenn Kuckucksuhr Nr. 7 unecht ist, dann ist Kuckucksuhr Nr. 2 echt.« 
; Kuckucksuhr Nr. 7: 
;   »Wenn Kuckucksuhr Nr. 3 unecht ist, dann ist Kuckucksuhr Nr. 5 echt.«

; Welche Kuckucksuhren sind echt?
; 

;[ k1 <-> (k3 -> k2)   ] &
;[ k2 <-> (!k4 -> !k7) ] &
;[ k3 <-> (!k2 -> k7)  ] &
;[ k4 <-> (!k6 -> k7)  ] &
;[ k5 <-> (k1 -> !k3)  ] &
;[ k6 <-> (!k7 -> k2)  ] &
;[ k7 <-> (!k3 -> k5)  ]
;

(def phi2
  '(and (equiv k1 (impl k3 k2))
        (equiv k2 (impl (not k4) (not k7)))
        (equiv k3 (impl (not k2) k7))
        (equiv k4 (impl (not k6) k7))
        (equiv k5 (impl k1 (not k3)))
        (equiv k6 (impl (not k7) k2))
        (equiv k7 (impl (not k3) k5))))

(tt phi2)

;phi::Lausbuben
;
; Logelei aus dem ZEIT Magazin Nr.48/2007 22.November 2007

; Eines Tages kommt die Katze des Dorflehrers mit einem rosa Hut auf dem 
; Kopf nach Hause.
; Der Lehrer empört: "Das waren bestimmt wieder einige der acht Lausbuben 
; in meiner Klasse!"
; Im Dorf läuft ihm Simon über den Weg, von dem er erfahrt: 
; "Wenn Detlev an der Aktion beteiligt war, dann auch Rolf!" 
; Als er gerade den Laden betreten will, sieht er Michel. 
; Der beteuert: "Wenn Friedl mitgemacht hat, dann hat aber Detlev 
; nichts mit der Sache zu tun." 
; Im Laden trifft er Detlev, welcher bekundet: "Das hat Simon angezettelt." 
; Auf dem Heimweg begegnet er schließlich Tobias und erfahrt von diesem: 
; "Klaus hat nichts mit der Sache zu tun!" 
; Am nächsten Morgen, bevor der Unterricht anfängt, begegnet der Lehrer 
; im Schulhof Friedl, der sagt: 
; "Wenn Tobias unschuldig ist, dann war Michel daran beteiligt!" 
; In der großen Pause nimmt er sich der Reihe nach Klaus und Rolf vor. 
; Klaus: "Wenn Rolf dabei war, dann auch Simon!" 
; Rolf: "Torsten war's!" 
; Fehlt nur noch Torsten, der muss ohnehin heute nachsitzen. 
; Eine gute Gelegenheit, um auch ihn zu befragen. 
; Ergebnis: "Soweit ich weiß, war Klaus einer von denen, die der Katze 
; den Hut aufgebunden haben." 

; Die Schuldigen haben natürlich gelogen, die anderen nicht. Wer war's?
;

;
; lausbub = Lausbub unschuldig,
; !lausbub = lausbub schuldig und Lügner
;

;[ simon <-> (!detlev -> !rolf) ] &
;[ michel <-> (!friedl -> detlev) ] &
;[ detlev <-> !simon ] &
;[ tobias <-> klaus ] &
;[ friedl <-> tobias -> !michel ] &
;[ klaus <-> !rolf -> !simon ] &
;[ rolf <-> !torsten ] &
;[ torsten <-> !klaus ]


(def phi3
  '(and
     (equiv simon  (impl (not detlev) (not rolf)))
     (equiv michel (impl (not friedl) detlev))
     (equiv detlev (not simon))
     (equiv tobias klaus)
     (equiv friedl (impl klaus (not michel)))
     (equiv klaus (impl (not rolf) (not simon)))
     (equiv rolf (not torsten))
     (equiv torsten (not klaus))))

(tt phi3)


; phi::Unsen
; /*
; Ein Zeit-Rätsel:

; Von den Unsen ist bekannt, dass sie entweder immer lügen oder 
; immer die Wahrheit sagen.
; Ansen, der Magier: "Wenn Honsen, der Medizinmann, lügt, dann sagt genau 
;   einer von Donsen, dem Wächter, und Insen, dem Späher, die Wahrheit."
; Bonsen, der Häuptling: "Wenn Consen, der Krieger, und Ensen, der Stallbursche,
;   lügen, dann sagt Konsen, der Älteste, die Wahrheit." 
; Consen, der Krieger: "Wenn Konsen, der Älteste, lügt, dann lügt auch Donsen,
;   der Wächter." 
; Donsen, der Wächter: "Wenn Insen, der Späher, die Wahrheit sagt, dann lügt 
;   entweder Gonsen oder Ensen." 
; Ensen, der Stallbursche: "Wenn Ansen, der Magier, lügt, dann sagt Insen die 
;   Wahrheit." 
; Fonsen, der Jäger: "Wenn Insen, der Späher, und Ansen, der Magier, beide die 
;   Wahrheit sagen, dann lügt Gonsen, der Gärtner." 
; Gonsen, der Gärtner: "Wenn Insen lügt, dann lügt auch Fonsen."
; Honsen, der Medizinmann: "Wenn Ensen die Wahrheit sagt, dann sagt auch 
;  Donsen,der Wächter, die Wahrheit." 
; Insen, der Späher: "Wenn Honsen, der Medizinmann, lügt, dann sagt Bonsen, 
;  der Häuptling, die Wahrheit." 
; Konsen, der Älteste: "Wenn Ensen, der Stallbursche, lügt, dann sagt von Insen, 
;   dem Späher, und Ansen, dem Magier, genau einer die Wahrheit."
; */

; ( a <-> (!h -> ((d&!i) | (!d&i))) ) &
; ( b <-> ((!c&!e)->k) ) &
; ( c <-> (!k -> !d) ) &
; ( d <-> (i -> ((!g&e)|(g&!e))) ) &
; ( e <-> (!a -> i) ) &
; ( f <-> ((i&a) -> !g) ) &
; ( g <-> (!i->!f) ) &
; ( h <-> (e->d) ) &
; ( i <-> (!h->b) ) &
; ( k <-> (!e -> ((i&!a) | (!i&a))) )


(def phi4 
  '(and
     (equiv a (impl (not h) (xor d i)))
     (equiv b (impl (and (not c) (not e)) k))
     (equiv c (impl (not k) (not d)))
     (equiv d (impl i (xor g e)))
     (equiv e (impl (not a) i))
     (equiv f (impl (and i a) (not g)))
     (equiv g (impl (not i) (not f)))
     (equiv h (impl e d))
     (equiv i (impl (not h) b))
     (equiv k (impl (not e) (xor i a)))))
     
 (tt phi4)           

; phi::Philosophen
; /*
;   Noch ein Zeit-Rätsel:

;   Von den turbetischen Philosophen ist bekannt, dass sie entweder immer 
;   lügen oder immer die Wahrheit sagen. Zudem nehmen sie an, dass bei einer
;   »Oder-Aussage« niemals beides der Fall sein kann. 
;   Phil: »Max oder Elke sagt die Wahrheit.« 
;   Tom: »Sven lügt, oder Elke sagt die Wahrheit.« 
;   Mani: »Phil lügt, oder Gert sagt die Wahrheit.« 
;   Chris: »Hol lügt, oder Lu sagt die Wahrheit.« 
;   Elke: »Mani lügt, oder Chris sagt die Wahrheit.« 
;   Bat: »Tanja lügt, oder Hol sagt die Wahrheit.« 
;   Sven: »Tanja oder Chris sagt die Wahrheit.« 
;   Gert: »Hol oder Elke sagt die Wahrheit.« 
;   Tanja: »Bat oder Phil lügt.« 
;   Hol: »Tom oder Chris lügt.« 
;   Max: »Elke oder Chris sagt die Wahrheit.«
;   Lu: »Hol lügt, oder Elke sagt die Wahrheit.« 
;  */

; ( phil <-> (max xor elke) ) &
; ( tom <-> (!sven xor elke) ) &
; ( mani <-> (!phil xor gert) ) &
; ( chris <-> (!hol xor lu) ) &
; ( elke <-> (!mani xor chris) ) &
; ( bat <-> (!tanja xor hol) ) &
; ( sven <-> (tanja xor chris) ) &
; ( gert <-> (hol xor elke) ) &
; ( tanja <-> (!bat xor !phil) ) &
; ( hol <-> (!tom xor !chris) ) &
; ( max <-> (elke xor chris) ) &
; ( lu <-> (!hol xor elke) )

(def phi5 
  '(and
     (equiv (phil (xor max elke)))
     (equiv (tom (xor (not sven) elke)))
     (equiv (mani (xor (not phil) gert)))
     (equiv (chris (xor (not hol) lu)))
     (equiv (elke (xor (not mani) chris)))
     (equiv (bat (xor (not tanja) hol)))
     (equiv (sven (xor tanja chris)))
     (equiv (gert (xor hol elke)))
     (equiv (tanja (xor (not bat) (not phil))))
     (equiv (hol (xor (not tom) (not chris))))
     (equiv (max (xor elke chris)))
     (equiv (lu (xor (not hol) elke)))))

(tt phi5)

; phi::Mule

; from Willis, Norman D. False logic puzzles. New York: Sterling, 1997. 
; found on Sperberg-McQueen's web site http://blackmesatech.com/2013/07/alloy/slides/exercises.xml
; Three farmers who have shared the use of a mule for some time disagree as to who owns the animal. 
; It is not certain, however, that the responsibility of ownership is desired. They have asked Socrates 
; to settle the issue. The three make the following statements. Each makes one true and one false statement. 

; A.
; It is C's mule.
; I can make no claim to it.
; B.
; C has no right to it.
; It is A's mule.
; C.
; It is my mule.
; B's second statement is false.

; Socrates hesitates for scarcely an instant and determines the owner. To which farmer does the mule belong?

; x = x owns the mule

(def phi6
  '(and
     (or a b c)
     (or (not a) (not b))
     (or (not a) (not c))
     (or (not b) (not c)) ; exactly one of #{a b c}
     #_(xor c (not a))      ; a and c make the same proposition
     (xor (not c) a)
     (xor c (not a))))

(tt phi6)

; result:
; B owns the mule

; phi:: Crumm and Moriarty

; from Jeffrey, Richard C. Formal logic: its scope and limits. New York: McGraw-Hill, 1967
; found on Sperberg-McQueen's web site http://blackmesatech.com/2013/07/alloy/slides/exercises.xml

; Assume these to be true:
; - Crumm is not guilty.
; - Moriarty is guilty if Crumm is.
; Does this follow?
; -Moriarty is not guilty.

; c = Crumm guilty
; m = Moriarty guilty

(def phi7
  '(impl
     (and (not c) (if m c))
     (not m)))

(tt phi7)

; result: yes

; phi::Moriarty's escape

; from Jeffrey, Richard C. Formal logic: its scope and limits. New York: McGraw-Hill, 1967
; found on Sperberg-McQueen's web site http://blackmesatech.com/2013/07/alloy/slides/exercises.xml

; Assume these to be true:
; - If Moriarty has escaped, then either Holmes has bungled or Watson is on the job.
; - Holmes has not bungled unless Moriarty has escaped.
; - Watson is not on the job.
; Does this follow?
; - Moriarty has escaped if and only if Holmes has bungled.

; m = Moriarty has escaped
; h = Holmes has bungled
; w = Watson is on the job

(def phi8
  '(impl
     (and
       (impl m (xor h w))
       (or (not h) m)
       (not w))
     (equiv m h)))

(tt phi8)

; result: yes