(ns know-your-xwing.views.start
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   [re-com.core :as rc :refer [at]]
   [know-your-xwing.events :as events]
   [know-your-xwing.styles :as styles]))

(def card-type-choices
  [{:id :upgrades :label "Upgrades"}
   {:id :pilots :label "Pilots"}])
(def faction-choices
  [{:id :galactic-empire :label "Galactic Empire"}
   {:id :separatist-alliance :label "Separatist Alliance"}
   {:id :scum-and-villainy :label "Scum and Villainy"}
   {:id :first-order :label "First Order"}
   {:id :rebel-alliance :label "Rebel Alliance"}
   {:id :resistance :label "Resistance"}
   {:id :galactic-republic :label "Galactic Republic"}])
(def number-of-card-choices
  [{:id 5 :label "5"}
   {:id 10 :label "10"}
   {:id 20 :label "20"}
   {:id 30 :label "30"}
   {:id 50 :label "50"}])

(def selected-card-types (r/atom (into #{} (map :id card-type-choices))))
(def selected-factions (r/atom (into #{} (map :id faction-choices))))
(def number-of-cards (r/atom 10))

(defn number-of-cards-dropdown []
  (rc/v-box
   :src (at)
   :gap "10px"
   :children [(rc/title
               :src (at)
               :level :level3
               :class (styles/level3)
               :underline? true
               :label "Number of Cards")
              [rc/single-dropdown
               :src (at)
               :width "60px"
               :choices number-of-card-choices
               :model number-of-cards
               :on-change #(reset! number-of-cards %)]]))

(defn card-type-select []
  (rc/v-box
   :src (at)
   :gap "10px"
   :children [(rc/title
               :src (at)
               :level :level3
               :class (styles/level3)
               :underline? true
               :label "Card Types")
              (rc/selection-list
               :src (at)
               :class (styles/selection-list)
               :choices card-type-choices
               :model selected-card-types 
               :on-change #(reset! selected-card-types %))]))

(defn faction-select []
  (rc/v-box
   :src (at)
   :gap "10px"
   :children [(rc/title
               :src (at)
               :level :level3
               :class (styles/level3)
               :underline? true
               :label "Factions")
              (rc/selection-list
               :src (at)
               :class (styles/selection-list)
               :choices faction-choices
               :model selected-factions
               :on-change #(reset! selected-factions %))]))

(defn configuration-panel []
  [rc/h-box
   :src (at)
   :gap "10px"
   :children [(faction-select)
              (card-type-select)
              (number-of-cards-dropdown)]])

(defn start-button []
  [rc/button
   :src (at)
   :label "START"
   :class "btn btn-default btn-lg"
   :on-click #(rf/dispatch [::events/start-game
                            {:factions @selected-factions
                             :card-types @selected-card-types
                             :number-of-cards @number-of-cards}])])

(defn start-panel []
  [rc/v-box
   :src (at)
   :height "400px"
   :align :center
   :justify :center
   :gap "10px"
   :children [(configuration-panel)
              (start-button)]])
