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
  (let [form-map (read-string text)
        form-names (keys (dissoc form-map :__description))]
    (str
      "/*" \newline
      (apply str (map #(str " * " % \newline) 
                   (str/split-lines (:__description form-map))))
      " */" \newline
      \newline
      (apply str (map #(str "phi:" % \newline
                         (% form-map)
                         \newline
                         \newline)
                   form-names))
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
        top-comment (str/trim (str/replace (next-formula all-lines)
                                #"//|\*/+|/\*+|\*+" ""))]
    
    ; pretty print result
    (str/replace
        ; recursion: put top comment, then one loop for each preposition
        (loop [file-map {:__description top-comment}
               lines (drop-while no-phi-line? all-lines)]
          (if (empty? lines)
              file-map
              (let [new-name (keyword (str/replace (subs (first lines) 5 (count (first lines)))
                                        #" " "_"))
                    new-formula (next-formula (rest lines))]
                (recur (assoc file-map new-name new-formula)
                  (drop-while no-phi-line? (rest lines))))))
        #"\\n" "\n")))