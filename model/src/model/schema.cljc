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


(def Feats [:map
            [:index string?]
            [:name string?]
            [:prerequisites
             [:vector
              [:map [:ability_score [:map [:index string?] [:name string?] [:url string?]]] [:minimum_score int?]]]]
            [:desc [:vector string?]]
            [:url string?]])

(def Races [:map
            [:starting_proficiency_options
             {:optional true}
             [:map
              [:choose int?]
              [:type string?]
              [:from [:vector [:map [:index string?] [:name string?] [:url string?]]]]]]
            [:age string?]
            [:index string?]
            [:speed int?]
            [:starting_proficiencies [:vector [:map [:index string?] [:name string?] [:url string?]]]]
            [:name string?]
            [:alignment string?]
            [:language_options
             {:optional true}
             [:map
              [:choose int?]
              [:type string?]
              [:from [:vector [:map [:index string?] [:name string?] [:url string?]]]]]]
            [:size string?]
            [:size_description string?]
            [:ability_bonus_options
             {:optional true}
             [:map
              [:choose int?]
              [:type string?]
              [:from
               [:vector [:map [:ability_score [:map [:index string?] [:name string?] [:url string?]]] [:bonus int?]]]]]]
            [:url string?]
            [:languages [:vector [:map [:index string?] [:name string?] [:url string?]]]]
            [:subraces [:vector [:map [:index string?] [:name string?] [:url string?]]]]
            [:language_desc string?]
            [:ability_bonuses
             [:vector [:map [:ability_score [:map [:index string?] [:name string?] [:url string?]]] [:bonus int?]]]]
            [:traits [:vector [:map [:index string?] [:name string?] [:url string?]]]]]),

(def Equipment [:map
               [:properties {:optional true} [:vector [:map [:index string?] [:name string?] [:url string?]]]]
               [:weapon_category {:optional true} string?]
               [:special {:optional true} [:vector string?]]
               [:desc {:optional true} [:vector string?]]
               [:index string?]
               [:capacity {:optional true} string?]
               [:speed {:optional true} [:map [:quantity number?] [:unit string?]]]
               [:equipment_category [:map [:index string?] [:name string?] [:url string?]]]
               [:two_handed_damage
                {:optional true}
                [:map [:damage_dice string?] [:damage_type [:map [:index string?] [:name string?] [:url string?]]]]]
               [:gear_category {:optional true} [:map [:index string?] [:name string?] [:url string?]]]
               [:name string?]
               [:vehicle_category {:optional true} string?]
               [:weapon_range {:optional true} string?]
               [:stealth_disadvantage {:optional true} boolean?]
               [:armor_class {:optional true} [:map [:base int?] [:dex_bonus boolean?] [:max_bonus :any]]]
               [:armor_category {:optional true} string?]
               [:damage
                {:optional true}
                [:map [:damage_dice string?] [:damage_type [:map [:index string?] [:name string?] [:url string?]]]]]
               [:weight {:optional true} number?]
               [:url string?]
               [:tool_category {:optional true} string?]
               [:str_minimum {:optional true} int?]
               [:cost [:map [:quantity int?] [:unit string?]]]
               [:contents
                {:optional true}
                [:vector [:map [:item [:map [:index string?] [:name string?] [:url string?]]] [:quantity int?]]]]
               [:quantity {:optional true} int?]
               [:category_range {:optional true} string?]
               [:range {:optional true} [:map [:normal int?] [:long :any]]]
               [:throw_range {:optional true} [:map [:normal int?] [:long int?]]]])

(def Subclasses [:map
                [:index string?]
                [:class [:map [:index string?] [:name string?] [:url string?]]]
                [:name string?]
                [:subclass_flavor string?]
                [:desc [:vector string?]]
                [:subclass_levels string?]
                [:url string?]
                [:spells
                 {:optional true}
                 [:vector
                  [:map
                   [:prerequisites [:vector [:map [:index string?] [:type string?] [:name string?] [:url string?]]]]
                   [:spell [:map [:index string?] [:name string?] [:url string?]]]]]]])

