(ns know-your-xwing.events
  (:require
   [ajax.core :as ajax]
   [day8.re-frame.http-fx]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   [know-your-xwing.db :as db]
   [re-frame.core :as rf]))

(rf/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(rf/reg-event-fx
  ::navigate
  (fn-traced [_ [_ handler]]
   {:navigate handler}))

(rf/reg-event-fx
 ::set-active-panel
 (fn-traced [{:keys [db]} [_ active-panel]]
   {:db (assoc db :active-panel active-panel)}))

;; set the game config in the app db and dispatch two co-effects,
;; one to change the active panel, and one to trigger the generate game request
(rf/reg-event-fx
 ::start-game
 (fn-traced [{:keys [db]} [_ game-config]]
            {:db (assoc db :game-config game-config)
             :fx [[:dispatch [::set-active-panel :game-panel]]
                  [:dispatch [::request-generate-game game-config]]]}))

;; send the request to the api to generate the game
(rf/reg-event-fx
 ::request-generate-game
 (fn [{:keys [db]} [_ game-config]]
   {:db (assoc-in db [:request :generate-game :status] :in-progress)
    :http-xhrio {:method          :post
                 :uri             "http://localhost:3000/api/generate-game"
                 :params          game-config
                 :format          (ajax/json-request-format)
                 :timeout         5000
                 :response-format (ajax/json-response-format {:keywords? true})
                 :on-success      [::generate-game-success]
                 :on-failure      [::generate-game-failure]}}))

(rf/reg-event-db
  ::generate-game-success
  (fn [db [_ response]]
    (-> db
        (assoc-in [:request :generate-game :status] :success)
        (assoc :game-cards response))))

(rf/reg-event-db
  ::generate-game-failure
  (fn [db [_ response]]
    (-> db
        (assoc-in [:request :generate-game :status] :failed)
        (assoc-in [:request :generate-game :response] response))))


