(ns examples.logelei-2
  (:require [logic.util :refer :all]))

stop

; Logelei aus dem ZEIT Magazin Nr.39/2007 20.September 2007

; Fabian ist ein neuer Wildpfleger im Dschungel. 
; Er unterhält sich gerade mit seinem Mentor Hans-Friederich, 
; einem erfahrenen Wildhüter: "Woran erkennt man eigentlich
; die unterschiedlichen Wildtiere im Dschungel?"
; "Jedes macht ein ganz bestimmtes Geräusch. Das ist ganz einfach. Pass auf: 
; Falls Fiedertiger surren, miauen Beutelaffen. 
; Falls Fiedertiger miauen, zischen Tentakelläufer. 
; Falls Fiedertiger kratzen, oinken Beutelaffen. 
; Falls Fiedertiger zischen, kratzen Ohrhörner. 
; Falls Fiedertiger pfeifen, miauen Rüsselratten. 
; Falls Fiedertiger oinken, pfeifen Tentakelläufer."

; "Und was ist mit den anderen Tieren?", will Fabian wissen.
; "Da ist es so: 
; Falls Ohrhörner miauen, kratzen Tentakelläufer. 
; Falls Beutelaffen miauen, kratzen Fiedertiger. 
; Falls Rüsselratten surren, dann zischen Ohrhörner. 
; Falls Nagekühe miauen, pfeifen Ohrhörner. 
; Falls Beutelaffen oinken, kratzen Fiedertiger. 
; Falls Ohrhörner surren, kratzen Tentakelläufer. 
; Falls Beutelaffen surren, zischen Nagekühe. 
; Falls Nagekühe surren, kratzen Fiedertiger. 
; Falls Rüsselratten miauen, dann surren Ohrhörner. 
; Falls Beutelaffen oinken, surren Ohrhörner." 

; Welches Tier macht welches Geräusch?

(def tiere '(fiedertiger, beutelaffen, tentakelläufer, ohrhörner, rüsselratten, nagekühe))
(def geraeusche '(surren miauen kratzen zischen pfeifen oinken))

(defn ein-geraeusch
  [tier]
  (oneof (map #(combine-syms tier %) geraeusche)))

(defn tiere-ein-geraeusch []
  (apply list 'and (map ein-geraeusch tiere)))

(defn ein-tier
  [geraeusch]
  (oneof (map #(combine-syms % geraeusch) tiere)))

(ein-tier 'zischen)
                                 
(defn geraeusche-ein-tier []
  (apply list 'and (map ein-tier geraeusche)))

(def fml
  (list 'and
        (tiere-ein-geraeusch)
        (geraeusche-ein-tier)
        '(impl fiedertiger-surren  beutelaffen-miauen)
        '(impl fiedertiger-miauen  tentakelläufer-zischen)
        '(impl fiedertiger-kratzen beutelaffen-oinken)
        '(impl fiedertiger-zischen ohrhörner-kratzen)
        '(impl fiedertiger-pfeifen rüsselratten-miauen)
        '(impl fiedertiger-oinken  tentakelläufer-pfeifen)
        '(impl ohrhörner-miauen    tentakelläufer-kratzen)
        '(impl beutelaffen-miauen  fiedertiger-kratzen)
        '(impl rüsselratten-surren ohrhörner-zischen)
        '(impl nagekühe-miauen     ohrhörner-pfeifen)
        '(impl beutelaffen-oinken  fiedertiger-kratzen)
        '(impl ohrhörner-surren    tentakelläufer-kratzen)
        '(impl beutelaffen-surren  nagekühe-zischen)
        '(impl nagekühe-surren     fiedertiger-kratzen)
        '(impl rüsselratten-miauen ohrhörner-surren)
        '(impl beutelaffen-oinken  ohrhörner-surren) 
        ))



(def solution
  (let [tseit (transform-tseitin fml)
        result (-> (tseit :tseitin-formula) generate-dimacs-clauses sat-solve)]
    (retransform-tseitin result tseit)))

solution

(filter literal? solution)

; Ergebnis kontrollieren:

(def evals ['beutelaffen-kratzen false 
            'beutelaffen-pfeifen false 
            'beutelaffen-surren false 
            'beutelaffen-zischen true 
            'beutelaffen-miauen false 
            'beutelaffen-oinken false 
            'nagekühe-kratzen false 
            'nagekühe-miauen false 
            'nagekühe-oinken true 
            'nagekühe-pfeifen false 
            'nagekühe-surren false 
            'nagekühe-zischen false 
            'ohrhörner-kratzen false 
            'ohrhörner-miauen false 
            'ohrhörner-oinken false 
            'ohrhörner-pfeifen false 
            'ohrhörner-surren true 
            'ohrhörner-zischen false 
            'rüsselratten-kratzen false 
            'rüsselratten-miauen true 
            'rüsselratten-oinken false 
            'rüsselratten-pfeifen false 
            'rüsselratten-surren false 
            'rüsselratten-zischen false 
            'tentakelläufer-kratzen true 
            'tentakelläufer-miauen false 
            'tentakelläufer-oinken false 
            'tentakelläufer-pfeifen false 
            'tentakelläufer-surren false 
            'tentakelläufer-zischen false 
            'fiedertiger-pfeifen true 
            'fiedertiger-surren false 
            'fiedertiger-zischen false 
            'fiedertiger-kratzen false 
            'fiedertiger-miauen false 
            'fiedertiger-oinken false])
(eval-formula fml evals)
; true