(def Monsters [:map
              [:senses
               [:map
                [:darkvision {:optional true} string?]
                [:passive_perception int?]
                [:blindsight {:optional true} string?]
                [:truesight {:optional true} string?]
                [:tremorsense {:optional true} string?]]]
              [:index string?]
              [:wisdom int?]
              [:speed
               [:map
                [:walk {:optional true} string?]
                [:swim {:optional true} string?]
                [:fly {:optional true} string?]
                [:burrow {:optional true} string?]
                [:climb {:optional true} string?]
                [:hover {:optional true} boolean?]]]
              [:charisma int?]
              [:name string?]
              [:alignment string?]
              [:intelligence int?]
              [:type string?]
              [:armor_class int?]
              [:damage_resistances [:vector string?]]
              [:actions
               {:optional true}
               [:vector
                [:map
                 [:desc string?]
                 [:dc
                  {:optional true}
                  [:map
                   [:dc_type [:map [:index string?] [:name string?] [:url string?]]]
                   [:dc_value int?]
                   [:success_type string?]]]
                 [:name string?]
                 [:attack_options
                  {:optional true}
                  [:map
                   [:choose int?]
                   [:type string?]
                   [:from
                    [:vector
                     [:map
                      [:name string?]
                      [:dc
                       [:map
                        [:dc_type [:map [:index string?] [:name string?] [:url string?]]]
                        [:dc_value int?]
                        [:success_type string?]]]
                      [:damage
                       {:optional true}
                       [:vector
                        [:map
                         [:damage_type [:map [:index string?] [:name string?] [:url string?]]]
                         [:damage_dice string?]]]]]]]]]
                 [:usage
                  {:optional true}
                  [:map
                   [:type string?]
                   [:times {:optional true} int?]
                   [:dice {:optional true} string?]
                   [:min_value {:optional true} int?]
                   [:rest_types {:optional true} [:vector string?]]]]
                 [:damage
                  {:optional true}
                  [:vector
                   [:map
                    [:damage_type {:optional true} [:map [:index string?] [:name string?] [:url string?]]]
                    [:damage_dice {:optional true} string?]
                    [:dc
                     {:optional true}
                     [:map
                      [:dc_type [:map [:index string?] [:name string?] [:url string?]]]
                      [:dc_value int?]
                      [:success_type string?]]]
                    [:choose {:optional true} int?]
                    [:type {:optional true} string?]
                    [:from
                     {:optional true}
                     [:vector
                      [:map [:damage_type [:map [:index string?] [:name string?] [:url string?]]] [:damage_dice string?]]]]]]]
                 [:options
                  {:optional true}
                  [:map
                   [:choose int?]
                   [:from
                    [:vector
                     [:vector [:map [:name string?] [:count :any] [:type string?] [:notes {:optional true} string?]]]]]]]
                 [:attacks
                  {:optional true}
                  [:vector
                   [:map
                    [:name string?]
                    [:dc
                     [:map
                      [:dc_type [:map [:index string?] [:name string?] [:url string?]]]
                      [:dc_value int?]
                      [:success_type string?]]]
                    [:damage
                     {:optional true}
                     [:vector
                      [:map [:damage_type [:map [:index string?] [:name string?] [:url string?]]] [:damage_dice string?]]]]]]]
                 [:attack_bonus {:optional true} int?]]]]
              [:special_abilities
               {:optional true}
               [:vector
                [:map
                 [:name string?]
                 [:desc string?]
                 [:dc
                  {:optional true}
                  [:map
                   [:dc_type [:map [:index string?] [:name string?] [:url string?]]]
                   [:dc_value int?]
                   [:success_type string?]]]
                 [:spellcasting
                  {:optional true}
                  [:map
                   [:level {:optional true} int?]
                   [:ability [:map [:index string?] [:name string?] [:url string?]]]
                   [:dc {:optional true} int?]
                   [:modifier {:optional true} int?]
                   [:components_required [:vector string?]]
                   [:school {:optional true} string?]
                   [:slots
                    {:optional true}
                    [:map
                     [:4 {:optional true} int?]
                     [:7 {:optional true} int?]
                     [:1 int?]
                     [:8 {:optional true} int?]
                     [:9 {:optional true} int?]
                     [:2 {:optional true} int?]
                     [:5 {:optional true} int?]
                     [:3 {:optional true} int?]
                     [:6 {:optional true} int?]]]
                   [:spells
                    [:vector
                     [:map
                      [:name string?]
                      [:level int?]
                      [:url string?]
                      [:usage {:optional true} [:map [:type string?] [:times {:optional true} int?]]]
                      [:notes {:optional true} string?]]]]]]
                 [:usage
                  {:optional true}
                  [:map [:type string?] [:times {:optional true} int?] [:rest_types {:optional true} [:vector string?]]]]
                 [:damage
                  {:optional true}
                  [:vector
                   [:map [:damage_type [:map [:index string?] [:name string?] [:url string?]]] [:damage_dice string?]]]]
                 [:attack_bonus {:optional true} int?]]]]
              [:size string?]
              [:damage_vulnerabilities [:vector string?]]
              [:hit_dice string?]
              [:proficiencies
               [:vector [:map [:value int?] [:proficiency [:map [:index string?] [:name string?] [:url string?]]]]]]
              [:challenge_rating number?]
              [:damage_immunities [:vector string?]]
              [:strength int?]
              [:dexterity int?]
              [:url string?]
              [:legendary_actions
               {:optional true}
               [:vector
                [:map
                 [:name string?]
                 [:desc string?]
                 [:attack_bonus {:optional true} int?]
                 [:damage
                  {:optional true}
                  [:vector
                   [:map [:damage_type [:map [:index string?] [:name string?] [:url string?]]] [:damage_dice string?]]]]
                 [:dc
                  {:optional true}
                  [:map
                   [:dc_type [:map [:index string?] [:name string?] [:url string?]]]
                   [:dc_value int?]
                   [:success_type string?]]]]]]
              [:xp int?]
              [:languages string?]
              [:hit_points int?]
              [:constitution int?]
              [:reactions
               {:optional true}
               [:vector
                [:map
                 [:name string?]
                 [:desc string?]
                 [:dc
                  {:optional true}
                  [:map
                   [:dc_type [:map [:index string?] [:name string?] [:url string?]]]
                   [:dc_value int?]
                   [:success_type string?]]]]]]
              [:forms {:optional true} [:vector [:map [:index string?] [:name string?] [:url string?]]]]
              [:condition_immunities [:vector [:map [:index string?] [:name string?] [:url string?]]]]
              [:subtype seqable?]])

