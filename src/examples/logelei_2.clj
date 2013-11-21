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

(defn ein-geraeusch
  [tier]
  (oneof (map #(combine-syms tier %)
              '(surren miauen kratzen zischen pfeifen oinken))))

(defn tiere-ein-geraeusch []
  (list 'and (map ein-geraeusch 
                  '(fiedertiger, beutelaffen, tentakelläufer, 
                    ohrhörner, rüsselratten, naagekühe))))
                                 
(def fml
  (list 'and
        (tiere-ein-geraeusch)
        (impl 'fiedertiger-surren  'beutelaffen-miauen)
        (impl 'fiedertiger-miauen  'tentakelläufer-zischen)
        (impl 'fiedertiger-kratzen 'beutelaffen-oinken)
        (impl 'fiedertiger-zischen 'ohrhörner-kratzen)
        (impl 'fiedertiger-pfeifen 'rüsselratten-miauen)
        (impl 'fiedertiger-oinken  'tentakelläufer-pfeifen)
        (impl 'ohrhörner-miauen    'tentakelläufer-kratzen)
        (impl 'beutelaffen-miauen  'fiedertiger-kratzen)
        (impl 'rüsselratten-surren 'ohrhörner-zischen)
        (impl 'nagekühe-miauen     'ohrhörner-pfeifen)
        (impl 'beutelaffen-oinken  'fiedertiger-kratzen)
        (impl 'ohrhörner-surren    'tentakelläufer-kratzen)
        (impl 'beutelaffen-surren  'nagekühe-zischen)
        (impl 'nagekühe-surren     'fiedertiger-kratzen)
        (impl 'rüsselratten-miauen 'ohrhörner-surren)
        (impl 'beutelaffen-oinken  'ohrhörner-surren) 
        ))

        