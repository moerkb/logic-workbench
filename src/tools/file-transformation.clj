(ns tools.main)

(defn replace-several [content & replacements]
  (let [replacement-list (partition 2 replacements)]
    (reduce #(apply str/replace %1 %2) content replacement-list)))

(defn pp-str [& things]
  (let [s (with-out-str (pretty/pprint things))]
    (println s)
    (-> s
        (.substring 0 (-> s .length dec dec))
        (.replace "(" " ")
        (str "\n"))))

(defn lwf2mpf
  "Takes a lwf file and produces the mpf file."
  [text]
  (let [file-vector (read-string text)
        description (first file-vector)
        forms (rest file-vector)]
    (str
      "/*" \newline
      (apply str (map #(str " * " % \newline) 
                   (str/split-lines description)))
      " */" \newline
      \newline
      (apply str (map (fn [m] (str "phi::" (:name m) \newline
                                (:proposition m)
                                \newline
                                \newline))
                   forms))
      )))

(defn mpf2lwf
  "Takes a mpf file and produces the lwf file."
  [text]
  (let [all-lines (str/split-lines text)
        no-phi-line? #(or 
                       (< (count %) 5)
                       (not= "phi::" (subs % 0 5))) 
        next-formula (fn [lines] (str/trim (apply str (map #(str % \newline)
                                                        (take-while no-phi-line? lines)))))
        top-comment [(str/trim (str/replace (next-formula all-lines)
                                 #"//|\*/+|/\*+|\*+" ""))]]
    
    ; print result
    (str/replace
        ; recursion: put top comment, then one loop for each preposition
        (loop [file-map top-comment
               lines (drop-while no-phi-line? all-lines)]
          (if (empty? lines)
              file-map
              (let [new-name (subs (first lines) 5 (count (first lines)))
                    new-formula (next-formula (rest lines))]
                (recur (conj file-map {:name new-name :proposition new-formula})
                  (drop-while no-phi-line? (rest lines))))))
        #"\\n" "\n")))