(def Skills [:map
            [:index string?]
            [:name string?]
            [:desc [:vector string?]]
            [:ability_score [:map [:index string?] [:name string?] [:url string?]]]
            [:url string?]])

(def Languages [:map
               [:index string?]
               [:name string?]
               [:type string?]
               [:typical_speakers [:vector string?]]
               [:script seqable?]
               [:url string?]
               [:desc {:optional true} string?]])


(def Subraces [:map
              [:desc string?]
              [:index string?]
              [:starting_proficiencies [:vector [:map [:index string?] [:name string?] [:url string?]]]]
              [:race [:map [:index string?] [:name string?] [:url string?]]]
              [:name string?]
              [:language_options
               {:optional true}
               [:map
                [:choose int?]
                [:from [:vector [:map [:index string?] [:name string?] [:url string?]]]]
                [:type string?]]]
              [:url string?]
              [:languages [:vector any?]]
              [:racial_traits [:vector [:map [:index string?] [:name string?] [:url string?]]]]
              [:ability_bonuses
               [:vector [:map [:ability_score [:map [:index string?] [:name string?] [:url string?]]] [:bonus int?]]]]])

(def Classes [:map
             [:starting_equipment_options
              [:vector
               [:map
                [:choose int?]
                [:type string?]
                [:from
                 [:or
                  [:vector
                   [:or
                    [:map
                     [:equipment {:optional true} [:map [:index string?] [:name string?] [:url string?]]]
                     [:quantity {:optional true} int?]
                     [:equipment_option
                      {:optional true}
                      [:map
                       [:choose int?]
                       [:type string?]
                       [:from [:map [:equipment_category [:map [:index string?] [:name string?] [:url string?]]]]]]]
                     [:prerequisites
                      {:optional true}
                      [:vector
                       [:map [:type string?] [:proficiency [:map [:index string?] [:name string?] [:url string?]]]]]]]
                    [:vector
                     [:map
                      [:equipment {:optional true} [:map [:index string?] [:name string?] [:url string?]]]
                      [:quantity {:optional true} int?]
                      [:equipment_option
                       {:optional true}
                       [:map
                        [:choose int?]
                        [:type string?]
                        [:from [:map [:equipment_category [:map [:index string?] [:name string?] [:url string?]]]]]]]]]]]
                  [:map [:equipment_category [:map [:index string?] [:name string?] [:url string?]]]]]]]]]
             [:saving_throws [:vector [:map [:index string?] [:name string?] [:url string?]]]]
             [:class_levels string?]
             [:index string?]
             [:starting_equipment
              [:vector [:map [:equipment [:map [:index string?] [:name string?] [:url string?]]] [:quantity int?]]]]
             [:name string?]
             [:hit_die int?]
             [:spells {:optional true} string?]
             [:proficiencies [:vector [:map [:index string?] [:name string?] [:url string?]]]]
             [:proficiency_choices
              [:vector
               [:map
                [:choose int?]
                [:type string?]
                [:from [:vector [:map [:index string?] [:name string?] [:url string?]]]]]]]
             [:spellcasting
              {:optional true}
              [:map
               [:level int?]
               [:spellcasting_ability [:map [:index string?] [:name string?] [:url string?]]]
               [:info [:vector [:map [:name string?] [:desc [:vector string?]]]]]]]
             [:url string?]
             [:subclasses [:vector [:map [:index string?] [:name string?] [:url string?]]]]
             [:multi_classing
              [:map
               [:prerequisites
                {:optional true}
                [:vector
                 [:map [:ability_score [:map [:index string?] [:name string?] [:url string?]]] [:minimum_score int?]]]]
               [:proficiencies [:vector [:map [:index string?] [:name string?] [:url string?]]]]
               [:proficiency_choices
                {:optional true}
                [:vector
                 [:map
                  [:choose int?]
                  [:type string?]
                  [:from [:vector [:map [:index string?] [:name string?] [:url string?]]]]]]]
               [:prerequisite_options
                {:optional true}
                [:map
                 [:type string?]
                 [:choose int?]
                 [:from
                  [:vector
                   [:map [:ability_score [:map [:index string?] [:name string?] [:url string?]]] [:minimum_score int?]]]]]]]]])

