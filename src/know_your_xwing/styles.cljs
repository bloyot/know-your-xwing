(ns know-your-xwing.styles
  (:require
    [spade.core   :refer [defglobal defclass]]
    [garden.units :refer [deg px]]))

(defglobal defaults
  [:body
   {:color               :#dddddd
    :background-color    :#121213}])

(defclass level1
  []
  {:color :#dddddd})
