package br.edu.fatecfranca.projetopoo.rpgcharactercreation.service;

import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.request.PlayerCharacterDTORequest;
import br.edu.fatecfranca.projetopoo.rpgcharactercreation.model.dto.response.PlayerCharacterDTOResponse;

import java.util.List;

public interface PlayerCharacterService {
  PlayerCharacterDTOResponse save(PlayerCharacterDTORequest request);

  List<PlayerCharacterDTOResponse> getAll();

  PlayerCharacterDTOResponse getById(String uuid);

  List<PlayerCharacterDTOResponse> findByName(String name);

  PlayerCharacterDTOResponse update(String uuid, PlayerCharacterDTORequest request);

  PlayerCharacterDTOResponse changeName(String uuid, String name);

  PlayerCharacterDTOResponse levelUp(String uuid);

  PlayerCharacterDTOResponse changeAttribute(String uuid, String attributeEnum, Integer value);

  PlayerCharacterDTOResponse changeRace(String uuid, Long raceId);

  PlayerCharacterDTOResponse changeCombatClass(String uuid, Long combatClassId);

  void delete(String uuid);
}
