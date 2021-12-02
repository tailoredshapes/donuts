(ns model.schema
  (:require [malli.core :as m]))

(def Link
  [:map
   [:index :string]
   [:name :string]
   [:url :string]])

(def AbilityBonus
  [:map
   [:ability_score Link]
   [:bonus :int]])

(def Options
  [:map
   [:choose :int]
   [:type :string]
   [:from [:sequential Link]]])

(def Role
  [:map
   [:index :string]
   [:name :string]
   [:speed :int]
   [:ability_bonuses [:sequential AbilityBonus]]
   [:alignment :string]
   [:age :string]
   [:size :string]
   [:size_description :string]
   [:starting_proficiencies [:sequential Link]]
   [:starting_proficiency_options {:optional true} Options]
   [:languages [:sequential Link]]
   [:traits [:sequential Link]]
   [:subraces [:sequential Link]]
   [:url :string]])

(def Language
  [:map
   [:index :string]
   [:name :string]
   [:desc {:optional true} :string]
   [:type :string]
   [:typical_speakers [:sequential :string]]
   [:url :string]])

(def Trait
  [:map
   [:index :string]
   [:races [:sequential Link]]
   [:subraces [:sequential Link]]
   [:name :string]
   [:desc [:sequential :string]]
   [:proficiencies [:sequential Link]]
   [:url :string]])


