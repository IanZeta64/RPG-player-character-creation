package br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.response;

import java.util.Map;

public record PlayerCharacterDTOResponse(String id, String name, Integer level, Integer hitPoints, Integer proficiencyBonus, Map<String, Integer> attributes, Map<String, Integer> attributesModifiers, RaceDTOResponse race, CombatClassDTOResponse combatClass) {
}
