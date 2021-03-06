(ns ginny.core
  (:require [ginny.config :as config]
            [ginny.workers.changelog :as changelog]
            [clj.qiniu :as qiniu])
  (:gen-class))

(defn run-workers
  [workers]
  (mapv #(apply % nil) workers))

(defn -main
  "I don't do a whole lot."
  [& args]
  (let [{:keys [access-key secret-key]} config/qiniu
        workers [changelog/worker]]
    (qiniu/set-config! :access-key access-key
                       :secret-key secret-key)
    (run-workers workers)))
