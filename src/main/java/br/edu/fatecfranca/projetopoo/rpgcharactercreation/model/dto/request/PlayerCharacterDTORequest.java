package br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.request;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.entity.CombatClassEntity;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.entity.PlayerCharacterEntity;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.entity.RaceEntity;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.enums.AttributeEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Optional;

import static br.edu.fatecfranca.projetopoo.rpgcharactercreation.utils.GlobalConstants.*;
import static br.edu.fatecfranca.projetopoo.rpgcharactercreation.utils.Mapper.zipToMap;

public record PlayerCharacterDTORequest(
  @NotBlank(message = "Player Character name can not be blank")
  String name,
  @Min(value = 1, message = "Level must be at least 1")
  @Max(value = 20, message = "Level must be at most 20")
  Integer level,
  @Min(value = 4, message = "Hit Point Dice must be at least 4")
  @Max(value = 12, message = "Hit Point Dice must be at most 12")
  Integer hpDice,
  @Size(min = 6, max = 6, message = "Attribute value must have exactly 6 elements")
  List<@Min(value = 1, message = "Attribute value must be at least 1")
  @Max(value = 20, message = "Attribute value must be at most 20")
  Integer> attributesValue,
  Long raceId,
  Long combatClassId) {
  public PlayerCharacterEntity toEntity(RaceEntity race, CombatClassEntity combatClass){
    var calculatedHp = this.level *
        (this.hpDice + ATTRIBUTE_MODIFIER_MAP.get(this.attributesValue.get(0))
          + Optional.ofNullable(race.getBonus().get(AttributeEnum.CONSTITUTION)).orElse(0));
    var proficiencyBonus = PROFICIENCY_VALUE_MAP.get(level);
    var mapAttributesValues = zipToMap(ATTRIBUTES_TYPES, this.attributesValue);
    return new PlayerCharacterEntity(
      this.name, this.level, calculatedHp, proficiencyBonus, mapAttributesValues, race, combatClass);
  }
}
