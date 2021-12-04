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
              [:map [:ability_score Link] [:minimum_score int?]]]]
            [:desc [:vector string?]]
            [:url string?]])

(def Races [:map
            [:starting_proficiency_options
             {:optional true}
             [:map
              [:choose int?]
              [:type string?]
              [:from [:vector Link]]]]
            [:age string?]
            [:index string?]
            [:speed int?]
            [:starting_proficiencies [:vector Link]]
            [:name string?]
            [:alignment string?]
            [:language_options
             {:optional true}
             [:map
              [:choose int?]
              [:type string?]
              [:from [:vector Link]]]]
            [:size string?]
            [:size_description string?]
            [:ability_bonus_options
             {:optional true}
             [:map
              [:choose int?]
              [:type string?]
              [:from
               [:vector [:map [:ability_score Link] [:bonus int?]]]]]]
            [:url string?]
            [:languages [:vector Link]]
            [:subraces [:vector Link]]
            [:language_desc string?]
            [:ability_bonuses
             [:vector [:map [:ability_score Link] [:bonus int?]]]]
            [:traits [:vector Link]]]),

(def Equipment [:map
               [:properties {:optional true} [:vector Link]]
               [:weapon_category {:optional true} string?]
               [:special {:optional true} [:vector string?]]
               [:desc {:optional true} [:vector string?]]
               [:index string?]
               [:capacity {:optional true} string?]
               [:speed {:optional true} [:map [:quantity number?] [:unit string?]]]
               [:equipment_category Link]
               [:two_handed_damage
                {:optional true}
                [:map [:damage_dice string?] [:damage_type Link]]]
               [:gear_category {:optional true} Link]
               [:name string?]
               [:vehicle_category {:optional true} string?]
               [:weapon_range {:optional true} string?]
               [:stealth_disadvantage {:optional true} boolean?]
               [:armor_class {:optional true} [:map [:base int?] [:dex_bonus boolean?] [:max_bonus :any]]]
               [:armor_category {:optional true} string?]
               [:damage
                {:optional true}
                [:map [:damage_dice string?] [:damage_type Link]]]
               [:weight {:optional true} number?]
               [:url string?]
               [:tool_category {:optional true} string?]
               [:str_minimum {:optional true} int?]
               [:cost [:map [:quantity int?] [:unit string?]]]
               [:contents
                {:optional true}
                [:vector [:map [:item Link] [:quantity int?]]]]
               [:quantity {:optional true} int?]
               [:category_range {:optional true} string?]
               [:range {:optional true} [:map [:normal int?] [:long :any]]]
               [:throw_range {:optional true} [:map [:normal int?] [:long int?]]]])

(def Subclasses [:map
                [:index string?]
                [:class Link]
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
                   [:spell Link]]]]])

(def Monster [:map
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
                   [:dc_type Link]
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
                        [:dc_type Link]
                        [:dc_value int?]
                        [:success_type string?]]]
                      [:damage
                       {:optional true}
                       [:vector
                        [:map
                         [:damage_type Link]
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
                    [:damage_type {:optional true} Link]
                    [:damage_dice {:optional true} string?]
                    [:dc
                     {:optional true}
                     [:map
                      [:dc_type Link]
                      [:dc_value int?]
                      [:success_type string?]]]
                    [:choose {:optional true} int?]
                    [:type {:optional true} string?]
                    [:from
                     {:optional true}
                     [:vector
                      [:map [:damage_type Link] [:damage_dice string?]]]]]]]
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
                      [:dc_type Link]
                      [:dc_value int?]
                      [:success_type string?]]]
                    [:damage
                     {:optional true}
                     [:vector
                      [:map [:damage_type Link] [:damage_dice string?]]]]]]]
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
                   [:dc_type Link]
                   [:dc_value int?]
                   [:success_type string?]]]
                 [:spellcasting
                  {:optional true}
                  [:map
                   [:level {:optional true} int?]
                   [:ability Link]
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
                   [:map [:damage_type Link] [:damage_dice string?]]]]
                 [:attack_bonus {:optional true} int?]]]]
              [:size string?]
              [:damage_vulnerabilities [:vector string?]]
              [:hit_dice string?]
              [:proficiencies
               [:vector [:map [:value int?] [:proficiency Link]]]]
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
                   [:map [:damage_type Link] [:damage_dice string?]]]]
                 [:dc
                  {:optional true}
                  [:map
                   [:dc_type Link]
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
                   [:dc_type Link]
                   [:dc_value int?]
                   [:success_type string?]]]]]]
              [:forms {:optional true} [:vector Link]]
              [:condition_immunities [:vector Link]]
              [:subtype seqable?]])

