package br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.entity;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.response.PlayerCharacterDTOResponse;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.enums.AttributeEnum;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static br.edu.fatecfranca.projetopoo.rpgcharactercreation.utils.Mapper.getMapOfAttributeModifier;
import static br.edu.fatecfranca.projetopoo.rpgcharactercreation.utils.Mapper.getMapOfAttributes;

@Entity
@Table(name = "tb_player_character")
public class PlayerCharacterEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String name;
  private Integer level;
  private Integer hitPoints;
  private Integer proficiencyBonus;
  @ElementCollection
  @CollectionTable(name = "player_attributes", joinColumns = @JoinColumn(name = "player_id"))
  @MapKeyColumn(name = "attribute")
  @MapKeyEnumerated(EnumType.STRING)
  @Column(name = "value")
  private Map<AttributeEnum, Integer> attributes = new HashMap<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "race_id")
  private RaceEntity race;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "combat_class_id")
  private CombatClassEntity combatClass;

  public PlayerCharacterEntity() {
  }

  public PlayerCharacterEntity(String name, Integer level, Integer hitPoints, Integer proficiencyBonus, Map<AttributeEnum, Integer> attributes, RaceEntity race, CombatClassEntity combatClass) {
    this.name = name;
    this.level = level;
    this.hitPoints = hitPoints;
    this.proficiencyBonus = proficiencyBonus;
    this.attributes = attributes;
    this.race = race;
    this.combatClass = combatClass;
  }

  public PlayerCharacterDTOResponse toResponse() {
    sumRaceAttributes();
    return new PlayerCharacterDTOResponse(this.id.toString(), this.name, this.level, this.hitPoints, this.proficiencyBonus,
      getMapOfAttributes(this.attributes), getMapOfAttributeModifier(this.attributes),
      this.race.toResponse(), this.combatClass.toResponse());
  }

  public PlayerCharacterEntity update(PlayerCharacterEntity playerCharacter) {
    this.name = playerCharacter.getName();
    this.level = playerCharacter.getLevel();
    this.hitPoints = playerCharacter.getHitPoints();
    this.proficiencyBonus = playerCharacter.getProficiencyBonus();
    this.attributes = playerCharacter.getAttributes();
    this.race = playerCharacter.getRace();
    this.combatClass = playerCharacter.getCombatClass();
    return this;
  }

  public UUID getId() {
    return id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public Integer getHitPoints() {
    return hitPoints;
  }

  public void setHitPoints(Integer hitPoints) {
    this.hitPoints = hitPoints;
  }

  public Integer getProficiencyBonus() {
    return proficiencyBonus;
  }

  public void setProficiencyBonus(Integer proficiencyBonus) {
    this.proficiencyBonus = proficiencyBonus;
  }

  public Map<AttributeEnum, Integer> getAttributes() {
    return attributes;
  }

  public void setAttributes(Map<AttributeEnum, Integer> attributes) {
    this.attributes = attributes;
  }

  public RaceEntity getRace() {
    return race;
  }

  public void setRace(RaceEntity race) {
    this.race = race;
  }

  public CombatClassEntity getCombatClass() {
    return combatClass;
  }

  public void setCombatClass(CombatClassEntity combatClass) {
    this.combatClass = combatClass;
  }

  public void sumRaceAttributes() {
    this.race.getBonus().forEach((key, value) ->
      this.attributes.put(key, value + this.attributes.get(key)));
  }
}
