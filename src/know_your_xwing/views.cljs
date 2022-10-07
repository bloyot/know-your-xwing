(ns know-your-xwing.views
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com :refer [at]]
   [know-your-xwing.styles :as styles]
   [know-your-xwing.routes :as routes]
   [know-your-xwing.subs :as subs]))

(defn home-panel []
  [re-com/p "PLACEHOLDER"])


(defmethod routes/panels :home-panel [] [home-panel])

(defn title []
  [re-com/title
               :src   (at)
               :label (str "Know Your X-Wing!")
               :level :level1
               :class (styles/level1)])

(defn info-button []
  [re-com/md-icon-button
   :src (at)
   :size :larger
   :md-icon-name "zmdi-info"
   :tooltip "About this tool"
   :on-click #((js/alert "implement me!"))])

(defn settings-button []
  [re-com/md-icon-button
   :src (at)
   :size :larger
   :md-icon-name "zmdi-settings"
   :tooltip "Change the types of cards shown"
   :on-click #((js/alert "implement me!"))])

(defn menu []
  [re-com/h-box
   :src (at)
   :align :center
   :gap "8px"
   :margin "0px 20px"
   :children [(info-button)
              (settings-button)]])

;; header
(defn header []
  [re-com/h-box
   :src (at)
   :justify :between
   :children [
              [re-com/box :src (at) :child ""] ;; for spacing
              (title)
              (menu)]])

;; main

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [re-com/v-box
     :src      (at)
     :height   "100%"
     :children [(header)
                [re-com/line]
                (routes/panels @active-panel)]]))