(def Rule-Sections [:map [:name string?] [:index string?] [:desc string?] [:url string?]])

(def Damage-Types [:map [:index string?] [:name string?] [:desc [:vector string?]] [:url string?]])

(def Equipment-Categories [:map
                          [:index string?]
                          [:name string?]
                          [:equipment [:vector [:map [:index string?] [:name string?] [:url string?]]]]
                          [:url string?]])

(def Backgrounds [:map
                 [:starting_equipment_options
                  [:vector
                   [:map
                    [:choose int?]
                    [:type string?]
                    [:from [:map [:equipment_category [:map [:index string?] [:name string?] [:url string?]]]]]]]]
                 [:ideals
                  [:map
                   [:choose int?]
                   [:type string?]
                   [:from
                    [:vector
                     [:map
                      [:desc string?]
                      [:alignments [:vector [:map [:index string?] [:name string?] [:url string?]]]]]]]]]
                 [:index string?]
                 [:starting_equipment
                  [:vector [:map [:equipment [:map [:index string?] [:name string?] [:url string?]]] [:quantity int?]]]]
                 [:starting_proficiencies [:vector [:map [:index string?] [:name string?] [:url string?]]]]
                 [:name string?]
                 [:bonds [:map [:choose int?] [:type string?] [:from [:vector string?]]]]
                 [:language_options
                  [:map
                   [:choose int?]
                   [:type string?]
                   [:from [:vector [:map [:index string?] [:name string?] [:url string?]]]]]]
                 [:personality_traits [:map [:choose int?] [:type string?] [:from [:vector string?]]]]
                 [:url string?]
                 [:feature [:map [:name string?] [:desc [:vector string?]]]]
                 [:flaws [:map [:choose int?] [:type string?] [:from [:vector string?]]]]])

(def Weapon-Properties [:map [:index string?] [:name string?] [:desc [:vector string?]] [:url string?]])

(def Conditions [:map [:index string?] [:name string?] [:desc [:vector string?]] [:url string?]])

