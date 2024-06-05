package br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.entity;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.response.CombatClassDTOResponse;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.enums.AttributeEnum;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.enums.CasterTypeEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_combat_class")
public class CombatClassEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private CasterTypeEnum caster;

  private String name;

  @ElementCollection
  @CollectionTable(name = "saving_throw_proficiency", joinColumns = @JoinColumn(name = "combat_class_id"))
  @Enumerated(EnumType.STRING)
  @Column(name = "saving_throw")
  private List<AttributeEnum> savingThrowProficiency;

  @ElementCollection
  @CollectionTable(name = "combat_class_abilities", joinColumns = @JoinColumn(name = "combat_class_abilities"))
  @Column(name = "ability", length = 1000)
  private List<String> combatClassAbilities;
  public CombatClassEntity() {
  }
  public CombatClassEntity(CasterTypeEnum caster, String name, List<AttributeEnum> savingThrowProficiency,List<String> combatClassAbilities) {
    this.caster = caster;
    this.name = name;
    this.savingThrowProficiency = savingThrowProficiency;
    this.combatClassAbilities = combatClassAbilities;
  }

  public CombatClassEntity(Long id, CasterTypeEnum caster, String name, List<AttributeEnum> savingThrowProficiency, List<String> combatClassAbilities) {
    this.id = id;
    this.caster = caster;
    this.name = name;
    this.savingThrowProficiency = savingThrowProficiency;
    this.combatClassAbilities = combatClassAbilities;
  }

  public CombatClassDTOResponse toResponse(){
    return new CombatClassDTOResponse(this.id, this.name,
      this.caster.toString(), this.savingThrowProficiency.stream().map(Enum::toString).toList(),
      this.combatClassAbilities);
  }
  public CombatClassEntity update(CombatClassEntity combatClass){
    this.caster = combatClass.getCaster();
    this.name = combatClass.getName();
    this.savingThrowProficiency = combatClass.getSavingThrowProficiency();
    this.combatClassAbilities = combatClass.getCombatClassAbilities();
    return this;
  }
  public Long getId() {
    return id;
  }

  public CasterTypeEnum getCaster() {
    return caster;
  }

  public String getName() {
    return name;
  }

  public List<String> getCombatClassAbilities() {
    return combatClassAbilities;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setCaster(CasterTypeEnum caster) {
    this.caster = caster;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCombatClassAbilities(List<String> combatClassAbilities) {
    this.combatClassAbilities = combatClassAbilities;
  }

  public List<AttributeEnum> getSavingThrowProficiency() {
    return savingThrowProficiency;
  }

  public void setSavingThrowProficiency(List<AttributeEnum> savingThrowProficiency) {
    this.savingThrowProficiency = savingThrowProficiency;
  }
}
