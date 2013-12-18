(ns stringmatching.core
  (:import [org.apache.commons.lang3 StringUtils])
  (:use [clojure.tools.trace :only [deftrace]]))

(defn ld
  "just a wrapper around StringUtils/LevenshteinDistance to get a nicer name"
  [s1 s2]
  (StringUtils/getLevenshteinDistance s1 s2))

(defn bk-tree [coll distfn]
  """
  A clojure implementation of a BK-tree (http://en.wikipedia.org/wiki/BK-tree)
  Returns a queriable Burkhard-Keller tree of a format similar to:
  [word-a {:distance1 [word-b {}]
           :distance2 [word-c {:distance1 [word-d]}]}]
  In this example word-c and word-d would have the same distance to word-a.

  `coll`: a collection of words
  `distfn`: a function [word-a word-b] determining similarity between two words;
          this has to be described as a distance in a metric space
  """
  (reduce
    (fn add [node word]
      (let [[ref-word related] node]
          (let [d (distfn word ref-word)]
            (if (contains? related d)
              [ref-word (assoc related d (add (related d) word))]
              [ref-word (assoc related d (vector word {}))]))))
    [(first coll) {}] (rest coll)))
