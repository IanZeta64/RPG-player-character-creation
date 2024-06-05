package br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.entity;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.response.RaceDTOResponse;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.enums.AttributeEnum;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.edu.fatecfranca.projetopoo.rpgcharactercreation.utils.Mapper.getMapOfAttributes;

@Entity
@Table(name = "tb_race")
public class RaceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ElementCollection
  @CollectionTable(name = "race_bonus", joinColumns = @JoinColumn(name = "race_id"))
  @MapKeyColumn(name = "attribute")
  @MapKeyEnumerated(EnumType.STRING)
  @Column(name = "bonus")
  private Map<AttributeEnum, Integer> bonus = new HashMap<>();

  @ElementCollection
  @CollectionTable(name = "race_abilities", joinColumns = @JoinColumn(name = "race_id"))
  @Column(name = "ability")
  private List<String> raceAbilities;

  public RaceEntity(){

  }

  public RaceEntity(String name, Map<AttributeEnum, Integer> bonus, List<String> raceAbilities) {
    this.name = name;
    this.bonus = bonus;
    this.raceAbilities = raceAbilities;
  }

  public RaceEntity(Long id, String name, Map<AttributeEnum, Integer> bonus, List<String> raceAbilities) {
    this.id = id;
    this.name = name;
    this.bonus = bonus;
    this.raceAbilities = raceAbilities;
  }

  public RaceDTOResponse toResponse() {
    return new RaceDTOResponse(
      this.id, this.name, getMapOfAttributes(this.bonus), this.raceAbilities);
  }

  public RaceEntity update(RaceEntity race) {
    this.name = race.getName();
    this.bonus = race.getBonus();
    this.raceAbilities = race.getRaceAbilities();
    return this;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Map<AttributeEnum, Integer> getBonus() {
    return bonus;
  }

  public List<String> getRaceAbilities() {
    return raceAbilities;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setBonus(Map<AttributeEnum, Integer> bonus) {
    this.bonus = bonus;
  }

  public void setRaceAbilities(List<String> raceAbilities) {
    this.raceAbilities = raceAbilities;
  }
}