(def Skill [:map
            [:index string?]
            [:name string?]
            [:desc [:vector string?]]
            [:ability_score Link]
            [:url string?]])

(def Language [:map
               [:index string?]
               [:name string?]
               [:type string?]
               [:typical_speakers [:vector string?]]
               [:script seqable?]
               [:url string?]
               [:desc {:optional true} string?]])


(def Subrace [:map
              [:desc string?]
              [:index string?]
              [:starting_proficiencies [:vector Link]]
              [:race Link]
              [:name string?]
              [:language_options
               {:optional true}
               [:map
                [:choose int?]
                [:from [:vector Link]]
                [:type string?]]]
              [:url string?]
              [:languages [:vector any?]]
              [:racial_traits [:vector Link]]
              [:ability_bonuses
               [:vector [:map [:ability_score Link] [:bonus int?]]]]])

(def Klass [:map
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
                     [:equipment {:optional true} Link]
                     [:quantity {:optional true} int?]
                     [:equipment_option
                      {:optional true}
                      [:map
                       [:choose int?]
                       [:type string?]
                       [:from [:map [:equipment_category Link]]]]]
                     [:prerequisites
                      {:optional true}
                      [:vector
                       [:map [:type string?] [:proficiency Link]]]]]
                    [:vector
                     [:map
                      [:equipment {:optional true} Link]
                      [:quantity {:optional true} int?]
                      [:equipment_option
                       {:optional true}
                       [:map
                        [:choose int?]
                        [:type string?]
                        [:from [:map [:equipment_category Link]]]]]]]]]
                  [:map [:equipment_category Link]]]]]]]
             [:saving_throws [:vector Link]]
             [:class_levels string?]
             [:index string?]
             [:starting_equipment
              [:vector [:map [:equipment Link] [:quantity int?]]]]
             [:name string?]
             [:hit_die int?]
             [:spells {:optional true} string?]
             [:proficiencies [:vector Link]]
             [:proficiency_choices
              [:vector
               [:map
                [:choose int?]
                [:type string?]
                [:from [:vector Link]]]]]
             [:spellcasting
              {:optional true}
              [:map
               [:level int?]
               [:spellcasting_ability Link]
               [:info [:vector [:map [:name string?] [:desc [:vector string?]]]]]]]
             [:url string?]
             [:subclasses [:vector Link]]
             [:multi_classing
              [:map
               [:prerequisites
                {:optional true}
                [:vector
                 [:map [:ability_score Link] [:minimum_score int?]]]]
               [:proficiencies [:vector Link]]
               [:proficiency_choices
                {:optional true}
                [:vector
                 [:map
                  [:choose int?]
                  [:type string?]
                  [:from [:vector Link]]]]]
               [:prerequisite_options
                {:optional true}
                [:map
                 [:type string?]
                 [:choose int?]
                 [:from
                  [:vector
                   [:map [:ability_score Link] [:minimum_score int?]]]]]]]]])

(def Rule-Section [:map [:name string?] [:index string?] [:desc string?] [:url string?]])

(def Damage-Type [:map [:index string?] [:name string?] [:desc [:vector string?]] [:url string?]])

(def Equipment-Categorie [:map
                          [:index string?]
                          [:name string?]
                          [:equipment [:vector Link]]
                          [:url string?]])

(def Background [:map
                 [:starting_equipment_options
                  [:vector
                   [:map
                    [:choose int?]
                    [:type string?]
                    [:from [:map [:equipment_category Link]]]]]]
                 [:ideals
                  [:map
                   [:choose int?]
                   [:type string?]
                   [:from
                    [:vector
                     [:map
                      [:desc string?]
                      [:alignments [:vector Link]]]]]]]
                 [:index string?]
                 [:starting_equipment
                  [:vector [:map [:equipment Link] [:quantity int?]]]]
                 [:starting_proficiencies [:vector Link]]
                 [:name string?]
                 [:bonds [:map [:choose int?] [:type string?] [:from [:vector string?]]]]
                 [:language_options
                  [:map
                   [:choose int?]
                   [:type string?]
                   [:from [:vector Link]]]]
                 [:personality_traits [:map [:choose int?] [:type string?] [:from [:vector string?]]]]
                 [:url string?]
                 [:feature [:map [:name string?] [:desc [:vector string?]]]]
                 [:flaws [:map [:choose int?] [:type string?] [:from [:vector string?]]]]])

