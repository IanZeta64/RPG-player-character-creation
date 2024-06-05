package br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.request;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.entity.RaceEntity;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.enums.AttributeEnum;
import jakarta.validation.constraints.*;

import java.util.List;
import java.util.Map;

import static br.edu.fatecfranca.projetopoo.rpgcharactercreation.utils.Mapper.zipToMap;

public record RaceDTORequest(
  @NotBlank(message = "Race's name can not be blank")
  String name,
  @Size(min = 2, max = 2, message = "Attribute type must have exactly 2 elements")
  List<@NotBlank(message = "Attribute type cannot be blank")
  @Pattern(regexp = "^(STRENGTH|DEXTERITY|CONSTITUTION|INTELLIGENCE|WISDOM|CHARISMA)$",
    message = "Attribute type should be 'STRENGTH', 'DEXTERITY', 'CONSTITUTION', 'INTELLIGENCE', 'WISDOM', or 'CHARISMA'")
    String> attributeType,

  @Size(min = 2, max = 2, message = "Attribute bonus must have exactly 2 elements")
  List<@Min(value = 1, message = "Attribute bonus must be at least 1")
  @Max(value = 2, message = "Attribute bonus must be at most 2")
    Integer> attributeBonus,

  @Size(min = 2, message = "At least 2 race abilities type must be provided")
  List<String> raceAbilities) {
  public RaceEntity toEntity() {
    Map<AttributeEnum, Integer> attributeBonus = zipToMap(this.attributeType().stream().map(AttributeEnum::valueOf).toList(), this.attributeBonus());
    return new RaceEntity(this.name(), attributeBonus, this.raceAbilities());
  }
}