(def Traits [:map
            [:desc [:vector string?]]
            [:races [:vector [:map [:index string?] [:name string?] [:url string?]]]]
            [:parent {:optional true} [:map [:index string?] [:name string?] [:url string?]]]
            [:index string?]
            [:name string?]
            [:trait_specific
             {:optional true}
             [:map
              [:spell_options
               {:optional true}
               [:map
                [:choose int?]
                [:from [:vector [:map [:index string?] [:name string?] [:url string?]]]]
                [:type string?]]]
              [:subtrait_options
               {:optional true}
               [:map
                [:choose int?]
                [:from [:vector [:map [:index string?] [:name string?] [:url string?]]]]
                [:type string?]]]
              [:damage_type {:optional true} [:map [:index string?] [:name string?] [:url string?]]]
              [:breath_weapon
               {:optional true}
               [:map
                [:name string?]
                [:desc string?]
                [:usage [:map [:type string?] [:times int?]]]
                [:dc [:map [:dc_type [:map [:index string?] [:name string?] [:url string?]]] [:success_type string?]]]
                [:damage
                 [:vector
                  [:map
                   [:damage_type [:map [:index string?] [:name string?] [:url string?]]]
                   [:damage_at_character_level [:map [:1 string?] [:6 string?] [:11 string?] [:16 string?]]]]]]]]]]
            [:proficiencies [:vector [:map [:index string?] [:name string?] [:url string?]]]]
            [:proficiency_choices
             {:optional true}
             [:map
              [:choose int?]
              [:type string?]
              [:from [:vector [:map [:index string?] [:name string?] [:url string?]]]]]]
            [:url string?]
            [:subraces [:vector [:map [:index string?] [:name string?] [:url string?]]]]])

(def Magic-Schools [:map [:index string?] [:name string?] [:desc string?] [:url string?]])

(def Ability-Scores [:map
                    [:index string?]
                    [:name string?]
                    [:full_name string?]
                    [:desc [:vector string?]]
                    [:skills [:vector [:map [:name string?] [:index string?] [:url string?]]]]
                    [:url string?]])

(def Proficiencies [:map
                   [:index string?]
                   [:type string?]
                   [:name string?]
                   [:classes [:vector [:map [:index string?] [:name string?] [:url string?]]]]
                   [:races [:vector [:map [:index string?] [:name string?] [:url string?]]]]
                   [:url string?]
                   [:reference [:map [:index string?] [:name string?] [:url string?]]]
                   [:references [:vector [:map [:index string?] [:name string?] [:type string?] [:url string?]]]]])

(def Magic-Items [:map
                 [:index string?]
                 [:name string?]
                 [:equipment_category [:map [:index string?] [:name string?] [:url string?]]]
                 [:desc [:vector string?]]
                 [:url string?]])

(def Rules [:map
           [:name string?]
           [:index string?]
           [:desc string?]
           [:subsections [:vector [:map [:name string?] [:index string?] [:url string?]]]]
           [:url string?]])

(def Alignments [:map [:index string?] [:name string?] [:abbreviation string?] [:desc string?] [:url string?]])

(def Spells [:map
            [:casting_time string?]
            [:desc [:vector string?]]
            [:dc
             {:optional true}
             [:map
              [:dc_type [:map [:index string?] [:name string?] [:url string?]]]
              [:dc_success string?]
              [:desc {:optional true} string?]]]
            [:index string?]
            [:school [:map [:index string?] [:name string?] [:url string?]]]
            [:name string?]
            [:concentration boolean?]
            [:attack_type {:optional true} string?]
            [:duration string?]
            [:level int?]
            [:damage
             {:optional true}
             [:map
              [:damage_type {:optional true} [:map [:index string?] [:name string?] [:url string?]]]
              [:damage_at_slot_level
               {:optional true}
               [:map
                [:4 {:optional true} string?]
                [:7 {:optional true} string?]
                [:1 {:optional true} string?]
                [:8 {:optional true} string?]
                [:9 {:optional true} string?]
                [:2 {:optional true} string?]
                [:5 {:optional true} string?]
                [:3 {:optional true} string?]
                [:6 {:optional true} string?]]]
              [:damage_at_character_level {:optional true} [:map [:1 string?] [:5 string?] [:11 string?] [:17 string?]]]]]
            [:components [:vector string?]]
            [:ritual boolean?]
            [:material {:optional true} string?]
            [:heal_at_slot_level
             {:optional true}
             [:map
              [:4 {:optional true} string?]
              [:7 {:optional true} string?]
              [:1 {:optional true} string?]
              [:8 {:optional true} string?]
              [:9 {:optional true} string?]
              [:2 {:optional true} string?]
              [:5 {:optional true} string?]
              [:3 {:optional true} string?]
              [:6 {:optional true} string?]]]
            [:higher_level {:optional true} [:vector string?]]
            [:url string?]
            [:area_of_effect {:optional true} [:map [:type string?] [:size int?]]]
            [:subclasses [:vector [:map [:index string?] [:name string?] [:url string?]]]]
            [:classes [:vector [:map [:index string?] [:name string?] [:url string?]]]]
            [:range string?]])