(def Weapon-Propertie [:map [:index string?] [:name string?] [:desc [:vector string?]] [:url string?]])

(def Condition [:map [:index string?] [:name string?] [:desc [:vector string?]] [:url string?]])

(def Trait [:map
            [:desc [:vector string?]]
            [:races [:vector Link]]
            [:parent {:optional true} Link]
            [:index string?]
            [:name string?]
            [:trait_specific
             {:optional true}
             [:map
              [:spell_options
               {:optional true}
               [:map
                [:choose int?]
                [:from [:vector Link]]
                [:type string?]]]
              [:subtrait_options
               {:optional true}
               [:map
                [:choose int?]
                [:from [:vector Link]]
                [:type string?]]]
              [:damage_type {:optional true} Link]
              [:breath_weapon
               {:optional true}
               [:map
                [:name string?]
                [:desc string?]
                [:usage [:map [:type string?] [:times int?]]]
                [:dc [:map [:dc_type Link] [:success_type string?]]]
                [:damage
                 [:vector
                  [:map
                   [:damage_type Link]
                   [:damage_at_character_level [:map [:1 string?] [:6 string?] [:11 string?] [:16 string?]]]]]]]]]]
            [:proficiencies [:vector Link]]
            [:proficiency_choices
             {:optional true}
             [:map
              [:choose int?]
              [:type string?]
              [:from [:vector Link]]]]
            [:url string?]
            [:subraces [:vector Link]]])

(def Magic-School [:map [:index string?] [:name string?] [:desc string?] [:url string?]])

(def Ability-Score [:map
                    [:index string?]
                    [:name string?]
                    [:full_name string?]
                    [:desc [:vector string?]]
                    [:skills [:vector [:map [:name string?] [:index string?] [:url string?]]]]
                    [:url string?]])

(def Proficiencie [:map
                   [:index string?]
                   [:type string?]
                   [:name string?]
                   [:classes [:vector Link]]
                   [:races [:vector Link]]
                   [:url string?]
                   [:reference Link]
                   [:references [:vector [:map [:index string?] [:name string?] [:type string?] [:url string?]]]]])

(def Magic-Item [:map
                 [:index string?]
                 [:name string?]
                 [:equipment_category Link]
                 [:desc [:vector string?]]
                 [:url string?]])

(def Rule [:map
           [:name string?]
           [:index string?]
           [:desc string?]
           [:subsections [:vector [:map [:name string?] [:index string?] [:url string?]]]]
           [:url string?]])

(def Alignment [:map [:index string?] [:name string?] [:abbreviation string?] [:desc string?] [:url string?]])

(def Spell [:map
            [:casting_time string?]
            [:desc [:vector string?]]
            [:dc
             {:optional true}
             [:map
              [:dc_type Link]
              [:dc_success string?]
              [:desc {:optional true} string?]]]
            [:index string?]
            [:school Link]
            [:name string?]
            [:concentration boolean?]
            [:attack_type {:optional true} string?]
            [:duration string?]
            [:level int?]
            [:damage
             {:optional true}
             [:map
              [:damage_type {:optional true} Link]
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
            [:subclasses [:vector Link]]
            [:classes [:vector Link]]
            [:range string?]])

(def Level [:map
            [:features [:vector Link]]
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
            [:class Link]
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
            [:subclass {:optional true} Link]])

(def Feature [:map
              [:desc [:vector string?]]
              [:parent {:optional true} Link]
              [:index string?]
              [:feature_specific
               {:optional true}
               [:map
                [:expertise_options
                 {:optional true}
                 [:map
                  [:choose int?]
                  [:type string?]
                  [:from [:vector Link]]]]
                [:subfeature_options
                 {:optional true}
                 [:map
                  [:choose int?]
                  [:type string?]
                  [:from [:vector Link]]]]]]
              [:name string?]
              [:level int?]
              [:reference {:optional true} string?]
              [:class Link]
              [:url string?]
              [:prerequisites
               [:vector
                [:map
                 [:type string?]
                 [:spell {:optional true} string?]
                 [:feature {:optional true} string?]
                 [:level {:optional true} int?]]]]
              [:subclass {:optional true} Link]])


