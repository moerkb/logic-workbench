(ns logic.basicfunctions-test
  (:require [clojure.test :refer :all]
            [logic.util :refer :all]))

(deftest nand-test
    (is (= false (nand true true)))
    (is (= true (nand true false)))
    (is (= true (nand false true)))
    (is (= true (nand false false)))
    
    (is (= false (nand true true true)))
    (is (= true (nand true true false)))
    (is (= true (nand true false true)))
    (is (= true (nand true false false)))
    (is (= true (nand false true true)))
    (is (= true (nand false true false)))
    (is (= true (nand false false true)))
    (is (= true (nand false false false))))

(deftest nor-test
    (is (= false (nor true true)))
    (is (= false (nor true false)))
    (is (= false (nor false true)))
    (is (= true (nor false false)))
    
    (is (= false (nor true true true)))
    (is (= false (nor true true false)))
    (is (= false (nor true false true)))
    (is (= false (nor true false false)))
    (is (= false (nor false true true)))
    (is (= false (nor false true false)))
    (is (= false (nor false false true)))
    (is (= true (nor false false false))))

(deftest impl-test
    (is (= true (impl true true)))
    (is (= false (impl true false)))
    (is (= true (impl false true)))
    (is (= true (impl false false))))

(deftest nimpl-test
    (is (= false (nimpl true true)))
    (is (= true (nimpl true false)))
    (is (= false (nimpl false true)))
    (is (= false (nimpl false false))))

(deftest equiv-test
    (is (= true (equiv true true)))
    (is (= false (equiv true false)))
    (is (= false (equiv false true)))
    (is (= true (equiv false false)))
    
    (is (= true (equiv true true true)))
    (is (= false (equiv true true false)))
    (is (= false (equiv true false true)))
    (is (= false (equiv true false false)))
    (is (= false (equiv false true true)))
    (is (= false (equiv false true false)))
    (is (= false (equiv false false true)))
    (is (= true (equiv false false false))))

(deftest cimpl-test
    (is (= true (cimpl true true)))
    (is (= true (cimpl true false)))
    (is (= false (cimpl false true)))
    (is (= true (cimpl false false))))

(deftest ncimpl-test
    (is (= false (ncimpl true true)))
    (is (= false (ncimpl true false)))
    (is (= true (ncimpl false true)))
    (is (= false (ncimpl false false))))

(deftest xor-test
    (is (= false (xor true true)))
    (is (= true (xor true false)))
    (is (= true (xor false true)))
    (is (= false (xor false false))))