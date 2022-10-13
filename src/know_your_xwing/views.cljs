(ns know-your-xwing.views
  (:require
   [re-frame.core :as re-frame]
   [re-com.core :as re-com :refer [at]]
   [know-your-xwing.views.header :as header]
   [know-your-xwing.routes :as routes]
   [know-your-xwing.views.start :as start]
   [know-your-xwing.views.game :as game]
   [know-your-xwing.subs :as subs]))

(defmethod routes/panels :start-panel [] [start/start-panel])
(defmethod routes/panels :game-panel [] [game/game-panel])

;; main panel
(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [re-com/v-box
     :src      (at)
     :children [(header/header)
                [re-com/line]
                (routes/panels @active-panel)]]))
