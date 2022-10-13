(ns know-your-xwing.styles
  (:require
    [spade.core   :refer [defglobal defclass]]
    [garden.units :refer [deg px]]))

(defglobal defaults
  [:body
   {:color               :white
    :background-image    "linear-gradient(60deg, #29323c 0%, #485563 100%);"}
   :rc-selection-list-group {:color :black}])

(defclass level1
  []
  {:color :white})

(defclass level3
  []
  {:color :white})


(defclass selection-list
  []
  {:background-color :white
   :padding "4px"})

(defclass info-button []
  {:color :white})
