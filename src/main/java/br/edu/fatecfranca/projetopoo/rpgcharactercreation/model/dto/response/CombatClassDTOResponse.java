package br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.response;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.enums.AttributeEnum;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.enums.CasterTypeEnum;

import java.util.List;

public record CombatClassDTOResponse(Long id, String name, String caster, List<String> savingThrows, List<String>combatClassAbilities) {
}
