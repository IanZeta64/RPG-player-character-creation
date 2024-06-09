package br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.response;

import java.util.List;
import java.util.Map;

public record RaceDTOResponse(Long id, String name, Map<String, Integer> bonus, List<String> raceAbilities) {
}
