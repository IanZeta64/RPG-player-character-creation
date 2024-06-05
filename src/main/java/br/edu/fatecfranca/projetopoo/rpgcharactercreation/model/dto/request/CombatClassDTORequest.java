package br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.request;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.entity.CombatClassEntity;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.enums.AttributeEnum;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.enums.CasterTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CombatClassDTORequest(
  @NotBlank(message = "Caster's type can not be blank")
  @Pattern(regexp = "^(FULL|HALF|QUARTER|NULL)+$", message = "The attribute should be 'FULL', 'HALF', 'QUARTER' or 'NULL'")
  String caster,
  @NotBlank(message = "Race's name can not be blank")
  String name,
  @Size(min = 2, max = 2, message = "Saving Throw attribute type must have exactly 2 elements")
  List<@NotBlank(message = "Saving Throw attribute cannot be blank")
  @Pattern(regexp = "^(STRENGTH|DEXTERITY|CONSTITUTION|INTELLIGENCE|WISDOM|CHARISMA)$",
    message = "Saving Throw attribute type should be 'STRENGTH', 'DEXTERITY', 'CONSTITUTION', 'INTELLIGENCE', 'WISDOM', or 'CHARISMA'")
    String> savingThrow,
  @Size(min = 3, message = "At least 3 combat class abilities type must be provided")
  List<String> combatClassAbilities) {
  public CombatClassEntity toEntity() {
    return new CombatClassEntity(CasterTypeEnum.valueOf(this.caster), this.name, this.savingThrow.stream().map(AttributeEnum::valueOf).toList(), this.combatClassAbilities);
  }
}
