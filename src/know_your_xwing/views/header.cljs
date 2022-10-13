(ns know-your-xwing.views.header
  (:require
   [know-your-xwing.styles :as styles]
   [re-com.core :as rc :refer [at]]))

(defn title []
  [rc/title
   :src   (at)
   :label "Know Your X-Wing!"
   :level :level1
   :style {:margin-left "10px"}
   :class (styles/level1)])

(defn info-button []
  [rc/md-icon-button
   :src (at)
   :size :larger
   :class (styles/info-button)
   :md-icon-name "zmdi-info-outline"
   :tooltip "About this tool"
   :on-click #(js/alert "implement me!")])

(defn menu []
  [rc/h-box
   :src (at)
   :align :center
   :gap "8px"
   :margin "0px 20px"
   :children [(info-button)]])

;; header
(defn header []
  [rc/h-box
   :src (at)
   :justify :between
   :children [(title)
              (menu)]])