(def Levels [:map
            [:features [:vector [:map [:index string?] [:name string?] [:url string?]]]]
            [:subclass_specific
             {:optional true}
             [:map [:additional_magical_secrets_max_lvl {:optional true} int?] [:aura_range {:optional true} int?]]]
            [:index string?]
            [:class_specific
             {:optional true}
             [:map
              [:wild_shape_fly {:optional true} boolean?]
              [:rage_count {:optional true} int?]
              [:bardic_inspiration_die {:optional true} int?]
              [:magical_secrets_max_9 {:optional true} int?]
              [:wild_shape_max_cr {:optional true} number?]
              [:mystic_arcanum_level_8 {:optional true} int?]
              [:mystic_arcanum_level_9 {:optional true} int?]
              [:sneak_attack {:optional true} [:map [:dice_count int?] [:dice_value int?]]]
              [:rage_damage_bonus {:optional true} int?]
              [:song_of_rest_die {:optional true} int?]
              [:mystic_arcanum_level_6 {:optional true} int?]
              [:invocations_known {:optional true} int?]
              [:destroy_undead_cr {:optional true} number?]
              [:sorcery_points {:optional true} int?]
              [:unarmored_movement {:optional true} int?]
              [:magical_secrets_max_7 {:optional true} int?]
              [:creating_spell_slots
               {:optional true}
               [:vector [:map [:spell_slot_level int?] [:sorcery_point_cost int?]]]]
              [:martial_arts {:optional true} [:map [:dice_count int?] [:dice_value int?]]]
              [:indomitable_uses {:optional true} int?]
              [:mystic_arcanum_level_7 {:optional true} int?]
              [:arcane_recovery_levels {:optional true} int?]
              [:magical_secrets_max_5 {:optional true} int?]
              [:favored_terrain {:optional true} int?]
              [:metamagic_known {:optional true} int?]
              [:ki_points {:optional true} int?]
              [:channel_divinity_charges {:optional true} int?]
              [:action_surges {:optional true} int?]
              [:brutal_critical_dice {:optional true} int?]
              [:wild_shape_swim {:optional true} boolean?]
              [:extra_attacks {:optional true} int?]
              [:aura_range {:optional true} int?]
              [:favored_enemies {:optional true} int?]]]
            [:ability_score_bonuses {:optional true} int?]
            [:level int?]
            [:class [:map [:index string?] [:name string?] [:url string?]]]
            [:prof_bonus {:optional true} int?]
            [:spellcasting
             {:optional true}
             [:map
              [:spell_slots_level_6 {:optional true} int?]
              [:spell_slots_level_9 {:optional true} int?]
              [:spell_slots_level_4 int?]
              [:spell_slots_level_5 int?]
              [:spell_slots_level_8 {:optional true} int?]
              [:spell_slots_level_2 int?]
              [:spell_slots_level_7 {:optional true} int?]
              [:spells_known {:optional true} int?]
              [:spell_slots_level_3 int?]
              [:spell_slots_level_1 int?]
              [:cantrips_known {:optional true} int?]]]
            [:url string?]
            [:subclass {:optional true} [:map [:index string?] [:name string?] [:url string?]]]])

(def Features [:map
              [:desc [:vector string?]]
              [:parent {:optional true} [:map [:index string?] [:name string?] [:url string?]]]
              [:index string?]
              [:feature_specific
               {:optional true}
               [:map
                [:expertise_options
                 {:optional true}
                 [:map
                  [:choose int?]
                  [:type string?]
                  [:from [:vector [:map [:index string?] [:name string?] [:url string?]]]]]]
                [:subfeature_options
                 {:optional true}
                 [:map
                  [:choose int?]
                  [:type string?]
                  [:from [:vector [:map [:index string?] [:name string?] [:url string?]]]]]]]]
              [:name string?]
              [:level int?]
              [:reference {:optional true} string?]
              [:class [:map [:index string?] [:name string?] [:url string?]]]
              [:url string?]
              [:prerequisites
               [:vector
                [:map
                 [:type string?]
                 [:spell {:optional true} string?]
                 [:feature {:optional true} string?]
                 [:level {:optional true} int?]]]]
              [:subclass {:optional true} [:map [:index string?] [:name string?] [:url string?]]]])


