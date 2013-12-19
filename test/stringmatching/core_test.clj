(ns stringmatching.core-test
  (:require [expectations :refer :all]
            [stringmatching.core :refer :all]) )

;; let's define the data set we will work with:

;; case (a): a simple sequence of strings
(let [input-sek '("live" "life" "love" "leaf" "thief" "something_completely_different")]
  ;; given 'live' is the root of our bk-tree, we'll compare it
  ;; to all other members of the data set
  (expect 1 (ld "live" "love"))
  (expect 1 (ld "live" "life"))
  (expect 3 (ld "live" "leaf"))
  (expect 4 (ld "live" "thief"))
  (expect 27 (ld "live" "something_completely_different"))
  ;; we see that 'live' has a distance of 1 to bot 'life' and 'love',
  ;; so only one of them is added as direct 1 relation, the other one
  ;; is compared to that one..
  (expect 2 (ld "life" "love"))
  ;; the corresponding bk-tree should look like:
  ;;                 "live"
  ;;            /1/   |3|    \4\        \27\
  ;;          "life" "leaf"  "thief"   "something_completely_different"
  ;;          /2/
  ;;        "love"
  ;;
  ;;
  ;;
  (expect ["live" {27 ["something_completely_different" {}], 4 ["thief" {}], 3 ["leaf" {}], 1 ["life" {2 ["love" {}]}]}] (bk-tree input-sek ld))
  (expect #{[0 "live"]} (set (query-tree "live" 0 (bk-tree input-sek ld) ld)))
  (expect #{[0 "live"] [1 "love"] [1 "life"]} (set (query-tree "live" 1 (bk-tree input-sek ld) ld)))
  (expect #{[0 "live"] [1 "love"] [1 "life"] [3 "leaf"]} (set (query-tree "live" 3 (bk-tree input-sek ld) ld)))
  (take 2 (query-tree "live" 3 (bk-tree input-sek ld) ld)))

;; case (b): a sequence of 2-tuples (`id-of-string-in-the-original-dataset` `string`)
